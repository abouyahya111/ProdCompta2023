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
import java.util.Calendar;
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
import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
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
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
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
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
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


public class AnnulerLesOF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;	
	private List<SousFamilleEnVrac> listSousFamille = new ArrayList<SousFamilleEnVrac>();
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
	private List<Production> listProduction=new ArrayList<Production>();
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
	
	  private ClientDAO clientdao;
	  private Map< String, Boolean> mapCoutServiceModifier = new HashMap<>();
	  private FactureServiceProductionDAO factureServiceProductiondao;
		private SousFamilleEnVracDAO sousFamilleEnvracDAo;
		 private DetailFactureServiceProductionDAO detailfactureServiceProductiondao;
			private ImageIcon imgSelectAll;
			private ImageIcon imgDeselectAll;
			private StockMPDAO stockMPDAO;
			private StockPFDAO stockPFDAO;
			private FactureServiceProductionDAO factureserviceproductionDAO;
			private DetailFactureServiceProductionDAO detailfactureserviceproductionDAO;
			private List<DetailFactureServiceProduction> listdetailFactureServiceProduction =new ArrayList<DetailFactureServiceProduction>();
			private FicheEmployeDAO ficheEmployeDAO;
			private CompteStockMPDAO compteStockMPDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AnnulerLesOF() {
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
        	 sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
        	 factureServiceProductiondao=new FactureServiceProductionDAOImpl();
             detailfactureServiceProductiondao=new DetailFactureServiceProductionDAOImpl();
             stockMPDAO = new StockMPDAOImpl();
 			stockPFDAO = new StockPFDAOImpl();
 			factureserviceproductionDAO=new FactureServiceProductionDAOImpl();
         	detailfactureserviceproductionDAO=new DetailFactureServiceProductionDAOImpl();
        	ficheEmployeDAO = new FicheEmployeDAOImpl();
        	compteStockMPDAO = new CompteStockMPDAOImpl();
         	listSousFamille=sousFamilleEnvracDAo.findAll();
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
					  		     			"Num OF","Num Facture","Date", "Depot","Article","Statut", "Type production","Client","Modifier"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,false,false,true
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
				  		     	scrollPane.setBounds(10, 300, 1139, 403);
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
				  		     	layeredPane.setBounds(10, 115, 1139, 118);
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
		  label.setBounds(230, 70, 70, 24);
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
		  comboarticle.setBounds(282, 71, 289, 24);
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
		  txtcodearticle.setBounds(116, 68, 102, 26);
		  layeredPane.add(txtcodearticle);
		  
		  JLabel lblCode = new JLabel("Code Article :");
		  lblCode.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  lblCode.setBounds(10, 68, 97, 24);
		  layeredPane.add(lblCode);
		  JButton btnImprimerDetailOf = new JButton("Valider");
		  btnImprimerDetailOf.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		
boolean annuler=false;
		  	try {
		  		
		  		
		  			
		  			if(listProduction.size()!=0)
		  			{
		  				
		  			
		  				
		  			mapCoutServiceModifier.clear();
		    		if(!remplirmapservicemodifier())	{
						JOptionPane.showMessageDialog(null, "Il faut selectionner les productions � modifier", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {

						int reponse=JOptionPane.showConfirmDialog(null, "Voulez Vous Vraiment Annuler Les Productions ?", 
								"Attention", JOptionPane.YES_NO_OPTION);
		  				
		  				
if(reponse == JOptionPane.NO_OPTION )		
							
						{
							return;
						}


if(reponse == JOptionPane.YES_OPTION )
{
	for(int i=0;i<listProduction.size();i++)
	{
		
		Production production=listProduction.get(i);
		
		if(mapCoutServiceModifier.containsKey(production.getNumOF()))
		{
			
			annulerStockMatierePremiere(production, production.getMagasinProd().getId(),
					production.getMagasinStockage().getId());

			annulerStockProduitFini(production);
			
			
		FactureServiceProduction factureServiceProduction=factureserviceproductionDAO.findByNumOF(production.getNumOF());
			if(factureServiceProduction!=null)
			{
				
			   listdetailFactureServiceProduction=detailfactureserviceproductionDAO.listeDetailFactureServiceProductionByFacture(factureServiceProduction.getId())	;
				for(int j=0;j<listdetailFactureServiceProduction.size();j++)
				{
					
				DetailFactureServiceProduction detailFactureServiceProduction=	listdetailFactureServiceProduction.get(j);
				detailfactureserviceproductionDAO.delete(detailFactureServiceProduction.getId());
					
					
					
				}
				
				factureserviceproductionDAO.delete(factureServiceProduction.getId());
				
			}
			
			

			deleteListeObject(ficheEmployeDAO.findByNumOf(production.getNumOF()));
			production.setStatut(ETAT_OF_ANNULER);
			production.setUtilisateurAnnulation(AuthentificationView.utilisateur);
			productionDAO.edit(production);
			
			
			annuler=true;
			
		}
		
		
	}
	
	
	if(annuler==true)
	{
		
	JOptionPane.showMessageDialog(null, "L'Annulation des OF �  �t� effectu�"
			+ "Avec Succ�e)" );	
		FindOF();
		
	}
	
	
	
	

	
}




					
					
					}
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  			}else
		  			{
		  				JOptionPane.showMessageDialog(null, "la liste des Production est vide","Erreur",JOptionPane.ERROR_MESSAGE);
			  			return;
		  			}
		  			
		  			
		  			
		  	
		  		
		  		
		  		
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Le Cout Service Doit etre en Chiffre SVP","Error",JOptionPane.ERROR_MESSAGE);
			}
		  	
		  	
		  	
		  	}
		  });
		  btnImprimerDetailOf.setBounds(444, 727, 163, 24);
		  add(btnImprimerDetailOf);
		  
		  JLabel lblModifierCoutProduction = new JLabel("                   Annuler Les Productions");
		  lblModifierCoutProduction.setFont(new Font("Tahoma", Font.BOLD, 45));
		  lblModifierCoutProduction.setBounds(137, 25, 1017, 47);
		  add(lblModifierCoutProduction);
		  
		  JButton btnAfficherStock = new JButton();
		  btnAfficherStock.setBounds(405, 244, 56, 31);
		  add(btnAfficherStock);
		  btnAfficherStock.setIcon(imgRechercher);
		  btnAfficherStock.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		FindOF();
		  		
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
		  		
		  	
		  		
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
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
		  btnDeslectionnerTout.setBounds(1094, 263, 26, 26);
		  btnDeslectionnerTout.setIcon(imgDeselectAll);
		  add(btnDeslectionnerTout);
		  
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
		  btnSelectionnertout.setBounds(1123, 263, 26, 26);
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
	

	
	
	
void afficher_tableProd(List<Production> listProduction)
	{
		intialiserTableau();
		 
			for (int i=0;i<listProduction.size();i++)
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				Production production=listProduction.get(i);
				Client client=clientdao.findClientByCodeClient(production.getMagasinPF().getCodeMachine());
				
				Object []ligne={production.getNumOF(),production.getNumFacture(), production.getDate_debFabPre(), production.getMagasinPF().getDepot().getLibelle(),production.getArticles().getLiblle(),production.getStatut(),production.getService(),client.getNom(),false};

				modeleProd.addRow( ligne);
			
			}
			
		
	}



boolean remplirmapservicemodifier(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean regle=(boolean) table.getValueAt(j, 8);
		if(regle==true ){
			
			mapCoutServiceModifier.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 8).toString()));
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
		     			"Num OF","Num Facture","Date", "Depot","Article","Statut", "Type production","Client","Modifier"
		     	}
		     ) {
		boolean[] columnEditables = new boolean[] {
				false,false,false,false,false,false,false,false,true
		};
		Class[] columnTypes = new Class[] {
				String.class,String.class,Date.class,String.class,String.class,String.class,String.class,String.class,Boolean.class
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



void annulerStockMatierePremiere(Production production, int idMagasinProd, int idMagasinStockage) {
	BigDecimal quantiteStockage = BigDecimal.ZERO;
	BigDecimal quantiteCharge = BigDecimal.ZERO;
	BigDecimal quantiteStockmp = BigDecimal.ZERO;
	BigDecimal quantiteARetournerCompteMP = BigDecimal.ZERO;
	boolean annuler = false;
	
	
	List<CoutMP> listCoutMP=production.getListCoutMP();
	
	
	for (int i = 0; i < listCoutMP.size(); i++) {
		quantiteStockage = BigDecimal.ZERO;
		CoutMP coutMP = listCoutMP.get(i);

		quantiteCharge = coutMP.getQuantCharge();
		BigDecimal quantiteConsomme = coutMP.getQuantConsomme();
		BigDecimal quantitechargeSupp = coutMP.getQuantChargeSupp();
		BigDecimal quantiteExistante = coutMP.getQuantExistante();
		BigDecimal quantiteDechet = coutMP.getQuantDechet();
		BigDecimal quantiteDechetFour = coutMP.getQuantDechetFournisseur();
		BigDecimal quantiteManquante = coutMP.getQuantiteManquante();
		BigDecimal quantiteOffre = coutMP.getQuantiteOffre();
		BigDecimal quantiteReste = coutMP.getQuantReste();

		StockMP stockMPProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(), idMagasinProd);
		StockMP stockMPStockage = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
				idMagasinStockage);
		quantiteStockmp = quantiteExistante.add(stockMPProd.getStock());
if(stockMPStockage!=null)
{
	quantiteStockage = stockMPStockage.getStock().add(quantiteCharge);
}
		

		BigDecimal ecart = (quantiteCharge.add(quantitechargeSupp).add(quantiteExistante))
				.subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(quantiteManquante)
						.add(quantiteOffre).add(quantiteReste));

		Calendar cal = Calendar.getInstance();
		cal.setTime(production.getDate());
		int annee = cal.get(Calendar.YEAR);
		int mois = cal.get(Calendar.MONTH) + 1;

		CompteStockMP compteStockMP = compteStockMPDAO.findByCodeMPAnneeMois(coutMP.getMatierePremier().getCode(),
				mois, annee);

		if (compteStockMP != null) {
			quantiteARetournerCompteMP = compteStockMP.getQuantite().subtract(ecart);
			compteStockMP.setQuantite(quantiteARetournerCompteMP);
			compteStockMPDAO.edit(compteStockMP);
		}

		stockMPProd.setStock(quantiteStockmp);
		if(stockMPStockage!=null)
		{
			stockMPStockage.setStock(quantiteStockage);
		}
		

		/*
		 * coutMP.setCoutDechet(0); coutMP.setQuantCharge(0);
		 * coutMP.setQuantChargeSupp(0); coutMP.setQuantConsomme(0);
		 * coutMP.setQuantDechet(0); coutMP.setQuantExistante(0); coutMP.setQuantite(0);
		 * coutMP.setQuantReste(0); coutMP.setQuantDechetFournisseur(0);
		 * coutMP.setQuantiteManquante(0); cou
		 */
		listCoutMP.set(i, coutMP);
		// listCoutMP.remove(i);
		if(stockMPStockage!=null)
		{
			stockMPDAO.edit(stockMPStockage);
		}
	
		stockMPDAO.edit(stockMPProd);
		annuler = true;
	}
	if (annuler == true) {
		// Supprimer le transfer stock MP
		TransferStockMP transferStockMP = transferStockMPDAO.findTransferByCode(production.getNumOF());
		
	int i=	transferStockMP.getId();
		i=i+1;
		boolean existe=true;
		
		while(existe==true)
		{
			
			TransferStockMP transferStockMPTmp = transferStockMPDAO.findById(i);
			
			if(transferStockMPTmp!=null)
			{
				if(transferStockMPTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE) || transferStockMPTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF))
				{
					transferStockMPDAO.delete(transferStockMPTmp.getId());
					
					existe=true;
					
				}else
				{
					existe=false;
				}
				
			}else
			{
				existe=false;
			}
			
			i=i+1;
			
		}
		
		
		
		
		
		
		if (transferStockMP != null) {
			transferStockMPDAO.delete(transferStockMP.getId());
		}

	}

	// production.setListCoutMP(listCoutMP);
}

void annulerStockProduitFini(Production  production) {
	BigDecimal quantiteAannuler = BigDecimal.ZERO;
	BigDecimal quantite = BigDecimal.ZERO;

	SousFamilleArticlePF sousfamilleTmp = null;	
	List<CoutMP> listCoutMP = productionDAO.listeCoutMP(production.getId());
	for (int i = 0; i < listCoutMP.size(); i++) {

		if (listCoutMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
				.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
			
			for(int j=0;j<listSousFamille.size();j++)
			{
				
				SousFamilleEnVrac sousFamilleArticlePF =listSousFamille.get(j);
				
				if(sousFamilleArticlePF.getMatierePremier() !=null)
				{
					
					if(sousFamilleArticlePF.getMatierePremier().getId()==listCoutMP.get(i).getMatierePremier().getId())
					{
						
						sousfamilleTmp=sousFamilleArticlePF.getSousfamile();
						
						
					}
					
					
					
				}
				
				
			}
			
			
			
			
			/*

			if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_3505)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505);

			} else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_3505_A)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

			}
			else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_3505_A_5KG)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

			} 
			
			else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

			} 
			else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA_5KG)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

			}
			else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_3505_AAA_5KG)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AAA);

			}
			
			else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_3505_B)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_B);

			} else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_3505_C)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_C);

			} else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_3505_D)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_D);

			} else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_401022)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_401022);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_4011)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9366)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9366);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9367)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9367);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9368)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9368);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9369)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9369);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9371)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9371);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9475)) {

				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9475);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9575)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9575);

			} else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_4011_NAZHA)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

			} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_4011)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

			} else if (listCoutMP.get(i).getMatierePremier().getCode()
					.equals(Constantes.CODE_SOUS_FAMILLE_4011_MAHA)) {
				sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

			}
			
			
			*/

		}
	}
	StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(production.getArticles().getId(),
			production.getMagasinPF().getId(), sousfamilleTmp.getId());
	TransferStockPF transferStockPF = transferStockPFDAO.findByCodeTransfert(production.getNumOF());
	quantiteAannuler = production.getQuantiteReel();
	quantite = stockPF.getStock().subtract(quantiteAannuler);

	stockPF.setStock(quantite);

	if (transferStockPF != null) {
		
		
	int j=	transferStockPF.getId();
	j=j+1;
	boolean existe=true;	
		
		while(existe==true)
		{
			
			TransferStockPF transferStockPFTmp = transferStockPFDAO.findById(j);	
			
			if(transferStockPFTmp!=null)
				
			{
				
				if(transferStockPFTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP))
				{
					
					
					List<DetailTransferProduitFini> listDetailTransferStockPF = detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPFTmp.getId());
					
					for (int i = 0; i < listDetailTransferStockPF.size(); i++) {
						DetailTransferProduitFini detailtransferproduitfini = listDetailTransferStockPF.get(i);
						detailTransferProduitFiniDAO.delete(detailtransferproduitfini.getId());

					}
					transferStockPFDAO.deleteObject(transferStockPFTmp);
					
					existe=true;
					
					
					
				}else
				{
					
					existe=false;
					
				}
				
		
				
				
			}else
			{
				existe=false;
				
			}
			
			
			
			j=j+1;
			
		}
		
		
		
		
		
		
		
		
		
		

		List<DetailTransferProduitFini> listDetailTransferStockPF = detailTransferProduitFiniDAO
				.findByTransferStockPF(transferStockPF.getId());
		for (int i = 0; i < listDetailTransferStockPF.size(); i++) {
			DetailTransferProduitFini detailtransferproduitfini = listDetailTransferStockPF.get(i);
			detailTransferProduitFiniDAO.delete(detailtransferproduitfini.getId());

		}
		transferStockPFDAO.deleteObject(transferStockPF);
	}

	stockPFDAO.edit(stockPF);

}

void deleteListeObject(List<FicheEmploye> listFicheEmploye) {

	for (int i = 0; i < listFicheEmploye.size(); i++) {
		FicheEmploye ficheEmploye = listFicheEmploye.get(i);
		ficheEmployeDAO.deleteObject(ficheEmploye);
	}
}


public void FindOF()
{
	
	listProduction.clear();
		
		Client client=mapClient.get(comboclient.getSelectedItem());
		Articles articles=mapArticle.get(comboarticle.getSelectedItem());
		
		String req="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
		
		
		
		req=req+" and date between '"+dateDu+"' and '"+dateAu+"'";	
		
	}
		
		

		
		if(client!=null)
		{
			
		Magasin magasin=depotDAO.magasinByCodeMachineByTypeMagasin(MAGASIN_CODE_TYPE_PF,client.getCode());
			
			if(magasin!=null)
			{
				req=req+" and magasinPF.id='"+magasin.getId()+"' ";
				
			}
			
			
			
		}
		
		if(articles!=null)
		{
			
			req=req+" and articles.id='"+articles.getId()+"' ";
			
		}
		
		req=req+" and service='"+Constantes.TRANSFERE_BL_FACTURE+"' ";
		
		
	     listProduction=productionDAO.listeProductionTerminerBydepotByReq(AuthentificationView.utilisateur.getCodeDepot(),req);
	     afficher_tableProd(listProduction);

	

	
}






}
