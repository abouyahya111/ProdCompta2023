package presentation.stockPF;

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
import util.ConverterNumberToWords;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
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
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
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


public class ConsulterMouvementStockPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleDetailTransferStockPF;
	

	private JXTable  tableDetailMouvement = new JXTable();
	
	
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();
	
	private Map< String, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private  JDateChooser dateChooserfin = new JDateChooser();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 JComboBox combodepot = new JComboBox();
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	private JTextField txtlibelle=new JTextField();
	
	
	private DepotDAO depotdao;
	private ArticlesDAO articleDAO;
	private FactureAchatDAO factureAchatdao;
	 private FacturePFDAO facturepfdao;
	 private JDateChooser dateChooser = new JDateChooser();

	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private JDateChooser dateChooserdebut;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	private JTextField txtcodearticle;
	private  JComboBox comboarticle = new JComboBox();
	
	String titre="";
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterMouvementStockPF() {
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
            articleDAO=new ArticlesDAOImpl();
            factureAchatdao=new FactureAchatDAOImpl();
            detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
            facturepfdao=new FacturePFDAOImpl();
         depotdao=new DepotDAOImpl();
         listArticle=articleDAO.findAll();
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableDetailMouvement.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Date", "Article","Sous Famille", "Initial", "Entrer Production", "Achat","Transfer Entrer", "Vente","Avoir", "Stock Final"
       	}
       ));
       tableDetailMouvement.getColumnModel().getColumn(0).setPreferredWidth(108);
       tableDetailMouvement.getColumnModel().getColumn(1).setPreferredWidth(102);
       tableDetailMouvement.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableDetailMouvement.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableDetailMouvement.getColumnModel().getColumn(4).setPreferredWidth(123);
       tableDetailMouvement.getColumnModel().getColumn(5).setPreferredWidth(118);
       tableDetailMouvement.getColumnModel().getColumn(6).setPreferredWidth(132);
       tableDetailMouvement.getColumnModel().getColumn(7).setPreferredWidth(92);
       tableDetailMouvement.getColumnModel().getColumn(8).setPreferredWidth(92);
				  		
       tableDetailMouvement.setShowVerticalLines(false);
       tableDetailMouvement.setSelectionBackground(new Color(51, 204, 255));
       tableDetailMouvement.setRowHeightEnabled(true);
       tableDetailMouvement.setBackground(new Color(255, 255, 255));
       tableDetailMouvement.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableDetailMouvement.setColumnControlVisible(true);
       tableDetailMouvement.setForeground(Color.BLACK);
       tableDetailMouvement.setGridColor(new Color(0, 0, 255));
       tableDetailMouvement.setAutoCreateRowSorter(true);
       tableDetailMouvement.setBounds(2, 27, 411, 198);
       tableDetailMouvement.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableDetailMouvement);
				  		     	scrollPane.setBounds(0, 195, 1465, 413);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Detail Mouvement de Stock");
				  		     	titledSeparator.setBounds(0, 154, 1465, 30);
				  		     	add(titledSeparator);
		      
		     	
		      
		     
		     
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 if(listMouvementStockPFAfficherTmp.size()!=0)
				 {
					 

						Map parameters = new HashMap();
						parameters.put("titre", titre);
						
						JasperUtils.imprimerMouvementStockProduitFini(listMouvementStockPFAfficherTmp,parameters);
					 
					 
					 
				 }else if(listMouvementStockPFAfficher.size()!=0)
				 {
					 
					 Map parameters = new HashMap();
						parameters.put("titre", titre);
						
						JasperUtils.imprimerMouvementStockProduitFini(listMouvementStockPFAfficher,parameters); 
					 
					 
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Mouvement Stock PF ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return; 
					 
				 }
					
				
				
			}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(663, 619, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter le Mouvement de Stock Produit Fini  :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(187, 11, 1112, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
	    		listMouvementStockPF.clear();
	    		detailTransferStockPFDAO.ViderSession();
	    		boolean trouve=false;
	    		Articles article=mapArticle.get(comboarticle.getSelectedItem());
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
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
		    		
		    		if(article!=null)
		    		{
		    			titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
		    		}else
		    		{
		    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
		    		}
		    		
		    		}
		    		
		    		if(dateChooserdebut.getDate()==null )
		    		{
		    			dateChooserfin.setCalendar(null);
		    			titre="Mouvement de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle();
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
		    		{
		    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		    			titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
		    		{
		    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
		    		}
		    		
		    		listDetailTransferStockPF=detailTransferStockPFDAO.findAllTransferStockPFOrderByDateTransfer(magasin);
		    		listDetailTransferStockPFGroupebyArticle=detailTransferStockPFDAO.findAllTransferStockPFGroupeByDateTransferByArticle(magasin);
		    		listDetailTransferStockPFBytypetransfer=detailTransferStockPFDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
		    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP,ETAT_TRANSFER_STOCK_ENTRER_PF_MP,ETAT_TRANSFER_STOCK_AVOIR_R,ETAT_TRANSFER_STOCK_AVOIR_CLIENT,ETAT_TRANSFER_STOCK_SORTIE_PF_PF,ETAT_TRANSFER_STOCK_ENTRER_PF_PF};
		    		BigDecimal achat=BigDecimal.ZERO;
		    		BigDecimal vente=BigDecimal.ZERO;
		    		BigDecimal TransferEntrer=BigDecimal.ZERO;
		    		BigDecimal TransferSortie=BigDecimal.ZERO;
		    		BigDecimal avoir=BigDecimal.ZERO;
		    		BigDecimal avoirClient=BigDecimal.ZERO;
		    		BigDecimal initial=BigDecimal.ZERO;
		    		BigDecimal production=BigDecimal.ZERO;
		    		BigDecimal stockfinal=BigDecimal.ZERO;
		    		
		    		
		    		for(int i=0;i<listDetailTransferStockPFGroupebyArticle.size();i++)
		    		{
		    			
		    			achat=new BigDecimal(0);
		    			vente=new BigDecimal(0);
		    			initial=new BigDecimal(0);
		    			production=new BigDecimal(0);
		    			stockfinal=new BigDecimal(0);
		    			avoir=new BigDecimal(0);
		    			avoirClient=new BigDecimal(0);
		    			TransferEntrer=new BigDecimal(0);
		    			for(int k=0;k<typetransfer.length;k++)
		    			{
		    				
		    			for(int j=0;j<listDetailTransferStockPF.size();j++)
		    			{
		    				
		    			if(listDetailTransferStockPFGroupebyArticle.get(i).getDateTransfer().equals(listDetailTransferStockPF.get(j).getDateTransfer() )
		    					&& listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId()== listDetailTransferStockPF.get(j).getArticle().getId() && listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId()== listDetailTransferStockPF.get(j).getSousFamille().getId() && listDetailTransferStockPF.get(j).getTypeTransfer().equals(typetransfer[k]))	
		    			{
		    			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
		    			{
		    				achat=achat.add(listDetailTransferStockPF.get(j).getQuantite());
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
		    			{
		    				
		    				vente=vente.add(listDetailTransferStockPF.get(j).getQuantite());
		    				
		    			}
		    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_PF_PF))
		    			{
		    				
		    				TransferSortie=TransferSortie.add(listDetailTransferStockPF.get(j).getQuantite());
		    				
		    			}
		    			
		    			
		    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
		    			{
		    				
		    				initial=initial.add(listDetailTransferStockPF.get(j).getQuantite());
		    			}else if(typetransfer[k].equals(TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
		    			{
		    				production=production.add(listDetailTransferStockPF.get(j).getQuantite());	
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
		    			{ 
		    				if(listDetailTransferStockPF.get(j).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
		    				{
		    					avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());
		    				}
		    				
		    				
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR_R))
		    			{ 
		    				
		    					avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());
		    				
		    				
		    				
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR_CLIENT))
		    			{ 
		    				
	    					avoirClient=avoirClient.add(listDetailTransferStockPF.get(j).getQuantite());
	    				
	    				
	    				
	    				
	    			}
		    			
		    			
		    			
		    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
		    			{ 
		    				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_MP))
		    			{ 
		    				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_PF))
		    			{ 
		    				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
		    			}
		    						    				
		    			}
		    					    			
		    			}
		    			}
		    			
		    			if(listMouvementStockPF.size()!=0)
		    			{
		    				for(int d=0;d<listMouvementStockPF.size();d++)
		    				{
		    					
		    					if(listMouvementStockPF.get(d).getArticle().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() && listMouvementStockPF.get(d).getSousFamille().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId())
		    					{
		    						initial=listMouvementStockPF.get(d).getStockFinal();
		    						trouve=true;
		    						
		    					}
		    				}
		    			
		    				
		    			}
		    			if(trouve==false)
		    			{
		    				for(int l=0;l<listDetailTransferStockPFBytypetransfer.size();l++)
		    				{
		    	if(listDetailTransferStockPFBytypetransfer.get(l).getArticle().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() && listDetailTransferStockPFBytypetransfer.get(l).getSousFamille().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId())
		    	{
		    		initial=listDetailTransferStockPFBytypetransfer.get(l).getQuantite();
		    			
		    	}
		    				}
		    				
	    				
		    			}
		    			
		    			stockfinal=(initial.add(production).add(achat).add(TransferEntrer).add(avoirClient)).subtract(vente.add(avoir));
		    			MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
		    			mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticle.get(i).getDateTransfer());
		    			mouvementstockPF.setAchat(achat);
		    			mouvementstockPF.setVente(vente);
		    			mouvementstockPF.setAvoir(avoir);
		    			mouvementstockPF.setAvoirClientPF(avoirClient);
		    			mouvementstockPF.setInitial(initial);
		    			mouvementstockPF.setEntrerProduction(production);
		    			mouvementstockPF.setTransferEntrer(TransferEntrer);
		    			mouvementstockPF.setTransferSortie(TransferSortie);
		    			mouvementstockPF.setSousFamille(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille());
		    			mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticle.get(i).getArticle());
		    			mouvementstockPF.setStockFinal(stockfinal);
		    			listMouvementStockPF.add(mouvementstockPF);
		    			
		    			
		    			

		    		}
		    		
		    		
		    		// detailtransfer entre deux date et par article
		    		
		    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && article!=null)
		    		{
		    			listMouvementStockPFAfficher.clear();
		    			listMouvementStockPFAfficherTmp.clear();
		    			
		    		
		    		for(int i=0;i<listMouvementStockPF.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
		    			
		    			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
		    				
		    			{
		    			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()))
		    			{

		    				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));	
		    			}
		    				
		    			
		    			}
		    			
		    		}
		    			
		    		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
		    			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
		    			{
		    			if(listMouvementStockPFAfficher.get(j).getArticle().getLiblle().equals(article.getLiblle()))
		    			{
		    				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
		    			}
		    				
		    			}
		    			
		    		}
		    		
		    		
		    		if(listMouvementStockPFAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficher);
		    		}
		    		
		    			
		    		// detailtransfer entre deux date (date fin null) et par article 
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
		    		{
		    			listMouvementStockPFAfficherTmp.clear();
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    		
		    			
		    			for(int i=0;i<listMouvementStockPF.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
			    			if(ddbut.equals(d1) && listMouvementStockPF.get(i).getArticle().equals(article) )
			    			{
			    			
			    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
			    			
			    			}
			    			
			    		}
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    			// detailtransfer entre deux date (date fin null)  
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
		    		{
		    			
		    			listMouvementStockPFAfficherTmp.clear();
	                      
	                      String d1=sdf.format(dateChooserdebut.getDate());
	  	    			
		    			
		    			for(int i=0;i<listMouvementStockPF.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
			    			if(ddbut.equals(d1) )
			    			{
			    			
			    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
			    			
			    			}
			    		
			    		}
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    			// detailtransfer par article
		    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && article!=null)
		    		{
		    			listMouvementStockPFAfficherTmp.clear();
		             
		               
		               
		    			for(int i=0;i<listMouvementStockPF.size();i++)	
			    		{
			    			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()) )
			    			{
			    			
			    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    			
		    			// detailtransfer entre deux date  
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && article==null)
		    		{
		    			listMouvementStockPFAfficher.clear();
		    			listMouvementStockPFAfficherTmp.clear();
		    		
		    		for(int i=0;i<listMouvementStockPF.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
		    			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
		    			{
		    				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));
		    			
		    				
		    			}
		    			
		    		}
		    			
		    		
		    		
		    		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
		    			
		    			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
		    			{
		    			
		    				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
		    			
		    			}
		    			
		    		}
		    		
		    		if(listMouvementStockPFAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockPFAfficher);
		    		}
		    				    			
		    		}
		    		
	    			
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	 
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(615, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1384, 51);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Date Debut :");
	    lblDateFacture.setBounds(10, 11, 136, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dateChooserdebut = new JDateChooser();
	     dateChooserdebut.setBounds(112, 9, 163, 26);
	     layeredPane_2.add(dateChooserdebut);
	     dateChooserdebut.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {}
	     });
	     dateChooserdebut.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     dateChooserdebut.setLocale(Locale.FRANCE);
	     dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	     
	     JLabel lblDateFin = new JLabel("Date Fin :");
	     lblDateFin.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblDateFin.setBounds(296, 11, 106, 24);
	     layeredPane_2.add(lblDateFin);
	     
	      dateChooserfin = new JDateChooser();
	     dateChooserfin.setLocale(Locale.FRANCE);
	     dateChooserfin.setDateFormatString("dd/MM/yyyy");
	     dateChooserfin.setBounds(399, 11, 169, 26);
	     layeredPane_2.add(dateChooserfin);
	     
	      comboarticle = new JComboBox();
	     comboarticle.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		


     	 		
		     	 
			 			if(!comboarticle.getSelectedItem().equals(""))
				 		{
				 			Articles article=mapArticle.get(comboarticle.getSelectedItem());
				 			txtcodearticle.setText(article.getCodeArticle());
				 			
				 		  				 			
				 		}else
				 		{
				 			txtcodearticle.setText("");
				 			
				 		}
				 	
			 		
	     	}
	     });
	     comboarticle.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent arg0) {
	     		
	     		
	     		
	     		
	     		
	     		
	     		
	     	}
	     });
	     comboarticle.setBounds(1109, 13, 218, 27);
	     layeredPane_2.add(comboarticle);
	     AutoCompleteDecorator.decorate(comboarticle);
	     JLabel label = new JLabel("Libelle :");
	     label.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     label.setBounds(1064, 13, 45, 26);
	     layeredPane_2.add(label);
	     
	     txtcodearticle = new JTextField();
	     txtcodearticle.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		

     			if(e.getKeyCode()==e.VK_ENTER)
		      		{
     				
     					
		      			if(!txtcodearticle.getText().equals(""))
		      			{
		      				Articles article=mapCodeArticle.get(txtcodearticle.getText());
		      				
				    		
				    		if(article!=null)
				    		{	
				    			comboarticle.setSelectedItem(article.getLiblle());
				    			
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
	     txtcodearticle.setBounds(961, 13, 93, 26);
	     layeredPane_2.add(txtcodearticle);
	     
	     JLabel label_1 = new JLabel("Code Article :");
	     label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     label_1.setBounds(889, 13, 74, 26);
	     layeredPane_2.add(label_1);
	     
	     JButton btnInitialiser = new JButton("Initialiser");
	     btnInitialiser.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		comboarticle.setSelectedItem("");
	     		txtcodearticle.setText("");
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		
	     		
	     	}
	     });
	     btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btnInitialiser.setBounds(748, 120, 107, 24);
	     add(btnInitialiser);
	     comboarticle.addItem("");;
	     int i=0;
	     while(i<listArticle.size())
	     {
	    	 Articles article=listArticle.get(i);
	    	 mapArticle.put(article.getLiblle(), article);
	    	 mapCodeArticle.put(article.getCodeArticle(), article);
	    	 comboarticle.addItem(article.getLiblle());
	    	 combodepot.setSelectedItem("");
	    	 i++;
	     }
	     
	    
	     txtcodearticle.setText("");
	     
	     JLabel label_2 = new JLabel("Depot  :");
	     label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     label_2.setBounds(603, 10, 45, 26);
	     layeredPane_2.add(label_2);
	     try {
			  
			 
	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
	          dateChooserdebut.setDate(date);
	          dateChooserfin.setDate(new Date());
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	      combodepot = new JComboBox();
	     combodepot.setBounds(664, 10, 202, 27);
	     layeredPane_2.add(combodepot);
	     
	     JButton button = new JButton("Exporter Excel");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		
	     		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	     		
	     		String titre="Mouvement de Stock PF "+magasin.getLibelle();
	     		String titrefeuille="Mouvement de Stock PF ";
	     		ExporterTableVersExcel.tabletoexcel(tableDetailMouvement, titre,titrefeuille);
	     		
	    		}else
	    		{
	                JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		}
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(522, 619, 123, 32);
	     button.setIcon(imgExcel);
	     add(button);
	     
	     if(utilisateur.getLogin().equals("admin"))
	 		  {
	 	    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_PF);
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
	 				  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_PF);
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
		modeleDetailTransferStockPF =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					//	"Date", "Article", "Famille", "Sous Famille", "Opération", "Quantité", "Prix Unitaire", "Opérateur"
						"Date", "Article","Sous Famille", "Initial", "Entrer Production","Transfer Entrer", "Achat", "Vente","Avoir", "Stock Final"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableDetailMouvement.setModel(modeleDetailTransferStockPF);
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
	
	
	
	
	
	
	void afficher_tableDetailMouvementStock(List<MouvementStockProduitsFini> listDetailMouvement)
	{
		modeleDetailTransferStockPF =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						//"Date", "Article", "Famille", "Sous Famille", "Opération", "Quantité", "Prix Unitaire", "Opérateur"
						"Date", "Article","Sous Famille", "Initial", "Entrer Production","Transfer Entrer","Transfer Sortie", "Achat", "Vente","Avoir FRS","Avoir CLT", "Stock Final"
						
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableDetailMouvement.setModel(modeleDetailTransferStockPF);
		int i=0;
		
		/*
		 String operateur="";
		while(i<listDetailMouvement.size())
		{	
		DetailTransferProduitFini detailMouvementStock=listDetailMouvement.get(i);
		
		if(detailMouvementStock.getTypeTransfer().equals(Constantes.ETAT_TRANSFER_STOCK_ACHAT))
		{
			FactureAchat factureAchat=factureAchatdao.findFacturePFByCodeTransfer(detailMouvementStock.getTransferStockPF().getCodeTransfer());
			operateur=factureAchat.getFournisseur().getNom();
		}else if(detailMouvementStock.getTypeTransfer().equals(Constantes.ETAT_TRANSFER_STOCK_VENTE))
		{
			FacturePF facturevente=facturepfdao.findFacturePFByCodeTransfer(detailMouvementStock.getTransferStockPF().getCodeTransfer());
			operateur=facturevente.getClientPF().getNom();
		}else if(detailMouvementStock.getTypeTransfer().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL))
		{
			operateur=detailMouvementStock.getTransferStockPF().getCreerPar().getNom();	
		}else if(detailMouvementStock.getTypeTransfer().equals(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
		{
			operateur=detailMouvementStock.getTransferStockPF().getCreerPar().getNom();	
		}
		*/
		while(i<listDetailMouvement.size())
		{	
		MouvementStockProduitsFini detailMouvementStock=listDetailMouvement.get(i);
			
		Object []ligne={detailMouvementStock.getDateStockPF(),detailMouvementStock.getArticle().getLiblle(),detailMouvementStock.getSousFamille().getLiblle(), detailMouvementStock.getInitial(),detailMouvementStock.getEntrerProduction(),detailMouvementStock.getTransferEntrer(),detailMouvementStock.getTransferSortie(), detailMouvementStock.getAchat(),detailMouvementStock.getVente(),detailMouvementStock.getAvoir(),detailMouvementStock.getAvoirClientPF(), detailMouvementStock.getStockFinal()};
		modeleDetailTransferStockPF.addRow(ligne);
			
			
			i++;
		}
}
	
	

	}


