package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FactureServiceProduction;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Production;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;


public class ConsulterEtatMPConsommerParOF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;
	

	
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<DetailResponsableProd> listEmployeGesnerique=new ArrayList<DetailResponsableProd>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<Magasin> listMagasin=new ArrayList<Magasin>();
	private List<Production> listProduction=new ArrayList<Production>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	  JComboBox comboMagasin = new JComboBox();
	  JCheckBox checkTerminer = new JCheckBox("Termin\u00E9");
	  JCheckBox checkAnnule = new JCheckBox("Annul\u00E9");
	  
	  private FactureServiceProductionDAO factureServiceProductionDAO;
	  
	  
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterEtatMPConsommerParOF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	depotdao=new DepotDAOImpl();
        	factureServiceProductionDAO=new FactureServiceProductionDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.setShowVerticalLines(false);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		     table.setRowHeightEnabled(true);
				  		     table.setBackground(new Color(255, 255, 255));
				  		     table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table.setColumnControlVisible(true);
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);
				  		     
				  		   modeleProd =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Date","Num Facture", "Num OF", "Statut","Code Article","Article","Code MP","MP","Quantite Consomme","Quantite Dechet","Quantite Dechet","Quantite Offre"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,false,false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleProd); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(60);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					         table.getColumnModel().getColumn(3).setPreferredWidth(60);
					         table.getColumnModel().getColumn(4).setPreferredWidth(60);
					         table.getColumnModel().getColumn(5).setPreferredWidth(160);
					         table.getColumnModel().getColumn(6).setPreferredWidth(60);
					         table.getColumnModel().getColumn(7).setPreferredWidth(160);
					         table.getColumnModel().getColumn(8).setPreferredWidth(60);
					         table.getColumnModel().getColumn(9).setPreferredWidth(60);
					         table.getColumnModel().getColumn(10).setPreferredWidth(60);
					         table.getColumnModel().getColumn(11).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1325, 529);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Date","Num Facture", "Num OF", "Statut","Code Article","Article","Code MP","MP","Quantite Consomme","Quantite Dechet","Quantite Dechet","Quantite Offre"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false,false,false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 569, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1325, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(10, 11, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(158, 10, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1101, 10, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String statut="";
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else
			
			{
				
				if(checkAnnule.isSelected()==true && checkTerminer.isSelected()==false)
				{
					statut=Constantes.ETAT_OF_ANNULER;
					
				}
				
				if(checkAnnule.isSelected()==false && checkTerminer.isSelected()==true)
				{
					statut=Constantes.ETAT_OF_TERMINER;
					
				}
				
				
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				
				if(depot==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Depot SVP !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				if(magasin==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
          listCoutMP.clear();
          listCoutMP=productionDAO.listeCoutMPBYDateByMagasin(dateDebutChooser.getDate(), dateFinChooser.getDate(), statut, magasin);
    // listProduction=productionDAO.listeProductionTerminerbyDepotEntreDeuxDate(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode());
afficher_tableProd(listCoutMP);
				
				
				
				
				
				
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(37, 11, 111, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(191, 11, 124, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepot.setBounds(325, 11, 51, 24);
		layeredPane.add(lblDepot);
		
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
	     					
	     					
	     					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_MP);
		     				if(listMagasin.size()!=0)
		     				{
		     					comboMagasin.removeAllItems();
		     					comboMagasin.addItem("");
		     					while(i<listMagasin.size())
  		     				{
  		     					Magasin magasin=listMagasin.get(i);
  		     					comboMagasin.addItem(magasin.getLibelle());
  		     					mapMagasin.put(magasin.getLibelle(), magasin);
  		     					i++;
  		     				}
		     				}else
		     				{
		     					comboMagasin.removeAllItems();
		     					
		     				}	
	     					
	     					
	     					
	     					
	     				}
		 				
		 				
		 				
	     			}
		 			 
		 			 
		 			 
		 			 
		 			 
    	 		 }
		 		
		 	}
		 });
		combodepot.setBounds(373, 12, 149, 24);
		layeredPane.add(combodepot);
		
		JButton btnAfficherDetailOF = new JButton("Exporter Excel");
		btnAfficherDetailOF.setIcon(imgExcel);
		btnAfficherDetailOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			

			  		

		      		

					
				  Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				  
				  if(magasin!=null) {
					  
					if(table.getRowCount()!=0)
   					{
						
					  String
					  titre="Etat Quantite MP Consommer Par Les OF "+mapMagasin.get(comboMagasin.getSelectedItem()).getLibelle(); 
					  String titrefeuille="Etat Quantite MP Consommer Par Les OF";
					  
					  
					  ExporterTableVersExcel.tabletoexcelEtatMPConsommerParOF (table, titre,titrefeuille);
					  
   					}
				  
			
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	
			  		
			  		
			  		
			  		
			  		
			  	
			
			}
		});
		btnAfficherDetailOF.setBounds(464, 630, 174, 24);
		add(btnAfficherDetailOF);
		
		
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
		      
		  }else{
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem("");
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		 
		  
		  combodepot.setSelectedItem("");;
		  
		  JLabel lblMagasin = new JLabel("Magasin :");
		  lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblMagasin.setBounds(545, 10, 59, 24);
		  layeredPane.add(lblMagasin);
		  
		   comboMagasin = new JComboBox();
		  comboMagasin.setSelectedIndex(-1);
		  comboMagasin.setBounds(601, 11, 222, 24);
		  layeredPane.add(comboMagasin);
		  
		   checkTerminer = new JCheckBox("Termin\u00E9");
		  checkTerminer.setBounds(833, 12, 97, 23);
		  layeredPane.add(checkTerminer);
		  
		   checkAnnule = new JCheckBox("Annul\u00E9");
		  checkAnnule.setBounds(934, 12, 97, 23);
		  layeredPane.add(checkAnnule);
		
		
	
				  		     
				  		 
	}
	
void afficher_tableProd(List<CoutMP> listCoutMP)
	{
		intialiserTableau();
		
		String numFacture="";
		 
			for (int i=0;i<listCoutMP.size();i++)
			{	numFacture="";
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				CoutMP coutMP=listCoutMP.get(i);
				
				if(coutMP.getProdcutionCM().getNumFacture()!=null)
				{
					numFacture=coutMP.getProdcutionCM().getNumFacture();
					
				}else
				{
					
					FactureServiceProduction factureServiceProduction=factureServiceProductionDAO.findByNumOF(coutMP.getProdcutionCM().getNumOF());
					
					if(factureServiceProduction!=null)
					{
						
						numFacture=factureServiceProduction.getNumFacture();
						
					}
					
					
				}
				
			
				
				
				if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(Constantes.UNITE_PIECE))
				{
					
					Object []ligne={coutMP.getProdcutionCM().getDate(),numFacture,coutMP.getProdcutionCM().getNumOF(),coutMP.getProdcutionCM().getStatut(),coutMP.getProdcutionCM().getArticles().getCodeArticle(),coutMP.getProdcutionCM().getArticles().getLiblle(),coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),coutMP.getQuantConsomme().setScale(0, BigDecimal.ROUND_DOWN),coutMP.getQuantDechet().setScale(0, BigDecimal.ROUND_DOWN),coutMP.getQuantiteOffre().setScale(0, BigDecimal.ROUND_DOWN)};
					modeleProd.addRow( ligne);
					
					
				}else
				{
					Object []ligne={coutMP.getProdcutionCM().getDate(),numFacture,coutMP.getProdcutionCM().getNumOF(),coutMP.getProdcutionCM().getStatut(),coutMP.getProdcutionCM().getArticles().getCodeArticle(),coutMP.getProdcutionCM().getArticles().getLiblle(),coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),coutMP.getQuantConsomme(),coutMP.getQuantDechet(),coutMP.getQuantiteOffre()};
					modeleProd.addRow( ligne);
				}
				

				
			
			}
			
		
	}








void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date","Num Facture", "Num OF", "Statut","Code Article","Article","Code MP","MP","Quantite Consomme","Quantite Dechet","Quantite Offre"

		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleProd); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);
      table.getColumnModel().getColumn(3).setPreferredWidth(60);
      table.getColumnModel().getColumn(4).setPreferredWidth(60);
      table.getColumnModel().getColumn(5).setPreferredWidth(160);
      table.getColumnModel().getColumn(6).setPreferredWidth(60);
      table.getColumnModel().getColumn(7).setPreferredWidth(160);
      table.getColumnModel().getColumn(8).setPreferredWidth(60);
      table.getColumnModel().getColumn(9).setPreferredWidth(60);
      table.getColumnModel().getColumn(10).setPreferredWidth(60);
     
 
}
}
