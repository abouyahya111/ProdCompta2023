package FactureServiceProduction;

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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
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
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.SequenceurDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FacturePF;
import dao.entity.FactureServiceProduction;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.Sequenceur;
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
import javax.swing.JCheckBox;


public class AjouterFactureServiceProduction extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<Client> listClient =new ArrayList<Client>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	private List<DetailFacturePF> listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private List<DetailTransferStockMP> listDetailTransferMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferMPAfficher =new ArrayList<DetailTransferStockMP>();
	private List<Production> listProductionServiceFacture =new ArrayList<Production>();
	private List<DetailFactureServiceProduction> listdetailFactureServiceProduction =new ArrayList<DetailFactureServiceProduction>();
	
	private List<TransferStockMP > ListTransferStockMP =new ArrayList<TransferStockMP>();
	private List<Production > ListProductionServiceAfficher =new ArrayList<Production>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, Client> mapClient= new HashMap<>();
	private Map< String, Client> mapClientparCode= new HashMap<>();
	private Map< String, Boolean> mapServiceFacture = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private   JDateChooser datefin = new JDateChooser();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FacturePFDAO facturepfdao;
	private FacturePF facturePF;
    private DetailFacturePFDAO detailFacturePfdao;
    private ClientDAO clientdao;
	ChargeProduction chargeproduction;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private  JCheckBox checkmodepaiement = new JCheckBox();
	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private JDateChooser datedebut;
	private StockPFDAO stockpfDAO;
	private FactureServiceProductionDAO factureserviceproductionDAO;
	private DetailFactureServiceProductionDAO detailfactureserviceproductionDAO;
	 
	private CompteClientDAO compteclientdao;
	private ProductionDAO productiondao;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	 JComboBox comboclient = new JComboBox();
	private StockMPDAO stockMPDAO;
	 private PromotionDAO promotiondao=new PromotionDAOImpl();
	 private ParametreDAO parametreDAO;
	 private DepotDAO depotDAO;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjouterFactureServiceProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1495, 1062);
      
	
        try{ 
        	
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	facturepfdao=new FacturePFDAOImpl();
         	detailFacturePfdao=new DetailFacturePFDAOImpl();
         	clientdao=new ClientDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	productiondao=new ProductionDAOImpl();
         	listArticle=articleDAO.findAll();
         	factureserviceproductionDAO=new FactureServiceProductionDAOImpl();
         	detailfactureserviceproductionDAO=new DetailFactureServiceProductionDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	parametreDAO=new ParametreDAOImpl();
         	depotDAO=new DepotDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
		  
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		combodepot.addItem(depot.getLibelle());
		     		
		     		mapDepot.put(depot.getLibelle(), depot);
		     	
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
		
		JLabel lblConslterLesFactures = new JLabel("           Ajouter les Factures Service :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(226, 11, 861, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1343, 539);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {/*
				
				if(table.getSelectedRow()!=-1)
				{
TransferStockMP transferMP=ListTransferStockMPAfficher.get(table.getSelectedRow());
Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
listDetailTransferMPAfficher.clear();
for(int i=0;i<transferMP.getListDetailTransferMP().size();i++)
{
	if(transferMP.getListDetailTransferMP().get(i).getMagasinSouce().getId()==magasin.getId())
	{
		listDetailTransferMPAfficher.add(transferMP.getListDetailTransferMP().get(i));
	}
}
afficher_tableDetailTransferMP(listDetailTransferMPAfficher);
				
				
				}
				
			*/}
		});	
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Num OF","Date Facture Service","Article", "Quantite","Facturé"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
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
	    		
	    		if(combodepot.getSelectedIndex()!=-1 && !combodepot.getSelectedItem().equals(""))
	    		{
	    			
	    			chargerFactureService();
	    			
	    			
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le depot SVP !!!!!!","Erruer",JOptionPane.ERROR_MESSAGE);
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
	    layeredPane_2.setBounds(10, 57, 1343, 51);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(245, 13, 46, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     datedebut = new JDateChooser();
	     datedebut.setBounds(284, 11, 151, 26);
	     layeredPane_2.add(datedebut);
	     datedebut.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {}
	     });
	     datedebut.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {


	     		
	     	}
	     });
	     datedebut.setLocale(Locale.FRANCE);
	     datedebut.setDateFormatString("dd/MM/yyyy");
	     
	     JLabel lblAu = new JLabel("Au :");
	     lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblAu.setBounds(461, 13, 46, 24);
	     layeredPane_2.add(lblAu);
	     
	      datefin = new JDateChooser();
	     datefin.setLocale(Locale.FRANCE);
	     datefin.setDateFormatString("dd/MM/yyyy");
	     datefin.setBounds(512, 13, 151, 26);
	     layeredPane_2.add(datefin);
	     
	     	
	     	JLabel label_3 = new JLabel("Depot :");
	     	label_3.setBounds(692, 13, 56, 24);
	     	layeredPane_2.add(label_3);
	     	label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     	
	     	  combodepot = new JComboBox();
	     	  combodepot.setBounds(741, 14, 177, 24);
	     	  layeredPane_2.add(combodepot);
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
	     	  
	     	
	     	   combodepot.setSelectedIndex(-1);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		combodepot.setSelectedIndex(-1);
	     		datedebut.setCalendar(null);
	     		datefin.setCalendar(null);

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(657, 120, 106, 23);
	     add(button);
	     
	     JLayeredPane layeredPane = new JLayeredPane();
	     layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	     layeredPane.setBounds(10, 737, 1343, 51);
	     add(layeredPane);
	     
	      comboclient = new JComboBox();
	     comboclient.setSelectedIndex(-1);
	     comboclient.setBounds(628, 14, 183, 24);
	     layeredPane.add(comboclient);
	     
	     JLabel label_5 = new JLabel("Client :");
	     label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_5.setBounds(581, 14, 56, 24);
	     layeredPane.add(label_5);
	     
	     
	     JButton btnTransfereProdService = new JButton("Transfere Prod Service en Facture");
	     btnTransfereProdService.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		if(!remplirmapServicefacture())	{
					JOptionPane.showMessageDialog(null, "Il faut coché les Services à  facturé", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					
					 {
						 Client clientpf=mapClient.get(comboclient.getSelectedItem());
						 Depot depot=mapDepot.get(combodepot.getSelectedItem());
							 if(clientpf==null)
							{
								JOptionPane.showMessageDialog(null, "Veuillez selectionner le client SVP", "Erreur", JOptionPane.ERROR_MESSAGE);	
								return;
							}
							
							else
							{
								BigDecimal MonatantHT=BigDecimal.ZERO;
								Parametre coutService=parametredao.findByCode(Constantes.COUT_SERVICE);
								 Parametre nombre_facture_par_jour=parametredao.findByCode(Constantes.NOMBRE_FACTURE_PAR_JOUR);
								
								boolean trouve=false;
								for(int i=0;i<ListProductionServiceAfficher.size();i++)
								{
							Production productionService=ListProductionServiceAfficher.get(i);
									if(mapServiceFacture.containsKey(productionService.getNumOF()))
									{
										
										
										
									/*New Numerotation 2019
									 * String
									 * NumFacture=Utils.genererCodeFactureVente(productionService.getDate_debFabPre(
									 * )); if(NumFacture.equals("")) {
									 * 
									 * JOptionPane.showMessageDialog(null, "le "+
									 * productionService.getDate_debFabPre()
									 * +" est déja valider le nombre des factures Autorisé","Erreur",JOptionPane.
									 * ERROR_MESSAGE); return;
									 * 
									 * }
									 */
										
										
										
										
										boolean cadeau =false;
										Parametre PercentagePrixCadeau=parametredao.findByCode(Constantes.PERCENTAGE_PRIX_OFFRE);
										
										  Magasin  magasinElnasTeaPacking=null;
											
										  magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
										
										FactureServiceProduction factureServiceProduction=new FactureServiceProduction();
										trouve=true;
										BigDecimal QuantiteOffre=BigDecimal.ZERO;
										
										Production production=productiondao.findByNumOF(productionService.getNumOF(), productionService.getCodeDepot());
									
										
										
										
										//MonatantHT=MonatantHT.add(production.getCoutTotal());
										DetailFactureServiceProduction detailfactureserviceproduction=new DetailFactureServiceProduction();
										detailfactureserviceproduction.setArticle(productionService.getArticles().getLiblle());
										for(int j=0;j<production.getListCoutMP().size();j++)
										{
											CoutMP coutmp=production.getListCoutMP().get(j);
											
											if(coutmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
											{
												
												QuantiteOffre=QuantiteOffre.add(coutmp.getQuantiteOffre());
												
											}
											if(coutmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
											{
												
													if(coutmp.getMagasin()!=null)
													{
														if(coutmp.getMagasin().getLibelle().equals(magasinElnasTeaPacking.getLibelle()))
														{
															
															cadeau=true;
															
														}
														
													}
													
													
	    	                                   
												
																								
											}
											
											
										}
										  BigDecimal PrixOffre=BigDecimal.ZERO;
										 
										 if(production.getOffre()!=null)
										 {
											
											Promotion  promotion=promotiondao.findByCode(production.getOffre());
										
											  if(promotion!=null)
											  {
												  for(int j=0;j<promotion.getDetailEstimationPromo().size();j++)
												  {
													/* if(promotion.getDetailEstimationPromo().get(j).getMatierePremiere() .getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
													 {
													  QuantiteEnVrac=QuantiteEnVrac.add(promotion.getDetailEstimationPromo().get(j).getQuantite()); 
													 }*/
													 
													 if(production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI))
													 {
														 if(promotion.getDetailEstimationPromo().get(j).getMatierePremiere() .getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
														 {
															 StockMP stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().get(j).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
															 
															
															 if(cadeau==true)
															 {
																 PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.getDetailEstimationPromo().get(j).getQuantite()).multiply(PercentagePrixCadeau.getValeur().divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE))); 
 
															 }
															 
															 
														 }else
														 {
															/* StockMP stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
															 PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.getDetailEstimationPromo().get(i).getQuantite()).multiply(new BigDecimal(1.2))); */
														 }
														 
													 }else
													 {
														 StockMP stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().get(j).getMatierePremiere().getId(), production.getMagasinStockage().getId());
														 PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.getDetailEstimationPromo().get(j).getQuantite()).multiply(new BigDecimal(1.2))); 
													 }
													
												  }
												  
												  PrixOffre=PrixOffre.divide(production.getArticles().getCentreCout3(), RoundingMode.HALF_UP); // centercout3 est le poids de carton 
											  }
											  
										 }
										
										
										 Parametre parametre=parametreDAO.findByCode(COUT_SERVICE);
										 BigDecimal quantite=productionService.getQuantiteReel().add(QuantiteOffre);
										 
										 PrixOffre=PrixOffre.add(parametre.getValeur());
										 
										 MonatantHT=quantite.multiply(PrixOffre);
										detailfactureserviceproduction.setQuantite(productionService.getQuantiteReel().add(QuantiteOffre));
										detailfactureserviceproduction.setPrix(PrixOffre);
										detailfactureserviceproduction.setMontantHT(MonatantHT);
										detailfactureserviceproduction.setUtilisateur(utilisateur);
										detailfactureserviceproduction.setFactureService(factureServiceProduction);
										
										listdetailFactureServiceProduction.add(detailfactureserviceproduction);
										productionService.setService(Constantes.TRANSFERE_BL_FACTURE);
										productiondao.edit(productionService);
									
										
											
										//Production productionTmp=ListProductionServiceAfficher.get(table.getSelectedRow());
										
											for(int t=0;t<production.getListCoutMP().size();t++)
											{
												
												CoutMP coutMP=production.getListCoutMP().get(t);
												if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
												{
													
													
													DetailFactureServiceProduction detailfactureserviceproductionTmp=new DetailFactureServiceProduction();
													detailfactureserviceproductionTmp.setArticle(coutMP.getMatierePremier().getNom());									
													detailfactureserviceproductionTmp.setQuantite(coutMP.getQuantiteOffre());
													detailfactureserviceproductionTmp.setPrix(BigDecimal.ZERO);
													detailfactureserviceproductionTmp.setMontantHT(BigDecimal.ZERO);
													detailfactureserviceproductionTmp.setUtilisateur(utilisateur);
													detailfactureserviceproductionTmp.setFactureService(factureServiceProduction);							
													listdetailFactureServiceProduction.add(detailfactureserviceproductionTmp);
													
												}
														
											}
											
										
										SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
										 String date = dcn.format(production.getDate_debFabPre());
										 
										if(listdetailFactureServiceProduction.size()!=0)
										{
											//factureServiceProduction.setNumFacture(Utils.genererCodeFactureVente(production.getDate_debFabPre())); //New Numerotation 2019
											factureServiceProduction.setNumFacture(Utils.genererCodeFactureVente(date));
											factureServiceProduction.setClient(clientpf);
											factureServiceProduction.setCreerPar(utilisateur);
											factureServiceProduction.setDateFacture(production.getDate_debFabPre());
											factureServiceProduction.setDateSaisi(new Date());
											factureServiceProduction.setDepot(depot);
											factureServiceProduction.setMontantHT(MonatantHT);
											factureServiceProduction.setMontantTVA(MonatantHT.multiply(new BigDecimal(0.2)));
											factureServiceProduction.setMontantTTC(MonatantHT.multiply(new BigDecimal(1.2)));
										//	factureServiceProduction.setDetailFactureServiceProduction(listdetailFactureServiceProduction);
											factureServiceProduction.setEtat(Constantes.ETAT_REGLE);
											factureServiceProduction.setType(Constantes.TRANSFERE_BL_FACTURE);
											factureserviceproductionDAO.add(factureServiceProduction);
					
											for(int j=0;j<listdetailFactureServiceProduction.size();j++)
											{
												detailfactureserviceproductionDAO.add(listdetailFactureServiceProduction.get(j));
											}
											
										/*New Numerotation 2019
										 * Calendar c = Calendar.getInstance();
										 * 
										 * c.setTime(production.getDate_debFabPre()); int
										 * nbrjrs=c.get(Calendar.DAY_OF_YEAR); int nbrsunday = nbrjrs / 7; int
										 * nbrjrssansweekend=nbrjrs-nbrsunday;
										 * 
										 * 
										 * 
										 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
										 * 
										 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
										 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
										 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
										 * sequenceurDAO.edit(sequenceur); }else {
										 * 
										 * int valeur=((nbrjrssansweekend-1) *
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
										 */
											
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
											
										}
									
									}
								}
								
							
								if(trouve==true)
								{
									JOptionPane.showMessageDialog(null, "Production(s) Service Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
									//txtnumfacture.setText(factureServiceProduction.getNumFacture());
									chargerFactureService();
									
								}
								
							}
						
							}
					
				}
	     		
	     		
	     		
	     	}
	     });
	     btnTransfereProdService.setBounds(833, 11, 239, 30);
	     layeredPane.add(btnTransfereProdService);
	     btnTransfereProdService.setFont(new Font("Tahoma", Font.BOLD, 11));
	     
	     JXTitledSeparator titledSeparator = new JXTitledSeparator();
	     GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator.getLayout();
	     gridBagLayout.rowWeights = new double[]{0.0};
	     gridBagLayout.rowHeights = new int[]{0};
	     gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	     gridBagLayout.columnWidths = new int[]{0, 0, 0};
	     titledSeparator.setTitle("Information Facture");
	     titledSeparator.setBounds(10, 704, 1343, 30);
	     add(titledSeparator);
	   
	    if(utilisateur.getLogin().equals("admin"))
		  {
	    	Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
			  int k=0;
			  combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
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
				  combodepot.addItem("");
				  combodepot.addItem(depot.getLibelle());
				  mapDepot.put(depot.getLibelle(), depot);
		     	
			  }
		  }
		
		}
	
	boolean remplirmapServicefacture(){
		boolean trouve=false;
		int i=0;
				
		for(int j=0;j<table.getRowCount();j++){
			
			boolean regle=(boolean) table.getValueAt(j, 4);
			if(regle==true ){
				
				mapServiceFacture.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 4).toString()));
				i++;
				trouve=true;
			}
			
		}
		return trouve;
	}
	
	
	void initialiserFacture()
	{
		
		datedebut.setCalendar(null);
		datefin.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
	
	}
	
	void chargerFactureService()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");

		comboclient.removeAllItems();
		mapClient.clear();
		mapClientparCode.clear();
		if(datedebut.getDate()!=null && datefin.getDate()!=null)
		{	
			String d1=sdf.format(datedebut.getDate());
			String d2=sdf.format(datefin.getDate());
			
		if(!d1.equals(d2))
		{
			if(datefin.getDate().compareTo(datedebut.getDate())<0)
			{
				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!", "Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		
		}else if(datedebut.getDate()==null && datefin.getDate()!=null)
		{
			JOptionPane.showMessageDialog(null, "Veillez entrer la date debut SVP !!!", "Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		ListProductionServiceAfficher.clear();
		Depot depot=mapDepot.get(combodepot.getSelectedItem());
		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		
		ListProductionServiceAfficher=productiondao.listeProductionServiceTerminerbyDepotEntreDeuxDate(datedebut.getDate(), datefin.getDate(), ETAT_OF_TERMINER, depot.getCode(), ETAT_TRANSFER_STOCK_SERVICE);
		for(int i=0;i<ListProductionServiceAfficher.size();i++)
		{
			Production production=ListProductionServiceAfficher.get(i);
			Client client=clientdao.findClientByCodeClient(production.getMagasinPF().getCodeMachine());
			Client clientTMP=mapClientparCode.get(client.getCode());
			if(clientTMP==null)
			{
				comboclient.addItem(client.getNom());
				mapClient.put(client.getNom(), client);
				mapClientparCode.put(client.getCode(), client);
				
			}
		}
		afficher_tableProductionService(ListProductionServiceAfficher);
		
		
	}



	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num OF","Date Facture Service","Article", "Quantite","Facturé"
				}
			) {
			boolean[] columnEditables = new boolean[] {
					false,false,false,false,true
			};
			Class[] columnTypes = new Class[] {
					String.class,Date.class,String.class, Boolean.class
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
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
	
}
	
	
	

	
	
	void afficher_tableProductionService(List<Production> listProduction)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num OF","Date Facture Service","Article", "Quantite","Facturé"
				}
			) {
			boolean[] columnEditables = new boolean[] {
					false,false,false,false,true
			};
			Class[] columnTypes = new Class[] {
					String.class,Date.class,String.class,BigDecimal.class, Boolean.class
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
		 
		while(i<listProduction.size())
		{	
		Production production=listProduction.get(i);
		
		Date datefacture=production.getDate_debFabPre();
			
			Object []ligne={production.getNumOF() ,datefacture,production.getArticles().getLiblle(),production.getQuantiteReel(),false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	}


