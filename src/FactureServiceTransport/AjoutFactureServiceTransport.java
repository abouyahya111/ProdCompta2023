package FactureServiceTransport;

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
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceTransportDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceTransportDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
import dao.daoImplManager.VilleDAOImpl;
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
import dao.daoManager.DetailFactureServiceTransportDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.daoManager.VilleDAO;
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
import dao.entity.DetailFactureServiceTransport;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.FacturePF;
import dao.entity.FactureServiceTransport;
import dao.entity.FactureVenteMP;
import dao.entity.FactureVentePF;
import dao.entity.FamilleArticlePF;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;
import dao.entity.Ville;

import javax.swing.JFormattedTextField;

import com.lowagie.text.pdf.events.IndexEvents.Entry;
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

import javax.swing.JCheckBox;
import javax.swing.JTable;


public class AjoutFactureServiceTransport extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFactureServiceTransport> listDetailFactureServiceTransport =new ArrayList<DetailFactureServiceTransport>();
	private List<Ville> listVille =new ArrayList<Ville>();

	private List<Depot> listDepot =new ArrayList<Depot>();
	
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	
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
	private Map< String, Ville> mapVille= new HashMap<>();
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
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private FactureServiceTransportDAO factureServiceTransportdao;
	private FactureServiceTransport factureServiceTransport=new FactureServiceTransport();

private DetailFactureServiceTransportDAO detailFactureServiceTransportdao;
private ClientPFDAO clientpfdao;
private StockPFDAO stockpfDAO;
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
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
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
		  JCheckBox checkttc = new JCheckBox("TTC");
		  JLabel labelmarge = new JLabel("marge");
		  JLabel labelprixmin = new JLabel("New label");
		  JLabel labelPrixMax = new JLabel("New label");
		  BigDecimal prixTTC=BigDecimal.ZERO;
		  BigDecimal StockFinale=BigDecimal.ZERO;
		  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
		  BigDecimal stockfinaleTherres=BigDecimal.ZERO;
		  BigDecimal stockfinaleVerres=BigDecimal.ZERO;
		  BigDecimal stockfinaleArticlePromo=BigDecimal.ZERO;
		  JLabel lblOffreTherre = new JLabel("Offre Therre :");
		  JComboBox comboBoxtherres = new JComboBox();
		  JLabel lbloffreverres = new JLabel("Offre Verres :");
		  JComboBox comboBoxverres = new JComboBox();
		  JLabel stockdisponibleoffre = new JLabel("");
		  JLabel stockdisponibleoffreverres = new JLabel("");
		  JComboBox comboBoxPromo = new JComboBox();
		  JLabel lblOffrePromo = new JLabel("Offre Promo :");
		  JLabel stockdisponiblearticlepromo = new JLabel("");
		  JCheckBox checkboxSansTva = new JCheckBox("Sans TVA");
		  private JXTable table;
		 VilleDAO villeDAO;
		  JComboBox comboVilleDu = new JComboBox();
		  JComboBox comboVilleAu = new JComboBox();
		  JComboBox comboMois = new JComboBox();  
		  
		  
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutFactureServiceTransport() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1532, 913);
      
	
        try{ 
        	
        	 
        	 
      //  facturePF=new FacturePF();
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
        	
         	depotdao=new DepotDAOImpl();
         
         	factureServiceTransportdao=new FactureServiceTransportDAOImpl();
         	
         	parametredao=new ParametreDAOImpl();
         	detailFactureServiceTransportdao=new DetailFactureServiceTransportDAOImpl();
         	clientpfdao=new ClientPFDAOImpl();
         	factureVenteMPdao=new FactureVenteMPDAOImpl();
         	factureVentePFdao=new FacturePFDAOImpl();
         	fournisseurdao=new ClientDAOImpl();
         	villeDAO=new VilleDAOImpl();
         	
         	listVille=villeDAO.findAll();
          } catch (Exception exp){exp.printStackTrace();}

        
     
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Services");
				  		     	titledSeparator.setBounds(10, 424, 1027, 30);
				  		     	add(titledSeparator);
		       
		    
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 String date = dcn.format(dateChooserfacture.getDate());
				 
				if(txtnumBL.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Erreur Num BL !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(dateChooserfacture.getDate()==null)
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
					
				}else if(comboFournisseur.getSelectedIndex()==-1)
				{

					JOptionPane.showMessageDialog(null, "Veuillez choisir le Fournisseur SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(comboClientpf.getSelectedIndex()==-1 || comboClientpf.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Client SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(listDetailFactureServiceTransport.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					
					FactureVenteMP factureventetmp=factureVenteMPdao.findFactureVenteMPByNumBL(txtnumBL.getText());
					FacturePF factureventetPF=factureVentePFdao.findFactureVentePFByNumBL(txtnumBL.getText());
					if(factureventetmp!=null || factureventetPF!=null)
					{
						JOptionPane.showMessageDialog(null, "Num BL existe déja ","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
				Client fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
				
			factureServiceTransport.setNumBl(txtnumBL.getText());
			factureServiceTransport.setClientPF(clientPF);
			factureServiceTransport.setFournisseur(fournisseur);
			factureServiceTransport.setDepot(depot);
			factureServiceTransport.setMagasin(magasin);
			factureServiceTransport.setDateBl(dateChooserfacture.getDate());
			factureServiceTransport.setDateFacture(dateChooserfacture.getDate());
			factureServiceTransport.setEtat(Constantes.ETAT_NON_REGLE);
			factureServiceTransport.setType(Constantes.TYPE_BON_LIVRAISON);
			factureServiceTransport.setMontantHT((new BigDecimal(txttotalmontantHT.getText())).setScale(6, RoundingMode.HALF_UP));
			factureServiceTransport.setMontantTVA((new BigDecimal(txttotalmontantTVA.getText())).setScale(6, RoundingMode.HALF_UP));
			factureServiceTransport.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
			factureServiceTransport.setDateSaisi(new Date());
			factureServiceTransport.setEspece(BigDecimal.ZERO);
			factureServiceTransport.setCheque(BigDecimal.ZERO);
			factureServiceTransport.setVirement(BigDecimal.ZERO);
			factureServiceTransport.setTraite(BigDecimal.ZERO);
			factureServiceTransport.setCredit(BigDecimal.ZERO);
			//facturePF.setDetailFacturePF(listDetailFacturePF);
			

			
			factureServiceTransport.setCreerPar(utilisateur);
		    factureServiceTransportdao.add(factureServiceTransport);
		
		  
		 for(int i=0;i<listDetailFactureServiceTransport.size();i++)
		 {
			 detailFactureServiceTransportdao.add(listDetailFactureServiceTransport.get(i));
		 }
		   
	   
		   	
			 
		    JOptionPane.showMessageDialog(null, "Facture Ajouté avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
		    initialiserFacture();
		    initialiser();
		  
		    factureServiceTransport=new FactureServiceTransport();
		
		  listDetailFactureServiceTransport.clear();
			 InitialiseTableau();
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(385, 740, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 175, 1031, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1031, 91);
		add(layeredPane_1);
		
		JLabel lblNBl = new JLabel("N\u00B0 BL  :");
		lblNBl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNBl.setBounds(10, 13, 89, 24);
		layeredPane_1.add(lblNBl);
		
		txtnumBL = new JTextField();
		txtnumBL.setColumns(10);
		txtnumBL.setBounds(109, 12, 183, 26);
		layeredPane_1.add(txtnumBL);
		
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(312, 13, 62, 24);
		layeredPane_1.add(label_1);
		
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
		dateChooserfacture.setBounds(373, 11, 181, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(574, 12, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(652, 13, 183, 24);
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
		  label_4.setBounds(10, 48, 56, 24);
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
		  combomagasin.setBounds(109, 49, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		   comboClientpf = new JComboBox();
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(373, 48, 183, 24);
		  layeredPane_1.add(comboClientpf);
		  
		  AutoCompleteDecorator.decorate(comboClientpf);
		  
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(312, 43, 56, 24);
		  layeredPane_1.add(lblClient);
		  
		  JLabel lblFournisseur = new JLabel("Fournisseur :");
		  lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblFournisseur.setBounds(574, 47, 79, 24);
		  layeredPane_1.add(lblFournisseur);
		  
		   comboFournisseur = new JComboBox();
		  comboFournisseur.setSelectedIndex(-1);
		  comboFournisseur.setBounds(652, 48, 183, 24);
		  layeredPane_1.add(comboFournisseur);
		  AutoCompleteDecorator.decorate(comboFournisseur);
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
		btnAjouter.setBounds(390, 391, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					
						
						
						if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(txtquantite.getText().equals("") )
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							
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
						}/*else if (comboMois.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Mois","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
							
						}
						*/
						
						else
						{
							
							String designation="";
							
							Ville VilleDu=mapVille.get(comboVilleDu.getSelectedItem());
							Ville VilleAu=mapVille.get(comboVilleAu.getSelectedItem());
							
						
							boolean trouve=false;
							
							if(VilleDu!=null)
							{
								designation=designation+VilleDu.getVille();
							}
							
							if(VilleAu!=null)
							{
								designation=designation+ " ----> "+ VilleAu.getVille();
							}
							
							
							/*
							for(int i=0;i<listDetailFactureServiceTransport.size();i++)
							{
								
								DetailFactureServiceTransport detailFactureServiceTransport=listDetailFactureServiceTransport.get(i);	
								
								if(designation.equals(""))
								{
									if(detailFactureServiceTransport.getDesignation().equals(DESIGNATION_FACTURE_SERVICE_TRANSPORT+Constantes.MOIS+comboMois.getSelectedItem()))
									{
										
										if(detailFactureServiceTransport.getMois().setScale(0, RoundingMode.HALF_UP).toString().equals(comboMois.getSelectedItem()))
										{
											 trouve=true;
											
											
											
											
										}
										
										
										
										
									}
									
								}else
								{
									if(detailFactureServiceTransport.getDesignation().equals(DESIGNATION_FACTURE_SERVICE_TRANSPORT +"  " + designation+Constantes.MOIS+comboMois.getSelectedItem()))
									{
									
										
										if(detailFactureServiceTransport.getMois().setScale(0, RoundingMode.HALF_UP).toString().equals(comboMois.getSelectedItem()))
										{
											 trouve=true;
											
											
											
											
										}
										
										
										
										
									}
									
								}
							
								
								
								
								
							}
							*/
							
							
							
							if(trouve==true)
							{
								JOptionPane.showMessageDialog(null, "L'enregistrement Déja existant Veuillez le modifier SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							

						DetailFactureServiceTransport detailFactureServiceTransport=new DetailFactureServiceTransport();
						detailFactureServiceTransport.setQuantite(new BigDecimal(txtquantite.getText()));
						detailFactureServiceTransport.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						detailFactureServiceTransport.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite()).setScale(6, RoundingMode.HALF_UP));
					
						if(VilleDu!=null)
						{
							detailFactureServiceTransport.setDu(VilleDu.getVille());
						}
						
						if(VilleAu!=null)
						{
							detailFactureServiceTransport.setAu(VilleAu.getVille());
						}
						
						
						if(designation.equals(""))
						{
							
							if(comboMois.getSelectedItem().equals(""))
							{
								detailFactureServiceTransport.setDesignation(DESIGNATION_FACTURE_SERVICE_TRANSPORT);

							}else
							{
								
								detailFactureServiceTransport.setDesignation(DESIGNATION_FACTURE_SERVICE_TRANSPORT+Constantes.MOIS+comboMois.getSelectedItem());

							}
							
							
							
							
							
						}else
						{
							if(comboMois.getSelectedItem().equals(""))
							{
								detailFactureServiceTransport.setDesignation(DESIGNATION_FACTURE_SERVICE_TRANSPORT +"  " + designation);

								
							}else
							{
								detailFactureServiceTransport.setDesignation(DESIGNATION_FACTURE_SERVICE_TRANSPORT +"  " + designation+Constantes.MOIS+comboMois.getSelectedItem());

							}
							
						}
						
						if(!comboMois.getSelectedItem().equals(""))
						{
							detailFactureServiceTransport.setMois(new BigDecimal(comboMois.getSelectedItem().toString()));
						}
						
						
						  if(!txtreduction.getText().equals(""))
				          {
							  detailFactureServiceTransport.setReduction(new BigDecimal(txtreduction.getText()));
				          }else
				          {
				        	  detailFactureServiceTransport.setReduction(BigDecimal.ZERO);  
				          }
						

					  
				         
				          if(checkboxSansTva.isSelected()==true)
				          {
				        	  detailFactureServiceTransport.setMontantTVA(BigDecimal.ZERO);
				        	  detailFactureServiceTransport.setTva(BigDecimal.ZERO);
				          }else
				          {
				        	  detailFactureServiceTransport.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).multiply(Constantes.TVA_TRANSPORT)).setScale(6, RoundingMode.HALF_UP));
				        	  detailFactureServiceTransport.setTva(Constantes.TVA_TRANSPORT.multiply(new BigDecimal(100)));
				          }
				         
				          
				          if(!txtreduction.getText().equals(""))
				          {
				        	  if(checkboxSansTva.isSelected()==true)
					          {
				        		  
				        		  detailFactureServiceTransport.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite()))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite()))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

					          }else
					          {
					        	  detailFactureServiceTransport.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).add((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).multiply(Constantes.TVA_TRANSPORT))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).add((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).multiply(Constantes.TVA_TRANSPORT))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

					          }
				          }else
				          {
				        	  
				        	  if(checkboxSansTva.isSelected()==true)
					          {
				        		  detailFactureServiceTransport.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite()))).setScale(6, RoundingMode.HALF_UP));

				        		  
					          }else
					          {
					        	  detailFactureServiceTransport.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).add((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).multiply(Constantes.TVA_TRANSPORT))).setScale(6, RoundingMode.HALF_UP));

					          }
				          
				          
				          }
				
				   
				          detailFactureServiceTransport.setFactureServiceTransport(factureServiceTransport);
					       //  detailFacture.setDateCreation(new Date());
					           
				          detailFactureServiceTransport.setUtilisateur(utilisateur);
					         
					           listDetailFactureServiceTransport.add(detailFactureServiceTransport);
						
						afficher_tableDetailFacturePF(listDetailFactureServiceTransport);
						
						 int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        BigDecimal lamarge=BigDecimal.ZERO;
					        BigDecimal compteur=BigDecimal.ZERO;
					        
					        while(i<listDetailFactureServiceTransport.size())
					        {

					        	 DetailFactureServiceTransport detailFactureServiceTransportTmp=listDetailFactureServiceTransport.get(i);
						          montanttotal=  montanttotal.add(detailFactureServiceTransportTmp.getMontantTTC());
						          sommequantite= sommequantite.add(detailFactureServiceTransportTmp.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFactureServiceTransportTmp.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFactureServiceTransportTmp.getMontantTVA());
						       
					        	
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal+"");
					       txttotalquantite.setText(sommequantite+"");
				  			txttotalmontantHT.setText(montanttotalHT+"");
				  			txttotalmontantTVA.setText(montanttotalTVA+"");	
				  			initialiser();
						
						}
					      
						
					
					
					
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(521, 390, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiser();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserFacture();
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(439, 141, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		try {
		   			

		   			if(dateChooserfacture.getDate()==null)
					{

						JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(txtquantite.getText().equals("") )
					{
						JOptionPane.showMessageDialog(null, "Veuillez saisir quantite  SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
						
						
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
					}
					/*else if (comboMois.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Mois","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
						
					}
					*/
					
					else
					{
						
						
						String designation="";
						
						Ville VilleDu=mapVille.get(comboVilleDu.getSelectedItem());
						Ville VilleAu=mapVille.get(comboVilleAu.getSelectedItem());
						
						
						
						boolean trouve=false;
						
						if(VilleDu!=null)
						{
							designation=designation+VilleDu.getVille();
						}
						
						if(VilleAu!=null)
						{
							designation=designation+ " ----> "+ VilleAu.getVille();
						}
						
						/*
						for(int i=0;i<listDetailFactureServiceTransport.size();i++)
						{
							if(i!=table.getSelectedRow())
							{
								
								DetailFactureServiceTransport detailFactureServiceTransport=listDetailFactureServiceTransport.get(i);	
								
								if(designation.equals(""))
								{
									if(detailFactureServiceTransport.getDesignation().equals(DESIGNATION_FACTURE_SERVICE_TRANSPORT+Constantes.MOIS+comboMois.getSelectedItem()))
									{
										
										if(detailFactureServiceTransport.getMois().setScale(0, RoundingMode.HALF_UP).toString().equals(comboMois.getSelectedItem()))
										{
											 trouve=true;
											
											
											
											
										}
										
										
										
										
									}
									
								}else
								{
									if(detailFactureServiceTransport.getDesignation().equals(DESIGNATION_FACTURE_SERVICE_TRANSPORT +"  " + designation+Constantes.MOIS+comboMois.getSelectedItem()))
									{
										
										if(detailFactureServiceTransport.getMois().setScale(0, RoundingMode.HALF_UP).toString().equals(comboMois.getSelectedItem()))
										{
											 trouve=true;
											
											
											
											
										}
										
										
										
										
									}
									
								}
								
							}
							
					
							
						}*/
						
						if(trouve==true)
						{
							JOptionPane.showMessageDialog(null, "L'enregistrement Déja existant Veuillez le modifier SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						
						
						
						
						
					DetailFactureServiceTransport detailFactureServiceTransport=listDetailFactureServiceTransport.get(table.getSelectedRow());						
					
					detailFactureServiceTransport.setQuantite(new BigDecimal(txtquantite.getText()));
					detailFactureServiceTransport.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
					detailFactureServiceTransport.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite()).setScale(6, RoundingMode.HALF_UP));
				
					if(VilleDu!=null)
					{
						detailFactureServiceTransport.setDu(VilleDu.getVille());
					}else
					{
						detailFactureServiceTransport.setDu("");
					}
					
					if(VilleAu!=null)
					{
						detailFactureServiceTransport.setAu(VilleAu.getVille());
					}else
					{
						detailFactureServiceTransport.setAu("");
					}
					
					
					if(designation.equals(""))
					{
						
						if(comboMois.getSelectedItem().equals(""))
						{
							detailFactureServiceTransport.setDesignation(DESIGNATION_FACTURE_SERVICE_TRANSPORT);

							
						}else
						{
							detailFactureServiceTransport.setDesignation(DESIGNATION_FACTURE_SERVICE_TRANSPORT+Constantes.MOIS+comboMois.getSelectedItem());

						}
						
					}else
					{
						
						if(comboMois.getSelectedItem().equals(""))
						{
							
							detailFactureServiceTransport.setDesignation(DESIGNATION_FACTURE_SERVICE_TRANSPORT +"  " + designation);

							
						}else
						{
							detailFactureServiceTransport.setDesignation(DESIGNATION_FACTURE_SERVICE_TRANSPORT +"  " + designation+Constantes.MOIS+comboMois.getSelectedItem());

							
						}
						
					}
					
					
					if(!comboMois.getSelectedItem().equals(""))
					{
						detailFactureServiceTransport.setMois(new BigDecimal(comboMois.getSelectedItem().toString()));
					}else
					{
						detailFactureServiceTransport.setMois(null);
					}
					
					
					  if(!txtreduction.getText().equals(""))
			          {
						  detailFactureServiceTransport.setReduction(new BigDecimal(txtreduction.getText()));
			          }else
			          {
			        	  detailFactureServiceTransport.setReduction(BigDecimal.ZERO);  
			          }
					

				  
			         
			          if(checkboxSansTva.isSelected()==true)
			          {
			        	  detailFactureServiceTransport.setMontantTVA(BigDecimal.ZERO);
			        	  detailFactureServiceTransport.setTva(BigDecimal.ZERO);
			          }else
			          {
			        	  detailFactureServiceTransport.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).multiply(Constantes.TVA_TRANSPORT)).setScale(6, RoundingMode.HALF_UP));
			        	  detailFactureServiceTransport.setTva(Constantes.TVA_TRANSPORT.multiply(new BigDecimal(100)));
			          }
			         
			          
			          if(!txtreduction.getText().equals(""))
			          {
			        	  if(checkboxSansTva.isSelected()==true)
				          {
			        		  
			        		  detailFactureServiceTransport.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite()))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite()))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

				          }else
				          {
				        	  detailFactureServiceTransport.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).add((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).multiply(Constantes.TVA_TRANSPORT))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).add((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).multiply(Constantes.TVA_TRANSPORT))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

				          }
			          }else
			          {
			        	  
			        	  if(checkboxSansTva.isSelected()==true)
				          {
			        		  detailFactureServiceTransport.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite()))).setScale(6, RoundingMode.HALF_UP));

			        		  
				          }else
				          {
				        	  detailFactureServiceTransport.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).add((new BigDecimal(txtPrix.getText()).multiply(detailFactureServiceTransport.getQuantite())).multiply(Constantes.TVA_TRANSPORT))).setScale(6, RoundingMode.HALF_UP));

				          }
			          
			          
			          }
			
			   
			          detailFactureServiceTransport.setFactureServiceTransport(factureServiceTransport);
				       //  detailFacture.setDateCreation(new Date());
				           
			          detailFactureServiceTransport.setUtilisateur(utilisateur);
				         
				           listDetailFactureServiceTransport.set(table.getSelectedRow(), detailFactureServiceTransport);
					
					afficher_tableDetailFacturePF(listDetailFactureServiceTransport);	
						
					 int i=0;
				        BigDecimal montanttotal=BigDecimal.ZERO;
				        BigDecimal sommequantite=BigDecimal.ZERO;
				        BigDecimal montanttotalHT=BigDecimal.ZERO;
				        BigDecimal montanttotalTVA=BigDecimal.ZERO;
				        BigDecimal lamarge=BigDecimal.ZERO;
				        BigDecimal compteur=BigDecimal.ZERO;
				        
				        while(i<listDetailFactureServiceTransport.size())
				        {

				        	 DetailFactureServiceTransport detailFactureServiceTransportTmp=listDetailFactureServiceTransport.get(i);
					          montanttotal=  montanttotal.add(detailFactureServiceTransportTmp.getMontantTTC());
					          sommequantite= sommequantite.add(detailFactureServiceTransportTmp.getQuantite());
					          montanttotalHT=montanttotalHT.add(detailFactureServiceTransportTmp.getMontantHT());
					          montanttotalTVA=montanttotalTVA.add(detailFactureServiceTransportTmp.getMontantTVA());
					       
				        	
				            i++;
				        }
				       txttotalmontantTTC.setText(montanttotal+"");
				       txttotalquantite.setText(sommequantite+"");
			  			txttotalmontantHT.setText(montanttotalHT+"");
			  			txttotalmontantTVA.setText(montanttotalTVA+"");	
						
						
			  			initialiser();
						
						
						
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
		  		
		  		if(table.getSelectedRow()!=-1)
		  		{
		  				
		  				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'article dans la facture  ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
								
								
							{
								
							
						
					        	 
					        	 
					        	 
					        	 
					  		listDetailFactureServiceTransport.remove(table.getSelectedRow());
					  	

					  		
					  		
					  		
					  		
					  		
					        afficher_tableDetailFacturePF(listDetailFactureServiceTransport);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        BigDecimal lamarge=BigDecimal.ZERO;
						        BigDecimal compteur=BigDecimal.ZERO;
						        
						        while(i<listDetailFactureServiceTransport.size())
						        {

						        	 DetailFactureServiceTransport detailFactureServiceTransport=listDetailFactureServiceTransport.get(i);
							          montanttotal=  montanttotal.add(detailFactureServiceTransport.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureServiceTransport.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureServiceTransport.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureServiceTransport.getMontantTVA());
							       
						        	
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
		  btnSupprimer.setBounds(1057, 515, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(779, 797, 105, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(903, 797, 134, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(662, 734, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(555, 734, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1057, 485, 73, 24);
		add(btnModifier);
		
		JLabel lblTotalMontantHt = new JLabel("Total Montant HT :");
		lblTotalMontantHt.setBounds(779, 735, 105, 26);
		add(lblTotalMontantHt);
		
		txttotalmontantHT = new JTextField();
		txttotalmontantHT.setEditable(false);
		txttotalmontantHT.setColumns(10);
		txttotalmontantHT.setBounds(903, 735, 134, 26);
		add(txttotalmontantHT);
		
		JLabel lblTotalMontantTva = new JLabel("Total Montant TVA :");
		lblTotalMontantTva.setBounds(779, 767, 105, 26);
		add(lblTotalMontantTva);
		
		txttotalmontantTVA = new JTextField();
		txttotalmontantTVA.setEditable(false);
		txttotalmontantTVA.setColumns(10);
		txttotalmontantTVA.setBounds(903, 767, 134, 26);
		add(txttotalmontantTVA);
		                            
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
		                            
		                             stockdisponibleoffreverres = new JLabel("");
		                            stockdisponibleoffreverres.setHorizontalAlignment(SwingConstants.LEFT);
		                            stockdisponibleoffreverres.setForeground(Color.RED);
		                            stockdisponibleoffreverres.setFont(new Font("Tahoma", Font.BOLD, 9));
		                            stockdisponibleoffreverres.setBounds(1044, 385, 613, 30);
		                            add(stockdisponibleoffreverres);
		                            
		                             checkboxSansTva = new JCheckBox("Sans TVA");
		                            checkboxSansTva.setBounds(1047, 277, 83, 24);
		                            add(checkboxSansTva);
		                            
		                            // comboArticle.addItem("");
		                             
		                            
		                              
               
		                              JLayeredPane layerArticleGratuit = new JLayeredPane();
		                              layerArticleGratuit.setBounds(10, 226, 1031, 139);
		                              add(layerArticleGratuit);
		                              layerArticleGratuit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		                              
		                              txtquantite = new JTextField();
		                              txtquantite.setBounds(42, 37, 99, 26);
		                              layerArticleGratuit.add(txtquantite);
		                              util.Utils.copycoller(txtquantite);
		                              txtquantite.addKeyListener(new KeyAdapter() {
		                              	@Override
		                              	public void keyPressed(KeyEvent e) {
		                              		

		                              		if(e.getKeyCode()==e.VK_ENTER)
			      		{
		                              			
		                                 			try {


		                                 				
    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");

			    		                   				
			    		                   				
								
							} catch (NumberFormatException e2) {JOptionPane.showMessageDialog(null, "Veuillez entrer les chiffres pour la quantité et le montant SVP !!!!!");
								// TODO: handle exception
							}
		                              			
		                              			
		                              		         
		                              			
		                              			
		                              			
			      		}
		                              		
		                              		
		                              	
		                               		
		                              		
		                              	}
		                              });
		                              txtquantite.setColumns(10);
		                              
		                               lblPrix = new JLabel("Prix  :");
		                               lblPrix.setBounds(169, 37, 36, 26);
		                               layerArticleGratuit.add(lblPrix);
		                               
		                               txtreduction = new JTextField();
		                               txtreduction.setBounds(641, 37, 112, 26);
		                               layerArticleGratuit.add(txtreduction);
		                               txtreduction.setColumns(10);
		                               
		                                labelpourcentage = new JLabel("%");
		                                labelpourcentage.setBounds(763, 37, 26, 26);
		                                layerArticleGratuit.add(labelpourcentage);
		                                labelpourcentage.setFont(new Font("Tahoma", Font.BOLD, 11));
		                                
		                                 lblReduction = new JLabel("Remise :");
		                                 lblReduction.setBounds(583, 37, 57, 26);
		                                 layerArticleGratuit.add(lblReduction);
		                                 
		     
		                                 
		                                  lblMontant = new JLabel("Montant  :");
		                                  lblMontant.setBounds(374, 37, 64, 26);
		                                  layerArticleGratuit.add(lblMontant);
		                                  
		                                  txtmontant = new JTextField();
		                                  txtmontant.setBounds(448, 37, 125, 26);
		                                  layerArticleGratuit.add(txtmontant);
		                                  txtmontant.setEditable(false);
		                                  txtmontant.setColumns(10);
		                                  
		                                  txtPrix = new JTextField();
		                                  txtPrix.setBounds(215, 37, 135, 26);
		                                  layerArticleGratuit.add(txtPrix);
		                                  txtPrix.addKeyListener(new KeyAdapter() {
		                                  	@Override
		                                  	public void keyPressed(KeyEvent e) {
		                                  		

		                                     		if(e.getKeyCode()==e.VK_ENTER)
      		{
		                                     			
		                                     			
		                                         			try {

		                                         			
		    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");

		                                         			
		                                         			
		                                         			
		                                         			} catch (NumberFormatException e2) {JOptionPane.showMessageDialog(null, "Veuillez entrer les chiffres pour la quantité et le montant SVP !!!!!");
					// TODO: handle exception
				}
			      
		                                     			
		                                       			
		                                     			
		                                     			
      		}	
		                                  	}
		                                  });
		                                  txtPrix.setColumns(10);
		                                  
		                                   
		                                       	
		                                        
		                                        JLabel labelQuantit = new JLabel("QU :");
		                                        labelQuantit.setBounds(10, 37, 42, 26);
		                                        layerArticleGratuit.add(labelQuantit);
		                                        
		                                        JLabel lblDu = new JLabel("Du :");
		                                        lblDu.setBounds(10, 81, 56, 24);
		                                        layerArticleGratuit.add(lblDu);
		                                        lblDu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                                        
		                                         comboVilleDu = new JComboBox();
		                                         comboVilleDu.setBounds(52, 82, 240, 24);
		                                         layerArticleGratuit.add(comboVilleDu);
		                                         comboVilleDu.setSelectedIndex(-1);
		                                         comboVilleDu.addItem("");
		                                         
		                                         JLabel lblAu = new JLabel("Au :");
		                                         lblAu.setBounds(312, 80, 56, 24);
		                                         layerArticleGratuit.add(lblAu);
		                                         lblAu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                                         
		                                          comboVilleAu = new JComboBox();
		                                          comboVilleAu.setBounds(359, 81, 235, 24);
		                                          layerArticleGratuit.add(comboVilleAu);
		                                          comboVilleAu.setSelectedIndex(-1);
		                                          
		                                        comboVilleAu.addItem("");
		                                        
		                                        JLabel lblMois = new JLabel("Mois :");
		                                        lblMois.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                                        lblMois.setBounds(623, 80, 56, 24);
		                                        layerArticleGratuit.add(lblMois);
		                                        
		                                         comboMois = new JComboBox();
		                                        comboMois.setSelectedIndex(-1);
		                                        comboMois.setBounds(675, 81, 144, 24);
		                                        layerArticleGratuit.add(comboMois);
		                                        
		                                        JScrollPane scrollPane = new JScrollPane();
		                                        scrollPane.setBounds(10, 465, 1027, 249);
		                                        add(scrollPane);
		                                        
		                                        table = new JXTable();
		                                        table.addMouseListener(new MouseAdapter() {
		                                        	@Override
		                                        	public void mouseClicked(MouseEvent arg0) {
		                                        		
		                                        		if(table.getSelectedRow()!=-1)
		                                        		{
		                                        			initialiser();
		                                        			
		                                        			if(listDetailFactureServiceTransport.get(table.getSelectedRow()).getDu()!=null)
		                                        			{
		                                        				comboVilleDu.setSelectedItem(listDetailFactureServiceTransport.get(table.getSelectedRow()).getDu());
		                                        			}
		                                        			
		                                        			
		                                        			if(listDetailFactureServiceTransport.get(table.getSelectedRow()).getAu()!=null)
		                                        			{
		                                        				comboVilleAu.setSelectedItem(listDetailFactureServiceTransport.get(table.getSelectedRow()).getAu());
		                                        			}
		                                        			
		                                        			if(listDetailFactureServiceTransport.get(table.getSelectedRow()).getMois()!=null)
		                                        			{
		                                        				comboMois.setSelectedItem(listDetailFactureServiceTransport.get(table.getSelectedRow()).getMois().setScale(0, RoundingMode.HALF_UP).toString());
		                                        			}

		                                        			
		                                        			
		                                        			
		                                        			
		                                        			
		                                        		txtquantite.setText(table.getValueAt(table.getSelectedRow(), 2).toString());	
		                                        		txtPrix.setText(table.getValueAt(table.getSelectedRow(), 3).toString());	
		                                        		txtreduction.setText(table.getValueAt(table.getSelectedRow(), 4).toString());	
		                                        		txtmontant.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
		                                        		
		                                        		if( new BigDecimal(table.getValueAt(table.getSelectedRow(), 6).toString()).compareTo(BigDecimal.ZERO)==0 )
		                                        		{
		                                        			checkboxSansTva.setSelected(true);
		                                        		}else
		                                        		{
		                                        			checkboxSansTva.setSelected(false);
		                                        		}
		                                        		
		                                        		
		                                        		btnAjouter.setEnabled(false);	
		                                        		
		                                        		
		                                        		}
		                                        		
		                                        		
		                                        		
		                                        		
		                                        		
		                                        		
		                                        		
		                                        	}
		                                        });
		                                        table.setSortable(false);
		                                        table.setModel(new DefaultTableModel(
		                                        	new Object[][] {
		                                        	},
		                                        	new String[] {
		                                        		"Designation", "Mois","Quantite", "Prix", "Reduction", "Montant HT", "Montant TVA", "Montant TTC"
		                                        	}
		                                        ));
		                                        scrollPane.setViewportView(table);
		    for(int i=0;i<listVille.size();i++)
		    {
		    	
		    Ville ville=listVille.get(i);
		    
		    comboVilleAu.addItem(ville.getVille());
		    comboVilleDu.addItem(ville.getVille());
		    mapVille.put(ville.getVille(), ville)	;
		    	
		    }
		    
		    comboMois.addItem("");   
		for(int d=1;d<13;d++) 
		{
			
			comboMois.addItem(String.valueOf(d) );
			
		}
		    
		    
		    
		    		                
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
		txtnumBL.setText("");
		
	}

	void initialiser()
	{

	txtquantite.setText("");
	txtPrix.setText("");
	txtmontant.setText("");
	
	txtreduction.setText("");
	checkboxSansTva.setSelected(false);
	
	comboVilleAu.setSelectedItem("");
	comboVilleDu.setSelectedItem("");
	comboMois.setSelectedItem("");
	btnAjouter.setEnabled(true);	
	
	}
	void initialiserGratuit()
	{
		 
			   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
   
	}
	
	
	
	
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						 "Quantite" ,"Prix Unitaire","Reduction %", "Montant HT", "Montant TVA", "Montant TTC"				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modeleChargefixe);
		table.getColumnModel().getColumn(0).setPreferredWidth(198);
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void afficher_tableDetailFacturePF(List<DetailFactureServiceTransport> listDetailFacture)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Designation","Mois", "Quantite" ,"Prix Unitaire","Reduction %", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modeleChargefixe);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
		DetailFactureServiceTransport detailFactureServiceTransport=listDetailFacture.get(i);
			
			Object []ligne={detailFactureServiceTransport.getDesignation() , detailFactureServiceTransport.getMois(), detailFactureServiceTransport.getQuantite(),detailFactureServiceTransport.getPrixUnitaire(),detailFactureServiceTransport.getReduction(), detailFactureServiceTransport.getMontantHT(),detailFactureServiceTransport.getMontantTVA(), detailFactureServiceTransport.getMontantTTC()};

			modeleChargefixe.addRow(ligne);
			i++;
		}
		
		
}
	}


