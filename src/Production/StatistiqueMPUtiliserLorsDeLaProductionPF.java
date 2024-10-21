package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import main1.AuthentificationView;
import main1.ProdLauncher;
import net.sf.jasperreports.components.table.ColumnGroup;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.MultiSplitLayout.ColSplit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;
 

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.GroupableTableHeader;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;


import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
 
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
 
import dao.daoManager.MachineDAO;
 
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatInitialParSousCtaegorieMP;
import dao.entity.EtatStockMP;
import dao.entity.FicheEmploye;
 
import dao.entity.Machine;
import dao.entity.Magasin;
 
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.PrixMoyenStockMP;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.StatistiqueMPConsommeLorsProductionPFClass;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;


public class StatistiqueMPUtiliserLorsDeLaProductionPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	
	private DefaultTableModel	 modeleMP1;

	private JXTable  tableStatistiqueEnvracConsomme;
	
	  private ImageIcon imgExcel;
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ProductionDAO productionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	 
	private List<Production> listProduction=new ArrayList<Production>();
	private List<MatierePremier> listMatierePremiere=new ArrayList<MatierePremier>();
 
	
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, BigDecimal> mapPrixMoyenneMatierePremiere = new HashMap<>();
	private Map< String, String> mapMatierePremiereMontantQuantite = new HashMap<>();
	private Map< String, String> mapMatierePremiereMontantPrix = new HashMap<>();
	private Map< String, BigDecimal> mapMontantServiceParArticle = new HashMap<>();
	
	private Utilisateur utilisateur;
	private DepotDAO depotDAO;
	private List<Depot> listDepot =new ArrayList<Depot>();
	private JComboBox txtNumOF = new JComboBox();
	private Map< String, Production> mapProduction = new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapSousFamille = new HashMap<>();
	private List<Object[]> listObject =new ArrayList<Object[]>();
	private List<Object[]> listObjectSommeMontantArticleService =new ArrayList<Object[]>();
	private List<Object[]> listObjectGroupByArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjectArticleFabriqueAvecsonType =new ArrayList<Object[]>();
	private List<StatistiqueMPConsommeLorsProductionPFClass> listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass =new ArrayList<StatistiqueMPConsommeLorsProductionPFClass>();
	List<String> listeNomCategorie =new ArrayList<String>();
	List<String> listeCodeMP =new ArrayList<String>();
	
	List<String> listeColumn =new ArrayList<String>(); 
 
	private  JComboBox combomp = new JComboBox();
	
	 
	private  JDateChooser dateChooserdechet = new JDateChooser();
 
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	JComboBox soucategoriempcombo = new JComboBox();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, CategorieMp> Mapcategorie = new HashMap<>();
	JComboBox combodepot = new JComboBox();
	JDateChooser dateChooser = new JDateChooser();
	private JDateChooser dateChooserDu;
	JDateChooser dateChooser_1 = new JDateChooser();
	private JDateChooser dateChooserAu;
	private SubCategorieMPDAO subcategoriempdao;
	JComboBox comboMagasin = new JComboBox();
	private Map< String, Magasin> mapMagasin = new HashMap<>();
	private Map< String, Magasin> mapMagasinMP = new HashMap<>();
	
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<CategorieMp> listCategorie =new ArrayList<CategorieMp>();
	private List<SousFamilleEnVrac> listSousFamilleEnVrac =new ArrayList<SousFamilleEnVrac>();
	private ArticlesDAO ArticleDAO;
	private Map< String, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	private Map< String, String> mapTypeArticle= new HashMap<>();
	private Map< String, BigDecimal> mapQuantiteConsomme= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremiereEnVrac= new HashMap<>();
	 String prix="Prix";
	 String quantite="Quantite";
	 String montant="Montant";
	 
		private List<EtatInitialParSousCtaegorieMP> listEtatInitialMP =new ArrayList<EtatInitialParSousCtaegorieMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTmp =new ArrayList<DetailTransferStockMP>();
		private List <Object[]> listeObject=new ArrayList<Object[]>();
		private List <Object[]> listeObjectPrixMoyenAchat=new ArrayList<Object[]>();
		private List<PrixMoyenStockMP> listPrixMoyenStockMP =new ArrayList<PrixMoyenStockMP>();
		private List<DetailTransferStockMP> listDetailTransfertStockMP =new ArrayList<DetailTransferStockMP>();
	//******************************************* Listes Pour Mouvement de Stock Mp **********************************************//
	
	
		private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer =new ArrayList<DetailTransferStockMP>();
		private List<DetailMouvementStock> listMouvementStockMP =new ArrayList<DetailMouvementStock>();
		private List<DetailMouvementStock> listMouvementStockMPAfficher =new ArrayList<DetailMouvementStock>();
		private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp =new ArrayList<DetailMouvementStock>();
		
		private List<EtatStockMP> listEtatStockMP =new ArrayList<EtatStockMP>();
		
		//*******************************************************************************************************************************//
	
	// ************************************************
	
		private List<DetailTransferStockMP> listDetailTransferStockMPInitial =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPAchat =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPAchatGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPSortie =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPSortieGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPAvoir =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPAvoirGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPService =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPServiceGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPVente =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPVenteGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPF =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFProduction =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFProductionGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPFabrique=new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPFabriqueGroupebyMP =new ArrayList<DetailTransferStockMP>();
		
		private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFService =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMPService =new ArrayList<DetailTransferStockMP>();
		
		private List<DetailTransferStockMP> listDetailTransferStockMPAllMP =new ArrayList<DetailTransferStockMP>();
		
		//***************************************************
	
	
	
	
	
	  JComboBox comboFournisseur = new JComboBox();
	  JComboBox comboPlusMoins = new JComboBox();
	  CategorieMpDAO categorieMpDAO;
	  JLabel totalmoins = new JLabel("0.00");
	  JLabel totalplus = new JLabel("0.00");
	  JLabel totalfabrique = new JLabel("0.00");
	  JLabel totalcharger = new JLabel("0.00");
	  JLabel differenceplusmoins = new JLabel("0.00");
	  ParametreDAO parametreDAO;
	  MachineDAO machineDAO;
	  JComboBox comboArticle = new JComboBox();
	  private Map< Date, Integer> mapIdListSisuation= new HashMap<>();
	
	  JComboBox comboCategorie = new JComboBox();
	  private JComboBox comboSousFamille;
	  private JTextField txtCodeArticle = new JTextField();
	  String etatCategorie=etatCategorie=CODE_NON;;
	  SousFamilleArticlesPFDAO sousFamilleArticlesPFDAO;
	  SousFamilleEnVracDAO sousFamilleEnVracDAO;
	  StockPFDAO stockPFDAO;
	  private SousFamilleEnVracDAO sousFamilleEnvracDAo;
	  JComboBox comboMagasinMP = new JComboBox();
	  private DetailTransferMPDAO detailTransferStockMPDAO;
	  private DetailFactureServiceProductionDAO detailFactureServiceProductionDAO;
	private   ClientDAO clientDAO;
	private DetailFactureAchatMPDAO detailFactureAchatMPdao;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public StatistiqueMPUtiliserLorsDeLaProductionPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 825);
        try{
        	categorieMpDAO=new CategorieMpDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	sousFamilleArticlesPFDAO=new SousFamilleArticlesPFDAOImpl();
        	ArticleDAO= new ArticlesDAOImpl();
        	//listObjectArticleFabriqueAvecsonType=productionDAO.listeArticleFabriqueEtSonType();
        	  machineDAO=new MachineDAOImpl();
        	  parametreDAO=new ParametreDAOImpl();
        	  matierePremiereDAO=new MatierePremierDAOImpl();
        	  subcategoriempdao=new SubCategorieMPAOImpl();
        	  sousFamilleEnVracDAO=new SousFamilleEnVracDAOImpl();
        	  stockPFDAO=new StockPFDAOImpl();
        	  detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
        	  listSousFamilleEnVrac=sousFamilleEnVracDAO.findAll();
        	  sousFamilleEnvracDAo=new SousFamilleEnVracDAOImpl();
        	  detailFactureServiceProductionDAO=new DetailFactureServiceProductionDAOImpl();
        	  clientDAO=new ClientDAOImpl();
              detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
        	imgAjouter= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
       	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Article","Categorie","Quantite Consomme","Pourcentage"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
				  		     	

				  		     	
				  		     		tableStatistiqueEnvracConsomme = new JXTable();
				  		     		  tableStatistiqueEnvracConsomme.setHorizontalScrollEnabled(true);
				  		     		tableStatistiqueEnvracConsomme.addMouseListener(new MouseAdapter() {
				  		     			@Override
				  		     			public void mouseClicked(MouseEvent e) {
				  		     				
				  		     				if(tableStatistiqueEnvracConsomme.getSelectedRow()!=-1)
				  		     				{

				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     			}
				  		     		});
				  		     		tableStatistiqueEnvracConsomme.setShowVerticalLines(false);
				  		     		tableStatistiqueEnvracConsomme.setSelectionBackground(new Color(51, 204, 255));
				  		     	     tableStatistiqueEnvracConsomme.setRowHeightEnabled(true);
				  		     		tableStatistiqueEnvracConsomme.setBackground(new Color(255, 255, 255));
				  		     		tableStatistiqueEnvracConsomme.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     		tableStatistiqueEnvracConsomme.setColumnControlVisible(true);
				  		     		tableStatistiqueEnvracConsomme.setForeground(Color.BLACK);
				  		     		tableStatistiqueEnvracConsomme.setGridColor(new Color(0, 0, 255));
				  		     		tableStatistiqueEnvracConsomme.setAutoCreateRowSorter(true);
				  		     		   // table.setBounds(2, 27, 411, 198);
				  		     		tableStatistiqueEnvracConsomme.setRowHeight(20);
				  		      modeleMP =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Article","NBR En Vrac Consomme"," En Vrac","Pourcentage"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  			     
				  			 tableStatistiqueEnvracConsomme.setModel(modeleMP); 
				  			 tableStatistiqueEnvracConsomme.getColumnModel().getColumn(0).setPreferredWidth(160);
				  	     tableStatistiqueEnvracConsomme.getColumnModel().getColumn(1).setPreferredWidth(60);
				  				JScrollPane scrollPane_1 = new JScrollPane(tableStatistiqueEnvracConsomme);
				  				
				  				scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  				scrollPane_1.setBounds(21, 183, 1467, 443);
				  				add(scrollPane_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1458, 120);
				  		     	add(layeredPane);
		
		JLabel label_2 = new JLabel("Depot :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 11, 55, 26);
		layeredPane.add(label_2);
		
		 combodepot = new JComboBox();
		 combodepot.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		
		 		

		     	 	
		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
 		     	  	 // comboGroupe = new JComboBox();
		     	 	comboMagasin.removeAllItems();
		     	 Depot depot=new Depot();
		     	 	//comboGroupe.addItem("");
		     	 	if(!combodepot.getSelectedItem().equals(""))
 		     	  	   	 depot =mapDepotSource.get(combodepot.getSelectedItem());
		  		       if(depot!=null)
		  		       {
		  		    	 listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);   
		  		       }
 		     	  		
		  		      if(listMagasin!=null){
		  		    	  
		  		    	  int j=0;
			  		      	while(j<listMagasin.size())
			  		      		{	
			  		      			Magasin magasin=listMagasin.get(j);
			  		      			comboMagasin.addItem(magasin.getLibelle());
			  		      			mapMagasin.put(magasin.getLibelle(), magasin);
			  		      			j++;
			  		      		}
		  		      }
		     	 	 }
		     	 	
		 		
		 		
		 	}
		 });
		combodepot.setBounds(65, 11, 202, 27);
		layeredPane.add(combodepot);

				
				
				
				
				combodepot.addItem("");
			    if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
		    		Depot depot = depotDAO.findByCode(utilisateur.getCodeDepot());
			     		combodepot.addItem(depot.getLibelle());
			     		mapDepotSource.put(depot.getLibelle(), depot);
		    }else {
		    	
		    	listDepot = depotDAO.findAll();	
			      int i=0;
			      	while(i<listDepot.size())
			      		{	
			      		Depot depot=listDepot.get(i);
			      			mapDepotSource.put(depot.getLibelle(), depot);
			      			combodepot.addItem(depot.getLibelle());
			      			i++;
			      		}
		    	
		    }
		  		
		  		 comboMagasin = new JComboBox();
		  		comboMagasin.setBounds(342, 11, 202, 27);
		  		layeredPane.add(comboMagasin);
		  		
		  		JLabel lblMagasin = new JLabel("Magasin :");
		  		lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  		lblMagasin.setBounds(287, 11, 55, 26);
		  		layeredPane.add(lblMagasin);
		  		
		  		JLabel label = new JLabel("Du :");
		  		label.setBounds(1024, 14, 45, 24);
		  		layeredPane.add(label);
		  		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		
		  		 dateChooserDu = new JDateChooser();
		  		 dateChooserDu.setBounds(1058, 12, 163, 26);
		  		 layeredPane.add(dateChooserDu);
		  		 dateChooserDu.setLocale(Locale.FRANCE);
		  		 dateChooserDu.setDateFormatString("dd/MM/yyyy");
		  		 
		  		 JLabel label_1 = new JLabel("Au :");
		  		 label_1.setBounds(1231, 14, 36, 24);
		  		 layeredPane.add(label_1);
		  		 label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  		 
		  		  dateChooserAu = new JDateChooser();
		  		  dateChooserAu.setBounds(1261, 12, 169, 26);
		  		  layeredPane.add(dateChooserAu);
		  		  dateChooserAu.setLocale(Locale.FRANCE);
		  		  dateChooserAu.setDateFormatString("dd/MM/yyyy");
			   
			
			 
		
		JLabel lblCtaegorie = new JLabel("Sous Famille :");
		lblCtaegorie.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCtaegorie.setBounds(10, 68, 70, 26);
		layeredPane.add(lblCtaegorie);
		
		 comboSousFamille = new JComboBox();
		 comboSousFamille.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		


		 		

		   		

		 		

	     	 	int i=0;
		   	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		   	
		   	 		
		   	 		 comboArticle.removeAllItems();
		   	 		 txtCodeArticle.setText("");
		   	 		mapArticle.clear();
		   	 	mapCodeArticle.clear();
		   	 mapTypeArticle.clear();
		   	 		 if(!comboSousFamille.getSelectedItem().equals(""))
		   	 		 {
		   	 			comboArticle.addItem("");
		   	 			 
		   	 			if(!comboMagasin.getSelectedItem().equals(""))
		   	 			{
		   	 				SousFamilleArticlePF sousfamille=mapSousFamille.get(comboSousFamille.getSelectedItem());
		   	 				
		   	 				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
		   	 			 listArticleStockPF=stockPFDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
		   	 				
		   	 				for(int t=0;t<listArticleStockPF.size();t++)
		   	 				{
		   	 					
		   	 				 Articles articles=listArticleStockPF.get(t).getArticles();
		   	 				comboArticle.addItem(articles.getLiblle());
			   	 			mapArticle.put(articles.getLiblle(), articles);
			   	 			mapCodeArticle.put(articles.getCodeArticle(), articles);
			   	 			 
		   	 				}
		   	 			
		   	 			
		   	 			
		   	 			}else
		   	 			{
		   	 				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le magasin SVP !!!!");
		   	 				return;
		   	 			}
		   	 			
		   	 			
		   	 			
		   	 			
		   	 		 
		   	 	
		   	 	comboArticle.setSelectedItem("");
		   	 			 
		   	 			 
		   	 			 
		   	 			 
		   	 		 }else
		   	 		 {
		   	 			 
		   	 			 
		   	 			comboArticle.addItem("");
		   	 			comboArticle.setSelectedItem(""); 
		   	 			 
		   	 		 }


		           
		   	 	 	}
		   	 	
				
				
				
				
			
			   		
			   	
			 		
			 		
			 	
			 		
			 		
			 	
		 		
		 		
		 		
		 		
		 	}
		 });
		comboSousFamille.setBounds(90, 68, 271, 27);
		layeredPane.add(comboSousFamille);
		
	
	JLabel lblArticle = new JLabel("Article :");
	lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
	lblArticle.setBounds(615, 67, 70, 26);
	layeredPane.add(lblArticle);
	
	 comboArticle = new JComboBox();
	 comboArticle.addItemListener(new ItemListener() {
	 	public void itemStateChanged(ItemEvent e) {

	 		

	   		

	 		

     	 	
   	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
   	
   	 		
   	 		 Articles articles=mapArticle.get(comboArticle.getSelectedItem().toString());
   	 		 
   	 		if(articles!=null)
   	 		{
   	 			

	  			txtCodeArticle.setText (articles.getCodeArticle());	
	  		
   	 			
   	 		}else
   	 		{
   	 			txtCodeArticle.setText ("");	
   	 		}
           
   	 	 	}
   	 	
		
		
		
		
	
	   		
	   	
	 		
	 		
	 	
	 		
	 		
	 	}
	 });
	comboArticle.setBounds(661, 67, 371, 27);
	layeredPane.add(comboArticle);
	comboArticle.addItem("");
	JLabel lblCodeArticle = new JLabel("Code Article");
	lblCodeArticle.setBounds(384, 67, 67, 26);
	layeredPane.add(lblCodeArticle);
	lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
	txtCodeArticle = new JTextField();
	txtCodeArticle.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {


	  		if (e.getKeyCode() == e.VK_ENTER)
	  		{
	  			
	  		Articles articles=mapCodeArticle.get(txtCodeArticle.getText());
	  		if(articles!=null)
	  		{
	  			comboArticle.setSelectedItem(articles.getLiblle());	
	  		}
	  		
	  		}
		
			
			
		}
	});
	txtCodeArticle.setBounds(446, 67, 153, 26);
	layeredPane.add(txtCodeArticle);
	txtCodeArticle.setColumns(10);
	
	JButton btnAfficherStock = new JButton();
	btnAfficherStock.setBounds(580, 141, 93, 31);
	add(btnAfficherStock);
	btnAfficherStock.setIcon(imgRechercher);
	btnAfficherStock.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.clear();
			listObjectGroupByArticle.clear();
			listObject.clear();
			if(combodepot.getSelectedItem().equals("") && comboMagasin.getSelectedItem().equals("") && dateChooserDu.getDate() ==null && dateChooserAu==null && comboCategorie.getSelectedItem().equals(""))
			{
				
				
				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Un Ou Plusieurs Champ SVP");
				return;
					
			}else
			{
				
				if(combodepot.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Depot SVP");
					return;
				}else if(comboMagasin.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP");
					return;
				}else
				{
					
					if(dateChooserDu.getDate()==null && dateChooserAu.getDate()==null)
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Date SVP");
						return;
					}
					
					if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()==null)
					{
						dateChooserAu.setDate(dateChooserDu.getDate());
					}
					if(dateChooserDu.getDate()==null && dateChooserAu.getDate()!=null)
					{
						dateChooserDu.setDate(dateChooserAu.getDate());
					}
					
					
					
					
					listeNomCategorie.clear();
					listeCodeMP.clear();
					
				String	dateDu=sdf.format(dateChooserDu.getDate()) ;
				String dateAu= sdf.format(dateChooserAu.getDate()) ;	
					
				String requete="";	
					
					Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
					Magasin magasinMP=mapMagasinMP.get(comboMagasinMP.getSelectedItem());
				
					SousFamilleArticlePF sousFamilleArticlePFSelectionner=mapSousFamille.get(comboSousFamille.getSelectedItem());
					Articles articleSelectionner=mapArticle.get(comboArticle.getSelectedItem());
				listObject=productionDAO.listeArticleFabriqueParMagasinParSousFamille(magasin);
				// JOptionPane.showMessageDialog(null, listObject.size());
				 listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.clear();
				 
				 Client client=clientDAO.findClientByCodeClient(magasin.getCodeMachine());
				 listObjectSommeMontantArticleService=detailFactureServiceProductionDAO.SommeMontantParArticleService(client,dateChooserDu.getDate(),dateChooserAu.getDate());
				 
				 
				 for(int i=0;i<listObjectSommeMontantArticleService.size();i++)
					{
					 
					 Object[] object=listObjectSommeMontantArticleService.get(i);
					 if(object[0]!=null)
						{
						 if(object[1]!=null)
							{
							 if(object[2]!=null)
								{
								 
								
								 
								 mapMontantServiceParArticle.put(object[0].toString()+object[1].toString(), (BigDecimal)object[2]);
								
								 System.out.println(object[0].toString()+" : "+object[1].toString() +" : "+  (BigDecimal)object[2]);
								
								}
							 
							
							 
							 
							}
						 
						 
						}
					 
					}
				 
				
					boolean existcolumn=false;
					boolean existrows=false;
					for(int i=0;i<listObject.size();i++)
					{
						 Object[] object=listObject.get(i);	
						
						 

							 existcolumn=false;
							 existrows=false;
						 
						if(object[0]!=null)
						{
							
						Articles articles=ArticleDAO.findByCode(object[0].toString())	;
						
						SousFamilleArticlePF sousFamilleArticlePF=sousFamilleArticlesPFDAO.findByCode((object[3].toString()))	;
							if(articles!=null)
							{

								for(int j=0;j<listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.size();j++)
								{
									if(listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.get(j).getArticles().getId()==articles.getId() && listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.get(j).getSousFamille().getId()==sousFamilleArticlePF.getId())
									{
										
										 
										
										existrows=true;
									}
									
									
									
									
									
								}
							
								if(existrows==false)
								{
									
									
									if(sousFamilleArticlePFSelectionner!=null)
									{
										if(sousFamilleArticlePFSelectionner.getId()==sousFamilleArticlePF.getId())
										{
											
											if(articleSelectionner!=null)
											{
												
												if(articles.getId()==articleSelectionner.getId())
												{
													
													StatistiqueMPConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=new StatistiqueMPConsommeLorsProductionPFClass();
													
													statistiqueEnVracConsommeLorsProductionPFClass.setArticles(articles);
													statistiqueEnVracConsommeLorsProductionPFClass.setSousFamille(sousFamilleArticlePF);
													listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.add(statistiqueEnVracConsommeLorsProductionPFClass);
													
												}
												
												
												
												
												
											}else
											{
												
												StatistiqueMPConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=new StatistiqueMPConsommeLorsProductionPFClass();
												
												statistiqueEnVracConsommeLorsProductionPFClass.setArticles(articles);
												statistiqueEnVracConsommeLorsProductionPFClass.setSousFamille(sousFamilleArticlePF);
												listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.add(statistiqueEnVracConsommeLorsProductionPFClass);
												
											}
											
											
										}
										
										
										
									}else
									{
										
										StatistiqueMPConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=new StatistiqueMPConsommeLorsProductionPFClass();
										
										statistiqueEnVracConsommeLorsProductionPFClass.setArticles(articles);
										statistiqueEnVracConsommeLorsProductionPFClass.setSousFamille(sousFamilleArticlePF);
										listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass.add(statistiqueEnVracConsommeLorsProductionPFClass);
										
									}
									
									
								
								
								}
								
								
								
							
							}
							
							
						}
						
							
						
					}
				 
				 
				 
				 
				 
				 listObject=productionDAO.listeMPConsommeParArticleParMagasin(dateChooserDu.getDate(), dateChooserAu.getDate(), Constantes.ETAT_OF_TERMINER, magasin,magasinMP);
				// JOptionPane.showMessageDialog(null, listObject.size());
				 
		
			listeColumn.clear();
			
			
			
			listeColumn.add("Articles");
			listeColumn.add("Sous Famille");
			 
			
			for(int i=0;i<listObject.size();i++)
			{
				 existcolumn=false;
				 Object[] object=listObject.get(i);	
				 if(object[0]!=null)
					{
						
					MatierePremier matierePremier=matierePremiereDAO.findByCode(object[1].toString())	;
					Articles article=ArticleDAO.findByCode(object[0].toString())	;
					SousFamilleArticlePF sousFamilleArticlePF=sousFamilleArticlesPFDAO.findByCode(object[2].toString());
					for(int j=0;j<listeColumn.size();j++)
					{
						if(listeColumn.get(j).toString().equals(matierePremier.getNom()+" "+quantite))
						{
							
							existcolumn=true;
						}
						
						
						
						
						
					}
					
					
					
					if(existcolumn==false)
					{
						mapMatierePremiere.put(matierePremier.getNom()+" "+prix, matierePremier);
						mapMatierePremiere.put(matierePremier.getNom()+" "+quantite, matierePremier);
						mapMatierePremiere.put(matierePremier.getNom()+" "+montant, matierePremier);
						mapMatierePremiereMontantPrix.put(matierePremier.getNom()+" "+montant, matierePremier.getNom()+" "+prix);
						mapMatierePremiereMontantQuantite.put(matierePremier.getNom()+" "+montant,matierePremier.getNom()+" "+quantite);
						 
							mapMatierePremiereEnVrac.put(matierePremier.getNom()+" "+montant, matierePremier);
							 
						 
						/*if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{*/
							listeColumn.add(matierePremier.getNom()+" "+quantite);
							listeColumn.add(matierePremier.getNom()+" "+prix);
							listeColumn.add(matierePremier.getNom()+" "+montant);
						/*}else
						{*/
							
							 
						/*}*/
						
						
					//	JOptionPane.showMessageDialog(null, matierePremier.getNom() +" : "+new BigDecimal(object[4].toString()));
						//JOptionPane.showMessageDialog(null, matierePremier.getCategorieMp().getSubCategorieMp().getNom() +" : "+new BigDecimal(object[4].toString()));
					}
					
					
				
					
					if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
					{
						
						
						if(object[4]!=null)
						{
							
							mapQuantiteConsomme.put(article.getLiblle()+matierePremier.getNom()+" "+quantite+sousFamilleArticlePF.getCode(), new BigDecimal(object[4].toString()));
						
 						
							
						}
					}else
					{
						
						
						if(matierePremier.getCategorieMp().getSubCategorieMp().getUnite().equals(Constantes.UNITE_PIECE))
						{
							
							if(object[3]!=null)
							{
								if(object[4]!=null)
								{
									mapQuantiteConsomme.put(article.getLiblle()+matierePremier.getNom()+" "+quantite+sousFamilleArticlePF.getCode(), (new BigDecimal(object[3].toString()).add(new BigDecimal(object[4].toString())).setScale(0, BigDecimal.ROUND_DOWN)));
								}else
								{
									mapQuantiteConsomme.put(article.getLiblle()+matierePremier.getNom()+" "+quantite+sousFamilleArticlePF.getCode(), new BigDecimal(object[3].toString()).setScale(0, BigDecimal.ROUND_DOWN));
								}
								
								
							
							}
							
						}else
						{
							if(object[3]!=null)
							{
								if(object[4]!=null)
								{
									mapQuantiteConsomme.put(article.getLiblle()+matierePremier.getNom()+" "+quantite+sousFamilleArticlePF.getCode(), new BigDecimal(object[3].toString()).add(new BigDecimal(object[4].toString())));
								}else
								{
									mapQuantiteConsomme.put(article.getLiblle()+matierePremier.getNom()+" "+quantite+sousFamilleArticlePF.getCode(), new BigDecimal(object[3].toString()));
								}
								
								
							
							}
						}
						
					
						
						
					}
					
				
					
					
					
					}
				
			 
				
			
				
				
				
				
			}
			
			
			
			
			listeColumn.add("Cout En Vrac");
			listeColumn.add("Cout Service");
			listeColumn.add("Montant Total");
			listeColumn.add("Quantite Total");
			listeColumn.add("Prix Par Kg");
			CalculerStock();
			 
			
			
			afficher_tableMP(listeStatistiqueEnVracConsommeLorsDeLaproductionPFClass);
					
	
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		
		
		}
	});
	btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
	JButton btnInitialiser = new JButton();
	btnInitialiser.setBounds(697, 141, 93, 31);
	add(btnInitialiser);
	btnInitialiser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			
			comboArticle.setSelectedItem("");
			txtCodeArticle.setText("");
			combodepot.setSelectedItem("");
			comboMagasin.setSelectedItem("");
			comboFournisseur.setSelectedItem("");
			comboPlusMoins.setSelectedItem("");
			
			dateChooserDu.setDate(null);
			dateChooserAu.setDate(null);
			
		}
	});
	btnInitialiser.setText("Initialiser");
	btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
	 int i=0;
		while(i<listObjectArticleFabriqueAvecsonType.size())
		{
			Object[] object=listObjectArticleFabriqueAvecsonType.get(i);	
			Articles articles=ArticleDAO.findByCode(object[0].toString())	;
			
			
			comboArticle.addItem(articles.getLiblle());
			mapArticle.put(articles.getLiblle(), articles);
			mapCodeArticle.put(articles.getCodeArticle(), articles);
			mapTypeArticle.put(articles.getCodeArticle(), object[1].toString());
			
			i++;
			
		}
	
	comboArticle.setSelectedItem("");
	
	JButton btnExporterExcel = new JButton();
	btnExporterExcel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			

			/*

			
Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());

			 
				
			if(tableStatistiqueEnvracConsomme.getRowCount()!=0)
			{
				
				
				 
				
				
				
				
				String titre="Situation MP Utiliser Lors De La production "+magasin.getLibelle() ;
	    		String titrefeuilleexcel="Situation MP Utiliser Lors De La production  "+magasin.getLibelle();
	    	 ExporterTableVersExcel.tabletoexcelStatistiqueEnVracUtiliserLorsDeLaProduction(tableStatistiqueEnvracConsomme, titre,titrefeuilleexcel);
				
				
			}else
			{
				
				JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
				
				
			}
		
	
	
			*/
			
			
			JFileChooser fchoose = new JFileChooser();
	           int option = fchoose.showSaveDialog(StatistiqueMPUtiliserLorsDeLaProductionPF.this);
	           if(option == JFileChooser.APPROVE_OPTION){
	             String name = fchoose.getSelectedFile().getName(); 
	             String path = fchoose.getSelectedFile().getParentFile().getPath();
	             String file = path + "\\" + name + ".xls"; 
	             export(tableStatistiqueEnvracConsomme, new File(file));
			
			
	           }
			
			
		}
	});
	btnExporterExcel.setText("Exporter Excel");
	btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	btnExporterExcel.setBounds(677, 643, 135, 40);
	btnExporterExcel.setIcon(imgExcel);
	add(btnExporterExcel);
	
	comboSousFamille.addItem("");

	listSousFamilleEnVrac.stream().forEach(e->{
		if(mapSousFamille.get(e.getSousfamile().getCode())==null)
		{
			comboSousFamille.addItem(e.getSousfamile().getCode());
			mapSousFamille.put(e.getSousfamile().getCode(), e.getSousfamile());
		}
		
	});
	 
	comboSousFamille.setSelectedItem("");	
	
	JLabel lblMagasinMp = new JLabel("Magasin MP :");
	lblMagasinMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	lblMagasinMp.setBounds(572, 10, 70, 26);
	layeredPane.add(lblMagasinMp);
	
	 comboMagasinMP = new JComboBox();
	comboMagasinMP.setBounds(652, 10, 271, 27);
	layeredPane.add(comboMagasinMP);
	
	comboMagasinMP.addItem("");
	List<Magasin> listMagasinMP=new ArrayList<Magasin>();
	
	listMagasinMP=depotDAO.findMagasinByCategorieByType(MAGASIN_CODE_CATEGORIE_STOCKAGE,  Constantes.MAGASIN_CODE_TYPE_MP);
	
	for(int j=0;j<listMagasinMP.size();j++)
	{
		
		comboMagasinMP.addItem(listMagasinMP.get(j).getLibelle());	
		mapMagasinMP.put(listMagasinMP.get(j).getLibelle(), listMagasinMP.get(j));
	}
	
	comboMagasinMP.setSelectedItem("");
	
	
	}
	
	
 
	
	
void afficher_tableMP(List<StatistiqueMPConsommeLorsProductionPFClass> listStatistiqueEnVracConsommeLorsProductionPFClass)
	{
	intialiserTableau2();
	/*
	int i=0;
	 
	while(i<listStatistiqueEnVracConsommeLorsProductionPFClass.size())
	{	
		
		
		StatistiqueEnVracConsommeLorsProductionPFClass statistiqueEnVracConsommeLorsProductionPFClass=listStatistiqueEnVracConsommeLorsProductionPFClass.get(i);
	
			
				 Object []ligne={statistiqueEnVracConsommeLorsProductionPFClass.getArticles().getCodeArticle() , statistiqueEnVracConsommeLorsProductionPFClass.getArticles().getLiblle() , statistiqueEnVracConsommeLorsProductionPFClass.getNombreEnvracConsomme(),statistiqueEnVracConsommeLorsProductionPFClass.getMatierePremier().getNom(),statistiqueEnVracConsommeLorsProductionPFClass.getPourcentage()};
				 modeleMP.addRow(ligne);
			 
				
		
		
		
		i++;
	}
	*/
	
//	Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
//	Magasin magasinMP=mapMagasinMP.get(comboMagasinMP.getSelectedItem());
	
	// listObject=productionDAO.listeMPConsommeParArticleParMagasin(dateChooserDu.getDate(), dateChooserAu.getDate(), Constantes.ETAT_OF_TERMINER, magasin,magasinMP);
	/////////////////////////////////////// a supprimer //////////////////////////////////////////
	 
	/*
		
		Vector<Object> data = new Vector<Object>();	
		
		 
		for(int t=0;t<listeColumn.size();t++)
		{
			data.add(listeColumn.get(t));
			
			 
		}
		
		
		modeleMP.addRow(data);
		
		
	 
	
	tableStatistiqueEnvracConsomme.setModel(modeleMP);
	 
/////////////////////////////////////////////////////////////////////////////////////	
*/	
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.000000####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
	
	
	for(int i=0;i<listStatistiqueEnVracConsommeLorsProductionPFClass.size();i++)
	{
		
		Vector<Object> data = new Vector<Object>();	
		
		data.add(listStatistiqueEnVracConsommeLorsProductionPFClass.get(i).getArticles().getLiblle());
		data.add(listStatistiqueEnVracConsommeLorsProductionPFClass.get(i).getSousFamille().getLiblle());
		for(int t=1;t<listeColumn.size();t++)
		{
			data.add("-");
			
			
		}
		
		 modeleMP.addRow(data);
		
		
		
		
		
		
	}
	

	tableStatistiqueEnvracConsomme.setModel(modeleMP);	
	//JOptionPane.showMessageDialog(null, tableStatistiqueEnvracConsomme.getColumnCount());
	
	BigDecimal total=BigDecimal.ZERO;
	
	BigDecimal totalQuantite=BigDecimal.ZERO;

	
	
	for(int y=0;y<tableStatistiqueEnvracConsomme.getRowCount();y++)
	{
	
		total=BigDecimal.ZERO;
		totalQuantite=BigDecimal.ZERO;
		
		for(int d=0;d<tableStatistiqueEnvracConsomme.getColumnCount();d++)
		{
			
			
			if(mapQuantiteConsomme.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+tableStatistiqueEnvracConsomme.getColumnName(d).toString()+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString())!=null)
			{
				
				tableStatistiqueEnvracConsomme.setValueAt(dfDecimal.format( mapQuantiteConsomme.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+tableStatistiqueEnvracConsomme.getColumnName(d).toString()+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString())), y, d);
				 
			
			
			
			
			}
			
			if(mapPrixMoyenneMatierePremiere.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())!=null)
			{
				
				if(!tableStatistiqueEnvracConsomme.getValueAt(y, d-1).equals("-"))
				{
					tableStatistiqueEnvracConsomme.setValueAt(dfDecimal.format( mapPrixMoyenneMatierePremiere.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())), y, d);
					 
					
				}
				
				
				
				
				
			
			}
			
			
			if(mapMatierePremiereMontantPrix.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())!=null)
			{
				
				if(mapMatierePremiereMontantQuantite.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())!=null)
				{
					
					
					
					if(mapQuantiteConsomme.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+mapMatierePremiereMontantQuantite.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString()).toString()+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString())!=null)
					{
						
						
						//System.out.println( mapMatierePremiereMontantQuantite.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString()) +" : "+ mapPrixMoyenneMatierePremiere.get(mapMatierePremiereMontantPrix.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())));
						
					
						if(mapPrixMoyenneMatierePremiere.get(mapMatierePremiereMontantPrix.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString()))!=null)
						{
							
							if(!tableStatistiqueEnvracConsomme.getValueAt(y, d-1).equals("-"))
							{
								
								tableStatistiqueEnvracConsomme.setValueAt(dfDecimal.format( mapPrixMoyenneMatierePremiere.get(mapMatierePremiereMontantPrix.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())).multiply(mapQuantiteConsomme.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+mapMatierePremiereMontantQuantite.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString()))), y, d);
							 
							if(mapMatierePremiereEnVrac.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())!=null)
							{
								
								
									total=total.add( mapPrixMoyenneMatierePremiere.get(mapMatierePremiereMontantPrix.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())).multiply(mapQuantiteConsomme.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+mapMatierePremiereMontantQuantite.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString())+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString())));
									
									if(mapMatierePremiereEnVrac.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString()).getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										
									totalQuantite=totalQuantite.add(mapQuantiteConsomme.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+mapMatierePremiereMontantQuantite.get(tableStatistiqueEnvracConsomme.getColumnName(d).toString()).toString()+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString()));
								
									
									}
								
								
								
								
								
								
							}
							
							 
								
								
								
								
							
							}
							
							
							
							
							
							
							
						}
						
						
						
						
					}
					
					
					
					
					
					
				}
				
				
				
				
			}
			
			
			
			
			
			 
			
			
			
			
			
			
			
		}
		
		tableStatistiqueEnvracConsomme.setValueAt(dfDecimal.format( total) , y, tableStatistiqueEnvracConsomme.getColumnCount()-5);
		
		if(mapMontantServiceParArticle.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString())!=null)
		{
			
			tableStatistiqueEnvracConsomme.setValueAt(dfDecimal.format( mapMontantServiceParArticle.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString())) , y, tableStatistiqueEnvracConsomme.getColumnCount()-4);
			
			tableStatistiqueEnvracConsomme.setValueAt(dfDecimal.format( mapMontantServiceParArticle.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString()).add(total)) , y, tableStatistiqueEnvracConsomme.getColumnCount()-3);

			tableStatistiqueEnvracConsomme.setValueAt(dfDecimal.format(totalQuantite) , y, tableStatistiqueEnvracConsomme.getColumnCount()-2);
			if(totalQuantite.compareTo(BigDecimal.ZERO)!=0)
			{
				tableStatistiqueEnvracConsomme.setValueAt(dfDecimal.format(( mapMontantServiceParArticle.get(tableStatistiqueEnvracConsomme.getValueAt(y, 0).toString()+tableStatistiqueEnvracConsomme.getValueAt(y, 1).toString()).add(total)).divide(totalQuantite, 6, RoundingMode.HALF_UP)  ) , y, tableStatistiqueEnvracConsomme.getColumnCount()-1);
			}
			
		}
		
		
	}
	
	
	/*
	Vector<Object> data = new Vector<Object>();	
	
	for(int t=0;t<modeleMP.getColumnCount();t++)
	{
		data.add(modeleMP.getColumnName(t));
		
		
	}
	
	 modeleMP.addRow(data);
	*/
	
		
			
		
	}









@SuppressWarnings("serial")
void intialiserTableau2(){
	
	
	
	modeleMP = new DefaultTableModel();
	
	
for(int i=0;i<listeColumn.size();i++)
{
		modeleMP.addColumn( listeColumn.get(i));
		


	
	
	 
}

/*
 

tableStatistiqueEnvracConsomme = new JTable( modeleMP ) {

	protected JTableHeader createDefaultTableHeader() {
        return new GroupableTableHeader(columnModel);
    }
  };

  
  
TableColumnModel cm = tableStatistiqueEnvracConsomme.getColumnModel();
JOptionPane.showMessageDialog(null, cm.getColumnCount());
GroupableTableHeader header = (GroupableTableHeader)tableStatistiqueEnvracConsomme.getTableHeader();
int t=0;
for(int i=0;i<listeColumn.size();i++)
{
	t=t+1;
	MatierePremier matierePremier=mapMatierePremiere.get( listeColumn.get(i));
	
	if(matierePremier!=null)
	{
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			
			 util.ColumnGroup g_name = new util.ColumnGroup(listeColumn.get(i));
			    g_name.add(cm.getColumn(t));
			    t=t+1;
			    g_name.add(cm.getColumn(t));
			    t=t+1;
			    g_name.add(cm.getColumn(t));
			    
			    
			   
			   
			    header.addColumnGroup(g_name);
			   
			
		}else
		{
			 
			
			
		}
	}else
	{
		
	}

	
	
	 
}


tableStatistiqueEnvracConsomme.setTableHeader(header);


*/

	
	
 
		
}






public void Vider()
{
	
	combodepot.setSelectedItem("");
	comboMagasin.setSelectedItem("");
	dateChooserDu.setDate(new Date());
	dateChooserAu.setDate(new Date());
	comboSousFamille.setSelectedItem("");
	
	
	
}

public void CalculerStock()
{

	Magasin magasin=mapMagasinMP.get(comboMagasinMP.getSelectedItem());
	

	
	
	
	
	
	

	listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);
	BigDecimal montantfabriquer=new BigDecimal(0);
		BigDecimal quantiteTotalfabriquer=new BigDecimal(0);
		
		
	for(int g=0;g<listDetailTransferStockMPGroupebyMP.size();g++)
	{
		
		
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		listPrixMoyenStockMP.clear();
		boolean existe=false;
			String TousSubCategorie="";
			String Touscategorie="";
			String TousArticle="";
			String requete="";
			if(magasin!=null)
			{
				
				requete=" d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
				
				requete=requete+"and  d.magasinDestination.id = '"+magasin.getId()+"' ";
				
				if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()!=null)
	  		{
					
	  		if(	DateUtils.nbJoursEntre(dateChooserDu.getDate(), dateChooserAu.getDate())>=0)
	  		{
	  			
	  			String dateDu=formatter.format(dateChooserDu.getDate());
	  			String dateAu=formatter.format(dateChooserAu.getDate());
	  			
	  			
	  			requete=requete+"and  d.transferStockMP.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";
	  			
	  			
	  			CategorieMp categorieMP=null;
	  			SubCategorieMp souscategorieMP=null;
	  			
	  			  
	  			
	  			MatierePremier mp=listDetailTransferStockMPGroupebyMP.get(g).getMatierePremier();
	  			categorieMP=mp.getCategorieMp();
	  			souscategorieMP=mp.getCategorieMp().getSubCategorieMp();
	  			if(souscategorieMP!=null)
	  			{
	  				requete=requete+"and  d.matierePremier.categorieMp.subCategorieMp.id = '"+souscategorieMP.getId()+"' ";
	  				
	  			}
	  			
	  			if(categorieMP!=null)
	  			{
	  				requete=requete+"and  d.matierePremier.categorieMp.id = '"+categorieMP.getId()+"' ";
	  				
	  			}
	  			
	  		
	  			
	  			
	  	
	  			
	  			if(mp!=null)
	  			{
	  				
	  				requete=requete+"and  d.matierePremier.id = '"+mp.getId()+"' ";	
	  				
	  				
	  			}
	  			
	  			
	  			
	  			
		  	       	listDetailTransfertStockMP=detailTransferStockMPDAO.listeDetailTransfertMP(requete);
		  			 for(int i=0;i<listDetailTransfertStockMP.size();i++)
		  			 {
		  				 
		  				DetailTransferStockMP detailTransferStockMP= listDetailTransfertStockMP.get(i);
		  				
		  			PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
		  			prixMoyenStockMP.setMp(detailTransferStockMP.getMatierePremier());
		  			prixMoyenStockMP.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
		  			prixMoyenStockMP.setQuantiteInitial(detailTransferStockMP.getQuantite());
		  			prixMoyenStockMP.setMontantInitial(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
		  			prixMoyenStockMP.setQuantiteAchat(BigDecimal.ZERO);	
		  			prixMoyenStockMP.setPrixAchat(BigDecimal.ZERO);
		  			prixMoyenStockMP.setMontantAchat(BigDecimal.ZERO);
		  			prixMoyenStockMP.setQuantiteFabriquer (BigDecimal.ZERO);	
		  			prixMoyenStockMP.setPrixFabriquer(BigDecimal.ZERO);
		  			prixMoyenStockMP.setMontantFabriquer(BigDecimal.ZERO);
		  			prixMoyenStockMP.setQuantiteFinale(detailTransferStockMP.getQuantite());
		  			prixMoyenStockMP.setPrixMoyen(detailTransferStockMP.getPrixUnitaire());
		  			prixMoyenStockMP.setMontantHTFinale(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
		  			
		  			
		  			SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(detailTransferStockMP.getMatierePremier());
		  			if(sousFamilleEnVrac!=null)
		  			{
		  				
		  				prixMoyenStockMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
		  				prixMoyenStockMP.setSousfamille(sousFamilleEnVrac.getSousfamile().getLiblle());
		  				
		  			}else
		  			{
		  				
		  				
		  				prixMoyenStockMP.setFamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
		  				prixMoyenStockMP.setSousfamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getNom());
		  				
		  				
		  			}
		  			
		  			listPrixMoyenStockMP.add(prixMoyenStockMP);
		  				 
		  				 
		  			 }
		  			 
	
		  			 
		  			 
		  			 
		  			 
		  				
		  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateChooserDu.getDate(), dateChooserAu.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
		  				
		  				
		  				
		  				for(int j=0;j<listeObject.size();j++)
		  				{
		  					existe=false;
		  					 Object[] object=listeObject.get(j);
		  					 
		  					 for(int k=0;k<listPrixMoyenStockMP.size();k++)
		  					 {
		  						 
		  						PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(k); 
		  						 
		  						 
		  						 
		  						if(object[0].toString().equals(prixMoyenStockMP.getMp().getCode()))
		  						{
		  							
		  							existe=true;
		  							
		  							prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
						  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
						  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
						  			prixMoyenStockMP.setQuantiteFinale(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()));
						  			prixMoyenStockMP.setPrixMoyen((prixMoyenStockMP.getMontantInitial().add(prixMoyenStockMP.getMontantAchat())).divide(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
						  			prixMoyenStockMP.setMontantHTFinale(prixMoyenStockMP.getQuantiteFinale().multiply(prixMoyenStockMP.getPrixMoyen()));
						  			 
						  			
						  			listPrixMoyenStockMP.set(k, prixMoyenStockMP);
		  							
		  						}
		  						
		  						 
		  					 }
		  					
		  					 if(existe==false)
		  					 {
		  						
		  						MatierePremier matierePremier=matierePremiereDAO.findByCode(object[0].toString()) ;
		  						 
		  						SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(matierePremier);
		  						
		  						
		  						
		  						PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
					  			prixMoyenStockMP.setMp(matierePremier);
					  			prixMoyenStockMP.setPrixInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setQuantiteInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setMontantInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
					  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
					  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
					  			prixMoyenStockMP.setQuantiteFinale(new BigDecimal(object[4].toString()));
					  			prixMoyenStockMP.setPrixMoyen(new BigDecimal(object[5].toString()));
					  			prixMoyenStockMP.setQuantiteFabriquer (BigDecimal.ZERO);	
					  			prixMoyenStockMP.setPrixFabriquer(BigDecimal.ZERO);
					  			prixMoyenStockMP.setMontantFabriquer(BigDecimal.ZERO);
					  			prixMoyenStockMP.setMontantHTFinale(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
					  			
					  			if(sousFamilleEnVrac!=null)
					  			{
					  				
					  				prixMoyenStockMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
					  				prixMoyenStockMP.setSousfamille(sousFamilleEnVrac.getSousfamile().getLiblle());
					  				
					  			}else
					  			{
					  				
					  				
					  				prixMoyenStockMP.setFamille(matierePremier.getCategorieMp().getSubCategorieMp().getNom());
					  				prixMoyenStockMP.setSousfamille(matierePremier.getCategorieMp().getNom());
					  				
					  				
					  			}
					  			
					  			
					  			listPrixMoyenStockMP.add(prixMoyenStockMP); 
		  						 
		  						 
		  						 
		  						 
		  						 
		  						 
		  					 }
		  					 
		  					 
		  					 
		  					 
		  					 
		  					
		  				}
		  				
		  				
		  				listDetailTransferStockMPFabrique=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateChooserDu.getDate(), dateChooserAu.getDate(), mp, ETAT_TRANSFER_STOCK_FABRIQUE,magasin);

		  					// calculer le prix moyen et quantite Fabriquer
		  				
		  				
		  			   
		  			  		montantfabriquer=new BigDecimal(0);
		  			  		quantiteTotalfabriquer=new BigDecimal(0);
		  			  		
		  			  	for(int k=0;k<listDetailTransferStockMPFabrique.size();k++)
		  			  	{
		  			  		
		  			  		if(mp.equals(listDetailTransferStockMPFabrique.get(k).getMatierePremier()))
		  			  		{
		  			  			
		  			  			if(listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		  							{
		  			  				montantfabriquer=montantfabriquer.add(listDetailTransferStockMPFabrique.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPFabrique.get(k).getQuantite()));
		  				    			quantiteTotalfabriquer=quantiteTotalfabriquer.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPFabrique.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
		  							}else
		  							{
		  								montantfabriquer=montantfabriquer.add(listDetailTransferStockMPFabrique.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPFabrique.get(k).getQuantite()));
		  				    			quantiteTotalfabriquer=quantiteTotalfabriquer.add(listDetailTransferStockMPFabrique.get(k).getQuantite());
		  							}
		  			  
		  			  			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		  			  			
		  			  		}
		  			  		
		  			  		
		  			  	}
		  			  		if(!montantfabriquer.equals(BigDecimal.ZERO) && !quantiteTotalfabriquer.equals(BigDecimal.ZERO))
		  			  		{
		  			  			/*	    			
		  			  			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
		  			  			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
		  			  			*/
		  			  			
		  			  			
		  			  			
		  			  			for(int i=0;i<listPrixMoyenStockMP.size();i++)
		  			  	    	{
		  			  			PrixMoyenStockMP prixMoyenStockMPTmp=listPrixMoyenStockMP.get(i); 
		  			  				
		  			  				if(prixMoyenStockMPTmp.getMp().getId()== mp.getId())
		  				    			{
		  			  					 
		  			  			
		  			  					if(prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		  			  					{
		  			  					prixMoyenStockMPTmp.setQuantiteFabriquer (quantiteTotalfabriquer.setScale(0, RoundingMode.CEILING));
		  			  				prixMoyenStockMPTmp.setPrixFabriquer(montantfabriquer.divide(quantiteTotalfabriquer.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		  			  			prixMoyenStockMPTmp.setMontantFabriquer(prixMoyenStockMPTmp.getQuantiteFabriquer().multiply(prixMoyenStockMPTmp.getPrixFabriquer()));
		  					    				 
		  			  						
		  			  						
		  			  					}else
		  			  					{
		  			  						
		  			  					prixMoyenStockMPTmp.setQuantiteFabriquer(quantiteTotalfabriquer);
		  			  				prixMoyenStockMPTmp.setPrixFabriquer(montantfabriquer.divide(quantiteTotalfabriquer,6,RoundingMode.HALF_UP));
		  			  			prixMoyenStockMPTmp.setMontantFabriquer(prixMoyenStockMPTmp.getQuantiteFabriquer().multiply(prixMoyenStockMPTmp.getPrixFabriquer()));
		  					    				 
		  			  					}
		  			  			
		  			  					
		  				    				
		  				    			}
		  			  				prixMoyenStockMPTmp.setPrixMoyen((prixMoyenStockMPTmp.getMontantInitial().add(prixMoyenStockMPTmp.getMontantAchat()).add(prixMoyenStockMPTmp.getMontantFabriquer())).divide(prixMoyenStockMPTmp.getQuantiteAchat().add(prixMoyenStockMPTmp.getQuantiteInitial()).add(prixMoyenStockMPTmp.getQuantiteFabriquer()), 6, RoundingMode.HALF_UP));

		  			  				
		  			  			listPrixMoyenStockMP.set(i, prixMoyenStockMPTmp);	
		  			  	    	}
		  			  			
		  			  			
		  			  			
		  			  			
		  			  			
		  			  		}
		  			  		
		  			  	 		
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  				 
		  				
		  				
		  				for(int y=0;y<listPrixMoyenStockMP.size();y++)
		  				{
		  					
		  					
		  					if(mp.getId()==listPrixMoyenStockMP.get(y).getMp().getId())
		  					{
		  						if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
		  						{
		  							mapPrixMoyenneMatierePremiere.put(mp.getNom()+" "+prix, BigDecimal.ZERO);
		  						}else
		  						{
		  							mapPrixMoyenneMatierePremiere.put(mp.getNom()+" "+prix, listPrixMoyenStockMP.get(y).getPrixMoyen());
		  							
		  						}
		  						
		  				//JOptionPane.showMessageDialog(null, mp.getNom()+" : "+ listPrixMoyenStockMP.get(y).getPrixMoyen());
		  					}
		  					
		  					
		  				}
		  				
		  				
		  				
		  				  
		  			  
		  			  
		  		 
		  			  
		  		
		  		  
	  			
	  		}else
	  		{
	  			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  			return;
	  		}
	  		
	  		}
			}else
			{
				JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
	}
	



}






public void CalculerPrixMoyenAchat( MatierePremier matierePremier)	
{






	
Magasin magasin=mapMagasinMP.get(comboMagasinMP.getSelectedItem());
String TousSubCategorie="";
String Touscategorie="";
String TousArticle="";
if(magasin!=null)
{
	if(dateChooserDu.getDate()!=null && dateChooserAu.getDate()!=null)
	{
		
	if(	DateUtils.nbJoursEntre(dateChooserDu.getDate(), dateChooserAu.getDate())>=0)
	{
		
		CategorieMp categorieMP=null;
		SubCategorieMp souscategorieMP=null;
		
	
	
		
	
		
		MatierePremier mp=matierePremier;
		
		 
				  
				
		listeObjectPrixMoyenAchat=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateChooserDu.getDate(), dateChooserAu.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
				
				  
		
	}else
	{
		JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	}
}else
{
	JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	return;
}














}


public void CalculerPrixMoyenInitial(MatierePremier matierePremier)	

{



		Magasin magasin=mapMagasinMP.get(comboMagasinMP.getSelectedItem());
		
		if(magasin!=null)
		{
			
		BigDecimal quantite=BigDecimal.ZERO;
		BigDecimal quantitefoiprix=BigDecimal.ZERO;
	BigDecimal quantiteOffre=BigDecimal.ZERO;
	BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;
		
		
		
			boolean trouve=false;
			
		String dateDebut=((JTextField)dateChooserDu.getDateEditor().getUiComponent()).getText();
	String dateFin=((JTextField)dateChooserAu.getDateEditor().getUiComponent()).getText();
if(dateDebut.equals(""))	{
	JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
} else if(dateFin.equals("")){
	JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
	

	}else {
		
		listEtatInitialMP.clear();
		listDetailTransferStockMPTmp=detailTransferStockMPDAO.listeDetailTransfertMPByDateByMagasinByStatut(dateChooserDu.getDate(), dateChooserAu.getDate(), magasin,Constantes.ETAT_TRANSFER_STOCK_INITIAL);
	
		if(listDetailTransferStockMPTmp.size()!=0)
		{
			
			
			 for(int k=0;k<listDetailTransferStockMPTmp.size();k++)
			 {
				 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPTmp.get(k);
				SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(detailTransferStockMP.getMatierePremier())	;
				 
				 trouve=false;
				
				 if(listDetailTransferStockMP.size()!=0)
				 {
					
					 for(int i=0;i<listDetailTransferStockMP.size();i++)
					 {
						 if(sousFamilleEnVrac!=null)
						 {
							SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnVracDAO.findByMP(listDetailTransferStockMP.get(i).getMatierePremier())	;
							 if(sousFamilleEnVracTmp!=null)
							 {
								 
								 if(sousFamilleEnVracTmp.getSousfamile().getId()==sousFamilleEnVrac.getSousfamile().getId())
								 {
									 
									 trouve=true;
									 
								 }
								 
								 
							 }
							 
							 
							 
							 
						 }else
						 {
							 
							 if(listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().equals(detailTransferStockMP.getMatierePremier().getCategorieMp()))
							 {
								 trouve=true;
							 } 
							 
						 }
						 
						
						 
						 
						 
						 
					 }
					 
				 }else
				 {
					listDetailTransferStockMP.add(detailTransferStockMP);
					trouve=true;
				 }
				 
				 if(trouve==false)
				 {
					listDetailTransferStockMP.add(detailTransferStockMP);
				 }
			 }
			
			
			
		boolean existe=false;
		
		String famille="";
		String sousFamille="";
			if(listDetailTransferStockMP.size()!=0)
			{
				for(int i=0;i<listDetailTransferStockMP.size();i++)
				{
					CategorieMp sousCategorieMP =listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp();
					quantite=BigDecimal.ZERO;
					quantitefoiprix=BigDecimal.ZERO;
					
				SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(listDetailTransferStockMP.get(i).getMatierePremier())	;
				
				if(sousFamilleEnVrac==null)
				{
					famille=listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getNom();
					sousFamille=listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().getNom();
					
				}else
				{
					
					famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle();
					sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
					
					
				}
					
					existe=false;
				for(int j=0;j<listDetailTransferStockMPTmp.size();j++)	
				{
					
					
					/*
					if(listDetailTransferStockMPTmp.get(j).getMatierePremier().getCategorieMp().equals(sousCategorieMP))
					{*/
					 
					   		if(sousFamilleEnVrac!=null)
					   		{
					   			
					   		SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnVracDAO.findByMP(listDetailTransferStockMPTmp.get(j).getMatierePremier())	;
					   		
					   		if(sousFamilleEnVracTmp!=null)
					   		{
					   			if(sousFamilleEnVracTmp.getSousfamile().getId()==sousFamilleEnVrac.getSousfamile().getId())
					   			{
					   			quantitefoiprix=quantitefoiprix.add(listDetailTransferStockMPTmp.get(j).getQuantite().multiply(listDetailTransferStockMPTmp.get(j).getPrixUnitaire()));
								quantite=quantite.add(listDetailTransferStockMPTmp.get(j).getQuantite());
								existe=true;
					   			}
					   	
							
							
					   		}
					   	 
					   			
					   		}else
					   		{
					   		if(listDetailTransferStockMPTmp.get(j).getMatierePremier().getCategorieMp().equals(sousCategorieMP))
					   		{
					   		quantitefoiprix=quantitefoiprix.add(listDetailTransferStockMPTmp.get(j).getQuantite().multiply(listDetailTransferStockMPTmp.get(j).getPrixUnitaire()));
							quantite=quantite.add(listDetailTransferStockMPTmp.get(j).getQuantite());
							existe=true;
					   		}
					   			
					   	
					   			
					   			
					   		}
					 
					   	
					   
						
						
						
				/*	}*/
					
					
				}
				

				if(existe==true)
				{
					
				
					EtatInitialParSousCtaegorieMP etatInitialParSousCtaegorieMP=new EtatInitialParSousCtaegorieMP();
					etatInitialParSousCtaegorieMP.setSousCategorie(sousCategorieMP);
					etatInitialParSousCtaegorieMP.setCategorie(sousCategorieMP.getSubCategorieMp());
					etatInitialParSousCtaegorieMP.setFamilleArticlePF(famille);
					etatInitialParSousCtaegorieMP.setSousFamilleArticlePF(sousFamille);
					etatInitialParSousCtaegorieMP.setTotalInitial(quantite);
					etatInitialParSousCtaegorieMP.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
					etatInitialParSousCtaegorieMP.setMontant(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(etatInitialParSousCtaegorieMP.getTotalInitial()).setScale(6, RoundingMode.FLOOR));
					listEtatInitialMP.add(etatInitialParSousCtaegorieMP);
				}
				
				
			
				
					
				}
				
				
				
				
			}else
			{
				JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
				listEtatInitialMP.clear();
				
				return;
			}
			
		}
		
		
	}
			
		}
		

	
		
	








}





public void export(JTable table, File file){
    try
    {
      TableModel m = table.getModel();
      FileWriter fw = new FileWriter(file);
      for(int i = 0; i < m.getColumnCount(); i++){
        fw.write(m.getColumnName(i) + "\t");
      }
      fw.write("\n");
      for(int i=0; i < m.getRowCount(); i++) {
        for(int j=0; j < m.getColumnCount(); j++) {
          fw.write(m.getValueAt(i,j).toString()+"\t");
        }
        fw.write("\n");
      }
      fw.close();
    }
    catch(IOException e){ System.out.println(e); }
  }





}
