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
import dao.daoImplManager.DetailFactureAutresVenteDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceTransportDAOImpl;
import dao.daoImplManager.FactureAutresVenteDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceTransportDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
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
import dao.daoManager.DetailFactureAutresVenteDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceTransportDAO;
import dao.daoManager.FactureAutresVenteDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.FournisseurDAO;
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
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAutresVente;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceTransport;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FactureAutresVente;
import dao.entity.FacturePF;
import dao.entity.FactureServiceTransport;
import dao.entity.FraisDepot;
import dao.entity.Habilitation;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
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


public class AjoutReglement extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	private List<DetailFactureServiceTransport> listDetailFactureServiceTransport =new ArrayList<DetailFactureServiceTransport>();
	private List<DetailFactureAutresVente> listDetailFactureAutresVente =new ArrayList<DetailFactureAutresVente>();
	private List<DetailCompteClient> listDetailCompteClient =new ArrayList<DetailCompteClient>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Client> listparFournisseur =new ArrayList<Client>();
	
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private List<FactureServiceTransport> listFactureServiceTransport =new ArrayList<FactureServiceTransport>();
	private List<FactureAutresVente> listFactureAutresVente =new ArrayList<FactureAutresVente>();
	private List<FactureAutresVente> listFactureAutresVenteCharger =new ArrayList<FactureAutresVente>();
	private List<FactureServiceTransport> listFactureServiceTransportCharger =new ArrayList<FactureServiceTransport>();
	private List<FactureServiceTransport> listFactureServiceTransportRegler =new ArrayList<FactureServiceTransport>();
	private List<FactureAutresVente> listFactureAutresVenteRegler =new ArrayList<FactureAutresVente>();
	private List<FacturePF> listFacturePFCharger =new ArrayList<FacturePF>();
	private List<FacturePF> listFacturePFRegler =new ArrayList<FacturePF>();
	private List<ClientPF> listClientPFP =new ArrayList<ClientPF>();
	private Map< String, Boolean> mapRegle = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Client> mapparfournisseur= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< Integer, DetailCompteClient> mapDetailCompteClient= new HashMap<>();
	private List<DetailFactureServiceTransport> listDetailFactureServiceTransportCalculerTTC =new ArrayList<DetailFactureServiceTransport>();
	private List<DetailFactureAutresVente> listDetailFactureAutresVenteCalculerTTC =new ArrayList<DetailFactureAutresVente>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FacturePFDAO facturepfdao;
	private FactureAutresVenteDAO factureAutresVentedao;
	
	private FactureServiceTransportDAO factureServiceTransportDAO;
	private FacturePF facturePF;
private DetailFacturePFDAO detailFacturePfdao;
private DetailFactureServiceTransportDAO detailFactureServiceTransportDAO;
private DetailFactureAutresVenteDAO detailFactureAutresVenteDAO;
private ClientPFDAO clientpfdao;
private  JLabel lblnumcheque = new JLabel();
private  JRadioButton rdbtnespece = new JRadioButton();
	private  JRadioButton rdbtnChque = new JRadioButton();
	private  JRadioButton rdbtnVirement = new JRadioButton();
private	 JRadioButton rdbtnTraite = new JRadioButton();
	private JTextField txtespece;
	ChargeProduction chargeproduction;
	private JTextField txtcheque;
	private JTextField txttotalfacture;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private ClientDAO fournisseurdao;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	 JButton btnSupprimer = new JButton();
	private JTextField txtparnumfacture;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	private  JButton btnDeslectionnerTout = new JButton();
	private JTextField txtDh;
	private JTextField txtnumcheque;
	private  JComboBox comboClientPf = new JComboBox();
	private JComboBox comboparfournisseur = new JComboBox();
	 private  JComboBox comboDepot = new JComboBox();
	 private DepotDAO depotdao;
	 private JTextField txtvirement;
	 private JTextField txtTraite;
	 private JTextField txtnumtraite;
	 private  JLabel lblnumtraite = new JLabel("N\u00B0 Traite :");
	 JCheckBox chekboxespece = new JCheckBox("Esp\u00E8ce");
	 private JCheckBox chkboxespece;
	 JCheckBox chckbxVirement = new JCheckBox("Virement");
	 JCheckBox chckbxChque = new JCheckBox("Ch\u00E9que");
	 JCheckBox chckbxTraite = new JCheckBox("Traite");
	 JCheckBox chckbxMultimodeReglement = new JCheckBox("MultiMode Reglement");
	 JCheckBox chckbxVersement = new JCheckBox("versement");
	 private JTextField txtVersement;
	 private JTextField txtnumVersement;
	 private JTextField txtCredit;
	 JCheckBox chckbxCrdit = new JCheckBox("Cr\u00E9dit");
	 JCheckBox chckbxTransport = new JCheckBox("Transport");
	 JCheckBox chckbxAutresVente = new JCheckBox("Autres vente");
	 private JTextField txtnumCredit;
	 
	 
	// private FacturePF ChangerNumChequefacture=null;
	 
	 
	 
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutReglement() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1359, 1106);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
           
            utilisateur=AuthentificationView.utilisateur;
            fournisseurdao=new ClientDAOImpl();
         
         	facturepfdao=new FacturePFDAOImpl();
         	detailFacturePfdao=new DetailFacturePFDAOImpl();
         	clientpfdao=new ClientPFDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	depotdao=new DepotDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	listArticle=articleDAO.findAll();
         	listClientPFP=clientpfdao.findAll();
         	Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
         listDetailCompteClient=detailCompteClientdao.findAllByDepot(depot);
         factureServiceTransportDAO=new FactureServiceTransportDAOImpl();
         detailFactureServiceTransportDAO=new DetailFactureServiceTransportDAOImpl();
         factureAutresVentedao=new FactureAutresVenteDAOImpl();
         detailFactureAutresVenteDAO=new DetailFactureAutresVenteDAOImpl();
          
         	
         	listDetailCompteClient.forEach(e->{
         		if(e.getFacturepf()!=null) {
         			mapDetailCompteClient.put(e.getFacturepf().getId(), e);
         		}
         	});
         	
         	
         	
          } catch (Exception exp){exp.printStackTrace();}
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(67, 539, 1202, 290);
				  		     	add(layeredPane);
		      
		      txtespece = new JTextField();
		     // txtespece.setVisible(false);
		     
		      util.Utils.copycoller(txtespece);
		      txtespece.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {}
		      });
		      
		      
		      
		      txtespece.setColumns(10);
		      txtespece.setBounds(144, 8, 180, 26);
		      layeredPane.add(txtespece);
		      
		    
		      
		      txtcheque = new JTextField();
		     // txtcheque.setVisible(false);
		      util.Utils.copycoller(txtcheque);
		      txtcheque.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtcheque.setColumns(10);
		      txtcheque.setBounds(144, 97, 180, 26);
		      layeredPane.add(txtcheque);
		      
		  
		     
		      
		      txtnumcheque = new JTextField();
		      txtnumcheque.setColumns(10);
		      txtnumcheque.setBounds(466, 97, 153, 26);
		      //txtnumcheque.setVisible(false);
		      layeredPane.add(txtnumcheque);
		      
		       lblnumcheque = new JLabel("N\u00B0 Cheque  :");
		      lblnumcheque.setFont(new Font("Tahoma", Font.BOLD, 11));
		      lblnumcheque.setVisible(false);
		      lblnumcheque.setBounds(340, 97, 116, 26);
		      layeredPane.add(lblnumcheque);
		      
		     
		      txtvirement = new JTextField();
		      txtvirement.setColumns(10);
		      txtvirement.setBounds(144, 52, 180, 26);
		      //txtvirement.setVisible(false);
		      layeredPane.add(txtvirement);
		      
		      ButtonGroup group = new ButtonGroup();
		      
		      txtTraite = new JTextField();
		      txtTraite.setColumns(10);
		      txtTraite.setBounds(144, 143, 180, 26);
		      layeredPane.add(txtTraite);
		      
		       lblnumtraite = new JLabel("N\u00B0 Traite :");
		      lblnumtraite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		      lblnumtraite.setBounds(340, 144, 116, 24);
		      layeredPane.add(lblnumtraite);
		      
		      txtnumtraite = new JTextField();
		      txtnumtraite.setColumns(10);
		      txtnumtraite.setBounds(466, 144, 153, 26);
		      layeredPane.add(txtnumtraite);
		      
		       chkboxespece = new JCheckBox("Esp\u00E8ce");
		      chkboxespece.setBounds(17, 9, 97, 23);
		      layeredPane.add(chkboxespece);
		      chkboxespece.setFont(new Font("Tahoma", Font.BOLD, 12));
		      
		       chckbxVirement = new JCheckBox("Virement");
		      chckbxVirement.setBounds(17, 52, 97, 23);
		      layeredPane.add(chckbxVirement);
		      chckbxVirement.setFont(new Font("Tahoma", Font.BOLD, 12));
		      
		       chckbxChque = new JCheckBox("Ch\u00E9que");
		      chckbxChque.setBounds(17, 98, 97, 23);
		      layeredPane.add(chckbxChque);
		      chckbxChque.setFont(new Font("Tahoma", Font.BOLD, 12));
		      
		       chckbxTraite = new JCheckBox("Traite");
		      chckbxTraite.setBounds(17, 144, 97, 23);
		      layeredPane.add(chckbxTraite);
		      chckbxTraite.setFont(new Font("Tahoma", Font.BOLD, 12));
		      
		       chckbxVersement = new JCheckBox("versement");
		      chckbxVersement.setFont(new Font("Tahoma", Font.BOLD, 12));
		      chckbxVersement.setBounds(17, 182, 97, 23);
		      layeredPane.add(chckbxVersement);
		      
		      txtVersement = new JTextField();
		      txtVersement.setColumns(10);
		      txtVersement.setBounds(144, 181, 180, 26);
		      layeredPane.add(txtVersement);
		      
		      JLabel lblNVersement = new JLabel("N\u00B0 Versement :");
		      lblNVersement.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		      lblNVersement.setBounds(340, 182, 116, 24);
		      layeredPane.add(lblNVersement);
		      
		      txtnumVersement = new JTextField();
		      txtnumVersement.setColumns(10);
		      txtnumVersement.setBounds(466, 182, 153, 26);
		      layeredPane.add(txtnumVersement);
		      
		       chckbxCrdit = new JCheckBox("Cr\u00E9dit");
		      chckbxCrdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		      chckbxCrdit.setBounds(17, 219, 97, 23);
		      layeredPane.add(chckbxCrdit);
		      
		      txtCredit = new JTextField();
		      txtCredit.setColumns(10);
		      txtCredit.setBounds(144, 218, 180, 26);
		      layeredPane.add(txtCredit);
		      
		      JLabel lblNCredit = new JLabel("N\u00B0 Credit:");
		      lblNCredit.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		      lblNCredit.setBounds(340, 217, 116, 24);
		      layeredPane.add(lblNCredit);
		      
		      txtnumCredit = new JTextField();
		      txtnumCredit.setColumns(10);
		      txtnumCredit.setBounds(466, 217, 153, 26);
		      layeredPane.add(txtnumCredit);
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txttotalfacture.getText().equals(""))
				{
					
					int reglementcount=0;
					
					if(chkboxespece.isSelected()==true)
					{
						
						if(!txtespece.getText().equals(""))
						{
							reglementcount=reglementcount+1;
						}
					
					}
					
					if(chckbxChque.isSelected()==true)
					{
						
						if(!txtcheque.getText().equals(""))
						{
							reglementcount=reglementcount+1;
						}
					
					}
					
					if(chckbxTraite.isSelected()==true)
					{
						
						if(!txtTraite.getText().equals(""))
						{
							reglementcount=reglementcount+1;
						}
					
					}
					
					if(chckbxVersement.isSelected()==true)
					{
						
						if(!txtVersement.getText().equals(""))
						{
							reglementcount=reglementcount+1;
						}
					
					}
					
					if(chckbxVirement.isSelected()==true)
					{
						
						if(!txtvirement.getText().equals(""))
						{
							reglementcount=reglementcount+1;
						}
					
					}
					
					if(chckbxCrdit.isSelected()==true)
					{
						
						if(!txtCredit.getText().equals(""))
						{
							reglementcount=reglementcount+1;
						}
					
					}
					
					
					
					if(reglementcount>1)
					{
						
						if(chckbxMultimodeReglement.isSelected()==false)
						{
							
							JOptionPane.showMessageDialog(null, "Veuillez Coché MiltiMode Réglement SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						
						
						
					}
					
					
					
					boolean facturer=false;
					
					if(chckbxMultimodeReglement.isSelected()==true)
					{
						
						if(mapRegle.size()<0)
						{
							
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner les factures à reglé SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							
							return;
						}else
						{
							
							BigDecimal regespece =BigDecimal.ZERO;
							BigDecimal regcheque =BigDecimal.ZERO;
							BigDecimal regvirement =BigDecimal.ZERO;
							BigDecimal regtraite =BigDecimal.ZERO;
							BigDecimal regVersement =BigDecimal.ZERO;
							BigDecimal regcredit =BigDecimal.ZERO;
							
							
							
							if(chkboxespece.isSelected()==true)
							{
								
								if(!txtespece.getText().equals(""))
								{
									regespece=new BigDecimal(txtespece.getText());
								}
							
							}
							
							
							if(chckbxCrdit.isSelected()==true)
							{
								
								if(!txtCredit.getText().equals(""))
								{
									regcredit=new BigDecimal(txtCredit.getText());
								}
							
							}
							
							
							if(chckbxChque.isSelected()==true)
							{
								
								if(!txtcheque.getText().equals(""))
								{
									if(!txtnumcheque.getText().equals(""))
									{
										regcheque=new BigDecimal(txtcheque.getText());
									}else
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Chèque SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
									}
									
								}
							
							}
							
							
							
							if(chckbxVirement.isSelected()==true)
							{
								
								if(!txtvirement.getText().equals(""))
								{
									regvirement=new BigDecimal(txtvirement.getText());
								}
							
							}
							
							if(chckbxTraite.isSelected()==true)
							{
								
								if(!txtTraite.getText().equals(""))
								{
									if(!txtnumtraite.getText().equals(""))
									{
										
										regtraite=new BigDecimal(txtTraite.getText());
									}else
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de traite SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									
								}
							
							}
							
							
							if(chckbxVersement.isSelected()==true)
							{
								
								if(!txtVersement.getText().equals(""))
								{
									/*
									if(!txtnumVersement.getText().equals(""))
									{
									*/
										
										regVersement=new BigDecimal(txtVersement.getText());
										
										
										/*
									}else
									{
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de versement SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									*/
									
								}
							
							}
							
							
							
							BigDecimal restespece =regespece;
							BigDecimal restcheque =regcheque;
							BigDecimal restvirement =regvirement;
							BigDecimal resttraite =regtraite;
							BigDecimal restversement =regVersement;
							BigDecimal restcredit =regcredit; 				
							
							
							if(chckbxTransport.isSelected()==true)
							{
								
								if(listFactureServiceTransport.size()==0)
								{
									JOptionPane.showMessageDialog(null, "La liste Des factures de Transport est Vide !!!!!!!");
									return;
								}

								for(int i=0;i<listFactureServiceTransport.size();i++)
								{
									FactureServiceTransport facturepf=listFactureServiceTransport.get(i);
									if(mapRegle.containsKey(facturepf.getNumFacture()))
									{
						                ////////////////------ Modification etat Facture ------/////////
										listFactureServiceTransportRegler=factureServiceTransportDAO.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
										for(int j=0;j<listFactureServiceTransportRegler.size();j++)
										{
											FactureServiceTransport facturepfTmp=listFactureServiceTransportRegler.get(j);
											
											facturepfTmp.setEtat(Constantes.ETAT_REGLE);
											facturepfTmp.setType(Constantes.TYPE_FACTURE);
											facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_ESPECE);
											
											facturepfTmp.setNumCheque("---");
										//	facturepfTmp.setEspece(new BigDecimal(txtespece.getText()));
											
											if(facturepfTmp.getCheque()==null)
											{
												facturepfTmp.setCheque(BigDecimal.ZERO);
											} if(facturepfTmp.getVirement()==null)
											{
												facturepfTmp.setVirement(BigDecimal.ZERO);
												
											}if(facturepfTmp.getTraite()==null)
											{
												facturepfTmp.setTraite (BigDecimal.ZERO);
											}
											if(facturepfTmp.getEspece()==null)
											{
												facturepfTmp.setEspece(BigDecimal.ZERO);
											}
											
											if(facturepfTmp.getVersement()==null)
											{
												facturepfTmp.setVersement(BigDecimal.ZERO);
											}
											
											if(facturepfTmp.getCredit()==null)
											{
												facturepfTmp.setCredit(BigDecimal.ZERO);
											}
											
											///////////////////////////////////////////// traitement de reglement MultiMode ////////////////////////////////////
											
										if(restcheque.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))).compareTo(facturepfTmp.getMontantTTC())!=0 )
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))).subtract(restcheque)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restcheque=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite())))).subtract(restcheque);
													
													restcheque=restcheque.multiply(new BigDecimal(-1));
													facturepfTmp.setCheque(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))));
													facturepfTmp.setNumCheque(txtnumcheque.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite())))).subtract(restcheque)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setCheque(restcheque);
													facturepfTmp.setNumCheque(txtnumcheque.getText());
													restcheque=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
											
										if(restespece.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restespece=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece);
													restespece=restespece.multiply(new BigDecimal(-1));
													facturepfTmp.setEspece (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))));
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setEspece(restespece);
													restespece=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										if(restvirement.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))) ).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restvirement=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement);
													restvirement=restvirement.multiply(new BigDecimal(-1));
													facturepfTmp.setVirement (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))));
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setVirement(restvirement);
													restvirement=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										
										if(resttraite.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite)).compareTo(BigDecimal.ZERO) <0)
												{
													
													resttraite=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite);
													resttraite=resttraite.multiply(new BigDecimal(-1));
													facturepfTmp.setTraite (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getCredit())))));
													facturepfTmp.setNumtraite(txtnumtraite.getText());
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setTraite(resttraite);
													facturepfTmp.setNumtraite(txtnumtraite.getText());
													resttraite=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										if(restversement.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restversement=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement);
													restversement=restversement.multiply(new BigDecimal(-1));
													facturepfTmp.setVersement (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit()))))));
													facturepfTmp.setNumVersement(txtnumVersement.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setVersement (restversement);
													facturepfTmp.setNumVersement(txtnumVersement.getText());
													restversement=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										
										if(restcredit.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restcredit=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit);
													restcredit=restcredit.multiply(new BigDecimal(-1));
													facturepfTmp.setCredit (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))));
													facturepfTmp.setNumCredit(txtnumCredit.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setCredit (restcredit);
													facturepfTmp.setNumCredit(txtnumCredit.getText());
													restcredit=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
											/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
											
											//facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
											////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
											
											
											
												factureServiceTransportDAO.edit(facturepfTmp);
										
											facturer=true;
										}
									
										
									}
								}
								
								
								
								
								
								
							}else if(chckbxAutresVente.isSelected()==true)
							{
								
								if(listFactureAutresVente.size()==0)
								{
									JOptionPane.showMessageDialog(null, "La liste Des factures Autres vente est Vide !!!!!!!");
									return;
								}

								for(int i=0;i<listFactureAutresVente.size();i++)
								{
									FactureAutresVente facturepf=listFactureAutresVente.get(i);
									if(mapRegle.containsKey(facturepf.getNumFacture()))
									{
						                ////////////////------ Modification etat Facture ------/////////
										listFactureAutresVenteRegler=factureAutresVentedao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
										for(int j=0;j<listFactureAutresVenteRegler.size();j++)
										{
											FactureAutresVente facturepfTmp=listFactureAutresVenteRegler.get(j);
											
											facturepfTmp.setEtat(Constantes.ETAT_REGLE);
											facturepfTmp.setType(Constantes.TYPE_FACTURE);
											facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_ESPECE);
											
											facturepfTmp.setNumCheque("---");
										//	facturepfTmp.setEspece(new BigDecimal(txtespece.getText()));
											
											if(facturepfTmp.getCheque()==null)
											{
												facturepfTmp.setCheque(BigDecimal.ZERO);
											} if(facturepfTmp.getVirement()==null)
											{
												facturepfTmp.setVirement(BigDecimal.ZERO);
												
											}if(facturepfTmp.getTraite()==null)
											{
												facturepfTmp.setTraite (BigDecimal.ZERO);
											}
											if(facturepfTmp.getEspece()==null)
											{
												facturepfTmp.setEspece(BigDecimal.ZERO);
											}
											
											if(facturepfTmp.getVersement()==null)
											{
												facturepfTmp.setVersement(BigDecimal.ZERO);
											}
											
											if(facturepfTmp.getCredit()==null)
											{
												facturepfTmp.setCredit(BigDecimal.ZERO);
											}
											
											///////////////////////////////////////////// traitement de reglement MultiMode ////////////////////////////////////
											
										if(restcheque.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))).compareTo(facturepfTmp.getMontantTTC())!=0 )
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))).subtract(restcheque)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restcheque=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite())))).subtract(restcheque);
													
													restcheque=restcheque.multiply(new BigDecimal(-1));
													facturepfTmp.setCheque(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))));
													facturepfTmp.setNumCheque(txtnumcheque.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite())))).subtract(restcheque)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setCheque(restcheque);
													facturepfTmp.setNumCheque(txtnumcheque.getText());
													restcheque=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
											
										if(restespece.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restespece=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece);
													restespece=restespece.multiply(new BigDecimal(-1));
													facturepfTmp.setEspece (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))));
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setEspece(restespece);
													restespece=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										if(restvirement.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))) ).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restvirement=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement);
													restvirement=restvirement.multiply(new BigDecimal(-1));
													facturepfTmp.setVirement (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))));
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setVirement(restvirement);
													restvirement=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										
										if(resttraite.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite)).compareTo(BigDecimal.ZERO) <0)
												{
													
													resttraite=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite);
													resttraite=resttraite.multiply(new BigDecimal(-1));
													facturepfTmp.setTraite (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getCredit())))));
													facturepfTmp.setNumtraite(txtnumtraite.getText());
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setTraite(resttraite);
													facturepfTmp.setNumtraite(txtnumtraite.getText());
													resttraite=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										if(restversement.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restversement=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement);
													restversement=restversement.multiply(new BigDecimal(-1));
													facturepfTmp.setVersement (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit()))))));
													facturepfTmp.setNumVersement(txtnumVersement.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setVersement (restversement);
													facturepfTmp.setNumVersement(txtnumVersement.getText());
													restversement=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										
										if(restcredit.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restcredit=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit);
													restcredit=restcredit.multiply(new BigDecimal(-1));
													facturepfTmp.setCredit (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))));
													facturepfTmp.setNumCredit(txtnumCredit.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setCredit (restcredit);
													facturepfTmp.setNumCredit(txtnumCredit.getText());
													restcredit=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
											/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
											
											//facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
											////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
											
											
											
												factureAutresVentedao.edit(facturepfTmp);
										
											facturer=true;
										}
									
										
									}
								}
								
								
								
								
								
								
							}
							
							
							
							else
							{
								
								if(listFacturePF.size()==0)
								{
									JOptionPane.showMessageDialog(null, "La liste Des factures  est Vide Veuillez décocher le transport au Autres Vente Puis rechercher à Nouveau SVP !!!!!!!");
									return;
								}

								for(int i=0;i<listFacturePF.size();i++)
								{
									FacturePF facturepf=listFacturePF.get(i);
									if(mapRegle.containsKey(facturepf.getNumFacture()))
									{
						                ////////////////------ Modification etat Facture ------/////////
										listFacturePFRegler=facturepfdao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
										for(int j=0;j<listFacturePFRegler.size();j++)
										{
											FacturePF facturepfTmp=listFacturePFRegler.get(j);
											
											facturepfTmp.setEtat(Constantes.ETAT_REGLE);
											facturepfTmp.setType(Constantes.TYPE_FACTURE);
											facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_ESPECE);
											
											facturepfTmp.setNumCheque("---");
										//	facturepfTmp.setEspece(new BigDecimal(txtespece.getText()));
											
											if(facturepfTmp.getCheque()==null)
											{
												facturepfTmp.setCheque(BigDecimal.ZERO);
											} if(facturepfTmp.getVirement()==null)
											{
												facturepfTmp.setVirement(BigDecimal.ZERO);
												
											}if(facturepfTmp.getTraite()==null)
											{
												facturepfTmp.setTraite (BigDecimal.ZERO);
											}
											if(facturepfTmp.getEspece()==null)
											{
												facturepfTmp.setEspece(BigDecimal.ZERO);
											}
											
											if(facturepfTmp.getVersement()==null)
											{
												facturepfTmp.setVersement(BigDecimal.ZERO);
											}
											
											if(facturepfTmp.getCredit()==null)
											{
												facturepfTmp.setCredit(BigDecimal.ZERO);
											}
											
											///////////////////////////////////////////// traitement de reglement MultiMode ////////////////////////////////////
											
										if(restcheque.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))).compareTo(facturepfTmp.getMontantTTC())!=0 )
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))).subtract(restcheque)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restcheque=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite())))).subtract(restcheque);
													
													restcheque=restcheque.multiply(new BigDecimal(-1));
													facturepfTmp.setCheque(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))));
													facturepfTmp.setNumCheque(txtnumcheque.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite())))).subtract(restcheque)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setCheque(restcheque);
													facturepfTmp.setNumCheque(txtnumcheque.getText());
													restcheque=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
											
										if(restespece.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restespece=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece);
													restespece=restespece.multiply(new BigDecimal(-1));
													facturepfTmp.setEspece (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))));
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restespece)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setEspece(restespece);
													restespece=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										if(restvirement.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))) ).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restvirement=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement);
													restvirement=restvirement.multiply(new BigDecimal(-1));
													facturepfTmp.setVirement (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))));
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(restvirement)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setVirement(restvirement);
													restvirement=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										
										if(resttraite.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite)).compareTo(BigDecimal.ZERO) <0)
												{
													
													resttraite=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite);
													resttraite=resttraite.multiply(new BigDecimal(-1));
													facturepfTmp.setTraite (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getCredit())))));
													facturepfTmp.setNumtraite(txtnumtraite.getText());
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getVersement().add(facturepfTmp.getCredit())))))).subtract(resttraite)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setTraite(resttraite);
													facturepfTmp.setNumtraite(txtnumtraite.getText());
													resttraite=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										if(restversement.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restversement=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement);
													restversement=restversement.multiply(new BigDecimal(-1));
													facturepfTmp.setVersement (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit()))))));
													facturepfTmp.setNumVersement(txtnumVersement.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getCredit())))))).subtract(restversement)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setVersement (restversement);
													facturepfTmp.setNumVersement(txtnumVersement.getText());
													restversement=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
										
										if(restcredit.compareTo(BigDecimal.ZERO)>0)
										{
											
											if((facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))).compareTo(facturepfTmp.getMontantTTC())!=0)
											{
												
												if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit)).compareTo(BigDecimal.ZERO) <0)
												{
													
													restcredit=(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit);
													restcredit=restcredit.multiply(new BigDecimal(-1));
													facturepfTmp.setCredit (facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))))));
													facturepfTmp.setNumCredit(txtnumCredit.getText());
													
												}else if(((facturepfTmp.getMontantTTC().subtract(facturepfTmp.getCheque().add(facturepfTmp.getEspece().add(facturepfTmp.getVirement().add(facturepfTmp.getTraite().add(facturepfTmp.getVersement())))))).subtract(restcredit)).compareTo(BigDecimal.ZERO) >=0)
												{
													
													facturepfTmp.setCredit (restcredit);
													facturepfTmp.setNumCredit(txtnumCredit.getText());
													restcredit=BigDecimal.ZERO;
													
													
													
												}
												
												
											}
											
											
										}
										
											/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
											
											//facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
											////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
											
											
											
											DetailCompteClient detailcompteclient=mapDetailCompteClient.get(facturepfTmp.getId());
											
											detailcompteclient.setMontantCredit(facturepfTmp.getMontantTTC());
										    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
										    detailcompteclient.setDesignation("Montant sur Facture N "+facturepfTmp.getNumFacture());
											facturepfdao.edit(facturepfTmp);
											detailCompteClientdao.edit(detailcompteclient);
											compteclientdao.edit( detailcompteclient.getCompteClient());
											facturer=true;
										}
									
										
									}
								}
								
								
							}
							
							
						
										
								
						
										
										if(facturer==true)
										{
											JOptionPane.showMessageDialog(null, "Bon(s) Livraison Facturé(s) avec succée ","Bravo",JOptionPane.INFORMATION_MESSAGE);
											initialiser();
											return;
										}
					
							
							
						}
						
						
							
					}else
					{
						
						
						if(chckbxTransport.isSelected()==true)
						{
							

							
							
							if(chkboxespece.isSelected()==true)
							{
								
								if(!txtespece.getText().equals(""))
								{
										for(int i=0;i<listFactureServiceTransport.size();i++)
										{
											FactureServiceTransport facturepf=listFactureServiceTransport.get(i);
											if(mapRegle.containsKey(facturepf.getNumFacture()))
											{
								                ////////////////------ Modification etat Facture ------/////////
												listFactureServiceTransportRegler=factureServiceTransportDAO.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
												for(int j=0;j<listFactureServiceTransportRegler.size();j++)
												{
													FactureServiceTransport facturepfTmp=listFactureServiceTransportRegler.get(j);
													
													facturepfTmp.setEtat(Constantes.ETAT_REGLE);
													facturepfTmp.setType(Constantes.TYPE_FACTURE);
													facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_ESPECE);
													facturepfTmp.setNumCheque("---");
													facturepfTmp.setEspece(facturepfTmp.getMontantTTC());
													
													if(facturepfTmp.getCheque()==null)
													{
														facturepfTmp.setCheque(BigDecimal.ZERO);
													} if(facturepfTmp.getVirement()==null)
													{
														facturepfTmp.setVirement(BigDecimal.ZERO);
														
													}if(facturepfTmp.getTraite()==null)
													{
														facturepfTmp.setTraite (BigDecimal.ZERO);
													}
													if(facturepfTmp.getEspece()==null)
													{
														facturepfTmp.setEspece(BigDecimal.ZERO);
													}
													if(facturepfTmp.getVersement()==null)
													{
														facturepfTmp.setVersement(BigDecimal.ZERO);
													}
													
													facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
													////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
													
													
													
													
													factureServiceTransportDAO.edit(facturepfTmp);
													
													facturer=true;
												}
											
												
											}
										}
										
										
								
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
							
								
								
								
								
							}
							
							
		                if(chckbxChque.isSelected()==true)
		                	
							{
								
								
								if(!txtcheque.getText().equals(""))
								{
									
									if(!txtnumcheque.getText().equals(""))
									{
										
											for(int i=0;i<listFactureServiceTransport.size();i++)
											{
												FactureServiceTransport facturepf=listFactureServiceTransport.get(i);
												if(mapRegle.containsKey(facturepf.getNumFacture()))
												{
													
													listFactureServiceTransportRegler=factureServiceTransportDAO.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
													for(int j=0;j<listFactureServiceTransportRegler.size();j++)
													{
														FactureServiceTransport facturepfTmp=listFactureServiceTransportRegler.get(j);
													
													////////////////------ Modification etat Facture ------/////////
														facturepfTmp.setEtat(Constantes.ETAT_REGLE);
														facturepfTmp.setType(Constantes.TYPE_FACTURE);
														facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_CHEQUE);												
														facturepfTmp.setNumCheque(txtnumcheque.getText());
														facturepfTmp.setCheque(facturepfTmp.getMontantTTC());
														if(facturepfTmp.getCheque()==null)
														{
															facturepfTmp.setCheque(BigDecimal.ZERO);
														} if(facturepfTmp.getVirement()==null)
														{
															facturepfTmp.setVirement(BigDecimal.ZERO);
														} if(facturepfTmp.getTraite()==null)
														{
															facturepfTmp.setTraite (BigDecimal.ZERO);
														} if(facturepfTmp.getEspece()==null)
														{
															facturepfTmp.setEspece(BigDecimal.ZERO);
														}
														if(facturepfTmp.getVersement()==null)
														{
															facturepfTmp.setVersement(BigDecimal.ZERO);
														}
														facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
								                   
													factureServiceTransportDAO.edit(facturepfTmp);
													
													facturer=true;
												}
												}
											}
											
											
											
											
									
										
									}else
									{
										
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Chèque SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
										
										
									}
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
							
								
							}
		 
		 
		 
		               if(chckbxVirement.isSelected()==true)
		            	   
							{
		            	   

								
								
								if(!txtvirement.getText().equals(""))
								{
									
										
											
											for(int i=0;i<listFactureServiceTransport.size();i++)
											{
												FactureServiceTransport facturepf=listFactureServiceTransport.get(i);
												if(mapRegle.containsKey(facturepf.getNumFacture()))
												{
													listFactureServiceTransportRegler=factureServiceTransportDAO.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
													for(int j=0;j<listFactureServiceTransportRegler.size();j++)
													{
														FactureServiceTransport facturepfTmp=listFactureServiceTransportRegler.get(j);
													
													////////////////------ Modification etat Facture ------/////////
														facturepfTmp.setEtat(Constantes.ETAT_REGLE);
														facturepfTmp.setType(Constantes.TYPE_FACTURE);
														facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_VIREMENT);
														facturepfTmp.setVirement(facturepfTmp.getMontantTTC());
														
														if(facturepfTmp.getCheque()==null)
														{
															facturepfTmp.setCheque(BigDecimal.ZERO);
														} if(facturepfTmp.getVirement()==null)
														{
															facturepfTmp.setVirement(BigDecimal.ZERO);
														} if(facturepfTmp.getTraite()==null)
														{
															facturepfTmp.setTraite (BigDecimal.ZERO);
														} if(facturepfTmp.getEspece()==null)
														{
															facturepfTmp.setEspece(BigDecimal.ZERO);
														}
														if(facturepfTmp.getVersement()==null)
														{
															facturepfTmp.setVersement(BigDecimal.ZERO);
														}
														facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
													//facturepf.setNumCheque(txtnumcheque.getText());
								                 
													factureServiceTransportDAO.edit(facturepfTmp);
													
													facturer=true;
												}
												}
											}										
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
								
								
							}

		 if(chckbxTraite.isSelected()==true)
			 
							{
								
								
								if(!txtTraite.getText().equals(""))
								{
									
									if(!txtnumtraite.getText().equals(""))
									{
										
										
											
											for(int i=0;i<listFactureServiceTransport.size();i++)
											{
												FactureServiceTransport facturepf=listFactureServiceTransport.get(i);
												if(mapRegle.containsKey(facturepf.getNumFacture()))
												{
													
													listFactureServiceTransportRegler=factureServiceTransportDAO.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
													for(int j=0;j<listFactureServiceTransportRegler.size();j++)
													{
														FactureServiceTransport facturepfTmp=listFactureServiceTransportRegler.get(j);
													
													////////////////------ Modification etat Facture ------/////////
														facturepfTmp.setEtat(Constantes.ETAT_REGLE);
														facturepfTmp.setType(Constantes.TYPE_FACTURE);
														facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_TRAITE);
														facturepfTmp.setNumtraite(txtnumtraite.getText());
														facturepfTmp.setTraite(facturepfTmp.getMontantTTC());
														if(facturepfTmp.getCheque()==null)
														{
															facturepfTmp.setCheque(BigDecimal.ZERO);
														} if(facturepfTmp.getVirement()==null)
														{
															facturepfTmp.setVirement(BigDecimal.ZERO);
														} if(facturepfTmp.getTraite()==null)
														{
															facturepfTmp.setTraite (BigDecimal.ZERO);
														} if(facturepfTmp.getEspece()==null)
														{
															facturepfTmp.setEspece(BigDecimal.ZERO);
														}
														if(facturepfTmp.getVersement()==null)
														{
															facturepfTmp.setVersement(BigDecimal.ZERO);
														}
														
														facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
								                   	////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
													
													factureServiceTransportDAO.edit(facturepfTmp);
													
													facturer=true;
												}
												}
											}
											
											
											
								
										
									}else
									{
										
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Traite SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
										
										
									}
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
							
								
							}
		 
		 

		 if(chckbxVersement.isSelected()==true)
			 
							{
								
								
								if(!txtVersement.getText().equals(""))
								{
									/*
									if(!txtnumVersement.getText().equals(""))
									{
									*/	
										
											
											for(int i=0;i<listFactureServiceTransport.size();i++)
											{
												FactureServiceTransport facturepf=listFactureServiceTransport.get(i);
												if(mapRegle.containsKey(facturepf.getNumFacture()))
												{
													
													listFactureServiceTransportRegler=factureServiceTransportDAO.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
													for(int j=0;j<listFactureServiceTransportRegler.size();j++)
													{
														FactureServiceTransport facturepfTmp=listFactureServiceTransportRegler.get(j);
													
													////////////////------ Modification etat Facture ------/////////
														facturepfTmp.setEtat(Constantes.ETAT_REGLE);
														facturepfTmp.setType(Constantes.TYPE_FACTURE);
														facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_VERSEMENT);
														facturepfTmp.setNumVersement (txtnumVersement.getText());
														facturepfTmp.setVersement(facturepfTmp.getMontantTTC());
														if(facturepfTmp.getCheque()==null)
														{
															facturepfTmp.setCheque(BigDecimal.ZERO);
														} if(facturepfTmp.getVirement()==null)
														{
															facturepfTmp.setVirement(BigDecimal.ZERO);
														} if(facturepfTmp.getTraite()==null)
														{
															facturepfTmp.setTraite (BigDecimal.ZERO);
														} if(facturepfTmp.getEspece()==null)
														{
															facturepfTmp.setEspece(BigDecimal.ZERO);
														}
														if(facturepfTmp.getVersement()==null)
														{
															facturepfTmp.setVersement(BigDecimal.ZERO);
														}
														
														
														facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
								                   	////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
													
													factureServiceTransportDAO.edit(facturepfTmp);
													
													facturer=true;
												}
												}
											}
											
											
											
								/*
										
									}else
									{
										
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Versement SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
										
										
									}
									*/
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
							
								
							}
		 
		 
		 if(chckbxCrdit.isSelected()==true)
			 
			{
				
				
				if(!txtCredit.getText().equals(""))
				{
					/*
					if(!txtnumVersement.getText().equals(""))
					{
					*/	
						
							
							for(int i=0;i<listFactureServiceTransport.size();i++)
							{
								FactureServiceTransport facturepf=listFactureServiceTransport.get(i);
								if(mapRegle.containsKey(facturepf.getNumFacture()))
								{
									
									listFactureServiceTransportRegler=factureServiceTransportDAO.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
									for(int j=0;j<listFactureServiceTransportRegler.size();j++)
									{
										FactureServiceTransport facturepfTmp=listFactureServiceTransportRegler.get(j);
									
									////////////////------ Modification etat Facture ------/////////
										facturepfTmp.setEtat(Constantes.ETAT_REGLE);
										facturepfTmp.setType(Constantes.TYPE_FACTURE);
										facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_CREDIT);
										 
										facturepfTmp.setCredit (facturepfTmp.getMontantTTC());
										facturepfTmp.setNumCredit(txtnumCredit.getText());
										if(facturepfTmp.getCheque()==null)
										{
											facturepfTmp.setCheque(BigDecimal.ZERO);
										} if(facturepfTmp.getVirement()==null)
										{
											facturepfTmp.setVirement(BigDecimal.ZERO);
										} if(facturepfTmp.getTraite()==null)
										{
											facturepfTmp.setTraite (BigDecimal.ZERO);
										} if(facturepfTmp.getEspece()==null)
										{
											facturepfTmp.setEspece(BigDecimal.ZERO);
										}
										if(facturepfTmp.getVersement()==null)
										{
											facturepfTmp.setVersement(BigDecimal.ZERO);
										}
										
										
										//facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
				                   	////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
									
									factureServiceTransportDAO.edit(facturepfTmp);
									
									facturer=true;
								}
								}
							}
							
							
							
				/*
						
					}else
					{
						
						JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Versement SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
						return;
						
						
					}
					*/
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
			
				
			}
		 
		 
		 
		 
		 
		 
		 
		 
		 
							
							
							
						
							
							
							
						}else
						{
							
							

							
							
							if(chkboxespece.isSelected()==true)
							{
								
								if(!txtespece.getText().equals(""))
								{
										for(int i=0;i<listFacturePF.size();i++)
										{
											FacturePF facturepf=listFacturePF.get(i);
											if(mapRegle.containsKey(facturepf.getNumFacture()))
											{
								                ////////////////------ Modification etat Facture ------/////////
												listFacturePFRegler=facturepfdao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
												for(int j=0;j<listFacturePFRegler.size();j++)
												{
													FacturePF facturepfTmp=listFacturePFRegler.get(j);
													
													facturepfTmp.setEtat(Constantes.ETAT_REGLE);
													facturepfTmp.setType(Constantes.TYPE_FACTURE);
													facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_ESPECE);
													facturepfTmp.setNumCheque("---");
													facturepfTmp.setEspece(facturepfTmp.getMontantTTC());
													
													if(facturepfTmp.getCheque()==null)
													{
														facturepfTmp.setCheque(BigDecimal.ZERO);
													} if(facturepfTmp.getVirement()==null)
													{
														facturepfTmp.setVirement(BigDecimal.ZERO);
														
													}if(facturepfTmp.getTraite()==null)
													{
														facturepfTmp.setTraite (BigDecimal.ZERO);
													}
													if(facturepfTmp.getEspece()==null)
													{
														facturepfTmp.setEspece(BigDecimal.ZERO);
													}
													if(facturepfTmp.getVersement()==null)
													{
														facturepfTmp.setVersement(BigDecimal.ZERO);
													}
													
													facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
													////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
													
													
													
													DetailCompteClient detailcompteclient=mapDetailCompteClient.get(facturepfTmp.getId());
													
													detailcompteclient.setMontantCredit(facturepfTmp.getMontantTTC());
												    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(facturepfTmp.getMontantTTC()));
												    detailcompteclient.setDesignation("Montant sur Facture N "+facturepfTmp.getNumFacture());
													facturepfdao.edit(facturepfTmp);
													detailCompteClientdao.edit(detailcompteclient);
													compteclientdao.edit( detailcompteclient.getCompteClient());
													facturer=true;
												}
											
												
											}
										}
										
										
								
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
							
								
								
								
								
							}
							
							
		                if(chckbxChque.isSelected()==true)
		                	
							{
								
								
								if(!txtcheque.getText().equals(""))
								{
									
									if(!txtnumcheque.getText().equals(""))
									{
										
											for(int i=0;i<listFacturePF.size();i++)
											{
												FacturePF facturepf=listFacturePF.get(i);
												if(mapRegle.containsKey(facturepf.getNumFacture()))
												{
													
													listFacturePFRegler=facturepfdao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
													for(int j=0;j<listFacturePFRegler.size();j++)
													{
														FacturePF facturepfTmp=listFacturePFRegler.get(j);
													
													////////////////------ Modification etat Facture ------/////////
														facturepfTmp.setEtat(Constantes.ETAT_REGLE);
														facturepfTmp.setType(Constantes.TYPE_FACTURE);
														facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_CHEQUE);												
														facturepfTmp.setNumCheque(txtnumcheque.getText());
														facturepfTmp.setCheque(facturepfTmp.getMontantTTC());
														if(facturepfTmp.getCheque()==null)
														{
															facturepfTmp.setCheque(BigDecimal.ZERO);
														} if(facturepfTmp.getVirement()==null)
														{
															facturepfTmp.setVirement(BigDecimal.ZERO);
														} if(facturepfTmp.getTraite()==null)
														{
															facturepfTmp.setTraite (BigDecimal.ZERO);
														} if(facturepfTmp.getEspece()==null)
														{
															facturepfTmp.setEspece(BigDecimal.ZERO);
														}
														if(facturepfTmp.getVersement()==null)
														{
															facturepfTmp.setVersement(BigDecimal.ZERO);
														}
														facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
								                   	////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
													DetailCompteClient detailcompteclient=mapDetailCompteClient.get(facturepfTmp.getId());
													detailcompteclient.setMontantCredit(facturepfTmp.getMontantTTC());
												    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(facturepfTmp.getMontantTTC()));
												    detailcompteclient.setDesignation("Montant sur Facture N "+facturepfTmp.getNumFacture());
													facturepfdao.edit(facturepfTmp);
													detailCompteClientdao.edit(detailcompteclient);
													compteclientdao.edit( detailcompteclient.getCompteClient());
													facturer=true;
												}
												}
											}
											
											
											
											
									
										
									}else
									{
										
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Chèque SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
										
										
									}
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
							
								
							}
		 
		 
		 
		               if(chckbxVirement.isSelected()==true)
		            	   
							{
		            	   

								
								
								if(!txtvirement.getText().equals(""))
								{
									
										
											
											for(int i=0;i<listFacturePF.size();i++)
											{
												FacturePF facturepf=listFacturePF.get(i);
												if(mapRegle.containsKey(facturepf.getNumFacture()))
												{
													listFacturePFRegler=facturepfdao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
													for(int j=0;j<listFacturePFRegler.size();j++)
													{
														FacturePF facturepfTmp=listFacturePFRegler.get(j);
													
													////////////////------ Modification etat Facture ------/////////
														facturepfTmp.setEtat(Constantes.ETAT_REGLE);
														facturepfTmp.setType(Constantes.TYPE_FACTURE);
														facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_VIREMENT);
														facturepfTmp.setVirement(facturepfTmp.getMontantTTC());
														
														if(facturepfTmp.getCheque()==null)
														{
															facturepfTmp.setCheque(BigDecimal.ZERO);
														} if(facturepfTmp.getVirement()==null)
														{
															facturepfTmp.setVirement(BigDecimal.ZERO);
														} if(facturepfTmp.getTraite()==null)
														{
															facturepfTmp.setTraite (BigDecimal.ZERO);
														} if(facturepfTmp.getEspece()==null)
														{
															facturepfTmp.setEspece(BigDecimal.ZERO);
														}
														if(facturepfTmp.getVersement()==null)
														{
															facturepfTmp.setVersement(BigDecimal.ZERO);
														}
														facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
													//facturepf.setNumCheque(txtnumcheque.getText());
								                   	////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
													DetailCompteClient detailcompteclient=mapDetailCompteClient.get(facturepfTmp.getId());
													detailcompteclient.setMontantCredit(facturepfTmp.getMontantTTC());
												    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(facturepfTmp.getMontantTTC()));
												    detailcompteclient.setDesignation("Montant sur Facture N "+facturepfTmp.getNumFacture());
													facturepfdao.edit(facturepfTmp);
													detailCompteClientdao.edit(detailcompteclient);
													compteclientdao.edit( detailcompteclient.getCompteClient());
													facturer=true;
												}
												}
											}										
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
								
								
							}

		 if(chckbxTraite.isSelected()==true)
			 
							{
								
								
								if(!txtTraite.getText().equals(""))
								{
									
									if(!txtnumtraite.getText().equals(""))
									{
										
										
											
											for(int i=0;i<listFacturePF.size();i++)
											{
												FacturePF facturepf=listFacturePF.get(i);
												if(mapRegle.containsKey(facturepf.getNumFacture()))
												{
													
													listFacturePFRegler=facturepfdao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
													for(int j=0;j<listFacturePFRegler.size();j++)
													{
														FacturePF facturepfTmp=listFacturePFRegler.get(j);
													
													////////////////------ Modification etat Facture ------/////////
														facturepfTmp.setEtat(Constantes.ETAT_REGLE);
														facturepfTmp.setType(Constantes.TYPE_FACTURE);
														facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_TRAITE);
														facturepfTmp.setNumtraite(txtnumtraite.getText());
														facturepfTmp.setTraite(facturepfTmp.getMontantTTC());
														if(facturepfTmp.getCheque()==null)
														{
															facturepfTmp.setCheque(BigDecimal.ZERO);
														} if(facturepfTmp.getVirement()==null)
														{
															facturepfTmp.setVirement(BigDecimal.ZERO);
														} if(facturepfTmp.getTraite()==null)
														{
															facturepfTmp.setTraite (BigDecimal.ZERO);
														} if(facturepfTmp.getEspece()==null)
														{
															facturepfTmp.setEspece(BigDecimal.ZERO);
														}
														if(facturepfTmp.getVersement()==null)
														{
															facturepfTmp.setVersement(BigDecimal.ZERO);
														}
														
														facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
								                   	////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
													DetailCompteClient detailcompteclient=mapDetailCompteClient.get(facturepfTmp.getId());
													detailcompteclient.setMontantCredit(facturepfTmp.getMontantTTC());
												    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(facturepfTmp.getMontantTTC()));
												    detailcompteclient.setDesignation("Montant sur Facture N "+facturepfTmp.getNumFacture());
													facturepfdao.edit(facturepfTmp);
													detailCompteClientdao.edit(detailcompteclient);
													compteclientdao.edit(detailcompteclient.getCompteClient());
													facturer=true;
												}
												}
											}
											
											
											
								
										
									}else
									{
										
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Traite SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
										
										
									}
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
							
								
							}
		 
		 

		 if(chckbxVersement.isSelected()==true)
			 
							{
								
								
								if(!txtVersement.getText().equals(""))
								{
									/*
									if(!txtnumVersement.getText().equals(""))
									{
									*/	
										
											
											for(int i=0;i<listFacturePF.size();i++)
											{
												FacturePF facturepf=listFacturePF.get(i);
												if(mapRegle.containsKey(facturepf.getNumFacture()))
												{
													
													listFacturePFRegler=facturepfdao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
													for(int j=0;j<listFacturePFRegler.size();j++)
													{
														FacturePF facturepfTmp=listFacturePFRegler.get(j);
													
													////////////////------ Modification etat Facture ------/////////
														facturepfTmp.setEtat(Constantes.ETAT_REGLE);
														facturepfTmp.setType(Constantes.TYPE_FACTURE);
														facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_VERSEMENT);
														facturepfTmp.setNumVersement (txtnumVersement.getText());
														facturepfTmp.setVersement(facturepfTmp.getMontantTTC());
														if(facturepfTmp.getCheque()==null)
														{
															facturepfTmp.setCheque(BigDecimal.ZERO);
														} if(facturepfTmp.getVirement()==null)
														{
															facturepfTmp.setVirement(BigDecimal.ZERO);
														} if(facturepfTmp.getTraite()==null)
														{
															facturepfTmp.setTraite (BigDecimal.ZERO);
														} if(facturepfTmp.getEspece()==null)
														{
															facturepfTmp.setEspece(BigDecimal.ZERO);
														}
														if(facturepfTmp.getVersement()==null)
														{
															facturepfTmp.setVersement(BigDecimal.ZERO);
														}
														
														
														facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
								                   	////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
													DetailCompteClient detailcompteclient=mapDetailCompteClient.get(facturepfTmp.getId());
													detailcompteclient.setMontantCredit(facturepfTmp.getMontantTTC());
												    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(facturepfTmp.getMontantTTC()));
												    detailcompteclient.setDesignation("Montant sur Facture N "+facturepfTmp.getNumFacture());
													facturepfdao.edit(facturepfTmp);
													detailCompteClientdao.edit(detailcompteclient);
													compteclientdao.edit(detailcompteclient.getCompteClient());
													facturer=true;
												}
												}
											}
											
											
											
								/*
										
									}else
									{
										
										JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Versement SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
										return;
										
										
									}
									*/
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								
							
								
							}
		 
		 
		 if(chckbxCrdit.isSelected()==true)
			 
			{
				
				
				if(!txtCredit.getText().equals(""))
				{
					/*
					if(!txtnumVersement.getText().equals(""))
					{
					*/	
						
							
							for(int i=0;i<listFacturePF.size();i++)
							{
								FacturePF facturepf=listFacturePF.get(i);
								if(mapRegle.containsKey(facturepf.getNumFacture()))
								{
									
									listFacturePFRegler=facturepfdao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
									for(int j=0;j<listFacturePFRegler.size();j++)
									{
										FacturePF facturepfTmp=listFacturePFRegler.get(j);
									
									////////////////------ Modification etat Facture ------/////////
										facturepfTmp.setEtat(Constantes.ETAT_REGLE);
										facturepfTmp.setType(Constantes.TYPE_FACTURE);
										facturepfTmp.setModeReglement(Constantes.MODE_REGLEMENT_CREDIT);
										facturepfTmp.setNumCredit(txtnumCredit.getText());
										facturepfTmp.setCredit (facturepfTmp.getMontantTTC());
										if(facturepfTmp.getCheque()==null)
										{
											facturepfTmp.setCheque(BigDecimal.ZERO);
										} if(facturepfTmp.getVirement()==null)
										{
											facturepfTmp.setVirement(BigDecimal.ZERO);
										} if(facturepfTmp.getTraite()==null)
										{
											facturepfTmp.setTraite (BigDecimal.ZERO);
										} if(facturepfTmp.getEspece()==null)
										{
											facturepfTmp.setEspece(BigDecimal.ZERO);
										}
										if(facturepfTmp.getVersement()==null)
										{
											facturepfTmp.setVersement(BigDecimal.ZERO);
										}
										
										
										//facturepfTmp.setCredit(facturepfTmp.getMontantTTC().subtract(facturepfTmp.getEspece().add(facturepfTmp.getCheque()).add(facturepfTmp.getVirement()).add(facturepfTmp.getTraite().add(facturepfTmp.getVersement()))));
				                   	////////////////------ inserer montant credit Detail Compte client et modifier solde compte client ------/////////
									DetailCompteClient detailcompteclient=mapDetailCompteClient.get(facturepfTmp.getId());
									detailcompteclient.setMontantCredit(facturepfTmp.getMontantTTC());
								    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(facturepfTmp.getMontantTTC()));
								    detailcompteclient.setDesignation("Montant sur Facture N "+facturepfTmp.getNumFacture());
									facturepfdao.edit(facturepfTmp);
									detailCompteClientdao.edit(detailcompteclient);
									compteclientdao.edit(detailcompteclient.getCompteClient());
									facturer=true;
								}
								}
							}
							
							
							
				/*
						
					}else
					{
						
						JOptionPane.showMessageDialog(null, "Veuillez entrer le Numero de Versement SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
						return;
						
						
					}
					*/
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant SVP !!!!","Attention",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
			
				
			}
		 
		 
		 
		 
		 
		 
		 
		 
		 
							
							
							
						
							
							
							
							
						}
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					
	
					
					
					if(facturer==true)
					{
						JOptionPane.showMessageDialog(null, "Bon(s) Livraison Facturé(s) avec succée ","Bravo",JOptionPane.INFORMATION_MESSAGE);
						initialiser();
						return;
					}
					
					
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le(s) Facture(s) à reglé et calculer le Montant !!!!","Attention",JOptionPane.ERROR_MESSAGE);
					initialiser();
					return;
					
				}
				
			
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(606, 840, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Mode de Reglement");
		titledSeparator_1.setBounds(67, 506, 1202, 30);
		add(titledSeparator_1);
		
			 
		      
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les BL \u00E0 Regl\u00E9 :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(228, 11, 808, 44);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(67, 229, 1202, 229);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Num Facture", "Date Facture", "Client","Fournisseur","Etat", "Montant TTC", "Regl\u00E9"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(82);
		
		
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setGridColor(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
	    
	    JButton btnSelectionnertout = new JButton();
	    btnSelectionnertout.setToolTipText("Selectionner Tout");
	    btnSelectionnertout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		txttotalfacture.setText("");
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 6);
	     		}
	    	}
	    });
	    btnSelectionnertout.setIcon(imgSelectAll);
	    btnSelectionnertout.setBounds(1239, 202, 26, 26);
	    add(btnSelectionnertout);
	    
	     btnDeslectionnerTout = new JButton();
	     btnDeslectionnerTout.setToolTipText("deselectionner Tout");
	     btnDeslectionnerTout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		txttotalfacture.setText("");
	     		
	     		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 6);
	     		}
	     	}
	     });
	   
	    btnDeslectionnerTout.setBounds(1210, 202, 26, 26);
	    btnDeslectionnerTout.setIcon(imgDeselectAll);
	    add(btnDeslectionnerTout);
	    
	    JButton btnNewButton = new JButton("Calculer");
	    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		BigDecimal total=BigDecimal.ZERO;
	    		mapRegle.clear();
	    		if(!remplirMapRegler())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à reglé", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					if(chckbxTransport.isSelected()==true)
					{
						
						for(int i=0;i<listFactureServiceTransport.size();i++)
						{
							FactureServiceTransport factureServiceTransport=listFactureServiceTransport.get(i);
							if(mapRegle.containsKey(factureServiceTransport.getNumFacture()))
							{
								listFactureServiceTransportRegler=factureServiceTransportDAO.findByNumFacture(factureServiceTransport.getNumFacture(), factureServiceTransport.getDepot());
								for(int j=0;j<listFactureServiceTransportRegler.size();j++)
								{
									
									listDetailFactureServiceTransportCalculerTTC=detailFactureServiceTransportDAO.listeDetailFactureServiceTransportByFacture(listFactureServiceTransportRegler.get(j).getId());
									
									for(int k=0;k<listDetailFactureServiceTransportCalculerTTC.size();k++)
									{
										DetailFactureServiceTransport detailFactureServiceTransport=listDetailFactureServiceTransportCalculerTTC.get(k);
										total=total.add(detailFactureServiceTransport.getMontantTTC());
									}
									
									
									
									
								}
								
							}
							
						}
						
					}else if(chckbxAutresVente.isSelected()==true)
					{
						
						for(int i=0;i<listFactureAutresVente.size();i++)
						{
							FactureAutresVente factureAutresVente=listFactureAutresVente.get(i);
							if(mapRegle.containsKey(factureAutresVente.getNumFacture()))
							{
								listFactureAutresVenteRegler=factureAutresVentedao.findByNumFacture(factureAutresVente.getNumFacture(), factureAutresVente.getDepot());
								for(int j=0;j<listFactureAutresVenteRegler.size();j++)
								{
									
									listDetailFactureAutresVenteCalculerTTC=detailFactureAutresVenteDAO.listeDetailFacturePFByFacture(listFactureAutresVenteRegler.get(j).getId());
									
									for(int k=0;k<listDetailFactureAutresVenteCalculerTTC.size();k++)
									{
										DetailFactureAutresVente detailFactureAutresVente=listDetailFactureAutresVenteCalculerTTC.get(k);
										total=total.add(detailFactureAutresVente.getMontantTTC());
									}
									
									
									
									
								}
								
							}
							
						}
						
					}
					
					
					
					else
					{
						
						for(int i=0;i<listFacturePF.size();i++)
						{
							FacturePF facturepf=listFacturePF.get(i);
							if(mapRegle.containsKey(facturepf.getNumFacture()))
							{
								listFacturePFRegler=facturepfdao.findByNumFacture(facturepf.getNumFacture(), facturepf.getDepot());
								for(int j=0;j<listFacturePFRegler.size();j++)
								{
									for(int k=0;k<listFacturePFRegler.get(j).getDetailFacturePF().size();k++)
									{
										DetailFacturePF detailFacturePF=listFacturePFRegler.get(j).getDetailFacturePF().get(k);
										total=total.add(detailFacturePF.getMontantTTC());
									}
									
								}
								
							}
							
						}
						
					}
					
					
					
			
					
					txttotalfacture.setText(total+"");
						
						
				}
	    		
	    		
	    	}
	    });
	    btnNewButton.setBounds(1180, 469, 89, 25);
	    add(btnNewButton);
	    
	    JLabel lblTotalRegl = new JLabel("Total  \u00E0 Regl\u00E9 :");
	    lblTotalRegl.setFont(new Font("Tahoma", Font.BOLD, 11));
	    lblTotalRegl.setBounds(875, 470, 89, 24);
	    add(lblTotalRegl);
	    
	    txttotalfacture = new JTextField();
	    txttotalfacture.setBounds(974, 469, 145, 26);
	    add(txttotalfacture);
	    txttotalfacture.setEditable(false);
	    txttotalfacture.setColumns(10);
	    util.Utils.copycoller(txttotalfacture);
	    txtDh = new JTextField();
	    txtDh.setEditable(false);
	    txtDh.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
	    txtDh.setText("DH");
	    txtDh.setColumns(10);
	    txtDh.setBounds(1129, 469, 41, 25);
	    add(txtDh);
	    
	    JLayeredPane layeredPane_1 = new JLayeredPane();
	    layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_1.setBounds(67, 66, 1202, 108);
	    add(layeredPane_1);
	    
	    JLabel lblNumFacture = new JLabel("Num Facture :");
	    lblNumFacture.setBounds(10, 14, 97, 24);
	    layeredPane_1.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumfacture = new JTextField();
	    txtparnumfacture.setBounds(117, 14, 139, 26);
	    layeredPane_1.add(txtparnumfacture);
	    util.Utils.copycoller(txtparnumfacture);
	    txtparnumfacture.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		
	    		if(e.getKeyCode()==e.VK_ENTER)
				{
					/*
					 * 
					 * 
					 * if(!txtparnumfacture.getText().equals("")) { Depot
					 * depot=mapDepot.get(comboDepot.getSelectedItem());
					 * ChangerNumChequefacture=facturepfdao.findByNumFacture(txtparnumfacture.
					 * getText(), depot);
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * }
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 */
	    			
				}
	    		
	    	}
	    	
	    	
	    });
	    txtparnumfacture.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(266, 14, 97, 24);
	    layeredPane_1.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Date Facture :");
	    lblDateFacture.setBounds(485, 16, 97, 24);
	    layeredPane_1.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(592, 14, 151, 26);
	     layeredPane_1.add(pardateChooser);
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
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.setBounds(487, 73, 107, 24);
	    layeredPane_1.add(btnAfficher);
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		detailFacturePfdao.ViderSession();
	    		
	    		
	    	if(comboClientPf.getSelectedItem().equals("")
	    			&& txtparnumfacture.getText().equals("") 
	    			&& pardateChooser.getDate()==null
	    			&& comboparfournisseur.getSelectedItem().equals("") && comboDepot.getSelectedItem().equals(""))
	    	{
	    		
	    		initialiser();
	    		initialiserFacture();
	    		
	    		InitialiseTableauFacture();
	    		
	    	}else
	    	{
	    		
	    		ClientPF clientpf=mapClientPFparCode.get(comboClientPf.getSelectedItem());
	    		Client fournisseur=mapparfournisseur.get(comboparfournisseur.getSelectedItem());
	    		Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	    		
	    		initialiserFacture();
	    		//initialiser();
	    		
	    		
	    		if(chckbxTransport.isSelected()==true)
	    			
	    		{
	    			listFactureServiceTransport.clear();
	    	    	
	    			  listFactureServiceTransportCharger=factureServiceTransportDAO.findByNumFcatureClientDateFactureFournisseurEtatRegle(txtparnumfacture.getText(),clientpf, pardateChooser.getDate(), fournisseur, Constantes.ETAT_NON_REGLE,depot);
	    			
	    			for(int i=0;i<listFactureServiceTransportCharger.size();i++)
	    			{
	    				
	    				FactureServiceTransport facture=listFactureServiceTransportCharger.get(i);
	    				
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
	    			
	    			
	    			
	    			afficher_tableFactureServiceTransport(listFactureServiceTransport);
	    			
	    			
	    			
	    			
	    			
	    			
	    		}else if(chckbxAutresVente.isSelected()==true)
	    		{
	    			
	    		
		    			listFactureAutresVente.clear();
		    	    	
		    			  listFactureAutresVenteCharger=factureAutresVentedao.findByNumFcatureClientDateFactureFournisseurEtatRegle(txtparnumfacture.getText(),clientpf, pardateChooser.getDate(), fournisseur, Constantes.ETAT_NON_REGLE,depot);
		    			
		    			for(int i=0;i<listFactureAutresVenteCharger.size();i++)
		    			{
		    				
		    				FactureAutresVente facture=listFactureAutresVenteCharger.get(i);
		    				
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
		    			
		    			
		    			
		    			afficher_tableFactureAutresVente (listFactureAutresVente);
		    			
		    			
		    			
		    			
		    			
		    			
		    		
	    			
	    		}
	    		
	    		
	    		else
	    		{
	    			
	    			listFacturePF.clear();
	    	    	
	    			  listFacturePFCharger=facturepfdao.findByNumFcatureClientDateFactureFournisseurEtatRegle(txtparnumfacture.getText(),clientpf, pardateChooser.getDate(), fournisseur, Constantes.ETAT_NON_REGLE,depot);
	    			
	    			for(int i=0;i<listFacturePFCharger.size();i++)
	    			{
	    				
	    				FacturePF facture=listFacturePFCharger.get(i);
	    				
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
	    			
	    			
	    			
	    					afficher_tableFacturePF(listFacturePF);
	    			
	    		}
	    			
	    	
	    	}
	    				
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setIcon(imgChercher);
	    
	    JLabel label = new JLabel("Fournisseur :");
	    label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label.setBounds(753, 14, 79, 24);
	    layeredPane_1.add(label);
	    
	     comboparfournisseur = new JComboBox();
	    comboparfournisseur.setSelectedIndex(-1);
	    comboparfournisseur.setBounds(830, 15, 149, 24);
	    layeredPane_1.add(comboparfournisseur);
	    AutoCompleteDecorator.decorate(comboparfournisseur);
	    JXTitledSeparator titledSeparator = new JXTitledSeparator();
	    GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator.getLayout();
	    gridBagLayout_1.rowWeights = new double[]{0.0};
	    gridBagLayout_1.rowHeights = new int[]{0};
	    gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
	    gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
	    titledSeparator.setTitle("Informations Bon Livraison");
	    titledSeparator.setBounds(67, 202, 1137, 30);
	    add(titledSeparator);
	   
	    listparFournisseur=fournisseurdao.findAll();
		  int k=0;
	    	 comboparfournisseur.addItem("");
	    	 
	    	  comboClientPf = new JComboBox();
	    	 comboClientPf.setSelectedIndex(-1);
	    	 comboClientPf.setBounds(321, 17, 149, 24);
	    	 layeredPane_1.add(comboClientPf);
	    	 AutoCompleteDecorator.decorate(comboClientPf);
	    	 comboClientPf.addItem("");
	    	 
	    	 JLabel lblDepot = new JLabel("Depot :");
	    	 lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    	 lblDepot.setBounds(988, 14, 97, 24);
	    	 layeredPane_1.add(lblDepot);
	    	 
	    	  comboDepot = new JComboBox();
	    	 comboDepot.setSelectedIndex(-1);
	    	 comboDepot.setBounds(1043, 17, 149, 24);
	    	 layeredPane_1.add(comboDepot);
	    	 
	    	 JButton button = new JButton("Initialiser");
	    	 button.addActionListener(new ActionListener() {
	    	 	public void actionPerformed(ActionEvent arg0) {
	    	 		
	    	 		txtparnumfacture.setText("");
	    	 		comboClientPf.setSelectedIndex(-1);
	    	 	pardateChooser.setCalendar(null);
	    	 		comboparfournisseur.setSelectedIndex(-1);
	    	 		comboDepot.setSelectedIndex(-1);
	    	 		
	    	 	}
	    	 });
	    	 button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    	 button.setBounds(611, 74, 106, 23);
	    	 layeredPane_1.add(button);
	    	 
	    	  chckbxTransport = new JCheckBox("Transport");
	    	  chckbxTransport.addActionListener(new ActionListener() {
	    	  	public void actionPerformed(ActionEvent arg0) {
	    	  		
	    	  		if(chckbxTransport.isSelected()==true)
	    	  		{
	    	  			
	    	  		chckbxAutresVente.setSelected(false);	
	    	  			
	    	  			
	    	  		}
	    	  		
	    	  		
	    	  		
	    	  		
	    	  	}
	    	  });
	    	 chckbxTransport.setFont(new Font("Tahoma", Font.BOLD, 12));
	    	 chckbxTransport.setBounds(10, 48, 97, 23);
	    	 layeredPane_1.add(chckbxTransport);
	    	 
	    	  chckbxAutresVente = new JCheckBox("Autres vente");
	    	  chckbxAutresVente.addActionListener(new ActionListener() {
	    	  	public void actionPerformed(ActionEvent e) {
	    	  		
	    	  		if(chckbxAutresVente.isSelected()==true)
	    	  		{
	    	  			
	    	  			chckbxTransport.setSelected(false);	
	    	  			
	    	  			
	    	  		}
	    	  		
	    	  	}
	    	  });
	    	 chckbxAutresVente.setFont(new Font("Tahoma", Font.BOLD, 12));
	    	 chckbxAutresVente.setBounds(10, 74, 131, 23);
	    	 layeredPane_1.add(chckbxAutresVente);
	    	 
	    	  chckbxMultimodeReglement = new JCheckBox("MultiMode Reglement");
	    	 chckbxMultimodeReglement.setFont(new Font("Tahoma", Font.BOLD, 12));
	    	 chckbxMultimodeReglement.setBounds(63, 476, 185, 23);
	    	 add(chckbxMultimodeReglement);
	    	
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 listClientPFP.forEach(e->{
	    		 comboClientPf.addItem(e.getNom());
	         		mapClientPFparCode.put(e.getNom(), e); 
	    	 });
	  
	    	
	    	listparFournisseur.forEach(e->{
comboparfournisseur.addItem(e.getNom());
	    		
	    		mapparfournisseur.put(e.getNom(), e);
	    	});
	    	
	    	
	    	
			  if(utilisateur.getLogin().equals("admin"))
			  {
				  listDepot=depotdao.findAll();
				  int l=0;
				  comboDepot.addItem("");
			     	while (l<listDepot.size())
			     	{
			     		Depot depot=listDepot.get(l);
			     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
			     		if(magasin!=null)
			     		{
			     			if(depot.getId()!=magasin.getDepot().getId())
				     		{
			     				comboDepot.addItem(depot.getLibelle());
					     		
					     		mapDepot.put(depot.getLibelle(), depot);
					     	
					     		
				     			
				     		}
			     		}else
			     		{
			     			comboDepot.addItem(depot.getLibelle());
				     		
				     		mapDepot.put(depot.getLibelle(), depot);
				     	
				     		
			     		}
			     		l++;
			     		
			     	}
			      
			  }else
			  {
				  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
				  if(depot!=null)
				  {
					  comboDepot.addItem(depot.getLibelle());
					
			     		mapDepot.put(depot.getLibelle(), depot);
				  }
			  }
	    	
	    	
	    	
	    	
	    	
	    	
		
		
		}
	
	
	
	

boolean remplirMapRegler(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean regle=(boolean) table.getValueAt(j, 6);
		if(regle==true ){
			
			mapRegle.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 6).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}
	

	
	void initialiserFacture()
	{
		txttotalfacture.setText("");
	
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		
		
	}

	void initialiser()
	{
		//chckbxTransport.setSelected(false);
		txttotalfacture.setText("");
		rdbtnChque.setSelected(false);
		rdbtnespece.setSelected(false);
		txtespece.setText("");
	
		txtcheque.setText("");
	chckbxMultimodeReglement.setSelected(false);
	chckbxChque.setSelected(false);
	chckbxTraite.setSelected(false);
	chckbxVirement.setSelected(false);
	chkboxespece.setSelected(false);
	chckbxCrdit.setSelected(false);
		txtnumcheque.setText("");
		txtCredit.setText("");
		txtvirement.setText("");
		rdbtnVirement.setSelected(false);
		
		rdbtnTraite.setSelected(false);
		txtTraite.setText("");
		
		txtnumtraite.setText("");
	txtVersement.setText("");
	txtnumVersement.setText("");
	chckbxVersement.setSelected(false);
	listFacturePF.clear();
		ClientPF clientpf=mapClientPFparCode.get(comboClientPf.getSelectedItem());
		Client fournisseur=mapparfournisseur.get(comboparfournisseur.getSelectedItem());
		Depot depot=mapDepot.get(comboDepot.getSelectedItem());
		
		listFacturePFCharger=facturepfdao.findByNumFcatureClientDateFactureFournisseurEtatRegle(txtparnumfacture.getText(),clientpf, pardateChooser.getDate(), fournisseur, Constantes.ETAT_NON_REGLE,depot);
			
		for(int i=0;i<listFacturePFCharger.size();i++)
		{
			
			FacturePF facture=listFacturePFCharger.get(i);
			
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
		
		
		
		
		
		afficher_tableFacturePF(listFacturePF);
		
	}
	
	
	
	
	

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Client","Fournisseur","Etat", "Montant TTC", "Regl\u00E9"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
		table.getColumnModel().getColumn(6).setPreferredWidth(82);
	
	
}
	
	
	

	
	
	void afficher_tableFacturePF(List<FacturePF> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Client","Fournisseur","Etat", "Montant TTC", "Regl\u00E9"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
		
		while(i<listFacture.size())
		{
			MontantTTC=BigDecimal.ZERO;
		FacturePF facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
		listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByNumFacture(facturepf.getNumFacture(), facturepf.getMagasin());
		for(int j=0;j<listDetailFacturePF.size();j++)
		{
			
			MontantTTC=MontantTTC.add(listDetailFacturePF.get(j).getMontantTTC());
		}
	
		
			Object []ligne={facturepf.getNumFacture(),datefacture,facturepf.getClientPF().getNom(),facturepf.getFournisseur().getNom(),facturepf.getEtat(),MontantTTC,false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tableFactureServiceTransport(List<FactureServiceTransport> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Client","Fournisseur","Etat", "Montant TTC", "Regl\u00E9"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
		
		while(i<listFacture.size())
		{
			MontantTTC=BigDecimal.ZERO;
		FactureServiceTransport facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
		listDetailFactureServiceTransport=detailFactureServiceTransportDAO.listeDetailFactureServiceTransportByNumFacture(facturepf.getNumFacture(), facturepf.getMagasin());
		for(int j=0;j<listDetailFactureServiceTransport.size();j++)
		{
			
			MontantTTC=MontantTTC.add(listDetailFactureServiceTransport.get(j).getMontantTTC());
		}
	
		
			Object []ligne={facturepf.getNumFacture(),datefacture,facturepf.getClientPF().getNom(),facturepf.getFournisseur().getNom(),facturepf.getEtat(),MontantTTC,false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	
	
	
	void afficher_tableFactureAutresVente(List<FactureAutresVente> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Client","Fournisseur","Etat", "Montant TTC", "Regl\u00E9"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
		
		while(i<listFacture.size())
		{
			MontantTTC=BigDecimal.ZERO;
		FactureAutresVente factureAutresVente=listFacture.get(i);
		
		Date datefacture=factureAutresVente.getDateFacture();
		listDetailFactureAutresVente=detailFactureAutresVenteDAO.listeDetailFacturePFByNumFacture(factureAutresVente.getNumFacture(), factureAutresVente.getDepot());
		for(int j=0;j<listDetailFactureAutresVente.size();j++)
		{
			
			MontantTTC=MontantTTC.add(listDetailFactureAutresVente.get(j).getMontantTTC());
		}
	
		
			Object []ligne={factureAutresVente.getNumFacture(),datefacture,factureAutresVente.getClientPF().getNom(),factureAutresVente.getFournisseur().getNom(),factureAutresVente.getEtat(),MontantTTC,false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	}


