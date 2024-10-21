package ProductionCarton;

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
import java.text.DateFormat;
import java.text.ParseException;
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

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import Production.MatierePremiere;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.MachineDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.entity.Articles;
import dao.entity.ArticlesMP;
import dao.entity.CompteurProduction;
import dao.entity.CompteurResponsableProd;
import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.LigneMachine;
import dao.entity.Machine;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.Promotion;
import dao.entity.Sequenceur;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.Utilisateur;

import javax.swing.JCheckBox;


public class CreerOrdreFabricationMP extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable   table = new JXTable();
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
	private JTextField codeArticleMP;

	private Map< String, Magasin> magasinMap = new HashMap<>();
	private Map< String, Magasin> magasinPordMap = new HashMap<>();
	private Map< String, ArticlesMP> mapAricleMP = new HashMap<>();
	private Map< String, String> mapCodeArticleMP= new HashMap<>();
	private Map< String, String> mapLibelleAricleMP = new HashMap<>();
	private Map< String, Depot> depotMap = new HashMap<>();
//	private Map< String, CategorieMp> mapCategorieMP = new HashMap<>();
	private Map< Integer, StockMP> mapQauntiteMatierePremier = new HashMap<>();
	
	private Map< String, BigDecimal> mapQuantiteMP = new HashMap<>();
	
	private Map< String, MatierePremier> mapMatierePremier = new HashMap<>();
	
	
	private EquipeDAO equipeDAO;
	private ProductionMPDAO productionMPDAO;
	private StockMPDAO stockMPDAO;
	//private DetailProdGenDAO detailProdGenDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private JComboBox categorieMP;
	private JComboBox comboMagasin;
	private JComboBox comboDepot  = new JComboBox();
	private JComboBox comboDepotProd= new JComboBox();
	private JComboBox comboMagasinProd= new JComboBox();
	
	private String nomMP;
	
	private ArticlesMPDAO articleMPDAO;
	private DepotDAO depotDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private StockPFDAO stockPFDAO;
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	private List<CoutProdMP> listCoutProdMP =new ArrayList<CoutProdMP>();

	private List<ArticlesMP> listArticlesMP =new ArrayList<ArticlesMP>();
	private List<DetailEstimationMP> lisDetailEstimationMP = new ArrayList<DetailEstimationMP>() ;
	
	private ArticlesMP articlemp = new ArticlesMP();
	private DetailTransferMPDAO detailTransferStockMPDAO;
	private static ProductionMP productionMP = new ProductionMP();
	private Utilisateur utilisateur;
	
	private JTextField txtNumOF;
	private JLabel lblDescriptionOf;
	private JTextField txtDescription;
	private JLabel label;
	private JLabel lblMagasinProd;
	boolean creerOF=true;
	private  JDateChooser datfabrication;
	public CreerOrdreFabricationMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1296, 565);
        try{
        	productionMP =new ProductionMP();
        	articleMPDAO=ProdLauncher.articlempDAO;
        	
        	equipeDAO=ProdLauncher.equipeDAO;
        	productionMPDAO= new ProductionMPDAOImpl();
        	stockMPDAO=ProdLauncher.stockMPDAO;
        	depotDAO=ProdLauncher.depotDAO;
        	
        //	detailProdGenDAO=ProdLauncher.detailProdGenDAO;
        	compteurProductionDAO=ProdLauncher.compteurProductionDAO;
        	compteurResponsableProdDAO=ProdLauncher.compteurResponsableProdDAO;
        	stockPFDAO=ProdLauncher.stockPFDAO;
        	matierePremiereDAO= new MatierePremierDAOImpl();
        	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	nomMP="";
        	mapQauntiteMatierePremier = new HashMap<>();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion a la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
    	comboDepotProd.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent e) {
    			
if(!comboDepotProd.getSelectedItem().equals(""))
{
	 if(e.getStateChange() == ItemEvent.SELECTED) {
		     	 		
		     	 		 List<Magasin> listMagasin=new ArrayList<Magasin>();
 		     	  	 // comboGroupe = new JComboBox();
		     	 	 		comboMagasinProd.removeAllItems();
		     	 	 	//comboGroupe.addItem("");
 		     	  	   	Depot depot =depotMap.get(comboDepotProd.getSelectedItem());
 		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
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
		     	 	 }
		     	}	
    	});
   
    	comboDepotProd.addItem("");
  		comboMagasinProd.addItem("");
  		comboDepot.addItem("");
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
     
        codeArticleMP = new JTextField();
        util.Utils.copycoller(codeArticleMP);
        categorieMP = new JComboBox();
        categorieMP.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		
        		
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	
	     	 		codeArticleMP.setText(mapCodeArticleMP.get(categorieMP.getSelectedItem()));
	     	 	
	     	 	 }
        	}
        });
        categorieMP.addItem(""); 
        listArticlesMP=articleMPDAO.findAll();
        int i=0;
	      	while(i<listArticlesMP.size())
	      		{
	      			ArticlesMP articlemp=listArticlesMP.get(i);
	      			mapCodeArticleMP.put(articlemp.getLiblle(), articlemp.getCodeArticle());
	      			mapLibelleAricleMP.put(articlemp.getCodeArticle(),articlemp.getLiblle());
	      			mapAricleMP.put(articlemp.getLiblle(), articlemp);
	      			categorieMP.addItem(articlemp.getLiblle());
	      			i++;
	      		}
		
		
		codeArticleMP.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyReleased(KeyEvent e)
		  	{
		  		if (e.getKeyCode() == e.VK_ENTER)
		  		{
		  		categorieMP.setSelectedItem(mapLibelleAricleMP.get(codeArticleMP.getText()));
		  			
		  		}}});
		
				  		  btnImprimer = new JButton("Bon Sortie MP");
				  		  btnImprimer.setIcon(imgImprimer);
				  		  btnImprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  	if(lisDetailEstimationMP.size()<=0)
		  		     			JOptionPane.showMessageDialog(null, "Il faut calculer la matière Première avant d'imprimer!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  	else {
				  		  
					  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					  		  	String date=dateFormat.format(datfabrication.getDate());
								 
								Map parameters = new HashMap();
								parameters.put("numOF", txtNumOF.getText());
								parameters.put("magasin", comboMagasin.getSelectedItem());
								parameters.put("dateProd", date);
								//JasperUtils.imprimerBonSortieMatierePremiereMP(listCoutProdMP,parameters,productionMP.getNumOFMP());
								
				  		  	}
								
								}
				  		  });
				  		
				  		
				  		 btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnImprimer.setBounds(561, 459, 133, 23);
				  		 add(btnImprimer);
				  		 comboMagasin = new JComboBox();		
				  		 comboMagasin.addItem("");
				  		  btnCalculeMP = new JButton("Calculer Mati\u00E8re ");
				  		  btnCalculeMP.setHorizontalAlignment(SwingConstants.LEADING);
				  		  btnCalculeMP.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		nomMP="";
				  		  //	intialiserTableau();
				  		  		if(categorieMP.getSelectedItem().equals("")){
				  		  			JOptionPane.showMessageDialog(null, "Il faut choisir l'article!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
				  		  		} else if(quantite.getText().equals("")){
				  		  		JOptionPane.showMessageDialog(null, "Il faut remplir la quantité!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
				  		  		}  else if(comboMagasin.getSelectedItem().equals("")){
					  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin Source!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  			
					  		  		} else if(comboMagasinProd.getSelectedItem().equals("")){
						  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un magasin Production!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		  			
						  		  		} else{
				  		  		
				  		
				  		  	 articlemp =mapAricleMP.get(categorieMP.getSelectedItem());
				  		  	 lisDetailEstimationMP =	articlemp.getDetailEstimationMP();
				  		  	
							txtNumOF.setText(Utils.creerCodeOF(articlemp.getCodeArticle(),magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode()));
							 
						
							
							if(!afficherTableMatierePremiere(lisDetailEstimationMP)){
		  		     			
							int reponse = JOptionPane.showConfirmDialog(null, "La quantité n'est pas suffaisante de la matière première:\n"+nomMP+"\n"
									+ "Voulez vous importer le Stock Suppélementaire ?", 
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
				  		  });
				  		  btnCalculeMP.setIcon(new ImageIcon(CreerOrdreFabricationMP.class.getResource("/img/chercher.png")));
				  		  btnCalculeMP.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  btnCalculeMP.setBounds(86, 459, 133, 23);
				  		  add(btnCalculeMP);
				  		   
				  		   categorieMP.setBounds(316, 12, 191, 26);
				  		   add(categorieMP);
				  		   
				  		   JLabel lblArticle = new JLabel("Article MP :");
				  		   lblArticle.setBounds(241, 11, 102, 26);
				  		   add(lblArticle);
				  		   lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   quantite = new JTextField();
				  		 util.Utils.copycoller(quantite);
				  		   quantite.setBounds(579, 12, 157, 26);
				  		   add(quantite);
				  		   quantite.setColumns(10);
				  		   
				  		   JLabel lblQuantite = new JLabel("Quantit\u00E9 :");
				  		   lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblQuantite.setBounds(517, 12, 68, 26);
				  		   add(lblQuantite);
				  		   codeArticleMP.setBounds(51, 12, 166, 26);
				  		   add(codeArticleMP);
				  		   codeArticleMP.setColumns(10);
				  		 
				  		   JLabel lblCodeArticle = new JLabel("Code");
				  		   lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCodeArticle.setBounds(10, 11, 82, 26);
				  		   add(lblCodeArticle);
				  		    
				  		    btnAjouter = new JButton("Cr\u00E9er OF");
				  		    btnAjouter.setBounds(266, 459, 107, 23);
				  		    add(btnAjouter);
				  		    btnAjouter.setIcon(imgAjouter);
				  		    btnAjouter.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		nomMP="";
				  		     		productionMP=productionMPDAO.findByNumOFMP(txtNumOF.getText(),codeDepot);
				  		     		
				  		     		if(productionMP==null){
				  		     			productionMP=new ProductionMP();
				  		     		
				  		     		
				  		     		if(lisDetailEstimationMP.size()<=0)
				  		     			JOptionPane.showMessageDialog(null, "Il faut calculer la matière Première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     		
				  		     		else if(!afficherTableMatierePremiereCreerOF(listCoutProdMP)){
				  		     			if(nomMP.equals(""))
				  		     				JOptionPane.showMessageDialog(null, "OF ne peut pas etre crée !Il faut remplir toutes quantités !!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			else
				  		     				JOptionPane.showMessageDialog(null, "OF ne peut pas etre crée !La quantité : "+nomMP+"n'est pas suffaisante", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}else {
				  		     			
				  		     			Magasin magasinProd=magasinPordMap.get(comboMagasinProd.getSelectedItem());
				  		     			Magasin magasinStock=magasinMap.get(comboMagasin.getSelectedItem());
				  		     			
				  		     			Sequenceur sequenceur =sequenceurDAO.findByLibelle(magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode());
				  		     			productionMP.setStatut(ETAT_OF_CREER);
				  		     			datfabrication.setDateFormatString("dd/MM/yyyy");
				  		     			Date dateFabrication=datfabrication.getDate();
				  		     			productionMP.setNumOFMP(txtNumOF.getText());
				  		     			productionMP.setCodeDepot(codeDepot);		  		     	
				  		     			productionMP.setQuantiteReel(new BigDecimal(quantite.getText()));
				  		     			productionMP.setUtilisateurCreation(AuthentificationView.utilisateur);
				  		     			productionMP.setDateProduction(dateFabrication);
				  		     			productionMP.setMagasinProd(magasinProd);
				  		     			productionMP.setMagasinStockage(magasinStock);
				  		     			
				  		     			productionMP.setArticlesMP(articlemp);
				  		     		
				  		     			productionMPDAO.add(productionMP);
				  		     		
				  		     			
				  		     			Utils.incrementerValeurSequenceur (magasinPordMap.get(comboMagasinProd.getSelectedItem()).getDepot().getCode());
				  		     			
				  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication crée avec succès!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			
				  		     			
				  		     			/*PARAMETRAGE EMAIL
				  		     			try {
											EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
												"OF Crée avec succès",
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
					  		     		
					  		     		JOptionPane.showMessageDialog(null, "Cet Ordre de Fabrication est déjà crée !", "Attention", JOptionPane.INFORMATION_MESSAGE);	
					  		     	}
				  		     	}
				  		     });
				  		    btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		     btnInitialiser = new JButton("Initialiser");
				  		     btnInitialiser.setBounds(421, 459, 102, 23);
				  		     add(btnInitialiser);
				  		     btnInitialiser.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnInitialiser.setIcon(imgInit);
				  		     btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table.setSortable(false);
				  		   
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
				  		     	scrollPane.setBounds(9, 95, 1229, 176);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	 
				  		
			  		     
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 68, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 272, 1228, 163);
				  		     	add(layeredPane);
				  		     	
				  		     	txtNumOF = new JTextField();
				  		      util.Utils.copycoller(txtNumOF);
				  		     
				  		     
				  		     	txtNumOF.setBounds(78, 25, 144, 26);
				  		     	layeredPane.add(txtNumOF);
				  		     	txtNumOF.setColumns(10);
				  		     	
				  		     	JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
				  		     	lblNumOF.setBounds(10, 26, 89, 24);
				  		     	layeredPane.add(lblNumOF);
				  		  
				  		  lblDescriptionOf = new JLabel("Description OF");
				  		  lblDescriptionOf.setBounds(251, 26, 89, 24);
				  		  layeredPane.add(lblDescriptionOf);
				  		  
				  		  txtDescription = new JTextField();
				  		util.Utils.copycoller(txtDescription);
				  		  txtDescription.setColumns(10);
				  		  txtDescription.setBounds(337, 28, 320, 26);
				  		  layeredPane.add(txtDescription);
				  		  
				  		  label = new JLabel("D\u00E9pot");
				  		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  label.setBounds(258, 75, 49, 24);
				  		  layeredPane.add(label);
				  		  
				  		  comboDepotProd.setBounds(317, 76, 166, 24);
				  		  layeredPane.add(comboDepotProd);
				  		  
				  		  lblMagasinProd = new JLabel("Magasin Prod");
				  		  lblMagasinProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  lblMagasinProd.setBounds(494, 75, 89, 24);
				  		  layeredPane.add(lblMagasinProd);
				  		  
				  		
				  		  comboMagasinProd.setBounds(581, 76, 220, 24);
				  		  layeredPane.add(comboMagasinProd);
				  		  
				  		   datfabrication = new JDateChooser();
				  		  datfabrication.setLocale(Locale.FRANCE);
				  		  datfabrication.setDateFormatString("dd/MM/yyyy");
				  		  datfabrication.setBounds(100, 75, 144, 26);
				  		  layeredPane.add(datfabrication);
				  		  
				  		  JLabel lblDateFabrication = new JLabel("Date Fabrication");
				  		  lblDateFabrication.setBounds(10, 75, 100, 26);
				  		  layeredPane.add(lblDateFabrication);
				  		
				  		  comboDepot.setBounds(817, 13, 166, 24);
				  		  add(comboDepot);
				  		  if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)) {
				  			Depot	 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
					    		depotMap.put(depot.getLibelle(), depot);
		  		      			comboDepot.addItem(depot.getLibelle());
		  		      			comboDepotProd.addItem(depot.getLibelle());
		  		      			
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
				  		      					
				  		      				}
				  		      				
				  		      			}else
				  		      			{
				  		      				
				  		      			depotMap.put(depot.getLibelle(), depot);
				  		      			comboDepot.addItem(depot.getLibelle());
				  		      			comboDepotProd.addItem(depot.getLibelle());
				  		      			}
				  		      			
				  		      			
				  		      			i++;
				  		      		}
					    }
				  		  JLabel lblDpot = new JLabel("D\u00E9pot");
				  		  lblDpot.setBounds(759, 12, 48, 24);
				  		  add(lblDpot);
				  		  lblDpot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 
				  		   comboMagasin.setBounds(1047, 12, 191, 24);
				  		   add(comboMagasin);
				  		   
				  		   JLabel lblMagasin = new JLabel("Magasin");
				  		   lblMagasin.setBounds(993, 12, 77, 24);
				  		   add(lblMagasin);
				  		   lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		
				  		 
				  		 
				  		   JButton btnValiderBonSortieMP = new JButton("Valider Bon Sortie MP");
				  		   btnValiderBonSortieMP.setBounds(729, 458, 167, 24);
				  		   add(btnValiderBonSortieMP);
				  		   
				  		   
				  		   
				  		 
				  		btnValiderBonSortieMP.setIcon(imgImprimer);
				  		btnValiderBonSortieMP.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	
				  		  	if(productionMP.getId()>0){
				  		  	List<CoutProdMP> listCoutProdMP =new ArrayList<CoutProdMP>();
				  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		  	String date=dateFormat.format(productionMP.getDateProduction());
							listCoutProdMP=productionMPDAO.listeCoutProdMP(productionMP.getId());
							 
							Map parameters = new HashMap();
							parameters.put("numOF", productionMP.getNumOFMP());
							parameters.put("magasin", productionMP.getMagasinProd().getLibelle());
							parameters.put("dateProd", date);
						//	JasperUtils.imprimerValiderBonSortieMatierePremiereMP(listCoutProdMP,parameters, productionMP.getNumOFMP());
							
							//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		  	}else {
				  		  	JOptionPane.showMessageDialog(null, "Il faut Créer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
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
			  		     	 });
				  		 
	}
	
	
	void intialiser()
	{
		quantite.setText("");
		codeArticleMP.setText("");
		categorieMP.setSelectedItem("");
		
		
		
	}
void 	intialiserTableau(){
		
		modeleMP =new DefaultTableModel(
  		     	new Object[][] {
  		     	},
  		     	new String[] {
  		     			"Code","Nom Matière Première", "Quantité Calculée", "Quantité Existante", "Quantité Manquante", "Quantité A Chargée"
  		     	}
  		     ) {
  		     	boolean[] columnEditables = new boolean[] {
  		     			false,false, false,false,false,true
  		     	};
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
boolean afficherTableMatierePremiere(List<DetailEstimationMP> lisDetailEstimationMP){
	
	try {
		
		creerOF=true;
		BigDecimal prix_unitaire = BigDecimal.ZERO;
		BigDecimal quantiteExistante=BigDecimal.ZERO;
		BigDecimal quantiteACharge=BigDecimal.ZERO;
		BigDecimal quantiteAChargeTHE=BigDecimal.ZERO;
		BigDecimal quantiteTotal=BigDecimal.ZERO;
		BigDecimal quantiteManqaunte=BigDecimal.ZERO;
		intialiserTableau();
		listCoutProdMP =new ArrayList<CoutProdMP>();
		StockMP stockMP=new StockMP();
		mapQauntiteMatierePremier = new HashMap<>();
		StockMP stockMPMagasinProd=new StockMP();
		
		StockMP stockMPQauantiteManquante=new StockMP();
		CoutProdMP coutProdMP=new CoutProdMP();
		Magasin magasinStockage =magasinMap.get(comboMagasin.getSelectedItem());
		Magasin magasinProd =magasinPordMap.get(comboMagasinProd.getSelectedItem());
		intialiserTableau();
		int j=0;
		for(int i=0;i<lisDetailEstimationMP.size();i++){
			
			coutProdMP= new CoutProdMP();
			 stockMPQauantiteManquante=new StockMP();
				DetailEstimationMP detailEstimationMP=lisDetailEstimationMP.get(i);
				quantiteTotal=detailEstimationMP.getQuantite().multiply(new BigDecimal(quantite.getText()));
				
				stockMP=stockMPDAO.findStockByMagasinMP(detailEstimationMP.getMatierePremier().getId(),magasinStockage.getId());
				stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(detailEstimationMP.getMatierePremier().getId(),magasinProd.getId());
				if(stockMPMagasinProd!=null)
				{
					quantiteExistante=stockMPMagasinProd.getStock();
				}
				
				prix_unitaire=stockMP.getPrixUnitaire();
				quantiteACharge=quantiteTotal.subtract(quantiteExistante) ;
				
				if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
					quantiteACharge=BigDecimal.ZERO;
		 
				
				if(stockMP!=null &&  stockMP.getStock().compareTo(quantiteACharge)  >=0){
					
					
					coutProdMP.setMatierePremier(detailEstimationMP.getMatierePremier());
					coutProdMP.setPrixUnitaire(prix_unitaire);
					coutProdMP.setQuantite(quantiteTotal);
					coutProdMP.setQuantExistante(quantiteExistante);
					coutProdMP.setQuantEstime(quantiteACharge);
					coutProdMP.setProdcutionCM(productionMP);
					listCoutProdMP.add(coutProdMP);
				//	listCoutMP.add(coutMP);
					Object [] ligne={coutProdMP.getMatierePremier().getCode(),coutProdMP.getMatierePremier().getNom(),quantiteTotal.setScale(6)+" "+coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutProdMP.getQuantExistante().setScale(6),coutProdMP.getQuantEstime().setScale(6),""};
					modeleMP.addRow(ligne);
				}else if(stockMP!=null && quantiteACharge.compareTo(stockMP.getStock()) >0){
					quantiteManqaunte=quantiteACharge.subtract(stockMP.getStock());
					stockMPQauantiteManquante.setMatierePremier(detailEstimationMP.getMatierePremier());
					stockMPQauantiteManquante.setStock(quantiteManqaunte);
					stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
					creerOF=false;
					nomMP+="-"+detailEstimationMP.getMatierePremier().getNom()+":"+quantiteManqaunte+"\n";
					mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
					j++;
				}
				
				}
		
	
	} catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, "la Quantite doit etre en chiffre SVP !!!!");
		// TODO: handle exception
	}
	
		return creerOF;
	
	
}



boolean afficherTableMatierePremiereCreerOF(List<CoutProdMP> listCoutProdMP){
	creerOF=true;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
	String date="01/01/"+sdf.format(datfabrication.getDate())+"";
		Date dateDebut= new Date(date);
	if(!remplirMapQuantiteCharge()){
		creerOF=false;
		JOptionPane.showMessageDialog(null, "Il faut remplir toutes les quantité avant de créer l'OF", "Erreur", JOptionPane.ERROR_MESSAGE);
	}else {
	
	BigDecimal prix_unitaire = BigDecimal.ZERO;
	BigDecimal quantiteExistante=BigDecimal.ZERO;
	BigDecimal quantiteACharge=BigDecimal.ZERO;
	BigDecimal quantiteAChargeTHE=BigDecimal.ZERO;
	BigDecimal quantiteTotal=BigDecimal.ZERO;
	BigDecimal quantiteManqaunte=BigDecimal.ZERO;
	BigDecimal StockFinale=BigDecimal.ZERO;
	intialiserTableau();
	List listCoutProdMPTmp =new ArrayList<CoutProdMP>();
	StockMP stockMP=new StockMP();
	mapQauntiteMatierePremier = new HashMap<>();
	StockMP stockMPMagasinProd=new StockMP();
	
	
	
	StockMP stockMPQauantiteManquante=new StockMP();
	CoutProdMP coutProdMP=new CoutProdMP();
	Magasin magasinStockage =magasinMap.get(comboMagasin.getSelectedItem());
	Magasin magasinProd =magasinPordMap.get(comboMagasinProd.getSelectedItem());
	//intialiserTableau();
	int j=0;
	
	for(int i=0;i<listCoutProdMP.size();i++){
		
		 coutProdMP= listCoutProdMP.get(i);
		 stockMPQauantiteManquante=new StockMP();
			quantiteTotal=mapQuantiteMP.get(coutProdMP.getMatierePremier().getCode());
			
			stockMP=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(),magasinStockage.getId());
			stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(),magasinProd.getId());
			if(stockMPMagasinProd!=null)
			{
				quantiteExistante=stockMPMagasinProd.getStock();
			}
			
			prix_unitaire=stockMP.getPrixUnitaire();
			quantiteACharge=quantiteTotal.subtract(quantiteExistante) ;
			
			StockFinale=BigDecimal.ZERO;
			// Stock Finale de ce jour
			List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, datfabrication.getDate(), magasinStockage, coutProdMP.getMatierePremier());
			if(!listestockfinale.isEmpty())
			{
				for(int t=0;t<listestockfinale.size();t++)
	 			{
	 				
	 			 Object[] object=listestockfinale.get(t);
				
	 			StockFinale= (BigDecimal)object[1];
				
	 			}
			}
			
			
			if(quantiteACharge.compareTo(BigDecimal.ZERO) <0)
				quantiteACharge=BigDecimal.ZERO;
			
			
			
			
			if(stockMP!=null &&  StockFinale.compareTo(quantiteACharge) >=0){
				
				coutProdMP.setQuantCharge(quantiteACharge);
				coutProdMP.setProdcutionCM(productionMP);
				listCoutProdMPTmp.add(coutProdMP);
				Object [] ligne={coutProdMP.getMatierePremier().getCode(),coutProdMP.getMatierePremier().getNom(),coutProdMP.getQuantite().setScale(6)+" "+coutProdMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutProdMP.getQuantExistante().setScale(6),coutProdMP.getQuantEstime().setScale(6),quantiteTotal.setScale(6)};
				modeleMP.addRow(ligne);
			}else if(stockMP!=null && quantiteACharge.compareTo(StockFinale)>0){
				quantiteManqaunte=quantiteACharge.subtract(stockMP.getStock())  ;
				stockMPQauantiteManquante.setMatierePremier(coutProdMP.getMatierePremier());
				stockMPQauantiteManquante.setStock(quantiteManqaunte);
				stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
				creerOF=false;
				nomMP+="-"+coutProdMP.getMatierePremier().getNom()+":"+quantiteManqaunte+"\n";
				mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
				j++;
			}
			
	}
	
	productionMP.setListCoutProdMP(listCoutProdMPTmp);
	}
	return creerOF;
	
}



/*boolean afficherTableMatierePremiereCreerOF(List<CoutMP> listCoutMP){
	creerOF=true;
	
	if(!remplirMapQuantiteCharge()){
		creerOF=false;
		JOptionPane.showMessageDialog(null, "Il faut remplir toutes les quantité avant de créer l'OF", "Erreur", JOptionPane.ERROR_MESSAGE);
	}else {
	
	BigDecimal prix_unitaire = 0;
	BigDecimal quantiteExistante=0;
	BigDecimal quantiteACharge=0;
	BigDecimal quantiteAChargeTHE=0;
	BigDecimal quantiteTotal=0;
	BigDecimal quantiteManqaunte=0;
	intialiserTableau();
	List listCoutMPTmp =new ArrayList<CoutMP>();
	StockMP stockMP=new StockMP();
	mapQauntiteMatierePremier = new HashMap<>();
	StockMP stockMPMagasinProd=new StockMP();
	
	
	
	StockMP stockMPQauantiteManquante=new StockMP();
	CoutMP coutMP=new CoutMP();
	Magasin magasinStockage =magasinMap.get(comboMagasin.getSelectedItem());
	Magasin magasinProd =magasinPordMap.get(comboMagasinProd.getSelectedItem());
	//intialiserTableau();
	int j=0;
	for(int i=0;i<listCoutMP.size();i++){
		
		 coutMP= listCoutMP.get(i);
		 stockMPQauantiteManquante=new StockMP();
		//	DetailEstimation detailEstimation=lisDetailEstimation.get(i);
			quantiteTotal=mapQuantiteMP.get(coutMP.getMatierePremier().getCode());
			

			
			stockMP=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),magasinStockage.getId());
			stockMPMagasinProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),magasinProd.getId());
			quantiteExistante=stockMPMagasinProd.getStock();
			prix_unitaire=stockMP.getPrixUnitaire();
			quantiteACharge=quantiteTotal-quantiteExistante;
			
			if(quantiteACharge<0)
				quantiteACharge=0;
		
		if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE) 
			|| coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) 
			|| coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE)
			|| coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){
	
			quantiteACharge=(BigDecimal) Math.ceil(quantiteACharge);
			
		} 
		if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH) && !coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
			
			if(stockMP!=null && quantiteACharge>stockMP.getStock()){
				quantiteManqaunte=quantiteACharge-stockMP.getStock();
				stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
				stockMPQauantiteManquante.setStock(quantiteManqaunte);
				stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
				creerOF=false;
				nomMP+="-"+coutMP.getMatierePremier().getNom()+":"+quantiteManqaunte+"\n";
				mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
				j++;
			}
			if(stockMP!=null &&  stockMP.getStock()>quantiteACharge){
				coutMP.setMatierePremier(coutMP.getMatierePremier());
				coutMP.setPrixUnitaire(prix_unitaire);
				coutMP.setQuantite(quantiteTotal);
				coutMP.setQuantExistante(quantiteExistante);
				coutMP.setQuantCharge(quantiteACharge);
				coutMP.setProdcutionCM(production);
				listCoutMPTmp.add(coutMP);
			//	listCoutMP.add(coutMP);
				Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),coutMP.getQuantite()+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),quantiteTotal};
				modeleMP.addRow(ligne);
			}
		
		}else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH)){
			MatierePremier matierePremiere=mapMatierePremier.get(comboScotch.getSelectedItem());
			if(coutMP.getMatierePremier().getCode().equals(matierePremiere.getCode())){
				if(stockMP!=null && quantiteACharge>stockMP.getStock()){
					quantiteManqaunte=quantiteACharge-stockMP.getStock();
					
					stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
					stockMPQauantiteManquante.setStock(quantiteManqaunte);
					stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
					
					creerOF=false;
					nomMP+="-"+coutMP.getMatierePremier().getNom()+":"+quantiteManqaunte+"\n";
					
					mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
					j++;
				}
				
				if(stockMP!=null &&  stockMP.getStock()>quantiteACharge){
					
					if(coutMP.getMatierePremier().getCode().equals(MATIERE_PREMIERE_SCOTCH_1000M))
						if(quantiteACharge%2!=0)
							quantiteACharge=quantiteACharge+1;
					
					coutMP.setMatierePremier(coutMP.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantCharge(quantiteACharge);
					coutMP.setProdcutionCM(production);
					listCoutMPTmp.add(coutMP);
				//	listCoutMP.add(coutMP);
					Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),coutMP.getQuantite()+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),quantiteTotal};
					modeleMP.addRow(ligne);
				}
				
			
				
			}
		}else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
			MatierePremier matierePremiere=mapMatierePremier.get(comboBoxThe.getSelectedItem());
			if(coutMP.getMatierePremier().getCode().equals(matierePremiere.getCode())){
				if(stockMP!=null && quantiteACharge>stockMP.getStock()){
					quantiteManqaunte=quantiteACharge-stockMP.getStock();
					
					stockMPQauantiteManquante.setMatierePremier(coutMP.getMatierePremier());
					stockMPQauantiteManquante.setStock(quantiteManqaunte);
					stockMPQauantiteManquante.setMagasin(stockMP.getMagasin());
					
					creerOF=false;
					nomMP+="-"+coutMP.getMatierePremier().getNom()+":"+quantiteManqaunte+"\n";
					
					mapQauntiteMatierePremier.put(j, stockMPQauantiteManquante);
					j++;
				}
				
				if(stockMP!=null && stockMP.getStock()>quantiteACharge){
					
					coutMP.setMatierePremier(coutMP.getMatierePremier());
					coutMP.setPrixUnitaire(prix_unitaire);
					coutMP.setQuantite(quantiteTotal);
					coutMP.setQuantExistante(quantiteExistante);
					coutMP.setQuantCharge(quantiteACharge);
					coutMP.setProdcutionCM(production);
					listCoutMPTmp.add(coutMP);
				//	listCoutMP.add(coutMP);
					Object [] ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),coutMP.getQuantite()+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),coutMP.getQuantExistante(),coutMP.getQuantEstime(),quantiteTotal};
					
					modeleMP.addRow(ligne);
				}
				
			
				
			}
		}
	}
	
	production.setListCoutMP(listCoutMPTmp);
	}
	return creerOF;
	
}*/








	 





 void compterProduction(Date dateProd,String periode){
	 int compteur=0;
	 CompteurProduction compteurProduction=compteurProductionDAO.findByDateProdPeriode(dateProd,periode);
	 
	 if(compteurProduction !=null){
		 compteur=compteurProduction.getCompteur()+1;
		 compteurProduction.setCompteur(compteur);	
		 compteurProductionDAO.edit(compteurProduction);
		 
	 }else{
		 compteurProduction= new CompteurProduction();
		 compteurProduction.setDateProd(dateProd);
		 compteurProduction.setPeriode(periode);
		 compteurProduction.setCompteur(1);
		 compteurProductionDAO.add(compteurProduction);
		 
	 }
	 
 }
 

 
 
 
 boolean remplirMapQuantiteCharge(){
		boolean trouve=false;
		int i=0;
		int j=0;	
		 BigDecimal quantite=BigDecimal.ZERO;
		for( j=0;j<table.getRowCount();j++){
			
			if(table.getValueAt(j, 5).toString()!=null && !table.getValueAt(j, 5).toString().equals("")  ){
				mapQuantiteMP.put(table.getValueAt(j, 0).toString(), new BigDecimal(table.getValueAt(j, 5).toString()));
				
				i++;
				
			}
			
		}
		
		
		 if(i>=j)
			 trouve=true;
		
		
		return trouve;
	}

 
 List<CoutMP> remplirQuantiteChargeMP(List<CoutMP> listCoutMP){
	 
	 BigDecimal quantite=BigDecimal.ZERO;
	 
	 List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
	 
	 for(int i=0;i<listCoutMP.size();i++){
		 
		 CoutMP coutMP=listCoutMP.get(i);
		 
		 quantite=mapQuantiteMP.get(coutMP.getMatierePremier().getCode());
		 
		 coutMP.setQuantCharge(quantite);
		 
		 listCoutMPTmp.add(coutMP);
	 }
	 return listCoutMPTmp;
 }

 
 private static String registerMailBody() {
		return "<HTML><b>OF N° :</b>"+productionMP.getNumOFMP()+"<br><br>"
				+ "<br><b>Les détails de l'OF :</b><br>"
				+ "<LI><b>ArticleMP : "+productionMP.getArticlesMP().getLiblle()+"</b></LI><br>"
				+ "<LI><b>Code Article : "+productionMP.getArticlesMP().getCodeArticle()+"</b></LI><br>"
				+ "<LI><b>Quantité  : "+productionMP.getQuantiteReel()+"</b></LI><br>"
				+ "<LI><b>Depot Stockage  : "+productionMP.getMagasinStockage().getDepot().getLibelle()+"</b></LI><br>"
				+ "<LI><b>Magasin Stockage  : "+productionMP.getMagasinStockage().getLibelle()+"</b></LI><br>"
				
				+ "<LI><b>Depot Production  : "+productionMP.getMagasinProd().getDepot().getLibelle()+"</b></LI><br>"
				+ "<LI><b>Magasin Production  : "+productionMP.getMagasinProd().getLibelle()+"</b></LI><br>"
				
				+ "Merci pour votre confiance<br>"
				+ "Service Informatique<br>"
				+"Système Production</HTML>";
	}
}
