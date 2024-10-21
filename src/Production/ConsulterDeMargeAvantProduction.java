package Production;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
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
import dao.entity.ClientPF;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatMargeStock;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.EtatValeurStock;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MargeAvantProductionCategorieMP;
import dao.entity.MargeAvantProductionCategoriePF;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Parametre;
import dao.entity.PrixMoyenStockMP;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
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
import javax.swing.JTable;


public class ConsulterDeMargeAvantProduction extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMargeMP;
	private DefaultTableModel	 modeleMargePF;

	private JXTable  tableMargeMP = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<Magasin> listMagasinPF =new ArrayList<Magasin>();
	
	private List<EtatStockMP> listEtatStockMP =new ArrayList<EtatStockMP>();
	private List<EtatMargeStock> listEtatMargeStock =new ArrayList<EtatMargeStock>();
	
	
	
	
	
	//***************************************************
	
	private List<SousFamilleEnVrac> listSousFamilleEnVrac =new ArrayList<SousFamilleEnVrac>();
	
	private List<SousFamilleArticlePF> listSousFamillePF =new ArrayList<SousFamilleArticlePF>();
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<CategorieMp> listCategorie =new ArrayList<CategorieMp>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, Magasin> mapMagasinPF= new HashMap<>();
	private Map< String, CategorieMp> mapCategorie= new HashMap<>();

	private Map< String, MatierePremier> mapMP= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 
	  private JComboBox combomagasinMP;
	 JComboBox combomp = new JComboBox();
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailTransferMPDAO detailTransferStockMPDAO;
private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
private CategorieMpDAO categorieMpDAO;
	private JTextField txtlibelle=new JTextField();
	
	private DepotDAO depotdao;
	
	 private JDateChooser dateProduction;
	 private JDateChooser dateChooser = new JDateChooser();

	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockMPDAO stockMPDAO;
	private SubCategorieMPDAO subCategorieMPDAO;
	private CompteClientDAO compteclientdao;
	private MatierePremiereDAO MatierPremiereDAO;
	String titre="";
	 Workbook workbook = new HSSFWorkbook();
	 JLabel lblDateInitial = new JLabel("Date Initial:");
	 JDateChooser dateinitiale = new JDateChooser();
	 JButton btnTransfert = new JButton("Transfert");
	 private TransferStockMPDAO transferStockMPDAO;
	 JComboBox ComboMagasinPF = new JComboBox();
	 private ParametreDAO parametreDAO;
	 JLabel labelstockinitial = new JLabel("");
	 JLabel labelstockemballage = new JLabel("");
	 JLabel labelstocksansemballage = new JLabel("");
	  JLabel labelstockachat = new JLabel("");
	  JLabel labelachatemballage = new JLabel("");
	  JLabel labelachatsansemballage = new JLabel("");
	  JLabel labelprodentrer = new JLabel("");
	  JLabel labelprodsortie = new JLabel("");
	  JLabel labelcavente = new JLabel("");
	  JLabel labelstockavoir = new JLabel("");
	  JLabel  labelgratuite = new JLabel("");
	  JLabel labelavoiremballage = new JLabel("");
	  JLabel labelavoirsansemballage = new JLabel("");
	  JLabel  labelstockfinale = new JLabel("");
	  JLabel labelfinaleemballage = new JLabel("");
	  JLabel  labelfinalesansemballage = new JLabel("");
	  private DetailPrixArticleDAO detailPrixArticleDAO;
	  private TransferStockPFDAO transferStockPFDAO;
	  
	 
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
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
		private List<DetailTransferStockMP> listDetailTransferStockMPAllMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFService =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMPService =new ArrayList<DetailTransferStockMP>();
		private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp =new ArrayList<DetailMouvementStock>();
		private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer =new ArrayList<DetailTransferStockMP>();
		private List<DetailMouvementStock> listMouvementStockMP =new ArrayList<DetailMouvementStock>();
		private List<DetailMouvementStock> listMouvementStockMPAfficher =new ArrayList<DetailMouvementStock>();
		
		
	  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
	  
	  private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	  private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();
		private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
		private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
		private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();		
		private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
		private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
		
	  private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	  private SousFamilleEnVracDAO sousFamilleEnVracDAO;
	  BigDecimal PrixMoyenFamille=BigDecimal.ZERO;
	  JComboBox comboCategorie = new JComboBox();
		private List<MargeAvantProductionCategorieMP> listMargeAvantProductionMP =new ArrayList<MargeAvantProductionCategorieMP>();
		private List<MargeAvantProductionCategoriePF> listMargeAvantProductionPF =new ArrayList<MargeAvantProductionCategoriePF>();
		private List<PrixMoyenStockMP> listPrixMoyenStockMP =new ArrayList<PrixMoyenStockMP>();
		private DetailFactureAchatMPDAO detailFactureAchatMPdao;
		private List <Object[]> listeObject=new ArrayList<Object[]>();
		JXTable tablemargePF = new JXTable();
		private SousFamilleArticlesPFDAO sousFamilleArticlesPFDAO;
		private FamilleArticlesPFDAO familleArticlesPFDAO;
	  
	  /**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public ConsulterDeMargeAvantProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(Color.GREEN);

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1553, 1062);
      
	
        try{ 
        	
        	
        	imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	mouvementStockGlobaleDAO=new MouvementStockGlobalDAOImpl();
         	MatierPremiereDAO=new MatierePremierDAOImpl();
         	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
         	detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
         	transferStockMPDAO=new TransferStockMPDAOImpl();
         	subCategorieMPDAO=new SubCategorieMPAOImpl();
         listMP=MatierPremiereDAO.findAll();
         parametreDAO = new ParametreDAOImpl();
         detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
      	transferStockPFDAO=new TransferStockPFDAOImpl();
      	categorieMpDAO=new CategorieMpDAOImpl();
     	detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
     	sousFamilleEnVracDAO=new SousFamilleEnVracDAOImpl();
     	 detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();
     	 
     	sousFamilleArticlesPFDAO=new SousFamilleArticlesPFDAOImpl();
     	familleArticlesPFDAO=new FamilleArticlesPFDAOImpl();
     	 
     	 
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableMargeMP.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"REF","STOCK REF ACTUELLE", "PRIX HT REF", "COUT SERVICE", "TOTAL PRIX EN VRAC"
       	}
       ));
       tableMargeMP.getColumnModel().getColumn(0).setPreferredWidth(258);
       tableMargeMP.getColumnModel().getColumn(1).setPreferredWidth(102);
       tableMargeMP.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableMargeMP.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableMargeMP.getColumnModel().getColumn(4).setPreferredWidth(123);
      
				  		
       tableMargeMP.setShowVerticalLines(false);
       tableMargeMP.setSelectionBackground(new Color(51, 204, 255));
       tableMargeMP.setRowHeightEnabled(true);
       tableMargeMP.setBackground(new Color(255, 255, 255));
       tableMargeMP.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableMargeMP.setColumnControlVisible(true);
       tableMargeMP.setForeground(Color.BLACK);
       tableMargeMP.setGridColor(new Color(0, 0, 255));
       tableMargeMP.setAutoCreateRowSorter(true);
       tableMargeMP.setBounds(2, 27, 411, 198);
       tableMargeMP.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableMargeMP);
				  		     	scrollPane.setBounds(10, 195, 1497, 292);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Etat de Stock  En Vrac");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		      
		     	
		      
		     
		     
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Map parameters = new HashMap();


				parameters.put("listeMargeAvantProductionMP",listMargeAvantProductionMP );
				parameters.put("listeMargeAvantProductionPF",listMargeAvantProductionPF );	
				parameters.put("dateProduction",dateProduction.getDate());	
				parameters.put("magasinMP",combomagasinMP.getSelectedItem());	
				parameters.put("magasinPF",ComboMagasinPF.getSelectedItem());	
				parameters.put("categorie",comboCategorie.getSelectedItem());
				parameters.put("categorie",comboCategorie.getSelectedItem());
				
				
				
				
				
try (InputStream source1 = this.getClass().getClassLoader().getResourceAsStream("jasper/MargeAvantProduction_MP.jasper")) {
	    			
	    			File theDir = new File("C:/report");
					if (!theDir.exists()){
					    theDir.mkdirs();
					}
					
					if(source1==null)
					{
						JOptionPane.showMessageDialog(null, "le chemin null !!!!");
						
					}
					
					
					
	    		    java.nio.file.Files.copy (source1, Paths.get("C:/report/MargeAvantProduction_MP.jasper"),StandardCopyOption.REPLACE_EXISTING);
	    		} catch (IOException ex) {
	    		    // An error occurred copying the resource
	    		}
	    		
	    		
	    		
try (InputStream source2 = this.getClass().getClassLoader().getResourceAsStream("jasper/MargeAvantProduction_PF.jasper")) {
	    			
	    			File theDir = new File("C:/report");
					if (!theDir.exists()){
					    theDir.mkdirs();
					}
					
	    		    java.nio.file.Files.copy (source2, Paths.get("C:/report/MargeAvantProduction_PF.jasper"),StandardCopyOption.REPLACE_EXISTING);
	    		} catch (IOException ex) {
	    		    // An error occurred copying the resource
	    		}
Path dest1 = Paths.get("C://report");
				
				  parameters.put("SUBREPORT_DIR",dest1+"//");
				

				JasperUtils.imprimerMargeAvantProduction(listMargeAvantProductionMP,parameters);	
				
				
				
				
				
}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(634, 870, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Marge Avant Production ");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 932, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		
	    		listMargeAvantProductionMP.clear();
	    		   listMargeAvantProductionPF.clear();
	    		
	    		Magasin magasinMP=mapMagasin.get(combomagasinMP.getSelectedItem());
	    		Magasin magasinPF=mapMagasinPF.get(ComboMagasinPF.getSelectedItem());
	    		CategorieMp categorieMp=mapCategorie.get(comboCategorie.getSelectedItem());
	    		Parametre coutService=parametreDAO.findByCode(COUT_SERVICE);
	    	boolean existe=false;
	    	
	    	if(coutService==null)
	    	{
	    		JOptionPane.showMessageDialog(null, "Veuillez Ajouter le cout service dans le parametre SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
	    		
	    	}
	    	else if(dateProduction.getDate()==null)
	    	{
	    		
	    		JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
	    		
	    		
	    	}else if(magasinMP==null)
	    	{
	    		JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin MP SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
	    		
	    	}else if(magasinPF==null)
	    	{
	    		JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin PF SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
	    		
	    	}else if(categorieMp==null)
	    	{
	    		JOptionPane.showMessageDialog(null, "Veuillez selectionner le categorie SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
    			return;
	    	}else
	    	{
	    		
	    		
	    		FamilleArticlePF familleArticlePF=familleArticlesPFDAO.findByLibelle(categorieMp.getNom());
	    		
	    		if(familleArticlePF!=null)
	    		{
	    			
	    			
	    			listSousFamillePF=sousFamilleArticlesPFDAO.listeSousFamillePFByFamilleArticlePF(familleArticlePF.getId());
	    			
	    		}
	    		
	    	for(int i=0;i<listSousFamilleEnVrac.size();i++)
	    	{
	    		
	    		SousFamilleEnVrac sousFamilleEnVrac=listSousFamilleEnVrac.get(i);
	    		if(sousFamilleEnVrac.getMatierePremier().getCategorieMp().getId()==categorieMp.getId())
	    		{
	    			
	    			existe=false;	
	    			
	    			for(int j=0;j<listMargeAvantProductionMP.size();j++)
	    			{
	    				
	    				if(listMargeAvantProductionMP.get(j).getSousFamille().getId()==sousFamilleEnVrac.getSousfamile().getId())
	    				{
	    					
	    					existe=true;	
	    					
	    					
	    				}
	    				
	    				
	    				
	    				
	    				
	    				
	    			}
	    			
	    			
	    			
	    			if(existe==false)
	    			{
	    				
	    				MargeAvantProductionCategorieMP margeAvantProductionCategorie=new MargeAvantProductionCategorieMP();
	    				if(!magasinPF.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF))
	    				{
	    					margeAvantProductionCategorie.setCoutService(coutService.getValeur());
	    				}else
	    				{
	    					margeAvantProductionCategorie.setCoutService(BigDecimal.ZERO);
	    				}
	    				
	    				margeAvantProductionCategorie.setPrixMoyen(BigDecimal.ZERO);
	    				margeAvantProductionCategorie.setSousFamille(sousFamilleEnVrac.getSousfamile());
	    				margeAvantProductionCategorie.setStock(BigDecimal.ZERO);
	    				margeAvantProductionCategorie.setMontant(BigDecimal.ZERO);
	    				margeAvantProductionCategorie.setTotalPrix(margeAvantProductionCategorie.getCoutService().add(margeAvantProductionCategorie.getPrixMoyen()));
	    				listMargeAvantProductionMP.add(margeAvantProductionCategorie);
	    				
	    				
	    				
	    			}
	    			
	    			
	    			
	    		}
	    		
	    		
	    		
	    	}
	    	
	    	
///////////////////////////////////////////////////////////////////////////////// Calculer  le Stock MP Par Categorie ////////////////////////////////////////////////////////////////////////	    	
	    	
	    	Calculer_Prix_Moyenne_MP();
	    	
	    	SousFamilleArticlePF sousFamilleArticlePF=null;
	    for(int i=0;i<listEtatStockMP.size();i++)
	    {
	    	
	    	EtatStockMP etatStockMP=listEtatStockMP.get(i);
	    	
	    	
	    		
	    	
	    		sousFamilleArticlePF=null;
	    		if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	    		{
	    			
	    			
	    			
	    			for(int d=0;d<listSousFamilleEnVrac.size();d++)
	    			{
	    				
	    				SousFamilleEnVrac sousFamilleEnVrac=listSousFamilleEnVrac.get(d);	
	    				if(sousFamilleEnVrac.getMatierePremier().getId()==etatStockMP.getMp().getId())
	    				{
	    					
	    					sousFamilleArticlePF=sousFamilleEnVrac.getSousfamile();
	    					
	    					
	    				}
	    				
	    				
	    			}
	    			
	    			if(sousFamilleArticlePF!=null)
	    			{
	    				for(int j=0;j<listMargeAvantProductionMP.size();j++)
		    	    	{
		    				MargeAvantProductionCategorieMP avantProductionCategorie=listMargeAvantProductionMP.get(j);
		    				
		    				
		    				if(sousFamilleArticlePF.getId()==avantProductionCategorie.getSousFamille().getId())
		    				{
		    					
		    					
		    					avantProductionCategorie.setMontant(avantProductionCategorie.getMontant().add(etatStockMP.getMontantFinale()));
		    					avantProductionCategorie.setStock(avantProductionCategorie.getStock().add(etatStockMP.getQuantiteFinale()));
		    					if(avantProductionCategorie.getMontant().compareTo(BigDecimal.ZERO)>0)
		    					{
		    						
		    						avantProductionCategorie.setPrixMoyen(avantProductionCategorie.getMontant().divide(avantProductionCategorie.getStock(), 6, RoundingMode.HALF_UP));
		    						
		    					}
		    					
		    					
		    					avantProductionCategorie.setTotalPrix(avantProductionCategorie.getCoutService().add(avantProductionCategorie.getPrixMoyen()));
		    					
		    					listMargeAvantProductionMP.set(j, avantProductionCategorie);
		    					
		    				}
		    				
		    				
		    				
		    				
		    	    	}
	    				
	    			}
	    		
	    			
	    			
	    		}
	    		
	    			
	    	
	    }
	    	
///////////////////////////////////////////////////////////////////////////////////////////////////////// Calculer Stock PF  ///////////////////////////////////////// ////////////////////////////////////////////////////////////////////////	    	
    		    	
	    CalculerPrixMoyenFamille( magasinPF);
	   boolean trouve=false; 	
	    for(int k=0;k<listEtatStockPF.size();k++)
	    {
	    	
	    EtatStockPF etatStockPF=listEtatStockPF.get(k);	
	    
	    boolean exist=false;
	    
	    
	    for (int t=0;t<listMargeAvantProductionMP.size();t++)
	    {
	    	
	    	
	    	if(listMargeAvantProductionMP.get(t).getSousFamille().getId()==etatStockPF.getSousFamille().getId())
	    	{
	    		
	    		
	    		exist=true;
	    	}
	    	
	    }
	    
	    
	    if(exist==true)
	    {
	    	
	    	System.out.println(etatStockPF.getSousFamille().getLiblle()); 
	    	
	        for (int t=0;t<listMargeAvantProductionMP.size();t++)
		    {
		    	boolean find=false;
		    	
	        	  for (int c=0;c<listMargeAvantProductionPF.size();c++)
	  		    {
	        		  
	        		 if(listMargeAvantProductionPF.get(c).getArticles().getId()==etatStockPF.getArticle().getId())
	        		 {
	        			 
	        			 if(listMargeAvantProductionPF.get(c).getSousFamille().getId()==listMargeAvantProductionMP.get(t).getSousFamille().getId())
		        		 {
		        			 
	        				 find=true;
		        			 
		        			 
		        			 
		        		 } 
	        			 
	        			 
	        			 
	        		 }
	        		  
	        		  
	        		  
	        		  
	  		    }
	        	  
	        	  
	        	  if(find==false)
	        	  {
	        		 
	        		  
		MargeAvantProductionCategoriePF margeAvantProductionCategoriePF=new MargeAvantProductionCategoriePF();	
	    				
	    				margeAvantProductionCategoriePF.setArticles(etatStockPF.getArticle());
	    				margeAvantProductionCategoriePF.setSousFamille(listMargeAvantProductionMP.get(t).getSousFamille());
	    			
	    				margeAvantProductionCategoriePF.setStock(BigDecimal.ZERO);
	    			
	  				 				

	  				 				
	  				 				
	  				 				
	  				 				
	  				 	
	  				 		
	  				 		margeAvantProductionCategoriePF.setPrixVente(BigDecimal.ZERO);	
	  				 		margeAvantProductionCategoriePF.setPrixEnvrac(listMargeAvantProductionMP.get(t).getTotalPrix());
	  				 		if(margeAvantProductionCategoriePF.getPrixVente().compareTo(BigDecimal.ZERO)!=0)
	  				 		{
		  				 		margeAvantProductionCategoriePF.setMarge(((margeAvantProductionCategoriePF.getPrixVente().subtract(margeAvantProductionCategoriePF.getPrixEnvrac())).divide(margeAvantProductionCategoriePF.getPrixVente(), 6, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

	  				 		}else
	  				 		{
	  				 			margeAvantProductionCategoriePF.setMarge(BigDecimal.ZERO);
	  				 		}
	    				
	  				 		listMargeAvantProductionPF.add(margeAvantProductionCategoriePF);
	        		  
	        		  
	        	  }
		    	
		    	
		    	
		    	
		    	
		    }
	    	
	    /*	for(int y=0;y<listMargeAvantProductionPF.size();y++)
	    	{
	    		
	    		JOptionPane.showMessageDialog(null, listMargeAvantProductionPF.get(y).getSousFamille().getLiblle());
	    	}
	    	
	    	*/
		    
		    
	    	for(int d=0;d<listMargeAvantProductionMP.size();d++)
	    	{
	    		
	    		MargeAvantProductionCategorieMP avantProductionCategorie=listMargeAvantProductionMP.get(d);	
	    		
	    		if(avantProductionCategorie.getSousFamille().getId()==etatStockPF.getSousFamille().getId())
	    		{
	    			trouve=false; 	
	    			
	    		for(int j=0;j<listMargeAvantProductionPF.size();j++)	
	    		{
	    			
	    			
	    		MargeAvantProductionCategoriePF margeAvantProductionCategoriePF=listMargeAvantProductionPF.get(j);	
	    			
	    			if(margeAvantProductionCategoriePF.getSousFamille().getId()==etatStockPF.getSousFamille().getId())
	    			{
	    				
	    			if(margeAvantProductionCategoriePF.getArticles().getId()==etatStockPF.getArticle().getId())	
	    			{
	    				BigDecimal prixVente=BigDecimal.ZERO;
	    				margeAvantProductionCategoriePF.setArticles(etatStockPF.getArticle());
	    				margeAvantProductionCategoriePF.setSousFamille(etatStockPF.getSousFamille());
	    				margeAvantProductionCategoriePF.setStock(etatStockPF.getQuantiteFinale());
	    			
	  				 				
  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(etatStockPF.getArticle().getId(), magasinPF.getId(),etatStockPF.getSousFamille().getFamileArticlePF().getId() , etatStockPF.getSousFamille().getId());

	  				 			if(detailprixarticleTmp!=null)	
	  				 			{
	  				 				
	  				 				prixVente=detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP);
	  				 				
	  				 			}else
	  				 			{
	  				 				
	  				 				prixVente=BigDecimal.ZERO;
	  				 			
	  				 			}
	  				 				
	  				 				
	  				 				
	  				 				
	  				 	
	  				 		
	  				 		margeAvantProductionCategoriePF.setPrixVente(prixVente);	
	  				 	//	margeAvantProductionCategoriePF.setPrixEnvrac(avantProductionCategorie.getTotalPrix());
	  				 		if(margeAvantProductionCategoriePF.getPrixVente().compareTo(BigDecimal.ZERO)!=0)
	  				 		{
		  				 		margeAvantProductionCategoriePF.setMarge(((margeAvantProductionCategoriePF.getPrixVente().subtract(margeAvantProductionCategoriePF.getPrixEnvrac())).divide(margeAvantProductionCategoriePF.getPrixVente(), 6, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

	  				 		}else
	  				 		{
	  				 			margeAvantProductionCategoriePF.setMarge(BigDecimal.ZERO);
	  				 		}
	    				
	  				 		listMargeAvantProductionPF.set(j,margeAvantProductionCategoriePF);
	    				
	    				
	    			
	    				
	    				
	    				trouve=true; 
	    				
	    				
	    			}
	    				
	    				
	    				
	    				
	    			}
	    			
	    			
	    			
	    			
	    		}
	    			
	    			if(trouve==false)
	    			{
	    				BigDecimal prixVente=BigDecimal.ZERO;
	    				MargeAvantProductionCategoriePF margeAvantProductionCategoriePF=new MargeAvantProductionCategoriePF();	
	    				
	    				margeAvantProductionCategoriePF.setArticles(etatStockPF.getArticle());
	    				margeAvantProductionCategoriePF.setSousFamille(etatStockPF.getSousFamille());
	    				margeAvantProductionCategoriePF.setStock(etatStockPF.getQuantiteFinale());
	    			
	  				 				
  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(etatStockPF.getArticle().getId(), magasinPF.getId(),etatStockPF.getSousFamille().getFamileArticlePF().getId() , etatStockPF.getSousFamille().getId());

	  				 			if(detailprixarticleTmp!=null)	
	  				 			{
	  				 				
	  				 				prixVente=detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP);
	  				 				
	  				 			}else
	  				 			{
	  				 				
	  				 				prixVente=BigDecimal.ZERO;
	  				 			
	  				 			}
	  				 				
	  				 				
	  				 				
	  				 				
	  				 	
	  				 		
	  				 		margeAvantProductionCategoriePF.setPrixVente(prixVente);	
	  				 		//margeAvantProductionCategoriePF.setPrixEnvrac(avantProductionCategorie.getTotalPrix());
	  				 		if(margeAvantProductionCategoriePF.getPrixVente().compareTo(BigDecimal.ZERO)!=0)
	  				 		{
		  				 		margeAvantProductionCategoriePF.setMarge(((margeAvantProductionCategoriePF.getPrixVente().subtract(margeAvantProductionCategoriePF.getPrixEnvrac())).divide(margeAvantProductionCategoriePF.getPrixVente(), 6, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

	  				 		}else
	  				 		{
	  				 			margeAvantProductionCategoriePF.setMarge(BigDecimal.ZERO);
	  				 		}
	    				
	  				 		listMargeAvantProductionPF.add(margeAvantProductionCategoriePF);
	    			}
	    			
	    		}else
	    		{
	    			
	    			
	    			
	    			trouve=false; 	
	    			
		    		for(int j=0;j<listMargeAvantProductionPF.size();j++)	
		    		{
		    			
		    			BigDecimal prixVente=BigDecimal.ZERO;
		    		MargeAvantProductionCategoriePF margeAvantProductionCategoriePF=listMargeAvantProductionPF.get(j);	
		    			
		    			if(margeAvantProductionCategoriePF.getSousFamille().getId()==etatStockPF.getSousFamille().getId())
		    			{
		    				
		    			if(margeAvantProductionCategoriePF.getArticles().getId()==etatStockPF.getArticle().getId())	
		    			{
		    				margeAvantProductionCategoriePF.setArticles(etatStockPF.getArticle());
		    				margeAvantProductionCategoriePF.setSousFamille(etatStockPF.getSousFamille());
		    				margeAvantProductionCategoriePF.setStock(etatStockPF.getQuantiteFinale());
		    			
		  				 				
	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(etatStockPF.getArticle().getId(), magasinPF.getId(),etatStockPF.getSousFamille().getFamileArticlePF().getId() , etatStockPF.getSousFamille().getId());

		  				 			if(detailprixarticleTmp!=null)	
		  				 			{
		  				 				
		  				 				prixVente=detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP);
		  				 				
		  				 			}else
		  				 			{
		  				 				
		  				 				prixVente=BigDecimal.ZERO;
		  				 			
		  				 			}
		  				 				
		  				 				
		  				 				
		  				 				
		  				 	
		  				 		
		  				 		margeAvantProductionCategoriePF.setPrixVente(prixVente);	
		  				 	//	margeAvantProductionCategoriePF.setPrixEnvrac(avantProductionCategorie.getTotalPrix());
		  				 		if(margeAvantProductionCategoriePF.getPrixVente().compareTo(BigDecimal.ZERO)!=0)
		  				 		{
			  				 		margeAvantProductionCategoriePF.setMarge(((margeAvantProductionCategoriePF.getPrixVente().subtract(margeAvantProductionCategoriePF.getPrixEnvrac())).divide(margeAvantProductionCategoriePF.getPrixVente(), 6, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

		  				 		}else
		  				 		{
		  				 			margeAvantProductionCategoriePF.setMarge(BigDecimal.ZERO);
		  				 		}
		    				
		  				 		listMargeAvantProductionPF.set(j,margeAvantProductionCategoriePF);
		    				
		  				 		trouve=true; 
		    			}
		    				
		    				
		    				
		    				
		    			}
		    			
		    			
		    			
		    			
		    		}
		    			
		    			if(trouve==false)
		    			{
		    				BigDecimal prixVente=BigDecimal.ZERO;
		    				MargeAvantProductionCategoriePF margeAvantProductionCategoriePF=new MargeAvantProductionCategoriePF();	
		    				
		    				margeAvantProductionCategoriePF.setArticles(etatStockPF.getArticle());
		    				margeAvantProductionCategoriePF.setSousFamille(etatStockPF.getSousFamille());
		    				margeAvantProductionCategoriePF.setStock(etatStockPF.getQuantiteFinale());
		    			
		  				 				
	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(etatStockPF.getArticle().getId(), magasinPF.getId(),etatStockPF.getSousFamille().getFamileArticlePF().getId() , etatStockPF.getSousFamille().getId());

		  				 			if(detailprixarticleTmp!=null)	
		  				 			{
		  				 				
		  				 				prixVente=detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP);
		  				 				
		  				 			}else
		  				 			{
		  				 				
		  				 				prixVente=BigDecimal.ZERO;
		  				 			
		  				 			}
		  				 				
		  				 				
		  				 				
		  				 				
		  				 	
		  				 		
		  				 		margeAvantProductionCategoriePF.setPrixVente(prixVente);	
		  				 	//	margeAvantProductionCategoriePF.setPrixEnvrac(avantProductionCategorie.getTotalPrix());
		  				 		if(margeAvantProductionCategoriePF.getPrixVente().compareTo(BigDecimal.ZERO)!=0)
		  				 		{
			  				 		margeAvantProductionCategoriePF.setMarge(((margeAvantProductionCategoriePF.getPrixVente().subtract(margeAvantProductionCategoriePF.getPrixEnvrac())).divide(margeAvantProductionCategoriePF.getPrixVente(), 6, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)));

		  				 		}else
		  				 		{
		  				 			margeAvantProductionCategoriePF.setMarge(BigDecimal.ZERO);
		  				 		}
		    				
		  				 		listMargeAvantProductionPF.add(margeAvantProductionCategoriePF);
		    			}
	    			
	    			
	    			
	    			
	    			
	    			
	    		}
	    		
	    		
	    		
	    		
	    	}
	    	
	    }
	    

	    		    		
	    	
	    }
	    		
	    		
	    		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////////////////	    	
	    	
	    afficher_tableMargeMP(listMargeAvantProductionMP);
	    afficher_tableMargePF(listMargeAvantProductionPF);
	    
	    
	    		
	    	}
	    	
	    	
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(534, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(62, 57, 1384, 51);
	    add(layeredPane);
	    
	    JLabel lblDateProduction = new JLabel("Date Production:");
	    lblDateProduction.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDateProduction.setBounds(10, 11, 125, 24);
	    layeredPane.add(lblDateProduction);
	    
	     dateProduction = new JDateChooser();
	    dateProduction.setLocale(Locale.FRANCE);
	    dateProduction.setDateFormatString("dd/MM/yyyy");
	    dateProduction.setBounds(143, 11, 169, 26);
	    layeredPane.add(dateProduction);
	    int i=0;
		while(i<listMP.size())
		{
			MatierePremier mp=listMP.get(i);
			combomp.addItem(mp.getNom());
			mapMP.put(mp.getNom(), mp);
			mapCodeMP.put(mp.getCode(), mp);
			
			
			i++;
			
		}
	    
	    JLabel lblMagasinMp = new JLabel("Magasin MP  :");
	    lblMagasinMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblMagasinMp.setBounds(337, 11, 79, 26);
	    layeredPane.add(lblMagasinMp);
	    
	     combomagasinMP = new JComboBox();
	    combomagasinMP.setBounds(426, 11, 235, 27);
	    layeredPane.add(combomagasinMP);
	  try {
		  
		 
          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
          dateProduction.setDate(new Date());
          
          JLabel lblMagasinPf = new JLabel("Magasin PF  :");
          lblMagasinPf.setFont(new Font("Tahoma", Font.PLAIN, 11));
          lblMagasinPf.setBounds(687, 11, 79, 26);
          layeredPane.add(lblMagasinPf);
          
           ComboMagasinPF = new JComboBox();
          ComboMagasinPF.setBounds(776, 11, 235, 27);
          layeredPane.add(ComboMagasinPF);
          
          JLabel lblCategorie = new JLabel("Categorie  :");
          lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 11));
          lblCategorie.setBounds(1032, 11, 79, 26);
          layeredPane.add(lblCategorie);
          
           comboCategorie = new JComboBox();
          comboCategorie.setBounds(1121, 11, 235, 27);
          layeredPane.add(comboCategorie);
		  
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	    
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		
	     		
	    		combomagasinMP.setSelectedItem("");
	    		ComboMagasinPF.setSelectedIndex(-1);


	     		
	     		
	     	
	    		
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	 
	    
	    
	    
	    
	    
	    
	  
		    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_MP);
				  int k=0;
				  combomagasinMP.addItem("");
			     	while (k<listMagasin.size())
			     	{
			     		Magasin magasin=listMagasin.get(k);
			     		
			     		
			     		combomagasinMP.addItem(magasin.getLibelle());
					     		
					     		mapMagasin.put(magasin.getLibelle(), magasin);
					     	
			     		k++;
			     		
			     	}
			      
			
	    
	    
	     
	      listMagasinPF=depotdao.listeMagasinByTypeMagasin( Constantes.MAGASIN_CODE_TYPE_PF);
	      
for(int j=0;j<listMagasinPF.size();j++)
{
	Magasin magasin=listMagasinPF.get(j);
	ComboMagasinPF.addItem(magasin.getLibelle());
	mapMagasinPF.put(magasin.getLibelle(), magasin);
}

	    
	    
	    SubCategorieMp subCategorieMp=subCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
listCategorie=categorieMpDAO.findBySubcategorie(subCategorieMp.getId());
comboCategorie.addItem("");
tablemargePF = new JXTable();

JScrollPane scrollPane_1 = new JScrollPane((Component) null);
scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
scrollPane_1.setBounds(10, 510, 1497, 349);
add(scrollPane_1);
 tablemargePF = new JXTable();
tablemargePF.setModel(new DefaultTableModel(
	new Object[][] {
	},
	new String[] {
			"Article", "Famille", "Sous Famille", "Prix vente", "Stock Actuelle", "Prix En Vrac", "Marge"	
	}
));
tablemargePF.setShowVerticalLines(false);
tablemargePF.setSelectionBackground(new Color(51, 204, 255));
tablemargePF.setRowHeightEnabled(true);
tablemargePF.setRowHeight(20);
tablemargePF.setGridColor(Color.BLUE);
tablemargePF.setForeground(Color.BLACK);
tablemargePF.setColumnControlVisible(true);
tablemargePF.setBackground(Color.WHITE);
tablemargePF.setAutoCreateRowSorter(true);
scrollPane_1.setViewportView(tablemargePF);
tablemargePF.getColumnModel().getColumn(0).setPreferredWidth(258);
tablemargePF.getColumnModel().getColumn(1).setPreferredWidth(102);
tablemargePF.getColumnModel().getColumn(2).setPreferredWidth(102);
tablemargePF.getColumnModel().getColumn(3).setPreferredWidth(91);
tablemargePF.getColumnModel().getColumn(4).setPreferredWidth(123);
	    for(int j=0;j<listCategorie.size();j++)
	    {
	    	
	    CategorieMp categorieMp=listCategorie.get(j);
	    comboCategorie.addItem(categorieMp.getNom());
	    mapCategorie.put(categorieMp.getNom(), categorieMp);
	    	
	    	
	    }
	    
	listSousFamilleEnVrac=sousFamilleEnVracDAO.findAll();    
	    
		
		}
	
	

	
	
	
	void InitialiseTableauMargeMP()
	{
		modeleMargeMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"REF","STOCK REF ACTUELLE", "PRIX HT REF", "COUT SERVICE", "TOTAL PRIX EN VRAC"				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMargeMP.setModel(modeleMargeMP);
		 tableMargeMP.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableMargeMP.getColumnModel().getColumn(1).setPreferredWidth(102);
	       tableMargeMP.getColumnModel().getColumn(2).setPreferredWidth(102);
	       tableMargeMP.getColumnModel().getColumn(3).setPreferredWidth(91);
	       tableMargeMP.getColumnModel().getColumn(4).setPreferredWidth(123);
	      
	     
}
	
	
	void InitialiseTableauMargePF()
	{
		modeleMargePF =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article", "Famille", "Sous Famille", "Prix vente", "Stock Actuelle", "Prix En Vrac", "Marge"		}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tablemargePF.setModel(modeleMargePF);
		tablemargePF.getColumnModel().getColumn(0).setPreferredWidth(258);
		tablemargePF.getColumnModel().getColumn(1).setPreferredWidth(102);
		tablemargePF.getColumnModel().getColumn(2).setPreferredWidth(102);
		tablemargePF.getColumnModel().getColumn(3).setPreferredWidth(91);
		tablemargePF.getColumnModel().getColumn(4).setPreferredWidth(123);
		tablemargePF.getColumnModel().getColumn(5).setPreferredWidth(91);
		tablemargePF.getColumnModel().getColumn(6).setPreferredWidth(123);
	      
	     
}
	
	
	
	
	
	
/*	void InitialiseTableauMouvementStock()
	{
		modeleMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date Mouvement", "Depot", "Magasin"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modeleMouvementStock);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		
		
	
}*/
	
	
	
	void afficher_tableMargeMP(List<MargeAvantProductionCategorieMP> listMargeAvantProductionCategorieMP)
	{

	InitialiseTableauMargeMP();
	
	int i=0;
	 
	while(i<listMargeAvantProductionCategorieMP.size())
	{	
		MargeAvantProductionCategorieMP  margeAvantProductionCategorieMP=listMargeAvantProductionCategorieMP.get(i);
		
		Object []ligne={ margeAvantProductionCategorieMP.getSousFamille().getLiblle(),margeAvantProductionCategorieMP.getStock(),margeAvantProductionCategorieMP.getPrixMoyen(),margeAvantProductionCategorieMP.getCoutService(),margeAvantProductionCategorieMP.getTotalPrix()};

		modeleMargeMP.addRow(ligne);
		i++;
	}
	
	
	
	
	
	
	}
	
	
	void afficher_tableMargePF(List<MargeAvantProductionCategoriePF> listMargeAvantProductionCategoriePF)
	{

		modeleMargePF =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article", "Famille", "Sous Famille", "Prix vente", "Stock Actuelle", "Prix En Vrac", "Marge"	
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			tablemargePF.setModel(modeleMargePF);
	
	
	int i=0;
	 
	
	while(i<listMargeAvantProductionCategoriePF.size())
	{	
		MargeAvantProductionCategoriePF  margeAvantProductionCategoriePF=listMargeAvantProductionCategoriePF.get(i);
		
		Object []ligne={margeAvantProductionCategoriePF.getArticles().getLiblle(),margeAvantProductionCategoriePF.getSousFamille().getFamileArticlePF().getLiblle(),margeAvantProductionCategoriePF.getSousFamille().getLiblle(),margeAvantProductionCategoriePF.getPrixVente(),margeAvantProductionCategoriePF.getStock(),margeAvantProductionCategoriePF.getPrixEnvrac(),margeAvantProductionCategoriePF.getMarge()};

		modeleMargePF.addRow(ligne);
		i++;
	}
	
	
	
	
	
	
	}
	
	
	
	
	
public void CalculerPrixMoyenFamille(  Magasin magasin)
{
	

	
	detailTransferStockPFDAO.ViderSession();
	
	
	listEtatStockPF.clear();
	 List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProduction =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajne =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajneGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	

	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	BigDecimal montantInitial=new BigDecimal(0);
	BigDecimal quantiteTotalInitial=new BigDecimal(0);
	BigDecimal PrixInitial=new BigDecimal(0);
	BigDecimal montantachat=new BigDecimal(0);
	BigDecimal quantiteTotalachat=new BigDecimal(0);
	BigDecimal quantiteTotalvente=new BigDecimal(0);
	BigDecimal montantvente=new BigDecimal(0);
	BigDecimal quantiteTotalavoir=new BigDecimal(0);
	BigDecimal montantavoir=new BigDecimal(0);
	BigDecimal quantiteTotalEntrer=new BigDecimal(0);
	BigDecimal montantEntrer=new BigDecimal(0);
	BigDecimal quantiteTotalGratuit=new BigDecimal(0);
	BigDecimal montantGratuit=new BigDecimal(0);
	BigDecimal quantiteTotalproduction=new BigDecimal(0);
	BigDecimal prixmoyenproduction=new BigDecimal(0);
	BigDecimal prixmoyenachat=new BigDecimal(0);
	BigDecimal prixmoyenvente=new BigDecimal(0);
	BigDecimal prixmoyentransferentrer=new BigDecimal(0);
	BigDecimal prixmoyenavoir=new BigDecimal(0);
	BigDecimal montantproduction=new BigDecimal(0);
	
	BigDecimal quantiteTotalFinale=new BigDecimal(0);
	BigDecimal montantFinale=new BigDecimal(0);
	boolean trouve=false;
	FamilleArticlePF familleArticle=null;
	Articles article=null;

	
	Date datedebut=null;
	
	if(magasin!=null)
	{
		
		if( dateProduction.getDate()!=null)
		{
			
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			String dateyearproduction=formatter.format(dateProduction.getDate());
			
			
	
				
				try {
					datedebut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/"+dateyearproduction);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}
		
		   		


		
		listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_INITIAL,magasin, familleArticle);
		
		listDetailTransferStockPFAchat=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		listDetailTransferStockPFAchatGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		
		listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateProduction.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateProduction.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		
		
		listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		
		

		listDetailTransferStockPFEntrerProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		listDetailTransferStockPFEntrerProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		
		listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
		listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
		
		
		
		
		listDetailTransferStockPFAvoir=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
		listDetailTransferStockPFAvoirGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
		
		listDetailTransferStockPFAvoirMarajne=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		listDetailTransferStockPFAvoirMarajneGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateProduction.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		
		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		
		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
		{
			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
			EtatStockPF etatstock=new EtatStockPF();
			etatstock.setArticle(detailtransferstockpf.getArticle());
			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
			etatstock.setFamilleArticle(detailtransferstockpf.getSousFamille().getFamileArticlePF());
			etatstock.setQuantiteInitial(BigDecimal.ZERO);
			etatstock.setPrixInitial(BigDecimal.ZERO);
			etatstock.setMontantInitial(BigDecimal.ZERO);
			etatstock.setQuantiteAchat(BigDecimal.ZERO);
			etatstock.setPrixAchat(BigDecimal.ZERO);
			etatstock.setMontantAchat(BigDecimal.ZERO);
			etatstock.setQuantiteProduction(BigDecimal.ZERO);
			etatstock.setPrixProduction(BigDecimal.ZERO);
			etatstock.setMontantProduction(BigDecimal.ZERO);
			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			etatstock.setPrixSortie(BigDecimal.ZERO);
			etatstock.setMontantSortie(BigDecimal.ZERO);
			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
			etatstock.setPrixEntrer(BigDecimal.ZERO);
			etatstock.setMontantEntrer(BigDecimal.ZERO);
			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
			etatstock.setPrixAvoir(BigDecimal.ZERO);
			etatstock.setMontantAvoir(BigDecimal.ZERO);
			etatstock.setMarge(BigDecimal.ZERO);
			etatstock.setQuantiteGratuit(BigDecimal.ZERO);
			etatstock.setPrixGratuit(BigDecimal.ZERO);
			etatstock.setMontantGratuit(BigDecimal.ZERO);
			etatstock.setQuantiteFinale(BigDecimal.ZERO);
			etatstock.setPrixFinale(BigDecimal.ZERO);
			etatstock.setMontantFinale(BigDecimal.ZERO);
			listEtatStockPF.add(etatstock);
			
		}
		
		// charger list Etat stock Articles initialiser les enregistrement des achats et ventes par zero
		for(int i=0;i<listDetailTransferStockPFInitial.size();i++)
		{
			
			
			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFInitial.get(i);
			/*
			EtatStockPF etatstock=new EtatStockPF();
			etatstock.setArticle(detailtransferstockpf.getArticle());
			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
			etatstock.setQuantiteInitial(detailtransferstockpf.getQuantite());
			*/
		
				
			
				PrixInitial=detailtransferstockpf.getPrixUnitaire();
			
				montantInitial=detailtransferstockpf.getQuantite().multiply(detailtransferstockpf.getPrixUnitaire());
			
			   quantiteTotalInitial=detailtransferstockpf.getQuantite();
			
			/*
			etatstock.setQuantiteAchat(BigDecimal.ZERO);
			etatstock.setPrixAchat(BigDecimal.ZERO);
			etatstock.setMontantAchat(BigDecimal.ZERO);
			etatstock.setQuantiteProduction(BigDecimal.ZERO);
			etatstock.setPrixProduction(BigDecimal.ZERO);
			etatstock.setMontantProduction(BigDecimal.ZERO);
			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			etatstock.setPrixSortie(BigDecimal.ZERO);
			etatstock.setMontantSortie(BigDecimal.ZERO);
			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
			etatstock.setPrixAvoir(BigDecimal.ZERO);
			etatstock.setMontantAvoir(BigDecimal.ZERO);
			
			listEtatStockPF.add(etatstock);
			*/
			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
    		{
    			
    			for(int p=0;p<listEtatStockPF.size();p++)
    	    	{
    				if(listEtatStockPF.get(p).getArticle().equals(listDetailTransferStockPFInitial.get(i).getArticle()) && listEtatStockPF.get(p).getSousFamille().equals(listDetailTransferStockPFInitial.get(i).getSousFamille()) )
	    			{
	    				EtatStockPF etatstockpf=listEtatStockPF.get(p);
	    				etatstockpf.setQuantiteInitial(quantiteTotalInitial);
	    				etatstockpf.setPrixInitial(PrixInitial);
	    				etatstockpf.setMontantInitial(montantInitial);
	    				listEtatStockPF.set(p, etatstockpf);
	    			
	    				
	    			}
    	    	}
    			
    			
    			
    		}
			
			
			
		}
		
		
		// calculer le prix moyen et quantite achat
		
		
	for(int j=0;j<listDetailTransferStockPFAchatGroupebyPF.size();j++)
	{
		montantachat=new BigDecimal(0);
		quantiteTotalachat=new BigDecimal(0);
		prixmoyenachat= new BigDecimal(0);
		
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFAchat.size();k++)
	{
		
		if(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle().equals(listDetailTransferStockPFAchat.get(k).getArticle()) && listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille().equals(listDetailTransferStockPFAchat.get(k).getSousFamille()))
		{
			montantachat=montantachat.add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFAchat.get(k).getQuantite()));
			prixmoyenachat=((prixmoyenachat.multiply(quantiteTotalachat)).add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAchat.get(k).getQuantite()))).divide(quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite()),6, RoundingMode.DOWN) ;

			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite());
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPF.size();i++)
	    	{
				if(listEtatStockPF.get(i).getArticle().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle()) && listEtatStockPF.get(i).getSousFamille().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille()) )
    			{
    				EtatStockPF etatstockpf=listEtatStockPF.get(i);
    				etatstockpf.setQuantiteAchat(quantiteTotalachat);
    				etatstockpf.setPrixAchat((prixmoyenachat));
    				etatstockpf.setMontantAchat(etatstockpf.getQuantiteAchat().multiply(etatstockpf.getPrixAchat()));
    				listEtatStockPF.set(i, etatstockpf);
    			
    				
    			}
	    	}
			
			
			
		}
		
	}
	
	
// calculer Quantite Production et le prix moyen
	

	
	for(int i=0;i<listDetailTransferStockPFProductionGroupebyPF.size();i++)
	{
		quantiteTotalproduction=BigDecimal.ZERO;
		montantproduction=new BigDecimal(0);
		prixmoyenproduction=BigDecimal.ZERO;
		
		for(int j=0;j<listDetailTransferStockPFProduction.size();j++)
		{
			
			if(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFProduction.get(j).getArticle()) && listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFProduction.get(j).getSousFamille()))
			{
				
				montantproduction=montantproduction.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFProduction.get(j).getQuantite())).setScale(6, RoundingMode.DOWN);
				
				prixmoyenproduction=((prixmoyenproduction.multiply(quantiteTotalproduction)).add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFProduction.get(j).getQuantite()))).divide(quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite()),6, RoundingMode.DOWN) ;
				quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite());
				
			
				
			}
			
			
		}
		
		if(!montantproduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalproduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteProduction(quantiteTotalproduction);
	    			etatstockpf.setPrixProduction(prixmoyenproduction);
	    			etatstockpf.setMontantProduction(quantiteTotalproduction.multiply(prixmoyenproduction));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    			

	    			
	    		}
	    		
	    		
	    	}
			
		}
		
		
		
	}
	
	
	
	
	// calculer Quantite Vente et le prix moyen
	
	
	
	for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++)
	{
		quantiteTotalvente=BigDecimal.ZERO;
		quantiteTotalGratuit=BigDecimal.ZERO;
		montantvente=new BigDecimal(0);
		prixmoyenvente=new BigDecimal(0);
		
		for(int j=0;j<listDetailTransferStockPFSortie.size();j++)
		{
			
			if(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFSortie.get(j).getArticle()) && listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFSortie.get(j).getSousFamille()))
			{
				
				
				// la quantite Gratuité
				if(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
				{
					
					quantiteTotalGratuit=quantiteTotalGratuit.add(listDetailTransferStockPFSortie.get(j).getQuantite());
					
					
					
				}else
				{
					
					// la quantite Vente
					
					montantvente=montantvente.add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFSortie.get(j).getQuantite()));
    				prixmoyenvente=((prixmoyenvente.multiply(quantiteTotalvente)).add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFSortie.get(j).getQuantite()))).divide(quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite()),6, RoundingMode.DOWN) ;

					quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite());
					
					
				}
				
				
			}
			
			
		}
		
		if(!quantiteTotalvente.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
	    			etatstockpf.setPrixSortie(prixmoyenvente);
	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(prixmoyenvente));
	    		
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
		
		if(!quantiteTotalGratuit.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteGratuit(quantiteTotalGratuit.setScale(6));
	    			if(etatstockpf.getPrixAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
	    			{
	    				etatstockpf.setPrixGratuit(etatstockpf.getPrixInitial().setScale(6));
	    			}else
	    			{
	    				etatstockpf.setPrixGratuit(etatstockpf.getPrixAchat().setScale(6));
	    			}
	    			
	    			etatstockpf.setMontantGratuit(etatstockpf.getQuantiteGratuit().setScale(6).multiply(etatstockpf.getPrixGratuit().setScale(6)));;
	    			
	    			 // ajouter la quantite gratuité et le prix gratuité(prix d'achat)
	    			
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    	}
			
			
		}
		
		
	}
	
// calculer Quantite Transfer Entrer et le prix moyen les MP Transfert EN PF vien de Production
	
	
	
	for(int i=0;i<listDetailTransferStockPFEntrerProductionGroupebyPF.size();i++)
	{
		quantiteTotalEntrer=BigDecimal.ZERO;
		montantEntrer=new BigDecimal(0);
		prixmoyentransferentrer=new BigDecimal(0);
		
		for(int j=0;j<listDetailTransferStockPFEntrerProduction.size();j++)
		{
			
			if(listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrerProduction.get(j).getArticle().getId() && listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getSousFamille().getId()==listDetailTransferStockPFEntrerProduction.get(j).getSousFamille().getId())
			{
				
				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()));
				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFEntrerProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()),6, RoundingMode.DOWN) ;

				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getSousFamille().getId())
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
	    			etatstockpf.setPrixEntrer(prixmoyentransferentrer);
	    			etatstockpf.setMontantEntrer(quantiteTotalEntrer.multiply(prixmoyentransferentrer));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
	}
	
	
// calculer Quantite Transfer Entrer et le prix moyen (Les MP transfer En PF )
	
	
	
	for(int i=0;i<listDetailTransferStockPFEntrerGroupebyPF.size();i++)
	{
		quantiteTotalEntrer=BigDecimal.ZERO;
		montantEntrer=new BigDecimal(0);
		prixmoyentransferentrer=new BigDecimal(0);
		
		for(int j=0;j<listDetailTransferStockPFEntrer.size();j++)
		{
			
			if(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrer.get(j).getArticle().getId() && listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFEntrer.get(j).getSousFamille().getId())
			{
				
				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()));
				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite()),6, RoundingMode.DOWN) ;

				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId())
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
	    			etatstockpf.setPrixEntrer(prixmoyentransferentrer);
	    			etatstockpf.setMontantEntrer(quantiteTotalEntrer.multiply(prixmoyentransferentrer));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
	}    	
	
	
	
	
	
	
	
// calculer Quantite avoir et le prix moyen
	
	
	
	for(int i=0;i<listDetailTransferStockPFAvoirGroupebyPF.size();i++)
	{
		quantiteTotalavoir=BigDecimal.ZERO;
		montantavoir=new BigDecimal(0);
		prixmoyenavoir=BigDecimal.ZERO;
		for(int j=0;j<listDetailTransferStockPFAvoir.size();j++)
		{
			
			if(listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFAvoir.get(j).getArticle().getId() && listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFAvoir.get(j).getSousFamille().getId() && listDetailTransferStockPFAvoir.get(j).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
			{
				
				montantavoir=montantavoir.add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()));
				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite()),6, RoundingMode.DOWN) ;

				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille().getId() && listEtatStockPF.get(k).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
	    			etatstockpf.setPrixAvoir(prixmoyenavoir);
	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(prixmoyenavoir));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
		
		
	}
	
	
// calculer Quantite avoir Marjane et le prix moyen
	
	
	
	for(int i=0;i<listDetailTransferStockPFAvoirMarajneGroupebyPF.size();i++)
	{
		quantiteTotalavoir=BigDecimal.ZERO;
		montantavoir=new BigDecimal(0);
		prixmoyenavoir=BigDecimal.ZERO;
		for(int j=0;j<listDetailTransferStockPFAvoirMarajne.size();j++)
		{
			
			if(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFAvoirMarajne.get(j).getArticle()) && listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFAvoirMarajne.get(j).getSousFamille()) && listDetailTransferStockPFAvoirMarajne.get(j).getTransferStockPF().getCodeTransfer().contains("_R"))
			{
				
				montantavoir=montantavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()));
				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoirMarajne.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()),6, RoundingMode.DOWN) ;

				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getSousFamille()) )
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
	    			etatstockpf.setPrixAvoir(prixmoyenavoir);
	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(prixmoyenavoir));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
		
		
	} 	
	
	
	
	
	
	
	Calculer_Prix_Moyenne_PF(); // Calculer le Prix Moyen a partir de mouvement de stock 
	
	
	// Calculer Stock Finale
	
	
   	for(int i=0;i<listEtatStockPF.size();i++)
	{
   	 BigDecimal prixMoyen=BigDecimal.ZERO;
   	 BigDecimal QuantiteTotal=BigDecimal.ZERO;
   		   quantiteTotalFinale=BigDecimal.ZERO;
   		    montantFinale=BigDecimal.ZERO;
			EtatStockPF etatstockpf=listEtatStockPF.get(i);
			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()))).subtract(etatstockpf.getQuantiteSortie().add(etatstockpf.getQuantiteAvoir()).add(etatstockpf.getQuantiteGratuit())));
			quantiteTotalFinale=quantiteTotalFinale.add(etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer())).subtract(etatstockpf.getQuantiteAvoir().add(etatstockpf.getQuantiteSortie()).add(etatstockpf.getQuantiteGratuit())));
			montantFinale=etatstockpf.getMontantInitial().add(etatstockpf.getMontantAchat().add(etatstockpf.getMontantProduction().add(etatstockpf.getMontantEntrer())));
			QuantiteTotal=etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction().add(etatstockpf.getQuantiteEntrer())));
			/*
			 * if(!QuantiteTotal.setScale(2,
			 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,
			 * RoundingMode.HALF_UP))) { prixMoyen=montantFinale.divide(QuantiteTotal, 6,
			 * RoundingMode.DOWN); etatstockpf.setPrixFinale(prixMoyen.setScale(6,
			 * RoundingMode.DOWN));
			 * etatstockpf.setMontantFinale(etatstockpf.getPrixFinale().multiply(etatstockpf
			 * .getQuantiteFinale())); }else {
			 * etatstockpf.setPrixFinale(prixMoyen.setScale(6, RoundingMode.DOWN));
			 * 
			 * }
			 */
			
			if(listMouvementStockPFAfficherTmp.size()!=0)
    		{
    			for(int l=0;l<listMouvementStockPFAfficherTmp.size();l++)
    			{
    				
    				
    				
    				if(etatstockpf.getArticle().getId() ==listMouvementStockPFAfficherTmp.get(l).getArticle().getId() && etatstockpf.getSousFamille().getId()==listMouvementStockPFAfficherTmp.get(l).getSousFamille().getId())
    				{
    					
    				
    					
    					prixMoyen=listMouvementStockPFAfficherTmp.get(l).getPrixFinal();
    					//System.out.println(listMouvementStockPFAfficherTmp.get(l).getArticle().getLiblle() + listMouvementStockPFAfficherTmp.get(l).getSousFamille().getLiblle() +" : Initial "+listMouvementStockPFAfficherTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockPFAfficherTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockPFAfficherTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockPFAfficherTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockPFAfficherTmp.get(l).getEntrerProduction() + " Transfer Enter : "+listMouvementStockPFAfficherTmp.get(l).getTransferEntrer() + " Vente : "+listMouvementStockPFAfficherTmp.get(l).getVente() + " Quantite Finale : "+listMouvementStockPFAfficherTmp.get(l).getStockFinal() + "  prix Moyen : "+listMouvementStockPFAfficherTmp.get(l).getPrixFinal() ) ;
    					
    				}
    				
    			}
    			
    		}else
    		{
    			for(int l=0;l<listMouvementStockPFAfficher.size();l++)
    			{
    				if(etatstockpf.getArticle().getId() ==listMouvementStockPFAfficher.get(l).getArticle().getId() && etatstockpf.getSousFamille().getId()==listMouvementStockPFAfficher.get(l).getSousFamille().getId())
    				{
    					
    				
    					
    					prixMoyen=listMouvementStockPFAfficher.get(l).getPrixFinal();
    					//System.out.println(listMouvementStockPFAfficher.get(l).getArticle().getLiblle() + listMouvementStockPFAfficher.get(l).getSousFamille().getLiblle() +" : Initial "+listMouvementStockPFAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockPFAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockPFAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockPFAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockPFAfficher.get(l).getEntrerProduction() + " Transfer Enter : "+listMouvementStockPFAfficher.get(l).getTransferEntrer()+ " Vente : "+listMouvementStockPFAfficher.get(l).getVente() + " Quantite Finale : "+listMouvementStockPFAfficher.get(l).getStockFinal() + "  prix Moyen : "+listMouvementStockPFAfficher.get(l).getPrixFinal() ) ;
    					
    				}
    			}
    			
    		}
			
			
			if(etatstockpf.getQuantiteFinale().compareTo(BigDecimal.ZERO)==0)
			{
				prixMoyen=BigDecimal.ZERO;
			}
			
			etatstockpf.setPrixFinale(prixMoyen);
    		etatstockpf.setMontantFinale(((etatstockpf.getQuantiteFinale())).multiply(etatstockpf.getPrixFinale()));
		
			
			
			if(etatstockpf.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
			{
				etatstockpf.setMarge(BigDecimal.ZERO);
				
			}else
			{
				etatstockpf.setMarge((etatstockpf.getPrixSortie().subtract(etatstockpf.getPrixInitial())).divide(etatstockpf.getPrixSortie(), RoundingMode.DOWN));
			}
			
			
			listEtatStockPF.set(i, etatstockpf);
		
		
	}
   	

   	
   
   	
   	
	}else
	{
		

		JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}
	

	
	
 	
	
	
}
	
	
	
	
	
	
	
	
void Calculer_Prix_Moyenne_PF()
{
	

	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfyear=new SimpleDateFormat("yyyy");
	listMouvementStockPF.clear();
	detailTransferStockPFDAO.ViderSession();
	boolean trouve=false;
	Articles article=null;
	Magasin magasin=mapMagasinPF.get(ComboMagasinPF.getSelectedItem());
	if(magasin!=null)
	{
		
   		
		String dateyearproduction=sdfyear.format(dateProduction.getDate());
		Date datedebut=null;
		try {
			datedebut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/"+dateyearproduction);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listDetailTransferStockPF=detailTransferStockPFDAO.findAllTransferStockPFOrderByDateTransfer(magasin);
		listDetailTransferStockPFGroupebyArticle=detailTransferStockPFDAO.findAllTransferStockPFGroupeByDateTransferByArticle(magasin);
		listDetailTransferStockPFBytypetransfer=detailTransferStockPFDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP,ETAT_TRANSFER_STOCK_ENTRER_PF_MP};
		BigDecimal achat=BigDecimal.ZERO;
		BigDecimal Prixachat=BigDecimal.ZERO;
		BigDecimal vente=BigDecimal.ZERO;
		BigDecimal TransferEntrer=BigDecimal.ZERO;
		BigDecimal PrixTransferEntrer=BigDecimal.ZERO;
		BigDecimal avoir=BigDecimal.ZERO;
		BigDecimal initial=BigDecimal.ZERO;
		BigDecimal prixinitial=BigDecimal.ZERO;
		BigDecimal production=BigDecimal.ZERO;
		BigDecimal Prixproduction=BigDecimal.ZERO;
		BigDecimal stockfinal=BigDecimal.ZERO;
		BigDecimal prixmoyenne=BigDecimal.ZERO;
		BigDecimal quantitefinale=BigDecimal.ZERO;
		for(int i=0;i<listDetailTransferStockPFGroupebyArticle.size();i++)
		{
			
			achat=new BigDecimal(0);
			vente=new BigDecimal(0);
			initial=new BigDecimal(0);
			production=new BigDecimal(0);
			stockfinal=new BigDecimal(0);
			avoir=new BigDecimal(0);
			TransferEntrer=new BigDecimal(0);
			Prixachat=new BigDecimal(0);
			 PrixTransferEntrer=new BigDecimal(0);
			 prixinitial=new BigDecimal(0);
			 Prixproduction=new BigDecimal(0);
			
			
				if(i!=0)
    			{
    				if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() != listDetailTransferStockPFGroupebyArticle.get(i-1).getArticle().getId())
    				{
    					prixmoyenne=new BigDecimal(0);
		    			quantitefinale=new BigDecimal(0);
    					
    				}
    				if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() == listDetailTransferStockPFGroupebyArticle.get(i-1).getArticle().getId())
    				{
    				if( listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId() != listDetailTransferStockPFGroupebyArticle.get(i-1).getSousFamille().getId())
    				{
    					prixmoyenne=new BigDecimal(0);
		    			quantitefinale=new BigDecimal(0);
    				}
    				}
    				
    				
    			}else
    			{
    				prixmoyenne=new BigDecimal(0);
	    			quantitefinale=new BigDecimal(0);
    			}
			 
			 
			 
			for(int j=0;j<listDetailTransferStockPF.size();j++)
			{
				

				for(int k=0;k<typetransfer.length;k++)
    			{
					
				
			if(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer().equals(listDetailTransferStockPF.get(j).getTransferStockPF().getDateTransfer()) 
					&& listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId()== listDetailTransferStockPF.get(j).getArticle().getId() && listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId()== listDetailTransferStockPF.get(j).getSousFamille().getId() && listDetailTransferStockPF.get(j).getTypeTransfer().equals(typetransfer[k]))	
			{
				
				if(listDetailTransferStockPF.get(j).getArticle().getCodeArticle().equals("2007"))
    			{
    				System.out.print("yes");
    			}
		
			
				
				achat=new BigDecimal(0);
    			vente=new BigDecimal(0);
    			initial=new BigDecimal(0);
    			production=new BigDecimal(0);
    			stockfinal=new BigDecimal(0);
    			avoir=new BigDecimal(0);
    			TransferEntrer=new BigDecimal(0);
    			Prixachat=new BigDecimal(0);
    			 PrixTransferEntrer=new BigDecimal(0);
    			 prixinitial=new BigDecimal(0);
    			 Prixproduction=new BigDecimal(0);
				
				
				
			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
			{
				
		    	   if(!achat.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
              	   {
                  	   Prixachat=(Prixachat.multiply(achat).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(achat.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

              	   }else
              	   {
              		   Prixachat=BigDecimal.ZERO;
              	   }
				
				achat=achat.add(listDetailTransferStockPF.get(j).getQuantite());
			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
			{
				vente=vente.add(listDetailTransferStockPF.get(j).getQuantite());
				
			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
			{
				 prixinitial=listDetailTransferStockPF.get(j).getPrixUnitaire();
				initial=initial.add(listDetailTransferStockPF.get(j).getQuantite());
				
			}else if(typetransfer[k].equals(TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
			{
				   if(!production.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
              	   {
                  	   Prixproduction=(Prixproduction.multiply(production).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(production.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)),RoundingMode.DOWN);

              	   }else
              	   {
              		   Prixachat=BigDecimal.ZERO;
              	   }
				
				production=production.add(listDetailTransferStockPF.get(j).getQuantite());	
				
				
			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
			{ 
				if(listDetailTransferStockPF.get(j).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
				{
					avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
				}
				
				
				
				
			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
			{ 
				   if(!TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
              	   {
                  	   PrixTransferEntrer=(PrixTransferEntrer.multiply(TransferEntrer).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

              	   }else
              	   {
              		 PrixTransferEntrer=BigDecimal.ZERO;
              	   }
				
				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
				
				
				
			}
			
			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_MP))
			{ 
				   if(!TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
           	   {
               	   PrixTransferEntrer=(PrixTransferEntrer.multiply(TransferEntrer).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

           	   }else
           	   {
           		 PrixTransferEntrer=BigDecimal.ZERO;
           	   }
				
				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
				
				
				
			}
			
			
			
			if(!quantitefinale.setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
			{
    			

				if(!quantitefinale.add(achat).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
    			{
	    			
if(quantitefinale.add(achat).add(TransferEntrer).add(production).compareTo(BigDecimal.ZERO)!=0)
{
prixmoyenne=(achat.multiply(Prixachat).add(quantitefinale.multiply(prixmoyenne)).add(TransferEntrer.multiply(PrixTransferEntrer)).add(production.multiply(Prixproduction))).divide(quantitefinale.add(achat).add(TransferEntrer).add(production),6, RoundingMode.DOWN);
//System.out.println(listDetailTransferStockPF.get(j).getArticle().getLiblle() + " : "+ listDetailTransferStockPF.get(j).getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

}
					
    			}else
    			{
    				prixmoyenne=BigDecimal.ZERO;

    			}
				
				

				quantitefinale=(quantitefinale.add(achat).add(TransferEntrer).add(production)).subtract(vente.add(avoir));
				
			}else
			{
				
				
				if(!initial.add(achat).add(production).add(TransferEntrer).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
    			{

					prixmoyenne=(achat.multiply(Prixachat).add(initial.multiply(prixinitial)).add(PrixTransferEntrer.multiply(TransferEntrer).add(Prixproduction.multiply(production)))).divide(initial.add(achat).add(production).add(TransferEntrer), 6, RoundingMode.DOWN);
					
    			
    			}else
    			{
    				prixmoyenne=BigDecimal.ZERO;

    			}
				
				
				quantitefinale=(initial.add(achat).add(TransferEntrer).add(production)).subtract(vente.add(avoir));

				
			}
			
						    				
			}
					    			
			}
			}
			
			if(listMouvementStockPF.size()!=0)
			{
				for(int d=0;d<listMouvementStockPF.size();d++)
				{
					
					if(listMouvementStockPF.get(d).getArticle().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() && listMouvementStockPF.get(d).getSousFamille().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId())
					{
						initial=listMouvementStockPF.get(d).getStockFinal();
						prixinitial=listMouvementStockPF.get(d).getPrixFinal();
						trouve=true;
						
					}
				}
			
				
			}
			if(trouve==false)
			{
				for(int l=0;l<listDetailTransferStockPFBytypetransfer.size();l++)
				{
	if(listDetailTransferStockPFBytypetransfer.get(l).getArticle().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() && listDetailTransferStockPFBytypetransfer.get(l).getSousFamille().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId())
	{
		initial=listDetailTransferStockPFBytypetransfer.get(l).getQuantite();
			
	}
				}
				
			
			}
			
			if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getCodeArticle().equals("2007"))
			{
				System.out.print("yes");
			}
			
			stockfinal=(initial.add(production).add(achat).add(TransferEntrer)).subtract(vente.add(avoir));
			MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
			mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer());
			mouvementstockPF.setAchat(achat);
			mouvementstockPF.setPrixAchat(Prixachat);
			mouvementstockPF.setVente(vente);
			mouvementstockPF.setAvoir(avoir);
			mouvementstockPF.setInitial(initial);
			mouvementstockPF.setPrixInitial(prixinitial);
			mouvementstockPF.setEntrerProduction(production);
			mouvementstockPF.setPrixProduction(Prixproduction);
			mouvementstockPF.setTransferEntrer(TransferEntrer);
			mouvementstockPF.setPrixTransferEntrer(PrixTransferEntrer);
			mouvementstockPF.setSousFamille(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille());
			mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticle.get(i).getArticle());
			
			
			
			/*
			 * if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().
			 * getCodeArticle().equals("NP1000")) {
			 * 
			 * if((mouvementstockPF.getAchat().add(mouvementstockPF.getInitial()).add(
			 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.
			 * getEntrerProduction())).setScale(6,
			 * RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6,
			 * RoundingMode.HALF_UP))!=0) {
			 * prixmoyenne=(mouvementstockPF.getAchat().multiply(mouvementstockPF.
			 * getPrixAchat()).add(mouvementstockPF.getInitial().multiply(mouvementstockPF.
			 * getPrixInitial())).add(mouvementstockPF.getPrixTransferEntrer().multiply(
			 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getPrixProduction(
			 * ).multiply(mouvementstockPF.getEntrerProduction())))).divide(mouvementstockPF
			 * .getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.
			 * getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()),
			 * RoundingMode.HALF_UP);
			 * 
			 * //JOptionPane.showMessageDialog(null,
			 * "Prix Moyenne calculé "+listDetailTransferStockPFGroupebyArticle.get(i).
			 * getArticle().getLiblle()); //JOptionPane.showMessageDialog(null,
			 * "Prix Moyenne calculé est : "+mouvementstockPF.getAchat()
			 * +"*"+mouvementstockPF.getPrixAchat() + "+"+mouvementstockPF.getPrixInitial()
			 * +"*"+mouvementstockPF.getInitial()
			 * +" + "+mouvementstockPF.getPrixTransferEntrer()+"*"+mouvementstockPF.
			 * getTransferEntrer() +" + "+mouvementstockPF.getPrixProduction() +" * "+
			 * mouvementstockPF.getEntrerProduction());
			 * 
			 * //JOptionPane.showMessageDialog(null,
			 * "Prix Moyenne calculé est : "+prixmoyenne); }else {
			 * prixmoyenne=BigDecimal.ZERO; //JOptionPane.showMessageDialog(null,
			 * "Prix Moyenne Zero"); }
			 * 
			 * 
			 * 
			 * 
			 * }
			 */
			
			
			/*
			 * if((mouvementstockPF.getAchat().add(mouvementstockPF.getInitial()).add(
			 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.
			 * getEntrerProduction())).setScale(6,
			 * RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6,
			 * RoundingMode.HALF_UP))!=0) {
			 * prixmoyenne=(mouvementstockPF.getAchat().multiply(mouvementstockPF.
			 * getPrixAchat()).add(mouvementstockPF.getInitial().multiply(mouvementstockPF.
			 * getPrixInitial())).add(mouvementstockPF.getPrixTransferEntrer().multiply(
			 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getPrixProduction(
			 * ).multiply(mouvementstockPF.getEntrerProduction())))).divide(mouvementstockPF
			 * .getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.
			 * getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()),
			 * RoundingMode.HALF_UP);
			 * 
			 * }else { prixmoyenne=BigDecimal.ZERO; }
			 */

			System.out.println( "new "+mouvementstockPF.getArticle().getLiblle() + " : "+mouvementstockPF.getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

			mouvementstockPF.setPrixFinal(prixmoyenne); 
			mouvementstockPF.setStockFinal((mouvementstockPF.getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.getEntrerProduction()).add(mouvementstockPF.getTransferEntrer())).subtract(mouvementstockPF.getVente().add(mouvementstockPF.getAvoir())));
			
			listMouvementStockPF.add(mouvementstockPF);
			
			
			

		}
		
	
		
		
		
		// detailtransfer entre deux date et par article
		
		if(datedebut!=null && dateProduction.getDate()!=null && article!=null)
		{
			listMouvementStockPFAfficher.clear();
			listMouvementStockPFAfficherTmp.clear();
			
		
		for(int i=0;i<listMouvementStockPF.size();i++)	
		{
			String ddebut=sdf.format(datedebut);
			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
			
			if(listMouvementStockPF.get(i).getDateStockPF().after(datedebut) ==true || ddebutTmp.equals(ddebut)   )
				
			{
			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()))
			{

				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));	
			}
				
			
			}
			
		}
			
		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
		{
			
			String dfin=sdf.format(dateProduction.getDate());
			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateProduction.getDate())==true || dfinTmp.equals(dfin) )
			{
			if(listMouvementStockPFAfficher.get(j).getArticle().getLiblle().equals(article.getLiblle()))
			{
				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
			}
				
			}
			
		}
		
	
			
		// detailtransfer entre deux date (date fin null) et par article 
			
		}else if(datedebut!=null && dateProduction.getDate()==null && article!=null)
		{
			listMouvementStockPFAfficherTmp.clear();
			String d1=sdf.format(datedebut);
		
			
			for(int i=0;i<listMouvementStockPF.size();i++)	
    		{
				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
    			if(ddbut.equals(d1) && listMouvementStockPF.get(i).getArticle().equals(article) )
    			{
    			
    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
    			
    			}
    			
    		}
			
			
			
			// detailtransfer entre deux date (date fin null)  
			
		}else if(datedebut!=null && dateProduction.getDate()==null && article==null)
		{
			
			listMouvementStockPFAfficherTmp.clear();
              
              String d1=sdf.format(datedebut);
    			
			
			for(int i=0;i<listMouvementStockPF.size();i++)	
    		{
				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
    			if(ddbut.equals(d1) )
    			{
    			
    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
    			
    			}
    		
    		}
			
		
			
			// detailtransfer par article
		}else if(datedebut==null && dateProduction.getDate()==null && article!=null)
		{
			listMouvementStockPFAfficherTmp.clear();
         
           
           
			for(int i=0;i<listMouvementStockPF.size();i++)	
    		{
    			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()) )
    			{
    			
    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
    			
    				
    			}
    			
    		}
			
			
			
			
			
			// detailtransfer entre deux date  
			
		}else if(datedebut!=null && dateProduction.getDate()!=null && article==null)
		{
			listMouvementStockPFAfficher.clear();
			listMouvementStockPFAfficherTmp.clear();
		
		for(int i=0;i<listMouvementStockPF.size();i++)	
		{
			String ddebut=sdf.format(datedebut);
			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
			if(listMouvementStockPF.get(i).getDateStockPF().after(datedebut) ==true || ddebutTmp.equals(ddebut))
			{
				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));
			
				
			}
			
		}
			
		
		
		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
		{
			
			String dfin=sdf.format(dateProduction.getDate());
			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
			
			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateProduction.getDate())==true || dfinTmp.equals(dfin)  )
			{
			
				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
			
			}
			
		}
		
	
				    			
		}
		
	
	
	}else
	{
		JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}

	

	
	
}


void Calculer_Prix_Moyenne_MP()
{
	

	detailTransferStockMPDAO.ViderSession();
	listEtatStockMP.clear();
	listDetailTransferStockMPInitial.clear() ;
	 listDetailTransferStockMPAchat.clear();
	 listDetailTransferStockMPAchatGroupebyMP.clear();
	 listDetailTransferStockMPSortie.clear();
	 listDetailTransferStockMPSortieGroupebyMP.clear();
	 listDetailTransferStockMPAvoir.clear();
	 listDetailTransferStockMPAvoirGroupebyMP.clear();
	 listDetailTransferStockMPChargeSupp.clear();
	 listDetailTransferStockMPChargeSuppGroupebyMP.clear();
	  listDetailTransferStockMPService.clear();
	 listDetailTransferStockMPServiceGroupebyMP.clear();
	 listDetailTransferStockMPVente.clear();
     listDetailTransferStockMPVenteGroupebyMP.clear();
	 listDetailTransferStockMPAllMP.clear();
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfyear=new SimpleDateFormat("yyyy");
	BigDecimal montantInitial=new BigDecimal(0);
	BigDecimal quantiteTotalInitial=new BigDecimal(0);
	BigDecimal PrixInitial=new BigDecimal(0);
	BigDecimal montantachat=new BigDecimal(0);
	BigDecimal quantiteTotalachat=new BigDecimal(0);
	BigDecimal montantavoir=new BigDecimal(0);
	BigDecimal quantiteTotalavoir=new BigDecimal(0);
	BigDecimal quantiteTotalsortie=new BigDecimal(0);
	BigDecimal montantTotalsortie=new BigDecimal(0);
	BigDecimal quantiteTotalFinale=new BigDecimal(0);
	BigDecimal montantFinale=new BigDecimal(0);
	BigDecimal montantchargesupp=new BigDecimal(0);
	BigDecimal quantiteTotalchargesupp=new BigDecimal(0);
	BigDecimal montantService=new BigDecimal(0);
	BigDecimal quantiteTotalService=new BigDecimal(0);
	BigDecimal montantVente=new BigDecimal(0);
	BigDecimal quantiteTotalVente=new BigDecimal(0);
	BigDecimal montantDechet=new BigDecimal(0);
	BigDecimal quantiteTotalDechet=new BigDecimal(0);
	BigDecimal montantDechetService=new BigDecimal(0);
	BigDecimal quantiteTotalDechetService=new BigDecimal(0);
	BigDecimal montantTransferMPPF=new BigDecimal(0);
	BigDecimal quantiteTotalTransferMPPF=new BigDecimal(0);
	BigDecimal quantiteDechetaSupprimer=new BigDecimal(0);
	BigDecimal quantiteOffreaSupprimer=new BigDecimal(0);
	BigDecimal quantiteDechetaSupprimerService=new BigDecimal(0);
	BigDecimal quantiteOffreaSupprimerService=new BigDecimal(0);
	BigDecimal montantOffreService=new BigDecimal(0);
	BigDecimal quantiteTotalOffreService=new BigDecimal(0);
	BigDecimal montantOffreProduction=new BigDecimal(0);
	BigDecimal quantiteTotalOffreProduction=new BigDecimal(0);
	
	String dateyearproduction=sdfyear.format(dateProduction.getDate());
	
	Date datedebut=null;
	try {
		datedebut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/"+dateyearproduction);
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	boolean trouve=false;
	MatierePremier mp=null;
	Magasin magasin=mapMagasin.get(combomagasinMP.getSelectedItem());
	
	if(magasin!=null)
	{
 		
		
		
		
		
		
		listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_INITIAL,magasin);
		listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
		
		listDetailTransferStockMPAchatGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
		listDetailTransferStockMPSortie=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
		listDetailTransferStockMPSortieGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
		
		listDetailTransferStockMPAvoir=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
		listDetailTransferStockMPAvoirGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
		
		listDetailTransferStockMPChargeSupp=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
		listDetailTransferStockMPChargeSuppGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
		
		listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutService(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
		listDetailTransferStockMPServiceGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
		

		listDetailTransferStockMPVente=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
		listDetailTransferStockMPVenteGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
		
		
		
		listDetailTransferStockMPTransferMPPF=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin);
		listDetailTransferStockMPTransferMPPFGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin);
		
		
		listDetailTransferStockMPTransferMPPFProduction=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
		listDetailTransferStockMPTransferMPPFProductionGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
		
		listDetailTransferStockMPTransferMPPFService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
		listDetailTransferStockMPTransferMPPFGroupebyMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(datedebut, dateProduction.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
		
		
		
		listDetailTransferStockMPAllMP=	detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);
		
		for(int p=0;p<listDetailTransferStockMPAllMP.size();p++)
		{
			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPAllMP.get(p);
			EtatStockMP etatstock=new EtatStockMP();
			etatstock.setMp(detailtransferstockmp.getMatierePremier());
			etatstock.setQuantiteInitial(BigDecimal.ZERO);
			etatstock.setPrixInitial(BigDecimal.ZERO);
			etatstock.setMontantInitial(BigDecimal.ZERO);
			etatstock.setQuantiteAchat(BigDecimal.ZERO);
			etatstock.setPrixAchat(BigDecimal.ZERO);
			etatstock.setMontantAchat(BigDecimal.ZERO);
			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			etatstock.setPrixSortie(BigDecimal.ZERO);
			etatstock.setMontantSortie(BigDecimal.ZERO);
			etatstock.setQuantiteService(BigDecimal.ZERO);
			etatstock.setPrixService(BigDecimal.ZERO);
			etatstock.setMontantService(BigDecimal.ZERO);
			etatstock.setQuantiteDechetService(BigDecimal.ZERO);
			etatstock.setPrixDechetService(BigDecimal.ZERO);
			etatstock.setMontantDechetService(BigDecimal.ZERO);
			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
			etatstock.setPrixAvoir(BigDecimal.ZERO);
			etatstock.setMontantAvoir(BigDecimal.ZERO);
			etatstock.setMarge(BigDecimal.ZERO);
			etatstock.setQuantiteDechet(BigDecimal.ZERO);
			etatstock.setPrixDechet(BigDecimal.ZERO);
			etatstock.setMontantDechet(BigDecimal.ZERO);
			etatstock.setQuantiteOffreService(BigDecimal.ZERO);
			etatstock.setPrixOffreService(BigDecimal.ZERO);
			etatstock.setMontantOffreService(BigDecimal.ZERO);
			etatstock.setQuantiteOffreProduction(BigDecimal.ZERO);
			etatstock.setPrixOffreProduction(BigDecimal.ZERO);
			etatstock.setMontantOffreProduction(BigDecimal.ZERO);
			
			listEtatStockMP.add(etatstock);
			
		}
		
		// charger list Etat stock MP initialiser les enregistrement des achats et ventes par zero
		for(int i=0;i<listDetailTransferStockMPInitial.size();i++)
		{
			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPInitial.get(i);
			
			PrixInitial=detailtransferstockmp.getPrixUnitaire();
			quantiteTotalInitial=detailtransferstockmp.getQuantite();
			montantInitial=quantiteTotalInitial.multiply(PrixInitial);
			
			/*
			EtatStockMP etatstock=new EtatStockMP();
			etatstock.setMp(detailtransferstockmp.getMatierePremier());
			etatstock.setQuantiteInitial(detailtransferstockmp.getQuantite());
			etatstock.setPrixInitial(detailtransferstockmp.getPrixUnitaire());
			etatstock.setMontantInitial(detailtransferstockmp.getQuantite().multiply(detailtransferstockmp.getPrixUnitaire()));
			etatstock.setQuantiteAchat(BigDecimal.ZERO);
			etatstock.setPrixAchat(BigDecimal.ZERO);
			etatstock.setMontantAchat(BigDecimal.ZERO);
			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			etatstock.setPrixSortie(BigDecimal.ZERO);
			etatstock.setMontantSortie(BigDecimal.ZERO);
			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
			etatstock.setPrixAvoir(BigDecimal.ZERO);
			etatstock.setMontantAvoir(BigDecimal.ZERO);
			listEtatStockMP.add(etatstock);
		*/
			
			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
    		{
    			
    			for(int p=0;p<listEtatStockMP.size();p++)
    	    	{
    				if(listEtatStockMP.get(p).getMp().equals(listDetailTransferStockMPInitial.get(i).getMatierePremier()))
	    			{
    					EtatStockMP etatstockmp=listEtatStockMP.get(p);
    					if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
    						etatstockmp.setQuantiteInitial(quantiteTotalInitial.setScale(0, RoundingMode.CEILING));
		    				etatstockmp.setPrixInitial(PrixInitial);
		    				etatstockmp.setMontantInitial(quantiteTotalInitial.setScale(0, RoundingMode.CEILING).multiply(PrixInitial));
		    				listEtatStockMP.set(p, etatstockmp);
    					}else
    					{
    						etatstockmp.setQuantiteInitial(quantiteTotalInitial);
		    				etatstockmp.setPrixInitial(PrixInitial);
		    				etatstockmp.setMontantInitial(quantiteTotalInitial.multiply(PrixInitial));
		    				listEtatStockMP.set(p, etatstockmp);
    					}
	    				
	    			
	    			}
    	    	}
    			
    		}
		}
		
		
		// calculer le prix moyen et quantite achat
		
		
	for(int j=0;j<listDetailTransferStockMPAchatGroupebyMP.size();j++)
	{
		montantachat=new BigDecimal(0);
		quantiteTotalachat=new BigDecimal(0);
		
	for(int k=0;k<listDetailTransferStockMPAchat.size();k++)
	{
		
		if(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPAchat.get(k).getMatierePremier()))
		{
			
			if(listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
    			quantiteTotalachat=quantiteTotalachat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPAchat.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
			}else
			{
				montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
    			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockMPAchat.get(k).getQuantite());
			}

			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
		{
			/*	    			
			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
			*/
			
			for(int i=0;i<listEtatStockMP.size();i++)
	    	{
				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier()))
    			{
					EtatStockMP etatstockmp=listEtatStockMP.get(i);
					
					if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
						etatstockmp.setQuantiteAchat(quantiteTotalachat.setScale(0, RoundingMode.CEILING));
	    				etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
	    				etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
	    				listEtatStockMP.set(i, etatstockmp);
						
						
					}else
					{
						
	    				etatstockmp.setQuantiteAchat(quantiteTotalachat);
	    				etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
	    				etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
	    				listEtatStockMP.set(i, etatstockmp);
					}
					
    				
    				
    			}
	    	}
			
			
		}
		
	}
		
	// charger les new value de stock achat dans la liste Etat Stock MP	
	/*
	for(int i=0;i<listEtatStockMP.size();i++)
	{
		for(int j=0;j<listDetailTransferStockMPAchatGroupebyMP.size();j++)
		{
			if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier()))
			{
				EtatStockMP etatstockmp=listEtatStockMP.get(i);
				etatstockmp.setQuantiteAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getQuantite());
				etatstockmp.setPrixAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getPrixUnitaire());
				etatstockmp.setMontantAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getQuantite().multiply(listDetailTransferStockMPAchatGroupebyMP.get(j).getPrixUnitaire()));
				listEtatStockMP.set(i, etatstockmp);
				
			}
			
			
		}
		
	}
	*/
	
	// calculer Quantite Sortie (charge)
	
	
	
	
	for(int i=0;i<listDetailTransferStockMPSortieGroupebyMP.size();i++)
	{
		
		quantiteTotalsortie=BigDecimal.ZERO;
		montantTotalsortie=BigDecimal.ZERO;
		quantiteTotalDechet=BigDecimal.ZERO;
		quantiteTotalOffreProduction=BigDecimal.ZERO;
		montantDechet=BigDecimal.ZERO;
		montantOffreProduction=BigDecimal.ZERO;
		
		for(int j=0;j<listDetailTransferStockMPSortie.size();j++)
		{
			
			if(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMPSortie.get(j).getMatierePremier()))
			{
				if(listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
					quantiteTotalsortie=quantiteTotalsortie.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
					montantTotalsortie=montantTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
				
					if(listDetailTransferStockMPSortie.get(j).getQuantiteDechet()!=null)
					{
    					quantiteTotalDechet=quantiteTotalDechet.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING));
    					montantDechet=montantDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
				
					}
					if(listDetailTransferStockMPSortie.get(j).getQuantiteOffre()!=null)
					{
						quantiteTotalOffreProduction=quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING));
						montantOffreProduction=montantOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
					}
					
				
				}else
				{
					quantiteTotalsortie=quantiteTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite());
					montantTotalsortie=montantTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
				
					
					if(listDetailTransferStockMPSortie.get(j).getQuantiteDechet()!=null)
					{
						quantiteTotalDechet=quantiteTotalDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet());
    					montantDechet=montantDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));

					}
					if(listDetailTransferStockMPSortie.get(j).getQuantiteOffre()!=null)
					{
						quantiteTotalOffreProduction=quantiteTotalOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre());
						montantOffreProduction=montantOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));

					}
					
				}
				
				
			}
			
			
		}
		
    	
		
		if(!quantiteTotalsortie.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantTotalsortie.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		{
			/*
			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
			detailtransferstockmp.setQuantite(quantiteTotalsortie);
			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
			*/
			
			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
	    	
	    	for(int k=0;k<listEtatStockMP.size();k++)
	    	{
	    
	    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
	    		{
	    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
	    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
		    			etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTotalsortie)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalsortie.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

	    				etatstockmp.setQuantiteSortie(quantiteTotalsortie.setScale(0, RoundingMode.CEILING));
		    			etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    		
		    			
					}else
					{
						
		    			etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTotalsortie)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalsortie),6,RoundingMode.HALF_UP));

						etatstockmp.setQuantiteSortie(quantiteTotalsortie);
		    			etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));	
		    		
					}
	    			
	    			
	    			listEtatStockMP.set(k, etatstockmp);
	    		}
	    		
	    	}
			
		}
		
		
		
		/// inserer la quantite dechet , prix dechet et montant dechet
		
		if(!quantiteTotalDechet.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantDechet.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		{
			/*
			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
			detailtransferstockmp.setQuantite(quantiteTotalsortie);
			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
			*/
			
			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
	    	
	    	for(int k=0;k<listEtatStockMP.size();k++)
	    	{
	    
	    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
	    		{
	    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
	    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
	    				etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().add(quantiteTotalDechet.setScale(0, RoundingMode.CEILING)) );
		    			etatstockmp.setPrixDechet(montantDechet.divide(quantiteTotalDechet.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    			etatstockmp.setMontantDechet (etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));
		    			
		    			
					}else
					{
						etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().add(quantiteTotalDechet));
		    			etatstockmp.setPrixDechet(montantDechet.divide(quantiteTotalDechet,6,RoundingMode.HALF_UP));
		    			etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));					    			
					}
	    			
	    			
	    			listEtatStockMP.set(k, etatstockmp);
	    		}
	    		
	    		
	    	}
			
			
		}
		
		
		
	/// inserer la quantite Offre , prix Offre et montant Offre (production)
		
		if(!quantiteTotalOffreProduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantOffreProduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		{
			/*
			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
			detailtransferstockmp.setQuantite(quantiteTotalsortie);
			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
			*/
			
			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
	    	
	    	for(int k=0;k<listEtatStockMP.size();k++)
	    	{
	    
	    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
	    		{
	    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
	    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
	    				etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().add(quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING)));
		    			etatstockmp.setPrixOffreProduction(montantOffreProduction.divide(quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    			etatstockmp.setMontantOffreProduction (etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));
		    			
					}else
					{
						etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().add(quantiteTotalOffreProduction));
		    			etatstockmp.setPrixOffreProduction(montantOffreProduction.divide(quantiteTotalOffreProduction,6,RoundingMode.HALF_UP));
		    			etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));					    			
					}
	    			
	    			listEtatStockMP.set(k, etatstockmp);
	    		}
	    		
	    	}
			
			
		}
		
		
		
	}
	


	
	// calculer le prix moyen et quantite Avoir
	
	
	for(int j=0;j<listDetailTransferStockMPAvoirGroupebyMP.size();j++)
	{
		montantavoir=new BigDecimal(0);
		quantiteTotalavoir=new BigDecimal(0);
		
	for(int k=0;k<listDetailTransferStockMPAvoir.size();k++)
	{
		
		if(listDetailTransferStockMPAvoirGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPAvoir.get(k).getMatierePremier()))
		{
			if(listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				montantavoir=montantavoir.add(listDetailTransferStockMPAvoir.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoir.get(k).getQuantite()));
    			quantiteTotalavoir=quantiteTotalavoir.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPAvoir.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
			}else
			{
				montantavoir=montantavoir.add(listDetailTransferStockMPAvoir.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoir.get(k).getQuantite()));
    			quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockMPAvoir.get(k).getQuantite());
			}
		
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if(!montantavoir.equals(BigDecimal.ZERO) && !quantiteTotalavoir.equals(BigDecimal.ZERO))
		{
			/*	    			
			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
			*/
			
			for(int i=0;i<listEtatStockMP.size();i++)
	    	{
				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAvoirGroupebyMP.get(j).getMatierePremier()))
    			{
    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
    					etatstockmp.setQuantiteAvoir(quantiteTotalavoir.setScale(0, RoundingMode.CEILING));
	    				etatstockmp.setPrixAvoir(montantavoir.divide(quantiteTotalavoir.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
	    				etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
	    				listEtatStockMP.set(i, etatstockmp);
					}else
					{
						etatstockmp.setQuantiteAvoir(quantiteTotalavoir);
	    				etatstockmp.setPrixAvoir(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.HALF_UP));
	    				etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
	    				listEtatStockMP.set(i, etatstockmp);
					}
	    			
    				
    			}
	    	}
				
		}
		
	}
	
	
// calculer le prix moyen et quantite ChargeSupp
	
	
	for(int j=0;j<listDetailTransferStockMPChargeSuppGroupebyMP.size();j++)
	{
		montantchargesupp=new BigDecimal(0);
		quantiteTotalchargesupp=new BigDecimal(0);
		
	for(int k=0;k<listDetailTransferStockMPChargeSupp.size();k++)
	{
		
		if(listDetailTransferStockMPChargeSuppGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPChargeSupp.get(k).getMatierePremier()))
		{
			if(listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				montantchargesupp=montantchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargeSupp.get(k).getQuantite()));
    			quantiteTotalchargesupp=quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPChargeSupp.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
			}else
			{
				montantchargesupp=montantchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargeSupp.get(k).getQuantite()));
    			quantiteTotalchargesupp=quantiteTotalchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getQuantite());
			}
			
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if(!montantchargesupp.equals(BigDecimal.ZERO) && !quantiteTotalchargesupp.equals(BigDecimal.ZERO))
		{
			/*	    			
			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
			*/
			
			for(int i=0;i<listEtatStockMP.size();i++)
	    	{
				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPChargeSuppGroupebyMP.get(j).getMatierePremier()))
    			{
    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
	    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantchargesupp)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

    					etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING)));
	    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
	    				listEtatStockMP.set(i, etatstockmp);
    					
					}else
					{
	    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantchargesupp)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalchargesupp),6,RoundingMode.HALF_UP));

						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalchargesupp));
	    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
	    				listEtatStockMP.set(i, etatstockmp);
					}
    				
    				
    			}
	    	}
				
		}
		
	}
	
	
	
	
//calculer le prix moyen et quantite Service
	
	
	for(int j=0;j<listDetailTransferStockMPServiceGroupebyMP.size();j++)
	{
		montantService=new BigDecimal(0);
		quantiteTotalService=new BigDecimal(0);
		montantDechetService=new BigDecimal(0);
		quantiteTotalDechetService=new BigDecimal(0);
		montantOffreService=new BigDecimal(0);
		quantiteTotalOffreService=new BigDecimal(0);
		
	for(int k=0;k<listDetailTransferStockMPService.size();k++)
	{
		
		if(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPService.get(k).getMatierePremier()))
		{
			if(listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantite()));
    			quantiteTotalService=quantiteTotalService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
    			if(listDetailTransferStockMPService.get(k).getQuantiteDechet()!=null)
    			{
    				montantDechetService=montantDechetService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteDechet()));
	    			quantiteTotalDechetService=quantiteTotalDechetService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantiteDechet().setScale(0, RoundingMode.CEILING));
	    			
    			}
    		
    			
    			if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
    			{
    				montantOffreService=montantOffreService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteOffre()));
	    			quantiteTotalOffreService=quantiteTotalOffreService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantiteOffre().setScale(0, RoundingMode.CEILING));
    			}
    			
				
			}else
			{
				montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantite()));
    			quantiteTotalService=quantiteTotalService.add(listDetailTransferStockMPService.get(k).getQuantite());
    			if(listDetailTransferStockMPService.get(k).getQuantiteDechet()!=null)
    			{
    				montantDechetService=montantDechetService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteDechet()));
	    			quantiteTotalDechetService=quantiteTotalDechetService.add(listDetailTransferStockMPService.get(k).getQuantiteDechet());
	    			
    			}
    		
    			if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
    			{
    				montantOffreService=montantOffreService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteOffre()));
	    			quantiteTotalOffreService=quantiteTotalOffreService.add(listDetailTransferStockMPService.get(k).getQuantiteOffre());	
    				
    			}
    			
    			
    			
			}
			
		
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
	
	
		if(!montantService.equals(BigDecimal.ZERO) && !quantiteTotalService.equals(BigDecimal.ZERO))
		{
			/*	    			
			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
			*/
			
			for(int i=0;i<listEtatStockMP.size();i++)
	    	{
				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier()))
    			{
					
					
    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
    				
    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
    					etatstockmp.setQuantiteService (etatstockmp.getQuantiteService().setScale(0, RoundingMode.CEILING).add(quantiteTotalService.setScale(0, RoundingMode.CEILING)));
	    				etatstockmp.setPrixService(montantService.divide(quantiteTotalService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
	    				etatstockmp.setMontantService(etatstockmp.getQuantiteService().multiply(etatstockmp.getPrixService()));
	    				
	    				
	    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).add(quantiteTotalDechetService.setScale(0, RoundingMode.CEILING)));
	    				if((etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
	    				{
		    				etatstockmp.setPrixDechetService(new BigDecimal(0));

	    				}else
	    				{
		    				etatstockmp.setPrixDechetService(montantDechetService.divide(quantiteTotalDechetService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));

	    				}
	    				etatstockmp.setMontantDechetService (etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
	    				
	    				listEtatStockMP.set(i, etatstockmp);
					}else
					{
						etatstockmp.setQuantiteService(etatstockmp.getQuantiteService().add(quantiteTotalService));
	    				etatstockmp.setPrixService (montantService.divide(quantiteTotalService,6,RoundingMode.HALF_UP));
	    				etatstockmp.setMontantService(etatstockmp.getQuantiteService().multiply(etatstockmp.getPrixService()));
	    				
	    				
	    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService));
	    				if((etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
	    				{
		    				etatstockmp.setPrixDechetService(new BigDecimal(0));

	    				}else
	    				{
		    				etatstockmp.setPrixDechetService(montantDechetService.divide(quantiteTotalDechetService,6,RoundingMode.HALF_UP));

	    				}	
	    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
	    				
	    				listEtatStockMP.set(i, etatstockmp);
					}
    				
    				
    			}
	    	}
				
		}
		// Ajouter la Quantite , prix et monatant Offre service
		if(!quantiteTotalOffreService.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		{
			/*	    			
			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
			*/
			
			for(int i=0;i<listEtatStockMP.size();i++)
	    	{
				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier()))
    			{
					
					
    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
    				
    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
    					etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).add(quantiteTotalOffreService.setScale(0, RoundingMode.CEILING)));
	    				etatstockmp.setPrixOffreService(montantOffreService.divide(quantiteTotalOffreService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
	    				etatstockmp.setMontantOffreService (etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
	    				
	    				
	    				listEtatStockMP.set(i, etatstockmp);
					}else
					{
						etatstockmp.setQuantiteOffreService (etatstockmp.getQuantiteOffreService().add(quantiteTotalOffreService));
	    				etatstockmp.setPrixOffreService (montantOffreService.divide(quantiteTotalOffreService,6,RoundingMode.HALF_UP));
	    				etatstockmp.setMontantOffreService (etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
	    				
	    				listEtatStockMP.set(i, etatstockmp);
					}
    				
    				
    			}
	    	}
				
		}
		
		
	}
	
	
// calculer le prix moyen et quantite Vente
	
	
	
	for(int j=0;j<listDetailTransferStockMPVenteGroupebyMP.size();j++)
	{
		montantVente=new BigDecimal(0);
		quantiteTotalVente=new BigDecimal(0);
		
	for(int k=0;k<listDetailTransferStockMPVente.size();k++)
	{
		
		if(listDetailTransferStockMPVenteGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPVente.get(k).getMatierePremier()))
		{
			if(listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				montantVente=montantVente.add(listDetailTransferStockMPVente.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVente.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
    			quantiteTotalVente=quantiteTotalVente.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPVente.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
			}else
			{
				montantVente=montantVente.add(listDetailTransferStockMPVente.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVente.get(k).getQuantite()));
    			quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockMPVente.get(k).getQuantite());
			}
	
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
	}
		if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
		{
			/*	    			
			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
			*/
			
			for(int i=0;i<listEtatStockMP.size();i++)
	    	{
				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPVenteGroupebyMP.get(j).getMatierePremier()))
    			{
    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
	    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantVente)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalVente.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

    					etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalVente.setScale(0, RoundingMode.CEILING)) );
	    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
	    				listEtatStockMP.set(i, etatstockmp);
    					
					}else
					{
	    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantVente)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalVente),6,RoundingMode.HALF_UP));

						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalVente));
	    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
	    				listEtatStockMP.set(i, etatstockmp);	
					}
    				
    				
    			}
	    	}
			
			
			
		}
		
	}
	
	
	// calculer calculer le prix moyen et quantite TransferMPPFProduction
	
	
for(int j=0;j<listDetailTransferStockMPTransferMPPFProductionGroupebyMP.size();j++)
	{
		montantTransferMPPF=new BigDecimal(0);
		quantiteTotalTransferMPPF=new BigDecimal(0);
		quantiteOffreaSupprimer=new BigDecimal(0);
		quantiteDechetaSupprimer=new BigDecimal(0);
		
		
	for(int k=0;k<listDetailTransferStockMPTransferMPPFProduction.size();k++)
	{
		
		if(listDetailTransferStockMPTransferMPPFProductionGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPFProduction.get(k).getMatierePremier()))
		{
			if(listDetailTransferStockMPTransferMPPFProduction.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPFProduction.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPFProduction.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPFProduction.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPFProduction.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFProduction.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFProduction.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFProduction.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
    		
    			
    			// calculer la somme de quantite dechet et offre a supprimer
    			if(listDetailTransferStockMPTransferMPPFProduction.get(k).getStockSource()!=null)
    			{
    				if(listDetailTransferStockMPTransferMPPFProduction.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
    				{
    					quantiteDechetaSupprimer=quantiteDechetaSupprimer.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFProduction.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
    				}else if(listDetailTransferStockMPTransferMPPFProduction.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
    				{
    					quantiteOffreaSupprimer=quantiteOffreaSupprimer.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFProduction.get(k).getQuantite().setScale(0, RoundingMode.CEILING))	;
    				}
    				
    			}
    			
    			////////////////////////////////////////////////////////////////////////////////// 
    			
    			
			}else
			{
				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFProduction.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFProduction.get(k).getQuantite()));
    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPFProduction.get(k).getQuantite());
    			
    			// calculer la somme de quantite dechet et offre a supprimer
    			
    			if(listDetailTransferStockMPTransferMPPFProduction.get(k).getStockSource()!=null)
    			{
    				
    				if(listDetailTransferStockMPTransferMPPFProduction.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
    				{
    					quantiteDechetaSupprimer=quantiteDechetaSupprimer.add(listDetailTransferStockMPTransferMPPFProduction.get(k).getQuantite());
    				}else if(listDetailTransferStockMPTransferMPPFProduction.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
    				{
    					quantiteOffreaSupprimer=quantiteOffreaSupprimer.add(listDetailTransferStockMPTransferMPPFProduction.get(k).getQuantite())	;
    				}
    				
    			}
    			
                //////////////////////////////////////////////////////////////////////////////////
    			
			}
	
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
	
	
	
		if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		{
				    			
			/*listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setQuantite(quantiteTotalTransferMPPF);
			listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setPrixUnitaire(montantTransferMPPF.divide(quantiteTotalTransferMPPF,6,RoundingMode.HALF_UP));*/
			
			
			for(int i=0;i<listEtatStockMP.size();i++)
	    	{
				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFProductionGroupebyMP.get(j).getMatierePremier()))
    			{
    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
    					
	    				    etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

							etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				// supprimer la quantite dechet et quantite offre
		    				etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().setScale(0, RoundingMode.CEILING).subtract(quantiteDechetaSupprimer.setScale(0, RoundingMode.CEILING)));
		    				
		    			/*	if(etatstockmp.getQuantiteDechet().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
		    				{
		    					etatstockmp.setPrixDechet(BigDecimal.ZERO);
		    				}*/
		    				etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));
		    				
		    				etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING).subtract(quantiteOffreaSupprimer.setScale(0, RoundingMode.CEILING)));
		    			/*	if(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
		    				{
		    					etatstockmp.setPrixOffreProduction(BigDecimal.ZERO);	
		    				}*/
		    				etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));
					
		    				
	    				listEtatStockMP.set(i, etatstockmp);
    					
					}else
					{
					     etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));

						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF));
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				// supprimer la quantite dechet et quantite offre
		    				etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().subtract(quantiteDechetaSupprimer));
		    				/*if(etatstockmp.getQuantiteDechet().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
		    				{
		    					etatstockmp.setPrixDechet(BigDecimal.ZERO);
		    				}*/
		    				etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));
		    				
		    				etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().setScale(6, RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimer.setScale(6, RoundingMode.HALF_UP)));
		    				/*if(etatstockmp.getQuantiteOffreProduction().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
		    				{
		    					etatstockmp.setPrixOffreProduction(BigDecimal.ZERO);	
		    				}*/
		    				etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));
						
	    				
	    				listEtatStockMP.set(i, etatstockmp);	
					}
    				
    				
    			}
	    	}
			
		}
		
	}



// calculer calculer le prix moyen et quantite TransferMPPF


for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMP.size();j++)
{
	montantTransferMPPF=new BigDecimal(0);
	quantiteTotalTransferMPPF=new BigDecimal(0);
	
	
	
	
for(int k=0;k<listDetailTransferStockMPTransferMPPF.size();k++)
{
	
	if(listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier()))
	{
		if(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		{
			montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
	
			
			////////////////////////////////////////////////////////////////////////////////// 
			
			
		}else
		{
			montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPF.get(k).getQuantite()));
			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite());
			
			
			
		}

		//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		
	}
	
	
}



	if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
	{
			    			
		/*listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setQuantite(quantiteTotalTransferMPPF);
		listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setPrixUnitaire(montantTransferMPPF.divide(quantiteTotalTransferMPPF,6,RoundingMode.HALF_UP));*/
		
		
		for(int i=0;i<listEtatStockMP.size();i++)
    	{
			if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier()))
			{
				EtatStockMP etatstockmp=listEtatStockMP.get(i);
				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
					
    				    etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
	    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
	    			
	    			
				
	    				
    				listEtatStockMP.set(i, etatstockmp);
					
				}else
				{
				     etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));

					etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF));
	    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
	    				
    				
    				listEtatStockMP.set(i, etatstockmp);	
				}
				
				
			}
    	}
		
	}
	
}

	
	



	
	// calculer calculer le prix moyen et quantite TransferMPPFSERVICE
	
	
	for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMPService.size();j++)
	{
		montantTransferMPPF=new BigDecimal(0);
		quantiteTotalTransferMPPF=new BigDecimal(0);
		quantiteOffreaSupprimerService=new BigDecimal(0);
		quantiteDechetaSupprimerService=new BigDecimal(0);
		
		
	for(int k=0;k<listDetailTransferStockMPTransferMPPFService.size();k++)
	{
		
		if(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier()))
		{
			if(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
    			
    			
    			// calculer la somme de quantite dechet et offre a supprimer
    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
    			{
    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
    				{
    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
    				{
    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING))	;
    				}
    				
    			}
    			////////////////////////////////////////////////////////////////////////////////// 
    			
    			
			}else
			{
				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite()));
    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
    			
    			// calculer la somme de quantite dechet et offre a supprimer
    			
    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
    			{
    				
    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
    				{
    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
    				{
    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite())	;
    				}
    				
    			}
    			
                //////////////////////////////////////////////////////////////////////////////////
    			
			}
	
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		{
			/*	    			
			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
			*/
			
			for(int i=0;i<listEtatStockMP.size();i++)
	    	{
				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier()))
    			{
    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
    					
	    				    etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

							etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				// supprimer la quantite dechet et quantite offre
		    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).subtract(quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING)));
		    				if(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
		    				{
		    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
		    				}
		    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
		    				
		    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).subtract(quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING)));
		    				if(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
		    				{
		    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
		    				}
		    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
						
    					
    				
	    				
	    				listEtatStockMP.set(i, etatstockmp);
    					
					}else
					{
						
							
						
	    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));

						   etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF));
					
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				// supprimer la quantite dechet et quantite offre
		    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().subtract(quantiteDechetaSupprimerService));
		    				if(etatstockmp.getQuantiteDechetService().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
		    				{
		    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
		    				}
		    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
		    				
		    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(6, RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimerService.setScale(6, RoundingMode.HALF_UP)));
		    				if(etatstockmp.getQuantiteOffreService().setScale(2, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)))
		    				{
		    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
		    				}
		    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
					
	    				listEtatStockMP.set(i, etatstockmp);	
					}
    				
    				
    			}
	    	}
			
		}
		
	}
	
	
	// Calculer le Prix Moyen a partir de mouvement de stock 
	
	CalculerPrixMoyenFinaleParMouvementStock();
	

	// calculer le stock finale et le prix moyen finale
	
	
 	for(int i=0;i<listEtatStockMP.size();i++)
	{
		
 		quantiteTotalFinale=BigDecimal.ZERO;
   		montantFinale=BigDecimal.ZERO;
	    BigDecimal prixMoyen=BigDecimal.ZERO;
			EtatStockMP etatstockmp=listEtatStockMP.get(i);
			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			{
				etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING))).subtract(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteAvoir().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING)))));
    			quantiteTotalFinale=(quantiteTotalFinale.setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING))));
    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP))));
    			
    			if(listMouvementStockMPAfficherMouvementTmp.size()!=0)
	    		{
	    			for(int l=0;l<listMouvementStockMPAfficherMouvementTmp.size();l++)
	    			{
	    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom()))
	    				{
	    					if(listMouvementStockMPAfficherMouvementTmp.get(l).getDateCreation().compareTo(dateProduction.getDate())<=0 )
	    					{
	    						prixMoyen=listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb();
		    					System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : prixMoyen : "+prixMoyen ) ;

	    					}
	    									    					
	    				}
	    				
	    			}
	    			
	    		}else
	    		{
	    			for(int l=0;l<listMouvementStockMPAfficher.size();l++)
	    			{
	    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom()))
	    				{
	    					if(listMouvementStockMPAfficher.get(l).getDateCreation().compareTo(dateProduction.getDate())<=0 )
	    					{
	    						prixMoyen=listMouvementStockMPAfficher.get(l).getPrixFinaldb();
		    					System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" :  prixMoyen :"+prixMoyen) ;
		    					
	    					}
	    					
	    				}
	    			}
	    			
	    		}
    			
    			StockMP stockmp=stockMPDAO.findStockByMagasinMP(etatstockmp.getMp().getId(),magasin.getId());
				/*
				 * if(etatstockmp.getQuantiteAchat().setScale(0,
				 * RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0,
				 * RoundingMode.CEILING)).compareTo(BigDecimal.ZERO)>0) {
				 * prixMoyen=(etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())
				 * ).divide(etatstockmp.getQuantiteAchat().setScale(0,
				 * RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0,
				 * RoundingMode.CEILING)), 6, RoundingMode.HALF_UP);
				 * 
				 * }
				 */
    			//prixMoyen=stockmp.getPrixUnitaire();
    			etatstockmp.setPrixFinale(prixMoyen);
	    		etatstockmp.setMontantFinale(((etatstockmp.getQuantiteFinale().setScale(0, RoundingMode.CEILING))).multiply(etatstockmp.getPrixFinale().setScale(6,RoundingMode.HALF_UP)));
    			
    			if(etatstockmp.getPrixSortie().setScale(6, RoundingMode.HALF_UP)!=BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
    			{
    				etatstockmp.setMarge((etatstockmp.getPrixSortie().subtract(etatstockmp.getPrixInitial())).divide(etatstockmp.getPrixSortie(), RoundingMode.HALF_UP));
    			}else
    			{
    				etatstockmp.setMarge(BigDecimal.ZERO);
    			}
			}else
			{
				etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat())).subtract(etatstockmp.getQuantiteSortie().add(etatstockmp.getQuantiteService()).add(etatstockmp.getQuantiteAvoir().add(etatstockmp.getQuantiteOffreService()).add(etatstockmp.getQuantiteOffreProduction()))));
    			quantiteTotalFinale=(quantiteTotalFinale.add(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat())));
    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP))));
    			
    			if(listMouvementStockMPAfficherMouvementTmp.size()!=0)
	    		{
	    			for(int l=0;l<listMouvementStockMPAfficherMouvementTmp.size();l++)
	    			{
	    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom()))
	    				{
	    					if(listMouvementStockMPAfficherMouvementTmp.get(l).getDateCreation().compareTo(dateProduction.getDate())<=0 )
	    					{
	    						prixMoyen=listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb();
		    					System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" :  prixMoyen : "+prixMoyen) ;
		    					
	    					}
	    					
	    				}
	    				
	    			}
	    			
	    		}else
	    		{
	    			for(int l=0;l<listMouvementStockMPAfficher.size();l++)
	    			{
	    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom()))
	    				{
	    					if(listMouvementStockMPAfficher.get(l).getDateCreation().compareTo(dateProduction.getDate())<=0 )
	    					{
	    						prixMoyen=listMouvementStockMPAfficher.get(l).getPrixFinaldb();
		    					System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" :  prixMoyen : "+prixMoyen ) ;
		    				
	    					}
	    					
	    				}
	    			}
	    			
	    		}
    			
    			StockMP stockmp=stockMPDAO.findStockByMagasinMP(etatstockmp.getMp().getId(),magasin.getId());
    			
				/*
				 * if(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteInitial()).
				 * compareTo(BigDecimal.ZERO)>0) {
				 * prixMoyen=(etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())
				 * ).divide(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteInitial())
				 * , 6, RoundingMode.HALF_UP);
				 * 
				 * }
				 */
    			
    			   // prixMoyen=stockmp.getPrixUnitaire();
    				etatstockmp.setPrixFinale(prixMoyen);
	    			etatstockmp.setMontantFinale((etatstockmp.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)).multiply(etatstockmp.getPrixFinale().setScale(6,RoundingMode.HALF_UP)));
    			
    			if(!etatstockmp.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
    			{
    				etatstockmp.setMarge((etatstockmp.getPrixSortie().subtract(etatstockmp.getPrixInitial())).divide(etatstockmp.getPrixSortie(), RoundingMode.HALF_UP));
    			}else
    			{
    				etatstockmp.setMarge(BigDecimal.ZERO);
    			}
			}
			
			listEtatStockMP.set(i, etatstockmp);
			
	}
	
	
	
	}
	else
	{
		JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}
	


	
	
	
	
}


public void CalculerPrixMoyenFinaleParMouvementStock()
{
	
	

	   
	  

	  
	  

	   // detailTransferStockMPDAO.ViderSession();
		listDetailTransferStockMP.clear();
		listDetailTransferStockMPGroupebyMP.clear();
		listDetailTransferStockMPBytypetransfer.clear();
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfyear=new SimpleDateFormat("yyyy");
		listMouvementStockMP.clear();
		boolean trouve=false;
		MatierePremier mp=null;
		Magasin magasin=mapMagasin.get(combomagasinMP.getSelectedItem());
		
		if(dateProduction.getDate()==null)
		{
			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
				return;
		}else
		{
			if(magasin!=null)
	    		{
	    		String dateyearproduction=sdfyear.format(dateProduction.getDate());
	    		Date datedebut=null;
	    		try {
	    			datedebut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/"+dateyearproduction);
	    			
	    			
	    		} catch (ParseException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
		    		
		    		listDetailTransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPOrderByDateTransfer(magasin);
		    		listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByDateTransferByMP(magasin);
		    		listDetailTransferStockMPBytypetransfer=detailTransferStockMPDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
		    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.ETAT_TRANSFER_STOCK_CHARGE,ETAT_TRANSFER_STOCK_CHARGE_SUPP,ETAT_TRANSFER_STOCK_VENTE,ETAT_TRANSFER_STOCK_AVOIR,ETAT_TRANSFER_STOCK_SERVICE,ETAT_TRANSFER_STOCK_SORTIE_PF};
		    		BigDecimal achat=BigDecimal.ZERO;
		    		BigDecimal prixAchat=BigDecimal.ZERO;
		    		BigDecimal initial=BigDecimal.ZERO;
		    		BigDecimal prixInitial=BigDecimal.ZERO;
		    		BigDecimal production=BigDecimal.ZERO;
		    		BigDecimal avoir=BigDecimal.ZERO;
		    		
		    		BigDecimal stockfinal=BigDecimal.ZERO;
		    		BigDecimal vente=BigDecimal.ZERO;
		    		BigDecimal service=BigDecimal.ZERO;
		    		BigDecimal dechet=BigDecimal.ZERO;
		    		BigDecimal dechetService=BigDecimal.ZERO;
		    		BigDecimal offreService=BigDecimal.ZERO;
		    		BigDecimal offreProduction=BigDecimal.ZERO;
		    		BigDecimal transfert=BigDecimal.ZERO;
		    		BigDecimal prixmoyenne=BigDecimal.ZERO;
		    		BigDecimal quantitefinale=BigDecimal.ZERO;
		    		for(int i=0;i<listDetailTransferStockMPGroupebyMP.size();i++)
		    		{
		    			achat=new BigDecimal(0);
		    			prixAchat=new BigDecimal(0);
		    			initial=new BigDecimal(0);
		    			production=new BigDecimal(0);
		    			dechet=new BigDecimal(0);
		    			dechetService=new BigDecimal(0);
		    			service=new BigDecimal(0);
		    			vente=new BigDecimal(0);
		    			avoir=BigDecimal.ZERO;
		    			stockfinal=new BigDecimal(0);
		    			offreService=new BigDecimal(0);
		    			offreProduction=new BigDecimal(0);
		    			transfert=new BigDecimal(0);
		    			
		    			if(i!=0)
		    			{
		    				if(!listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i-1).getMatierePremier()))
		    				{
		    					prixmoyenne=new BigDecimal(0);
				    			quantitefinale=new BigDecimal(0);
		    					
		    				}
		    				
		    			}else
		    			{
		    				prixmoyenne=new BigDecimal(0);
			    			quantitefinale=new BigDecimal(0);
		    			}
		    			
		    			
		    				
		    			for(int j=0;j<listDetailTransferStockMP.size();j++)
		    			{
		    				for(int k=0;k<typetransfer.length;k++)
			    			{
		    				
		    			if(listDetailTransferStockMPGroupebyMP.get(i).getTransferStockMP().getDateTransfer().equals(listDetailTransferStockMP.get(j).getTransferStockMP().getDateTransfer()) 
		    					&& listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMP.get(j).getMatierePremier()) && listDetailTransferStockMP.get(j).getTransferStockMP().getStatut().equals(typetransfer[k]))	
		    			{
		    				
		    				achat=new BigDecimal(0);
			    			prixAchat=new BigDecimal(0);
			    			initial=new BigDecimal(0);
			    			production=new BigDecimal(0);
			    			dechet=new BigDecimal(0);
			    			dechetService=new BigDecimal(0);
			    			service=new BigDecimal(0);
			    			vente=new BigDecimal(0);
			    			avoir=BigDecimal.ZERO;
			    			stockfinal=new BigDecimal(0);
			    			offreService=new BigDecimal(0);
			    			offreProduction=new BigDecimal(0);
			    			transfert=new BigDecimal(0);
		    				if(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getId()==94)
		    				{
		    					System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom());
		    				}
		    				
		    			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
		    			{
		    				
                  if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                      {
               	   if(!achat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
               	   {
                   	   prixAchat=(prixAchat.multiply(achat).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMP.get(j).getPrixUnitaire()))).divide(achat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING)), RoundingMode.HALF_UP);

               	   }else
               	   {
               		   prixAchat=BigDecimal.ZERO;
               	   }
               	   achat=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
               	   
                       }else
                       {
                       	if(!listDetailTransferStockMP.get(j).getQuantite().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
                       	{
	                        	   prixAchat=(prixAchat.multiply(achat).add(listDetailTransferStockMP.get(j).getQuantite().multiply(listDetailTransferStockMP.get(j).getPrixUnitaire()))).divide(achat.add(listDetailTransferStockMP.get(j).getQuantite()), RoundingMode.HALF_UP);

                       	}else
                       	{
	                        	   prixAchat=BigDecimal.ZERO;

                       	}
                       	

                       	achat=listDetailTransferStockMP.get(j).getQuantite();
                       }
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
		    			{
		    				   if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                          {
		    					    prixInitial=listDetailTransferStockMP.get(j).getPrixUnitaire();
		    						initial=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                          }else
                          {
                       	    prixInitial=listDetailTransferStockMP.get(j).getPrixUnitaire();
                       		initial=listDetailTransferStockMP.get(j).getQuantite(); 
                          }
		    			
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE) || typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE_SUPP))
		    			{
		    				 if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                          {
		    					 production=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
		    					 
		    					 if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE))
			    				 {
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
			    					{
			    						
			    						dechet=listDetailTransferStockMP.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
			    					{
			    						
			    						offreProduction=listDetailTransferStockMP.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    				 }
		    					 
		    					 
                          }else
                          {
                       	  // System.out.println("listDetailTransferStockMP.get(j).getQuantite() :"+listDetailTransferStockMP.get(j).getQuantite());
                       	   production=listDetailTransferStockMP.get(j).getQuantite(); 
                       	   if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE))
				    				 {
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
				    					{
				    						
				    						dechet=listDetailTransferStockMP.get(j).getQuantiteDechet();
				    					}
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
				    					{
				    						
				    						offreProduction=listDetailTransferStockMP.get(j).getQuantiteOffre();
				    					}
				    					 
				    				 }
                          }
		    				 
		    				
		    				 
		    			}
		    			
		    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                          {
		    					vente=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                          }else
                          {
                       	   vente=listDetailTransferStockMP.get(j).getQuantite();
                          }
		    					
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                          {
		    					avoir=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                          }else
                          {
                       	   avoir=listDetailTransferStockMP.get(j).getQuantite();
                          }
		    					
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SERVICE))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                          {
		    					service=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);	
		    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
			    					{
			    						
			    						dechetService=listDetailTransferStockMP.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
			    					{
			    						
			    						offreService=listDetailTransferStockMP.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING);
			    					}
			    					 
			    				
                          }else
                          {
                       	   service=listDetailTransferStockMP.get(j).getQuantite();	
                       	   
				    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
				    					{
				    						
				    						dechetService=listDetailTransferStockMP.get(j).getQuantiteDechet();
				    					}
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
				    					{
				    						
				    						offreService=listDetailTransferStockMP.get(j).getQuantiteOffre();
				    					}
				    					 
                          }
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_PF))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                          {
		    					transfert=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);	
		    					 
			    				
                          }else
                          {
                       	   transfert=listDetailTransferStockMP.get(j).getQuantite();	
                       	  	 
                          }
		    				
		    			}
		    			
		    			
		    			if(!quantitefinale.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    			{
			    			

		    				if(!quantitefinale.add(achat).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
			    			{
				    			//System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +quantitefinale +" * "+prixmoyenne +" / "+ "("+quantitefinale +achat+" )" );

		    					prixmoyenne=(achat.multiply(prixAchat).add(quantitefinale.multiply(prixmoyenne))).divide(quantitefinale.add(achat), RoundingMode.HALF_UP);

			    			}else
			    			{
			    				prixmoyenne=BigDecimal.ZERO;

			    			}
		    				
		    				
		    			//	System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

		    				quantitefinale=(quantitefinale.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
		    				
		    			}else
		    			{
		    				
		    				
		    				if(!initial.add(achat).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
			    			{
				    			//System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +initial +" * "+prixInitial +" / "+ "("+initial +" + "+achat+")" );

		    					prixmoyenne=(achat.multiply(prixAchat).add(initial.multiply(prixInitial))).divide(initial.add(achat), RoundingMode.HALF_UP);

			    			}else
			    			{
			    				prixmoyenne=BigDecimal.ZERO;

			    			}
		    				
		    				
		    				quantitefinale=(initial.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
			    			//System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

		    				
		    			}
		    			

		    				
		    			}
		    			
		    			
		    			
		    			
		    			}
		    			}
		    			
		    			
		    			if(listMouvementStockMP.size()!=0)
		    			{
		    				for(int d=0;d<listMouvementStockMP.size();d++)
		    				{
		    					
		    					if(listMouvementStockMP.get(d).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier()))
		    					{
		    						initial=listMouvementStockMP.get(d).getStockFinaldb();
		    						prixInitial=listMouvementStockMP.get(d).getPrixFinaldb();
		    						trouve=true;
		    						
		    					}
		    				}
		    				
		    				
		    			}
		    			if(trouve==false)
		    			{
		    				for(int l=0;l<listDetailTransferStockMPBytypetransfer.size();l++)
		    				{
		    	if(listDetailTransferStockMPBytypetransfer.get(l).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier()))
		    	{
		    		initial=listDetailTransferStockMPBytypetransfer.get(l).getQuantite();
		    		
		    	}
		    	
		    				}
		    				
		    			}
		    			
		    			//stockfinal=(initial.add(achat)).subtract(vente.add(production).add(avoir).add(service).add(dechet).add(dechetService));
		    			DetailMouvementStock mouvementstockMP=new DetailMouvementStock();
		    			mouvementstockMP.setDateCreation(listDetailTransferStockMPGroupebyMP.get(i).getTransferStockMP().getDateTransfer());
		    			mouvementstockMP.setProduction(production);
		    			mouvementstockMP.setDechet(dechet);
		    			mouvementstockMP.setOffreProduction(offreProduction);
		    			mouvementstockMP.setVente(vente);
		    			mouvementstockMP.setInitial(initial);
		    			mouvementstockMP.setPrixInitial(prixInitial);
		    			mouvementstockMP.setAchat(achat);
		    			mouvementstockMP.setPrixAchat(prixAchat);
		    			mouvementstockMP.setAvoir(avoir);
		    			mouvementstockMP.setService(service);
		    			mouvementstockMP.setDechetService(dechetService);
		    			mouvementstockMP.setOffreService(offreService);
		    			mouvementstockMP.setTransfert(transfert);
		    			mouvementstockMP.setMatierePremier(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier());
					/*
					 * if(!mouvementstockMP.getInitial().add(mouvementstockMP.getAchat()).setScale(
					 * 6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
					 * RoundingMode.HALF_UP))) {
					 * mouvementstockMP.setPrixFinaldb((mouvementstockMP.getAchat().multiply(
					 * mouvementstockMP.getPrixAchat()).add(mouvementstockMP.getInitial().multiply(
					 * mouvementstockMP.getPrixInitial()))).divide(mouvementstockMP.getInitial().add
					 * (mouvementstockMP.getAchat()), RoundingMode.HALF_UP));
					 * 
					 * }else { mouvementstockMP.setPrixFinaldb(BigDecimal.ZERO);
					 * 
					 * }
					 */
		    			mouvementstockMP.setPrixFinaldb(prixmoyenne);
		    			mouvementstockMP.setStockFinaldb((mouvementstockMP.getInitial().add(mouvementstockMP.getAchat())).subtract(mouvementstockMP.getVente().add(mouvementstockMP.getProduction().add(mouvementstockMP.getAvoir().add(mouvementstockMP.getService()).add(mouvementstockMP.getTransfert())))));
		    			listMouvementStockMP.add(mouvementstockMP);
		    			
		    		}
		    		
		    		// detailtransfer entre deux date et par article
		    	 if(datedebut!=null && dateProduction.getDate()!=null && mp==null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(datedebut);
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			if(listMouvementStockMP.get(i).getDateCreation().after(datedebut) ==true || ddebutTmp.equals(ddebut))
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
		    			
		    				
		    			}
		    			
		    		}
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateProduction.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateProduction.getDate())==true || dfinTmp.equals(dfin)  )
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMPAfficher.get(j));
		    			
		    				
		    			}
		    			
		    		}
		    		
		    	/*	if(listMouvementStockMPAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficher);
		    		}*/
		    		
		    		}
		    			
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
		}

	
		
		   
		   
		   
		   
		   
		   
	
	
	
	
}
	}






