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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
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
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
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
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
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
import javax.swing.JCheckBox;


public class EtatFacturePF extends JLayeredPane implements Constantes{
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
	private List<dao.entity.EtatFacturePF> listEtatFacturePF =new ArrayList<dao.entity.EtatFacturePF>();
	private List<FacturePF> listFacturePFMAJ =new ArrayList<FacturePF>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	
	
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
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	 private JButton btnSupprimer = new JButton();
	 private  JComboBox comboFournisseur = new JComboBox();
	private JRadioButton rdbtnDateFacture;
	private JDateChooser dudateChooser;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	String titre="";
	 private ImageIcon imgExcel;
	  JCheckBox chckbxEspce = new JCheckBox("Esp\u00E9ce");
	  JCheckBox chckbxChque = new JCheckBox("Ch\u00E9que");
	   JCheckBox chckbxVersement = new JCheckBox("Versement");
	  JCheckBox chckbxVirement = new JCheckBox("Virement");
	   JCheckBox chckbxTraite = new JCheckBox("Traite");
	  JCheckBox chckbxCrdit = new JCheckBox("Cr\u00E9dit");
	  private JCheckBox chckbxCredit;
	  JComboBox comboMagasin = new JComboBox();
	  JButton button_1 = new JButton("Exporter Excel");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatFacturePF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1530, 1062);
      
	
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
         
          } catch (Exception exp){exp.printStackTrace();}
		  
        
        
        
    
        
        
        
        
        
        
        JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1498, 51);
	    add(layeredPane_2);
		    
        JLabel lblMagasin = new JLabel("Magasin :");
	     lblMagasin.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblMagasin.setBounds(734, 10, 97, 24);
	     layeredPane_2.add(lblMagasin);
	     
	      comboMagasin = new JComboBox();
	    
	     comboMagasin.setBounds(807, 13, 175, 24);
	     layeredPane_2.add(comboMagasin);
	     
	     
	     
	     if(utilisateur.getLogin().equals("admin"))
		  {
			  listMagasin=depotdao.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_PF);
			  int l=0;
			  comboMagasin.addItem("");
		     	while (l<listMagasin.size())
		     	{
		     	
		     		Magasin magasin=listMagasin.get(l);
		     		
		     		 comboMagasin.addItem(magasin.getLibelle());
		     		 mapMagasin.put(magasin.getLibelle(), magasin);
		     		l++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  comboMagasin.addItem("");
			  if(depot!=null)
			  {

				  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
				  
				  for(int d=0;d<listMagasin.size();d++)
				  {
					  
						Magasin magasin=listMagasin.get(d);
			     		
			     		 comboMagasin.addItem(magasin.getLibelle());
			     		 mapMagasin.put(magasin.getLibelle(), magasin);  
					  
					  
					  
				  }
				  
				  
			  }
		  }
   
        
        
        
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if(listEtatFacturePF.size()!=0)
			{
				
				Map parameters = new HashMap();
				parameters.put("titre", titre);
				
				JasperUtils.imprimerEtatFacturePFAvecModeRegelement(listEtatFacturePF,parameters);
				
				
			}else
			{
				 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 599, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                 Etat Des Factures");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 791, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1520, 427);
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
				"Date Facture", "N° Facture","Type", "Client", "Montant HT", "Montant TV","Montant TTC","Mode Réglement","Numero Piece"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
	
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
	    	
	    		String req="";
	    		
	    		listEtatFacturePF.clear();
	    		
	    		Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				if(magasin!=null)
				{
					req=req+"  magasin.id='" +magasin.getId()+"'" ;
					
				}else
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP");
					return;
				}
	    		
	    		
	    		
	    		
		  		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if(dudateChooser.getDate() !=null || audateChooser.getDate()!=null) {
	    			
	    			if(dudateChooser.getDate()==null && audateChooser.getDate()!=null)
	    			{
	    				dudateChooser.setDate(audateChooser.getDate());
	    			}else if(dudateChooser.getDate()!=null && audateChooser.getDate()==null)
	    			{
	    				audateChooser.setDate(dudateChooser.getDate());
	    				
	    			}else if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
	    			{
	    				
	    				if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())<0)
			    		{

			    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			    			return;
			    		
			    		}
	    				
	    			}
	    			
	    			String dateDu=formatter.format(dudateChooser.getDate());
	    			String dateAu=formatter.format(audateChooser.getDate());
	    			
	    			
	    			
	    			req=req+" and dateFacture between '"+dateDu+"' and '"+dateAu+"'";	
	    			
	    		}
				
				ClientPF clientpf=mapClientPF.get(comboclient.getSelectedItem());
				if(clientpf!=null)
				{
					
					req=req+" and clientPF.id='" +clientpf.getId()+"'" ;
					
				}
				
				if(chckbxEspce.isSelected()==true || chckbxChque.isSelected()==true || chckbxTraite.isSelected()==true || chckbxVersement.isSelected()==true || chckbxVirement.isSelected()==true || chckbxCredit.isSelected()==true )
				{
					req=req+" and ( " ;
					
				}
				
				if(chckbxEspce.isSelected()==true)
				{
					req=req+" espece !=0 " ;
				}
				
				if(chckbxChque.isSelected()==true)
				{
					if(chckbxEspce.isSelected()==true)
					{
						
						req=req+" or cheque !=0 " ;
						
					}else
					{
						req=req+" cheque !=0 " ;
						
					}
					
				}
				
				if(chckbxTraite.isSelected()==true)
				{
					
					if(chckbxChque.isSelected()==true || chckbxEspce.isSelected()==true)
					{
						req=req+"  or traite !=0 " ;
						
					}else {
						
						req=req+" traite !=0 " ;
						
					}
						
					
					
					
					
				}
				
				if(chckbxVersement.isSelected()==true)
				{
					if(chckbxTraite.isSelected()==true || chckbxChque.isSelected()==true || chckbxEspce.isSelected()==true)
					{
						req=req+" or versement !=0 " ;
						
					}else
					{
						req=req+"  versement !=0 " ;
					}
					
				}
				
				if(chckbxVirement.isSelected()==true)
				{
					if(chckbxVersement.isSelected()==true || chckbxTraite.isSelected()==true || chckbxChque.isSelected()==true || chckbxEspce.isSelected()==true)
					{
						req=req+" or virement !=0 " ;
						
					}else
					{
						req=req+" virement !=0 " ;
					}
					
				}
				
				if(chckbxCredit.isSelected()==true)
				{
					if(chckbxVirement.isSelected()==true || chckbxVersement.isSelected()==true || chckbxTraite.isSelected()==true || chckbxChque.isSelected()==true || chckbxEspce.isSelected()==true)
					{
						req=req+" or credit !=0 " ;
						
					}else
					{
						req=req+"  credit !=0 " ;
						
					}
					
				}
				
				if(chckbxEspce.isSelected()==true || chckbxChque.isSelected()==true || chckbxTraite.isSelected()==true || chckbxVersement.isSelected()==true || chckbxVirement.isSelected()==true || chckbxCredit.isSelected()==true )
				{
					req=req+" ) " ;
					
				}
				
				System.out.println(req);
				
				listFacturePF=facturepfdao.findFacturePFByRequete(req);
				boolean existe=false;
				for(int i=0;i<listFacturePF.size() ; i++)
				{
					
					existe=false;
					FacturePF facturePF=listFacturePF.get(i);
				
					for(int j=0;j<listEtatFacturePF.size();j++)
					{
						
						dao.entity.EtatFacturePF etatFacturePF=listEtatFacturePF.get(j);
						
						if(etatFacturePF.getNumFacture().equals(facturePF.getNumFacture()))
						{
							
							BigDecimal montantHT=BigDecimal.ZERO;
							BigDecimal montantTVA=BigDecimal.ZERO;
							BigDecimal montantTTC=BigDecimal.ZERO;
							
							listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());	
							
							for(int t=0;t<listDetailFacturePF.size();t++)
							{
								DetailFacturePF detailFacturePF=listDetailFacturePF.get(t);
								
								montantHT=montantHT.add(detailFacturePF.getMontantHT());
								montantTVA=montantTVA.add(detailFacturePF.getMontantTVA());
								montantTTC=montantTTC.add(detailFacturePF.getMontantTTC());
								
							}
							
							
						existe=true;	
						
						
						String modereglement="";
						
						
						etatFacturePF.setMontantHT(etatFacturePF.getMontantHT().add(montantHT) );
						etatFacturePF.setMontantTVA(etatFacturePF.getMontantTVA().add(montantTVA));
						etatFacturePF.setMontantTTC(etatFacturePF.getMontantTTC().add(montantTTC));	
						
						if(listDetailFacturePF.size()!=0)
						{
							
							if(facturePF.getEspece()!=null)
							{
								if(facturePF.getEspece().compareTo(BigDecimal.ZERO)>0)
								{
									etatFacturePF.setEspece(etatFacturePF.getEspece().add(facturePF.getEspece()) );
									
								}
							}
							
							
							if(facturePF.getCheque()!=null)
							{
								if(facturePF.getCheque().compareTo(BigDecimal.ZERO)>0)
								{
									etatFacturePF.setCheque(etatFacturePF.getCheque().add(facturePF.getCheque()) );
									
								}
							}
							
							if(facturePF.getTraite()!=null)
							{
								if(facturePF.getTraite().compareTo(BigDecimal.ZERO)>0)
								{
									
									etatFacturePF.setTraite(facturePF.getTraite());
								}
							}
							
							if(facturePF.getVersement()!=null)
							{
								if(facturePF.getVersement().compareTo(BigDecimal.ZERO)>0)
								{
									etatFacturePF.setVersement(etatFacturePF.getVersement().add(facturePF.getVersement()) );
								}
							}
							
							if(facturePF.getVirement()!=null)
							{
								if(facturePF.getVirement().compareTo(BigDecimal.ZERO)>0)
								{
									etatFacturePF.setVirement(etatFacturePF.getVirement().add(facturePF.getVirement()) );
								}
							}
							if(facturePF.getCredit()!=null)
							{
								if(facturePF.getCredit().compareTo(BigDecimal.ZERO)>0)
								{
									etatFacturePF.setCredit(etatFacturePF.getCredit().add(facturePF.getCredit()) );
									
								}
								
							}
							
							if(facturePF.getNumCheque()!=null)
							{
								if(!facturePF.getNumCheque().equals(""))
								{
									
									etatFacturePF.setNumCheque(facturePF.getNumCheque());
									
								}
								
							}
							
							
							if(facturePF.getNumtraite()!=null)
							{
								if(!facturePF.getNumtraite().equals(""))
								{
									
									etatFacturePF.setNumtraite(facturePF.getNumtraite());
									
								}
								
							}
							
							
							if(facturePF.getNumVersement()!=null)
							{
								if(!facturePF.getNumVersement().equals(""))
								{
									
									etatFacturePF.setNumVersement(facturePF.getNumVersement());
									
								}
								
							}
							
							
							
							
							
							if(etatFacturePF.getEspece()!=null)
							{
								if(etatFacturePF.getEspece().compareTo(BigDecimal.ZERO)>0)
								{
									
									modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_ESPECE;
									
								}
								
								
							}
							
							
							if(etatFacturePF.getCheque()!=null)
							{
								if(etatFacturePF.getCheque().compareTo(BigDecimal.ZERO)>0)
								{
									
									modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_CHEQUE;
									
									
								
									
								}
								
								
							}
							
							if(etatFacturePF.getVersement()!=null)
							{
								if(etatFacturePF.getVersement().compareTo(BigDecimal.ZERO)>0)
								{
									
									modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_VERSEMENT;
									
								
									
								}
								
								
							}
							
							if(etatFacturePF.getVirement()!=null)
							{
								if(etatFacturePF.getVirement().compareTo(BigDecimal.ZERO)>0)
								{
									
									modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_VIREMENT;
									
								}
								
								
							}
							
							if(etatFacturePF.getTraite()!=null)
							{
								if(etatFacturePF.getTraite().compareTo(BigDecimal.ZERO)>0)
								{
									
									modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_TRAITE;
									
								
									
									
								}
								
								
							}
							
							if(etatFacturePF.getCredit()!=null)
							{
								if(etatFacturePF.getCredit().compareTo(BigDecimal.ZERO)>0)
								{
									
									modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_CREDIT;
									
								}
								
								
							}
							
							etatFacturePF.setModeReglement (modereglement);
							
						}
						
			
						
					
						
						listEtatFacturePF.set(j, etatFacturePF);
					
						
							
						}
						
						
						
						
						
						
						
						
					}
					
					if(existe==false)
					{
						
						
						if(facturePF.getNumFacture()!=null)
						{
							BigDecimal montantHT=BigDecimal.ZERO;
							BigDecimal montantTVA=BigDecimal.ZERO;
							BigDecimal montantTTC=BigDecimal.ZERO;
							
							listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
							
							for(int t=0;t<listDetailFacturePF.size();t++)
							{
								DetailFacturePF detailFacturePF=listDetailFacturePF.get(t);
								
								montantHT=montantHT.add(detailFacturePF.getMontantHT());
								montantTVA=montantTVA.add(detailFacturePF.getMontantTVA());
								montantTTC=montantTTC.add(detailFacturePF.getMontantTTC());
								
							}
							
							
							dao.entity.EtatFacturePF etatFacturePF=new dao.entity.EtatFacturePF();	
							
							
							String modereglement="";
							
							etatFacturePF.setNumFacture(facturePF.getNumFacture());
							etatFacturePF.setClientPF(facturePF.getClientPF());
							etatFacturePF.setMontantHT(montantHT);
							etatFacturePF.setMontantTVA(montantTVA);
							etatFacturePF.setMontantTTC(montantTTC);	
							if(listDetailFacturePF.size()!=0)
							{
								etatFacturePF.setEspece(facturePF.getEspece());
								etatFacturePF.setCheque(facturePF.getCheque());
								etatFacturePF.setTraite(facturePF.getTraite());
								etatFacturePF.setVersement(facturePF.getVersement());
								etatFacturePF.setVirement(facturePF.getVirement());
								etatFacturePF.setCredit(facturePF.getCredit());
								etatFacturePF.setNumCheque(facturePF.getNumCheque());
								etatFacturePF.setNumtraite(facturePF.getNumtraite());
								etatFacturePF.setNumVersement(facturePF.getNumVersement());
							}else
							{
								etatFacturePF.setEspece(BigDecimal.ZERO);
								etatFacturePF.setCheque(BigDecimal.ZERO);
								etatFacturePF.setTraite(BigDecimal.ZERO);
								etatFacturePF.setVersement(BigDecimal.ZERO);
								etatFacturePF.setVirement(BigDecimal.ZERO);
								etatFacturePF.setCredit(BigDecimal.ZERO);
								etatFacturePF.setNumCheque("");
								etatFacturePF.setNumtraite("");
								etatFacturePF.setNumVersement("");
							}
							
							etatFacturePF.setDateFacture(facturePF.getDateFacture());
							
							if(listDetailFacturePF.size()!=0)
							{
								if(facturePF.getEspece()!=null)
								{
									if(facturePF.getEspece().compareTo(BigDecimal.ZERO)>0)
									{
										
										modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_ESPECE;
										
									}
									
									
								}
								
								
								if(facturePF.getCheque()!=null)
								{
									if(facturePF.getCheque().compareTo(BigDecimal.ZERO)>0)
									{
										
										modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_CHEQUE;
										
										
									
										
									}
									
									
								}
								
								if(facturePF.getVersement()!=null)
								{
									if(facturePF.getVersement().compareTo(BigDecimal.ZERO)>0)
									{
										
										modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_VERSEMENT;
										
									
										
									}
									
									
								}
								
								if(facturePF.getVirement()!=null)
								{
									if(facturePF.getVirement().compareTo(BigDecimal.ZERO)>0)
									{
										
										modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_VIREMENT;
										
									}
									
									
								}
								
								if(facturePF.getTraite()!=null)
								{
									if(facturePF.getTraite().compareTo(BigDecimal.ZERO)>0)
									{
										
										modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_TRAITE;
										
									
										
										
									}
									
									
								}
								
								if(facturePF.getCredit()!=null)
								{
									if(facturePF.getCredit().compareTo(BigDecimal.ZERO)>0)
									{
										
										modereglement=modereglement+" - "+Constantes.MODE_REGLEMENT_CREDIT;
										
									}
									
									
								}	
								
							}else
							{
								if(facturePF.getModeReglement()!=null)
								{
									modereglement=facturePF.getModeReglement();
								}
								
								
							}
							
					
							
							etatFacturePF.setModeReglement (modereglement);
							
							listEtatFacturePF.add(etatFacturePF);
							
							
						}
						
				
					}
					
					
					
					
					
					
					
				}
				
				
				
	    		titre="Etat des Factures Par Mode De Réglement :  "+magasin.getLibelle();
	    		
	    		
	    		if(clientpf!=null)
	    		{
	    			
	    			titre=titre+" De Client : "+clientpf.getNom();
	    			
	    		}
	    		
	    		
	    		afficher_tableFacturePF(listEtatFacturePF);
	    		
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	 
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(301, 13, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(336, 11, 166, 26);
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
	     lblAu.setBounds(512, 13, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     		


	     		
	     		
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(547, 11, 166, 26);
	     layeredPane_2.add(audateChooser);
	     
	     JLabel lblClient_1 = new JLabel("Client  :");
	     lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblClient_1.setBounds(10, 13, 70, 24);
	     layeredPane_2.add(lblClient_1);
	     
	      comboclient = new JComboBox();
	     comboclient.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {

	     	
	     	}
	     });
	  
	     comboclient.setBounds(73, 11, 206, 24);
	     layeredPane_2.add(comboclient);
	     
	     AutoCompleteDecorator.decorate(comboclient);
	     
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
	     comboclient.addItem("");
	     
	  
	     
	      chckbxEspce = new JCheckBox("Esp\u00E9ce");
	     chckbxEspce.setBounds(988, 14, 70, 23);
	     layeredPane_2.add(chckbxEspce);
	     
	      chckbxChque = new JCheckBox("Ch\u00E9que");
	     chckbxChque.setBounds(1067, 14, 70, 23);
	     layeredPane_2.add(chckbxChque);
	     
	      chckbxVersement = new JCheckBox("Versement");
	     chckbxVersement.setBounds(1143, 14, 84, 23);
	     layeredPane_2.add(chckbxVersement);
	     
	      chckbxVirement = new JCheckBox("Virement");
	     chckbxVirement.setBounds(1229, 14, 90, 23);
	     layeredPane_2.add(chckbxVirement);
	     
	      chckbxTraite = new JCheckBox("Traite");
	     chckbxTraite.setBounds(1315, 14, 90, 23);
	     layeredPane_2.add(chckbxTraite);
	     
	      chckbxCredit = new JCheckBox("Cr\u00E9dit");
	     chckbxCredit.setBounds(1402, 15, 90, 23);
	     layeredPane_2.add(chckbxCredit);
	     
	      button_1 = new JButton("Exporter Excel");
	      button_1.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		

			  		

		      		

					
				  Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				  if(magasin!=null) {
					  
					if(listEtatFacturePF.size()!=0)
   					{
						
					  String
					  titre="Etat des Factures PF Par Mode Réglement "+mapMagasin.get(comboMagasin.getSelectedItem()).getLibelle(); 
					  String titrefeuille="Etat des Factures PF Par Mode Réglement ";
					  
					  
					  ExporterTableVersExcel.tabletoexcelEtatFactureParModeReglement (table, titre,titrefeuille);
					  
   					}
				  
			
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	
			  		
			  		
			  		
			  		
			  		
			  	
	      		
	      		
	      		
	      		
	      		
	      	}
	      });
	     button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button_1.setBounds(808, 600, 142, 24);
	     button_1.setIcon(imgExcel);
	     add(button_1);
	     int i=0;
	     while(i<listClientPF.size())
	     {
	    	 
	    	ClientPF clientpf=listClientPF.get(i);
	    	comboclient.addItem(clientpf.getNom());
	    	 mapClientPF.put(clientpf.getNom(), clientpf);
	    	 
	    	 i++;
	     }
	     
	     
	     
	     
	 
	     
	     
	     
	     
	     
	     


		
		}
	


	void initialiser()
	{
comboclient.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
		
	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date Facture", "N° Facture", "Client", "Montant HT", "Montant TV","Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false
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
		
}
	
	
	
	
	
	
	void afficher_tableFacturePF(List<dao.entity.EtatFacturePF> listEtatFacturePF)
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date Facture", "N° Facture","Code Client", "Client", "Montant HT", "Montant TV","Montant TTC","Mode Réglement","Numero Piece"
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
		int i=0;
	
		while(i<listEtatFacturePF.size())
		{	
			
		dao.entity.EtatFacturePF etatfacturepf=listEtatFacturePF.get(i);
	String ModeReglement=" ";
	String NumeroPiece=" ";
	
	
	if(etatfacturepf.getEspece()!=null)
	{
		if(etatfacturepf.getEspece().compareTo(BigDecimal.ZERO)>0)
		{
			
			ModeReglement=ModeReglement+" - "+Constantes.MODE_REGLEMENT_ESPECE;
			
		}
		
		
	}
	
	
	if(etatfacturepf.getCheque()!=null)
	{
		if(etatfacturepf.getCheque().compareTo(BigDecimal.ZERO)>0)
		{
			
			ModeReglement=ModeReglement+" - "+Constantes.MODE_REGLEMENT_CHEQUE;
			
			
			if(etatfacturepf.getNumCheque()!=null)
			{
				
				if(!etatfacturepf.getNumCheque().equals(""))
				{
					NumeroPiece=NumeroPiece+" - "+etatfacturepf.getNumCheque();
					
				}
				
				
			}
			
		}
		
		
	}
	
	if(etatfacturepf.getVersement()!=null)
	{
		if(etatfacturepf.getVersement().compareTo(BigDecimal.ZERO)>0)
		{
			
			ModeReglement=ModeReglement+" - "+Constantes.MODE_REGLEMENT_VERSEMENT;
			
			if(etatfacturepf.getNumVersement()!=null)
			{
				
				if(!etatfacturepf.getNumVersement().equals(""))
				{
					NumeroPiece=NumeroPiece+" - "+etatfacturepf.getNumVersement();
					
				}
				
				
			}
			
		}
		
		
	}
	
	if(etatfacturepf.getVirement()!=null)
	{
		if(etatfacturepf.getVirement().compareTo(BigDecimal.ZERO)>0)
		{
			
			ModeReglement=ModeReglement+" - "+Constantes.MODE_REGLEMENT_VIREMENT;
			
		}
		
		
	}
	
	if(etatfacturepf.getTraite()!=null)
	{
		if(etatfacturepf.getTraite().compareTo(BigDecimal.ZERO)>0)
		{
			
			ModeReglement=ModeReglement+" - "+Constantes.MODE_REGLEMENT_TRAITE;
			
			if(etatfacturepf.getNumtraite()!=null)
			{
				
				if(!etatfacturepf.getNumtraite().equals(""))
				{
					NumeroPiece=NumeroPiece+" - "+etatfacturepf.getNumtraite();
					
				}
				
				
			}
			
			
		}
		
		
	}
	
	if(etatfacturepf.getCredit()!=null)
	{
		if(etatfacturepf.getCredit().compareTo(BigDecimal.ZERO)>0)
		{
			
			ModeReglement=ModeReglement+" - "+Constantes.MODE_REGLEMENT_CREDIT;
			
		}
		
		
	}
	
	etatfacturepf.setNumPiece(NumeroPiece);
	
	String datefacture=formatter.format(etatfacturepf.getDateFacture());
	
	
			
			Object []ligne={datefacture , etatfacturepf.getNumFacture(), etatfacturepf.getClientPF().getCode(),etatfacturepf.getClientPF().getNom() , etatfacturepf.getMontantHT(), etatfacturepf.getMontantTVA(), etatfacturepf.getMontantTTC(),etatfacturepf.getModeReglement(),etatfacturepf.getNumPiece()};

			modelefacture.addRow(ligne);
			i++;
		}
}
	}


