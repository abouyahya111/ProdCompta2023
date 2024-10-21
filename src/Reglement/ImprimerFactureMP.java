package Reglement;

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
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureVenteMPDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
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
import dao.daoManager.DetailFactureVenteMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureVenteMP;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FacturePF;
import dao.entity.FactureVenteMP;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.StockPF;
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


public class ImprimerFactureMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<Client> listClient =new ArrayList<Client>();
	private List<DetailFactureVenteMP> listDetailFactureVenteMP =new ArrayList<DetailFactureVenteMP>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureVenteMP> listFactureVenteMP =new ArrayList<FactureVenteMP>();
	private List<Client> listClientPFParCode =new ArrayList<Client>();
	
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, Client> mapClient= new HashMap<>();
	private Map< String, Client> mapClientPFparCode= new 	HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FactureVenteMPDAO factureVenteMPdao;
	private FactureVenteMP factureVenteMP;
    private DetailFactureVenteMPDAO detailFactureVenteMPdao;
    private ClientDAO clientdao;
	ChargeProduction chargeproduction;
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
	private  JCheckBox checkmodepaiement = new JCheckBox();
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	 JButton btnSupprimer = new JButton();
	private   JComboBox comboClient ;
	private JTextField txtparnumfacture;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private  JComboBox combopardepot = new JComboBox();;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;
	private StockMPDAO stockMPDAO;
	private CompteClientDAO compteclientdao;
	private JTextField txttimbre;
	private JTextField txtnetapayer;
	private JComboBox comboparclient = new JComboBox();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ImprimerFactureMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1359, 1123);
      
	
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
         	factureVenteMPdao=new FactureVenteMPDAOImpl();
         	detailFactureVenteMPdao=new DetailFactureVenteMPDAOImpl();
         	clientdao=new ClientDAOImpl();
         	
         	stockMPDAO=new StockMPDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	listArticle=articleDAO.findAll();
         	
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       		"Article", "Prix Unitaire", "Quantite", "Montant HT", "Montant TVA", "Montant TTC"
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
				  		     	scrollPane.setBounds(10, 537, 1262, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 509, 1262, 30);
				  		     	add(titledSeparator);
		      
		     
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
String sommetowords="";



				
				 if(listDetailFactureVenteMP.size()!=0)
				 {
					
						
			  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
			  		    FactureVenteMP factureVenteMP=listFactureVenteMP.get(table.getSelectedRow());
						
						
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
						parameters.put("client", factureVenteMP.getClient().getNom());
						if(factureVenteMP.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
						{
							parameters.put("NumFacture", factureVenteMP.getNumFacture());
							parameters.put("type", "Facture N°    :");
							
						}else if(factureVenteMP.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							parameters.put("NumFacture", factureVenteMP.getNumBl());
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
						parameters.put("adresse", factureVenteMP.getClient().getAdresse());
						parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
						parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
						parameters.put("code",factureVenteMP.getClient().getCode());
						if(factureVenteMP.getClient().getIce()!=null)
						{
							parameters.put("iceclient",factureVenteMP.getClient().getIce());
						}else
						{
							parameters.put("iceclient","");
						}
						
						
						if(checkmodepaiement.isSelected()==true)
						{
							if(factureVenteMP.getModeReglement()!=null)
							{
								parameters.put("modepaiement", factureVenteMP.getModeReglement());
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
						
						
						
						JasperUtils.imprimerFactureVenteMP(listDetailFactureVenteMP,parameters);
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun MP pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
			}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(364, 812, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 408, 1252, 51);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(10, 13, 89, 24);
		layeredPane_1.add(label);
		
		txtnumfacture = new JTextField();
		txtnumfacture.setEditable(false);
		txtnumfacture.setColumns(10);
		txtnumfacture.setBounds(57, 12, 183, 26);
		layeredPane_1.add(txtnumfacture);
	
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(261, 13, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(301, 11, 181, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(492, 12, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(541, 13, 177, 24);
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
		  

		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(734, 12, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.setBounds(788, 13, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		   comboClient = new JComboBox();
		  comboClient.setSelectedIndex(-1);
		  comboClient.setBounds(1039, 13, 183, 24);
		  layeredPane_1.add(comboClient);
		  AutoCompleteDecorator.decorate(comboClient);
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(992, 13, 56, 24);
		  layeredPane_1.add(lblClient);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 375, 1252, 30);
		add(titledSeparator_2);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(992, 875, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1114, 875, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(885, 818, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(778, 818, 97, 26);
		add(lblTotalQuantite);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les BL par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 791, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1252, 218);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(table.getSelectedRow()!=-1)
				{
					if(listFactureVenteMP.size()!=0)
					{
						listStockMP.clear();
						 factureVenteMP=listFactureVenteMP.get(table.getSelectedRow()) ;
						
						txtnumfacture.setText(factureVenteMP.getNumFacture());
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
					        BigDecimal netapayer=BigDecimal.ZERO;
					        BigDecimal timber=BigDecimal.ZERO;
					        while(i<listDetailFactureVenteMP.size())
					        {
					        	 StockMP stockMP=stockMPDAO.findStockByMagasinMP(listDetailFactureVenteMP.get(i).getMatierePremiere().getId(),factureVenteMP.getMagasinDestination().getId());
					        	listStockMP.add(stockMP);
					        	  DetailFactureVenteMP detailFactureVenteMP=listDetailFactureVenteMP.get(i);
						          montanttotal=  montanttotal.add(detailFactureVenteMP.getMontantTTC());
						          sommequantite= sommequantite.add(detailFactureVenteMP.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFactureVenteMP.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFactureVenteMP.getMontantTVA());
					            
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
					        txttotalquantite.setText(sommequantite.setScale(2, RoundingMode.HALF_UP)+"");
					        txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
				  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
				  			if(factureVenteMP.getModeReglement()!=null)
				  			{
				  				if(factureVenteMP.getModeReglement().equals(Constantes.MODE_REGLEMENT_ESPECE))
					  			{
					  				timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
									timber=timber.multiply(new BigDecimal(txttotalmontantTTC.getText()));
					  			}else
					  			{
					  				timber=BigDecimal.ZERO;
					  			}
				  				
				  			}else
				  			{
				  				timber=BigDecimal.ZERO;
				  			}
				  			
				  			
							txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
							netapayer=timber.add(new BigDecimal(txttotalmontantTTC.getText()));
				  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
							
						afficher_tableDetailFactureVenteMP(listDetailFactureVenteMP);
						
						
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
				"Num Facture", "Date Facture", "Etat","Type", "Client", "Depot", "Magasin", "Montant TTC"
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
	    	if(comboparclient.getSelectedItem().equals("")
	    			&& txtparnumfacture.getText().equals("") 
	    			&& pardateChooser.getDate()==null
	    			&& combopardepot.getSelectedItem().equals(""))
	    	{
	    		
	    	
	    		initialiserFacture();
	    		InitialiseTableau();
	    		InitialiseTableauFacture();
	    		
	    	}else
	    	{
	    		
	    		Client client=mapClient.get(comboparclient.getSelectedItem());
	    		Depot depotDestination=mapparDepot.get(combopardepot.getSelectedItem());
	    		Depot depotSource=depotdao.findByCode(utilisateur.getCodeDepot());
	    		
	    		initialiserFacture();
	    		
	    		InitialiseTableau();
	    	
	    			listFactureVenteMP=factureVenteMPdao.findByNumFactureClientDateFactureDepotEtatRegleFacture(txtparnumfacture.getText(),client, pardateChooser.getDate(), depotSource,depotDestination);
	    		
	    			afficher_tableFactureVenteMP(listFactureVenteMP);
	    	}
	    				
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLabel label_2 = new JLabel("Total Montant TVA :");
	    label_2.setBounds(992, 844, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1114, 844, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1114, 812, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(992, 812, 105, 26);
	    add(label_5);
	    
	   
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1252, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num Facture :");
	    lblNumFacture.setBounds(32, 11, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumfacture = new JTextField();
	    txtparnumfacture.setBounds(138, 11, 151, 26);
	    layeredPane_2.add(txtparnumfacture);
	    util.Utils.copycoller(txtparnumfacture);
	    txtparnumfacture.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumfacture.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(322, 11, 64, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Date Facture :");
	    lblDateFacture.setBounds(604, 12, 97, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(711, 11, 151, 26);
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
	     combopardepot.setBounds(950, 13, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(888, 12, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	      comboparclient = new JComboBox();
	     comboparclient.setSelectedIndex(-1);
	     comboparclient.setBounds(379, 12, 213, 24);
	     layeredPane_2.add(comboparclient);
	     AutoCompleteDecorator.decorate(comboparclient);
	      checkmodepaiement = new JCheckBox("Imprimer avec Mode de Paiement");
	     checkmodepaiement.setFont(new Font("Tahoma", Font.BOLD, 11));
	     checkmodepaiement.setBounds(492, 813, 229, 30);
	     add(checkmodepaiement);
	     
	     JLabel lblTimbre = new JLabel("Timbre 0,25%       :");
	     lblTimbre.setBounds(992, 912, 112, 26);
	     add(lblTimbre);
	     
	     txttimbre = new JTextField();
	     txttimbre.setEditable(false);
	     txttimbre.setColumns(10);
	     txttimbre.setBounds(1114, 912, 136, 26);
	     add(txttimbre);
	     
	     JLabel lblNetAPayer = new JLabel("Net A Payer         :");
	     lblNetAPayer.setBounds(992, 951, 112, 26);
	     add(lblNetAPayer);
	     
	     txtnetapayer = new JTextField();
	     txtnetapayer.setEditable(false);
	     txtnetapayer.setColumns(10);
	     txtnetapayer.setBounds(1114, 951, 136, 26);
	     add(txtnetapayer);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		txtparnumfacture.setText("");
	     		
	     		pardateChooser.setCalendar(null);
	     		combopardepot.setSelectedItem("");
	     		comboparclient.setSelectedItem("");
	     		
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(657, 120, 106, 23);
	     add(button);
	   
	     listClient=clientdao.findAll();
	     comboClient.addItem("");
   		comboparclient.addItem("");
      	for(int i=0;i<listClient.size();i++)
      	{
      		Client client=listClient.get(i);
      		comboClient.addItem(client.getNom());
      		comboparclient.addItem(client.getNom());
      		mapClient.put(client.getNom(), client);
      	}
      	comboparclient.setSelectedItem("");
      	comboClient.setSelectedItem("");
      	
		  
			  listDepot=depotdao.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());
			  int k=0;
		     	 combodepot.addItem("");
		     	combopardepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		combodepot.addItem(depot.getLibelle());
		     		combopardepot.addItem(depot.getLibelle());
		     		mapDepot.put(depot.getLibelle(), depot);
		     	mapparDepot.put(depot.getLibelle(), depot);
		     		k++;
		     	}
		      
		 
		 
	     	
		  combodepot.setSelectedItem("");
		 combopardepot.setSelectedItem("");
      	
		}
	
	void initialiserFacture()
	{
		txtnumfacture.setText("");
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		comboClient.setSelectedIndex(-1);
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
	}


	
	void InitialiseTableau()
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article", "Prix Unitaire", "Quantite", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Etat", "Client", "Depot", "Magasin", "Montant TTC"
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
	
	
	
	void afficher_tableDetailFactureVenteMP(List<DetailFactureVenteMP> listDetailFacture)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article", "Prix Unitaire", "Quantite", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
		DetailFactureVenteMP detailfactureVenteMP=listDetailFacture.get(i);
			
			Object []ligne={detailfactureVenteMP.getMatierePremiere().getNom(),detailfactureVenteMP.getPrixUnitaire(),detailfactureVenteMP.getQuantite(),detailfactureVenteMP.getMontantHT(),detailfactureVenteMP.getMontantTVA(), detailfactureVenteMP.getMontantTTC()};

			modeleArticle.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tableFactureVenteMP(List<FactureVenteMP> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Etat","Type", "Client", "Depot", "Magasin", "Montant TTC"
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
		 
		while(i<listFacture.size())
		{	
		FactureVenteMP factureVenteMP=listFacture.get(i);
		
		Date datefacture=factureVenteMP.getDateFacture();
			
			Object []ligne={factureVenteMP.getNumFacture(),datefacture,factureVenteMP.getEtat(),factureVenteMP.getType(),factureVenteMP.getClient().getNom(),factureVenteMP.getDepotDestination().getLibelle(),factureVenteMP.getMagasinDestination().getLibelle(),factureVenteMP.getMontantTTC()};

			modelefacture.addRow(ligne);
			i++;
		}
}
	}


