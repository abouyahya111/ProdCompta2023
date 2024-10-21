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
import java.util.Calendar;
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
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureVenteMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
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
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureVenteMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureVenteMPDAO;
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
import dao.entity.DetailFactureVenteMP;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FacturePF;
import dao.entity.FactureVenteMP;
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

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;


public class ConsulterFactureVenteMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<Client> listClient =new ArrayList<Client>();

	private List<DetailFactureVenteMP> listDetailFactureVenteMP =new ArrayList<DetailFactureVenteMP>();
	private List<DetailFactureVenteMP> listDetailFactureVenteMPTMP =new ArrayList<DetailFactureVenteMP>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<StockMP> listArticleStockMP =new ArrayList<StockMP>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureVenteMP> listFactureVenteMP =new ArrayList<FactureVenteMP>();
	private List<FactureVenteMP> listFactureVenteMPTmp =new ArrayList<FactureVenteMP>();
	
	TransferStockMP transferStockMP ;
	private	List<DetailTransferStockMP> listDetailTransferMP= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferStockMPdao;
	private List<FactureVenteMP> listFactureVenteMPMAJ =new ArrayList<FactureVenteMP>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, Client> mapClient= new HashMap<>();
	private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
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
	private FactureVenteMPDAO factureVenteMPdao;
	private FactureVenteMP factureVenteMP;
private DetailFactureVenteMPDAO detailFactureVenteMPdao;
private ClientDAO clientdao;
	private JTextField txtcodemp;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtnumbl;
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
	 private ImageIcon imgSelectAll;
		private ImageIcon imgDeselectAll;
	 private JComboBox comboClient;
	private JTextField txtparnumbl;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private  JComboBox combopardepot;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;
	private JComboBox comboparfournisseur = new JComboBox();
	private JComboBox comboparclient;
	private StockMPDAO stockMPDAO;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private JTextField txtreduction;
	private ParametreDAO parametreDAO;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterFactureVenteMP() {
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
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             
             
             
            utilisateur=AuthentificationView.utilisateur;
            detailTransferStockMPdao=new DetailTransferMPDAOImpl();
            transferStockMPDAO=new TransferStockMPDAOImpl();
            
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	factureVenteMPdao=new FactureVenteMPDAOImpl();
         	detailFactureVenteMPdao=new DetailFactureVenteMPDAOImpl();
         	parametreDAO=new ParametreDAOImpl();
         	matierePremierDAO=new MatierePremierDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         
         	clientdao=new ClientDAOImpl();
         	
         	
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		txtcodemp.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0).toString());
       		combomp.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
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
       			"Code MP","Matière Première", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
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
				  		     	scrollPane.setBounds(10, 641, 1375, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 613, 1458, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 523, 1458, 58);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code MP :");
		  lblCodeArticle.setBounds(150, 11, 74, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(325, 11, 45, 26);
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
		      txtcodemp.setBounds(222, 11, 93, 26);
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
		      combomp.setBounds(370, 11, 218, 27);
		      layeredPane.add(combomp);
		      AutoCompleteDecorator.decorate(combomp);
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(598, 11, 62, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(664, 11, 101, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(775, 11, 45, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(811, 12, 87, 26);
		      layeredPane.add(txtPrix);
		      
		      JLabel lblRemise = new JLabel("Remise :");
		      lblRemise.setBounds(908, 12, 57, 26);
		      layeredPane.add(lblRemise);
		      
		      txtreduction = new JTextField();
		      txtreduction.setColumns(10);
		      txtreduction.setBounds(962, 11, 101, 26);
		      layeredPane.add(txtreduction);
		      
		      JLabel label_8 = new JLabel("%");
		      label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		      label_8.setBounds(1066, 11, 26, 26);
		      layeredPane.add(label_8);
		     
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal solde=BigDecimal.ZERO;
				if(txtnumbl.getText().equals(""))
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
				}else if(comboClient.getSelectedIndex()==-1)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Client SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}
				
				
				else if(listDetailFactureVenteMP.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les MP à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				Client client =mapClient.get(comboClient.getSelectedItem());
				
				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier La facture  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
						
					{
						FactureVenteMP factureMPTmp=listFactureVenteMP.get(table.getSelectedRow());
						
						//factureMPTmp.setNumFacture(txtnumbl.getText());
						factureMPTmp.setClient(client);
						factureMPTmp.setDepotDestination(depot);
						factureMPTmp.setMagasinDestination(magasin);
						factureMPTmp.setDateFacture(dateChooserfacture.getDate());
						factureMPTmp.setEtat(Constantes.ETAT_NON_REGLE);
						factureMPTmp.setType(Constantes.TYPE_BON_LIVRAISON);
						factureMPTmp.setMontantHT(new BigDecimal(txttotalmontantHT.getText()).setScale(6, RoundingMode.HALF_UP));
						factureMPTmp.setMontantTVA(new BigDecimal(txttotalmontantTVA.getText()).setScale(6, RoundingMode.HALF_UP));
						factureMPTmp.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
						//facturePFTmp.setDetailFacturePF(listDetailFacturePF);	
						factureMPTmp.setModifierPar(utilisateur);
					    factureVenteMPdao.edit(factureMPTmp);
					    
					    
					    /*
					    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
					    FactureVenteMP factureVenteMP=listFactureVenteMP.get(table.getSelectedRow());
						DetailCompteClient detailcompteclient=detailCompteClientdao.findByFacture(factureVenteMP.getId());
						detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(detailcompteclient.getMontantDebit()));
						compteclientdao.edit(detailcompteclient.getCompteClient());
						detailcompteclient.setCompteClient(client.getccompteClient);
						detailcompteclient.setUtilisateurMAJ(utilisateur);
						detailcompteclient.setDateMaj(new Date());
						detailcompteclient.setFournisseur(fournisseur);
						detailcompteclient.setMontantDebit(new BigDecimal(txttotalmontantTTC.getText()));
						detailcompteclient.setDesignation("Montant sur Facture N "+factureVenteMP.getNumFacture());
						detailcompteclient.setFacturepf(factureVenteMP);	
					    detailCompteClientdao.edit(detailcompteclient);
					    solde=clientPF.getCompteClient().getSolde().add(new BigDecimal(txttotalmontantTTC.getText()));
					    clientPF.getCompteClient().setSolde(solde);
					    compteclientdao.edit(clientPF.getCompteClient());
					    
					   */
					    
					    // update transfer stock produit fini 
					    transferStockMP.setModifierPar(utilisateur);
					   // transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
					    transferStockMPDAO.edit(transferStockMP);
					    
					    int i=0;
					    while(i<listStockMP.size())
					    {
					    	StockMP stockMP=stockMPDAO.findById(listStockMP.get(i).getId());
					    	stockMP.setStock(listStockMP.get(i).getStock());
					    	stockMPDAO.edit(stockMP);
					    	i++;
					    }
					    
					    JOptionPane.showMessageDialog(null, "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					    initialiserFacture();
					    initialiser();
					   
					  
					    listDetailFactureVenteMP.clear();
						InitialiseTableau();
						chargerListFacture();
								
						
					}
				
				
				
				
			
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(619, 917, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 493, 1458, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 408, 1458, 51);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(10, 13, 89, 24);
		layeredPane_1.add(label);
		
		txtnumbl = new JTextField();
		txtnumbl.setEditable(false);
		txtnumbl.setColumns(10);
		txtnumbl.setBounds(57, 12, 129, 26);
		layeredPane_1.add(txtnumbl);
	
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
		  
		
			  listDepot=depotdao.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		         Depot depot=listDepot.get(k);
		     		
			     combodepot.addItem(depot.getLibelle());
				     		
				 mapDepot.put(depot.getLibelle(), depot);
				   
		     		k++;
		     		
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
		  
		  JLabel lblClient_1 = new JLabel("Client :");
		  lblClient_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient_1.setBounds(859, 13, 79, 24);
		  layeredPane_1.add(lblClient_1);
		  
		   comboClient = new JComboBox();
		  comboClient.setSelectedIndex(-1);
		  comboClient.setBounds(910, 14, 176, 24);
		  layeredPane_1.add(comboClient);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 375, 1228, 30);
		add(titledSeparator_2);
		
	  
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(498, 592, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					

					boolean trouve=false;
					BigDecimal stock=BigDecimal.ZERO;
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
						
						 MatierePremier matierepremiere=mapMP.get(combomp.getSelectedItem());
				        
				         Magasin magasin=factureVenteMP.getMagasinSource();
				      
				         
				         for(int i=0;i<listDetailFactureVenteMP.size();i++)
				         {
				        	 DetailFactureVenteMP detailFactureventeMP =listDetailFactureVenteMP.get(i);
				        	 if(detailFactureventeMP.getMatierePremiere().getNom().equals(matierepremiere.getNom()))
				        	 {
				        		 trouve=true;
				        	 }
				         }
				         
				         if(trouve==false)
				         {
				        	 StockMP stockmp=stockMPDAO.findStockByMagasinMP(matierepremiere.getId(), magasin.getId());
				        	 if(stockmp!=null)
				        	 {
				        		 
				        	  	 if(stockmp.getStock().compareTo(new BigDecimal(txtquantite.getText()))<0)
					        	 {
					        		 JOptionPane.showMessageDialog(null, "Stock "+matierepremiere.getNom()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        		 return;
					        	 }else
					        	 { 
				        	 		 marge=new BigDecimal(txtPrix.getText()).subtract(stockmp.getPrixUnitaire()).divide(new BigDecimal(txtPrix.getText()), 6, RoundingMode.HALF_UP);
			        				 
					        		 marge=marge.multiply(new BigDecimal(100));
						        	 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
						        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
						        	 if(marge.compareTo(margemax.getValeur())>0 || marge.compareTo(margemin.getValeur())<0)
						        	 {
						        		 
						        		 int reponse = JOptionPane.showConfirmDialog(null, " Erreur de la marge Vous voulez vraiment valider le prix  ?", 
													"Satisfaction", JOptionPane.YES_NO_OPTION);
											 
											if(reponse == JOptionPane.YES_OPTION )
												
												
											{
												
												
											}else
											{
												 return;
											}
						        	 }
				        	 
				        	 
				        	 
				        	 
				        	 DetailFactureVenteMP detailFacture=new DetailFactureVenteMP();
				        	 if(!txtreduction.getText().equals(""))
					          {
					        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
					          }else
					          {
					        	  detailFacture.setReduction(BigDecimal.ZERO);  
					          }
				        	 
					          detailFacture.setMatierePremiere(matierepremiere);
					          detailFacture.setQuantite(new BigDecimal(txtquantite.getText()));
					           detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
					           detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())));
						        detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
						        detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
						      
						        if(!txtreduction.getText().equals(""))
						          {
						        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
						          }else
						          {
						        	  detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
						          }
						        
						        //detailFacture.setTypeVente(typevente);
					          
					           detailFacture.setFactureVenteMP(factureVenteMP);
					       //    detailFacture.setDateCreation(new Date());
					           
					           detailFacture.setUtilisateur(utilisateur);
					           listDetailFactureVenteMP.add(detailFacture);
					         detailFactureVenteMPdao.add(detailFacture);
					         
					           stock=stockmp.getStock().subtract(new BigDecimal(txtquantite.getText()));
					           stockmp.setStock(stock);
					           listStockMP.add(stockmp);
					           
					          afficher_tableDetailFactureVenteMP(listDetailFactureVenteMP);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        while(i<listDetailFactureVenteMP.size())
						        {
						        	 DetailFactureVenteMP detailFactureVenteMP=listDetailFactureVenteMP.get(i);
							          montanttotal=  montanttotal.add(detailFactureVenteMP.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureVenteMP.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureVenteMP.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureVenteMP.getMontantTVA());
						            
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						        txttotalquantite.setText(sommequantite+"");
						        txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");
					  			
					  			
					  		/// ajout transfer stock PF (Mouvement Stock PF)
					  			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
					  			detailTransferStockMP.setMatierePremier(matierepremiere);
					  			
					  			detailTransferStockMP.setMagasinSouce(magasin);
					  			detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
					  			detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));
					  			detailTransferStockMP.setTransferStockMP(transferStockMP);
								
								detailTransferStockMPdao.add(detailTransferStockMP);
								listDetailTransferMP.add(detailTransferStockMP);
					  			
					  			
					  			
					  			
						        initialiser();
								
					       }
				        	 }else
				        	 {
				        		 JOptionPane.showMessageDialog(null,  "Stock "+matierepremiere.getNom()+" introuvable dans le magasin "+magasin.getLibelle(),"Attention",JOptionPane.ERROR_MESSAGE);
				        	 }
				        	 
				      
				       
								
					       }else
					       {
					    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    	   return;
					       }
					       
				         }
				        
						
					
					
				}catch (NumberFormatException e) {
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
		   			

			   		BigDecimal stock=BigDecimal.ZERO;
			   		BigDecimal stocktmp=BigDecimal.ZERO;
			   		BigDecimal marge=BigDecimal.ZERO;
			   		if(tableArticle.getSelectedRow()!=-1)
			   		{
			   			boolean trouve=false;
						
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
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
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
							
							 MatierePremier matierepremiere=mapMP.get(combomp.getSelectedItem());
					        
					         Magasin magasin=factureVenteMP.getMagasinSource();
					       
					         
					         for(int i=0;i<listDetailFactureVenteMP.size();i++)
					         {
					        	 if(i!=tableArticle.getSelectedRow())
					        	 {
					        		 
					        		 DetailFactureVenteMP detailFactureVenteMP =listDetailFactureVenteMP.get(i);
						        	 if(detailFactureVenteMP.getMatierePremiere().getNom().equals(matierepremiere.getNom()))
						        	 {
						        		 trouve=true;
						        	 }
						        	
					        	 }
					        	 
					        	 
					         }
					         
					         if(trouve==false)
					         {
					        	 
					        	 StockMP stockMPtmp =listStockMP.get(tableArticle.getSelectedRow());
					        
					        	 stocktmp=stockMPtmp.getStock().add(listDetailFactureVenteMP.get(tableArticle.getSelectedRow()).getQuantite());
					        	 stockMPtmp.setStock(stocktmp);
					        	
					        	 StockMP stockmp=stockMPDAO.findStockByMagasinMP(matierepremiere.getId(), magasin.getId());
					        	 if(stockmp!=null)
					        	 {
					        		 
					            	 if(stockmp.getStock().compareTo(new BigDecimal(txtquantite.getText()))<0)
						        	 {
						        		 JOptionPane.showMessageDialog(null, "Stck "+matierepremiere.getNom()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        		 return;
						        	 }else
						        	 {
						        		 
						        		 marge=new BigDecimal(txtPrix.getText()).subtract(stockmp.getPrixUnitaire()).divide(new BigDecimal(txtPrix.getText()), 6, RoundingMode.HALF_UP);
				        				 
						        		 marge=marge.multiply(new BigDecimal(100));
							        	 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
							        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
							        	 if(marge.compareTo(margemax.getValeur())>0 || marge.compareTo(margemin.getValeur())<0)
							        	 {	
							        		 int reponse = JOptionPane.showConfirmDialog(null, " Erreur de la marge Vous voulez vraiment valider le prix  ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(reponse == JOptionPane.YES_OPTION )
													
													
												{
													
													
												}else
												{
													 return;
												} 
							        		
							        	 }
							        	 
					        	 
					        	 DetailTransferStockMP detailTransferStockMP=listDetailTransferMP.get(tableArticle.getSelectedRow());
					        	 DetailFactureVenteMP detailFacture= listDetailFactureVenteMP.get(tableArticle.getSelectedRow());
					        	  if(!txtreduction.getText().equals(""))
						          {
						        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
						          }else
						          {
						        	  detailFacture.setReduction(BigDecimal.ZERO);  
						          }
					        	  
					        	  // Modifier Transfer Stock PF
					        	  detailTransferStockMP.setMatierePremier(matierepremiere);
					        	  detailTransferStockMP.setMagasinSouce(magasin);
					        	  detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
					        	  detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));		
									detailTransferStockMPdao.edit(detailTransferStockMP);
									listDetailTransferMP.set(tableArticle.getSelectedRow(), detailTransferStockMP);
									
									
					        	 
						          detailFacture.setMatierePremiere(matierepremiere);
						          detailFacture.setQuantite(new BigDecimal(txtquantite.getText()));
						           detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						           detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())));
							       detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
							       detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
							     				       
							          if(!txtreduction.getText().equals(""))
							          {
							        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
							          }else
							          {
							        	  detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
							          }
						          // detailFacture.setTypeVente(typevente);
						          
						           detailFacture.setFactureVenteMP(factureVenteMP);
						       //    detailFacture.setDateCreation(new Date());
						           
						           detailFacture.setUtilisateur(utilisateur);	
						          
						           stock=stockmp.getStock().subtract(new BigDecimal(txtquantite.getText()));
						           
						           stockmp.setStock(stock);
						          
						           
						           listStockMP.set(tableArticle.getSelectedRow(), stockmp);
						           
						           listDetailFactureVenteMP.set(tableArticle.getSelectedRow(), detailFacture);
						          detailFactureVenteMPdao.edit(detailFacture);
						          afficher_tableDetailFactureVenteMP(listDetailFactureVenteMP);
						          int i=0;
							        BigDecimal montanttotal=BigDecimal.ZERO;
							        BigDecimal sommequantite=BigDecimal.ZERO;
							        BigDecimal montanttotalHT=BigDecimal.ZERO;
							        BigDecimal montanttotalTVA=BigDecimal.ZERO;
							        while(i<listDetailFactureVenteMP.size())
							        {
							        	 DetailFactureVenteMP detailFactureVenteMP=listDetailFactureVenteMP.get(i);
								          montanttotal=  montanttotal.add(detailFactureVenteMP.getMontantTTC());
								          sommequantite= sommequantite.add(detailFactureVenteMP.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFactureVenteMP.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFactureVenteMP.getMontantTVA());
							            
							            i++;
							        }
							       txttotalmontantTTC.setText(montanttotal+"");
							        txttotalquantite.setText(sommequantite+"");
							        txttotalmontantHT.setText(montanttotalHT+"");
						  			txttotalmontantTVA.setText(montanttotalTVA+"");
						  			initialiser();
									
						  			
					        		 
						        	 }
					        		 
					        	 }else
					        	 {
					        		 JOptionPane.showMessageDialog(null,  "Stock "+matierepremiere.getNom()+" introuvable dans le magasin "+magasin.getLibelle(),"Attention",JOptionPane.ERROR_MESSAGE);
					        	 }
					        	 
					        	
									
						       }else
						       {
						    	   JOptionPane.showMessageDialog(null, "MP déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						    	   return;
						       }
						       
								
					        	 
					         }
						}
			   			
			   		
		   			
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
							StockMP stockMPtmp =listStockMP.get(tableArticle.getSelectedRow());
					        stocktmp=stockMPtmp.getStock().add(listDetailFactureVenteMP.get(tableArticle.getSelectedRow()).getQuantite());
					        stockMPtmp.setStock(stocktmp);
					        
					        DetailFactureVenteMP detailfactureMP=listDetailFactureVenteMP.get(tableArticle.getSelectedRow());
					        listDetailFactureVenteMP.remove(tableArticle.getSelectedRow());
					  		detailFactureVenteMPdao.delete(detailfactureMP.getId());
					  		
					  		listStockMP.set(tableArticle.getSelectedRow(), stockMPtmp);
					  		DetailTransferStockMP detailtransferstockMP=listDetailTransferMP.get(tableArticle.getSelectedRow());
					  		detailTransferStockMPdao.delete(detailtransferstockMP.getId());
					  		listDetailTransferMP.remove(tableArticle.getSelectedRow());
					  		
					        afficher_tableDetailFactureVenteMP(listDetailFactureVenteMP);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        while(i<listDetailFactureVenteMP.size())
						        {
						        	 DetailFactureVenteMP detailFactureMP=listDetailFactureVenteMP.get(i);
							          montanttotal=  montanttotal.add(detailFactureMP.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureMP.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureMP.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureMP.getMontantTVA());
						            
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
		  btnSupprimer.setBounds(1395, 700, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(1116, 974, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1238, 974, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(1009, 917, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(902, 917, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1395, 670, 73, 24);
		add(btnModifier);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les Factures / BL de Vente MP par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(151, 11, 1234, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1458, 218);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				boolean trouve=false;
				if(table.getSelectedRow()!=-1)
				{
					if(listFactureVenteMP.size()!=0)
					{
						listStockMP.clear();
						 factureVenteMP=listFactureVenteMP.get(table.getSelectedRow()) ;
						 
						if(factureVenteMP.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
						{
							btnAjouter.setEnabled(false);
							btnModifier.setEnabled(false);
							btnSupprimer.setEnabled(false);
							buttonvalider.setEnabled(false);
							btninitialiser.setEnabled(false);
							trouve=true;
						}else if(factureVenteMP.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							btnAjouter.setEnabled(true);
							btnModifier.setEnabled(true);
							btnSupprimer.setEnabled(true);
							buttonvalider.setEnabled(true);
							btninitialiser.setEnabled(true);
							trouve=false;
						}
						
						 transferStockMP=transferStockMPDAO.findTransferByCode(factureVenteMP.getCodeTransfer());
						
						 listDetailTransferMP=detailTransferStockMPdao.findByTransferStockMP(transferStockMP.getId());
						 
						 
						txtnumbl.setText(factureVenteMP.getNumBl());
						dateChooserfacture.setDate(factureVenteMP.getDateFacture());
						
						combodepot.setSelectedItem(factureVenteMP.getDepotDestination().getLibelle());
						
					
						combomagasin.setSelectedItem(factureVenteMP.getMagasinDestination().getLibelle());
						
						
						comboClient.setSelectedItem(factureVenteMP.getClient().getNom());
						listDetailFactureVenteMP=detailFactureVenteMPdao.listeDetailFactureVenteByFacture(factureVenteMP.getId());
						
						 int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        while(i<listDetailFactureVenteMP.size())
					        {
					        	 StockMP stockMP=stockMPDAO.findStockByMagasinMP(listDetailFactureVenteMP.get(i).getMatierePremiere().getId(), factureVenteMP.getMagasinSource().getId());
					        	listStockMP.add(stockMP);
					        	  DetailFactureVenteMP detailFactureVenteMP=listDetailFactureVenteMP.get(i);
						          montanttotal=  montanttotal.add(detailFactureVenteMP.getMontantTTC());
						          sommequantite= sommequantite.add(detailFactureVenteMP.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFactureVenteMP.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFactureVenteMP.getMontantTVA());
					            
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal+"");
					        txttotalquantite.setText(sommequantite+"");
					        txttotalmontantHT.setText(montanttotalHT+"");
				  			txttotalmontantTVA.setText(montanttotalTVA+"");
							
				  			
				  			
						afficher_tableDetailFactureVenteMP(listDetailFactureVenteMP);
						if(trouve==false)
						{
							initialiser();
						}else
						{
							initialiserPourFacture();
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
				"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture"
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
	    	if(comboparclient.getSelectedItem().equals("")
	    			&& txtparnumbl.getText().equals("") 
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
	    label_2.setBounds(1116, 943, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1238, 943, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1238, 911, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(1116, 911, 105, 26);
	    add(label_5);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1458, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num BL :");
	    lblNumFacture.setBounds(221, 13, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumbl = new JTextField();
	    txtparnumbl.setBounds(327, 13, 116, 26);
	    layeredPane_2.add(txtparnumbl);
	    util.Utils.copycoller(txtparnumbl);
	    txtparnumbl.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumbl.setColumns(10);
	    
	    JLabel lblDateBl = new JLabel("Date BL :");
	    lblDateBl.setBounds(688, 12, 97, 24);
	    layeredPane_2.add(lblDateBl);
	    lblDateBl.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
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
	     
	     JLabel lblClient = new JLabel("Client :");
	     lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     lblClient.setBounds(453, 12, 79, 24);
	     layeredPane_2.add(lblClient);
	     
	      comboparclient = new JComboBox();
	     comboparclient.setSelectedIndex(-1);
	     comboparclient.setBounds(501, 14, 149, 24);
	     layeredPane_2.add(comboparclient);
    
  listMP=matierePremierDAO.findAll();
  combomp.addItem("");
  for(int j=0;j<listMP.size();j++)
  {
	 MatierePremier mp= listMP.get(j);
	 combomp.addItem(mp.getNom());
	 mapMP.put(mp.getNom(), mp);
	 mapCodeMP.put(mp.getCode(), mp);
	  
  }
 	
        
        listClient=clientdao.findListClientByCodeDepot(utilisateur.getCodeDepot());
        int i=0;
        comboparclient.addItem("");
        while(i<listClient.size())
        {
       	Client client= listClient.get(i);
       	comboClient.addItem(client.getNom());
       	comboparclient.addItem(client.getNom());
       mapClient.put(client.getNom(), client);
       i++;
       	 
        }
        comboClient.setSelectedIndex(-1);
        
      
	     
	      btninitialiser = new JButton("Initialiser");
	     btninitialiser.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     	initialiser();
	     	}
	     });
	     btninitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btninitialiser.setBounds(625, 592, 106, 23);
	     add(btninitialiser);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		txtparnumbl.setText("");
	     		comboparfournisseur.setSelectedItem("");
	     		pardateChooser.setCalendar(null);
	     		combopardepot.setSelectedItem("");
	     		
	     	
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(711, 120, 106, 23);
	     add(button);
	     
	     JButton btnSelectionnertout = new JButton();
	     btnSelectionnertout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 7);
	     		}
	    	
	     	}
	     });
	     btnSelectionnertout.setToolTipText("Selectionner Tout");
	     btnSelectionnertout.setBounds(1424, 119, 26, 26);
	     btnSelectionnertout.setIcon(imgDeselectAll);
	     add(btnSelectionnertout);
	     
	     JButton btnDeslectionnerTout = new JButton();
	     btnDeslectionnerTout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	     		
	     		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 7);
	     		}
	     	
	     	
	     	}
	     });
	     btnDeslectionnerTout.setToolTipText("deselectionner Tout");
	     btnDeslectionnerTout.setBounds(1395, 119, 26, 26);
	     btnDeslectionnerTout.setIcon(imgDeselectAll);
	     add(btnDeslectionnerTout);
	     
	     JButton button_1 = new JButton("Transfere BL en Facture");
	     button_1.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		boolean transferEnBL=false;
	    		maptransfereblfacture.clear();
	    		if(!remplirmaptransfereblfacture())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir les BL à transferer au facture", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					  Parametre nombre_facture_par_jour=parametreDAO.findByCode(Constantes.NOMBRE_FACTURE_PAR_JOUR);
					
					for(int i=0;i<listFactureVenteMP.size();i++)
					{
						FactureVenteMP factureVenteMP=listFactureVenteMP.get(i);
						if(factureVenteMP.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							if(maptransfereblfacture.containsKey(factureVenteMP.getNumBl()))
							{
								
								
						/////////////////////////////////////////////////////// New Numerotation 2019 /////////////////////////////////////////////////		
								
								//New Numerotation 2019
								  
								/*
								 * SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd"); String date =
								 * dcn.format(factureVenteMP.getDateFacture());
								 * 
								 * 
								 * if(factureVenteMP.getNumFacture()==null) {
								 * 
								 * String
								 * NumFacture=Utils.genererCodeFactureVente(factureVenteMP.getDateFacture());
								 * 
								 * if(NumFacture.equals("")) {
								 * 
								 * JOptionPane.showMessageDialog(null, "le "+ factureVenteMP.getDateFacture()
								 * +" est déja valider le nombre des factures Autorisé","Erreur",JOptionPane.
								 * ERROR_MESSAGE); return;
								 * 
								 * }
								 * 
								 * Calendar c = Calendar.getInstance();
								 * 
								 * c.setTime(factureVenteMP.getDateFacture()); int
								 * nbrjrs=c.get(Calendar.DAY_OF_YEAR); int nbrsunday = nbrjrs / 7; int
								 * nbrjrssansweekend=nbrjrs-nbrsunday;
								 * 
								 * 
								 * 
								 * 
								 * factureVenteMP.setNumFacture(NumFacture);
								 * factureVenteMP.setType(Constantes.TRANSFERE_BL_FACTURE);
								 * factureVenteMPdao.edit(factureVenteMP);
								 * 
								 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
								 * 
								 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
								 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
								 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
								 * sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
								 * nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
								 * sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
								 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
								 * sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
								 * 
								 * }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
								 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
								 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
								 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
								 * sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
								 * nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
								 * sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
								 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
								 * sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
								 * 
								 * }
								 * 
								 * transferEnBL=true; }else {
								 * 
								 * factureVenteMP.setType(Constantes.TRANSFERE_BL_FACTURE);
								 * factureVenteMPdao.edit(factureVenteMP); transferEnBL=true;
								 * 
								 * }
								 * 
								 * 
								 * if(transferEnBL==true) { JOptionPane.showMessageDialog(null,
								 * "Bon(s) de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
								 * INFORMATION_MESSAGE); listFactureVenteMPMAJ=factureVenteMPdao.findAll();
								 * 
								 * chargerListFacture();
								 * 
								 * }else { JOptionPane.showMessageDialog(null,
								 * "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
								 * return;
								 * 
								 * }
								 */
								  
								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
								  
								 
								
							
							
								
								  SimpleDateFormat dcn = new SimpleDateFormat("yyyy"); 
								  String date =dcn.format(factureVenteMP.getDateFacture());
								  if(factureVenteMP.getNumFacture()==null)
								  { String NumFacture=Utils.genererCodeFactureVente(date);
								  
								  factureVenteMP.setNumFacture(NumFacture);
								  factureVenteMP.setType(Constantes.TRANSFERE_BL_FACTURE);
								  factureVenteMPdao.edit(factureVenteMP);
								  
								  
								  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
								  
								  {
                                  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_FACTURE_VENTE_ETP); 
                                  if(sequenceur!=null) { int valeur=sequenceur.getValeur()+1; 
                                  sequenceur.setValeur(valeur);
								  sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
								  Sequenceur(); sequenceurTmp.setCode(date);
								  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
								  sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
								  
								  }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
								  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
								  Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
								  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
								  sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
								  Sequenceur(); sequenceurTmp.setCode(date);
								  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
								  sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
								  
								  }
								  
								  transferEnBL=true; }else {
								  
								  factureVenteMP.setType(Constantes.TRANSFERE_BL_FACTURE);
								  factureVenteMPdao.edit(factureVenteMP); transferEnBL=true;
								  
								  }
								  
								  
								  if(transferEnBL==true) { JOptionPane.showMessageDialog(null,
								  "Bon(s) de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
								  INFORMATION_MESSAGE); listFactureVenteMPMAJ=factureVenteMPdao.findAll();
								  
								  chargerListFacture();
								  
								  }else { JOptionPane.showMessageDialog(null,
								  "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
								  return;
								  
								  }
								 
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							}
						}
						
					}
					
				
					
					
					
						
				}
	    		
	    		
	     	}
	     });
	     button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	     button_1.setBounds(1264, 375, 167, 30);
	     add(button_1);
	   
	   
			  listDepot=depotdao.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());
			  int l=0;
			  combopardepot.addItem("");
		     	while (l<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(l);
		     	
		     				combopardepot.addItem(depot.getLibelle());
				     		
		     				mapparDepot.put(depot.getLibelle(), depot);
				
		     		l++;
		     		
		     	}
		 
		
		}
	
void chargerListFacture()
{
	
	boolean exist=false;
	
	Client client=mapClient.get(comboparclient.getSelectedItem());
	Depot depotDestination=mapparDepot.get(combopardepot.getSelectedItem());
	Depot depotSource=depotdao.findByCode(utilisateur.getCodeDepot());
	//initialiserFacture();
	initialiser();
	InitialiseTableau();
	listFactureVenteMP.clear();
	listFactureVenteMP=factureVenteMPdao.findByNumFactureClientDateFactureDepotEtatRegle(txtparnumbl.getText(),client, pardateChooser.getDate(), depotDestination,Constantes.ETAT_NON_REGLE,depotSource);
		
					//initialiserFacture();
		    		initialiser();
		    		InitialiseTableau();
		    		InitialiseTableauFacture();
				
					afficher_tableFactureVenteMP(listFactureVenteMP);
				
}

boolean remplirmaptransfereblfacture(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(table.getValueAt(j,8)!=null)
		{
			
			boolean regle=(boolean) table.getValueAt(j,8);
			if(regle==true ){
				
				maptransfereblfacture.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 8).toString()));
				i++;
				trouve=true;
			}
		}
		
		
	}
	return trouve;
}
	
	
	void initialiserFacture()
	{
		txtnumbl.setText("");
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		comboparclient.setSelectedItem("");
		
	}

	void initialiser()
	{
		txtcodemp.setText("");
		combomp.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	    
	}
	
	
	void initialiserPourFacture()
	{
		txtcodemp.setText("");
		combomp.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
	     txtreduction.setText("");
	    
	}
	
	void initialiserInfofactureEt()
	{
		  txttotalmontantTTC.setText("");
			txttotalquantite.setText("");
			txttotalmontantHT.setText("");
			txttotalmontantTVA.setText("");	
			comboClient.setSelectedIndex(-1);
			txtnumbl.setText("");
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
						"Num BL", "Date BL", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		
}
	
	
	
	void afficher_tableDetailFactureVenteMP(List<DetailFactureVenteMP> listDetailFactureVenteMP)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
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
		 
		while(i<listDetailFactureVenteMP.size())
		{	
		DetailFactureVenteMP detailfactureVenteMP=listDetailFactureVenteMP.get(i);
			
			Object []ligne={detailfactureVenteMP.getMatierePremiere().getCode(),detailfactureVenteMP.getMatierePremiere().getNom(),detailfactureVenteMP.getPrixUnitaire(),detailfactureVenteMP.getQuantite(),detailfactureVenteMP.getReduction(), detailfactureVenteMP.getMontantHT(),detailfactureVenteMP.getMontantTVA(), detailfactureVenteMP.getMontantTTC()};

			modeleArticle.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tableFactureVenteMP(List<FactureVenteMP> listFactureMP)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
					};
				 public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		int i=0;
		 
		while(i<listFactureMP.size())
		{	
		FactureVenteMP factureVenteMP=listFactureMP.get(i);
		
		Date datefacture=factureVenteMP.getDateFacture();
			if(factureVenteMP.getNumFacture()!=null)
			{
				Object []ligne={factureVenteMP.getNumBl(),datefacture,factureVenteMP.getType(),factureVenteMP.getNumFacture(), factureVenteMP.getClient().getNom(),factureVenteMP.getDepotDestination().getLibelle(),factureVenteMP.getMagasinDestination().getLibelle(),factureVenteMP.getMontantTTC()};
				modelefacture.addRow(ligne);
			}else
			{
				Object []ligne={factureVenteMP.getNumBl(),datefacture,factureVenteMP.getType()," ",factureVenteMP.getClient().getNom(),factureVenteMP.getDepotDestination().getLibelle(),factureVenteMP.getMagasinDestination().getLibelle(),factureVenteMP.getMontantTTC()};
				modelefacture.addRow(ligne);
			}

			
			i++;
		}
}
	}


