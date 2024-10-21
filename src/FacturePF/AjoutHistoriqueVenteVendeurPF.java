package FacturePF;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.PrintException;
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
import util.ConverterNumberToWords;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.AdresseClientDAOImpl;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailHistoriqueVenteVendeurDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.EnTeteDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.HistoriqueVenteVendeurDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeBLDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
import dao.daoManager.AdresseClientPFDAO;
import dao.daoManager.ArticlesDAO;
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
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailHistoriqueVenteVendeurDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EnTeteDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.HistoriqueVenteVendeurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeBLDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.AdresseClientPF;
import dao.entity.Articles;
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
import dao.entity.DetailHistoriqueVenteVendeur;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.EnTete;
import dao.entity.FacturePF;
import dao.entity.FactureVenteMP;
import dao.entity.FamilleArticlePF;
import dao.entity.FraisDepot;
import dao.entity.HistoriqueVentevendeur;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.TypeBL;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;

import com.lowagie.text.pdf.events.IndexEvents.Entry;
import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;


public class AjoutHistoriqueVenteVendeurPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tableArticle = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailHistoriqueVenteVendeur> listDetailFacturePF =new ArrayList<DetailHistoriqueVenteVendeur>();
	private List<DetailHistoriqueVenteVendeur> listDetailFacturePFBlTransferer =new ArrayList<DetailHistoriqueVenteVendeur>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private List<Articles> listArticleOffre =new ArrayList<Articles>();
	private List<SousFamilleArticlePF> listSousFamilleArticleOffre =new ArrayList<SousFamilleArticlePF>();
	private List<DetailPrixArticle> listArticleStockPF =new ArrayList<DetailPrixArticle>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private List<TransferStockPF> listTransferStockPF =new ArrayList<TransferStockPF>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	
	private TransferStockPF transferStock = new TransferStockPF();
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private	List<HistoriqueVentevendeur> listHistoriqueVentevendeur =new ArrayList<HistoriqueVentevendeur>();
	
	private Map< String, Articles> mapArticleOffre = new HashMap<>();
	private Map< String, Articles> mapArticlePromo = new HashMap<>();
	private Map< String, Articles> mapArticleOffreCode = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamilleOffre= new HashMap<>();
	private Map< String, HistoriqueVentevendeur> mapHistoriqueVenteVendeur = new HashMap<>();
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
	private    JComboBox combofamille =new JComboBox();
	 private JComboBox comboArticle;
	 private  JComboBox combosousfamille ;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private HistoriqueVenteVendeurDAO historiqueVenteVendeurDAO;
	private HistoriqueVentevendeur historiqueVentevendeur=new HistoriqueVentevendeur();
	private DetailCompteClientDAO detailCompteClientdao;
private DetailHistoriqueVenteVendeurDAO detailHistoriqueVenteVendeurDAO;
private ClientPFDAO clientpfdao;

	private JTextField txtcodearticle;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtnumBL;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	private CompteClientDAO compteclientdao;
	private ClientDAO fournisseurdao;
	private FamilleArticlesPFDAO familleArticleDAO;
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	private DetailPrixArticleDAO detailPrixArticleDAO;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	 private JDateChooser dateChooserfacture;
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txtPrix;
	private JTextField txtmontant;
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	private  JButton btnModifier ;
	private  JButton btnSupprimer = new JButton();
	private   JComboBox comboFournisseur = new JComboBox();
	private   JComboBox comboClientpf ;
	private JTextField txttotalmontantHT;
	private JTextField txttotalmontantTVA;
	private JTextField txtreduction;
	private  JCheckBox checkboxGratuits = new JCheckBox("Gratuit");
	 JComboBox comboFamilleGratuit = new JComboBox();
	 JComboBox comboSousFamilleGratuit = new JComboBox();
	 JComboBox comboArticleGratuit = new JComboBox();
	 JLayeredPane layerArticle = new JLayeredPane();
	 JLabel labelSousFamillegratuit = new JLabel("Sous Famille :");
	 JLabel labelcodearticlegratuit = new JLabel("Code Article :");
	 JLabel labelfamillearticlegratuit = new JLabel("Famille Article :");
	 JLabel labelarticlegratuit = new JLabel("Libelle :");
	 JLabel labelquantitegratuit = new JLabel("Quantite :");
	  JLabel lblPrix = new JLabel("Prix  :");
	  JLabel lblMontant = new JLabel("Montant  :");
	  JLabel lblReduction = new JLabel("Remise :");
	  JLabel labelpourcentage = new JLabel("%");
	  private FactureVenteMPDAO factureVenteMPdao;
		private FacturePFDAO factureVentePFdao;
		private JTextField txtquantitepacket;
		  JCheckBox checkttc = new JCheckBox("TTC");
		  JLabel labelmarge = new JLabel("marge");
		  private JLabel stockdisponible;
		  JLabel labelprixmin = new JLabel("New label");
		  JLabel labelPrixMax = new JLabel("New label");
		  BigDecimal prixTTC=BigDecimal.ZERO;
		  BigDecimal StockFinale=BigDecimal.ZERO;
		  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
		  BigDecimal stockfinaleTherres=BigDecimal.ZERO;
		  BigDecimal stockfinaleVerres=BigDecimal.ZERO;
		  BigDecimal stockfinaleArticlePromo=BigDecimal.ZERO;
		  private JTextField txtquantitetherres;
		  private JTextField txtquantiteverres;
		  JLabel lblOffreTherre = new JLabel("Offre Therre :");
		  JComboBox comboBoxtherres = new JComboBox();
		  JLabel lbloffreverres = new JLabel("Offre Verres :");
		  JComboBox comboBoxverres = new JComboBox();
		  JLabel stockdisponibleoffre = new JLabel("");
		  private JLabel stockdisponibleoffretherres;
		  JLabel stockdisponibleoffreverres = new JLabel("");
		  private JTextField txtquantitepromo;
		  JComboBox comboBoxPromo = new JComboBox();
		  JLabel lblOffrePromo = new JLabel("Offre Promo :");
		  JLabel stockdisponiblearticlepromo = new JLabel("");
		  private JTextField txtnumFacture;
		 
		  private JTextField txttimbre;
		  private JTextField txtnetapayer;
		  private JTextField txtespece;
		  private JTextField txtvirement;
		  private JTextField txtcheque;
		  private JTextField txttraite;
		  private JTextField txtnumtraite;
		  private JTextField txtversement;
		  private JTextField txtnumversement;
		  private JTextField txtnumcheque;
		  JCheckBox checkVersement = new JCheckBox("versement");
		  JCheckBox checkTraite = new JCheckBox("Traite");
		 JCheckBox checkCheque = new JCheckBox("Ch\u00E9que");
		 JCheckBox checkVirement = new JCheckBox("Virement");
		JCheckBox checkEspece = new JCheckBox("Esp\u00E8ce");
		JComboBox comboBlTransferer = new JComboBox();
		JButton btnInitialiserfacture = new JButton("Initialiser");
		JCheckBox chckbxLesBlTransferer = new JCheckBox("Les BL Transferer");
		private JCheckBox checkSansmodepaiement;
		JCheckBox chckbxSansTimbre = new JCheckBox("Sans Timbre");
		private JTextField txtnumCommande= new JTextField();
		JComboBox comboTypeBl = new JComboBox();
		JDateChooser dateChooserBCommande = new JDateChooser();
		JComboBox comboAdresse = new JComboBox();
		JCheckBox chckbxPiece = new JCheckBox("Piece");
		private TypeBLDAO typeBLDAO;
		private List<TypeBL> listTypeBL =new ArrayList<TypeBL>();
		private Map< String, TypeBL> mapTypeBL= new HashMap<>();
		 JCheckBox chckbxImprimerNumBc = new JCheckBox("Imprimer Num BC");
		 JCheckBox chckbxImprimerNumBl = new JCheckBox("Imprimer Num BL");
		 JComboBox comboEnTete = new JComboBox();
		 JLabel lblEnTete = new JLabel("En Tete :");
		 
		 private JCheckBox checkEnTete= new JCheckBox("Avec En Tete");
		 private  List<EnTete>listEntet=new ArrayList<EnTete>();
		private EnTeteDAO enTeteDAO;
		 private Map< String, EnTete> mapEntete= new HashMap<>();
		 JCheckBox chckbxImprimerBl = new JCheckBox("Imprimer  BL");
		 
		 private List<AdresseClientPF> listAdresseClient =new ArrayList<AdresseClientPF>();
		    AdresseClientPFDAO adresseClientPFDAO; 
		 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutHistoriqueVenteVendeurPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1748, 959);
      
	
        try{ 
        	
        	 
        	 
      //  facturePF=new FacturePF();
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
        	
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	historiqueVenteVendeurDAO=new HistoriqueVenteVendeurDAOImpl();
         	
         	parametredao=new ParametreDAOImpl();
         	detailHistoriqueVenteVendeurDAO=new DetailHistoriqueVenteVendeurDAOImpl();
         	clientpfdao=new ClientPFDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	fournisseurdao=new ClientDAOImpl();
         	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
         	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
         	factureVentePFdao=new FacturePFDAOImpl();
         	factureVenteMPdao=new FactureVenteMPDAOImpl();
         	typeBLDAO=new TypeBLDAOImpl();
        	listTypeBL=typeBLDAO.findAll();
        	enTeteDAO=new EnTeteDAOImpl();
        	adresseClientPFDAO=new AdresseClientDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		if(tableArticle.getSelectedRow()!=-1)
       		{
       			
           		Articles articleOffre=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
           		if(articleOffre!=null)
           		{
            		if(!articleOffre.getCodeArticle().equals(Constantes.OFFRE_THERRES) && !articleOffre.getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().contains("_PFO"))
               		{
               			
               	   		
                   		if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()).setScale(6,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,RoundingMode.HALF_UP)) && new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 6).toString()).setScale(6,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,RoundingMode.HALF_UP)))
                   		{
                   			boolean trouve=false;
                   			checkboxGratuits.setSelected(true);
            	            labelpourcentage.setVisible(false);
            		 		txtPrix.setVisible(false);
            		 		txtreduction.setVisible(false);
            		 		txtmontant.setVisible(false);
            		 		lblMontant.setVisible(false);
            		 		lblReduction.setVisible(false);	
            		 		lblPrix.setVisible(false);	
            		 		checkttc.setVisible(false);
                   			
                   			
                   			combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
                       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
                       		Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		checkttc.setSelected(false);
                       		
                       		if(article.getCentreCout2()!=null)
                       		{
                       			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
                       			{
                       				trouve=true;
                       			}
                       			
                       		}
                       		
                       		
                       		if(trouve==true)
                       		{
                       			if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).compareTo(article.getConditionnement())<0)
                       			{
                       				txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
                       			}else
                       			{
                       				txtquantitepacket.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).divide(article.getConditionnement(),6,RoundingMode.HALF_UP)+"");
                       				String part[]=txtquantitepacket.getText().split("\\.");
                       				txtquantitepacket.setText(part[0]);
                       				txtquantite.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).subtract(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
                       			}
                       		}else
                       		{
                       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());	
                       		}
                       		
                       		
                       		btnAjouter.setEnabled(false);
                   			
                   		}else
                   		{
                   			boolean offre1=false , offre2=false;
                   			
                   			boolean trouve=false;
                   			checkboxGratuits.setSelected(false);
                   			labelpourcentage.setVisible(false);
            		 		txtPrix.setVisible(true);
            		 		txtreduction.setVisible(true);
            		 		txtmontant.setVisible(true);
            		 		lblMontant.setVisible(true);
            		 		lblReduction.setVisible(true);	
            		 		lblPrix.setVisible(true);	
            		 		checkttc.setVisible(true);
            		 		
                   			Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                   			combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
                       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
                       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
                       		checkttc.setSelected(false);
                       		
                       		
                       		
                       	// Traitement article Promo///////////////////////////
        			 			
        			 			if(article.getCentreCout5()!=null)
        			 			{
        			 			// centrecout5 = article promotion ou non
        			 				if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
        			 				{
        			 					if(tableArticle.getRowCount()-1>=tableArticle.getSelectedRow()+1)
        			 							{
        			 						
        			 						Articles articlesTherres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+1, 2).toString());
        			 						Articles articlesVerres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+1, 2).toString());
        			 						
        			 						if(articlesTherres!=null)
        			 						{
        			 							
        			 						if(articlesTherres.getCodeArticle().equals(Constantes.OFFRE_THERRES))
        			 						{
        			 							offre1=true;
        			 							txtquantitetherres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+1, 4).toString());	
        			 						}
        			 						
        			 						
        			 						
        			 							
        			 						}
        			 						
        			 						if(articlesVerres!=null)
        			 						{
        			 							
        			 						if(articlesVerres.getCodeArticle().equals(Constantes.OFFRE_VERRES))
        			 						{
        			 							offre1=true;
        			 							txtquantiteverres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+1, 4).toString());	
        			 						}
        			 						
        			 						}
        			 						
        			 						
        			 						
        			 						Articles articlesPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle();
        			 						if(articlesPromo.getCodeArticle().contains("_PFO") && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_VERRES) && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_THERRES))
        			 						{
        			 							offre1=true;
        			 							txtquantitepromo.setText(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite().toString());
        			 						}
        			 						        			 							
        			 						}
        			 					if(offre1==true)
        			 					{
        			 						
        			 						if(tableArticle.getRowCount()-1>=tableArticle.getSelectedRow()+2)
    			 							{
    			 						
    			 						Articles articlesTherres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+2, 2).toString());
    			 						Articles articlesVerres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+2, 2).toString());
    			 						
    			 						if(articlesTherres!=null)
    			 						{
    			 							
    			 						if(articlesTherres.getCodeArticle().equals(Constantes.OFFRE_THERRES))
    			 						{
    			 							offre2=true;
    			 							txtquantitetherres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+2, 4).toString());	
    			 						}
    			 						
    			 						
    			 						
    			 							
    			 						}
    			 						
    			 						if(articlesVerres!=null)
    			 						{
    			 							
    			 						if(articlesVerres.getCodeArticle().equals(Constantes.OFFRE_VERRES))
    			 						{
    			 							offre2=true;
    			 							txtquantiteverres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+2, 4).toString());	
    			 						}
    			 						
    			 						}
    			 						
    			 						
    			 						Articles articlesPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle();
    			 						if(articlesPromo.getCodeArticle().contains("_PFO") && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_VERRES) && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_THERRES))
    			 						{
    			 							offre2=true;
    			 							txtquantitepromo.setText(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite().toString());
    			 						}
    			 						
    			 						
    			 						
    			 						
    			 						
    			 							}
        			 					}
        			 					
        			 				
        			 					
        			 					
        			 					if(offre1==true && offre2==true)
        			 					{
        			 						
        			 						
        			 						if(tableArticle.getRowCount()-1>=tableArticle.getSelectedRow()+3)
    			 							{
    			 						
    			 						Articles articlesTherres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+3, 2).toString());
    			 						Articles articlesVerres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+3, 2).toString());
    			 						
    			 						if(articlesTherres!=null)
    			 						{
    			 							
    			 						if(articlesTherres.getCodeArticle().equals(Constantes.OFFRE_THERRES))
    			 						{
    			 							txtquantitetherres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+3, 4).toString());	
    			 						}
    			 						
    			 						}
    			 						
    			 						if(articlesVerres!=null)
    			 						{
    			 							
    			 						if(articlesVerres.getCodeArticle().equals(Constantes.OFFRE_VERRES))
    			 						{
    			 							txtquantiteverres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+3, 4).toString());	
    			 						}
    			 						
    			 						}
    			 						
    			 						
    			 						Articles articlesPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle();
    			 						if(articlesPromo.getCodeArticle().contains("_PFO") && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_VERRES) && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_THERRES))
    			 						{
    			 							txtquantitepromo.setText(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite().toString());
    			 						}
    			 						
    			 						}
        			 						
        			 						
        			 					}
        			 					
        			 					
        			 		
        			 					
        			 					
        			 				}else
        			 				{
        			 					txtquantitetherres.setText("");	
        		 						txtquantiteverres.setText("");	
        		 						txtquantitepromo.setText("");
        			 				}
        			 				
        			 			}else
        			 			{
        			 				
        			 				txtquantitetherres.setText("");	
        	 						txtquantiteverres.setText("");	
        	 						txtquantitepromo.setText("");
        			 			}
                       		
                       		
                       		if(article.getCentreCout2()!=null)
                       		{
                       			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
                       			{
                       				trouve=true;
                       			}
                       		
                       		}
                       	
                       		if(trouve==true)
                       		{
                       			if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).compareTo(article.getConditionnement())<0)
                       			{
                       				txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
                       			}else
                       			{
                       				txtquantitepacket.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).divide(article.getConditionnement(),6,RoundingMode.HALF_UP)+"");
                       				
                       				String[] part=txtquantitepacket.getText().split("\\.");
                       				
                       				txtquantitepacket.setText(part[0]);
                       				txtquantite.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).subtract(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
                       			}
                       		}else
                       		{
                       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());	
                       		}
                       		
                       		if(trouve==true)
                       		{
                       			txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
                           		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
                           		txtmontant.setText((new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()))).add(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()).multiply(new BigDecimal(txtPrix.getText())))+"");
                           		
                       		}else
                       		{
                       			txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
                           		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
                           		txtmontant.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 6).toString());
                           		
                       		}
                       		
                     
                       		btnAjouter.setEnabled(false);	
                   			
                   		}
                   		
                   		
            if(!txtquantite.getText().equals(""))
            {
            	if(new BigDecimal(txtquantite.getText()).equals(BigDecimal.ZERO))
            		{
            			txtquantite.setText("");
            		}
            }
                   		
            	
            if(!txtquantitepacket.getText().equals(""))
            {
            	if(new BigDecimal(txtquantitepacket.getText()).equals(BigDecimal.ZERO))
            		{
            			txtquantitepacket.setText("");
            		}	
            }
                   		
               			
               		}
           		}
       
        
           		
           		 		
       			
       		}
       		
       		
       		
       		
       	}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction","Montant HT", "Montant TVA", "Montant TTC"
       	}
       ));
				  		
       tableArticle.setShowVerticalLines(false);
       tableArticle.setSelectionBackground(new Color(51, 204, 255));
       tableArticle.setRowHeightEnabled(true);
       tableArticle.setBackground(new Color(255, 255, 255));
       tableArticle.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableArticle.setColumnControlVisible(true);
       tableArticle.setForeground(Color.BLACK);
       tableArticle.setGridColor(new Color(0, 0, 255));
       tableArticle.setAutoCreateRowSorter(true);
       tableArticle.setBounds(2, 27, 411, 198);
       tableArticle.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableArticle);
				  		     	scrollPane.setBounds(6, 491, 1031, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(6, 450, 1027, 30);
				  		     	add(titledSeparator);
		       
		    
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


                 BigDecimal solde = BigDecimal.ZERO;
                 SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
                 String date = dcn.format(dateChooserfacture.getDate());
              
                if (txtnumFacture.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer le num de Fcature SVP !!!!!", "Erreur", 0);
                    return;
                }
                
                if (dateChooserfacture.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!", "Erreur", 0);
                    return;
                }
                if (combodepot.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!", "Erreur", 0);
                    return;
                }
                if (combomagasin.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!", "Erreur", 0);
                    return;
                }
                if (comboFournisseur.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir le Fournisseur SVP !!!!!", "Erreur", 0);
                    return;
                }
                if (comboClientpf.getSelectedIndex() == -1 || comboClientpf.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir le Client SVP !!!!!", "Erreur", 0);
                    return;
                }
                
                
                if(chckbxLesBlTransferer.isSelected()==false)
                {
                	   if (listDetailFacturePF.size() == 0) {
                           JOptionPane.showMessageDialog(null, "Veuillez entrer les articles \u00e0 factur\u00e9 SVP !!!!!", "Erreur", 0);
                           return;
                       }
                }
             
                 Depot depot = mapDepot.get(combodepot.getSelectedItem());
                 Magasin magasin = mapMagasin.get(combomagasin.getSelectedItem());
                 HistoriqueVentevendeur factureventetPFNumFacture = historiqueVenteVendeurDAO.findByNumFacture(txtnumFacture.getText(), depot);
                 HistoriqueVentevendeur factureventetPF = historiqueVenteVendeurDAO.findFactureVentePFByNumBLByDepot (txtnumBL.getText(),depot);
                if (factureventetPF != null || factureventetPFNumFacture != null) {
                    JOptionPane.showMessageDialog(null, "Num BL ou Num Facture existe d\u00e9ja ", "Erreur", 0);
                    return;
                }
                
             
                
                if(chckbxLesBlTransferer.isSelected()==true)
                {
                	
                	if(comboBlTransferer.getSelectedItem().equals(""))
                	{
                		
                		JOptionPane.showMessageDialog(null, "Veuillez Selectionner le BL Transferer SVP ", "Erreur", 0);
                        return;
                		
                	}
                	
                	
                	
                }else
                {
                	
                	
                	  if (txtnumBL.getText().equals("")) {
                          JOptionPane.showMessageDialog(null, "Erreur Num BL !!!!!", "Erreur", 0);
                          return;
                      }
                	
                	
                }
                
                
                
                
                
                
                
      /////////////////////////////////////////////////////////////////// Traitement Mode Reglement ////////////////////////////////////////////////////////////          
            
                String ModePaiement = "";
                boolean existe=false;   
                BigDecimal montantcheque=BigDecimal.ZERO;  
                BigDecimal montantespece=BigDecimal.ZERO;   
                BigDecimal montantvirement=BigDecimal.ZERO;  
                BigDecimal montanttraite=BigDecimal.ZERO;   
                BigDecimal montantversement=BigDecimal.ZERO;  
                String numcheque="";
                String numversement="";
                String numtraite="";
                String modereglement="";
                String numpiece="";
                
                BigDecimal timber = BigDecimal.ZERO;
               
                    
                  
                
                
                
                           if(checkEspece.isSelected()==true) 
                           {
                         	  if(!txtespece.getText().equals(""))
                         	  {
                         		  montantespece=new BigDecimal(txtespece.getText());
                         		  
                         		  modereglement=Constantes.MODE_REGLEMENT_ESPECE;
                         		  
                         		 existe=true;
                         		 
                         		 
                                timber = timber.add(Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).multiply(montantespece)) ;
                         		 
                                ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_ESPECE;
                         		  
                         	  }
                         	 
                           }
                              
                           if(checkCheque.isSelected()==true) 
                           {
                         	  if(!txtcheque.getText().equals(""))
                         	  {
                         		  montantcheque=new BigDecimal(txtcheque.getText());
                         		  
                         		  if(!txtnumcheque.getText().equals(""))
                         		  {
                         			  numcheque=txtnumcheque.getText();
                         			 numpiece=numpiece+" Cheque N° : "+numcheque;
                         		  }
                         		  
                         		  modereglement=Constantes.MODE_REGLEMENT_CHEQUE;
                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_CHEQUE;
                         		
                         		 
                         		 existe=true;
                         	  }
                         	 
                           } 
                              
                           if(checkVirement.isSelected()==true) 
                           {
                         	  if(!txtvirement.getText().equals(""))
                         	  {
                         		  montantvirement=new BigDecimal(txtvirement.getText());
                         		  modereglement=Constantes.MODE_REGLEMENT_VIREMENT;
                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_VIREMENT;
                         		 existe=true;
                         	  }
                         	 
                           } 
                           
                           
                           if(checkVersement.isSelected()==true) 
                           {
                         	  if(!txtversement.getText().equals(""))
                         	  {
                         		  montantversement=new BigDecimal(txtversement.getText());
                         		  modereglement=Constantes.MODE_REGLEMENT_VERSEMENT;
                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_VERSEMENT;
                         		  if(!txtnumversement.getText().equals(""))
                         		  {
                         			  numversement=txtnumversement.getText();
                         			  
                         			 numpiece=numpiece+" Versement N° "+numversement;
                         		  }
                         		  
                         		 existe=true;
                         		 
                         		 
                                 timber = timber.add(Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).multiply(montantversement)) ;

                         		  
                         	  }
                         	 
                           }
                           
                           if(checkTraite.isSelected()==true) 
                           {
                         	  if(!txttraite.getText().equals(""))
                         	  {
                         		  montanttraite=new BigDecimal(txttraite.getText());
                         		  modereglement=Constantes.MODE_REGLEMENT_TRAITE;
                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_TRAITE;
                         		  
                         		  if(!txtnumtraite.getText().equals(""))
                         		  {
                         			  numtraite =txtnumtraite.getText();
                         			 numpiece=numpiece+" Traite N° : "+numtraite;
                         		  }
                         		  
                         		 existe=true;
                         		  
                         	  }
                         	 
                           }
                           
                
                if(existe==false)
                {
                
                	JOptionPane.showMessageDialog(null, "Veuillez entrer le reglement SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
                	return;
                	
                }
                
                
                
                
                
                
                
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
                
                
                
                
                
                
                 ClientPF clientPF = mapClientPF.get(comboClientpf.getSelectedItem());
                 Client fournisseur = mapFournisseur.get(comboFournisseur.getSelectedItem());
                 String codeTransfert = Utils.genererCodeTransfer(depot.getCode(), "VENTE");
                 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                 List<DetailHistoriqueVenteVendeur> listDetailFacturePFImprimer =new ArrayList<DetailHistoriqueVenteVendeur>();
     			List<DetailHistoriqueVenteVendeur> listDetailFacturePFImprimerTmp =new ArrayList<DetailHistoriqueVenteVendeur>();
                 
     			boolean trouve=false;
     			 BigDecimal montanttotal=BigDecimal.ZERO;
  		        BigDecimal montanttotalHT=BigDecimal.ZERO;
  		        BigDecimal montanttotalTVA=BigDecimal.ZERO;
  
                 if(chckbxLesBlTransferer.isSelected()==true)
                 {
                 	
                 	if(!comboBlTransferer.getSelectedItem().equals(""))
                 	{
                 		
                 		HistoriqueVentevendeur historiqueVentevendeur=mapHistoriqueVenteVendeur.get(comboBlTransferer.getSelectedItem());
                 		
                 		 
                          historiqueVentevendeur.setNumFacture(txtnumFacture.getText());
                          historiqueVentevendeur.setEtat(Constantes.ETAT_REGLE);
                          historiqueVentevendeur.setType(Constantes.TYPE_FACTURE);
                          historiqueVentevendeur.setMontantHT(new BigDecimal(txttotalmontantHT.getText()).setScale(6, RoundingMode.HALF_UP));
                          historiqueVentevendeur.setMontantTVA(new BigDecimal(txttotalmontantTVA.getText()).setScale(6, RoundingMode.HALF_UP));
                          historiqueVentevendeur.setMontantTTC(new BigDecimal(txttotalmontantTTC.getText()).setScale(6, RoundingMode.HALF_UP));
                          historiqueVentevendeur.setCodeTransfer(codeTransfert);
                          historiqueVentevendeur.setDateSaisi(new Date());
                          historiqueVentevendeur.setEspece(montantespece);
                          historiqueVentevendeur.setCheque(montantcheque);
                          historiqueVentevendeur.setVirement(montantvirement);
                          historiqueVentevendeur.setTraite(montanttraite);
                          historiqueVentevendeur.setVersement(montantversement);
                          historiqueVentevendeur.setCredit(historiqueVentevendeur.getMontantTTC().subtract(montantespece.add(montantcheque.add(montantvirement.add(montanttraite.add(montantversement))))));
                          historiqueVentevendeur.setModeReglement(modereglement);
                      	if(!numcheque.equals(""))
          	   			{
                      		historiqueVentevendeur.setNumCheque(numcheque);
          	   			}else if(!numtraite.equals(""))
          	   			{
          	   				historiqueVentevendeur.setNumtraite(numtraite);
          	   			}else if(!numversement.equals(""))
          	   			{
          	   				historiqueVentevendeur.setNumVersement(numversement);
          	   			}
                          historiqueVentevendeur.setCreerPar(utilisateur);
                          historiqueVenteVendeurDAO.edit(historiqueVentevendeur);
                 		
                 		
                 		
                 		
                          BigDecimal quantitePieceCalculer=BigDecimal.ZERO; 
           		       BigDecimal prixPieceCalculer=BigDecimal.ZERO; 
           		       BigDecimal MontantHTPieceCalculer=BigDecimal.ZERO; 
           		       BigDecimal MontantTVAPieceCalculer=BigDecimal.ZERO; 
           		       BigDecimal MontantTTCPieceCalculer=BigDecimal.ZERO; 
                 		
                 		
                    	 for(int i=0;i<listDetailFacturePFBlTransferer.size();i++)
            			 {
                    		 
                    		 
                    		 quantitePieceCalculer=BigDecimal.ZERO; 
					         prixPieceCalculer=BigDecimal.ZERO; 
					         MontantHTPieceCalculer=BigDecimal.ZERO; 
					         MontantTVAPieceCalculer=BigDecimal.ZERO; 
					         MontantTTCPieceCalculer=BigDecimal.ZERO; 
                    		 
            				 trouve=false;
            				 
            				 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
            				 {
            					if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePFBlTransferer.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePFBlTransferer.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
            					{
            						
            						
            						
            						trouve=true;
            						
            						if(listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
									{
										if(listDetailFacturePFImprimer.get(j).getHistoriqueventevendeur().getImprimerPiece()==true)
										{
											
											 quantitePieceCalculer=listDetailFacturePFBlTransferer.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFBlTransferer.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
									         prixPieceCalculer=listDetailFacturePFBlTransferer.get(i).getPrixUnitaire().divide(listDetailFacturePFBlTransferer.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
									         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
									        if(listDetailFacturePFBlTransferer.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
									        {
									        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
									        }
									         
									         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
											
									         listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(quantitePieceCalculer));
			            						listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(MontantHTPieceCalculer));
			            						listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

			            						listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(MontantTVAPieceCalculer));
			            						listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(MontantTTCPieceCalculer));
			            						listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFBlTransferer.get(i).getReduction()));			
			            						listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
											
											
											
											
											
											
											 
											 
											
										}else
										{
											listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFBlTransferer.get(i).getQuantite()));
		            						listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFBlTransferer.get(i).getMontantHT()));
		            						listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

		            						listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFBlTransferer.get(i).getMontantTVA()));
		            						listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFBlTransferer.get(i).getMontantTTC()));
		            						listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFBlTransferer.get(i).getReduction()));			
		            						listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
										}
										
										
									}else
									{
										
										listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFBlTransferer.get(i).getQuantite()));
	            						listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFBlTransferer.get(i).getMontantHT()));
	            						listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

	            						listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFBlTransferer.get(i).getMontantTVA()));
	            						listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFBlTransferer.get(i).getMontantTTC()));
	            						listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFBlTransferer.get(i).getReduction()));			
	            						listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
										
										
									}
            						
            						
            						
            						
            						
            						
            					}
            					
            					
            				 }
            				 if(trouve==false)
            				 {
            					 
            					 
            						if(listDetailFacturePFBlTransferer.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFBlTransferer.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
									{
										if(listDetailFacturePFBlTransferer.get(i).getHistoriqueventevendeur().getImprimerPiece()==true)
										{
											
											 quantitePieceCalculer=listDetailFacturePFBlTransferer.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFBlTransferer.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
									         prixPieceCalculer=listDetailFacturePFBlTransferer.get(i).getPrixUnitaire().divide(listDetailFacturePFBlTransferer.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
									         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
									        if(listDetailFacturePFBlTransferer.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
									        {
									        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
									        }
									         
									         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
									         
									         
									         listDetailFacturePFBlTransferer.get(i).setQuantite(quantitePieceCalculer);
									         listDetailFacturePFBlTransferer.get(i).setPrixUnitaire(prixPieceCalculer);
									         listDetailFacturePFBlTransferer.get(i).setMontantHT(MontantHTPieceCalculer);
									         listDetailFacturePFBlTransferer.get(i).setMontantTVA(MontantTVAPieceCalculer);
									         listDetailFacturePFBlTransferer.get(i).setMontantTTC(MontantTTCPieceCalculer);
									         listDetailFacturePFImprimer.add(listDetailFacturePFBlTransferer.get(i));
											
										}else
										{
											listDetailFacturePFImprimer.add(listDetailFacturePFBlTransferer.get(i)); 
										}
										
									}else
									{
										listDetailFacturePFImprimer.add(listDetailFacturePFBlTransferer.get(i)); 
									}
            					 
            				 }
            				  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFBlTransferer.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
            		          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFBlTransferer.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
            		          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFBlTransferer.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
            				 
            			 }
            			 
            			 if(listDetailFacturePFImprimer.size()!=0)
            			 {
            				 
            				    montanttotal=BigDecimal.ZERO;
            			         montanttotalHT=BigDecimal.ZERO;
            			         montanttotalTVA=BigDecimal.ZERO;
            				 
            				 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
            				 {
            					 
            					 quantitePieceCalculer=BigDecimal.ZERO; 
						         prixPieceCalculer=BigDecimal.ZERO; 
						         MontantHTPieceCalculer=BigDecimal.ZERO; 
						         MontantTVAPieceCalculer=BigDecimal.ZERO; 
						         MontantTTCPieceCalculer=BigDecimal.ZERO;  
            					 
            					 trouve=false;
            					 
            					 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
            					 {
            						if(listDetailFacturePFImprimerTmp.get(j).getArticle().equals(listDetailFacturePFImprimer.get(i).getArticle()) && listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2)) && listDetailFacturePFImprimer.get(i).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2))) 
            						{
            							trouve=true;
            							
            							
            							if(listDetailFacturePFImprimerTmp.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFImprimerTmp.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
										{
											if(listDetailFacturePFImprimerTmp.get(j).getHistoriqueventevendeur().getImprimerPiece()==true)
											{
												 
												 quantitePieceCalculer=listDetailFacturePFImprimer.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFImprimer.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
										         prixPieceCalculer=listDetailFacturePFImprimer.get(i).getPrixUnitaire().divide(listDetailFacturePFImprimer.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
										         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
										        if(listDetailFacturePFImprimer.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
										        {
										        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
										        }
										         
										         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
												
												
										            listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(quantitePieceCalculer));
													listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(prixPieceCalculer)).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
													listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(MontantHTPieceCalculer));
													listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(MontantTVAPieceCalculer));
													listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(MontantTTCPieceCalculer));
													listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction().add(listDetailFacturePFImprimer.get(i).getReduction()));			
													listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));  
										         
										         
										         
												
												
												
											}else
											{
												listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
		            							listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
		            							listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
		            							listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
		            							listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
		            							listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction().add(listDetailFacturePFImprimer.get(i).getReduction()));			
		            							listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));
											}
											
											
										}else
										{
											listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
	            							listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
	            							listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
	            							listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
	            							listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
	            							listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction().add(listDetailFacturePFImprimer.get(i).getReduction()));			
	            							listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));
										}
            							
            							
            							
            							
            							
            						}
            						
            						
            					 }
            					 if(trouve==false)
            					 {
            						 if(listDetailFacturePFImprimer.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFImprimer.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
										{
										 
										 if(listDetailFacturePFImprimer.get(i).getHistoriqueventevendeur().getImprimerPiece()==true)
											{
											 
											 
											 quantitePieceCalculer=listDetailFacturePFImprimer.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFImprimer.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
									         prixPieceCalculer=listDetailFacturePFImprimer.get(i).getPrixUnitaire().divide(listDetailFacturePFImprimer.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
									         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
									        if(listDetailFacturePFImprimer.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
									        {
									        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
									        }
									         
									         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
											 
									         
									         listDetailFacturePFImprimer.get(i).setQuantite(quantitePieceCalculer);
									         listDetailFacturePFImprimer.get(i).setPrixUnitaire(prixPieceCalculer);
									         listDetailFacturePFImprimer.get(i).setMontantHT(MontantHTPieceCalculer);
									         listDetailFacturePFImprimer.get(i).setMontantTVA(MontantTVAPieceCalculer);
									         listDetailFacturePFImprimer.get(i).setMontantTTC(MontantTTCPieceCalculer);
											 
									         listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
											 
											 
											 
											 
											}else
											{
												
												 listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
											}
										 
										 
										}else
										{
											
											listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
										}
            						 
            						 
            					 }
            					  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTTC().setScale( 6, RoundingMode.DOWN)); 
            			          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
            			          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
            					 
            				 }  
            			 }	
                 		
                 	}
                 	
                 	
                 	
                 }else
                 {
                 	
                	  historiqueVentevendeur.setNumBl(txtnumBL.getText());
                      historiqueVentevendeur.setNumFacture(txtnumFacture.getText());
                      historiqueVentevendeur.setClientPF(clientPF);
                      historiqueVentevendeur.setFournisseur(fournisseur);
                      historiqueVentevendeur.setDepot(depot);
                      historiqueVentevendeur.setMagasin(magasin);
                      historiqueVentevendeur.setDateBl(dateChooserfacture.getDate());
                      historiqueVentevendeur.setDateFacture(dateChooserfacture.getDate());
                      historiqueVentevendeur.setEtat(Constantes.ETAT_REGLE);
                      historiqueVentevendeur.setType(Constantes.TYPE_FACTURE);
                      historiqueVentevendeur.setMontantHT(new BigDecimal(txttotalmontantHT.getText()).setScale(6, RoundingMode.HALF_UP));
                      historiqueVentevendeur.setMontantTVA(new BigDecimal(txttotalmontantTVA.getText()).setScale(6, RoundingMode.HALF_UP));
                      historiqueVentevendeur.setMontantTTC(new BigDecimal(txttotalmontantTTC.getText()).setScale(6, RoundingMode.HALF_UP));
                      historiqueVentevendeur.setCodeTransfer(codeTransfert);
                      historiqueVentevendeur.setDateSaisi(new Date());
                      historiqueVentevendeur.setEspece(montantespece);
                      historiqueVentevendeur.setCheque(montantcheque);
                      historiqueVentevendeur.setVirement(montantvirement);
                      historiqueVentevendeur.setTraite(montanttraite);
                      historiqueVentevendeur.setVersement(montantversement);
                      historiqueVentevendeur.setCredit(historiqueVentevendeur.getMontantTTC().subtract(montantespece.add(montantcheque.add(montantvirement.add(montanttraite.add(montantversement))))));
                      historiqueVentevendeur.setModeReglement(modereglement);
                      
                      historiqueVentevendeur.setImprimerPiece(chckbxPiece.isSelected());
                      historiqueVentevendeur.setNumCommande(txtnumCommande.getText());
                      historiqueVentevendeur.setDateBCommande(dateChooserBCommande.getDate());
                      if(chckbxImprimerNumBl.isSelected()==true)
                      {
                    	  historiqueVentevendeur.setImprimerAvecNumBl(Constantes.CODE_OUI);
                      }else
                      {
                    	  historiqueVentevendeur.setImprimerAvecNumBl(Constantes.CODE_NON);
                      }
                     
                      if(chckbxImprimerNumBc.isSelected()==true)
                      {
                    	  historiqueVentevendeur.setImprimerAvecNumCommande (Constantes.CODE_OUI);
                      }else
                      {
                    	  historiqueVentevendeur.setImprimerAvecNumCommande(Constantes.CODE_NON);
                      }
        			
        			if(comboAdresse.getSelectedIndex()!=-1)
        			{
        				if(!comboAdresse.getSelectedItem().equals(""))
        				{
        					historiqueVentevendeur.setAdresseClient(comboAdresse.getSelectedItem().toString());
        				}else
        				{
        					historiqueVentevendeur.setAdresseClient(clientPF.getAdresse());
        				}
        				
        				
        				
        			}else
        			{
        				historiqueVentevendeur.setAdresseClient(clientPF.getAdresse());
        			}
        			
        			if(comboTypeBl.getSelectedIndex()!=-1)
        			{
        				if(!comboTypeBl.getSelectedItem().equals(""))
        				{
        					
        					TypeBL typeBL=mapTypeBL.get(comboTypeBl.getSelectedItem().toString());
        					if(typeBL!=null)
        					{
        						
        						historiqueVentevendeur.setTypeBL(typeBL);
        						
        					}
        					
        					
        				}
        			} 
                      
                      
                      
                  	if(!numcheque.equals(""))
      	   			{
                  		historiqueVentevendeur.setNumCheque(numcheque);
      	   			}else if(!numtraite.equals(""))
      	   			{
      	   				historiqueVentevendeur.setNumtraite(numtraite);
      	   			}else if(!numversement.equals(""))
      	   			{
      	   				historiqueVentevendeur.setNumVersement(numversement);
      	   			}
                      historiqueVentevendeur.setCreerPar(utilisateur);
                      historiqueVenteVendeurDAO.add(historiqueVentevendeur);
                      for (int i = 0; i < listDetailFacturePF.size(); ++i) {
                          detailHistoriqueVenteVendeurDAO.add(listDetailFacturePF.get(i));
                      }
                      
                      BigDecimal quantitePieceCalculer=BigDecimal.ZERO; 
       		       BigDecimal prixPieceCalculer=BigDecimal.ZERO; 
       		       BigDecimal MontantHTPieceCalculer=BigDecimal.ZERO; 
       		       BigDecimal MontantTVAPieceCalculer=BigDecimal.ZERO; 
       		       BigDecimal MontantTTCPieceCalculer=BigDecimal.ZERO; 
                      
                      
                  	 for(int i=0;i<listDetailFacturePF.size();i++)
        			 {
                  		 
                  		 quantitePieceCalculer=BigDecimal.ZERO; 
				         prixPieceCalculer=BigDecimal.ZERO; 
				         MontantHTPieceCalculer=BigDecimal.ZERO; 
				         MontantTVAPieceCalculer=BigDecimal.ZERO; 
				         MontantTTCPieceCalculer=BigDecimal.ZERO; 
        				 trouve=false;
        				 
        				 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
        				 {
        					if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
        					{
        						
        						
        						trouve=true;
        						
        						if(listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
								{
									if(listDetailFacturePFImprimer.get(j).getHistoriqueventevendeur().getImprimerPiece()==true)
									{
										
										 quantitePieceCalculer=listDetailFacturePF.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePF.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
								         prixPieceCalculer=listDetailFacturePF.get(i).getPrixUnitaire().divide(listDetailFacturePF.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
								         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
								        if(listDetailFacturePF.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
								        {
								        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
								        }
								         
								         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
										
								         listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(quantitePieceCalculer));
			        						listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(MontantHTPieceCalculer));
			        						listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

			        						listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(MontantTVAPieceCalculer));
			        						listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(MontantTTCPieceCalculer));
			        						listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
			        						listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
										
										
										
										
										
										 
										 
										
									}else
									{
										listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
		        						listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
		        						listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

		        						listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
		        						listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
		        						listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
		        						listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
									}
									
									
								}else
								{
									
									listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
	        						listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
	        						listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

	        						listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
	        						listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
	        						listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
	        						listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
								}
        						
        						
        						
        						
        						
        						
        					}
        					
        					
        				 }
        				 if(trouve==false)
        				 {
        					 
        						if(listDetailFacturePF.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePF.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
								{
									if(listDetailFacturePF.get(i).getHistoriqueventevendeur().getImprimerPiece()==true)
									{
										
										 quantitePieceCalculer=listDetailFacturePF.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePF.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
								         prixPieceCalculer=listDetailFacturePF.get(i).getPrixUnitaire().divide(listDetailFacturePF.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
								         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
								        if(listDetailFacturePF.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
								        {
								        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
								        }
								         
								         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
								         
								         
								         listDetailFacturePF.get(i).setQuantite(quantitePieceCalculer);
								         listDetailFacturePF.get(i).setPrixUnitaire(prixPieceCalculer);
								         listDetailFacturePF.get(i).setMontantHT(MontantHTPieceCalculer);
								         listDetailFacturePF.get(i).setMontantTVA(MontantTVAPieceCalculer);
								         listDetailFacturePF.get(i).setMontantTTC(MontantTTCPieceCalculer);
								         listDetailFacturePFImprimer.add(listDetailFacturePF.get(i));
										
									}else
									{
										listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
									}
									
								}else
								{
									listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
								}
        					 
        				 }
        				  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePF.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
        		          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePF.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
        		          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePF.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
        				 
        			 }
        			 
        			 if(listDetailFacturePFImprimer.size()!=0)
        			 {
        				 
        				    montanttotal=BigDecimal.ZERO;
        			         montanttotalHT=BigDecimal.ZERO;
        			         montanttotalTVA=BigDecimal.ZERO;
        				 
        				 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
        				 {
        					 
        					 quantitePieceCalculer=BigDecimal.ZERO; 
					         prixPieceCalculer=BigDecimal.ZERO; 
					         MontantHTPieceCalculer=BigDecimal.ZERO; 
					         MontantTVAPieceCalculer=BigDecimal.ZERO; 
					         MontantTTCPieceCalculer=BigDecimal.ZERO;  
        					 
        					 trouve=false;
        					 
        					 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
        					 {
        						if(listDetailFacturePFImprimerTmp.get(j).getArticle().equals(listDetailFacturePFImprimer.get(i).getArticle()) && listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2)) && listDetailFacturePFImprimer.get(i).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2))) 
        						{
        							trouve=true;
        							
        							
										
										listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
	        							listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
	        							listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
	        							listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
	        							listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
	        							listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction().add(listDetailFacturePFImprimer.get(i).getReduction()));			
	        							listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));
									
        							
        							
        							
        						}
        						
        						
        					 }
        					 if(trouve==false)
        					 {
        						 
        						 
										listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
								
        						 
        						 
        						 
        					 }
        					  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTTC().setScale( 6, RoundingMode.DOWN)); 
        			          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
        			          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
        					 
        				 }  
        			 }   
                      
                      
                 	
                 	
                 }
              
                 
                 
               
                
              
      
                
			 txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
				
			   txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
		  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
                
                
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                if(chckbxSansTimbre.isSelected()==true)
                {
                    txttimbre.setText(new StringBuilder().append(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)).toString());
	                 
                    txtnetapayer.setText(new StringBuilder().append(new BigDecimal(txttotalmontantTTC.getText()).setScale(2, RoundingMode.HALF_UP)).toString());
  
                	
                }else
                {
                	
                    txttimbre.setText(new StringBuilder().append(timber.setScale(2, RoundingMode.HALF_UP)).toString());
	                 
                    txtnetapayer.setText(new StringBuilder().append(new BigDecimal(txttotalmontantTTC.getText()).setScale(2, RoundingMode.HALF_UP).add(timber.setScale(2, RoundingMode.HALF_UP))).toString());
  
                	
                	
                }
	  
            
                
                
                
                
                String sommetowords = "";
                  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateFacture = "";
                
            
                
                
                  Map parameters = new HashMap();
                
                
                if(chckbxLesBlTransferer.isSelected()==true)
                {
                	
                	if(!comboBlTransferer.getSelectedItem().equals(""))
                	{
                		
                		HistoriqueVentevendeur historiqueVentevendeur=mapHistoriqueVenteVendeur.get(comboBlTransferer.getSelectedItem());
                		
                		dateFacture = dateFormat.format(historiqueVentevendeur.getDateFacture());

                    	
                    	
                    	 parameters.put("client", historiqueVentevendeur.getClientPF().getNom());
                         parameters.put("NumFacture", historiqueVentevendeur.getNumFacture());
                         parameters.put("adresse", historiqueVentevendeur.getClientPF().getAdresse());
                         parameters.put("code", historiqueVentevendeur.getClientPF().getCode());
                         if (historiqueVentevendeur.getClientPF().getIce() != null) {
                             parameters.put("iceclient", historiqueVentevendeur.getClientPF().getIce());
                         }
                         else {
                             parameters.put("iceclient", "");
                         }
                         
                         
         				if(historiqueVentevendeur.getImprimerAvecNumBl()!=null)
						{
							if(!historiqueVentevendeur.getImprimerAvecNumBl().equals(""))
							{
								
								if(historiqueVentevendeur.getImprimerAvecNumBl().equals(Constantes.CODE_OUI))
								{
									 parameters.put("NumBL", historiqueVentevendeur.getNumBl());
										
										if(historiqueVentevendeur.getDateBl()!=null)
										{
											parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getDateBl()));
										}else
										{
											parameters.put("dateBl", "");
										}
									
									 
								}else if(historiqueVentevendeur.getImprimerAvecNumBl().equals(Constantes.CODE_NON))
								
								{
									 
									 parameters.put("NumBL", "");
										
										 
											parameters.put("dateBl", "");
										 
									
								}
								
								 
								
							}else
							{
								
								if(chckbxImprimerNumBl.isSelected()==true)
								{
									parameters.put("NumBL", historiqueVentevendeur.getNumBl());
									
									if(historiqueVentevendeur.getDateBl()!=null)
									{
										parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getDateBl()));
									}else
									{
										parameters.put("dateBl", "");
									}
									
								}else
								{
									
									 parameters.put("NumBL", "");
										
									 
										parameters.put("dateBl", "");
									 
									
								}
								
								
							}
						}else
						{

							if(chckbxImprimerNumBl.isSelected()==true)
							{
								parameters.put("NumBL", historiqueVentevendeur.getNumBl());
								
								if(historiqueVentevendeur.getDateBl()!=null)
								{
									parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getDateBl()));
								}else
								{
									parameters.put("dateBl", "");
								}
								
							}else
							{
								
								 parameters.put("NumBL", "");
									
								 
									parameters.put("dateBl", "");
								 
								
							}
							
						}
						
						
						
                     
						if(historiqueVentevendeur.getImprimerAvecNumCommande()!=null)
						{
							if(!historiqueVentevendeur.getImprimerAvecNumCommande().equals(""))
							{
								
								if(historiqueVentevendeur.getImprimerAvecNumCommande().equals(Constantes.CODE_OUI))
								{
									if(historiqueVentevendeur.getDateBCommande()!=null)
									{
										parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getDateBCommande()) );
									}else
									{
										parameters.put("dateBC", "");
									}
									if(historiqueVentevendeur.getNumCommande()!=null)
									{
										parameters.put("NumBC", historiqueVentevendeur.getNumCommande());
									}else
									{
										parameters.put("NumBC", "");
									}
									
									 
								}else if(historiqueVentevendeur.getImprimerAvecNumCommande().equals(Constantes.CODE_NON))
								
								{
									 
									 
										parameters.put("dateBC", "");
									 
									 
										parameters.put("NumBC", "");
									 
										 
									
								}
								
								 
								
							}else
							{
								
								if(chckbxImprimerNumBc.isSelected()==true)
								{
									if(historiqueVentevendeur.getDateBCommande()!=null)
									{
										parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getDateBCommande()) );
									}else
									{
										parameters.put("dateBC", "");
									}
									if(historiqueVentevendeur.getNumCommande()!=null)
									{
										parameters.put("NumBC", historiqueVentevendeur.getNumCommande());
									}else
									{
										parameters.put("NumBC", "");
									}
									
								}else
								{
									parameters.put("dateBC", "");
									 
									 
									parameters.put("NumBC", "");
									 
									
								}
								
								
							}
						}else
						{

							if(chckbxImprimerNumBc.isSelected()==true)
							{
							 
								if(historiqueVentevendeur.getDateBCommande()!=null)
								{
									parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getDateBCommande()) );
								}else
								{
									parameters.put("dateBC", "");
								}
								if(historiqueVentevendeur.getNumCommande()!=null)
								{
									parameters.put("NumBC", historiqueVentevendeur.getNumCommande());
								}else
								{
									parameters.put("NumBC", "");
								}
								
							}else
							{
								
								parameters.put("dateBC", "");
								 
								 
								parameters.put("NumBC", "");
								 
								
							}
							
						}
                         
                         
						String datechance="";
						if(historiqueVentevendeur.getClientPF().getDelaiPaiement()!=null)
						{
							if(historiqueVentevendeur.getClientPF().getDelaiPaiement().compareTo(BigDecimal.ZERO)!=0)
							{
								
								if(historiqueVentevendeur.getClientPF().getDelaiPaiementParBlOuFacture()!=null)
								{
									
									if(!historiqueVentevendeur.getClientPF().getDelaiPaiementParBlOuFacture().equals(""))
									{
										if(historiqueVentevendeur.getClientPF().getDelaiPaiementParBlOuFacture().equals(Constantes.TYPE_BON_LIVRAISON))
										{
											parameters.put("delai","Delai de paiement :  "+ historiqueVentevendeur.getClientPF().getDelaiPaiement().intValue()+" jours date de bon livraison");
											
											datechance=dateFormat.format(DateUtils.ajoutNbJours(historiqueVentevendeur.getDateBl(), historiqueVentevendeur.getClientPF().getDelaiPaiement().intValue()));
											
											 
											
											parameters.put("dateechance","Date d'échance     :  "+datechance);
										
										}else if(historiqueVentevendeur.getClientPF().getDelaiPaiementParBlOuFacture().equals(TRANSFERE_BL_FACTURE))
										{
											
											parameters.put("delai","Delai de paiement :  "+ historiqueVentevendeur.getClientPF().getDelaiPaiement().intValue()+" jours date de facture");
										
                                            datechance=dateFormat.format(DateUtils.ajoutNbJours(historiqueVentevendeur.getDateFacture(), historiqueVentevendeur.getClientPF().getDelaiPaiement().intValue()));
											
											parameters.put("dateechance","Date d'échance     :  "+datechance);
											
										 
										}else
										{
											parameters.put("delai","");
											parameters.put("dateechance","");
										}
										
										
										
										
									}else
									{
										parameters.put("delai","");
										parameters.put("dateechance","");
									}
								}else
								{
									parameters.put("delai","");
									parameters.put("dateechance","");
								}
								
								
							}else
							{
								parameters.put("delai","");
								parameters.put("dateechance","");
							}
							
							
						}else
						{
							parameters.put("delai","");
							parameters.put("dateechance","");
						}
                         
                         
						
						if(historiqueVentevendeur.getImprimerAvecNumBl()!=null)
						{
							if(historiqueVentevendeur.getImprimerAvecNumBl().equals(""))
							{
								
								if(chckbxImprimerNumBl.isSelected()==true)
								{
									historiqueVentevendeur.setImprimerAvecNumBl(Constantes.CODE_OUI);
								}else
								{
									historiqueVentevendeur.setImprimerAvecNumBl(Constantes.CODE_NON);
								}
								
								historiqueVenteVendeurDAO.edit(historiqueVentevendeur);
								
							}
						}else
						{
							if(chckbxImprimerNumBl.isSelected()==true)
							{
								historiqueVentevendeur.setImprimerAvecNumBl(Constantes.CODE_OUI);
							}else
							{
								historiqueVentevendeur.setImprimerAvecNumBl(Constantes.CODE_NON);
							}
							
							historiqueVenteVendeurDAO.edit(historiqueVentevendeur);
							
						}
						
						
						if(historiqueVentevendeur.getImprimerAvecNumCommande()!=null)
						{
							if(historiqueVentevendeur.getImprimerAvecNumCommande().equals(""))
							{
								
								if(chckbxImprimerNumBc.isSelected()==true)
								{
									historiqueVentevendeur.setImprimerAvecNumCommande(Constantes.CODE_OUI);
								}else
								{
									historiqueVentevendeur.setImprimerAvecNumCommande(Constantes.CODE_NON);
									 
								}
								historiqueVenteVendeurDAO.edit(historiqueVentevendeur);
							}
						}else
						{
							if(chckbxImprimerNumBc.isSelected()==true)
							{
								historiqueVentevendeur.setImprimerAvecNumCommande(Constantes.CODE_OUI);
								 
							}else
							{
								historiqueVentevendeur.setImprimerAvecNumCommande(Constantes.CODE_NON);
								 
							}
							
							historiqueVenteVendeurDAO.edit(historiqueVentevendeur);
							
						}
						
						if(historiqueVentevendeur.getClientPF().getPatente()!=null)
						{
							parameters.put("patente", historiqueVentevendeur.getClientPF().getPatente());
						}else
						{
							parameters.put("patente", "");
						}
                    
                		
                	}
               
                
                
                
                
                }else
                {
                	
                	dateFacture = dateFormat.format(historiqueVentevendeur.getDateFacture());
                	 parameters.put("client", historiqueVentevendeur.getClientPF().getNom());
                     parameters.put("NumFacture", historiqueVentevendeur.getNumFacture());
                     parameters.put("adresse", historiqueVentevendeur.getClientPF().getAdresse());
                     parameters.put("code", historiqueVentevendeur.getClientPF().getCode());
                     if (historiqueVentevendeur.getClientPF().getIce() != null) {
                         parameters.put("iceclient", historiqueVentevendeur.getClientPF().getIce());
                     }
                     else {
                         parameters.put("iceclient", "");
                     }
                     
                     
                     
     				if(historiqueVentevendeur.getImprimerAvecNumBl()!=null)
					{
						if(!historiqueVentevendeur.getImprimerAvecNumBl().equals(""))
						{
							
							if(historiqueVentevendeur.getImprimerAvecNumBl().equals(Constantes.CODE_OUI))
							{
								 parameters.put("NumBL", historiqueVentevendeur.getNumBl());
									
									if(historiqueVentevendeur.getDateBl()!=null)
									{
										parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getDateBl()));
									}else
									{
										parameters.put("dateBl", "");
									}
								
								 
							}else if(historiqueVentevendeur.getImprimerAvecNumBl().equals(Constantes.CODE_NON))
							
							{
								 
								 parameters.put("NumBL", "");
									
									 
										parameters.put("dateBl", "");
									 
								
							}
							
							 
							
						}else
						{
							
							if(chckbxImprimerNumBl.isSelected()==true)
							{
								parameters.put("NumBL", historiqueVentevendeur.getNumBl());
								
								if(historiqueVentevendeur.getDateBl()!=null)
								{
									parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getDateBl()));
								}else
								{
									parameters.put("dateBl", "");
								}
								
							}else
							{
								
								 parameters.put("NumBL", "");
									
								 
									parameters.put("dateBl", "");
								 
								
							}
							
							
						}
					}else
					{

						if(chckbxImprimerNumBl.isSelected()==true)
						{
							parameters.put("NumBL", historiqueVentevendeur.getNumBl());
							
							if(historiqueVentevendeur.getDateBl()!=null)
							{
								parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getDateBl()));
							}else
							{
								parameters.put("dateBl", "");
							}
							
						}else
						{
							
							 parameters.put("NumBL", "");
								
							 
								parameters.put("dateBl", "");
							 
							
						}
						
					}
					
					
					
                 
					if(historiqueVentevendeur.getImprimerAvecNumCommande()!=null)
					{
						if(!historiqueVentevendeur.getImprimerAvecNumCommande().equals(""))
						{
							
							if(historiqueVentevendeur.getImprimerAvecNumCommande().equals(Constantes.CODE_OUI))
							{
								if(historiqueVentevendeur.getDateBCommande()!=null)
								{
									parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getDateBCommande()) );
								}else
								{
									parameters.put("dateBC", "");
								}
								if(historiqueVentevendeur.getNumCommande()!=null)
								{
									parameters.put("NumBC", historiqueVentevendeur.getNumCommande());
								}else
								{
									parameters.put("NumBC", "");
								}
								
								 
							}else if(historiqueVentevendeur.getImprimerAvecNumCommande().equals(Constantes.CODE_NON))
							
							{
								 
								 
									parameters.put("dateBC", "");
								 
								 
									parameters.put("NumBC", "");
								 
									 
								
							}
							
							 
							
						}else
						{
							
							if(chckbxImprimerNumBc.isSelected()==true)
							{
								if(historiqueVentevendeur.getDateBCommande()!=null)
								{
									parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getDateBCommande()) );
								}else
								{
									parameters.put("dateBC", "");
								}
								if(historiqueVentevendeur.getNumCommande()!=null)
								{
									parameters.put("NumBC", historiqueVentevendeur.getNumCommande());
								}else
								{
									parameters.put("NumBC", "");
								}
								
							}else
							{
								parameters.put("dateBC", "");
								 
								 
								parameters.put("NumBC", "");
								 
								
							}
							
							
						}
					}else
					{

						if(chckbxImprimerNumBc.isSelected()==true)
						{
						 
							if(historiqueVentevendeur.getDateBCommande()!=null)
							{
								parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getDateBCommande()) );
							}else
							{
								parameters.put("dateBC", "");
							}
							if(historiqueVentevendeur.getNumCommande()!=null)
							{
								parameters.put("NumBC", historiqueVentevendeur.getNumCommande());
							}else
							{
								parameters.put("NumBC", "");
							}
							
						}else
						{
							
							parameters.put("dateBC", "");
							 
							 
							parameters.put("NumBC", "");
							 
							
						}
						
					} 
                     
                     
					String datechance="";
					if(historiqueVentevendeur.getClientPF().getDelaiPaiement()!=null)
					{
						if(historiqueVentevendeur.getClientPF().getDelaiPaiement().compareTo(BigDecimal.ZERO)!=0)
						{
							
							if(historiqueVentevendeur.getClientPF().getDelaiPaiementParBlOuFacture()!=null)
							{
								
								if(!historiqueVentevendeur.getClientPF().getDelaiPaiementParBlOuFacture().equals(""))
								{
									if(historiqueVentevendeur.getClientPF().getDelaiPaiementParBlOuFacture().equals(Constantes.TYPE_BON_LIVRAISON))
									{
										parameters.put("delai","Delai de paiement :  "+ historiqueVentevendeur.getClientPF().getDelaiPaiement().intValue()+" jours date de bon livraison");
										
										datechance=dateFormat.format(DateUtils.ajoutNbJours(historiqueVentevendeur.getDateBl(), historiqueVentevendeur.getClientPF().getDelaiPaiement().intValue()));
										
										 
										
										parameters.put("dateechance","Date d'échance     :  "+datechance);
									
									}else if(historiqueVentevendeur.getClientPF().getDelaiPaiementParBlOuFacture().equals(TRANSFERE_BL_FACTURE))
									{
										
										parameters.put("delai","Delai de paiement :  "+ historiqueVentevendeur.getClientPF().getDelaiPaiement().intValue()+" jours date de facture");
									
                                        datechance=dateFormat.format(DateUtils.ajoutNbJours(historiqueVentevendeur.getDateFacture(), historiqueVentevendeur.getClientPF().getDelaiPaiement().intValue()));
										
										parameters.put("dateechance","Date d'échance     :  "+datechance);
										
									 
									}else
									{
										parameters.put("delai","");
										parameters.put("dateechance","");
									}
									
									
									
									
								}else
								{
									parameters.put("delai","");
									parameters.put("dateechance","");
								}
							}else
							{
								parameters.put("delai","");
								parameters.put("dateechance","");
							}
							
							
						}else
						{
							parameters.put("delai","");
							parameters.put("dateechance","");
						}
						
						
					}else
					{
						parameters.put("delai","");
						parameters.put("dateechance","");
					}
					
					if(historiqueVentevendeur.getClientPF().getPatente()!=null)
					{
						parameters.put("patente", historiqueVentevendeur.getClientPF().getPatente());
					}else
					{
						parameters.put("patente", "");
					} 
                     
                     
                }
                
                
                
                
                
                
                
                parameters.put("dateFacture", dateFacture);
              
                final String x = txtnetapayer.getText().replace(".", ",");
                sommetowords = ConverterNumberToWords.converter(x);
                parameters.put("NumberToWords", sommetowords);
                final String totalht = String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
                final String totaltva = String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
                final String totalttc = String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
                final String timbertmp = String.valueOf(new BigDecimal(txttimbre.getText()));
                final String netapayerTmp = String.valueOf(new BigDecimal(txtnetapayer.getText()));
                parameters.put("TotalHT", totalht);
                parameters.put("TotalTVA", totaltva);
                parameters.put("TotalTTC", totalttc);
               
                parameters.put("type", "Facture N°    :");
                parameters.put("tva", Constantes.TVA);
                if (utilisateur.getCodeDepot().equals("TANTAN")) {
                    parameters.put("ice", "001564063000025");
                }
                else if (utilisateur.getCodeDepot().equals("AGADIR")) {
                    parameters.put("ice", "001536022000047");
                }else if (utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
                    parameters.put("ice", ICE_ELALAOUI);
                }
                
                parameters.put("timber", timbertmp);
                parameters.put("netapayer", netapayerTmp);
             
            	if(checkSansmodepaiement.isSelected()==true)
				{
					ModePaiement="";
					numpiece="";
				}
                
                
                parameters.put("modepaiement", ModePaiement);
                if (!numpiece.equals("")) {
                	if(numpiece!=null)
                	{
                		parameters.put("numcheque", numpiece);
                	}
               
                    
                }
                
            	if(checkEnTete.isSelected()==true)
				{
					
				if(!comboEnTete.getSelectedItem().equals(""))
				{
					
					EnTete enTete=mapEntete.get(comboEnTete.getSelectedItem().toString());
					
					byte[] blobAsBytes = enTete.getUrl();
					

					InputStream input = new ByteArrayInputStream(blobAsBytes);
					
					parameters.put("logoImpression",input) ;
					
				}
					
					
					
				}
                
                
                
                
                
                try {
                	
                	if(listDetailFacturePFImprimerTmp.size()!=0)
					{
                		if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
                			
    						JasperUtils.imprimerHistoriqueVenteVendeur(listDetailFacturePFImprimerTmp,parameters,true);

                		}else
                		{
                			JasperUtils.imprimerHistoriqueVenteVendeurSansTVA(listDetailFacturePFImprimerTmp,parameters,true);
                		}
                		
						
					}else
					{
						if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
							JasperUtils.imprimerHistoriqueVenteVendeur(listDetailFacturePFImprimer,parameters,true);

						}else
						{
							JasperUtils.imprimerHistoriqueVenteVendeurSansTVA(listDetailFacturePFImprimer,parameters,true);

						}
					}
                	
                	
                	if(chckbxImprimerBl.isSelected()==true)
                	{
                		
                		

						
						 
						 
						 BigDecimal quantitePieceCalculer=BigDecimal.ZERO; 
					       BigDecimal prixPieceCalculer=BigDecimal.ZERO; 
					       BigDecimal MontantHTPieceCalculer=BigDecimal.ZERO; 
					       BigDecimal MontantTVAPieceCalculer=BigDecimal.ZERO; 
					       BigDecimal MontantTTCPieceCalculer=BigDecimal.ZERO; 
					       
					       if(chckbxLesBlTransferer.isSelected()==true)
			                {
			                	
			                	if(!comboBlTransferer.getSelectedItem().equals(""))
			                	{
			                		 
			                		
							  			
						  			  dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	
								   dateFacture="";
						  		   
								 
								 dateFacture=dateFormat.format(listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getDateFacture());
								/*
								 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
								 * 
								 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
								 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
								 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
								 */
						  		 
									  parameters = new HashMap();
								
									parameters.put("client", listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getClientPF().getNom());
									
									
										parameters.put("NumBL", listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getNumBl());
										
										if(listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getDateBl()!=null)
										{
											parameters.put("dateBl",dateFormat.format( listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getDateBl()));
										}else
										{
											parameters.put("dateBl", "");
										}
										
										if(listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getDateBCommande()!=null)
										{
											parameters.put("dateBC",dateFormat.format(listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getDateBCommande()) );
										}else
										{
											parameters.put("dateBC", "");
										}
										if(listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getNumCommande()!=null)
										{
											parameters.put("NumBC", listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getNumCommande());
										}else
										{
											parameters.put("NumBC", "");
										}
										
								 /*
									 
									if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
									{
										
										parameters.put("ice", Constantes.ICE_ETP);
										
									}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
									{
										
										
										parameters.put("ice", Constantes.ICE_AHLBRAHIM);
										
									}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_ELALAOUI))
									{
										
										
										parameters.put("ice", Constantes.ICE_ELALAOUI);
									}
									
									*/
									
										
										if(listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getAdresseClient()!=null)
										{
											if(!listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getAdresseClient().equals(""))
											{
												parameters.put("adresse", listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getAdresseClient());
											}else
											{
												parameters.put("adresse", listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getClientPF().getAdresse());
											}
											
										}else
										{
											parameters.put("adresse", listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getClientPF().getAdresse());
											
										}
									
									
									parameters.put("code",listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getClientPF().getCode());
									
									if(listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getClientPF().getPatente()!=null)
									{
										parameters.put("patente", listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getClientPF().getPatente());
									}else
									{
										parameters.put("patente", "");
									}
									
									if(listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getClientPF().getIce()!=null)
									{
										parameters.put("iceclient",listDetailFacturePFBlTransferer.get(0).getHistoriqueventevendeur().getClientPF().getIce());
									}else
									{
										parameters.put("iceclient","");
									}
									
							
									
									if(checkEnTete.isSelected()==true)
									{
										
									if(!comboEnTete.getSelectedItem().equals(""))
									{
										
										EnTete enTete=mapEntete.get(comboEnTete.getSelectedItem().toString());
										
										byte[] blobAsBytes = enTete.getUrl();
										

										InputStream input = new ByteArrayInputStream(blobAsBytes);
										
										parameters.put("logoImpression",input) ;
										
									}
										
										
										
									}
									
									
									
									
									
									
									try {
										
										
										
										 
											 
												 JasperUtils.imprimerBonDeLivraisonHistorique(listDetailFacturePFBlTransferer,parameters,true);
											   
											
											
									 
										
									} catch (PrintException | IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage());
									}
			                		
			                		
			                	}
			                		
			                		
					       
					       
			                } else
			                {
			                	
 
			                	 
			                		 
			                		
			             	     
								 
								 
								
								 
									
									
									 
							  			
						  			  dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	
								   dateFacture="";
						  		   
								 
								 dateFacture=dateFormat.format(listDetailFacturePF.get(0).getHistoriqueventevendeur().getDateFacture());
								/*
								 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
								 * 
								 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
								 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
								 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
								 */
						  		 
									  parameters = new HashMap();
								
									parameters.put("client", listDetailFacturePF.get(0).getHistoriqueventevendeur().getClientPF().getNom());
									
									
										parameters.put("NumBL", listDetailFacturePF.get(0).getHistoriqueventevendeur().getNumBl());
										
										if(listDetailFacturePF.get(0).getHistoriqueventevendeur().getDateBl()!=null)
										{
											parameters.put("dateBl",dateFormat.format( listDetailFacturePF.get(0).getHistoriqueventevendeur().getDateBl()));
										}else
										{
											parameters.put("dateBl", "");
										}
										
										if(listDetailFacturePF.get(0).getHistoriqueventevendeur().getDateBCommande()!=null)
										{
											parameters.put("dateBC",dateFormat.format(listDetailFacturePF.get(0).getHistoriqueventevendeur().getDateBCommande()) );
										}else
										{
											parameters.put("dateBC", "");
										}
										if(listDetailFacturePF.get(0).getHistoriqueventevendeur().getNumCommande()!=null)
										{
											parameters.put("NumBC", listDetailFacturePF.get(0).getHistoriqueventevendeur().getNumCommande());
										}else
										{
											parameters.put("NumBC", "");
										}
										
								 /*
									 
									if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
									{
										
										parameters.put("ice", Constantes.ICE_ETP);
										
									}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
									{
										
										
										parameters.put("ice", Constantes.ICE_AHLBRAHIM);
										
									}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_ELALAOUI))
									{
										
										
										parameters.put("ice", Constantes.ICE_ELALAOUI);
									}
									
									*/
									
										
										if(listDetailFacturePF.get(0).getHistoriqueventevendeur().getAdresseClient()!=null)
										{
											parameters.put("adresse", listDetailFacturePF.get(0).getHistoriqueventevendeur().getAdresseClient());
										}else
										{
											parameters.put("adresse", listDetailFacturePF.get(0).getHistoriqueventevendeur().getClientPF().getAdresse());
											
										}
									
									
									parameters.put("code",listDetailFacturePF.get(0).getHistoriqueventevendeur().getClientPF().getCode());
									
									if(listDetailFacturePF.get(0).getHistoriqueventevendeur().getClientPF().getPatente()!=null)
									{
										parameters.put("patente", listDetailFacturePF.get(0).getHistoriqueventevendeur().getClientPF().getPatente());
									}else
									{
										parameters.put("patente", "");
									}
									
									if(listDetailFacturePF.get(0).getHistoriqueventevendeur().getClientPF().getIce()!=null)
									{
										parameters.put("iceclient",listDetailFacturePF.get(0).getHistoriqueventevendeur().getClientPF().getIce());
									}else
									{
										parameters.put("iceclient","");
									}
									
							
									
									if(checkEnTete.isSelected()==true)
									{
										
									if(!comboEnTete.getSelectedItem().equals(""))
									{
										
										EnTete enTete=mapEntete.get(comboEnTete.getSelectedItem().toString());
										
										byte[] blobAsBytes = enTete.getUrl();
										

										InputStream input = new ByteArrayInputStream(blobAsBytes);
										
										parameters.put("logoImpression",input) ;
										
									}
										
										
										
									}
									
									
									
									
									
									
									try {
										
										
										
										 
											 
												 JasperUtils.imprimerBonDeLivraisonHistorique(listDetailFacturePF,parameters,true);
											   
											
											
									 
										
									} catch (PrintException | IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage());
									}
			                		
			                		
			                	
			                		
			                		
					       
					       
			                
			                	
			                	
			                }
					       
					      
				
						
							
					 
                		
                		
                	} 
                	
                	
                	
               
                    
                    
                }
                catch (PrintException | IOException e2) {
                    
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Facture Ajout\u00e9 avec succ\u00e9e", "Satisfaction", 1);
                initialiserFacture();
                initialiser();
                historiqueVentevendeur=new HistoriqueVentevendeur();
                listDetailFacturePF.clear();
                listDetailFacturePFBlTransferer.clear();
                InitialiseTableau();
                labelmarge.setText("");
			
                InitialiserModeReglement();
			ChargerBlTransferer();
			
			
			checkEnTete.setSelected(false);
			comboEnTete.setSelectedItem("");
			chckbxImprimerNumBc.setSelected(false);
			chckbxImprimerNumBl.setSelected(false);
			chckbxPiece.setSelected(false);
			
			chckbxImprimerBl.setSelected(false);
			
			
			
			
			
			
			
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(370, 761, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 180, 1031, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1175, 119);
		add(layeredPane_1);
		
		JLabel lblNBl = new JLabel("N\u00B0 BL  :");
		lblNBl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNBl.setBounds(667, 5, 63, 24);
		layeredPane_1.add(lblNBl);
		
		txtnumBL = new JTextField();
		txtnumBL.setColumns(10);
		txtnumBL.setBounds(740, 6, 177, 26);
		layeredPane_1.add(txtnumBL);
		
		JLabel lblDateBl = new JLabel("Date   :");
		lblDateBl.setBounds(6, 44, 47, 24);
		layeredPane_1.add(lblDateBl);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {/*
				if(dateChooserfacture.getDate()!=null)
				{
				SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 String date = dcn.format(dateChooserfacture.getDate());
				
				 
				txtnumfacture.setText(Utils.genererCodeFactureVente(date));
				}
			*/}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(63, 43, 181, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(544, 44, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(610, 43, 183, 24);
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
     					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_PF);
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
		     				
		     				
		     				
		     				mapClientPF.clear();
		     				listClientPF=clientpfdao.findListClientByCodeDepot(depot.getCode());
		     				
		     				if(listClientPF.size()!=0)
		     				{
		     					comboClientpf.removeAllItems();
		     					comboClientpf.addItem("");
		     					for(int j=0;j<listClientPF.size();j++)
		     					{
		     						ClientPF clientpf=listClientPF.get(j);
		     						
		     						comboClientpf.addItem(clientpf.getNom());
		     						mapClientPF.put(clientpf.getNom(), clientpf);
		     						
		     						
		     					}
		     				}else
		     				{
		     					comboClientpf.removeAllItems();
		     				}
		     				
		     				
		     			}else
		     			{
		     				combomagasin.removeAllItems();
		     				
		     			}
		     				
     					
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
		  label_4.setBounds(803, 43, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
		  			
		  			 if(combomagasin.getSelectedIndex()!=-1)
		  			 {
		  				 if(!combomagasin.getSelectedItem().equals(""))
		  				 {
		  					comboFournisseur.removeAllItems();
		  					 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			  				 Client fournisseur=fournisseurdao.findClientByCodeClient(magasin.getCodeMachine());
			  				mapFournisseur.put(fournisseur.getNom(), fournisseur);
			  				comboFournisseur.addItem(fournisseur.getNom()); 
		  				 }
		  				
		  				 
		  			 }
		  			 
		  			 
    	 		 }
		  		
		  		
		  		
		  		
		  	}
		  });
		  combomagasin.setBounds(869, 44, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		   comboClientpf = new JComboBox();
		   comboClientpf.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent arg0) {
		   		

		   		
		   		if(comboClientpf.getSelectedIndex()!=-1)
		   		{
		   			
		   			if(!comboClientpf.getSelectedItem().equals(""))
		   			{
		   				
		   				ClientPF clientPF=mapClientPF.get(comboClientpf.getSelectedItem());
		   				if(clientPF!=null)
		   				{
		   					
		   					comboAdresse.removeAllItems();
				   			comboAdresse.addItem("");
				   			listAdresseClient=adresseClientPFDAO.findListAdresseClientByClient(clientPF)  ;
				   			if(listAdresseClient.size()!=0)
				   			{
				   				comboAdresse.addItem(clientPF.getAdresse());
				   				for(int i=0;i<listAdresseClient.size();i++)
				   				{
				   					comboAdresse.addItem(listAdresseClient.get(i).getAdresse());
				   				}
				   				
				   				
				   			}else
				   			{
				   				comboAdresse.addItem(clientPF.getAdresse());
				   			}
				   			
				   			
				   			
				   			 
				   			comboAdresse.setSelectedIndex(1);
				   			
				   			
				   			
				   			
		   				}else
		   				{
		   					comboAdresse.removeAllItems();
				   			comboAdresse.addItem("");
				   			comboAdresse.setSelectedIndex(0);
		   				}
		   				
		   				
		   			}else
		   			{
		   				
		   				
		   				comboAdresse.removeAllItems();
			   			comboAdresse.addItem("");
			   			comboAdresse.setSelectedIndex(0);
			   			
		   			}
		   			
		   			
		   			
		   		}else
		   		{
		   			comboAdresse.removeAllItems();
		   			comboAdresse.addItem("");
		   			comboAdresse.setSelectedIndex(0);
		   			 
		   		}
		   		
		   		
		   		
		   	
		   		
		   		
		   		
		   		
		   	}
		   });
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(67, 84, 256, 24);
		  layeredPane_1.add(comboClientpf);
		  
		  AutoCompleteDecorator.decorate(comboClientpf);
		  
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(6, 83, 56, 24);
		  layeredPane_1.add(lblClient);
		  
		  JLabel lblFournisseur = new JLabel("Fournisseur :");
		  lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblFournisseur.setBounds(813, 83, 79, 24);
		  layeredPane_1.add(lblFournisseur);
		  
		   comboFournisseur = new JComboBox();
		  comboFournisseur.setSelectedIndex(-1);
		  comboFournisseur.setBounds(891, 84, 183, 24);
		  layeredPane_1.add(comboFournisseur);
		  
		  JLabel lblNFacture = new JLabel("N\u00B0 Facture  :");
		  lblNFacture.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblNFacture.setBounds(928, 6, 89, 24);
		  layeredPane_1.add(lblNFacture);
		  
		  txtnumFacture = new JTextField();
		  txtnumFacture.setColumns(10);
		  txtnumFacture.setBounds(1027, 5, 138, 26);
		  layeredPane_1.add(txtnumFacture);
		  
		   chckbxLesBlTransferer = new JCheckBox("Les BL Transferer");
		  chckbxLesBlTransferer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		if(chckbxLesBlTransferer.isSelected()==true)
		  		{
		  			
		  			 btnAjouter.setEnabled(false);  
		   			   btnModifier.setEnabled(false); 
		   			   btnSupprimer.setEnabled(false);
		   			   btnInitialiserfacture.setEnabled(false);
		   			   comboBlTransferer.setVisible(true);
		   			   txtnumBL.setVisible(false);
		   			txtnumBL.setText("");
		   			listDetailFacturePFBlTransferer.clear();
		   			comboBlTransferer.setSelectedItem("");
		   			 initialiser();
		  		}else
		  		{
		  			 btnAjouter.setEnabled(true);  
		   			   btnModifier.setEnabled(true); 
		   			   btnSupprimer.setEnabled(true);
		   			   btnInitialiserfacture.setEnabled(true);
		   			 comboBlTransferer.setVisible(false);
		   		   txtnumBL.setVisible(true);
		   		txtnumBL.setText("");
		   	 initialiser();
		   	listDetailFacturePFBlTransferer.clear();
   			comboBlTransferer.setSelectedItem("");
		   		
		  		}
		  		
		  		
		  		
		  		
		  	}
		  });
		  chckbxLesBlTransferer.setFont(new Font("Tahoma", Font.BOLD, 12));
		  chckbxLesBlTransferer.setBounds(6, 7, 143, 23);
		  layeredPane_1.add(chckbxLesBlTransferer);
		  
		   comboBlTransferer = new JComboBox();
		   comboBlTransferer.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent arg0) {
		   		if(comboBlTransferer.getSelectedIndex()!=-1)
		   		{
		   			
		   	   		if(!comboBlTransferer.getSelectedItem().equals(""))
			   		{
			   			
			   			listDetailFacturePF.clear();
			   			
			   			HistoriqueVentevendeur historiqueVentevendeur=mapHistoriqueVenteVendeur.get(comboBlTransferer.getSelectedItem());
			   			if(historiqueVentevendeur!=null)
			   			{
			   				
			   				dateChooserfacture.setDate(historiqueVentevendeur.getDateFacture());
			   				combodepot.setSelectedItem(historiqueVentevendeur.getDepot().getLibelle());
			   				combomagasin.setSelectedItem(historiqueVentevendeur.getMagasin().getLibelle());
			   				comboClientpf.setSelectedItem(historiqueVentevendeur.getClientPF().getNom());
			   				comboFournisseur.setSelectedItem(historiqueVentevendeur.getFournisseur().getNom());
			   				if(historiqueVentevendeur.getTypeBL()!=null)
			   				{
			   					comboTypeBl.setSelectedItem(historiqueVentevendeur.getTypeBL().getType());
			   				}
			   				if(historiqueVentevendeur.getNumCommande()!=null)
			   				{
			   					txtnumCommande.setText(historiqueVentevendeur.getNumCommande());
			   				}
			   				
			   				chckbxPiece.setSelected(historiqueVentevendeur.getImprimerPiece());
			   				
			   				if(historiqueVentevendeur.getDateBCommande()!=null)
			   				{
			   					dateChooserBCommande.setDate(historiqueVentevendeur.getDateBCommande());
			   				}
			   				
			   				listDetailFacturePFBlTransferer=detailHistoriqueVenteVendeurDAO.listeDetailFacturePFByFacture(historiqueVentevendeur.getId());	
			   			    afficher_tableDetailFacturePF(listDetailFacturePFBlTransferer);	
			   			 int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					       
					        while(i<listDetailFacturePFBlTransferer.size())
					        {
					        
					          DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePFBlTransferer.get(i);
					          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
					          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
					          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
					          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());


					         
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal+"");
					        txttotalquantite.setText(sommequantite+"");
					        txttotalmontantHT.setText(montanttotalHT+"");
				  			txttotalmontantTVA.setText(montanttotalTVA+"");

			   			    
			   			    
			   			  btnAjouter.setEnabled(false);  
			   			   btnModifier.setEnabled(false); 
			   			   btnSupprimer.setEnabled(false);
			   			   btnInitialiserfacture.setEnabled(false);
			   				
			   			}
			   			
			   			
			   		}
		   		}
		
		   		
		   		
		   		
		   	}
		   });
		  comboBlTransferer.setSelectedIndex(-1);
		  comboBlTransferer.setBounds(740, 7, 177, 24);
		  layeredPane_1.add(comboBlTransferer);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 11, 1031, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(386, 417, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				
				try {
					
				
					
					
					if(checkboxGratuits.isSelected()==true)
					{
						
// ajouter les articles gratuits
						

						boolean trouve =false;
						
						
						BigDecimal marge=BigDecimal.ZERO;
						BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
					
						if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(comboArticle.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtcodearticle.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
							
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							
						}else if(!txtquantitepacket.getText().equals("") && ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
							
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							
								
						}else
						{
							
							 Articles article=mapArticle.get(comboArticle.getSelectedItem());
					        
					         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					         SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
					         
					         if(article.getCentreCout1()!=null)
					         {
					        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
					        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
					        	 
					        	 
					        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
					        	{
					        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;
					        		}
					        		
					        	}else
					        	{
					        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
					        	
					        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
					        	
					        		if(rest.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;	
					        		}
					        		
					        	}
					        	 
					         }
					         
					         
					         for(int i=0;i<listDetailFacturePF.size();i++)
					         {
					        	 DetailHistoriqueVenteVendeur detailFacturePF =listDetailFacturePF.get(i);
					        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && detailFacturePF.getPrixUnitaire().equals(BigDecimal.ZERO) && detailFacturePF.getMontantHT().equals(BigDecimal.ZERO)
					        			 
					        			&&  detailFacturePF.getMontantTVA().equals(BigDecimal.ZERO) && detailFacturePF.getMontantTTC().equals(BigDecimal.ZERO) && detailFacturePF.getReduction().equals(BigDecimal.ZERO) && detailFacturePF.getTva().equals(BigDecimal.ZERO)
					        			 
					        			 )
					        	 {
					        		 trouve=true;
					        	 }
					         }
					         
					         if(trouve==false)
					         {
					          	
					        	 
						        	
					        	
					        		 
					        		 if(!txtquantitepacket.getText().equals(""))
					        		 {
					        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
					        		 }
					        		 if(!txtquantite.getText().equals(""))
					        		 {
					        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
					        		 }
					        		 
					        		
					        		 
					        		 
					        		 
					        		 
					        		 
					        		 // Verifier le stock offre pour la gratuité du the Promotion
					        		 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM) )
					        		 {
					        			
					        			 
					        		
					        			 ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
							        		 
							        		 DetailHistoriqueVenteVendeur detailFacture=new DetailHistoriqueVenteVendeur();
							        	 	
									          detailFacture.setArticle(article);
									          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
									          
									          
									          DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
		  	  		  				 			
			  	  		  				 		
			  	  		  				 		if(detailprixarticle!=null)
			  	  		  				 			{
			  	  		  				 		detailFacture.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 				 
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 				
			  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
				
			  	  		  				 			if(detailprixarticleTmp!=null)	
			  	  		  				 			{
			  	  		  				 			detailFacture.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 				
			  	  		  				 			 
			  	  		  				 				
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 				
			  	  		  				 			detailFacture.setPrixUnitaire(BigDecimal.ZERO);
			  	  		  				 			
			  	  		  				 			}
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 			}
									          
									          detailFacture.setMontantHT(BigDecimal.ZERO);
									          detailFacture.setMontantTVA(BigDecimal.ZERO);
									          detailFacture.setTva(BigDecimal.ZERO);
									          detailFacture.setSousFamille(sousfamille);
									          detailFacture.setReduction(new BigDecimal(100));
									          detailFacture.setMontantTTC(BigDecimal.ZERO); 
									          detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
									          detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));

									         //  detailFacture.setTypeVente(typevente);
									        
									           detailFacture.setHistoriqueventevendeur(historiqueVentevendeur);
									       //  detailFacture.setDateCreation(new Date());
									           
									           detailFacture.setUtilisateur(utilisateur);
									         
									           listDetailFacturePF.add(detailFacture);
									        
									        
									          afficher_tableDetailFacturePF(listDetailFacturePF);
									          int i=0;
										        BigDecimal montanttotal=BigDecimal.ZERO;
										        BigDecimal montanttotalHT=BigDecimal.ZERO;
										        BigDecimal montanttotalTVA=BigDecimal.ZERO;
										        BigDecimal sommequantite=BigDecimal.ZERO;
										        BigDecimal lamarge=BigDecimal.ZERO;
										        BigDecimal compteur=BigDecimal.ZERO;
										        while(i<listDetailFacturePF.size())
										        {

										          DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
										          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
										          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());


										        	
										            i++;
										        }
										       txttotalmontantTTC.setText(montanttotal+"");
										       txttotalquantite.setText(sommequantite+"");
										       txttotalmontantHT.setText(montanttotalHT+"");
									  		   txttotalmontantTVA.setText(montanttotalTVA+"");


									  			
									  		  initialiser(); 
							        	 
							        		 
							        	 
					        		 }else
					        		 {
					        			 
					        			 ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
							        		 
							        		 DetailHistoriqueVenteVendeur detailFacture=new DetailHistoriqueVenteVendeur();
							        	 	
									          detailFacture.setArticle(article);
									          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
									          DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
		  	  		  				 			
			  	  		  				 		
			  	  		  				 		if(detailprixarticle!=null)
			  	  		  				 			{
			  	  		  				 		detailFacture.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 				 
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 				
			  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
				
			  	  		  				 			if(detailprixarticleTmp!=null)	
			  	  		  				 			{
			  	  		  				 			detailFacture.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 				
			  	  		  				 			 
			  	  		  				 				
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 				
			  	  		  				 			detailFacture.setPrixUnitaire(BigDecimal.ZERO);
			  	  		  				 			
			  	  		  				 			}
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 			}
									          detailFacture.setMontantHT(BigDecimal.ZERO);
									          detailFacture.setMontantTVA(BigDecimal.ZERO);
									          detailFacture.setTva(BigDecimal.ZERO);
									          detailFacture.setSousFamille(sousfamille);
									          detailFacture.setReduction(new BigDecimal(100));
									          detailFacture.setMontantTTC(BigDecimal.ZERO); 
									          detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
									          detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
									         
									         
									         //  detailFacture.setTypeVente(typevente);
									        
									           detailFacture.setHistoriqueventevendeur(historiqueVentevendeur);
									       //  detailFacture.setDateCreation(new Date());
									           
									           detailFacture.setUtilisateur(utilisateur);
									         
									           listDetailFacturePF.add(detailFacture);

									          afficher_tableDetailFacturePF(listDetailFacturePF);
									          int i=0;
										        BigDecimal montanttotal=BigDecimal.ZERO;
										        BigDecimal montanttotalHT=BigDecimal.ZERO;
										        BigDecimal montanttotalTVA=BigDecimal.ZERO;
										        BigDecimal sommequantite=BigDecimal.ZERO;
										        BigDecimal lamarge=BigDecimal.ZERO;
										        BigDecimal compteur=BigDecimal.ZERO;
										        while(i<listDetailFacturePF.size())
										        {

										          DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
										          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
										          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
										     
										        	
										            i++;
										        }
										       txttotalmontantTTC.setText(montanttotal+"");
										       txttotalquantite.setText(sommequantite+"");
										       txttotalmontantHT.setText(montanttotalHT+"");
									  		   txttotalmontantTVA.setText(montanttotalTVA+"");


									  		 


									  			
									  		  initialiser(); 
							        	
					        		 }
					        		 
					        		 
					        	
					         }else
					         {
					        	 
					        	 JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						    	   return; 
					        	 
					         }
					         
					        	
						       
					         }
					        
							
						
						
					}else
					{
						
						boolean trouve=false;
						
						BigDecimal stockTherresPromo=BigDecimal.ZERO;
						BigDecimal stockVerresPromo=BigDecimal.ZERO;
						BigDecimal stockArticlePromo=BigDecimal.ZERO;
						BigDecimal marge=BigDecimal.ZERO;
						BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						
						boolean promo=false;
						
						if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(comboArticle.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtcodearticle.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
						}else if(!txtquantitepacket.getText().equals("") &&  ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
						
						}else if(txtPrix.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtmontant.getText().equals("") && checkttc.isSelected()==false)
						{
							JOptionPane.showMessageDialog(null, "Erreur de Montant","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else
						{
							
							 Articles article=mapArticle.get(comboArticle.getSelectedItem());
					        
							 
					         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					         SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
					      
					         if(article.getCentreCout1()!=null)
					         {
					        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
					        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
					        	 
					        	 
					        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
					        	{
					        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;
					        		}
					        		
					        	}else
					        	{
					        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
					        	
					        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
					        	
					        		if(rest.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;	
					        		}
					        		
					        	}
					        	 
					         }
					         
					         for(int i=0;i<listDetailFacturePF.size();i++)
					         {
					        	 DetailHistoriqueVenteVendeur detailFacturePF =listDetailFacturePF.get(i);
					        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
					        	 {
					        		 trouve=true;
					        	 }
					         }
					         
					         if(trouve==false)
					         {
					        	 
					        	 
					        	
					        		 if(!txtquantitepacket.getText().equals(""))
					        		 {
					        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
					        		 }
					        		 if(!txtquantite.getText().equals(""))
					        		 {
					        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
					        		 }
					        		 
					        		


						        		 
						        		 SousFamilleArticlePF sousFamilleArticlePFOffreTherres=null; 
						        		 SousFamilleArticlePF sousFamilleArticlePFOffreVerres=null;
						        		 Articles articlesOffreTherres=null;
						        		 Articles articlesOffreVerres=null;
						        		
						        		 
						        		////////////////////////////////////////////////// Traitement Promotion  /////////////////////////////////////// 
						        		 
						        			if(article.getCentreCout5()!=null)
				  				 			{
				  				 			// centrecout5 = article promotion ou non
				  				 				if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
				  				 				{
				  				 					
				  				 					 if(!txtquantitetherres.getText().equals("") )
				  				 					{
				  				 						 if(((new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)<0)))
				  				 						 {
				  				 							JOptionPane.showMessageDialog(null, "la quantite des Therres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  										return; 
				  				 						 }
				  				 						
				  									else if( new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)>0 )
				  				 						{
				  				 							
				  				 							sousFamilleArticlePFOffreTherres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
					  	  				 					
						  	  				 				articlesOffreTherres=mapArticleOffre.get(comboBoxtherres.getSelectedItem());
						  				 						
						  	  				 		
				  				 							
				  				 						}
				  				 						
				  	  				 				
				  				 							}
				  				 					 
				  				 					 
				  				 					///////////////////////////////////////////////////////////////// 
				  				 					 
				  				 					 
				  				 					 if(!txtquantiteverres.getText().equals("") )
				  				 					{
				  				 						 if (((new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)<0)))
		                                               {
			                                      JOptionPane.showMessageDialog(null, "la quantite des Verres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			                                 	return;
				
		                                              }else if(new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)>0 )
				  				 						{
				  				 							
				  				 						 sousFamilleArticlePFOffreVerres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
					  				 						
					  				 					  articlesOffreVerres=mapArticleOffre.get(comboBoxverres.getSelectedItem());
						  	  				 		
					  				 						
						  	  				 			 
				  				 						}
				  				 						
				  				 					}
				  				 					 
				  				 					 
				  				 					 //////////////////////////////////////////////////////////////////////////////////////////////////////
				  				 						
										        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
										        		 if(articlespromo!=null)
										        		 {
										        			if(!txtquantitepromo.getText().equals("")) 
										        			{
										        				
										        				
										        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)<0 )
										        				{
										        					JOptionPane.showMessageDialog(null, "la quantite d'Article Promo doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							  										return;
										        				}
										        				
										        			
										        				
										        				
										        				
										        			}
										        			 
										        			 
										        		 }
										        		 
										        		 
										        		 
										        		 promo=true;
										        		 
										        	 }else if (article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && checkboxGratuits.isSelected()==false  &&  magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF) )
						  				 				{
										        		 

						  				 					
					  				 					 if(!txtquantitetherres.getText().equals("") )
					  				 					{
					  				 						 if(((new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)<0)))
					  				 						 {
					  				 							JOptionPane.showMessageDialog(null, "la quantite des Therres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						  										return; 
					  				 						 }
					  				 						
					  									else if( new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)>0 )
					  				 						{
					  				 							
					  				 							sousFamilleArticlePFOffreTherres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
						  	  				 					
							  	  				 				articlesOffreTherres=mapArticleOffre.get(comboBoxtherres.getSelectedItem());


							  	  				 				
					  				 							
					  				 						}
					  				 						
					  	  				 				
					  				 							}
					  				 					 
					  				 					 
					  				 					///////////////////////////////////////////////////////////////// 
					  				 					 
					  				 					 
					  				 					 if(!txtquantiteverres.getText().equals("") )
					  				 					{
					  				 						 if (((new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)<0)))
			                                               {
				                                      JOptionPane.showMessageDialog(null, "la quantite des Verres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
				                                 	return;
					
			                                              }else if(new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)>0 )
					  				 						{
					  				 							
					  				 						 sousFamilleArticlePFOffreVerres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
						  				 						
						  				 					  articlesOffreVerres=mapArticleOffre.get(comboBoxverres.getSelectedItem());


						  				 						
							  	  				 			 
					  				 						}
					  				 						
					  				 					}
					  				 					 
					  				 					 
					  				 					 //////////////////////////////////////////////////////////////////////////////////////////////////////
					  				 						
											        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
											        		 if(articlespromo!=null)
											        		 {
											        			if(!txtquantitepromo.getText().equals("")) 
											        			{
											        				

											        				
											        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)<0 )
											        				{
											        					JOptionPane.showMessageDialog(null, "la quantite d'Article Promo doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								  										return;
											        				}
											        				


											        				
											        				
											        				
											        			}
											        			 
											        			 
											        		 }
											        		 
											        		 
											        		 
											        		 promo=true;
											        		 
											        	 
										        		 
						  				 				}
				  	  				 			    	
				  	  				 			  
				  				 			}
						        		 
						        		 
						        		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


							        	 

							        	 DetailHistoriqueVenteVendeur detailFacture=new DetailHistoriqueVenteVendeur();
						        	 	  if(!txtreduction.getText().equals(""))
								          {
								        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
								        	  
								        	  
								          }else
								          {
								        	  detailFacture.setReduction(BigDecimal.ZERO);  
								        	  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
								          }
								          detailFacture.setArticle(article);
								          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
								   if(checkttc.isSelected()==true)
								   {
									   detailFacture.setPrixUnitaire(prixTTC);
								          detailFacture.setBrutHT(prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								      
								          if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
								        	  detailFacture.setMontantTVA((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
									          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								          }else
								          {
								        	  detailFacture.setMontantTVA((BigDecimal.ZERO).setScale(6, RoundingMode.HALF_UP));
									          detailFacture.setTva(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP));
								          }
								         
								          detailFacture.setSousFamille(sousfamille);
								          if(!txtreduction.getText().equals(""))
								          {
								        	  
								        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
								        		  
								        		  detailFacture.setRemiseCommerciale((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
								        		  detailFacture.setMontantHT((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
								        		  detailFacture.setMontantTVA((((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
										          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
										          
									        	  detailFacture.setMontantTTC(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).add((((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP)));  

								        	  }else
								        	  {
								        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
								        		  detailFacture.setMontantHT((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
								        		  detailFacture.setMontantTVA((BigDecimal.ZERO).setScale(6, RoundingMode.HALF_UP));
								        		  detailFacture.setTva(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP));
								        		 
								        		  detailFacture.setMontantTTC((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								        	  }
								          }else
								          {
								        	  
								        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
								        		  
								        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
								        		  detailFacture.setMontantHT(prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								        		  detailFacture.setMontantTVA(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
								        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								        		  detailFacture.setMontantTTC((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
 
								        	  }else
								        	  {
								        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
								        		  detailFacture.setMontantHT(prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								        		  detailFacture.setMontantTVA((BigDecimal.ZERO).setScale(6, RoundingMode.HALF_UP));
								        		
								        		  detailFacture.setTva(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP));
									        	  detailFacture.setMontantTTC((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP));
 
								        	  }
								          }
								 
								   }else
								   {
									      detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
									      detailFacture.setBrutHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));

								         
								          if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
								              detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
									          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								          }else
								          {
								              detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
									          detailFacture.setTva(BigDecimal.ZERO);
								          }
								    
								          detailFacture.setSousFamille(sousfamille);
								          if(!txtreduction.getText().equals(""))
								          {
								        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
								        		  
								        		  detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
								        		  detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
								        		  detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA));
								        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).add(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)));  

								        	  }else
								        	  {
								        		  detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
								        		  detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
								        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
										          detailFacture.setTva(BigDecimal.ZERO);
								        		  
								        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								        	  }
								          }else
								          {
								        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
								        		  
								        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
								        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								        		  detailFacture.setMontantTVA((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP));
								        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP)) );

								        	
								        	  
								        	  }else
								        	  {
								        		  
								        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
								        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
								        		  detailFacture.setTva(BigDecimal.ZERO);
									        	  detailFacture.setMontantTTC(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).setScale(6, RoundingMode.HALF_UP));

								        	  }
								          }
								
								   }
								        	 
								       
								         //  detailFacture.setTypeVente(typevente);
								        
								           detailFacture.setHistoriqueventevendeur (historiqueVentevendeur);
								       //  detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);
								         
								           listDetailFacturePF.add(detailFacture);
								          
								           
								          if(promo==true)
								          {
								        	  ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
								        	   ////////////////////////////////////////////////////// Therres offre /////////////////////////////////////////////
							        		 
								        	  if(!txtquantitetherres.getText().equals(""))
								        	  {
								        		  
								        		  DetailHistoriqueVenteVendeur detailFactureTherres=new DetailHistoriqueVenteVendeur();
									        		 
									        		 detailFactureTherres.setArticle(articlesOffreTherres);
									        		 detailFactureTherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
									        		 
	DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlesOffreTherres.getId(), magasin.getId(),sousFamilleArticlePFOffreTherres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreTherres.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
					  	  		  				 		if(detailprixarticle!=null)
					  	  		  				 			{
					  	  		  				 	 detailFactureTherres.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 			 
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 				
					  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articlesOffreTherres.getId(), magasin.getId(),sousFamilleArticlePFOffreTherres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreTherres.getId());
						
					  	  		  				 			if(detailprixarticleTmp!=null)	
					  	  		  				 			{
					  	  		  				 		 detailFactureTherres.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 				 
					  	  		  				 				
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 			detailFactureTherres.setPrixUnitaire(BigDecimal.ZERO);
					  	  		  				 		 
					  	  		  				 			
					  	  		  				 			}
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 			}
									        		 
									        		
									        		 detailFactureTherres.setMontantHT(BigDecimal.ZERO);
									        		 detailFactureTherres.setMontantTVA(BigDecimal.ZERO);
									        		 detailFactureTherres.setTva(BigDecimal.ZERO);
									        		 detailFactureTherres.setSousFamille(sousFamilleArticlePFOffreTherres);
									        		 detailFactureTherres.setReduction(new BigDecimal(100));
									        		 detailFactureTherres.setMontantTTC(BigDecimal.ZERO);  
									        		 detailFactureTherres.setRemiseCommerciale(detailFactureTherres.getPrixUnitaire().multiply(detailFactureTherres.getQuantite()));
									        		 detailFactureTherres.setBrutHT(detailFactureTherres.getPrixUnitaire().multiply(detailFactureTherres.getQuantite()));
 												         //  detailFacture.setTypeVente(typevente);
												        
									        		 detailFactureTherres.setHistoriqueventevendeur (historiqueVentevendeur);
												       //  detailFacture.setDateCreation(new Date());
												           
									        		 detailFactureTherres.setUtilisateur(utilisateur);
												         
												           listDetailFacturePF.add(detailFactureTherres);
												        


												           
												           
								        	  }
								        	  
								        	  
							        		
										           
										           
								          
										           ////////////////////////////////////////////////////// Verres offre /////////////////////////////////////////////
								        	  if(!txtquantiteverres.getText().equals(""))
								        	  {
								        		 

										           DetailHistoriqueVenteVendeur detailFactureVerres=new DetailHistoriqueVenteVendeur();
									        		 
									        		 detailFactureVerres.setArticle(articlesOffreVerres);
									        		 detailFactureVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
									        		 DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlesOffreVerres.getId(), magasin.getId(),sousFamilleArticlePFOffreVerres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreVerres.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
					  	  		  				 		if(detailprixarticle!=null)
					  	  		  				 			{
					  	  		  				 		detailFactureVerres.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 			 
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 				
					  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articlesOffreVerres.getId(), magasin.getId(),sousFamilleArticlePFOffreVerres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreVerres.getId());
						
					  	  		  				 			if(detailprixarticleTmp!=null)	
					  	  		  				 			{
					  	  		  				 			detailFactureVerres.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 				 
					  	  		  				 				
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 			detailFactureVerres.setPrixUnitaire(BigDecimal.ZERO);
					  	  		  				 				
					  	  		  				 		 
					  	  		  				 			
					  	  		  				 			}
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 			}


					  	  		  				  detailFactureVerres.setMontantHT(BigDecimal.ZERO);
									        		 detailFactureVerres.setMontantTVA(BigDecimal.ZERO);
									        		 detailFactureVerres.setTva(BigDecimal.ZERO);
									        		 detailFactureVerres.setSousFamille(sousFamilleArticlePFOffreVerres);
									        		 detailFactureVerres.setReduction(new BigDecimal(100));
									        		 detailFactureVerres.setMontantTTC(BigDecimal.ZERO);  
									        		 detailFactureVerres.setRemiseCommerciale(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
									        		 detailFactureVerres.setBrutHT(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
												         
												         
												         //  detailFacture.setTypeVente(typevente);
												        
									        		 detailFactureVerres.setHistoriqueventevendeur(historiqueVentevendeur);
												       //  detailFacture.setDateCreation(new Date());
												           
									        		 detailFactureVerres.setUtilisateur(utilisateur);
												         
												           listDetailFacturePF.add(detailFactureVerres);
												        
												          
								        		  
								        	  }
								        	  
								        	  
												           
											//////////////////////////////////////////////// Article Promo ///////////////////////////////////////////////////////////	     
												           
												           Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
											        		 if(articlespromo!=null)
											        		 {
											        			if(!txtquantitepromo.getText().equals("")) 
											        			{
											        				
											        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)!=0 && new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)>0)
											        				{
											        					
											        					
											        					 DetailHistoriqueVenteVendeur detailFactureArticlePromo=new DetailHistoriqueVenteVendeur();
														        		 
											        					 detailFactureArticlePromo.setArticle(articlespromo);
											        					 detailFactureArticlePromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));

											        					 DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
									  	  		  				 			
										  	  		  				 		
										  	  		  				 		if(detailprixarticle!=null)
										  	  		  				 			{
										  	  		  				 		detailFactureArticlePromo.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
										  	  		  				 			 
										  	  		  				 			}else
										  	  		  				 			{
										  	  		  				 				
										  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
											
										  	  		  				 			if(detailprixarticleTmp!=null)	
										  	  		  				 			{
										  	  		  				 			detailFactureArticlePromo.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
										  	  		  				 				 
										  	  		  				 				
										  	  		  				 			}else
										  	  		  				 			{
										  	  		  				 				
										  	  		  				 			detailFactureArticlePromo.setPrixUnitaire(BigDecimal.ZERO);
										  	  		  				 			
										  	  		  				 			}
										  	  		  				 				
										  	  		  				 				
										  	  		  				 				
										  	  		  				 				
										  	  		  				 			}
											        					 
											        					 
										  	  		  				  detailFactureArticlePromo.setMontantHT(BigDecimal.ZERO);
											        					 detailFactureArticlePromo.setMontantTVA(BigDecimal.ZERO);
											        					 detailFactureArticlePromo.setTva(BigDecimal.ZERO);
											        					 detailFactureArticlePromo.setSousFamille(sousfamille);
											        					 detailFactureArticlePromo.setReduction(new BigDecimal(100));
											        					 detailFactureArticlePromo.setMontantTTC(BigDecimal.ZERO);  
											        					 detailFactureArticlePromo.setBrutHT(detailFactureArticlePromo.getQuantite().multiply(detailFactureArticlePromo.getPrixUnitaire()));
											        					 detailFactureArticlePromo.setRemiseCommerciale(detailFactureArticlePromo.getQuantite().multiply(detailFactureArticlePromo.getPrixUnitaire()));
							
																	         
																	         
																	         //  detailFacture.setTypeVente(typevente);
																	        
											        					 detailFactureArticlePromo.setHistoriqueventevendeur(historiqueVentevendeur);
																	       //  detailFacture.setDateCreation(new Date());
																	           
											        					 detailFactureArticlePromo.setUtilisateur(utilisateur);
																	         
																	           listDetailFacturePF.add(detailFactureArticlePromo);
																	        


											        					
											        					
											        					
											        				}
											        				
											        				
											        			}
											        		 }
												                  
								        
								          }
								     	  
								          
								          afficher_tableDetailFacturePF(listDetailFacturePF);
								          int i=0;
									        BigDecimal montanttotal=BigDecimal.ZERO;
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        BigDecimal sommequantite=BigDecimal.ZERO;
									        BigDecimal lamarge=BigDecimal.ZERO;
									        BigDecimal compteur=BigDecimal.ZERO;
									        while(i<listDetailFacturePF.size())
									        {
									        
									          DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
									          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
									          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
									          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
									          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());


									         
									            i++;
									        }
									       txttotalmontantTTC.setText(montanttotal+"");
									        txttotalquantite.setText(sommequantite+"");
									        txttotalmontantHT.setText(montanttotalHT+"");
								  			txttotalmontantTVA.setText(montanttotalTVA+"");

									           

							        		
											
								  			
								  		  initialiser(); 
						        	
						        	 
					        	
					        	 
						       }else
						       {
						    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						    	   return;
						       }
						       
					         }
					        
					}
					
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(517, 416, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiser();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		   btnInitialiserfacture = new JButton("Initialiser");
		  btnInitialiserfacture.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserFacture();
		  	
		  		
		  	}
		  });
		  btnInitialiserfacture.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiserfacture.setBounds(441, 158, 106, 23);
		  add(btnInitialiserfacture);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		try {
		   			
		   			
		   		
		   			
		   			if(checkboxGratuits.isSelected()==true)
		   			{

				   		
				   		BigDecimal stocktmp=BigDecimal.ZERO;
				   		BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						
				   		if(tableArticle.getSelectedRow()!=-1)
				   		{
				   			boolean trouve=false;
							
				   		 if(dateChooserfacture.getDate()==null)
							{

								JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
								return;	
							}else if(comboArticle.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodearticle.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
									JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								
								
							}else if(!txtquantitepacket.getText().equals("") && ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
							JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								
							
							}else
							{
								
								 Articles article=mapArticle.get(comboArticle.getSelectedItem());
						        
						      Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
						      SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
						      
						      if(article.getCentreCout1()!=null)
						         {
						        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
						        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
						        	 
						        	 
						        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
						        	{
						        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
						        		{
						        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;
						        		}
						        		
						        	}else
						        	{
						        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
						        	
						        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
						        	
						        		if(rest.compareTo(BigDecimal.ZERO)!=0)
						        		{
						        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;	
						        		}
						        		
						        	}
						        	 
						         }
						      
						      
						      
						         for(int i=0;i<listDetailFacturePF.size();i++)
						         {
						        	 if(i!=tableArticle.getSelectedRow())
						        	 {
						        		 
						        		 DetailHistoriqueVenteVendeur detailFacturePF =listDetailFacturePF.get(i);
							        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle())  && detailFacturePF.getPrixUnitaire().equals(BigDecimal.ZERO) && detailFacturePF.getMontantHT().equals(BigDecimal.ZERO)
							        			 
								        			&&  detailFacturePF.getMontantTVA().equals(BigDecimal.ZERO) && detailFacturePF.getMontantTTC().equals(BigDecimal.ZERO) && detailFacturePF.getReduction().equals(BigDecimal.ZERO) && detailFacturePF.getTva().equals(BigDecimal.ZERO)
								        			   )
							        	 {
							        		 trouve=true;
							        	 }
							        	  
						        		 
						        	 }
						        	 
						        	 
						         }
						         
						         if(trouve==false)
						         {
						        	 
						      
						        		 if(!txtquantitepacket.getText().equals(""))
						        		 {
						        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
						        		 }
						        		 if(!txtquantite.getText().equals(""))
						        		 {
						        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
						        		 }
						        		 
						        		 // Verifier le stock offre pour la gratuité du the promotion
						        		 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
						        		 {
						        			 
						        		
						        		

								        		 
									        	 DetailHistoriqueVenteVendeur detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
									        	 
									        	
										         detailFacture.setReduction(BigDecimal.ZERO);  
										         
										          detailFacture.setArticle(article);
										          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
										          
										          
										           detailFacture.setPrixUnitaire(BigDecimal.ZERO);
										           detailFacture.setMontantHT(BigDecimal.ZERO);
											       detailFacture.setMontantTVA(BigDecimal.ZERO);
											       detailFacture.setSousFamille(sousfamille);
											      detailFacture.setMontantTTC(BigDecimal.ZERO);  
											      detailFacture.setBrutHT(BigDecimal.ZERO);
											      detailFacture.setTva(BigDecimal.ZERO);
											      detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
											         
											       
										         //  detailFacture.setTypeVente(typevente);
										          
										           detailFacture.setHistoriqueventevendeur(historiqueVentevendeur);
										       //    detailFacture.setDateCreation(new Date());
										           
										           detailFacture.setUtilisateur(utilisateur);
										          
										           listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
										          afficher_tableDetailFacturePF(listDetailFacturePF);
										          int i=0;
											        BigDecimal montanttotal=BigDecimal.ZERO;
											        BigDecimal sommequantite=BigDecimal.ZERO;
											        BigDecimal montanttotalHT=BigDecimal.ZERO;
											        BigDecimal montanttotalTVA=BigDecimal.ZERO;
											       
											        while(i<listDetailFacturePF.size())
											        {
											        
											        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
												          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
												          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
												          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
												          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
												     
												        	
											            i++;
											        }
											       txttotalmontantTTC.setText(montanttotal+"");
											        txttotalquantite.setText(sommequantite+"");
											        txttotalmontantHT.setText(montanttotalHT+"");
										  			txttotalmontantTVA.setText(montanttotalTVA+"");
										  	
										  			
										  			
										  			
								        		initialiser();
								        	
								        	 
								        	
						        		 }else
						        		 {
						        			 DetailHistoriqueVenteVendeur detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
						        			 


						        			 
									        	
										         detailFacture.setReduction(BigDecimal.ZERO);  
										         
										          detailFacture.setArticle(article);
										          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
										          
										          
										           detailFacture.setPrixUnitaire(BigDecimal.ZERO);
										           detailFacture.setMontantHT(BigDecimal.ZERO);
											       detailFacture.setMontantTVA(BigDecimal.ZERO);
											       detailFacture.setSousFamille(sousfamille);
											      detailFacture.setMontantTTC(BigDecimal.ZERO);  
											      detailFacture.setBrutHT(BigDecimal.ZERO);
											      detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
											      detailFacture.setTva(BigDecimal.ZERO);
											         
											       
										         //  detailFacture.setTypeVente(typevente);
										          
										           detailFacture.setHistoriqueventevendeur (historiqueVentevendeur);
										       //    detailFacture.setDateCreation(new Date());
										           
										           detailFacture.setUtilisateur(utilisateur);
										          
										           listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
										        
										          int i=0;
											        BigDecimal montanttotal=BigDecimal.ZERO;
											        BigDecimal sommequantite=BigDecimal.ZERO;
											        BigDecimal montanttotalHT=BigDecimal.ZERO;
											        BigDecimal montanttotalTVA=BigDecimal.ZERO;
											      
											        while(i<listDetailFacturePF.size())
											        {
											        
											        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
												          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
												          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
												          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
												          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
												       
												        	
											            i++;
											        }
											       txttotalmontantTTC.setText(montanttotal+"");
											        txttotalquantite.setText(sommequantite+"");
											        txttotalmontantHT.setText(montanttotalHT+"");
										  			txttotalmontantTVA.setText(montanttotalTVA+"");


										  			
										  			
													afficher_tableDetailFacturePF(listDetailFacturePF);
								        		    initialiser();
								        	
								        	 
						        		 }
						        		 
						            	
						        		 
						        	
						        	   }else
							       {
							    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							    	   return;
							       }
							       
						         }
						        
							}
							
		   				
		   			}else
		   			{


				   	
				   		BigDecimal stocktmp=BigDecimal.ZERO;
				   		BigDecimal stocktmpoffretherres=BigDecimal.ZERO;
				   		BigDecimal stocktmpoffreverres=BigDecimal.ZERO;
				   		BigDecimal stocktmpoffreArticlePromo=BigDecimal.ZERO;
				   		BigDecimal marge=BigDecimal.ZERO;
				   		BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						
				   		if(tableArticle.getSelectedRow()!=-1)
				   		{
				   			boolean trouve=false;
							
				   		 if(dateChooserfacture.getDate()==null)
							{

								JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
								return;	
							}else if(comboArticle.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodearticle.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
									JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								
								
							}else if(!txtquantitepacket.getText().equals("") && ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
									JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								
							
							}else if(txtPrix.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtmontant.getText().equals("") && checkttc.isSelected()==false)
							{
								JOptionPane.showMessageDialog(null, "Erreur de Montant","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else
							{
									
								Articles articleOffre=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
					       		if(articleOffre!=null)
					       		{
					       			if(articleOffre.getCodeArticle().equals(Constantes.OFFRE_THERRES)  || articleOffre.getCodeArticle().equals(Constantes.OFFRE_VERRES))
						       		{
						       			
						       			JOptionPane.showMessageDialog(null, "Veuillez Selectionner Article Promo SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
						       			
						       			
						       		}
					       		}
					       	
								
					       		if(tableArticle.getSelectedRow()-3>=0)
					       		{
					       			
					       			if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()-3).getArticle().getCodeArticle()+"_PFO"))
					       			{
					       				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Article Promo SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
					       			}
					       			
					       		}
					       		
					       		
								
								
								
								 Articles article=mapArticle.get(comboArticle.getSelectedItem());
						        
						      Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
						      SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
						      
						      if(article.getCentreCout1()!=null)
						         {
						        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
						        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
						        	 
						        	 
						        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
						        	{
						        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
						        		{
						        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;
						        		}
						        		
						        	}else
						        	{
						        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
						        	
						        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
						        	
						        		if(rest.compareTo(BigDecimal.ZERO)!=0)
						        		{
						        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;	
						        		}
						        		
						        	}
						        	 
						         }
						      
						         for(int i=0;i<listDetailFacturePF.size();i++)
						         {
						        	 if(i!=tableArticle.getSelectedRow())
						        	 {
						        		 
						        		 DetailHistoriqueVenteVendeur detailFacturePF =listDetailFacturePF.get(i);
							        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && !detailFacturePF.getPrixUnitaire().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) )
							        	 {
							        		 trouve=true;
							        	 }
							        	  
						        		 
						        	 }
						        	 
						        	 
						         }
						         
						         if(trouve==false)
						         {
						        	 boolean offfrepromo =false ; 
						        	 
						        	 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5()!=null)
			  				 			{
			  				 			// centrecout5 = article promotion ou non
			  				 				if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getSousFamille(). getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM) )
			  				 				{
			  				 					if(article.getCentreCout5()!=null)
			  				 					{
			  				 						if(!article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
			  				 						{
			  				 							
			  				 							JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			  				 							
			  				 							return;
			  				 							
			  				 						}else if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) &&  sousfamille. getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM) )
			  				 						
			  				 						{
			  				 							
                                                       JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			  				 							
			  				 							return;
			  				 							
			  				 						}else
			  				 						
			  				 						{
			  				 							
			  				 					
			  				 									
			  				 									offfrepromo=true;
			  				 									
				  				 				
			  				 							
			  				 							
			  				 						}
			  				 						
			  				 						
			  				 					}else
			  				 					{
			  				 						JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		  				 							
		  				 							return;
			  				 					}
			  				 					 
			  				 					
			  				 				} 
			  				 				else 	if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF) )
			  				 				{
			  				 					if(article.getCentreCout5()!=null)
			  				 					{
			  				 						if(!article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
			  				 						{
			  				 							
			  				 							JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			  				 							
			  				 							return;
			  				 							
			  				 						}else
			  				 						
			  				 						{
			  				 							
			  				 					
			  				 									
			  				 									offfrepromo=true;
			  				 									
				  				 				
			  				 							
			  				 							
			  				 						}
			  				 						
			  				 						
			  				 					}else
			  				 					{
			  				 						JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		  				 							
		  				 							return;
			  				 					}
			  				 					 
			  				 					
			  				 				}
						        	
			  				 			}
						        	 
						        	 
						        	 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille. getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
						        	 {
						        		 
						        		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && listDetailFacturePF.get(tableArticle.getSelectedRow()).getSousFamille(). getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM) ) 
						        		 {
						        			 
						        				JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  				 							
	  				 							return; 
						        			 
						        			 
						        		 }else if(!listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) )
						        		 {
						        			 JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  				 							
	  				 							return; 
						        		 }
						        		 
						        		 
						        	 }else if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF))
						        	 {
						        		 
						        		 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  ) 
						        		 {
						        			 
						        				JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  				 							
	  				 							return; 
						        			 
						        			 
						        		 } 
						        		 
						        		 
						        	 }
						        	 						        	 
						        	 StockPF stockpfOffreTherres=null;
						        	 StockPF stockpfOffreVerres=null;
						        	 StockPF stockpfOffreArticlepromo=null;
						        	 boolean articlepromoexiste=false;
						        	 
						        	 if(offfrepromo==true)
						        		 
						        	 {
						        		 
						        		 if(txtquantitetherres.getText().equals("") && txtquantiteverres.getText().equals("") && txtquantitepromo.getText().equals(""))
		  				 					{
		  				 						JOptionPane.showMessageDialog(null, "Veuillez saisir quantite des Offres SVP","Erreur",JOptionPane.ERROR_MESSAGE);
		  										return;
		  				 					}
						        		 
						        		 
						        		 
						        		 if(!txtquantitetherres.getText().equals("") )
		  				 					{
						        			 
						        			 if( ((new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)<0)))
						        			 {
						        				 
						        				 JOptionPane.showMessageDialog(null, "la quantite des Therres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			  										return;
			  										
						        			 }
		  				 						
		  				 						
		  				 					}
						        		 
						        		 
						        		 if(!txtquantiteverres.getText().equals("") )
		  				 					{
						        			 if( ((new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)<0)))
						        			 {
						        				 
						        					JOptionPane.showMessageDialog(null, "la quantite des Verres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			  										return; 
						        				 
						        			 }
						        			 
		  				 					
		  				 						
		  				 					}
						        		 
						        		 
						        		if(!txtquantitepromo.getText().equals("") )
		  				 					{
						        			
						        			if(((new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)<0)))
						        			{

		  				 						JOptionPane.showMessageDialog(null, "la quantite de'Article Promo doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
		  										return;	
						        				
						        			}
						        			
		  				 						
		  				 					}
		  				 						
		  				 						
		  				 					
		  				 						
		  				 						
		  				 						
		  				 						
		  				 						
		  				 						
		  				 						
		  				 						
		  				 				
								        	
		  				 				
						        		 
						        		 
						        	 }
						        	 
						        	 
						        	
						        	 
						        	
						        		 if(!txtquantitepacket.getText().equals(""))
						        		 {
						        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
						        		 }
						        		 if(!txtquantite.getText().equals(""))
						        		 {
						        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
						        		 }
						        		 
						        		 
						        		 DetailHistoriqueVenteVendeur detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
					        			 
					        		
								        		 
								        		
								        	
								        	 
							        		 
								        
								        	 
								        	 if(!txtreduction.getText().equals(""))
									          {
									        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
									          }else
									          {
									        	  detailFacture.setReduction(BigDecimal.ZERO);  
									          }
									          detailFacture.setArticle(article);
									          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
									  
									        	  if(checkttc.isSelected()==true)
									        	  {
									        		  detailFacture.setPrixUnitaire(prixTTC);
									        		  
											           detailFacture.setBrutHT ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
											           
											        
												       detailFacture.setSousFamille(sousfamille);
												       if(!txtreduction.getText().equals(""))
												          {
												    	   if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
												    		   
												    		   detailFacture.setRemiseCommerciale((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
												    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												    		   detailFacture.setMontantTVA(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA));
												    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
												    		   detailFacture.setMontantTTC(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)) );  

												    	   }else
												    	   {
												    		   detailFacture.setRemiseCommerciale((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
												    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
												    		   detailFacture.setTva(BigDecimal.ZERO);
                                                                detailFacture.setMontantTTC((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

												    	   }
												          }else
												          {
												        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
												        		  
													    		   detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
													    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
													    		   detailFacture.setMontantTVA(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP));
													    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
													    		   detailFacture.setMontantTTC(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP)) );

												        	  }else
												        	  {
												        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
												        		  detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
													    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
													    		   detailFacture.setTva(BigDecimal.ZERO);
													    		   detailFacture.setMontantTTC((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));


												        	  }
												          }  
									        	  }else
									        	  {
									        		  detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
									        		  
											           detailFacture.setBrutHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
											         
											           
											         
											           
											           
												       detailFacture.setSousFamille(sousfamille);
												       if(!txtreduction.getText().equals(""))
												          {
												    	   if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
												    		   
												    		   detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
												    		   detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												    		   detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP) );
												    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
												    		   detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).add(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP) ));  
  
												    	   }else
												    	   {
												    		   detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));

												    		   detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
												    		   detailFacture.setTva(BigDecimal.ZERO);
												    		   detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
  
												    	   }
												          }else
												          {
												        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
												        		  
												        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
												        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
												        		  detailFacture.setMontantTVA((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP));
												        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
												        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP)) );
  
												        	  }else
												        	  {
												        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
												        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
												        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
												        		  detailFacture.setTva(BigDecimal.ZERO);
												        		  detailFacture.setMontantTTC(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));

												        	  }
												          }  
									        	  }
									        	 
									        	  
									         
									          
									         //  detailFacture.setTypeVente(typevente);
									          
									           detailFacture.setHistoriqueventevendeur(historiqueVentevendeur);
									       //    detailFacture.setDateCreation(new Date());
									           
									           detailFacture.setUtilisateur(utilisateur);
									         
									           listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
									       
									 
											
											 if(offfrepromo==true)
								        	 {
												
												 boolean offre1=false , offre2=false;
												 
												 
												 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
												 {
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
													 {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
														 {
															 
															 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
															
															 offre1=true;
														 }
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
														 {
															 
															 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
															
															 offre1=true;
														 }
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
														 {
															 
															 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
															 
															 offre1=true;
														 }
														 
														 
														 
														 
													 }
													 
													 
												 }
												 
												 if(offre1==true)
												 {
													 
													 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
													 {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
														 {
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																
																 offre2=true;
															 }
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																
																 offre2=true;
															 }
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																
																 offre2=true;
															 }
															 
															 
															 
															 
														 }
														 
														 
													 } 
													 
													 
													 
													 
												 }
												 
												 if(offre1==true && offre2==true)
												 {
													 
													 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
													 {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
														 {
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																 
																
															 }
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																
																 
															 }
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																
																
															 }
															 
															 
															 
															 
														 }
														 
														 
													 } 
													 
													 
													 
													 
												 }
												 
												 DetailHistoriqueVenteVendeur historiqueVenteVendeur=listDetailFacturePF.get(tableArticle.getSelectedRow());
													
												 ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
												 if(!txtquantitetherres.getText().equals(""))
												 {
													 
											SousFamilleArticlePF	sousfamilleoffretherre=	 mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
													 Articles articletherre=mapArticleOffre.get(comboBoxtherres.getSelectedItem());
													 
													 DetailHistoriqueVenteVendeur detailFacturetherres= new DetailHistoriqueVenteVendeur();
													 detailFacturetherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
													 detailFacturetherres.setArticle(articletherre);
													 DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articletherre.getId(), magasin.getId(),sousfamilleoffretherre.getFamileArticlePF().getId() , sousfamilleoffretherre.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
					  	  		  				 		if(detailprixarticle!=null)
					  	  		  				 			{
					  	  		  				 		detailFacturetherres.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 			 
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 				
					  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articletherre.getId(), magasin.getId(),sousfamilleoffretherre.getFamileArticlePF().getId() , sousfamilleoffretherre.getId());
						
					  	  		  				 			if(detailprixarticleTmp!=null)	
					  	  		  				 			{
					  	  		  				 			detailFacturetherres.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 				 
					  	  		  				 				
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 			detailFacturetherres.setPrixUnitaire(BigDecimal.ZERO);
					  	  		  				 		 
					  	  		  				 			
					  	  		  				 			}
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 			}
									        		 
					  	  		  				 	detailFacturetherres.setMontantHT(BigDecimal.ZERO);
					  	  		  				detailFacturetherres.setMontantTVA(BigDecimal.ZERO);
					  	  		  			detailFacturetherres.setTva(BigDecimal.ZERO);
									        		 
					  	  		  		detailFacturetherres.setReduction(new BigDecimal(100));
					  	  		  	detailFacturetherres.setMontantTTC(BigDecimal.ZERO);  
					  	  		detailFacturetherres.setRemiseCommerciale(detailFacturetherres.getPrixUnitaire().multiply(detailFacturetherres.getQuantite()));
					  	  	detailFacturetherres.setBrutHT(detailFacturetherres.getPrixUnitaire().multiply(detailFacturetherres.getQuantite()));
												 
													 detailFacturetherres.setSousFamille(sousfamilleoffretherre);
													 detailFacturetherres.setHistoriqueventevendeur(historiqueVenteVendeur.getHistoriqueventevendeur());
													
													 listDetailFacturePF.add (detailFacturetherres);
											
													 
													 
													 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
													 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+1));
													 listDetailFacturePF.set(tableArticle.getSelectedRow()+1, detailFacturePF);
													 
													
													 
													 
												 }
												 
													
												 if(!txtquantiteverres.getText().equals(""))
												 {
													 Articles articleverres=mapArticleOffre.get(comboBoxverres.getSelectedItem());
													 SousFamilleArticlePF	sousfamilleoffreVerre=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
													 DetailHistoriqueVenteVendeur detailFactureVerres= new DetailHistoriqueVenteVendeur();
													 detailFactureVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
													 detailFactureVerres.setArticle(articleverres);
													
													 
													 DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articleverres.getId(), magasin.getId(),sousfamilleoffreVerre.getFamileArticlePF().getId() , sousfamilleoffreVerre.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
					  	  		  				 		if(detailprixarticle!=null)
					  	  		  				 			{
					  	  		  				 		detailFactureVerres.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 			 
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 				
					  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articleverres.getId(), magasin.getId(),sousfamilleoffreVerre.getFamileArticlePF().getId() , sousfamilleoffreVerre.getId());
						
					  	  		  				 			if(detailprixarticleTmp!=null)	
					  	  		  				 			{
					  	  		  				 			detailFactureVerres.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 				 
					  	  		  				 				
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 			detailFactureVerres.setPrixUnitaire(BigDecimal.ZERO);
					  	  		  				 				
					  	  		  				 		 
					  	  		  				 			
					  	  		  				 			}
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 			}
									        		 
					  	  		  				 	detailFactureVerres.setMontantHT(BigDecimal.ZERO);
									        		 detailFactureVerres.setMontantTVA(BigDecimal.ZERO);
									        		 detailFactureVerres.setTva(BigDecimal.ZERO);
									        		 detailFactureVerres.setReduction(new BigDecimal(100));
									        		 detailFactureVerres.setMontantTTC(BigDecimal.ZERO);  
									        		 detailFactureVerres.setRemiseCommerciale(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
									        		 detailFactureVerres.setBrutHT(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
												 

															
													 detailFactureVerres.setSousFamille(sousfamilleoffreVerre);
													 detailFactureVerres.setHistoriqueventevendeur (historiqueVenteVendeur.getHistoriqueventevendeur());
													
													 listDetailFacturePF.add (detailFactureVerres);
												
													
													 
													 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO"))
													 {
														 
														 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
														 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+1));
														 listDetailFacturePF.set(tableArticle.getSelectedRow()+1, detailFacturePF);
														 


														 
														 
														 
														 
													 }else {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
														 {
															 
															 
															 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
															 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+2));
															 listDetailFacturePF.set(tableArticle.getSelectedRow()+2, detailFacturePF);
															 
															 
															 
															 
															 
														 }
														 
													 }
													 
													 
													
													 
												 }			
															
										
												 if(!txtquantitepromo.getText().equals(""))
												 {
													 
													 
													 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
													 
													 DetailHistoriqueVenteVendeur detailFacturepromo= new DetailHistoriqueVenteVendeur();
													 detailFacturepromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));
													 detailFacturepromo.setArticle(articlespromo);
													 
													 detailFacturepromo.setPrixUnitaire(BigDecimal.ZERO);
													 
													 DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
					  	  		  				 		if(detailprixarticle!=null)
					  	  		  				 			{
					  	  		  				 		detailFacturepromo.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 			 
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 				
					  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
						
					  	  		  				 			if(detailprixarticleTmp!=null)	
					  	  		  				 			{
					  	  		  				 			detailFacturepromo.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
					  	  		  				 				 
					  	  		  				 				
					  	  		  				 			}else
					  	  		  				 			{
					  	  		  				 				
					  	  		  				 			detailFacturepromo.setPrixUnitaire(BigDecimal.ZERO);
					  	  		  				 			
					  	  		  				 			}
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 				
					  	  		  				 			}
						
					  	  		  				 	detailFacturepromo.setMontantHT(BigDecimal.ZERO);
					  	  		  				detailFacturepromo.setMontantTVA(BigDecimal.ZERO);
					  	  		  			detailFacturepromo.setTva(BigDecimal.ZERO);
					  	  		  		detailFacturepromo.setSousFamille(sousfamille);
					  	  		  	detailFacturepromo.setReduction(new BigDecimal(100));
					  	  		detailFacturepromo.setMontantTTC(BigDecimal.ZERO);  
					  	  	detailFacturepromo.setBrutHT(detailFacturepromo.getQuantite().multiply(detailFacturepromo.getPrixUnitaire()));
					  	  detailFacturepromo.setRemiseCommerciale(detailFacturepromo.getQuantite().multiply(detailFacturepromo.getPrixUnitaire()));
											
													 detailFacturepromo.setSousFamille(sousfamille);
													 detailFacturepromo.setHistoriqueventevendeur (historiqueVenteVendeur.getHistoriqueventevendeur());
													
													 listDetailFacturePF.add (detailFacturepromo);
													 
												
													 
													 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 
														 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
														 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+1));
														 listDetailFacturePF.set(tableArticle.getSelectedRow()+1, detailFacturePF);
														 
														
														 
														 
														 
													 }else {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
														 {
															 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
																 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+2));
																 listDetailFacturePF.set(tableArticle.getSelectedRow()+2, detailFacturePF);
																 
															
																 
															 }else
															 {
																 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3)!=null)
																 {
																	 
																	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
																	 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+3));
																	 listDetailFacturePF.set(tableArticle.getSelectedRow()+3, detailFacturePF);
																	 
																	
																	 
																 }
																 
																 
															 }
															 
															
															 
															 
														 }
														 
													 }
													 
												 }	
												 
								        	 }
											
											
											
									          afficher_tableDetailFacturePF(listDetailFacturePF);
									          int i=0;
										        BigDecimal montanttotal=BigDecimal.ZERO;
										        BigDecimal sommequantite=BigDecimal.ZERO;
										        BigDecimal montanttotalHT=BigDecimal.ZERO;
										        BigDecimal montanttotalTVA=BigDecimal.ZERO;
										        BigDecimal lamarge=BigDecimal.ZERO;
										        BigDecimal compteur=BigDecimal.ZERO;
										        while(i<listDetailFacturePF.size())
										        {
										        	
										        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
											          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
											          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
											          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
											          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
											     
											          
										            i++;
										        }
										       txttotalmontantTTC.setText(montanttotal+"");
										        txttotalquantite.setText(sommequantite+"");
										        txttotalmontantHT.setText(montanttotalHT+"");
									  			txttotalmontantTVA.setText(montanttotalTVA+"");


									  			
									  			
									  			
												
							        		    initialiser();
							        	
							        	
						        		 
						        	
						        	   }else
							       {
							    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							    	   return;
							       }
							       
						         }
						        
							}
							
				   		
		   				
		   			}
		   			
		   			
		   			
		   		} catch (NumberFormatException e) {
		   			
		   			
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		BigDecimal stocktmp=BigDecimal.ZERO;
		  		BigDecimal stockOffreTherre=BigDecimal.ZERO;
		  		BigDecimal stockOffreVerre=BigDecimal.ZERO;
		  		boolean trouve=false;
		  	
		  		
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			
		  			
		  				
		  				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'article dans la facture  ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
								
								
							{
								
								Articles articleOffre=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
					       		if(articleOffre!=null)
					       		{
					       			if(articleOffre.getCodeArticle().equals(Constantes.OFFRE_THERRES)  || articleOffre.getCodeArticle().equals(Constantes.OFFRE_VERRES))
						       		{
						       			
						       			JOptionPane.showMessageDialog(null, "Veuillez Selectionner Article Promo SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
						       			
						       			
						       		}
					       		}
								
					       		if(tableArticle.getSelectedRow()-3>=0)
								{
					       			
					       			if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()-3).getArticle().getCodeArticle()+"_PFO"))
					       			{
					       				
					       				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Article Promo SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
					       				
					       				
					       			}
					       			
					       			
								}
					       		
								
								 Articles article=listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle();
							
							
					        	 boolean offfrepromo =false ; 
					        	 
					        	 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5()!=null)
		  				 			{
		  				 			// centrecout5 = article promotion ou non
		  				 				if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
		  				 				{
		  				 					if(article.getCentreCout5()!=null)
		  				 					{
		  				 						if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
		  				 						{
		  				 									offfrepromo=true;
		  				 								
		  				 						}
		  				 						
		  				 						
		  				 					}
		  				 					 
		  				 					
		  				 				}
					        	
		  				 			}
					        	 
					        	 if(offfrepromo==true)
					        	 {
					        		 
					        		boolean offre1=false , offre2=false; 
					        		 
					        		 
					        		 
					        		 
					        		 if(tableArticle.getRowCount()-1>=tableArticle.getSelectedRow()+1)
					        		 {
					        			 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
					        			 {
					        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
						        			 {
						        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
									        		{
									        			
				/////////////////////////////////////////////////////// Therres ////////////////////////////////////////////////
										        		 
							        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
									        			
										 offre1=true;
									        			
									        		} 
						        			 }
					        				 
					        			 }
					        			 
					        		
					        		  		
					        			 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
					        			 {
					        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
						        			 {
						        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
									        		{
									        			
				/////////////////////////////////////////////////////// Verres ////////////////////////////////////////////////
										        		 
						                          listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
						               
						                offre1=true;
						                
									        		} 
						        			 }
					        			 }
					        			 
					        		
					        			 
					        			 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
					        			 {
					        				 
					        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
						        			 {
						        					if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
									        		{
				/////////////////////////////////////////////////////// Article Promo ////////////////////////////////////////////////
										        		
						        				
						     	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
						     	               
						     	               offre1=true;
									        		} 
						        			 }
					        			 }
					        			 
					        			

							        	
					        			 
					        		 }
                                     if( offre1==true)
	                                       {
	                                    	 
	                                   		 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
	    					        		 {
	                                   			 
	                                   			 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
	    					        			 {
	                                   				 
	                                   	 			if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
	          					        			 {
	                                          				
	                                          				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
	           							        		{
	           							        			
	           		/////////////////////////////////////////////////////// Therres ////////////////////////////////////////////////
	           								        		 
	           				        		
	           					        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
	           								
	           								 offre2=true;
	           							        			
	           							        			
	           							        		}
	                                          				
	          					        			 }
	                                   				 
	    					        			 }
	                                   			 
	                                   			if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
	   					        			 {
	                                   				
	                                   				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
	         					        			 {
	                                      				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
	       							        		{
	       							        			
	       		/////////////////////////////////////////////////////// Verres ////////////////////////////////////////////////
	       								        		 
	       				            
	       		  	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
	       				              
	       				                
	       				           	 offre2=true;
	       				                
	       							        		}
	                                      				
	         					        			 }	
	                                   				
	   					        			 }
	    					        		  	
	                                   			if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
	      					        			 {
	                                   				
	                                   				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
	        					        			 {
	                                      				
	                                      				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
	       							        		{
	       		/////////////////////////////////////////////////////// Article Promo ////////////////////////////////////////////////
	       								        		
	       				        				
	       				     	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
	       				     	              
	       				     	                
	       				     	          	 offre2=true;
	       				     	                
	       							        		} 
	                                      				
	        					        			 }
	                                   				
	      					        			 }
	    							        		 
	                                   	
	
	    							        	
	    					        			 
	    					        		 }
		
		
	                                          }
					      
					     if(offre1==true && offre2==true)
					     {
					    	 
							 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
			        		 {
								 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
				        			 {
									 
									 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
				        			 {
									 
								 		if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
						        		{
						        			
	/////////////////////////////////////////////////////// Therres ////////////////////////////////////////////////
							        		 
			        		 
				        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
							
							
						        			
						        			
						        		}
									 
				        			 }
									 
				        			 }
								 
								 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
			        			 {
									 
									 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
				        			 {
										 
											if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
							        		{
							        			
		/////////////////////////////////////////////////////// Verres ////////////////////////////////////////////////
								        		 
				               
		  	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
				               
				                
				           	
				                
							        		} 
				        			 }
			        			 }
			        		 
								 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
			        			 {
									 
									 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
				        			 {
										 
											if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
							        		{
		/////////////////////////////////////////////////////// Article Promo ////////////////////////////////////////////////
								        		
				        				
				     	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
				     	              
				     	                
				     	          	
				     	                
							        		} 
											
				        			 }
									 
			        			 }
					        		 
						

					        	
			        			 
			        		 }
					    	 
					    	 
					     }

					     				
					        	   
					                
					        	 }
					        	 
					        	 
					        	 
					        	 
					  		listDetailFacturePF.remove(tableArticle.getSelectedRow());
					  	
					  		
					  		
					  		
					  		
					  		
					  		
					        afficher_tableDetailFacturePF(listDetailFacturePF);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        BigDecimal lamarge=BigDecimal.ZERO;
						        BigDecimal compteur=BigDecimal.ZERO;
						        
						        while(i<listDetailFacturePF.size())
						        {

						        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
							          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
							          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());


						        	
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						       txttotalquantite.setText(sommequantite+"");
					  			txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");


					  			JOptionPane.showMessageDialog(null, "Article supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  			initialiser();
							}
		  			
		  		}
		  		
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1057, 564, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(775, 830, 105, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(899, 830, 134, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(658, 760, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(551, 760, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1057, 534, 73, 24);
		add(btnModifier);
		
		JLabel lblTotalMontantHt = new JLabel("Total Montant HT :");
		lblTotalMontantHt.setBounds(775, 761, 105, 26);
		add(lblTotalMontantHt);
		
		txttotalmontantHT = new JTextField();
		txttotalmontantHT.setEditable(false);
		txttotalmontantHT.setColumns(10);
		txttotalmontantHT.setBounds(899, 761, 134, 26);
		add(txttotalmontantHT);
		
		JLabel lblTotalMontantTva = new JLabel("Total Montant TVA :");
		lblTotalMontantTva.setBounds(775, 793, 105, 26);
		add(lblTotalMontantTva);
		
		txttotalmontantTVA = new JTextField();
		txttotalmontantTVA.setEditable(false);
		txttotalmontantTVA.setColumns(10);
		txttotalmontantTVA.setBounds(899, 793, 134, 26);
		add(txttotalmontantTVA);
		 
		  checkboxGratuits = new JCheckBox("Gratuit");
		 checkboxGratuits.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(checkboxGratuits.isSelected()==true)
		 		{	
		 		labelpourcentage.setVisible(false);
		 		txtPrix.setVisible(false);
		 		txtreduction.setVisible(false);
		 		txtmontant.setVisible(false);
		 		lblMontant.setVisible(false);
		 		lblReduction.setVisible(false);	
		 		lblPrix.setVisible(false);	
		 		checkttc.setVisible(false);	
		 		comboArticle.setSelectedIndex(-1);
		 		comboArticle.removeAllItems();
		 		labelmarge.setText("");
    			labelpourcentage.setText("");
    			labelPrixMax.setText("");
    			labelprixmin.setText("");
		 		initialiserGratuit();
		 		}else if(checkboxGratuits.isSelected()==false)
		 		{
		 			labelpourcentage.setVisible(false);
			 		txtPrix.setVisible(true);
			 		txtreduction.setVisible(true);
			 		txtmontant.setVisible(true);
			 		lblMontant.setVisible(true);
			 		lblReduction.setVisible(true);	
			 		lblPrix.setVisible(true);	
			 		checkttc.setVisible(true);	
			 		comboArticle.setSelectedIndex(-1);
			 		comboArticle.removeAllItems();
			 		labelmarge.setText("");
	    			labelpourcentage.setText("");
	    			labelPrixMax.setText("");
	    			labelprixmin.setText("");
		 		    initialiserGratuit();
		 		}
		 		
		 		
		 	}
		 });
		 checkboxGratuits.setBounds(1047, 221, 83, 24);
		 add(checkboxGratuits);
		
		                
 

		       		 
		 
				// stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
		     
		       		 
		       		   
		       		    layerArticle = new JLayeredPane();
		       		    layerArticle.setBounds(20, 221, 1021, 163);
		       		    add(layerArticle);
		       		    layerArticle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		              
		              // comboArticle.addItem("");
		               
		              
		                
               
		                JLayeredPane layerArticleGratuit = new JLayeredPane();
		                layerArticleGratuit.setBounds(0, 0, 1021, 163);
		                layerArticle.add(layerArticleGratuit);
		                layerArticleGratuit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	

		    		                
		    		                   combofamille = new JComboBox();
		    		                   combofamille.setBounds(91, 12, 167, 24);
		    		                   layerArticleGratuit.add(combofamille);
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
		    		                   combofamille.addItem("");
		    		                   
		    		                   txtquantite = new JTextField();
		    		                   txtquantite.setBounds(36, 56, 99, 26);
		    		                   layerArticleGratuit.add(txtquantite);
		    		                   util.Utils.copycoller(txtquantite);
		    		                   txtquantite.addKeyListener(new KeyAdapter() {
		    		                   	@Override
		    		                   	public void keyPressed(KeyEvent e) {
		    		                   		

		    		                   		if(e.getKeyCode()==e.VK_ENTER)
			      		{
		    		                   			
		    		                      			try {
			    		                   				Articles article=mapArticle.get(comboArticle.getSelectedItem());
			    		                   				if(article!=null)
			    		                   				{
			    		                   	 				if(!txtPrix.getText().equals(""))
				    		                   				{
				    		                   					if(!txtquantite.getText().equals(""))
				    		                   					{
				    		                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
				    		                   						{
				    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
						      					{
				    		                   								
				    		                   								if(!txtquantitepacket.getText().equals(""))
				    		    		                   					{
				    		    		                   						if(new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)>0)
				    		    		                   						{
				    		    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
				    				      					{
				    		    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement())))+"");					
				    		    		                   								
				    				      					}
				    		    		                   						}}else
				    		    		                   						{
				    		    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");
				    		    		                   						}
						      							
						      						
						      						
						      					}
				    		                   							
				    		                   						}
				    		                   						
				    		                   					}
				    		                   					
				    		                   					
				    		                   						      					
				    		                   				}
				    		                   				
			    		                   				}
			    		                   				
			    		                   				
			    		                   				if(article.getCentreCout5()!=null)
			    		      				 			{
			    		      				 			// centrecout5 = article promotion ou non
			    		      				 				if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && checkboxGratuits.isSelected()==false )
			    		      				 				{
			    		      				 					
			    		      				 					BigDecimal quantitetherres=BigDecimal.ZERO;
			    		      				 					if(article.getCentreCout3()!=null)
			    		      				 					{
			    		      				 						quantitetherres=new BigDecimal(txtquantite.getText()).divide(article.getCentreCout3(), 6, RoundingMode.HALF_UP) ; // quantitetherre= quantitearticle / le poid de carton 
			    		      				 						
			    		      				 						quantitetherres=quantitetherres.subtract(quantitetherres.remainder(BigDecimal.ONE));
			    		      				 					
			    		      				 					txtquantitetherres.setText(quantitetherres.toString());
			    		      				 					
			    		      				 					txtquantiteverres.setText(quantitetherres.multiply(new BigDecimal(2)).toString());
			    		      				 					
			    		      				 					if(mapArticlePromo.get(comboBoxPromo.getSelectedItem())!=null)
			    		      				 					{
			    		      				 						
			    		      				 						txtquantitepromo.setText(quantitetherres.divide(article.getConditionnement(), 6, RoundingMode.HALF_UP).toString());
			    		      				 						
			    		      				 					}
			    		      				 					
			    		      				 					
			    		      				 					}
			    		      				 					
			    		      				 					
			    		      				 					
			    		      				 					
			    		      				 					
			    		      				 					
			    		      				 					
			    		      				 					
			    		      				 				}
			    		                   				
			    		                   				
			    		      				 			}
			    		                   				
			    		                   				
			    		                   				
			    		                   				
			    		                   				
			    		                   				
			    		                   				
			    		                   				
			    		                  
			    		                   				
								
							} catch (NumberFormatException e2) {JOptionPane.showMessageDialog(null, "Veuillez entrer les chiffres pour la quantité et le montant SVP !!!!!");
								// TODO: handle exception
							}
		    		                   			
		    		                   			
		    		         
		    		                   			
		    		                   			
		    		                   			
			      		}
		    		                   		
		    		                   		
		    		                   	
		     		
		    		                   		
		    		                   	}
		    		                   });
		    		                   txtquantite.setColumns(10);
		    		                   
		    		                    lblPrix = new JLabel("Prix  :");
		    		                    lblPrix.setBounds(322, 56, 36, 26);
		    		                    layerArticleGratuit.add(lblPrix);
		    		                    
		      
		    		                    
		    		                    JLabel labelFamilleArticle = new JLabel("Famille Article :");
		    		                    labelFamilleArticle.setBounds(0, 11, 87, 24);
		    		                    layerArticleGratuit.add(labelFamilleArticle);
		    		                    labelFamilleArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    		                    
		    		                    
		    		                    
		    		                    
		       
		       JLabel lblSousFamille = new JLabel("Sous Famille :");
		       lblSousFamille.setBounds(268, 11, 87, 24);
		       layerArticleGratuit.add(lblSousFamille);
		       lblSousFamille.setFont(new Font("Tahoma", Font.PLAIN, 12));
		       
		       
		
 		
		       
		       
		       
		       
		       
		           combosousfamille = new JComboBox();
		           combosousfamille.setBounds(342, 12, 167, 24);
		           layerArticleGratuit.add(combosousfamille);
		           
		           txtcodearticle = new JTextField();
		           txtcodearticle.setBounds(590, 11, 112, 26);
		           layerArticleGratuit.add(txtcodearticle);
		           util.Utils.copycoller(txtcodearticle);
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
  					    			comboArticle.setSelectedItem(article.getLiblle());
  					    			
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
		           
		             JLabel labellCodeArticle = new JLabel("Code Article :");
		             labellCodeArticle.setBounds(519, 11, 82, 26);
		             layerArticleGratuit.add(labellCodeArticle);
		             labellCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		             
		             JLabel labelarticle = new JLabel("Libelle :");
		             labelarticle.setBounds(712, 11, 57, 26);
		             layerArticleGratuit.add(labelarticle);
		             labelarticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		             
		             txtreduction = new JTextField();
		             txtreduction.setBounds(794, 56, 112, 26);
		             layerArticleGratuit.add(txtreduction);
		             txtreduction.setColumns(10);
		             
		              labelpourcentage = new JLabel("%");
		              labelpourcentage.setBounds(916, 56, 26, 26);
		              layerArticleGratuit.add(labelpourcentage);
		              labelpourcentage.setFont(new Font("Tahoma", Font.BOLD, 11));
		              
		               lblReduction = new JLabel("Remise :");
		               lblReduction.setBounds(736, 56, 57, 26);
		               layerArticleGratuit.add(lblReduction);
		               
		     
		               
		                lblMontant = new JLabel("Montant  :");
		                lblMontant.setBounds(527, 56, 64, 26);
		                layerArticleGratuit.add(lblMontant);
		                
		                txtmontant = new JTextField();
		                txtmontant.setBounds(601, 56, 125, 26);
		                layerArticleGratuit.add(txtmontant);
		                txtmontant.setEditable(false);
		                txtmontant.setColumns(10);
		                
		                txtPrix = new JTextField();
		                txtPrix.setBounds(368, 56, 135, 26);
		                layerArticleGratuit.add(txtPrix);
		                txtPrix.addKeyListener(new KeyAdapter() {
		                	@Override
		                	public void keyPressed(KeyEvent e) {
		                		

		                   		if(e.getKeyCode()==e.VK_ENTER)
      		{
		                   			
		                   			
		                       			try {
			                   				Articles article=mapArticle.get(comboArticle.getSelectedItem());
			                   				if(article!=null)
			                   				{
			                   	 				if(!txtPrix.getText().equals(""))
	    		                   				{
	    		                   					if(!txtquantite.getText().equals(""))
	    		                   					{
	    		                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
	    		                   						{
	    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
			      					{
	    		                   								
	    		                   								if(!txtquantitepacket.getText().equals(""))
	    		    		                   					{
	    		    		                   						if(new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)>0)
	    		    		                   						{
	    		    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
	    				      					{
	    		    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement())))+"");					
	    		    		                   								
	    				      					}
	    		    		                   						}}else
	    		    		                   						{
	    		    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");
	    		    		                   						}
			      							
			      						
			      						
			      					}
	    		                   							
	    		                   						}
	    		                   						
	    		                   					}
	    		                   					
	    		                   					
	    		                   						      					
	    		                   				}
			                   	 				
			                   	 				
			                   	 			if(!txtPrix.getText().equals(""))
			                   				{
			                   					if(!txtquantitepacket.getText().equals(""))
			                   					{
			                   						if(new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)>0)
			                   						{
			                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
		      					{
			                   								
			                   								if(!txtquantite.getText().equals(""))
			    		                   					{
			    		                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
			    		                   						{
			    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
					      					{
			    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement())))+"");					
			    		                   								
					      					}
			    		                   						}
			    		                   						}else
			    		                   						{
			    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
			    		                   						}
		      							
		      						
		      						
		      					}
			                   							
			                   						}
			                   						
			                   					}
			                   					
			                   					
			                   						      					
			                   				}
	    		                   				
			                   				}
			                   				
			                  
			                   				
					
				} catch (NumberFormatException e2) {JOptionPane.showMessageDialog(null, "Veuillez entrer les chiffres pour la quantité et le montant SVP !!!!!");
					// TODO: handle exception
				}
			      
		                   			
		             			
		                   			
		                   			
      		}	
		                	}
		                });
		                txtPrix.setColumns(10);
		                
		                JLabel lblQPacket = new JLabel("Q Packet :");
		                lblQPacket.setBounds(145, 56, 72, 26);
		                layerArticleGratuit.add(lblQPacket);
		                
		                txtquantitepacket = new JTextField();
		                txtquantitepacket.addKeyListener(new KeyAdapter() {
		                	@Override
		                	public void keyPressed(KeyEvent e) {
		                		

		                   		if(e.getKeyCode()==e.VK_ENTER)
      		{
		                   			
		                       			try {
			                   				Articles article=mapArticle.get(comboArticle.getSelectedItem());
			                   				if(article!=null)
			                   				{
			                   	 				if(!txtPrix.getText().equals(""))
	    		                   				{
	    		                   					if(!txtquantitepacket.getText().equals(""))
	    		                   					{
	    		                   						if(new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)>0)
	    		                   						{
	    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
			      					{
	    		                   								
	    		                   								if(!txtquantite.getText().equals(""))
	    		    		                   					{
	    		    		                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
	    		    		                   						{
	    		    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
	    				      					{
	    		    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement())))+"");					
	    		    		                   								
	    				      					}
	    		    		                   						}}else
	    		    		                   						{
	    		    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
	    		    		                   						}
			      							
			      						
			      						
			      					}
	    		                   							
	    		                   						}
	    		                   						
	    		                   					}
	    		                   					
	    		                   					
	    		                   						      					
	    		                   				}
	    		                   				
			                   				}
			                   				
			                  
			                   				
					
				} catch (NumberFormatException e2) {JOptionPane.showMessageDialog(null, "Veuillez entrer les chiffres pour la quantité et le montant SVP !!!!!");
					// TODO: handle exception
				}
		                   			
		        
		                   			
		                   			
		                   			
      		}
		          	
		                		
		                	}
		                });
		                txtquantitepacket.setColumns(10);
		                txtquantitepacket.setBounds(200, 56, 112, 26);
		                layerArticleGratuit.add(txtquantitepacket);
		                
		                 
		                     	
		                      
		                      JLabel labelQuantit = new JLabel("QU :");
		                      labelQuantit.setBounds(4, 56, 42, 26);
		                      layerArticleGratuit.add(labelQuantit);
		                      // combofamille.addItem("");
		       
		                           
	
	
		                           
		    		                         
		                      
		                           
		                            comboArticle = new JComboBox();
		                            comboArticle.setBounds(755, 11, 258, 27);
		                            layerArticleGratuit.add(comboArticle);
		                            comboArticle.setSelectedIndex(-1);
		                            comboArticle.addActionListener(new ActionListener() {
		                            	public void actionPerformed(ActionEvent arg0) {
		                            		
		     	 		
  		     	 	if(comboArticle.getSelectedIndex()!=-1)
  			 		{
  			 			if(!comboArticle.getSelectedItem().equals(""))
  				 		{
  			 				
  			 				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
  				 			Articles article=mapArticle.get(comboArticle.getSelectedItem());
  				 			SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
  				 			BigDecimal PrixMin=BigDecimal.ZERO;
  				 			BigDecimal PrixMax=BigDecimal.ZERO;
  				 			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
  				 			ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
  				 	//	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
  				 			String date="01/01/"+sdf.format(dateChooserfacture.getDate())+"";
  				 			String dateFin="31/12/"+sdf.format(dateChooserfacture.getDate())+"";
  				 			Date dateDebut= new Date(date);
  				 			Date dateFinAnne= new Date(dateFin);
  				 		
  				 
  				 	
  				 			
  				 			
  				 			
  				 			
  				 			// Traitement article Promo///////////////////////////
  				 			
  				 			if(article.getCentreCout5()!=null)
  				 			{
  				 			// centrecout5 = article promotion ou non
  				 				if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && checkboxGratuits.isSelected()==false  && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM)  )
  				 				{
  				 					
  				 					txtquantitepromo.setText("");
  				 					txtquantitetherres.setText("");
  				 					txtquantiteverres.setText("");
  				 					
  				 					comboBoxPromo.removeAllItems();
  				 					mapArticlePromo.clear();
  				 					Articles articlepromo=articleDAO.findByCode(article.getCodeArticle()+"_PFO");
  				 					if(articlepromo!=null)
  				 					{
  				 					
  				 						comboBoxPromo.addItem(articlepromo.getLiblle());
  	  				 					mapArticlePromo.put(articlepromo.getLiblle(), articlepromo);
  				 					 
  				 					 
  				 					 
  				 						
  				 					lblOffrePromo.setVisible(true);
  				 					comboBoxPromo.setVisible(true);
  				 					txtquantitepromo.setVisible(true);	
  				 					
  				 					
  				 					
  				 					
  				 					
  				 					}
  				 					
  				 					
  				 					Articles articlesTherresOffre=mapArticleOffreCode.get(Constantes.OFFRE_THERRES);
  				 					Articles articlesVerresOffre=mapArticleOffreCode.get(Constantes.OFFRE_VERRES);
  				 					SousFamilleArticlePF sousFamilleArticlePFOffreTherres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
  				 					SousFamilleArticlePF sousFamilleArticlePFOffreVerres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
  				 					 stockfinaleTherres=BigDecimal.ZERO;
  				 					 stockfinaleVerres=BigDecimal.ZERO;
  				 					
  				 					lblOffreTherre.setVisible(true);
  				 					comboBoxtherres.setVisible(true);
  				 					txtquantitetherres.setVisible(true);
  				 					lbloffreverres.setVisible(true);
  				 					comboBoxverres.setVisible(true);
  				 					txtquantiteverres.setVisible(true);
  				 					
  		
  		  					
  				 					
  				 			
  				 			
  				 			
  				 			
  				 				
  				 				}else if (article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && checkboxGratuits.isSelected()==false  &&  magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF) )
  				 				{
  				 					

  				 					
  				 					txtquantitepromo.setText("");
  				 					txtquantitetherres.setText("");
  				 					txtquantiteverres.setText("");
  				 					
  				 					comboBoxPromo.removeAllItems();
  				 					mapArticlePromo.clear();
  				 					Articles articlepromo=articleDAO.findByCode(article.getCodeArticle()+"_PFO");
  				 					if(articlepromo!=null)
  				 					{
  				 					
  				 						comboBoxPromo.addItem(articlepromo.getLiblle());
  	  				 					mapArticlePromo.put(articlepromo.getLiblle(), articlepromo);
  				 						
  				 					lblOffrePromo.setVisible(true);
  				 					comboBoxPromo.setVisible(true);
  				 					txtquantitepromo.setVisible(true);	
  				 					
  				 					
  				 					
  				 					
  				 					
  				 					}
  				 					
  				 					
  				 					Articles articlesTherresOffre=mapArticleOffreCode.get(Constantes.OFFRE_THERRES);
  				 					Articles articlesVerresOffre=mapArticleOffreCode.get(Constantes.OFFRE_VERRES);
  				 					SousFamilleArticlePF sousFamilleArticlePFOffreTherres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
  				 					SousFamilleArticlePF sousFamilleArticlePFOffreVerres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
  				 					 stockfinaleTherres=BigDecimal.ZERO;
  				 					 stockfinaleVerres=BigDecimal.ZERO;
  				 					
  				 					lblOffreTherre.setVisible(true);
  				 					comboBoxtherres.setVisible(true);
  				 					txtquantitetherres.setVisible(true);
  				 					lbloffreverres.setVisible(true);
  				 					comboBoxverres.setVisible(true);
  				 					txtquantiteverres.setVisible(true);
  			
  		  					
  				 				
  				 			
  				 			
  				 				
  				 				
  				 				}
  				 					
  				 				
  				 				else
  				 				{
  				 					lblOffreTherre.setVisible(false);
  				 					comboBoxtherres.setVisible(false);
  				 					txtquantitetherres.setVisible(false);
  				 					lbloffreverres.setVisible(false);
  				 					comboBoxverres.setVisible(false);
  				 					txtquantiteverres.setVisible(false);
  				 					txtquantitetherres.setText("");
  				 					txtquantiteverres.setText("");
  				 					lblOffrePromo.setVisible(false);
				 					comboBoxPromo.setVisible(false);
				 					txtquantitepromo.setVisible(false);	
				 					txtquantitepromo.setText("");
  				 					stockdisponibleoffretherres.setText("");		
  		  				 			stockdisponibleoffreverres.setText("");		
  		  				 		stockdisponiblearticlepromo.setText("");
  		  				 	txtquantitepromo.setText("");
			 					txtquantitetherres.setText("");
			 					txtquantiteverres.setText("");
			 					
  				 				}
  				 				
  				 				
  				 				
  				 			}
  				 			
  				 			
  				 			/////////////////////////////////
  				 			
  				 			
  				 			
  				 			
  				 			if(magasin!=null)
  				 			{
  				 				if(article!=null)
  	  				 			{
  				 					boolean trouve=false;
  				 					
  				 						if(article.getCentreCout2()!=null)
  				 						{
  				 							if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
  				 							{
  				 							trouve=true;
  				 							}
  				 						}
  				 					
  				 					if(trouve==true)
  				 					{
  				 						txtquantitepacket.setText("");
  				 						txtquantitepacket.setEnabled(true);
  				 					}else
  				 					{
  				 						txtquantitepacket.setText("");
  				 						txtquantitepacket.setEnabled(false);
  				 					}
  				 					
  				 					
  				 					
  	  				 				txtcodearticle.setText(article.getCodeArticle());
  	  				 				
  	  				 			 
  	  				 				
  	  	  				 			
  	  				 			DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
  	  		  				 			
  	  		  				 		
  	  		  				 		if(detailprixarticle!=null)
  	  		  				 			{
  	  		  				 				txtPrix.setText(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP)+"");
  	  		  				 			}else
  	  		  				 			{
  	  		  				 				
  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
	
  	  		  				 			if(detailprixarticleTmp!=null)	
  	  		  				 			{
  	  		  				 				
  	  		  				 			txtPrix.setText(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP)+"");
  	  		  				 				
  	  		  				 			}else
  	  		  				 			{
  	  		  				 				
  	  		  				 			txtPrix.setText(BigDecimal.ZERO+"");
  	  		  				 			
  	  		  				 			}
  	  		  				 				
  	  		  				 				
  	  		  				 				
  	  		  				 				
  	  		  				 			}
  	  		  				 			
  	  		  				 			txtmontant.setText("");
  	  		  				 		
  	  		  				 			


  	  		  				 				
  	  		  				 			
  	  		  				 		 
  	  		  				 		
  	  		  				 			
  	  		  				 	
  	  				 			}
  				 			}
  				 			
  				 					 			
  				 		}else
  				 		{
  				 			txtcodearticle.setText("");
  				 			stockdisponible.setText("");
  				 			
  				 		}
  				 	
  			 		}
  			 		
		                            	}
		                            });
		                            AutoCompleteDecorator.decorate(comboArticle);
		                            comboArticle.setSelectedIndex(-1);
		                            
		                             checkttc = new JCheckBox("TTC");
		                             checkttc.addActionListener(new ActionListener() {
		                             	public void actionPerformed(ActionEvent arg0) {
		                             		if(checkttc.isSelected()==true)
		                             		{
		                             			
		                             			if(!txtPrix.getText().equals(""))
		                             			{
		                             				prixTTC=new BigDecimal(txtPrix.getText());
		                             				  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
		                             					 txtPrix.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(1.2)).setScale(2, RoundingMode.CEILING)+"");
		                             					  
		                             				  }
		                             				
		                             			}

		                             		}else if(checkttc.isSelected()==false)
		                             		{
		                             			if(!txtPrix.getText().equals(""))
		                             			{
		                             				 if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
		                             					 
				                             				txtPrix.setText(new BigDecimal(txtPrix.getText()).divide(new BigDecimal(1.2),2, RoundingMode.CEILING)+"");

		                             				 }
		                             				
		                             			}
		                             		}
		                             		
		                             	}
		                             });
		                            checkttc.setBounds(948, 58, 67, 24);
		                            layerArticleGratuit.add(checkttc);
		                            
		                             comboBoxtherres = new JComboBox();
		                            comboBoxtherres.setSelectedIndex(-1);
		                            comboBoxtherres.setBounds(79, 94, 253, 27);
		                            layerArticleGratuit.add(comboBoxtherres);
		                            
		                            comboBoxtherres.setVisible(false);
		                            
		                            txtquantitetherres = new JTextField();
		                            txtquantitetherres.setColumns(10);
		                            txtquantitetherres.setBounds(342, 93, 110, 29);
		                            layerArticleGratuit.add(txtquantitetherres);
		                            
		                            txtquantitetherres.setVisible(false);
		                            
		                             comboBoxverres = new JComboBox();
		                            comboBoxverres.setSelectedIndex(-1);
		                            comboBoxverres.setBounds(547, 93, 266, 27);
		                            layerArticleGratuit.add(comboBoxverres);
		                            
		                            comboBoxverres.setVisible(false);
		                            
		                            txtquantiteverres = new JTextField();
		                            txtquantiteverres.setColumns(10);
		                            txtquantiteverres.setBounds(823, 93, 99, 29);
		                            layerArticleGratuit.add(txtquantiteverres);
		                            
		                            txtquantiteverres.setVisible(false);
		                            
		                             lblOffreTherre = new JLabel("Offre Therre :");
		                            lblOffreTherre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                            lblOffreTherre.setBounds(0, 94, 87, 24);
		                            layerArticleGratuit.add(lblOffreTherre);
		                            lblOffreTherre.setVisible(false);
		                             lbloffreverres = new JLabel("Offre Verres :");
		                            lbloffreverres.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                            lbloffreverres.setBounds(462, 94, 87, 24);
		                            layerArticleGratuit.add(lbloffreverres);
		                            
		                             lblOffrePromo = new JLabel("Offre Promo :");
		                            lblOffrePromo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                            lblOffrePromo.setBounds(0, 129, 87, 24);
		                            layerArticleGratuit.add(lblOffrePromo);
		                            lblOffrePromo.setVisible(false);
		                             comboBoxPromo = new JComboBox();
		                            comboBoxPromo.setSelectedIndex(-1);
		                            comboBoxPromo.setBounds(79, 129, 253, 24);
		                            layerArticleGratuit.add(comboBoxPromo);
		                            comboBoxPromo.setVisible(false);
		                            txtquantitepromo = new JTextField();
		                            txtquantitepromo.setColumns(10);
		                            txtquantitepromo.setBounds(342, 129, 110, 26);
		                            layerArticleGratuit.add(txtquantitepromo);
		                            txtquantitepromo.setVisible(false);
		                             stockdisponible = new JLabel("");
		                             stockdisponible.setHorizontalAlignment(SwingConstants.LEFT);
		                             stockdisponible.setFont(new Font("Tahoma", Font.BOLD, 9));
		                             stockdisponible.setForeground(Color.RED);
		                            stockdisponible.setBounds(1047, 260, 475, 69);
		                            add(stockdisponible);
		                            
		                             labelmarge = new JLabel("");
		                            labelmarge.setForeground(Color.RED);
		                            labelmarge.setFont(new Font("Tahoma", Font.BOLD, 14));
		                            labelmarge.setBounds(1047, 299, 600, 30);
		                            add(labelmarge);
		                            
		                             labelprixmin = new JLabel("");
		                            labelprixmin.setForeground(new Color(50, 205, 50));
		                            labelprixmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		                            labelprixmin.setBounds(1136, 213, 193, 38);
		                            add(labelprixmin);
		                            
		                             labelPrixMax = new JLabel("");
		                            labelPrixMax.setFont(new Font("Tahoma", Font.BOLD, 13));
		                            labelPrixMax.setForeground(new Color(255, 0, 0));
		                            labelPrixMax.setBounds(1339, 213, 193, 38);
		                            add(labelPrixMax);
		                            
		                             stockdisponibleoffretherres = new JLabel("");
		                            stockdisponibleoffretherres.setHorizontalAlignment(SwingConstants.LEFT);
		                            stockdisponibleoffretherres.setForeground(Color.RED);
		                            stockdisponibleoffretherres.setFont(new Font("Tahoma", Font.BOLD, 9));
		                            stockdisponibleoffretherres.setBounds(1047, 328, 600, 30);
		                            add(stockdisponibleoffretherres);
		                            
		                             stockdisponibleoffreverres = new JLabel("");
		                            stockdisponibleoffreverres.setHorizontalAlignment(SwingConstants.LEFT);
		                            stockdisponibleoffreverres.setForeground(Color.RED);
		                            stockdisponibleoffreverres.setFont(new Font("Tahoma", Font.BOLD, 9));
		                            stockdisponibleoffreverres.setBounds(1044, 385, 613, 30);
		                            add(stockdisponibleoffreverres);
		                            
		                             stockdisponiblearticlepromo = new JLabel("");
		                            stockdisponiblearticlepromo.setHorizontalAlignment(SwingConstants.LEFT);
		                            stockdisponiblearticlepromo.setForeground(Color.RED);
		                            stockdisponiblearticlepromo.setFont(new Font("Tahoma", Font.BOLD, 9));
		                            stockdisponiblearticlepromo.setBounds(1047, 411, 600, 30);
		                            add(stockdisponiblearticlepromo);
		                            
		                            JButton btnAjouterDetailTransfer = new JButton("Ajouter Detail Transfer");
		                            btnAjouterDetailTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				/*la modification à été effectué sur la base réel le 20.03.2020 à 12:32 aprés l'autorisation de fatma et abdelouahab
				 * 
				 * 
				 * FacturePF facturePF =
				 * facturepfdao.findFacturePFByCodeTransfer("VENTE_AGA_050919_3102_2961");
				 * TransferStockPF transferStockPF = new TransferStockPF();
				 * transferStockPF.setCodeTransfer(facturePF.getCodeTransfer());
				 * transferStockPF.setCreerPar(facturePF.getCreerPar());
				 * transferStockPF.setDate(facturePF.getDateSaisi());
				 * transferStockPF.setDateTransfer(facturePF.getDateFacture());
				 * transferStockPF.setStatut(ETAT_TRANSFER_STOCK_VENTE);
				 * transferStockPFDAO.add(transferStockPF); for(int i=0 ;
				 * i<facturePF.getDetailFacturePF().size();i++) {
				 * 
				 * DetailFacturePF detailFacturePF = facturePF.getDetailFacturePF().get(i);
				 * DetailTransferProduitFini detailTransferProduitFini=new
				 * DetailTransferProduitFini();
				 * detailTransferProduitFini.setArticle(detailFacturePF.getArticle());
				 * detailTransferProduitFini.setDateTransfer(facturePF.getDateFacture());
				 * detailTransferProduitFini.setMagasinDestination(facturePF.getMagasin());
				 * detailTransferProduitFini.setPrixUnitaire(detailFacturePF.getPrixUnitaire());
				 * detailTransferProduitFini.setQuantite(detailFacturePF.getQuantite());
				 * detailTransferProduitFini.setSousFamille(detailFacturePF.getSousFamille());
				 * detailTransferProduitFini.setTransferStockPF(transferStockPF);
				 * detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
				 * detailTransferStockPFdao.add(detailTransferProduitFini);
				 * 
				 * 
				 * }
				 * 
				 * 
				 * JOptionPane.showMessageDialog(null, "Modification Effectue Avec Succe");
				 * 
				 * 
				 * 
				 */}
		                            });
		                            btnAjouterDetailTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		                            btnAjouterDetailTransfer.setBounds(35, 807, 179, 24);
		                            add(btnAjouterDetailTransfer);
		                            btnAjouterDetailTransfer.setVisible(false);
		           
		            combosousfamille.addItemListener(new ItemListener() {
		            	@SuppressWarnings("unchecked")
						public void itemStateChanged(ItemEvent e) {
		            		

		            		 if(e.getStateChange() == ItemEvent.SELECTED)
		     	 		 {
		            			 if(!combosousfamille.getSelectedItem().equals(""))
		            			 {
		            				
		            				 SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
		            				 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		            				 if(magasin!=null)
		            				 {
		            					 if(sousfamille!=null)
				        			 {
				        				 comboArticle.removeAllItems();
				        				 comboArticle.addItem("");
				        				 listArticleStockPF=detailPrixArticleDAO.findDetaileArticleBySousFamilleByMagasin(sousfamille.getId(), magasin.getId()) ;
				        				 for(int i=0;i<listArticleStockPF.size();i++)
				        				 {
				        					 
				        					Articles article= listArticleStockPF.get(i).getArticles();
				        					
				        					if(sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
				        					{
				        						
				        						if(!article.getLiblle().contains("(OFFRE)"))
				        						{
				        							
						        					comboArticle.addItem(article.getLiblle());
						        					 mapArticle.put(article.getLiblle(), article);
						        					 mapCodeArticle.put(article.getCodeArticle(), article);
				        							
				        						}
				        						
				        						
				        					}else
				        					{

					        					comboArticle.addItem(article.getLiblle());
					        					 mapArticle.put(article.getLiblle(), article);
					        					 mapCodeArticle.put(article.getCodeArticle(), article);
				        						
				        					}
				        					
				        				 }
				        				 
				        			 }else
				        			 {
				        				 comboArticle.removeAllItems();
				        				 txtcodearticle.setText("");
				        			 }
			        				   
		            				 }
			        			
		            			 }else
		            			 {
		            				 comboArticle.removeAllItems();
		            				 txtcodearticle.setText("");
		            			 }
		            			  
		     	 		 }
		            		
		            		
		            		
		            	}
		            });
		    		                  

		     	   int p=0;
				      while(p<listFamilleArticle.size())
				      {
				    	  
				    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
				    	  combofamille.addItem(famillearticle.getLiblle());
				    	 
				    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
				    	  
				    	  
				    	  
				    	  
				    	  p++;
				      }	
				      
				      listArticleOffre =articleDAO.listeArticleByCodeOffre(Constantes.OFFRE_THERRES, Constantes.OFFRE_VERRES);
				      
				      listSousFamilleArticleOffre=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePFOffre(Constantes.SOUS_FAMILLE_OFFRE_THERRES, Constantes.SOUS_FAMILLE_OFFRE_VERRES);
				      
				      for(int i=0;i<listArticleOffre.size();i++)
				    	  
				      {
				    	  
				    	Articles articles=listArticleOffre.get(i)  ;
				    	
				    	if(articles.getCodeArticle().equals(Constantes.OFFRE_THERRES))
				    	{
				    		comboBoxtherres.addItem(articles.getLiblle());
				    		mapArticleOffre.put(articles.getLiblle(), articles);
				    		mapArticleOffreCode.put(articles.getCodeArticle(), articles);
				    	}else if(articles.getCodeArticle().equals(Constantes.OFFRE_VERRES))
				    	{
				    		comboBoxverres.addItem(articles.getLiblle());
				    		mapArticleOffre.put(articles.getLiblle(), articles);
				    		mapArticleOffreCode.put(articles.getCodeArticle(), articles);
				    	}
				    	
				    	
				    	  
				      }
				      
				      for(int i=0;i<listSousFamilleArticleOffre.size();i++)
				      {
				    	SousFamilleArticlePF sousFamilleArticlePF=  listSousFamilleArticleOffre.get(i);
				    	
				    	mapsousfamilleOffre.put(sousFamilleArticlePF.getCode(), sousFamilleArticlePF);
				    	  
				      }
				      
				      JLabel label = new JLabel("Timbre 0,25%       :");
				      label.setBounds(775, 863, 112, 26);
				      add(label);
				      
				      txttimbre = new JTextField();
				      txttimbre.setEditable(false);
				      txttimbre.setColumns(10);
				      txttimbre.setBounds(897, 863, 136, 26);
				      add(txttimbre);
				      
				      JLabel label_2 = new JLabel("Net A Payer         :");
				      label_2.setBounds(775, 900, 112, 26);
				      add(label_2);
				      
				      txtnetapayer = new JTextField();
				      txtnetapayer.setEditable(false);
				      txtnetapayer.setColumns(10);
				      txtnetapayer.setBounds(897, 900, 136, 26);
				      add(txtnetapayer);
				      
				      JLayeredPane layerModeReglement = new JLayeredPane();
				      layerModeReglement.setBounds(1195, 11, 543, 180);
				      add(layerModeReglement);
				      
				      txtnumtraite = new JTextField();
				      txtnumtraite.setBounds(409, 103, 115, 26);
				      layerModeReglement.add(txtnumtraite);
				      txtnumtraite.setColumns(10);
				      
				      txtnumversement = new JTextField();
				      txtnumversement.setBounds(409, 137, 115, 26);
				      layerModeReglement.add(txtnumversement);
				      txtnumversement.setColumns(10);
				      
				      JLabel label_6 = new JLabel("N\u00B0 Versement :");
				      label_6.setBounds(283, 137, 116, 24);
				      layerModeReglement.add(label_6);
				      label_6.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
				      
				      JLabel label_5 = new JLabel("N\u00B0 Traite :");
				      label_5.setBounds(283, 103, 116, 24);
				      layerModeReglement.add(label_5);
				      label_5.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
				      
				      txtespece = new JTextField();
				      txtespece.setBounds(133, 11, 140, 26);
				      layerModeReglement.add(txtespece);
				      txtespece.setColumns(10);
				      
				      txtvirement = new JTextField();
				      txtvirement.setBounds(133, 42, 140, 26);
				      layerModeReglement.add(txtvirement);
				      txtvirement.setColumns(10);
				      
				      txtcheque = new JTextField();
				      txtcheque.setBounds(133, 72, 140, 26);
				      layerModeReglement.add(txtcheque);
				      txtcheque.setColumns(10);
				      
				      txttraite = new JTextField();
				      txttraite.setBounds(133, 104, 140, 26);
				      layerModeReglement.add(txttraite);
				      txttraite.setColumns(10);
				      
				      txtversement = new JTextField();
				      txtversement.setBounds(133, 137, 140, 26);
				      layerModeReglement.add(txtversement);
				      txtversement.setColumns(10);
				      
				       checkVersement = new JCheckBox("versement");
				      checkVersement.setBounds(6, 138, 97, 23);
				      layerModeReglement.add(checkVersement);
				      checkVersement.setFont(new Font("Tahoma", Font.BOLD, 12));
				      
				       checkTraite = new JCheckBox("Traite");
				      checkTraite.setBounds(6, 105, 97, 23);
				      layerModeReglement.add(checkTraite);
				      checkTraite.setFont(new Font("Tahoma", Font.BOLD, 12));
				      
				       checkCheque = new JCheckBox("Ch\u00E9que");
				      checkCheque.setBounds(6, 73, 97, 23);
				      layerModeReglement.add(checkCheque);
				      checkCheque.setFont(new Font("Tahoma", Font.BOLD, 12));
				      
				       checkVirement = new JCheckBox("Virement");
				      checkVirement.setBounds(6, 42, 97, 23);
				      layerModeReglement.add(checkVirement);
				      checkVirement.setFont(new Font("Tahoma", Font.BOLD, 12));
				      
				       checkEspece = new JCheckBox("Esp\u00E8ce");
				      checkEspece.setBounds(6, 12, 97, 23);
				      layerModeReglement.add(checkEspece);
				      checkEspece.setFont(new Font("Tahoma", Font.BOLD, 12));
				      
				      JLabel lblNChque = new JLabel("N\u00B0 Ch\u00E9que:");
				      lblNChque.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
				      lblNChque.setBounds(283, 66, 116, 24);
				      layerModeReglement.add(lblNChque);
				      
				      txtnumcheque = new JTextField();
				      txtnumcheque.setBounds(409, 66, 115, 26);
				      layerModeReglement.add(txtnumcheque);
				      txtnumcheque.setColumns(10);
				      
				      JButton button_1 = new JButton("Initialiser");
				      button_1.addActionListener(new ActionListener() {
				      	public void actionPerformed(ActionEvent arg0) {
				      		
				      		
				      		InitialiserModeReglement();
				      		
				      	}
				      });
				      button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				      button_1.setBounds(1551, 202, 106, 23);
				      add(button_1);
				      
				      
		comboBlTransferer.addItem("");		      
		
		JLabel label_7 = new JLabel("Type BL :");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(155, 8, 79, 24);
		layeredPane_1.add(label_7);
		
		  comboTypeBl = new JComboBox();
		comboTypeBl.setSelectedIndex(-1);
		comboTypeBl.setBounds(217, 8, 168, 24);
		layeredPane_1.add(comboTypeBl);
		
		JLabel label_1 = new JLabel("N\u00B0 Commande  :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(395, 6, 134, 24);
		layeredPane_1.add(label_1);
		
		txtnumCommande = new JTextField();
		txtnumCommande.setColumns(10);
		txtnumCommande.setBounds(519, 6, 138, 26);
		layeredPane_1.add(txtnumCommande);
		
		JLabel label_8 = new JLabel("Date  BCM :");
		label_8.setBounds(278, 44, 62, 24);
		layeredPane_1.add(label_8);
		
		 dateChooserBCommande = new JDateChooser();
		dateChooserBCommande.setLocale(Locale.FRANCE);
		dateChooserBCommande.setDateFormatString("dd/MM/yyyy");
		dateChooserBCommande.setBounds(339, 42, 181, 26);
		layeredPane_1.add(dateChooserBCommande);
		
		JLabel label_9 = new JLabel("Adresse :");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_9.setBounds(349, 79, 56, 24);
		layeredPane_1.add(label_9);
		
		 comboAdresse = new JComboBox();
		comboAdresse.setSelectedIndex(-1);
		comboAdresse.setBounds(410, 84, 363, 24);
		layeredPane_1.add(comboAdresse);
		
		checkSansmodepaiement = new JCheckBox("Imprimer Sans Mode de Paiement");
		checkSansmodepaiement.setFont(new Font("Tahoma", Font.BOLD, 11));
		checkSansmodepaiement.setBounds(1057, 450, 245, 30);
		add(checkSansmodepaiement);
		
		 chckbxSansTimbre = new JCheckBox("Sans Timbre");
		chckbxSansTimbre.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxSansTimbre.setBounds(1057, 491, 245, 30);
		add(chckbxSansTimbre);
		
		 chckbxPiece = new JCheckBox("Piece");
		chckbxPiece.setBounds(1057, 612, 83, 24);
		add(chckbxPiece);
		
		ChargerBlTransferer();
		comboBlTransferer.setVisible(false);
		
		 comboTypeBl.addItem("");
		 
		  chckbxImprimerNumBl = new JCheckBox("Imprimer Num BL");
		 chckbxImprimerNumBl.setFont(new Font("Tahoma", Font.BOLD, 11));
		 chckbxImprimerNumBl.setBounds(1043, 644, 229, 30);
		 add(chckbxImprimerNumBl);
		 
		  chckbxImprimerNumBc = new JCheckBox("Imprimer Num BC");
		 chckbxImprimerNumBc.setFont(new Font("Tahoma", Font.BOLD, 11));
		 chckbxImprimerNumBc.setBounds(1043, 677, 229, 30);
		 add(chckbxImprimerNumBc);
		 
		  checkEnTete = new JCheckBox("Avec En Tete");
		  checkEnTete.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {

	    		
	    		if(checkEnTete.isSelected()==true)
	    		{
	    			lblEnTete.setVisible(true);
	    			comboEnTete.setVisible(true);
	    			comboEnTete.setSelectedItem("");
	    			
	    		}else
	    		{
	    			lblEnTete.setVisible(false);
	    			comboEnTete.setVisible(false);
	    			comboEnTete.setSelectedItem("");
	    		}
	    		
	    		
	    	
		  	}
		  });
		 checkEnTete.setFont(new Font("Tahoma", Font.BOLD, 11));
		 checkEnTete.setBounds(1043, 710, 112, 30);
		 add(checkEnTete);
		 
		  comboEnTete = new JComboBox();
		 comboEnTete.setBounds(1220, 714, 201, 26);
		 add(comboEnTete);
		 
		  lblEnTete = new JLabel("En Tete :");
		 lblEnTete.setFont(new Font("Tahoma", Font.BOLD, 12));
		 lblEnTete.setBounds(1161, 714, 63, 24);
		 add(lblEnTete);
	      for(int i=0;i<listTypeBL.size();i++)
	      {
	    	TypeBL typeBL=  listTypeBL.get(i);
	    	
	    	mapTypeBL.put(typeBL.getType(), typeBL);
	    	 comboTypeBl.addItem(typeBL.getType());
	    	
	    	
	      }   
	      
	      
	      lblEnTete.setVisible(false);
			comboEnTete.setVisible(false);
			comboEnTete.addItem("");
			
			 chckbxImprimerBl = new JCheckBox("Imprimer  BL");
			chckbxImprimerBl.setFont(new Font("Tahoma", Font.BOLD, 11));
			chckbxImprimerBl.setBounds(168, 762, 134, 30);
			add(chckbxImprimerBl);
		
	      if(utilisateur.getLogin().equals("admin"))
		  {
	    	 listEntet=enTeteDAO.findAll();
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  listEntet=enTeteDAO.findEnTeteByDepot(depot); 
				  
				  for(int i=0;i<listEntet.size();i++)
				  {
					  
					  EnTete enTete=listEntet.get(i);
					  comboEnTete.addItem(enTete.getVille());
					  mapEntete.put(enTete.getVille(), enTete);
					  
				  }
		     	
			  }
		  }
	
	}
	
	
	
	void ChargerBlTransferer()
	{
		mapHistoriqueVenteVendeur.clear();
		comboBlTransferer.removeAllItems();
		comboBlTransferer.addItem("");		
		Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
		listHistoriqueVentevendeur=historiqueVenteVendeurDAO.findFactureByTypeByDepot(Constantes.TYPE_BON_LIVRAISON,depot);
		
		for(int i=0;i<listHistoriqueVentevendeur.size();i++)
		{
		
			HistoriqueVentevendeur historiqueVentevendeur=listHistoriqueVentevendeur.get(i);
			
			comboBlTransferer.addItem(historiqueVentevendeur.getNumBl());
			
			mapHistoriqueVenteVendeur.put(historiqueVentevendeur.getNumBl(), historiqueVentevendeur);
			
			
		}
		
	}
	

	void InitialiserModeReglement()
	{
		
	checkCheque.setSelected(false);
		checkEspece.setSelected(false);
		checkTraite.setSelected(false);
		checkVersement.setSelected(false);
		checkVirement.setSelected(false);
		txtcheque.setText("");
		txtespece.setText("");
		txttraite.setText("");
		txtversement.setText("");
		txtvirement.setText("");
		txtnumcheque.setText("");
		txtnumtraite.setText("");
		txtnumversement.setText("");
		
	}
	
	
	
	void initialiserFacture()
	{
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		comboClientpf.setSelectedIndex(-1);
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
		comboFournisseur.setSelectedIndex(-1);
		txttimbre.setText("");
		txtnetapayer.setText("");
		chckbxSansTimbre.setSelected(false);
		txtnumBL.setText("");
		txtnumFacture.setText("");
		checkSansmodepaiement.setSelected(false);
		
	}

	void initialiser()
	{
		 
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
		//combotypevente.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	     combosousfamille.setSelectedIndex(-1);
	  checkboxGratuits.setSelected(false);
	  txtquantitepacket.setText("");
	  txtquantitepacket.setEnabled(false);
	checkttc.setSelected(false);
	stockdisponible.setText("");
	labelPrixMax.setText("");
	labelprixmin.setText("");
	labelpourcentage.setVisible(false);
		
		txtPrix.setVisible(true);
		txtreduction.setVisible(true);
		txtmontant.setVisible(true);
		lblMontant.setVisible(true);
		lblReduction.setVisible(true);	
		lblPrix.setVisible(true);	
		checkttc.setVisible(true);
	
	lblOffreTherre.setVisible(false);
	comboBoxtherres.setVisible(false);
	txtquantitetherres.setVisible(false);
	lbloffreverres.setVisible(false);
	comboBoxverres.setVisible(false);
	txtquantiteverres.setVisible(false);
	stockdisponibleoffretherres.setText("");
	stockdisponibleoffreverres.setText("");
	txtquantiteverres.setText("");
	txtquantitetherres.setText("");
	
	    lblOffrePromo.setVisible(false);
		comboBoxPromo.setVisible(false);
		txtquantitepromo.setVisible(false);	
		stockdisponiblearticlepromo.setText("");
		txtquantitepromo.setText("");
		
		comboBoxPromo.removeAllItems();
		mapArticlePromo.clear();
		
		txtquantitepromo.setText("");
		txtquantitetherres.setText("");
		txtquantiteverres.setText("");
		
		
		txtnumcheque.setText("");
		
		txttimbre.setText("");
		txtnetapayer.setText("");
		
	}
	void initialiserGratuit()
	{
		 
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
		//combotypevente.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	     combosousfamille.setSelectedIndex(-1);
	 
	}
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleChargefixe);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void afficher_tableDetailFacturePF(List<DetailHistoriqueVenteVendeur> listDetailFacture)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction %", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleChargefixe);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
		DetailHistoriqueVenteVendeur detailfacturepf=listDetailFacture.get(i);
			
			Object []ligne={detailfacturepf.getSousFamille().getFamileArticlePF().getLiblle(),detailfacturepf.getSousFamille().getLiblle(), detailfacturepf.getArticle().getLiblle(),detailfacturepf.getPrixUnitaire(),detailfacturepf.getQuantite(),detailfacturepf.getReduction(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

			modeleChargefixe.addRow(ligne);
			i++;
		}
}
	}


