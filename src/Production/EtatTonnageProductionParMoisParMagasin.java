package Production;

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
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
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
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FactureServiceProduction;
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
import dao.entity.TonnageProduction;
import dao.entity.TransferStockMP;
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;

import java.awt.SystemColor;


public class EtatTonnageProductionParMoisParMagasin extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private ImageIcon imgExcel;
	private DefaultTableModel	 modeleMP;
	private JTable table = new JTable();
	private List<Object[]> listObject =new ArrayList<Object[]>();
	private List<TonnageProduction> listTonnageProduction =new ArrayList<TonnageProduction>();
	private List<String> listNomMagasin =new ArrayList<String>();
	private List<Integer> listMois =new ArrayList<Integer>();
	 
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
 
	 

private   JDateChooser audateChooser = new JDateChooser();
	private  JComboBox comboclient = new JComboBox();
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	 
	private CoutMPDAO coutMPDAO;
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
	 JComboBox combomagasin = new JComboBox();
	 JComboBox comboarticle = new JComboBox();
	   JComboBox comboparfamille = new JComboBox();
	 private FamilleArticlesPFDAO famillearticleDAo;
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 JComboBox comboBox = new JComboBox();
	 
	private JTextField txtdu;
	private JTextField txtau;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatTonnageProductionParMoisParMagasin() {
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
            
            coutMPDAO=new CoutMPDAOImpl();
           
       
          } catch (Exception exp){exp.printStackTrace();}
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                       Etat Tonnage Production");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(381, 11, 903, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1524, 427);
		add(scrollPane_1);
		 
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
}
		});
		
		
		scrollPane_1.setViewportView(table);
		 
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mois", "Tonnage Globale","Ahl Brahim", "Sahara Packing","Inzi" 
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		 
	
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		 
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
	    		

	    		
	    	 
	    		
	    		if(dudateChooser.getDate()==null || audateChooser.getDate()==null)
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir la date debut et la date fin ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		listObject=coutMPDAO.EtatTonnageProductionParMoisParMagasin(dudateChooser.getDate(), audateChooser.getDate());
	    		listTonnageProduction.clear();
	    		listNomMagasin.clear();
	    		listMois.clear();
	    		boolean existe=false;
	    		boolean Moisexiste=false;
	    		for(int i=0;i<listObject.size();i++)
	    		{
	    			existe=false;
	    			Moisexiste=false;
	    			Object[] object=listObject.get(i);	
	    			
	    			if(object[0]!=null)
	    			{
	    				if(object[1]!=null)
		    			{
	    					
	    					if(object[2]!=null)
	    	    			{
	    						TonnageProduction tonnageProduction=new TonnageProduction();
	    	    				tonnageProduction.setMois((int)object[0]);
	    	    				tonnageProduction.setQuantite((BigDecimal)object[1]);
	    	    				tonnageProduction.setMagasin((String)object[2]);
	    	    				listTonnageProduction.add(tonnageProduction);
	    	    				
	    	    				
	    	    				for(int j=0;j<listNomMagasin.size();j++)
	    	    				{
	    	    					
	    	    					if(listNomMagasin.get(j).toString().equals((String)object[2]))
	    	    					{
	    	    						
	    	    						existe=true;
	    	    						
	    	    					}
	    	    					
	    	    					
	    	    					
	    	    				}
	    	    				
	    	    				for(int j=0;j<listMois.size();j++)
	    	    				{
	    	    					
	    	    					if(listMois.get(j).equals((int)object[0]))
	    	    					{
	    	    						
	    	    						Moisexiste=true;
	    	    						
	    	    					}
	    	    					
	    	    					
	    	    					
	    	    				}
	    	    				
	    	    				if(existe==false)
	    	    				{
	    	    					listNomMagasin.add((String)object[2]);
	    	    				}

	    	    				if(Moisexiste==false)
	    	    				{
	    	    					listMois.add((int)object[0]);
	    	    				}
	    	    				
	    						
	    	    			}
	    					
		    			}
	    				
	    				
	    				
	    			}
	    			
	    			
	    			
	    		}
	    		
	  		
	    		
	    	
	    	afficher_table(listTonnageProduction);	
	    		
	    		
	    		
	    		
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
	    lblDateFacture.setBounds(294, 13, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(329, 11, 166, 26);
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
	     lblAu.setBounds(505, 13, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(540, 11, 166, 26);
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
	     
	     
	     
	     JButton btnExporterExcel = new JButton("Exporter Excel");
	     btnExporterExcel.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {   
	     		 /*
	     		JFileChooser fchoose = new JFileChooser();
		           int option = fchoose.showSaveDialog(EtatTonnageProductionParMoisParMagasin.this);
		           if(option == JFileChooser.APPROVE_OPTION){
		             String name = fchoose.getSelectedFile().getName(); 
		             String path = fchoose.getSelectedFile().getParentFile().getPath();
		             String file = path + "\\" + name + ".xls"; 
		             export(table, new File(file));
			 
	     		
	     		
	     		
				
		           }
		           
		           */
	     		
	     		
	     		
	     		
	     		String titre="Etat Tonnage Production  ";
	    		String titrefeuille="Etat Tonnage Production  ";
	    		ExporterTableVersExcel.tableTonnageproductionToExcel (modeleMP, titre,titrefeuille);
	     		
	     		
	     	}
	     });
	     btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btnExporterExcel.setBounds(663, 592, 123, 32);
	     btnExporterExcel.setIcon(imgExcel);
	     add(btnExporterExcel);
	     
	     JButton btnAjouterOfFacture = new JButton("Ajouter OF Facture");
	     btnAjouterOfFacture.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {

	     	
	     	
	     	
	     	
	     	
	     	}
	     });
	     btnAjouterOfFacture.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btnAjouterOfFacture.setBounds(78, 706, 138, 33);
	     btnAjouterOfFacture.setVisible(false);
	     add(btnAjouterOfFacture);
	     
	     JButton btnSupprimerFactureService = new JButton("Supprimer facture Service");
	     btnSupprimerFactureService.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {

	     	
	     	}
	     });
	     btnSupprimerFactureService.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btnSupprimerFactureService.setBounds(717, 703, 183, 33);
	     btnSupprimerFactureService.setVisible(false);
	     add(btnSupprimerFactureService);
	     
	     txtdu = new JTextField();
	     txtdu.setColumns(10);
	     txtdu.setBounds(345, 706, 102, 26);
	     txtdu.setVisible(false);
	     add(txtdu);
	     
	     JLabel lblDu = new JLabel("Du :");
	     lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblDu.setBounds(286, 706, 50, 24);
	     lblDu.setVisible(false);
	     add(lblDu);
	     
	     txtau = new JTextField();
	     txtau.setColumns(10);
	     txtau.setBounds(535, 706, 145, 26);
	     txtau.setVisible(false);
	     add(txtau);
	     
	     JLabel lblAu_1 = new JLabel("Au :");
	     lblAu_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblAu_1.setBounds(472, 706, 97, 24);
	     lblAu_1.setVisible(false);
	     add(lblAu_1);
	     
 
		
		}
	
	


	void initialiser()
	{
 
 
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
 
 InitialiseTableau();	
	}

	
	
 
	
	
	
	
	
	
	void InitialiseTableau()
	{

		modeleMP = new DefaultTableModel();
		
		modeleMP.addColumn("Mois");
		modeleMP.addColumn("Tonnage Globale");
		
		
		for(int i=0;i<listNomMagasin.size();i++)
		{
				modeleMP.addColumn( listNomMagasin.get(i));
				
 
			 
		}
	
	}
	
	
	
	void afficher_table(List<TonnageProduction> listTonnageProduction)
	{
		
		InitialiseTableau();
		for(int i=0;i<listMois.size();i++)
		{
			
			Vector<Object> data = new Vector<Object>();	
			
			data.add(listMois.get(i));
			data.add("0");
			for(int t=0;t<listNomMagasin.size();t++)
			{
				data.add("0");
				
				
			}
			
			 modeleMP.addRow(data);
			
			
			
			
			
			
		}
		

		table.setModel(modeleMP);
		
		
		BigDecimal totalQuantite=BigDecimal.ZERO;
		
		for(int y=0;y<table.getRowCount();y++)
		{
		
			 
			totalQuantite=BigDecimal.ZERO;
			
			for(int d=0;d<table.getColumnCount();d++)
			{
				
				for(int t=0;t<listTonnageProduction.size();t++)
				{
					
				if(table.getColumnName(d).toString().equals(listTonnageProduction.get(t).getMagasin().toString()))	
				{
					
					if( Integer.valueOf(table.getValueAt(y, 0).toString()) .equals(listTonnageProduction.get(t).getMois()))
					{
						
					table.setValueAt(listTonnageProduction.get(t).getQuantite(), y, d);	
					totalQuantite=totalQuantite.add(listTonnageProduction.get(t).getQuantite());
					}
					
					
				}
					
					
					
					
				}
				
				
			}
			
			table.setValueAt(totalQuantite, y, 1);
			
		
		
		}
		
		
		
		
		
		
}
	
	public void export(JTable table, File file){
	    try
	    {
	      TableModel m = table.getModel();
	      FileWriter fw = new FileWriter(file);
	      for(int i = 0; i < m.getColumnCount(); i++){
	        fw.write(m.getColumnName(i) + "\t");
	      }
	      fw.write("\n");
	      for(int i=0; i < m.getRowCount(); i++) {
	        for(int j=0; j < m.getColumnCount(); j++) {
	          fw.write(m.getValueAt(i,j).toString()+"\t");
	        }
	        fw.write("\n");
	      }
	      fw.close();
	    }
	    catch(IOException e){ System.out.println(e); }
	  }
	
	
	}


