package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import matierePremiere.ListeMatierePremiere;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MachineDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Articles;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.Journee;
import dao.entity.LigneMachine;
import dao.entity.Machine;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JTable;

public class CreerOrdreFabrication extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;

	private DefaultTableModel modeleMP;
	private DefaultTableModel modeleMPCAT;

	private JXTable table = new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgImprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;

	private JButton btnCalculeMP;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private JTextField quantite;
	private JTextField codeArticle;

	private Map<String, LigneMachine> ligneMachineMap = new HashMap<>();
	private Map<String, Magasin> magasinMap = new HashMap<>();
	private Map<String, Magasin> magasinPordMap = new HashMap<>();
	private Map<String, Magasin> magasinStockagePF = new HashMap<>();
	private Map<String, Equipe> mapEquipeProd = new HashMap<>();
	private Map<String, Equipe> mapEquipeGen = new HashMap<>();
	private Map<String, Articles> mapAricle = new HashMap<>();
	private Map<String, String> mapCodeArticle = new HashMap<>();
	private Map<String, String> mapLibelleAricle = new HashMap<>();
	private Map<String, Machine> machineMap = new HashMap<>();
	private Map<String, Depot> depotMap = new HashMap<>();
//	private Map< String, CategorieMp> mapCategorieMP = new HashMap<>();
	private Map<Integer, StockMP> mapQauntiteMatierePremier = new HashMap<>();

	private Map<String, Employe> mapEmployeTechnicien = new HashMap<>();
	private Map<String, Employe> mapEmployeAideTechnicien = new HashMap<>();
	private Map<String, Employe> mapEmployeChefProd = new HashMap<>();
	private Map<String, Employe> mapEmployeChefEquipe = new HashMap<>();
	private Map<String, Employe> mapListEmployeResponsable = new HashMap<>();
	private Map<String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map<Integer, MatierePremier> mapMatierePremierImp = new HashMap<>();
	private Map<String, BigDecimal> mapQuantiteMP = new HashMap<>();

	private Map<String, Promotion> mapPromotion = new HashMap<>();

	private Map<String, MatierePremier> mapMatierePremier = new HashMap<>();

	private Map<Integer, MatierePremier> mapMatierePremiereDetailMouvement = new HashMap<>();
	private Map<Integer, BigDecimal> mapQuantiteDetailMouvement = new HashMap<>();
	private JCheckBox checkBoxSrvice = new JCheckBox();

	private SousFamilleArticlesPFDAO sousFamilleArticleDAo;
	private MachineDAO machineDAO;
	private EquipeDAO equipeDAO;
	private ProductionDAO productionDAO;
	private StockMPDAO stockMPDAO;
	// private DetailProdGenDAO detailProdGenDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private EmployeDAO employeDAO;
	private static SequenceurDAO sequenceurDAO = new SequenceurDAOImpl();
	private JComboBox categorie;
	private JComboBox comboMachine;
	private JComboBox comboLigneMachine;
	private JComboBox comboMagasin;
	private JComboBox comboDepot = new JComboBox();
	private JComboBox comboPeriode = new JComboBox();
	private JComboBox comboMagasinPF = new JComboBox();
	private JComboBox comboDepotPF = new JComboBox();
	private JComboBox comboDepotProd = new JComboBox();
	private JComboBox comboMagasinProd = new JComboBox();
	private JButton btnajouterEstimation = new JButton("Ajouter Estimation");
	private JButton btnvaliderEstimation;
	private String nomMP;
	private DetailEstimationDAO detailestimationDAO;
	private ArticlesDAO articleDAO;
	private DepotDAO depotDAO;

	private CompteurProductionDAO compteurProductionDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private StockPFDAO stockPFDAO;
	private List<SousFamilleEnVrac> listSousFamilleEnVrac = new ArrayList<SousFamilleEnVrac>();
	private List<DetailMouvementStock> listDetailMouvementStock = new ArrayList<DetailMouvementStock>();
	private List<Machine> listMachine = new ArrayList<Machine>();
	private List<Depot> listDepot = new ArrayList<Depot>();
	private List<Equipe> listEquipeProd = new ArrayList<Equipe>();
	private List<Equipe> listEquipeGen = new ArrayList<Equipe>();
	private List<CoutMP> listCoutMP = new ArrayList<CoutMP>();
	private List<Employe> listEmploye = new ArrayList<Employe>();

	TransferStockMP transferStock = new TransferStockMP();
	private List<DetailTransferStockMP> listDetailTransferStockMP = new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;

	private List<Articles> listArticles = new ArrayList<Articles>();
	private List<DetailEstimation> lisDetailEstimation = new ArrayList<DetailEstimation>();
	private List<DetailEstimation> lisDetailEstimationbycategorie = new ArrayList<DetailEstimation>();
	private List<DetailResponsableProd> listDetailResponsableProd = new ArrayList<DetailResponsableProd>();
	private List<Promotion> listPromotion = new ArrayList<Promotion>();

	private Articles article = new Articles();
	private PromotionDAO promotiondao = new PromotionDAOImpl();

	private static Production production = new Production();
	private Utilisateur utilisateur;
	private JourneeDAO journeeDAO;
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	private SousFamilleEnVracDAO sousFamilleEnvracDAo;
	private JTextField txtNumOF;
	private JLabel lblDescriptionOf;
	private JTextField txtDescription;
	private JScrollPane scrollPaneMP = new JScrollPane((Component) null);
	private JDateChooser dateFinChooser;
	private JDateChooser dateDebutChooser;
	private JLabel label;
	private JLabel lblMagasinProd;
	private JLabel lblPriode;
	private JComboBox comboScotch;
	private JComboBox comboBoxThe;
	private JComboBox combopromotion;
	private JCheckBox checkPromotion;
	JCheckBox chckbxArticleMixte;
	boolean creerOF = true;
	private JTable tableMP;
	private JButton btnAjouterEstimation;
	private DetailTransferMPDAO detailTransferStockMPDAO;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public CreerOrdreFabrication() {
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1649, 565);
        try{
        	production = new Production();
        	articleDAO=new ArticlesDAOImpl();
        	machineDAO=new MachineDAOImpl();
        	equipeDAO=new EquipeDAOImpl();
        	productionDAO=new ProductionDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	detailestimationDAO=new DetailEstimationDAOImpl();
        	mouvementStockGlobalDAO=new MouvementStockGlobalDAOImpl();
        	journeeDAO=new JourneeDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	
        	  //	detailProdGenDAO=ProdLauncher.detailProdGenDAO;	
        //	detailProdGenDAO=ProdLauncher.detailProdGenDAO;
        	compteurProductionDAO=new CompteurProductionDAOImpl();
        	compteurResponsableProdDAO=new CompteurResponsableProdDAOImpl();
        	stockPFDAO=new StockPFDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	sousFamilleArticleDAo=new SousFamilleArticlesPFDAOImpl();
        	sousFamilleEnvracDAo=new SousFamilleEnVracDAOImpl();
        	nomMP="";
        	mapQauntiteMatierePremier = new HashMap<>();
        	chckbxArticleMixte = new JCheckBox("Article Mixte");
        	comboBoxThe = new JComboBox();
        	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
        	
        	listSousFamilleEnVrac=sousFamilleEnvracDAo.findAll();
        	
        	chckbxArticleMixte.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent arg0) {
        			if(chckbxArticleMixte.isSelected()==true)
	        		{
        				btnAjouterEstimation.setVisible(true);
        				intialiserTableauMP();
        				comboBoxThe.setSelectedIndex(-1);
        				comboBoxThe.setEnabled(false);
        				
        				
	        		}else
	        		{
	        		
	        			btnAjouterEstimation.setVisible(false);
	        			hide();
	        			comboBoxThe.removeAllItems();
		     	 		comboBoxThe.addItem("");
	        			comboBoxThe.setEnabled(true);
	        			int idArticle=mapAricle.get(categorie.getSelectedItem()).getId();
		     	 		
		     	 		List<DetailEstimation> listeDetailEstimationCatThe=articleDAO.listeMatierePremierByArticleByMP(idArticle, SOUS_CATEGORIE_MATIERE_PREMIERE_THE,PRIORITE_ESTIMATION_1);
		     			
		     			for(int i=0;i<listeDetailEstimationCatThe.size();i++)
		     	  		{	
		     				DetailEstimation detailEstimation=listeDetailEstimationCatThe.get(i);
		     				mapMatierePremier.put(detailEstimation.getMatierePremier().getNom(), detailEstimation.getMatierePremier());
		     	  			comboBoxThe.addItem(detailEstimation.getMatierePremier().getNom());
		     	  			
		     	  		}
	        		}
        			
        		}
        	});
        	chckbxArticleMixte.setOpaque(false);

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion a la base de donn�es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        comboScotch = new JComboBox();
        combopromotion = new JComboBox();
        comboPeriode.addItem("");
    	comboPeriode.addItem(PRPDUCTION_PERIODE_MATIN);
    	comboPeriode.addItem(PRPDUCTION_PERIODE_SOIR);
    	comboDepotProd.addItem("");
  		comboMagasinProd.addItem("");
  		comboDepot.addItem("");
  		comboScotch.addItem("");
  		String Codedepot = utilisateur.getCodeDepot();
  		
        try{
            	imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            	imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            	imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            	imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            	imgSupp1 = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             
          } catch (Exception exp){exp.printStackTrace();}
        
        intialiserTableau();
        
        String codeDepot=AuthentificationView.utilisateur.getCodeDepot();	
     
        codeArticle = new JTextField();
        util.Utils.copycoller(codeArticle);
        categorie = new JComboBox();
        categorie.addItem(""); 
        listArticles=articleDAO.findAll();
        int i=0;
	      	while(i<listArticles.size())
	      		{	
	      			Articles article=listArticles.get(i);
	      			mapCodeArticle.put(article.getLiblle(), article.getCodeArticle());
	      			mapLibelleAricle.put( article.getCodeArticle(),article.getLiblle());
	      			mapAricle.put(article.getLiblle(), article);
	      			categorie.addItem(article.getLiblle());
	      			i++;
	      		}
		
		categorie.addItemListener(new ItemListener() {

	public void itemStateChanged(ItemEvent e) {
	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	 		comboBoxThe.removeAllItems();
	     	 		comboBoxThe.addItem("");
	     	 		checkPromotion.setSelected(false);
		  			combopromotion.setEditable(false);
		  			combopromotion.removeAllItems();
	     	
	     	 		codeArticle.setText(mapCodeArticle.get(categorie.getSelectedItem()));
	     	 		
	     	 		int idArticle=mapAricle.get(categorie.getSelectedItem()).getId();
	     	 		
	     	 		List<DetailEstimation> listeDetailEstimationCatThe=articleDAO.listeMatierePremierByArticleByMP(idArticle, SOUS_CATEGORIE_MATIERE_PREMIERE_THE,PRIORITE_ESTIMATION_1);
	     			
	     			for(int i=0;i<listeDetailEstimationCatThe.size();i++)
	     	  		{	
	     				DetailEstimation detailEstimation=listeDetailEstimationCatThe.get(i);
	     				mapMatierePremier.put(detailEstimation.getMatierePremier().getNom(), detailEstimation.getMatierePremier());
	     	  			comboBoxThe.addItem(detailEstimation.getMatierePremier().getNom());
	     	  			
	     	  		}
                  
	     	 	 	}
	     	 	}
	     	 });
		
		
		List<MatierePremier> listeMatierePremierCatScotch=matierePremiereDAO.listeMatierePremierByCategorie(CATEGORIE_MATIERE_PREMIERE_SCOTCH);
		
		for( i=0;i<listeMatierePremierCatScotch.size();i++)
  		{	
			MatierePremier matierePremier=listeMatierePremierCatScotch.get(i);
			mapMatierePremier.put(matierePremier.getNom(), matierePremier);
  			comboScotch.addItem(matierePremier.getNom());
  			
  		}
	  
			
		
	
	      
		codeArticle.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyReleased(KeyEvent e)
		  	{
		  		if (e.getKeyCode() == e.VK_ENTER)
		  		{
		  			
		  			categorie.setSelectedItem(mapLibelleAricle.get(codeArticle.getText()));
		  			checkPromotion.setSelected(false);
		  			combopromotion.setEditable(false);
		  		}}});
		
		
				  		  btnImprimer = new JButton("Bon Sortie MP");
				  		  btnImprimer.setIcon(imgImprimer);
				  		  btnImprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  	if(lisDetailEstimation.size()<=0)
		  		     			JOptionPane.showMessageDialog(null, "Il faut calculer la mati�re Premi�re avant d'imprimer!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  	else {
				  		  
					  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					  		  	String date=dateFormat.format(dateDebutChooser.getDate());
								 
								Map parameters = new HashMap();
								parameters.put("numOF", txtNumOF.getText());
								parameters.put("machine", comboMachine.getSelectedItem());
								parameters.put("equipe", "");
								parameters.put("magasin", comboMagasin.getSelectedItem());
								parameters.put("dateProd", date);
								JasperUtils.imprimerBonSortieMatierePremiere(listCoutMP,parameters,production.getNumOF());
				  		  	}
								
								}
				  		  });
				  		
				  		
				  		 btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnImprimer.setBounds(362, 504, 133, 23);
				  		 add(btnImprimer);
				  		 comboMagasin = new JComboBox();		
				  		 comboMagasin.addItem("");
				  		  btnCalculeMP = new JButton("Calculer Mati\u00E8re ");
				  		  btnCalculeMP.setHorizontalAlignment(SwingConstants.LEADING);
				  		  btnCalculeMP.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		nomMP="";
				  		  //	intialiserTableau();
				  		  		if(categorie.getSelectedItem().equals("")){
				  		  			JOptionPane.showMessageDialog(null, "Il faut choisir l'article!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
				  		  		} else if(quantite.getText().equals("")){
				  		  		JOptionPane.showMessageDialog(null, "Il faut remplir la quantit�!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
				  		  		}  else if(comboMagasin.getSelectedItem().equals("")){
					  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin Source!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
					  		  		} else if(comboMagasinProd.getSelectedItem().equals("")){
						  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin Production!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		  			
						  		  		} else  if(comboScotch.getSelectedItem().equals("")){
							  		  		JOptionPane.showMessageDialog(null, "Il faut choisir Type de SCOTCH!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		  			
							  		  		} else{
							  		  			
							  		  		  if(chckbxArticleMixte.isSelected()==false && comboBoxThe.getSelectedItem().equals(""))
							  		  			
							  		  				JOptionPane.showMessageDialog(null, "Il faut choisir Type du THE VRAC!", "Attention", JOptionPane.INFORMATION_MESSAGE);
							  		  			else {
							  		  				
							  		  				
				  		
								  		  				article =mapAricle.get(categorie.getSelectedItem());
								  		  				lisDetailEstimation =	article.getDetailEstimation();
								  		  				if(article.getCentreCout5()!=null)
								  		  				{
								  		  					
								  		  				if(article.getCentreCout5().compareTo(BigDecimal.ONE)==0 && checkBoxSrvice.isSelected()==false)	
								  		  				{
								  		  					
								  		  				JOptionPane.showMessageDialog(null, "Il faut cocher le Service SVP", "Attention", JOptionPane.ERROR_MESSAGE);
								  		  				comboDepot.setSelectedIndex(-1);
								  		  					return;
								  		  				}
								  		  					
								  		  				}
								  		  				
				  		  	
								  		  				txtNumOF.setText(Utils.creerCodeOF(article.getCodeArticle(),magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode()));
							
								  		  				if(!afficherTableMatierePremiere(lisDetailEstimation)){
		  		     			
								  		  					int reponse = JOptionPane.showConfirmDialog(null, "La quantit� n'est pas suffaisante de la mati�re premi�re:\n"+nomMP+"\n"
								  		  							+ "Voulez vous importer le Stock Supp�lementaire ?", 
								  		  							"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
								  		  					if(reponse == JOptionPane.YES_OPTION )
								  		  					{
								  		  						JFrame popupJFrame = new MatierePremiere(mapQauntiteMatierePremier);
								  		  						popupJFrame.setVisible(true);
								
								  		  					} else {
								  		  						intialiserTableau();
								  		  					}
								  		  				}
							  		  				}
								  		  		}
							
				  		  	}
				  		  });
				  		  btnCalculeMP.setIcon(new ImageIcon(CreerOrdreFabrication.class.getResource("/img/chercher.png")));
				  		  btnCalculeMP.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  btnCalculeMP.setBounds(10, 504, 133, 23);
				  		  add(btnCalculeMP);
				  		   
				  		   categorie.setBounds(313, 12, 229, 26);
				  		   add(categorie);
				  		   
				  		   JLabel lblArticle = new JLabel("Article:");
				  		   lblArticle.setBounds(265, 11, 102, 26);
				  		   add(lblArticle);
				  		   lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   quantite = new JTextField();
				  		   util.Utils.copycoller(quantite);
				  		   quantite.setBounds(610, 12, 157, 26);
				  		   add(quantite);
				  		   quantite.setColumns(10);
				  		   
				  		   JLabel lblQuantite = new JLabel("Quantit\u00E9 :");
				  		   lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblQuantite.setBounds(552, 11, 68, 26);
				  		   add(lblQuantite);
				  		   comboLigneMachine = new JComboBox();
				  		   comboLigneMachine.addItem("");
				  		   codeArticle.setBounds(83, 11, 166, 26);
				  		   add(codeArticle);
				  		   codeArticle.setColumns(10);
				  		 
				  		   JLabel lblCodeArticle = new JLabel("Code Article");
				  		   lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCodeArticle.setBounds(10, 11, 82, 26);
				  		   add(lblCodeArticle);
				  		   dateDebutChooser = new JDateChooser();
				  		   dateFinChooser = new JDateChooser();
				  		    
				  		    btnAjouter = new JButton("Cr\u00E9er OF");
				  		    btnAjouter.setBounds(147, 504, 107, 23);
				  		    add(btnAjouter);
				  		    btnAjouter.setIcon(imgAjouter);
				  		    btnAjouter.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		Boolean existe=false;
				  		     		nomMP="";
				  		     		MouvementStockGlobal mouvementStockGlobal=null;
				  		     		production=productionDAO.findByNumOF(txtNumOF.getText(),codeDepot);
				  		     		
				  		     		if(production==null){
				  		     			
				  		     			
				  		     			
				  		     		/////////////////////////////////////////////////////////////////////////////// find sous Famille En Vrac   //////////////////////////////////////////////////////////////////////////////////////////	
				  		     			
				  		     			if(listCoutMP.size()>0)
				  		     			{
				  		     				for(int i=0;i<listCoutMP.size();i++)
				  		     				{
				  		     					CoutMP coutMP=listCoutMP.get(i);
				  		     					
				  		     					
				  		     					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		                         {
				  		     						existe=false;
				  		     						
				  		     						for(int j=0;j<listSousFamilleEnVrac.size();j++)
				  		     						{
				  		     							
				  		     						SousFamilleEnVrac sousFamilleEnVrac=listSousFamilleEnVrac.get(j);	
				  		     							if(sousFamilleEnVrac.getMatierePremier().getId()==coutMP.getMatierePremier().getId())
				  		     							{
				  		     								existe=true;
				  		     								
				  		     								
				  		     								
				  		     							}
				  		     							
				  		     							
				  		     							
				  		     						}
				  		     						
				  		     						if(existe==false)
				  		     						{
				  		     							
				  		     							JOptionPane.showMessageDialog(null, "Sous Famille En Vrac  "+coutMP.getMatierePremier().getNom() +" introuvable , Veuillez entrer le sous Famille Envrac ");
				  		     							
				  		     							
				  		     							return;
				  		     							
				  		     							
				  		     						}
				  		    	
				  		    	
				  		                        }
				  		     					
				  		     					
				  		     					
				  		     				}
				  		     			
				  		     				
				  		     			}
				  		     			
				  		   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  			
				  		     			
				  		     			
				  		     			
				  		     			production=new Production();
				  		     		
				  		     		
				  		     		if(lisDetailEstimation.size()<=0)
				  		     			JOptionPane.showMessageDialog(null, "Il faut calculer la mati�re Premi�re!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(comboLigneMachine.getSelectedItem().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir une ligne de machine!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(comboPeriode.getSelectedItem().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir une p�riode!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(dateDebutChooser.getDate()==null)
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir date d�but!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(dateFinChooser.getDate()==null)
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir date Fin!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if(comboMagasinPF.getSelectedItem().equals(""))
				  		     			JOptionPane.showMessageDialog(null, "Il faut choisir magasin produit fini!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     		else if(!afficherTableMatierePremiereCreerOF(listCoutMP)){
				  		     			if(nomMP.equals(""))
				  		     				JOptionPane.showMessageDialog(null, "OF ne peut pas etre cr�e !Il faut remplir toutes quantit�s !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			else
				  		     				JOptionPane.showMessageDialog(null, "OF ne peut pas etre cr�e !La quantit� : "+nomMP+"n'est pas suffaisante", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}else {
				  		     			
			  		     		
			  		     				dateDebutChooser.setDateFormatString("dd/MM/yyyy");
			  		     				dateFinChooser.setDateFormatString("dd/MM/yyyy");
				  		     			Date dateDebutPrevue=dateDebutChooser.getDate();
				  		     			Date dateFinPrevue=dateFinChooser.getDate();
						  				
									
				  		     			LigneMachine ligneMachine = ligneMachineMap.get(comboLigneMachine.getSelectedItem());
				  		     			Magasin magasinProd=magasinPordMap.get(comboMagasinProd.getSelectedItem());
				  		     			Magasin magasinStock=magasinMap.get(comboMagasin.getSelectedItem());
				  		     			Magasin magasinPF=magasinStockagePF.get(comboMagasinPF.getSelectedItem());
				  		     			
				  		     			/*//////////////////// Chercher Journee /////////////
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			Journee journee=journeeDAO.findByDateEtatOuverte(dateDebutChooser.getDate(), Constantes.ETAT_STATUT_OVERTE,depotMap.get(comboDepot.getSelectedItem()).getId());
				  		     			if(journee!=null)
				  		     			{
				  		     				 mouvementStockGlobal=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(dateDebutChooser.getDate(),depotMap.get(comboDepot.getSelectedItem()).getId(), magasinStock.getId());
				  		     				if(mouvementStockGlobal!=null)
				  		     				{
				  		     				
				  		     					listDetailMouvementStock=mouvementStockGlobal.getDetailMouvementStock();
				  		     					
				  		     				}
				  		     			
				  		     			
				  		     			}*/
				  		     			
				  		     			
				  		     			production.setDate(dateDebutPrevue);
				  		     			Sequenceur sequenceur =sequenceurDAO.findByLibelle(magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode());
				  		     			production.setStatut(ETAT_OF_CREER);
				  		     			production.setPeriode(comboPeriode.getSelectedItem().toString());
				  		     			production.setNumOF(txtNumOF.getText());
				  		     			production.setCodeDepot(codeDepot);
				  		     			production.setDescription(txtDescription.getText());
				  		     			production.setDate_debFabPre(dateDebutPrevue);
				  		     			production.setDateFinFabPre(dateFinPrevue);
				  		     			production.setQuantiteEstime(new BigDecimal(quantite.getText()));
				  		     			production.setUtilisateurCreation(AuthentificationView.utilisateur);
				  		     			production.setLigneMachine(ligneMachine);
				  		     			production.setMagasinProd(magasinProd);
				  		     			production.setMagasinStockage(magasinStock);
				  		     			production.setMagasinPF(magasinPF);
				  		     			production.setArticles(article);
				  		     			production.setListDetailResponsableProd(listDetailResponsableProd);
				  		     			production.setArticleMixte(chckbxArticleMixte.isSelected());
				  		     if(checkBoxSrvice.isSelected()==true)
				  		     {
				  		    	production.setService(PRODUCTION_SERVICE_OUI); 
				  		     }else
				  		     {
				  		    	production.setService(PRODUCTION_SERVICE_NON); 
				  		     }
				  		     
				  		   if(checkPromotion.isSelected()==true)
			  		   		{
				  			   Promotion promotion=mapPromotion.get(combopromotion.getSelectedItem());
				  			 production.setOffre(promotion.getCode());
				  			   
			  		   		}
				  		     
				  		     			productionDAO.add(production);
				  		     			compterProduction(dateDebutPrevue,comboPeriode.getSelectedItem().toString());
				  		     			
				  		     			/*/////////////////////////////// chercher MP dans la liste detailMouvementStock et Modifier Quantite Charger et leStock Finale ////////////////////////////////
				  		     			
				  		     			
				  		     			for(int j=0;j<listDetailMouvementStock.size();j++)
				  		     			{
				  		     				MatierePremier matierepremiere=listDetailMouvementStock.get(j).getMatierePremier();
				  		     				DetailMouvementStock detailMouvementStock=listDetailMouvementStock.get(j);
				  		     				MatierePremier matierepremiereTmp=mapMatierePremiereDetailMouvement.get(matierepremiere.getId());
				  		     				if(matierepremiereTmp!=null)
				  		     				{
				  		     					
				  		     					detailMouvementStock.setCharge(detailMouvementStock.getCharge().add(mapQuantiteDetailMouvement.get(matierepremiereTmp.getId())));	
				  		     					detailMouvementStock.setStockFinaldb((detailMouvementStock.getInitial().add(detailMouvementStock.getReception()).add(detailMouvementStock.getTransfertEntrees()).subtract((detailMouvementStock.getSorties().add(detailMouvementStock.getTransfertSorties()).add(detailMouvementStock.getCharge()).add(detailMouvementStock.getChargeSupplementaire())))).add(detailMouvementStock.getRetour()));
				  		     					
				  		     					listDetailMouvementStock.set(j, detailMouvementStock);
				  		     					
				  		     					
				  		     				}
				  		     				
				  		     				
				  		     			}
				  		     			if(mouvementStockGlobal!=null)
				  		     			{
				  		     				mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);
				  		     				
				  		     				mouvementStockGlobalDAO.edit(mouvementStockGlobal);
				  		     				
				  		     			}
				  		     			*/
				  		     			
				  		     	
				  		     			
				  		     			
				  		     			Utils.incrementerValeurSequenceur (magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode());
				  		     			
				  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication Cr�e Avec Succ�s!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			
				  		     			
				  		     			/*PARAMETRAGE EMAIL
				  		     			try {
											EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
												"OF Cr�e avec succ�s",
												registerMailBody());
										} catch (AddressException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (MessagingException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}*/
				  		     			
				  		     			btnImprimer.setVisible(true);
			  		     			//	}	
				  		     		}
				  		     		

				  		     		}else {
					  		     		
					  		     		JOptionPane.showMessageDialog(null, "Cet Ordre de Fabrication est d�j� cr�e !", "Attention", JOptionPane.INFORMATION_MESSAGE);	
					  		     	}
				  		     	}
				  		     });
				  		    btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		     btnInitialiser = new JButton("Initialiser");
				  		     btnInitialiser.setBounds(257, 504, 102, 23);
				  		     add(btnInitialiser);
				  		     btnInitialiser.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnInitialiser.setIcon(imgInit);
				  		     btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
				  		     table.setShowVerticalLines(false);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		     table.setRowHeightEnabled(true);
				  		     table.setBackground(new Color(255, 255, 255));
				  		     table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table.setColumnControlVisible(true);
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 95, 1117, 226);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	 
				  		     	
				  			   
				  		  		List<Machine> listMachine= machineDAO.findListMachineByCodeDepot(Codedepot);
				  		     comboMachine=new JComboBox ();
				  		     comboMachine.addItem("");
				  		       i=0;
				  		      	while(i<listMachine.size())
				  		      		{	
				  		      			Machine machine=listMachine.get(i);
				  		      			machineMap.put(machine.getNom(), machine);
				  		      			comboMachine.addItem(machine.getNom());
				  		      			i++;
				  		      		}
			  		     	 
			  		    
			  		     
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 68, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 332, 1116, 161);
				  		     	add(layeredPane);
				  		     	
				  		     	txtNumOF = new JTextField();
				  		      util.Utils.copycoller(txtNumOF);
				  		     	txtNumOF.addKeyListener(new KeyAdapter() {
				  			  	@Override
				  			  	public void keyReleased(KeyEvent e)
				  			  	{
				  			  		if (e.getKeyCode() == e.VK_ENTER)
				  			  		{
				  			  			
				  			  			production=productionDAO.findByNumOF(txtNumOF.getText(),codeDepot);
				  			  			comboDepotPF.setSelectedItem(production.getMagasinPF().getDepot().getLibelle());
				  			  		
				  			  		}}});
				  		     
				  		     	txtNumOF.setBounds(78, 11, 144, 26);
				  		     	layeredPane.add(txtNumOF);
				  		     	txtNumOF.setColumns(10);
				  		     	
				  		     	JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
				  		     	lblNumOF.setBounds(10, 12, 89, 24);
				  		     	layeredPane.add(lblNumOF);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Machine");
				  		     	lblMachine.setBounds(10, 86, 58, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 
				  		     	 comboMachine.setBounds(78, 87, 163, 24);
				  		     	 layeredPane.add(comboMachine);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Ligne Machine");
				  		     	 lblGroupe.setBounds(251, 86, 77, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboLigneMachine.setBounds(338, 87, 215, 24);
				  		     	 layeredPane.add(comboLigneMachine);
				  		  
				  		  JLabel lblDatePrevue = new JLabel("Date D\u00E9but");
				  		  lblDatePrevue.setBounds(10, 48, 77, 26);
				  		  layeredPane.add(lblDatePrevue);
				  		  
				  		  
				  		  dateDebutChooser.setLocale(Locale.FRANCE);
				  		  dateDebutChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateDebutChooser.setBounds(78, 48, 144, 26);
				  		  layeredPane.add(dateDebutChooser);
				  		  
				  		  JLabel lblDateFin = new JLabel("Date Fin");
				  		  lblDateFin.setBounds(251, 48, 77, 26);
				  		  layeredPane.add(lblDateFin);
				  	
				  		  dateFinChooser.setLocale(Locale.FRANCE);
				  		  dateFinChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateFinChooser.setBounds(337, 48, 144, 26);
				  		  layeredPane.add(dateFinChooser);
				  		  
				  		  lblDescriptionOf = new JLabel("Description OF");
				  		  lblDescriptionOf.setBounds(251, 12, 89, 24);
				  		  layeredPane.add(lblDescriptionOf);
				  		  
				  		  txtDescription = new JTextField();
				  		util.Utils.copycoller(txtDescription);
				  		  txtDescription.setColumns(10);
				  		  txtDescription.setBounds(337, 14, 320, 26);
				  		  layeredPane.add(txtDescription);
				  		  
				  		  label = new JLabel("D\u00E9pot");
				  		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  label.setBounds(10, 126, 68, 24);
				  		  layeredPane.add(label);
				  		  
				  		  comboDepotProd.setBounds(73, 127, 166, 24);
				  		  layeredPane.add(comboDepotProd);
				  		  
				  		  lblMagasinProd = new JLabel("Magasin Prod");
				  		  lblMagasinProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  lblMagasinProd.setBounds(251, 127, 89, 24);
				  		  layeredPane.add(lblMagasinProd);
				  		  
				  		
				  		  comboMagasinProd.setBounds(338, 128, 215, 24);
				  		  layeredPane.add(comboMagasinProd);
				  		  
				  		  JLabel lblDpotStockageProduit = new JLabel("D\u00E9pot Stockage Produit Fini");
				  		  lblDpotStockageProduit.setBounds(605, 86, 167, 24);
				  		  layeredPane.add(lblDpotStockageProduit);
				  		  lblDpotStockageProduit.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  comboDepotPF.setBounds(782, 87, 247, 24);
				  		  layeredPane.add(comboDepotPF);
				  		  comboDepotPF.addItem("");
				  		  
				  		  JLabel lblMagasinProduitFini = new JLabel("Magasin Produit Fini");
				  		  lblMagasinProduitFini.setBounds(605, 125, 151, 24);
				  		  layeredPane.add(lblMagasinProduitFini);
				  		  lblMagasinProduitFini.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  comboMagasinPF.setBounds(780, 127, 249, 24);
				  		  layeredPane.add(comboMagasinPF);
				  		  comboMagasinPF.addItem("");
				  		  
				  		   checkPromotion = new JCheckBox("Offre");
				  		   checkPromotion.setBounds(782, 13, 58, 23);
				  		   layeredPane.add(checkPromotion);
				  		   combopromotion = new JComboBox();
				  		   combopromotion.setBounds(846, 12, 173, 24);
				  		   layeredPane.add(combopromotion);
				  		   checkPromotion.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   		
				  		   		if(checkPromotion.isSelected()==true)
				  		   		{
				  		   			//combopromotion.setVisible(true);
				  		   		combopromotion.setEditable(true);
				  		   		combopromotion.setEnabled(true);
				  		   			listPromotion.clear();
				  		   			combopromotion.removeAllItems();
				  		   			if(categorie.getSelectedIndex()!=-1 && !categorie.getSelectedItem().equals(""))
				  		   			{
				  		   				Articles article=mapAricle.get(categorie.getSelectedItem());
				  		   				if(article!=null)
				  		   				{
				  		   			listPromotion=promotiondao.findByArticleActif(article.getId());
				  		   		
				  		   			if(listPromotion.size()!=0)
				  		   			{
				  		   				for(int i=0;i<listPromotion.size();i++)
				  		   				{
				  		   					Promotion promotion=listPromotion.get(i);
				  		   					combopromotion.addItem(promotion.getCode());
				  		   					mapPromotion.put(promotion.getCode(), promotion);
				  		   				}
				  		   			}
				  		   		   }
				  		   		 }
				  		   		}else
				  		   		{
				  		   			combopromotion.setEditable(false);
				  		   			combopromotion.setEnabled(false);
				  		   			combopromotion.removeAllItems();
				  		   		}
				  		   	}
				  		   });
				  		  
				  		  comboDepotPF.addItemListener(new ItemListener() {
		  		     	 	public void itemStateChanged(ItemEvent e) {
		  		     	 	
		  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
			  		     	  	 // comboGroupe = new JComboBox();
		  		     	 	comboMagasinPF.removeAllItems();
		  		     	 	//comboGroupe.addItem("");
			  		     	  	   	Depot depot =depotMap.get(comboDepotPF.getSelectedItem());
			  		     	  	   	
			  		     	  
			  		     	 	if(checkBoxSrvice.isSelected()==true)
			  		     	  	{
			  		     	  
			  		     	 	Magasin MagasinElNassTeaPacking=depotDAO.magasinByCode(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING);	
					  		      			
					  		      Magasin AutreMagasinMP=magasinMap.get(comboMagasin.getSelectedItem())	;
					  		      
					  		      if(AutreMagasinMP!=null)
					  		      {
					  		    	  
					  		    	listMagasin= depotDAO.findMagasinPFAutreDepot(MAGASIN_CODE_TYPE_PF, AutreMagasinMP.getDepot().getId());
					  		    	  
					  		      }else
					  		      {
					  		    	listMagasin= depotDAO.findMagasinPFAutreDepot(MAGASIN_CODE_TYPE_PF, MagasinElNassTeaPacking.getDepot().getId());
					  		      }
					  		      	
					  		      	
							  		      if(listMagasin!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      		comboMagasinPF.addItem(magasin.getLibelle());
							  		      			magasinStockagePF.put(magasin.getLibelle(), magasin);	
								  		      			j++;
								  		      		}
							  		      }	
					  		      			
					  		      			
					  		      			
					  		      			
					  		      			
					  		      
			  		     	  	}else
			  		     	  	{
			  		     	    	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_PF);
						  		      if(listMagasin!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listMagasin.size())
							  		      		{	
							  		      			Magasin magasin=listMagasin.get(j);
							  		      			comboMagasinPF.addItem(magasin.getLibelle());
							  		      			magasinStockagePF.put(magasin.getLibelle(), magasin);
							  		      			j++;
							  		      		}
						  		      }
			  		     	  	}
			  		     	  	   	
			  		     	  	   	
			  		     	  	
		  		     	 	 }
		  		     	 	}
		  		     	 });
				  		
				  		  comboDepot.setBounds(83, 48, 166, 24);
				  		  add(comboDepot);
				  		  if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)) {
				  			Depot	 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
					    		depotMap.put(depot.getLibelle(), depot);
		  		      			comboDepot.addItem(depot.getLibelle());
		  		      			comboDepotProd.addItem(depot.getLibelle());
		  		      			comboDepotPF.addItem(depot.getLibelle());
					    }else {
				  		  listDepot = depotDAO.findAll();	
				  		 Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
				  		       i=0;
				  		      	while(i<listDepot.size())
				  		      		{	
				  		      			Depot depot=listDepot.get(i);
				  		      			if(magasin!=null)
				  		      			{
				  		      				if(depot.getId()!=magasin.getDepot().getId())
				  		      				{
				  		      				depotMap.put(depot.getLibelle(), depot);
					  		      			comboDepot.addItem(depot.getLibelle());
					  		      			comboDepotProd.addItem(depot.getLibelle());
					  		      			comboDepotPF.addItem(depot.getLibelle());
				  		      					
				  		      				}
				  		      				
				  		      			}else
				  		      			{
				  		      				
				  		      			depotMap.put(depot.getLibelle(), depot);
				  		      			comboDepot.addItem(depot.getLibelle());
				  		      			comboDepotProd.addItem(depot.getLibelle());
				  		      			comboDepotPF.addItem(depot.getLibelle());	
				  		      				
				  		      			}
				  		      			
				  		      			i++;
				  		      		}
					    }
				  		  JLabel lblDpot = new JLabel("D\u00E9pot");
				  		  lblDpot.setBounds(10, 48, 58, 24);
				  		  add(lblDpot);
				  		  lblDpot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 
				  		   comboMagasin.setBounds(313, 49, 229, 24);
				  		   add(comboMagasin);
				  		   
				  		   JLabel lblMagasin = new JLabel("Magasin");
				  		   lblMagasin.setBounds(259, 49, 77, 24);
				  		   add(lblMagasin);
				  		   lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		  
				  		   comboPeriode.setBounds(612, 49, 157, 24);
				  		   add(comboPeriode);
				  		   
				  		   lblPriode = new JLabel("P\u00E9riode :");
				  		   lblPriode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblPriode.setBounds(552, 48, 77, 24);
				  		   add(lblPriode);
				  		   
				  		   JButton btnImprimerEquipe = new JButton("Fiche Equipe Prod");
				  		   btnImprimerEquipe.setIcon(imgImprimer);
				  		   btnImprimerEquipe.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		if(production.getId()>0){
				  		   		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		   		String date=dateFormat.format(production.getDate());
								 List<Employe> listEmploye=production.getEquipe().getListEmploye();
								Map parameters = new HashMap();
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine", production.getLigneMachine().getMachine().getNom());
								parameters.put("equipe", production.getEquipe().getNomEquipe());
								parameters.put("magasin", production.getMagasinProd().getLibelle());
								parameters.put("dateProd",date);
								JasperUtils.imprimerListeEmploye(listEmploye,parameters,production.getNumOF());
								//JasperUtils.imprimerListeEmployeTab(listEmploye);
							//	JOptionPane.showMessageDialog(null, "PDF export� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				  		   		}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Cr�er OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   btnImprimerEquipe.setBounds(498, 504, 142, 23);
				  		   add(btnImprimerEquipe);
				  		   
				  		   JButton btnImprimerFicherEquipeGen = new JButton("Fiche Equipe Emabalage");
				  		   btnImprimerFicherEquipeGen.setIcon(imgImprimer);
				  		   btnImprimerFicherEquipeGen.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   		if(production.getId()>0){
				  		   			
				  		   		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		   		String date=dateFormat.format(production.getDate());
				  		   		List<Employe> listEmploye=production.getEquipeGen().getListEmploye();
								Map parameters = new HashMap();
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine",production.getLigneMachine().getMachine().getNom());
								parameters.put("equipe", production.getEquipeGen().getNomEquipe());
								parameters.put("magasin",production.getMagasinProd().getLibelle());
								parameters.put("dateProd", date);
								JasperUtils.imprimerFicheEquipeGen(listEmploye,parameters,production.getNumOF());
								//JOptionPane.showMessageDialog(null, "PDF export� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
								
				  		   		}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Cr�er OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
				  		   		
				  		   	}
				  		   });
				  		   btnImprimerFicherEquipeGen.setBounds(650, 504, 167, 24);
				  		   add(btnImprimerFicherEquipeGen);
				  		   
				  		   JButton btnImprimerGen = new JButton("imprimer Equipe Gen");
				  		   btnImprimerGen.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		//JasperUtils.imprimerInterne()
				  		   		
				  		  	if(production.getId()>0){
			  		   			
				  		   		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		   		String date=dateFormat.format(production.getDate());
				  		   		List<Employe> listEmploye=employeDAO.findAutreEmploye();
								Map parameters = new HashMap();
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine",production.getLigneMachine().getMachine().getNom());
								//parameters.put("equipe", production.getEquipeGen().getNomEquipe());
								parameters.put("magasin",production.getMagasinProd().getLibelle());
								parameters.put("dateProd", date);
								JasperUtils.imprimerFicheEquipeGen2(listEmploye,parameters,production.getNumOF());
								//JOptionPane.showMessageDialog(null, "PDF export� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
								
				  		   		}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Cr�er OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
				  		   	}
				  		   });
				  		   btnImprimerGen.setBounds(819, 504, 133, 23);
				  		   add(btnImprimerGen);
				  		   
				  		 
				  		   comboScotch.setBounds(866, 12, 157, 24);
				  		   add(comboScotch);
				  		   
				  		   JLabel lblScotche = new JLabel("Scotche :");
				  		   lblScotche.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblScotche.setBounds(785, 12, 77, 24);
				  		   add(lblScotche);
				  		   
				  		   JLabel lblThEnVrac = new JLabel("Th\u00E9 En Vrac :");
				  		   lblThEnVrac.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblThEnVrac.setBounds(785, 48, 77, 24);
				  		   add(lblThEnVrac);
				  		   
				  		    
				  		   comboBoxThe.setBounds(866, 48, 210, 24);
				  		   add(comboBoxThe);
				  		   
				  		   JButton btnValiderBonSortieMP = new JButton("Valider Bon Sortie MP");
				  		   btnValiderBonSortieMP.setBounds(959, 504, 167, 24);
				  		   add(btnValiderBonSortieMP);

				  		   btnValiderBonSortieMP.setIcon(imgImprimer);
				  		
				  		
				  		chckbxArticleMixte.setBounds(1165, 12, 97, 23);
				  		add(chckbxArticleMixte);
				  		
				  		 scrollPaneMP = new JScrollPane((Component) null);
				  		scrollPaneMP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		scrollPaneMP.setBounds(1136, 48, 513, 337);
				  		scrollPaneMP.setVisible(false);
				  		add(scrollPaneMP);
				  		
				  		tableMP = new JTable();
				  		tableMP.setModel(new DefaultTableModel(
				  			new Object[][] {
				  			},
				  			new String[] {
				  				"Code", "Nom Matiere Premiere", "Quantit\u00E9 Estim\u00E9"
				  			})
				  			 {
						     	boolean[] columnEditables = new boolean[] {
						     			false,false,true
						     	};
						     	public boolean isCellEditable(int row, int column) {
						     		return columnEditables[column];
						     	}});
				  		
				  		tableMP.setFillsViewportHeight(true);
				  		scrollPaneMP.setViewportView(tableMP);
				  		
				  		 btnvaliderEstimation = new JButton("Valider Estimation");
				  		 btnvaliderEstimation.addActionListener(new ActionListener() {
				  		 	public void actionPerformed(ActionEvent arg0) {
				  		 		

								boolean trouve=false;
								
								BigDecimal number ; 
								BigDecimal somme = new BigDecimal("0");
								BigDecimal result = new BigDecimal("1");
							if(!remplirMapQuantiteEstimation())	{
								
								JOptionPane.showMessageDialog(null, "Il faut remplir la quantit�", "Erreur", JOptionPane.ERROR_MESSAGE);
							} else {
								List<DetailEstimation> listeDetailEstimationtmp= remplirDetailEstimation();
								
									for(int i=0;i<listeDetailEstimationtmp.size();i++)
									{
										if(listeDetailEstimationtmp.get(i).getQuantite().compareTo(BigDecimal.ONE) >=0 || listeDetailEstimationtmp.get(i).getQuantite().compareTo(BigDecimal.ZERO)  <=0)
										{
											trouve=true;
										}
									}
									
									if(trouve==true)
									{
										JOptionPane.showMessageDialog(null, "la Quantit� Mixte doit etre entre 0 et 1", "Erreur", JOptionPane.ERROR_MESSAGE);
										return;
									}else
									{
										
										for(int i=0;i<listeDetailEstimationtmp.size();i++)
										{
											number =new BigDecimal(listeDetailEstimationtmp.get(i).getQuantite()+"");
											somme=somme.add(number);
										}
										
										if(somme.compareTo(result)==1)
										{
											JOptionPane.showMessageDialog(null, "La somme de la quantit� Mixte doit etre egale 1 ", "Erreur", JOptionPane.ERROR_MESSAGE);
											return;
										}
										else
										{
											
											article=articleDAO.findByCode(codeArticle.getText());
											List<DetailEstimation> listeDetailEstimation= remplirDetailEstimation();
											article.setDetailEstimation(listeDetailEstimation);
												
											articleDAO.edit(article);
											JOptionPane.showMessageDialog(null, "Article ajout� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
											intialiserTableauMP();
											hide();
											
										}
											
										
									}
							
								
							}
						  
				  		 		
				  		 		
				  		 	}
				  		 });
				  		btnvaliderEstimation.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnvaliderEstimation.setBounds(1334, 396, 158, 23);
				  		btnvaliderEstimation.setIcon(imgAjouter);
				  		btnvaliderEstimation.setVisible(false);
				  		add(btnvaliderEstimation);
				  		
				  		btnAjouterEstimation = new JButton("Ajouter Estimation");
				  		btnAjouterEstimation.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent e) {
				  				

				        		
				        			
				        			if(!categorie.getSelectedItem().equals(""))
				        			{
				        				if(codeArticle.getText()!="")
				        				{
				        						show();
				        							Articles article=mapAricle.get(categorie.getSelectedItem());
				        							lisDetailEstimationbycategorie=detailestimationDAO.findDetilestimationByCategorie(article.getId());
				        						
				        							if(lisDetailEstimationbycategorie.size()!=0)
				        							{
				        								intialiserTableauMP();
				        							 	List<MatierePremier> listeMP = matierePremiereDAO.listeMatierePremierByCategorie(lisDetailEstimationbycategorie.get(0).getMatierePremier().getCategorieMp().getCode());	
				        					
				        								afficher_tableMatierPremier(listeMP);
				        							}else
				        							{
				        								intialiserTableauMP();
				        							}
				        						
				        				
				        				}else
				        				{
				        					
				        				JOptionPane.showMessageDialog(null, "Merci de Choisir l'article avant de continue SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
				        				return;
				        				}
				        				
				        			}else
				        			{
				        				
				        				JOptionPane.showMessageDialog(null, "Merci de saisir code article avant de continue SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
				        				return;
				        			}
				        			
				        			
				  				
				  				
				  			}
				  		});
				  		btnAjouterEstimation.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnAjouterEstimation.setBounds(1302, 13, 133, 23);
				  		btnAjouterEstimation.setVisible(false);
				  		add(btnAjouterEstimation);
				  		
				  		 checkBoxSrvice = new JCheckBox("Production Service");
				  		checkBoxSrvice.setBounds(1035, 11, 118, 24);
				  		add(checkBoxSrvice);
				  		
				  		
				  		btnValiderBonSortieMP.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	
				  		  	if(production.getId()>0){
				  		  	List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
				  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		  	String date=dateFormat.format(production.getDate());
							listCoutMP=productionDAO.listeCoutMP(production.getId());
							 
							Map parameters = new HashMap();
							parameters.put("numOF", production.getNumOF());
							parameters.put("machine", production.getLigneMachine().getMachine().getNom());
							parameters.put("equipe", production.getEquipe().getNomEquipe());
							parameters.put("magasin", production.getMagasinProd().getLibelle());
							parameters.put("dateProd", date);
							JasperUtils.imprimerValiderBonSortieMatierePremiere(listCoutMP,parameters,production.getNumOF());
							
							//JOptionPane.showMessageDialog(null, "PDF export� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				  		  	}else {
				  		  	JOptionPane.showMessageDialog(null, "Il faut Cr�er OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
				  		  	}
				  		  	}
				  		  });
				  		   
				  		  
				  		   comboDepot.addItemListener(new ItemListener() {
			  		     	 	public void itemStateChanged(ItemEvent e) {
			  		     	 	
			  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
			  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
				  		     	  	 // comboGroupe = new JComboBox();
			  		     	 	comboMagasin.removeAllItems();
			  		     	 	//comboGroupe.addItem("");
				  		     	  	   	Depot depot =depotMap.get(comboDepot.getSelectedItem());
						  		       if(checkBoxSrvice.isSelected()==true)
						  		       {
						  		    	   
						  		    	Magasin MagasinElNassTeaPacking=depotDAO.magasinByCode(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING);
						  		    	   
						  		    	 listMagasin=depotDAO.findMagasinMPAutreDepot(MAGASIN_CODE_TYPE_MP,MagasinElNassTeaPacking.getId());
						  		    	
				  		      	      if(listMagasin!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listMagasin.size())
							  		      		{	
							  		      			Magasin magasin=listMagasin.get(j);
							  		      		comboMagasin.addItem(magasin.getLibelle());
						  		      			magasinMap.put(magasin.getLibelle(), magasin);
							  		      			j++;
							  		      		}
						  		      }
				  		      			
				  		      			
				  		      			
				  		      			
				  		      			
						  		    	   
						  		       }else
						  		       {
						  		    	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,MAGASIN_CODE_CATEGORIE_STOCKAGE);
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasin.addItem(magasin.getLibelle());
									  		      			magasinMap.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
						  		       }
				  		     	
			  		     	 	 }
			  		     	 	}
			  		     	 });
				  		
				  		comboDepotProd.addItemListener(new ItemListener() {
		  		     	 	public void itemStateChanged(ItemEvent e) {
		  		     	 	
		  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		  		     	 		 if(comboMachine.getSelectedItem()!=null && !comboMachine.getSelectedItem().equals("")){
		  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
			  		     	  	 // comboGroupe = new JComboBox();
		  		     	 	 		comboMagasinProd.removeAllItems();
		  		     	 	 	//comboGroupe.addItem("");
			  		     	  	   	Depot depot =depotMap.get(comboDepotProd.getSelectedItem());
			  		     	  	 Machine machine =machineMap.get(comboMachine.getSelectedItem());
			  		     	  
			  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,machine.getMatricule());
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinProd.addItem(magasin.getLibelle());
						  		      			magasinPordMap.put(magasin.getLibelle(), magasin);
						  		      			j++;
						  		      		}
					  		      }
			  		     	  	}
			  		     	 
		  		     	 	 
		  		     	 		else {
			  		     	 		JOptionPane.showMessageDialog(null, "Il faut Choisir une machine", "Erreur", JOptionPane.ERROR_MESSAGE);
			  		     	 	 }
		  		     	 	 }
		  		     	 	}
		  		     	 });
				  		  comboMachine.addItemListener(new ItemListener() {
			  		     	 	public void itemStateChanged(ItemEvent e) {
			  		     	 	
			  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
			  		     	 		 
			  		     	 		 if(!comboDepotProd.getSelectedItem().equals("")){

					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasinProd.removeAllItems();
					  		     	 	//comboGroupe.addItem("");
						  		     	  	   	Depot depot =depotMap.get(comboDepotProd.getSelectedItem());
						  		     	  	 Machine machine =machineMap.get(comboMachine.getSelectedItem());
						  		     	  	
						  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,machine.getMatricule());
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasinProd.addItem(magasin.getLibelle());
									  		      			magasinPordMap.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
					  		     	 	 
			  		     	 			 
			  		     	 		 }
			  		     	 	 List<LigneMachine> listLigneMachine=new ArrayList<LigneMachine>();
			  		     	 	comboLigneMachine.removeAllItems();
				  		     	 Machine machine =machineMap.get(comboMachine.getSelectedItem());
						  		       
						  		      listLigneMachine = machine.getListLigneMachine();
						  		      if(listLigneMachine!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listLigneMachine.size())
							  		      		{	
							  		      			LigneMachine ligneMachine=listLigneMachine.get(j);
							  		      			comboLigneMachine.addItem(ligneMachine.getNom());
							  		      			ligneMachineMap.put(ligneMachine.getNom(), ligneMachine);
							  		      			j++;
							  		      		}
						  		      }
			  		     	 	 }
			  		     	 	}
			  		     	 });
				  		 
	}

	void intialiser() {
		quantite.setText("");
		codeArticle.setText("");
		categorie.setSelectedItem("");

	}

	void intialiserTableau() {

		modeleMP = new DefaultTableModel(new Object[][] {}, new String[] { "Code", "Nom Mati�re Premi�re",
				"Quantit� Calcul�e", "Quantit� Existante", "Quantit� Manquante", "Quantit� A Charg�e" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(modeleMP);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(260);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		table.getColumnModel().getColumn(5).setPreferredWidth(160);
	}

	void afficher_tableCouMP(List<CoutMP> listCoutMP) {
		intialiserTableau();

		for (int i = 0; i < listCoutMP.size(); i++) {
			{
				CoutMP coutMP = listCoutMP.get(i);
				if (
						coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)) {

					Object[] ligne = { coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
							NumberUtils.GroupingFormatBigDecimal(coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP))
									+ " " + coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
							NumberUtils.GroupingFormatBigDecimal(coutMP.getQuantExistante().setScale(6)),
							NumberUtils
									.GroupingFormatBigDecimal(coutMP.getQuantEstime().setScale(0, BigDecimal.ROUND_UP)),
							coutMP.getQuantEstime().setScale(0, BigDecimal.ROUND_UP) };

					modeleMP.addRow(ligne);

				}else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
				{
					

					Object[] ligne = { coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
							NumberUtils.GroupingFormatBigDecimal(coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP))
									+ " " + coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
							NumberUtils.GroupingFormatBigDecimal(coutMP.getQuantExistante().setScale(6)),
							NumberUtils
									.GroupingFormatBigDecimal(coutMP.getQuantEstime().setScale(0, BigDecimal.ROUND_DOWN)),
							coutMP.getQuantEstime().setScale(0, BigDecimal.ROUND_DOWN) };

					modeleMP.addRow(ligne);
					
				}
				
				
				else if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {
					Object[] ligne = { coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
							NumberUtils.GroupingFormatBigDecimal(coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP))
									+ " " + coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
							NumberUtils.GroupingFormatBigDecimal(coutMP.getQuantExistante().setScale(6)),
							NumberUtils
									.GroupingFormatBigDecimal(coutMP.getQuantEstime().setScale(0, BigDecimal.ROUND_UP)),
							coutMP.getQuantEstime().setScale(0, BigDecimal.ROUND_UP) };

					modeleMP.addRow(ligne);
				} else

				{

					Object[] ligne = { coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
							NumberUtils.GroupingFormatBigDecimal(coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP))
									+ " " + coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
							NumberUtils.GroupingFormatBigDecimal(
									coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP)),
							NumberUtils.GroupingFormatBigDecimal(
									coutMP.getQuantEstime().setScale(6, RoundingMode.HALF_UP)),
							coutMP.getQuantEstime().setScale(6, RoundingMode.HALF_UP) };
					modeleMP.addRow(ligne);
				}

			}
		}
	}

boolean afficherTableMatierePremiere(List<DetailEstimation> lisDetailEstimation){
	creerOF=true;
	BigDecimal prix_unitaire = BigDecimal.ZERO;
	BigDecimal quantiteExistante=BigDecimal.ZERO;
	BigDecimal quantiteACharge=BigDecimal.ZERO;
	BigDecimal quantiteAChargeTHE=BigDecimal.ZERO;
	BigDecimal quantiteTotal=BigDecimal.ZERO;
	BigDecimal quantiteManqaunte=BigDecimal.ZERO;
	intialiserTableau();
	listCoutMP =new ArrayList<CoutMP>();
	StockMP stockMP=new StockMP();
	mapQauntiteMatierePremier = new HashMap<>();
	StockMP stockMPMagasinProd=new StockMP();
	Magasin  magasinElnasTeaPacking=null;
	StockMP stockMPQauantiteManquante=new StockMP();
	CoutMP coutMP=new CoutMP();
	Magasin magasinStockage =magasinMap.get(comboMagasin.getSelectedItem());
	Magasin magasinProd =magasinPordMap.get(comboMagasinProd.getSelectedItem());
	intialiserTableau();
	int j=0;
	for(int i=0;i<lisDetailEstimation.size();i++){
		
		if(checkBoxSrvice.isSelected()==true)
		{
			
			 coutMP= new CoutMP();
			 stockMPQauantiteManquante=new StockMP();
				DetailEstimation detailEstimation=lisDetailEstimation.get(i);
				
if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
{

	quantiteTotal=detailEstimation.getQuantite().multiply(new BigDecimal(quantite.getText()));
	
	stockMP=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinStockage.getId());
	stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId());
	quantiteExistante=stockMPMagasinProd.getStock();
	prix_unitaire=stockMP.getPrixUnitaire();
	quantiteACharge=quantiteTotal.subtract(quantiteExistante);
	
	if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
		quantiteACharge=BigDecimal.ZERO;

if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) 
	|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) 
	|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE)
	|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){

	quantiteACharge=(BigDecimal)(quantiteACharge);
	
} 
if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
	
	
	if(stockMP!=null){
		coutMP.setMatierePremier(detailEstimation.getMatierePremier());
		coutMP.setPrixUnitaire(prix_unitaire);
		coutMP.setQuantite(quantiteTotal);
		coutMP.setQuantExistante(quantiteExistante);
		coutMP.setQuantEstime(quantiteACharge);
		coutMP.setProdcutionCM(production);
		listCoutMP.add(coutMP);
	//	listCoutMP.add(coutMP);
		/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
		modeleMP.addRow(ligne);*/
	}

}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH)){
	MatierePremier matierePremiere=mapMatierePremier.get(comboScotch.getSelectedItem());
	if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())){
	
		
		if(stockMP!=null){
			
			if(detailEstimation.getMatierePremier().getCode().equals(MATIERE_PREMIERE_SCOTCH_1000M))
				if(quantiteACharge.remainder(new BigDecimal(2)).compareTo(new BigDecimal(0))!=0)
					quantiteACharge=quantiteACharge.add(new BigDecimal(1)) ;
			
			coutMP.setMatierePremier(detailEstimation.getMatierePremier());
			coutMP.setPrixUnitaire(prix_unitaire);
			coutMP.setQuantite(quantiteTotal);
			coutMP.setQuantExistante(quantiteExistante);
			coutMP.setQuantEstime(quantiteACharge);
			coutMP.setProdcutionCM(production);
			listCoutMP.add(coutMP);
		//	listCoutMP.add(coutMP);
		/*	Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
			modeleMP.addRow(ligne);*/
		}
		
	
		
	}
}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
	
if(!chckbxArticleMixte.isSelected()){
	MatierePremier matierePremiere=mapMatierePremier.get(comboBoxThe.getSelectedItem());
	if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())&& detailEstimation.getPriorite()==PRIORITE_ESTIMATION_1){
		
		
		if(stockMP!=null){
			
			coutMP.setMatierePremier(detailEstimation.getMatierePremier());
			coutMP.setPrixUnitaire(prix_unitaire);
			coutMP.setQuantite(quantiteTotal);
			coutMP.setQuantExistante(quantiteExistante);
			coutMP.setQuantEstime(quantiteACharge);
			coutMP.setProdcutionCM(production);
			listCoutMP.add(coutMP);
		//	listCoutMP.add(coutMP);
			/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
			modeleMP.addRow(ligne);*/
		}
	}
}else if(chckbxArticleMixte.isSelected()){
	
	if(detailEstimation.getPriorite()==2){
		
		if(stockMP!=null){
			
			coutMP.setMatierePremier(detailEstimation.getMatierePremier());
			coutMP.setPrixUnitaire(prix_unitaire);
			coutMP.setQuantite(quantiteTotal);
			coutMP.setQuantExistante(quantiteExistante);
			coutMP.setQuantEstime(quantiteACharge);
			coutMP.setProdcutionCM(production);
			listCoutMP.add(coutMP);
		//	listCoutMP.add(coutMP);
			/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
			modeleMP.addRow(ligne);*/
		}
		
	}
	
}
}


	
}else
{
					/*
					 * if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().
					 * getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {
					 */
		stockMP=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinStockage.getId());
		if(stockMP!=null)
		{
			if(stockMP.getStock().compareTo(BigDecimal.ZERO)>0)
			{
					//magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
				
				quantiteTotal=detailEstimation.getQuantite().multiply(new BigDecimal(quantite.getText()));
				
				//stockMP=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinElnasTeaPacking.getId());
				stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId());
				quantiteExistante=stockMPMagasinProd.getStock();
				prix_unitaire=stockMP.getPrixUnitaire();
				quantiteACharge=quantiteTotal.subtract(quantiteExistante);
				
				if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
					quantiteACharge=BigDecimal.ZERO;

			if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) 
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) 
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE)
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){

				quantiteACharge=(BigDecimal)(quantiteACharge);
				
			} 
			if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
				
				
				if(stockMP!=null){
					coutMP.setMatierePremier(detailEstimation.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteACharge);
					coutMP.setMagasin(magasinStockage);
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
				//	listCoutMP.add(coutMP);
					/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);*/
				}

			}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH)){
				MatierePremier matierePremiere=mapMatierePremier.get(comboScotch.getSelectedItem());
				if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())){
				
					
					if(stockMP!=null){
						
						if(detailEstimation.getMatierePremier().getCode().equals(MATIERE_PREMIERE_SCOTCH_1000M))
							if(quantiteACharge.remainder(new BigDecimal(2)).compareTo(new BigDecimal(0))!=0)
								quantiteACharge=quantiteACharge.add(new BigDecimal(1)) ;
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setMagasin(magasinStockage);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
					/*	Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
					
					
				}
			}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
				
			if(!chckbxArticleMixte.isSelected()){
				MatierePremier matierePremiere=mapMatierePremier.get(comboBoxThe.getSelectedItem());
				if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())&& detailEstimation.getPriorite()==PRIORITE_ESTIMATION_1){
					
					
					if(stockMP!=null){
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setMagasin(magasinStockage);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
						/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
				}
			}else if(chckbxArticleMixte.isSelected()){
				
				if(detailEstimation.getPriorite()==2){
					
					if(stockMP!=null){
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setMagasin(magasinStockage);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
						/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
					
				}
				
			}
			}

				
				
				
			}else
			{
				
				magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
				
				quantiteTotal=detailEstimation.getQuantite().multiply(new BigDecimal(quantite.getText()));
				
				stockMP=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinElnasTeaPacking.getId());
				stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId());
				quantiteExistante=stockMPMagasinProd.getStock();
				prix_unitaire=stockMP.getPrixUnitaire();
				quantiteACharge=quantiteTotal.subtract(quantiteExistante);
				
				if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
					quantiteACharge=BigDecimal.ZERO;

			if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) 
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) 
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE)
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){

				quantiteACharge=(BigDecimal)(quantiteACharge);
				
			} 
			if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
				
				
				if(stockMP!=null){
					coutMP.setMatierePremier(detailEstimation.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteACharge);
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
				//	listCoutMP.add(coutMP);
					/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);*/
				}

			}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH)){
				MatierePremier matierePremiere=mapMatierePremier.get(comboScotch.getSelectedItem());
				if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())){
				
					
					if(stockMP!=null){
						
						if(detailEstimation.getMatierePremier().getCode().equals(MATIERE_PREMIERE_SCOTCH_1000M))
							if(quantiteACharge.remainder(new BigDecimal(2)).compareTo(new BigDecimal(0))!=0)
								quantiteACharge=quantiteACharge.add(new BigDecimal(1)) ;
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
					/*	Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
					
					
				}
			}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
				
			if(!chckbxArticleMixte.isSelected()){
				MatierePremier matierePremiere=mapMatierePremier.get(comboBoxThe.getSelectedItem());
				if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())&& detailEstimation.getPriorite()==PRIORITE_ESTIMATION_1){
					
					
					if(stockMP!=null){
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
						/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
				}
			}else if(chckbxArticleMixte.isSelected()){
				
				if(detailEstimation.getPriorite()==2){
					
					if(stockMP!=null){
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
						/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
					
				}
				
			}
			}

			
				
			
				
				
				
			}
			
			
		}else
		{
			
			magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
			
			quantiteTotal=detailEstimation.getQuantite().multiply(new BigDecimal(quantite.getText()));
			
			stockMP=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinElnasTeaPacking.getId());
			stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId());
			quantiteExistante=stockMPMagasinProd.getStock();
			prix_unitaire=stockMP.getPrixUnitaire();
			quantiteACharge=quantiteTotal.subtract(quantiteExistante);
			
			if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
				quantiteACharge=BigDecimal.ZERO;

		if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) 
			|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) 
			|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE)
			|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){

			quantiteACharge=(BigDecimal)(quantiteACharge);
			
		} 
		if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
			
			
			if(stockMP!=null){
				coutMP.setMatierePremier(detailEstimation.getMatierePremier());
				coutMP.setPrixUnitaire(prix_unitaire);
				coutMP.setQuantite(quantiteTotal);
				coutMP.setQuantExistante(quantiteExistante);
				coutMP.setQuantEstime(quantiteACharge);
				coutMP.setProdcutionCM(production);
				listCoutMP.add(coutMP);
			//	listCoutMP.add(coutMP);
				/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
				modeleMP.addRow(ligne);*/
			}

		}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH)){
			MatierePremier matierePremiere=mapMatierePremier.get(comboScotch.getSelectedItem());
			if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())){
			
				
				if(stockMP!=null){
					
					if(detailEstimation.getMatierePremier().getCode().equals(MATIERE_PREMIERE_SCOTCH_1000M))
						if(quantiteACharge.remainder(new BigDecimal(2)).compareTo(new BigDecimal(0))!=0)
							quantiteACharge=quantiteACharge.add(new BigDecimal(1)) ;
					
					coutMP.setMatierePremier(detailEstimation.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteACharge);
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
				//	listCoutMP.add(coutMP);
				/*	Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);*/
				}
				
				
			}
		}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
			
		if(!chckbxArticleMixte.isSelected()){
			MatierePremier matierePremiere=mapMatierePremier.get(comboBoxThe.getSelectedItem());
			if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())&& detailEstimation.getPriorite()==PRIORITE_ESTIMATION_1){
				
				
				if(stockMP!=null){
					
					coutMP.setMatierePremier(detailEstimation.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteACharge);
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
				//	listCoutMP.add(coutMP);
					/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);*/
				}
			}
		}else if(chckbxArticleMixte.isSelected()){
			
			if(detailEstimation.getPriorite()==2){
				
				if(stockMP!=null){
					
					coutMP.setMatierePremier(detailEstimation.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteACharge);
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
				//	listCoutMP.add(coutMP);
					/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);*/
				}
				
			}
			
		}
		}

		
			
		}
		
/*	}else
	{
		
		magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
		
		quantiteTotal=detailEstimation.getQuantite().multiply(new BigDecimal(quantite.getText()));
		
		stockMP=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinElnasTeaPacking.getId());
		stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId());
		quantiteExistante=stockMPMagasinProd.getStock();
		prix_unitaire=stockMP.getPrixUnitaire();
		quantiteACharge=quantiteTotal.subtract(quantiteExistante);
		
		if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
			quantiteACharge=BigDecimal.ZERO;

	if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) 
		|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) 
		|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE)
		|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){

		quantiteACharge=(BigDecimal)(quantiteACharge);
		
	} 
	if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
		
		
		if(stockMP!=null){
			coutMP.setMatierePremier(detailEstimation.getMatierePremier());
			coutMP.setPrixUnitaire(prix_unitaire);
			coutMP.setQuantite(quantiteTotal);
			coutMP.setQuantExistante(quantiteExistante);
			coutMP.setQuantEstime(quantiteACharge);
			coutMP.setProdcutionCM(production);
			listCoutMP.add(coutMP);
		//	listCoutMP.add(coutMP);
			Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
			modeleMP.addRow(ligne);
		}

	}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH)){
		MatierePremier matierePremiere=mapMatierePremier.get(comboScotch.getSelectedItem());
		if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())){
		
			
			if(stockMP!=null){
				
				if(detailEstimation.getMatierePremier().getCode().equals(MATIERE_PREMIERE_SCOTCH_1000M))
					if(quantiteACharge.remainder(new BigDecimal(2)).compareTo(new BigDecimal(0))!=0)
						quantiteACharge=quantiteACharge.add(new BigDecimal(1)) ;
				
				coutMP.setMatierePremier(detailEstimation.getMatierePremier());
				coutMP.setPrixUnitaire(prix_unitaire);
				coutMP.setQuantite(quantiteTotal);
				coutMP.setQuantExistante(quantiteExistante);
				coutMP.setQuantEstime(quantiteACharge);
				coutMP.setProdcutionCM(production);
				listCoutMP.add(coutMP);
			//	listCoutMP.add(coutMP);
				Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
				modeleMP.addRow(ligne);
			}
			
			
		}
	}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
		
	if(!chckbxArticleMixte.isSelected()){
		MatierePremier matierePremiere=mapMatierePremier.get(comboBoxThe.getSelectedItem());
		if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())&& detailEstimation.getPriorite()==PRIORITE_ESTIMATION_1){
			
			
			if(stockMP!=null){
				
				coutMP.setMatierePremier(detailEstimation.getMatierePremier());
				coutMP.setPrixUnitaire(prix_unitaire);
				coutMP.setQuantite(quantiteTotal);
				coutMP.setQuantExistante(quantiteExistante);
				coutMP.setQuantEstime(quantiteACharge);
				coutMP.setProdcutionCM(production);
				listCoutMP.add(coutMP);
			//	listCoutMP.add(coutMP);
				Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
				modeleMP.addRow(ligne);
			}
		}
	}else if(chckbxArticleMixte.isSelected()){
		
		if(detailEstimation.getPriorite()==2){
			
			if(stockMP!=null){
				
				coutMP.setMatierePremier(detailEstimation.getMatierePremier());
				coutMP.setPrixUnitaire(prix_unitaire);
				coutMP.setQuantite(quantiteTotal);
				coutMP.setQuantExistante(quantiteExistante);
				coutMP.setQuantEstime(quantiteACharge);
				coutMP.setProdcutionCM(production);
				listCoutMP.add(coutMP);
			//	listCoutMP.add(coutMP);
				Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
				modeleMP.addRow(ligne);
			}
			
		}
		
	}
	}

	
		
		
	}*/
	
	
	 
	
	
}
				
		
			
			
			
			
			
			
		}else
		{
			
			 coutMP= new CoutMP();
			 stockMPQauantiteManquante=new StockMP();
				DetailEstimation detailEstimation=lisDetailEstimation.get(i);
				quantiteTotal=detailEstimation.getQuantite().multiply(new BigDecimal(quantite.getText()));
				
				stockMP=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinStockage.getId());
				stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(detailEstimation.getMatierePremier().getId(),magasinProd.getId());
				quantiteExistante=stockMPMagasinProd.getStock();
				prix_unitaire=stockMP.getPrixUnitaire();
				quantiteACharge=quantiteTotal.subtract(quantiteExistante);
				
				if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
					quantiteACharge=BigDecimal.ZERO;
			
			if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) 
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) 
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE)
				|| detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){
		
				quantiteACharge=(BigDecimal)(quantiteACharge);
				
			} 
			if(!detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
				
				
				if(stockMP!=null){
					coutMP.setMatierePremier(detailEstimation.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantEstime(quantiteACharge);
					coutMP.setProdcutionCM(production);
					listCoutMP.add(coutMP);
				//	listCoutMP.add(coutMP);
					/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
					modeleMP.addRow(ligne);*/
				}
			
			}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH)){
				MatierePremier matierePremiere=mapMatierePremier.get(comboScotch.getSelectedItem());
				if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())){
				
					
					if(stockMP!=null){
						
						if(detailEstimation.getMatierePremier().getCode().equals(MATIERE_PREMIERE_SCOTCH_1000M))
							if(quantiteACharge.remainder(new BigDecimal(2)).compareTo(new BigDecimal(0))!=0)
								quantiteACharge=quantiteACharge.add(new BigDecimal(1)) ;
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
					/*	Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
					
				
					
				}
			}else if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
				
			if(!chckbxArticleMixte.isSelected()){
				MatierePremier matierePremiere=mapMatierePremier.get(comboBoxThe.getSelectedItem());
				if(detailEstimation.getMatierePremier().getCode().equals(matierePremiere.getCode())&& detailEstimation.getPriorite()==PRIORITE_ESTIMATION_1){
					
					
					if(stockMP!=null){
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
						/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
				}
			}else if(chckbxArticleMixte.isSelected()){
				
				if(detailEstimation.getPriorite()==2){
					
					if(stockMP!=null){
						
						coutMP.setMatierePremier(detailEstimation.getMatierePremier());
						coutMP.setPrixUnitaire(prix_unitaire);
						coutMP.setQuantite(quantiteTotal);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteACharge);
						coutMP.setProdcutionCM(production);
						listCoutMP.add(coutMP);
					//	listCoutMP.add(coutMP);
						/*Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),""};
						modeleMP.addRow(ligne);*/
					}
					
				}
				
			}
			}
		
			
			
			
			
		}
		
		
	}
	
	
	remplirQuantiteOffreMP(listCoutMP);
	afficher_tableCouMP(listCoutMP);
	return true;
	
}

	boolean afficherTableMatierePremiereCreerOF(List<CoutMP> listCoutMP) {
		creerOF = true;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String date="01/01/"+sdf.format(dateDebutChooser.getDate())+"";
			Date dateDebut= new Date(date);
		BigDecimal StockFinale=BigDecimal.ZERO;
		if (!remplirMapQuantiteCharge()) {
			creerOF = false;
			JOptionPane.showMessageDialog(null, "Il faut remplir toutes les quantit� avant de cr�er l'OF", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		} else {

			BigDecimal prix_unitaire = BigDecimal.ZERO;
			BigDecimal quantiteExistante = BigDecimal.ZERO;
			BigDecimal quantiteACharge = BigDecimal.ZERO;
			BigDecimal quantiteAChargeTHE = BigDecimal.ZERO;
			BigDecimal quantiteTotal = BigDecimal.ZERO;
			BigDecimal quantiteManqaunte = BigDecimal.ZERO;
			intialiserTableau();
			List listCoutMPTmp = new ArrayList<CoutMP>();
			StockMP stockMP = new StockMP();
			mapQauntiteMatierePremier = new HashMap<>();
			StockMP stockMPMagasinProd = new StockMP();

			Magasin magasinElnasTeaPacking = null;
			StockMP stockMPElnassTeaPacking = null;
			StockMP stockMPQauantiteManquante = new StockMP();
			CoutMP coutMP = new CoutMP();
			Magasin magasinStockage = magasinMap.get(comboMagasin.getSelectedItem());
			Magasin magasinProd = magasinPordMap.get(comboMagasinProd.getSelectedItem());

			if (!magasinStockage.getLibelle()
					.equals(depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING).getLibelle())) {
				magasinElnasTeaPacking = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
			}

			// intialiserTableau();
			int j = 0;
			for (int i = 0; i < listCoutMP.size(); i++) {
				StockFinale=BigDecimal.ZERO;
				coutMP = listCoutMP.get(i);
				stockMPQauantiteManquante = new StockMP();
				quantiteTotal = mapQuantiteMP.get(coutMP.getMatierePremier().getCode());

				if (checkBoxSrvice.isSelected() == true) {
					// Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU
					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {

						stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinStockage.getId());
						
						// Stock Finale de ce jour
						List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateDebutChooser.getDate(), magasinStockage, coutMP.getMatierePremier());
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
						quantiteExistante = stockMPMagasinProd.getStock();
						prix_unitaire = stockMP.getPrixUnitaire();
						quantiteACharge = quantiteTotal.subtract(quantiteExistante);

						if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
							quantiteACharge = BigDecimal.ZERO;

						if (stockMP != null && quantiteTotal.compareTo(stockMP.getStock()) > 0 ||  quantiteTotal.compareTo(StockFinale) > 0) {
							/// 2eme etape

							if(quantiteTotal.compareTo(stockMP.getStock()) > 0)
							{
								quantiteManqaunte = quantiteACharge.subtract(stockMP.getStock());
								
							}else if(quantiteTotal.compareTo(StockFinale) > 0)
							{
								
								quantiteManqaunte = quantiteACharge.subtract(StockFinale);
								
							}
							

							if (checkBoxSrvice.isSelected() == true) {
								

								
								
								stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
								stockMPQauantiteManquante.setStock(quantiteManqaunte);
								stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
								creerOF = false;
								nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
								mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
							
								
								
								/*
								 * 
								 * if (stockMP.getStock().compareTo(quantiteManqaunte) >= 0 &&
								 * StockFinale.compareTo(quantiteManqaunte)>= 0) {
								 * stockMP.setStock(stockMP.getStock().subtract(quantiteManqaunte)); ;
								 * stockMP.setStock(stockMP.getStock().add(quantiteManqaunte)); ;
								 * DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();
								 * detailTransferStockMP.setMagasinDestination(magasinStockage);
								 * detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
								 * detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
								 * detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire()); if
								 * (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								 * .equals(Constantes.CODE_BOX) ||
								 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								 * .equals(Constantes.CODE_CARTON) ||
								 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								 * .equals(Constantes.CODE_TAMPON) ||
								 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								 * .equals(Constantes.CODE_STICKERS) ||
								 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								 * .equals(Constantes.CODE_THERRES_VERRES)) { detailTransferStockMP
								 * .setQuantite(quantiteManqaunte.setScale(0, RoundingMode.CEILING)); } else {
								 * detailTransferStockMP.setQuantite(quantiteManqaunte); }
								 * 
								 * detailTransferStockMP.setTransferStockMP(transferStock);
								 * listDetailTransferStockMP.add(detailTransferStockMP); //
								 * coutMP.setQuantCharge(stockMP.getStock().add(quantiteManqaunte));
								 * coutMP.setQuantCharge(quantiteACharge); coutMP.setProdcutionCM(production);
								 * coutMP.setMagasin(magasinStockage); coutMP.setTransfer(COUT_MP_TRANSFER_OUI);
								 * listCoutMPTmp.add(coutMP); Object[] ligne = {
								 * coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
								 * coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " " +
								 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp() .getUnite(),
								 * coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
								 * coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
								 * modeleMP.addRow(ligne);
								 * 
								 * } else {
								 * stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
								 * stockMPQauantiteManquante.setStock(quantiteManqaunte);
								 * stockMPQauantiteManquante.setMagasin(stockMP.getMagasin()); creerOF = false;
								 * nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte
								 * + "\n"; mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
								 * 
								 * }
								 * 
								 */} else {
								
								
								stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
								stockMPQauantiteManquante.setStock(quantiteManqaunte);
								stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
								creerOF = false;
								nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
								mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
							}

							j++;
						} else if (stockMP != null && stockMP.getStock().compareTo(quantiteTotal) >= 0 && StockFinale.compareTo(quantiteTotal) >= 0) {

							coutMP.setQuantCharge(quantiteTotal);
							coutMP.setProdcutionCM(production);
							coutMP.setMagasin(magasinStockage);
							coutMP.setTransfer(COUT_MP_TRANSFER_NON);
							listCoutMPTmp.add(coutMP);
							Object[] ligne = { coutMP.getMatierePremier().getCode(),
									coutMP.getMatierePremier().getNom(),
									coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " "
											+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
													.getUnite(),
									coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
									coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
							modeleMP.addRow(ligne);
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
						List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateDebutChooser.getDate(), magasinStockage, coutMP.getMatierePremier());
						if(!listestockfinale.isEmpty())
						{
							for(int t=0;t<listestockfinale.size();t++)
				 			{
				 				
				 			 Object[] object=listestockfinale.get(t);
							
				 			StockFinale= (BigDecimal)object[1];
							
				 			}
						}

						if (stockMP != null) {

							stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
									magasinProd.getId());
							quantiteExistante = stockMPMagasinProd.getStock();
							prix_unitaire = stockMP.getPrixUnitaire();
							quantiteACharge = quantiteTotal.subtract(quantiteExistante);

							if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteACharge = BigDecimal.ZERO;
							if (stockMP.getStock().compareTo(quantiteTotal) >= 0 && StockFinale.compareTo(quantiteTotal) >= 0) {

								coutMP.setQuantCharge(quantiteTotal);
								coutMP.setProdcutionCM(production);
								coutMP.setMagasin(magasinStockage);
								coutMP.setTransfer(COUT_MP_TRANSFER_NON);
								listCoutMPTmp.add(coutMP);
								Object[] ligne = { coutMP.getMatierePremier().getCode(),
										coutMP.getMatierePremier().getNom(),
										coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " "
												+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
														.getUnite(),
										coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
										coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
								modeleMP.addRow(ligne);
							} else {

								if (magasinElnasTeaPacking != null) {
									stockMPElnassTeaPacking = stockMPDAO.findStockByMagasinMP(
											coutMP.getMatierePremier().getId(), magasinElnasTeaPacking.getId());
								}

								stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
										magasinElnasTeaPacking.getId());
								// Stock Finale de ce jour
								StockFinale=BigDecimal.ZERO;
								List<Object[]> listestockfinaleMagasinElnasTeaPacking=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateDebutChooser.getDate(), magasinElnasTeaPacking, coutMP.getMatierePremier());
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
								quantiteExistante = stockMPMagasinProd.getStock();
								prix_unitaire = stockMP.getPrixUnitaire();
								quantiteACharge = quantiteTotal.subtract(quantiteExistante);

								if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
									quantiteACharge = BigDecimal.ZERO;

								if (stockMP != null && quantiteTotal.compareTo(stockMP.getStock()) > 0 || quantiteTotal.compareTo(StockFinale) > 0 ) {
									/// 2eme etape

									if(quantiteTotal.compareTo(stockMP.getStock()) > 0)
									{
										quantiteManqaunte = quantiteACharge.subtract(stockMP.getStock());
									}else if(quantiteTotal.compareTo(StockFinale) > 0)
									{
										quantiteManqaunte = quantiteACharge.subtract(StockFinale);
									}
									

									if (checkBoxSrvice.isSelected() == true) {
										

										stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
										stockMPQauantiteManquante.setStock(quantiteManqaunte);
										stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
										creerOF = false;
										nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte
												+ "\n";
										mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
									
										
										
										
										
										/*
										 * 
										 * if (stockMPElnassTeaPacking != null) { if
										 * (stockMPElnassTeaPacking.getStock().compareTo(quantiteManqaunte) >= 0 &&
										 * StockFinale.compareTo(quantiteManqaunte) >= 0) {
										 * stockMPElnassTeaPacking.setStock(
										 * stockMPElnassTeaPacking.getStock().subtract(quantiteManqaunte)); ;
										 * stockMP.setStock(stockMP.getStock().add(quantiteManqaunte)); ;
										 * DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();
										 * detailTransferStockMP.setMagasinDestination(magasinStockage);
										 * detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
										 * detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
										 * detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire()); if
										 * (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
										 * .getCode().equals(Constantes.CODE_BOX) ||
										 * coutMP.getMatierePremier().getCategorieMp() .getSubCategorieMp().getCode()
										 * .equals(Constantes.CODE_CARTON) ||
										 * coutMP.getMatierePremier().getCategorieMp() .getSubCategorieMp().getCode()
										 * .equals(Constantes.CODE_TAMPON) ||
										 * coutMP.getMatierePremier().getCategorieMp() .getSubCategorieMp().getCode()
										 * .equals(Constantes.CODE_STICKERS) ||
										 * coutMP.getMatierePremier().getCategorieMp() .getSubCategorieMp().getCode()
										 * .equals(Constantes.CODE_THERRES_VERRES)) { detailTransferStockMP.setQuantite(
										 * quantiteManqaunte.setScale(0, RoundingMode.CEILING)); } else {
										 * detailTransferStockMP.setQuantite(quantiteManqaunte); }
										 * 
										 * detailTransferStockMP.setTransferStockMP(transferStock);
										 * listDetailTransferStockMP.add(detailTransferStockMP); //
										 * coutMP.setQuantCharge(stockMP.getStock().add(quantiteManqaunte));
										 * coutMP.setQuantCharge(quantiteACharge); coutMP.setProdcutionCM(production);
										 * coutMP.setTransfer(COUT_MP_TRANSFER_OUI); listCoutMPTmp.add(coutMP); Object[]
										 * ligne = { coutMP.getMatierePremier().getCode(),
										 * coutMP.getMatierePremier().getNom(), coutMP.getQuantite().setScale(6,
										 * RoundingMode.HALF_UP) + " " + coutMP.getMatierePremier().getCategorieMp()
										 * .getSubCategorieMp().getUnite(), coutMP.getQuantExistante().setScale(6,
										 * RoundingMode.HALF_UP), coutMP.getQuantEstime(), quantiteTotal.setScale(6,
										 * RoundingMode.HALF_UP) }; modeleMP.addRow(ligne);
										 * 
										 * } else {
										 * stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
										 * stockMPQauantiteManquante.setStock(quantiteManqaunte);
										 * stockMPQauantiteManquante .setMagasin(stockMPElnassTeaPacking.getMagasin());
										 * creerOF = false; nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" +
										 * quantiteManqaunte + "\n"; mapQauntiteMatierePremier.put(j,
										 * stockMPQauantiteManquante);
										 * 
										 * } }
										 * 
										 */} else {
										stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
										stockMPQauantiteManquante.setStock(quantiteManqaunte);
										stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
										creerOF = false;
										nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte
												+ "\n";
										mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
									}

									j++;
								} else if (stockMP != null && stockMP.getStock().compareTo(quantiteTotal) >= 0  && StockFinale.compareTo(quantiteTotal) >= 0 ) {

									coutMP.setQuantCharge(quantiteTotal);
									coutMP.setProdcutionCM(production);
									coutMP.setMagasin(magasinElnasTeaPacking);
									coutMP.setTransfer(COUT_MP_TRANSFER_NON);
									listCoutMPTmp.add(coutMP);
									Object[] ligne = { coutMP.getMatierePremier().getCode(),
											coutMP.getMatierePremier().getNom(),
											coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " "
													+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
															.getUnite(),
											coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
											coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
									modeleMP.addRow(ligne);
								}

								if (quantiteTotal.compareTo(BigDecimal.ZERO) > 0) {
									mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
											coutMP.getMatierePremier());
									mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteTotal);

								}

							}

						} else {

							if (magasinElnasTeaPacking != null) {
								stockMPElnassTeaPacking = stockMPDAO.findStockByMagasinMP(
										coutMP.getMatierePremier().getId(), magasinElnasTeaPacking.getId());
							}
							
							
							StockFinale=BigDecimal.ZERO;
							// Stock Finale de ce jour
							List<Object[]> listestockfinaleMagasinElnassTeaPacking=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateDebutChooser.getDate(), magasinElnasTeaPacking, coutMP.getMatierePremier());
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
							quantiteExistante = stockMPMagasinProd.getStock();
							prix_unitaire = stockMP.getPrixUnitaire();
							quantiteACharge = quantiteTotal.subtract(quantiteExistante);

							if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteACharge = BigDecimal.ZERO;

							if (stockMP != null && quantiteTotal.compareTo(stockMP.getStock()) > 0  || quantiteTotal.compareTo(StockFinale) > 0) {
								/// 2eme etape

								if(quantiteTotal.compareTo(stockMP.getStock()) > 0)
								{
									quantiteManqaunte = quantiteACharge.subtract(stockMP.getStock());
								}else if(quantiteTotal.compareTo(StockFinale) > 0)
								{
									quantiteManqaunte = quantiteACharge.subtract(StockFinale);
								}

								if (checkBoxSrvice.isSelected() == true) {
									
									

									stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
									stockMPQauantiteManquante.setStock(quantiteManqaunte);
									stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
									creerOF = false;
									nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
									mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
								
									
									
									/*
									 * 
									 * if (stockMPElnassTeaPacking != null) { if
									 * (stockMPElnassTeaPacking.getStock().compareTo(quantiteManqaunte) >= 0 &&
									 * StockFinale.compareTo(quantiteManqaunte) >= 0) {
									 * stockMPElnassTeaPacking.setStock(
									 * stockMPElnassTeaPacking.getStock().subtract(quantiteManqaunte)); ;
									 * stockMP.setStock(stockMP.getStock().add(quantiteManqaunte)); ;
									 * DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();
									 * detailTransferStockMP.setMagasinDestination(magasinStockage);
									 * detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
									 * detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
									 * detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire()); if
									 * (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
									 * .getCode().equals(Constantes.CODE_BOX) ||
									 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
									 * .getCode().equals(Constantes.CODE_CARTON) ||
									 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
									 * .getCode().equals(Constantes.CODE_TAMPON) ||
									 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
									 * .getCode().equals(Constantes.CODE_STICKERS) ||
									 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
									 * .getCode().equals(Constantes.CODE_THERRES_VERRES)) {
									 * detailTransferStockMP.setQuantite( quantiteManqaunte.setScale(0,
									 * RoundingMode.CEILING)); } else {
									 * detailTransferStockMP.setQuantite(quantiteManqaunte); }
									 * 
									 * detailTransferStockMP.setTransferStockMP(transferStock);
									 * listDetailTransferStockMP.add(detailTransferStockMP); //
									 * coutMP.setQuantCharge(stockMP.getStock().add(quantiteManqaunte));
									 * coutMP.setQuantCharge(quantiteACharge); coutMP.setProdcutionCM(production);
									 * coutMP.setTransfer(COUT_MP_TRANSFER_OUI); listCoutMPTmp.add(coutMP); Object[]
									 * ligne = { coutMP.getMatierePremier().getCode(),
									 * coutMP.getMatierePremier().getNom(), coutMP.getQuantite().setScale(6,
									 * RoundingMode.HALF_UP) + " " + coutMP.getMatierePremier().getCategorieMp()
									 * .getSubCategorieMp().getUnite(), coutMP.getQuantExistante().setScale(6,
									 * RoundingMode.HALF_UP), coutMP.getQuantEstime(), quantiteTotal.setScale(6,
									 * RoundingMode.HALF_UP) }; modeleMP.addRow(ligne);
									 * 
									 * } else {
									 * stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
									 * stockMPQauantiteManquante.setStock(quantiteManqaunte);
									 * stockMPQauantiteManquante.setMagasin(stockMPElnassTeaPacking.getMagasin());
									 * creerOF = false; nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" +
									 * quantiteManqaunte + "\n"; mapQauntiteMatierePremier.put(j,
									 * stockMPQauantiteManquante);
									 * 
									 * } }
									 * 
									 */} else {
									stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
									stockMPQauantiteManquante.setStock(quantiteManqaunte);
									stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
									creerOF = false;
									nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
									mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
								}

								j++;
							} else if (stockMP != null && stockMP.getStock().compareTo(quantiteTotal) >= 0  && StockFinale.compareTo(quantiteTotal) >= 0) {

								coutMP.setQuantCharge(quantiteTotal);
								coutMP.setProdcutionCM(production);
								coutMP.setMagasin(magasinElnasTeaPacking);
								coutMP.setTransfer(COUT_MP_TRANSFER_NON);
								listCoutMPTmp.add(coutMP);
								Object[] ligne = { coutMP.getMatierePremier().getCode(),
										coutMP.getMatierePremier().getNom(),
										coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " "
												+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
														.getUnite(),
										coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
										coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
								modeleMP.addRow(ligne);
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

						stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinStockage.getId());
						
						StockFinale=BigDecimal.ZERO;
						// Stock Finale de ce jour
						List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateDebutChooser.getDate(), magasinStockage, coutMP.getMatierePremier());
						if(!listestockfinale.isEmpty())
						{
							for(int t=0;t<listestockfinale.size();t++)
				 			{
				 				
				 			 Object[] object=listestockfinale.get(t);
							
				 			StockFinale= (BigDecimal)object[1];
							
				 			}
						}

						

						if (stockMP != null) {

							stockMPMagasinProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
									magasinProd.getId());
							quantiteExistante = stockMPMagasinProd.getStock();
							prix_unitaire = stockMP.getPrixUnitaire();
							quantiteACharge = quantiteTotal.subtract(quantiteExistante);

							if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteACharge = BigDecimal.ZERO;
							if (stockMP.getStock().compareTo(quantiteTotal) >= 0 && StockFinale.compareTo(quantiteTotal) >= 0) {

								coutMP.setQuantCharge(quantiteTotal);
								coutMP.setProdcutionCM(production);
								coutMP.setMagasin(magasinStockage);
								coutMP.setTransfer(COUT_MP_TRANSFER_NON);
								listCoutMPTmp.add(coutMP);
								Object[] ligne = { coutMP.getMatierePremier().getCode(),
										coutMP.getMatierePremier().getNom(),
										coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " "
												+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
														.getUnite(),
										coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
										coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
								modeleMP.addRow(ligne);
							} else {

								if (magasinElnasTeaPacking != null) {
									stockMPElnassTeaPacking = stockMPDAO.findStockByMagasinMP(
											coutMP.getMatierePremier().getId(), magasinElnasTeaPacking.getId());
								}

								stockMP = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
										magasinElnasTeaPacking.getId());
								
								StockFinale=BigDecimal.ZERO;
								// Stock Finale de ce jour
								List<Object[]> listestockfinaleElnasTeaPacking=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateDebutChooser.getDate(), magasinElnasTeaPacking, coutMP.getMatierePremier());
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
								quantiteExistante = stockMPMagasinProd.getStock();
								prix_unitaire = stockMP.getPrixUnitaire();
								quantiteACharge = quantiteTotal.subtract(quantiteExistante);

								if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
									quantiteACharge = BigDecimal.ZERO;

								if (stockMP != null && quantiteTotal.compareTo(stockMP.getStock()) > 0 ||  quantiteTotal.compareTo(StockFinale) > 0) {
									/// 2eme etape

									if(quantiteTotal.compareTo(stockMP.getStock()) > 0)
									{
										quantiteManqaunte = quantiteACharge.subtract(stockMP.getStock());
									}else if(quantiteTotal.compareTo(StockFinale) > 0)
									{
										quantiteManqaunte = quantiteACharge.subtract(StockFinale);
									}

									if (checkBoxSrvice.isSelected() == true) {

										/*
										 * if (stockMPElnassTeaPacking != null) { if
										 * (stockMPElnassTeaPacking.getStock().compareTo(quantiteManqaunte) >= 0 &&
										 * StockFinale.compareTo(quantiteManqaunte) >= 0) {
										 */
											
											
											
												
												
												/*
												 * stockMPElnassTeaPacking.setStock(
												 * stockMPElnassTeaPacking.getStock().subtract(quantiteManqaunte)); ;
												 * stockMP.setStock(stockMP.getStock().add(quantiteManqaunte)); ;
												 * DetailTransferStockMP detailTransferStockMP = new
												 * DetailTransferStockMP();
												 * detailTransferStockMP.setMagasinDestination(magasinStockage);
												 * detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
												 * detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
												 * detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire()); if
												 * (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
												 * .getCode().equals(Constantes.CODE_BOX) ||
												 * coutMP.getMatierePremier().getCategorieMp()
												 * .getSubCategorieMp().getCode() .equals(Constantes.CODE_CARTON) ||
												 * coutMP.getMatierePremier().getCategorieMp()
												 * .getSubCategorieMp().getCode() .equals(Constantes.CODE_TAMPON) ||
												 * coutMP.getMatierePremier().getCategorieMp()
												 * .getSubCategorieMp().getCode() .equals(Constantes.CODE_STICKERS) ||
												 * coutMP.getMatierePremier().getCategorieMp()
												 * .getSubCategorieMp().getCode()
												 * .equals(Constantes.CODE_THERRES_VERRES)) {
												 * detailTransferStockMP.setQuantite( quantiteManqaunte.setScale(0,
												 * RoundingMode.CEILING)); } else {
												 * detailTransferStockMP.setQuantite(quantiteManqaunte); }
												 * 
												 * detailTransferStockMP.setTransferStockMP(transferStock);
												 * listDetailTransferStockMP.add(detailTransferStockMP); //
												 * coutMP.setQuantCharge(stockMP.getStock().add(quantiteManqaunte));
												 * coutMP.setQuantCharge(quantiteACharge);
												 * coutMP.setProdcutionCM(production);
												 * coutMP.setTransfer(COUT_MP_TRANSFER_OUI); listCoutMPTmp.add(coutMP);
												 * Object[] ligne = { coutMP.getMatierePremier().getCode(),
												 * coutMP.getMatierePremier().getNom(), coutMP.getQuantite().setScale(6,
												 * RoundingMode.HALF_UP) + " " +
												 * coutMP.getMatierePremier().getCategorieMp()
												 * .getSubCategorieMp().getUnite(),
												 * coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
												 * coutMP.getQuantEstime(), quantiteTotal.setScale(6,
												 * RoundingMode.HALF_UP) }; modeleMP.addRow(ligne);
												 * 
												 } else {*/
												stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
												stockMPQauantiteManquante.setStock(quantiteManqaunte);
												stockMPQauantiteManquante
														.setMagasin(stockMPElnassTeaPacking.getMagasin());
												creerOF = false;
												nomMP += "-" + coutMP.getMatierePremier().getNom() + ":"
														+ quantiteManqaunte + "\n";
												mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);

										/*	}
										}*/

									} else {
										stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
										stockMPQauantiteManquante.setStock(quantiteManqaunte);
										stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
										creerOF = false;
										nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte
												+ "\n";
										mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
									}

									j++;
								} else if (stockMP != null && stockMP.getStock().compareTo(quantiteTotal) >= 0 && StockFinale.compareTo(quantiteTotal) >= 0 ) {

									coutMP.setQuantCharge(quantiteTotal);
									coutMP.setProdcutionCM(production);
									coutMP.setMagasin(magasinElnasTeaPacking);
									coutMP.setTransfer(COUT_MP_TRANSFER_NON);
									listCoutMPTmp.add(coutMP);
									Object[] ligne = { coutMP.getMatierePremier().getCode(),
											coutMP.getMatierePremier().getNom(),
											coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " "
													+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
															.getUnite(),
											coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
											coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
									modeleMP.addRow(ligne);
								}

								if (quantiteTotal.compareTo(BigDecimal.ZERO) > 0) {
									mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
											coutMP.getMatierePremier());
									mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteTotal);

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
							List<Object[]> listestockfinaleElnasTeaPacking=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateDebutChooser.getDate(), magasinElnasTeaPacking, coutMP.getMatierePremier());
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
							quantiteExistante = stockMPMagasinProd.getStock();
							prix_unitaire = stockMP.getPrixUnitaire();
							quantiteACharge = quantiteTotal.subtract(quantiteExistante);

							if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
								quantiteACharge = BigDecimal.ZERO;

							if (stockMP != null && quantiteTotal.compareTo(stockMP.getStock()) > 0  || quantiteTotal.compareTo(StockFinale) > 0 ) {
								/// 2eme etape

								if(quantiteTotal.compareTo(stockMP.getStock()) > 0)
								{
									quantiteManqaunte = quantiteACharge.subtract(stockMP.getStock());
								}else if(quantiteTotal.compareTo(StockFinale) > 0)
								{
									quantiteManqaunte = quantiteACharge.subtract(StockFinale);
								}

								if (checkBoxSrvice.isSelected() == true) {

									/*if (stockMPElnassTeaPacking != null) {
										if (stockMPElnassTeaPacking.getStock().compareTo(quantiteManqaunte) >= 0
												&& StockFinale.compareTo(quantiteManqaunte) >= 0) {*/

											
											/*
											 * stockMPElnassTeaPacking.setStock(
											 * stockMPElnassTeaPacking.getStock().subtract(quantiteManqaunte)); ;
											 * stockMP.setStock(stockMP.getStock().add(quantiteManqaunte)); ;
											 * DetailTransferStockMP detailTransferStockMP = new
											 * DetailTransferStockMP();
											 * detailTransferStockMP.setMagasinDestination(magasinStockage);
											 * detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
											 * detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
											 * detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire()); if
											 * (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
											 * .getCode().equals(Constantes.CODE_BOX) ||
											 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
											 * .getCode().equals(Constantes.CODE_CARTON) ||
											 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
											 * .getCode().equals(Constantes.CODE_TAMPON) ||
											 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
											 * .getCode().equals(Constantes.CODE_STICKERS) ||
											 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
											 * .getCode().equals(Constantes.CODE_THERRES_VERRES)) {
											 * detailTransferStockMP.setQuantite( quantiteManqaunte.setScale(0,
											 * RoundingMode.CEILING)); } else {
											 * detailTransferStockMP.setQuantite(quantiteManqaunte); }
											 * 
											 * detailTransferStockMP.setTransferStockMP(transferStock);
											 * listDetailTransferStockMP.add(detailTransferStockMP); //
											 * coutMP.setQuantCharge(stockMP.getStock().add(quantiteManqaunte));
											 * coutMP.setQuantCharge(quantiteACharge);
											 * coutMP.setProdcutionCM(production);
											 * coutMP.setTransfer(COUT_MP_TRANSFER_OUI); listCoutMPTmp.add(coutMP);
											 * Object[] ligne = { coutMP.getMatierePremier().getCode(),
											 * coutMP.getMatierePremier().getNom(), coutMP.getQuantite().setScale(6,
											 * RoundingMode.HALF_UP) + " " + coutMP.getMatierePremier().getCategorieMp()
											 * .getSubCategorieMp().getUnite(), coutMP.getQuantExistante().setScale(6,
											 * RoundingMode.HALF_UP), coutMP.getQuantEstime(), quantiteTotal.setScale(6,
											 * RoundingMode.HALF_UP) }; modeleMP.addRow(ligne);
											 * 
											 } else {*/
											stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
											stockMPQauantiteManquante.setStock(quantiteManqaunte);
											stockMPQauantiteManquante.setMagasin(stockMPElnassTeaPacking.getMagasin());
											creerOF = false;
											nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte
													+ "\n";
											mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);

									/*	}
									}*/

								} else {
									stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
									stockMPQauantiteManquante.setStock(quantiteManqaunte);
									stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
									creerOF = false;
									nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
									mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
								}

								j++;
							} else if (stockMP != null && stockMP.getStock().compareTo(quantiteTotal) >= 0  && StockFinale.compareTo(quantiteTotal) >= 0 ) {

								coutMP.setQuantCharge(quantiteTotal);
								coutMP.setProdcutionCM(production);
								coutMP.setMagasin(magasinElnasTeaPacking);
								coutMP.setTransfer(COUT_MP_TRANSFER_NON);
								listCoutMPTmp.add(coutMP);
								Object[] ligne = { coutMP.getMatierePremier().getCode(),
										coutMP.getMatierePremier().getNom(),
										coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " "
												+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp()
														.getUnite(),
										coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
										coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
								modeleMP.addRow(ligne);
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
					List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, dateDebutChooser.getDate(), magasinStockage, coutMP.getMatierePremier());
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
					quantiteExistante = stockMPMagasinProd.getStock();
					prix_unitaire = stockMP.getPrixUnitaire();
					quantiteACharge = quantiteTotal.subtract(quantiteExistante);

					if (quantiteACharge.compareTo(BigDecimal.ZERO) < 0)
						quantiteACharge = BigDecimal.ZERO;

					if (stockMP != null && quantiteTotal.compareTo(stockMP.getStock()) > 0  || quantiteTotal.compareTo(StockFinale) > 0) {
						/// 2eme etape

						if(quantiteTotal.compareTo(stockMP.getStock()) > 0)
						{
							quantiteManqaunte = quantiteACharge.subtract(stockMP.getStock());
						}else if(quantiteTotal.compareTo(StockFinale) > 0)
						{
							quantiteManqaunte = quantiteACharge.subtract(StockFinale);
						}

						if (checkBoxSrvice.isSelected() == true) {
							

							stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
							stockMPQauantiteManquante.setStock(quantiteManqaunte);
							stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
							creerOF = false;
							nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
							mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
						
							
							
							/*
							 * 
							 * if (stockMPElnassTeaPacking != null) { if
							 * (stockMPElnassTeaPacking.getStock().compareTo(quantiteManqaunte) >= 0 &&
							 * StockFinale.compareTo(quantiteManqaunte) >= 0) { stockMPElnassTeaPacking
							 * .setStock(stockMPElnassTeaPacking.getStock().subtract(quantiteManqaunte)); ;
							 * stockMP.setStock(stockMP.getStock().add(quantiteManqaunte)); ;
							 * DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();
							 * detailTransferStockMP.setMagasinDestination(magasinStockage);
							 * detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
							 * detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
							 * detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire()); if
							 * (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							 * .equals(Constantes.CODE_BOX) ||
							 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							 * .equals(Constantes.CODE_CARTON) ||
							 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							 * .equals(Constantes.CODE_TAMPON) ||
							 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							 * .equals(Constantes.CODE_STICKERS) ||
							 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							 * .equals(Constantes.CODE_THERRES_VERRES)) { detailTransferStockMP
							 * .setQuantite(quantiteManqaunte.setScale(0, RoundingMode.CEILING)); } else {
							 * detailTransferStockMP.setQuantite(quantiteManqaunte); }
							 * 
							 * detailTransferStockMP.setTransferStockMP(transferStock);
							 * listDetailTransferStockMP.add(detailTransferStockMP); //
							 * coutMP.setQuantCharge(stockMP.getStock().add(quantiteManqaunte));
							 * coutMP.setQuantCharge(quantiteACharge); coutMP.setProdcutionCM(production);
							 * coutMP.setTransfer(COUT_MP_TRANSFER_OUI); listCoutMPTmp.add(coutMP); Object[]
							 * ligne = { coutMP.getMatierePremier().getCode(),
							 * coutMP.getMatierePremier().getNom(), coutMP.getQuantite().setScale(6,
							 * RoundingMode.HALF_UP) + " " +
							 * coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp() .getUnite(),
							 * coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP),
							 * coutMP.getQuantEstime(), quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
							 * modeleMP.addRow(ligne);
							 * 
							 * } else {
							 * stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
							 * stockMPQauantiteManquante.setStock(quantiteManqaunte);
							 * stockMPQauantiteManquante.setMagasin(stockMPElnassTeaPacking.getMagasin());
							 * creerOF = false; nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" +
							 * quantiteManqaunte + "\n"; mapQauntiteMatierePremier.put(j,
							 * stockMPQauantiteManquante);
							 * 
							 * } }
							 * 
							 */} else {
							stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
							stockMPQauantiteManquante.setStock(quantiteManqaunte);
							stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
							creerOF = false;
							nomMP += "-" + coutMP.getMatierePremier().getNom() + ":" + quantiteManqaunte + "\n";
							mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
						}

						j++;
					} else if (stockMP != null && stockMP.getStock().compareTo(quantiteTotal) >= 0 && StockFinale.compareTo(quantiteTotal) >= 0) {

						coutMP.setQuantCharge(quantiteTotal);
						coutMP.setProdcutionCM(production);
						coutMP.setTransfer(COUT_MP_TRANSFER_NON);
						listCoutMPTmp.add(coutMP);
						Object[] ligne = { coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
								coutMP.getQuantite().setScale(6, RoundingMode.HALF_UP) + " "
										+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
								coutMP.getQuantExistante().setScale(6, RoundingMode.HALF_UP), coutMP.getQuantEstime(),
								quantiteTotal.setScale(6, RoundingMode.HALF_UP) };
						modeleMP.addRow(ligne);
					}

					if (quantiteTotal.compareTo(BigDecimal.ZERO) > 0) {
						mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(),
								coutMP.getMatierePremier());
						mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteTotal);

					}

				}

			}

			/* if (listDetailTransferStockMP.size() != 0) { */
				/*
				 * String
				 * codeTransfert=Utils.genererCodeTransfer(magasinProd.getDepot().getCode(),
				 * ETAT_TRANSFER_STOCK_CHARGE);
				 * 
				 * transferStock.setDate(new Date());
				 * transferStock.setCodeTransfer(txtNumOF.getText());
				 * transferStock.setCreerPar(utilisateur);
				 * transferStock.setDateTransfer(dateDebutChooser.getDate());
				 * transferStock.setDepot(depotMap.get(comboDepot.getSelectedItem()));
				 * transferStock.setListDetailTransferMP(listDetailTransferStockMP);
				 * transferStock.setStatut(ETAT_TRANSFER_STOCK_SERVICE);
				 * transferStockMPDAO.add(transferStock);
				 */

				if (magasinElnasTeaPacking != null) {
					if(stockMPElnassTeaPacking!=null)
						{
						stockMPDAO.edit(stockMPElnassTeaPacking);
						}
					
				}

				stockMPDAO.edit(stockMP);

			/*}*/

			production.setListCoutMP(listCoutMPTmp);

		}
		return creerOF;

	}

	void compterProduction(Date dateProd, String periode) {
		int compteur = 0;
		CompteurProduction compteurProduction = compteurProductionDAO.findByDateProdPeriode(dateProd, periode);

		if (compteurProduction != null) {
			compteur = compteurProduction.getCompteur() + 1;
			compteurProduction.setCompteur(compteur);
			compteurProductionDAO.edit(compteurProduction);

		} else {
			compteurProduction = new CompteurProduction();
			compteurProduction.setDateProd(dateProd);
			compteurProduction.setPeriode(periode);
			compteurProduction.setCompteur(1);
			compteurProductionDAO.add(compteurProduction);

		}

	}

	

	boolean remplirMapQuantiteCharge() {
		boolean trouve = false;
		int i = 0;
		int j = 0;
		BigDecimal quantite = BigDecimal.ZERO;
		for (j = 0; j < table.getRowCount(); j++) {

			if (table.getValueAt(j, 5).toString() != null && !table.getValueAt(j, 5).toString().equals("")) {
				mapQuantiteMP.put(table.getValueAt(j, 0).toString(), new BigDecimal(table.getValueAt(j, 5).toString()));

				i++;
			}
		}

		if (i >= j)
			trouve = true;
		return trouve;
	}

	List<CoutMP> remplirQuantiteChargeMP(List<CoutMP> listCoutMP) {

		BigDecimal quantite = BigDecimal.ZERO;

		List<CoutMP> listCoutMPTmp = new ArrayList<CoutMP>();

		for (int i = 0; i < listCoutMP.size(); i++) {

			CoutMP coutMP = listCoutMP.get(i);

			quantite = mapQuantiteMP.get(coutMP.getMatierePremier().getCode());

			coutMP.setQuantCharge(quantite);

			listCoutMPTmp.add(coutMP);
		}
		return listCoutMPTmp;
	}

	void remplirQuantiteOffreMP(List<CoutMP> listCoutMP) {

		if (checkPromotion.isSelected() == true) {
			Promotion promotion;
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
			Magasin magasinStockage = magasinMap.get(comboMagasin.getSelectedItem());
			Magasin magasinProd = magasinPordMap.get(comboMagasinProd.getSelectedItem());

			if (!combopromotion.getSelectedItem().equals("")) {
				promotion = mapPromotion.get(combopromotion.getSelectedItem());
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
					quantiteExistante = stockMPMagasinProd.getStock();

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
						coutMP.setPrixUnitaire(stockMP.getPrixUnitaire());
						coutMP.setQuantite(quantiteCalule);
						coutMP.setQuantExistante(quantiteExistante);
						coutMP.setQuantEstime(quantiteAcharge);
						coutMP.setProdcutionCM(production);
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
							listCoutMP.set(j, coutMP);
							find = true;
						} else if (trouve == true && find == true)
							break;
					}

				}
			}
		}
	}

	private static String registerMailBody() {
		return "<HTML><b>OF N� :</b>" + production.getNumOF() + "<br><br>" + "<br><b>Les d�tails de l'OF :</b><br>"
				+ "<LI><b>Article : " + production.getArticles().getLiblle() + "</b></LI><br>"
				+ "<LI><b>Code Article : " + production.getArticles().getCodeArticle() + "</b></LI><br>"
				+ "<LI><b>Quantit�  : " + production.getQuantiteEstime() + "</b></LI><br>" + "<LI><b>Depot Stockage  : "
				+ production.getMagasinStockage().getDepot().getLibelle() + "</b></LI><br>"
				+ "<LI><b>Magasin Stockage  : " + production.getMagasinStockage().getLibelle() + "</b></LI><br>"
				+ "<LI><b>Machine  : " + production.getLigneMachine().getMachine().getNom() + "</b></LI><br>"
				+ "<LI><b>Machine  : " + production.getLigneMachine().getNom() + "</b></LI><br>"
				+ "<LI><b>Magasin Production  : " + production.getMagasinProd().getLibelle() + "</b></LI><br>"
				+ "<LI><b>Machine  : " + production.getLigneMachine().getMachine().getNom() + "</b></LI><br>"
				+ "<LI><b>Groupe Production  : " + production.getEquipe().getNomEquipe() + "</b></LI><br>"
				+ "<LI><b>Groupe G�n�rique  : " + production.getEquipeGen().getNomEquipe() + "</b></LI><br>"
				+ "<LI><b>Depot Production  : " + production.getMagasinProd().getDepot().getLibelle() + "</b></LI><br>"
				+ "<LI><b>Magasin Production  : " + production.getMagasinProd().getLibelle() + "</b></LI><br>"
				+ "<LI><b>Depot Stockage Produit Fini  : " + production.getMagasinPF().getDepot().getLibelle()
				+ "</b></LI><br>" + "<LI><b>Magasin Stockage Produit Fini  : " + production.getMagasinPF().getLibelle()
				+ "</b></LI><br>" + "Merci pour votre confiance<br>" + "Service Informatique<br>"
				+ "Syst�me Production</HTML>";
	}

	void afficher_tableMatierPremier(List<MatierePremier> listMatierePremier) {
		intialiserTableauMP();
		int i = 0;
		while (i < listMatierePremier.size()) {

			MatierePremier matierePremier = listMatierePremier.get(i);
			mapMatierePremierTmp.put(matierePremier.getCode(), matierePremier);
			Object[] ligne = { matierePremier.getCode(), matierePremier.getNom(), "" };

			modeleMPCAT.addRow(ligne);
			i++;
		}
	}

	void intialiserTableauMP() {
		modeleMPCAT = new DefaultTableModel(new Object[][] {},
				new String[] { "Code", "Nom Mati�re Premi�re", "Quantit� Estim�" }) {
			boolean[] columnEditables = new boolean[] { false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableMP.setModel(modeleMPCAT);
		tableMP.getColumnModel().getColumn(0).setPreferredWidth(160);
		tableMP.getColumnModel().getColumn(1).setPreferredWidth(160);
		tableMP.getColumnModel().getColumn(2).setPreferredWidth(160);

		// q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	}

	boolean remplirMapQuantiteEstimation() {
		boolean trouve = false;
		int i = 0;
		mapQuantiteMP = new HashMap<>();

		mapMatierePremierImp = new HashMap<>();

		for (int j = 0; j < tableMP.getRowCount(); j++) {

			if (!tableMP.getValueAt(j, 2).toString().equals("")) {

				mapQuantiteMP.put(tableMP.getValueAt(j, 0).toString(),
						new BigDecimal((String) tableMP.getValueAt(j, 2)));

				mapMatierePremierImp.put(i, mapMatierePremierTmp.get(tableMP.getValueAt(j, 0).toString()));
				i++;
				trouve = true;
			}

		}
		return trouve;
	}

	List<DetailEstimation> remplirDetailEstimation() {
		BigDecimal quantite = BigDecimal.ZERO;

		List<DetailEstimation> listDetailEstimation = new ArrayList<DetailEstimation>();

		/* ajouter le teste si cet estimation existe d�ja ; */
		// fff
		for (int i = 0; i < mapMatierePremierImp.size(); i++) {

			DetailEstimation detailEstimation = new DetailEstimation();

			MatierePremier matierePremier = mapMatierePremierImp.get(i);

			quantite = mapQuantiteMP.get(matierePremier.getCode());

			detailEstimation.setQuantite(quantite);
			detailEstimation.setPriorite(2);
			detailEstimation.setMatierePremier(matierePremier);
			detailEstimation.setArticles(article);

			listDetailEstimation.add(detailEstimation);

		}
		return listDetailEstimation;

	}

	public void show() {
		lisDetailEstimationbycategorie.clear();

		comboBoxThe.setSelectedIndex(-1);
		comboBoxThe.setEnabled(false);
		scrollPaneMP.setVisible(true);
		btnvaliderEstimation.setVisible(true);
	}

	public void hide() {

		scrollPaneMP.setVisible(false);
		btnvaliderEstimation.setVisible(false);

	}
}
