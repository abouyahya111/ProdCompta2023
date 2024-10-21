package FacturePF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

 
import util.Constantes;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureAutresVenteDAOImpl;
import dao.daoImplManager.FactureAvoirClientPFDAOImpl;
import dao.daoImplManager.FactureAvoirPFDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FactureServiceTransportDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAutresVenteDAO;
import dao.daoManager.FactureAvoirClientPFDAO;
import dao.daoManager.FactureAvoirPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.*;
import java.awt.GridBagLayout;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class ImporterModifications extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;
	private ImageIcon imgImprimer;
	private ImageIcon imgValider;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	private JFrame mainFrame;
	private Utilisateur utilisateur;
	TransferStockMP transferStock = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp= new ArrayList<DetailTransferStockMP>();
	private	List<DetailTransferStockMP> listDetailTransferStockMPCharge= new ArrayList<DetailTransferStockMP>();
	private	List<DetailTransferProduitFini> listDetailTransferPF= new ArrayList<DetailTransferProduitFini>();
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransfertMPDAO ;
	
	private DepotDAO depotDAO;
	private ProductionDAO productionDAO;
	private StockMPDAO stockMPDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private List<CoutMP> listCoutMPTmpMPInsufisant =new ArrayList<CoutMP>();
	
	private Map< String, String> mapChargeSupp = new HashMap<>();
	
	private Map< Integer, MatierePremier> mapMatierePremiereDetailMouvement= new HashMap<>();
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private List<Journee> listJournee =new ArrayList<Journee>();
	private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	private JourneeDAO journeeDAO;
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	private List<SousFamilleEnVrac> listSousFamille = new ArrayList<SousFamilleEnVrac>();
	private SousFamilleArticlesPFDAO sousFamilleArticleDAo;
	private SousFamilleEnVracDAO sousFamilleEnvracDAo;
	 
	boolean QuantiteInsuffisant=false;
	private JTextField txtlien;
	
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell CellCodeClient;
    private static XSSFCell CellNumFacture;
    private static XSSFCell CellNumBL;
    private static XSSFCell CellNomClient;
    private static XSSFCell CellMagasinMPSTE;
    private static XSSFCell CellMagasinMPClient;
    private static XSSFCell CellMagasinPF;
    private static XSSFCell CellDateOF;
    private static XSSFCell CellDate;
    private static XSSFCell CellCodeOffre;
    private static XSSFCell CellService;
    private static XSSFCell CellScotch;
    private static XSSFCell CellMixte;
    private static XSSFCell CellEnVrac;
    private static XSSFCell CellHeure;
    private static XSSFCell CellCoutService;
    private static XSSFCell CellClient;
    private static XSSFCell CellModeReglement;
    private static XSSFCell CellNumModeReglement;
    private List<DetailEstimation> lisDetailEstimation = new ArrayList<DetailEstimation>();
    
    private ArticlesDAO articlesDAO; 
    private MatierePremiereDAO matierePremiereDAO;
    private PromotionDAO promotiondao = new PromotionDAOImpl();
    boolean creerOF = true;
    private DetailTransferMPDAO detailTransferStockMPDAO;
    private String nomMP;
    Magasin magasinProduction;
    private BigDecimal coutTotalMP = BigDecimal.ZERO;
    private ParametreDAO parametreDAO;
	BigDecimal coutPF = BigDecimal.ZERO;
	SousFamilleArticlePF sousfamilleEnvrac = null;
	SousFamilleArticlePF sousfamilleTmp = null;
	private StockPFDAO stockPFDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	private DetailTransferMPDAO detailtransferMPDAO;
	private List<DetailFactureServiceProduction> listdetailFactureServiceProduction =new ArrayList<DetailFactureServiceProduction>();
	 private ClientDAO clientdao;
	 private FactureServiceProductionDAO factureserviceproductionDAO;
		private DetailFactureServiceProductionDAO detailfactureserviceproductionDAO;
		private   SequenceurDAO sequenceurDAO;
    DetailEstimationDAO detailEstimationDAO;
    ClientPFDAO clientPFDAO;
    private JTextField txtlientclientfacture;
    private FacturePFDAO facturePFDAO;
    private DetailCompteClientDAO detailCompteClientDAO;
    private JTextField txtlientclientfactureaveccredit;
    private JTextField txtlienRemplacerNumFactureAvoirParNumFactureVente;
    private FactureAvoirClientPFDAO factureAvoirClientPFDAO;
    private JTextField txtlienModifierdateFactureAchatPF;
    FactureAchatDAO factureAchatDAO;
    FactureAvoirPFDAO factureAvoirPFDAO;
    private JTextField txtlienModifierdateFactureAvoir;
    private JTextField txtlienAjouterListeClientPF;
	private  CompteClientDAO compteClientdao;
	private JTextField txtlienAjouterNumFactureEtModeReglement;
	 JDateChooser datefacture = new JDateChooser();
		private FactureServiceTransportDAO factureServiceTransportdao;
		private FactureAutresVenteDAO factureAutresVentedao;
		
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ImporterModifications() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 950);
        try{
        	
        	mouvementStockGlobalDAO=new MouvementStockGlobalDAOImpl();
        	journeeDAO=new JourneeDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	compteurProductionDAO=new CompteurProductionDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	detailTransfertMPDAO=new DetailTransferMPDAOImpl();
        	articlesDAO=new ArticlesDAOImpl();
        	sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
        	magasinProduction=depotDAO.magasinByCode(Constantes.CODE_MAGASIN_PRODUCTION);
        	parametreDAO = new ParametreDAOImpl();
        	stockPFDAO = new StockPFDAOImpl();
        	clientdao=new ClientDAOImpl();
			detailtransferMPDAO = new DetailTransferMPDAOImpl();
        	transferStockPFDAO = new TransferStockPFDAOImpl();
			detailTransferProduitFiniDAO = new DetailTransferProduitFiniDAOImpl();
			factureserviceproductionDAO=new FactureServiceProductionDAOImpl();
         	detailfactureserviceproductionDAO=new DetailFactureServiceProductionDAOImpl();
         	sequenceurDAO=new SequenceurDAOImpl();
        	sousFamilleArticleDAo=new SousFamilleArticlesPFDAOImpl();
         	listSousFamille=sousFamilleEnvracDAo.findAll();
         	detailEstimationDAO=new DetailEstimationDAOImpl();
         	clientPFDAO=new ClientPFDAOImpl();
         	facturePFDAO=new FacturePFDAOImpl();
         	detailCompteClientDAO=new DetailCompteClientDAOImpl();
         	factureAvoirClientPFDAO=new FactureAvoirClientPFDAOImpl();
         	factureAchatDAO=new FactureAchatDAOImpl();
         	factureAvoirPFDAO=new FactureAvoirPFDAOImpl();
         	compteClientdao=new CompteClientDAOImpl();
         	factureServiceTransportdao=new FactureServiceTransportDAOImpl();
         	 factureAutresVentedao=new FactureAutresVenteDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	String codeDepot=AuthentificationView.utilisateur.getCodeDepot();
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
			

        
        txtlien = new JTextField();
        txtlien.setEditable(false);
        txtlien.setColumns(10);
        txtlien.setBounds(248, 52, 459, 36);
        add(txtlien);
        JFileChooser fileDialog = new JFileChooser();
        JButton button_1 = new JButton("Ouvrir");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
               txtlien.setText(file.getAbsolutePath());
           
        		
        	}
        });
        button_1.setBounds(717, 52, 89, 30);
        add(button_1);
	  		  
				  
        JButton button = new JButton("Lire Fichier excel");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Parametre PercentagePrixCadeau = parametreDAO.findByCode(Constantes.PERCENTAGE_PRIX_OFFRE);
        		
        		if(!txtlien.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlien.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  			
	  				       
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  	 				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		


   						
	  							
	  							


for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	coutTotalMP = BigDecimal.ZERO;
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellCodeClient=ExcelWSheet.getRow(i).getCell(0);
	CellNomClient=ExcelWSheet.getRow(i).getCell(3);
	 
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellCodeClient);
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	 ClientPF articleTPF=clientPFDAO.findClientByCodeClient(formattedCellStr);
	  
	 articleTPF.setNom(CellNomClient.getStringCellValue());

	 clientPFDAO.edit(articleTPF);

 

 }
 
	



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
        		 	
        	}
        });
        button.setBounds(69, 52, 129, 36);
        add(button);			        
        
        JXTitledSeparator titledSeparator = new JXTitledSeparator();
        GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator.getLayout();
        gridBagLayout.rowWeights = new double[]{0.0};
        gridBagLayout.rowHeights = new int[]{0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        titledSeparator.setTitle("Remplacer Nom Client");
        titledSeparator.setBounds(31, 11, 1142, 30);
        add(titledSeparator);
        
        JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
        GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_1.getLayout();
        gridBagLayout_1.rowWeights = new double[]{0.0};
        gridBagLayout_1.rowHeights = new int[]{0};
        gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
        gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
        titledSeparator_1.setTitle("Remplacer Client Facture");
        titledSeparator_1.setBounds(31, 99, 1142, 30);
        add(titledSeparator_1);
        
        JButton button_2 = new JButton("Lire Fichier excel");
        button_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		         		
        		if(!txtlientclientfacture.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlientclientfacture.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  			
	  				       
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  	 				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		


   						
	  							
	  							


for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	coutTotalMP = BigDecimal.ZERO;
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellNumFacture=ExcelWSheet.getRow(i).getCell(0);
	CellCodeClient=ExcelWSheet.getRow(i).getCell(1);
	 
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumFacture);
	 
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	 List<FacturePF> listFacturePF=facturePFDAO.findByNumFactureByMagasin(formattedCellStr, 7);
	 ClientPF articleTPF=clientPFDAO.findClientByCodeClient(CellCodeClient.getStringCellValue());  
	 
for( int d=0;d<listFacturePF.size();d++)
{
	
FacturePF facturePF=listFacturePF.get(d);

DetailCompteClient detailCompteClient=detailCompteClientDAO.findByFacture(facturePF.getId());
detailCompteClient.setCompteClient(articleTPF.getCompteClient());

facturePF.setClientPF(articleTPF);	
facturePFDAO.edit(facturePF);
detailCompteClientDAO.edit(detailCompteClient);
	
	
	
	
}
 

 }
 
	 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
        		 	
        	
        		
        		
        	}
        });
        button_2.setBounds(48, 150, 129, 36);
        add(button_2);
        
        txtlientclientfacture = new JTextField();
        txtlientclientfacture.setEditable(false);
        txtlientclientfacture.setColumns(10);
        txtlientclientfacture.setBounds(227, 150, 459, 36);
        add(txtlientclientfacture);
        
        JButton button_3 = new JButton("Ouvrir");
        button_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
                txtlientclientfacture.setText(file.getAbsolutePath());
           
        		
        	
        		
        		
        	}
        });
        button_3.setBounds(696, 150, 89, 30);
        add(button_3);
        
        JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
        GridBagLayout gridBagLayout_2 = (GridBagLayout) titledSeparator_2.getLayout();
        gridBagLayout_2.rowWeights = new double[]{0.0};
        gridBagLayout_2.rowHeights = new int[]{0};
        gridBagLayout_2.columnWeights = new double[]{0.0, 0.0, 0.0};
        gridBagLayout_2.columnWidths = new int[]{0, 0, 0};
        titledSeparator_2.setTitle("Remplacer Client Facture Avec Credit");
        titledSeparator_2.setBounds(10, 203, 1142, 30);
        add(titledSeparator_2);
        
        JButton button_4 = new JButton("Lire Fichier excel");
        button_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		
         		
        		if(!txtlientclientfactureaveccredit.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlientclientfactureaveccredit.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  			
	  				       
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  	 				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		


   						
	  							
	  							


for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	coutTotalMP = BigDecimal.ZERO;
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellNumFacture=ExcelWSheet.getRow(i).getCell(0);
	CellCodeClient=ExcelWSheet.getRow(i).getCell(1);
	 
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumFacture);
	 
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	 List<FacturePF> listFacturePF=facturePFDAO.findByNumFactureByMagasin(formattedCellStr, 7);
	 ClientPF articleTPF=clientPFDAO.findClientByCodeClient(CellCodeClient.getStringCellValue());  
	 
for( int d=0;d<listFacturePF.size();d++)
{
	
FacturePF facturePF=listFacturePF.get(d);


DetailCompteClient detailCompteClient=detailCompteClientDAO.findByFacture(facturePF.getId());
detailCompteClient.setCompteClient(articleTPF.getCompteClient());
facturePF.setModeReglement(Constantes.MODE_REGLEMENT_CREDIT);
facturePF.setCredit(facturePF.getEspece());
facturePF.setEspece(BigDecimal.ZERO);
facturePF.setClientPF(articleTPF);	
facturePFDAO.edit(facturePF);
detailCompteClientDAO.edit(detailCompteClient);
	
	
	
	
}
 

 }
 
	 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
        		 	
        	
        		
        		
        	
        		
        		
        	}
        });
        button_4.setBounds(48, 253, 129, 36);
        add(button_4);
        
        txtlientclientfactureaveccredit = new JTextField();
        txtlientclientfactureaveccredit.setEditable(false);
        txtlientclientfactureaveccredit.setColumns(10);
        txtlientclientfactureaveccredit.setBounds(227, 253, 459, 36);
        add(txtlientclientfactureaveccredit);
        
        JButton button_5 = new JButton("Ouvrir");
        button_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		

        		

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
                txtlientclientfactureaveccredit.setText(file.getAbsolutePath());
           
        		
        	
        		
        		
        	
        		
        		
        	}
        });
        button_5.setBounds(696, 253, 89, 30);
        add(button_5);
        
        JButton button_6 = new JButton("Lire Fichier excel");
        button_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		

        		

        		
         		
        		if(!txtlienRemplacerNumFactureAvoirParNumFactureVente.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlienRemplacerNumFactureAvoirParNumFactureVente.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  			
	  				       
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  	 				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		


   						
	  							
	  							


for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	coutTotalMP = BigDecimal.ZERO;
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellNumFacture=ExcelWSheet.getRow(i).getCell(5);
	CellNumBL=ExcelWSheet.getRow(i).getCell(0);
	 CellDate=ExcelWSheet.getRow(i).getCell(4);
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumBL);
	 Depot depot=depotDAO.findByCode(Constantes.CODE_DEPOT_AGADIR);
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	  
	 FactureAvoirClientPF factureAvoirClientPF=factureAvoirClientPFDAO.findByNumBLByDepot(CellNumBL.getStringCellValue(), depot);
	 
	 
	 
 if(factureAvoirClientPF!=null)
 {
	
	 factureAvoirClientPF.setNumFacture(CellNumFacture.getStringCellValue());
	 factureAvoirClientPF.setDateFacture(CellDate.getDateCellValue());
	 factureAvoirClientPFDAO.edit(factureAvoirClientPF);
	 
	 
	 
 }
	
 
	
	
	
	
 
 

 }
 
	 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
        		 	
        	
        		
        		
        	
        		
        		
        	
        		
        		
        		
        		
        	}
        });
        button_6.setBounds(48, 356, 129, 36);
        add(button_6);
        
        txtlienRemplacerNumFactureAvoirParNumFactureVente = new JTextField();
        txtlienRemplacerNumFactureAvoirParNumFactureVente.setEditable(false);
        txtlienRemplacerNumFactureAvoirParNumFactureVente.setColumns(10);
        txtlienRemplacerNumFactureAvoirParNumFactureVente.setBounds(199, 356, 487, 36);
        add(txtlienRemplacerNumFactureAvoirParNumFactureVente);
        
        JButton button_7 = new JButton("Ouvrir");
        button_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		


        		

        		

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
                txtlienRemplacerNumFactureAvoirParNumFactureVente.setText(file.getAbsolutePath());
           
        		
        	
        		
        		
        	
        		
        		
        	
        		
        		
        	}
        });
        button_7.setBounds(696, 356, 89, 30);
        add(button_7);
        
        JXTitledSeparator titledSeparator_3 = new JXTitledSeparator();
        GridBagLayout gridBagLayout_3 = (GridBagLayout) titledSeparator_3.getLayout();
        gridBagLayout_3.rowWeights = new double[]{0.0};
        gridBagLayout_3.rowHeights = new int[]{0};
        gridBagLayout_3.columnWeights = new double[]{0.0, 0.0, 0.0};
        gridBagLayout_3.columnWidths = new int[]{0, 0, 0};
        titledSeparator_3.setTitle("Remplacer Num Facture Avoir Par Num facture Vente");
        titledSeparator_3.setBounds(10, 315, 1142, 30);
        add(titledSeparator_3);
        
        JXTitledSeparator titledSeparator_4 = new JXTitledSeparator();
        GridBagLayout gridBagLayout_4 = (GridBagLayout) titledSeparator_4.getLayout();
        gridBagLayout_4.rowWeights = new double[]{0.0};
        gridBagLayout_4.rowHeights = new int[]{0};
        gridBagLayout_4.columnWeights = new double[]{0.0, 0.0, 0.0};
        gridBagLayout_4.columnWidths = new int[]{0, 0, 0};
        titledSeparator_4.setTitle("Modifier Les Date Factures PF");
        titledSeparator_4.setBounds(10, 414, 1142, 30);
        add(titledSeparator_4);
        
        JButton button_8 = new JButton("Lire Fichier excel");
        button_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		

        		

        		
         		
        		if(!txtlienModifierdateFactureAchatPF.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlienModifierdateFactureAchatPF.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  			
	  				       
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  	 				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		


   						
	  							
	  							


for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	coutTotalMP = BigDecimal.ZERO;
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellNumFacture=ExcelWSheet.getRow(i).getCell(0);
	 
	 CellDate=ExcelWSheet.getRow(i).getCell(1);
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumFacture);
	 
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	  
	FacturePF facturePF= facturePFDAO.findFactureVentePFByNumBL(formattedCellStr);
	 
	 
	 
 if(facturePF!=null)
 {
	 TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(facturePF.getCodeTransfer());
	 facturePF.setDateFacture(CellDate.getDateCellValue()); 
	 facturePF.setDateBl(CellDate.getDateCellValue());
	 facturePFDAO.edit(facturePF);
	 
	 transferStockPF.setDateTransfer(CellDate.getDateCellValue());
	 transferStockPFDAO.edit(transferStockPF);
	 
	 listDetailTransferPF=detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPF.getId());
	 for(int j=0;j<listDetailTransferPF.size();j++)
	 {
		DetailTransferProduitFini detailTransferProduitFini= listDetailTransferPF.get(j);
		detailTransferProduitFini.setDateTransfer(CellDate.getDateCellValue());
		 
		 detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
		 
	 }
	 
 }
	
 

 }
 
	 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
        		 	
        	
        		
        		
        	
        		
        		
        	
        		
        		
        		
        		
        	
        		
        		
        		
        	}
        });
        button_8.setBounds(48, 455, 129, 36);
        add(button_8);
        
        txtlienModifierdateFactureAchatPF = new JTextField();
        txtlienModifierdateFactureAchatPF.setEditable(false);
        txtlienModifierdateFactureAchatPF.setColumns(10);
        txtlienModifierdateFactureAchatPF.setBounds(199, 455, 487, 36);
        add(txtlienModifierdateFactureAchatPF);
        
        JButton button_9 = new JButton("Ouvrir");
        button_9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		

        		


        		

        		

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
                txtlienModifierdateFactureAchatPF.setText(file.getAbsolutePath());
           
        		
        	
        		
        		
        	
        		
        		
        	
        		
        		
        	
        		
        	}
        });
        button_9.setBounds(696, 455, 89, 30);
        add(button_9);
        
        JXTitledSeparator titledSeparator_5 = new JXTitledSeparator();
        GridBagLayout gridBagLayout_5 = (GridBagLayout) titledSeparator_5.getLayout();
        gridBagLayout_5.rowWeights = new double[]{0.0};
        gridBagLayout_5.rowHeights = new int[]{0};
        gridBagLayout_5.columnWeights = new double[]{0.0, 0.0, 0.0};
        gridBagLayout_5.columnWidths = new int[]{0, 0, 0};
        titledSeparator_5.setTitle("Modifier Les Date Factures Avoir");
        titledSeparator_5.setBounds(10, 514, 1142, 30);
        add(titledSeparator_5);
        
        JButton button_10 = new JButton("Lire Fichier excel");
        button_10.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
    		
        		if(!txtlienModifierdateFactureAvoir.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlienModifierdateFactureAvoir.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  			
	  				       
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(1);
	  				      
	  				      
	  				      
	  	 				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		


   						
	  							
	  							


for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	coutTotalMP = BigDecimal.ZERO;
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellNumFacture=ExcelWSheet.getRow(i).getCell(0);
	 
	 CellDate=ExcelWSheet.getRow(i).getCell(4);
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumFacture);
	 
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	  
	FactureAvoirPF factureAvoirPF=factureAvoirPFDAO.findByNumFacture(formattedCellStr);
	 
	
	 
 if(factureAvoirPF!=null)
 {
	 TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(factureAvoirPF.getCodeTransfer());
	 if(transferStockPF!=null)
	 {
		 factureAvoirPF.setDateFacture(CellDate.getDateCellValue());
		 transferStockPF.setDateTransfer(CellDate.getDateCellValue());
		 
		 List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
		 
		 listDetailTransferProduitFini=detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPF.getId());
		 
		 
		 for(int d=0;d<listDetailTransferProduitFini.size();d++)
		 {
			 
			DetailTransferProduitFini detailTransferProduitFini= listDetailTransferProduitFini.get(d);
			detailTransferProduitFini.setDateTransfer(CellDate.getDateCellValue());
			detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
			 
			 
		 }
		 
		 factureAvoirPFDAO.edit(factureAvoirPF);
		 transferStockPFDAO.edit(transferStockPF);
		 
		 
	 }
	  
	 
	 
	 
 }
	
 
	
	
	
	
 
 

 }
 
	 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
        		 	
        	
        		
        		
        	
        		
        		
        	
        		
        		
        		
        		
        	
        		
        		
        		
        	
        		
        		
        		
        	}
        });
        button_10.setBounds(48, 555, 129, 36);
        add(button_10);
        
        txtlienModifierdateFactureAvoir = new JTextField();
        txtlienModifierdateFactureAvoir.setEditable(false);
        txtlienModifierdateFactureAvoir.setColumns(10);
        txtlienModifierdateFactureAvoir.setBounds(199, 555, 487, 36);
        add(txtlienModifierdateFactureAvoir);
        
        JButton button_11 = new JButton("Ouvrir");
        button_11.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		

        		


        		

        		

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
                txtlienModifierdateFactureAvoir.setText(file.getAbsolutePath());
           
        		
        	
        		
        		
        	
        		
        		
        	
        		
        		
        	
        		
        	
        		
        	}
        });
        button_11.setBounds(696, 555, 89, 30);
        add(button_11);
        
        JXTitledSeparator titledSeparator_6 = new JXTitledSeparator();
        GridBagLayout gridBagLayout_6 = (GridBagLayout) titledSeparator_6.getLayout();
        gridBagLayout_6.rowWeights = new double[]{0.0};
        gridBagLayout_6.rowHeights = new int[]{0};
        gridBagLayout_6.columnWeights = new double[]{0.0, 0.0, 0.0};
        gridBagLayout_6.columnWidths = new int[]{0, 0, 0};
        titledSeparator_6.setTitle("Ajouter Liste Client PF");
        titledSeparator_6.setBounds(10, 602, 1142, 30);
        add(titledSeparator_6);
        
        JButton button_12 = new JButton("Lire Fichier excel");
        button_12.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		 Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot()); 	
        		if(!txtlienAjouterListeClientPF.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlienAjouterListeClientPF.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
 	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
 	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		
 
for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	

	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellClient=ExcelWSheet.getRow(i).getCell(0);
 
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellClient);
	 
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {

//////////////////////////////////////////////  Ajouter Compte Client //////////////////////////////////////////////////////////////////////////////////
	 
	 CompteClient compteclient=new CompteClient();
			compteclient.setCode(util.Utils.genererCodeCompte(Constantes.CODE_COMPTE));
		compteclient.setLibelle(formattedCellStr.toUpperCase());
	compteclient.setUtilisateurCreation(utilisateur);
compteclient.setSolde(BigDecimal.ZERO);
compteclient.setDateCreation(new Date());
compteClientdao.add(compteclient);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 
	int maxidclient=clientPFDAO.maxIdClientPF();
	String codeClient =Utils.genererCodeReferentiel(maxidclient,utilisateur.getCodeDepot());
		 
		ClientPF clientPF = new ClientPF();
	clientPF.setNom(formattedCellStr);
clientPF.setNumTel("");
clientPF.setAdresse("");

clientPF.setDateCreation(new Date());
clientPF.setEmail("");
	clientPF.setCodeDepot(utilisateur.getCodeDepot());
clientPF.setTypeClient(Constantes.CLIENT_LIBELLE_EXTERNE);
clientPF.setCode(codeClient);
clientPF.setUtilCreation(utilisateur);
clientPF.setCompteClient(compteclient);
clientPF.setIce("");
clientPF.setPatente("");
clientPFDAO.add(clientPF);
Utils.incrementerValeurSequenceur(Constantes.CLIENT_LIBELLE);
 
  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
 
 }
 
	 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;

 

} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
         		
        	}
        });
        button_12.setBounds(48, 643, 129, 36);
        add(button_12);
        
        txtlienAjouterListeClientPF = new JTextField();
        txtlienAjouterListeClientPF.setEditable(false);
        txtlienAjouterListeClientPF.setColumns(10);
        txtlienAjouterListeClientPF.setBounds(199, 643, 487, 36);
        add(txtlienAjouterListeClientPF);
        
        JButton button_13 = new JButton("Ouvrir");
        button_13.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		

        		

        		


        		

        		

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
                txtlienAjouterListeClientPF.setText(file.getAbsolutePath());
           
        		
        	
        		
        		
        	
        		
        		
        	
        		
        		
        	
        		
        	
        		
        	
        		
        	}
        });
        button_13.setBounds(696, 643, 89, 30);
        add(button_13);
        
        JXTitledSeparator titledSeparator_7 = new JXTitledSeparator();
        GridBagLayout gridBagLayout_7 = (GridBagLayout) titledSeparator_7.getLayout();
        gridBagLayout_7.rowWeights = new double[]{0.0};
        gridBagLayout_7.rowHeights = new int[]{0};
        gridBagLayout_7.columnWeights = new double[]{0.0, 0.0, 0.0};
        gridBagLayout_7.columnWidths = new int[]{0, 0, 0};
        titledSeparator_7.setTitle("Ajouter Numerotation Des Facture PF Et Mode Reglement");
        titledSeparator_7.setBounds(10, 706, 1142, 30);
        add(titledSeparator_7);
        
        JButton button_14 = new JButton("Lire Fichier excel");
        button_14.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		if(datefacture.getDate()==null)
        		{
        			JOptionPane.showMessageDialog(null, "Veuillez Entrer La date SVP");
        		}
        		
        		
        		 String date =""; 
        		
        		SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
        		
Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
        		
date= dcn.format(datefacture.getDate());
        		
         		
        		if(!txtlienAjouterNumFactureEtModeReglement.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlienAjouterNumFactureEtModeReglement.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  			
	  				       
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  	 				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		


   						
	  							
	  							


for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	coutTotalMP = BigDecimal.ZERO;
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellNumBL=ExcelWSheet.getRow(i).getCell(0);
	 
	 CellNumFacture=ExcelWSheet.getRow(i).getCell(1);
	 CellModeReglement=ExcelWSheet.getRow(i).getCell(2);
	 CellNumModeReglement=ExcelWSheet.getRow(i).getCell(3);
	 
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedNumBL = dataFormatter.formatCellValue(CellNumBL);
	String formattedNumFacture = dataFormatter.formatCellValue(CellNumFacture);
	String formattedModeReglement = dataFormatter.formatCellValue(CellModeReglement);
	String formattedNumModeReglement = dataFormatter.formatCellValue(CellNumModeReglement);
	 String code="";  
	
 if(formattedNumBL!=null && formattedNumBL.isEmpty()==false && !formattedNumBL.equals(""))
 {
	  
	FacturePF facturePF= facturePFDAO.findFactureVentePFByNumBLByDepot(formattedNumBL, depot);
	 
	 
	 
 if(facturePF!=null)
 {
	 code="";
	Integer numfacture=Integer.valueOf(formattedNumFacture)  ;
	
	  if(numfacture<100 && numfacture>=10) {
	  code="0"+numfacture+"/"+date; 
	  }else if(numfacture<10) 
	  { code="00"+numfacture+"/"+date;
	  }else if(numfacture>=100) 
	  { code=numfacture+"/"+date; } 
		  
	 facturePF.setNumFacture(code);
	 facturePF.setModeReglement(formattedModeReglement);
	 facturePF.setEtat(Constantes.ETAT_REGLE);
	 facturePF.setType(Constantes.TYPE_FACTURE);
	
	 
		 if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_CHEQUE)) 
		 {
			 facturePF.setModeReglement(Constantes.MODE_REGLEMENT_CHEQUE);
			 facturePF.setCheque(facturePF.getMontantTTC());
			 facturePF.setEspece(BigDecimal.ZERO);
			 facturePF.setVersement(BigDecimal.ZERO);
			 facturePF.setVirement(BigDecimal.ZERO);
			 facturePF.setCredit(BigDecimal.ZERO);
			 facturePF.setTraite(BigDecimal.ZERO);
			 
			 if(formattedNumModeReglement!=null)
			 {
				 facturePF.setNumCheque(formattedNumModeReglement);
			 }
			 
		 } else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_ESPECE))
		 {

			 facturePF.setModeReglement(Constantes.MODE_REGLEMENT_ESPECE);
			 facturePF.setCheque(BigDecimal.ZERO);
			 facturePF.setEspece(facturePF.getMontantTTC());
			 facturePF.setVersement(BigDecimal.ZERO);
			 facturePF.setVirement(BigDecimal.ZERO);
			 facturePF.setCredit(BigDecimal.ZERO);
			 facturePF.setTraite(BigDecimal.ZERO);
			 facturePF.setNumCheque("---");
			 
		  
			 
			 
		 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_VERSEMENT))
		 {

			 facturePF.setModeReglement(Constantes.MODE_REGLEMENT_VERSEMENT);
			 facturePF.setCheque(BigDecimal.ZERO);
			 facturePF.setEspece(BigDecimal.ZERO);
			 facturePF.setVersement(facturePF.getMontantTTC());
			 facturePF.setVirement(BigDecimal.ZERO);
			 facturePF.setCredit(BigDecimal.ZERO);
			 facturePF.setTraite(BigDecimal.ZERO);
			 
			 if(formattedNumModeReglement!=null)
			 {
				 facturePF.setNumVersement(formattedNumModeReglement);
			 }
			 facturePF.setNumCheque("---");
			 
			 
		 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_VIREMENT))
		 {

			 facturePF.setModeReglement(Constantes.MODE_REGLEMENT_VIREMENT);
			 facturePF.setCheque(BigDecimal.ZERO);
			 facturePF.setEspece(BigDecimal.ZERO);
			 facturePF.setVersement(BigDecimal.ZERO);
			 facturePF.setVirement(facturePF.getMontantTTC());
			 facturePF.setCredit(BigDecimal.ZERO);
			 facturePF.setTraite(BigDecimal.ZERO);
			 facturePF.setNumCheque("---");
			  
			 
		 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_CREDIT))
		 {

			 facturePF.setModeReglement(Constantes.MODE_REGLEMENT_CREDIT);
			 facturePF.setCheque(BigDecimal.ZERO);
			 facturePF.setEspece(BigDecimal.ZERO);
			 facturePF.setVersement(BigDecimal.ZERO);
			 facturePF.setVirement(BigDecimal.ZERO);
			 facturePF.setCredit(facturePF.getMontantTTC());
			 facturePF.setTraite(BigDecimal.ZERO);
			 if(formattedNumModeReglement!=null)
			 {
				 facturePF.setNumCredit (formattedNumModeReglement);
			 }
			 facturePF.setNumCheque("---");
			 
		 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_TRAITE))
		 {

			 facturePF.setModeReglement(Constantes.MODE_REGLEMENT_TRAITE);
			 facturePF.setCheque(BigDecimal.ZERO);
			 facturePF.setEspece(BigDecimal.ZERO);
			 facturePF.setVersement(BigDecimal.ZERO);
			 facturePF.setVirement(BigDecimal.ZERO);
			 facturePF.setCredit(BigDecimal.ZERO);
			 facturePF.setTraite(facturePF.getMontantTTC());
			 if(formattedNumModeReglement!=null)
			 {
				 facturePF.setNumtraite (formattedNumModeReglement);
			 }
			 facturePF.setNumCheque("---");
			 
		 }
		 
	 
	 
	 facturePFDAO.edit(facturePF);
	DetailCompteClient detailCompteClient=detailCompteClientDAO.findByFacture(facturePF.getId()) ;
	detailCompteClient.setMontantCredit(facturePF.getMontantTTC());
	detailCompteClient.getCompteClient().setSolde(detailCompteClient.getCompteClient().getSolde().subtract(facturePF.getMontantTTC()));
	detailCompteClient.setDesignation("Montant sur Facture N "+facturePF.getNumFacture());
	detailCompteClientDAO.edit(detailCompteClient);
	compteClientdao.edit( detailCompteClient.getCompteClient());
	 
	
	 
 }else
 {
	 
	 FactureServiceTransport factureTransport=factureServiceTransportdao.findFactureVentePFByNumBL(formattedNumBL) ;
	 if(factureTransport!=null)
	 {

		 code="";
		Integer numfacture=Integer.valueOf(formattedNumFacture)  ;
		
		  if(numfacture<100 && numfacture>=10) {
		  code="0"+numfacture+"/"+date; 
		  }else if(numfacture<10) 
		  { code="00"+numfacture+"/"+date;
		  }else if(numfacture>=100) 
		  { code=numfacture+"/"+date; } 
			  
		  factureTransport.setNumFacture(code);
		  factureTransport.setModeReglement(formattedModeReglement);
		  factureTransport.setEtat(Constantes.ETAT_REGLE);
		  factureTransport.setType(Constantes.TYPE_FACTURE);
		  
		 
			 if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_CHEQUE)) 
			 {
				 factureTransport.setModeReglement(Constantes.MODE_REGLEMENT_CHEQUE);
				 factureTransport.setCheque(factureTransport.getMontantTTC());
				 factureTransport.setEspece(BigDecimal.ZERO);
				 factureTransport.setVersement(BigDecimal.ZERO);
				 factureTransport.setVirement(BigDecimal.ZERO);
				 factureTransport.setCredit(BigDecimal.ZERO);
				 factureTransport.setTraite(BigDecimal.ZERO);
				 
				 if(formattedNumModeReglement!=null)
				 {
					 factureTransport.setNumCheque(formattedNumModeReglement);
				 }
				 
			 } else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_ESPECE))
			 {

				 factureTransport.setModeReglement(Constantes.MODE_REGLEMENT_ESPECE);
				 factureTransport.setCheque(BigDecimal.ZERO);
				 factureTransport.setEspece(factureTransport.getMontantTTC());
				 factureTransport.setVersement(BigDecimal.ZERO);
				 factureTransport.setVirement(BigDecimal.ZERO);
				 factureTransport.setCredit(BigDecimal.ZERO);
				 factureTransport.setTraite(BigDecimal.ZERO);
				 factureTransport.setNumCheque("---");
				 
			  
				 
				 
			 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_VERSEMENT))
			 {

				 factureTransport.setModeReglement(Constantes.MODE_REGLEMENT_VERSEMENT);
				 factureTransport.setCheque(BigDecimal.ZERO);
				 factureTransport.setEspece(BigDecimal.ZERO);
				 factureTransport.setVersement(factureTransport.getMontantTTC());
				 factureTransport.setVirement(BigDecimal.ZERO);
				 factureTransport.setCredit(BigDecimal.ZERO);
				 factureTransport.setTraite(BigDecimal.ZERO);
				 
				 if(formattedNumModeReglement!=null)
				 {
					 factureTransport.setNumVersement(formattedNumModeReglement);
				 }
				 factureTransport.setNumCheque("---");
				 
				 
			 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_VIREMENT))
			 {

				 factureTransport.setModeReglement(Constantes.MODE_REGLEMENT_VIREMENT);
				 factureTransport.setCheque(BigDecimal.ZERO);
				 factureTransport.setEspece(BigDecimal.ZERO);
				 factureTransport.setVersement(BigDecimal.ZERO);
				 factureTransport.setVirement(factureTransport.getMontantTTC());
				 factureTransport.setCredit(BigDecimal.ZERO);
				 factureTransport.setTraite(BigDecimal.ZERO);
				 factureTransport.setNumCheque("---");
				  
				 
			 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_CREDIT))
			 {

				 factureTransport.setModeReglement(Constantes.MODE_REGLEMENT_CREDIT);
				 factureTransport.setCheque(BigDecimal.ZERO);
				 factureTransport.setEspece(BigDecimal.ZERO);
				 factureTransport.setVersement(BigDecimal.ZERO);
				 factureTransport.setVirement(BigDecimal.ZERO);
				 factureTransport.setCredit(factureTransport.getMontantTTC());
				 factureTransport.setTraite(BigDecimal.ZERO);
				 if(formattedNumModeReglement!=null)
				 {
					 factureTransport.setNumCredit (formattedNumModeReglement);
				 }
				 factureTransport.setNumCheque("---");
				 
			 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_TRAITE))
			 {

				 factureTransport.setModeReglement(Constantes.MODE_REGLEMENT_TRAITE);
				 factureTransport.setCheque(BigDecimal.ZERO);
				 factureTransport.setEspece(BigDecimal.ZERO);
				 factureTransport.setVersement(BigDecimal.ZERO);
				 factureTransport.setVirement(BigDecimal.ZERO);
				 factureTransport.setCredit(BigDecimal.ZERO);
				 factureTransport.setTraite(factureTransport.getMontantTTC());
				 if(formattedNumModeReglement!=null)
				 {
					 factureTransport.setNumtraite (formattedNumModeReglement);
				 }
				 factureTransport.setNumCheque("---");
				 
			 }
			 
		 
		 
			 factureServiceTransportdao.edit(factureTransport);
 		 
	 }else
	 {
		 FactureAutresVente factureAutresVente=factureAutresVentedao.findFactureVentePFByNumBL(formattedNumBL);
		 
		if(factureAutresVente!=null)
		{
			

			 code="";
			Integer numfacture=Integer.valueOf(formattedNumFacture)  ;
			
			  if(numfacture<100 && numfacture>=10) {
			  code="0"+numfacture+"/"+date; 
			  }else if(numfacture<10) 
			  { code="00"+numfacture+"/"+date;
			  }else if(numfacture>=100) 
			  { code=numfacture+"/"+date; } 
				  
			  factureAutresVente.setNumFacture(code);
			  factureAutresVente.setModeReglement(formattedModeReglement);
			  factureAutresVente.setEtat(Constantes.ETAT_REGLE);
			  factureAutresVente.setType(Constantes.TYPE_FACTURE);
			 
			 
				 if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_CHEQUE)) 
				 {
					 factureAutresVente.setModeReglement(Constantes.MODE_REGLEMENT_CHEQUE);
					 factureAutresVente.setCheque(factureAutresVente.getMontantTTC());
					 factureAutresVente.setEspece(BigDecimal.ZERO);
					 factureAutresVente.setVersement(BigDecimal.ZERO);
					 factureAutresVente.setVirement(BigDecimal.ZERO);
					 factureAutresVente.setCredit(BigDecimal.ZERO);
					 factureAutresVente.setTraite(BigDecimal.ZERO);
					 
					 if(formattedNumModeReglement!=null)
					 {
						 factureAutresVente.setNumCheque(formattedNumModeReglement);
					 }
					 
				 } else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_ESPECE))
				 {

					 factureAutresVente.setModeReglement(Constantes.MODE_REGLEMENT_ESPECE);
					 factureAutresVente.setCheque(BigDecimal.ZERO);
					 factureAutresVente.setEspece(factureAutresVente.getMontantTTC());
					 factureAutresVente.setVersement(BigDecimal.ZERO);
					 factureAutresVente.setVirement(BigDecimal.ZERO);
					 factureAutresVente.setCredit(BigDecimal.ZERO);
					 factureAutresVente.setTraite(BigDecimal.ZERO);
					 factureAutresVente.setNumCheque("---");
					 
				  
					 
					 
				 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_VERSEMENT))
				 {

					 factureAutresVente.setModeReglement(Constantes.MODE_REGLEMENT_VERSEMENT);
					 factureAutresVente.setCheque(BigDecimal.ZERO);
					 factureAutresVente.setEspece(BigDecimal.ZERO);
					 factureAutresVente.setVersement(factureAutresVente.getMontantTTC());
					 factureAutresVente.setVirement(BigDecimal.ZERO);
					 factureAutresVente.setCredit(BigDecimal.ZERO);
					 factureAutresVente.setTraite(BigDecimal.ZERO);
					 
					 if(formattedNumModeReglement!=null)
					 {
						 factureAutresVente.setNumVersement(formattedNumModeReglement);
					 }
					 factureAutresVente.setNumCheque("---");
					 
					 
				 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_VIREMENT))
				 {

					 factureAutresVente.setModeReglement(Constantes.MODE_REGLEMENT_VIREMENT);
					 factureAutresVente.setCheque(BigDecimal.ZERO);
					 factureAutresVente.setEspece(BigDecimal.ZERO);
					 factureAutresVente.setVersement(BigDecimal.ZERO);
					 factureAutresVente.setVirement(factureAutresVente.getMontantTTC());
					 factureAutresVente.setCredit(BigDecimal.ZERO);
					 factureAutresVente.setTraite(BigDecimal.ZERO);
					 factureAutresVente.setNumCheque("---");
					  
					 
				 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_CREDIT))
				 {

					 factureAutresVente.setModeReglement(Constantes.MODE_REGLEMENT_CREDIT);
					 factureAutresVente.setCheque(BigDecimal.ZERO);
					 factureAutresVente.setEspece(BigDecimal.ZERO);
					 factureAutresVente.setVersement(BigDecimal.ZERO);
					 factureAutresVente.setVirement(BigDecimal.ZERO);
					 factureAutresVente.setCredit(factureAutresVente.getMontantTTC());
					 factureAutresVente.setTraite(BigDecimal.ZERO);
					 if(formattedNumModeReglement!=null)
					 {
						 factureAutresVente.setNumCredit (formattedNumModeReglement);
					 }
					 factureAutresVente.setNumCheque("---");
					 
				 }else if(formattedModeReglement.equals(Constantes.MODE_REGLEMENT_TRAITE))
				 {

					 factureAutresVente.setModeReglement(Constantes.MODE_REGLEMENT_TRAITE);
					 factureAutresVente.setCheque(BigDecimal.ZERO);
					 factureAutresVente.setEspece(BigDecimal.ZERO);
					 factureAutresVente.setVersement(BigDecimal.ZERO);
					 factureAutresVente.setVirement(BigDecimal.ZERO);
					 factureAutresVente.setCredit(BigDecimal.ZERO);
					 factureAutresVente.setTraite(factureAutresVente.getMontantTTC());
					 if(formattedNumModeReglement!=null)
					 {
						 factureAutresVente.setNumtraite (formattedNumModeReglement);
					 }
					 factureAutresVente.setNumCheque("---");
					 
				 }
				 
			 
			 
				 factureAutresVentedao.edit(factureAutresVente);
			DetailCompteClient detailCompteClient=detailCompteClientDAO.findByFactureAutresVente(factureAutresVente.getId()) ;
			if(detailCompteClient!=null)
			{
				detailCompteClient.setMontantCredit(factureAutresVente.getMontantTTC());
				detailCompteClient.getCompteClient().setSolde(detailCompteClient.getCompteClient().getSolde().subtract(factureAutresVente.getMontantTTC()));
				detailCompteClient.setDesignation("Montant sur Facture N "+factureAutresVente.getNumFacture());
				detailCompteClientDAO.edit(detailCompteClient);
				compteClientdao.edit( detailCompteClient.getCompteClient());
			}
			
			 
			
			 
		 
			
			
		}
		 
		 
		 
	 }
	 
	 
	 
	 
	 
	 
 }
	
 

 }
 
	 

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
        		 	
        	
        		
        		
        	
        		
        		
        	
        		
        		
        		
        		
        	
        		
        		
        		
        	
        		
        		
        		
        		
        	}
        });
        button_14.setBounds(48, 747, 129, 36);
        add(button_14);
        
        txtlienAjouterNumFactureEtModeReglement = new JTextField();
        txtlienAjouterNumFactureEtModeReglement.setEditable(false);
        txtlienAjouterNumFactureEtModeReglement.setColumns(10);
        txtlienAjouterNumFactureEtModeReglement.setBounds(199, 747, 487, 36);
        add(txtlienAjouterNumFactureEtModeReglement);
        
        JButton button_15 = new JButton("Ouvrir");
        button_15.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
 
        		

                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
                txtlienAjouterNumFactureEtModeReglement.setText(file.getAbsolutePath());
           
        		
        	 
        		
        	
        		
        	}
        });
        button_15.setBounds(696, 747, 89, 30);
        add(button_15);
        
        JLabel label = new JLabel("Date Facture:");
        label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
        label.setBounds(811, 749, 97, 24);
        add(label);
        
          datefacture = new JDateChooser();
        datefacture.setLocale(Locale.FRANCE);
        datefacture.setDateFormatString("yyyy");
        datefacture.setBounds(906, 747, 151, 26);
        add(datefacture);
				  		     
				  		 
	}
	
 
	
	void remplirQuantiteOffreMP(List<CoutMP> listCoutMP , Promotion promotion,Magasin magasinStockage) {

		 
			;
			BigDecimal quantiteAcharge = BigDecimal.ZERO;
			BigDecimal quantiteCalule = BigDecimal.ZERO;
			BigDecimal quantiteExistante = BigDecimal.ZERO;
			BigDecimal quantiteCarton = BigDecimal.ZERO;
			BigDecimal quantiteThe = BigDecimal.ZERO;
			BigDecimal quantiteTheTotal = BigDecimal.ZERO;
			BigDecimal quantiteFilmGold = BigDecimal.ZERO;
			BigDecimal quantiteFilmNormal = BigDecimal.ZERO;
			BigDecimal quantiteBox = BigDecimal.ZERO;
			boolean trouve = false;
			boolean find = false;
			boolean findBox = false;
			CoutMP coutMP = new CoutMP();
			StockMP stockMPMagasinProd = new StockMP();
			StockMP stockMPQauantiteManquante = new StockMP();
			StockMP stockMP = new StockMP();
			 
			Magasin magasinProd = magasinProduction;

			 
				 
				for (int j = 0; j < listCoutMP.size(); j++) {
					coutMP = listCoutMP.get(j);
					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)) {
						quantiteCarton =quantiteCarton.add(coutMP.getQuantite().setScale(0, RoundingMode.DOWN)) ;
					}
					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						quantiteTheTotal = quantiteTheTotal.add(coutMP.getQuantite());

					}
					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL))
						quantiteFilmNormal = coutMP.getQuantite();
					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD))
						quantiteFilmGold = coutMP.getQuantite();

				}

				List<DetailEstimationPromo> listeDetailEstimationPromo = promotion.getDetailEstimationPromo();

				for (int i = 0; i < listeDetailEstimationPromo.size(); i++) {
					DetailEstimationPromo detailEstimationPromo = listeDetailEstimationPromo.get(i);
					trouve = false;
					find = false;
					quantiteCalule = quantiteCarton.multiply(detailEstimationPromo.getQuantite());

					stockMP = stockMPDAO.findStockByMagasinMP(detailEstimationPromo.getMatierePremiere().getId(),
							magasinStockage.getId());
					stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(
							detailEstimationPromo.getMatierePremiere().getId(), magasinProd.getId());
					if(stockMPMagasinProd!=null)
					{
						quantiteExistante = stockMPMagasinProd.getStock();
					}
					

					if (detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						quantiteThe = quantiteCalule;

					for (int j = 0; j < listCoutMP.size(); j++) {

						coutMP = listCoutMP.get(j);

						if (detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode()
								.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)) {
							// quantiteBox=quantiteCalule;
							findBox = true;

						}

						if (trouve == false && detailEstimationPromo.getMatierePremiere().getCode()
								.equals(coutMP.getMatierePremier().getCode())) {

							quantiteCalule = quantiteCalule.add(coutMP.getQuantite());
							quantiteAcharge = quantiteCalule.subtract(coutMP.getQuantExistante());

							if (detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp()
									.getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)
									|| detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp()
											.getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
								quantiteAcharge = quantiteAcharge.setScale(0, RoundingMode.FLOOR);

							coutMP.setQuantite(quantiteCalule);
							coutMP.setQuantEstime(quantiteAcharge);
							coutMP.setQuantCharge(quantiteAcharge);
							if(detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp()
									.getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)
									|| detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp()
											.getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							{
								coutMP.setQuantiteOffre(quantiteCarton.multiply(detailEstimationPromo.getQuantite()).setScale(0, RoundingMode.FLOOR));
							}else {
								coutMP.setQuantiteOffre(quantiteCarton.multiply(detailEstimationPromo.getQuantite()));
							}
							
							listCoutMP.set(j, coutMP);
							trouve = true;
							find = true;

						}
					}

					if (find == false) {
						quantiteAcharge = quantiteCalule.subtract(quantiteExistante);
						if (detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode()
								.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)
								|| detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp()
										.getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
							quantiteAcharge = quantiteAcharge.setScale(0, RoundingMode.FLOOR);
						coutMP = new CoutMP();
						coutMP.setMatierePremier(detailEstimationPromo.getMatierePremiere());
						if(stockMP!=null)
						{
							coutMP.setPrixUnitaire(stockMP.getPrixUnitaire());
						}else
						{
							coutMP.setPrixUnitaire(BigDecimal.ZERO);
						}
						
						coutMP.setQuantite(quantiteCalule);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteAcharge);
						coutMP.setQuantCharge(quantiteAcharge);
						
						if (detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp().getCode()
								.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)
								|| detailEstimationPromo.getMatierePremiere().getCategorieMp().getSubCategorieMp()
										.getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
						{
							coutMP.setQuantiteOffre(quantiteCarton.multiply(detailEstimationPromo.getQuantite()).setScale(0, RoundingMode.FLOOR));
						}else
						{
							coutMP.setQuantiteOffre(quantiteCarton.multiply(detailEstimationPromo.getQuantite()));
							
						}
						
						 
						listCoutMP.add(coutMP);
						find = true;
					}
				}

				if (findBox == true) {
					quantiteCalule = BigDecimal.ZERO;
					quantiteAcharge = BigDecimal.ZERO;
					trouve = false;
					find = false;
					for (int j = 0; j < listCoutMP.size(); j++) {

						coutMP = listCoutMP.get(j);
						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL)) {
							quantiteCalule = (quantiteFilmNormal.divide(quantiteTheTotal, 6, BigDecimal.ROUND_HALF_UP))
									.multiply(quantiteThe);

							quantiteCalule = quantiteCalule.add(coutMP.getQuantite());
							quantiteAcharge = quantiteCalule.subtract(coutMP.getQuantExistante());
							coutMP.setQuantite(quantiteCalule);
							coutMP.setQuantEstime(quantiteAcharge);
							coutMP.setQuantCharge(quantiteAcharge);
							coutMP.setQuantiteOffre((quantiteFilmNormal.divide(quantiteTheTotal, 6, BigDecimal.ROUND_HALF_UP))
									.multiply(quantiteThe));
							listCoutMP.set(j, coutMP);
							trouve = true;
						} else if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD)) {
							quantiteCalule = (quantiteFilmGold.divide(quantiteTheTotal, 6, BigDecimal.ROUND_HALF_UP))
									.multiply(quantiteThe);

							quantiteCalule = quantiteCalule.add(coutMP.getQuantite());
							quantiteAcharge = quantiteCalule.subtract(coutMP.getQuantExistante());
							if (quantiteAcharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteAcharge = BigDecimal.ZERO;
							coutMP.setQuantite(quantiteCalule);
							coutMP.setQuantEstime(quantiteAcharge);
							coutMP.setQuantCharge(quantiteAcharge);
							coutMP.setQuantiteOffre((quantiteFilmGold.divide(quantiteTheTotal, 6, BigDecimal.ROUND_HALF_UP))
									.multiply(quantiteThe));
							listCoutMP.set(j, coutMP);
							find = true;
						} else if (trouve == true && find == true)
							break;
					}

				}
			 
		 
	}
	
	
	boolean afficherTableMatierePremiereCreerOF( Magasin magasinStockage,Magasin magasinStockageSte, Date dateProduction,String service) {
		creerOF = true;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String date="01/01/"+sdf.format(dateProduction)+"";
			Date dateDebut= new Date(date);
		BigDecimal StockFinale=BigDecimal.ZERO;
	 
		listCoutMPTmpMPInsufisant.clear();
			BigDecimal prix_unitaire = BigDecimal.ZERO;
			BigDecimal quantiteExistante = BigDecimal.ZERO;
			BigDecimal quantiteACharge = BigDecimal.ZERO;
			BigDecimal quantiteAChargeTHE = BigDecimal.ZERO;
			BigDecimal quantiteTotal = BigDecimal.ZERO;
			BigDecimal quantiteManqaunte = BigDecimal.ZERO;
		 
			List listCoutMPTmp = new ArrayList<CoutMP>();
			StockMP stockMP = new StockMP();
		 
			StockMP stockMPMagasinProd = new StockMP();

			Magasin magasinElnasTeaPacking = null;
			StockMP stockMPElnassTeaPacking = null;
			StockMP stockMPQauantiteManquante = new StockMP();
			
			 
			Magasin magasinProd = magasinProduction;

			if (!magasinStockage.getLibelle()
					.equals(magasinStockageSte.getLibelle())) {
				magasinElnasTeaPacking = magasinStockageSte;
			}

			// intialiserTableau();
			int j = 0;
			for (int i = 0; i < listCoutMP.size(); i++) {
				StockFinale=BigDecimal.ZERO;
				CoutMP coutMP   = listCoutMP.get(i);
				stockMPQauantiteManquante = new StockMP();
				quantiteTotal = coutMP.getQuantCharge();

				if (service.contains(Constantes.CODE_OUI)) {
					// Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU
					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {

						stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinStockage.getId());
						
						// Stock Finale de ce jour
						List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateProduction, magasinStockage, coutMP.getMatierePremier());
						if(!listestockfinale.isEmpty())
						{
							for(int t=0;t<listestockfinale.size();t++)
				 			{
								
				 				
				 			Object[] object=listestockfinale.get(t);
							
				 			StockFinale= (BigDecimal)object[1];
				 			
							
				 			}
						}
						
						
						stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinProd.getId());
						if(stockMPMagasinProd!=null)
						{
							quantiteExistante = stockMPMagasinProd.getStock();
						}
						
						if(stockMP!=null)
						{
							prix_unitaire = stockMP.getPrixUnitaire();
						}
						
						quantiteACharge = quantiteTotal.subtract(quantiteExistante);

						if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
							quantiteACharge = BigDecimal.ZERO;

						if (  quantiteTotal.compareTo(StockFinale) > 0) {
							/// 2eme etape

								
								quantiteManqaunte = quantiteACharge.subtract(StockFinale);
								
						
							

							if (service.contains(Constantes.CODE_OUI)) {
								

								
								
								stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
								stockMPQauantiteManquante.setStock(quantiteManqaunte);
							//	stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
								creerOF = false;
								nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
								 
							
								
							 } else {
								
								
								stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
								stockMPQauantiteManquante.setStock(quantiteManqaunte);
								//stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
								creerOF = false;
								nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
								 
							}

							j++;
						} else if ( StockFinale.compareTo(quantiteTotal) >= 0) {

							
							coutMP.setMagasin(magasinStockage);
							coutMP.setTransfer(COUT_MP_TRANSFER_NON);
							listCoutMP.set(i, coutMP);
							 
						}

						if (quantiteTotal.compareTo(BigDecimal.ZERO) > 0) {
							mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
									coutMP.getMatierePremier());
							mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteTotal);

						}

					}

					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {
						StockFinale=BigDecimal.ZERO;

						stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinStockage.getId());
						
						// Stock Finale de ce jour
						List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateProduction, magasinStockage, coutMP.getMatierePremier());
						if(!listestockfinale.isEmpty())
						{
							for(int t=0;t<listestockfinale.size();t++)
				 			{
				 				
				 			 Object[] object=listestockfinale.get(t);
							
				 			StockFinale= (BigDecimal)object[1];
							
				 			}
						}

						if (StockFinale.compareTo(BigDecimal.ZERO)!=0) {

							stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
									magasinProd.getId());
							if(stockMPMagasinProd!=null)
							{
								quantiteExistante = stockMPMagasinProd.getStock();
							}
							
							if(stockMP!=null)
							{
								prix_unitaire = stockMP.getPrixUnitaire();
							}
						
							quantiteACharge = quantiteTotal.subtract(quantiteExistante);

							if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteACharge = BigDecimal.ZERO;
							if ( StockFinale.compareTo(quantiteTotal) >= 0) {

								 
								coutMP.setMagasin(magasinStockage);
								coutMP.setTransfer(COUT_MP_TRANSFER_NON);
								listCoutMP.set(i, coutMP);
								 
							} else {
								
								
								BigDecimal QuantiteExisteEnStockClient=BigDecimal.ZERO;
								 
								if(StockFinale.compareTo(BigDecimal.ZERO)!=0)
								{
									QuantiteExisteEnStockClient=(StockFinale.subtract(quantiteTotal)).multiply(new BigDecimal(-1));
								}
								
 
								if (magasinElnasTeaPacking != null) {
									stockMPElnassTeaPacking = stockMPDAO.findStockByMagasinMP(
											coutMP.getMatierePremier().getId(), magasinElnasTeaPacking.getId());
								}

								stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
										magasinElnasTeaPacking.getId());
								// Stock Finale de ce jour
								StockFinale=BigDecimal.ZERO;
								List<Object[]> listestockfinaleMagasinElnasTeaPacking=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateProduction, magasinElnasTeaPacking, coutMP.getMatierePremier());
								if(!listestockfinaleMagasinElnasTeaPacking.isEmpty())
								{
									for(int t=0;t<listestockfinaleMagasinElnasTeaPacking.size();t++)
						 			{
						 				
						 			 Object[] object=listestockfinaleMagasinElnasTeaPacking.get(t);
									
						 			StockFinale= (BigDecimal)object[1];
									
						 			}
								}
								
								
								stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
										magasinProd.getId());
								if(stockMPMagasinProd!=null)
								{
									quantiteExistante = stockMPMagasinProd.getStock();
								}
							if(stockMP!=null)
							{
								prix_unitaire = stockMP.getPrixUnitaire();
							}
							
								quantiteACharge = (quantiteTotal.subtract(QuantiteExisteEnStockClient)).subtract(quantiteExistante);

								if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
									quantiteACharge = BigDecimal.ZERO;

								if ((quantiteTotal.subtract(QuantiteExisteEnStockClient)).compareTo(StockFinale) > 0 ) {
									/// 2eme etape

									 
										quantiteManqaunte = quantiteACharge.subtract(StockFinale);
									 
									

									 
										

										stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
										stockMPQauantiteManquante.setStock(quantiteManqaunte);
										//stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
										creerOF = false;
										nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte
												+ "\n";
									 

									j++;
								} else if ( StockFinale.compareTo((quantiteTotal.subtract(QuantiteExisteEnStockClient))) >= 0 ) {

									coutMP.setQuantCharge(quantiteTotal.subtract(QuantiteExisteEnStockClient));
									coutMP.setQuantConsomme(quantiteTotal.subtract(QuantiteExisteEnStockClient));
									coutMP.setPrixTotal((quantiteTotal.subtract(QuantiteExisteEnStockClient)).multiply(coutMP.getPrixUnitaire()));
									coutMP.setMagasin(magasinElnasTeaPacking);
									coutMP.setTransfer(COUT_MP_TRANSFER_NON);
									listCoutMP.set(i, coutMP);
									 
									CoutMP coutMPTmp=new CoutMP();
									coutMPTmp.setMatierePremier(coutMP.getMatierePremier());
									coutMPTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
									coutMPTmp.setPrixTotal(coutMP.getPrixUnitaire().multiply(QuantiteExisteEnStockClient));
									coutMPTmp.setProdcutionCM(coutMP.getProdcutionCM());
									coutMPTmp.setQuantCharge(QuantiteExisteEnStockClient);
									coutMPTmp.setQuantConsomme(QuantiteExisteEnStockClient);
									coutMPTmp.setMagasin(magasinStockage);
									coutMPTmp.setTransfer(COUT_MP_TRANSFER_NON);
									listCoutMPTmpMPInsufisant.add(coutMPTmp);
									 
								}

								if ((quantiteTotal.subtract(QuantiteExisteEnStockClient)).compareTo(BigDecimal.ZERO) > 0) {
									mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
											coutMP.getMatierePremier());
									mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), (quantiteTotal.subtract(QuantiteExisteEnStockClient)));

								}

							}

						} else {

							if (magasinElnasTeaPacking != null) {
								stockMPElnassTeaPacking = stockMPDAO.findStockByMagasinMP(
										coutMP.getMatierePremier().getId(), magasinElnasTeaPacking.getId());
							}
							
							
							StockFinale=BigDecimal.ZERO;
							// Stock Finale de ce jour
							List<Object[]> listestockfinaleMagasinElnassTeaPacking=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateProduction, magasinElnasTeaPacking, coutMP.getMatierePremier());
							if(!listestockfinaleMagasinElnassTeaPacking.isEmpty())
							{
								for(int t=0;t<listestockfinaleMagasinElnassTeaPacking.size();t++)
					 			{
					 				
					 			 Object[] object=listestockfinaleMagasinElnassTeaPacking.get(t);
								
					 			StockFinale= (BigDecimal)object[1];
								
					 			}
							}

							stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
									magasinElnasTeaPacking.getId());
							
							
							stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
									magasinProd.getId());
							if(stockMPMagasinProd!=null)
							{
								quantiteExistante = stockMPMagasinProd.getStock();
							}
							if(stockMP!=null)
							{
								prix_unitaire = stockMP.getPrixUnitaire();
							}
							
							quantiteACharge = quantiteTotal.subtract(quantiteExistante);

							if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteACharge = BigDecimal.ZERO;

							if (  quantiteTotal.compareTo(StockFinale) > 0) {
								/// 2eme etape

							 
									quantiteManqaunte = quantiteACharge.subtract(StockFinale);
								 

								 

									stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
									stockMPQauantiteManquante.setStock(quantiteManqaunte);
									//stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
									creerOF = false;
									nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
									 
								 

								j++;
							} else if (StockFinale.compareTo(quantiteTotal) >= 0) {

								 
								coutMP.setMagasin(magasinElnasTeaPacking);
								coutMP.setTransfer(COUT_MP_TRANSFER_NON);
								listCoutMP.set(i, coutMP);
								 
							}

							if (quantiteTotal.compareTo(BigDecimal.ZERO) > 0) {
								mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
										coutMP.getMatierePremier());
								mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteTotal);

							}

						}

					}

					if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)
							&& !coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))

					{
						
						
						if(coutMP.getMatierePremier().getCode().equals("MP_1502"))
						{
							System.out.println("MP_1502");
							
						}
						

						stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinStockage.getId());
						
						StockFinale=BigDecimal.ZERO;
						// Stock Finale de ce jour
						List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateProduction, magasinStockage, coutMP.getMatierePremier());
						if(!listestockfinale.isEmpty())
						{
							for(int t=0;t<listestockfinale.size();t++)
				 			{
				 				
				 			 Object[] object=listestockfinale.get(t);
							
				 			StockFinale= (BigDecimal)object[1];
							
				 			}
						}
 
					

						if (StockFinale.compareTo(BigDecimal.ZERO)!=0) {

							stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
									magasinProd.getId());
							if(stockMPMagasinProd!=null)
							{
								quantiteExistante = stockMPMagasinProd.getStock();
							}
							if(stockMP!=null)
							{
								prix_unitaire = stockMP.getPrixUnitaire();	
							}
							
							quantiteACharge = quantiteTotal.subtract(quantiteExistante);

							if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteACharge = BigDecimal.ZERO;
							if ( StockFinale.compareTo(quantiteTotal) >= 0) {

								 
								coutMP.setMagasin(magasinStockage);
								coutMP.setTransfer(COUT_MP_TRANSFER_NON);
								listCoutMP.set(i, coutMP);
								 
							} else {
								
						BigDecimal QuantiteExisteEnStockClient=BigDecimal.ZERO;
						 
						if(StockFinale.compareTo(BigDecimal.ZERO)!=0)
						{
							QuantiteExisteEnStockClient=StockFinale;
						}
						
								

								if (magasinElnasTeaPacking != null) {
									stockMPElnassTeaPacking = stockMPDAO.findStockByMagasinMP(
											coutMP.getMatierePremier().getId(), magasinElnasTeaPacking.getId());
								}

								stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
										magasinElnasTeaPacking.getId());
								
								StockFinale=BigDecimal.ZERO;
								// Stock Finale de ce jour
								List<Object[]> listestockfinaleElnasTeaPacking=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateProduction, magasinElnasTeaPacking, coutMP.getMatierePremier());
								if(!listestockfinaleElnasTeaPacking.isEmpty())
								{
									for(int t=0;t<listestockfinaleElnasTeaPacking.size();t++)
						 			{
						 				
						 			 Object[] object=listestockfinaleElnasTeaPacking.get(t);
									
						 			StockFinale= (BigDecimal)object[1];
									
						 			}
								}
								
								stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
										magasinProd.getId());
								if(stockMPMagasinProd!=null)
								{
									quantiteExistante = stockMPMagasinProd.getStock();	
								}
								
								if(stockMP!=null)
								{
									prix_unitaire = stockMP.getPrixUnitaire();
								}
								
								quantiteACharge = (quantiteTotal.subtract(QuantiteExisteEnStockClient)).subtract(quantiteExistante);

								if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
									quantiteACharge = BigDecimal.ZERO;

								if ((quantiteTotal.subtract(QuantiteExisteEnStockClient)).compareTo(StockFinale) > 0) {
									/// 2eme etape

									
										quantiteManqaunte = quantiteACharge.subtract(StockFinale);
									

								 
												stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
												stockMPQauantiteManquante.setStock(quantiteManqaunte);
												//stockMPQauantiteManquante.setMagasin(stockMPElnassTeaPacking.getMagasin());
												creerOF = false;
												nomMP += "-" + coutMP.getMatierePremier().getNom() + ":"
														+ quantiteManqaunte + "\n";
												 

								 

									j++;
								} else if (StockFinale.compareTo(quantiteTotal.subtract(QuantiteExisteEnStockClient)) >= 0 ) {

									coutMP.setQuantCharge(quantiteTotal.subtract(QuantiteExisteEnStockClient));
									coutMP.setQuantConsomme(quantiteTotal.subtract(QuantiteExisteEnStockClient));
									coutMP.setPrixTotal(quantiteTotal.subtract(QuantiteExisteEnStockClient).multiply(coutMP.getPrixUnitaire()));
									coutMP.setMagasin(magasinElnasTeaPacking);
									coutMP.setTransfer(COUT_MP_TRANSFER_NON);
									listCoutMP.set(i, coutMP);
									
									CoutMP coutMPTmp=new CoutMP();
									coutMPTmp.setMatierePremier(coutMP.getMatierePremier());
									coutMPTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
									coutMPTmp.setPrixTotal(coutMP.getPrixUnitaire().multiply(QuantiteExisteEnStockClient));
									coutMPTmp.setProdcutionCM(coutMP.getProdcutionCM());
									coutMPTmp.setQuantCharge(QuantiteExisteEnStockClient);
									coutMPTmp.setQuantConsomme(QuantiteExisteEnStockClient);
									coutMPTmp.setMagasin(magasinStockage);
									coutMPTmp.setTransfer(COUT_MP_TRANSFER_NON);
									listCoutMPTmpMPInsufisant.add(coutMPTmp);
								}

								if ((quantiteTotal.subtract(QuantiteExisteEnStockClient)).compareTo(BigDecimal.ZERO) > 0) {
									mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
											coutMP.getMatierePremier());
									mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteTotal.subtract(QuantiteExisteEnStockClient));

								}

							}

						} else {

							if (magasinElnasTeaPacking != null) {
								stockMPElnassTeaPacking = stockMPDAO.findStockByMagasinMP(
										coutMP.getMatierePremier().getId(), magasinElnasTeaPacking.getId());
							}

							stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
									magasinElnasTeaPacking.getId());
							
							StockFinale=BigDecimal.ZERO;
							// Stock Finale de ce jour
							List<Object[]> listestockfinaleElnasTeaPacking=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateProduction, magasinElnasTeaPacking, coutMP.getMatierePremier());
							if(!listestockfinaleElnasTeaPacking.isEmpty())
							{
								for(int t=0;t<listestockfinaleElnasTeaPacking.size();t++)
					 			{
					 				
					 			 Object[] object=listestockfinaleElnasTeaPacking.get(t);
								
					 			StockFinale= (BigDecimal)object[1];
								
					 			}
							}
							
							stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
									magasinProd.getId());
							if(stockMPMagasinProd!=null)
							{
								quantiteExistante = stockMPMagasinProd.getStock();
							}
							if(stockMP!=null)
							{
								prix_unitaire = stockMP.getPrixUnitaire();
							}
							
							quantiteACharge = quantiteTotal.subtract(quantiteExistante);

							if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteACharge = BigDecimal.ZERO;

							if ( quantiteTotal.compareTo(StockFinale) > 0 ) {
								/// 2eme etape

							 
									quantiteManqaunte = quantiteACharge.subtract(StockFinale);
								 

							 
											stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
											stockMPQauantiteManquante.setStock(quantiteManqaunte);
											//stockMPQauantiteManquante.setMagasin(stockMPElnassTeaPacking.getMagasin());
											creerOF = false;
											nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte
													+ "\n";
							 

								j++;
							} else if (StockFinale.compareTo(quantiteTotal) >= 0 ) {

								 
								coutMP.setMagasin(magasinElnasTeaPacking);
								coutMP.setTransfer(COUT_MP_TRANSFER_NON);
								listCoutMP.set(i, coutMP);
								 
							}

							if (quantiteTotal.compareTo(BigDecimal.ZERO) > 0) {
								mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
										coutMP.getMatierePremier());
								mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteTotal);

							}

						}

					}

				} else {

					if (magasinElnasTeaPacking != null) {
						stockMPElnassTeaPacking = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinElnasTeaPacking.getId());
					}

					stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
							magasinStockage.getId());
					
					StockFinale=BigDecimal.ZERO;
					// Stock Finale de ce jour
					List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateProduction, magasinStockage, coutMP.getMatierePremier());
					if(!listestockfinale.isEmpty())
					{
						for(int t=0;t<listestockfinale.size();t++)
			 			{
			 				
			 			 Object[] object=listestockfinale.get(t);
						
			 			StockFinale= (BigDecimal)object[1];
						
			 			}
					}
					
					
					
					stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
							magasinProd.getId());
					if(stockMPMagasinProd!=null)
					{
						quantiteExistante = stockMPMagasinProd.getStock();
					}
					
					if(stockMP!=null)
					{
						prix_unitaire = stockMP.getPrixUnitaire();
					}
					
					quantiteACharge = quantiteTotal.subtract(quantiteExistante);

					if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
						quantiteACharge = BigDecimal.ZERO;

					if ( quantiteTotal.compareTo(StockFinale) > 0) {
						/// 2eme etape

					 
							quantiteManqaunte = quantiteACharge.subtract(StockFinale);
						 
 
							stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
							stockMPQauantiteManquante.setStock(quantiteManqaunte);
						//	stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
							creerOF = false;
							nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
							 
					 

						j++;
					} else if (StockFinale.compareTo(quantiteTotal) >= 0) {

						 
						coutMP.setTransfer(COUT_MP_TRANSFER_NON);
						listCoutMP.set(i, coutMP);
						 
					}

					if (quantiteTotal.compareTo(BigDecimal.ZERO) > 0) {
						mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
								coutMP.getMatierePremier());
						mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteTotal);

					}

				}

			}

			 
 if(stockMP!=null)
 {
	 stockMPDAO.edit(stockMP);
 }

				
for(int i=0;i<listCoutMPTmpMPInsufisant.size();i++)
{
	listCoutMP.add(listCoutMPTmpMPInsufisant.get(i));
}
		  
		 
		return creerOF;

	}
	
	
	void calculerStockCoutProduitFini(BigDecimal coutTotal,Promotion promotion , String service, Production production , Magasin magasinSte , BigDecimal coutService) {
		Parametre PercentagePrixCadeau = parametreDAO.findByCode(Constantes.PERCENTAGE_PRIX_OFFRE);

		coutPF = BigDecimal.ZERO;
		BigDecimal nouveauCout = BigDecimal.ZERO;
		BigDecimal quantiteTotal = BigDecimal.ZERO;
		BigDecimal coutStock = BigDecimal.ZERO;
		BigDecimal NombreCarton = BigDecimal.ZERO;
		BigDecimal QuantiteEnVrac = BigDecimal.ZERO;
		BigDecimal PrixOffre = BigDecimal.ZERO;

		// coutTotal=production.getCoutTotalEmploye()+production.getCoutTotalEmployeGen()+production.getCoutTotalMP()+production.getCoutTotalEmployeEmbalage();
		 
		 
		coutPF = coutTotal.divide(listCoutMP.get(0).getProdcutionCM().getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP);
		sousfamilleTmp = null;
		// production=productionDAO.findByNumOF(txtNumOF.getText(),codeDepot);

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
				
				
				
			

			}

			if (listCoutMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)) {
				NombreCarton = NombreCarton.add(listCoutMP.get(i).getQuantConsomme());
			}

		}

		if (promotion != null) {

			 
			Magasin magasinElnasTeaPacking = null;
			magasinElnasTeaPacking = magasinSte;
			if (promotion != null) {
				for (int i = 0; i < promotion.getDetailEstimationPromo().size(); i++) {
					if (promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getCategorieMp()
							.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						QuantiteEnVrac = QuantiteEnVrac.add(promotion.getDetailEstimationPromo().get(i).getQuantite());
					}

					if (service.equals(Constantes.CODE_OUI)==true) {
						if (promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getCategorieMp()
								.getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {

							for (int k = 0; k < listCoutMP.size(); k++) {

								if (listCoutMP.get(k).getMatierePremier().getCategorieMp()
										.getSubCategorieMp().getCode()
										.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)
										&& listCoutMP.get(k).getMatierePremier().getId() == promotion
												.getDetailEstimationPromo().get(i).getMatierePremiere().getId()) {
									if (listCoutMP.get(k).getMagasin() != null) {
										if (listCoutMP.get(k).getMagasin()
												.equals(magasinElnasTeaPacking)) {

											StockMP stockMP = stockMPDAO
													.findStockByMagasinMP(
															promotion.getDetailEstimationPromo().get(i)
																	.getMatierePremiere().getId(),
															magasinElnasTeaPacking.getId());
if(stockMP!=null)
{

	PrixOffre = PrixOffre.add(stockMP.getPrixUnitaire()
			.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
			.multiply((PercentagePrixCadeau.getValeur()
					.divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE))));
}else
{

	PrixOffre = PrixOffre.add(BigDecimal.ZERO
			.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
			.multiply((PercentagePrixCadeau.getValeur()
					.divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE))));
}

										}

									}

								}

							}

						} else {
							/*
							 * StockMP
							 * stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().
							 * get(i).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
							 * PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.
							 * getDetailEstimationPromo().get(i).getQuantite()).multiply(new
							 * BigDecimal(1.2)));
							 */
						}

					} else {
						StockMP stockMP = stockMPDAO.findStockByMagasinMP(
								promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getId(),
								listCoutMP.get(0).getProdcutionCM().getMagasinStockage().getId());
						if(stockMP!=null)
						{
							PrixOffre = PrixOffre.add(stockMP.getPrixUnitaire()
									.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
									.multiply(new BigDecimal(1.2)));
						}else
						{
							PrixOffre = PrixOffre.add(BigDecimal.ZERO
									.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
									.multiply(new BigDecimal(1.2)));
						}
						
					}

				}

				PrixOffre = PrixOffre.divide(production.getArticles().getCentreCout3(), RoundingMode.HALF_UP); // centercout3
																												// est
																												// le
																												// poids
																												// de
																												// carton
			}

		}

		if (service.equals(Constantes.CODE_OUI)==true) {
			/*
			 * List <CoutMP> listeCoutMPTmp=production.getListCoutMP();
			 * 
			 * for(CoutMP coutMP : listeCoutMPTmp){
			 * 
			 * if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().
			 * equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
			 * 
			 * coutPF= coutMP.getPrixUnitaire(); break; } }
			 */

			coutPF = coutPF.add(PrixOffre).add(coutService);
		} else {
			coutPF = coutPF;
		}

		StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(production.getArticles().getId(),
				production.getMagasinPF().getId(), sousfamilleTmp.getId());

		if (stockPF != null) {
			quantiteTotal = stockPF.getStock().add(production.getQuantiteReel());
			// +cout service prod +
			coutStock = stockPF.getStock().multiply(stockPF.getPrixUnitaire());

			if (coutStock.compareTo(BigDecimal.ZERO) > 0)
				nouveauCout = ((coutPF.multiply(production.getQuantiteReel())).add(coutStock)).divide(quantiteTotal, 6,
						BigDecimal.ROUND_HALF_UP);
			else {
				nouveauCout = coutPF;
			}

			stockPF.setArticles(production.getArticles());
			stockPF.setPrixUnitaire(nouveauCout);
			if (promotion != null) {
				if (stockPF.getStockOffre() != null) {
					stockPF.setStockOffre(stockPF.getStockOffre().add(QuantiteEnVrac.multiply(NombreCarton)));
				} else {
					stockPF.setStockOffre(QuantiteEnVrac.multiply(NombreCarton));
				}

			} else {

				stockPF.setStockOffre(BigDecimal.ZERO);
			}

			stockPF.setStock(quantiteTotal.subtract(stockPF.getStockOffre()));

			
			SousFamilleArticlePF sousfamille = null;

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
								
								sousfamille=sousFamilleArticlePF.getSousfamile();
								stockPF.setSousFamille(sousfamille);
								
							}
							
							
							
						}
						
						
						
						
						
					}
					
					
					
			

				}
			}

			DetailTransferProduitFini detailTransferProduitFini = new DetailTransferProduitFini();
			TransferStockPF transferStockPF = new TransferStockPF();
			List<DetailTransferProduitFini> listeDetailTransferProduitFini = new ArrayList<DetailTransferProduitFini>();

			detailTransferProduitFini.setArticle(production.getArticles());
			detailTransferProduitFini.setSousFamille(sousfamille);
			detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
			detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
			detailTransferProduitFini.setMagasinSouce(production.getMagasinProd());
			detailTransferProduitFini.setQuantite(production.getQuantiteReel());
			detailTransferProduitFini.setPrixUnitaire(coutPF);
			detailTransferProduitFini.setTypeTransfer(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
			detailTransferProduitFini.setTransferStockPF(transferStockPF);

			listeDetailTransferProduitFini.add(detailTransferProduitFini);

			transferStockPF.setCodeTransfer(production.getNumOF());
			transferStockPF.setCreerPar(AuthentificationView.utilisateur);
			transferStockPF.setDate(new Date());
			transferStockPF.setDateTransfer(production.getDate_debFabPre());
			// transferStockPF.setListDetailTransferProduitFini(listeDetailTransferProduitFini);
			transferStockPF.setStatut(TYPE_TRANSFER_PRODUIT_FINI_ENTRE);

			transferStockPFDAO.add(transferStockPF);
			detailTransferProduitFiniDAO.add(detailTransferProduitFini);

			stockPFDAO.edit(stockPF);
			sousfamilleEnvrac = sousfamille;

		} else if (stockPF == null) {
			StockPF stockPFmp = new StockPF();

			quantiteTotal = production.getQuantiteReel();

			if (coutStock.compareTo(BigDecimal.ZERO) > 0)
				nouveauCout = (coutPF.multiply(production.getQuantiteReel())).divide(quantiteTotal, 6,
						BigDecimal.ROUND_HALF_UP);
			else {
				nouveauCout = coutPF;
			}

			stockPFmp.setArticles(production.getArticles());
			stockPFmp.setPrixUnitaire(nouveauCout);
			if (promotion != null) {

				stockPFmp.setStockOffre(QuantiteEnVrac.multiply(NombreCarton));

			} else {
				stockPFmp.setStockOffre(BigDecimal.ZERO);
			}

			stockPFmp.setStock(quantiteTotal.subtract(stockPFmp.getStockOffre()));

			stockPFmp.setMagasin(production.getMagasinPF());

			

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
								
								SousFamilleArticlePF sousfamille=sousFamilleArticlePF.getSousfamile();
								stockPFmp.setSousFamille(sousfamille);
								
							}
							
							
							
						}
						
						
					}
					
					
					
					
					
				

				}
			}

			DetailTransferProduitFini detailTransferProduitFini = new DetailTransferProduitFini();
			TransferStockPF transferStockPF = new TransferStockPF();
			List<DetailTransferProduitFini> listeDetailTransferProduitFini = new ArrayList<DetailTransferProduitFini>();

			detailTransferProduitFini.setArticle(production.getArticles());
			detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
			detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
			detailTransferProduitFini.setMagasinSouce(production.getMagasinProd());
			detailTransferProduitFini.setQuantite(production.getQuantiteReel());
			detailTransferProduitFini.setPrixUnitaire(coutPF);
			detailTransferProduitFini.setTypeTransfer(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
			detailTransferProduitFini.setTransferStockPF(transferStockPF);
			detailTransferProduitFini.setSousFamille(stockPFmp.getSousFamille());
			listeDetailTransferProduitFini.add(detailTransferProduitFini);

			transferStockPF.setCodeTransfer(production.getNumOF());
			transferStockPF.setCreerPar(AuthentificationView.utilisateur);
			transferStockPF.setDate(new Date());
			transferStockPF.setDateTransfer(production.getDate_debFabPre());
			// transferStockPF.setListDetailTransferProduitFini(listeDetailTransferProduitFini);
			transferStockPF.setStatut(TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
			transferStockPFDAO.add(transferStockPF);
			detailTransferProduitFiniDAO.add(detailTransferProduitFini);
			stockPFDAO.add(stockPFmp);
			sousfamilleEnvrac = stockPFmp.getSousFamille();
		}

	}	
	
	
	// transfert MP en Produit Fini (offre et dechet)

		void transfertMPEnProduitFini(Production production , Magasin magasinSte , BigDecimal CoutService) {
			BigDecimal CoutMPOffre = BigDecimal.ZERO;
			Magasin magasinElnasTeaPacking = null;
			Magasin magasinElnassTeaPackingPF = null;
			Magasin magasinElnassTeaPackingPFTmp = magasinSte;
			BigDecimal PrixOffre = BigDecimal.ZERO;
			BigDecimal QuantiteEnVrac = BigDecimal.ZERO;
			// calculer le cout des mp offre
			for (int j = 0; j < listCoutMP.size(); j++) {
				CoutMP coutMP = listCoutMP.get(j);
				if (!coutMP.getQuantiteOffre().setScale(6).equals(BigDecimal.ZERO.setScale(6))) {
					if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)
							&& !coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {
						if (coutMP.getMagasin() != null) {
							if (coutMP.getMagasin().getId() != magasinElnassTeaPackingPFTmp.getId()) {

								CoutMPOffre = CoutMPOffre.add(coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()));

							}

						} else {

							CoutMPOffre = CoutMPOffre.add(coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()));

						}

					}

				}

			}

			if (production.getOffre() != null) {
				Parametre PercentagePrixCadeau = parametreDAO.findByCode(Constantes.PERCENTAGE_PRIX_OFFRE);
				Promotion promotion = promotiondao.findByCode(production.getOffre());
				Magasin magasinElnasTeaPackingTmp = null;
				magasinElnasTeaPackingTmp = magasinSte;
				if (promotion != null) {
					for (int i = 0; i < promotion.getDetailEstimationPromo().size(); i++) {
						if (promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getCategorieMp()
								.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							QuantiteEnVrac = QuantiteEnVrac.add(promotion.getDetailEstimationPromo().get(i).getQuantite());
						}

						if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
							if (promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getCategorieMp()
									.getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {

								for (int k = 0; k < production.getListCoutMP().size(); k++) {

									if (production.getListCoutMP().get(k).getMatierePremier().getCategorieMp()
											.getSubCategorieMp().getCode()
											.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)
											&& production.getListCoutMP().get(k).getMatierePremier().getId() == promotion
													.getDetailEstimationPromo().get(i).getMatierePremiere().getId()) {
										if (production.getListCoutMP().get(k).getMagasin() != null) {
											if (production.getListCoutMP().get(k).getMagasin()
													.getId() == magasinElnasTeaPackingTmp.getId()) {

												StockMP stockMP = stockMPDAO
														.findStockByMagasinMP(
																promotion.getDetailEstimationPromo().get(i)
																		.getMatierePremiere().getId(),
																magasinElnasTeaPackingTmp.getId());
if(stockMP!=null)
{
	PrixOffre = PrixOffre.add(stockMP.getPrixUnitaire()
			.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
			.multiply((PercentagePrixCadeau.getValeur()
					.divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE))));
}else
{
	PrixOffre = PrixOffre.add(BigDecimal.ZERO
			.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
			.multiply((PercentagePrixCadeau.getValeur()
					.divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE))));
}
												

											}

										}

									}

								}

							} else {
								/*
								 * StockMP
								 * stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().
								 * get(i).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
								 * PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.
								 * getDetailEstimationPromo().get(i).getQuantite()).multiply(new
								 * BigDecimal(1.2)));
								 */
							}

						} else {
							StockMP stockMP = stockMPDAO.findStockByMagasinMP(
									promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getId(),
									production.getMagasinStockage().getId());
							if(stockMP!=null)
							{
								PrixOffre = PrixOffre.add(stockMP.getPrixUnitaire()
										.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
										.multiply((PercentagePrixCadeau.getValeur().divide(new BigDecimal(100), 6,
												RoundingMode.UP).add(BigDecimal.ONE))));
								
							}else
							{
								PrixOffre = PrixOffre.add(BigDecimal.ZERO
										.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
										.multiply((PercentagePrixCadeau.getValeur().divide(new BigDecimal(100), 6,
												RoundingMode.UP).add(BigDecimal.ONE))));
							}
							
						}

					}

					PrixOffre = PrixOffre.divide(production.getArticles().getCentreCout3(), RoundingMode.HALF_UP); // centercout3
																													// est
																													// le
																													// poids
																													// de
																													// carton
				}

			}

			for (int i = 0; i < listCoutMP.size(); i++) {
				CoutMP coutMP = listCoutMP.get(i);

				// Quantit� Offre
				if (!coutMP.getQuantiteOffre().setScale(6).equals(BigDecimal.ZERO.setScale(6))) {

					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)
							|| coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {

						// Inserer les Qtt offre dans la table transfert stock MP

						TransferStockMP transferStockMP = new TransferStockMP();

						transferStockMP.setCodeTransfer(Utils.genererCodeTransfer(
								AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
						transferStockMP.setCreerPar(AuthentificationView.utilisateur);
						transferStockMP.setDate(new Date());
						transferStockMP.setDateTransfer(production.getDate_debFabPre());

						transferStockMP.setDepot(production.getMagasinStockage().getDepot());

						DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();

						if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
							transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE);
							transferStockMPDAO.add(transferStockMP);
							magasinElnasTeaPacking = magasinSte;

							if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {

								detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
								if (coutMP.getMagasin() != null) {

									detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());

								} else {
									
									detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
									
								}

							}

						} else {
							transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF);
							transferStockMPDAO.add(transferStockMP);
							if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
								detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
							}
						}

						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
							detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
						}

						detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
						detailTransferStockMP.setQuantiteOffre(BigDecimal.ZERO);
						detailTransferStockMP.setQuantite(coutMP.getQuantiteOffre());
						detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
						detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
						detailTransferStockMP.setStockSource(Constantes.MP_STOCK_OFFRE);
						detailTransferStockMP.setTransferStockMP(transferStockMP);
						detailtransferMPDAO.add(detailTransferStockMP);

						// Inserer les Qtt offre dans la table transfert stock PF

						TransferStockPF transferStockPF = new TransferStockPF();
						transferStockPF.setCodeTransfer(Utils.genererCodeTransfer(
								AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
						transferStockPF.setCreerPar(AuthentificationView.utilisateur);
						transferStockPF.setDate(new Date());
						transferStockPF.setDateTransfer(production.getDate_debFabPre());
						transferStockPF.setStatut(ETAT_TRANSFER_STOCK_ENTRER_MP);
						transferStockPFDAO.add(transferStockPF);

						DetailTransferProduitFini detailTransferProduitFini = new DetailTransferProduitFini();

						if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
							magasinElnasTeaPacking = magasinSte;
							detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
							detailTransferProduitFini.setMagasinSouce(magasinElnasTeaPacking);

						} else {
							detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
							detailTransferProduitFini.setMagasinSouce(production.getMagasinStockage());
						}

						if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
							if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								BigDecimal coutEnvrac = BigDecimal.ZERO;

								coutEnvrac = (CoutMPOffre
										.add((coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()))))
												.divide(coutMP.getQuantiteOffre(), 6, RoundingMode.HALF_UP);
								 
								coutEnvrac = coutEnvrac.add(CoutService).add(PrixOffre);
								Articles article = articlesDAO
										.findByCode(coutMP.getProdcutionCM().getArticles().getCodeArticle() + CODE_PRODUIT_FINI_OFFRE);
								if (article != null) {
									detailTransferProduitFini.setArticle(article);

									StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
											production.getMagasinPF().getId(), sousfamilleEnvrac.getId());
									if (stockPF != null) {
										stockPF.setStockOffre(stockPF.getStockOffre().add(coutMP.getQuantiteOffre()));
										stockPF.setPrixUnitaire(stockPF.getPrixUnitaire().multiply(stockPF.getStockOffre())
												.add(coutEnvrac.multiply(coutMP.getQuantiteOffre()))
												.divide(coutMP.getQuantiteOffre().add(stockPF.getStockOffre()),
														RoundingMode.HALF_UP));
										stockPFDAO.edit(stockPF);
									} else {
										StockPF stockPFTmp = new StockPF();
										stockPFTmp.setArticles(article);
										stockPFTmp.setMagasin(production.getMagasinPF());
										stockPFTmp.setPrixUnitaire(coutEnvrac);
										stockPFTmp.setSousFamille(sousfamilleEnvrac);
										stockPFTmp.setStock(BigDecimal.ZERO);
										stockPFTmp.setStockOffre(coutMP.getQuantiteOffre());
										stockPFDAO.add(stockPFTmp);
									}

								} else {
									Articles articleTmp = new Articles();
									articleTmp.setCodeArticle(
											coutMP.getProdcutionCM().getArticles().getCodeArticle() + CODE_PRODUIT_FINI_OFFRE);
									articleTmp.setConditionnement(
											coutMP.getProdcutionCM().getArticles().getConditionnement());
									articleTmp.setLiblle(coutMP.getProdcutionCM().getArticles().getLiblle() + " (OFFRE)");
									articleTmp.setCentreCout1(coutMP.getProdcutionCM().getArticles().getCentreCout1());
									articleTmp.setCentreCout3(coutMP.getProdcutionCM().getArticles().getCentreCout3());
									articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
									articleTmp.setDateCreation(new Date());
									articlesDAO.add(articleTmp);
									detailTransferProduitFini.setArticle(articleTmp);

									StockPF stcokPfTmp = new StockPF();
									stcokPfTmp.setArticles(articleTmp);
									stcokPfTmp.setSousFamille(sousfamilleEnvrac);
									stcokPfTmp.setMagasin(production.getMagasinPF());
									stcokPfTmp.setPrixUnitaire(coutEnvrac);
									stcokPfTmp.setStock(BigDecimal.ZERO);
									stcokPfTmp.setStockOffre(coutMP.getQuantiteOffre());
									stockPFDAO.add(stcokPfTmp);
								}

								detailTransferProduitFini.setSousFamille(sousfamilleEnvrac);
								detailTransferProduitFini.setPrixUnitaire(coutEnvrac);

								detailTransferProduitFini.setQuantite(coutMP.getQuantiteOffre());

								detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
								detailTransferProduitFini.setTransferStockPF(transferStockPF);
								detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
								detailTransferProduitFiniDAO.add(detailTransferProduitFini);

							} else {

								if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
										.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {

									if (coutMP.getMagasin() != null) {

										if (!coutMP.getMagasin().getLibelle()
												.equals(magasinSte.getLibelle())) {

											SousFamilleArticlePF sousfamille = sousFamilleArticleDAo
													.findByCode(coutMP.getMatierePremier().getCode() + "_O");
											Articles article = articlesDAO
													.findByCode(coutMP.getMatierePremier().getCode() + CODE_PRODUIT_FINI_OFFRE);
											if (article != null) {
												detailTransferProduitFini.setArticle(article);

												StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(
														article.getId(), production.getMagasinPF().getId(),
														sousfamille.getId());
												if (stockPF != null) {
													stockPF.setStockOffre(
															stockPF.getStockOffre().add(coutMP.getQuantiteOffre()));
													stockPFDAO.edit(stockPF);
												} else {
													StockPF stockPFTmp = new StockPF();
													stockPFTmp.setArticles(article);
													stockPFTmp.setMagasin(production.getMagasinPF());
													stockPFTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
													stockPFTmp.setSousFamille(sousfamille);
													stockPFTmp.setStock(BigDecimal.ZERO);
													stockPFTmp.setStockOffre(coutMP.getQuantiteOffre());
													stockPFDAO.add(stockPFTmp);
												}

											} else {

												Articles articleTmp = new Articles();
												articleTmp.setCodeArticle(coutMP.getMatierePremier().getCode() + CODE_PRODUIT_FINI_OFFRE);
												articleTmp.setConditionnement(BigDecimal.ONE);
												articleTmp.setLiblle(coutMP.getMatierePremier().getNom() + " (OFFRE)");
												
												articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
												articleTmp.setDateCreation(new Date());
												articlesDAO.add(articleTmp);
												detailTransferProduitFini.setArticle(articleTmp);

												StockPF stcokPfTmp = new StockPF();
												stcokPfTmp.setArticles(articleTmp);
												stcokPfTmp.setSousFamille(sousfamille);
												stcokPfTmp.setMagasin(production.getMagasinPF());
												stcokPfTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
												stcokPfTmp.setStock(BigDecimal.ZERO);
												stcokPfTmp.setStockOffre(coutMP.getQuantiteOffre());
												stockPFDAO.add(stcokPfTmp);
											}

											detailTransferProduitFini.setSousFamille(sousfamille);
											detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
											detailTransferProduitFini.setQuantite(coutMP.getQuantiteOffre());
											detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
											detailTransferProduitFini.setTransferStockPF(transferStockPF);
											detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
											detailTransferProduitFiniDAO.add(detailTransferProduitFini);

										}

									}

								}

							}
						} else {
							if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								BigDecimal coutEnvrac = BigDecimal.ZERO;
								coutEnvrac = (CoutMPOffre
										.add((coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()))))
												.divide(coutMP.getQuantiteOffre(), 6, RoundingMode.HALF_UP);

								Articles article = articlesDAO
										.findByCode(coutMP.getProdcutionCM().getArticles().getCodeArticle() + CODE_PRODUIT_FINI_OFFRE);
								if (article != null) {
									detailTransferProduitFini.setArticle(article);

									StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
											production.getMagasinPF().getId(), sousfamilleEnvrac.getId());
									if (stockPF != null) {
										stockPF.setStockOffre(stockPF.getStockOffre().add(coutMP.getQuantiteOffre()));
										stockPF.setPrixUnitaire(stockPF.getPrixUnitaire().multiply(stockPF.getStockOffre())
												.add(coutEnvrac.multiply(coutMP.getQuantiteOffre()))
												.divide(coutMP.getQuantiteOffre().add(stockPF.getStockOffre()),
														RoundingMode.HALF_UP));
										stockPFDAO.edit(stockPF);
									} else {
										StockPF stockPFTmp = new StockPF();
										stockPFTmp.setArticles(article);
										stockPFTmp.setMagasin(production.getMagasinPF());
										stockPFTmp.setPrixUnitaire(coutEnvrac);
										stockPFTmp.setSousFamille(sousfamilleEnvrac);
										stockPFTmp.setStock(BigDecimal.ZERO);
										stockPFTmp.setStockOffre(coutMP.getQuantiteOffre());
										stockPFDAO.add(stockPFTmp);
									}

								} else {
									Articles articleTmp = new Articles();
									articleTmp.setCodeArticle(
											coutMP.getProdcutionCM().getArticles().getCodeArticle() + CODE_PRODUIT_FINI_OFFRE);
									articleTmp.setConditionnement(
											coutMP.getProdcutionCM().getArticles().getConditionnement());
									articleTmp.setLiblle(coutMP.getProdcutionCM().getArticles().getLiblle() + " (OFFRE)");
									articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
									articleTmp.setCentreCout1(coutMP.getProdcutionCM().getArticles().getCentreCout1());
									articleTmp.setCentreCout3(coutMP.getProdcutionCM().getArticles().getCentreCout3());
									articleTmp.setDateCreation(new Date());
									articlesDAO.add(articleTmp);
									detailTransferProduitFini.setArticle(articleTmp);

									StockPF stcokPfTmp = new StockPF();
									stcokPfTmp.setArticles(articleTmp);
									stcokPfTmp.setSousFamille(sousfamilleEnvrac);
									stcokPfTmp.setMagasin(production.getMagasinPF());
									stcokPfTmp.setPrixUnitaire(coutEnvrac);
									stcokPfTmp.setStock(BigDecimal.ZERO);
									stcokPfTmp.setStockOffre(coutMP.getQuantiteOffre());
									stockPFDAO.add(stcokPfTmp);
								}

								detailTransferProduitFini.setSousFamille(sousfamilleEnvrac);
								detailTransferProduitFini.setPrixUnitaire(coutEnvrac);

								detailTransferProduitFini.setQuantite(coutMP.getQuantiteOffre());

								detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
								detailTransferProduitFini.setTransferStockPF(transferStockPF);
								detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
								detailTransferProduitFiniDAO.add(detailTransferProduitFini);

							} else {
								SousFamilleArticlePF sousfamille = sousFamilleArticleDAo
										.findByCode(coutMP.getMatierePremier().getCode() + "_O");
								Articles article = articlesDAO.findByCode(coutMP.getMatierePremier().getCode() + CODE_PRODUIT_FINI_OFFRE);
								if (article != null) {
									detailTransferProduitFini.setArticle(article);

									StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
											production.getMagasinPF().getId(), sousfamille.getId());
									if (stockPF != null) {
										stockPF.setStockOffre(stockPF.getStockOffre().add(coutMP.getQuantiteOffre()));
										stockPFDAO.edit(stockPF);
									} else {
										StockPF stockPFTmp = new StockPF();
										stockPFTmp.setArticles(article);
										stockPFTmp.setMagasin(production.getMagasinPF());
										stockPFTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
										stockPFTmp.setSousFamille(sousfamille);
										stockPFTmp.setStock(BigDecimal.ZERO);
										stockPFTmp.setStockOffre(coutMP.getQuantiteOffre());
										stockPFDAO.add(stockPFTmp);
									}

								} else {

									Articles articleTmp = new Articles();
									articleTmp.setCodeArticle(coutMP.getMatierePremier().getCode() + CODE_PRODUIT_FINI_OFFRE);
									articleTmp.setConditionnement(BigDecimal.ONE);
									articleTmp.setLiblle(coutMP.getMatierePremier().getNom() + " (OFFRE)");
									articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
									articleTmp.setDateCreation(new Date());
									articlesDAO.add(articleTmp);
									detailTransferProduitFini.setArticle(articleTmp);

									StockPF stcokPfTmp = new StockPF();
									stcokPfTmp.setArticles(articleTmp);
									stcokPfTmp.setSousFamille(sousfamille);
									stcokPfTmp.setMagasin(production.getMagasinPF());
									stcokPfTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
									stcokPfTmp.setStock(BigDecimal.ZERO);
									stcokPfTmp.setStockOffre(coutMP.getQuantiteOffre());
									stockPFDAO.add(stcokPfTmp);
								}

								detailTransferProduitFini.setSousFamille(sousfamille);
								detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());

								detailTransferProduitFini.setQuantite(coutMP.getQuantiteOffre());

								detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
								detailTransferProduitFini.setTransferStockPF(transferStockPF);
								detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
								detailTransferProduitFiniDAO.add(detailTransferProduitFini);
							}
						}

					} else {

						// Inserer les Qtt offre dans la table transfert stock MP

						TransferStockMP transferStockMP = new TransferStockMP();

						transferStockMP.setCodeTransfer(Utils.genererCodeTransfer(
								AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
						transferStockMP.setCreerPar(AuthentificationView.utilisateur);
						transferStockMP.setDate(new Date());
						transferStockMP.setDateTransfer(production.getDate_debFabPre());

						transferStockMP.setDepot(production.getMagasinStockage().getDepot());

						DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();

						if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
							transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE);
							transferStockMPDAO.add(transferStockMP);
							magasinElnasTeaPacking = magasinSte;

							if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
								if (coutMP.getMagasin() != null) {
									detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());
								} else {
									detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
								}

							}

						} else {
							transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF);
							transferStockMPDAO.add(transferStockMP);
							if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
								detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
							}

						}

						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
							detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
						}

						detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
						detailTransferStockMP.setQuantiteOffre(BigDecimal.ZERO);
						detailTransferStockMP.setQuantite(coutMP.getQuantiteOffre());
						detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
						detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
						detailTransferStockMP.setStockSource(Constantes.MP_STOCK_OFFRE);
						detailTransferStockMP.setTransferStockMP(transferStockMP);
						detailtransferMPDAO.add(detailTransferStockMP);

					}

				}

				// Quantite Dechet
				if (!coutMP.getQuantDechet().setScale(6).equals(BigDecimal.ZERO.setScale(6))) {

					/* Inserer les Qtt dechet dans la table transfert stock MP */
					SousFamilleArticlePF sousFamilleArticlePF = null;
					sousFamilleArticlePF = sousFamilleArticleDAo.findByCode(coutMP.getMatierePremier().getCode());
					TransferStockMP transferStockMP = new TransferStockMP();

					transferStockMP.setCodeTransfer(Utils.genererCodeTransfer(
							AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
					transferStockMP.setCreerPar(AuthentificationView.utilisateur);
					transferStockMP.setDate(new Date());
					transferStockMP.setDateTransfer(production.getDate_debFabPre());

					transferStockMP.setDepot(production.getMagasinStockage().getDepot());

					if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						sousFamilleArticlePF = sousFamilleArticleDAo.findByCode(coutMP.getMatierePremier().getCode());
					}

					DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();

					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
						transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE);
						transferStockMPDAO.add(transferStockMP);
						magasinElnasTeaPacking = magasinSte;
						magasinElnassTeaPackingPF = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING_PF);
						if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							if (coutMP.getMagasin() != null) {
								detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());
							} else {
								detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
							}
							detailTransferStockMP.setMagasinDestination(magasinElnassTeaPackingPF);

						}

					} else {
						transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF);
						transferStockMPDAO.add(transferStockMP);
						if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
							detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
						}
					}

					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
						detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
					}

					detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
					detailTransferStockMP.setQuantiteOffre(BigDecimal.ZERO);
					detailTransferStockMP.setQuantite(coutMP.getQuantDechet());
					detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
					detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
					detailTransferStockMP.setStockSource(Constantes.MP_STOCK_DECHET);
					detailTransferStockMP.setTransferStockMP(transferStockMP);
					detailtransferMPDAO.add(detailTransferStockMP);

					/*
					 * if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().
					 * equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					 */

					/* Inserer les Qtt dechet dans la table transfert stock PF */

					TransferStockPF transferStockPF = new TransferStockPF();
					transferStockPF.setCodeTransfer(Utils.genererCodeTransfer(
							AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
					transferStockPF.setCreerPar(AuthentificationView.utilisateur);
					transferStockPF.setDate(new Date());
					transferStockPF.setDateTransfer(production.getDate_debFabPre());
					transferStockPF.setStatut(ETAT_TRANSFER_STOCK_ENTRER_MP);
					transferStockPFDAO.add(transferStockPF);

					DetailTransferProduitFini detailTransferProduitFini = new DetailTransferProduitFini();
					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
						/*
						 * if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().
						 * equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						 * detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
						 * detailTransferProduitFini.setMagasinSouce(production.getMagasinStockage());
						 * 
						 * }else {
						 * detailTransferProduitFini.setMagasinDestination(magasinElnassTeaPackingPF);
						 * detailTransferProduitFini.setMagasinSouce(magasinElnasTeaPacking); }
						 */
						if (coutMP.getMagasin() != null) {

							detailTransferProduitFini.setMagasinSouce(coutMP.getMagasin());

							if (coutMP.getMagasin().getId() == magasinElnasTeaPacking.getId()) {
								detailTransferProduitFini.setMagasinDestination(magasinElnassTeaPackingPF);
							} else {
								detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
							}

						} else {
							detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
							detailTransferProduitFini.setMagasinSouce(production.getMagasinStockage());

						}

					} else {
						detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
						detailTransferProduitFini.setMagasinSouce(production.getMagasinStockage());
					}

					Articles article = articlesDAO.findByCode(coutMP.getMatierePremier().getCode() + "_PFD");
					if (article != null) {
						detailTransferProduitFini.setArticle(article);
						StockPF stockPF = null;
						if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {

							if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
										production.getMagasinPF().getId(), sousfamilleEnvrac.getId());

							} else {
								if (coutMP.getMagasin() != null) {
									if (coutMP.getMagasin().getId() == magasinElnasTeaPacking.getId()) {
										stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
												magasinElnassTeaPackingPF.getId(), sousFamilleArticlePF.getId());

									} else {
										stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
												production.getMagasinPF().getId(), sousfamilleEnvrac.getId());

									}

								} else {

									stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
											magasinElnassTeaPackingPF.getId(), sousFamilleArticlePF.getId());

								}

							}

						} else {
							if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
										production.getMagasinPF().getId(), sousfamilleEnvrac.getId());
							} else {
								stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
										production.getMagasinPF().getId(), sousFamilleArticlePF.getId());

							}

						}

						if (stockPF != null) {
							stockPF.setStock(stockPF.getStock().add(coutMP.getQuantDechet()));
							stockPFDAO.edit(stockPF);
						} else {
							StockPF stockPFTmp = new StockPF();
							stockPFTmp.setArticles(article);
							if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
								if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
										.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {

									stockPFTmp.setMagasin(production.getMagasinPF());

								} else

								{
									if (coutMP.getMagasin() != null) {

										if (coutMP.getMagasin().getId() == magasinElnasTeaPacking.getId()) {
											stockPFTmp.setMagasin(magasinElnassTeaPackingPF);

										} else {

											stockPFTmp.setMagasin(production.getMagasinPF());
										}

									} else {
										stockPFTmp.setMagasin(magasinElnassTeaPackingPF);
									}

								}

							} else {
								stockPFTmp.setMagasin(production.getMagasinPF());
							}

							stockPFTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
							if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								stockPFTmp.setSousFamille(sousfamilleEnvrac);
							} else {
								stockPFTmp.setSousFamille(sousFamilleArticlePF);
							}

							stockPFTmp.setStockOffre(BigDecimal.ZERO);
							stockPFTmp.setStock(coutMP.getQuantDechet());
							stockPFDAO.add(stockPFTmp);
						}

					} else {
						Articles articleTmp = new Articles();
						articleTmp.setCodeArticle(coutMP.getMatierePremier().getCode() + "_PFD");
						articleTmp.setConditionnement(coutMP.getProdcutionCM().getArticles().getConditionnement());
						articleTmp.setLiblle(coutMP.getMatierePremier().getNom() + " (DECHET)");
						articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
						articleTmp.setDateCreation(new Date());
						articlesDAO.add(articleTmp);
						detailTransferProduitFini.setArticle(articleTmp);

						StockPF stcokPfTmp = new StockPF();
						stcokPfTmp.setArticles(articleTmp);
						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							stcokPfTmp.setSousFamille(sousfamilleEnvrac);
						} else {
							stcokPfTmp.setSousFamille(sousFamilleArticlePF);
						}

						if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {

							if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
								stcokPfTmp.setMagasin(production.getMagasinPF());
							} else {

								if (coutMP.getMagasin() != null) {

									if (coutMP.getMagasin().getId() == magasinElnasTeaPacking.getId()) {

										stcokPfTmp.setMagasin(magasinElnassTeaPackingPF);

									} else {

										stcokPfTmp.setMagasin(production.getMagasinPF());

									}

								} else {
									stcokPfTmp.setMagasin(magasinElnassTeaPackingPF);

								}

							}

						} else {
							stcokPfTmp.setMagasin(production.getMagasinPF());
						}
						stcokPfTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
						stcokPfTmp.setStock(coutMP.getQuantDechet());
						stcokPfTmp.setStockOffre(BigDecimal.ZERO);
						stockPFDAO.add(stcokPfTmp);
					}

					detailTransferProduitFini.setQuantite(coutMP.getQuantDechet());
					detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						detailTransferProduitFini.setSousFamille(sousfamilleEnvrac);
					} else {
						detailTransferProduitFini.setSousFamille(sousFamilleArticlePF);

					}

					detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
					detailTransferProduitFini.setTransferStockPF(transferStockPF);
					detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
					detailTransferProduitFiniDAO.add(detailTransferProduitFini);



				}

			}

		}
}
