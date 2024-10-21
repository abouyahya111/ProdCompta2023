package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.CalculePrixEtatStockMPAHB;
import util.CalculePrixEtatStockMPETP;
import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockMPAHB;
import dao.entity.EtatStockMPETP;
import dao.entity.FactureServiceProduction;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;


public class ModifierCoutProductionServiceParNumFacture extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;	
////////////////////////////////////////////////////////////// Ahl Brahim ///////////////////////////////////////////////////////	
	
	private  List<EtatStockMPAHB> listEtatStockMPAHB = new ArrayList<EtatStockMPAHB>();
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//////////////////////////////////////////////////////////////El Nass Tea Packing ///////////////////////////////////////////////////////
	
private  List<EtatStockMPETP> listEtatStockMPETP = new ArrayList<EtatStockMPETP>();


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	private Map< String, Boolean> mapBonProduction = new HashMap<>();
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 
	 List<DetailResponsableProd> listEmployeGesnerique=new ArrayList<DetailResponsableProd>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	 List<DetailFactureServiceProduction> listDetailFactureServiceProduction=new ArrayList<DetailFactureServiceProduction>();
	 List<TransferStockPF> listTransferStockPF=new ArrayList<TransferStockPF>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFProduction=new ArrayList<DetailTransferProduitFini>();
	 List<TransferStockMP> listTransferStockMP=new ArrayList<TransferStockMP>();
	 List<DetailTransferProduitFini> listDetailTransferStockPF=new ArrayList<DetailTransferProduitFini>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<DetailFactureServiceProduction> listDetailFactureServiceProductionGroupByFacture=new ArrayList<DetailFactureServiceProduction>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	private CoutMPDAO coutMPDAO;
	private TransferStockMPDAO transferStockMPDAO ;
	private DetailTransferMPDAO detailTransferMPDAO ;
	private TransferStockPFDAO transferStockPFDAO;
	DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	private ParametreDAO parametreDAO;
	private PromotionDAO promotiondao = new PromotionDAOImpl();
	private DepotDAO depotDAO;
	private List<Client> listClient =new ArrayList<Client>();
	private Map< String, Client> mapClient= new HashMap<>();
	  JComboBox comboclient = new JComboBox();
	  JComboBox comboarticle = new JComboBox();
	  private List<Articles> listArticle =new ArrayList<Articles>();
		private Map< String, Articles> mapArticle = new HashMap<>();
		private Map< String, Articles> mapCodeArticle = new HashMap<>();
	  private List<Production> listProductionServiceArticle =new ArrayList<Production>();	
	  private List<Production> listProductionServiceByMagasinPF =new ArrayList<Production>();	
	  private JTextField txtcodearticle= new JTextField();
	  private JTextField txtcoutservice= new JTextField();
	  private ClientDAO clientdao;
	  private Map< String, Boolean> mapCoutServiceModifier = new HashMap<>();
	  private FactureServiceProductionDAO factureServiceProductiondao;
		 private DetailFactureServiceProductionDAO detailfactureServiceProductiondao;
		 private JTextField txtnumfacturedu;
		 private JTextField txtnumfactureau;
			private ImageIcon imgSelectAll;
			private ImageIcon imgDeselectAll;
			 JCheckBox tva = new JCheckBox("TVA");
			  JCheckBox SansTva = new JCheckBox("Sans TVA");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ModifierCoutProductionServiceParNumFacture() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	depotdao=new DepotDAOImpl();
        	coutMPDAO=new CoutMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	transferStockPFDAO=new TransferStockPFDAOImpl();
        	detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
        	parametreDAO = new ParametreDAOImpl();
        	depotDAO = new DepotDAOImpl();
        	 clientdao=new ClientDAOImpl();
        	 factureServiceProductiondao=new FactureServiceProductionDAOImpl();
             detailfactureServiceProductiondao=new DetailFactureServiceProductionDAOImpl();
        	 
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
            imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.setShowVerticalLines(false);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		     table.setRowHeightEnabled(true);
				  		     table.setBackground(new Color(255, 255, 255));
				  		     table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table.setColumnControlVisible(true);
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);
				  		     
				  		   modeleProd =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Num OF","Date", "Depot","Article","Statut", "Type production","Client","Modifier"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,false,true
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleProd); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(160);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(10, 300, 1324, 403);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Num OF", "Date","Depot","Article","Statut", "Type production"
				  			     	}
				  			     ) {
				  		  		boolean[] columnEditables = new boolean[] {
				  						false,false,false,false,false,false,true
				  				};
				  				Class[] columnTypes = new Class[] {
				  						String.class,Date.class,String.class,String.class,String.class,String.class
				  					};
				  				  public Class getColumnClass(int columnIndex) {
				  						return columnTypes[columnIndex];
				  					}
				  				public boolean isCellEditable(int row, int column) {
				  					return columnEditables[column];
				  				}
				  			};
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 115, 1324, 118);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(10, 18, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(249, 17, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		 
		dateDebutChooser.setBounds(37, 18, 202, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(282, 18, 184, 24);
		layeredPane.add(dateFinChooser);
		  
		  JLabel label_1 = new JLabel("Client  :");
		  label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  label_1.setBounds(492, 18, 70, 24);
		  layeredPane.add(label_1);
		  
		   comboclient = new JComboBox();
		  comboclient.setBounds(557, 18, 296, 24);
		  layeredPane.add(comboclient);
		  comboclient.addItem("");
		  AutoCompleteDecorator.decorate(comboclient);
		  JLabel label = new JLabel("Article :");
		  label.setFont(new Font("Tahoma", Font.BOLD, 12));
		  label.setBounds(721, 81, 70, 24);
		  layeredPane.add(label);
		  
		   comboarticle = new JComboBox();
		   comboarticle.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		if(!comboarticle.getSelectedItem().equals(""))
		   		{
		   			
		   			Articles articles=mapArticle.get(comboarticle.getSelectedItem());
		   			if(articles!=null)
		   			{
		   				
		   				txtcodearticle.setText(articles.getCodeArticle());
		   				
		   				
		   			}else
		   			{
		   				txtcodearticle.setText("");
		   			}
		   			
		   			
		   			
		   			
		   		}else
	   			{
	   				txtcodearticle.setText("");
	   			}
		   		
		   		
		   		
		   	}
		   });
		  comboarticle.setBounds(773, 82, 289, 24);
		  layeredPane.add(comboarticle);
		  comboarticle.addItem("");
		  AutoCompleteDecorator.decorate(comboarticle);
		  
		  txtcodearticle = new JTextField();
		  txtcodearticle.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyPressed(KeyEvent e) {
		  		
		  		

     			if(e.getKeyCode()==e.VK_ENTER)
    		{
     				
     					
    			if(!txtcodearticle.getText().equals(""))
    			{
    				//SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
    				Articles article=mapCodeArticle.get(txtcodearticle.getText().toUpperCase());
		    		
		    		if(article!=null)
		    		{	
		    			comboarticle.setSelectedItem(article.getLiblle());
		    			
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
		  txtcodearticle.setBounds(607, 79, 102, 26);
		  layeredPane.add(txtcodearticle);
		  
		  JLabel lblCode = new JLabel("Code Article :");
		  lblCode.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  lblCode.setBounds(501, 79, 97, 24);
		  layeredPane.add(lblCode);
		  
		  JLabel lblCoutService = new JLabel("Cout Service :");
		  lblCoutService.setBounds(1083, 81, 97, 24);
		  layeredPane.add(lblCoutService);
		  lblCoutService.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  
		  txtcoutservice = new JTextField();
		  txtcoutservice.setBounds(1189, 81, 102, 26);
		  layeredPane.add(txtcoutservice);
		  txtcoutservice.setColumns(10);
		  
		  txtnumfacturedu = new JTextField();
		  txtnumfacturedu.setColumns(10);
		  txtnumfacturedu.setBounds(138, 81, 102, 26);
		  layeredPane.add(txtnumfacturedu);
		  
		  JLabel lblNumFactureDu = new JLabel("Num Facture Du :");
		  lblNumFactureDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  lblNumFactureDu.setBounds(10, 81, 130, 24);
		  layeredPane.add(lblNumFactureDu);
		  
		  txtnumfactureau = new JTextField();
		  txtnumfactureau.setColumns(10);
		  txtnumfactureau.setBounds(377, 79, 102, 26);
		  layeredPane.add(txtnumfactureau);
		  
		  JLabel lblNumFactureAu = new JLabel("Num Facture Au :");
		  lblNumFactureAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  lblNumFactureAu.setBounds(249, 81, 130, 24);
		  layeredPane.add(lblNumFactureAu);
		  
		   tva = new JCheckBox("TVA");
		   tva.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		if(tva.isSelected()==true)
		   		{
		   			
		   			dateDebutChooser.setEnabled(false);
		   			dateFinChooser.setEnabled(false);
		   			txtnumfactureau.setEnabled(false);
		   			txtnumfacturedu.setEnabled(false);
		   			txtcodearticle.setEnabled(false);
		   			comboarticle.setEnabled(false);
		   			txtnumfactureau.setText("");
		   			txtnumfacturedu.setText("");
		   			txtcodearticle.setText("");
		   			comboarticle.setSelectedItem("");
		   			txtcoutservice.setText("");
		   			txtcoutservice.setEnabled(false);
		   			SansTva.setSelected(false);
		   			
		   		}else
		   		{
		   			
		   			dateDebutChooser.setEnabled(true);
		   			dateFinChooser.setEnabled(true);
		   			txtnumfactureau.setEnabled(true);
		   			txtnumfacturedu.setEnabled(true);
		   			txtcodearticle.setEnabled(true);
		   			comboarticle.setEnabled(true);
		   			txtcoutservice.setEnabled(true);
		   			txtnumfactureau.setText("");
		   			txtnumfacturedu.setText("");
		   			txtcodearticle.setText("");
		   			comboarticle.setSelectedItem("");
		   			
		   		}
		   		
		   		
		   		
		   		
		   	}
		   });
		  tva.setBounds(927, 20, 62, 23);
		  layeredPane.add(tva);
		  
		   SansTva = new JCheckBox("Sans TVA");
		   SansTva.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		

		   		
		   		if(SansTva.isSelected()==true)
		   		{
		   			
		   			dateDebutChooser.setEnabled(false);
		   			dateFinChooser.setEnabled(false);
		   			txtnumfactureau.setEnabled(false);
		   			txtnumfacturedu.setEnabled(false);
		   			txtcodearticle.setEnabled(false);
		   			comboarticle.setEnabled(false);
		   			txtnumfactureau.setText("");
		   			txtnumfacturedu.setText("");
		   			txtcodearticle.setText("");
		   			comboarticle.setSelectedItem("");
		   			txtcoutservice.setText("");
		   			txtcoutservice.setEnabled(false);
		   			
		   			tva.setSelected(false);
		   			
		   		}else
		   		{
		   			
		   			dateDebutChooser.setEnabled(true);
		   			dateFinChooser.setEnabled(true);
		   			txtnumfactureau.setEnabled(true);
		   			txtnumfacturedu.setEnabled(true);
		   			txtcodearticle.setEnabled(true);
		   			comboarticle.setEnabled(true);
		   			txtnumfactureau.setText("");
		   			txtnumfacturedu.setText("");
		   			txtcodearticle.setText("");
		   			comboarticle.setSelectedItem("");
		   			txtcoutservice.setEnabled(true);
		   			
		   		}
		   		
		   		
		   		
		   	}
		   });
		  SansTva.setBounds(1000, 20, 102, 23);
		  layeredPane.add(SansTva);
		  JButton btnImprimerDetailOf = new JButton("Valider");
		  btnImprimerDetailOf.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
boolean modifier=false;
		  		listDetailTransferStockPFProduction= detailTransferProduitFiniDAO.listDetailTransfertPFProductionByStatut(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP);
		  	try {
		  		
		  		Client client=mapClient.get(comboclient.getSelectedItem());
				
				
		  		
		  		if(tva.isSelected()==true || SansTva.isSelected()==true)
				{
		  			
		  			
		  			
		  		
		  			int reponse=JOptionPane.showConfirmDialog(null, "Voulez Vous Vraiment Modifier Le TVA ?", 
							"Attention", JOptionPane.YES_NO_OPTION);
	  				
	  				
if(reponse == JOptionPane.NO_OPTION )		
						
					{
						return;
					}
		  			
		  			

for(int i=0;i<listDetailFactureServiceProductionGroupByFacture.size();i++)
{
	
	
		
FactureServiceProduction factureServiceProduction=listDetailFactureServiceProductionGroupByFacture.get(i).getFactureService();			
if(factureServiceProduction!=null)	
{

listDetailFactureServiceProduction=detailfactureServiceProductiondao.listeDetailFactureServiceProductionByFacture(factureServiceProduction.getId());
BigDecimal montantHT=BigDecimal.ZERO;
BigDecimal montantTVA=BigDecimal.ZERO;
BigDecimal montantTTC=BigDecimal.ZERO;
for(int j=0;j<listDetailFactureServiceProduction.size();j++)
{
	
	
DetailFactureServiceProduction detailFactureServiceProduction=	listDetailFactureServiceProduction.get(j);
	
if(detailFactureServiceProduction.getMontantHT().compareTo(BigDecimal.ZERO)!=0)
{
		
	montantHT=montantHT.add(detailFactureServiceProduction.getMontantHT());
}


	
}
			
factureServiceProduction.setMontantHT(montantHT);




	if(tva.isSelected()==true)
	{
		factureServiceProduction.setMontantTVA(factureServiceProduction.getMontantHT().multiply(new BigDecimal("0.2")));
		factureServiceProduction.setMontantTTC(factureServiceProduction.getMontantHT().multiply(new BigDecimal("1.2")));
	}
	if(SansTva.isSelected()==true)
	{
		factureServiceProduction.setMontantTVA(BigDecimal.ZERO);
		factureServiceProduction.setMontantTTC(factureServiceProduction.getMontantHT());
	}
	
	


factureServiceProduction.setDateModifier(new Date());
			
factureServiceProductiondao.edit(factureServiceProduction);	


modifier=true;
}





		
		

			

	
	
}
	
	
	
	
if(modifier==true)
{
	
	
	
	JOptionPane.showMessageDialog(null, "TVA � �t� modifier avec succee ","Bravo",JOptionPane.INFORMATION_MESSAGE);
}
		  			
		  			
		  			
		  			
		  			
					if(tva.isSelected()==true)
					{
						client.setTva(true);
						clientdao.edit(client);
					}
					if(SansTva.isSelected()==true)
					{
						client.setTva(false);
						clientdao.edit(client);
						
					}
					
					
				}else
				{
					
					
			  		
			  		if(!txtcoutservice.getText().equals(""))
			  		{
			  			
			  			if(listDetailFactureServiceProductionGroupByFacture.size()!=0)
			  			{
			  				
			  			
			  			BigDecimal coutservice=new BigDecimal(txtcoutservice.getText())	;
			  			
			  				
			  			if(coutservice.compareTo(BigDecimal.ZERO)<=0)	
			  			{
			  				
			  				int reponse=JOptionPane.showConfirmDialog(null, "Attention le Cout est inf�rieur ou 0 Voulez vous continuer ?", 
									"Attention", JOptionPane.YES_NO_OPTION);
			  				
			  				
	if(reponse == JOptionPane.NO_OPTION )		
								
							{
								return;
							}
			  				
			  				
			  				
			  			}
			  				
			  				
			  			mapCoutServiceModifier.clear();
			    		if(!remplirmapservicemodifier())	{
							JOptionPane.showMessageDialog(null, "Il faut selectionner les Factures � modifier", "Erreur", JOptionPane.ERROR_MESSAGE);
						} else {
							
							
						for(int i=0;i<listDetailFactureServiceProductionGroupByFacture.size();i++)
						{
							Production production=productionDAO.findByNumOF(listDetailFactureServiceProductionGroupByFacture.get(i).getFactureService().getNumOF(), AuthentificationView.utilisateur.getCodeDepot());
							
							if(mapCoutServiceModifier.containsKey(listDetailFactureServiceProductionGroupByFacture.get(i).getFactureService().getNumFacture()))
							{
								
					FactureServiceProduction factureServiceProduction=listDetailFactureServiceProductionGroupByFacture.get(i).getFactureService();			
					if(factureServiceProduction!=null)	
					{
						
						listDetailFactureServiceProduction=detailfactureServiceProductiondao.listeDetailFactureServiceProductionByFacture(factureServiceProduction.getId());
						BigDecimal montantHT=BigDecimal.ZERO;
						BigDecimal montantTVA=BigDecimal.ZERO;
						BigDecimal montantTTC=BigDecimal.ZERO;
						for(int j=0;j<listDetailFactureServiceProduction.size();j++)
						{
							
							
						DetailFactureServiceProduction detailFactureServiceProduction=	listDetailFactureServiceProduction.get(j);
							
						if(detailFactureServiceProduction.getMontantHT().compareTo(BigDecimal.ZERO)!=0)
						{
							detailFactureServiceProduction.setPrix(new BigDecimal(txtcoutservice.getText()));
							detailFactureServiceProduction.setMontantHT(detailFactureServiceProduction.getQuantite().multiply(detailFactureServiceProduction.getPrix()));
							detailfactureServiceProductiondao.edit(detailFactureServiceProduction);
							
							montantHT=montantHT.add(detailFactureServiceProduction.getMontantHT());
						}
						
						
							
						}
									
						factureServiceProduction.setMontantHT(montantHT);
						
						
						
					
							
							if(factureServiceProduction.getClient().isTva()==true)
							{
								factureServiceProduction.setMontantTVA(factureServiceProduction.getMontantHT().multiply(new BigDecimal("0.2")));
								factureServiceProduction.setMontantTTC(factureServiceProduction.getMontantHT().multiply(new BigDecimal("1.2")));
								
							}else
							{
								factureServiceProduction.setMontantTVA(BigDecimal.ZERO);
								factureServiceProduction.setMontantTTC(factureServiceProduction.getMontantHT());
							}
							
						
							
							
						
						
						factureServiceProduction.setDateModifier(new Date());
									
						factureServiceProductiondao.edit(factureServiceProduction);	
						
						
						modifier=true;
					}
					
					listCoutMP=productionDAO.listeCoutMP(production.getId());
					BigDecimal PrixEnVrac=BigDecimal.ZERO;
					
					for(int d=0;d<listCoutMP.size();d++)
					{
						CoutMP coutMP=listCoutMP.get(d);
						
						if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  {
				  
				  PrixEnVrac= coutMP.getPrixUnitaire();
				  
				  
				  
			  }
					}
					
				boolean entrerproduction=false;
				
			String numOF="";
								
								
				for(int c=0;c<listDetailTransferStockPFProduction.size();c++)	
				{
					
					
					DetailTransferProduitFini detailTransferProduitFini=listDetailTransferStockPFProduction.get(c);
					
					if(detailTransferProduitFini.getTransferStockPF().getCodeTransfer().equals(production.getNumOF()))
					{
						
						if(detailTransferProduitFini.getSousFamille().getFamileArticlePF().getLiblle() .equals(Constantes.LIBELLE_FAMILLE_CHAARA) || detailTransferProduitFini.getSousFamille().getFamileArticlePF().getLiblle() .equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || detailTransferProduitFini.getSousFamille().getFamileArticlePF().getLiblle() .equals(Constantes.LIBELLE_FAMILLE_ELNASS))
						{
							
							detailTransferProduitFini.setPrixUnitaire(PrixEnVrac.add(new BigDecimal(txtcoutservice.getText())) );
							detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
						}	
						entrerproduction=true;
						numOF=production.getNumOF();
						modifier=true;
					}
					
					
					if(entrerproduction==true)
					{
						
						if(detailTransferProduitFini.getTypeTransfer().equals(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
						{
							if(!detailTransferProduitFini.getTransferStockPF().getCodeTransfer().equals(numOF))
							{
								
								
								entrerproduction=false;
								
								
							}
							
							
							
							
						}else if(detailTransferProduitFini.getTypeTransfer().equals( Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP))
						{
							
							if(detailTransferProduitFini.getSousFamille().getFamileArticlePF().getLiblle() .equals(Constantes.LIBELLE_FAMILLE_CHAARA) || detailTransferProduitFini.getSousFamille().getFamileArticlePF().getLiblle() .equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || detailTransferProduitFini.getSousFamille().getFamileArticlePF().getLiblle() .equals(Constantes.LIBELLE_FAMILLE_ELNASS))
							{
								
								detailTransferProduitFini.setPrixUnitaire(PrixEnVrac.add(new BigDecimal(txtcoutservice.getText())) );
								detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
							
								modifier=true;
							}
							
							
							
						}
						
						
					}
					
						
					
				}
									
							}
							
							
						}
							
							
							
							
						if(modifier==true)
						{
							
							
							
							JOptionPane.showMessageDialog(null, "Le cout Service � �t� modifier avec succee ","Bravo",JOptionPane.INFORMATION_MESSAGE);
						}
							
							
							
						}
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  			}else
			  			{
			  				JOptionPane.showMessageDialog(null, "la liste des Production est vide","Erreur",JOptionPane.ERROR_MESSAGE);
				  			return;
			  			}
			  			
			  			
			  			
			  			
			  			
			  		}else
			  		{
			  			JOptionPane.showMessageDialog(null, "veuillez Saisir le Cout Service SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			  			return;
			  		}
					
					
				}
		  		
		  		
		  		
		  		

		  		
		  		
		  		
		  		
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Le Cout Service Doit etre en Chiffre SVP","Error",JOptionPane.ERROR_MESSAGE);
			}
		  	
		  	
		  	
		  	}
		  });
		  btnImprimerDetailOf.setBounds(444, 727, 163, 24);
		  add(btnImprimerDetailOf);
		  
		  JLabel lblModifierCoutProduction = new JLabel("     MODIFIER COUT PRODUCTION SERVICE");
		  lblModifierCoutProduction.setFont(new Font("Tahoma", Font.BOLD, 45));
		  lblModifierCoutProduction.setBounds(137, 25, 1017, 47);
		  add(lblModifierCoutProduction);
		  
		  JButton btnAfficherStock = new JButton();
		  btnAfficherStock.setBounds(405, 244, 56, 31);
		  add(btnAfficherStock);
		  btnAfficherStock.setIcon(imgRechercher);
		  btnAfficherStock.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		listDetailFactureServiceProductionGroupByFacture.clear();
		  		Client client=mapClient.get(comboclient.getSelectedItem());
		  		Articles articles=mapArticle.get(comboarticle.getSelectedItem());
		  		
		  		String req="";
		  		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  		
		  		boolean AppliquerTva=false;
		  		
		  		if(tva.isSelected()==true || SansTva.isSelected()==true)
	  			{
		  			if(client==null)
		  			{
		  				
		  				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Client SVP !!!!");
		  				return;
		  				
		  			}else
		  			{
		  				AppliquerTva=true;
		  				req=req+" and factureService.client.id='"+client.getId()+"' ";
		  				
		  			}
		  			
		  			
	  			}
		  		
		  		
		  		
		 
		  		
		  		if(AppliquerTva==false)
		  		{
		  			
		  			
		  			if(dateDebutChooser.getDate() !=null || dateFinChooser.getDate()!=null) {
		    			
		    			if(dateDebutChooser.getDate()==null && dateFinChooser.getDate()!=null)
		    			{
		    				dateDebutChooser.setDate(dateFinChooser.getDate());
		    			}else if(dateDebutChooser.getDate()!=null && dateFinChooser.getDate()==null)
		    			{
		    				dateFinChooser.setDate(dateDebutChooser.getDate());
		    				
		    			}else if(dateDebutChooser.getDate()!=null && dateFinChooser.getDate()!=null)
		    			{
		    				
		    				if(	DateUtils.nbJoursEntre(dateDebutChooser.getDate(), dateFinChooser.getDate())<0)
				    		{

				    			JOptionPane.showMessageDialog(null, "La date de d�but doit etre sup�rieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				    			return;
				    		
				    		}
		    				
		    			}
		    			
		    			String dateDu=formatter.format(dateDebutChooser.getDate());
		    			String dateAu=formatter.format(dateFinChooser.getDate());
		    			
		    			
		    			
		    			req=req+" and factureService.dateFacture between '"+dateDu+"' and '"+dateAu+"'";	
		    			
		    		}
		  			
		  			
		  			 
			  		
			  		if(client!=null)
			  		{
			  			
			  		
			  				req=req+" and factureService.client.id='"+client.getId()+"' ";
			  				
			  			
			  			
			  			
			  			
			  		}
			  		
			  		if(articles!=null)
			  		{
			  			
			  			req=req+" and article='"+articles.getLiblle()+"' ";
			  			
			  		}
			  		
			  		
			  		if(! txtnumfacturedu.getText().equals("") && !txtnumfactureau.getText().equals(""))
			  		{
			  			
			  			req=req+" and factureService.numFacture between '"+txtnumfacturedu.getText()+"' and '"+txtnumfactureau.getText()+"'";	
			  			
			  		}else if(! txtnumfacturedu.getText().equals("") && txtnumfactureau.getText().equals(""))
			  		{
			  			req=req+" and factureService.numFacture between '"+txtnumfacturedu.getText()+"' and '"+txtnumfacturedu.getText()+"'";	
			  			
			  		}else if( txtnumfacturedu.getText().equals("") && !txtnumfactureau.getText().equals(""))
			  		{
			  			req=req+" and factureService.numFacture between '"+txtnumfactureau.getText()+"' and '"+txtnumfactureau.getText()+"'";	
			  			
			  		}
		  			
			  			
		  			
		  			
		  		}
		  		
		
		  		
		
		  		
		  		
		  		req=req+" group by  factureService.numFacture";
		  		
		  		
		  		
		  		Depot depot=depotdao.findByCode(AuthentificationView.utilisateur.getCodeDepot());
		  		
		  		
		  		
		  		
		  	     listDetailFactureServiceProductionGroupByFacture=detailfactureServiceProductiondao.listeDetailFactureServiceProductionByDepotByReq(depot.getId(),req);
		  	     afficher_tableProd(listDetailFactureServiceProductionGroupByFacture);

		  	
		    }
		  });
		  btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton button = new JButton("Initialiser");
		  button.setBounds(483, 245, 106, 30);
		  add(button);
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		dateDebutChooser.setCalendar(null);
		  		dateFinChooser.setCalendar(null);
		  		
		  	tva.setSelected(false);
		  		
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton btnDeslectionnerTout = new JButton();
		  btnDeslectionnerTout.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		

		  		

	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 5);
	     		}
	    	
		  		
		  		
		  		
		  		
		  	}
		  });
		  btnDeslectionnerTout.setToolTipText("deselectionner Tout");
		  btnDeslectionnerTout.setBounds(1279, 263, 26, 26);
		  btnDeslectionnerTout.setIcon(imgDeselectAll);
		  add(btnDeslectionnerTout);
		  
		  JButton btnSelectionnertout = new JButton();
		  btnSelectionnertout.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {

	    		
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 5);
	     		}
	    		
	    	
		  		
		  		
		  	}
		  });
		  btnSelectionnertout.setToolTipText("Selectionner Tout");
		  btnSelectionnertout.setBounds(1308, 263, 26, 26);
		  btnSelectionnertout.setIcon(imgSelectAll);
		  add(btnSelectionnertout);
		
		listProductionServiceByMagasinPF=productionDAO.listeProductionGroupByMagasinPF(ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot(), Constantes.TRANSFERE_BL_FACTURE);
		   
		     int i=0;
		     while(i<listProductionServiceByMagasinPF.size())
		     {
		    	 
		    	 Client client=clientdao.findClientByCodeClient(listProductionServiceByMagasinPF.get(i).getMagasinPF().getCodeMachine());
		    	if(client!=null)
		    	{
		    		 comboclient.addItem(client.getNom());
			    	 mapClient.put(client.getNom(), client);
		    	}
		    	
		    	
		    	 i++;
		     }
		listProductionServiceArticle=productionDAO.listeProductionGroupByArticle(ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot(), Constantes.TRANSFERE_BL_FACTURE);	
		for(int j=0;j<listProductionServiceArticle.size();j++)
		{
			
		Articles articles=	listProductionServiceArticle.get(j).getArticles();
			comboarticle.addItem (articles.getLiblle());
			mapArticle.put(articles.getLiblle(), articles);
			mapCodeArticle.put(articles.getCodeArticle(), articles);
			
			
		}
		
		
		
		
		
		
				  		 
	}
	

	
	
	
void afficher_tableProd(List<DetailFactureServiceProduction> listDetailFactureServiceProduction)
	{
		intialiserTableau();
		 
			for (int i=0;i<listDetailFactureServiceProduction.size();i++)
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				
				
				DetailFactureServiceProduction detailFactureServiceProduction=listDetailFactureServiceProduction.get(i);
				
				Production production= productionDAO.findByNumOF(detailFactureServiceProduction.getFactureService().getNumOF(), AuthentificationView.utilisateur.getCodeDepot());
				
				Object []ligne={detailFactureServiceProduction.getFactureService().getNumFacture() ,detailFactureServiceProduction.getFactureService().getDateFacture(),detailFactureServiceProduction.getFactureService().getDepot().getLibelle(),production.getArticles().getLiblle(),detailFactureServiceProduction.getFactureService().getClient().getNom(),false };

				modeleProd.addRow( ligne);
			
			}
			
		
	}



boolean remplirmapservicemodifier(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean regle=(boolean) table.getValueAt(j, 5);
		if(regle==true ){
			
			mapCoutServiceModifier.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 5).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}



void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num Facture","Date", "Depot","Article","Client","Modifier"
		     	}
		     ) {
		boolean[] columnEditables = new boolean[] {
				false,false,false,false,false,true
		};
		Class[] columnTypes = new Class[] {
				String.class,Date.class,String.class,String.class,String.class,Boolean.class
			};
		  public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
		     
		 table.setModel(modeleProd); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(160);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}
}