package FactureAchatMP;

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

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
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
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
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
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
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

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;


public class ConsulterEtatFactureAchatMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	

	private DefaultTableModel	 modelefacture;

	private JXTable  tableEtatFactureAchatMP = new JXTable();
	private List<Fournisseur> listFournisseur =new ArrayList<Fournisseur>();

	private List<DetailFactureAchatMP> listDetailFactureAchatMP =new ArrayList<DetailFactureAchatMP>();
	private List<DetailFactureAchatMP> listDetailFactureAchatMPTMP =new ArrayList<DetailFactureAchatMP>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<StockMP> listArticleStockMP =new ArrayList<StockMP>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureAchatMP> listFactureAchatMP =new ArrayList<FactureAchatMP>();
	private List<FactureAchatMP> listFactureAchatMPTmp =new ArrayList<FactureAchatMP>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private CategorieMpDAO categoriempdao;
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	private Map< String, FamilleArticlePF> mapFamille = new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapSousFamille = new HashMap<>();
	
	private SubCategorieMPDAO subcategoriempdao;
	TransferStockMP transferStockMP ;
	private	List<DetailTransferStockMP> listDetailTransferMP= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferStockMPdao;
	
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, Fournisseur> mapFournisseur= new HashMap<>();
	private Map< String, Fournisseur> mapCodeFournisseur= new HashMap<>();

	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private MatierePremiereDAO matierePremierDAO;
	private FactureAchatMPDAO factureAchatMPdao;
	private FactureAchatMP factureAchatMP;
private DetailFactureAchatMPDAO detailFactureAchatMPdao;
private FournisseurDAO fournisseurdao;
	ChargeProduction chargeproduction;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	 private JButton btnSupprimer = new JButton();
	
	private JRadioButton rdbtnDateFacture;
	private JDateChooser datefacturedu;
	private JComboBox comboparfournisseur = new JComboBox();
	private StockMPDAO stockMPDAO;
	JCheckBox checktva = new JCheckBox("TVA");
	private JTextField txtcodeMP;
	
	 private JComboBox familleCombo;
	   JComboBox categoriempcombo = new JComboBox();
	   private JComboBox sousFamilleCombo;
	   JComboBox comboMP = new JComboBox();
	   JComboBox combodepot = new JComboBox();
	   JComboBox combofournisseur = new JComboBox();
	   JDateChooser datefactureau = new JDateChooser();
	   private SousFamilleEnVracDAO sousFamilleEnvracDAo;
	   private SousFamilleArticlesPFDAO sousFamilleArticlesPFDAO;
	   private FamilleArticlesPFDAO familleArticlesPFDAO;
	   private	List<FamilleArticlePF> listFamille= new ArrayList<FamilleArticlePF>();
	   private	List<SousFamilleArticlePF> listsousFamille= new ArrayList<SousFamilleArticlePF>(); 
	   private	List<SousFamilleEnVrac> listsousFamilleEnvrac= new ArrayList<SousFamilleEnVrac>(); 
	   
	   
	   
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatFactureAchatMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1478, 1062);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
             
             
             
            utilisateur=AuthentificationView.utilisateur;
            detailTransferStockMPdao=new DetailTransferMPDAOImpl();
            transferStockMPDAO=new TransferStockMPDAOImpl();
            
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	factureAchatMPdao=new FactureAchatMPDAOImpl();
         	detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();
         	
         	matierePremierDAO=new MatierePremierDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         
         	fournisseurdao=new FournisseurDAOImpl();
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
        	sousFamilleArticlesPFDAO=new SousFamilleArticlesPFDAOImpl();
        	familleArticlesPFDAO=new FamilleArticlesPFDAOImpl();
        	
        	listFamille=familleArticlesPFDAO.findAll();
        	
        	
        	
          } catch (Exception exp){exp.printStackTrace();}
        tableEtatFactureAchatMP.setSortable(false);
        tableEtatFactureAchatMP.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
}
       });
        
       tableEtatFactureAchatMP.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Num Facture", "Date Facture", "Etat", "Fournisseur", "Depot", "Magasin","Code MP","Matière Première","Famille","Sous Famille", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
       	}
       ));
				  		
       tableEtatFactureAchatMP.setShowVerticalLines(false);
       tableEtatFactureAchatMP.setSelectionBackground(new Color(51, 204, 255));
       tableEtatFactureAchatMP.setRowHeightEnabled(true);
       tableEtatFactureAchatMP.setBackground(new Color(255, 255, 255));
       tableEtatFactureAchatMP.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableEtatFactureAchatMP.setColumnControlVisible(true);
       tableEtatFactureAchatMP.setForeground(Color.BLACK);
       tableEtatFactureAchatMP.setGridColor(new Color(0, 0, 255));
       tableEtatFactureAchatMP.setAutoCreateRowSorter(true);
       tableEtatFactureAchatMP.setBounds(2, 27, 411, 198);
       tableEtatFactureAchatMP.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableEtatFactureAchatMP);
				  		     	scrollPane.setBounds(10, 257, 1375, 424);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 229, 1458, 30);
				  		     	add(titledSeparator);
		     
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				


				
				
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String datedebutFacture=dateFormat.format(datefacturedu.getDate());
			  		  String dateFinFacture=dateFormat.format(datefactureau.getDate());
					
					if(listDetailFactureAchatMPTMP.size()!=0)
			{
						
						
				
				Map parameters = new HashMap();
				
				parameters.put("magasin", magasin.getLibelle());
				parameters.put("depot", magasin.getDepot().getLibelle());
				if(!datedebutFacture.equals("") || !dateFinFacture.equals(""))
				{
					if(!datedebutFacture.equals("") && dateFinFacture.equals(""))
					{
						dateFinFacture=datedebutFacture;
						
					}else if(datedebutFacture.equals("") && !dateFinFacture.equals(""))
					{
						datedebutFacture=dateFinFacture;
					}
					
					
					parameters.put("periode", "Période : Du :"+datedebutFacture +" Au : "+dateFinFacture);
					
					
					
				}
					JasperUtils.imprimerEtatFactureAchatMP(tableEtatFactureAchatMP.getModel(),parameters);
				
				
				
				
			}else
			{
				 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE);
				 return;
			}
				
					

				
		
				
				
				
				
				
				
				
				
}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonvalider.setBounds(448, 704, 131, 30);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
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
		
		JLabel lblConslterLesFactures = new JLabel("                             Consulter Etat Achat MP  ");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(151, 11, 1234, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		String requete="";
	    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		if(magasin==null)
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}else
	    		{
	    			
	    			requete=" where factureMP.magasin.id='"+magasin.getId()+"' ";
	    			
	    			
	    			
	    		if(datefacturedu.getDate() !=null || datefactureau.getDate()!=null) {
		    			
		    			if(datefacturedu.getDate()==null && datefactureau.getDate()!=null)
		    			{
		    				datefacturedu.setDate(datefactureau.getDate());
		    			}else if(datefacturedu.getDate()!=null && datefactureau.getDate()==null)
		    			{
		    				datefactureau.setDate(datefacturedu.getDate());
		    				
		    			}else if(datefacturedu.getDate()!=null && datefactureau.getDate()!=null)
		    			{
		    				
		    				if(	DateUtils.nbJoursEntre(datefacturedu.getDate(), datefactureau.getDate())<0)
				    		{

				    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				    			return;
				    		
				    		}
		    				
		    			}
		    			
		    			String dateDu=formatter.format(datefacturedu.getDate());
		    			String dateAu=formatter.format(datefactureau.getDate());
		    			
		    			
		    			
		    			requete=requete +" and factureMP.dateFacture between '"+dateDu +"' and  '"+dateAu+"' ";
		    			
		    		}
	    		
	    		
	    		Fournisseur fournisseur=	mapFournisseur.get(combofournisseur.getSelectedItem());
    			if(fournisseur!=null)
    			{
    				requete=requete +" and factureMP.Fournisseur.id = '"+fournisseur.getId() +"'";
    			}
    			
    		/*	if(soucategoriempcombo.getSelectedIndex()!=-1)
    			{
    				if(!soucategoriempcombo.getSelectedItem().equals(""))
	    			{
	    				SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
	    				 
	    				requete=requete +" and matierePremiere.categorieMp.subCategorieMp.id = '"+subCategorieMp.getId() +"'";
	    				 
	    				 
	    			}
    			}
    			
    			if(categoriempcombo.getSelectedIndex()!=-1)
    			{
    				if(!categoriempcombo.getSelectedItem().equals(""))
	    			{
	    				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
	    				 
	    				requete=requete +" and matierePremiere.categorieMp.id = '"+categorieMp.getId() +"'";
	    				 
	    				 
	    			}
    			}
    			
    			*/
    			
    			
    			
    			if(comboMP.getSelectedIndex()!=-1)
    			{
    				if(!comboMP.getSelectedItem().equals(""))
	    			{
	    				MatierePremier matierePremier=mapMP.get(comboMP.getSelectedItem());
	    				
	    				if(matierePremier!=null)
	    				{
	    					requete=requete +" and matierePremiere.id = '"+matierePremier.getId() +"'";
	    					
	    				}
	    				
	    				
	    			}
    				
    			}
    			
	    			
	    			
	    			
	    			listDetailFactureAchatMP=detailFactureAchatMPdao.listeDetailFactureAchatByRequete(requete);
	    			
	    			listDetailFactureAchatMPTMP.clear();
	    			
	    			
	    			
	    			
	    			
	    			
	    			
	    			
	    			
	    			
	    			afficher_tableFactureAchat(listDetailFactureAchatMP);
	    			
	    			
	    			
	    			
	    			
	    		}
	   
	    		
	    	
	    		
	    		
	    	
	    				
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(577, 179, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1458, 111);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Date Du:");
	    lblDateFacture.setBounds(459, 24, 97, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     datefacturedu = new JDateChooser();
	     datefacturedu.setBounds(534, 23, 159, 26);
	     layeredPane_2.add(datefacturedu);
	     datefacturedu.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {}
	     });
	     datefacturedu.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     datefacturedu.setLocale(Locale.FRANCE);
	     datefacturedu.setDateFormatString("dd/MM/yyyy");
	     
	     JLabel label_3 = new JLabel("Depot :");
	     label_3.setBounds(10, 25, 56, 24);
	     layeredPane_2.add(label_3);
	     label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     
	      combodepot = new JComboBox();
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
			     				
	    					
	    				}
	    				
	    				
	   	 		 }
	   	 	
			      		
	      	}
	      });
	      combodepot.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {}
	      });
	     combodepot.setBounds(58, 25, 144, 24);
	     layeredPane_2.add(combodepot);
	     combodepot.setSelectedIndex(-1);
	     
	     JLabel label_4 = new JLabel("Magasin :");
	     label_4.setBounds(212, 25, 56, 24);
	     layeredPane_2.add(label_4);
	     label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     
	    combomagasin = new JComboBox();
	    combomagasin.setBounds(266, 26, 183, 24);
	     layeredPane_2.add(combomagasin);
	     combomagasin.setSelectedIndex(-1);
	     
	      datefactureau = new JDateChooser();
	     datefactureau.setLocale(Locale.FRANCE);
	     datefactureau.setDateFormatString("dd/MM/yyyy");
	     datefactureau.setBounds(778, 22, 151, 26);
	     layeredPane_2.add(datefactureau);
	     
	     JLabel lblDateAu = new JLabel("Date Au:");
	     lblDateAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblDateAu.setBounds(703, 23, 97, 24);
	     layeredPane_2.add(lblDateAu);
	     
	     JLabel label_6 = new JLabel("Fournisseur :");
	     label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_6.setBounds(939, 24, 79, 24);
	     layeredPane_2.add(label_6);
	     
	      combofournisseur = new JComboBox();
	     combofournisseur.setSelectedIndex(-1);
	     combofournisseur.setBounds(1017, 25, 183, 24);
	     layeredPane_2.add(combofournisseur);
	     AutoCompleteDecorator.decorate(combofournisseur);
	     JLabel label = new JLabel("Code MP :");
	     label.setBounds(632, 60, 74, 26);
	     layeredPane_2.add(label);
	     label.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     
	     txtcodeMP = new JTextField();
	     txtcodeMP.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		

		  		if (e.getKeyCode() == e.VK_ENTER)
		  		{
		  			if(!txtcodeMP.getText().equals(""))
		  			{
		  				MatierePremier matierePremier=mapCodeMP.get(txtcodeMP.getText().toUpperCase());
			  			if(matierePremier!=null)
			  			{
			  				comboMP.setSelectedItem(matierePremier.getNom());
			  			}else
			  			{
			  				comboMP.setSelectedIndex(-1);
			  			}
			  			
		  				
		  			}
		  			
		  		}
	     		
	     		
	     	}
	     });
	     txtcodeMP.setBounds(704, 60, 93, 26);
	     layeredPane_2.add(txtcodeMP);
	     txtcodeMP.setColumns(10);
	     
	     JLabel label_1 = new JLabel("Libelle :");
	     label_1.setBounds(807, 60, 45, 26);
	     layeredPane_2.add(label_1);
	     label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     
	      comboMP = new JComboBox();
	      comboMP.addItemListener(new ItemListener() {
	      	public void itemStateChanged(ItemEvent e) {
	      		

		     	 	
		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
		     	
		     	 		MatierePremier mp= mapMP.get(comboMP.getSelectedItem());
		     	 		 if(mp!=null)
		     	 		 {
		     	 		   	txtcodeMP.setText(mp.getCode());
		     	 		 }
 		     	
	                   
		     	 	 	}
		     	 	
	      		
	      		
	      		
	      	}
	      });
	      comboMP.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
}
	      });
	     comboMP.setBounds(852, 60, 218, 27);
	     layeredPane_2.add(comboMP);
	     AutoCompleteDecorator.decorate(comboMP);
	     JLabel lblFamille = new JLabel("Famille");
	     lblFamille.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     lblFamille.setBounds(10, 62, 144, 24);
	     layeredPane_2.add(lblFamille);
	     
	      familleCombo = new JComboBox();
	     familleCombo.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		

  		  		int i=0;
  		  		if(familleCombo.getSelectedIndex()!=-1 && !familleCombo.getSelectedItem().equals(""))
  		  		{
  		  			
  		  			
  		  			sousFamilleCombo.removeAllItems();
  		  		sousFamilleCombo.addItem("");
  		  		
  		  		
  		  		if(subcatMap.get(familleCombo.getSelectedItem())!=null)
  		  		{
  		  		listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(familleCombo.getSelectedItem()).getId());
		  			if(listecategoriemp!=null)
		  			{
		  				while(i<listecategoriemp.size())
		  				{
		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i));
		  					sousFamilleCombo.addItem(listecategoriemp.get(i).getNom());
		  					i++;
		  				}
		  				
		  			}
		  			
		  			
		  			listMP=matierePremierDAO.listeMatierePremierBySousCategorie(subcatMap.get(familleCombo.getSelectedItem()).getId());
		  			mapMP.clear();
		  			mapCodeMP.clear();
		  			comboMP.removeAllItems();
		  		comboMP.addItem("");
		  			for(int j=0;j<listMP.size();j++)
		  			{
		  				MatierePremier matierePremier=listMP.get(j);
		  				comboMP.addItem(matierePremier.getNom());
		  				mapMP.put(matierePremier.getNom(), matierePremier);
		  				mapCodeMP.put(matierePremier.getCode(), matierePremier);
		  				
		  				
		  			}
  		  			
  		  		}else
  		  		{
  		  			FamilleArticlePF familleArticlePF=mapFamille.get(familleCombo.getSelectedItem());
  		  			
  		  			if(familleArticlePF!=null)
  		  			{
  		  				
  		  	  		listsousFamille= sousFamilleArticlesPFDAO.listeSousFamillePFByFamilleArticlePF(familleArticlePF.getId());
  		  			if(listsousFamille!=null)
  		  			{
  		  				while(i<listsousFamille.size())
  		  				{
  		  					mapSousFamille.put(listsousFamille.get(i).getLiblle(), listsousFamille.get(i));
  		  					sousFamilleCombo.addItem(listsousFamille.get(i).getLiblle());
  		  					i++;
  		  				}
  		  				
  		  			}
  		  			
  		  			
  		  			listsousFamilleEnvrac=sousFamilleEnvracDAo.listeMatierePremierByFamille(familleArticlePF.getId());
  		  			mapMP.clear();
  		  			mapCodeMP.clear();
  		  			comboMP.removeAllItems();
  		  		comboMP.addItem("");
  		  			for(int j=0;j<listsousFamilleEnvrac.size();j++)
  		  			{
  		  				MatierePremier matierePremier=listsousFamilleEnvrac.get(j).getMatierePremier();
  		  				comboMP.addItem(matierePremier.getNom());
  		  				mapMP.put(matierePremier.getNom(), matierePremier);
  		  				mapCodeMP.put(matierePremier.getCode(), matierePremier);
  		  				
  		  				
  		  			}
  	  		  			
  		  				
  		  			}
  		  			
  		
  		  			
  		  			
  		  			
  		  			
  		  		}
  		  		 		  			 		  			
  		  		}else
  		  		{
  		  		sousFamilleCombo.removeAllItems();
  		  	sousFamilleCombo.addItem("");
  				listMP=matierePremierDAO.findAll();
  				mapMP.clear();
		  			mapCodeMP.clear();
		  			comboMP.removeAllItems();
		  		comboMP.addItem("");
		  			for(int j=0;j<listMP.size();j++)
		  			{
		  				MatierePremier matierePremier=listMP.get(j);
		  				comboMP.addItem(matierePremier.getNom());
		  				mapMP.put(matierePremier.getNom(), matierePremier);
		  				mapCodeMP.put(matierePremier.getCode(), matierePremier);
		  				
		  				
		  			}
  		  		}
  		  		
  		  	
	     		
	     	}
	     });
	     familleCombo.setBounds(83, 62, 218, 24);
	     layeredPane_2.add(familleCombo);
	     
	     JLabel lblSousFamille = new JLabel("Sous Famille");
	     lblSousFamille.setBounds(339, 61, 108, 26);
	     layeredPane_2.add(lblSousFamille);
	     
	      sousFamilleCombo = new JComboBox();
	      sousFamilleCombo.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		

	     		

  		  		int i=0;
  		  		if(sousFamilleCombo.getSelectedIndex()!=-1 && !sousFamilleCombo.getSelectedItem().equals(""))
  		  		{
  		  			comboMP.removeAllItems();
  		  			txtcodeMP.setText("");
  		  		CategorieMp categorieMp=catMap.get(sousFamilleCombo.getSelectedItem());
  		  		if(categorieMp!=null)
  		  		{
  		  		listMP=matierePremierDAO.listeMatierePremierByidcategorie(categorieMp.getId());
  		  		
  		  	mapMP.clear();
	  			mapCodeMP.clear();
	  			comboMP.removeAllItems();
	  		comboMP.addItem("");
	  			for(int j=0;j<listMP.size();j++)
	  			{
	  				MatierePremier matierePremier=listMP.get(j);
	  				comboMP.addItem(matierePremier.getNom());
	  				mapMP.put(matierePremier.getNom(), matierePremier);
	  				mapCodeMP.put(matierePremier.getCode(), matierePremier);
	  				
	  				
	  			}
  		  		
  		  		
  		  		}else
  		  		{
  		  			
  		  			SousFamilleArticlePF sousFamilleArticlePF=mapSousFamille.get(sousFamilleCombo.getSelectedItem());
  		  			if(sousFamilleArticlePF!=null)
  		  			{
  		  				
  		  				listsousFamilleEnvrac=sousFamilleEnvracDAo.listeMatierePremierBySousFamille(sousFamilleArticlePF.getId());
  		  				
  		  			mapMP.clear();
  		  			mapCodeMP.clear();
  		  			comboMP.removeAllItems();
  		  		comboMP.addItem("");
  		  			for(int j=0;j<listsousFamilleEnvrac.size();j++)
  		  			{
  		  				MatierePremier matierePremier=listsousFamilleEnvrac.get(j).getMatierePremier();
  		  				comboMP.addItem(matierePremier.getNom());
  		  				mapMP.put(matierePremier.getNom(), matierePremier);
  		  				mapCodeMP.put(matierePremier.getCode(), matierePremier);
  		  				
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  		}
  		  			
  		  		
  		  			
  		  			
  		  		}else
  		  		{
  				listMP=matierePremierDAO.findAll();
  				mapMP.clear();
		  			mapCodeMP.clear();
		  			comboMP.removeAllItems();
		  		comboMP.addItem("");
		  			for(int j=0;j<listMP.size();j++)
		  			{
		  				MatierePremier matierePremier=listMP.get(j);
		  				comboMP.addItem(matierePremier.getNom());
		  				mapMP.put(matierePremier.getNom(), matierePremier);
		  				mapCodeMP.put(matierePremier.getCode(), matierePremier);
		  				
		  				
		  			}
  		  		}
  		  		
  		  		
	      		
	      		
	      	}
	      });
	     sousFamilleCombo.setBounds(420, 62, 191, 24);
	     layeredPane_2.add(sousFamilleCombo);
    
  listMP=matierePremierDAO.findAll();
  comboMP.addItem("");
  for(int j=0;j<listMP.size();j++)
  {
	 MatierePremier mp= listMP.get(j);
	 comboMP.addItem(mp.getNom());
	 mapMP.put(mp.getNom(), mp);
	 mapCodeMP.put(mp.getCode(), mp);
	  
  }
 	
        
      
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		
	     	
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(707, 180, 106, 23);
	     add(button);
	     
	     JButton btnExporterExcel = new JButton("Exporter Excel");
	     btnExporterExcel.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
				  
				  String
				  titre="Etat Achat MP "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
				  String titrefeuille="Etat Achat MP ";
				  ExporterTableVersExcel.tabletoexcelEtatAchatMP(tableEtatFactureAchatMP, titre,titrefeuille);
				  
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	  	
	     		
	     		
	     	}
	     });
	     btnExporterExcel.setFont(new Font("Tahoma", Font.BOLD, 11));
	     btnExporterExcel.setBounds(599, 704, 131, 30);
	     add(btnExporterExcel);
	   
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
				     		
		     				mapparDepot.put(depot.getLibelle(), depot);
				     	
				     		
			     			
			     		}
		     		}else
		     		{
		     			combodepot.addItem(depot.getLibelle());
			     		
		     			mapparDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem("");
				  combodepot.addItem(depot.getLibelle());
				  mapparDepot.put(depot.getLibelle(), depot);
		     	
			  }
		  }
	    
	    
	   
	    
	    
		  listFournisseur=fournisseurdao.findAll();
			combofournisseur.addItem("");
	        int i=0;
	        while(i<listFournisseur.size())
	        {
	       	Fournisseur fournisseur= listFournisseur.get(i);
	       	combofournisseur.addItem(fournisseur.getNom());
	       	comboparfournisseur.addItem(fournisseur.getNom());
	       mapFournisseur.put(fournisseur.getNom(), fournisseur);
	       i++;
	       	 
	        }
		  
		  listsubcategoriemp=subcategoriempdao.findAll();
		  familleCombo.addItem("");
		  
		  
		  for(int t=0;t<listFamille.size();t++)
		  {
			  
			  FamilleArticlePF familleArticlePF=listFamille.get(t);
			  if(familleArticlePF.getLiblle().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || familleArticlePF.getLiblle().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || familleArticlePF.getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
			  {
				  
				  familleCombo.addItem(familleArticlePF.getLiblle());  
				  
				  mapFamille.put(familleArticlePF.getLiblle(), familleArticlePF);
				  
				  
				  
				  
			  }
			  
			  
			  
			  
			  
		  }
		  
		  
		  
		  
		  for(int j=0;j<listsubcategoriemp.size();j++)
		  {
			  
			SubCategorieMp subCategorieMp=  listsubcategoriemp.get(j);
			
		
			familleCombo.addItem(subCategorieMp.getNom());
			  subcatMap.put(subCategorieMp.getNom(), subCategorieMp);
			  
		  }
		  
		  
		  
		  
		  
	    
		
		}
	
	
	


	void initialiser()
	{
txtcodeMP.setText("");
	
	comboMP.setSelectedIndex(-1);
	sousFamilleCombo.setSelectedIndex(-1);
	familleCombo.setSelectedIndex(-1);
	combofournisseur.setSelectedIndex(-1);
	datefactureau.setDate(null);
	datefacturedu.setDate(null);
	
	}
	

	
	
	

	

	
	
	

	
	
	void afficher_tableFactureAchat(List<DetailFactureAchatMP> listDetailFactureMP)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Etat", "Fournisseur", "Depot", "Magasin","Code MP","Matière Première","Famille","Sous Famille", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatFactureAchatMP.setModel(modelefacture);
		int i=0;
		
		
		listDetailFactureAchatMPTMP.clear();
		
		
		 
		while(i<listDetailFactureMP.size())
		{	
		DetailFactureAchatMP detailfactureAchatMP=listDetailFactureMP.get(i);
		
		SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(detailfactureAchatMP.getMatierePremiere());
		
		
		if(sousFamilleEnVrac!=null)
		{
			
			detailfactureAchatMP.setSousFamille(sousFamilleEnVrac.getSousfamile().getLiblle());
			detailfactureAchatMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
		}else
		{
			
			detailfactureAchatMP.setSousFamille(detailfactureAchatMP.getMatierePremiere().getCategorieMp().getNom());
			detailfactureAchatMP.setFamille(detailfactureAchatMP.getMatierePremiere().getCategorieMp().getSubCategorieMp().getNom());
			
		}
		
		
		if(sousFamilleCombo.getSelectedIndex()!=-1 && !sousFamilleCombo.getSelectedItem().equals(""))
		{
			
			if(detailfactureAchatMP.getSousFamille().equals(sousFamilleCombo.getSelectedItem()))
			{
				
				listDetailFactureAchatMPTMP.add(detailfactureAchatMP);
			}
		}else if(sousFamilleCombo.getSelectedItem().equals("") && !familleCombo.getSelectedItem().equals(""))
		{
			
			if(detailfactureAchatMP.getFamille().equals(familleCombo.getSelectedItem()))
			{
				
				listDetailFactureAchatMPTMP.add(detailfactureAchatMP);
			}
			
			
		}else
		{
			
			listDetailFactureAchatMPTMP.add(detailfactureAchatMP);
			
		}
			
			
			i++;
		}
		
		for(int j=0;j<listDetailFactureAchatMPTMP.size();j++)
		{
			
			
			DetailFactureAchatMP detailfactureAchatMP=listDetailFactureAchatMPTMP.get(j);
			
Object []ligne={detailfactureAchatMP.getFactureAchatMP().getNumFacture(),detailfactureAchatMP.getFactureAchatMP().getDateFacture(),detailfactureAchatMP.getFactureAchatMP().getEtat(),detailfactureAchatMP.getFactureAchatMP().getFournisseur().getNom(),detailfactureAchatMP.getFactureAchatMP().getDepot().getLibelle(),detailfactureAchatMP.getFactureAchatMP().getMagasin().getLibelle(),detailfactureAchatMP.getMatierePremiere().getCode(),detailfactureAchatMP.getMatierePremiere().getNom(),detailfactureAchatMP.getFamille(),detailfactureAchatMP.getSousFamille(), detailfactureAchatMP.getPrixUnitaire(),detailfactureAchatMP.getQuantite(),detailfactureAchatMP.getReduction(),detailfactureAchatMP.getMontantHT(),detailfactureAchatMP.getMontantTVA(),detailfactureAchatMP.getMontantTTC()};

			
			listDetailFactureMP.set(j, detailfactureAchatMP);
			
			modelefacture.addRow(ligne);
			
			
			
			
		}
		
		
		
		
		
}
	}


