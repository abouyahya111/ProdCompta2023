package FactureAchatMP;

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
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
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
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FactureServiceProductionDAO;
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


public class ImporterFcatureAchatMPExcel extends JLayeredPane implements Constantes{
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
    private static XSSFCell CellNumFacture;
    private static XSSFCell CellDateFacture;
    private static XSSFCell CellMagasinMP;
    private static XSSFCell CellFournisseur;
    private static XSSFCell CellCodeMP;
    private static XSSFCell CellQuantite;
    private static XSSFCell CellPrix;
    private static XSSFCell CellRemise;
    private static XSSFCell CellTVA;
 
    
    private FournisseurDAO fournisseurdao;
    private MatierePremiereDAO matierePremiereDAO;
   
    
    private DetailTransferMPDAO detailTransferStockMPDAO;
 
    private ParametreDAO parametreDAO;
	BigDecimal coutPF = BigDecimal.ZERO;
	SousFamilleArticlePF sousfamilleEnvrac = null;
	SousFamilleArticlePF sousfamilleTmp = null;
	private StockPFDAO stockPFDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private DetailTransferMPDAO detailtransferMPDAO;
	private FactureAchatMPDAO factureAchatMPdao;
	private DetailFactureAchatMPDAO detailFactureAchatMPdao;
	private List<String> listNumFacture =new ArrayList<String>();
	private Map< String, Depot> mapNumFactureParDepot = new HashMap<>();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ImporterFcatureAchatMPExcel() {
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
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	detailTransfertMPDAO=new DetailTransferMPDAOImpl();
        	fournisseurdao=new FournisseurDAOImpl();
        	sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
        	detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();
        	factureAchatMPdao=new FactureAchatMPDAOImpl();
        	parametreDAO = new ParametreDAOImpl();
        	stockPFDAO = new StockPFDAOImpl();
         
			detailtransferMPDAO = new DetailTransferMPDAOImpl();
        	transferStockPFDAO = new TransferStockPFDAOImpl();
			 
        	sousFamilleArticleDAo=new SousFamilleArticlesPFDAOImpl();
         	listSousFamille=sousFamilleEnvracDAo.findAll();
         	
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
	CellMagasinMP=ExcelWSheet.getRow(i).getCell(2);
	CellFournisseur=ExcelWSheet.getRow(i).getCell(3);
	CellCodeMP=ExcelWSheet.getRow(i).getCell(4);
	CellQuantite=ExcelWSheet.getRow(i).getCell(5);
	CellPrix=ExcelWSheet.getRow(i).getCell(6);
	CellRemise=ExcelWSheet.getRow(i).getCell(7);
	CellTVA=ExcelWSheet.getRow(i).getCell(8);
	 
	NumFactureExiste=false;
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumFacture);
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	 
	 Magasin magasinMP =depotDAO.MagasinByLibelle(CellMagasinMP.getStringCellValue());
	 
	 for(int d=0;d<listNumFacture.size();d++)
	 {
		 
		 if(listNumFacture.get(d).equals(formattedCellStr))
		 {
			 NumFactureExiste=true;
		 }
		 
		 
	 }
	 
	 if(NumFactureExiste==false)
	 {
		 
		 FactureAchatMP factureAchatTMP=factureAchatMPdao.findByNumFactureByDepot(formattedCellStr,magasinMP.getDepot());
		 
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
	CellMagasinMP=ExcelWSheet.getRow(i).getCell(2);
	CellFournisseur=ExcelWSheet.getRow(i).getCell(3);
	CellCodeMP=ExcelWSheet.getRow(i).getCell(4);
	CellQuantite=ExcelWSheet.getRow(i).getCell(5);
	CellPrix=ExcelWSheet.getRow(i).getCell(6);
	CellRemise=ExcelWSheet.getRow(i).getCell(7);
	CellTVA=ExcelWSheet.getRow(i).getCell(8);
	 
	 
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellNumFacture);
	String remise = dataFormatter.formatCellValue(CellRemise);
	String quantite = dataFormatter.formatCellValue(CellQuantite).replace(",", ".");
	String prix = dataFormatter.formatCellValue(CellPrix).replace(",", ".");
	String tva = dataFormatter.formatCellValue(CellTVA);
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	 
	 Magasin magasinMP =depotDAO.MagasinByLibelle(CellMagasinMP.getStringCellValue());
	 Fournisseur fournisseur=fournisseurdao.findFournisseurByNom(CellFournisseur.getStringCellValue()) ;
     
	 FactureAchatMP factureAchatTMP=factureAchatMPdao.findByNumFactureByDepot(formattedCellStr,magasinMP.getDepot());
	 MatierePremier matierePremier=matierePremiereDAO.findByCode(CellCodeMP.getStringCellValue());
	 BigDecimal Montant=BigDecimal.ZERO;
	 
	 if(factureAchatTMP==null)
	 {
		 
		 
		 FactureAchatMP factureAchatMP=new FactureAchatMP(); 
		 String codeTransfert=Utils.genererCodeTransfer(magasinMP.getDepot().getCode(),ETAT_TRANSFER_STOCK_ACHAT);	
			factureAchatMP.setNumFacture(formattedCellStr);
			factureAchatMP.setFournisseur(fournisseur);
			factureAchatMP.setDepot(magasinMP.getDepot());
			factureAchatMP.setMagasin(magasinMP);
			factureAchatMP.setDateFacture(CellDateFacture.getDateCellValue());
			factureAchatMP.setEtat(Constantes.ETAT_IMPORTATION_EXCEL);
			factureAchatMP.setType(Constantes.TYPE_BON_LIVRAISON);
			factureAchatMP.setCodeTransfer(codeTransfert);
			factureAchatMP.setCreerPar(utilisateur);
			factureAchatMP.setDateCreer(new Date());
		  
	
	TransferStockMP transferStockMP=new TransferStockMP();
	transferStockMP.setCodeTransfer(codeTransfert);
	transferStockMP.setCreerPar(utilisateur);	    
    transferStockMP.setDate(new Date());
	transferStockMP.setDateTransfer(CellDateFacture.getDateCellValue());
	transferStockMP.setDepot(magasinMP.getDepot());
	transferStockMP.setStatut(Constantes.ETAT_IMPORTATION_EXCEL);	 
	transferStockMPDAO.add(transferStockMP);
	

    
	 
	 StockMP stockMP=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinMP.getId());
			
	 
   		 
   	 	 DetailFactureAchatMP detailFactureMP=new DetailFactureAchatMP();
   	 	  if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
	          {
   	 		  
   	 		  
   	 		 detailFactureMP.setReduction(new BigDecimal(remise));
	          }else
	          {
	        	  detailFactureMP.setReduction(BigDecimal.ZERO);  
	          }
   	 	 detailFactureMP.setMatierePremiere(matierePremier);
   	 	detailFactureMP.setQuantite(new BigDecimal(quantite));
   	 	detailFactureMP.setPrixUnitaire(new BigDecimal(prix));
	           Montant=new BigDecimal(prix).multiply(new BigDecimal(quantite));
	           detailFactureMP.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));
	           
	           if(tva!=null && tva.isEmpty()==false && !tva.equals(""))
	           {
	        	   if(tva.toUpperCase().equals(Constantes.CODE_OUI))
	        	   {
	        		   detailFactureMP.setMontantTVA(((Montant).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
			           detailFactureMP.setTva(Constantes.TVA.multiply(new BigDecimal(100))); 
	        	   }else
	        	   {
	        		   detailFactureMP.setMontantTVA(((Montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
			           detailFactureMP.setTva(BigDecimal.ZERO);
	        	   }
	        	    
	           }else
	           {
	        	   detailFactureMP.setMontantTVA(((Montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
		           detailFactureMP.setTva(BigDecimal.ZERO);
	           }
	          
	           
	          if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
	          {
	        	  if(tva!=null && tva.isEmpty()==false && !tva.equals(""))
		           {
	        		  if(tva.toUpperCase().equals(Constantes.CODE_OUI))
		        	   {
			        	  detailFactureMP.setMontantTTC((((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

		        	   }else
		        	   {
				        	  detailFactureMP.setMontantTTC((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

		        	   }

		           }else
		           {
			        	  detailFactureMP.setMontantTTC((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

		           }
	          }else
	          {
	        	  if(tva!=null && tva.isEmpty()==false && !tva.equals(""))
		           {
	        		  if(tva.toUpperCase().equals(Constantes.CODE_OUI))
		        	   {
		        		  detailFactureMP.setMontantTTC(((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));

		        	   }else
		        	   {
			        	   detailFactureMP.setMontantTTC(((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)); 

		        	   }
		           }else
		           {
		        	   detailFactureMP.setMontantTTC(((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)); 
		           }
	        	  
	        	 
	          }
	         
	       
	          
	          detailFactureMP.setFactureAchatMP(factureAchatMP);
	       //    detailFacture.setDateCreation(new Date());
	           
	          detailFactureMP.setUtilisateur(utilisateur);
	          
	          factureAchatMP.setMontantHT(detailFactureMP.getMontantHT());
	          factureAchatMP.setMontantTVA(detailFactureMP.getMontantTVA());
	          factureAchatMP.setMontantTTC (detailFactureMP.getMontantTTC());    
	          factureAchatMPdao.add(factureAchatMP);
	          
	          
	          detailFactureAchatMPdao.add(detailFactureMP);
	            
	           if(stockMP!=null)
	           {
	        	 BigDecimal coutAchat=new BigDecimal(quantite).multiply(new BigDecimal(prix));
	        	 BigDecimal coutStock=stockMP.getStock().multiply(stockMP.getPrixUnitaire());
	        	 BigDecimal  QuantiteTotal=new BigDecimal(quantite).add(stockMP.getStock());
	        	   
	        	 BigDecimal nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
	        	  stockMP.setStock(QuantiteTotal);
	        	  stockMP.setPrixUnitaire(nouveauprix);
		            stockMPDAO.edit(stockMP);
	           
	           }else
	           {
	        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
	        	   
	        	  StockMP stockMPTmp=new StockMP(); 
	        	   
	        	  stockMPTmp.setMatierePremier(matierePremier);
	        	  stockMPTmp.setMagasin(magasinMP);
	        	  stockMPTmp.setPrixUnitaire(new BigDecimal(prix));
	        	  stockMPTmp.setStock(new BigDecimal(quantite));
	        	  stockMPTmp.setStockMin(BigDecimal.ZERO);
	        	
	        	  stockMPDAO.add(stockMPTmp);
	        	   
	        	  }
	        	   
	         
	  			
	  		/// ajout transfer stock PF (Mouvement Stock PF)
	  			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
	  			detailTransferStockMP.setMatierePremier(matierePremier);
	  			//detailTransferStockMP.set(dateChooserfacture.getDate());
	  			detailTransferStockMP.setMagasinDestination(magasinMP);
	  			detailTransferStockMP.setPrixUnitaire(new BigDecimal(prix));
	  			detailTransferStockMP.setQuantite(new BigDecimal(quantite));
	  			detailTransferStockMP.setTransferStockMP(transferStockMP);
	  			detailTransferStockMPDAO.add(detailTransferStockMP);
	  			//detailTransferStockMP.set(ETAT_TRANSFER_STOCK_ACHAT);
	  			//detailTransferMPdao.add(detailTransferStockMP);
				 
	  			
	  			
	  			
		        
   	
  
	
	
	
		 
	 }else
	 {
		 
 
		 
		 StockMP stockMP=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinMP.getId());
				
		 
	   		 
	   	 	 DetailFactureAchatMP detailFactureMP=new DetailFactureAchatMP();
	   	 	  if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
		          {
	   	 		  
	   	 		  
	   	 		 detailFactureMP.setReduction(new BigDecimal(remise));
		          }else
		          {
		        	  detailFactureMP.setReduction(BigDecimal.ZERO);  
		          }
	   	 	 detailFactureMP.setMatierePremiere(matierePremier);
	   	 	detailFactureMP.setQuantite(new BigDecimal(quantite));
	   	 	detailFactureMP.setPrixUnitaire(new BigDecimal(prix));
		           Montant=new BigDecimal(prix).multiply(new BigDecimal(quantite));
		           detailFactureMP.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));
		           
		           if(tva!=null && tva.isEmpty()==false && !tva.equals(""))
		           {
		        	   if(tva.toUpperCase().equals(Constantes.CODE_OUI))
		        	   {
		        		   detailFactureMP.setMontantTVA(((Montant).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
				           detailFactureMP.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
		        		   
		        	   }else
		        	   {
		        		   detailFactureMP.setMontantTVA(((Montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
				           detailFactureMP.setTva(BigDecimal.ZERO);
		        	   }
		        	    
		           }else
		           {
		        	   detailFactureMP.setMontantTVA(((Montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
			           detailFactureMP.setTva(BigDecimal.ZERO);
		           }
		          
		           
		          if(remise!=null && remise.isEmpty()==false && !remise.equals(""))
		          {
		        	  if(tva!=null && tva.isEmpty()==false && !tva.equals(""))
			           {
		        		  if(tva.toUpperCase().equals(Constantes.CODE_OUI))
			        	   {
				        	  detailFactureMP.setMontantTTC((((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

			        	   }else
			        	   {
					        	  detailFactureMP.setMontantTTC((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

			        	   }

			           }else
			           {
				        	  detailFactureMP.setMontantTTC((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(remise)).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

			           }
		          }else
		          {
		        	  if(tva!=null && tva.isEmpty()==false && !tva.equals(""))
			           {
		        		  if(tva.toUpperCase().equals(Constantes.CODE_OUI))
			        	   {
			        		  detailFactureMP.setMontantTTC(((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));

			        	   }else
			        	   {
				        	   detailFactureMP.setMontantTTC(((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)); 

			        	   }
			           }else
			           {
			        	   detailFactureMP.setMontantTTC(((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)); 
			           }
		        	  
		        	 
		          }
		         
		       
		          
		          detailFactureMP.setFactureAchatMP(factureAchatTMP);
		       //    detailFacture.setDateCreation(new Date());
		           
		          detailFactureMP.setUtilisateur(utilisateur);
		          
		          detailFactureAchatMPdao.add(detailFactureMP);
		            
		           if(stockMP!=null)
		           {
		        	 BigDecimal coutAchat=new BigDecimal(quantite).multiply(new BigDecimal(prix));
		        	 BigDecimal coutStock=stockMP.getStock().multiply(stockMP.getPrixUnitaire());
		        	 BigDecimal  QuantiteTotal=new BigDecimal(quantite).add(stockMP.getStock());
		        	   
		        	 BigDecimal nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
		        	  stockMP.setStock(QuantiteTotal);
		        	  stockMP.setPrixUnitaire(nouveauprix);
			            stockMPDAO.edit(stockMP);
		           
		           }else
		           {
		        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
		        	   
		        	  StockMP stockMPTmp=new StockMP(); 
		        	   
		        	  stockMPTmp.setMatierePremier(matierePremier);
		        	  stockMPTmp.setMagasin(magasinMP);
		        	  stockMPTmp.setPrixUnitaire(new BigDecimal(prix));
		        	  stockMPTmp.setStock(new BigDecimal(quantite));
		        	  stockMPTmp.setStockMin(BigDecimal.ZERO);
		        	
		        	  stockMPDAO.add(stockMPTmp);
		        	   
		        	  } 
		           
		           
		           factureAchatTMP.setMontantHT(factureAchatTMP.getMontantHT().add(detailFactureMP.getMontantHT()) );
		           factureAchatTMP.setMontantTVA(factureAchatTMP.getMontantTVA().add(detailFactureMP.getMontantTVA()) );
		           factureAchatTMP.setMontantTTC (factureAchatTMP.getMontantTTC().add(detailFactureMP.getMontantTTC()) );    
			          factureAchatMPdao.edit(factureAchatTMP);   
		           
		           
		 
		 
		 
	
	TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(factureAchatTMP.getCodeTransfer());
	
if(transferStockMP==null)
{
	TransferStockMP transferStockMPTmp=new TransferStockMP();
	transferStockMPTmp.setCodeTransfer(factureAchatTMP.getCodeTransfer());
	transferStockMPTmp.setCreerPar(utilisateur);	    
	transferStockMPTmp.setDate(new Date());
	transferStockMPTmp.setDateTransfer(CellDateFacture.getDateCellValue());
	transferStockMPTmp.setDepot(magasinMP.getDepot());
	transferStockMPTmp.setStatut(Constantes.ETAT_IMPORTATION_EXCEL);	 
	transferStockMPDAO.add(transferStockMPTmp);
	
	DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
	detailTransferStockMP.setMatierePremier(matierePremier);
	//detailTransferStockMP.set(dateChooserfacture.getDate());
	detailTransferStockMP.setMagasinDestination(magasinMP);
	detailTransferStockMP.setPrixUnitaire(new BigDecimal(prix));
	detailTransferStockMP.setQuantite(new BigDecimal(quantite));
	detailTransferStockMP.setTransferStockMP(transferStockMPTmp);
	detailTransferStockMPDAO.add(detailTransferStockMP);
	
	
	
}else
{
	
	
	DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		detailTransferStockMP.setMatierePremier(matierePremier);
		//detailTransferStockMP.set(dateChooserfacture.getDate());
		detailTransferStockMP.setMagasinDestination(magasinMP);
		detailTransferStockMP.setPrixUnitaire(new BigDecimal(prix));
		detailTransferStockMP.setQuantite(new BigDecimal(quantite));
		detailTransferStockMP.setTransferStockMP(transferStockMP);
		detailTransferStockMPDAO.add(detailTransferStockMP);
	
	
	
	
}
    
	 
		 
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
	
String numFacture=listNumFacture.get(p);	
Depot depot=mapNumFactureParDepot.get(numFacture);

FactureAchatMP factureAchatMP=factureAchatMPdao.findByNumFactureByDepot(numFacture, depot);
if(factureAchatMP!=null)
{
	detailFactureAchatMPdao.DelateDetailfactureAchatMPByFactureAchatMP(factureAchatMP);
	
	TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(factureAchatMP.getCodeTransfer());
	if(transferStockMP!=null)
	{
		detailTransfertMPDAO.DeleteTransfertStockMPByTransfertStockMP(transferStockMP);
	}
	
	if(factureAchatMP!=null)
	{
		factureAchatMPdao.delete(factureAchatMP.getId());	
	}
	if(transferStockMP!=null)
	{
		transferStockMPDAO.delete(transferStockMP.getId());
	}
	
}







	
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
