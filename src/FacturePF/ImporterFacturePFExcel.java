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
import sun.invoke.empty.Empty;

import org.apache.poi.ss.usermodel.CellType;
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
import dao.daoImplManager.ArticlesClientDAOImpl;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailArticlesClientDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
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
import dao.daoImplManager.TypeBLDAOImpl;
import dao.daoManager.ArticlesClientDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailArticlesClientDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
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
import dao.daoManager.TypeBLDAO;
import dao.entity.*;


public class ImporterFacturePFExcel extends JLayeredPane implements Constantes{
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
    private static XSSFCell CellNumBL;
    private static XSSFCell CellTypeBL;
    private static XSSFCell CellFournisseur;
    private static XSSFCell CellClient;
    private static XSSFCell CellMagasin;
    private static XSSFCell CellDate;
    private static XSSFCell CellMontant;
    private static XSSFCell CellArticlet;
    private static XSSFCell CellSousFamille;
    private static XSSFCell CellQuantite;
    private static XSSFCell CellPrix;
    private static XSSFCell CellReduction;
    private static XSSFCell CellMontantHT;
    private static XSSFCell CellMontantTVA;
    private static XSSFCell CellMontantTTC;
    private static XSSFCell CellQuantiteInitial;
    
     
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
	 private ClientPFDAO clientPFDAO;
	 private FactureServiceProductionDAO factureserviceproductionDAO;
		private DetailFactureServiceProductionDAO detailfactureserviceproductionDAO;
		private   SequenceurDAO sequenceurDAO;
    DetailEstimationDAO detailEstimationDAO;
    
    DetailArticlesClientDAO detailArticlesClientDAO;
    ArticlesClientDAO articlesClientDAO;
    private	List<DetailArticlesClient> listDetailArticleClient= new ArrayList<DetailArticlesClient>();
    private	List<DetailTransferProduitFini> listDetailArticleGroupBySousFamille= new ArrayList<DetailTransferProduitFini>();
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
	private DetailPrixArticleDAO detailPrixArticleDAO;
	private ArticlesDAO articleDAO;
	private List<Articles> listArticleOffre =new ArrayList<Articles>();
	private List<SousFamilleArticlePF> listSousFamilleArticleOffre =new ArrayList<SousFamilleArticlePF>();
	private Map< String, Articles> mapArticleOffre = new HashMap<>();
	private Map< String, Articles> mapArticlePromo = new HashMap<>();
	private Map< String, Articles> mapArticleOffreCode = new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamilleOffre= new HashMap<>();
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	private ClientDAO fournisseurdao;
	private TypeBLDAO typeBLDAO;
	private FacturePFDAO facturepfdao;
	private DetailFacturePFDAO detailFacturePfdao;
	private DetailCompteClientDAO detailCompteClientdao;
	private CompteClientDAO compteclientdao;
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private	List<listeCordonneFacturePourImportationExcel> listCodonneFacturePFExcel= new ArrayList<listeCordonneFacturePourImportationExcel>();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ImporterFacturePFExcel() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
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
        	clientPFDAO=new ClientPFDAOImpl();
			detailtransferMPDAO = new DetailTransferMPDAOImpl();
        	transferStockPFDAO = new TransferStockPFDAOImpl();
			detailTransferProduitFiniDAO = new DetailTransferProduitFiniDAOImpl();
			factureserviceproductionDAO=new FactureServiceProductionDAOImpl();
         	detailfactureserviceproductionDAO=new DetailFactureServiceProductionDAOImpl();
         	sequenceurDAO=new SequenceurDAOImpl();
        	sousFamilleArticleDAo=new SousFamilleArticlesPFDAOImpl();
         	listSousFamille=sousFamilleEnvracDAo.findAll();
         	detailEstimationDAO=new DetailEstimationDAOImpl();
         	 detailArticlesClientDAO=new DetailArticlesClientDAOImpl();
         	 articlesClientDAO=new ArticlesClientDAOImpl();
         	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
        	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
        	fournisseurdao=new ClientDAOImpl();
        	typeBLDAO=new TypeBLDAOImpl();
        	facturepfdao=new FacturePFDAOImpl();
        	detailFacturePfdao=new DetailFacturePFDAOImpl();
        	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
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
        txtlien.setBounds(263, 200, 459, 36);
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
        button_1.setBounds(732, 200, 89, 30);
        add(button_1);
	  		  
				  
        JButton button = new JButton("Lire Fichier excel");
        button.addActionListener(new ActionListener() {
        	@SuppressWarnings("unlikely-arg-type")
			public void actionPerformed(ActionEvent e) {
        		
        		 
        		
        		if(!txtlien.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlien.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  				      
	  				      
	  				   
	  				      
	  				      
	  				      
	  				    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");				    
	  				      
int t=0;							  				      
	  				      
int id=0;		

SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
   						
	  		
for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	 
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellCodeClient=ExcelWSheet.getRow(i).getCell(14);
	CellDate=ExcelWSheet.getRow(i).getCell(12);
	CellMagasin=ExcelWSheet.getRow(i).getCell(17);	 
	CellNumBL=ExcelWSheet.getRow(i).getCell(1);
	CellTypeBL=ExcelWSheet.getRow(i).getCell(0);
	CellFournisseur=ExcelWSheet.getRow(i).getCell(18);
	ClientPF clientPF=clientPFDAO.findClientByCodeClient(CellCodeClient.getStringCellValue())	;
	Client fournisseur=fournisseurdao.findClientByCodeClient(CellFournisseur.getStringCellValue());
	Magasin magasin=depotDAO.MagasinByLibelle(CellMagasin.getStringCellValue())	;
	
	
	String date="01/01/"+sdf.format(CellDate.getDateCellValue())+"";
		String dateFin="31/12/"+sdf.format(CellDate.getDateCellValue())+"";
		Date dateDebut= new Date(date);	
	
	 

	 
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellCodeClient);
	String formattedNumBL = dataFormatter.formatCellValue(CellNumBL);
	String formattedTypeBL = dataFormatter.formatCellValue(CellTypeBL);
	 
	boolean trouver=false;
 if(formattedNumBL!=null && formattedNumBL.isEmpty()==false && !formattedNumBL.equals(""))
 {
	 
  for(int f=0;f<listCodonneFacturePFExcel.size();f++)
  {
	  
	  if(listCodonneFacturePFExcel.get(f).getNumBL().equals(formattedNumBL))
	  {
		  trouver=true;
	  }
	  
  }

	 if(trouver==false)
	 {
		 
		listeCordonneFacturePourImportationExcel listeCordonneFacturePourImportationExcel=new listeCordonneFacturePourImportationExcel();
		
		listeCordonneFacturePourImportationExcel.setClientPF(clientPF);
		listeCordonneFacturePourImportationExcel.setDateFacture(CellDate.getDateCellValue());
		listeCordonneFacturePourImportationExcel.setDepot(magasin.getDepot());
		listeCordonneFacturePourImportationExcel.setMagasin(magasin);
		listeCordonneFacturePourImportationExcel.setNumBL(formattedNumBL);
		listeCordonneFacturePourImportationExcel.setTypeBL(formattedTypeBL);
		listeCordonneFacturePourImportationExcel.setFournisseur(fournisseur);
		listCodonneFacturePFExcel.add(listeCordonneFacturePourImportationExcel);
		 
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




for(int u=0;u<listCodonneFacturePFExcel.size();u++)
{
	listDetailFacturePF.clear();
	listDetailTransferProduitFini.clear();
	
	String codeTransfert=Utils.genererCodeTransfer(listCodonneFacturePFExcel.get(u).getDepot().getCode(),ETAT_TRANSFER_STOCK_VENTE);
	TypeBL typeBL	=typeBLDAO.findTypeBLByType(listCodonneFacturePFExcel.get(u).getTypeBL());
	FacturePF facturePF=new FacturePF();
	facturePF.setNumBl(listCodonneFacturePFExcel.get(u).getNumBL());
	facturePF.setClientPF(listCodonneFacturePFExcel.get(u).getClientPF());
	facturePF.setFournisseur(listCodonneFacturePFExcel.get(u).getFournisseur());
	facturePF.setDepot(listCodonneFacturePFExcel.get(u).getDepot());
	facturePF.setMagasin(listCodonneFacturePFExcel.get(u).getMagasin());
	facturePF.setDateBl(listCodonneFacturePFExcel.get(u).getDateFacture());
	facturePF.setDateFacture(listCodonneFacturePFExcel.get(u).getDateFacture());
	facturePF.setEtat(Constantes.ETAT_NON_REGLE);
	facturePF.setType(Constantes.TYPE_BON_LIVRAISON);
	
	
		
String msg="";

for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{



try {


Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();

CellNumBL=ExcelWSheet.getRow(i).getCell(1);
CellSousFamille=ExcelWSheet.getRow(i).getCell(3);
CellArticlet=ExcelWSheet.getRow(i).getCell(4);
CellPrix=ExcelWSheet.getRow(i).getCell(6);
CellQuantite=ExcelWSheet.getRow(i).getCell(7);
CellReduction=ExcelWSheet.getRow(i).getCell(8);
CellMontantHT=ExcelWSheet.getRow(i).getCell(9);
CellMontantTVA=ExcelWSheet.getRow(i).getCell(10);
CellMontantTTC=ExcelWSheet.getRow(i).getCell(11);
 


String date="01/01/"+sdf.format(CellDate.getDateCellValue())+"";
String dateFin="31/12/"+sdf.format(CellDate.getDateCellValue())+"";
Date dateDebut= new Date(date);	

Articles article=articleDAO.findByCode(CellArticlet.getStringCellValue());
SousFamilleArticlePF sousFamilleArticlePF=sousFamilleArticleDAO.findByLibelle(CellSousFamille.getStringCellValue());


DataFormatter dataFormatter = new DataFormatter();
String formattedCellStr = dataFormatter.formatCellValue(CellNumBL);
String formattedCellReduction = dataFormatter.formatCellValue(CellReduction);
 

System.out.println(CellNumBL.getCellType());
System.out.println(CellMontantHT.getCellType());
System.out.println(CellQuantite.getCellType());
System.out.println(CellPrix.getCellType());
 

if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
{

 if(formattedCellStr.equals(listCodonneFacturePFExcel.get(u).getNumBL()))
 {
	
	 DetailFacturePF detailFacturePF=new DetailFacturePF();
	 detailFacturePF.setArticle(article);
	 detailFacturePF.setSousFamille(sousFamilleArticlePF);
	 detailFacturePF.setMontantHT(new BigDecimal(CellMontantHT.getNumericCellValue()));
	 detailFacturePF.setMontantTVA(new BigDecimal(CellMontantTVA.getNumericCellValue()));
	 detailFacturePF.setMontantTTC(new BigDecimal(CellMontantTTC.getNumericCellValue()));
	 detailFacturePF.setPrixUnitaire(new BigDecimal(CellPrix.getNumericCellValue()));
	 detailFacturePF.setQuantite(new BigDecimal(CellQuantite.getNumericCellValue()));
	 if(formattedCellReduction!=null && formattedCellReduction.isEmpty()==false && !formattedCellReduction.equals(""))
	 {
		 detailFacturePF.setReduction(new BigDecimal(CellReduction.getNumericCellValue()));
		 
	 }else
	 {
		 detailFacturePF.setReduction(BigDecimal.ZERO);
	 }
	
	 
	
 
	 
	 if(detailFacturePF.getReduction().compareTo(new BigDecimal(100))!=0)
	 {
		 detailFacturePF.setBrutHT(new BigDecimal(CellQuantite.getNumericCellValue()).multiply(new BigDecimal(CellPrix.getNumericCellValue())));
		 detailFacturePF.setRemiseCommerciale(BigDecimal.ZERO); 
		 
		 detailFacturePF.setTva(new BigDecimal(20));
	 }else
	 {
		 detailFacturePF.setTva(BigDecimal.ZERO); 
		 detailFacturePF.setBrutHT(BigDecimal.ZERO);
		 detailFacturePF.setRemiseCommerciale(BigDecimal.ZERO); 
	 }
	listDetailFacturePF.add(detailFacturePF);
	
	
	DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
		detailTransferStockPF.setArticle(article);
		detailTransferStockPF.setSousFamille(sousFamilleArticlePF);
	detailTransferStockPF.setDateTransfer(listCodonneFacturePFExcel.get(u).getDateFacture());
	detailTransferStockPF.setMagasinDestination(listCodonneFacturePFExcel.get(u).getMagasin());
	if(detailFacturePF.getReduction().compareTo(new BigDecimal(100))!=0)
	 {
		 detailTransferStockPF.setPrixUnitaire(new BigDecimal(CellPrix.getNumericCellValue()));
	 }else
	 {
		 detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
	 }
	
	detailTransferStockPF.setQuantite(new BigDecimal(CellQuantite.getNumericCellValue()));
	detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
	listDetailTransferProduitFini.add(detailTransferStockPF);
	
	
	
	 
 }
 

}

 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;







} catch (Exception ex) {
//TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 
return;
}









}

BigDecimal montantht=BigDecimal.ZERO;
BigDecimal montanttva=BigDecimal.ZERO;
BigDecimal montantttc=BigDecimal.ZERO;
BigDecimal solde=BigDecimal.ZERO;

for(int y=0;y<listDetailFacturePF.size();y++)
{
	montantht=montantht.add(listDetailFacturePF.get(y).getMontantHT());
	montanttva=montanttva.add(listDetailFacturePF.get(y).getMontantTVA());
	montantttc=montantttc.add(listDetailFacturePF.get(y).getMontantTTC());
}


facturePF.setMontantHT(montantht);
facturePF.setMontantTVA(montanttva);
facturePF.setMontantTTC(montantttc);	
facturePF.setCodeTransfer(codeTransfert);
facturePF.setDateSaisi(new Date());
facturePF.setEspece(BigDecimal.ZERO);
facturePF.setCheque(BigDecimal.ZERO);
facturePF.setVirement(BigDecimal.ZERO);
facturePF.setTraite(BigDecimal.ZERO);
facturePF.setCredit(BigDecimal.ZERO);
if(typeBL!=null)
{
facturePF.setTypeBL(typeBL);
}

facturePF.setCreerPar(utilisateur);
facturepfdao.add(facturePF);

for(int y=0;y<listDetailFacturePF.size();y++)
{
	listDetailFacturePF.get(y).setFacturePF(facturePF);
	 detailFacturePfdao.add(listDetailFacturePF.get(y));
}


DetailCompteClient detailcompteclient=new DetailCompteClient();
detailcompteclient.setCompteClient(listCodonneFacturePFExcel.get(u).getClientPF().getCompteClient());
detailcompteclient.setUtilisateurCreation(utilisateur);
detailcompteclient.setDateCreation(new Date());
detailcompteclient.setDateOperation(CellDate.getDateCellValue());
detailcompteclient.setMontantDebit(montantttc);
detailcompteclient.setMontantCredit(BigDecimal.ZERO);
detailcompteclient.setDesignation("Montant sur Facture N "+listCodonneFacturePFExcel.get(u).getNumBL());
detailcompteclient.setFacturepf(facturePF);
detailcompteclient.setFournisseur(listCodonneFacturePFExcel.get(u).getFournisseur());
detailCompteClientdao.add(detailcompteclient);
solde=listCodonneFacturePFExcel.get(u).getClientPF().getCompteClient().getSolde().add(montantttc);
listCodonneFacturePFExcel.get(u).getClientPF().getCompteClient().setSolde(solde);
compteclientdao.edit(listCodonneFacturePFExcel.get(u).getClientPF().getCompteClient());

TransferStockPF  transferStock=new TransferStockPF();
transferStock.setCodeTransfer(codeTransfert);
transferStock.setCreerPar(utilisateur);
transferStock.setDate(new Date());
transferStock.setDateTransfer(CellDate.getDateCellValue());
transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
transferStock.setStatut(ETAT_TRANSFER_STOCK_VENTE);
 
transferStockPFDAO.add(transferStock);

 

 for(int j=0;j<listDetailTransferProduitFini.size();j++)
 {
	 listDetailTransferProduitFini.get(j).setTransferStockPF(transferStock);
	 detailTransferStockPFdao.add(listDetailTransferProduitFini.get(j));
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
        button.setBounds(84, 200, 129, 36);
        add(button);			        
        
        JButton btnImporterStock = new JButton("Importer Stock");
        btnImporterStock.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
 
        		 
        		
        		if(!txtlien.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlien.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				       
	  			
	  				       
	  				      
	  				       
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  				      
	  				      
	  				   
	  				      
	  				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		

SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
   						
	  							
	  							
String msg="";
String date="01/01/2023";
 
Date dateDebut= new Date(date);	
TransferStockPF transferStockPF=new TransferStockPF();
transferStockPF.setDate(new Date());
transferStockPF.setDateTransfer(dateDebut);
transferStockPF.setStatut(ETAT_TRANSFER_STOCK_INITIAL);
transferStockPF.setCreerPar(utilisateur);
transferStockPF.setCodeTransfer("INITIAL_AGA_280323_24243_1");
transferStockPFDAO.add(transferStockPF);

for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	 
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellArticlet=ExcelWSheet.getRow(i).getCell(0);
	CellSousFamille=ExcelWSheet.getRow(i).getCell(2);
	CellMagasin=ExcelWSheet.getRow(i).getCell(4);
	CellQuantiteInitial=ExcelWSheet.getRow(i).getCell(3);
	Magasin magasin=depotDAO.MagasinByLibelle(CellMagasin.getStringCellValue())	;
	
	
	 	
	 

	 
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellArticlet);
	 
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	 
	if( magasin!=null)
	{
		
		Articles article= articleDAO.findBylibelle(CellArticlet.getStringCellValue());
		SousFamilleArticlePF sousFamilleArticlePF=sousFamilleArticleDAo.findByLibelle(CellSousFamille.getStringCellValue());
		
	 
	 DetailTransferProduitFini detailTransferProduitFini=new DetailTransferProduitFini();
	 detailTransferProduitFini.setArticle(article);
	 detailTransferProduitFini.setDateTransfer(dateDebut);
	 detailTransferProduitFini.setMagasinDestination(magasin);
	 detailTransferProduitFini.setTransferStockPF(transferStockPF);
	 detailTransferProduitFini.setPrixUnitaire(BigDecimal.ZERO);
	 detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_INITIAL);
	 detailTransferProduitFini.setQuantite(new BigDecimal(CellQuantiteInitial.getNumericCellValue()) );
	 detailTransferProduitFini.setSousFamille(sousFamilleArticlePF);
		detailTransferProduitFiniDAO.add(detailTransferProduitFini);
		
	}else
	{
		JOptionPane.showMessageDialog(null, CellMagasin.getStringCellValue()  +" Introuvable");
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
        btnImporterStock.setBounds(233, 381, 129, 36);
        add(btnImporterStock);
        btnImporterStock.setVisible(false);
				  		
        listArticleOffre =articleDAO.listeArticleByCodeOffre(Constantes.OFFRE_THERRES, Constantes.OFFRE_VERRES);
	      
	      listSousFamilleArticleOffre=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePFOffre(Constantes.SOUS_FAMILLE_OFFRE_THERRES, Constantes.SOUS_FAMILLE_OFFRE_VERRES);
	      
	      for(int i=0;i<listArticleOffre.size();i++)
	    	  
	      {
	    	  
	    	Articles articles=listArticleOffre.get(i)  ;
	    	
	    	if(articles.getCodeArticle().equals(Constantes.OFFRE_THERRES))
	    	{
	    		 
	    		mapArticleOffre.put(articles.getLiblle(), articles);
	    		mapArticleOffreCode.put(articles.getCodeArticle(), articles);
	    	}else if(articles.getCodeArticle().equals(Constantes.OFFRE_VERRES))
	    	{
	    		 
	    		mapArticleOffre.put(articles.getLiblle(), articles);
	    		mapArticleOffreCode.put(articles.getCodeArticle(), articles);
	    	}
	    	
	    	
	    	  
	      }
	      
	      for(int i=0;i<listSousFamilleArticleOffre.size();i++)
	      {
	    	SousFamilleArticlePF sousFamilleArticlePF=  listSousFamilleArticleOffre.get(i);
	    	
	    	mapsousfamilleOffre.put(sousFamilleArticlePF.getCode(), sousFamilleArticlePF);
	    	  
	      }    
        
        
        
				  		 
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
