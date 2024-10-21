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
import dao.entity.AdresseClientPF;
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
import javax.swing.JTable;

public class ConsulterTransfererPFProduitFini extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modele;

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
	private JComboBox<String> comboDepotSource=new JComboBox();;
	private  JComboBox<String> comboMagasinSource=new JComboBox();
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
	private  JComboBox comboarticle = new JComboBox();
	private JComboBox comboarticleSource;
	private   JComboBox combomp = new JComboBox();
	private 	JLayeredPane layerArticle = new JLayeredPane();
	Articles article =new Articles ();
	private   JDateChooser dateTransfereChooser = new JDateChooser();
	private JDateChooser dateDu;
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
		JComboBox combosousfamilleDestination = new JComboBox();
		JComboBox combofamilleDestination = new JComboBox();
		JComboBox comboarticleDestination = new JComboBox();
		private DetailTransferProduitFiniDAO detailTransferStockPFdao;
		 BigDecimal StockFinale=BigDecimal.ZERO;
		  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
		  private DetailPrixArticleDAO detailPrixArticleDAO;  
		  private JTable table;
		  JDateChooser dateAu = new JDateChooser();
		  private List<DetailTransferProduitFini> listDetailTransferPF =new ArrayList<DetailTransferProduitFini>();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterTransfererPFProduitFini() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1295, 798);
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
        	 imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		     btnIntialiserOF.setBounds(760, 265, 112, 23);
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
					  		      				}
					  		      				
					  		      			}else
					  		      			{
					  		      			mapDepotSource.put(depot.getLibelle(), i);
					  		      		    mapDepotSourcetmp.put(depot.getLibelle(), depot);
					  		      			mapDepotDestionation.put(depot.getLibelle(), i);
					  		      			comboDepotSource.addItem(depot.getLibelle());
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
				  		     	 lblGroupe.setBounds(337, 34, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(440, 35, 193, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		  
				  		   dateDu = new JDateChooser();
				  		  dateDu.setLocale(Locale.FRANCE);
				  		  dateDu.setDateFormatString("dd/MM/yyyy");
				  		  dateDu.setBounds(770, 34, 155, 26);
				  		  layeredPane.add(dateDu);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Du :");
				  		  lblDateTransfre.setBounds(710, 34, 64, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		
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
	
		
	
		
		
		JButton btnValiderTransfer = new JButton("Afficher");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
				FamilleArticlePF familleArticlePF=mapfamille.get(combofamilleSource.getSelectedItem());
				SousFamilleArticlePF sousFamilleArticlePF=mapsousfamille.get(combosousfamilleSource.getSelectedItem());
				Articles articles=mapArticle.get(comboarticleSource.getSelectedItem());
				if(magasin==null && familleArticlePF==null && sousFamilleArticlePF==null && articles==null && dateDu.getDate()==null && dateAu.getDate()==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez S�lectionner un ou plusieurs champs SVP !!!!!","Erreur",JOptionPane.WARNING_MESSAGE);
					return;
				}else
				{
					listDetailTransferPF.clear();
					String req="";
					req=req+" where d.transferStockPF.statut='"+ETAT_TRANSFER_STOCK_SORTIE_PF_PF+"' ";
					
					if(magasin!=null)
					{
						req=req+" and  d.magasinDestination='"+magasin.getId()+"' ";
					}
					
					if(dateDu.getDate()!=null && dateAu.getDate()!=null)
					{
						req=req+" and  d.transferStockPF.dateTransfer between'"+ sdf.format(dateDu.getDate()) +"'  and '"+sdf.format(dateAu.getDate())+"' ";
					}
					
					
					if(dateDu.getDate()!=null && dateAu.getDate()==null)
					{
						req=req+" and  d.transferStockPF.dateTransfer='"+ sdf.format(dateDu.getDate())+"' ";
					}
					
					if(dateDu.getDate()==null && dateAu.getDate()!=null)
					{
						req=req+" and  d.transferStockPF.dateTransfer='"+sdf.format(dateAu.getDate())+"' ";
					}
					
					
					if(familleArticlePF!=null)
					{
						req=req+" and  d.sousFamille.famileArticlePF.id='"+familleArticlePF.getId()+"' ";
					}
					
					if(sousFamilleArticlePF!=null)
					{
						req=req+" and  d.sousFamille.id='"+sousFamilleArticlePF.getId()+"' ";
					}
					
					if(articles!=null)
					{
						req=req+" and  d.article.id='"+articles.getId()+"' ";
					}
					
					
					listDetailTransferPF=detailTransferStockPFdao.listeDetailTransfertPFByRequete(req);
					
					
					afficher_TransfertPF(listDetailTransferPF);
					
				}
				
				
				
				
				
				
				}
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(569, 265, 165, 23);
		add(btnValiderTransfer);
		
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
	  listArticle=articledao.findAll();
	  comboarticleSource.addItem("");
		 for(int j=0;j<listArticle.size();j++)
		 {
			Articles article= listArticle.get(j);
			comboarticleSource.addItem(article.getLiblle());
			comboarticleDestination.addItem(article.getLiblle());
			 mapArticle.put(article.getLiblle(), article);
			mapCodeArticle.put(article.getCodeArticle(), article);
		 }
	  	
		    while(p<listFamilleArticle.size())
		      {
		    	  
		    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
		    	  combofamilleSource.addItem(famillearticle.getLiblle());
		    	  combofamilleDestination.addItem(famillearticle.getLiblle());
		    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
		    	  p++;
		      }
		      
		   
		  	
		  	comboarticleSource.addItem("");	
		  	
		  	 dateAu = new JDateChooser();
		  	dateAu.setLocale(Locale.FRANCE);
		  	dateAu.setDateFormatString("dd/MM/yyyy");
		  	dateAu.setBounds(1035, 34, 155, 26);
		  	layeredPane.add(dateAu);
		  	
		  	JLabel lblDateAu = new JLabel("Date Au :");
		  	lblDateAu.setBounds(975, 34, 64, 26);
		  	layeredPane.add(lblDateAu);
		  	
		  	JScrollPane scrollPane = new JScrollPane();
		  	scrollPane.setBounds(9, 311, 1276, 404);
		  	add(scrollPane);
		  	
		  	table = new JTable();
		  	scrollPane.setViewportView(table);
		  	table.setModel(new DefaultTableModel(
		  		new Object[][] {
		  		},
		  		new String[] {
		  			"Article", "Sous Famille", "Magasin Source", "Article", "Sous Famille", "Magsin Destination", "Quantite"
		  		}
		  	));
		  	table.setFillsViewportHeight(true);
	  	
				  		 
	}
	
	
	
	
	void afficher_TransfertPF(List<DetailTransferProduitFini> listDetailTransferPF)
	{
		
		modele=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article", "Sous Famille", "Magasin Source", "Article", "Sous Famille", "Magsin Destination", "Quantite"
				}
			){
			  	boolean[] columnEditables = new boolean[] {
				  		false, false, false ,false, false, false ,false
				  	};
			  	
				  	public boolean isCellEditable(int row, int column) {
				  		return columnEditables[column];
				  	}
				  };
			setLayout(null);
		
		
	 List<DetailTransferProduitFini> listDetailTransferPFEntrer=new ArrayList<>();
	int i=0;
		while(i<listDetailTransferPF.size()){
			
			 
			  
			DetailTransferProduitFini detailTransferProduitFini=listDetailTransferPF.get(i);
			
			TransferStockPF transfererPFProduitFini=transferStockPFDAO.findByCodeTransfertByStatut(detailTransferProduitFini.getTransferStockPF().getCodeTransfer(), ETAT_TRANSFER_STOCK_ENTRER_PF_PF);
			if(transfererPFProduitFini!=null)
			{
				listDetailTransferPFEntrer=detailTransferStockPFdao.findByTransferStockPF(transfererPFProduitFini.getId());
				 
				if(listDetailTransferPFEntrer.size()!=0)
				{
					DetailTransferProduitFini detailTransferProduitFiniTmp=listDetailTransferPFEntrer.get(0);

					Object []ligne={detailTransferProduitFini.getArticle().getLiblle(),detailTransferProduitFini.getSousFamille().getLiblle(),detailTransferProduitFini.getMagasinDestination().getLibelle(),detailTransferProduitFiniTmp.getArticle().getLiblle(),detailTransferProduitFiniTmp.getSousFamille().getLiblle(),detailTransferProduitFiniTmp.getMagasinDestination().getLibelle(),detailTransferProduitFiniTmp.getQuantite()};
			       modele.addRow(ligne);
					
				}
				
				
			}
			
	              
	               
	i++;
	      
		}
		 table.setModel(modele); 
		
		
	}
	
	
	
	
	

	
	void intialiser()
	{
		comboDepotSource.setSelectedIndex(-1);
		comboMagasinSource.setSelectedIndex(-1);
		comboarticleSource.setSelectedIndex(-1);
		combomp.setSelectedIndex(-1);
		txtcodearticleSource.setText("");
		 
		dateDu.setCalendar(null);
		dateAu.setCalendar(null);
		combofamilleSource.setSelectedItem("");
		combosousfamilleSource.setSelectedItem("");
		comboarticleSource.setSelectedItem("");
	
	
	}

	








}
