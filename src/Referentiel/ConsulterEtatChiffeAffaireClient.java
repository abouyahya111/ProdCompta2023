package Referentiel;

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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import util.ConverterNumberToWords;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailFactureAvoirClientPFDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailFactureServiceTransportDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
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
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFactureAvoirClientPFDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailFactureServiceTransportDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
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
import dao.entity.ClientPF;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAvoirClientPF;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatChiffreAffaireClient;
import dao.entity.EtatInitialParSousCtaegorieMP;
import dao.entity.EtatPrixMoyen;
import dao.entity.EtatPrixMoyenMP;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.EtatValeurSystem;
import dao.entity.EtatValeurisationStockParSousFamille;
import dao.entity.EtatVenteParFamilleArticle;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FactureServiceProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Parametre;
import dao.entity.PrixMoyenStockMP;
import dao.entity.PrixMoyenStockPF;
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
import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class ConsulterEtatChiffeAffaireClient extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
 
	
 
	
	
	
 
	
	//***************************************************

	 
	
	
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 JComboBox combomp = new JComboBox();
	 
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
 
	private JTextField txtlibelle=new JTextField();
	  JComboBox comboMagasinMP = new JComboBox();
	
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();
 
	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	 
	private CompteClientDAO compteclientdao;
	 JComboBox comboService = new JComboBox();
	 
	String titre="";
	 
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
	 JTextArea textArea = new JTextArea();
	 JLabel lblDateInitial = new JLabel("Date Initial :");
	 JButton btnTransfert = new JButton("Transfert");
	 JDateChooser dateinitiale = new JDateChooser();
	 
	 JComboBox comboMagasinPF = new JComboBox();
	 
		private List<EtatChiffreAffaireClient> listEtatChiffreAffaireClient =new ArrayList<EtatChiffreAffaireClient>();
	 
			private List <Object[]> listeObjectChiffreAffaireClientAvecICE=new ArrayList<Object[]>();
			private List <Object[]> listeObjectChiffreAffaireClientSansICE=new ArrayList<Object[]>();
			private  DetailFacturePFDAO detailfacturedao;
			private DetailFactureServiceProductionDAO detailFactureServiceProductionDAO;
		 private DetailFactureServiceTransportDAO detailFactureServiceTransportDAO;
		 
		 
 
		

 

/**

	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatChiffeAffaireClient() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(Color.RED);

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 1111);
      
	
        try{ 
        	
        	
        	 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	detailFactureServiceProductionDAO=new DetailFactureServiceProductionDAOImpl();
         	detailFactureServiceTransportDAO=new DetailFactureServiceTransportDAOImpl();
      
      	  detailfacturedao=new DetailFacturePFDAOImpl();
      	  
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"CLIENT","ICE","MONTANT HT","MONTANT TTC"
       	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(160);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(160);
       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(160);
       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(160);
        
       
       tableEtatStock.setShowVerticalLines(false);
       tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
       tableEtatStock.setRowHeightEnabled(true);
       tableEtatStock.setBackground(new Color(255, 255, 255));
       tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableEtatStock.setColumnControlVisible(true);
       tableEtatStock.setForeground(Color.BLACK);
       tableEtatStock.setGridColor(new Color(0, 0, 255));
       tableEtatStock.setAutoCreateRowSorter(true);
       tableEtatStock.setBounds(2, 27, 411, 198);
       tableEtatStock.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableEtatStock);
				  		     	scrollPane.setBounds(10, 195, 1465, 504);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("ETAT CHIFFRE AFFAIRE CLIENT");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           ETAT CHIFFRE AFFAIRE CLIENT");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 1080, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		listEtatChiffreAffaireClient.clear();
	    		
 	    		
	    		 
	    		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		
	    	   
		    		Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
		    	
		    		String requete="";
		    		if(magasin!=null)
		    		{
		    			
		    		if(comboService.getSelectedItem().equals(""))	
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Service SVP !!!!");
		    			return;
		    		}
		    			
		    			
		    			
		    			
		    			  
		    			
		    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
			    		{
		    				
			    		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
			    		{
			    			
			    			
			    			if(comboService.getSelectedItem().equals(Constantes.SERVICE_VENTE))
			    			{
			        			requete=" where c.facturePF.magasin.id='"+magasin.getId()+"' ";
				    			
				    			String dateDu=formatter.format(dateChooserdebut.getDate());
				    			String dateAu=formatter.format(dateChooserfin.getDate());
				    			
				    			
				    			requete=requete+"and  c.facturePF.dateFacture between '"+dateDu +"' and '"+dateAu+"' ";
				    			

				    			listeObjectChiffreAffaireClientAvecICE=detailfacturedao.listeEtatChiffreAffaireClientAvecICE(requete);
				    			
				    			for(int i=0;i<listeObjectChiffreAffaireClientAvecICE.size();i++)
				    			{
				    				
				    				 Object[] object=listeObjectChiffreAffaireClientAvecICE.get(i);
				    				
				    				if(object[0]!=null)
				    				{
				    					
				    				EtatChiffreAffaireClient etatChiffreAffaireClient=new EtatChiffreAffaireClient();	
				    					
				    				etatChiffreAffaireClient.setClient(object[0].toString());
				    				etatChiffreAffaireClient.setIce(object[1].toString());
				    				etatChiffreAffaireClient.setMontantHT(new BigDecimal(object[2].toString()));
				    				etatChiffreAffaireClient.setMontantTTC(new BigDecimal(object[3].toString()));	
				    				listEtatChiffreAffaireClient.add(etatChiffreAffaireClient);
				    				}
				    			}
				    			
				    			
				    			
				    			listeObjectChiffreAffaireClientSansICE=detailfacturedao.listeEtatChiffreAffaireClientSansICE(requete);
					  	     
				    			for(int i=0;i<listeObjectChiffreAffaireClientSansICE.size();i++)
				    			{
				    				
				    				 Object[] object=listeObjectChiffreAffaireClientSansICE.get(i);
				    				
				    				if(object[0]!=null)
				    				{
				    					
				    				EtatChiffreAffaireClient etatChiffreAffaireClient=new EtatChiffreAffaireClient();	
				    					
				    				etatChiffreAffaireClient.setClient(AUTRE_CLIENT);
				    				etatChiffreAffaireClient.setIce("");
				    				etatChiffreAffaireClient.setMontantHT(new BigDecimal(object[0].toString()));
				    				etatChiffreAffaireClient.setMontantTTC(new BigDecimal(object[1].toString()));	
				    				listEtatChiffreAffaireClient.add(etatChiffreAffaireClient);
				    				}
				    			} 
					  			 
					  			  
					  		  
			    			}else if(comboService.getSelectedItem().equals(Constantes.SERVICE_PRODUCTION_TRANSPORT))
			    			{
			    				
			    				
/////////////////////////////////////////////////////////////////////////////////////////////////////////  Production  Avec ICE  //////////////////////////////////////////////////////////////////////////////////////////////////////			    				

			        			requete=" where c.factureService.depot.id='"+magasin.getDepot().getId()+"' ";
				    			
				    			String dateDu=formatter.format(dateChooserdebut.getDate());
				    			String dateAu=formatter.format(dateChooserfin.getDate());
				    			
				    			
				    			requete=requete+"and  c.factureService.dateFacture between '"+dateDu +"' and '"+dateAu+"' ";
				    			

				    			
				    			
				    			
				    			listeObjectChiffreAffaireClientAvecICE=detailFactureServiceProductionDAO.listeEtatChiffreAffaireClientAvecICEProductionService (requete);
				    			
				    			
				    			
				    			for(int i=0;i<listeObjectChiffreAffaireClientAvecICE.size();i++)
				    			{
				    				
				    				 Object[] object=listeObjectChiffreAffaireClientAvecICE.get(i);
				    				
				    				if(object[0]!=null)
				    				{
				    					
				    				EtatChiffreAffaireClient etatChiffreAffaireClient=new EtatChiffreAffaireClient();	
				    					
				    				etatChiffreAffaireClient.setClient(object[0].toString());
				    				etatChiffreAffaireClient.setIce(object[1].toString());
				    				etatChiffreAffaireClient.setMontantHT(new BigDecimal(object[2].toString()));
				    				etatChiffreAffaireClient.setMontantTTC(new BigDecimal(object[3].toString()));	
				    				listEtatChiffreAffaireClient.add(etatChiffreAffaireClient);
				    				}
				    			}
				    			
	/////////////////////////////////////////////////////////////////////////     Transport  Avec ICE ///////////////////////////////////////////////////////////////////////////////////			    			
				    			
                              requete=" where c.factureServiceTransport.magasin.id='"+magasin.getId()+"' ";
				    			
				    			 
				    			
				    			
				    			requete=requete+"and  c.factureServiceTransport.dateFacture between '"+dateDu +"' and '"+dateAu+"' ";
				    			 
				    			
				    			listeObjectChiffreAffaireClientAvecICE=detailFactureServiceTransportDAO.listeEtatChiffreAffaireClientAvecICETransportService(requete);
				    			boolean existe=false;
				    			for(int i=0;i<listeObjectChiffreAffaireClientAvecICE.size();i++)
				    			{
				    				
				    				
				    				 Object[] object=listeObjectChiffreAffaireClientAvecICE.get(i);
				    				
				    				if(object[0]!=null)
				    				{
				    					existe=false;
				    					
				    					for(int t=0;t<listEtatChiffreAffaireClient.size();t++)
				    					{
				    						EtatChiffreAffaireClient etatChiffreAffaireClient =listEtatChiffreAffaireClient.get(t);
				    						if(etatChiffreAffaireClient.getIce().equals(object[1].toString())  )
				    						{
				    							existe=true;
				    							etatChiffreAffaireClient.setMontantHT(etatChiffreAffaireClient.getMontantHT().add(new BigDecimal(object[2].toString())));
				    							etatChiffreAffaireClient.setMontantTTC(etatChiffreAffaireClient.getMontantTTC().add(new BigDecimal(object[3].toString())));
				    							listEtatChiffreAffaireClient.set(t, etatChiffreAffaireClient);
				    							
				    						}
				    						
				    						
				    					}
				    					
				    					if(existe==false)
				    					{
				    						EtatChiffreAffaireClient etatChiffreAffaireClient=new EtatChiffreAffaireClient();	
					    					
						    				etatChiffreAffaireClient.setClient(object[0].toString());
						    				etatChiffreAffaireClient.setIce(object[1].toString());
						    				etatChiffreAffaireClient.setMontantHT(new BigDecimal(object[2].toString()));
						    				etatChiffreAffaireClient.setMontantTTC(new BigDecimal(object[3].toString()));	
						    				listEtatChiffreAffaireClient.add(etatChiffreAffaireClient);
				    						
				    					}
				    					
				    			
				    				}
				    			}
				    			
				    			
///////////////////////////////////////////////////////////////////////////////////////////////////  Production Sans ICE   ///////////////////////////////////////////////////////////////////////////////////////////////////////////				    			
				    			
					  	     
requete=" where c.factureService.depot.id='"+magasin.getDepot().getId()+"' ";
				    			
				    			 
				    			
				    			
				    			requete=requete+"and  c.factureService.dateFacture between '"+dateDu +"' and '"+dateAu+"' ";
				    			
				    			listeObjectChiffreAffaireClientSansICE=detailFactureServiceProductionDAO.listeEtatChiffreAffaireClientSansICEProductionService (requete);	
				    			
				    			for(int i=0;i<listeObjectChiffreAffaireClientSansICE.size();i++)
				    			{
				    				
				    				 Object[] object=listeObjectChiffreAffaireClientSansICE.get(i);
				    				
				    				if(object[0]!=null)
				    				{
				    					
				    				EtatChiffreAffaireClient etatChiffreAffaireClient=new EtatChiffreAffaireClient();	
				    					
				    				etatChiffreAffaireClient.setClient(AUTRE_CLIENT);
				    				etatChiffreAffaireClient.setIce("");
				    				etatChiffreAffaireClient.setMontantHT(new BigDecimal(object[0].toString()));
				    				etatChiffreAffaireClient.setMontantTTC(new BigDecimal(object[1].toString()));	
				    				listEtatChiffreAffaireClient.add(etatChiffreAffaireClient);
				    				}
				    			} 
					  			 
					  			  
///////////////////////////////////////////////////////////////////////////////////////////////////  Transport Sans ICE   ///////////////////////////////////////////////////////////////////////////////////////////////////////////				    			
				    			
						  	     
				    			 requete=" where c.factureServiceTransport.magasin.id='"+magasin.getId()+"' ";
					    			
				    			 
					    			
					    			
					    			requete=requete+"and  c.factureServiceTransport.dateFacture between '"+dateDu +"' and '"+dateAu+"' ";

listeObjectChiffreAffaireClientSansICE=detailFactureServiceTransportDAO.listeEtatChiffreAffaireClientSansICETransportService (requete);	

boolean trouver=false;

for(int i=0;i<listeObjectChiffreAffaireClientSansICE.size();i++)
{

Object[] object=listeObjectChiffreAffaireClientSansICE.get(i);

if(object[0]!=null)
{
	trouver=false;
	
for(int t=0;t<listEtatChiffreAffaireClient.size();t++)
{
	
	
	EtatChiffreAffaireClient etatChiffreAffaireClient=listEtatChiffreAffaireClient.get(t);
	if(etatChiffreAffaireClient.getClient().equals(AUTRE_CLIENT))
	{
		trouver=true;
		
		etatChiffreAffaireClient.setMontantHT(etatChiffreAffaireClient.getMontantHT().add(new BigDecimal(object[0].toString())));
		etatChiffreAffaireClient.setMontantTTC(etatChiffreAffaireClient.getMontantTTC().add(new BigDecimal(object[1].toString())));
		listEtatChiffreAffaireClient.set(t, etatChiffreAffaireClient);
		
	}
	
}
	
if(trouver==false)
{
	EtatChiffreAffaireClient etatChiffreAffaireClient=new EtatChiffreAffaireClient();	

	etatChiffreAffaireClient.setClient(AUTRE_CLIENT);
	etatChiffreAffaireClient.setIce("");
	etatChiffreAffaireClient.setMontantHT(new BigDecimal(object[0].toString()));
	etatChiffreAffaireClient.setMontantTTC(new BigDecimal(object[1].toString()));	
	listEtatChiffreAffaireClient.add(etatChiffreAffaireClient);
}




}
}					  		  
			    				
			    				
			    				
			    				
			    				
			    				
			    			}
			    			
			    			
			
			    			
			    		}else
			    		{
			    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			    			return;
			    		}
			    		
			    		}
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
		    
		    		
	    	
	  
	    		
	    		
	    	
	    	
	    	
	    	afficher_tableEtatStock(listEtatChiffreAffaireClient);
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(534, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(10, 57, 1491, 51);
	    add(layeredPane);
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(101, 11, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(274, 9, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(348, 9, 169, 26);
	    layeredPane.add(dateChooserfin);
	    try {
			  
			 
	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
	          dateChooserdebut.setDate(date);
	          dateChooserfin.setDate(new Date());
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		
	     		
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		
	     		comboMagasinMP.setSelectedItem("");
	     	
	     		comboMagasinPF.setSelectedItem("");
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 
	    		Magasin magasinPF=mapMagasin.get(comboMagasinPF.getSelectedItem());
	    		if(  magasinPF!=null )
	    		{
	    		
	    		String titre="Etat Chiffre Affaire Clients "+mapMagasin.get(comboMagasinPF.getSelectedItem()).getLibelle();
	    		String titrefeuille="Etat Chiffre Affaire Clients ";
	    	 ExporterTableVersExcel.tabletoexcelEtatChiffreAffaireClient(tableEtatStock, titre,titrefeuille); 
	    		
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin  SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	}
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setBounds(704, 710, 123, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	    
	     comboMagasinPF = new JComboBox();
	    comboMagasinPF.setBounds(637, 8, 202, 27);
	    layeredPane.add(comboMagasinPF);
	    
	    JLabel lblMagasinPf = new JLabel("Magasin PF  :");
	    lblMagasinPf.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblMagasinPf.setBounds(547, 5, 94, 26);
	    layeredPane.add(lblMagasinPf);
	    
	    JLabel lblService = new JLabel("Service  :");
	    lblService.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblService.setBounds(864, 5, 94, 26);
	    layeredPane.add(lblService);
	    
	     comboService = new JComboBox();
	    comboService.setBounds(912, 8, 244, 27);
	    layeredPane.add(comboService);
	    
	    //button_1.setVisible(false);
	    
	
	     if(utilisateur.getLogin().equals("admin"))
	 		  {
	 	    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_PF);
	 			  int k=0;
	 			 comboMagasinPF.addItem("");
	 		     	while (k<listMagasin.size())
	 		     	{
	 		     		Magasin magasin=listMagasin.get(k);
	 		     		
	 		     		
	 		     		comboMagasinPF.addItem(magasin.getLibelle());
	 				     		
	 				     		mapMagasin.put(magasin.getLibelle(), magasin);
	 				     	
	 		     		k++;
	 		     		
	 		     	}
	 		     	
	 		     	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_MP);
		 			  int j=0;
		 			 comboMagasinMP.addItem("");
		 		     	while (j<listMagasin.size())
		 		     	{
		 		     		Magasin magasin=listMagasin.get(j);
		 		     		
		 		     		
		 		     		comboMagasinMP.addItem(magasin.getLibelle());
		 				     		
		 				     		mapMagasin.put(magasin.getLibelle(), magasin);
		 				     	
		 		     		j++;
		 		     		
		 		     	}
	 		     	
	 		     	
	 		     	
	 		     	
	 		      
	 		  }else
	 		  {
	 			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	 			  if(depot!=null)
	 			  {
	 				  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_PF);
	 				  int k=0;
	 				 comboMagasinPF.addItem("");
	 			     	while (k<listMagasin.size())
	 			     	{
	 			     		Magasin magasin=listMagasin.get(k);
	 			     		
	 			     		
	 			     		comboMagasinPF.addItem(magasin.getLibelle());
	 					     		
	 					     		mapMagasin.put(magasin.getLibelle(), magasin);
	 					     	
	 			     		k++;
	 			     		
	 			     	}
	 			     	
	 			     	
	 			 	  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
	 				  int j=0;
	 				 comboMagasinMP.addItem("");
	 			     	while (j<listMagasin.size())
	 			     	{
	 			     		Magasin magasin=listMagasin.get(j);
	 			     		
	 			     		
	 			     		comboMagasinMP.addItem(magasin.getLibelle());
	 					     		
	 					     		mapMagasin.put(magasin.getLibelle(), magasin);
	 					     	
	 			     		j++;
	 			     		
	 			     	}
	 			     	
	 			     	
	 			     	
	 			     	
	 				 
	 			  }
	 		  }
	    
	    
	    comboService.addItem("");
	    comboService.addItem(Constantes.SERVICE_VENTE);
	    comboService.addItem(Constantes.SERVICE_PRODUCTION_TRANSPORT);
		}
	
	

	
	
	

	
	void InitialiseTableauDetailMouvementStock()
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Production", "Prix Production","Montant Production","Quantite Transfer Entrer", "Prix Transfer Entrer","Montant Transfer Entrer","Quantite Vente", "Prix Vente","Montant Vente", "Quantite Gratuit", "Prix Gratuit","Montant Gratuit","Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Finale", "Prix Finale","Montant Finale","Marge"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		 tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
	       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
	       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
	       tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
	       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(11).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(12).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(13).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(14).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(15).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(16).setPreferredWidth(99);
		
}
	
	
/*	void InitialiseTableauMouvementStock()
	{
		modeleMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date Mouvement", "Depot", "Magasin"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modeleMouvementStock);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		
}*/
	
	
	
	
	
	void afficher_tableEtatStock(List<EtatChiffreAffaireClient> listEtatChiffreAffaireClient)
	{
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
		dfDecimal.setDecimalFormatSymbols(symbols);
		dfDecimal.setGroupingSize(3);
		dfDecimal.setGroupingUsed(true);

		
 
 
 
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"CLIENT","ICE","MONTANT HT","MONTANT TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		int i=0;
		
		String article="";
		
		while(i<listEtatChiffreAffaireClient.size())
		{
		
			

EtatChiffreAffaireClient etatChiffreAffaireClient=listEtatChiffreAffaireClient.get(i);





			

			
			
			
			
		        Object []ligne={etatChiffreAffaireClient.getClient(),etatChiffreAffaireClient.getIce(),etatChiffreAffaireClient.getMontantHT(),etatChiffreAffaireClient.getMontantTTC()};

				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
		
		

		
}
	}


