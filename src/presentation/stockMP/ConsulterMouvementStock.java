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

import org.hibernate.Session;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.ExporterTableVersExcel;
import util.HibernateUtil;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailMouvementStockDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
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
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailMouvementStockDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FacturePF;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
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


public class ConsulterMouvementStock extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleDetailMouvementStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableDetailMouvement = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer =new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherTmp =new ArrayList<DetailMouvementStock>();
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, MatierePremier> mapMP= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 JComboBox combomp = new JComboBox();
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailMouvementStockDAO detailMouvementStockDAO ;

private DetailTransferMPDAO detailTransferStockMPDAO;
private TransferStockMPDAO transferStockMPDAO;
	private JTextField txtlibelle=new JTextField();
	
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();
	 JComboBox combodepot = new JComboBox();
	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	private MatierePremiereDAO MatierPremiereDAO;
	private JTextField txtcodemp;
	String titre="";
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterMouvementStock() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1485, 1137);
      
	
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
         	productionDAO=new ProductionDAOImpl();
         	mouvementStockGlobaleDAO=new MouvementStockGlobalDAOImpl();
         	MatierPremiereDAO=new MatierePremierDAOImpl();
         	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
         	transferStockMPDAO=new TransferStockMPDAOImpl();
         listMP=MatierPremiereDAO.findAll();
         detailMouvementStockDAO=new DetailMouvementStockDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
       tableDetailMouvement.setEditable(false);
       tableDetailMouvement.setAutoStartEditOnKeyStroke(false);
      
       
        
       tableDetailMouvement.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Date","Matiere Premiere", "Initial", "Production","Dechet","Offre production", "Production Service","Dechet Service","Offre Service","Transfert","Achat","Vente","Avoir", "Stock Final"
       	}
       ));
       tableDetailMouvement.getColumnModel().getColumn(0).setPreferredWidth(102);
       tableDetailMouvement.getColumnModel().getColumn(1).setPreferredWidth(260);
       tableDetailMouvement.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableDetailMouvement.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableDetailMouvement.getColumnModel().getColumn(4).setPreferredWidth(123);
       tableDetailMouvement.getColumnModel().getColumn(5).setPreferredWidth(118);
       tableDetailMouvement.getColumnModel().getColumn(6).setPreferredWidth(132);
       tableDetailMouvement.getColumnModel().getColumn(7).setPreferredWidth(92);
      
       
       tableDetailMouvement.setShowVerticalLines(false);
       tableDetailMouvement.setSelectionBackground(new Color(51, 204, 255));
       tableDetailMouvement.setRowHeightEnabled(true);
       tableDetailMouvement.setBackground(new Color(255, 255, 255));
       tableDetailMouvement.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableDetailMouvement.setColumnControlVisible(true);
       tableDetailMouvement.setForeground(Color.BLACK);
       tableDetailMouvement.setGridColor(new Color(0, 0, 255));
       tableDetailMouvement.setBounds(2, 27, 411, 198);
       tableDetailMouvement.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableDetailMouvement);
				  		     	scrollPane.setBounds(10, 195, 1465, 587);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Detail Mouvement de Stock");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		      
		     	
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 if(listMouvementStockMPAfficherTmp.size()!=0)
				 {
						Map parameters = new HashMap();
						/*parameters.put("datemouvement", listMouvementStockMPAfficherTmp.get(0).getMouvementStockGlobal().getDateMouvement());
						parameters.put("depot", listMouvementStockMPAfficherTmp.get(0).getMouvementStockGlobal().getDepot().getLibelle());	
						parameters.put("magasin",listMouvementStockMPAfficherTmp.get(0).getMouvementStockGlobal().getMagasin().getLibelle());*/	
						
						parameters.put("titre", titre);
						JasperUtils.imprimerMouvementStock(listMouvementStockMPAfficherTmp,parameters);
						
				 }else if(listMouvementStockMPAfficher.size()!=0)
				 {

						Map parameters = new HashMap();
						/*parameters.put("datemouvement", listMouvementStockMPAfficher.get(0).getMouvementStockGlobal().getDateMouvement());
						parameters.put("depot", listMouvementStockMPAfficher.get(0).getMouvementStockGlobal().getDepot().getLibelle());	
						parameters.put("magasin",listMouvementStockMPAfficher.get(0).getMouvementStockGlobal().getMagasin().getLibelle());	*/
						parameters.put("titre", titre);
						
						JasperUtils.imprimerMouvementStock(listMouvementStockMPAfficher,parameters);
						
				  
				 }
				 
				 
				 
				 
				 else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Mouvement Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
					
				
				
			}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(698, 793, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter le Mouvement de Stock :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		 detailTransferStockMPDAO.ViderSession();
		    		listDetailTransferStockMP.clear();
		    		listDetailTransferStockMPGroupebyMP.clear();
		    		listDetailTransferStockMPBytypetransfer.clear();
		    		
		    		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
		    		listMouvementStockMP.clear();
		    		boolean trouve=false;
		    		MatierePremier mp=mapMP.get(combomp.getSelectedItem());
		    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
		    		
		    		if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
	    				return;
		    		}else
		    		{
		    			if(magasin!=null)
			    		{
			    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
				    		{
				    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
				    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
				    			
				    		if(!d1.equals(d2))
				    		{
				    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
				    			{
				    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
				    				return;
				    			}
				    			
				    		}
				    		
				    		if(mp!=null)
				    		{
				    			titre="Mouvement de Stock de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
				    		}else
				    		{
				    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
				    		}
				    		
				    		}
				    		
				    		if(dateChooserdebut.getDate()==null )
				    		{
				    			dateChooserfin.setCalendar(null);
				    			titre="Mouvement de Stock  magasin : "+magasin.getLibelle();
				    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
				    		{
				    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
				    			titre="Mouvement de Stock de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
				    			
				    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
				    		{
				    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
				    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
				    		}
				    		
				    		
				    		listDetailTransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPOrderByDateTransfer(magasin);
				    		listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByDateTransferByMP(magasin);
				    		listDetailTransferStockMPBytypetransfer=detailTransferStockMPDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
				    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.ETAT_TRANSFER_STOCK_CHARGE,ETAT_TRANSFER_STOCK_CHARGE_SUPP,ETAT_TRANSFER_STOCK_VENTE,ETAT_TRANSFER_STOCK_AVOIR,ETAT_TRANSFER_STOCK_SERVICE,ETAT_TRANSFER_STOCK_SORTIE_PF,ETAT_TRANSFER_STOCK_SORTIE_MP_PF,ETAT_TRANSFER_STOCK_FABRIQUE};
				    		BigDecimal achat=BigDecimal.ZERO;
				    		
				    		BigDecimal initial=BigDecimal.ZERO;
				    		BigDecimal production=BigDecimal.ZERO;
				    		BigDecimal avoir=BigDecimal.ZERO;
				    		
				    		BigDecimal stockfinal=BigDecimal.ZERO;
				    		BigDecimal vente=BigDecimal.ZERO;
				    		BigDecimal service=BigDecimal.ZERO;
				    		BigDecimal dechet=BigDecimal.ZERO;
				    		BigDecimal dechetService=BigDecimal.ZERO;
				    		BigDecimal offreService=BigDecimal.ZERO;
				    		BigDecimal offreProduction=BigDecimal.ZERO;
				    		BigDecimal transfert=BigDecimal.ZERO;
				    		BigDecimal fabrique=BigDecimal.ZERO;
				    		
				    		for(int i=0;i<listDetailTransferStockMPGroupebyMP.size();i++)
				    		{
				    			achat=new BigDecimal(0);
				    			
				    			initial=new BigDecimal(0);
				    			production=new BigDecimal(0);
				    			dechet=new BigDecimal(0);
				    			dechetService=new BigDecimal(0);
				    			service=new BigDecimal(0);
				    			vente=new BigDecimal(0);
				    			avoir=BigDecimal.ZERO;
				    			stockfinal=new BigDecimal(0);
				    			offreService=new BigDecimal(0);
				    			offreProduction=new BigDecimal(0);
				    			transfert=new BigDecimal(0);
				    			fabrique=new BigDecimal(0);
				    			
				    			for(int k=0;k<typetransfer.length;k++)
				    			{
				    				
				    			for(int j=0;j<listDetailTransferStockMP.size();j++)
				    			{
				    				
				    				//
				    			if(listDetailTransferStockMPGroupebyMP.get(i).getTransferStockMP().getDateTransfer().equals(listDetailTransferStockMP.get(j).getTransferStockMP().getDateTransfer()) &&listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom().equals(listDetailTransferStockMP.get(j).getMatierePremier().getNom()) && listDetailTransferStockMP.get(j).getTransferStockMP().getStatut().equals(typetransfer[k]))	
				    			{
				    			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
				    			{
				    				
	                           if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
	                               {
	                        	   achat=achat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
	                                }else
	                                {
	                                	achat=achat.add(listDetailTransferStockMP.get(j).getQuantite());
	                                }
				    				
				    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_FABRIQUE))
				    			{
				    				
			                           if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
			                               {
			                        	   fabrique=fabrique.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
			                                }else
			                                {
			                                	fabrique=fabrique.add(listDetailTransferStockMP.get(j).getQuantite());
			                                }
						    				
						    			}
				    			
				    			
				    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
				    			{
				    				   if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		                               {
				    						initial=initial.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
		                               }else
		                               {
		                            		initial=initial.add(listDetailTransferStockMP.get(j).getQuantite()); 
		                               }
				    			
				    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE) || typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE_SUPP))
				    			{
				    				 if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		                               {
				    					 production=production.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
				    					 
				    					 if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE))
					    				 {
					    					
					    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
					    					{
					    						
					    						dechet=dechet.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantiteDechet()).setScale(0, RoundingMode.CEILING);
					    					}
					    					
					    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
					    					{
					    						
					    						offreProduction=offreProduction.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantiteOffre()).setScale(0, RoundingMode.CEILING);
					    					}
					    					
					    					 
					    					 
					    					 
					    				 }
				    					 
				    					 
		                               }else
		                               {
		                            	  // System.out.println("listDetailTransferStockMP.get(j).getQuantite() :"+listDetailTransferStockMP.get(j).getQuantite());
		                            	   production=production.add(listDetailTransferStockMP.get(j).getQuantite()); 
		                            	   if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE))
						    				 {
						    					
						    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
						    					{
						    						
						    						dechet=dechet.add(listDetailTransferStockMP.get(j).getQuantiteDechet());
						    					}
						    					
						    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
						    					{
						    						
						    						offreProduction=offreProduction.add(listDetailTransferStockMP.get(j).getQuantiteOffre());
						    					}
						    					 
						    				 }
		                               }
				    				 
				    				
				    				 
				    			}
				    			
				    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
				    			{
				    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		                               {
				    					vente=vente.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
		                               }else
		                               {
		                            	   vente=vente.add(listDetailTransferStockMP.get(j).getQuantite());
		                               }
				    					
				    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
				    			{
				    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		                               {
				    					avoir=avoir.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
		                               }else
		                               {
		                            	   avoir=avoir.add(listDetailTransferStockMP.get(j).getQuantite());
		                               }
				    					
				    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SERVICE))
				    			{
				    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		                               {
				    					service=service.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));	
				    					
					    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
					    					{
					    						
					    						dechetService=dechetService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantiteDechet()).setScale(0, RoundingMode.CEILING);
					    					}
					    					
					    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
					    					{
					    						
					    						offreService=offreService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantiteOffre()).setScale(0, RoundingMode.CEILING);
					    					}
					    					 
					    				
		                               }else
		                               {
		                            	   service=service.add(listDetailTransferStockMP.get(j).getQuantite());	
		                            	   
						    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
						    					{
						    						
						    						dechetService=dechetService.add(listDetailTransferStockMP.get(j).getQuantiteDechet());
						    					}
						    					
						    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
						    					{
						    						
						    						offreService=offreService.add(listDetailTransferStockMP.get(j).getQuantiteOffre());
						    					}
						    					 
		                               }
				    				
				    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_PF))
				    			{
				    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		                               {
				    					transfert=transfert.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));	
				    					 
					    				
		                               }else
		                               {
		                            	   transfert=transfert.add(listDetailTransferStockMP.get(j).getQuantite());	
		                            	  	 
		                               }
				    				
				    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_MP_PF))
				    			{
				    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		                               {
				    					transfert=transfert.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING));	
				    					 
					    				
		                               }else
		                               {
		                            	   transfert=transfert.add(listDetailTransferStockMP.get(j).getQuantite());	
		                            	  	 
		                               }
				    				
				    			}
				    				
				    				
				    			}
				    			
				    			
				    			}
				    			}
				    			
				    			if(listMouvementStockMP.size()!=0)
				    			{
				    				for(int d=0;d<listMouvementStockMP.size();d++)
				    				{
				    					
				    					if(listMouvementStockMP.get(d).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier()))
				    					{
				    						initial=listMouvementStockMP.get(d).getStockFinaldb();
				    						trouve=true;
				    						
				    					}
				    				}
				    				
				    				
				    			}
				    			if(trouve==false)
				    			{
				    				for(int l=0;l<listDetailTransferStockMPBytypetransfer.size();l++)
				    				{
				    	if(listDetailTransferStockMPBytypetransfer.get(l).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier()))
				    	{
				    		initial=listDetailTransferStockMPBytypetransfer.get(l).getQuantite();
				    		
				    	}
				    	
				    				}
				    				
				    			}
				    			
				    			//stockfinal=(initial.add(achat)).subtract(vente.add(production).add(avoir).add(service).add(dechet).add(dechetService));
				    			DetailMouvementStock mouvementstockMP=new DetailMouvementStock();
				    			mouvementstockMP.setDateCreation(listDetailTransferStockMPGroupebyMP.get(i).getTransferStockMP().getDateTransfer());
				    			mouvementstockMP.setProduction(production);
				    			mouvementstockMP.setDechet(dechet);
				    			mouvementstockMP.setOffreProduction(offreProduction);
				    			mouvementstockMP.setVente(vente);
				    			mouvementstockMP.setInitial(initial);
				    			mouvementstockMP.setAchat(achat);
				    			mouvementstockMP.setAvoir(avoir);
				    			mouvementstockMP.setService(service);
				    			mouvementstockMP.setDechetService(dechetService);
				    			mouvementstockMP.setOffreService(offreService);
				    			mouvementstockMP.setTransfert(transfert);
				    			mouvementstockMP.setFabriquer(fabrique);
				    			mouvementstockMP.setMatierePremier(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier());
				    			mouvementstockMP.setStockFinaldb((mouvementstockMP.getInitial().add(mouvementstockMP.getAchat()).add(mouvementstockMP.getFabriquer())).subtract(mouvementstockMP.getVente().add(mouvementstockMP.getProduction().add(mouvementstockMP.getAvoir().add(mouvementstockMP.getService()).add(mouvementstockMP.getTransfert()).add(mouvementstockMP.getOffreService()).add(mouvementstockMP.getOffreProduction())))));
				    			listMouvementStockMP.add(mouvementstockMP);
				    			
				    		}
				    		
				    		
				    		// detailtransfer entre deux date et par article
				    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp!=null)
				    		{
				    			listMouvementStockMPAfficher.clear();
				    			listMouvementStockMPAfficherTmp.clear();
				    			
				    		
				    		for(int i=0;i<listMouvementStockMP.size();i++)	
				    		{
				    			String ddebut=sdf.format(dateChooserdebut.getDate());
				    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
				    			
				    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
				    				
				    			{
				    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()))
				    			{
				    				listMouvementStockMPAfficher.add(listMouvementStockMP.get(i));	
				    			}
				    				
				    			
				    				
				    			}
				    			
				    		}
				    			
				    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
				    		{
				    			
				    			String dfin=sdf.format(dateChooserfin.getDate());
				    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
				    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
				    			{
				    			if(listMouvementStockMPAfficher.get(j).getMatierePremier().getNom().equals(mp.getNom()))
				    			{
				    				listMouvementStockMPAfficherTmp.add(listMouvementStockMPAfficher.get(j));
				    			}
				    				
				    			
				    				
				    			}
				    			
				    		}
				    		
				    		
				    		if(listMouvementStockMPAfficherTmp.size()!=0)
				    		{
				    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
				    			
				    		}else
				    		{
				    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficher);
				    		}
				    		
				    			
				    		// detailtransfer entre deux date (date fin null) et par article 
				    			
				    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
				    		{
				    			listMouvementStockMPAfficherTmp.clear();
				    			String d1=sdf.format(dateChooserdebut.getDate());
				    		
				    			
				    			for(int i=0;i<listMouvementStockMP.size();i++)	
					    		{
				    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
					    			if(ddbut.equals(d1) && listMouvementStockMP.get(i).getMatierePremier().equals(mp) )
					    			{
					    			
					    				listMouvementStockMPAfficherTmp.add(listMouvementStockMP.get(i));
					    			
					    				
					    			}
					    			
					    		}
				    			
				    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
				    			
				    			// detailtransfer entre deux date (date fin null)  
				    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
				    		{
				    			
				    			listMouvementStockMPAfficherTmp.clear();
			                      
			                      String d1=sdf.format(dateChooserdebut.getDate());
			  	    			
				    			
				    			for(int i=0;i<listMouvementStockMP.size();i++)	
					    		{
				    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
					    			if(ddbut.equals(d1) )
					    			{
					    			
					    				listMouvementStockMPAfficherTmp.add(listMouvementStockMP.get(i));
					    			
					    				
					    			}
					    			
					    		}
				    			
				    			
				    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
				    			
				    			// detailtransfer par article
				    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && mp!=null)
				    		{
				    			listMouvementStockMPAfficherTmp.clear();
				             
				               
				               
				    			for(int i=0;i<listMouvementStockMP.size();i++)	
					    		{
					    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()) )
					    			{
					    			
					    				listMouvementStockMPAfficherTmp.add(listMouvementStockMP.get(i));
					    			
					    				
					    			}
					    			
					    		}
				    			
				    			
				    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
				    			
				    			
				    			// detailtransfer entre deux date  
				    			
				    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp==null)
				    		{
				    			listMouvementStockMPAfficher.clear();
				    			listMouvementStockMPAfficherTmp.clear();
				    		
				    		for(int i=0;i<listMouvementStockMP.size();i++)	
				    		{
				    			String ddebut=sdf.format(dateChooserdebut.getDate());
				    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
				    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
				    			{
				    			
				    				listMouvementStockMPAfficher.add(listMouvementStockMP.get(i));
				    			
				    				
				    			}
				    			
				    		}
				    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
				    		{
				    			
				    			String dfin=sdf.format(dateChooserfin.getDate());
				    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
				    			
				    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
				    			{
				    			
				    				listMouvementStockMPAfficherTmp.add(listMouvementStockMPAfficher.get(j));
				    			
				    				
				    			}
				    			
				    		}
				    		
				    		if(listMouvementStockMPAfficherTmp.size()!=0)
				    		{
				    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
				    			
				    		}else
				    		{
				    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficher);
				    		}
				    		
				    		}
				    			
			    		}else
			    		{
			    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
			    			return;
			    		}
		    		}
		    
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(534, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(62, 57, 1384, 51);
	    add(layeredPane);
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(112, 9, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(296, 11, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(399, 11, 169, 26);
	    layeredPane.add(dateChooserfin);
	    
	     combomp = new JComboBox();
	     combomp.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		
	 			if(!combomp.getSelectedItem().equals(""))
		 		{
		 			MatierePremier mp=mapMP.get(combomp.getSelectedItem());
		 			txtcodemp.setText(mp.getCode());
		 			
		 		  				 			
		 		}else
		 		{
//txtcodemp.setText(" ");
		 			
		 		}
		 	
	 		
	     	}
	     });
	    combomp.setBounds(1131, 11, 218, 27);
	    layeredPane.add(combomp);
	    
	    combomp.addItem("");
	    int i=0;
		while(i<listMP.size())
		{
			MatierePremier mp=listMP.get(i);
			combomp.addItem(mp.getNom());
			mapMP.put(mp.getNom(), mp);
			mapCodeMP.put(mp.getCode(), mp);
			
			
			i++;
			
		}
	    
	    
	    JLabel label_2 = new JLabel("Libelle :");
	    label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_2.setBounds(1086, 11, 45, 26);
	    layeredPane.add(label_2);
	    
	    txtcodemp = new JTextField();
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
				    			 JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				    		
				    			
				    		}
		      				
		      				
		      		}else
		      		{
		      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		      			
		      			
		      		}
     				
		      		}
	     			
	     			
	    	}
	    });
	    txtcodemp.setText("");
	    txtcodemp.setColumns(10);
	    txtcodemp.setBounds(983, 11, 93, 26);
	    layeredPane.add(txtcodemp);
	    
	    JLabel lblCodeMp = new JLabel("Code MP :");
	    lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblCodeMp.setBounds(911, 11, 74, 26);
	    layeredPane.add(lblCodeMp);
	    
	    JLabel lblDepot = new JLabel("Depot  :");
	    lblDepot.setBounds(592, 11, 45, 26);
	    layeredPane.add(lblDepot);
	    lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    
	     combodepot = new JComboBox();
	    combodepot.setBounds(653, 11, 202, 27);
	    layeredPane.add(combodepot);
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
	    		

	     		combomp.setSelectedItem("");
	     		txtcodemp.setText("");
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		combodepot.setSelectedItem("");
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    			String titre="Mouvement Stock MP "+magasin.getLibelle();
		    		String titrefeuille="Mouvement Stock MP ";
		    		ExporterTableVersExcel.tabletoexcel(tableDetailMouvement, titre,titrefeuille);
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    	
	    		
	    	}
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setIcon(imgExcel);
	    btnExporterExcel.setBounds(545, 793, 143, 32);
	    
	    add(btnExporterExcel);
	  
	    if(utilisateur.getLogin().equals("admin"))
		  {
	    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_MP);
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listMagasin.size())
		     	{
		     		Magasin magasin=listMagasin.get(k);
		     		
		     		
			     			combodepot.addItem(magasin.getLibelle());
				     		
				     		mapMagasin.put(magasin.getLibelle(), magasin);
				     	
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
				  int k=0;
			     	 combodepot.addItem("");
			     	while (k<listMagasin.size())
			     	{
			     		Magasin magasin=listMagasin.get(k);
			     		
			     		
				     			combodepot.addItem(magasin.getLibelle());
					     		
					     		mapMagasin.put(magasin.getLibelle(), magasin);
					     	
			     		k++;
			     		
			     	}
				 
			  }
		  }
	    
	    
	    
		
		}
	

	
	void InitialiseTableauDetailMouvementStock()
	{
		modeleDetailMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date","Matiere Premiere", "Initial", "Production","Dechet","Offre production", "Production Service","Dechet Service","Offre Service","Transfert","Achat","Vente","Avoir", "Stock Final"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableDetailMouvement.setModel(modeleDetailMouvementStock);
		 tableDetailMouvement.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableDetailMouvement.getColumnModel().getColumn(1).setPreferredWidth(102);
	       tableDetailMouvement.getColumnModel().getColumn(2).setPreferredWidth(102);
	       tableDetailMouvement.getColumnModel().getColumn(3).setPreferredWidth(91);
	       tableDetailMouvement.getColumnModel().getColumn(4).setPreferredWidth(123);
	       tableDetailMouvement.getColumnModel().getColumn(5).setPreferredWidth(118);
	       tableDetailMouvement.getColumnModel().getColumn(6).setPreferredWidth(132);
	       tableDetailMouvement.getColumnModel().getColumn(7).setPreferredWidth(92);
	       tableDetailMouvement.getColumnModel().getColumn(8).setPreferredWidth(92);
	       tableDetailMouvement.getColumnModel().getColumn(9).setPreferredWidth(92);
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
	
	
	
	void afficher_tableDetailMouvementStock(List<DetailMouvementStock> listDetailMouvement)
	{
		modeleDetailMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date","Matiere Premiere", "Initial", "Production","Fabriquer", "Dechet","Offre production", "Production Service","Dechet Service","Offre Service","Transfert","Achat","Vente","Avoir", "Stock Final"
				}
			) {
				
			};
		tableDetailMouvement.setModel(modeleDetailMouvementStock);
		int i=0;
		 
		while(i<listDetailMouvement.size())
		{	
		DetailMouvementStock detailMouvementStock=listDetailMouvement.get(i);
		
			
				Object []ligne={detailMouvementStock.getDateCreation(),detailMouvementStock.getMatierePremier().getNom(),detailMouvementStock.getInitial(),detailMouvementStock.getProduction(),detailMouvementStock.getFabriquer(),detailMouvementStock.getDechet() ,detailMouvementStock.getOffreProduction() ,detailMouvementStock.getService(),detailMouvementStock.getDechetService(),detailMouvementStock.getOffreService() ,detailMouvementStock.getTransfert() ,detailMouvementStock.getAchat(),detailMouvementStock.getVente(),detailMouvementStock.getAvoir(),detailMouvementStock.getStockFinaldb()};

				modeleDetailMouvementStock.addRow(ligne);
			
			
			i++;
		}
}
	
	
	void afficher_tableMouvementStock(List<MouvementStockGlobal> listMouvementStock)
	{/*
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
		
		int i=0;
		 
		while(i<listMouvementStock.size())
		{	
			
			
		MouvementStockGlobal mouvementStockGlobal=listMouvementStock.get(i);
		
		
			
			Object []ligne={mouvementStockGlobal.getDateMouvement(),mouvementStockGlobal.getDepot().getLibelle(),mouvementStockGlobal.getMagasin().getLibelle()};

			modeleMouvementStock.addRow(ligne);
			i++;
		}
*/}
	}


