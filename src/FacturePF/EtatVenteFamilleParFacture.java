package FacturePF;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;

import Production.MatierePremiere;
import util.Constantes;
import util.ConverterNumberToWords;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.DetailTransferProduitFini;
import dao.entity.EtatStockPF;
import dao.entity.EtatVentePFParFamille;
import dao.entity.EtatVenteParArticle;
import dao.entity.EtatVenteParFamilleArticle;
import dao.entity.EtatVenteParFamilleParFacture;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import javax.swing.border.EtchedBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;


public class EtatVenteFamilleParFacture extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCompte;
	private DefaultTableModel	 modeledetail;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	
	
	private  DetailFacturePFDAO detailfacturedao;
	
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<FamilleArticlePF> listFamille =new ArrayList<FamilleArticlePF>();
	private List<String> listLibelleFamille =new ArrayList<String>();
	
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, FamilleArticlePF> mapFamillePF= new HashMap<>();
	private List<EtatVenteParFamilleParFacture> listVentePFParFamilleParFacture =new ArrayList<EtatVenteParFamilleParFacture>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private JScrollPane scrollPane;
	private JXTable table;
	private Utilisateur utilisateur;
	private JDateChooser datedu = new JDateChooser();
	private JLabel lblDateFin;
	private JButton btnImprimer;
	private JLabel lblEtatDeVente;
	private JButton button_1;
	String message="";
	private JLabel label;
	private JComboBox combodepot;
	private JLabel label_1;
	private JComboBox combomagasin;
	private DepotDAO depotdao;
	private FamilleArticlesPFDAO familleArticlesPFDAO;
	private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	   
		  JDateChooser dateAu = new JDateChooser();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EtatVenteFamilleParFacture() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1513, 882);
       
        try{
        	   utilisateur=AuthentificationView.utilisateur;
        	   detailfacturedao=new DetailFacturePFDAOImpl();
        	   detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
        	
        	
        	
      

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
            depotdao=ProdLauncher.depotDAO;
            utilisateur=AuthentificationView.utilisateur;
            familleArticlesPFDAO=new FamilleArticlesPFDAOImpl();                        
            listFamille=familleArticlesPFDAO.findAll();
            
            
            
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		   		
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(10, 269, 1470, 378);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JXTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {}
				  		   			});
				  		   			scrollPane.setViewportView(table);
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Date Facture","Num Facture","Famille Article", "Montant HT", "Montant TVA","Montant TTC"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(221);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(120);
				  		   			table.getColumnModel().getColumn(2).setPreferredWidth(123);
				  		   		table.getColumnModel().getColumn(3).setPreferredWidth(123);
				  		   	table.getColumnModel().getColumn(4).setPreferredWidth(123);
				  		  table.getColumnModel().getColumn(5).setPreferredWidth(123);
				  		   			table.setFillsViewportHeight(true);
				  		   			
				  		   			btnImprimer = new JButton("Imprimer");
				  		   			btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * 
				 * if(listVentePFParFamille.size()!=0) {
				 * 
				 * 
				 * DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); String
				 * datedebut=dateFormat.format(dateChooserdebut.getDate()); //String
				 * dateFin=dateFormat.format(dateChooserfin.getDate());
				 * 
				 * 
				 * 
				 * Map parameters = new HashMap(); parameters.put("datedebut", datedebut);
				 * parameters.put("dateFin", datedebut);
				 * 
				 * JasperUtils.imprimerEtatVenteParFamille(listVentePFParFamille, parameters);;
				 * 
				 * }else { JOptionPane.showMessageDialog(null,
				 * "Il n'existe auccun vente pour cette periode ", "Erreur",
				 * JOptionPane.ERROR_MESSAGE); }
				 * 
				 * 
				 * 
				 * 
				 */
				 SimpleDateFormat dt = new SimpleDateFormat("yyyy");
				
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				
	    		if(magasin!=null)
	    		{
	    		   
	    		
	    		String titre="Etat de Vente Par Famille Par Facture"+magasin.getLibelle() +" Annee  "+ dt.format(datedu.getDate());
	    		String titrefeuilleexcel="Etat de Vente Par Famille Par Facture";
	    		ExporterTableVersExcel.tabletoexcelEtatVenteParFamilleParFacture (table, titre,titrefeuilleexcel);
	    		
	    		
	    		
	    		}else
	    		{

	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		}
				
				
			
			
			
			}
				  		   			});
				  		   			btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnImprimer.setBounds(583, 674, 107, 24);
				  		   		btnImprimer.setIcon(imgExcel);
				  		   			add(btnImprimer);
				  		   			
				  		   			lblEtatDeVente = new JLabel("             Etat de Vente Par Famille Article Par Facture");
				  		   			lblEtatDeVente.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
				  		   			lblEtatDeVente.setBackground(Color.WHITE);
				  		   			lblEtatDeVente.setBounds(143, 40, 1093, 35);
				  		   			add(lblEtatDeVente);
				  		   			
				  		   			JLayeredPane layeredPane = new JLayeredPane();
				  		   			layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane.setBounds(10, 111, 1470, 106);
				  		   			add(layeredPane);
				  		   			
				  		   			datedu = new JDateChooser();
				  		   			datedu.setBounds(553, 25, 162, 26);
				  		   			layeredPane.add(datedu);
				  		   			datedu.setLocale(Locale.FRANCE);
				  		   			datedu.setDateFormatString("dd/MM/yyyy");
				  		   			
				  		   			lblDateFin = new JLabel("Du  :");
				  		   			lblDateFin.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		   			lblDateFin.setBounds(513, 27, 45, 24);
				  		   			layeredPane.add(lblDateFin);
				  		   			
				  		   			label = new JLabel("Depot :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(10, 27, 56, 24);
				  		   			layeredPane.add(label);
				  		   			
				  		   			combodepot = new JComboBox();
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
					  		 		     				
					  		 		     				
					  		 		     				
					  		 		     				
					  		 		     			}else
					  		 		     			{
					  		 		     				combomagasin.removeAllItems();
					  		 		     				
					  		 		     			}
					  		 		     				
					  		      					
					  		      				}
					  		      				
					  		      				
					  		     	 		 }
					  		     	 	
					  		  	
					  		   			 		
					  		   			 		
					  		   			 		
					  		   			 		
					  		   			 		
					  		   			 	
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			combodepot.setSelectedIndex(-1);
				  		   			combodepot.setBounds(60, 27, 183, 24);
				  		   			layeredPane.add(combodepot);
				  		   			
				  		   			label_1 = new JLabel("Magasin :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(254, 26, 56, 24);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			combomagasin = new JComboBox();
				  		   			combomagasin.setSelectedIndex(-1);
				  		   			combomagasin.setBounds(320, 27, 183, 24);
				  		   			layeredPane.add(combomagasin);
				  		   			
				  		   			
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
				  				  
				  				  button_1 = new JButton();
				  				  button_1.setBounds(1013, 20, 31, 31);
				  				  layeredPane.add(button_1);
				  				  button_1.addActionListener(new ActionListener() {
				  				  	public void actionPerformed(ActionEvent e) {
				  				  		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  				  		
				  				  		if(magasin!=null)
				  				  		{

				  				  		if(datedu.getDate()==null && dateAu.getDate()==null)
				  				  		{
				  				  		JOptionPane.showMessageDialog(null, "Il faut choisir le Date SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  						return ;
				  				  		}else
				  				  		{
				  				  			
				  				  			
				  				  		if(datedu.getDate()==null && dateAu.getDate()!=null)
				  				  		{
				  				  		datedu.setDate(dateAu.getDate());
				  				  		}else if(datedu.getDate()!=null && dateAu.getDate()==null)
				  				  		{
				  				  			
				  				  		dateAu.setDate(datedu.getDate());
				  				  		}
				  				  			
				  				  		listeObject=detailfacturedao.listeEtatVentePFParFamilleParFacture(datedu.getDate(), dateAu.getDate(), magasin)	;
				  				  			
				  				  			
				  				  		for(int i=0;i<listeObject.size();i++)
				  				  		{
				  				  			
				  				  		 Object[] object=listeObject.get(i);	
				  				  			
				  				  		if(object[0]!=null)	
				  				  		{
				  				  			
				  				  		EtatVenteParFamilleParFacture etatVenteParFamilleParFacture=new EtatVenteParFamilleParFacture();
				  				  		
				  				  	etatVenteParFamilleParFacture.setDateFacture((Date)object[0]);	
				  				  etatVenteParFamilleParFacture.setNumFacture((String) object[1]);
				  				  		etatVenteParFamilleParFacture.setFamille((String) object[2]);	
				  				  			
				  				  			etatVenteParFamilleParFacture.setTotalHT((BigDecimal) object[3]);
				  				  		etatVenteParFamilleParFacture.setTotalTVA((BigDecimal) object[4]);
				  				  	etatVenteParFamilleParFacture.setTotalTTC((BigDecimal) object[5]);
				  				  	listVentePFParFamilleParFacture.add(etatVenteParFamilleParFacture);
				  				  			
				  				  			
				  				  			
				  				  		}
				  				  			
				  				  			
				  				  			
				  				  		}
				  				  		
				  				  		
				  				  		
				  				  		afficher_tableEtatVenteArticle(listVentePFParFamilleParFacture);
				  				  		
				  				  		
				  				  		
				  				  		
				  				  			
				  				  		}
				  				  		
				  				  		
				  				  		
				  				  		}else
				  				  		{
				  				  			
				  				  		JOptionPane.showMessageDialog(null, "Il faut choisir le Magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  						return ;
				  				  			
				  				  		}
				  				  		
				  		   		
				  				  	
				  				  		
				  				  	}
				  				  });
				  				  button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  				  button_1.setIcon(imgRechercher);
				  				  
				  				  JLabel lblAu = new JLabel("Au  :");
				  				  lblAu.setFont(new Font("Tahoma", Font.BOLD, 11));
				  				  lblAu.setBounds(750, 27, 45, 24);
				  				  layeredPane.add(lblAu);
				  				  
				  				   dateAu = new JDateChooser();
				  				  dateAu.setLocale(Locale.FRANCE);
				  				  dateAu.setDateFormatString("dd/MM/yyyy");
				  				  dateAu.setBounds(790, 25, 162, 26);
				  				  layeredPane.add(dateAu);
				  				  
				  				  
				  				  
				  				  for(int i=0;i<listFamille.size();i++)
				  				  {
				  					  
				  					  FamilleArticlePF familleArticlePF= listFamille.get(i);
				  					
				  					  mapFamillePF.put(familleArticlePF.getLiblle(), familleArticlePF);
				  					  
				  				  }
				  		   			
				  		   		
				  		   			
	}
	
	

	


	
	
	
	
	
	
	void afficher_tableEtatVenteArticle(List<EtatVenteParFamilleParFacture> listEtatVenteParFamilleParFacture)
	{
		
		

		modeleCompte =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Date Facture","Num Facture","Famille Article", "Montant HT", "Montant TVA","Montant TTC"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleCompte);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	  		 SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
		  int i=0;
			while(i<listEtatVenteParFamilleParFacture.size())
			{	
				
				EtatVenteParFamilleParFacture etatVentePFParFamilleParFacture=listEtatVenteParFamilleParFacture.get(i);
				
				
				String datefacture=dt.format(etatVentePFParFamilleParFacture.getDateFacture());
				
				
				Object []ligne={datefacture ,etatVentePFParFamilleParFacture.getNumFacture(),etatVentePFParFamilleParFacture.getFamille(),etatVentePFParFamilleParFacture.getTotalHT(),etatVentePFParFamilleParFacture.getTotalTVA(),etatVentePFParFamilleParFacture.getTotalTTC()};

				modeleCompte.addRow(ligne);
				i++;
			}
			
			
	 
			
			
	}
	
	
	
	
	
	
	public void CalculerEtatPF( Date datedu , Date dateau , String famille)
	
	{
		/*
		 * 
		 * 
		 * 
		 * detailTransferStockPFDAO.ViderSession();
		 * 
		 * listEtatStockPFImprimer.clear(); listEtatStockPF.clear();
		 * List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
		 * List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFProductionGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFSortieGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFEntrerGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProduction
		 * =new ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFEntrerProductionGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajne =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFAvoirMarajneGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFAvoirGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFAllPFTransfer =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * 
		 * SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); BigDecimal
		 * montantInitial=new BigDecimal(0); BigDecimal quantiteTotalInitial=new
		 * BigDecimal(0); BigDecimal PrixInitial=new BigDecimal(0); BigDecimal
		 * montantachat=new BigDecimal(0); BigDecimal quantiteTotalachat=new
		 * BigDecimal(0); BigDecimal quantiteTotalvente=new BigDecimal(0); BigDecimal
		 * montantvente=new BigDecimal(0); BigDecimal quantiteTotalavoir=new
		 * BigDecimal(0); BigDecimal montantavoir=new BigDecimal(0); BigDecimal
		 * quantiteTotalEntrer=new BigDecimal(0); BigDecimal montantEntrer=new
		 * BigDecimal(0); BigDecimal quantiteTotalGratuit=new BigDecimal(0); BigDecimal
		 * montantGratuit=new BigDecimal(0); BigDecimal quantiteTotalproduction=new
		 * BigDecimal(0); BigDecimal prixmoyenproduction=new BigDecimal(0); BigDecimal
		 * prixmoyenachat=new BigDecimal(0); BigDecimal prixmoyenvente=new
		 * BigDecimal(0); BigDecimal prixmoyentransferentrer=new BigDecimal(0);
		 * BigDecimal prixmoyenavoir=new BigDecimal(0); BigDecimal montantproduction=new
		 * BigDecimal(0);
		 * 
		 * BigDecimal quantiteTotalFinale=new BigDecimal(0); BigDecimal
		 * montantFinale=new BigDecimal(0); boolean trouve=false; FamilleArticlePF
		 * familleArticle=familleArticlesPFDAO.findByLibelle(famille); Articles
		 * article=null; Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		 * if(magasin!=null) {
		 * 
		 * 
		 * 
		 * 
		 * 
		 * listDetailTransferStockPFSortie=detailTransferStockPFDAO.
		 * ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedu, dateau, article,
		 * ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		 * listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.
		 * ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedu, dateau,
		 * article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		 * 
		 * 
		 * 
		 * listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.
		 * findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		 * 
		 * for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++) {
		 * DetailTransferProduitFini
		 * detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
		 * EtatStockPF etatstock=new EtatStockPF();
		 * etatstock.setArticle(detailtransferstockpf.getArticle());
		 * etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
		 * etatstock.setFamilleArticle(detailtransferstockpf.getSousFamille().
		 * getFamileArticlePF()); etatstock.setQuantiteInitial(BigDecimal.ZERO);
		 * etatstock.setPrixInitial(BigDecimal.ZERO);
		 * etatstock.setMontantInitial(BigDecimal.ZERO);
		 * etatstock.setQuantiteAchat(BigDecimal.ZERO);
		 * etatstock.setPrixAchat(BigDecimal.ZERO);
		 * etatstock.setMontantAchat(BigDecimal.ZERO);
		 * etatstock.setQuantiteProduction(BigDecimal.ZERO);
		 * etatstock.setPrixProduction(BigDecimal.ZERO);
		 * etatstock.setMontantProduction(BigDecimal.ZERO);
		 * etatstock.setQuantiteSortie(BigDecimal.ZERO);
		 * etatstock.setPrixSortie(BigDecimal.ZERO);
		 * etatstock.setMontantSortie(BigDecimal.ZERO);
		 * etatstock.setQuantiteEntrer(BigDecimal.ZERO);
		 * etatstock.setPrixEntrer(BigDecimal.ZERO);
		 * etatstock.setMontantEntrer(BigDecimal.ZERO);
		 * etatstock.setQuantiteAvoir(BigDecimal.ZERO);
		 * etatstock.setPrixAvoir(BigDecimal.ZERO);
		 * etatstock.setMontantAvoir(BigDecimal.ZERO);
		 * etatstock.setMarge(BigDecimal.ZERO);
		 * etatstock.setQuantiteGratuit(BigDecimal.ZERO);
		 * etatstock.setPrixGratuit(BigDecimal.ZERO);
		 * etatstock.setMontantGratuit(BigDecimal.ZERO);
		 * etatstock.setQuantiteFinale(BigDecimal.ZERO);
		 * etatstock.setPrixFinale(BigDecimal.ZERO);
		 * etatstock.setMontantFinale(BigDecimal.ZERO); listEtatStockPF.add(etatstock);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * // calculer Quantite Vente et le prix moyen
		 * 
		 * 
		 * 
		 * for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++) {
		 * quantiteTotalvente=BigDecimal.ZERO; quantiteTotalGratuit=BigDecimal.ZERO;
		 * montantvente=new BigDecimal(0); prixmoyenvente=new BigDecimal(0);
		 * 
		 * for(int j=0;j<listDetailTransferStockPFSortie.size();j++) {
		 * 
		 * if(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle().equals(
		 * listDetailTransferStockPFSortie.get(j).getArticle()) &&
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille().equals(
		 * listDetailTransferStockPFSortie.get(j).getSousFamille())) {
		 * 
		 * 
		 * // la quantite Gratuité
		 * if(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6).
		 * equals(BigDecimal.ZERO.setScale(6))) {
		 * 
		 * quantiteTotalGratuit=quantiteTotalGratuit.add(listDetailTransferStockPFSortie
		 * .get(j).getQuantite());
		 * 
		 * 
		 * 
		 * }else {
		 * 
		 * // la quantite Vente
		 * 
		 * montantvente=montantvente.add(listDetailTransferStockPFSortie.get(j).
		 * getPrixUnitaire().multiply(listDetailTransferStockPFSortie.get(j).getQuantite
		 * ())); prixmoyenvente=((prixmoyenvente.multiply(quantiteTotalvente)).add(
		 * listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6,
		 * RoundingMode.DOWN).multiply(listDetailTransferStockPFSortie.get(j).
		 * getQuantite()))).divide(quantiteTotalvente.add(
		 * listDetailTransferStockPFSortie.get(j).getQuantite()),6, RoundingMode.DOWN) ;
		 * 
		 * quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFSortie.get
		 * (j).getQuantite());
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * if(!quantiteTotalvente.equals(BigDecimal.ZERO)) {
		 * 
		 * 
		 * for(int k=0;k<listEtatStockPF.size();k++) {
		 * 
		 * 
		 * if(listEtatStockPF.get(k).getArticle().equals(
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) &&
		 * listEtatStockPF.get(k).getSousFamille().equals(
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille())) {
		 * 
		 * EtatStockPF etatstockpf=listEtatStockPF.get(k);
		 * etatstockpf.setQuantiteSortie(quantiteTotalvente);
		 * etatstockpf.setPrixSortie(prixmoyenvente);
		 * etatstockpf.setMontantSortie(quantiteTotalvente.multiply(prixmoyenvente));
		 * 
		 * listEtatStockPF.set(k, etatstockpf); }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * if(!quantiteTotalGratuit.equals(BigDecimal.ZERO)) {
		 * 
		 * 
		 * for(int k=0;k<listEtatStockPF.size();k++) {
		 * 
		 * 
		 * if(listEtatStockPF.get(k).getArticle().equals(
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) &&
		 * listEtatStockPF.get(k).getSousFamille().equals(
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille())) {
		 * 
		 * EtatStockPF etatstockpf=listEtatStockPF.get(k);
		 * etatstockpf.setQuantiteGratuit(quantiteTotalGratuit.setScale(6));
		 * if(etatstockpf.getPrixAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6))
		 * ) { etatstockpf.setPrixGratuit(etatstockpf.getPrixInitial().setScale(6));
		 * }else { etatstockpf.setPrixGratuit(etatstockpf.getPrixAchat().setScale(6)); }
		 * 
		 * etatstockpf.setMontantGratuit(etatstockpf.getQuantiteGratuit().setScale(6).
		 * multiply(etatstockpf.getPrixGratuit().setScale(6)));;
		 * 
		 * // ajouter la quantite gratuité et le prix gratuité(prix d'achat)
		 * 
		 * 
		 * listEtatStockPF.set(k, etatstockpf); }
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * }else {
		 * 
		 * 
		 * JOptionPane.showMessageDialog(null,
		 * "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		 * return; }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */}
}
