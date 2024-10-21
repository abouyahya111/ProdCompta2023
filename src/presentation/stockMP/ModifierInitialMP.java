package presentation.stockMP;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Map;

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

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Journee;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
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


public class ModifierInitialMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;
	
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<StockMP> listStockMPTmp =new ArrayList<StockMP>();
	private List<StockMP> listStockMPAfficher =new ArrayList<StockMP>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private List<SubCategorieMp> listFamille =new ArrayList<SubCategorieMp>();
	private List<CategorieMp> listSousFamille =new ArrayList<CategorieMp>();
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremiere= new HashMap<>();
	private Map< String, CategorieMp> mapSousFamille= new HashMap<>();
	private Map< String, SubCategorieMp> mapFamille= new HashMap<>();
	
	TransferStockMP transferStock = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	
	 private JComboBox combomp;

	


	 private JourneeDAO journeeDAO;
private StockMPDAO stockMPDAO;
	private JTextField txtcodemp;
	
	private JTextField txtquantite;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	
	private DepotDAO depotdao;
	private MatierePremiereDAO matierepremieredao;
	private static SequenceurDAO sequenceurDAO=new SequenceurDAOImpl();
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	private DetailTransferMPDAO detailTransferStockMPDAO;
	 JComboBox combomagasin = new JComboBox();
	
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txtPrix;
	private  JButton btnSupprimer = new JButton();
	 private   JComboBox comboFournisseur = new JComboBox();
	private   JDateChooser dateChooserMP ;
	 JComboBox comboFamille = new JComboBox();
	 JComboBox comboSousFamille = new JComboBox();
	 private CategorieMpDAO categorieMpDAO;
	 private SubCategorieMPDAO subCategorieMPDAO;
	 
	 
	 
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ModifierInitialMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1395, 795);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	matierepremieredao=new MatierePremierDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	mouvementStockGlobalDAO=new MouvementStockGlobalDAOImpl();
        	journeeDAO=new JourneeDAOImpl();
        
         transferStockMPDAO=new TransferStockMPDAOImpl();
         detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
         categorieMpDAO=new CategorieMpDAOImpl();
         subCategorieMPDAO=new SubCategorieMPAOImpl();
         
         listFamille=subCategorieMPDAO.findAll();
         	
          } catch (Exception exp){exp.printStackTrace();}
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 221, 1225, 140);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code MP :");
		  lblCodeArticle.setBounds(690, 37, 72, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(854, 36, 57, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
		      txtcodemp = new JTextField();
		      util.Utils.copycoller(txtcodemp);
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
			      				 JOptionPane.showMessageDialog(null, " Code MP  incorrecte ", "Erreur", JOptionPane.ERROR_MESSAGE);
			      				
			      			}
			      			
			      			
			      			}else
			      		{
			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			      			
			      			
			      		}
	     				
	     				
	     				
			      		}
		     			
		     			
		     			
		     			
		     			
		     		}
		      });
		      
		      
		      
		      txtcodemp.setColumns(10);
		      txtcodemp.setBounds(748, 37, 96, 26);
		      layeredPane.add(txtcodemp);
		    
		   
		       combomp = new JComboBox();
		       combomp.addItem("");
		       combomp.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		     	 		if(combomp.getSelectedItem()!=null)
		     	 		{
		     	 			
		     	 			if(!combomp.getSelectedItem().equals(""))
	  				 		{
	  				 			MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
	  				 			txtcodemp.setText(mp.getCode());
	  				 		
	  				 			
	  				 		
	  				 		  				 			
	  				 		}else
	  				 		{
	  				 			txtcodemp.setText("");
	  				 			
	  				 		}
		     	 		}else
  				 		{
  				 			txtcodemp.setText("");
  				 			
  				 		}
  		     	 	
  			 		
  				 	
  			 		
  			 		
  			 		
  		     	 		
  		     	 	
		       	}
		       });
		      combomp.setBounds(905, 36, 300, 27);
		      layeredPane.add(combomp);
		      
		     
		     	int i=0;
		     	while(i<listMP.size())
		     	{
		     		
		     		MatierePremier mp=listMP.get(i);
		     		mapCodeMP.put(mp.getCode(), mp);
		     		mapMatierePremiere.put(mp.getNom(), mp);
		     		combomp.addItem(mp.getNom());
		     		
		     		
		     		i++;
		     	}
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(10, 91, 57, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(69, 91, 167, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(265, 91, 64, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(324, 91, 167, 26);
		      layeredPane.add(txtPrix);
		      
		      JLabel lblFamille = new JLabel("Famille :");
		      lblFamille.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      lblFamille.setBounds(10, 39, 56, 24);
		      layeredPane.add(lblFamille);
		      
		       comboFamille = new JComboBox();
		       comboFamille.addItemListener(new ItemListener() {
		       	public void itemStateChanged(ItemEvent e) {
		       		
		       	 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
		       		 
		       		 listMP.clear(); 
		       		 listSousFamille.clear();
		       		 comboSousFamille.removeAllItems();
		       		 combomp.removeAllItems();
		       		comboSousFamille.addItem("");
		       		SubCategorieMp subCategorieMp=mapFamille.get(comboFamille.getSelectedItem());
		       		
		       		if(subCategorieMp!=null)
		       		{
		       			
		       		listSousFamille=categorieMpDAO.findBySubcategorie(subCategorieMp.getId())	;
		       		
		       		
		       		
		       		for(int j=0;j<listSousFamille.size();j++)
		       		{
		       			
		       		CategorieMp categorieMp=	listSousFamille.get(j);
		       		
		       		
		       		comboSousFamille.addItem(categorieMp.getNom());
		       			
		       		mapSousFamille.put(categorieMp.getNom(), categorieMp)	;
		       			
		       			
		       		}
		       			
		       			
		       			
		       			
		       		}
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
    	 		 }
		       		
		       		
		       		
		       		
		       		
		       		
		       	}
		       });
		      comboFamille.setBounds(60, 40, 207, 24);
		      layeredPane.add(comboFamille);
		      
		      JLabel lblSousFamille = new JLabel("Sous Famille :");
		      lblSousFamille.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      lblSousFamille.setBounds(277, 38, 86, 24);
		      layeredPane.add(lblSousFamille);
		      
		       comboSousFamille = new JComboBox();
		       comboSousFamille.addItemListener(new ItemListener() {
		       	public void itemStateChanged(ItemEvent e) {
		       		

		       		 
		       		 listMP.clear(); 
		       		
		       		
		       		 combomp.removeAllItems();
		       		combomp.addItem("");
		       		
		       		CategorieMp CategorieMp=mapSousFamille.get(comboSousFamille.getSelectedItem());
		       		
		       		if(CategorieMp!=null)
		       		{
		       			
		       		listMP=matierepremieredao.listeMatierePremierByidcategorie(CategorieMp.getId())	;
		       		
		       		
		       		
		       		for(int j=0;j<listMP.size();j++)
		       		{
		       			
		       		MatierePremier mp=	listMP.get(j);
		       		
		       		
		       		combomp.addItem(mp.getNom());
		       			
		       		mapMatierePremiere.put(mp.getNom(), mp)	;
		       		mapCodeMP.put(mp.getCode(), mp)	;	
		       			
		       		}
		       			
		       			
		       			
		       			
		       		}
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
   	 		 
		       		
		       		
		       		
		       		
		       		
		       		
		       		
		       		
		       	}
		       });
		      comboSousFamille.setBounds(351, 39, 309, 24);
		      layeredPane.add(comboSousFamille);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations MP");
		titledSeparator_1.setBounds(10, 180, 1225, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1225, 97);
		add(layeredPane_1);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(439, 30, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(505, 31, 209, 24);
		  layeredPane_1.add(combodepot);
		  combodepot.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		

 	 			
    	 		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
    	 			int i=0;
    	 		
    	 				if(!combodepot.getSelectedItem().equals(""))
     			{
     				Depot depot=mapDepot.get(combodepot.getSelectedItem());
     				if(depot!=null)
     				{
     					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_MP);
		     				if(listMagasin.size()!=0)
		     				{
		     					combomagasin.removeAllItems();
		     					combomagasin.addItem("");
		     					while(i<listMagasin.size())
  		     				{
  		     					Magasin magasin=listMagasin.get(i);
  		     					combomagasin.addItem(magasin.getLibelle());
  		     					mapMagasin.put(magasin.getLibelle(), magasin);
  		     					i++;
  		     				}
		     				}else
		     				{
		     					combomagasin.removeAllItems();
		     					
		     				}
		     				
		     				
		     				
		     				
		     			}else
		     			{
		     				combomagasin.removeAllItems();
		     				
		     			}
		     				
     					
     				}else
     				{
     					combomagasin.removeAllItems();
     				}
     				
     				
    	 		 }
    	 	
 	}
		  });
		  
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
		     		if(magasin!=null)
		     		{
		     			if(depot.getId()!=magasin.getDepot().getId())
			     		{
			     			combodepot.addItem(depot.getLibelle());
				     		
				     		mapDepot.put(depot.getLibelle(), depot);
				     	
				     		
			     			
			     		}
		     		}else
		     		{
		     			combodepot.addItem(depot.getLibelle());
			     		
			     		mapDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(756, 31, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	}
		  });
		  combomagasin.setBounds(836, 32, 183, 24);
		  layeredPane_1.add(combomagasin);
		  
		  JLabel label = new JLabel("Date  :");
		  label.setBounds(58, 33, 62, 24);
		  layeredPane_1.add(label);
		  
		   dateChooserMP = new JDateChooser();
		  dateChooserMP.setLocale(Locale.FRANCE);
		  dateChooserMP.setDateFormatString("dd/MM/yyyy");
		  dateChooserMP.setBounds(162, 30, 181, 26);
		  layeredPane_1.add(dateChooserMP);
		 
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Depot");
		titledSeparator_2.setBounds(10, 11, 1225, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Modifier");
		btnAjouter.setBounds(342, 409, 107, 24);
		btnAjouter.setIcon(imgModifierr);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					
					boolean existe=false;
					BigDecimal quantitetotal=BigDecimal.ZERO;
					BigDecimal newprix=BigDecimal.ZERO;
					
					
					 if(dateChooserMP.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(combodepot.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(combomagasin.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
							
						}else if(combomp.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner la matière première SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodemp.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if( (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))
							{
								JOptionPane.showMessageDialog(null, "la quantite MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else if(txtPrix.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir le prix MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else if( (new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<0))
							{
								JOptionPane.showMessageDialog(null, "le prix MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else	
							{
								  Depot depot=mapDepot.get(combodepot.getSelectedItem());
								Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
								MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
								StockMP stockmp=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
								
								/*
								  Journee journeeDestination=journeeDAO.findByDateEtatOuverte(dateChooserMP.getDate(), Constantes.ETAT_STATUT_OVERTE,depot.getId());
								if(journeeDestination==null)
								{
									 
			                    	  JOptionPane.showMessageDialog(null, "Veuillez créer Journnée SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			                    	  return;
								}
								*/
								
								DetailTransferStockMP detailtransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPByMPInitialiser(mp, ETAT_TRANSFER_STOCK_INITIAL,depot,magasin);
								
							
								
								
								if(detailtransferStockMP!=null)
								{
									
									detailtransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
									detailtransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));
									
									detailTransferStockMPDAO.edit(detailtransferStockMP);
									existe=true;
								}
								if(existe==true)
								{
									JOptionPane.showMessageDialog(null, "Initial Modifier avec succé","Bravo",JOptionPane.INFORMATION_MESSAGE);
									initialiserMP();
								}else
								{
									JOptionPane.showMessageDialog(null, mp.getNom() +"introuvable !!!!!","ERREUR",JOptionPane.ERROR_MESSAGE);
									return;
								}
							
								
								
							
								
									
								
								
							}
						        
							
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "la Quantité et le prix doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				
		
				}
				
			
		});	
		
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(473, 408, 106, 23);
		  btnInitialiser.setIcon(imgInit);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiserMP();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserdepot();;
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(441, 147, 106, 23);
		  add(button);
		
		comboFamille.addItem("");
		
		JButton button_1 = new JButton("Rechercher");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				

		 		
		 		MatierePremier matierePremier=mapMatierePremiere.get(combomp.getSelectedItem());
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				
				
				DetailTransferStockMP detailtransferMP=detailTransferStockMPDAO.findAllTransferStockMPByMPInitialiser(matierePremier, ETAT_TRANSFER_STOCK_INITIAL, magasin.getDepot(), magasin);
		 		
				if(detailtransferMP!=null)
				{
					txtPrix.setText(detailtransferMP.getPrixUnitaire()+"");
					txtquantite.setText(detailtransferMP.getQuantite()+"");
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, matierePremier.getNom() +"introuvable !!!!!","ERREUR",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 		
		 	
				
				
				
				
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(1257, 260, 107, 24);
		add(button_1);
		for(int j=0;j<listFamille.size();j++)
		{
			
			SubCategorieMp subCategorieMp=listFamille.get(j);
			
			comboFamille.addItem(subCategorieMp.getNom());
			mapFamille.put(subCategorieMp.getNom(), subCategorieMp);
			
			
			
			
		}
		
		
			
		}
	

	
	void initialiserdepot()
	{
		dateChooserMP.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		comboFournisseur.setSelectedIndex(-1);
		}

	void initialiserMP()
	{
		txtcodemp.setText("");
		combomp.setSelectedItem("");
	   txtPrix.setText("");
		txtquantite.setText("");
		
	     btnAjouter.setEnabled(true);
       
		
		 combomp.removeAllItems();
		 comboSousFamille.removeAllItems();
		 comboFamille.setSelectedItem("");
		 
		
	}
	

	

	}


