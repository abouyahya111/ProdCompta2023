package Reglement;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.PrintException;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.DateUtils;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAutresVenteDAOImpl;
import dao.daoImplManager.DetailFactureAvoirClientPFDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceTransportDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.EnTeteDAOImpl;
import dao.daoImplManager.FactureAutresVenteDAOImpl;
import dao.daoImplManager.FactureAvoirClientPFDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceTransportDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureAutresVenteDAO;
import dao.daoManager.DetailFactureAvoirClientPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceTransportDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.EnTeteDAO;
import dao.daoManager.FactureAutresVenteDAO;
import dao.daoManager.FactureAvoirClientPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.ClientPF;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAvoirClientPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceTransport;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.EnTete;
import dao.entity.FactureAutresVente;
import dao.entity.FactureAvoirClientPF;
import dao.entity.FacturePF;
import dao.entity.FactureServiceTransport;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
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


public class ImprimerFacturePF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	
	
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private List<FacturePF> listFacturePFTmp =new ArrayList<FacturePF>();
	private List<FacturePF> listFacturePFCharger =new ArrayList<FacturePF>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	
	private ImageIcon imgExcel;
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	private Map< Integer, FacturePF> mapImprimer = new HashMap<>();
	private Map< String, EnTete> mapEntete= new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FacturePFDAO facturepfdao;
	private FacturePF facturePF;
    private DetailFacturePFDAO detailFacturePfdao;
    private ClientPFDAO clientpfdao;
	ChargeProduction chargeproduction;
	private JTextField txtnumfacture;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	 private JDateChooser dateChooserfacture;
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private  JCheckBox checkmodepaiement = new JCheckBox();
	private JCheckBox checkSansNummodepaiement;
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	 JButton btnSupprimer = new JButton();
	private   JComboBox comboClientpf ;
	private JTextField txtparnumfacture;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private JDateChooser dateFacture_Au ;
	private  JComboBox combopardepot;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	private JTextField txttimbre;
	private JTextField txtnetapayer;
	private List<ClientPF> listClientPFP =new ArrayList<ClientPF>();
	 JComboBox comboparClient = new JComboBox();
	 JCheckBox checkAppliquerTimbre = new JCheckBox("Appliquer Timbre");
	 JCheckBox checkSansmodepaiement = new JCheckBox("Imprimer Sans Mode de Paiement");
	 JButton buttonvalider = new JButton("Imprimer");
	 JCheckBox checkEnTete = new JCheckBox("Avec En Tete");
	 JLabel lblEnTete = new JLabel("En Tete :");
	 JComboBox comboEnTete = new JComboBox();
	private  List<EnTete>listEntet=new ArrayList<EnTete>();
	 EnTeteDAO enTeteDAO;
	 JCheckBox chckbxImprimerNumBl = new JCheckBox("Imprimer Num BL");
	 JCheckBox chckbxImprimerNumBc = new JCheckBox("Imprimer Num BC");
	 private DetailPrixArticleDAO detailPrixArticleDAO;
	 JComboBox comboModeReglemnt = new JComboBox();
	 JCheckBox chckbxAvecGratuite = new JCheckBox("Avec Gratuite");
	 private List<FactureAvoirClientPF> listFactureAvoirClientPF =new ArrayList<FactureAvoirClientPF>();
		private List<FactureAvoirClientPF> listFactureAvoirClientPFTmp =new ArrayList<FactureAvoirClientPF>();
	    private List<FactureAvoirClientPF> listFactureAvoirClientPFCharger =new ArrayList<FactureAvoirClientPF>();
		private FactureAvoirClientPFDAO factureAvoirClientpfdao;
		private List<FactureServiceTransport> listFactureServiceTransportTmp =new ArrayList<FactureServiceTransport>();
	private DetailFactureAvoirClientPFDAO detailFactureAvoirClientPFdao;
	 private DetailFactureServiceTransportDAO detailFactureServiceTransportdao;
	private FactureServiceTransportDAO factureServiceTransportDAO;
	private List<FactureServiceTransport> listFactureServiceTransport =new ArrayList<FactureServiceTransport>();
 
	
	private List<FactureServiceTransport> listFactureServiceTransportCharger =new ArrayList<FactureServiceTransport>();
	private List<FactureAutresVente> listFactureAutresVente =new ArrayList<FactureAutresVente>();
	private List<FactureAutresVente> listFactureAutresVenteTmp =new ArrayList<FactureAutresVente>();
	private List<FactureAutresVente> listFactureAutresVenteCharger =new ArrayList<FactureAutresVente>();
	  private DetailFactureAutresVenteDAO detailFactureAutresVentedao;
	  private FactureAutresVenteDAO factureAutresVentedao;
	
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	 
	public ImprimerFacturePF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1537, 1062);
      
	
        try{ 
        	
        	 listDetailFacturePF =new ArrayList<DetailFacturePF>();
        	 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	facturepfdao=new FacturePFDAOImpl();
         	detailFacturePfdao=new DetailFacturePFDAOImpl();
         	clientpfdao=new ClientPFDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	enTeteDAO=new EnTeteDAOImpl();
        	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
         	listArticle=articleDAO.findAll();
         	listClientPFParCode=clientpfdao.findListClientByCodeDepot(utilisateur.getCodeDepot());;
         	listClientPFP=clientpfdao.findListClientByCodeDepot(utilisateur.getCodeDepot());
        	factureAvoirClientpfdao=new FactureAvoirClientPFDAOImpl();
         	detailFactureAvoirClientPFdao=new DetailFactureAvoirClientPFDAOImpl();
         	factureServiceTransportDAO=new FactureServiceTransportDAOImpl();
         	detailFactureServiceTransportdao=new DetailFactureServiceTransportDAOImpl();
         	factureAutresVentedao=new FactureAutresVenteDAOImpl();
         	detailFactureAutresVentedao=new DetailFactureAutresVenteDAOImpl();
         	
         	for(int i=0;i<listClientPFParCode.size();i++)
         	{
         		ClientPF clientpf=listClientPFParCode.get(i);
         		mapClientPFparCode.put(clientpf.getCode(), clientpf);
         	}
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       		"Article","Sous Famille", "Prix Unitaire", "Quantite", "Montant HT", "Montant TVA", "Montant TTC"
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
				  		     	scrollPane.setBounds(10, 537, 1262, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 509, 1262, 30);
				  		     	add(titledSeparator);
		      
		     
		
		 buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mapImprimer.clear();
	    		if(!remplirMapImprimer())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					boolean apercu=false;
					
					 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Afficher l'aperçu Avant l'impression ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )		
							
						{
							apercu=true;
						}
					
					
					
					for(int k=0;k<listFacturePF.size();k++)
					{
						FacturePF facturepfTmp=listFacturePF.get(k);
						
						if(mapImprimer.containsKey(facturepfTmp.getId()))
						{
							
							
							
							
							///////////////////////////////////////////////
							
			detailFacturePfdao.ViderSession();
			String sommetowords="";

			List<DetailFacturePF> listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
			List<DetailFacturePF> listDetailFacturePFImprimerTmp =new ArrayList<DetailFacturePF>();
			   List<DetailFacturePF> listDetailFacturePFTmp =new ArrayList<DetailFacturePF>();
			 listFacturePFTmp.clear();
			 if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
			 {
				 listDetailFacturePFTmp=detailFacturePfdao.listeDetailFacturePFByFacture(facturepfTmp.getId());
				 
			 }else
			 {
				 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getDepot());
				 listDetailFacturePFTmp=detailFacturePfdao.listeDetailFacturePFByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getMagasin());
				 
			 }
			    BigDecimal montanttotal=BigDecimal.ZERO;
		        BigDecimal montanttotalHT=BigDecimal.ZERO;
		        BigDecimal montanttotalTVA=BigDecimal.ZERO;
		        BigDecimal netapayer=BigDecimal.ZERO;
		        BigDecimal timber=BigDecimal.ZERO;
		        BigDecimal timberversement=BigDecimal.ZERO;
		        BigDecimal timberespece=BigDecimal.ZERO;
		       BigDecimal quantitePieceCalculer=BigDecimal.ZERO; 
		       BigDecimal prixPieceCalculer=BigDecimal.ZERO; 
		       BigDecimal MontantHTPieceCalculer=BigDecimal.ZERO; 
		       BigDecimal MontantTVAPieceCalculer=BigDecimal.ZERO; 
		       BigDecimal MontantTTCPieceCalculer=BigDecimal.ZERO; 
		        
							boolean trouve=false;
							
							 if(listDetailFacturePFTmp.size()!=0)
							 {
								 
								 for(int i=0;i<listDetailFacturePFTmp.size();i++)
								 {
									 
									 
									    quantitePieceCalculer=BigDecimal.ZERO; 
								         prixPieceCalculer=BigDecimal.ZERO; 
								         MontantHTPieceCalculer=BigDecimal.ZERO; 
								         MontantTVAPieceCalculer=BigDecimal.ZERO; 
								         MontantTTCPieceCalculer=BigDecimal.ZERO;  
									 
									 
									 trouve=false;
									 
									 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
									 {
										if(listDetailFacturePFImprimer.get(j).getArticle().getId()== listDetailFacturePFTmp.get(i).getArticle().getId() && !listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2)) && !listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2))) 
										{
											trouve=true;
											 
											if(listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
											{
												if(listDetailFacturePFImprimer.get(j).getFacturePF().getImprimerPiece()!=null)
												{
													if(listDetailFacturePFImprimer.get(j).getFacturePF().getImprimerPiece()==true)
													{
														
														 quantitePieceCalculer=listDetailFacturePFTmp.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFTmp.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
												         prixPieceCalculer=listDetailFacturePFTmp.get(i).getPrixUnitaire().divide(listDetailFacturePFTmp.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
												         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
												        if(listDetailFacturePFTmp.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
												        {
												        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
												        }
												         
												         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
														
														listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(quantitePieceCalculer));
														listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(MontantHTPieceCalculer));
														listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

														listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(MontantTVAPieceCalculer));
														listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(MontantTTCPieceCalculer));
														if(listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)) && listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)))
														{
															listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFTmp.get(i).getReduction());
														}else
														{
															listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));
														}
																	
														listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
														
														
														
														
														
														
														 
														 
														
													}else
													{
														listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
														listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
														listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

														listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
														listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
														if(listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)) && listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)))
														{
															listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction());
															
														}else
														{
															listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));	
														}
																
														listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
													}
													
												}else
												{

													listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
													listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
													listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

													listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
													listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
													if(listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)) && listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)))
													{
														listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction());
													}else
													{
														listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));
													}
																
													listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
												
												}
												
												
												
											}else
											{
												listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
												listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
												listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

												listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
												listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
												if(listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)) && listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)))
												{
													listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction());	
												}else
												{
													listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));	
												}
														
												listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
											}
											
											
											
										}
										
										
									 }
									 if(trouve==false)
									 {
											if(listDetailFacturePFTmp.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFTmp.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
											{
												if(listDetailFacturePFTmp.get(i).getFacturePF().getImprimerPiece()!=null)
												{
													if(listDetailFacturePFTmp.get(i).getFacturePF().getImprimerPiece()==true)
													{
														
														 quantitePieceCalculer=listDetailFacturePFTmp.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFTmp.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
												         prixPieceCalculer=listDetailFacturePFTmp.get(i).getPrixUnitaire().divide(listDetailFacturePFTmp.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
												         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
												        if(listDetailFacturePFTmp.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
												        {
												        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
												        }
												         
												         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
												         
												         
												         listDetailFacturePFTmp.get(i).setQuantite(quantitePieceCalculer);
												         listDetailFacturePFTmp.get(i).setPrixUnitaire(prixPieceCalculer);
												         listDetailFacturePFTmp.get(i).setMontantHT(MontantHTPieceCalculer);
												         listDetailFacturePFTmp.get(i).setMontantTVA(MontantTVAPieceCalculer);
												         listDetailFacturePFTmp.get(i).setMontantTTC(MontantTTCPieceCalculer);
												         listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i));
														
													}else
													{
														listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
													}
												}else
												{
													listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
												}
											
												
											}else
											{
												 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
											}
										 
										
										 
										 
										 
									 }
									  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
							          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
							          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
									 
								 }
								 
								 if(listDetailFacturePFImprimer.size()!=0)
								 {
									 
									    montanttotal=BigDecimal.ZERO;
								         montanttotalHT=BigDecimal.ZERO;
								         montanttotalTVA=BigDecimal.ZERO;
									 
									 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
									 {
										 
										  
										 
										 trouve=false;
										 
										 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
										 {
											if(listDetailFacturePFImprimerTmp.get(j).getArticle().getId()==listDetailFacturePFImprimer.get(i).getArticle().getId() && listDetailFacturePFImprimerTmp.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2)) && listDetailFacturePFImprimer.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2))) 
											{
												trouve=true;
												
													
													
													
													listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
													listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
													listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
													listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
													listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
													listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction());			
													listDetailFacturePFImprimerTmp.get(j).setBrutHT(listDetailFacturePFImprimerTmp.get(j).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire()));
													listDetailFacturePFImprimerTmp.get(j).setRemiseCommerciale(listDetailFacturePFImprimerTmp.get(j).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire()));
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
								 
								 
								 
								 
								 
								 /////////////////////////////////////////////////////////////////////////////////  Ajouter Les prix Aux Offres   ///////////////////////////////////////////////////////////////////////////////////
								
								 boolean ErreurPrix=false;
								 if(listDetailFacturePFImprimerTmp.size()!=0)
									{
									 for(int t=0;t<listDetailFacturePFImprimerTmp.size();t++)
									 {
										 
										 if(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()!=null)
										 {
											 if(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
											 {
												 
												 if(listDetailFacturePFImprimerTmp.get(t).getReduction()!=null)
													 
												 {
													 
													 if(listDetailFacturePFImprimerTmp.get(t).getReduction().compareTo(new BigDecimal(100))==0)
														 
													 {
														 
															DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (listDetailFacturePFImprimerTmp.get(t).getArticle().getId(), listDetailFacturePFImprimerTmp.get(t).getFacturePF().getMagasin().getId(),listDetailFacturePFImprimerTmp.get(t).getSousFamille().getFamileArticlePF().getId() , listDetailFacturePFImprimerTmp.get(t).getSousFamille().getId() , listDetailFacturePFImprimerTmp.get(t).getFacturePF().getClientPF().getId());
					  	  		  				 			
						  	  		  				 		
						  	  		  				 		if(detailprixarticle!=null)
						  	  		  				 			{
						  	  		  				 			
						  	  		  				 		listDetailFacturePFImprimerTmp.get(t).setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
						  	  		  				 	listDetailFacturePFImprimerTmp.get(t).setBrutHT(listDetailFacturePFImprimerTmp.get(t).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()));
														listDetailFacturePFImprimerTmp.get(t).setRemiseCommerciale(listDetailFacturePFImprimerTmp.get(t).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()));
						  	  		  				 				
						  	  		  				 			}else
						  	  		  				 			{
						  	  		  				 				
						  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailFacturePFImprimerTmp.get(t).getArticle().getId(),listDetailFacturePFImprimerTmp.get(t).getFacturePF().getMagasin().getId(),listDetailFacturePFImprimerTmp.get(t).getSousFamille().getFamileArticlePF().getId() , listDetailFacturePFImprimerTmp.get(t).getSousFamille().getId());
							
						  	  		  				 			if(detailprixarticleTmp!=null)	
						  	  		  				 			{
						  	  		  				 				
						  	  		  				 			listDetailFacturePFImprimerTmp.get(t).setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
						  	  		  				 		listDetailFacturePFImprimerTmp.get(t).setBrutHT(listDetailFacturePFImprimerTmp.get(t).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()));
															listDetailFacturePFImprimerTmp.get(t).setRemiseCommerciale(listDetailFacturePFImprimerTmp.get(t).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()));
						  	  		  				 				
						  	  		  				 			}else
						  	  		  				 			{
						  	  		  				 				
						  	  		  				 			ErreurPrix=true;
						  	  		  				 			
						  	  		  				 			}
						  	  		  				 				
						  	  		  				 				
						  	  		  				 				
						  	  		  				 				
						  	  		  				 			}	 
														 
													 }
													 												 
													 
												 }
												 
												 
												 
											 }
											 
											 
											 
										 }
										 
										 
									 }
								 }
								
								 if(ErreurPrix==true)
								 {
									 if(facturepfTmp.getNumFacture()!=null)
									 {
										
										 if(facturepfTmp.getNumFacture().isEmpty()==true)
										 {
											 JOptionPane.showMessageDialog(null, "Veuillez Entrer Les Prix Aux Offres existant Dans la facture Num : "+facturepfTmp.getNumBl(),"Erreur", JOptionPane.ERROR_MESSAGE);
										
										 break;
										  
										 }else
										 {
											 JOptionPane.showMessageDialog(null,  "Veuillez Entrer Les Prix Aux Offres existant Dans la facture Num : "+facturepfTmp.getNumFacture(),"Erreur", JOptionPane.ERROR_MESSAGE);
											 break;
										 }
									 }else
									 {
										 JOptionPane.showMessageDialog(null,  "Veuillez Entrer Les Prix Aux Offres existant Dans la facture Num : "+facturepfTmp.getNumBl(), "Erreur",JOptionPane.ERROR_MESSAGE);
										 break;
									 }
									 
									 
								 }
								 
								////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
							
								 for(int d=0;d<listFacturePFTmp.size();d++)
								 {
									 facturepfTmp=listFacturePFTmp.get(d);
									 
									 if(facturepfTmp.getModeReglement()!=null)
							  			{
							  				
							  					
							  					
							  					if(facturepfTmp.getEspece()!=null)
												{
							  						if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
								  					{
							  							if(checkAppliquerTimbre.isSelected()==true)
							  							{
							  								timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
															timberespece=timberespece.add(timber.multiply(facturepfTmp.getEspece().setScale(6, RoundingMode.HALF_UP))) ;
							  							}
							  							
								  					}else
								  					{
								  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
														timberespece=timberespece.add(timber.multiply(facturepfTmp.getEspece().setScale(6, RoundingMode.HALF_UP))) ;
								  					}
													
												
													
													
												}else
								  			{
								  				timberespece=timberespece.add(BigDecimal.ZERO);
								  			}
							  				
							  			}else
							  			{
							  				timberespece=timberespece.add(BigDecimal.ZERO);
							  				timberversement=timberversement.add(BigDecimal.ZERO);
							  			}
										
										if(facturepfTmp.getModeReglement()!=null)
							  			{
							  				
							  					if(facturepfTmp.getVersement()!=null)
												{
							  						if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
								  					{
							  							if(checkAppliquerTimbre.isSelected()==true)
							  							{
							  								timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
															timberversement=timberversement.add(timber.multiply(facturepfTmp.getVersement().setScale(6, RoundingMode.HALF_UP))) ;
							  							}
							  							
								  					}else
								  					{
								  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
														timberversement=timberversement.add(timber.multiply(facturepfTmp.getVersement().setScale(6, RoundingMode.HALF_UP))) ;
								  					}
											
													
													
												} 
								  			else
								  			{
								  				timberversement=timberversement.add(BigDecimal.ZERO);
								  			}
							  				
							  			}else
							  			{
							  				timberespece=timberespece.add(BigDecimal.ZERO);
							  				timberversement=timberversement.add(BigDecimal.ZERO);
							  			}
										
									
									
									 
								 }
									
									
									
									 txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
									netapayer=timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP).add(new BigDecimal(txttotalmontantTTC.getText()));
								   txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
							  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
							  			txttimbre.setText(timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP)+"");
							  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
							  			
						  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	
								 String dateFacture="";
						  		   
								 
								 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
								/*
								 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
								 * 
								 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
								 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
								 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
								 */
						  		 
									Map parameters = new HashMap();
									parameters.put("dateFacture", dateFacture);
									String totalht=String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
									String totaltva=String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
									String totalttc=String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
									String timbertmp=String.valueOf(new BigDecimal(txttimbre.getText()));
									String netapayerTmp=String.valueOf(new BigDecimal(txtnetapayer.getText()));
									
									parameters.put("TotalHT", totalht);
									parameters.put("TotalTVA", totaltva);
									parameters.put("TotalTTC",totalttc);
									parameters.put("client", facturepfTmp.getClientPF().getNom());
									
									if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepfTmp.getType().equals(Constantes.TYPE_FACTURE))
									{
										parameters.put("NumFacture", facturepfTmp.getNumFacture());
										
										if(facturepfTmp.getImprimerAvecNumBl()!=null)
										{
											if(!facturepfTmp.getImprimerAvecNumBl().equals(""))
											{
												
												if(facturepfTmp.getImprimerAvecNumBl().equals(Constantes.CODE_OUI))
												{
													 parameters.put("NumBL", facturepfTmp.getNumBl());
														
														if(facturepfTmp.getDateBl()!=null)
														{
															parameters.put("dateBl",dateFormat.format( facturepfTmp.getDateBl()));
														}else
														{
															parameters.put("dateBl", "");
														}
													
													 
												}else if(facturepfTmp.getImprimerAvecNumBl().equals(Constantes.CODE_NON))
												
												{
													 
													 parameters.put("NumBL", "");
														
														 
															parameters.put("dateBl", "");
														 
													
												}
												
												 
												
											}else
											{
												
												if(chckbxImprimerNumBl.isSelected()==true)
												{
													parameters.put("NumBL", facturepfTmp.getNumBl());
													
													if(facturepfTmp.getDateBl()!=null)
													{
														parameters.put("dateBl",dateFormat.format( facturepfTmp.getDateBl()));
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
												parameters.put("NumBL", facturepfTmp.getNumBl());
												
												if(facturepfTmp.getDateBl()!=null)
												{
													parameters.put("dateBl",dateFormat.format( facturepfTmp.getDateBl()));
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
										
										
										
                                     
										if(facturepfTmp.getImprimerAvecNumCommande()!=null)
										{
											if(!facturepfTmp.getImprimerAvecNumCommande().equals(""))
											{
												
												if(facturepfTmp.getImprimerAvecNumCommande().equals(Constantes.CODE_OUI))
												{
													if(facturepfTmp.getDateBCommande()!=null)
													{
														parameters.put("dateBC",dateFormat.format(facturepfTmp.getDateBCommande()) );
													}else
													{
														parameters.put("dateBC", "");
													}
													if(facturepfTmp.getNumCommande()!=null)
													{
														parameters.put("NumBC", facturepfTmp.getNumCommande());
													}else
													{
														parameters.put("NumBC", "");
													}
													
													 
												}else if(facturepfTmp.getImprimerAvecNumCommande().equals(Constantes.CODE_NON))
												
												{
													 
													 
														parameters.put("dateBC", "");
													 
													 
														parameters.put("NumBC", "");
													 
														 
													
												}
												
												 
												
											}else
											{
												
												if(chckbxImprimerNumBc.isSelected()==true)
												{
													if(facturepfTmp.getDateBCommande()!=null)
													{
														parameters.put("dateBC",dateFormat.format(facturepfTmp.getDateBCommande()) );
													}else
													{
														parameters.put("dateBC", "");
													}
													if(facturepfTmp.getNumCommande()!=null)
													{
														parameters.put("NumBC", facturepfTmp.getNumCommande());
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
											 
												if(facturepfTmp.getDateBCommande()!=null)
												{
													parameters.put("dateBC",dateFormat.format(facturepfTmp.getDateBCommande()) );
												}else
												{
													parameters.put("dateBC", "");
												}
												if(facturepfTmp.getNumCommande()!=null)
												{
													parameters.put("NumBC", facturepfTmp.getNumCommande());
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
										
										
										
										
										
										
									
										
										
										
										
										
									} 
									
									
									
									parameters.put("tva", Constantes.TVA);
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
									
									if(facturepfTmp.getAdresseClient()!=null)
									{
										if(!facturepfTmp.getAdresseClient().equals(""))
										{
											parameters.put("adresse", facturepfTmp.getAdresseClient());
										}else
										{
											parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
										}
										
									}else
									{
										parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
									}
									
									parameters.put("timber",timbertmp);
									parameters.put("netapayer",netapayerTmp);
									parameters.put("code",facturepfTmp.getClientPF().getCode());
									
									if(facturepfTmp.getClientPF().getPatente()!=null)
									{
										parameters.put("patente", facturepfTmp.getClientPF().getPatente());
									}else
									{
										parameters.put("patente", "");
									}
									
									if(facturepfTmp.getClientPF().getIce()!=null)
									{
										parameters.put("iceclient",facturepfTmp.getClientPF().getIce());
									}else
									{
										parameters.put("iceclient","");
									}
									
									String datechance="";
									if(facturepfTmp.getClientPF().getDelaiPaiement()!=null)
									{
										if(facturepfTmp.getClientPF().getDelaiPaiement().compareTo(BigDecimal.ZERO)!=0)
										{
											
											if(facturepfTmp.getClientPF().getDelaiPaiementParBlOuFacture()!=null)
											{
												
												if(!facturepfTmp.getClientPF().getDelaiPaiementParBlOuFacture().equals(""))
												{
													if(facturepfTmp.getClientPF().getDelaiPaiementParBlOuFacture().equals(Constantes.TYPE_BON_LIVRAISON))
													{
														parameters.put("delai","Delai de paiement :  "+ facturepfTmp.getClientPF().getDelaiPaiement().intValue()+" jours date de bon livraison");
														
														datechance=dateFormat.format(DateUtils.ajoutNbJours(facturepfTmp.getDateBl(), facturepfTmp.getClientPF().getDelaiPaiement().intValue()));
														
														 
														
														parameters.put("dateechance","Date d'échance     :  "+datechance);
													
													}else if(facturepfTmp.getClientPF().getDelaiPaiementParBlOuFacture().equals(TRANSFERE_BL_FACTURE))
													{
														
														parameters.put("delai","Delai de paiement :  "+ facturepfTmp.getClientPF().getDelaiPaiement().intValue()+" jours date de facture");
													
                                                        datechance=dateFormat.format(DateUtils.ajoutNbJours(facturepfTmp.getDateFacture(), facturepfTmp.getClientPF().getDelaiPaiement().intValue()));
														
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
									
									
									
									/*if(checkmodepaiement.isSelected()==true)
									{*/
										if( facturepfTmp.getModeReglement()!=null)
										{
											String numpiece ="";
											String ModePaiement ="";
											boolean espece , cheque = false ;
											boolean virement , traite  =false;
											boolean  versement =false;
											boolean  credit =false;
											if(facturepfTmp.getEspece()!=null)
											{
												if(facturepfTmp.getEspece().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_ESPECE;
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_ESPECE;
													}
													
													espece=true;
												}	
											}
											if(facturepfTmp.getCheque()!=null)
											{
												
												if(facturepfTmp.getCheque().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_CHEQUE;
														
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_CHEQUE;
													}
													
													cheque=true;
													if(facturepfTmp.getNumCheque()!=null)
													{
														
														if(!facturepfTmp.getNumCheque().equals(""))
														{
															
															numpiece=numpiece+	"  Cheque N° : "+ facturepfTmp.getNumCheque();
															
														}
														
														
														
													}
												
													
												}	
												
												
											}
											
											if(facturepfTmp.getVirement()!=null)
											{
												
												if(facturepfTmp.getVirement().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_VIREMENT;
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_VIREMENT;
													}
													
													virement=true;
												}	
												
												
											}
											if(facturepfTmp.getTraite()!=null)
											{
												
												if(facturepfTmp.getTraite().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_TRAITE;
														
													}else
													{
														
														ModePaiement=Constantes.MODE_REGLEMENT_TRAITE;
													}
													
													traite=true;
													
													if(facturepfTmp.getNumtraite()!=null)
													{
														
														if(!facturepfTmp.getNumtraite().equals(""))
														{
															
															numpiece=numpiece+" Traite N° : "+ facturepfTmp.getNumtraite();
															
														}
															
														
													}
													
													
													
												}	
												
												
											}
											
											
											if(facturepfTmp.getVersement()!=null)
											{
												
												if(facturepfTmp.getVersement().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_VERSEMENT;
														
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_VERSEMENT;
													}
													
													versement=true;
													
													if(facturepfTmp.getNumVersement()!=null)
													{
														
														if(!facturepfTmp.getNumVersement().equals(""))
														{
															
															
															numpiece=numpiece+	" Versement N° : "+ facturepfTmp.getNumVersement();
															
														}
														
														
														
													}
												
													
													
												}	
												
												
											}
											
											
											if(facturepfTmp.getCredit()!=null)
											{
												
												if(facturepfTmp.getCredit().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_CREDIT;
														
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_CREDIT;
													}
													
													credit=true;
												}	
												
												
											}
											
											
											
											
											if(facturepfTmp.getEspece()==null && facturepfTmp.getCheque()==null && facturepfTmp.getVirement()==null && facturepfTmp.getTraite()==null && facturepfTmp.getVersement()==null && facturepfTmp.getCredit()==null)
											{
												
												
												ModePaiement=facturepfTmp.getModeReglement();
												
												
											}
											
											if(checkSansmodepaiement.isSelected()==true)
											{
												ModePaiement="";
												 
											}
											
											if(checkSansNummodepaiement.isSelected()==true)
											{
												 
												numpiece="";
											}
											
											
											parameters.put("modepaiement", ModePaiement);
											
											
											
											if(!numpiece.equals(""))
											{
												
												parameters.put("numcheque", numpiece);
												
								
											}else
											{
												
											
													  parameters.put("numcheque", "");
													  
													  
												
												
											}
											
											
										
										}else
										{
											parameters.put("modepaiement", "");
											parameters.put("numcheque", "");
										}
								
									
									/*}else
									{
									parameters.put("modepaiement", "");
									}*/
									
									//double totalttc=Double.valueOf(txtnetapayer.getText());
									String x=txtnetapayer.getText().replace(".", ",");
									
									sommetowords= ConverterNumberToWords.converter(x);
								
									parameters.put("NumberToWords",sommetowords);
									
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
											 if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
											 {
												 JasperUtils.imprimerFacturePF(listDetailFacturePFImprimerTmp,parameters,apercu);
											 }else
											 {
												 JasperUtils.imprimerFacturePFSansTVA(listDetailFacturePFImprimerTmp,parameters,apercu);
											 }
											
											
										}else
										{
											 if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
											 {
												 JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters,apercu);
											 }else
											 {
												 JasperUtils.imprimerFacturePFSansTVA (listDetailFacturePFImprimer,parameters,apercu);
											 }
											
											
										}
										
										

										if(facturepfTmp.getImprimerAvecNumBl()!=null)
										{
											if(facturepfTmp.getImprimerAvecNumBl().equals(""))
											{
												
												if(chckbxImprimerNumBl.isSelected()==true)
												{
													facturepfTmp.setImprimerAvecNumBl(Constantes.CODE_OUI);
												}else
												{
													facturepfTmp.setImprimerAvecNumBl(Constantes.CODE_NON);
												}
												
												detailFacturePfdao.ViderSession();
												facturepfdao.edit(facturepfTmp);
												
											}
										}else
										{
											if(chckbxImprimerNumBl.isSelected()==true)
											{
												facturepfTmp.setImprimerAvecNumBl(Constantes.CODE_OUI);
											}else
											{
												facturepfTmp.setImprimerAvecNumBl(Constantes.CODE_NON);
											}
											
											detailFacturePfdao.ViderSession();
											facturepfdao.edit(facturepfTmp);
											
										}
										
										
										if(facturepfTmp.getImprimerAvecNumCommande()!=null)
										{
											if(facturepfTmp.getImprimerAvecNumCommande().equals(""))
											{
												
												if(chckbxImprimerNumBc.isSelected()==true)
												{
													facturepfTmp.setImprimerAvecNumCommande(Constantes.CODE_OUI);
												}else
												{
													facturepfTmp.setImprimerAvecNumCommande(Constantes.CODE_NON);
													 
												}
												
												detailFacturePfdao.ViderSession();
												facturepfdao.edit(facturepfTmp);
											}
										}else
										{
											if(chckbxImprimerNumBc.isSelected()==true)
											{
												facturepfTmp.setImprimerAvecNumCommande(Constantes.CODE_OUI);
												 
											}else
											{
												facturepfTmp.setImprimerAvecNumCommande(Constantes.CODE_NON);
												 
											}
											
											detailFacturePfdao.ViderSession();
											
											facturepfdao.edit(facturepfTmp);
											
										}
										
									 
											
										
										
									} catch (PrintException | IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage());
									}
								
									
							 }else
							 {
								 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
							 }
								
							
						}
						
						
					}
					
					
				}
				
				
	    		
	    		
	    		
	    		
	    		

					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				///////////////////////////////////////////////
				
				/*
				
				detailFacturePfdao.ViderSession();
String sommetowords="";

listDetailFacturePF.clear();
listDetailFacturePFImprimer.clear();
listFacturePFTmp.clear();
listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
 listDetailFacturePF=new ArrayList<DetailFacturePF>();
 listFacturePFTmp=new ArrayList<FacturePF>();
 FacturePF facturepfTmp=listFacturePF.get(table.getSelectedRow());
 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(),facturepfTmp.getDepot());
	for(int k=0;k<listFacturePFTmp.size();k++)
	{
		listDetailFacturePF.addAll(listFacturePFTmp.get(k).getDetailFacturePF());
	}

				boolean trouve=false;
				 if(listDetailFacturePF.size()!=0)
				 {
					 
					 for(int i=0;i<listDetailFacturePF.size();i++)
					 {
						 trouve=false;
						 
						 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
						 {
							if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2))) 
							{
								trouve=true;
								
								listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
								listDetailFacturePFImprimer.get(j).setPrixUnitaire((listDetailFacturePFImprimer.get(j).getPrixUnitaire().add(listDetailFacturePF.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
								listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
								listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
								listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
								listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
								listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
								
							}
							
							 
						 }
						 if(trouve==false)
						 {
							 listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
						 }
						 
						 
					 }
					 
					 
			  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
			  		    FacturePF facturepf=listFacturePF.get(table.getSelectedRow());
						
						Map parameters = new HashMap();
						parameters.put("dateFacture", dateFacture);
						String[]totalht=String.format("%,f", new BigDecimal(txttotalmontantHT.getText())).split(",");
						String[]totaltva=String.format("%,f",new BigDecimal(txttotalmontantTVA.getText())).split(",");
						String[]totalttc=String.format("%,f",new BigDecimal(txttotalmontantTTC.getText())).split(",");
						String[]timber=String.format("%,f",new BigDecimal(txttimbre.getText())).split(",");
						String[]netapayer=String.format("%,f",new BigDecimal(txtnetapayer.getText())).split(",");
						
						parameters.put("TotalHT", totalht[0]+","+ totalht[1].substring(0, 2));
						parameters.put("TotalTVA", totaltva[0]+","+ totaltva[1].substring(0, 2));
						parameters.put("TotalTTC",totalttc[0]+","+ totalttc[1].substring(0, 2));
						parameters.put("client", facturepf.getClientPF().getNom());
						
						if(facturepf.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepf.getType().equals(Constantes.TYPE_FACTURE))
						{
							parameters.put("NumFacture", facturepf.getNumFacture());
							parameters.put("type", "Facture N°    :");
							
						}else if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							parameters.put("NumFacture", facturepf.getNumBl());
							parameters.put("type", "BL N°    :");
						}
						
						
						parameters.put("tva", Constantes.TVA);
						if(utilisateur.getId()==1)
						{
							parameters.put("ice", Constantes.ICE_ETP);
						}else if (utilisateur.getId()==2)
						{
							parameters.put("ice", Constantes.ICE_AHLBRAHIM);
						}
						
						parameters.put("adresse", facturepf.getClientPF().getAdresse());
						parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
						parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
						parameters.put("code",facturepf.getClientPF().getCode());
						
						if(facturepf.getClientPF().getIce()!=null)
						{
							parameters.put("iceclient",facturepf.getClientPF().getIce());
						}else
						{
							parameters.put("iceclient","");
						}
						
						
						if(checkmodepaiement.isSelected()==true)
						{
							if( facturepf.getModeReglement()!=null)
							{
								parameters.put("modepaiement", facturepf.getModeReglement());
							}else
							{
								parameters.put("modepaiement", "");
							}
					
						
						}else
						{
						parameters.put("modepaiement", "");
						}
						
						//double totalttc=Double.valueOf(txtnetapayer.getText());
						String x=txtnetapayer.getText().replace(".", ",");
						
						sommetowords= ConverterNumberToWords.converter(x);
					
						parameters.put("NumberToWords",sommetowords);
						
						
						
						JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters);
					
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
			*/}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(364, 812, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 447, 1252, 51);
		add(layeredPane_1);	
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(10, 13, 89, 24);
		layeredPane_1.add(label);
		
		txtnumfacture = new JTextField();
		txtnumfacture.setEditable(false);
		txtnumfacture.setColumns(10);
		txtnumfacture.setBounds(57, 12, 183, 26);
		layeredPane_1.add(txtnumfacture);
	
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(261, 13, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(301, 11, 181, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(492, 12, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(541, 13, 177, 24);
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
		     		combodepot.addItem(depot.getLibelle());
		     		
		     		mapDepot.put(depot.getLibelle(), depot);
		     	
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
		  label_4.setBounds(734, 12, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.setBounds(788, 13, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		   comboClientpf = new JComboBox();
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(1039, 13, 183, 24);
		  layeredPane_1.add(comboClientpf);
		  AutoCompleteDecorator.decorate(comboClientpf);
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(992, 13, 56, 24);
		  layeredPane_1.add(lblClient);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 406, 1252, 30);
		add(titledSeparator_2);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(992, 875, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1114, 875, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(885, 818, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(778, 818, 97, 26);
		add(lblTotalQuantite);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les BL par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 791, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 185, 1252, 218);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				
				if(table.getSelectedRow()!=-1)
				{
					if(listFacturePF.size()!=0)
					{
                         detailFacturePfdao.ViderSession();
                         checkSansmodepaiement.setSelected(false);
						listDetailFacturePF=new ArrayList<DetailFacturePF>();
						listStockPF.clear();
						listDetailFacturePF.clear();
						 facturePF=listFacturePF.get(table.getSelectedRow()) ;
						 
						 
						 
						 
						 
						 
						 
						 
						 if(facturePF.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						 {
							 txtnumfacture.setText(facturePF.getNumFacture());
							dateChooserfacture.setDate(facturePF.getDateFacture());
							
							combodepot.setSelectedItem(facturePF.getDepot().getLibelle());
							combomagasin.setSelectedItem(facturePF.getMagasin().getLibelle());
							comboClientpf.setSelectedItem(facturePF.getClientPF().getNom());
							listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
							afficher_tableDetailFacturePF(listDetailFacturePF);
							 int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        BigDecimal netapayer=BigDecimal.ZERO;
						        BigDecimal timber=BigDecimal.ZERO;
						        while(i<listDetailFacturePF.size())
						        {
						        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(i).getArticle().getId(),facturePF.getMagasin().getId(),listDetailFacturePF.get(i).getSousFamille().getId());
						        	listStockPF.add(stockpf);
						        	  DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
							          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
							          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
						            
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
						        txttotalquantite.setText(sommequantite.setScale(2, RoundingMode.HALF_UP)+"");
						        txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
					  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
					  			if(facturePF.getModeReglement()!=null)
					  			{
					  				if(facturePF.getModeReglement().equals(Constantes.MODE_REGLEMENT_ESPECE))
						  			{
					  					
					  					if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
					  					{
					  						
					  						if(checkAppliquerTimbre.isSelected()==true)
					  						{
					  							
					  							timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
												timber=timber.multiply(new BigDecimal(txttotalmontantTTC.getText()));
					  						}else
					  						{
					  							timber=BigDecimal.ZERO;
					  						}
					  						
					  						
					  						
					  					}else
					  					{
					  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
											timber=timber.multiply(new BigDecimal(txttotalmontantTTC.getText()));
					  						
					  					}
					  					
						  				
										
										
										
										
						  			}else
						  			{
						  				timber=BigDecimal.ZERO;
						  			}
					  				
					  			}else
					  			{
					  				timber=BigDecimal.ZERO;
					  			}
					  			
					  			
								txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
								netapayer=timber.add(new BigDecimal(txttotalmontantTTC.getText()));
					  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
								}else
						 {
							 
								txtnumfacture.setText(facturePF.getNumFacture());
								dateChooserfacture.setDate(facturePF.getDateFacture());
								combodepot.setSelectedItem(facturePF.getDepot().getLibelle());
								combomagasin.setSelectedItem(facturePF.getMagasin().getLibelle());
								comboClientpf.setSelectedItem(facturePF.getClientPF().getNom());
								listFacturePFTmp=facturepfdao.findByNumFacture(facturePF.getNumFacture(),facturePF.getDepot());
								
								for(int i=0;i<listFacturePFTmp.size();i++)
								{
									listDetailFacturePF.addAll(listFacturePFTmp.get(i).getDetailFacturePF());
								}
								
								//listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByNumFacture(facturePF.getNumFacture());
								
								
								afficher_tableDetailFacturePF(listDetailFacturePF);
								 int i=0;
							        BigDecimal montanttotal=BigDecimal.ZERO;
							        BigDecimal sommequantite=BigDecimal.ZERO;
							        BigDecimal montanttotalHT=BigDecimal.ZERO;
							        BigDecimal montanttotalTVA=BigDecimal.ZERO;
							        BigDecimal netapayer=BigDecimal.ZERO;
							        BigDecimal timber =BigDecimal.ZERO;
							        BigDecimal timberespece=BigDecimal.ZERO;
							        BigDecimal timberversement=BigDecimal.ZERO;
							        while(i<listDetailFacturePF.size())
							        {
							        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(i).getArticle().getId(),facturePF.getMagasin().getId(),listDetailFacturePF.get(i).getSousFamille().getId());
							        	listStockPF.add(stockpf);
							        	  DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
								          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
								          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
							            
							            i++;
							        }
							       txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
							        txttotalquantite.setText(sommequantite.setScale(2, RoundingMode.HALF_UP)+"");
							        txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
						  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
						  			
						  			for(int k=0;k<listFacturePFTmp.size();k++)
						  			{
						  				facturePF=listFacturePFTmp.get(k);
						  				
						  				if(facturePF.getModeReglement()!=null )
							  			{
							  				//Constantes.MODE_REGLEMENT_ESPECE
							  				
							  				if(facturePF.getEspece()!=null)
								  			{
							  					
							  					if(facturePF.getEspece().compareTo(BigDecimal.ZERO)>0)
							  					{
							  						
							  						if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
								  					{
							  							
							  							if(checkAppliquerTimbre.isSelected()==true)
							  							{
							  								timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
									  						
									  						timberespece=timberespece.add(timber.multiply(facturePF.getEspece())) ;
							  								
							  							}else
							  							{
							  								timberespece=timberespece.add(BigDecimal.ZERO);
							  							}
							  							
							  							
							  							
								  					}else
								  					{
								  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
								  						
								  						timberespece=timberespece.add(timber.multiply(facturePF.getEspece())) ;
								  					}
								  						
							  							
							  						
							  						
							  					
							  						
							  					}
							  					else
							  					{
							  						
							  						timberespece=timberespece.add(BigDecimal.ZERO);
							  						
							  						
							  					}
								  				
												
												
												
								  			}else if(facturePF.getEspece()==null && facturePF.getModeReglement().equals(Constantes.MODE_REGLEMENT_ESPECE))
								  			{
								  				if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
							  					{
								  					
								  					if(checkAppliquerTimbre.isSelected()==true)
						  							{
								  						
								  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
										  				timberespece=timberespece.add(timber.multiply(facturePF.getMontantTTC()));
						  							}else
						  							{
						  								timberespece=timberespece.add(BigDecimal.ZERO);
						  							}
								  					
								  					
							  					}else
							  					{
							  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
									  				timberespece=timberespece.add(timber.multiply(facturePF.getMontantTTC()));
							  					}
								  				
								  				
								  				
								  				
								  			}
							  				
							  				
							  				if(facturePF.getVersement()!=null)
								  			{
							  					
							  					if(facturePF.getVersement().compareTo(BigDecimal.ZERO)>0)
							  					{
							  						if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
								  					{
							  							if(checkAppliquerTimbre.isSelected()==true)
							  							{
							  								
							  								
							  								timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
															timberversement=timberversement.add(timber.multiply(facturePF.getVersement()));
							  							}else
							  							{
							  								timberversement=timberversement.add(BigDecimal.ZERO);
							  							}
							  							
							  							
								  					}else
								  					{
								  						
								  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
														timberversement=timberversement.add(timber.multiply(facturePF.getVersement()));
								  						
								  					}
							  						
							  					
													
													
							  						
							  					}
							  					else
							  					{
							  						timberversement=timberversement.add(BigDecimal.ZERO);
							  					}
								  				
												
												
												
								  			}else if(facturePF.getVersement()==null && facturePF.getModeReglement().equals(Constantes.MODE_REGLEMENT_VERSEMENT))
								  			{
								  				if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
							  					{
								  					
								  					if(checkAppliquerTimbre.isSelected()==true)
								  					{
								  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
										  				timberversement=timberversement.add(timber.multiply(facturePF.getMontantTTC()))  ;
								  					}
								  					
								  					
								  					
							  					}else
							  					{
							  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
									  				timberversement=timberversement.add(timber.multiply(facturePF.getMontantTTC()))  ;
							  					}
								  				
								  			
								  				
								  				
								  			}
							  				
							  				
							  				
							  				
							  				
							  				
							  				
							  			}else
							  			{
							  				timberversement=timberversement.add(BigDecimal.ZERO);
							  				timberespece=timberespece.add(BigDecimal.ZERO);
							  				
							  			}
							  			
						  				
						  				
						  				
						  			}
						  			
						  	
						  			
									txttimbre.setText(timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP)+"");
									netapayer=timberversement.add(timberespece).add(new BigDecimal(txttotalmontantTTC.getText()));
						  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
						  
							 checkmodepaiement.setSelected(true);
							 
						 }
						 
						 
						
					}
					
				}
				
			}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num Facture", "Date Facture", "Etat","Type", "Client", "Depot", "Magasin", "Montant TTC","Imprimer"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setGridColor(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(combopardepot.getSelectedItem().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    	if(comboparClient.getSelectedItem().equals("")
	    			&& txtparnumfacture.getText().equals("") 
	    			&& pardateChooser.getDate()==null
	    			&& dateFacture_Au.getDate()==null
	    			&& combopardepot.getSelectedItem().equals(""))
	    	{
	    		
	    	
	    		initialiserFacture();
	    		InitialiseTableau();
	    		InitialiseTableauFacture();
	    		chckbxImprimerNumBc.setSelected(false);
	    		chckbxImprimerNumBl.setSelected(false);
	    		checkEnTete.setSelected(false);
	    	}else
	    	{
	    		chckbxImprimerNumBc.setSelected(false);
	    		chckbxImprimerNumBl.setSelected(false);
	    		checkEnTete.setSelected(false);
	    		
	    		ClientPF clientpf=mapClientPF.get(comboparClient.getSelectedItem());
	    		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
	    		initialiserFacture();
	    		
	    		InitialiseTableau();
	    		listFacturePF.clear();
	    		listFacturePFCharger.clear();
	    		
	    		
	    		String req="";
	    		if(chckbxAvecGratuite.isSelected()==true)
	    		{
	    			req=req+" and c.id in (select d.facturePF.id from DetailFacturePF d where d.reduction=100)";
	    		}
	    		
	    		if(!comboModeReglemnt.getSelectedItem().toString().equals(""))
	    		{
	    			
	    			comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_ESPECE);
	    		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_CHEQUE);
	    		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_VERSEMENT);
	    		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_VIREMENT);
	    		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_TRAITE);
	    		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_CREDIT);	
	    			
	    		    if(comboModeReglemnt.getSelectedItem().toString().equals(Constantes.MODE_REGLEMENT_ESPECE))
	    		    {
	    		    	req=req+" and c.espece <>0 ";
	    		    	
	    		    	
	    		    }else if(comboModeReglemnt.getSelectedItem().toString().equals(Constantes.MODE_REGLEMENT_CHEQUE))
	    		    {
	    		    	req=req+" and c.cheque <>0 ";
	    		    	
	    		    }else if(comboModeReglemnt.getSelectedItem().toString().equals(Constantes.MODE_REGLEMENT_VERSEMENT))
	    		    {
	    		    	req=req+" and c.versement <>0 ";
	    		    	
	    		    }else if(comboModeReglemnt.getSelectedItem().toString().equals(Constantes.MODE_REGLEMENT_VIREMENT))
	    		    {
	    		    	req=req+" and c.virement <>0 ";
	    		    	
	    		    }else if(comboModeReglemnt.getSelectedItem().toString().equals(Constantes.MODE_REGLEMENT_TRAITE))
	    		    {
	    		    	
	    		    	req=req+" and c.traite <>0 ";
	    		    	
	    		    }else if(comboModeReglemnt.getSelectedItem().toString().equals(Constantes.MODE_REGLEMENT_CREDIT))
	    		    {
	    		    	req=req+" and c.credit <>0 ";
	    		    	
	    		    } 
	    			
	    			
	    		}
	    		
	    		
	    			 listFacturePFCharger=facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegleFacture(txtparnumfacture.getText(),clientpf, pardateChooser.getDate(),dateFacture_Au.getDate(), depot, req);
	    			 
	    			if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
	    			{
	    				for(int i=0;i<listFacturePFCharger.size();i++)
		    			{
		    				
		    				FacturePF facture=listFacturePFCharger.get(i);
		    				if(!facture.getType().equals(Constantes.TYPE_BON_LIVRAISON))
		    				{
		    				
		    					 boolean exist=false;
		    					
		    					for(int j=0;j<listFacturePF.size();j++)
			    				{
			    					
			    					FacturePF factureTmp=listFacturePF.get(j);
			    					if(factureTmp.getNumFacture()!=null && facture.getNumFacture()!=null)
			    					{
			    						
			    						if(factureTmp.getNumFacture().equals(facture.getNumFacture()))
			    						{
			    							exist=true;
			    							
			    						}
			    						
			    					}
			    					
			    					
			    				}
		    					
		    					if(exist==false)
		    					{
		    						
		    						listFacturePF.add(facture);
		    					}
		    					
		    				}
		    				
		    				
		    			}
		    			afficher_tableFacturePF(listFacturePF);
	    				
	    			}else
	    			{
	    				listFacturePF.addAll(listFacturePFCharger);
	    				afficher_tableFacturePF(listFacturePF);
	    			}
	    			
	    			
	    			checkEnTete.setSelected(false);
	    			lblEnTete.setVisible(false);
	    			comboEnTete.setSelectedItem("");
	    			comboEnTete.setVisible(false);
	    		
	    	}
	    				
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 150, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLabel label_2 = new JLabel("Total Montant TVA :");
	    label_2.setBounds(992, 844, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1114, 844, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1114, 812, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(992, 812, 105, 26);
	    add(label_5);
	    
	   
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1252, 90);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num Facture :");
	    lblNumFacture.setBounds(10, 11, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumfacture = new JTextField();
	    txtparnumfacture.setBounds(106, 11, 151, 26);
	    layeredPane_2.add(txtparnumfacture);
	    util.Utils.copycoller(txtparnumfacture);
	    txtparnumfacture.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumfacture.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(267, 11, 64, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel dateFacture_Du = new JLabel("Date Du:");
	    dateFacture_Du.setBounds(554, 11, 97, 24);
	    layeredPane_2.add(dateFacture_Du);
	    dateFacture_Du.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    dateFacture_Au = new JDateChooser();
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(616, 9, 151, 26);
	     layeredPane_2.add(pardateChooser);
	     pardateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {}
	     });
	     pardateChooser.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     pardateChooser.setLocale(Locale.FRANCE);
	     pardateChooser.setDateFormatString("dd/MM/yyyy");
	     
	     combopardepot = new JComboBox();
	     combopardepot.setBounds(1030, 12, 196, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(964, 11, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	      comboparClient = new JComboBox();
	     comboparClient.setSelectedIndex(-1);
	     comboparClient.setBounds(322, 12, 211, 24);
	     layeredPane_2.add(comboparClient);
	     AutoCompleteDecorator.decorate(comboparClient);
	      checkSansNummodepaiement = new JCheckBox("Imprimer Sans Num Mode de Paiement");
	     checkSansNummodepaiement.setFont(new Font("Tahoma", Font.BOLD, 11));
	     checkSansNummodepaiement.setBounds(482, 842, 281, 30);
	     add(checkSansNummodepaiement);
	     checkSansNummodepaiement.setVisible(true);
	     JLabel lblTimbre = new JLabel("Timbre 0,25%       :");
	     lblTimbre.setBounds(992, 912, 112, 26);
	     add(lblTimbre);
	     
	     txttimbre = new JTextField();
	     txttimbre.setEditable(false);
	     txttimbre.setColumns(10);
	     txttimbre.setBounds(1114, 912, 136, 26);
	     add(txttimbre);
	     
	     JLabel lblNetAPayer = new JLabel("Net A Payer         :");
	     lblNetAPayer.setBounds(992, 951, 112, 26);
	     add(lblNetAPayer);
	     
	     txtnetapayer = new JTextField();
	     txtnetapayer.setEditable(false);
	     txtnetapayer.setColumns(10);
	     txtnetapayer.setBounds(1114, 951, 136, 26);
	     add(txtnetapayer);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		txtparnumfacture.setText("");
	     		comboparClient.setSelectedItem("");;
	     		pardateChooser.setCalendar(null);
	     		dateFacture_Au.setCalendar(null);
	     		combopardepot.setSelectedIndex(-1);
	     		
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(657, 151, 106, 23);
	     add(button);
	   
	    if(utilisateur.getLogin().equals("admin"))
		  {
	    	Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
			  int k=0;
		     	 combopardepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		if(magasin!=null)
		     		{
		     			
		     			if(depot.getId()!=magasin.getDepot().getId())
		     			{
		     				combopardepot.addItem(depot.getLibelle());
				     		mapparDepot.put(depot.getLibelle(), depot);
		     				
		     			}
		     			
		     		}else
		     		{
		     			
		     			combopardepot.addItem(depot.getLibelle());
			     		mapparDepot.put(depot.getLibelle(), depot);
		     		}
		     		
		     	
		     	
		     		k++;
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combopardepot.addItem("");
				  combopardepot.addItem(depot.getLibelle());
				  mapparDepot.put(depot.getLibelle(), depot);
		     	
			  }
		  }
	    
	    comboparClient.addItem("");
	    
	    
	    dateFacture_Au.setLocale(Locale.FRANCE);
	    dateFacture_Au.setDateFormatString("dd/MM/yyyy");
	    dateFacture_Au.setBounds(807, 9, 151, 26);
	    layeredPane_2.add(dateFacture_Au);
	    
	    JLabel lblAu = new JLabel("Au");
	    lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblAu.setBounds(777, 11, 44, 24);
	    layeredPane_2.add(lblAu);
	    
	    JLabel label_6 = new JLabel("Mode Reglement :");
	    label_6.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_6.setBounds(22, 58, 126, 24);
	    layeredPane_2.add(label_6);
	    
	      comboModeReglemnt = new JComboBox();
	    comboModeReglemnt.setSelectedIndex(-1);
	    comboModeReglemnt.setBounds(158, 59, 249, 24);
	    layeredPane_2.add(comboModeReglemnt);
	    
	      chckbxAvecGratuite = new JCheckBox("Avec Gratuite");
	    chckbxAvecGratuite.setBounds(429, 60, 116, 23);
	    layeredPane_2.add(chckbxAvecGratuite);
	    
	    JButton btnDeslectionnerTout = new JButton();
	    btnDeslectionnerTout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 9);
	     		}
	    	}
	    });
	    btnDeslectionnerTout.setToolTipText("deselectionner Tout");
	    btnDeslectionnerTout.setIcon(imgDeselectAll);
	    btnDeslectionnerTout.setBounds(1190, 150, 26, 26);
	    add(btnDeslectionnerTout);
	    
	    JButton btnSelectionnertout = new JButton();
	    btnSelectionnertout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 9);
	     		}
	    		
	    	}
	    });
	    btnSelectionnertout.setToolTipText("Selectionner Tout");
	    btnSelectionnertout.setIcon(imgSelectAll);
	    btnSelectionnertout.setBounds(1219, 150, 26, 26);
	    add(btnSelectionnertout);
	    
	    JButton btnExporterToExcel = new JButton("Exporter To Excel");
	    btnExporterToExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		

				
				mapImprimer.clear();
	    		if(!remplirMapImprimer())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					
					
					
					
					for(int k=0;k<listFacturePF.size();k++)
					{
						FacturePF facturepfTmp=listFacturePF.get(k);
						
						if(mapImprimer.containsKey(facturepfTmp.getId()))
						{
							
							///////////////////////////////////////////////
							
			detailFacturePfdao.ViderSession();
			String sommetowords="";

			List<DetailFacturePF> listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
			List<DetailFacturePF> listDetailFacturePFImprimerTmp =new ArrayList<DetailFacturePF>();
			   List<DetailFacturePF> listDetailFacturePFTmp =new ArrayList<DetailFacturePF>();
			 listFacturePFTmp.clear();
			 if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
			 {
				 listDetailFacturePFTmp=detailFacturePfdao.listeDetailFacturePFByFacture(facturepfTmp.getId());
				 
			 }else
			 {
				 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getDepot());
				 listDetailFacturePFTmp=detailFacturePfdao.listeDetailFacturePFByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getMagasin());
				 
			 }
			    BigDecimal montanttotal=BigDecimal.ZERO;
		        BigDecimal montanttotalHT=BigDecimal.ZERO;
		        BigDecimal montanttotalTVA=BigDecimal.ZERO;
		        BigDecimal netapayer=BigDecimal.ZERO;
		        BigDecimal timber=BigDecimal.ZERO;
							boolean trouve=false;
							
							 if(listDetailFacturePFTmp.size()!=0)
							 {
								 
								 for(int i=0;i<listDetailFacturePFTmp.size();i++)
								 {
									 trouve=false;
									 
									 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
									 {
										if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePFTmp.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePFTmp.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
										{
											trouve=true;
											
											listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
											listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
											listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

											listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
											listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
											listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));			
											listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
											
										}
										
										
									 }
									 if(trouve==false)
									 {
										 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
									 }
									  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
							          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
							          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
									 
								 }
								 
								 if(listDetailFacturePFImprimer.size()!=0)
								 {
									 
									    montanttotal=BigDecimal.ZERO;
								         montanttotalHT=BigDecimal.ZERO;
								         montanttotalTVA=BigDecimal.ZERO;
									 
									 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
									 {
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
								 
							
								 /*
									if(facturepfTmp.getModeReglement()!=null)
						  			{
						  				if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_ESPECE))
							  			{
							  				timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
											timber=timber.multiply(montanttotal.setScale(6, RoundingMode.HALF_UP));
							  			}else
							  			{
							  				timber=BigDecimal.ZERO;
							  			}
						  				
						  			}else
						  			{
						  				timber=BigDecimal.ZERO;
						  			}
									
									if(facturepfTmp.getEspece()!=null)
									{
										
										timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
										timber=timber.multiply(facturepfTmp.getEspece().setScale(6, RoundingMode.HALF_UP));
										
										
									}
							*/
									
									
									
									
									/*
									 txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
									netapayer=timber.setScale(2, RoundingMode.HALF_UP).add(new BigDecimal(txttotalmontantTTC.getText()));
								   txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
							  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
							  			txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
							  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
							  			
						  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	
								
								
								 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
								 * 
								 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
								 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
								 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
								 */
								 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 String dateFacture="";
						  		   
								 
								 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
									Map parameters = new HashMap();
									parameters.put("dateFacture", dateFacture);
									String totalht=String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
									String totaltva=String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
									String totalttc=String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
									String timbertmp=String.valueOf(new BigDecimal(txttimbre.getText()));
									String netapayerTmp=String.valueOf(new BigDecimal(txtnetapayer.getText()));
									
									parameters.put("TotalHT", totalht);
									parameters.put("TotalTVA", totaltva);
									parameters.put("TotalTTC",totalttc);
									parameters.put("client", facturepfTmp.getClientPF().getNom());
									
									if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepfTmp.getType().equals(Constantes.TYPE_FACTURE))
									{
										parameters.put("NumFacture", facturepfTmp.getNumFacture());
										parameters.put("type", "Facture N°    :");
										
									}else if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
									{
										parameters.put("NumFacture", facturepfTmp.getNumBl());
										parameters.put("type", "BL N°    :");
									}
									
									
									
									parameters.put("tva", Constantes.TVA);
									if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
									{
										
										parameters.put("ice", Constantes.ICE_ETP);
									}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
									{
										
										
										parameters.put("ice", Constantes.ICE_AHLBRAHIM);
									}
									
									parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
									parameters.put("timber",timbertmp);
									parameters.put("netapayer",netapayerTmp);
									parameters.put("code",facturepfTmp.getClientPF().getCode());
									
									if(facturepfTmp.getClientPF().getIce()!=null)
									{
										parameters.put("iceclient",facturepfTmp.getClientPF().getIce());
									}else
									{
										parameters.put("iceclient","");
									}
									
									
									/*if(checkmodepaiement.isSelected()==true)
									{*/
										if( facturepfTmp.getModeReglement()!=null)
										{
											
											String ModePaiement ="";
											boolean espece , cheque = false ;
											boolean virement , traite  =false;
											boolean  versement =false;
											if(facturepfTmp.getEspece()!=null)
											{
												if(facturepfTmp.getEspece().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_ESPECE;
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_ESPECE;
													}
													
													espece=true;
												}	
											}
											if(facturepfTmp.getCheque()!=null)
											{
												
												if(facturepfTmp.getCheque().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_CHEQUE;
														
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_CHEQUE;
													}
													
													cheque=true;
												}	
												
												
											}
											
											if(facturepfTmp.getVirement()!=null)
											{
												
												if(facturepfTmp.getVirement().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_VIREMENT;
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_VIREMENT;
													}
													
													virement=true;
												}	
												
												
											}
											if(facturepfTmp.getTraite()!=null)
											{
												
												if(facturepfTmp.getTraite().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_TRAITE;
														
													}else
													{
														
														ModePaiement=Constantes.MODE_REGLEMENT_TRAITE;
													}
													
													traite=true;
												}	
												
												
											}
											
											
											if(facturepfTmp.getVersement()!=null)
											{
												
												if(facturepfTmp.getVersement().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_VERSEMENT;
														
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_VERSEMENT;
													}
													
													versement=true;
												}	
												
												
											}
											
											
											
											
											if(facturepfTmp.getEspece()==null && facturepfTmp.getCheque()==null && facturepfTmp.getVirement()==null && facturepfTmp.getTraite()==null && facturepfTmp.getVersement()==null)
											{
												
												
												ModePaiement=facturepfTmp.getModeReglement();
												
												
											}
											
											
											
											
											parameters.put("modepaiement", ModePaiement);
											
											
											
											if(cheque==true  &&  facturepfTmp.getNumCheque()!=null && traite==false && versement==false)
											{
												
												parameters.put("numcheque", "Cheque N° : "+ facturepfTmp.getNumCheque());
												
											}else if(traite==true  &&  facturepfTmp.getNumtraite()!=null && cheque==false && versement==false)
											{
												
												parameters.put("numcheque", "Traite N° : "+ facturepfTmp.getNumtraite());
												
											}else if(versement==true  &&  facturepfTmp.getNumVersement()!=null && cheque==false && traite==false)
											{
												
												parameters.put("numcheque", "Versement N° : "+ facturepfTmp.getNumVersement());
												
											}else if(cheque==true  &&  facturepfTmp.getNumCheque()!=null && traite==true && facturepfTmp.getNumtraite() !=null && versement==false)
											{
												
												parameters.put("numcheque", "Cheque N° : "+ facturepfTmp.getNumCheque()+" "+"Traite N° : "+ facturepfTmp.getNumtraite());
												
											}else if(cheque==true  &&  facturepfTmp.getNumCheque()!=null && traite==false && facturepfTmp.getNumVersement() !=null && versement==true)
											{
												
												parameters.put("numcheque", "Cheque N° : "+ facturepfTmp.getNumCheque()+" "+"Versement N° : "+ facturepfTmp.getNumVersement());
												
											}else if(traite==true  &&  facturepfTmp.getNumtraite()!=null && cheque==false && facturepfTmp.getNumVersement() !=null && versement==true)
											{
												
												parameters.put("numcheque", "Traite N° : "+ facturepfTmp.getNumtraite()+" "+"Versement N° : "+ facturepfTmp.getNumVersement());
												
											}else if(traite==true  &&  facturepfTmp.getNumtraite()!=null && cheque==true && facturepfTmp.getNumCheque()!=null && facturepfTmp.getNumVersement() !=null && versement==true)
											{
												
												parameters.put("numcheque", "Traite N° : "+ facturepfTmp.getNumtraite()+" "+"Versement N° : "+ facturepfTmp.getNumVersement()+" "+"Cheque N° : "+ facturepfTmp.getNumCheque());
												
											}else
											{
												
												
												
												  if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_CHEQUE) && facturepfTmp.getNumCheque()!=null)
												  { 
													  parameters.put("numcheque","Cheque N° : "+ facturepfTmp.getNumCheque());
												  
												  }else if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_TRAITE) && facturepfTmp.getNumtraite()!=null) 
												  {
													  parameters.put("numcheque","Traite N° : "+ facturepfTmp.getNumtraite());
												  
												  }else if(facturepfTmp.getModeReglement().equals(Constantes.MODE_REGLEMENT_VERSEMENT) && facturepfTmp.getNumVersement()!=null) 
												  {
													  parameters.put("numcheque","Versement N° : "+ facturepfTmp.getNumVersement());
												  
												  }
												  
												  
												  
												  else 
												  {
													  parameters.put("numcheque", "");
													  
													  
												}
												
											}
											
										
											if(checkSansmodepaiement.isSelected()==true)
											{
												parameters.put("modepaiement", "");
												parameters.put("numcheque", "");
											}
											
											
											
										
										}else
										{
											parameters.put("modepaiement", "");
											parameters.put("numcheque", "");
										}
								
									
									/*}else
									{
									parameters.put("modepaiement", "");
									}*/
									
									//double totalttc=Double.valueOf(txtnetapayer.getText());
									String x=txtnetapayer.getText().replace(".", ",");
									
									sommetowords= ConverterNumberToWords.converter(x);
								
									parameters.put("NumberToWords",sommetowords);
									
									
									try {
										
										if(listDetailFacturePFImprimerTmp.size()!=0)
										{
											JasperUtils.ExporterFacturePFToExcel(listDetailFacturePFImprimerTmp,parameters);
											
										}else
										{
											
											JasperUtils.ExporterFacturePFToExcel(listDetailFacturePFImprimer,parameters );
										}
										
									} catch (PrintException | IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage());
									}
								
									
							 }else
							 {
								 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
							 }
								
							
						}
						
						
					}
					
					
				}
				
				

					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				///////////////////////////////////////////////
				
				/*
				
				detailFacturePfdao.ViderSession();
String sommetowords="";

listDetailFacturePF.clear();
listDetailFacturePFImprimer.clear();
listFacturePFTmp.clear();
listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
 listDetailFacturePF=new ArrayList<DetailFacturePF>();
 listFacturePFTmp=new ArrayList<FacturePF>();
 FacturePF facturepfTmp=listFacturePF.get(table.getSelectedRow());
 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(),facturepfTmp.getDepot());
	for(int k=0;k<listFacturePFTmp.size();k++)
	{
		listDetailFacturePF.addAll(listFacturePFTmp.get(k).getDetailFacturePF());
	}

				boolean trouve=false;
				 if(listDetailFacturePF.size()!=0)
				 {
					 
					 for(int i=0;i<listDetailFacturePF.size();i++)
					 {
						 trouve=false;
						 
						 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
						 {
							if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2))) 
							{
								trouve=true;
								
								listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
								listDetailFacturePFImprimer.get(j).setPrixUnitaire((listDetailFacturePFImprimer.get(j).getPrixUnitaire().add(listDetailFacturePF.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
								listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
								listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
								listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
								listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
								listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
								
							}
							
							 
						 }
						 if(trouve==false)
						 {
							 listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
						 }
						 
						 
					 }
					 
					 
			  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
			  		    FacturePF facturepf=listFacturePF.get(table.getSelectedRow());
						
						Map parameters = new HashMap();
						parameters.put("dateFacture", dateFacture);
						String[]totalht=String.format("%,f", new BigDecimal(txttotalmontantHT.getText())).split(",");
						String[]totaltva=String.format("%,f",new BigDecimal(txttotalmontantTVA.getText())).split(",");
						String[]totalttc=String.format("%,f",new BigDecimal(txttotalmontantTTC.getText())).split(",");
						String[]timber=String.format("%,f",new BigDecimal(txttimbre.getText())).split(",");
						String[]netapayer=String.format("%,f",new BigDecimal(txtnetapayer.getText())).split(",");
						
						parameters.put("TotalHT", totalht[0]+","+ totalht[1].substring(0, 2));
						parameters.put("TotalTVA", totaltva[0]+","+ totaltva[1].substring(0, 2));
						parameters.put("TotalTTC",totalttc[0]+","+ totalttc[1].substring(0, 2));
						parameters.put("client", facturepf.getClientPF().getNom());
						
						if(facturepf.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepf.getType().equals(Constantes.TYPE_FACTURE))
						{
							parameters.put("NumFacture", facturepf.getNumFacture());
							parameters.put("type", "Facture N°    :");
							
						}else if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							parameters.put("NumFacture", facturepf.getNumBl());
							parameters.put("type", "BL N°    :");
						}
						
						
						parameters.put("tva", Constantes.TVA);
						if(utilisateur.getId()==1)
						{
							parameters.put("ice", Constantes.ICE_ETP);
						}else if (utilisateur.getId()==2)
						{
							parameters.put("ice", Constantes.ICE_AHLBRAHIM);
						}
						
						parameters.put("adresse", facturepf.getClientPF().getAdresse());
						parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
						parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
						parameters.put("code",facturepf.getClientPF().getCode());
						
						if(facturepf.getClientPF().getIce()!=null)
						{
							parameters.put("iceclient",facturepf.getClientPF().getIce());
						}else
						{
							parameters.put("iceclient","");
						}
						
						
						if(checkmodepaiement.isSelected()==true)
						{
							if( facturepf.getModeReglement()!=null)
							{
								parameters.put("modepaiement", facturepf.getModeReglement());
							}else
							{
								parameters.put("modepaiement", "");
							}
					
						
						}else
						{
						parameters.put("modepaiement", "");
						}
						
						//double totalttc=Double.valueOf(txtnetapayer.getText());
						String x=txtnetapayer.getText().replace(".", ",");
						
						sommetowords= ConverterNumberToWords.converter(x);
					
						parameters.put("NumberToWords",sommetowords);
						
						
						
						JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters);
					
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
			*/
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnExporterToExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterToExcel.setBounds(20, 812, 159, 32);
	    btnExporterToExcel.setIcon(imgExcel);
	    add(btnExporterToExcel);
	    
	     checkAppliquerTimbre = new JCheckBox("Appliquer Timbre");
	    checkAppliquerTimbre.setFont(new Font("Tahoma", Font.BOLD, 11));
	    checkAppliquerTimbre.setBounds(1279, 185, 229, 30);
	    add(checkAppliquerTimbre);
	    
	     checkSansmodepaiement = new JCheckBox("Imprimer Sans Mode de Paiement");
	    checkSansmodepaiement.setFont(new Font("Tahoma", Font.BOLD, 11));
	    checkSansmodepaiement.setBounds(482, 813, 245, 30);
	    add(checkSansmodepaiement);
	    
	     lblEnTete = new JLabel("En Tete :");
	    lblEnTete.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblEnTete.setBounds(1267, 283, 64, 24);
	    add(lblEnTete);
	    
	     comboEnTete = new JComboBox();
	    comboEnTete.setSelectedIndex(-1);
	    comboEnTete.setBounds(1342, 284, 191, 24);
	    add(comboEnTete);
	    
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
	    checkEnTete.setBounds(1268, 236, 229, 30);
	    add(checkEnTete);
	    for(int i=0;i<listClientPFP.size();i++)
	  	{
	  		ClientPF clientpf=listClientPFP.get(i);
	  		comboparClient.addItem(clientpf.getNom());
	  		mapClientPF.put(clientpf.getNom(), clientpf);
	  	}
	    
	    
	    
	    if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
	    {
	    	
	    	checkAppliquerTimbre.setVisible(true);
	    	
	    	
	    }else
	    {
	    	checkAppliquerTimbre.setVisible(false);
	    }
	    
	    
	    lblEnTete.setVisible(false);
		comboEnTete.setVisible(false);
		comboEnTete.addItem("");
		
		 chckbxImprimerNumBl = new JCheckBox("Imprimer Num BL");
		chckbxImprimerNumBl.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxImprimerNumBl.setBounds(1279, 332, 229, 30);
		add(chckbxImprimerNumBl);
		
		 chckbxImprimerNumBc = new JCheckBox("Imprimer Num BC");
		chckbxImprimerNumBc.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxImprimerNumBc.setBounds(1279, 365, 229, 30);
		add(chckbxImprimerNumBc);
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
		   
		   
		   
		   comboModeReglemnt.addItem("");
		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_ESPECE);
		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_CHEQUE);
		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_VERSEMENT);
		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_VIREMENT);
		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_TRAITE);
		    comboModeReglemnt.addItem(Constantes.MODE_REGLEMENT_CREDIT);
		    
		    comboModeReglemnt.setSelectedItem("");
		    
		    JButton button_1 = new JButton("Transfert En PDF");
		    button_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		
		    		
		    		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		    		 List<JasperPrint> jasperPrintList = new ArrayList<>(); 
		    		 
////////////////////////////////////////////////////////////////////// Factures Avoir Client PF    ////////////////////////////////////////////////////////////////////////////////////////////////////		    		 
		    		 

		    		 
		    	 
		    			listFactureAvoirClientPF.clear();
		    			listFactureAvoirClientPFCharger=factureAvoirClientpfdao.findByNumFcatureClientPFDateFactureDepotEtatRegle("",null, null, depot);
		    			 
		    				for(int i=0;i<listFactureAvoirClientPFCharger.size();i++)
			    			{
			    				
			    				FactureAvoirClientPF facture=listFactureAvoirClientPFCharger.get(i);
			    				if(!facture.getType().equals(Constantes.TYPE_BON_LIVRAISON))
			    				{
			    					if(facture.getNumFacture()!=null)
			    					{
			    						
			    						if(!facture.getNumFacture().isEmpty())
				    					{
			    							
			    							 boolean exist=false;
						    					
						    					for(int j=0;j<listFactureAvoirClientPF.size();j++)
							    				{
							    					
						    						FactureAvoirClientPF factureTmp=listFactureAvoirClientPF.get(j);
							    					if(factureTmp.getNumFacture()!=null && facture.getNumFacture()!=null)
							    					{
							    						
							    						if(factureTmp.getNumFacture().equals(facture.getNumFacture()))
							    						{
							    							exist=true;
							    							
							    						}
							    						
							    					}
							    					
							    					
							    				}
						    					
						    					if(exist==false)
						    					{
						    						
						    						listFactureAvoirClientPF.add(facture);
						    					}
				    					}
			    						
			    						
			    					}
			    				
			    					
			    					
			    				}
			    				
			    				
			    			}	
		    			 
////////////////////////////////////////////////////////////////////////////////////Facture Transport  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    		
		    
		    	    		 
		    	    		listFactureServiceTransport.clear();
		    	    		listFactureServiceTransportCharger.clear();
		    	    		
		    	    			 listFactureServiceTransportCharger=factureServiceTransportDAO.findByNumFcatureClientDateFactureDepotEtatRegleFacture("",null, null,null, depot);
		    	    			 
		    	    			 
		    	    				for(int i=0;i<listFactureServiceTransportCharger.size();i++)
		    		    			{
		    		    				
		    		    				FactureServiceTransport facture=listFactureServiceTransportCharger.get(i);
		    		    				if(!facture.getType().equals(Constantes.TYPE_BON_LIVRAISON))
		    		    				{
		    		    					if(facture.getNumFacture()!=null)
		    		    					{
		    		    						
		    		    						if(!facture.getNumFacture().isEmpty())
		    			    					{
		    		    							 boolean exist=false;
		 		    		    					
		 		    		    					for(int j=0;j<listFactureServiceTransport.size();j++)
		 		    			    				{
		 		    			    					
		 		    			    					FactureServiceTransport factureTmp=listFactureServiceTransport.get(j);
		 		    			    					if(factureTmp.getNumFacture()!=null && facture.getNumFacture()!=null)
		 		    			    					{
		 		    			    						
		 		    			    						if(factureTmp.getNumFacture().equals(facture.getNumFacture()))
		 		    			    						{
		 		    			    							exist=true;
		 		    			    							
		 		    			    						}
		 		    			    						
		 		    			    					}
		 		    			    					
		 		    			    					
		 		    			    				}
		 		    		    					
		 		    		    					if(exist==false)
		 		    		    					{
		 		    		    						
		 		    		    						listFactureServiceTransport.add(facture);
		 		    		    					}
		    		    							
		    			    					}
		    		    						
		    		    						
		    		    					}
		    		    				
		    		    					
		    		    				}
		    		    				
		    		    				
		    		    			}
		    		    			 
/////////////////////////////////////////////////////////////////////////////////////////    Factures Autres Ventes    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    		


		   	    	    		 
listFactureAutresVente.clear();
listFactureAutresVenteCharger.clear();

listFactureAutresVenteCharger=factureAutresVentedao.findByNumFcatureClientDateFactureDepotEtatRegleFacture("",null, null,null, depot);


for(int i=0;i<listFactureAutresVenteCharger.size();i++)
{

FactureAutresVente facture=listFactureAutresVenteCharger.get(i);
if(!facture.getType().equals(Constantes.TYPE_BON_LIVRAISON))
{

boolean exist=false;

for(int j=0;j<listFactureAutresVente.size();j++)
{

FactureAutresVente factureTmp=listFactureAutresVente.get(j);
if(factureTmp.getNumFacture()!=null && facture.getNumFacture()!=null)
{

if(factureTmp.getNumFacture().equals(facture.getNumFacture()))
{
exist=true;

}

}


}

if(exist==false)
{

listFactureAutresVente.add(facture);
}

}


}




	    	    		 
		    	    	
		    		 
		    		 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		    		 
		    		 
					
					mapImprimer.clear();
		    		if(!remplirMapImprimer())	{
						JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						/*
						boolean apercu=false;
						
						 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Afficher l'aperçu Avant l'impression ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )		
								
							{
								apercu=true;
							}
						*/
						
						
						for(int k=0;k<listFacturePF.size();k++)
						{
							FacturePF facturepfTmp=listFacturePF.get(k);
							
							if(mapImprimer.containsKey(facturepfTmp.getId()))
							{
								
								
								
								
								///////////////////////////////////////////////
								
				detailFacturePfdao.ViderSession();
				String sommetowords="";

				List<DetailFacturePF> listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
				List<DetailFacturePF> listDetailFacturePFImprimerTmp =new ArrayList<DetailFacturePF>();
				   List<DetailFacturePF> listDetailFacturePFTmp =new ArrayList<DetailFacturePF>();
				 listFacturePFTmp.clear();
				 if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
				 {
					 listDetailFacturePFTmp=detailFacturePfdao.listeDetailFacturePFByFacture(facturepfTmp.getId());
					 
				 }else
				 {
					 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getDepot());
					 listDetailFacturePFTmp=detailFacturePfdao.listeDetailFacturePFByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getMagasin());
					 
				 }
				    BigDecimal montanttotal=BigDecimal.ZERO;
			        BigDecimal montanttotalHT=BigDecimal.ZERO;
			        BigDecimal montanttotalTVA=BigDecimal.ZERO;
			        BigDecimal netapayer=BigDecimal.ZERO;
			        BigDecimal timber=BigDecimal.ZERO;
			        BigDecimal timberversement=BigDecimal.ZERO;
			        BigDecimal timberespece=BigDecimal.ZERO;
			       BigDecimal quantitePieceCalculer=BigDecimal.ZERO; 
			       BigDecimal prixPieceCalculer=BigDecimal.ZERO; 
			       BigDecimal MontantHTPieceCalculer=BigDecimal.ZERO; 
			       BigDecimal MontantTVAPieceCalculer=BigDecimal.ZERO; 
			       BigDecimal MontantTTCPieceCalculer=BigDecimal.ZERO; 
			        
								boolean trouve=false;
								
								 if(listDetailFacturePFTmp.size()!=0)
								 {
									 
									 for(int i=0;i<listDetailFacturePFTmp.size();i++)
									 {
										 
										 
										    quantitePieceCalculer=BigDecimal.ZERO; 
									         prixPieceCalculer=BigDecimal.ZERO; 
									         MontantHTPieceCalculer=BigDecimal.ZERO; 
									         MontantTVAPieceCalculer=BigDecimal.ZERO; 
									         MontantTTCPieceCalculer=BigDecimal.ZERO;  
										 
										 
										 trouve=false;
										 
										 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
										 {
											if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePFTmp.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2)) && !listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2))) 
											{
												trouve=true;
												 
												if(listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
												{
													if(listDetailFacturePFImprimer.get(j).getFacturePF().getImprimerPiece()!=null)
													{
														if(listDetailFacturePFImprimer.get(j).getFacturePF().getImprimerPiece()==true)
														{
															
															 quantitePieceCalculer=listDetailFacturePFTmp.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFTmp.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
													         prixPieceCalculer=listDetailFacturePFTmp.get(i).getPrixUnitaire().divide(listDetailFacturePFTmp.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
													         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
													        if(listDetailFacturePFTmp.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
													        {
													        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
													        }
													         
													         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
															
															listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(quantitePieceCalculer));
															listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(MontantHTPieceCalculer));
															listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

															listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(MontantTVAPieceCalculer));
															listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(MontantTTCPieceCalculer));
															if(listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)) && listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)))
															{
																listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction());			

															}else
															{
																listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));			

															}
															listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
															
															
															
															
															
															
															 
															 
															
														}else
														{
															listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
															listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
															listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

															listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
															listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
															if(listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)) && listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)))
															{
																listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction());			

															}else
															{
																listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));			

															}
															listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
														}
														
													}else
													{

														listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
														listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
														listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

														listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
														listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
														if(listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)) && listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)))
														{
															listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction());			

														}else
														{
															listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));			

														}
														listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
													
													}
												
													
													
												}else
												{
													listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
													listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
													listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

													listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
													listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
													if(listDetailFacturePFImprimer.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)) && listDetailFacturePFTmp.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2, RoundingMode.DOWN)))
													{
														listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction());			

													}else
													{
														listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));			

													}
													listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
												}
												
												
												
											}
											
											
										 }
										 if(trouve==false)
										 {
												if(listDetailFacturePFTmp.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFTmp.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
												{
													if(listDetailFacturePFTmp.get(i).getFacturePF().getImprimerPiece()!=null)
													{
														if(listDetailFacturePFTmp.get(i).getFacturePF().getImprimerPiece()==true)
														{
															
															 quantitePieceCalculer=listDetailFacturePFTmp.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFTmp.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
													         prixPieceCalculer=listDetailFacturePFTmp.get(i).getPrixUnitaire().divide(listDetailFacturePFTmp.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
													         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
													        if(listDetailFacturePFTmp.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
													        {
													        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
													        }
													         
													         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
													         
													         
													         listDetailFacturePFTmp.get(i).setQuantite(quantitePieceCalculer);
													         listDetailFacturePFTmp.get(i).setPrixUnitaire(prixPieceCalculer);
													         listDetailFacturePFTmp.get(i).setMontantHT(MontantHTPieceCalculer);
													         listDetailFacturePFTmp.get(i).setMontantTVA(MontantTVAPieceCalculer);
													         listDetailFacturePFTmp.get(i).setMontantTTC(MontantTTCPieceCalculer);
													         listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i));
															
														}else
														{
															listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
														}
														
													}else
													{

														listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
													
													}
												
													
												}else
												{
													 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
												}
											 
											
											 
											 
											 
										 }
										  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
								          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
								          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
										 
									 }
									 
									 if(listDetailFacturePFImprimer.size()!=0)
									 {
										 
										    montanttotal=BigDecimal.ZERO;
									         montanttotalHT=BigDecimal.ZERO;
									         montanttotalTVA=BigDecimal.ZERO;
										 
										 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
										 {
											 
											  
											 
											 trouve=false;
											 
											 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
											 {
												if(listDetailFacturePFImprimerTmp.get(j).getArticle().equals(listDetailFacturePFImprimer.get(i).getArticle()) && listDetailFacturePFImprimerTmp.get(j).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2)) && listDetailFacturePFImprimer.get(i).getReduction().setScale(2, RoundingMode.DOWN).equals(new BigDecimal(100).setScale(2))) 
												{
													trouve=true;
													
														
														
														
														listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
														listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
														listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
														listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
														listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
														listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction());			
                                                        listDetailFacturePFImprimerTmp.get(j).setBrutHT(listDetailFacturePFImprimerTmp.get(j).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire()));
														listDetailFacturePFImprimerTmp.get(j).setRemiseCommerciale(listDetailFacturePFImprimerTmp.get(j).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire()));
														
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
									 
									 
									 
									 
									 
									 /////////////////////////////////////////////////////////////////////////////////  Ajouter Les prix Aux Offres   ///////////////////////////////////////////////////////////////////////////////////
									
									 boolean ErreurPrix=false;
									 if(listDetailFacturePFImprimerTmp.size()!=0)
										{
										 for(int t=0;t<listDetailFacturePFImprimerTmp.size();t++)
										 {
											 
											 if(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()!=null)
											 {
												 if(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
												 {
													 
													 if(listDetailFacturePFImprimerTmp.get(t).getReduction()!=null)
														 
													 {
														 
														 if(listDetailFacturePFImprimerTmp.get(t).getReduction().compareTo(new BigDecimal(100))==0)
															 
														 {
															 
																DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (listDetailFacturePFImprimerTmp.get(t).getArticle().getId(), listDetailFacturePFImprimerTmp.get(t).getFacturePF().getMagasin().getId(),listDetailFacturePFImprimerTmp.get(t).getSousFamille().getFamileArticlePF().getId() , listDetailFacturePFImprimerTmp.get(t).getSousFamille().getId() , listDetailFacturePFImprimerTmp.get(t).getFacturePF().getClientPF().getId());
						  	  		  				 			
							  	  		  				 		
							  	  		  				 		if(detailprixarticle!=null)
							  	  		  				 			{
							  	  		  				 			
							  	  		  				 		listDetailFacturePFImprimerTmp.get(t).setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
							  	  		  				 	listDetailFacturePFImprimerTmp.get(t).setBrutHT(listDetailFacturePFImprimerTmp.get(t).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()));
															listDetailFacturePFImprimerTmp.get(t).setRemiseCommerciale(listDetailFacturePFImprimerTmp.get(t).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()));
															
							  	  		  				 				
							  	  		  				 			}else
							  	  		  				 			{
							  	  		  				 				
							  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailFacturePFImprimerTmp.get(t).getArticle().getId(),listDetailFacturePFImprimerTmp.get(t).getFacturePF().getMagasin().getId(),listDetailFacturePFImprimerTmp.get(t).getSousFamille().getFamileArticlePF().getId() , listDetailFacturePFImprimerTmp.get(t).getSousFamille().getId());
								
							  	  		  				 			if(detailprixarticleTmp!=null)	
							  	  		  				 			{
							  	  		  				 				
							  	  		  				 			listDetailFacturePFImprimerTmp.get(t).setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
							  	  		  				 		listDetailFacturePFImprimerTmp.get(t).setBrutHT(listDetailFacturePFImprimerTmp.get(t).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()));
																listDetailFacturePFImprimerTmp.get(t).setRemiseCommerciale(listDetailFacturePFImprimerTmp.get(t).getQuantite().multiply(listDetailFacturePFImprimerTmp.get(t).getPrixUnitaire()));
															
							  	  		  				 				
							  	  		  				 			}else
							  	  		  				 			{
							  	  		  				 				
							  	  		  				 			ErreurPrix=true;
							  	  		  				 			
							  	  		  				 			}
							  	  		  				 				
							  	  		  				 				
							  	  		  				 				
							  	  		  				 				
							  	  		  				 			}	 
															 
														 }
														 												 
														 
													 }
													 
													 
													 
												 }
												 
												 
												 
											 }
											 
											 
										 }
									 }
									
									 if(ErreurPrix==true)
									 {
										 if(facturepfTmp.getNumFacture()!=null)
										 {
											
											 if(facturepfTmp.getNumFacture().isEmpty()==true)
											 {
												 JOptionPane.showMessageDialog(null, "Veuillez Entrer Les Prix Aux Offres existant Dans la facture Num : "+facturepfTmp.getNumBl(),"Erreur", JOptionPane.ERROR_MESSAGE);
											
											 break;
											  
											 }else
											 {
												 JOptionPane.showMessageDialog(null,  "Veuillez Entrer Les Prix Aux Offres existant Dans la facture Num : "+facturepfTmp.getNumFacture(),"Erreur", JOptionPane.ERROR_MESSAGE);
												 break;
											 }
										 }else
										 {
											 JOptionPane.showMessageDialog(null,  "Veuillez Entrer Les Prix Aux Offres existant Dans la facture Num : "+facturepfTmp.getNumBl(), "Erreur",JOptionPane.ERROR_MESSAGE);
											 break;
										 }
										 
										 
									 }
									 
									////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
								
									 for(int d=0;d<listFacturePFTmp.size();d++)
									 {
										 facturepfTmp=listFacturePFTmp.get(d);
										 
										 if(facturepfTmp.getModeReglement()!=null)
								  			{
								  				
								  					
								  					
								  					if(facturepfTmp.getEspece()!=null)
													{
								  						if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
									  					{
								  							if(checkAppliquerTimbre.isSelected()==true)
								  							{
								  								timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
																timberespece=timberespece.add(timber.multiply(facturepfTmp.getEspece().setScale(6, RoundingMode.HALF_UP))) ;
								  							}
								  							
									  					}else
									  					{
									  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
															timberespece=timberespece.add(timber.multiply(facturepfTmp.getEspece().setScale(6, RoundingMode.HALF_UP))) ;
									  					}
														
													
														
														
													}else
									  			{
									  				timberespece=timberespece.add(BigDecimal.ZERO);
									  			}
								  				
								  			}else
								  			{
								  				timberespece=timberespece.add(BigDecimal.ZERO);
								  				timberversement=timberversement.add(BigDecimal.ZERO);
								  			}
											
											if(facturepfTmp.getModeReglement()!=null)
								  			{
								  				
								  					if(facturepfTmp.getVersement()!=null)
													{
								  						if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
									  					{
								  							if(checkAppliquerTimbre.isSelected()==true)
								  							{
								  								timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
																timberversement=timberversement.add(timber.multiply(facturepfTmp.getVersement().setScale(6, RoundingMode.HALF_UP))) ;
								  							}
								  							
									  					}else
									  					{
									  						timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
															timberversement=timberversement.add(timber.multiply(facturepfTmp.getVersement().setScale(6, RoundingMode.HALF_UP))) ;
									  					}
												
														
														
													} 
									  			else
									  			{
									  				timberversement=timberversement.add(BigDecimal.ZERO);
									  			}
								  				
								  			}else
								  			{
								  				timberespece=timberespece.add(BigDecimal.ZERO);
								  				timberversement=timberversement.add(BigDecimal.ZERO);
								  			}
											
										
										
										 
									 }
										
										
										
										 txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
										netapayer=timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP).add(new BigDecimal(txttotalmontantTTC.getText()));
									   txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
								  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
								  			txttimbre.setText(timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP)+"");
								  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
								  			
							  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							  		  	
									 String dateFacture="";
							  		   
									 
									 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
									/*
									 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
									 * 
									 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
									 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
									 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
									 */
							  		 
										Map parameters = new HashMap();
										parameters.put("dateFacture", dateFacture);
										String totalht=String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
										String totaltva=String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
										String totalttc=String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
										String timbertmp=String.valueOf(new BigDecimal(txttimbre.getText()));
										String netapayerTmp=String.valueOf(new BigDecimal(txtnetapayer.getText()));
										
										parameters.put("TotalHT", totalht);
										parameters.put("TotalTVA", totaltva);
										parameters.put("TotalTTC",totalttc);
										parameters.put("client", facturepfTmp.getClientPF().getNom());
										
										if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepfTmp.getType().equals(Constantes.TYPE_FACTURE))
										{
											parameters.put("NumFacture", facturepfTmp.getNumFacture());
											
											if(facturepfTmp.getImprimerAvecNumBl()!=null)
											{
												if(!facturepfTmp.getImprimerAvecNumBl().equals(""))
												{
													
													if(facturepfTmp.getImprimerAvecNumBl().equals(Constantes.CODE_OUI))
													{
														 parameters.put("NumBL", facturepfTmp.getNumBl());
															
															if(facturepfTmp.getDateBl()!=null)
															{
																parameters.put("dateBl",dateFormat.format( facturepfTmp.getDateBl()));
															}else
															{
																parameters.put("dateBl", "");
															}
														
														 
													}else if(facturepfTmp.getImprimerAvecNumBl().equals(Constantes.CODE_NON))
													
													{
														 
														 parameters.put("NumBL", "");
															
															 
																parameters.put("dateBl", "");
															 
														
													}
													
													 
													
												}else
												{
													
													if(chckbxImprimerNumBl.isSelected()==true)
													{
														parameters.put("NumBL", facturepfTmp.getNumBl());
														
														if(facturepfTmp.getDateBl()!=null)
														{
															parameters.put("dateBl",dateFormat.format( facturepfTmp.getDateBl()));
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
													parameters.put("NumBL", facturepfTmp.getNumBl());
													
													if(facturepfTmp.getDateBl()!=null)
													{
														parameters.put("dateBl",dateFormat.format( facturepfTmp.getDateBl()));
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
											
											
											
	                                     
											if(facturepfTmp.getImprimerAvecNumCommande()!=null)
											{
												if(!facturepfTmp.getImprimerAvecNumCommande().equals(""))
												{
													
													if(facturepfTmp.getImprimerAvecNumCommande().equals(Constantes.CODE_OUI))
													{
														if(facturepfTmp.getDateBCommande()!=null)
														{
															parameters.put("dateBC",dateFormat.format(facturepfTmp.getDateBCommande()) );
														}else
														{
															parameters.put("dateBC", "");
														}
														if(facturepfTmp.getNumCommande()!=null)
														{
															parameters.put("NumBC", facturepfTmp.getNumCommande());
														}else
														{
															parameters.put("NumBC", "");
														}
														
														 
													}else if(facturepfTmp.getImprimerAvecNumCommande().equals(Constantes.CODE_NON))
													
													{
														 
														 
															parameters.put("dateBC", "");
														 
														 
															parameters.put("NumBC", "");
														 
															 
														
													}
													
													 
													
												}else
												{
													
													if(chckbxImprimerNumBc.isSelected()==true)
													{
														if(facturepfTmp.getDateBCommande()!=null)
														{
															parameters.put("dateBC",dateFormat.format(facturepfTmp.getDateBCommande()) );
														}else
														{
															parameters.put("dateBC", "");
														}
														if(facturepfTmp.getNumCommande()!=null)
														{
															parameters.put("NumBC", facturepfTmp.getNumCommande());
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
												 
													if(facturepfTmp.getDateBCommande()!=null)
													{
														parameters.put("dateBC",dateFormat.format(facturepfTmp.getDateBCommande()) );
													}else
													{
														parameters.put("dateBC", "");
													}
													if(facturepfTmp.getNumCommande()!=null)
													{
														parameters.put("NumBC", facturepfTmp.getNumCommande());
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
											
											
											
											
											
											
										
											
											
											
											
											
										} 
										
										
										
										parameters.put("tva", Constantes.TVA);
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
										
										if(facturepfTmp.getAdresseClient()!=null)
										{
											if(!facturepfTmp.getAdresseClient().equals(""))
											{
												parameters.put("adresse", facturepfTmp.getAdresseClient());
											}else
											{
												parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
											}
											
										}else
										{
											parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
										}
										
										parameters.put("timber",timbertmp);
										parameters.put("netapayer",netapayerTmp);
										parameters.put("code",facturepfTmp.getClientPF().getCode());
										
										if(facturepfTmp.getClientPF().getPatente()!=null)
										{
											parameters.put("patente", facturepfTmp.getClientPF().getPatente());
										}else
										{
											parameters.put("patente", "");
										}
										
										if(facturepfTmp.getClientPF().getIce()!=null)
										{
											parameters.put("iceclient",facturepfTmp.getClientPF().getIce());
										}else
										{
											parameters.put("iceclient","");
										}
										
										String datechance="";
										if(facturepfTmp.getClientPF().getDelaiPaiement()!=null)
										{
											if(facturepfTmp.getClientPF().getDelaiPaiement().compareTo(BigDecimal.ZERO)!=0)
											{
												
												if(facturepfTmp.getClientPF().getDelaiPaiementParBlOuFacture()!=null)
												{
													
													if(!facturepfTmp.getClientPF().getDelaiPaiementParBlOuFacture().equals(""))
													{
														if(facturepfTmp.getClientPF().getDelaiPaiementParBlOuFacture().equals(Constantes.TYPE_BON_LIVRAISON))
														{
															parameters.put("delai","Delai de paiement :  "+ facturepfTmp.getClientPF().getDelaiPaiement().intValue()+" jours date de bon livraison");
															
															datechance=dateFormat.format(DateUtils.ajoutNbJours(facturepfTmp.getDateBl(), facturepfTmp.getClientPF().getDelaiPaiement().intValue()));
															
															 
															
															parameters.put("dateechance","Date d'échance     :  "+datechance);
														
														}else if(facturepfTmp.getClientPF().getDelaiPaiementParBlOuFacture().equals(TRANSFERE_BL_FACTURE))
														{
															
															parameters.put("delai","Delai de paiement :  "+ facturepfTmp.getClientPF().getDelaiPaiement().intValue()+" jours date de facture");
														
	                                                        datechance=dateFormat.format(DateUtils.ajoutNbJours(facturepfTmp.getDateFacture(), facturepfTmp.getClientPF().getDelaiPaiement().intValue()));
															
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
										
										
										
										/*if(checkmodepaiement.isSelected()==true)
										{*/
											if( facturepfTmp.getModeReglement()!=null)
											{
												String numpiece ="";
												String ModePaiement ="";
												boolean espece , cheque = false ;
												boolean virement , traite  =false;
												boolean  versement =false;
												boolean  credit =false;
												if(facturepfTmp.getEspece()!=null)
												{
													if(facturepfTmp.getEspece().compareTo(BigDecimal.ZERO)>0)
													{
														if(ModePaiement!="")
														{
															ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_ESPECE;
														}else
														{
															ModePaiement=Constantes.MODE_REGLEMENT_ESPECE;
														}
														
														espece=true;
													}	
												}
												if(facturepfTmp.getCheque()!=null)
												{
													
													if(facturepfTmp.getCheque().compareTo(BigDecimal.ZERO)>0)
													{
														if(ModePaiement!="")
														{
															ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_CHEQUE;
															
														}else
														{
															ModePaiement=Constantes.MODE_REGLEMENT_CHEQUE;
														}
														
														cheque=true;
														if(facturepfTmp.getNumCheque()!=null)
														{
															
															if(!facturepfTmp.getNumCheque().equals(""))
															{
																
																numpiece=numpiece+	"  Cheque N° : "+ facturepfTmp.getNumCheque();
																
															}
															
															
															
														}
													
														
													}	
													
													
												}
												
												if(facturepfTmp.getVirement()!=null)
												{
													
													if(facturepfTmp.getVirement().compareTo(BigDecimal.ZERO)>0)
													{
														if(ModePaiement!="")
														{
															
															ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_VIREMENT;
														}else
														{
															ModePaiement=Constantes.MODE_REGLEMENT_VIREMENT;
														}
														
														virement=true;
													}	
													
													
												}
												if(facturepfTmp.getTraite()!=null)
												{
													
													if(facturepfTmp.getTraite().compareTo(BigDecimal.ZERO)>0)
													{
														if(ModePaiement!="")
														{
															ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_TRAITE;
															
														}else
														{
															
															ModePaiement=Constantes.MODE_REGLEMENT_TRAITE;
														}
														
														traite=true;
														
														if(facturepfTmp.getNumtraite()!=null)
														{
															
															if(!facturepfTmp.getNumtraite().equals(""))
															{
																
																numpiece=numpiece+" Traite N° : "+ facturepfTmp.getNumtraite();
																
															}
																
															
														}
														
														
														
													}	
													
													
												}
												
												
												if(facturepfTmp.getVersement()!=null)
												{
													
													if(facturepfTmp.getVersement().compareTo(BigDecimal.ZERO)>0)
													{
														if(ModePaiement!="")
														{
															ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_VERSEMENT;
															
														}else
														{
															ModePaiement=Constantes.MODE_REGLEMENT_VERSEMENT;
														}
														
														versement=true;
														
														if(facturepfTmp.getNumVersement()!=null)
														{
															
															if(!facturepfTmp.getNumVersement().equals(""))
															{
																
																
																numpiece=numpiece+	" Versement N° : "+ facturepfTmp.getNumVersement();
																
															}
															
															
															
														}
													
														
														
													}	
													
													
												}
												
												
												if(facturepfTmp.getCredit()!=null)
												{
													
													if(facturepfTmp.getCredit().compareTo(BigDecimal.ZERO)>0)
													{
														if(ModePaiement!="")
														{
															ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_CREDIT;
															
														}else
														{
															ModePaiement=Constantes.MODE_REGLEMENT_CREDIT;
														}
														
														credit=true;
													}	
													
													
												}
												
												
												
												
												if(facturepfTmp.getEspece()==null && facturepfTmp.getCheque()==null && facturepfTmp.getVirement()==null && facturepfTmp.getTraite()==null && facturepfTmp.getVersement()==null && facturepfTmp.getCredit()==null)
												{
													
													
													ModePaiement=facturepfTmp.getModeReglement();
													
													
												}
												
												if(checkSansmodepaiement.isSelected()==true)
												{
													ModePaiement="";
													 
												}
												
												if(checkSansNummodepaiement.isSelected()==true)
												{
													 
													numpiece="";
												}
												
												
												parameters.put("modepaiement", ModePaiement);
												
												
												
												if(!numpiece.equals(""))
												{
													
													parameters.put("numcheque", numpiece);
													
									
												}else
												{
													
												
														  parameters.put("numcheque", "");
														  
														  
													
													
												}
												
												
											
											}else
											{
												parameters.put("modepaiement", "");
												parameters.put("numcheque", "");
											}
									
										
										/*}else
										{
										parameters.put("modepaiement", "");
										}*/
										
										//double totalttc=Double.valueOf(txtnetapayer.getText());
										String x=txtnetapayer.getText().replace(".", ",");
										
										sommetowords= ConverterNumberToWords.converter(x);
									
										parameters.put("NumberToWords",sommetowords);
										
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
										
										
										
										
										
										
										 
											
											if(listDetailFacturePFImprimerTmp.size()!=0)
											{
												 if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
												 {

														InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVente.jasper");
														
														try {
															
														//	JasperDesign jasperDesign = JRXmlLoader.load(str);
														//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
															
															JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePFImprimerTmp));
															//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
															jasperPrintList.add(JASPER_PRINT);
															
															
														} catch (JRException ex) {
															// TODO Auto-generated catch blockal
															ex.printStackTrace();
														}
													 
												 }else
												 {
													 
														InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVenteSansTVA.jasper");
														
														try {
															
														//	JasperDesign jasperDesign = JRXmlLoader.load(str);
														//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
															
															JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePFImprimerTmp));
															//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");

															jasperPrintList.add(JASPER_PRINT);
															
															
															
														} catch (JRException ex) {
															// TODO Auto-generated catch blockal
															ex.printStackTrace();
														}
													 
													 
												 }
												
												
											}else
											{
												 if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
												 {
														InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVente.jasper");
														
														try {
															
														//	JasperDesign jasperDesign = JRXmlLoader.load(str);
														//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
															
															JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePFImprimer));
															//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
															jasperPrintList.add(JASPER_PRINT);
															
															
														} catch (JRException ex) {
															// TODO Auto-generated catch blockal
															ex.printStackTrace();
														}
													 
													 
												 }else
												 {
													 
														InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVenteSansTVA.jasper");
														
														try {
															
														//	JasperDesign jasperDesign = JRXmlLoader.load(str);
														//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
															
															JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePFImprimer));
															//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
															jasperPrintList.add(JASPER_PRINT);
															
															
														} catch (JRException ex) {
															// TODO Auto-generated catch blockal
															ex.printStackTrace();
														}
													 
													 
													  
												 }
												
												
											}
											
											

										
											
											
											
											
									 
									
										
								 }else
								 {
									 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
								 }
									
								
							}
							
							
						}
						
						
					}
		    		
////////////////////////////////////////////////////////////////////////////////  Facture Avoir Client PF  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		    		
 
					
					for(int k=0;k<listFactureAvoirClientPF.size();k++)
					{
						FactureAvoirClientPF facturepfTmp=listFactureAvoirClientPF.get(k);
						
					
							
							///////////////////////////////////////////////
							
							if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) )
							{
								
								detailFactureAvoirClientPFdao.ViderSession();
								String sommetowords="";

								List<DetailFactureAvoirClientPF> listDetailFacturePFImprimer =new ArrayList<DetailFactureAvoirClientPF>();
								List<DetailFactureAvoirClientPF> listDetailFacturePFImprimerTmp =new ArrayList<DetailFactureAvoirClientPF>();
								   List<DetailFactureAvoirClientPF> listDetailFacturePFTmp =new ArrayList<DetailFactureAvoirClientPF>();
								 listFactureAvoirClientPFTmp.clear();
								 if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
								 {
									 listDetailFacturePFTmp=detailFactureAvoirClientPFdao.listeDetailFactureAvoirByFacture(facturepfTmp.getId());
									 
								 }else
								 {
									 listFactureAvoirClientPFTmp=factureAvoirClientpfdao.findByNumFactureByDepot(facturepfTmp.getNumFacture(), facturepfTmp.getDepot());
									 listDetailFacturePFTmp=detailFactureAvoirClientPFdao.listeDetailFactureAvoirClientPFByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getMagasin());
									 
								 }
								    BigDecimal montanttotal=BigDecimal.ZERO;
							        BigDecimal montanttotalHT=BigDecimal.ZERO;
							        BigDecimal montanttotalTVA=BigDecimal.ZERO;
							        BigDecimal netapayer=BigDecimal.ZERO;
							       
												boolean trouve=false;
												
												 if(listDetailFacturePFTmp.size()!=0)
												 {
													 
													 for(int i=0;i<listDetailFacturePFTmp.size();i++)
													 {
														 trouve=false;
														 
														 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
														 {
															if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePFTmp.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePFTmp.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
															{
																trouve=true;
																
																listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
																listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
																listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

																listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
																listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
																listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));			
																listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
																
															}
															
															
														 }
														 if(trouve==false)
														 {
															 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
														 }
														  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
												          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
												          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
														 
													 }
													 
													 if(listDetailFacturePFImprimer.size()!=0)
													 {
														 
														    montanttotal=BigDecimal.ZERO;
													         montanttotalHT=BigDecimal.ZERO;
													         montanttotalTVA=BigDecimal.ZERO;
														 
														 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
														 {
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
													 
												
													
														
														
														
														 //txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
														
													   //txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
												  		//	txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
												  			
												  			
											  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
											  		  	
													 String dateFacture="";
											  		   
													 
													 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
													/*
													 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
													 * 
													 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
													 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
													 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
													 */
											  		 
														Map parameters = new HashMap();
														parameters.put("dateFacture", dateFacture);
														String totalht=String.valueOf(montanttotalHT.setScale(2, RoundingMode.HALF_UP));
														String totaltva=String.valueOf(montanttotalTVA.setScale(2, RoundingMode.HALF_UP));
														String totalttc=String.valueOf(montanttotal.setScale(2, RoundingMode.HALF_UP));
														 
														
														
														parameters.put("TotalHT", totalht);
														parameters.put("TotalTVA", totaltva);
														parameters.put("TotalTTC",totalttc);
														parameters.put("client", facturepfTmp.getClientPF().getNom());
														
														
														
														
														if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepfTmp.getType().equals(Constantes.TYPE_FACTURE))
														{
															parameters.put("NumFacture", facturepfTmp.getNumFacture());
															parameters.put("type", "Facture Avoir N°    :");
															
														}else if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
														{
															parameters.put("NumFacture", facturepfTmp.getNumBL());
															parameters.put("type", "BL N°    :");
														}
														
														
														
														parameters.put("tva", Constantes.TVA);
														if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
														{
															
															parameters.put("ice", Constantes.ICE_ETP);
														}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
														{
															
															
															parameters.put("ice", Constantes.ICE_AHLBRAHIM);
														}
														
														parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
														
														parameters.put("code",facturepfTmp.getClientPF().getCode());
														
														if(facturepfTmp.getClientPF().getIce()!=null)
														{
															parameters.put("iceclient",facturepfTmp.getClientPF().getIce());
														}else
														{
															parameters.put("iceclient","");
														}
														
												
																 
														
														/*}else
														{
														parameters.put("modepaiement", "");
														}*/
														
														//double totalttc=Double.valueOf(txtnetapayer.getText());
														String x=totalttc.replace(".", ",");
														
														sommetowords= ConverterNumberToWords.converter(x);
													
														parameters.put("NumberToWords",sommetowords);
														
														
														 
															
															if(listDetailFacturePFImprimerTmp.size()!=0)
															{
																
																
																InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureAvoirClientPF.jasper");
																
																try {
																	
																//	JasperDesign jasperDesign = JRXmlLoader.load(str);
																//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
																	
																	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePFImprimerTmp));
																	//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
																	jasperPrintList.add(JASPER_PRINT);
																 
																	
																} catch (JRException ex) {
																	// TODO Auto-generated catch blockal
																	ex.printStackTrace();
																}
																 
																
															}else
															{
																InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureAvoirClientPF.jasper");
																
																try {
																	
																//	JasperDesign jasperDesign = JRXmlLoader.load(str);
																//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
																	
																	JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePFImprimer));
																	//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
																	jasperPrintList.add(JASPER_PRINT);
																	
																	
																} catch (JRException ex) {
																	// TODO Auto-generated catch blockal
																	ex.printStackTrace();
																}
																
																 
															}
															
													 
													
														
												 }else
												 {
													// JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
												 }
													
												
								
								
								
							}
							
		
						
						
						
					}
					
					
					
		    		
		    		
		    		
		    		
////////////////////////////////////////////////////////////////////////////////Facture Service Transport  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		    		
		    		

					
					 
					
					
					for(int k=0;k<listFactureServiceTransport.size();k++)
					{
						FactureServiceTransport facturepfTmp=listFactureServiceTransport.get(k);
						
					 
							
			detailFactureServiceTransportdao.ViderSession();
			String sommetowords="";

		
			   List<DetailFactureServiceTransport> listDetailFactureServiceTransportTmp =new ArrayList<DetailFactureServiceTransport>();
			 listFactureServiceTransportTmp.clear();
			 if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
			 {
				 listDetailFactureServiceTransportTmp=detailFactureServiceTransportdao.listeDetailFactureServiceTransportByFacture(facturepfTmp.getId());
				 
			 }else
			 {
				 listFactureServiceTransportTmp=factureServiceTransportDAO.findByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getDepot());
				 listDetailFactureServiceTransportTmp=detailFactureServiceTransportdao.listeDetailFactureServiceTransportByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getMagasin());
				 
			 }
			    BigDecimal montanttotal=BigDecimal.ZERO;
		        BigDecimal montanttotalHT=BigDecimal.ZERO;
		        BigDecimal montanttotalTVA=BigDecimal.ZERO;
		        BigDecimal netapayer=BigDecimal.ZERO;
		        BigDecimal timber=BigDecimal.ZERO;
		        BigDecimal timberversement=BigDecimal.ZERO;
		        BigDecimal timberespece=BigDecimal.ZERO;
							boolean trouve=false;
							
							 if(listDetailFactureServiceTransportTmp.size()!=0)
							 {
								 
								 for(int i=0;i<listDetailFactureServiceTransportTmp.size();i++)
								 {
									 
									  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFactureServiceTransportTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
							          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFactureServiceTransportTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
							          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFactureServiceTransportTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
									 
								 }
								 
							
								 
							
								 for(int d=0;d<listFactureServiceTransportTmp.size();d++)
								 {
									 facturepfTmp=listFactureServiceTransportTmp.get(d);
									 
									 if(facturepfTmp.getModeReglement()!=null)
							  			{
							  				
							  					
							  					
							  					if(facturepfTmp.getEspece()!=null)
												{
													
													timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
													timberespece=timberespece.add(timber.multiply(facturepfTmp.getEspece().setScale(6, RoundingMode.HALF_UP))) ;
													
													
												}else
								  			{
								  				timberespece=timberespece.add(BigDecimal.ZERO);
								  			}
							  				
							  			}else
							  			{
							  				timberespece=timberespece.add(BigDecimal.ZERO);
							  				timberversement=timberversement.add(BigDecimal.ZERO);
							  			}
										
										if(facturepfTmp.getModeReglement()!=null)
							  			{
							  				
							  					if(facturepfTmp.getVersement()!=null)
												{
													
													timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
													timberversement=timberversement.add(timber.multiply(facturepfTmp.getVersement().setScale(6, RoundingMode.HALF_UP))) ;
													
													
												} 
								  			else
								  			{
								  				timberversement=timberversement.add(BigDecimal.ZERO);
								  			}
							  				
							  			}else
							  			{
							  				timberespece=timberespece.add(BigDecimal.ZERO);
							  				timberversement=timberversement.add(BigDecimal.ZERO);
							  			}
										
									
									
									 
								 }
									
									
									
									// txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
									netapayer=timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP).add(montanttotal.setScale(2, RoundingMode.HALF_UP));
								  // txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
							  			//txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
							  			//txttimbre.setText(timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP)+"");
							  			//txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
							  			
						  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	
								 String dateFacture="";
						  		   
								 
								 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
								/*
								 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
								 * 
								 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
								 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
								 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
								 */
						  		 
									Map parameters = new HashMap();
									parameters.put("dateFacture", dateFacture);
									String totalht=String.valueOf(montanttotalHT.setScale(2, RoundingMode.HALF_UP));
									String totaltva=String.valueOf(montanttotalTVA.setScale(2, RoundingMode.HALF_UP));
									String totalttc=String.valueOf(montanttotal.setScale(2, RoundingMode.HALF_UP));
									String timbertmp=String.valueOf(timberversement.add(timberespece).setScale(2, RoundingMode.HALF_UP));
									String netapayerTmp=String.valueOf(netapayer.setScale(2, RoundingMode.HALF_UP));
									
									parameters.put("TotalHT", totalht);
									parameters.put("TotalTVA", totaltva);
									parameters.put("TotalTTC",totalttc);
									parameters.put("client", facturepfTmp.getClientPF().getNom());
									
									if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepfTmp.getType().equals(Constantes.TYPE_FACTURE))
									{
										parameters.put("NumFacture", facturepfTmp.getNumFacture());
										parameters.put("type", "Facture N°    :");
										
									}else if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
									{
										parameters.put("NumFacture", facturepfTmp.getNumBl());
										parameters.put("type", "BL N°    :");
									}
									
									
									
									parameters.put("tva", Constantes.TVA_TRANSPORT);
									if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
									{
										
										parameters.put("ice", Constantes.ICE_ETP);
									}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
									{
										
										
										parameters.put("ice", Constantes.ICE_AHLBRAHIM);
									}
									
									if(facturepfTmp.getClientPF().getPatente()!=null)
									{
										parameters.put("patente", facturepfTmp.getClientPF().getPatente());
									}else
									{
										parameters.put("patente", "");
									}
								 
									
									parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
									parameters.put("timber",timbertmp);
									parameters.put("netapayer",netapayerTmp);
									parameters.put("code",facturepfTmp.getClientPF().getCode());
									
									if(facturepfTmp.getClientPF().getIce()!=null)
									{
										parameters.put("iceclient",facturepfTmp.getClientPF().getIce());
									}else
									{
										parameters.put("iceclient","");
									}
									
									parameters.put("delai","");
									parameters.put("dateechance","");
									
									/*if(checkmodepaiement.isSelected()==true)
									{*/
										if( facturepfTmp.getModeReglement()!=null)
										{
											String numpiece ="";
											String ModePaiement ="";
											boolean espece , cheque = false ;
											boolean virement , traite  =false;
											boolean  versement =false;
											boolean  credit =false;
											if(facturepfTmp.getEspece()!=null)
											{
												if(facturepfTmp.getEspece().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_ESPECE;
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_ESPECE;
													}
													
													espece=true;
												}	
											}
											if(facturepfTmp.getCheque()!=null)
											{
												
												if(facturepfTmp.getCheque().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_CHEQUE;
														
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_CHEQUE;
													}
													
													cheque=true;
													if(facturepfTmp.getNumCheque()!=null)
													{
														
														if(!facturepfTmp.getNumCheque().equals(""))
														{
															
															numpiece=numpiece+	"  Cheque N° : "+ facturepfTmp.getNumCheque();
															
														}
														
														
														
													}
												
													
												}	
												
												
											}
											
											if(facturepfTmp.getVirement()!=null)
											{
												
												if(facturepfTmp.getVirement().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_VIREMENT;
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_VIREMENT;
													}
													
													virement=true;
												}	
												
												
											}
											if(facturepfTmp.getTraite()!=null)
											{
												
												if(facturepfTmp.getTraite().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_TRAITE;
														
													}else
													{
														
														ModePaiement=Constantes.MODE_REGLEMENT_TRAITE;
													}
													
													traite=true;
													
													if(facturepfTmp.getNumtraite()!=null)
													{
														
														if(!facturepfTmp.getNumtraite().equals(""))
														{
															
															numpiece=numpiece+" Traite N° : "+ facturepfTmp.getNumtraite();
															
														}
															
														
													}
													
													
													
												}	
												
												
											}
											
											
											if(facturepfTmp.getVersement()!=null)
											{
												
												if(facturepfTmp.getVersement().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_VERSEMENT;
														
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_VERSEMENT;
													}
													
													versement=true;
													
													if(facturepfTmp.getNumVersement()!=null)
													{
														
														if(!facturepfTmp.getNumVersement().equals(""))
														{
															
															
															numpiece=numpiece+	" Versement N° : "+ facturepfTmp.getNumVersement();
															
														}
														
														
														
													}
												
													
													
												}	
												
												
											}
											
											
											if(facturepfTmp.getCredit()!=null)
											{
												
												if(facturepfTmp.getCredit().compareTo(BigDecimal.ZERO)>0)
												{
													if(ModePaiement!="")
													{
														ModePaiement=ModePaiement + " - "+Constantes.MODE_REGLEMENT_CREDIT;
														
													}else
													{
														ModePaiement=Constantes.MODE_REGLEMENT_CREDIT;
													}
													
													credit=true;
												}	
												
												
											}
											
											
											
											
											if(facturepfTmp.getEspece()==null && facturepfTmp.getCheque()==null && facturepfTmp.getVirement()==null && facturepfTmp.getTraite()==null && facturepfTmp.getVersement()==null && facturepfTmp.getCredit()==null)
											{
												
												
												ModePaiement=facturepfTmp.getModeReglement();
												
												
											}
											
											
											
											
											
										 
												parameters.put("modepaiement", ModePaiement);
												
												if(!numpiece.equals(""))
												{
													
													parameters.put("numcheque", numpiece);
													
									
												}else
												{
													
												
														  parameters.put("numcheque", "");
														  
														  
													
													
												}
											 
											
											
											
											
										
											
											
										
										}else
										{
											parameters.put("modepaiement", "");
											parameters.put("numcheque", "");
										}
										
										if(facturepfTmp.getClientPF().getPatente()!=null)
										{
											parameters.put("patente", facturepfTmp.getClientPF().getPatente());
										}else
										{
											parameters.put("patente", "");
										}
										
								
									
									/*}else
									{
									parameters.put("modepaiement", "");
									}*/
									
									//double totalttc=Double.valueOf(txtnetapayer.getText());
									String x=netapayerTmp.replace(".", ",");
									
									sommetowords= ConverterNumberToWords.converter(x);
								
									parameters.put("NumberToWords",sommetowords);
									
									
									
									
									
									
									
								 
										
										if(listDetailFactureServiceTransportTmp.size()!=0)
										{
											
											
											InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureServiceTransport.jasper");
											
											try {
												
											//	JasperDesign jasperDesign = JRXmlLoader.load(str);
											//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
												
												JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureServiceTransportTmp));
												//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
												 
												jasperPrintList.add(JASPER_PRINT);
												
											} catch (JRException ex) {
												// TODO Auto-generated catch blockal
												ex.printStackTrace();
											}
											
											
 											
											
											
											
										}
										
								 
								
									
							 }else
							 {
								// JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
							 }
								
						 
						
						
					}
					
					
				
		    		
		    		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		    		
		    		
		    		
		    		
		    		if(jasperPrintList.size()!=0)
					{
					 
						JasperPrint mergedJasperPrint = jasperPrintList.get(0);

						try {
							
							
						    for (int j=0;j<jasperPrintList.size();j++) {
						    	
						    	
						    	if(j!=0)
						    	{
						    		 for (Object page : jasperPrintList.get(j).getPages()) {
								            if (page instanceof JRPrintPage) {
								                JRPrintPage printPage = (JRPrintPage) page;
								                mergedJasperPrint.addPage(printPage);
								            }
								        }
						    		
						    	}
						       
						    }

						    // Check if the merged JasperPrint is empty
						    if (mergedJasperPrint.getPages().isEmpty()) {
						        System.out.println("Merged JasperPrint is empty.");
						    } else {
						         
						    	
						        JasperViewer.viewReport(mergedJasperPrint, false);
						    }
						} catch (Exception ex) {
						    ex.printStackTrace();
						}
						
						
					}
		    		
		    		

						
				//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  		  	
					///////////////////////////////////////////////
					
					/*
					
					detailFacturePfdao.ViderSession();
	String sommetowords="";

	listDetailFacturePF.clear();
	listDetailFacturePFImprimer.clear();
	listFacturePFTmp.clear();
	listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
	 listDetailFacturePF=new ArrayList<DetailFacturePF>();
	 listFacturePFTmp=new ArrayList<FacturePF>();
	 FacturePF facturepfTmp=listFacturePF.get(table.getSelectedRow());
	 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(),facturepfTmp.getDepot());
		for(int k=0;k<listFacturePFTmp.size();k++)
		{
			listDetailFacturePF.addAll(listFacturePFTmp.get(k).getDetailFacturePF());
		}

					boolean trouve=false;
					 if(listDetailFacturePF.size()!=0)
					 {
						 
						 for(int i=0;i<listDetailFacturePF.size();i++)
						 {
							 trouve=false;
							 
							 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
							 {
								if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2))) 
								{
									trouve=true;
									
									listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
									listDetailFacturePFImprimer.get(j).setPrixUnitaire((listDetailFacturePFImprimer.get(j).getPrixUnitaire().add(listDetailFacturePF.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
									listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
									listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
									listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
									listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
									listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
									
								}
								
								 
							 }
							 if(trouve==false)
							 {
								 listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
							 }
							 
							 
						 }
						 
						 
				  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
				  		    FacturePF facturepf=listFacturePF.get(table.getSelectedRow());
							
							Map parameters = new HashMap();
							parameters.put("dateFacture", dateFacture);
							String[]totalht=String.format("%,f", new BigDecimal(txttotalmontantHT.getText())).split(",");
							String[]totaltva=String.format("%,f",new BigDecimal(txttotalmontantTVA.getText())).split(",");
							String[]totalttc=String.format("%,f",new BigDecimal(txttotalmontantTTC.getText())).split(",");
							String[]timber=String.format("%,f",new BigDecimal(txttimbre.getText())).split(",");
							String[]netapayer=String.format("%,f",new BigDecimal(txtnetapayer.getText())).split(",");
							
							parameters.put("TotalHT", totalht[0]+","+ totalht[1].substring(0, 2));
							parameters.put("TotalTVA", totaltva[0]+","+ totaltva[1].substring(0, 2));
							parameters.put("TotalTTC",totalttc[0]+","+ totalttc[1].substring(0, 2));
							parameters.put("client", facturepf.getClientPF().getNom());
							
							if(facturepf.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepf.getType().equals(Constantes.TYPE_FACTURE))
							{
								parameters.put("NumFacture", facturepf.getNumFacture());
								parameters.put("type", "Facture N°    :");
								
							}else if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
							{
								parameters.put("NumFacture", facturepf.getNumBl());
								parameters.put("type", "BL N°    :");
							}
							
							
							parameters.put("tva", Constantes.TVA);
							if(utilisateur.getId()==1)
							{
								parameters.put("ice", Constantes.ICE_ETP);
							}else if (utilisateur.getId()==2)
							{
								parameters.put("ice", Constantes.ICE_AHLBRAHIM);
							}
							
							parameters.put("adresse", facturepf.getClientPF().getAdresse());
							parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
							parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
							parameters.put("code",facturepf.getClientPF().getCode());
							
							if(facturepf.getClientPF().getIce()!=null)
							{
								parameters.put("iceclient",facturepf.getClientPF().getIce());
							}else
							{
								parameters.put("iceclient","");
							}
							
							
							if(checkmodepaiement.isSelected()==true)
							{
								if( facturepf.getModeReglement()!=null)
								{
									parameters.put("modepaiement", facturepf.getModeReglement());
								}else
								{
									parameters.put("modepaiement", "");
								}
						
							
							}else
							{
							parameters.put("modepaiement", "");
							}
							
							//double totalttc=Double.valueOf(txtnetapayer.getText());
							String x=txtnetapayer.getText().replace(".", ",");
							
							sommetowords= ConverterNumberToWords.converter(x);
						
							parameters.put("NumberToWords",sommetowords);
							
							
							
							JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters);
						
							
					 }else
					 {
						 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 }
						
						
				//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  		  	
					
				*/
		    		
		    		
		    	}
		    });
		    button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		    button_1.setBounds(201, 812, 153, 32);
		    add(button_1);
		
		
		}
	
	boolean remplirMapImprimer(){
		boolean trouve=false;
		int i=0;
				
		for(int j=0;j<table.getRowCount();j++){
			
			boolean imprimer=(boolean) table.getValueAt(j, 9);
			if(imprimer==true ){
				FacturePF facturePF=listFacturePF.get(j);
				
				mapImprimer.put(facturePF.getId(), facturePF);
				i++;
				trouve=true;
			}
			
		}
		return trouve;
	}

	
	
	void initialiserFacture()
	{
		txtnumfacture.setText("");
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		comboClientpf.setSelectedIndex(-1);
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
	}


	
	
	
	
	void InitialiseTableau()
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Sous Famille", "Prix Unitaire", "Quantite", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Etat","Type", "Client", "Depot", "Magasin", "Montant TTC","Imprimer"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class ,BigDecimal.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		
	
}
	
	
	
	void afficher_tableDetailFacturePF(List<DetailFacturePF> listDetailFacture)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article", "Sous Famille","Prix Unitaire", "Quantite", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
		DetailFacturePF detailfacturepf=listDetailFacture.get(i);
			
			Object []ligne={detailfacturepf.getArticle().getLiblle(),detailfacturepf.getSousFamille().getLiblle(),detailfacturepf.getPrixUnitaire(),detailfacturepf.getQuantite(),detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

			modeleArticle.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tableFacturePF(List<FacturePF> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Etat","Type", "Client", "Depot", "Magasin", "Montant TTC","Mode Regelement","Imprimer"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,true
				};
				
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class ,BigDecimal.class,String.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		int i=0;
		BigDecimal MontantTTC=BigDecimal.ZERO;
		 String modeReglement="";
		while(i<listFacture.size())
		{	
			modeReglement="";
			
			MontantTTC=BigDecimal.ZERO;
		FacturePF facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
		if(facturepf.getNumFacture()!=null)
		{
		if(!facturepf.getNumFacture().equals(""))	
		{
			listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByNumFacture(facturepf.getNumFacture(), facturepf.getMagasin());
			for(int j=0;j<listDetailFacturePF.size();j++)
			{
				
				MontantTTC=MontantTTC.add(listDetailFacturePF.get(j).getMontantTTC());
			}
		}else
		{
			for(int j=0;j<facturepf.getDetailFacturePF().size();j++)
			{
				
				MontantTTC=MontantTTC.add(facturepf.getDetailFacturePF().get(j).getMontantTTC());
			}
		}
					
		}else
		{
			for(int j=0;j<facturepf.getDetailFacturePF().size();j++)
			{
				
				MontantTTC=MontantTTC.add(facturepf.getDetailFacturePF().get(j).getMontantTTC());
			}
		}
		
		
		
		 
		    
		
		if(facturepf.getEspece()!=null)
		{
			if(facturepf.getEspece().compareTo(BigDecimal.ZERO)!=0)
			{
				modeReglement= modeReglement+Constantes.MODE_REGLEMENT_ESPECE;
				
			}
			{
				
			}
		}
		
		if(facturepf.getCheque()!=null)
		{
			if(facturepf.getCheque().compareTo(BigDecimal.ZERO)!=0)
			{
				modeReglement= modeReglement +" "+Constantes.MODE_REGLEMENT_CHEQUE;
				
			}
			{
				
			}
		}
		
		if(facturepf.getVersement()!=null)
		{
			if(facturepf.getVersement().compareTo(BigDecimal.ZERO)!=0)
			{
				modeReglement= modeReglement+" "+Constantes.MODE_REGLEMENT_VERSEMENT;
				
			}
			{
				
			}
		}
	
		if(facturepf.getVirement()!=null)
		{
			if(facturepf.getVirement().compareTo(BigDecimal.ZERO)!=0)
			{
				
				modeReglement= modeReglement+" "+Constantes.MODE_REGLEMENT_VIREMENT;
			}
			{
				
			}
		}
		if(facturepf.getTraite()!=null)
		{
			if(facturepf.getTraite().compareTo(BigDecimal.ZERO)!=0)
			{
				
				modeReglement= modeReglement+" "+Constantes.MODE_REGLEMENT_TRAITE;
			}
			{
				
			}
		}
		
		if(facturepf.getCredit()!=null)
		{
			if(facturepf.getCredit().compareTo(BigDecimal.ZERO)!=0)
			{
				modeReglement= modeReglement+" "+Constantes.MODE_REGLEMENT_CREDIT;
				
			}
			{
				
			}
		}
			
			Object []ligne={facturepf.getNumFacture(),datefacture,facturepf.getEtat(),facturepf.getType(),facturepf.getClientPF().getNom(),facturepf.getDepot().getLibelle(),facturepf.getMagasin().getLibelle(),MontantTTC,modeReglement,false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	}


