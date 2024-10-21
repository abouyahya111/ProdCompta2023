package presentation.stockPF;

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
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailFacturePF;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransfererPFProduitFini extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	
	
	
	List<DetailTransferProduitFini> listDetailTransferStockPFSource= new ArrayList<DetailTransferProduitFini>();
	List<DetailTransferProduitFini> listDetailTransferStockPFDestination= new ArrayList<DetailTransferProduitFini>();
	private Map< String, MatierePremier> mapcodeMP = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	
	private Map< String, Integer> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotSourcetmp = new HashMap<>();
	private Map< String, Integer> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<MatierePremier> listMatierePremier =new ArrayList<MatierePremier>();
	
	
	TransferStockMP transferStock = new TransferStockMP();
	 
	
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
	private Depot depot = new Depot();
	private JTextField txtCodeTransfer;
	private JTextField txtquantite;
	private  JComboBox comboarticle = new JComboBox();
	private JComboBox comboarticleSource;
	private   JComboBox combomp = new JComboBox();
	private 	JLayeredPane layerArticle = new JLayeredPane();
	Articles article =new Articles ();
	private   JDateChooser dateTransfereChooser = new JDateChooser();
	boolean detailtransfermp=false;
	boolean detailtransferpf=false;
	 
	private JComboBox combofamilleSource= new JComboBox();
	 
	private JComboBox combosousfamilleSource= new JComboBox();
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
	 private JTextField txtcodearticleSource;
	 private JTextField txtcodearticleDestination;
		JComboBox combosousfamilleDestination = new JComboBox();
		JComboBox combofamilleDestination = new JComboBox();
		JComboBox comboarticleDestination = new JComboBox();
		private DetailTransferProduitFiniDAO detailTransferStockPFdao;
		 BigDecimal StockFinale=BigDecimal.ZERO;
		  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
		  private DetailPrixArticleDAO detailPrixArticleDAO;  
		  private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
		  
			//**********************************************************************listes Mouvemement Stock***************************************************
			
			private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
			private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();
			private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
			private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
			private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
			private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();
			
			
			
			//*************************************************************************************************************************************************  
		  
		  
		  
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public TransfererPFProduitFini() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1295, 565);
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
        	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
         	stockpfDAO=new StockPFDAOImpl();
        	detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
        	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
        	detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
        	 imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
        		
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		String Codedepot= AuthentificationView.utilisateur.getCodeDepot();
		listDepot =new ArrayList<Depot>(); 
		if(Codedepot.equals(CODE_DEPOT_SIEGE)){
				listDepot = depotDAO.findDepotByCodeSaufEnParametre(Codedepot);
		   	} else {
		   		depot = depotDAO.findByCode(Codedepot);
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
				  		     btnIntialiserOF.setBounds(408, 310, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
					  		  txtCodeTransfer = new JTextField();
					  		util.Utils.copycoller(txtCodeTransfer);
					       
					      
				  		      
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
					  		      				mapDepotSource.put(depot.getLibelle(), i);
						  		      		    mapDepotSourcetmp.put(depot.getLibelle(), depot);
						  		      			mapDepotDestionation.put(depot.getLibelle(), i);
						  		      			comboDepotSource.addItem(depot.getLibelle());
						  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      				}
					  		      				
					  		      			}else
					  		      			{
					  		      			mapDepotSource.put(depot.getLibelle(), i);
					  		      		    mapDepotSourcetmp.put(depot.getLibelle(), depot);
					  		      			mapDepotDestionation.put(depot.getLibelle(), i);
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
					  		     	 Depot depot =new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	if(!comboDepotSource.getSelectedItem().equals(""))
						  		     	  	   	 depot =listDepot.get(mapDepotSource.get(comboDepotSource.getSelectedItem()));
								  		       
						  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
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
					  		     	 });
					  		      
					  		    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
					  		     	  	   	 depot =listDepot.get(mapDepotDestionation.get(comboDepotDestination.getSelectedItem()));
							  		       
					  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
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
				  		     	 });
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 1276, 241);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 34, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(103, 34, 193, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 74, 193, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(491, 74, 200, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination produit Fini");
				  		  lblEquipe.setBounds(316, 73, 165, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("D\u00E9pot Destination Produit Fini");
				  		  lblDpotDestination.setBounds(316, 33, 155, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(491, 34, 200, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination Produit Fini");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(289, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		
				  		  txtCodeTransfer.setBounds(806, 75, 155, 24);
				  		  layeredPane.add(txtCodeTransfer);
				  		  txtCodeTransfer.setColumns(10);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(803, 35, 155, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(710, 34, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		  JLabel label = new JLabel("Code Transafert ");
				  		  label.setBounds(720, 74, 94, 24);
				  		  layeredPane.add(label);
				  		  
				  		  txtquantite = new JTextField();
				  		  txtquantite.setColumns(10);
				  		  txtquantite.setBounds(1089, 111, 177, 24);
				  		  layeredPane.add(txtquantite);
				  		  
				  		  JLabel lblQuantit = new JLabel("Quantit\u00E9 ");
				  		  lblQuantit.setBounds(1025, 110, 94, 24);
				  		  layeredPane.add(lblQuantit);
				  		
				  		JLabel label_4 = new JLabel("Famille Article :");
				  		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		label_4.setBounds(10, 111, 87, 24);
				  		layeredPane.add(label_4);
				  		
				  		 combofamilleSource = new JComboBox();
				  		combofamilleSource.addItemListener(new ItemListener() {
				  			public void itemStateChanged(ItemEvent e) {
				  				

				        		
				        		 if(e.getStateChange() == ItemEvent.SELECTED)
				     	 		 {
				        			 if(!combofamilleSource.getSelectedItem().equals(""))
				        			 {
				        				 
				        					FamilleArticlePF famille=mapfamille.get(combofamilleSource.getSelectedItem());
				    		        		if(famille!=null)
				    		        		{
				    		        			combosousfamilleSource.removeAllItems();
				    		        			 combosousfamilleSource.addItem("");
				    		        			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
				    		        			for(int i=0;i<listSousFamilleArticle.size();i++)
				    		        			{
				    		        			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
				    		        			combosousfamilleSource.addItem(sousfamille.getLiblle());
				    		        			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
				    		        				
				    		        			}
				    		        			
				    		        		}else
				    		        		{
				    		        			 combosousfamilleSource.removeAllItems();
				    		        		}
				    		        			
				        				 
				        			 }else
				        			 {
				        				//combosousfamille.removeAllItems(); 
				        			 }
				        			 
				        			
				        	 
				        			
				        			 
				     	 		 }
				     	 			 
				        		
				        		
				        	
				  				
				  				
				  			}
				  		});
				  		combofamilleSource.setSelectedIndex(-1);
				  		combofamilleSource.setBounds(92, 112, 167, 24);
				  		layeredPane.add(combofamilleSource);
				  		
				  		JLabel label_5 = new JLabel("Sous Famille :");
				  		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		label_5.setBounds(270, 110, 87, 24);
				  		layeredPane.add(label_5);
				  		
				  		 combosousfamilleSource = new JComboBox();
				  		 combosousfamilleSource.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {


        		

       		 if(e.getStateChange() == ItemEvent.SELECTED)
	 		 {
       			 if(!combosousfamilleSource.getSelectedItem().equals(""))
       			 {
       				
       				 SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamilleSource.getSelectedItem());
       				 Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
       				 if(magasin!=null)
       				 {
       					 if(sousfamille!=null)
	        			 {
	        				 comboarticleSource.removeAllItems();
	        				 comboarticleSource.addItem("");
	        			List<StockPF>	 listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
	        				 for(int i=0;i<listArticleStockPF.size();i++)
	        				 {
	        					 
	        					Articles article= listArticleStockPF.get(i).getArticles();
	        					
	        					if(sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
	        					{
	        						/*
	        						if(!article.getLiblle().contains("(OFFRE)"))
	        						{
	        						*/	
			        					comboarticleSource.addItem(article.getLiblle());
			        					 mapArticle.put(article.getLiblle(), article);
			        					 mapCodeArticle.put(article.getCodeArticle(), article);
	        							
	        						/*	
	        						}*/
	        						
	        						
	        					}else
	        					{

	        						comboarticleSource.addItem(article.getLiblle());
		        					 mapArticle.put(article.getLiblle(), article);
		        					 mapCodeArticle.put(article.getCodeArticle(), article);
	        						
	        					}
	        					
	        				 }
	        				 
	        			 }else
	        			 {
	        				 comboarticleSource.removeAllItems();
	        				 txtcodearticleSource.setText("");
	        			 }
       				   
       				 }
       			
       			 }else
       			 {
       				 comboarticleSource.removeAllItems();
       				 txtcodearticleSource.setText("");
       			 }
       			  
	 		 }
       		
       		
       		
       	
			
			
			
			}
				  		 });
				  		combosousfamilleSource.setSelectedIndex(-1);
				  		combosousfamilleSource.setBounds(350, 111, 160, 24);
				  		layeredPane.add(combosousfamilleSource);
	
		
	
		
		
		JButton btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				detailtransfermp=false;
				detailtransferpf=false;
				
				try {
					
					
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
				}
				
			 
			 
				else if(txtquantite.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer la quantité matiere premiere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
				{
					JOptionPane.showMessageDialog(null, "la quantité   doit etre supérieur à 0 SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(txtcodearticleSource.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le code article Source SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(comboarticleSource.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner l'Article Source  SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(txtcodearticleDestination.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le code article Destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(comboarticleDestination.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner l'Article  Destination SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				else {
					
					
					
					Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
					Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
				SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamilleSource.getSelectedItem());
				SousFamilleArticlePF sousfamilleDestination=mapsousfamille.get(combosousfamilleDestination.getSelectedItem());
				
						Articles articleSource=articledao.findByCode(txtcodearticleSource.getText());
						Articles articleDestination=articledao.findByCode(txtcodearticleDestination.getText());
						
						if(articleSource==null || articleDestination==null)
						{
							
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
							
						}
						
						if(sousfamille==null || sousfamilleDestination==null)
						{
							
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner Sous Famille SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
							
						}
						
						listDetailTransferStockPFSource= new ArrayList<DetailTransferProduitFini>();
						listDetailTransferStockPFDestination= new ArrayList<DetailTransferProduitFini>();
						
						
						
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
				 			 
				 			String date="01/01/"+sdf.format(dateTransfereChooser.getDate())+"";
				 			String dateFin="31/12/"+sdf.format(dateTransfereChooser.getDate())+"";
				 			Date dateDebut= new Date(date);
				 			Date dateFinAnne= new Date(dateFin);
				 		 StockFinale=BigDecimal.ZERO;
				 		StockFinaleAnne=BigDecimal.ZERO;
				 		List<Object[]> listestockfinale=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateTransfereChooser.getDate(), magasinSource, articleSource, sousfamille);
		 			List<Object[]> listestockfinaleAnne=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateFinAnne, magasinSource, articleSource, sousfamille);

			 			
		 		 
				 			
				 			for(int i=0;i<listestockfinale.size();i++)
				 			{
				 				
				 			 Object[] object=listestockfinale.get(i);
							
				 			 if((int) object[0]==articleSource.getId() && (int)object[1]==sousfamille.getId())
				 			 {
				 				StockFinale=(BigDecimal)object[2];
				 			 }
				 			 	
				 			}
				 			
				 		 
				 					
				 					 
				 			 
				 			
				 			for(int i=0;i<listestockfinaleAnne.size();i++)
			 			{
			 				
			 			 Object[] object=listestockfinaleAnne.get(i);
						
			 			 if((int) object[0]==articleSource.getId() && (int)object[1]==sousfamille.getId())
			 			 {
			 				StockFinaleAnne=(BigDecimal)object[2];
			 			 }
			 			 	
			 			}
		
						
				 			 if(StockFinale.compareTo(new BigDecimal(txtquantite.getText()))<0 || StockFinaleAnne.compareTo(new BigDecimal(txtquantite.getText()))<0)
				        	 {
				        		 JOptionPane.showMessageDialog(null, "Stock "+articleSource.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
				        		 return;
				        	 }
				 			 
				 			 
							 Calculer_Prix_Moyenne_PF();
								
								BigDecimal prixMoyen=BigDecimal.ZERO;
								
								if(listMouvementStockPFAfficherTmp.size()!=0)
					    		{
					    			for(int l=0;l<listMouvementStockPFAfficherTmp.size();l++)
					    			{
					    				
					    				
					    				
					    				if(articleSource.getId() ==listMouvementStockPFAfficherTmp.get(l).getArticle().getId() && sousfamille.getId()==listMouvementStockPFAfficherTmp.get(l).getSousFamille().getId())
					    				{
					    					
					    				
					    					
					    					prixMoyen=listMouvementStockPFAfficherTmp.get(l).getPrixFinal();
					    					//System.out.println(listMouvementStockPFAfficherTmp.get(l).getArticle().getLiblle() + listMouvementStockPFAfficherTmp.get(l).getSousFamille().getLiblle() +" : Initial "+listMouvementStockPFAfficherTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockPFAfficherTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockPFAfficherTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockPFAfficherTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockPFAfficherTmp.get(l).getEntrerProduction() + " Transfer Enter : "+listMouvementStockPFAfficherTmp.get(l).getTransferEntrer() + " Vente : "+listMouvementStockPFAfficherTmp.get(l).getVente() + " Quantite Finale : "+listMouvementStockPFAfficherTmp.get(l).getStockFinal() + "  prix Moyen : "+listMouvementStockPFAfficherTmp.get(l).getPrixFinal() ) ;
					    					
					    				}
					    				
					    			}
					    			
					    		}else
					    		{
					    			for(int l=0;l<listMouvementStockPFAfficher.size();l++)
					    			{
					    				if(articleSource.getId() ==listMouvementStockPFAfficher.get(l).getArticle().getId() && sousfamille.getId()==listMouvementStockPFAfficher.get(l).getSousFamille().getId())
					    				{
					    					
					    				
					    					
					    					prixMoyen=listMouvementStockPFAfficher.get(l).getPrixFinal();
					    					//System.out.println(listMouvementStockPFAfficher.get(l).getArticle().getLiblle() + listMouvementStockPFAfficher.get(l).getSousFamille().getLiblle() +" : Initial "+listMouvementStockPFAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockPFAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockPFAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockPFAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockPFAfficher.get(l).getEntrerProduction() + " Transfer Enter : "+listMouvementStockPFAfficher.get(l).getTransferEntrer()+ " Vente : "+listMouvementStockPFAfficher.get(l).getVente() + " Quantite Finale : "+listMouvementStockPFAfficher.get(l).getStockFinal() + "  prix Moyen : "+listMouvementStockPFAfficher.get(l).getPrixFinal() ) ;
					    					
					    				}
					    			}
					    			
					    		}	
								
				 			 
						
						
						
						
					//transfere stock PF Source
					txtCodeTransfer.setText(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_SORTIE));
					
					TransferStockPF transferPFSource=new TransferStockPF();
					transferPFSource.setCodeTransfer(txtCodeTransfer.getText());
					transferPFSource.setCreerPar(AuthentificationView.utilisateur);
					transferPFSource.setDate(new Date());
					transferPFSource.setDateTransfer(dateTransfereChooser.getDate());
					transferPFSource.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF_PF);
					transferStockPFDAO.add(transferPFSource);
					
					DetailTransferProduitFini detailTransferStockPFSource=new DetailTransferProduitFini();
					detailTransferStockPFSource.setMagasinDestination(magasinSource);
					
					detailTransferStockPFSource.setArticle(articleSource);
					detailTransferStockPFSource.setQuantite(new BigDecimal(txtquantite.getText()));
				 
						detailTransferStockPFSource.setPrixUnitaire(prixMoyen);
					 
					
					detailTransferStockPFSource.setSousFamille(sousfamille);
					detailTransferStockPFSource.setDateTransfer(dateTransfereChooser.getDate());
					detailTransferStockPFSource.setTransferStockPF(transferPFSource);
					detailTransferStockPFSource.setTypeTransfer(ETAT_TRANSFER_STOCK_SORTIE_PF_PF);
					 detailTransferPFDAO.add(detailTransferStockPFSource);
					 
					 listDetailTransferStockPFSource.add(detailTransferStockPFSource);
					
					// transfer stock PF Destination
					 
					 TransferStockPF transferPFDestination=new TransferStockPF();
					 transferPFDestination.setCodeTransfer(txtCodeTransfer.getText());
					 transferPFDestination.setCreerPar(AuthentificationView.utilisateur);
					 transferPFDestination.setDate(new Date());
					 transferPFDestination.setDateTransfer(dateTransfereChooser.getDate());
					 transferPFDestination.setStatut(ETAT_TRANSFER_STOCK_ENTRER_PF_PF);
					 transferStockPFDAO.add(transferPFDestination);
					 
					 DetailTransferProduitFini detailTransferStockPFDestination=new DetailTransferProduitFini();
					 detailTransferStockPFDestination.setMagasinDestination (magasinDestination);
						
					 detailTransferStockPFDestination.setArticle(articleDestination);
					 detailTransferStockPFDestination.setQuantite(new BigDecimal(txtquantite.getText()));
						
 
							detailTransferStockPFDestination.setPrixUnitaire(prixMoyen);
							
						 
						
						detailTransferStockPFDestination.setSousFamille(sousfamilleDestination);
						detailTransferStockPFDestination.setDateTransfer(dateTransfereChooser.getDate());
						detailTransferStockPFDestination.setTransferStockPF(transferPFDestination);
						detailTransferStockPFDestination.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_PF_PF);
						 detailTransferPFDAO.add(detailTransferStockPFDestination);
						 listDetailTransferStockPFDestination.add(detailTransferStockPFDestination);
						 JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
							ImprimerBon();
							intialiser();
						 
				
					
						
			
				
				}
					
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "La quantité Trasfere doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				

		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(217, 310, 165, 23);
		add(btnValiderTransfer);
		 btnimprimer = new JButton("Bon Transfere MP Produit Fini");
		 btnimprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		

	  		  	if(listDetailTransferStockPFSource.size()>0 && listDetailTransferStockPFDestination.size()>0){
	  		  		
	  		  		if(!comboDepotDestination.getSelectedItem().equals("") ){
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	
	  		  DetailTransferProduitFini detailTransferStockPFSource=listDetailTransferStockPFSource.get(0);
	  		 DetailTransferProduitFini detailTransferStockPFDestination=listDetailTransferStockPFDestination.get(0);
	  		  	
	  		  	String date=dateFormat.format(detailTransferStockPFSource.getTransferStockPF().getDateTransfer());
				 
				 
				 
					
						Map parameters = new HashMap();
						parameters.put("numTransfer", detailTransferStockPFSource.getTransferStockPF().getCodeTransfer());
						parameters.put("machineSource", detailTransferStockPFSource.getMagasinDestination().getLibelle());
						parameters.put("depSource", detailTransferStockPFSource.getMagasinDestination().getDepot().getLibelle());
						parameters.put("magasinDest", detailTransferStockPFDestination.getMagasinDestination().getLibelle());
						parameters.put("depDest", detailTransferStockPFDestination.getMagasinDestination().getDepot().getLibelle());
						parameters.put("dateTransfer", date);
						JasperUtils.imprimerBonTransferePFProduitFini(listDetailTransferStockPFSource,parameters,detailTransferStockPFSource.getTransferStockPF().getCodeTransfer());
					 
				 
				 
	  		  		}else {
	  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		  		}
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
		 		
		 		
		 	}
		 });
		btnimprimer.setBounds(543, 310, 201, 23);
		btnimprimer.setIcon(imgImprimer);
		add(btnimprimer);
		btnimprimer.setVisible(false);
		
		  int p=0;
	      combofamilleSource.addItem("");
	      
	      JLabel label_1 = new JLabel("Code Article :");
	      label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      label_1.setBounds(520, 108, 82, 26);
	      layeredPane.add(label_1);
	      
	      txtcodearticleSource = new JTextField();
	      txtcodearticleSource.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyPressed(KeyEvent e) {
	      		
	      		

     			if(e.getKeyCode()==e.VK_ENTER)
    		{
     				
     					
    			if(!txtcodearticleSource.getText().equals(""))
    			{
    				//SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
    				Articles article=mapCodeArticle.get(txtcodearticleSource.getText().toUpperCase());
		    		
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
	      txtcodearticleSource.setColumns(10);
	      txtcodearticleSource.setBounds(591, 108, 112, 26);
	      layeredPane.add(txtcodearticleSource);
	      
	      JLabel label_2 = new JLabel("Libelle :");
	      label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      label_2.setBounds(713, 108, 57, 26);
	      layeredPane.add(label_2);
	      
	       comboarticleSource = new JComboBox();
	      comboarticleSource.addItemListener(new ItemListener() {
	      	public void itemStateChanged(ItemEvent e) {

	  		   	 if(e.getStateChange() == ItemEvent.SELECTED) {
	  		   		 
	  		   	Articles articles=mapArticle.get(comboarticleSource.getSelectedItem());
	  		   		 if(articles!=null)
	  		   		 {
	  		   			 txtcodearticleSource.setText(articles.getCodeArticle());
	  		   			 
			
	  		   			  
	  		   		 }else
	  		   		 {
	  		   		txtcodearticleSource.setText("");
	  		   		 }
	  		   		 
	  		   	 	 }
	  		   		
	  		   	
	      		
	      		
	      		
	      	}
	      });
	      comboarticleSource.setSelectedIndex(-1);
	      comboarticleSource.setBounds(756, 108, 258, 27);
	      layeredPane.add(comboarticleSource);
	      AutoCompleteDecorator.decorate(comboarticleSource);
	  	
	  	JLabel label_3 = new JLabel("Famille Article :");
	  	label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  	label_3.setBounds(10, 161, 87, 24);
	  	layeredPane.add(label_3);
	  	
	  	 combofamilleDestination = new JComboBox();
	  	 combofamilleDestination.addItemListener(new ItemListener() {
	  	 	public void itemStateChanged(ItemEvent e) {
	  	 		

  				

        		
       		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
       			 if(!combofamilleDestination.getSelectedItem().equals(""))
       			 {
       				 
       					FamilleArticlePF famille=mapfamille.get(combofamilleDestination.getSelectedItem());
   		        		if(famille!=null)
   		        		{
   		        			combosousfamilleDestination.removeAllItems();
   		        			 combosousfamilleDestination.addItem("");
   		        			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
   		        			for(int i=0;i<listSousFamilleArticle.size();i++)
   		        			{
   		        			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
   		        			combosousfamilleDestination.addItem(sousfamille.getLiblle());
   		        			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
   		        				
   		        			}
   		        			
   		        		}else
   		        		{
   		        			 combosousfamilleDestination.removeAllItems();
   		        		}
   		        			
       				 
       			 }else
       			 {
       				//combosousfamille.removeAllItems(); 
       			 }
       			 
       			
       	 
       			
       			 
    	 		 }
    	 			 
       		
       		
       	
 				
 				
 			
	  	 		
	  	 		
	  	 	}
	  	 });
	  	combofamilleDestination.setSelectedIndex(-1);
	  	combofamilleDestination.setBounds(92, 162, 167, 24);
	  	layeredPane.add(combofamilleDestination);
	  	
	  	JLabel label_6 = new JLabel("Sous Famille :");
	  	label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  	label_6.setBounds(270, 160, 87, 24);
	  	layeredPane.add(label_6);
	  	
	  	 combosousfamilleDestination = new JComboBox();
	  	combosousfamilleDestination.setSelectedIndex(-1);
	  	combosousfamilleDestination.setBounds(350, 161, 160, 24);
	  	layeredPane.add(combosousfamilleDestination);
	  	
	  	JLabel label_7 = new JLabel("Code Article :");
	  	label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  	label_7.setBounds(520, 158, 82, 26);
	  	layeredPane.add(label_7);
	  	
	  	txtcodearticleDestination = new JTextField();
	  	txtcodearticleDestination.setColumns(10);
	  	txtcodearticleDestination.setBounds(591, 158, 112, 26);
	  	layeredPane.add(txtcodearticleDestination);
	  	
	  	JLabel label_8 = new JLabel("Libelle :");
	  	label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  	label_8.setBounds(713, 158, 57, 26);
	  	layeredPane.add(label_8);
	  	
	  	 comboarticleDestination = new JComboBox();
	  	 comboarticleDestination.addItemListener(new ItemListener() {
	  	 	public void itemStateChanged(ItemEvent e) {
	  	 		
	  	 		


	  		   	 if(e.getStateChange() == ItemEvent.SELECTED) {
	  		   		 
	  		   	Articles articles=mapArticle.get(comboarticleDestination.getSelectedItem());
	  		   		 if(articles!=null)
	  		   		 {
	  		   			 txtcodearticleDestination.setText(articles.getCodeArticle());
	  		   			 
			
	  		   			  
	  		   		 }else
	  		   		 {
	  		   		txtcodearticleDestination.setText("");
	  		   		 }
	  		   		 
	  		   	 	 }
	  		   		
	  		   	
	      		
	      		
	      		
	      	
	  	 	}
	  	 });
	  	comboarticleDestination.setSelectedIndex(-1);
	  	comboarticleDestination.setBounds(756, 158, 258, 27);
	  	layeredPane.add(comboarticleDestination);
	  	 AutoCompleteDecorator.decorate(comboarticleDestination);
	  listArticle=articledao.findAll();
		 for(int j=0;j<listArticle.size();j++)
		 {
			Articles article= listArticle.get(j);
			comboarticleSource.addItem(article.getLiblle());
			comboarticleDestination.addItem(article.getLiblle());
			 mapArticle.put(article.getLiblle(), article);
			mapCodeArticle.put(article.getCodeArticle(), article);
		 }
	  	
	  	
		 combofamilleDestination.addItem("");
	  	
		    while(p<listFamilleArticle.size())
		      {
		    	  
		    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
		    	  combofamilleSource.addItem(famillearticle.getLiblle());
		    	  combofamilleDestination.addItem(famillearticle.getLiblle());
		    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
		    	  p++;
		      }
		      
		   
		  	
		  	comboarticleSource.addItem("");	
	  	
				  		 
	}
	

	
	void intialiser()
	{
		comboDepotDestination.setSelectedIndex(-1);
		comboDepotSource.setSelectedIndex(-1);
		comboMagasinDestination.setSelectedIndex(-1);
		comboMagasinSource.setSelectedIndex(-1);
		comboarticleSource.setSelectedIndex(-1);
		combomp.setSelectedIndex(-1);
		txtquantite.setText("");
		txtcodearticleSource.setText("");
		 
		txtCodeTransfer.setText("");
		dateTransfereChooser.setCalendar(null);
		combofamilleSource.setSelectedItem("");
		combosousfamilleSource.setSelectedItem("");
		comboarticleSource.setSelectedItem("");
		
 txtcodearticleDestination.setText("");
	combofamilleDestination.setSelectedItem("");
	combosousfamilleDestination.setSelectedItem("");
	comboarticleDestination.setSelectedItem("");
	
	}

	







public void ImprimerBon()
{
	

		
		

  	if(listDetailTransferStockPFSource.size()>0 && listDetailTransferStockPFDestination.size()>0){
	  		
	  		if(!comboDepotDestination.getSelectedItem().equals("") ){
	  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  	
	  DetailTransferProduitFini detailTransferStockPFSource=listDetailTransferStockPFSource.get(0);
	 DetailTransferProduitFini detailTransferStockPFDestination=listDetailTransferStockPFDestination.get(0);
	  	
	  	String date=dateFormat.format(detailTransferStockPFSource.getTransferStockPF().getDateTransfer());
	 
	 
	 
		
			Map parameters = new HashMap();
			parameters.put("numTransfer", detailTransferStockPFSource.getTransferStockPF().getCodeTransfer());
			parameters.put("machineSource", detailTransferStockPFSource.getMagasinDestination().getLibelle());
			parameters.put("depSource", detailTransferStockPFSource.getMagasinDestination().getDepot().getLibelle());
			parameters.put("magasinDest", detailTransferStockPFDestination.getMagasinDestination().getLibelle());
			parameters.put("depDest", detailTransferStockPFDestination.getMagasinDestination().getDepot().getLibelle());
			parameters.put("dateTransfer", date);
			JasperUtils.imprimerBonTransferePFProduitFini(listDetailTransferStockPFSource,parameters,detailTransferStockPFSource.getTransferStockPF().getCodeTransfer());
		 
	 
	 
	  		}else {
	  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		}
	
//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  	}else {
	  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  	}
		
		
	
	
	
}



void Calculer_Prix_Moyenne_PF()
{
	SimpleDateFormat sdfyeras=new SimpleDateFormat("yyyy");
	String date="01/01/"+sdfyeras.format(dateTransfereChooser.getDate())+"";
	
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	
	
	 
	try {
		Date dateDebut = sdf.parse(date);
		
		listMouvementStockPF.clear();
		detailTransferStockPFDAO.ViderSession();
		boolean trouve=false;
		Articles article=mapArticle.get(comboarticleSource.getSelectedItem());
		Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		if(magasin!=null)
		{
			
	 
			
		 
			
			listDetailTransferStockPF=detailTransferStockPFDAO.findAllTransferStockPFOrderByDateTransfer(magasin);
			listDetailTransferStockPFGroupebyArticle=detailTransferStockPFDAO.findAllTransferStockPFGroupeByDateTransferByArticle(magasin);
			listDetailTransferStockPFBytypetransfer=detailTransferStockPFDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
			String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP,ETAT_TRANSFER_STOCK_ENTRER_PF_MP,ETAT_TRANSFER_STOCK_AVOIR_R,ETAT_TRANSFER_STOCK_AVOIR_CLIENT,ETAT_TRANSFER_STOCK_ENTRER_PF_PF,ETAT_TRANSFER_STOCK_SORTIE_PF_PF};
			BigDecimal achat=BigDecimal.ZERO;
			BigDecimal Prixachat=BigDecimal.ZERO;
			BigDecimal vente=BigDecimal.ZERO;
			BigDecimal TransferEntrer=BigDecimal.ZERO;
			BigDecimal PrixTransferEntrer=BigDecimal.ZERO;
			BigDecimal avoir=BigDecimal.ZERO;
			BigDecimal avoirClient=BigDecimal.ZERO;
			BigDecimal PrixavoirClient=BigDecimal.ZERO;
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
				avoirClient=new BigDecimal(0);
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
	    			avoirClient=new BigDecimal(0);
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
					
				}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_PF_PF))
				{
					vente=vente.add(listDetailTransferStockPF.get(j).getQuantite());
					
				}
				else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
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
					
					
					
					
				}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR_CLIENT))
				{ 
					
					
					  if(!avoir.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
	             	   {
	                 	   PrixavoirClient=(PrixavoirClient.multiply(avoir).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(avoir.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

	             	   }else
	             	   {
	             		   Prixachat=BigDecimal.ZERO;
	             	   }
					
					
						avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
					
					
					
					
					
				}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR_R))
				{ 
					
						avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
					
					
					
					
					
				}
				
				else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
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
					
					
					
				}	else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_PF))
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
						
		    			
	if(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClient).compareTo(BigDecimal.ZERO)!=0)
	{


	prixmoyenne=(achat.multiply(Prixachat).add(quantitefinale.multiply(prixmoyenne)).add(TransferEntrer.multiply(PrixTransferEntrer)).add(production.multiply(Prixproduction)).add(avoirClient.multiply(PrixavoirClient))).divide(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClient),6, RoundingMode.DOWN);
	//System.out.println(listDetailTransferStockPF.get(j).getArticle().getLiblle() + " : "+ listDetailTransferStockPF.get(j).getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

	}
						
	    			}else
	    			{
	    				prixmoyenne=BigDecimal.ZERO;

	    			}
					
					

					quantitefinale=(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClient)).subtract(vente.add(avoir));
					
				}else
				{
					
					
					if(!initial.add(achat).add(production).add(TransferEntrer).add(avoirClient).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
	    			{
						
					
						prixmoyenne=(achat.multiply(Prixachat).add(initial.multiply(prixinitial)).add(PrixTransferEntrer.multiply(TransferEntrer).add(Prixproduction.multiply(production)).add(PrixavoirClient.multiply(avoirClient)))).divide(initial.add(achat).add(production).add(TransferEntrer).add(avoirClient), 6, RoundingMode.DOWN);
						
	    			
	    			}else
	    			{
	    				prixmoyenne=BigDecimal.ZERO;

	    			}
					
					
					quantitefinale=(initial.add(achat).add(TransferEntrer).add(production).add(avoirClient)).subtract(vente.add(avoir));

					
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
				
			
				
				stockfinal=(initial.add(production).add(achat).add(TransferEntrer).add(avoirClient)).subtract(vente.add(avoir));
				MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
				mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer());
				mouvementstockPF.setAchat(achat);
				mouvementstockPF.setPrixAchat(Prixachat);
				mouvementstockPF.setVente(vente);
				mouvementstockPF.setAvoir(avoir);
				mouvementstockPF.setAvoirClientPF(avoirClient);
				mouvementstockPF.setInitial(initial);
				mouvementstockPF.setPrixInitial(prixinitial);
				mouvementstockPF.setEntrerProduction(production);
				mouvementstockPF.setPrixProduction(Prixproduction);
				mouvementstockPF.setTransferEntrer(TransferEntrer);
				mouvementstockPF.setPrixTransferEntrer(PrixTransferEntrer);
				mouvementstockPF.setSousFamille(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille());
				mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticle.get(i).getArticle());
				
			/*	if(mouvementstockPF.getArticle().getLiblle().equals("EL AARAB VERT PROMO 200G") && mouvementstockPF.getSousFamille().getLiblle().equals("9371"))
				{
					System.out.println ( "Avoir :  "+avoir );
					System.out.println ( "new "+mouvementstockPF.getArticle().getLiblle() + " : "+mouvementstockPF.getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

				}
				*/
				
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

				//System.out.println( "new "+mouvementstockPF.getArticle().getLiblle() + " : "+mouvementstockPF.getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

				mouvementstockPF.setPrixFinal(prixmoyenne); 
				mouvementstockPF.setStockFinal((mouvementstockPF.getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getAvoirClientPF())).subtract(mouvementstockPF.getVente().add(mouvementstockPF.getAvoir())));
				
				listMouvementStockPF.add(mouvementstockPF);
				
				
				

			}
			
		
			// detailtransfer entre deux date et par article
			
			if(dateDebut!=null && dateTransfereChooser.getDate()!=null && article!=null)
			{
				listMouvementStockPFAfficher.clear();
				listMouvementStockPFAfficherTmp.clear();
				
			
			for(int i=0;i<listMouvementStockPF.size();i++)	
			{
				String ddebut=sdf.format(dateDebut);
				String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
				
				if(listMouvementStockPF.get(i).getDateStockPF().after(dateDebut) ==true || ddebutTmp.equals(ddebut)   )
					
				{
				if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()))
				{

					listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));	
				}
					
				
				}
				
			}
				
			for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
			{
				
				String dfin=sdf.format(dateTransfereChooser.getDate());
				String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
				if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateTransfereChooser.getDate())==true || dfinTmp.equals(dfin) )
				{
				if(listMouvementStockPFAfficher.get(j).getArticle().getLiblle().equals(article.getLiblle()))
				{
					listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
				}
					
				}
				
			}
			
		
				
			// detailtransfer entre deux date (date fin null) et par article 
				
			}else if(dateDebut!=null && dateTransfereChooser.getDate()==null && article!=null)
			{
				listMouvementStockPFAfficherTmp.clear();
				String d1=sdf.format(dateDebut);
			
				
				for(int i=0;i<listMouvementStockPF.size();i++)	
	    		{
					String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
	    			if(ddbut.equals(d1) && listMouvementStockPF.get(i).getArticle().equals(article) )
	    			{
	    			
	    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
	    			
	    			}
	    			
	    		}
				
				
				
				// detailtransfer entre deux date (date fin null)  
				
			}else if(dateDebut!=null && dateTransfereChooser.getDate()==null && article==null)
			{
				
				listMouvementStockPFAfficherTmp.clear();
	              
	              String d1=sdf.format(dateDebut);
	    			
				
				for(int i=0;i<listMouvementStockPF.size();i++)	
	    		{
					String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
	    			if(ddbut.equals(d1) )
	    			{
	    			
	    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
	    			
	    			}
	    		
	    		}
				
			
				
				// detailtransfer par article
			}else if(dateDebut==null && dateTransfereChooser.getDate()==null && article!=null)
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
				
			}else if(dateDebut!=null && dateTransfereChooser.getDate()!=null && article==null)
			{
				listMouvementStockPFAfficher.clear();
				listMouvementStockPFAfficherTmp.clear();
			
			for(int i=0;i<listMouvementStockPF.size();i++)	
			{
				String ddebut=sdf.format(dateDebut);
				String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
				if(listMouvementStockPF.get(i).getDateStockPF().after(dateDebut) ==true || ddebutTmp.equals(ddebut))
				{
					listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));
				
					
				}
				
			}
				
			
			
			for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
			{
				
				String dfin=sdf.format(dateTransfereChooser.getDate());
				String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
				
				if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateTransfereChooser.getDate())==true || dfinTmp.equals(dfin)  )
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

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	
}
}
