package FactureAchat;

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
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
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
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
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


public class ImporterFcatureAchatPFExcel extends JLayeredPane implements Constantes{
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
	 
	private DetailTransferMPDAO detailTransfertMPDAO ;
	
	private DepotDAO depotDAO;
	private ProductionDAO productionDAO;
	private StockMPDAO stockMPDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	
	private Map< String, String> mapChargeSupp = new HashMap<>();
	
	private Map< Integer, MatierePremier> mapMatierePremiereDetailMouvement= new HashMap<>();
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private List<Journee> listJournee =new ArrayList<Journee>();
	private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	private JourneeDAO journeeDAO;
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	 
	private SousFamilleArticlesPFDAO sousFamilleArticleDAo;
	 
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();

	boolean QuantiteInsuffisant=false;
	private JTextField txtlien;
	
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell CellNumFacture;
    private static XSSFCell CellDateFacture;
    private static XSSFCell CellMagasinPF;
    private static XSSFCell CellFournisseur;
    private static XSSFCell CellCodeArticle;
    private static XSSFCell CellArticle;
    private static XSSFCell CellFamille;
    private static XSSFCell CellSousFamille;
    private static XSSFCell CellQuantite;
    private static XSSFCell CellPrix;
    private static XSSFCell CellRemise;
    private static XSSFCell CellTVA;
 
    
    private FournisseurDAO fournisseurdao;
    private MatierePremiereDAO matierePremiereDAO;
	private ArticlesDAO articleDAO;
    private DetailTransferProduitFiniDAO detailTransferStockPFdao;
    private ParametreDAO parametreDAO;
	BigDecimal coutPF = BigDecimal.ZERO;
	SousFamilleArticlePF sousfamilleEnvrac = null;
	SousFamilleArticlePF sousfamilleTmp = null;
	private StockPFDAO stockPFDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private DetailTransferMPDAO detailtransferMPDAO;
	private FactureAchatDAO factureAchatdao;
	private DetailFactureAchatDAO detailFactureAchatdao;
	private FamilleArticlesPFDAO familleArticleDAO;
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	private List<String> listNumFacture =new ArrayList<String>();
	private Map< String, Depot> mapNumFactureParDepot = new HashMap<>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private StockPFDAO stockpfDAO;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ImporterFcatureAchatPFExcel() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	mouvementStockGlobalDAO=new MouvementStockGlobalDAOImpl();
        	journeeDAO=new JourneeDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	compteurProductionDAO=new CompteurProductionDAOImpl();
        	articleDAO=new ArticlesDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	detailTransfertMPDAO=new DetailTransferMPDAOImpl();
        	fournisseurdao=new FournisseurDAOImpl();
        	stockpfDAO=new StockPFDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
        	detailFactureAchatdao=new DetailFactureAchatDAOImpl();
        	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	factureAchatdao=new FactureAchatDAOImpl();
        	parametreDAO = new ParametreDAOImpl();
        	stockPFDAO = new StockPFDAOImpl();
         
			detailtransferMPDAO = new DetailTransferMPDAOImpl();
        	transferStockPFDAO = new TransferStockPFDAOImpl();
        	sousFamilleArticleDAo=new SousFamilleArticlesPFDAOImpl();
        	listFamilleArticle=familleArticleDAO.findAll(); 
         	
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
        	public void actionPerformed(ActionEvent e) {
        		
        		 
        		
        		if(!txtlien.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlien.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				        
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  				      
	  				      
	  				   
	  				      
	  				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		


/////////////////////////////////////////////////////////////////////////////////////////  Verifier Si La facture existant  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
boolean NumFactureExiste=false;
String msg="";
listNumFacture.clear();
mapNumFactureParDepot.clear();
for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	 
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellNumFacture=ExcelWSheet.getRow(i).getCell(0);
	CellDateFacture=ExcelWSheet.getRow(i).getCell(1);
	CellMagasinPF=ExcelWSheet.getRow(i).getCell(2);
	CellFournisseur=ExcelWSheet.getRow(i).getCell(3);
	CellFamille=ExcelWSheet.getRow(i).getCell(4);
	CellSousFamille=ExcelWSheet.getRow(i).getCell(5);
	CellCodeArticle=ExcelWSheet.getRow(i).getCell(6);
	CellArticle=ExcelWSheet.getRow(i).getCell(7);
	CellQuantite=ExcelWSheet.getRow(i).getCell(8);
	CellPrix=ExcelWSheet.getRow(i).getCell(9);
	CellRemise=ExcelWSheet.getRow(i).getCell(10);
	CellTVA=ExcelWSheet.getRow(i).getCell(11);
	 
	NumFactureExiste=false;
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumFacture);
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	 
	 Magasin magasinMP =depotDAO.MagasinByLibelle(CellMagasinPF.getStringCellValue());
	 
	 for(int d=0;d<listNumFacture.size();d++)
	 {
		 
		 if(listNumFacture.get(d).equals(formattedCellStr))
		 {
			 NumFactureExiste=true;
		 }
		 
		 
	 }
	 
	 if(NumFactureExiste==false)
	 {
		 FactureAchat factureAchatTMP=factureAchatdao.findByNumFactureBydepot(formattedCellStr, magasinMP.getDepot());
		 
		 
		 if(factureAchatTMP==null)
		 {
			 listNumFacture.add(formattedCellStr);
			 mapNumFactureParDepot.put(formattedCellStr, magasinMP.getDepot());
			 
			 
		 }else
		 {
			 
			 msg=msg+formattedCellStr +" Depot : "+ magasinMP.getDepot().getLibelle() +"n/" ;
			 
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


if(!msg.equals(""))
{
	JOptionPane.showMessageDialog(null, "Les factures Suivante : "+msg+" D�ja existant Dans la base");
	return;
}
   						
	  							
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	  							


for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	 
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellNumFacture=ExcelWSheet.getRow(i).getCell(0);
	CellDateFacture=ExcelWSheet.getRow(i).getCell(1);
	CellMagasinPF=ExcelWSheet.getRow(i).getCell(2);
	CellFournisseur=ExcelWSheet.getRow(i).getCell(3);
	CellFamille=ExcelWSheet.getRow(i).getCell(4);
	CellSousFamille=ExcelWSheet.getRow(i).getCell(5);
	CellCodeArticle=ExcelWSheet.getRow(i).getCell(6);
	CellArticle=ExcelWSheet.getRow(i).getCell(7);
	CellQuantite=ExcelWSheet.getRow(i).getCell(8);
	CellPrix=ExcelWSheet.getRow(i).getCell(9);
	CellRemise=ExcelWSheet.getRow(i).getCell(10);
	CellTVA=ExcelWSheet.getRow(i).getCell(11);
	 
	 
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumFacture);
	String remise = dataFormatter.formatCellValue(CellRemise);
	String quantiteAchat = dataFormatter.formatCellValue(CellQuantite).replace(",", ".");
	String prix = dataFormatter.formatCellValue(CellPrix).replace(",", ".");
	String tva = dataFormatter.formatCellValue(CellTVA);
	String famille = dataFormatter.formatCellValue(CellFamille);
	String sousfamille = dataFormatter.formatCellValue(CellSousFamille);
	String codeArticle = dataFormatter.formatCellValue(CellCodeArticle);
	
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {

	 Magasin magasinPF =depotDAO.MagasinByLibelle(CellMagasinPF.getStringCellValue());
	 Fournisseur fournisseur=fournisseurdao.findFournisseurByNom(CellFournisseur.getStringCellValue()) ;
     SousFamilleArticlePF sousFamilleArticlePF=sousFamilleArticleDAo.findByLibelle(sousfamille);
	 FactureAchat factureAchatTMP=factureAchatdao.findByNumFactureBydepot(formattedCellStr, magasinPF.getDepot());
	 Articles articles=articleDAO.findByCode(codeArticle);
	 BigDecimal Montant=BigDecimal.ZERO;
	 BigDecimal nouveauprix=BigDecimal.ZERO;
		BigDecimal coutAchat=BigDecimal.ZERO;
		BigDecimal coutStock=BigDecimal.ZERO;
		BigDecimal QuantiteTotal=BigDecimal.ZERO;
		BigDecimal QuantiteTotalStock=BigDecimal.ZERO;
	 
	 if(factureAchatTMP==null)
	 {
		 
		 String codeTransfert=Utils.genererCodeTransfer(magasinPF.getDepot().getCode(),ETAT_TRANSFER_STOCK_ACHAT);
		 FactureAchat factureAchat=new FactureAchat();
			factureAchat.setNumFacture(formattedCellStr);
			factureAchat.setFournisseur(fournisseur);
			factureAchat.setDepot(magasinPF.getDepot());
			factureAchat.setMagasin(magasinPF);
			factureAchat.setDateFacture(CellDateFacture.getDateCellValue());
			factureAchat.setEtat(Constantes.ETAT_IMPORTATION_EXCEL);
			factureAchat.setType(Constantes.TYPE_BON_LIVRAISON);
			 			//factureAchat.setDetailFactureAchat(listDetailFactureAchat);
			factureAchat.setCodeTransfer(codeTransfert);
			factureAchat.setCreerPar(utilisateur);
			factureAchat.setDateCreer(new Date());
		
		    factureAchatdao.add(factureAchat); 
		    TransferStockPF transferStock = new TransferStockPF();
		    transferStock.setCodeTransfer(codeTransfert);
			transferStock.setCreerPar(utilisateur);
			transferStock.setDate(new Date());
			transferStock.setDateTransfer(CellDateFacture.getDateCellValue());
			transferStock.setStatut(Constantes.ETAT_IMPORTATION_EXCEL);
			transferStockPFDAO.add(transferStock);

			////////////////////////////////////////////////////////////////////////////  Traitement ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			

			
			 
	         
	         
	         
	        
	         
	         
	         if(articles.getCentreCout1()!=null)
	         {
	        	 BigDecimal quantite =new BigDecimal(quantiteAchat).setScale(3);
	        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
	        	 
	        	 
	        	if(articles.getCentreCout1().equals(BigDecimal.ONE)) 
	        	{
	        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
	        		{
	        			JOptionPane.showMessageDialog(null, "Grammage  "+articles.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
	        			return;
	        		}
	        		
	        	}else
	        	{
	        		BigDecimal result=grammage.divide(articles.getCentreCout1(), 6, RoundingMode.FLOOR);
	        	
	        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
	        	
	        		if(rest.compareTo(BigDecimal.ZERO)!=0)
	        		{
	        			JOptionPane.showMessageDialog(null, "Grammage d'"+articles.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
	        			return;	
	        		}
	        		
	        	}
	        	 
	         }
	         
	      
	       
	    
	        	 
	        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(articles.getId(), magasinPF.getId(),sousFamilleArticlePF.getId());
	        		
					
	        	 
		        	 	 DetailFactureAchat detailFacture=new DetailFactureAchat();
		        	 	if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
		  	          {
				        	  detailFacture.setReduction(new BigDecimal(remise));
				          }else
				          {
				        	  detailFacture.setReduction(BigDecimal.ZERO);  
				          }
		        	 	 detailFacture.setArticle(articles);
		        	 	 
		        	 	 detailFacture.setQuantite(new BigDecimal(quantiteAchat));  
		        	     detailFacture.setPrixUnitaire(new BigDecimal(prix));
				         Montant=new BigDecimal(quantiteAchat).multiply(new BigDecimal(prix));
				          
				       
			        	 	 
				          if(articles.getCodeArticle().equals("1500") || articles.getCodeArticle().equals("1503") || articles.getCodeArticle().equals("1504") ) // les articles sans TVA 
				          {
				        	 	if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
					  	          {
				        	 		
				        		  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP).subtract(Montant.setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
					          }else
					          {
					        	  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));  
					          }
				        	 
					          detailFacture.setMontantTVA(((detailFacture.getMontantHT()).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
					          detailFacture.setTva(BigDecimal.ZERO);
					          detailFacture.setMontantTTC(detailFacture.getMontantHT().add(detailFacture.getMontantTVA()));
					          detailFacture.setSousFamille(sousFamilleArticlePF);
				          }else
				          {
				        	  if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
				  	          {
					        	  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP).subtract(Montant.setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));

					          }else
					          {
					        	  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));

					          }
				        	  
				        	  if(tva!=null && tva.isEmpty()==false && !tva.equals(""))
				  	          {
				        		  detailFacture.setMontantTVA(((detailFacture.getMontantHT()).multiply(new BigDecimal(tva).divide(new BigDecimal(100), 6, RoundingMode.FLOOR))).setScale(6, RoundingMode.HALF_UP));
						          detailFacture.setTva(new BigDecimal(tva));
				  	          }else
				  	          {
				  	        	detailFacture.setMontantTVA(detailFacture.getMontantHT());
						          detailFacture.setTva(BigDecimal.ZERO); 
				  	          }
					          
					          detailFacture.setMontantTTC(detailFacture.getMontantHT().add(detailFacture.getMontantTVA()));
					          detailFacture.setSousFamille(sousFamilleArticlePF);
				          }
				    
				         
				       
				        
				          
				           detailFacture.setFactureAchat(factureAchat);
				       //    detailFacture.setDateCreation(new Date());
				           
				           detailFacture.setUtilisateur(utilisateur);
				          
				           detailFactureAchatdao.add(detailFacture);
				           
				           if(!sousFamilleArticlePF.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
				           {
				        	   
				        	   if(stockpf!=null)
					           {
					        	   if(articles.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					        	   {
					        		   coutAchat=(new BigDecimal(quantiteAchat).multiply(articles.getConditionnement().multiply(articles.getConditionnementcaisse()))).multiply(new BigDecimal(prix).divide(articles.getConditionnement().multiply(articles.getConditionnementcaisse()), 2, RoundingMode.DOWN).setScale(2));
					        		   QuantiteTotal=new BigDecimal(quantiteAchat).multiply(articles.getConditionnement().multiply(articles.getConditionnementcaisse()));
					        	   }else
					        	   {
					        		   coutAchat=(new BigDecimal(quantiteAchat)).multiply(new BigDecimal(prix));
					        		   QuantiteTotal=new BigDecimal(quantiteAchat);
					        	   }
					        	
					        	 	 
					        	 
					        	  coutStock=stockpf.getStock().multiply(stockpf.getPrixUnitaire());
					        	 
					        	QuantiteTotalStock=(QuantiteTotal).add(stockpf.getStock());
				        	 	 
					        	 
					        	   
					        	   nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotalStock, 6, RoundingMode.HALF_UP);
						           stockpf.setStock(QuantiteTotalStock);
						           stockpf.setPrixUnitaire(nouveauprix);
						           stockpfDAO.edit(stockpf);
					           
					           }else
					           {
					        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
					        	   
					        	  StockPF stockpfTmp=new StockPF(); 
					        	   
					        	  stockpfTmp.setArticles(articles);
					        	  stockpfTmp.setMagasin(magasinPF);
                                 if(articles.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
                                   {
                                    stockpfTmp.setPrixUnitaire(new BigDecimal(prix).divide(articles.getConditionnement().multiply(articles.getConditionnementcaisse()),2, RoundingMode.DOWN));
                                    QuantiteTotal=new BigDecimal(quantiteAchat).multiply(articles.getConditionnement().multiply(articles.getConditionnementcaisse()));
                                   }else
                                   {
                                   stockpfTmp.setPrixUnitaire(new BigDecimal(prix));
                                   QuantiteTotal=new BigDecimal(quantiteAchat);
                                    }
					        	
					        	  stockpfTmp.setStock(QuantiteTotal);
				        	 	 
					        	 
					        	  stockpfTmp.setStockMin(BigDecimal.ZERO);
					        	  stockpfTmp.setSousFamille(sousFamilleArticlePF);
					        	  stockpfDAO.add(stockpfTmp);
					        	   
					        	  }  
				        	   
				           }
				           
				        
				        	   
				        
				           
				          
				        
				  			
				  			 if(!sousFamilleArticlePF.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
					           {
				  				 
				  				/// ajout transfer stock PF (Mouvement Stock PF)
						  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
						  			detailTransferStockPF.setArticle(articles);
						  			detailTransferStockPF.setSousFamille(sousFamilleArticlePF);
									detailTransferStockPF.setDateTransfer(CellDateFacture.getDateCellValue());
									detailTransferStockPF.setMagasinDestination(magasinPF);
									if(articles.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
									{
										
										detailTransferStockPF.setPrixUnitaire((new BigDecimal(prix).subtract(new BigDecimal(prix).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR)))).divide(articles.getConditionnement().multiply(articles.getConditionnementcaisse()) , 6, RoundingMode.FLOOR));
									
									}else
									{
										detailTransferStockPF.setPrixUnitaire(new BigDecimal(prix).subtract(new BigDecimal(prix).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR))));
									}
									
									detailTransferStockPF.setQuantite(QuantiteTotal); 
									detailTransferStockPF.setTransferStockPF(transferStock);
									detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ACHAT);
									 detailTransferStockPFdao.add(detailTransferStockPF);
				  				 
					           }
				  			
				  	
				  			
				  			
					      
		        	
		      
		       
	         
			
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
		 
	 }else
	 {
		 
		 

		 
		 
		 
		 
		  

			////////////////////////////////////////////////////////////////////////////  Traitement ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			

			
			 
	         
	         
	         
	        
	         
	         
	         if(articles.getCentreCout1()!=null)
	         {
	        	 BigDecimal quantite =new BigDecimal(quantiteAchat).setScale(3);
	        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
	        	 
	        	 
	        	if(articles.getCentreCout1().equals(BigDecimal.ONE)) 
	        	{
	        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
	        		{
	        			JOptionPane.showMessageDialog(null, "Grammage  "+articles.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
	        			return;
	        		}
	        		
	        	}else
	        	{
	        		BigDecimal result=grammage.divide(articles.getCentreCout1(), 6, RoundingMode.FLOOR);
	        	
	        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
	        	
	        		if(rest.compareTo(BigDecimal.ZERO)!=0)
	        		{
	        			JOptionPane.showMessageDialog(null, "Grammage d'"+articles.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
	        			return;	
	        		}
	        		
	        	}
	        	 
	         }
	         
	      
	       
	    
	        	 
	        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(articles.getId(), magasinPF.getId(),sousFamilleArticlePF.getId());
	        		
					
	        	 
		        	 	 DetailFactureAchat detailFacture=new DetailFactureAchat();
		        	 	if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
		  	          {
				        	  detailFacture.setReduction(new BigDecimal(remise));
				          }else
				          {
				        	  detailFacture.setReduction(BigDecimal.ZERO);  
				          }
		        	 	 detailFacture.setArticle(articles);
		        	 	 
		        	 	 detailFacture.setQuantite(new BigDecimal(quantiteAchat));  
		        	     detailFacture.setPrixUnitaire(new BigDecimal(prix));
				         Montant=new BigDecimal(quantiteAchat).multiply(new BigDecimal(prix));
				          
				       
			        	 	 
				          if(articles.getCodeArticle().equals("1500") || articles.getCodeArticle().equals("1503") || articles.getCodeArticle().equals("1504") ) // les articles sans TVA 
				          {
				        	 	if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
					  	          {
				        	 		
				        		  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP).subtract(Montant.setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
					          }else
					          {
					        	  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));  
					          }
				        	 
					          detailFacture.setMontantTVA(((detailFacture.getMontantHT()).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
					          detailFacture.setTva(BigDecimal.ZERO);
					          detailFacture.setMontantTTC(detailFacture.getMontantHT().add(detailFacture.getMontantTVA()));
					          detailFacture.setSousFamille(sousFamilleArticlePF);
				          }else
				          {
				        	  if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
				  	          {
					        	  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP).subtract(Montant.setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));

					          }else
					          {
					        	  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));

					          }
				        	  
				        	  
				        	  if(tva!=null && tva.isEmpty()==false && !tva.equals(""))
				  	          {
				        		  
				        		   detailFacture.setMontantTVA(((detailFacture.getMontantHT()).multiply(new BigDecimal(tva).divide(new BigDecimal(100), 6, RoundingMode.FLOOR))).setScale(6, RoundingMode.HALF_UP));
							          detailFacture.setTva(new BigDecimal(tva));
				  	          }else
				  	          {
				  	        	   detailFacture.setMontantTVA(detailFacture.getMontantHT());
							          detailFacture.setTva(BigDecimal.ZERO);
				  	          }
					       
					          detailFacture.setMontantTTC(detailFacture.getMontantHT().add(detailFacture.getMontantTVA()));
					          detailFacture.setSousFamille(sousFamilleArticlePF);
				          }
				    
				         
				       
				        
				          
				           detailFacture.setFactureAchat(factureAchatTMP);
				       //    detailFacture.setDateCreation(new Date());
				           
				           detailFacture.setUtilisateur(utilisateur);
				          
				           detailFactureAchatdao.add(detailFacture);
				           
				           if(!sousFamilleArticlePF.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
				           {
				        	   
				        	   if(stockpf!=null)
					           {
					        	   if(articles.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					        	   {
					        		   coutAchat=(new BigDecimal(quantiteAchat).multiply(articles.getConditionnement().multiply(articles.getConditionnementcaisse()))).multiply(new BigDecimal(prix).divide(articles.getConditionnement().multiply(articles.getConditionnementcaisse()), 2, RoundingMode.DOWN).setScale(2));
					        		   QuantiteTotal=new BigDecimal(quantiteAchat).multiply(articles.getConditionnement().multiply(articles.getConditionnementcaisse()));
					        	   }else
					        	   {
					        		   coutAchat=(new BigDecimal(quantiteAchat)).multiply(new BigDecimal(prix));
					        		   QuantiteTotal=new BigDecimal(quantiteAchat);
					        	   }
					        	
					        	 	 
					        	 
					        	  coutStock=stockpf.getStock().multiply(stockpf.getPrixUnitaire());
					        	 
					        	QuantiteTotalStock=(QuantiteTotal).add(stockpf.getStock());
				        	 	 
					        	 
					        	   
					        	   nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotalStock, 6, RoundingMode.HALF_UP);
						           stockpf.setStock(QuantiteTotalStock);
						           stockpf.setPrixUnitaire(nouveauprix);
						           stockpfDAO.edit(stockpf);
					           
					           }else
					           {
					        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
					        	   
					        	  StockPF stockpfTmp=new StockPF(); 
					        	   
					        	  stockpfTmp.setArticles(articles);
					        	  stockpfTmp.setMagasin(magasinPF);
                                 if(articles.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
                                   {
                                    stockpfTmp.setPrixUnitaire(new BigDecimal(prix).divide(articles.getConditionnement().multiply(articles.getConditionnementcaisse()),2, RoundingMode.DOWN));
                                    QuantiteTotal=new BigDecimal(quantiteAchat).multiply(articles.getConditionnement().multiply(articles.getConditionnementcaisse()));
                                   }else
                                   {
                                   stockpfTmp.setPrixUnitaire(new BigDecimal(prix));
                                   QuantiteTotal=new BigDecimal(quantiteAchat);
                                    }
					        	
					        	  stockpfTmp.setStock(QuantiteTotal);
				        	 	 
					        	 
					        	  stockpfTmp.setStockMin(BigDecimal.ZERO);
					        	  stockpfTmp.setSousFamille(sousFamilleArticlePF);
					        	  stockpfDAO.add(stockpfTmp);
					        	   
					        	  }  
				        	   
				           }
				           
				        
				        	   
				        
				           
				          
				        
				  			
				  			 if(!sousFamilleArticlePF.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
					           {
				  				 
				  				  TransferStockPF transferStock =  transferStockPFDAO.findByCodeTransfert(factureAchatTMP.getCodeTransfer());
				  				  if(transferStock!=null)
				  				  {
				  					/// ajout transfer stock PF (Mouvement Stock PF)
							  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
							  			detailTransferStockPF.setArticle(articles);
							  			detailTransferStockPF.setSousFamille(sousFamilleArticlePF);
										detailTransferStockPF.setDateTransfer(CellDateFacture.getDateCellValue());
										detailTransferStockPF.setMagasinDestination(magasinPF);
										if(articles.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
										{
											
											detailTransferStockPF.setPrixUnitaire((new BigDecimal(prix).subtract(new BigDecimal(prix).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR)))).divide(articles.getConditionnement().multiply(articles.getConditionnementcaisse()) , 6, RoundingMode.FLOOR));
										
										}else
										{
											detailTransferStockPF.setPrixUnitaire(new BigDecimal(prix).subtract(new BigDecimal(prix).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR))));
										}
										
										detailTransferStockPF.setQuantite(QuantiteTotal); 
										detailTransferStockPF.setTransferStockPF(transferStock);
										detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ACHAT);
										 detailTransferStockPFdao.add(detailTransferStockPF);
				  				  }else
				  				  {
				  					  
				  					 TransferStockPF transferStockTmp=new TransferStockPF();
				  					transferStockTmp.setCodeTransfer(factureAchatTMP.getCodeTransfer());
				  					transferStockTmp.setCreerPar(utilisateur);
				  					transferStockTmp.setDate(new Date());
				  					transferStockTmp.setDateTransfer(CellDateFacture.getDateCellValue());
				  					transferStockTmp.setStatut(Constantes.ETAT_IMPORTATION_EXCEL);
						  				transferStockPFDAO.add(transferStockTmp);
						  				
						  				/// ajout transfer stock PF (Mouvement Stock PF)
							  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
							  			detailTransferStockPF.setArticle(articles);
							  			detailTransferStockPF.setSousFamille(sousFamilleArticlePF);
										detailTransferStockPF.setDateTransfer(CellDateFacture.getDateCellValue());
										detailTransferStockPF.setMagasinDestination(magasinPF);
										if(articles.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
										{
											
											detailTransferStockPF.setPrixUnitaire((new BigDecimal(prix).subtract(new BigDecimal(prix).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR)))).divide(articles.getConditionnement().multiply(articles.getConditionnementcaisse()) , 6, RoundingMode.FLOOR));
										
										}else
										{
											detailTransferStockPF.setPrixUnitaire(new BigDecimal(prix).subtract(new BigDecimal(prix).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR))));
										}
										
										detailTransferStockPF.setQuantite(QuantiteTotal); 
										detailTransferStockPF.setTransferStockPF(transferStockTmp);
										detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ACHAT);
										 detailTransferStockPFdao.add(detailTransferStockPF);	
						  				
						  				
						  				
						  				
				  				  }
				  			  
				  				 
				  				
				  				 
					           }
				  			
				  	
				  			
				  			
					      
		        	
		      
		       
	         
			
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
		 
	  
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
	 
	 
 
 }
 
	



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 

for(int p=0;p<listNumFacture.size();p++)
{



}





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
        button.setBounds(84, 200, 129, 36);
        add(button);			        
				  		     
				  		 
	}
	
 
	
	

}
