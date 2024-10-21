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
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
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
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFacturePFParArticle;
import dao.entity.DetailFacturePFParFamille;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.EtatPrixMoyen;
import dao.entity.EtatPrixMoyenMP;
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
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
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


public class PrixMoyenDesAchatsMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	
	private List<EtatPrixMoyenMP> listEtatPrixMoyen =new ArrayList<EtatPrixMoyenMP>();
	private List<MatierePremier> listMatierePremiere =new ArrayList<MatierePremier>();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<CategorieMp> listCategorieMP =new ArrayList<CategorieMp>();
	private List<SubCategorieMp> listSousCategorieMP =new ArrayList<SubCategorieMp>();
	private List<DetailFacturePFParFamille> listDetailFactureParFamille =new ArrayList<DetailFacturePFParFamille>();
	private List<DetailFacturePFParArticle> listDetailFactureParArticle =new ArrayList<DetailFacturePFParArticle>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();
	private Map< String, CategorieMp> mapcategorie= new HashMap<>();
	private Map< String, SubCategorieMp> mapsouscategorie= new HashMap<>();
	private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	private ImageIcon imgExcel;
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private MatierePremiereDAO MatierPremierDAO;
	private FactureAchatMPDAO factureAchatMPDAO;
	private FacturePF facturePF;
private DetailFactureAchatMPDAO detailFactureAchatMPdao;
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
	private StockMPDAO stockMPDAO;
	private CompteClientDAO compteclientdao;
	String titre="";
	 JComboBox combomagasin = new JComboBox();
	 JComboBox comboarticle = new JComboBox();
	 private JComboBox comboMP;
	   JComboBox comboparfamille = new JComboBox();
	   private JComboBox comboparSousCategorie;
	 private CategorieMpDAO categorieDAo;
	 private SubCategorieMPDAO souscategorieDAO;
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 ButtonGroup group = new ButtonGroup();
	 JCheckBox prixmoyen = new JCheckBox("Calcule prix Moyen");
	 JComboBox SousFamilleCombo = new JComboBox();
	 private JComboBox CategorieCombo;
	 private SousFamilleEnVracDAO sousFamilleEnVracDAO;
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public PrixMoyenDesAchatsMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1564, 1062);
      
	
        try{ 
        	
        	
        	 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             
             
             
            utilisateur=AuthentificationView.utilisateur;
            clientpfdao=new ClientPFDAOImpl();
         	factureAchatMPDAO=new FactureAchatMPDAOImpl();
       depotdao=new DepotDAOImpl();
       detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();
       categorieDAo=new CategorieMpDAOImpl();
       souscategorieDAO=new SubCategorieMPAOImpl();
       stockMPDAO=ProdLauncher.stockMPDAO;
       sousFamilleEnVracDAO=new SousFamilleEnVracDAOImpl();
       MatierPremierDAO=new MatierePremierDAOImpl();
       
          } catch (Exception exp){exp.printStackTrace();}
		      
		    
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
					Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					if(magasin!=null )
					{
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String datedebutFacture=dateFormat.format(dudateChooser.getDate());
				  		  String dateFinFacture=dateFormat.format(audateChooser.getDate());
				  		  
				  		int i=0;
				  		
				  		
				  		BigDecimal TotalQuantite=BigDecimal.ZERO;
				  		BigDecimal TotalMontantHT=BigDecimal.ZERO;
				  		BigDecimal TotalMontantTVA=BigDecimal.ZERO;
				  		BigDecimal TotalMontantTTC=BigDecimal.ZERO;
				  		BigDecimal PrixMoyen=BigDecimal.ZERO;
						 
				  		listEtatPrixMoyen.clear();
						while(i<listeObject.size())
						{	
							 Object[] object=listeObject.get(i);
							 
							 
							 
							 
							EtatPrixMoyenMP etatPrixMoyenMP = new EtatPrixMoyenMP();
							
							 String famille="";
							 String sousFamille="";
						
							 MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString());
							 
							 SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(matierePremier);
							 
							 if(sousFamilleEnVrac!=null)
							 {
								 
								famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle(); 
								sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
								 
							 }else
							 {
								 
								 famille=matierePremier.getCategorieMp().getSubCategorieMp().getNom(); 
									sousFamille=matierePremier.getCategorieMp().getNom();
								 
								 
							 }
							 
							 
							 
							 
							etatPrixMoyenMP.setMp(object[0].toString());
							etatPrixMoyenMP.setSouscategorie (object[2].toString());
							etatPrixMoyenMP.setCategorie(object[3].toString());
							
							etatPrixMoyenMP.setFamille(famille);
							etatPrixMoyenMP.setSousfamille(sousFamille);
							
							etatPrixMoyenMP.setQuantite(new BigDecimal(object[4].toString()));
							etatPrixMoyenMP.setPrix(new BigDecimal(object[5].toString()));
							etatPrixMoyenMP.setMontantHT(new BigDecimal(object[6].toString()));
							etatPrixMoyenMP.setMontantTVA(new BigDecimal(object[7].toString()));
							etatPrixMoyenMP.setMontantTTC(new BigDecimal(object[8].toString()));
							
							TotalQuantite=TotalQuantite.add(etatPrixMoyenMP.getQuantite());
							TotalMontantHT=TotalMontantHT.add(etatPrixMoyenMP.getMontantHT());
							TotalMontantTVA=TotalMontantTVA.add(etatPrixMoyenMP.getMontantTVA());
							TotalMontantTTC=TotalMontantTTC.add(etatPrixMoyenMP.getMontantTTC());
							
							
							
							listEtatPrixMoyen.add(etatPrixMoyenMP);
							
							
							
							
							
							
							i++;
						}
				  	
				  		  if(TotalQuantite.compareTo(BigDecimal.ZERO)!=0)
				  		  {
				  			  
				  			PrixMoyen=TotalMontantHT.divide(TotalQuantite, 6, RoundingMode.HALF_UP);
				  		  }
				  		  
				  		  
						
						if(listEtatPrixMoyen.size()!=0)
				{
						
							
					
					Map parameters = new HashMap();
				
					
					parameters.put("magasin", magasin.getLibelle());
					parameters.put("TotalQuantite", TotalQuantite);
					parameters.put("TotalMontantHT", TotalMontantHT);
					parameters.put("TotalMontantTVA", TotalMontantTVA);
					parameters.put("TotalMontantTTC", TotalMontantTTC);
					parameters.put("PrixMoyen", PrixMoyen);
					parameters.put("titre", "ETAT PRIX MOYEN ACHAT MP");
					parameters.put("datedebut",datedebutFacture);
					parameters.put("dateFin", dateFinFacture);
					JasperUtils.imprimerEtatPrixMoyenMP(listEtatPrixMoyen,parameters);
				
					
					
					
				}
					
			}}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 599, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                      Prix Moyen Des Achats MP");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(381, 11, 880, 35);
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
					"Code MP","MP","Categorie", "Sous Categorie","Famille","Sous Famille","Quantite total", "prix Moyen","Total HT", "Total TVA","Total TTC"
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
		table.getColumnModel().getColumn(9).setPreferredWidth(136);
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setGridColor(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		 //Group the radio buttons.
	    
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		
	    	  	
		    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		    		String TousSubCategorie="";
		    		String Touscategorie="";
		    		String TousArticle="";
		    		if(magasin!=null)
		    		{
		    			if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
			    		{
		    				
			    		if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
			    		{
			    			
			    			CategorieMp categorieMP=null;
			    			SubCategorieMp souscategorieMP=null;
			    			
			    			if(comboparSousCategorie.getSelectedIndex()!=-1)
			    			{
			    				if(!comboparSousCategorie.getSelectedItem().equals(TOUS))
				    			{
			    					souscategorieMP=mapsouscategorie.get(comboparSousCategorie.getSelectedItem());
				    			}else
				    			{
				    				TousSubCategorie=TOUS;
				    			}
			    			}
			    			
			    			
			    			if(CategorieCombo.getSelectedIndex()!=-1)
			    			{
			    				if(!CategorieCombo.getSelectedItem().equals(TOUS))
				    			{
				    				 categorieMP=mapcategorie.get(CategorieCombo.getSelectedItem());
				    			}else
				    			{
				    				Touscategorie=TOUS;
				    			}
			    			}
			    			
			    		
			    			
			    			MatierePremier mp=null;
			    			if(comboMP.getSelectedIndex()!=-1)
			    			{
			    				if(!comboMP.getSelectedItem().equals(TOUS))
				    			{
				    				mp=mapMatierePremiere.get(comboMP.getSelectedItem());
				    			}else
				    			{
				    				TousArticle=TOUS;
				    			}
			    				
			    			}
			    			
				  		
				  			 
				  				  
				  				
				  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dudateChooser.getDate(), audateChooser.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
				  				afficher_tableEtatPrixMoyen(listeObject); 
				  				  
				  			  
				  			  
				  		 
				  			  
				  		
				  		  
			    			
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
		    
		    		
	    	
	  
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1524, 51);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(246, 15, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(281, 13, 166, 26);
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
	     lblAu.setBounds(457, 15, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(492, 13, 166, 26);
	     layeredPane_2.add(audateChooser);
	 
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(641, 120, 106, 23);
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
	     
	     JLabel lblCategorie = new JLabel("Categorie :");
	     lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     lblCategorie.setBounds(668, 15, 108, 24);
	     layeredPane_2.add(lblCategorie);
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(63, 16, 173, 24);
	      layeredPane_2.add(combomagasin);
	      combomagasin.setSelectedIndex(-1);
	      combomagasin.addItem("");
	      
	      comboparSousCategorie = new JComboBox();
	     comboparSousCategorie.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		listMatierePremiere.clear();
	     		if(combomagasin.getSelectedIndex()!=-1)
	     		{
	     			if(!combomagasin.getSelectedItem().equals(""))
		     		{
		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			     		if(magasin!=null )
			     		{
			     			if(!comboparSousCategorie.getSelectedItem().equals(""))
			     			{
			     				comboMP.removeAllItems();
			     				CategorieCombo.removeAllItems();
			     			
			     				if(comboparSousCategorie.getSelectedIndex()!=-1)
			     				{
			     					if(!comboparSousCategorie.getSelectedItem().equals(TOUS))
			     					{
			     						SubCategorieMp souscategoriemp=mapsouscategorie.get(comboparSousCategorie.getSelectedItem());
			     						
						     			listCategorieMP=categorieDAo.findBySubcategorie(souscategoriemp.getId());
						     			if(listCategorieMP.size()!=0)
						     			{
						     				CategorieCombo.addItem(TOUS);
						     			}
						     			for(int i=0;i<listCategorieMP.size();i++)
						     			{
						     				
						     				CategorieCombo.addItem(listCategorieMP.get(i).getNom());
						     				mapcategorie.put(listCategorieMP.get(i).getNom(), listCategorieMP.get(i));
						     				
						     			}
			     					}
			     					comboMP.setSelectedIndex(-1);
			     					CategorieCombo.setSelectedIndex(-1);
			     				}else
			     				{
			     					comboMP.removeAllItems();
			     					CategorieCombo.removeAllItems();
			     				}
			     			
			     			}else
			     			{
			     				comboMP.removeAllItems();
			     				CategorieCombo.removeAllItems();
			     			}
			     			
			     			
			     		}else
			     		{
			     			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			     			
			     			return;
			     		}
		     		}
	     		}
	     		
	     
	     	}
	     });
	     comboparSousCategorie.setSelectedIndex(-1);
	     comboparSousCategorie.setBounds(744, 16, 173, 24);
	     layeredPane_2.add(comboparSousCategorie);
	    
	     JLabel labelarticle = new JLabel("MP :");
	     labelarticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     labelarticle.setBounds(1227, 15, 40, 24);
	     layeredPane_2.add(labelarticle);
	     
	      comboMP = new JComboBox();
	     comboMP.setBounds(1264, 16, 250, 24);
	     layeredPane_2.add(comboMP);
	     AutoCompleteDecorator.decorate(comboMP);
	     JLabel label_1 = new JLabel("Magasin :");
	     label_1.setBounds(10, 14, 56, 24);
	     layeredPane_2.add(label_1);
	     label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     
	      
	     
	    
	     int i=0;
	     while(i<listClientPF.size())
	     {
	    	 
	    	ClientPF clientpf=listClientPF.get(i);
	    	comboclient.addItem(clientpf.getNom());
	    	 mapClientPF.put(clientpf.getNom(), clientpf);
	    	 
	    	 i++;
	     }
	    
	    listSousCategorieMP=souscategorieDAO.findAll();
	    comboparSousCategorie.addItem("");
	    if(listSousCategorieMP.size()!=0)
			{
	    	comboparSousCategorie.addItem(TOUS);
			}
	     int p=0;
	      while(p<listSousCategorieMP.size())
	      {
	    	  
	    	  SubCategorieMp subcategoriemp=listSousCategorieMP.get(p);
	    	  comboparSousCategorie.addItem(subcategoriemp.getNom());
	    	 
	    	  mapsouscategorie.put(subcategoriemp.getNom(), subcategoriemp);
	    	  p++;
	      }	
	      comboparSousCategorie.setSelectedIndex(-1);
	      
	       CategorieCombo = new JComboBox();
	       CategorieCombo.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       		

	     		listMatierePremiere.clear();
	     		if(combomagasin.getSelectedIndex()!=-1)
	     		{
	     			if(!combomagasin.getSelectedItem().equals(""))
		     		{
		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			     		if(magasin!=null )
			     		{
			     			if(CategorieCombo.getSelectedIndex()!=-1   )
			     			{
			     				comboMP.removeAllItems();
			     				
			     			
			     				if(!CategorieCombo.getSelectedItem().equals(""))
			     				{
			     					if(!CategorieCombo.getSelectedItem().equals(TOUS))
			     					{
			     						CategorieMp categoriemp=mapcategorie.get(CategorieCombo.getSelectedItem());
			     						
						     			listStockMP=stockMPDAO.listeStockMPByCategorie(magasin.getId(), categoriemp.getId());
						     			if(listStockMP.size()!=0)
						     			{
						     				comboarticle.addItem(TOUS);
						     			}
						     			for(int i=0;i<listStockMP.size();i++)
						     			{
						     				StockMP stockMP=listStockMP.get(i);
						     				comboMP.addItem(stockMP.getMatierePremier().getNom());
						     				mapMatierePremiere.put(stockMP.getMatierePremier().getNom(), stockMP.getMatierePremier());
						     				listMatierePremiere.add(stockMP.getMatierePremier());
						     			}
			     					}
			     					comboMP.setSelectedIndex(-1);
			     					
			     				}else
			     				{
			     					comboMP.removeAllItems();
			     				}
			     			
			     			}else
			     			{
			     				comboMP.removeAllItems();
			     			}
			     			
			     			
			     		}else
			     		{
			     			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			     			
			     			return;
			     		}
		     		}
	     		}
	     		
	     
	     	
	       		
	       		
	       		
	       	}
	       });
	      CategorieCombo.setSelectedIndex(-1);
	      CategorieCombo.setBounds(1036, 15, 181, 24);
	      layeredPane_2.add(CategorieCombo);
	      
	      JLabel lblSousFamilleArticle = new JLabel("Sous Categorie :");
	      lblSousFamilleArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	      lblSousFamilleArticle.setBounds(927, 15, 115, 24);
	      layeredPane_2.add(lblSousFamilleArticle);
	      
	      JButton btnExporterExcel = new JButton("Exporter Excel");
	      btnExporterExcel.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		

				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
				  
				  String
				  titre="Etat Prix Moyen Achat "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
				  String titrefeuille="Etat Prix Moyen Achat Article PF ";
				  ExporterTableVersExcel.tabletoexcelEtatFactureArticle(table, titre,titrefeuille);
				  
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	}
	      });
	      btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnExporterExcel.setBounds(801, 600, 128, 24);
	      btnExporterExcel.setIcon(imgExcel);
	      add(btnExporterExcel);
	      
	      
	      
	     
	      Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
	      
for(int j=0;j<listMagasin.size();j++)
{
	Magasin magasin=listMagasin.get(j);
	combomagasin.addItem(magasin.getLibelle());
	mapMagasin.put(magasin.getLibelle(), magasin);
}
		
		}
	
	


	void initialiser()
	{
		combomagasin.setSelectedIndex(-1);
comboclient.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
 comboparSousCategorie.setSelectedIndex(-1);
 comboMP.setSelectedIndex(-1);
 group.clearSelection();
 InitialiseTableauFacture();	

	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","MP","Sous Categorie", "Categorie","Quantite total", "prix Moyen","Total HT", "Total TVA","Total TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false
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
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(114);
		table.getColumnModel().getColumn(7).setPreferredWidth(114);
		
}
	
	
	
	void afficher_tableEtatPrixMoyen(List<Object[]> listEtatPrixMoyen)
	{
		
	
			
				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Code MP","MP","Categorie", "Sous Categorie","Famille","Sous Famille","Quantite total", "prix Moyen","Total HT", "Total TVA","Total TTC"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				 
				while(i<listEtatPrixMoyen.size())
				{	
					 Object[] object=listEtatPrixMoyen.get(i);
					 
					 String famille="";
					 String sousFamille="";
				
					 MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString());
					 
					 SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(matierePremier);
					 
					 if(sousFamilleEnVrac!=null)
					 {
						 
						famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle(); 
						sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
						 
					 }else
					 {
						 
						 famille=matierePremier.getCategorieMp().getSubCategorieMp().getNom(); 
							sousFamille=matierePremier.getCategorieMp().getNom();
						 
						 
					 }
					 
					 
					 
					 
					 
					 
				
					 Object []ligne={object[0],object[1],object[2],object[3],famille,sousFamille, object[4],new BigDecimal(object[5].toString()) , new BigDecimal(object[6].toString()),new BigDecimal(object[7].toString()),new BigDecimal(object[8].toString())};
						
					modelefacture.addRow(ligne);
					i++;
				}


			
		
		
		
	
}
	}


