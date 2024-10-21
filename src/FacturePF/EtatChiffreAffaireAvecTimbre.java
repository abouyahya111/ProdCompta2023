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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
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
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFacturePFParArticle;
import dao.entity.DetailFacturePFParFamille;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;












































import com.toedter.calendar.DateUtil;
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

import java.awt.SystemColor;

import javax.swing.JCheckBox;


public class EtatChiffreAffaireAvecTimbre extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	private List<DetailFacturePF> listDetailFacturePFTMP =new ArrayList<DetailFacturePF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private List <Object[]> listeObjectAvecTVA=new ArrayList<Object[]>();
	private List <Object[]> listeObjectSansTVA=new ArrayList<Object[]>();
	private List<dao.entity.EtatMontantFacturePF> listEtatMontantFacturePF=new ArrayList<dao.entity.EtatMontantFacturePF>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private List<DetailFacturePFParFamille> listDetailFactureParFamille =new ArrayList<DetailFacturePFParFamille>();
	private List<DetailFacturePFParArticle> listDetailFactureParArticle =new ArrayList<DetailFacturePFParArticle>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FacturePFDAO facturepfdao;
	private FacturePF facturePF;
private DetailFacturePFDAO detailFacturePfdao;
private ClientPFDAO clientpfdao;
private ClientDAO fournisseurdao;

private   JDateChooser audateChooser = new JDateChooser();
	private  JComboBox comboclient = new JComboBox();
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	 private JButton btnSupprimer = new JButton();
	 private  JComboBox comboFournisseur = new JComboBox();
	private JRadioButton rdbtnDateFacture;
	private JDateChooser dudateChooser;
	private StockPFDAO stockpfDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	private CompteClientDAO compteclientdao;
	String titre="";
	 JComboBox combomagasin = new JComboBox();
	 JComboBox comboarticle = new JComboBox();
	   JComboBox comboparfamille = new JComboBox();
	 private FamilleArticlesPFDAO famillearticleDAo;
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	   JCheckBox checkplusbl = new JCheckBox("Plus BL");
	   private JTextField pourcentage;
	   private ImageIcon imgExcel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatChiffreAffaireAvecTimbre() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1564, 1062);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             
             
            utilisateur=AuthentificationView.utilisateur;
            clientpfdao=new ClientPFDAOImpl();
         	facturepfdao=new FacturePFDAOImpl();
       depotdao=new DepotDAOImpl();
       detailFacturePfdao=new DetailFacturePFDAOImpl();
       famillearticleDAo=new FamilleArticlesPFDAOImpl();
       stockpfDAO=new StockPFDAOImpl();
       articleDAO=new ArticlesDAOImpl();
       transferStockPFDAO=new TransferStockPFDAOImpl();
       detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
		      
		    
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					if(magasin!=null && dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
					{
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String datedebutFacture=dateFormat.format(dudateChooser.getDate());
				  		  String dateFinFacture=dateFormat.format(audateChooser.getDate());
				  		listEtatMontantFacturePF.clear();
						if(listeObjectAvecTVA.size()!=0)
				{
							
							
							
						/*	if(comboclient.getSelectedIndex()!=-1)
							{
								if(!comboclient.getSelectedItem().equals(""))
								{
									
									
										titre="Etat du chiffre d'affaires des Articles Du : "+datedebutFacture +"  Au : "+ dateFinFacture+"  de client : "+comboclient.getSelectedItem();
									
									
								}else{
									titre="Etat du chiffre d'affaires des Articles Du : "+datedebutFacture +"  Au : "+ dateFinFacture;
								}
							}else
							{
								titre="Etat du chiffre d'affaires des Articles Du : "+datedebutFacture +"  Au : "+ dateFinFacture;
							}*/
							
					
					Map parameters = new HashMap();
					//parameters.put("titre", titre);
					parameters.put("magasin", magasin.getLibelle());
					parameters.put("datedebut", datedebutFacture);
					parameters.put("datefin", dateFinFacture);

if(table.getRowCount()!=0)
{
	JasperUtils.imprimerEtatChiffreAffaireAvecTimbre(table.getModel(),parameters);
}
					
					
					
					
					
					
					
				}else
				{
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Montant pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE);
					 return;
				}
					
					}	
					
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 599, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                    Etat Des BL/Facture");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(431, 11, 791, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1524, 427);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mode de Reglement","Montant HT","Montant TVA" , "Montant TTC","Timbre"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		
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
	    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    			if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
		    		{
	    				listeObjectAvecTVA.clear();
	    				if(checkplusbl.isSelected()==true)
	    				{
	    					
	    					listeObjectAvecTVA=detailFacturePfdao.listeEtatMontantFactureAvecTVA(dudateChooser.getDate(), audateChooser.getDate(),magasin, Constantes.TYPE_BON_LIVRAISON);
	    		    		 
	    		    		 afficher_tableMontant(listeObjectAvecTVA);
	    		    		 
	    		    		 
	    		    		 
	    		    		 
	    					
	    				}else
	    				{
	    					listeObjectAvecTVA=detailFacturePfdao.listeEtatMontantFactureAvecTVA(dudateChooser.getDate(), audateChooser.getDate(),magasin, Constantes.TYPE_FACTURE);
	    		    		 
	    		    		 afficher_tableMontant(listeObjectAvecTVA);
	    		    		 
	    		    		 
	    		    		 
	    		    		 
	    				}
		    		
		    		 
		    		 
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
	    		}
	    	//select sum(d.MONTANT_HT) as "Montant HT" , sum(d.MONTANT_TVA) as "Montant TVA" , sum(d.MONTANT_TTC) as "Montant TTC", sum(d.MONTANT_TTC)*(0.25/100) as "Montant Timbre"  from facturepf f , detail_facture_pf d where d.id_facturePF=f.id and f.DATE_FACTURE between '2018-01-01' and '2019-02-15' and f.ID_MAGASIN=7 and f.MODE_REGLEMENT="Espèces"
	    
	    	
	    	
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(680, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1524, 51);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(665, 13, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(700, 11, 166, 26);
	     layeredPane_2.add(dudateChooser);
	     dudateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     		


	     		
	     	}
	     });
	     dudateChooser.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     dudateChooser.setLocale(Locale.FRANCE);
	     dudateChooser.setDateFormatString("dd/MM/yyyy");
	     
	     JLabel lblAu = new JLabel("Au :");
	     lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblAu.setBounds(876, 13, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(911, 11, 166, 26);
	     layeredPane_2.add(audateChooser);
	     
	 
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(797, 120, 106, 23);
	     add(button);
	   
	     
	     if(utilisateur.getLogin().equals("admin"))
	 		  {

	 		  listClientPF=clientpfdao.findAll();
	 		  
	 		  	 		  
	 		  }else
	 		  {
	 			 
	 			  
	 			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	 			  if(depot!=null)
	 			  {
	 				 listClientPF=clientpfdao.findListClientByCodeDepot(depot.getCode());
	 					
	 		     	
	 			  }
	 		  }
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(459, 13, 196, 24);
	      layeredPane_2.add(combomagasin);
	      combomagasin.setSelectedIndex(-1);
	      combomagasin.addItem("");
	     
	     JLabel label_1 = new JLabel("Magasin :");
	     label_1.setBounds(406, 11, 56, 24);
	     layeredPane_2.add(label_1);
	     label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	      
	       checkplusbl = new JCheckBox("Plus BL");
	      checkplusbl.setFont(new Font("Tahoma", Font.BOLD, 12));
	      checkplusbl.setBackground(SystemColor.activeCaption);
	      checkplusbl.setBounds(1107, 14, 97, 23);
	      layeredPane_2.add(checkplusbl);
	      checkplusbl.setVisible(false);
	      JComboBox comboarticlegratuite = new JComboBox();
	      comboarticlegratuite.setSelectedIndex(-1);
	      comboarticlegratuite.setBounds(10, 683, 271, 24);
	      add(comboarticlegratuite);
	      comboarticlegratuite.setVisible(false);
	      JButton btnAjouterGratuite = new JButton("Ajouter Gratuite");
	      btnAjouterGratuite.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		
	      		Articles articles=mapArticle.get(comboarticlegratuite.getSelectedItem());
	      		
	      		if(articles!=null)
	      		{
	      			if(pourcentage.getText().equals(""))
	      			{
	      				JOptionPane.showMessageDialog(null, "Veuillez entrer pourcentage");
		      			return;
	      			}
	      		}else
	      		{
	      			JOptionPane.showMessageDialog(null, "Veuillez entrer article");
	      			return;
	      		}
	      		
	      		
	      		
	      		
	      		listFacturePF=facturepfdao.listBLGratuite(articles);

	      		//select  f.DATE_FACTURE, d.quantite , d.prix_unitaire ,f.NUM_BL from facturepf f , detail_facture_pf d where d.id_facturePF=f.id and d.id_article=156 and d.prix_unitaire<>0 and f.TYPE="BL"
	      	//	JOptionPane.showMessageDialog(null, listFacturePF.size());
      		
      		
	      		
for(int i=0 ; i<listFacturePF.size();i++)
{
	boolean trouve=false;
	FacturePF facturepf=listFacturePF.get(i);
	TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(facturepf.getCodeTransfer());
	listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturepf.getId());
	SousFamilleArticlePF sousFamilleArticlePF=null;
	BigDecimal quantite=BigDecimal.ZERO;
	for(int j=0;j<listDetailFacturePF.size();j++)
	{
		
		DetailFacturePF detailFacturePF=listDetailFacturePF.get(j);
		if(detailFacturePF.getArticle().getId()== articles.getId())
		{
			sousFamilleArticlePF=detailFacturePF.getSousFamille();
			quantite=detailFacturePF.getQuantite();
			trouve=true;
		}
		
		
	}
	if(trouve==true)
	{
		DetailFacturePF detailFacturePF=new DetailFacturePF();
		detailFacturePF.setArticle(articles);
		detailFacturePF.setFacturePF(facturepf);
		detailFacturePF.setMontantHT(BigDecimal.ZERO);
		detailFacturePF.setMontantTTC(BigDecimal.ZERO);
		detailFacturePF.setMontantTVA(BigDecimal.ZERO);
		detailFacturePF.setPrixUnitaire(BigDecimal.ZERO);
		BigDecimal valeurbig=(((new BigDecimal(pourcentage.getText())).multiply(quantite)).setScale(6, BigDecimal.ROUND_HALF_UP));
		String val=String.valueOf(valeurbig.intValue());
		detailFacturePF.setQuantite(new BigDecimal(val));
		detailFacturePF.setReduction(BigDecimal.ZERO);
		detailFacturePF.setSousFamille(sousFamilleArticlePF);
		detailFacturePF.setTva(BigDecimal.ZERO);
		detailFacturePF.setUtilisateur(utilisateur);
		detailFacturePfdao.add(detailFacturePF);
		
		DetailTransferProduitFini detailTransferProduitFini=new DetailTransferProduitFini();
		
		detailTransferProduitFini.setArticle(articles);
		detailTransferProduitFini.setDateTransfer(transferStockPF.getDateTransfer());
		detailTransferProduitFini.setPrixUnitaire(BigDecimal.ZERO);
		detailTransferProduitFini.setQuantite(new BigDecimal(val));
		detailTransferProduitFini.setSousFamille(sousFamilleArticlePF);
		detailTransferProduitFini.setTransferStockPF(transferStockPF);
		detailTransferProduitFini.setMagasinDestination(facturepf.getMagasin());
		detailTransferProduitFini.setTypeTransfer(transferStockPF.getStatut());
		detailTransferProduitFiniDAO.add(detailTransferProduitFini);
		System.out.println("Quantite : "+quantite + "  Quantite gratuit : "+(((new BigDecimal(pourcentage.getText())).multiply(quantite)).setScale(6, BigDecimal.ROUND_HALF_UP)).add(BigDecimal.ONE).setScale(0, BigDecimal.ROUND_HALF_UP));
	}
	
	
}

JOptionPane.showMessageDialog(null, "Gratuité de +"+articles.getLiblle() + " ajouté avec succé ");
	      		
	      		
	      	}
	      });
	      btnAjouterGratuite.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnAjouterGratuite.setBounds(431, 683, 168, 24);
	      add(btnAjouterGratuite);
	      
	      pourcentage = new JTextField();
	      pourcentage.setColumns(10);
	      pourcentage.setBounds(291, 683, 121, 26);
	      add(pourcentage);
	      
	      JButton btnModifierFactureEn = new JButton("Modifier Facture en BL");
	      btnModifierFactureEn.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		
	      		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	      		listFacturePF=facturepfdao.listeFacturePFByNumFacture(magasin);	
   		
	      		for(int i=0 ; i<listFacturePF.size();i++)
	      		{
	      			
	      			FacturePF facturePF=listFacturePF.get(i);
	      			
	      			facturePF.setEtat(Constantes.ETAT_NON_REGLE);
	      			facturePF.setType(TYPE_BON_LIVRAISON);
	      			facturePF.setModeReglement(null);
	      			facturepfdao.edit(facturePF);
	      		}
	      		
	      		
	      		JOptionPane.showMessageDialog(null, "Facture modifier avec succee");
	      		
	      	}
	      });
	      btnModifierFactureEn.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnModifierFactureEn.setBounds(972, 713, 208, 51);
	      add(btnModifierFactureEn);
	      
	      JButton exporterExcel = new JButton("Exporter Excel");
	      exporterExcel.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		
	      		

	      		

		  		

	      		

				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
					  
					if(listeObjectAvecTVA.size()!=0)
 					{
						
					  String
					  titre="Etat Chiffre d'Affaire Avec Timbre "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
					  String titrefeuille="Etat Chiffre d'Affaire Avec Timbre ";
					  
					  
					  ExporterTableVersExcel.tabletoexcelEtatChiffreAffaireAvectimbre (table, titre,titrefeuille);
					  
 					}
				  
			
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	
			  		
			  		
			  		
			  		
			  		
			  	
	      		
	      		
	      		
	      		
	      		
	      	
	      		
	      		
	      		
	      		
	      		
	      		
	      	}
	      });
	      exporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      exporterExcel.setBounds(797, 600, 142, 24);
	      exporterExcel.setIcon(imgExcel);
	      add(exporterExcel);
	      btnModifierFactureEn.setVisible(false);
	   btnAjouterGratuite.setVisible(false);
	    pourcentage.setVisible(false);
	      
	     
	      Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      
for(int j=0;j<listMagasin.size();j++)
{
	Magasin magasin=listMagasin.get(j);
	combomagasin.addItem(magasin.getLibelle());
	mapMagasin.put(magasin.getLibelle(), magasin);
}
listArticle=articleDAO.listeArticleGratuite();
for(int j=0;j<listArticle.size();j++)
{
Articles article=listArticle.get(j);
comboarticlegratuite.addItem(article.getLiblle());
mapArticle.put(article.getLiblle(), article);
	
	
	
}
		
		}
	
	


	void initialiser()
	{
		combomagasin.setSelectedIndex(-1);
comboclient.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
 comboparfamille.setSelectedIndex(-1);
 comboarticle.setSelectedIndex(-1);
 InitialiseTableauFacture();	
	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mode de Reglement","Montant HT","Montant TVA" , "Montant TTC","Timbre"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
	
}
	
	
	
	void afficher_tableMontant(List<Object[]> listEtatMontantPF)
	{
		
	

				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Mode de Reglement","Montant HT","Montant TVA" , "Montant TTC","Timbre"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false
						};
						
						   Class[] types = new Class [] {
							        //COL. TYPES ARE HERE!!!
							        java.lang.String.class, BigDecimal.class, BigDecimal.class, BigDecimal.class,BigDecimal.class
							    };
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				int j=0;
				 BigDecimal especeAvecTVA=BigDecimal.ZERO;
				 
				 BigDecimal chequeAvecTVA=BigDecimal.ZERO;
				 BigDecimal versementAvecTVA=BigDecimal.ZERO;
				 BigDecimal virementAvecTVA=BigDecimal.ZERO;
				 BigDecimal traiteAvecTVA=BigDecimal.ZERO;
				 BigDecimal creditAvecTVA=BigDecimal.ZERO;
				 
				 
                  BigDecimal especeSansTVA=BigDecimal.ZERO;
				 
				 BigDecimal chequeSansTVA=BigDecimal.ZERO;
				 BigDecimal versementSansTVA=BigDecimal.ZERO;
				 BigDecimal virementSansTVA=BigDecimal.ZERO;
				 BigDecimal traiteSansTVA=BigDecimal.ZERO;
				 BigDecimal creditSansTVA=BigDecimal.ZERO;
				
				
				
				 
				while(i<listEtatMontantPF.size())
				{	
					 Object[] object=listEtatMontantPF.get(i);
				
					// BigDecimal espece=((BigDecimal) object[0]).add(((BigDecimal) object[1]));
					 
					 if(((BigDecimal) object[0])!=null)
					 {
						 especeAvecTVA=((BigDecimal) object[0]);
					 }
					 
					 
					 if(((BigDecimal) object[2])!=null)
					 {
						 chequeAvecTVA=((BigDecimal) object[2]);
					 }
					 if(((BigDecimal) object[3])!=null)
					 {
						 versementAvecTVA=((BigDecimal) object[3]);
					 }
					 if(((BigDecimal) object[4])!=null)
					 {
						 virementAvecTVA=((BigDecimal) object[4]);
					 }
					 if(((BigDecimal) object[5])!=null)
					 {
						 traiteAvecTVA=((BigDecimal) object[5]);
					 }
					 if(((BigDecimal) object[6])!=null)
					 {
						 creditAvecTVA=((BigDecimal) object[6]);
					 }
					 
					 /*
					 System.out.println("Espece 1 : "+(BigDecimal) object[0]);
					 System.out.println("Espece 2 : "+(BigDecimal) object[1]);
					 System.out.println("cheque : "+(BigDecimal) object[2]);
					 System.out.println("versement : "+(BigDecimal) object[3]);
					 System.out.println("virement : "+(BigDecimal) object[4]);
					 System.out.println("traite : "+(BigDecimal) object[5]);
					 System.out.println("credit : "+(BigDecimal) object[6]);
			*/
					
					
					i++;
				}
				
				
				
				
				//////////////////////////////////////////////////////////////////////////////////////////////////// Sans TVA ///////////////////////////////////////////////////////////////////////////////////
				
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    			if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
		    		{
	    				listeObjectSansTVA.clear();
	    				if(checkplusbl.isSelected()==true)
	    				{
	    					
	    					listeObjectSansTVA=detailFacturePfdao.listeEtatMontantFactureSansTVA(dudateChooser.getDate(), audateChooser.getDate(),magasin, Constantes.TYPE_BON_LIVRAISON);
	    		    		 
	    		    		
	    		    		 
	    		    		 
	    		    		 
	    		    		 
	    					
	    				}else
	    				{
	    					listeObjectSansTVA=detailFacturePfdao.listeEtatMontantFactureSansTVA(dudateChooser.getDate(), audateChooser.getDate(),magasin, Constantes.TYPE_FACTURE);
	    		    		 
	    		    		
	    		    		 
	    		    		 
	    		    		 
	    		    		 
	    				}
		    		
		    		 
		    		 
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
	    		}
				
				
	    		
				 
				while(j<listeObjectSansTVA.size())
				{	
					 Object[] object=listeObjectSansTVA.get(j);
				
					// BigDecimal espece=((BigDecimal) object[0]).add(((BigDecimal) object[1]));
					 
					 if(((BigDecimal) object[0])!=null)
					 {
						 especeSansTVA=((BigDecimal)object[0]);
					 }
					
					 if(((BigDecimal) object[2])!=null)
					 {
						 chequeSansTVA=((BigDecimal) object[2]);
					 }
					 if(((BigDecimal) object[3])!=null)
					 {
						 versementSansTVA=((BigDecimal) object[3]); 
					 }
					 if(((BigDecimal) object[4])!=null)
					 {
						 virementSansTVA=((BigDecimal) object[4]);
					 }
					 if(((BigDecimal) object[5])!=null)
					 {
						 traiteSansTVA=((BigDecimal) object[5]); 
					 }
					  
					 if(((BigDecimal) object[6])!=null)
					 {
						 creditSansTVA=((BigDecimal) object[6]);
					 }
					 
					/* 
					 System.out.println("Espece 1 : "+(BigDecimal) object[0]);
					 System.out.println("Espece 2 : "+(BigDecimal) object[1]);
					 System.out.println("cheque : "+(BigDecimal) object[2]);
					 System.out.println("versement : "+(BigDecimal) object[3]);
					 System.out.println("virement : "+(BigDecimal) object[4]);
					 System.out.println("traite : "+(BigDecimal) object[5]);
					 System.out.println("credit : "+(BigDecimal) object[6]);
					*/
					
					j++;
				}
				
				
				if((especeAvecTVA.add(especeSansTVA)).compareTo(BigDecimal.ZERO)>0)
				{
					Object []ligne={"Espèces",(especeAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP)).add(especeSansTVA),(especeAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP)).multiply(new BigDecimal(0.2)).setScale(6, RoundingMode.HALF_UP),especeAvecTVA.add(especeSansTVA),especeAvecTVA.multiply(new BigDecimal(0.25).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)).add(especeSansTVA.multiply(new BigDecimal(0.25).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))};
					modelefacture.addRow(ligne);
				}
					
				if((chequeAvecTVA.add(chequeSansTVA)).compareTo(BigDecimal.ZERO)>0)
				{
					Object []ligne={"Chéque",chequeAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP).add(chequeSansTVA),(chequeAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP)).multiply(new BigDecimal(0.2)).setScale(6, RoundingMode.HALF_UP),chequeAvecTVA.add(chequeSansTVA),BigDecimal.ZERO};
					modelefacture.addRow(ligne);
				}	 
				
					
				if((versementAvecTVA.add(versementSansTVA)).compareTo(BigDecimal.ZERO)>0)
				{
					Object []ligne={"Versement",versementAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP).add(versementSansTVA),(versementAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP)).multiply(new BigDecimal(0.2)).setScale(6, RoundingMode.HALF_UP),versementAvecTVA.add(versementSansTVA),versementAvecTVA.multiply(new BigDecimal(0.25).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)).add(versementSansTVA.multiply(new BigDecimal(0.25).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))};
					modelefacture.addRow(ligne);
				}	 
					
				if((virementAvecTVA.add(virementSansTVA)).compareTo(BigDecimal.ZERO)>0)
				{
					Object []ligne={"Virement",virementAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP).add(virementSansTVA),(virementAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP)).multiply(new BigDecimal(0.2)).setScale(6, RoundingMode.HALF_UP),virementAvecTVA.add(virementSansTVA),BigDecimal.ZERO};
					modelefacture.addRow(ligne);
				}	 
						
				if((traiteAvecTVA.add(traiteSansTVA)).compareTo(BigDecimal.ZERO)>0)
				{
					Object []ligne={"Traite",traiteAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP).add(virementSansTVA),(traiteAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP)).multiply(new BigDecimal(0.2)).setScale(6, RoundingMode.HALF_UP),traiteAvecTVA.add(traiteSansTVA),BigDecimal.ZERO};
					modelefacture.addRow(ligne);
				}	
				
				
				if((creditAvecTVA.add(creditSansTVA)).compareTo(BigDecimal.ZERO)!=0)
				{
					Object []ligne={"Crédit",creditAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP).add(creditSansTVA),(creditAvecTVA.divide(new BigDecimal(1.2),6, RoundingMode.HALF_UP)).multiply(new BigDecimal(0.2)).setScale(6, RoundingMode.HALF_UP),creditAvecTVA.add(creditSansTVA),BigDecimal.ZERO};
					modelefacture.addRow(ligne);
				}
							
				
				
				
			
				
	
}
	}


