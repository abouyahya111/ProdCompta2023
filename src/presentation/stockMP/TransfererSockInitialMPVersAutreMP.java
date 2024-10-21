package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockInitialMPVersAutreMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockInitialMPVersAutreMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockMP;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockInitialMPVersStockInitialAutreMP;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransfererSockInitialMPVersAutreMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	
	
	//******************************************* Listes Pour Mouvement de Stock Mp **********************************************//
	private List<EtatStockMP> listEtatStockMP =new ArrayList<EtatStockMP>();
	
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
	private List<DetailTransferStockMP> listDetailTransferStockMPFabrique=new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPFabriqueGroupebyMP =new ArrayList<DetailTransferStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFService =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMPService =new ArrayList<DetailTransferStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPAllMP =new ArrayList<DetailTransferStockMP>();
	
	//***************************************************
	
	
	
	
	
	List<DetailTransferProduitFini> listDetailTransferStockPF= new ArrayList<DetailTransferProduitFini>();
	private Map< String, MatierePremier> mapcodeMP = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotSourcetmp = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<MatierePremier> listMatierePremier =new ArrayList<MatierePremier>();
	
	
	TransferStockMP transferStock = new TransferStockMP();
	TransferStockPF transferPF=new TransferStockPF();
	
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();;
	private  JComboBox<String> comboMagasinSource=new JComboBox();;
	private JComboBox<String> comboDepotDestination=new JComboBox();;
	
	private JLabel lblMagasinSource;
	private JLabel lblDpotDestination;
	private JLabel lblMagasinDstination;
	private JButton btnimprimer = new JButton("Bon Transfere MP Produit Fini");
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private StockPFDAO stockpfdao;
	private TransferStockMPDAO transferStockMPDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private DetailTransferProduitFiniDAO detailTransferPFDAO;
	private ArticlesDAO articledao;
 
	private JTextField txtCodeTransfer=new JTextField();
	private JTextField txtcodempSource=new JTextField();
	private JTextField txtquantite;
	private  JComboBox comboarticle = new JComboBox();
 
	private JComboBox combompSource;
	private 	JLayeredPane layerArticle = new JLayeredPane();
	Articles article =new Articles ();
	private   JDateChooser dateTransfereChooser = new JDateChooser();
	boolean detailtransfermp=false;
	boolean detailtransferpf=false;
	private JComboBox combofamille = new JComboBox();
	private JComboBox combosousfamille = new JComboBox();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private FamilleArticlesPFDAO familleArticleDAO;
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	private StockPFDAO stockpfDAO;
	 JComboBox combotypeMP = new JComboBox();
	 private DetailTransferMPDAO detailTransferStockMPDAO;
	 private JTextField txtcodempDestination=new JTextField();
		JComboBox combompDestination = new JComboBox();
		 private SousFamilleEnVracDAO sousFamilleEnvracDAo;
		 private TransferStockInitialMPVersAutreMPDAO transferStockInitialMPVersAutreMPDAO;
		 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public TransfererSockInitialMPVersAutreMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1409, 565);
        try{
        	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
        	detailTransferPFDAO=new DetailTransferProduitFiniDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	articledao=new ArticlesDAOImpl();
        	stockpfdao=new StockPFDAOImpl();
        	transferStockPFDAO=new TransferStockPFDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	transferStockInitialMPVersAutreMPDAO=new TransferStockInitialMPVersAutreMPDAOImpl();
        	sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
         	 
         	stockpfDAO=new StockPFDAOImpl();
        	 imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		String Codedepot= AuthentificationView.utilisateur.getCodeDepot();
		listDepot =new ArrayList<Depot>(); 
		if(Codedepot.equals(CODE_DEPOT_SIEGE)){
				listDepot = depotDAO.findDepotByCodeSaufEnParametre(Codedepot);
		   	} else {
		   	Depot	depot = depotDAO.findByCode(Codedepot);
		   		listDepot.add(depot);
		   	}
    		
    		
	     //	comboDepotDestination.addItem(depot.getLibelle());
	     //	comboDepotSource.addItem(depot.getLibelle());
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(664, 393, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
					       
					      
				  		      
				  		   /*   List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinSource.addItem(magasin.getLibelle());
						  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
						  		      			comboMagasinDestination.addItem(magasin.getLibelle());
						  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
					  		      			
						  		      			j++;
						  		      		}
					  		      }*/
					  		      
					  		      
				  		     
				  		     //	listDepot = depotDAO.findAll();	
					  		
				    		 listMatierePremier=matierePremiereDAO.findAll();
				  		     	comboDepotSource.addItem("");
				  		     	comboDepotDestination.addItem("");
				  		    	Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			if(magasin!=null)
					  		      			{
					  		      				if(depot.getId()!=magasin.getDepot().getId())
					  		      				{
					  		      				mapDepotSource.put(depot.getLibelle(), depot);
						  		      		   
						  		      			mapDepotDestionation.put(depot.getLibelle(), depot);
						  		      			comboDepotSource.addItem(depot.getLibelle());
						  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      				}
					  		      				
					  		      			}else
					  		      			{
					  		      			mapDepotSource.put(depot.getLibelle(), depot);
					  		      		   
					  		      			mapDepotDestionation.put(depot.getLibelle(), depot);
					  		      			comboDepotSource.addItem(depot.getLibelle());
					  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      			}
					  		      			
					  		      			i++;
					  		      		}
					  		      	
					  		      comboDepotSource.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasinSource.removeAllItems();
					  		     	 
					  		     	 	//comboGroupe.addItem("");
					  		     	if(!comboDepotSource.getSelectedItem().equals(""))
					  		     	{

					  		     	Depot	depot  = mapDepotSource.get(comboDepotSource.getSelectedItem().toString());
								  		       
						  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasinSource.addItem(magasin.getLibelle());
									  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
					  		     	}
					  		     	 	 }
					  		     	 	}
					  		     	 });
					  		      
					  		    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
				  		     	 	{
				  		     	Depot depot = mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
						  		       
					  		     listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
							  		      if(listMagasin!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      			comboMagasinDestination.addItem(magasin.getLibelle());
								  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
				  		     	 	}
					  		     	  	  
				  		     	 	 }
				  		     	 	}
				  		     	 });
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 1351, 345);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(7, 44, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(125, 44, 193, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(359, 43, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(452, 44, 236, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(472, 148, 254, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination ");
				  		  lblEquipe.setBounds(359, 147, 165, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("D\u00E9pot Destination");
				  		  lblDpotDestination.setBounds(7, 147, 155, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(125, 148, 193, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination  ");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(289, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(800, 45, 155, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(707, 44, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		  JLabel lblCodeMp = new JLabel("Code MP Source");
				  		  lblCodeMp.setBounds(7, 97, 114, 24);
				  		  layeredPane.add(lblCodeMp);
				  		  
				  		  txtcodempSource = new JTextField();
				  		  txtcodempSource.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
				  		  		
				  		  	if (e.getKeyCode() == e.VK_ENTER)
				  		  	{
				  		  	if(!txtcodempSource.getText().equals(""))
			  		  			
			  		  		{
			  		  			MatierePremier matierepremiere=mapcodeMP.get(txtcodempSource.getText().toUpperCase());
			  		  			if(matierepremiere!=null)
			  		  			{
			  		  				
			  		  				combompSource.setSelectedItem(matierepremiere.getNom());
			  		  			}else
			  		  			{
			  		  				JOptionPane.showMessageDialog(null, "Matiere Premiere introuvable !!!");
			  		  			}
			  		  			
			  		  			
			  		  			
			  		  			
			  		  		}
				  		  	}
				  		  		
				  		  	}
				  		  });
				  		  txtcodempSource.setColumns(10);
				  		  txtcodempSource.setBounds(156, 97, 183, 24);
				  		  layeredPane.add(txtcodempSource);
				  		  
				  		  JLabel lblLibelleMp = new JLabel("MP Source");
				  		  lblLibelleMp.setBounds(372, 97, 72, 24);
				  		  layeredPane.add(lblLibelleMp);
				  		  
				  		   combompSource = new JComboBox();
				  		   combompSource.addItemListener(new ItemListener() {
				  		   	public void itemStateChanged(ItemEvent e) {
				  		   	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		   		 
				  		   		 MatierePremier matierepremiere=mapMatierePremier.get(combompSource.getSelectedItem());
				  		   		 if(matierepremiere!=null)
				  		   		 {
				  		   			 txtcodempSource.setText(matierepremiere.getCode());
				  		   			 
						/*
						 * if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK)) {
						 * txtcodearticle.setText(matierepremiere.getCode()+"_PF");
						 * txtLibelle.setText(matierepremiere.getNom());
						 * 
						 * }else if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK_DECHET)) {
						 * txtcodearticle.setText(matierepremiere.getCode()+"_PFD");
						 * txtLibelle.setText(matierepremiere.getNom()+" (DECHET)");
						 * 
						 * }else if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK_OFFRE)) {
						 * txtcodearticle.setText(matierepremiere.getCode()+"_PFO");
						 * txtLibelle.setText(matierepremiere.getNom()+" (OFFRE)"); }
						 */
				  		   			  
				  		   		 }else
				  		   		 {
				  		   		 txtcodempSource.setText("");
				  		   		 }
				  		   		 
				  		   	 	 }
				  		   		
				  		   	}
				  		   });
				  		  combompSource.setBounds(460, 97, 458, 24);
				  		  layeredPane.add(combompSource);
				  		  
				  		  txtquantite = new JTextField();
				  		  txtquantite.setColumns(10);
				  		  txtquantite.setBounds(1008, 97, 155, 24);
				  		  layeredPane.add(txtquantite);
				  		  AutoCompleteDecorator.decorate(combompSource);
				  		  JLabel lblQuantit = new JLabel("Quantit\u00E9 ");
				  		  lblQuantit.setBounds(944, 96, 63, 24);
				  		  layeredPane.add(lblQuantit);
				  	
				  		
				  		JLabel lblCodeMpDestination = new JLabel("Code MP Destination");
				  		lblCodeMpDestination.setBounds(7, 206, 102, 24);
				  		layeredPane.add(lblCodeMpDestination);
				  		
				  		txtcodempDestination = new JTextField();
				  		txtcodempDestination.addKeyListener(new KeyAdapter() {
				  			@Override
				  			public void keyPressed(KeyEvent e) {
				  				

				  		  		
					  		  	if (e.getKeyCode() == e.VK_ENTER)
					  		  	{
					  		  	if(!txtcodempDestination.getText().equals(""))
				  		  			
				  		  		{
				  		  			MatierePremier matierepremiere=mapcodeMP.get(txtcodempDestination.getText().toUpperCase());
				  		  			if(matierepremiere!=null)
				  		  			{
				  		  				
				  		  				combompDestination.setSelectedItem(matierepremiere.getNom());
				  		  			}else
				  		  			{
				  		  				JOptionPane.showMessageDialog(null, "Matiere Premiere introuvable !!!");
				  		  			}
				  		  			
				  		  			
				  		  			
				  		  			
				  		  		}
					  		  	}
					  		  		
					  		  	
				  				
				  			}
				  		});
				  		txtcodempDestination.setColumns(10);
				  		txtcodempDestination.setBounds(163, 206, 174, 24);
				  		layeredPane.add(txtcodempDestination);
				  		
				  		JLabel lblMpDestination = new JLabel("MP Destination");
				  		lblMpDestination.setBounds(381, 206, 72, 24);
				  		layeredPane.add(lblMpDestination);
				  		
				  		  combompDestination = new JComboBox();
				  		  combompDestination.addItemListener(new ItemListener() {
				  		  	public void itemStateChanged(ItemEvent e) {
				  		  		

					  		   	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		   		 
					  		   		 MatierePremier matierepremiere=mapMatierePremier.get(combompDestination.getSelectedItem());
					  		   		 if(matierepremiere!=null)
					  		   		 {
					  		   			 txtcodempDestination.setText(matierepremiere.getCode());
					  		   			 
							/*
							 * if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK)) {
							 * txtcodearticle.setText(matierepremiere.getCode()+"_PF");
							 * txtLibelle.setText(matierepremiere.getNom());
							 * 
							 * }else if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK_DECHET)) {
							 * txtcodearticle.setText(matierepremiere.getCode()+"_PFD");
							 * txtLibelle.setText(matierepremiere.getNom()+" (DECHET)");
							 * 
							 * }else if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK_OFFRE)) {
							 * txtcodearticle.setText(matierepremiere.getCode()+"_PFO");
							 * txtLibelle.setText(matierepremiere.getNom()+" (OFFRE)"); }
							 */
					  		   			  
					  		   		 }else
					  		   		 {
					  		   		 txtcodempDestination.setText("");
					  		   		 }
					  		   		 
					  		   	 	 }
					  		   		
				  		  	}
				  		  });
				  		combompDestination.setBounds(460, 206, 458, 24);
				  		layeredPane.add(combompDestination);
	
				  	  AutoCompleteDecorator.decorate(combompDestination);
	
		
		
		JButton btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(dateTransfereChooser.getDate()==null)
					{
						JOptionPane.showMessageDialog(null, "Veuillez Entrer la date de transfere SVP");
						return;
					}

					String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
					if(comboDepotSource.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le depot source SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
						

								} else if(comboMagasinSource.getSelectedItem().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez choisir le Magasin source SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
									
								}
								else if(comboDepotSource.getSelectedItem().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez choisir le depot destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
									
								}else if(comboMagasinDestination.getSelectedItem().equals(""))
								{
									
									JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}else if(dateTransfere.equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez choisir la date de transfere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}else if(txtcodempSource.getText().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le code Matiere Premiere Source SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}else if(combompSource.getSelectedItem().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez choisir la matiere  Premiere Source SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}else if(txtcodempDestination.getText().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le code Matiere Premiere Destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}else if(combompDestination.getSelectedItem().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez choisir la matiere  Premiere Destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}else if(txtquantite.getText().equals(""))
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer la quantitÈ matiere premiere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}
								else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
								{
									JOptionPane.showMessageDialog(null, "la quantitÈ matiere premiere doit etre supÈrieur ‡ 0 SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								}else
								{
									
									Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
									Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
									
									MatierePremier matierePremierSource=mapMatierePremier.get(combompSource.getSelectedItem().toString());
									MatierePremier matierePremierDestination=mapMatierePremier.get(combompDestination.getSelectedItem().toString());
									
									
									if(magasinSource==null)
									{
										JOptionPane.showMessageDialog(null, "Veuillez choisir le Magasin source SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									if(magasinDestination==null)
									{
										JOptionPane.showMessageDialog(null, "Veuillez choisir le Magasin Destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
									}
									
									DetailTransferStockMP detailTransferStockMP=detailTransferStockMPDAO.findInitialeMPByMPByMagasinBayDate( matierePremierSource, magasinSource, dateTransfereChooser.getDate(), dateTransfereChooser.getDate());
									 
									if(detailTransferStockMP!=null)
									{
										
										if(detailTransferStockMP.getQuantite().compareTo(new BigDecimal(txtquantite.getText()))<0)
										{
											JOptionPane.showMessageDialog(null, "Quantite Initiale est insifusant !!!","Erreur",JOptionPane.ERROR_MESSAGE);
											return;
											
										}else
										{
											
											DetailTransferStockMP detailTransferStockMPTmpImp=detailTransferStockMPDAO.findInitialeMPByMPByMagasinBayDate( matierePremierDestination, magasinDestination, dateTransfereChooser.getDate(), dateTransfereChooser.getDate());

											
											if(detailTransferStockMPTmpImp!=null)
											{
												JOptionPane.showMessageDialog(null, matierePremierDestination.getNom() +" a dÈja un Initial D'Annee SÈlectionner","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
											}
											
											
											
											
											CalculerStockFinale();
											
											boolean StockInsifusant=false;
											BigDecimal prix=BigDecimal.ZERO;
											
											for(int j=0;j<listEtatStockMP.size();j++)
											{
												
												if(listEtatStockMP.get(j).getMp().getId()==matierePremierSource.getId())
												{
													prix=listEtatStockMP.get(j).getPrixInitial();
													if(listEtatStockMP.get(j).getQuantiteFinale().compareTo(new BigDecimal(txtquantite.getText()))<0)
													{
														
														StockInsifusant=true;
														
													}
													
												}
												
											}
											
											
											if(StockInsifusant==true)
											{
												JOptionPane.showMessageDialog(null, "Quantite Finale est insifusant !!!","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
											}else
											{
												
											TransferStockInitialMPVersStockInitialAutreMP transferStockInitialMPVersStockInitialAutreMP=new TransferStockInitialMPVersStockInitialAutreMP();
											
											transferStockInitialMPVersStockInitialAutreMP.setCodeTransfer(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_INITIAL));	
											transferStockInitialMPVersStockInitialAutreMP.setCreerPar(AuthentificationView.utilisateur);
											transferStockInitialMPVersStockInitialAutreMP.setDateCreer(new Date());
											transferStockInitialMPVersStockInitialAutreMP.setDateTransfer(dateTransfereChooser.getDate());
											transferStockInitialMPVersStockInitialAutreMP.setDepotDestination(magasinDestination.getDepot());
											transferStockInitialMPVersStockInitialAutreMP.setDepotSource(magasinSource.getDepot());
											transferStockInitialMPVersStockInitialAutreMP.setMagasinDestination(magasinDestination);
											transferStockInitialMPVersStockInitialAutreMP.setMagasinSouce(magasinSource);
											transferStockInitialMPVersStockInitialAutreMP.setMpDestination(matierePremierDestination);
											transferStockInitialMPVersStockInitialAutreMP.setMpSource(matierePremierSource);
											transferStockInitialMPVersStockInitialAutreMP.setPrixUnitaire(prix);
											transferStockInitialMPVersStockInitialAutreMP.setQuantite(new BigDecimal(txtquantite.getText()));
											transferStockInitialMPVersAutreMPDAO.add(transferStockInitialMPVersStockInitialAutreMP);
											
											detailTransferStockMP.setQuantite(detailTransferStockMP.getQuantite().subtract(new BigDecimal(txtquantite.getText())));
											detailTransferStockMPDAO.edit(detailTransferStockMP);
											
											TransferStockMP transferStockMP=new TransferStockMP();
											transferStockMP.setCodeTransfer(transferStockInitialMPVersStockInitialAutreMP.getCodeTransfer());
											transferStockMP.setCreerPar(AuthentificationView.utilisateur);
											transferStockMP.setDate(new Date());
											transferStockMP.setDateTransfer(dateTransfereChooser.getDate());
											transferStockMP.setDepot(magasinDestination.getDepot());
											transferStockMP.setStatut(ETAT_TRANSFER_STOCK_INITIAL);
											transferStockMPDAO.add(transferStockMP);
											
											DetailTransferStockMP detailTransferStockMPTmp=new DetailTransferStockMP();
											detailTransferStockMPTmp.setMatierePremier(matierePremierDestination);
											detailTransferStockMPTmp.setQuantite(new BigDecimal(txtquantite.getText()));
											detailTransferStockMPTmp.setPrixUnitaire(prix);
											detailTransferStockMPTmp.setMagasinDestination(magasinDestination);
											detailTransferStockMPTmp.setTransferStockMP(transferStockMP);											
											detailTransferStockMPDAO.add(detailTransferStockMPTmp);
											
											JOptionPane.showMessageDialog(null, "Matiere Premeiere Transferer Avec Succee ","Information",JOptionPane.INFORMATION_MESSAGE);
											intialiser();
											
											}
											
											
										}
										
										
									}else
									{
										JOptionPane.showMessageDialog(null, matierePremierSource.getNom() +" Ne contient auccun Initial dans le Magasin "+magasinSource.getLibelle() +" Au date : "+dateTransfere+" ","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									
									
									
								}
					
					
				
				
				}
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(473, 393, 165, 23);
		add(btnValiderTransfer);
		
	 
		combompSource.addItem("");
		combompDestination.addItem("");
		listMatierePremier=matierePremiereDAO.findAll();
		
		 for(int d=0;d<listMatierePremier.size();d++)
		 {
			 MatierePremier mp=listMatierePremier.get(d);
			 combompSource.addItem(mp.getNom());
			 combompDestination.addItem(mp.getNom());
			 mapcodeMP.put(mp.getCode(), mp);
			 mapMatierePremier.put(mp.getNom(), mp);
			 
		 }
	  	
		 combompSource.setSelectedItem("");
		 combompDestination.setSelectedItem("");
	  	
	  	
	  	
				  		 
	}
	

	
	void intialiser()
	{

		comboDepotSource.setSelectedItem("");
		comboDepotDestination.setSelectedItem("");
		comboMagasinSource.setSelectedItem("");
		comboMagasinDestination.setSelectedItem("");
		dateTransfereChooser.setDate(null);
		txtcodempDestination.setText("");
		txtcodempSource.setText("");
		combompSource.setSelectedItem("");
		combompDestination.setSelectedItem("");
	    txtquantite.setText("");
	
	}



void CalculerStockFinale()
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
     listDetailTransferStockMPFabrique.clear();
     listDetailTransferStockMPFabriqueGroupebyMP.clear();
     
     
     
     
	 listDetailTransferStockMPAllMP.clear();
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
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
	BigDecimal montantFabrique=new BigDecimal(0);
	BigDecimal quantiteFabriquer=new BigDecimal(0);
	
	
	boolean trouve=false;
	MatierePremier mp=mapMatierePremier.get(combompSource.getSelectedItem());
	Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
	
	String patternYear = "yyyy";
		String patternDate = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
		SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);
		
		Date dateChooserfin=null;
		Date dateChooserdebut=null;
		String year = simpleDateFormatyear.format(dateTransfereChooser.getDate());
			
			try {
				dateChooserfin=simpleDateFormatDate.parse(year+"-12-31");
				dateChooserdebut=simpleDateFormatDate.parse(year+"-01-01");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	if(magasin!=null)
	{
 	 
		
		
	 
		
		
		listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_INITIAL,magasin);
		listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
		
		listDetailTransferStockMPAchatGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
		
		listDetailTransferStockMPSortie=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
		listDetailTransferStockMPSortieGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
		
		listDetailTransferStockMPAvoir=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
		listDetailTransferStockMPAvoirGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
		
		listDetailTransferStockMPChargeSupp=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
		listDetailTransferStockMPChargeSuppGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
		
		listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutService(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
		listDetailTransferStockMPServiceGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
		

		listDetailTransferStockMPVente=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
		listDetailTransferStockMPVenteGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
		
		
		
		listDetailTransferStockMPTransferMPPF=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin);
		listDetailTransferStockMPTransferMPPFGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin);
		
		
		listDetailTransferStockMPTransferMPPFProduction=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
		listDetailTransferStockMPTransferMPPFProductionGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
		
		listDetailTransferStockMPTransferMPPFService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
		listDetailTransferStockMPTransferMPPFGroupebyMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
		
		listDetailTransferStockMPFabrique=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_FABRIQUE,magasin);
		listDetailTransferStockMPFabriqueGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateChooserdebut, dateChooserfin, mp, ETAT_TRANSFER_STOCK_FABRIQUE,magasin);
		
		
		
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
			etatstock.setQuantiteFabriquer(BigDecimal.ZERO);
			etatstock.setPrixFabriquer(BigDecimal.ZERO);
			etatstock.setMontantFabriquer(BigDecimal.ZERO);
			
			
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
	
	
	
	
	// calculer le prix moyen et quantite Fabriquer
	
	
for(int j=0;j<listDetailTransferStockMPFabriqueGroupebyMP.size();j++)
{
	montantachat=new BigDecimal(0);
	quantiteTotalachat=new BigDecimal(0);
	
for(int k=0;k<listDetailTransferStockMPFabrique.size();k++)
{
	
	if(listDetailTransferStockMPFabriqueGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPFabrique.get(k).getMatierePremier()))
	{
		
		if(listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		{
			montantachat=montantachat.add(listDetailTransferStockMPFabrique.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPFabrique.get(k).getQuantite()));
			quantiteTotalachat=quantiteTotalachat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPFabrique.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
		}else
		{
			montantachat=montantachat.add(listDetailTransferStockMPFabrique.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPFabrique.get(k).getQuantite()));
			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockMPFabrique.get(k).getQuantite());
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
			if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPFabriqueGroupebyMP.get(j).getMatierePremier()))
			{
				EtatStockMP etatstockmp=listEtatStockMP.get(i);
				
				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
					etatstockmp.setQuantiteFabriquer (quantiteTotalachat.setScale(0, RoundingMode.CEILING));
    				etatstockmp.setPrixFabriquer(montantachat.divide(quantiteTotalachat.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
    				etatstockmp.setMontantFabriquer(etatstockmp.getQuantiteFabriquer().multiply(etatstockmp.getPrixFabriquer()));
    				listEtatStockMP.set(i, etatstockmp);
					
					
				}else
				{
					
    				etatstockmp.setQuantiteFabriquer(quantiteTotalachat);
    				etatstockmp.setPrixFabriquer(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
    				etatstockmp.setMontantFabriquer(etatstockmp.getQuantiteFabriquer().multiply(etatstockmp.getPrixFabriquer()));
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
				etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteFabriquer().setScale(0, RoundingMode.CEILING))).subtract(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteAvoir().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING)))));
    			quantiteTotalFinale=(quantiteTotalFinale.setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteFabriquer().setScale(0, RoundingMode.CEILING))));
    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP)).add(etatstockmp.getMontantFabriquer().setScale(6,RoundingMode.HALF_UP))));
    			
    			if(listMouvementStockMPAfficherMouvementTmp.size()!=0)
	    		{
	    			for(int l=0;l<listMouvementStockMPAfficherMouvementTmp.size();l++)
	    			{
	    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom()))
	    				{
	    					if(listMouvementStockMPAfficherMouvementTmp.get(l).getDateCreation().compareTo(dateChooserfin)<=0 )
	    					{
	    						prixMoyen=listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb();
		    					System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficherMouvementTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficherMouvementTmp.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficherMouvementTmp.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb() ) ;

	    					}
	    									    					
	    				}
	    				
	    			}
	    			
	    		}else
	    		{
	    			for(int l=0;l<listMouvementStockMPAfficher.size();l++)
	    			{
	    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom()))
	    				{
	    					if(listMouvementStockMPAfficher.get(l).getDateCreation().compareTo(dateChooserfin)<=0 )
	    					{
	    						prixMoyen=listMouvementStockMPAfficher.get(l).getPrixFinaldb();
		    					System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficher.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficher.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficher.get(l).getPrixFinaldb() ) ;
		    					
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
				etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()).add(etatstockmp.getQuantiteFabriquer())).subtract(etatstockmp.getQuantiteSortie().add(etatstockmp.getQuantiteService()).add(etatstockmp.getQuantiteAvoir().add(etatstockmp.getQuantiteOffreService()).add(etatstockmp.getQuantiteOffreProduction()))));
    			quantiteTotalFinale=(quantiteTotalFinale.add(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()).add(etatstockmp.getQuantiteFabriquer())));
    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP)).add(etatstockmp.getMontantFabriquer().setScale(6,RoundingMode.HALF_UP))));
    			
    			if(listMouvementStockMPAfficherMouvementTmp.size()!=0)
	    		{
	    			for(int l=0;l<listMouvementStockMPAfficherMouvementTmp.size();l++)
	    			{
	    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom()))
	    				{
	    					if(listMouvementStockMPAfficherMouvementTmp.get(l).getDateCreation().compareTo(dateChooserfin)<=0 )
	    					{
	    						prixMoyen=listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb();
		    					System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficherMouvementTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficherMouvementTmp.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficherMouvementTmp.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb() ) ;
		    					
	    					}
	    					
	    				}
	    				
	    			}
	    			
	    		}else
	    		{
	    			for(int l=0;l<listMouvementStockMPAfficher.size();l++)
	    			{
	    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom()))
	    				{
	    					if(listMouvementStockMPAfficher.get(l).getDateCreation().compareTo(dateChooserfin)<=0 )
	    					{
	    						prixMoyen=listMouvementStockMPAfficher.get(l).getPrixFinaldb();
		    					System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficher.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficher.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficher.get(l).getPrixFinaldb() ) ;
		    				
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
	listMouvementStockMP.clear();
	boolean trouve=false;
	MatierePremier mp=mapMatierePremier.get(combompSource.getSelectedItem());
	Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
	String patternYear = "yyyy";
	String patternDate = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
	SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);
	
	Date dateChooserfin=null;
	Date dateChooserdebut=null;
	String year = simpleDateFormatyear.format(dateTransfereChooser.getDate());
		
		try {
			dateChooserfin=simpleDateFormatDate.parse(year+"-12-31");
			dateChooserdebut=simpleDateFormatDate.parse(year+"-01-01");
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	
	if(dateChooserdebut==null && dateChooserfin==null)
	{
		JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
			return;
	}else
	{
		if(magasin!=null)
  		{
  			if(dateChooserdebut!=null && dateChooserfin!=null)
	    		{
	    			String d1=sdf.format(dateChooserdebut);
	    			String d2=sdf.format(dateChooserfin);
	    			
	    		if(!d1.equals(d2))
	    		{
	    			if(dateChooserfin.compareTo(dateChooserdebut)<0)
	    			{
	    				JOptionPane.showMessageDialog(null, "date de fin doit etre supÈrieur au date debut SVP !!!");
	    				return;
	    			}
	    			
	    		}
	    		
	    	 
	    		
	    		}
	    		
	    	 
	    		
	    		
	    		listDetailTransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPOrderByDateTransfer(magasin);
	    		listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByDateTransferByMP(magasin);
	    		listDetailTransferStockMPBytypetransfer=detailTransferStockMPDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
	    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.ETAT_TRANSFER_STOCK_CHARGE,ETAT_TRANSFER_STOCK_CHARGE_SUPP,ETAT_TRANSFER_STOCK_VENTE,ETAT_TRANSFER_STOCK_AVOIR,ETAT_TRANSFER_STOCK_SERVICE,ETAT_TRANSFER_STOCK_SORTIE_PF,ETAT_TRANSFER_STOCK_FABRIQUE};
	    		BigDecimal achat=BigDecimal.ZERO;
	    		BigDecimal fabrique=BigDecimal.ZERO;
	    		BigDecimal prixAchat=BigDecimal.ZERO;
	    		BigDecimal prixFabrique=BigDecimal.ZERO;
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
	    			fabrique=new BigDecimal(0);
	    			prixFabrique=new BigDecimal(0);
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
		    			fabrique=new BigDecimal(0);
		    			prixFabrique=new BigDecimal(0);
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
	    				
	    			}	else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_FABRIQUE))
	    			{
	    				
	                     if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
	                         {
	                  	   if(!fabrique.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
	                  	   {
	                      	   prixFabrique=(prixFabrique.multiply(fabrique).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMP.get(j).getPrixUnitaire()))).divide(fabrique.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING)), RoundingMode.HALF_UP);

	                  	   }else
	                  	   {
	                  		 prixFabrique=BigDecimal.ZERO;
	                  	   }
	                  	   fabrique=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
	                  	   
	                          }else
	                          {
	                          	if(!listDetailTransferStockMP.get(j).getQuantite().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
	                          	{
	                          		prixFabrique=(prixFabrique.multiply(fabrique).add(listDetailTransferStockMP.get(j).getQuantite().multiply(listDetailTransferStockMP.get(j).getPrixUnitaire()))).divide(fabrique.add(listDetailTransferStockMP.get(j).getQuantite()), RoundingMode.HALF_UP);

	                          	}else
	                          	{
	                          		prixFabrique=BigDecimal.ZERO;

	                          	}
	                          	

	                          	fabrique=listDetailTransferStockMP.get(j).getQuantite();
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
		    			

	    				if(!quantitefinale.add(achat.add(fabrique)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    			{
			    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +quantitefinale +" * "+prixmoyenne +" / "+ "("+quantitefinale +achat+" )" );

	    					prixmoyenne=(achat.multiply(prixAchat).add(fabrique.multiply(prixFabrique)).add(quantitefinale.multiply(prixmoyenne))).divide(quantitefinale.add(achat).add(fabrique), RoundingMode.HALF_UP);

		    			}else
		    			{
		    				prixmoyenne=BigDecimal.ZERO;

		    			}
	    				
	    				
	    				System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

	    				quantitefinale=(quantitefinale.add(achat).add(fabrique)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
	    				
	    			}else
	    			{
	    				
	    				
	    				if(!initial.add(achat).add(fabrique).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    			{
			    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +initial +" * "+prixInitial +" / "+ "("+initial +" + "+achat+")" );

	    					prixmoyenne=(achat.multiply(prixAchat).add(initial.multiply(prixInitial)).add(fabrique.multiply(prixFabrique))).divide(initial.add(achat).add(fabrique), RoundingMode.HALF_UP);

		    			}else
		    			{
		    				prixmoyenne=BigDecimal.ZERO;

		    			}
	    				
	    				
	    				quantitefinale=(initial.add(achat).add(fabrique)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
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
	    			mouvementstockMP.setFabriquer(fabrique);
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
	    			mouvementstockMP.setStockFinaldb((mouvementstockMP.getInitial().add(mouvementstockMP.getAchat()).add(mouvementstockMP.getFabriquer())).subtract(mouvementstockMP.getVente().add(mouvementstockMP.getProduction().add(mouvementstockMP.getAvoir().add(mouvementstockMP.getService()).add(mouvementstockMP.getTransfert())))));
	    			listMouvementStockMP.add(mouvementstockMP);
	    			
	    		}
	    		
	    		// detailtransfer entre deux date et par article
	    		if(dateChooserdebut!=null && dateChooserfin!=null && mp!=null)
	    		{
	    			listMouvementStockMPAfficher.clear();
	    			listMouvementStockMPAfficherMouvementTmp.clear();
	    			
	    		
	    		for(int i=0;i<listMouvementStockMP.size();i++)	
	    		{
	    			String ddebut=sdf.format(dateChooserdebut);
	    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
	    			
	    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut) ==true || ddebutTmp.equals(ddebut)   )
	    				
	    			{
	    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()))
	    			{
	    				listMouvementStockMPAfficher.add(listMouvementStockMP.get(i));	
	    			}
	    				
	    			}
	    			
	    		}
	    			
	    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
	    		{
	    			
	    			String dfin=sdf.format(dateChooserfin);
	    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
	    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin)==true || dfinTmp.equals(dfin) )
	    			{
	    			if(listMouvementStockMPAfficher.get(j).getMatierePremier().getNom().equals(mp.getNom()))
	    			{
	    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMPAfficher.get(j));
	    			}
	    				
	    			}
	    			
	    		}
	    		
	    			
	    		// detailtransfer entre deux date (date fin null) et par article 
	    			
	    		}else if(dateChooserdebut!=null && dateChooserfin==null && mp!=null)
	    		{
	    			listMouvementStockMPAfficherMouvementTmp.clear();
	    			String d1=sdf.format(dateChooserdebut);
	    		
	    			
	    			for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
	    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			if(ddbut.equals(d1) && listMouvementStockMP.get(i).getMatierePremier().equals(mp) )
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
		    			
		    				
		    			}
		    			
		    		}
	    			
	    		
	    			
	    			// detailtransfer entre deux date (date fin null)  
	    		}else if(dateChooserdebut!=null && dateChooserfin==null && mp==null)
	    		{
	    			
	    			listMouvementStockMPAfficherMouvementTmp.clear();
                    
                    String d1=sdf.format(dateChooserdebut);
	    			
	    			
	    			for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
	    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			if(ddbut.equals(d1) )
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
		    			
		    				
		    			}
		    			
		    		}
	    			
	    			
	    			
	    			
	    			// detailtransfer par article
	    		}else if(dateChooserdebut==null && dateChooserfin==null && mp!=null)
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
	    			
	    		}else if(dateChooserdebut!=null && dateChooserfin!=null && mp==null)
	    		{
	    			listMouvementStockMPAfficher.clear();
	    			listMouvementStockMPAfficherMouvementTmp.clear();
	    		
	    		for(int i=0;i<listMouvementStockMP.size();i++)	
	    		{
	    			String ddebut=sdf.format(dateChooserdebut);
	    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
	    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut) ==true || ddebutTmp.equals(ddebut))
	    			{
	    			
	    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
	    			
	    				
	    			}
	    			
	    		}
	    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
	    		{
	    			
	    			String dfin=sdf.format(dateChooserfin);
	    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
	    			
	    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin)==true || dfinTmp.equals(dfin)  )
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
