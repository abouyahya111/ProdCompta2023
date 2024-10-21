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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DepotDAO;
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
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
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

public class TransfererMPProduitFini extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgAjouter;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	
	
	
	List<DetailTransferProduitFini> listDetailTransferStockPF= new ArrayList<DetailTransferProduitFini>();
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
	private Depot depot = new Depot();
	private JTextField txtCodeTransfer;
	private JTextField txtcodemp;
	private JTextField txtquantite;
	private  JComboBox comboarticle = new JComboBox();
	private   JComboBox combomp = new JComboBox();
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
	 private JTextField txtcodearticle;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public TransfererMPProduitFini() {
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
				  		     btnIntialiserOF.setBounds(409, 277, 112, 23);
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
				  		     	layeredPane.setBounds(9, 13, 1276, 222);
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
				  		  
				  		  JLabel lblCodeMp = new JLabel("Code MP");
				  		  lblCodeMp.setBounds(340, 110, 94, 24);
				  		  layeredPane.add(lblCodeMp);
				  		  
				  		  txtcodemp = new JTextField();
				  		  txtcodemp.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent e) {
				  		  		
				  		  	if (e.getKeyCode() == e.VK_ENTER)
				  		  	{
				  		  	if(!txtcodemp.getText().equals(""))
			  		  			
			  		  		{
			  		  			MatierePremier matierepremiere=mapcodeMP.get(txtcodemp.getText().toUpperCase());
			  		  			if(matierepremiere!=null)
			  		  			{
			  		  				
			  		  				combomp.setSelectedItem(matierepremiere.getNom());
			  		  			}else
			  		  			{
			  		  				JOptionPane.showMessageDialog(null, "Matiere Premiere introuvable !!!");
			  		  			}
			  		  			
			  		  			
			  		  			
			  		  			
			  		  		}
				  		  	}
				  		  		
				  		  	}
				  		  });
				  		  txtcodemp.setColumns(10);
				  		  txtcodemp.setBounds(399, 109, 155, 24);
				  		  layeredPane.add(txtcodemp);
				  		  
				  		  JLabel lblLibelleMp = new JLabel("Matiere Premiere");
				  		  lblLibelleMp.setBounds(583, 109, 91, 24);
				  		  layeredPane.add(lblLibelleMp);
				  		  
				  		   combomp = new JComboBox();
				  		   combomp.addItemListener(new ItemListener() {
				  		   	public void itemStateChanged(ItemEvent e) {
				  		   	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		   		 
				  		   		 MatierePremier matierepremiere=mapMatierePremier.get(combomp.getSelectedItem());
				  		   		 if(matierepremiere!=null)
				  		   		 {
				  		   			 txtcodemp.setText(matierepremiere.getCode());
				  		   			 
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
				  		   		 txtcodemp.setText("");
				  		   		 }
				  		   		 
				  		   	 	 }
				  		   		
				  		   	}
				  		   });
				  		  combomp.setBounds(684, 111, 247, 24);
				  		  layeredPane.add(combomp);
				  		  
				  		  txtquantite = new JTextField();
				  		  txtquantite.setColumns(10);
				  		  txtquantite.setBounds(1046, 112, 155, 24);
				  		  layeredPane.add(txtquantite);
				  		  
				  		  JLabel lblQuantit = new JLabel("Quantit\u00E9 ");
				  		  lblQuantit.setBounds(960, 111, 94, 24);
				  		  layeredPane.add(lblQuantit);
				  		combomp.addItem("");
				  		
				  		JLabel label_4 = new JLabel("Famille Article :");
				  		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		label_4.setBounds(0, 150, 87, 24);
				  		layeredPane.add(label_4);
				  		
				  		 combofamille = new JComboBox();
				  		combofamille.addItemListener(new ItemListener() {
				  			public void itemStateChanged(ItemEvent e) {
				  				

				        		
				        		 if(e.getStateChange() == ItemEvent.SELECTED)
				     	 		 {
				        			 if(!combofamille.getSelectedItem().equals(""))
				        			 {
				        				 
				        					FamilleArticlePF famille=mapfamille.get(combofamille.getSelectedItem());
				    		        		if(famille!=null)
				    		        		{
				    		        			combosousfamille.removeAllItems();
				    		        			 combosousfamille.addItem("");
				    		        			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
				    		        			for(int i=0;i<listSousFamilleArticle.size();i++)
				    		        			{
				    		        			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
				    		        			combosousfamille.addItem(sousfamille.getLiblle());
				    		        			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
				    		        				
				    		        			}
				    		        			
				    		        		}else
				    		        		{
				    		        			 combosousfamille.removeAllItems();
				    		        		}
				    		        			
				        				 
				        			 }else
				        			 {
				        				//combosousfamille.removeAllItems(); 
				        			 }
				        			 
				        			
				        	 
				        			
				        			 
				     	 		 }
				     	 			 
				        		
				        		
				        	
				  				
				  				
				  			}
				  		});
				  		combofamille.setSelectedIndex(-1);
				  		combofamille.setBounds(82, 151, 167, 24);
				  		layeredPane.add(combofamille);
				  		
				  		JLabel label_5 = new JLabel("Sous Famille :");
				  		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		label_5.setBounds(260, 149, 87, 24);
				  		layeredPane.add(label_5);
				  		
				  		 combosousfamille = new JComboBox();
				  		 combosousfamille.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				/*
				 * 
				 * if(e.getStateChange() == ItemEvent.SELECTED) {
				 * if(!combosousfamille.getSelectedItem().equals("")) {
				 * 
				 * SousFamilleArticlePF
				 * sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem()); Magasin
				 * magasin=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
				 * if(magasin!=null) {
				 * 
				 * if(sousfamille!=null) {
				 * 
				 * comboarticle.removeAllItems(); comboarticle.addItem("");
				 * listStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId
				 * (), sousfamille.getId());
				 * 
				 * for(int i=0;i<listStockPF.size();i++) { Articles article=
				 * listStockPF.get(i).getArticles(); comboarticle.addItem(article.getLiblle());
				 * mapArticle.put(article.getLiblle(), article);
				 * mapCodeArticle.put(article.getCodeArticle(), article); }
				 * 
				 * 
				 * 
				 * }else { comboarticle.removeAllItems(); txtcodearticle.setText(""); } }
				 * 
				 * 
				 * }else { comboarticle.removeAllItems(); txtcodearticle.setText(""); }
				 * 
				 * 
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 */}
				  		 });
				  		combosousfamille.setSelectedIndex(-1);
				  		combosousfamille.setBounds(340, 150, 160, 24);
				  		layeredPane.add(combosousfamille);
	
		
	
		
		
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
				
				else if(txtcodemp.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le code Matiere Premiere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(combomp.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir la matiere  Premiere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(txtquantite.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer la quantité matiere premiere SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
				{
					JOptionPane.showMessageDialog(null, "la quantité matiere premiere doit etre supérieur à 0 SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(txtcodearticle.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le code article SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(comboarticle.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectionner l'Article  SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
					Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
				
					if(magasinSource.getCode().equals(magasinDestination.getCode())){
						JOptionPane.showMessageDialog(null, "Le Magasin source doit ètre différent déstination", "Erreur", JOptionPane.ERROR_MESSAGE);
					
					}
					else
					
					{
						Articles article=articledao.findByCode(txtcodearticle.getText());
						
						if(article==null)
						{
							
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
							
						}
						
						
						
					//transfere stock MP
					txtCodeTransfer.setText(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_SORTIE));
					transferStock.setCodeTransfer(txtCodeTransfer.getText());
					transferStock.setCreerPar(AuthentificationView.utilisateur);
					transferStock.setDate(new Date());
					transferStock.setDateTransfer(dateTransfereChooser.getDate());
					transferStock.setStatut(ETAT_TRANSFER_STOCK_SORTIE_MP_PF);
					transferStock.setDepot(mapDepotSourcetmp.get(comboDepotSource.getSelectedItem()));
					transferStock.setListDetailTransferMP(remplirDetailTransfer());	
					
					
					// transfer stock produit fini
					transferPF.setCodeTransfer(txtCodeTransfer.getText());
					transferPF.setCreerPar(AuthentificationView.utilisateur);
					transferPF.setDate(new Date());
					transferPF.setDateTransfer(dateTransfereChooser.getDate());
					transferPF.setStatut(ETAT_TRANSFER_STOCK_ENTRER_PF_MP);
					remplirDetailTransferProduitFini();
					transferPF.setListDetailTransferProduitFini(remplirDetailTransferProduitFini());
					
					SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
					MatierePremier mp=mapMatierePremier.get(combomp.getSelectedItem());
					 if(detailtransfermp==false)
					 {
						 if(detailtransferpf==false)
						 {
							 	transferStockMPDAO.add(transferStock);
								transferStockPFDAO.add(transferPF);	
								for(int k=0;k<listDetailTransferStockPF.size();k++)
								{
									detailTransferPFDAO.add(listDetailTransferStockPF.get(k));
								}
								
								StockPF stcokPf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasinDestination.getId(), sousfamille.getId());
									StockMP stockmp=stockMPDAO.findStockByMagasinMP(mp.getId(), magasinSource.getId());
									if(stcokPf!=null)
									{
										stcokPf.setStock(stcokPf.getStock().add(new BigDecimal(txtquantite.getText())));
										stockpfdao.edit(stcokPf);
									}else
									{
										StockPF stockpfTmp=new StockPF();
										stockpfTmp.setArticles(article);
										stockpfTmp.setSousFamille(sousfamille);
										stockpfTmp.setMagasin(magasinDestination);
										stockpfTmp.setPrixUnitaire(stockmp.getPrixUnitaire());
										stockpfTmp.setStock(new BigDecimal(txtquantite.getText()));
										stockpfTmp.setStockOffre(BigDecimal.ZERO);
										stockpfdao.add(stockpfTmp);
									}
									
								
								
								JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
								ImprimerBon();
								intialiser();
								
								
								
								
								transferStock=new TransferStockMP();
								transferPF=new TransferStockPF();
							 
						 }else
						 {
							///	JOptionPane.showMessageDialog(null, "Liste produit fini est vide", "Erreur", JOptionPane.INFORMATION_MESSAGE);
							 return;
						 }
						 
						 
					 }else
					 {
							JOptionPane.showMessageDialog(null, "Liste Matière premiere est vide", "Erreur", JOptionPane.INFORMATION_MESSAGE);
						 return;
					 }
					
						
			
					}
				}
					
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "La quantité de matiere premiere doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				

		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(218, 277, 165, 23);
		add(btnValiderTransfer);
		 btnimprimer = new JButton("Bon Transfere MP Produit Fini");
		 btnimprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		

	  		  	if(transferStock.getId()>0){
	  		  		
	  		  		if(!comboDepotDestination.getSelectedItem().equals("") ){
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String date=dateFormat.format(transferStock.getDateTransfer());
				 List<DetailTransferStockMP> listDetailTransferStockMP=transferStock.getListDetailTransferMP();
				 
				 if(listDetailTransferStockMP.size()!=0)
				 {
					 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(0);
						Map parameters = new HashMap();
						parameters.put("numTransfer", transferStock.getCodeTransfer());
						parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle());
						parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
						parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
						parameters.put("depDest", comboDepotDestination.getSelectedItem());
						parameters.put("dateTransfer", date);
						JasperUtils.imprimerBonTransfereMPProduitFini(listDetailTransferStockMP,parameters,transferStock.getCodeTransfer());
					 
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il faut saisir la matière premiere a transferer", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				 }
				 
	  		  		}else {
	  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		  		}
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
		 		
		 		
		 	}
		 });
		btnimprimer.setBounds(544, 277, 201, 23);
		btnimprimer.setIcon(imgImprimer);
		add(btnimprimer);
		btnimprimer.setVisible(false);
		
		  int p=0;
	      combofamille.addItem("");
	      
	       combotypeMP = new JComboBox();
	       combotypeMP.addItemListener(new ItemListener() {
	       	public void itemStateChanged(ItemEvent arg0) {
	       		
	       		combomp.removeAllItems();
	       	
	       			if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK) || combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK_DECHET))
	       			{
	       				combomp.addItem("");
	       				listMatierePremier.clear();
	       				listMatierePremier=matierePremiereDAO.findAll();
	       				int k=0;
	       				while(k<listMatierePremier.size())
	       				{
	       					MatierePremier matierepremiere=listMatierePremier.get(k);
	       					combomp.addItem(matierepremiere.getNom());
	       					
	       					
	       					mapMatierePremier.put(matierepremiere.getNom(), matierepremiere);
	       					mapcodeMP.put(matierepremiere.getCode(), matierepremiere);
	       					k++;
	       				}
	       				
	       			}else if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK_OFFRE))
	       			{
	       				combomp.addItem("");
	       				listMatierePremier.clear();
	       				listMatierePremier=matierePremiereDAO.listeMatierePremierByCategorie(CATEGORIE_MATIERE_PREMIERE_CADEAU);
	       				int k=0;
	       				while(k<listMatierePremier.size())
	       				{
	       					MatierePremier matierepremiere=listMatierePremier.get(k);
	       					combomp.addItem(matierepremiere.getNom());
	       					
	       					
	       					mapMatierePremier.put(matierepremiere.getNom(), matierepremiere);
	       					mapcodeMP.put(matierepremiere.getCode(), matierepremiere);
	       					k++;
	       				}
	       				
	       			}
	       	
	       	}
	       });
	      combotypeMP.setBounds(79, 110, 237, 24);
	      layeredPane.add(combotypeMP);
	      
	      JLabel lblTypeMp = new JLabel("Type MP");
	      lblTypeMp.setBounds(10, 108, 62, 24);
	      layeredPane.add(lblTypeMp);
	      
	      JLabel label_1 = new JLabel("Code Article :");
	      label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      label_1.setBounds(510, 147, 82, 26);
	      layeredPane.add(label_1);
	      
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
	      txtcodearticle.setBounds(581, 147, 112, 26);
	      layeredPane.add(txtcodearticle);
	      
	      JLabel label_2 = new JLabel("Libelle :");
	      label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      label_2.setBounds(703, 147, 57, 26);
	      layeredPane.add(label_2);
	      
	       comboarticle = new JComboBox();
	      comboarticle.addItemListener(new ItemListener() {
	      	public void itemStateChanged(ItemEvent e) {

	  		   	 if(e.getStateChange() == ItemEvent.SELECTED) {
	  		   		 
	  		   	Articles articles=mapArticle.get(comboarticle.getSelectedItem());
	  		   		 if(articles!=null)
	  		   		 {
	  		   			 txtcodearticle.setText(articles.getCodeArticle());
	  		   			 
			
	  		   			  
	  		   		 }else
	  		   		 {
	  		   		txtcodearticle.setText("");
	  		   		 }
	  		   		 
	  		   	 	 }
	  		   		
	  		   	
	      		
	      		
	      		
	      	}
	      });
	      comboarticle.setSelectedIndex(-1);
	      comboarticle.setBounds(746, 147, 258, 27);
	      layeredPane.add(comboarticle);
	      while(p<listFamilleArticle.size())
	      {
	    	  
	    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
	    	  combofamille.addItem(famillearticle.getLiblle());
	    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
	    	  p++;
	      }
	      
	  	for(int j=0;j<Constantes.TYPE_MP.length;j++)
		{
			combotypeMP.addItem(Constantes.TYPE_MP[j]);
		}
	  	
	  	comboarticle.addItem("");
	  listArticle=articledao.findAll();
		 for(int j=0;j<listArticle.size();j++)
		 {
			Articles article= listArticle.get(j);
			comboarticle.addItem(article.getLiblle());
			 mapArticle.put(article.getLiblle(), article);
			mapCodeArticle.put(article.getCodeArticle(), article);
		 }
	  	
	  	
	  	
	  	
	  	
	  	
				  		 
	}
	

	
	void intialiser()
	{
		comboDepotDestination.setSelectedIndex(-1);
		comboDepotSource.setSelectedIndex(-1);
		comboMagasinDestination.setSelectedIndex(-1);
		comboMagasinSource.setSelectedIndex(-1);
		comboarticle.setSelectedIndex(-1);
		combomp.setSelectedIndex(-1);
		txtquantite.setText("");
		txtcodearticle.setText("");
		txtcodemp.setText("");
		txtCodeTransfer.setText("");
		dateTransfereChooser.setCalendar(null);
		combofamille.setSelectedItem("");
		combosousfamille.setSelectedItem("");
		comboarticle.setSelectedItem("");;
	
	
	}

	

List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;

	BigDecimal prixStockSource=BigDecimal.ZERO;
	
	BigDecimal stockSource=BigDecimal.ZERO;
	
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY");
	String date="01/01/"+sdf.format(dateTransfereChooser.getDate())+"";
		Date dateDebut= new Date(date);
	BigDecimal StockFinale=BigDecimal.ZERO;
	
	
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		MatierePremier matierePremier =mapMatierePremier.get(combomp.getSelectedItem());
		quantite=new BigDecimal(txtquantite.getText());
		StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
		if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK))	
			
		{
			
			
			
			// Stock Finale de ce jour
			List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateTransfereChooser.getDate(), magasinSource, matierePremier);
			if(!listestockfinale.isEmpty())
			{
				for(int t=0;t<listestockfinale.size();t++)
	 			{
	 				
	 			 Object[] object=listestockfinale.get(t);
				
	 			StockFinale= (BigDecimal)object[1];
				
	 			}
			}
			
			
			if(stockMPSource.getStock().compareTo(quantite) >=0 && StockFinale.compareTo(quantite) >=0){
				/*sommeStock=quantite+stockMPDestination.getStock();*/
			
				stockSource=stockMPSource.getStock().subtract(quantite);
				
				
				prixStockSource=stockMPSource.getPrixUnitaire();
				
				//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
				
				//=prixMoyen/sommeStock;
		     	//	stockMPDestination.setPrixUnitaire(prixMoyen);
				
			
				stockMPSource.setStock(stockSource);
				
				stockMPDAO.edit(stockMPSource);
				
				detailTransferStockMP.setMagasinDestination(magasinDestination);
				detailTransferStockMP.setMagasinSouce(magasinSource);
				detailTransferStockMP.setMatierePremier(matierePremier);
				detailTransferStockMP.setQuantite(quantite);
				detailTransferStockMP.setPrixUnitaire(prixStockSource);
				detailTransferStockMP.setStockSource(Constantes.MP_STOCK);
				detailTransferStockMP.setTransferStockMP(transferStock);
				listDetailTransferStockMP.add(detailTransferStockMP);
		
			}else {
			JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
			
		}
		}else if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK_DECHET))
		{
if(stockMPSource.getStockDechet()==null)
{
	JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock Dechet et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
	

}else if(stockMPSource.getStockDechet().compareTo(quantite) >=0){
				/*sommeStock=quantite+stockMPDestination.getStock();*/
			
				stockSource=stockMPSource.getStockDechet().subtract(quantite);
				
				
				prixStockSource=stockMPSource.getPrixUnitaire();
				
				//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
				
				//=prixMoyen/sommeStock;
		     	//	stockMPDestination.setPrixUnitaire(prixMoyen);
				
			
				stockMPSource.setStockDechet(stockSource);
				
				stockMPDAO.edit(stockMPSource);
				
				detailTransferStockMP.setMagasinDestination(magasinDestination);
				detailTransferStockMP.setMagasinSouce(magasinSource);
				detailTransferStockMP.setMatierePremier(matierePremier);
				detailTransferStockMP.setQuantite(quantite);
				detailTransferStockMP.setPrixUnitaire(prixStockSource);
				detailTransferStockMP.setStockSource(Constantes.MP_STOCK_DECHET);
				detailTransferStockMP.setTransferStockMP(transferStock);
				listDetailTransferStockMP.add(detailTransferStockMP);
		
			
		}else {
			JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock Dechet et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
			
		}
		
			
		}else if(combotypeMP.getSelectedItem().equals(Constantes.MP_STOCK_OFFRE))
		{
if(stockMPSource.getStockOffre()==null)
{
	JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock Offre et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);

}else if(stockMPSource.getStockOffre().compareTo(quantite) >=0){
				/*sommeStock=quantite+stockMPDestination.getStock();*/
			
				stockSource=stockMPSource.getStockOffre().subtract(quantite);
				
				
				prixStockSource=stockMPSource.getPrixUnitaire();
				
				//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
				
				//=prixMoyen/sommeStock;
		     	//	stockMPDestination.setPrixUnitaire(prixMoyen);
				
			
				stockMPSource.setStockOffre(stockSource);
				
				stockMPDAO.edit(stockMPSource);
				
				detailTransferStockMP.setMagasinDestination(magasinDestination);
				detailTransferStockMP.setMagasinSouce(magasinSource);
				detailTransferStockMP.setMatierePremier(matierePremier);
				detailTransferStockMP.setQuantite(quantite);
				detailTransferStockMP.setPrixUnitaire(prixStockSource);
				detailTransferStockMP.setStockSource(Constantes.MP_STOCK_OFFRE);
				detailTransferStockMP.setTransferStockMP(transferStock);
				listDetailTransferStockMP.add(detailTransferStockMP);
		
			
		}else {
			JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock Offre et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
			
		}
		
		}

		
if(listDetailTransferStockMP.size()==0)
{
	detailtransfermp=true;
}
	
	return listDetailTransferStockMP;
	
}


List<DetailTransferProduitFini> remplirDetailTransferProduitFini(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	
	
	
	listDetailTransferStockPF= new ArrayList<DetailTransferProduitFini>();
	
	DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		MatierePremier matierePremier =mapMatierePremier.get(combomp.getSelectedItem());
		Articles article=articledao.findByCode(txtcodearticle.getText());
		SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
		quantite=new BigDecimal(txtquantite.getText());
		StockPF stockPFDestination=null;
		if(article!=null)
		{
			 stockPFDestination=stockpfdao.findStockByMagasinPFBySousFamille (article.getId(), magasinDestination.getId(),sousfamille.getId());
		}
		
		StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
		
	if(detailtransfermp==false)
	{
		/*sommeStock=quantite+stockMPDestination.getStock();*/
		if(stockPFDestination!=null)
		{
			
			stockDestination=stockPFDestination.getStock().add(quantite);
			
			
			
			prixStockDestination=stockPFDestination.getPrixUnitaire();
			
			
			//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
			
			//=prixMoyen/sommeStock;
		//	stockMPDestination.setPrixUnitaire(prixMoyen);
			
			stockPFDestination.setStock(stockDestination);
		
			stockpfdao.edit(stockPFDestination);
			
			
			detailTransferStockPF.setMagasinDestination(magasinDestination);
			detailTransferStockPF.setMagasinSouce(magasinSource);
			
			detailTransferStockPF.setArticle(article);
			detailTransferStockPF.setQuantite(quantite);
			detailTransferStockPF.setPrixUnitaire(prixStockDestination);
			detailTransferStockPF.setSousFamille(sousfamille);
			detailTransferStockPF.setDateTransfer(dateTransfereChooser.getDate());
			detailTransferStockPF.setTransferStockPF(transferPF);
			detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
			//detailTransferPFDAO.add(detailTransferStockPF);
			listDetailTransferStockPF.add(detailTransferStockPF);
		}else
		{
			
			StockPF stockpf=new StockPF();
			stockpf.setArticles(article);
			stockpf.setSousFamille(sousfamille);
			stockpf.setMagasin(magasinDestination);
			stockpf.setStock(quantite);
			stockpf.setStockMin(BigDecimal.ONE);
			stockpf.setPrixUnitaire(stockMPSource.getPrixUnitaire());
			
			//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
			
			//=prixMoyen/sommeStock;
		//	stockMPDestination.setPrixUnitaire(prixMoyen);
			
			
			stockpfdao.add(stockpf);
		
			detailTransferStockPF.setMagasinDestination(magasinDestination);
			detailTransferStockPF.setMagasinSouce(magasinDestination);
			
			detailTransferStockPF.setArticle(article);
			detailTransferStockPF.setQuantite(quantite);
			detailTransferStockPF.setSousFamille(sousfamille);
			detailTransferStockPF.setPrixUnitaire(stockMPSource.getPrixUnitaire());
			detailTransferStockPF.setDateTransfer(dateTransfereChooser.getDate());
			detailTransferStockPF.setTransferStockPF(transferPF);
			detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
			//detailTransferPFDAO.add(detailTransferStockPF);
			listDetailTransferStockPF.add(detailTransferStockPF);
			
		}
		
	}
		
	
if(listDetailTransferStockPF.size()==0)
{ 
	stockSource=stockMPSource.getStock().add(quantite);
	stockMPDAO.edit(stockMPSource);
	detailtransferpf=true;
	
	
}
	
	return listDetailTransferStockPF;

	
}


public void ImprimerBon()
{
	

		
		

	  	if(transferStock.getId()>0){
	  		
	  		if(!comboDepotDestination.getSelectedItem().equals("") ){
	  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  	String date=dateFormat.format(transferStock.getDateTransfer());
	 List<DetailTransferStockMP> listDetailTransferStockMP=transferStock.getListDetailTransferMP();
	 
	 if(listDetailTransferStockMP.size()!=0)
	 {
		 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(0);
			Map parameters = new HashMap();
			parameters.put("numTransfer", transferStock.getCodeTransfer());
			parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle());
			parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
			parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
			parameters.put("depDest", comboDepotDestination.getSelectedItem());
			parameters.put("dateTransfer", date);
			JasperUtils.imprimerBonTransfereMPProduitFini(listDetailTransferStockMP,parameters,transferStock.getCodeTransfer());
		 
	 }else
	 {
		 JOptionPane.showMessageDialog(null, "Il faut saisir la matière premiere a transferer", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	 }
	 
	  		}else {
	  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		}
	
//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  	}else {
	  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  	}
		
		
	
	
	
}




}
