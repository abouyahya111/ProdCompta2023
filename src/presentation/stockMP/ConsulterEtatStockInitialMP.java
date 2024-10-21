package presentation.stockMP;

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
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
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
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.FacturePF;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SousFamilleEnVrac;
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


public class ConsulterEtatStockInitialMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<EtatStockMP> listEtatStockMP =new ArrayList<EtatStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPTmp =new ArrayList<DetailTransferStockMP>();
	
	//******************************************* Listes Pour Mouvement de Stock Mp **********************************************//
	
	
	private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer =new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp =new ArrayList<DetailMouvementStock>();
	
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
	
	
	
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFService =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMPService =new ArrayList<DetailTransferStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPAllMP =new ArrayList<DetailTransferStockMP>();
	
	//***************************************************
	
	private List<DetailMouvementStock> listMouvementStockMPAfficherTmp =new ArrayList<DetailMouvementStock>();
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

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
	  JComboBox combodepot = new JComboBox();
	 JComboBox combomp = new JComboBox();
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailTransferMPDAO detailTransferStockMPDAO;

	private JTextField txtlibelle=new JTextField();
	
	
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();

	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockMPDAO stockMPDAO;
	
	private CompteClientDAO compteclientdao;
	private MatierePremiereDAO MatierPremiereDAO;
	private JTextField txtcodemp;
	String titre="";
	 Workbook workbook = new HSSFWorkbook();
	 JLabel lblDateInitial = new JLabel("Date Initial:");
	 JDateChooser dateinitiale = new JDateChooser();
	 JButton btnTransfert = new JButton("Transfert");
	 private TransferStockMPDAO transferStockMPDAO;
	 private SousFamilleEnVracDAO sousFamilleEnvracDAo;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public ConsulterEtatStockInitialMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1485, 1137);
      
	
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
         	transferStockMPDAO=new TransferStockMPDAOImpl();
         	sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
         listMP=MatierPremiereDAO.findAll();
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Matiere Premiere","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial"
       	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
      
				  		
       tableEtatStock.setShowVerticalLines(false);
       tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
       tableEtatStock.setRowHeightEnabled(true);
       tableEtatStock.setBackground(new Color(255, 255, 255));
       tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableEtatStock.setColumnControlVisible(true);
       tableEtatStock.setForeground(Color.BLACK);
       tableEtatStock.setGridColor(new Color(0, 0, 255));
       tableEtatStock.setAutoCreateRowSorter(true);
       tableEtatStock.setBounds(2, 27, 411, 198);
       tableEtatStock.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableEtatStock);
				  		     	scrollPane.setBounds(10, 195, 1465, 587);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Etat de Stock");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		      
		     	
		      
		     
		     
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal totalmontantinitial=BigDecimal.ZERO;
				BigDecimal totalmontantachat=BigDecimal.ZERO;
				BigDecimal totalmontantsortie=BigDecimal.ZERO;
				BigDecimal totalmontantservice=BigDecimal.ZERO;
				BigDecimal totalmontantavoir=BigDecimal.ZERO;
				BigDecimal totalmontantfinale=BigDecimal.ZERO;
				 if(listEtatStockMP.size()!=0)
				 {
					 int i=0;
					 while(i<listEtatStockMP.size())
					 {
						 EtatStockMP etatstockmp=listEtatStockMP.get(i);
						 totalmontantinitial=totalmontantinitial.add(etatstockmp.getMontantInitial()) ;
						
						 
						 
						 
						 i++;
					 }
					 
					 
					 
					 
						Map parameters = new HashMap();
					
						parameters.put("totalmontantinitial",totalmontantinitial );
						
						parameters.put("titre", titre);
						JasperUtils.imprimerEtatStockInitialMP(listEtatStockMP,parameters);
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
					
				
				
			}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(892, 793, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat Initial de Stock MP :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		detailTransferStockMPDAO.ViderSession();
	    		listEtatStockMP.clear();
	    		listDetailTransferStockMPInitial.clear() ;
	    		
	    		 listDetailTransferStockMPAllMP.clear();
	    		
	    		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	    		BigDecimal montantInitial=new BigDecimal(0);
	    		BigDecimal quantiteTotalInitial=new BigDecimal(0);
	    		BigDecimal PrixInitial=new BigDecimal(0);
	    		BigDecimal montantachat=new BigDecimal(0);
	    		
	    		
	    		boolean trouve=false;
	    		MatierePremier mp=mapMP.get(combomp.getSelectedItem());
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		
	    		if(magasin!=null)
	    		{
	    	 		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		    		{	
		    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
		    			
		    		if(!d1.equals(d2))
		    		{
		    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
		    			{
		    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
		    				return;
		    			}
		    			
		    		}
		    		
		    		if(mp!=null)
		    		{
		    			titre="Etat Stock Initial de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
		    		}else
		    		{
		    			titre="Etat Stock Initial de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
		    		}
		    		
		    		}
		    		
		    		
		    		if(dateChooserdebut.getDate()==null && mp!=null)
		    		{
		    			dateChooserfin.setCalendar(null);
		    			titre="Etat Stock Initial de "+mp.getNom() +" au magasin : "+magasin.getLibelle();
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		    			titre="Etat Stock Initial de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
		    		{
		    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		    			titre="Etat Stock Initial de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
		    		}	
		    		
		    		
		    		listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_INITIAL,magasin);
		    			
		    		
		    		
		    		listDetailTransferStockMPAllMP=	detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);
		    		
		    		for(int p=0;p<listDetailTransferStockMPAllMP.size();p++)
		    		{
		    			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPAllMP.get(p);
		    			EtatStockMP etatstock=new EtatStockMP();
		    			etatstock.setMp(detailtransferstockmp.getMatierePremier());
		    			
		    			if(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		    			{
		    			
		    				SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(detailtransferstockmp.getMatierePremier());
		    				
		    				
		    				if(sousFamilleEnVrac!=null)
		    				{
		    					
		    					etatstock.setSousFamille(sousFamilleEnVrac.getSousfamile().getLiblle());
		    					etatstock.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
		    				}else
		    				{
		    					
		    					etatstock.setSousFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getNom());
		    					etatstock.setFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
		    					
		    				}
		    				
		    				
		    				
		    			}else
		    			{
		    				
		    				etatstock.setSousFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getNom());
	    					etatstock.setFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
		    				
		    				
		    			}
		    			
		    			
		    			etatstock.setQuantiteInitial(BigDecimal.ZERO);
		    			etatstock.setPrixInitial(BigDecimal.ZERO);
		    			etatstock.setMontantInitial(BigDecimal.ZERO);
		    			
		    			
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
		    		
		
		    	
		    	
		    	afficher_tableEtatStock(listEtatStockMP);
	    		}
	    		else
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
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
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(112, 9, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(296, 11, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(368, 11, 169, 26);
	    layeredPane.add(dateChooserfin);
	    
	     combomp = new JComboBox();
	     combomp.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		
	 			if(!combomp.getSelectedItem().equals(""))
		 		{
		 			MatierePremier mp=mapMP.get(combomp.getSelectedItem());
		 			txtcodemp.setText(mp.getCode());
		 			
		 		  				 			
		 		}else
		 		{
//txtcodemp.setText(" ");
		 			
		 		}
		 	
	 		
	     	}
	     });
	    combomp.setBounds(1048, 11, 218, 27);
	    layeredPane.add(combomp);
	    
	    combomp.addItem("");
	    int i=0;
		while(i<listMP.size())
		{
			MatierePremier mp=listMP.get(i);
			combomp.addItem(mp.getNom());
			mapMP.put(mp.getNom(), mp);
			mapCodeMP.put(mp.getCode(), mp);
			
			
			i++;
			
		}
	    
	    
	    JLabel label_2 = new JLabel("Libelle :");
	    label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_2.setBounds(1003, 11, 45, 26);
	    layeredPane.add(label_2);
	    
	    txtcodemp = new JTextField();
	    txtcodemp.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		

	     		

     			if(e.getKeyCode()==e.VK_ENTER)
		      		{
     				
     					
		      			if(!txtcodemp.getText().equals(""))
		      			{
		      				MatierePremier mp=mapCodeMP.get(txtcodemp.getText().toUpperCase());
		      				
				    		
				    		if(mp!=null)
				    		{	
				    			combomp.setSelectedItem(mp.getNom());
				    			
				    		}else
				    		{
				    			 JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				    		
				    			
				    		}
		      				
		      				
		      		}else
		      		{
		      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		      			
		      			
		      		}
     				
		      		}
	     			
	    		
	    	}
	    });
	    txtcodemp.setText("");
	    txtcodemp.setColumns(10);
	    txtcodemp.setBounds(900, 11, 93, 26);
	    layeredPane.add(txtcodemp);
	    
	    JLabel lblCodeMp = new JLabel("Code MP :");
	    lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblCodeMp.setBounds(848, 11, 74, 26);
	    layeredPane.add(lblCodeMp);
	    
	    JLabel label_3 = new JLabel("Depot  :");
	    label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_3.setBounds(561, 11, 45, 26);
	    layeredPane.add(label_3);
	    
	     combodepot = new JComboBox();
	    combodepot.setBounds(622, 11, 202, 27);
	    layeredPane.add(combodepot);
	  try {
		  
		 
          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
          dateChooserdebut.setDate(date);
          dateChooserfin.setDate(new Date());
		  
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	    
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		
	     		combomp.setSelectedItem("");
	     		txtcodemp.setText("");
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		
	     		
	     	
	    		
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    		
	    		String titre="Etat Stock Initial MP "+magasin.getLibelle();
	    		String titrefeuilleexcel="Etat Stock Initial MP ";
	    		ExporterTableVersExcel.tabletoexcelMP(tableEtatStock, titre,titrefeuilleexcel);
	    		}else
	    		{

	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		}
	    		/*
	    		

	            int number = workbook.getNumberOfSheets();
	            if (number == 1) {
	                workbook.removeSheetAt(0);
	            }
	            org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet("simple");

	            org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

	            org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
	            ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
	            ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
	            ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

	            Font font1 = (Font) workbook.createFont();
	            ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
	            ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
	            ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);
	          
	            for (int j = 0; j < tableEtatStock.getColumns().size(); j++) {
	                row.createCell(j).setCellValue(tableEtatStock.getColumnName(j).toString());
	       

	            }

	            for (int i = 0; i < tableEtatStock.getRowCount(); i++) {
	                row = spreadsheet.createRow(i + 1);
	                for (int j = 0; j < tableEtatStock.getColumns().size(); j++) {
	                    if (tableEtatStock.getValueAt(i, j) != null) {
	                        row.createCell(j).setCellValue(tableEtatStock.getValueAt(i, j).toString());
	       

	                    } else {
	                        row.createCell(j).setCellValue("");
	                    }
	                }
	            }
	            int rowNum = 0;
	            for (short i = spreadsheet.getRow(0).getFirstCellNum(),
	                    end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
	                CellRangeAddress ca
	                        = new CellRangeAddress(0, rowNum,
	                                spreadsheet.getRow(0).getFirstCellNum(),
	                                spreadsheet.getRow(0).getLastCellNum());
	      
	                rowNum++;

	            }
	            for (short i = spreadsheet.getRow(0).getFirstCellNum(),
	                    end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
	                spreadsheet.autoSizeColumn(i);
	            }
	            try (FileOutputStream fileOut = new FileOutputStream("workbook.xls")) {

	                workbook.write(fileOut);
	                fileOut.flush();
	                fileOut.close();
	                Desktop.getDesktop().open(new File("workbook.xls"));

	            } catch (Exception e) {
					// TODO: handle exception
				}
	    		
	    		
	    		
	    		
	    	*/}
	    	
	
	    	
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setBounds(739, 793, 130, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	 
	    
	    
	    
	    
	    
	    
	  
	    if(utilisateur.getLogin().equals("admin"))
			  {
		    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_MP);
				  int k=0;
			     	 combodepot.addItem("");
			     	while (k<listMagasin.size())
			     	{
			     		Magasin magasin=listMagasin.get(k);
			     		
			     		
				     			combodepot.addItem(magasin.getLibelle());
					     		
					     		mapMagasin.put(magasin.getLibelle(), magasin);
					     	
			     		k++;
			     		
			     	}
			      
			  }else
			  {
				  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
				  if(depot!=null)
				  {
					  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
					  int k=0;
				     	 combodepot.addItem("");
				     	while (k<listMagasin.size())
				     	{
				     		Magasin magasin=listMagasin.get(k);
				     		
				     		
					     			combodepot.addItem(magasin.getLibelle());
						     		
						     		mapMagasin.put(magasin.getLibelle(), magasin);
						     	
				     		k++;
				     		
				     	}
					 
				  }
			  }
		
		}
	
	
	  public void CalculerPrixMoyenFinaleParMouvementStock()
{
		   
		  

		  
		  

  	   // detailTransferStockMPDAO.ViderSession();
  		listDetailTransferStockMP.clear();
  		listDetailTransferStockMPGroupebyMP.clear();
  		listDetailTransferStockMPBytypetransfer.clear();
  		
  		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
  		listMouvementStockMP.clear();
  		boolean trouve=false;
  		MatierePremier mp=mapMP.get(combomp.getSelectedItem());
  		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
  		
  		if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
  		{
  			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
				return;
  		}else
  		{
  			if(magasin!=null)
	    		{
	    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			String d2=sdf.format(dateChooserfin.getDate());
		    			
		    		if(!d1.equals(d2))
		    		{
		    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
		    			{
		    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
		    				return;
		    			}
		    			
		    		}
		    		
		    		if(mp!=null)
		    		{
		    			titre="Mouvement de Stock de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
		    		}else
		    		{
		    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
		    		}
		    		
		    		}
		    		
		    		if(dateChooserdebut.getDate()==null )
		    		{
		    			dateChooserfin.setCalendar(null);
		    			titre="Mouvement de Stock  magasin : "+magasin.getLibelle();
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			titre="Mouvement de Stock de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
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
				    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +quantitefinale +" * "+prixmoyenne +" / "+ "("+quantitefinale +achat+" )" );

		    					prixmoyenne=(achat.multiply(prixAchat).add(quantitefinale.multiply(prixmoyenne))).divide(quantitefinale.add(achat), RoundingMode.HALF_UP);

			    			}else
			    			{
			    				prixmoyenne=BigDecimal.ZERO;

			    			}
		    				
		    				
		    				System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

		    				quantitefinale=(quantitefinale.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
		    				
		    			}else
		    			{
		    				
		    				
		    				if(!initial.add(achat).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
			    			{
				    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +initial +" * "+prixInitial +" / "+ "("+initial +" + "+achat+")" );

		    					prixmoyenne=(achat.multiply(prixAchat).add(initial.multiply(prixInitial))).divide(initial.add(achat), RoundingMode.HALF_UP);

			    			}else
			    			{
			    				prixmoyenne=BigDecimal.ZERO;

			    			}
		    				
		    				
		    				quantitefinale=(initial.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
			    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

		    				
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
		    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp!=null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    			
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			
		    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
		    				
		    			{
		    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()))
		    			{
		    				listMouvementStockMPAfficher.add(listMouvementStockMP.get(i));	
		    			}
		    				
		    			}
		    			
		    		}
		    			
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
		    			{
		    			if(listMouvementStockMPAfficher.get(j).getMatierePremier().getNom().equals(mp.getNom()))
		    			{
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMPAfficher.get(j));
		    			}
		    				
		    			}
		    			
		    		}
		    		
		    			
		    		// detailtransfer entre deux date (date fin null) et par article 
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    		
		    			
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
			    			if(ddbut.equals(d1) && listMouvementStockMP.get(i).getMatierePremier().equals(mp) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    		
		    			
		    			// detailtransfer entre deux date (date fin null)  
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
		    		{
		    			
		    			listMouvementStockMPAfficherMouvementTmp.clear();
	                      
	                      String d1=sdf.format(dateChooserdebut.getDate());
	  	    			
		    			
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
			    			if(ddbut.equals(d1) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			
		    			
		    			// detailtransfer par article
		    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		             
		               
		               
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
			    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			// detailtransfer entre deux date  
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp==null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
		    			
		    				
		    			}
		    			
		    		}
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
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
	
	
	
	void InitialiseTableauDetailMouvementStock()
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matiere Premiere","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Sortie", "Prix Sortie","Montant Sortie","Quantite Dechet", "Prix Dechet","Montant Dechet","Quantite Offre", "Prix Offre","Montant Offre","Quantite Service", "Prix Service","Montant Service","Quantite Dechet Service", "Prix Dechet Service","Montant Dechet Service","Quantite Offre Service", "Prix Offre Service","Montant Offre Service" ,"Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Finale", "Prix Finale","Montant Finale",
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		 tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
	       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
	       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
	       tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
	       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(11).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(12).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(13).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(14).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(15).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(16).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(17).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(18).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(19).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(20).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(21).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(22).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(23).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(24).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(25).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(26).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(27).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(28).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(29).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(30).setPreferredWidth(99);
	     
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
	
	
	
	void afficher_tableEtatStock(List<EtatStockMP> listEtatStockMP)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matiere Premiere","Famille","Sous Famille","Quantite Initial", "Prix Initial","Montant Initial"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		int i=0;
		
		String Famille="";
		String SousFamille="";
		 
		while(i<listEtatStockMP.size())
		{	
		EtatStockMP EtatStockMP=listEtatStockMP.get(i);
		
		Famille="";
		SousFamille="";
		
		if(EtatStockMP.getSousFamille()!=null)	
		{
			SousFamille=EtatStockMP.getSousFamille();
			
			
		}
		
		if(EtatStockMP.getFamille()!=null)	
		{
			Famille=EtatStockMP.getFamille();
			
			
		}
		 
	
			
				//Object []ligne={EtatStockMP.getMp().getNom(),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteOffreService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixOffreService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantOffreService().setScale(6, RoundingMode.HALF_UP)), NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMarge().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP)))+"%"};
			
		
		
		
		Object []ligne={EtatStockMP.getMp().getNom(), Famille ,SousFamille, EtatStockMP.getQuantiteInitial().setScale(6, RoundingMode.HALF_UP),EtatStockMP.getPrixInitial().setScale(6, RoundingMode.HALF_UP),EtatStockMP.getMontantInitial().setScale(6, RoundingMode.HALF_UP)};

				modeleEtatStock.addRow(ligne);
				

			
			i++;
		}
}
	}






