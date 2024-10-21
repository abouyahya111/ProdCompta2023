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
import util.JasperUtils;
import util.Utils;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
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


public class DetailOF extends JLayeredPane implements Constantes{
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
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<Production> listProduction=new ArrayList<Production>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	private JTextField txtCodeArticle;
	private JTextField txtLibelle;
	private JTextField txtDateProduction;
	private JTextField txtQuantiteDemande;
	private JTextField txtPeriode;
	private JTextField txtQuantiteReel;
	private JTextField txtCoutTotal;
	private JTable tableMP;
	private JTextField txtPrix;
	private JTextField txtCoutTotalMP;
	private JTextField txtTotalCoutOffreMP;
	private JTextField txtTotalCoutDechetMP;
	private JTextField txtTotalCoutQuantiteConsommeMP;
	private JTextField txtTotalCoutDechetFournisseurMP;
	private JTextField txtTotalCoutManquanteMP;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public DetailOF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	depotdao=new DepotDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
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
					  		     			"Num OF","Date", "Depot","Article","Statut"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleProd); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(160);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 573, 338);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Num OF", "Date","Depot","Article","Statut"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false
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
				  		     	layeredPane.setBounds(9, 11, 569, 54);
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
		btnAfficherStock.setBounds(532, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else if(combodepot.getSelectedIndex()==-1)
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else
			
			{
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
          listProduction.clear();
    // listProduction=productionDAO.listeProductionTerminerbyDepotEntreDeuxDate(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode());
afficher_tableProd(listProduction);
				
				
				
				
				
				
				
				
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
		combodepot.setBounds(373, 12, 149, 24);
		layeredPane.add(combodepot);
		
		JButton btnAfficherDetailOF = new JButton("Afficher Detail OF");
		btnAfficherDetailOF.setIcon(imgImprimer);
		btnAfficherDetailOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {/*
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Map parameters = new HashMap();
				BigDecimal coutTotalEmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalEmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalEmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalDechetMP= BigDecimal.ZERO;
				BigDecimal coutTotalDechetFournisseurMP= BigDecimal.ZERO;
				BigDecimal coutTotalOffreMP= BigDecimal.ZERO;
				BigDecimal coutTotalManquanteMP= BigDecimal.ZERO;
				BigDecimal coutTotalCoutQuantiteConsommeMP= BigDecimal.ZERO;
				
				intialiserTableauEmployeGenerique();
				intialiserTableauMP();
				intialiserTableauEmployeProduction();
				
				Production production =listProduction.get(table.getSelectedRow());
				txtCodeArticle.setText(production.getArticles().getCodeArticle());
				txtLibelle.setText(production.getArticles().getLiblle());
				txtDateProduction.setText(String.valueOf(production.getDate_debFabPre()) );
				txtPeriode.setText( production.getPeriode());
				txtQuantiteDemande.setText(String.valueOf(production.getQuantiteEstime()));
				txtQuantiteReel.setText(String.valueOf(production.getQuantiteReel()));
				txtPrix.setText(String.valueOf( production.getCoutTotal().divide(production.getQuantiteReel(), 2, RoundingMode.HALF_UP)));
				txtCoutTotal.setText(String.valueOf(production.getCoutTotal()));
				txtCoutTotalMP.setText(String.valueOf(production.getCoutTotalMP().setScale(2, RoundingMode.HALF_UP).add(production.getCoutDechet().setScale(2, RoundingMode.HALF_UP))));
				
				listCoutMP=production.getListCoutMP();
				listEmployeGesnerique=production.getListDetailResponsableProd();
				listEmployeEmballage=production.getListDetailProdGen();
				listEmployeProduction=production.getDetailProductions();
				
				
				for(int k=0;k<listCoutMP.size();k++)
				{
					
					coutTotalDechetMP=coutTotalDechetMP.add(listCoutMP.get(k).getCoutDechet());
					coutTotalOffreMP=coutTotalOffreMP.add(listCoutMP.get(k).getCoutOffre());
					coutTotalCoutQuantiteConsommeMP=coutTotalCoutQuantiteConsommeMP.add(listCoutMP.get(k).getPrixTotal());
					coutTotalDechetFournisseurMP=coutTotalDechetFournisseurMP.add(listCoutMP.get(k).getCoutDechetFournisseur());
					coutTotalManquanteMP=coutTotalManquanteMP.add(listCoutMP.get(k).getCoutManquante());
				}
				
			
				for(int i=0;i<listEmployeGesnerique.size();i++)
				{
					coutTotalEmployeGenerique=coutTotalEmployeGenerique.add(listEmployeGesnerique.get(i).getCoutTotal());
					coutTotalSupp25EmployeGenerique=coutTotalSupp25EmployeGenerique.add(listEmployeGesnerique.get(i).getCoutSupp25());
					coutTotalSupp50EmployeGenerique=coutTotalSupp50EmployeGenerique.add(listEmployeGesnerique.get(i).getCoutSupp50());
				}
				
				for(int j=0;j<listEmployeProduction.size();j++)
				{
					coutTotalEmployeProduction=coutTotalEmployeProduction.add(listEmployeProduction.get(j).getCoutTotal());
					coutTotalSupp25EmployeProduction=coutTotalSupp25EmployeProduction.add(listEmployeProduction.get(j).getCoutSupp25());
					coutTotalSupp50EmployeProduction=coutTotalSupp50EmployeProduction.add(listEmployeProduction.get(j).getCoutSupp50());
					
				}
				
				for(int j=0;j<listEmployeEmballage.size();j++)
				{
					coutTotalEmployeEmballage=coutTotalEmployeEmballage.add(listEmployeEmballage.get(j).getCoutTotal());
					coutTotalSupp25EmployeEmballage=coutTotalSupp25EmployeEmballage.add(listEmployeEmballage.get(j).getCoutSupp25());
					coutTotalSupp50EmployeEmballage=coutTotalSupp50EmployeEmballage.add(listEmployeEmballage.get(j).getCoutSupp50());
				}
				
				txtCoutTotalEmployeGenerique.setText(String.valueOf(coutTotalEmployeGenerique.setScale(2, RoundingMode.HALF_UP)));
				txtCoutTotalEmployeProduction.setText(String.valueOf(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP)));
				txtCoutTotalEmployeEmballage.setText(String.valueOf(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP)));
				txtTotalCoutDechetMP.setText(String.valueOf(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP)));
				txtTotalCoutOffreMP.setText(String.valueOf(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP)));
				txtTotalCoutDechetFournisseurMP.setText(String.valueOf(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)));
				txtTotalCoutQuantiteConsommeMP.setText(String.valueOf(coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP)));
				txttotalcoutsupp25employeEmballage.setText(String.valueOf(coutTotalSupp25EmployeEmballage.setScale(2, RoundingMode.HALF_UP)));
				txttotalcoutsupp50employeEmballage.setText(String.valueOf(coutTotalSupp50EmployeEmballage.setScale(2, RoundingMode.HALF_UP)));
				txttotalcoutsupp25employegenerique.setText(String.valueOf(coutTotalSupp25EmployeGenerique.setScale(2, RoundingMode.HALF_UP)));
				txttotalcoutsupp50employegenerique.setText(String.valueOf(coutTotalSupp50EmployeGenerique.setScale(2, RoundingMode.HALF_UP)));
				txttotalcoutsupp25employeProduction.setText(String.valueOf(coutTotalSupp25EmployeProduction.setScale(2, RoundingMode.HALF_UP)));
				txttotalcoutsupp50employeProduction.setText(String.valueOf(coutTotalSupp50EmployeProduction.setScale(2, RoundingMode.HALF_UP)));
				txtTotalCoutManquanteMP.setText(String.valueOf(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP)));
				afficher_tableMP(listCoutMP);
				afficher_tableEmployeproduction(listEmployeProduction);
				afficher_tableEmployeGenerique(listEmployeGesnerique);
				afficher_tableEmployeEmballage(listEmployeEmballage);
				
				
				
				parameters.put("depot",depot.getLibelle());
				parameters.put("codearticle",production.getArticles().getCodeArticle());
				parameters.put("libellearticle",production.getArticles().getLiblle());
				parameters.put("dateproduction",production.getDate_debFabPre());
				parameters.put("quantiteestime",production.getQuantiteEstime());
				parameters.put("quantitereel",production.getQuantiteReel());
				parameters.put("periode",production.getPeriode());
				parameters.put("couttotal",production.getCoutTotal());
				parameters.put("prixunitaire",production.getCoutTotal().divide(production.getQuantiteReel(), 2, RoundingMode.HALF_UP));
				parameters.put("listEmployeGenerique",listEmployeGesnerique);
				parameters.put("listEmployeEmballage",listEmployeEmballage);
				parameters.put("listEmployeProd",listEmployeProduction);
				parameters.put("listCoutMP",listCoutMP);
				JasperUtils.imprimerDetailOF(listProduction, parameters);
				
				
			*/
				BigDecimal coutTotalEmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalEmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalEmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalSupp50EmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeProduction= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeGenerique= BigDecimal.ZERO;
				BigDecimal coutTotalSupp25EmployeEmballage= BigDecimal.ZERO;
				BigDecimal coutTotalDechetMP= BigDecimal.ZERO;
				BigDecimal coutTotalDechetFournisseurMP= BigDecimal.ZERO;
				BigDecimal coutTotalOffreMP= BigDecimal.ZERO;
				BigDecimal coutTotalManquanteMP= BigDecimal.ZERO;
				BigDecimal coutTotalCoutQuantiteConsommeMP= BigDecimal.ZERO;
				BigDecimal coutTotal= BigDecimal.ZERO;
				BigDecimal prixUnitaire= BigDecimal.ZERO;
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				
				
				Production production =listProduction.get(table.getSelectedRow());
				listCoutMP=production.getListCoutMP();
				listEmployeGesnerique=production.getListDetailResponsableProd();
				listEmployeProduction=production.getDetailProductions();
				listEmployeEmballage=production.getListDetailProdGen();
				
				txtCodeArticle.setText(production.getArticles().getCodeArticle());
				txtLibelle.setText(production.getArticles().getLiblle());
				txtDateProduction.setText(String.valueOf(production.getDate_debFabPre()) );
				txtPeriode.setText( production.getPeriode());
				txtQuantiteDemande.setText(String.valueOf(production.getQuantiteEstime()));
				txtQuantiteReel.setText(String.valueOf(production.getQuantiteReel()));
				txtPrix.setText(String.valueOf( production.getCoutTotalMP().divide(production.getQuantiteReel(), 6, RoundingMode.HALF_UP)));
				txtCoutTotal.setText(String.valueOf(production.getCoutTotalMP()));
				
				for(int k=0;k<listCoutMP.size();k++)
				{
					
					coutTotalDechetMP=coutTotalDechetMP.add(listCoutMP.get(k).getCoutDechet());
					coutTotalOffreMP=coutTotalOffreMP.add(listCoutMP.get(k).getCoutOffre());
					coutTotalCoutQuantiteConsommeMP=coutTotalCoutQuantiteConsommeMP.add(listCoutMP.get(k).getPrixTotal());
					coutTotalDechetFournisseurMP=coutTotalDechetFournisseurMP.add(listCoutMP.get(k).getCoutDechetFournisseur());
					coutTotalManquanteMP=coutTotalManquanteMP.add(listCoutMP.get(k).getCoutManquante());
				}
				
			
				for(int i=0;i<listEmployeGesnerique.size();i++)
				{
					coutTotalEmployeGenerique=coutTotalEmployeGenerique.add(listEmployeGesnerique.get(i).getCoutTotal());
					coutTotalSupp25EmployeGenerique=coutTotalSupp25EmployeGenerique.add(listEmployeGesnerique.get(i).getCoutSupp25());
					coutTotalSupp50EmployeGenerique=coutTotalSupp50EmployeGenerique.add(listEmployeGesnerique.get(i).getCoutSupp50());
				}
				
				for(int j=0;j<listEmployeProduction.size();j++)
				{
					coutTotalEmployeProduction=coutTotalEmployeProduction.add(listEmployeProduction.get(j).getCoutTotal());
					coutTotalSupp25EmployeProduction=coutTotalSupp25EmployeProduction.add(listEmployeProduction.get(j).getCoutSupp25());
					coutTotalSupp50EmployeProduction=coutTotalSupp50EmployeProduction.add(listEmployeProduction.get(j).getCoutSupp50());
					
				}
				
				for(int j=0;j<listEmployeEmballage.size();j++)
				{
					coutTotalEmployeEmballage=coutTotalEmployeEmballage.add(listEmployeEmballage.get(j).getCoutTotal());
					coutTotalSupp25EmployeEmballage=coutTotalSupp25EmployeEmballage.add(listEmployeEmballage.get(j).getCoutSupp25());
					coutTotalSupp50EmployeEmballage=coutTotalSupp50EmployeEmballage.add(listEmployeEmballage.get(j).getCoutSupp50());
				}
				
				
				
				/**/
				coutTotal=coutTotal.add(coutTotalCoutQuantiteConsommeMP);
				prixUnitaire=coutTotal.divide(production.getQuantiteReel(), 6, RoundingMode.HALF_UP);
				txtCoutTotalMP.setText(String.valueOf(coutTotal));
				
				
				txtTotalCoutDechetMP.setText(String.valueOf(coutTotalDechetMP));
				txtTotalCoutOffreMP.setText(String.valueOf(coutTotalOffreMP));
				txtTotalCoutDechetFournisseurMP.setText(String.valueOf(coutTotalDechetFournisseurMP));
				txtTotalCoutQuantiteConsommeMP.setText(String.valueOf(coutTotalCoutQuantiteConsommeMP));
				
				txtTotalCoutManquanteMP.setText(String.valueOf(coutTotalManquanteMP));
				afficher_tableMP(listCoutMP);
		
				
				
			}
		});
		btnAfficherDetailOF.setBounds(87, 414, 174, 24);
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
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		  gridBagLayout.rowWeights = new double[]{0.0};
		  gridBagLayout.rowHeights = new int[]{0};
		  gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_1.setTitle("Cout Production");
		  titledSeparator_1.setBackground(Color.RED);
		  titledSeparator_1.setBounds(592, 11, 910, 30);
		  add(titledSeparator_1);
		  
		  JLabel lblCodeArticle = new JLabel("Code Article :");
		  lblCodeArticle.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCodeArticle.setBounds(592, 52, 122, 23);
		  add(lblCodeArticle);
		  
		  txtCodeArticle = new JTextField();
		  txtCodeArticle.setEditable(false);
		  txtCodeArticle.setColumns(10);
		  txtCodeArticle.setBounds(710, 49, 111, 30);
		  add(txtCodeArticle);
		  
		  JLabel lblLibelle = new JLabel("Libelle :");
		  lblLibelle.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblLibelle.setBounds(835, 49, 122, 23);
		  add(lblLibelle);
		  
		  txtLibelle = new JTextField();
		  txtLibelle.setEditable(false);
		  txtLibelle.setColumns(10);
		  txtLibelle.setBounds(894, 42, 265, 30);
		  add(txtLibelle);
		  
		  JLabel lblDateProduction = new JLabel("Date Production :");
		  lblDateProduction.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblDateProduction.setBounds(592, 90, 122, 23);
		  add(lblDateProduction);
		  
		  txtDateProduction = new JTextField();
		  txtDateProduction.setEditable(false);
		  txtDateProduction.setColumns(10);
		  txtDateProduction.setBounds(710, 86, 111, 30);
		  add(txtDateProduction);
		  
		  JLabel lblQuantitDemand = new JLabel("Quantit\u00E9 Demand\u00E9:");
		  lblQuantitDemand.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblQuantitDemand.setBounds(831, 90, 174, 23);
		  add(lblQuantitDemand);
		  
		  txtQuantiteDemande = new JTextField();
		  txtQuantiteDemande.setEditable(false);
		  txtQuantiteDemande.setColumns(10);
		  txtQuantiteDemande.setBounds(967, 83, 192, 30);
		  add(txtQuantiteDemande);
		  
		  JLabel lblPeriode = new JLabel("Periode :");
		  lblPeriode.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPeriode.setBounds(592, 124, 122, 23);
		  add(lblPeriode);
		  
		  txtPeriode = new JTextField();
		  txtPeriode.setEditable(false);
		  txtPeriode.setColumns(10);
		  txtPeriode.setBounds(710, 121, 111, 30);
		  add(txtPeriode);
		  
		  JLabel lblQuantitRel = new JLabel("Quantit\u00E9 R\u00E9el :");
		  lblQuantitRel.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblQuantitRel.setBounds(833, 124, 122, 23);
		  add(lblQuantitRel);
		  
		  txtQuantiteReel = new JTextField();
		  txtQuantiteReel.setEditable(false);
		  txtQuantiteReel.setColumns(10);
		  txtQuantiteReel.setBounds(967, 120, 192, 30);
		  add(txtQuantiteReel);
		  
		  JLabel lblCoutTotal = new JLabel("Cout  Total :");
		  lblCoutTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutTotal.setBounds(835, 168, 122, 23);
		  add(lblCoutTotal);
		  
		  txtCoutTotal = new JTextField();
		  txtCoutTotal.setEditable(false);
		  txtCoutTotal.setColumns(10);
		  txtCoutTotal.setBounds(967, 161, 192, 30);
		  add(txtCoutTotal);
		  
		  JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		  gridBagLayout_1.rowWeights = new double[]{0.0};
		  gridBagLayout_1.rowHeights = new int[]{0};
		  gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_2.setTitle("Cout MP");
		  titledSeparator_2.setBackground(Color.RED);
		  titledSeparator_2.setBounds(592, 202, 981, 24);
		  add(titledSeparator_2);
		  
		  JScrollPane scrollPane_1 = new JScrollPane();
		  scrollPane_1.setBounds(592, 237, 981, 136);
		  add(scrollPane_1);
		  
		  tableMP = new JTable();
		  tableMP.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  		"Code MP", "Matiere Premiere","prix unitaire", "Quantite Consomme", "Quantite Dechet", "Quantite Offre","Quantite Dechet Fournisseur", "Quantite Manquante","Cout Quantite Consomme", "Cout Dechet", "Cout Offre","Cout Dechet Fournisseur","Cout Manquante"
		  	}
		  ));
			 tableMP.getColumnModel().getColumn(0).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(1).setPreferredWidth(160);
			 tableMP.getColumnModel().getColumn(2).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(3).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(4).setPreferredWidth(60); 
			 tableMP.getColumnModel().getColumn(5).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(6).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(7).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(8).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(9).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(10).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(11).setPreferredWidth(60);
			 tableMP.getColumnModel().getColumn(12).setPreferredWidth(60);
		   //  tableMP.setModel(modeleMP);
		  scrollPane_1.setViewportView(tableMP);
		  
		  JLabel lblPrix = new JLabel("Prix  :");
		  lblPrix.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblPrix.setBounds(592, 160, 122, 23);
		  add(lblPrix);
		  
		  txtPrix = new JTextField();
		  txtPrix.setEditable(false);
		  txtPrix.setColumns(10);
		  txtPrix.setBounds(710, 157, 111, 30);
		  add(txtPrix);
		  
		  JLabel lblCoutTotalMp = new JLabel("Cout Total MP :");
		  lblCoutTotalMp.setFont(new Font("Tahoma", Font.BOLD, 13));
		  lblCoutTotalMp.setBounds(1028, 426, 111, 27);
		  add(lblCoutTotalMp);
		  
		  txtCoutTotalMP = new JTextField();
		  txtCoutTotalMP.setEditable(false);
		  txtCoutTotalMP.setColumns(10);
		  txtCoutTotalMP.setBounds(1171, 425, 402, 30);
		  add(txtCoutTotalMP);
		  
		  txtTotalCoutOffreMP = new JTextField();
		  txtTotalCoutOffreMP.setEditable(false);
		  txtTotalCoutOffreMP.setColumns(10);
		  txtTotalCoutOffreMP.setBounds(1357, 384, 80, 30);
		  add(txtTotalCoutOffreMP);
		  
		  txtTotalCoutDechetMP = new JTextField();
		  txtTotalCoutDechetMP.setEditable(false);
		  txtTotalCoutDechetMP.setColumns(10);
		  txtTotalCoutDechetMP.setBounds(1272, 384, 80, 30);
		  add(txtTotalCoutDechetMP);
		  
		  txtTotalCoutQuantiteConsommeMP = new JTextField();
		  txtTotalCoutQuantiteConsommeMP.setEditable(false);
		  txtTotalCoutQuantiteConsommeMP.setColumns(10);
		  txtTotalCoutQuantiteConsommeMP.setBounds(1171, 384, 97, 30);
		  add(txtTotalCoutQuantiteConsommeMP);
		  
		  txtTotalCoutDechetFournisseurMP = new JTextField();
		  txtTotalCoutDechetFournisseurMP.setEditable(false);
		  txtTotalCoutDechetFournisseurMP.setColumns(10);
		  txtTotalCoutDechetFournisseurMP.setBounds(1432, 384, 76, 30);
		  add(txtTotalCoutDechetFournisseurMP);
		  
		  txtTotalCoutManquanteMP = new JTextField();
		  txtTotalCoutManquanteMP.setEditable(false);
		  txtTotalCoutManquanteMP.setColumns(10);
		  txtTotalCoutManquanteMP.setBounds(1510, 384, 63, 30);
		  add(txtTotalCoutManquanteMP);
		  
		  JButton btnImprimerDetailOf = new JButton("Imprimer Detail OF");
		  btnImprimerDetailOf.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		try {
		  			BigDecimal prixUnitaire= BigDecimal.ZERO;
			  		BigDecimal coutTotalDechetMP= BigDecimal.ZERO;
					BigDecimal coutTotalDechetFournisseurMP= BigDecimal.ZERO;
					BigDecimal coutTotalOffreMP= BigDecimal.ZERO;
					BigDecimal coutTotalManquanteMP= BigDecimal.ZERO;
					BigDecimal coutTotalCoutQuantiteConsommeMP= BigDecimal.ZERO;
			  		BigDecimal coutTotal= BigDecimal.ZERO;
			  		Production production =listProduction.get(table.getSelectedRow());
			  		Depot depot=mapDepot.get(combodepot.getSelectedItem());
			  		
			  		for(int k=0;k<listCoutMP.size();k++)
					{						
						coutTotalDechetMP=coutTotalDechetMP.add(listCoutMP.get(k).getCoutDechet());
						coutTotalOffreMP=coutTotalOffreMP.add(listCoutMP.get(k).getCoutOffre());
						coutTotalCoutQuantiteConsommeMP=coutTotalCoutQuantiteConsommeMP.add(listCoutMP.get(k).getPrixTotal());
						coutTotalDechetFournisseurMP=coutTotalDechetFournisseurMP.add(listCoutMP.get(k).getCoutDechetFournisseur());
						coutTotalManquanteMP=coutTotalManquanteMP.add(listCoutMP.get(k).getCoutManquante());
					}
			  		coutTotal=coutTotal.add(coutTotalCoutQuantiteConsommeMP);
			  		prixUnitaire=coutTotal.divide(production.getQuantiteReel(), 6, RoundingMode.HALF_UP);
			  		Map parameters = new HashMap();
					parameters.put("depot",depot.getLibelle());
					parameters.put("codearticle",production.getArticles().getCodeArticle());
					parameters.put("libellearticle",production.getArticles().getLiblle());
					parameters.put("dateproduction",production.getDate_debFabPre());
					parameters.put("quantiteestime",production.getQuantiteEstime());
					parameters.put("quantitereel",production.getQuantiteReel());
					parameters.put("periode",production.getPeriode());
					parameters.put("couttotal",coutTotal);
					parameters.put("couttotalMP",coutTotal);
					parameters.put("prixunitaire",prixUnitaire);
					parameters.put("listEmployeGenerique",listEmployeGesnerique);
					parameters.put("listEmployeProd",listEmployeProduction);
					parameters.put("listEmployeEmballage",listEmployeEmballage);
					parameters.put("listCoutMP",listCoutMP);
					JasperUtils.imprimerDetailOF(listCoutMP, parameters);	
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					
				}
		  		
		  			
		  	}
		  });
		  btnImprimerDetailOf.setBounds(288, 415, 174, 24);
		  add(btnImprimerDetailOf);
		
		
	
				  		     
				  		 
	}
	
void afficher_tableProd(List<Production> listProduction)
	{
		intialiserTableau();
		 
			for (int i=0;i<listProduction.size();i++)
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				Production production=listProduction.get(i);
				
				
				Object []ligne={production.getNumOF(),production.getDate_debFabPre(), production.getMagasinPF().getDepot().getLibelle(),production.getArticles().getLiblle(),production.getStatut()};

				modeleProd.addRow( ligne);
			
			}
			
		
	}

void afficher_tableMP(List<CoutMP> listCoutMP)
{
	intialiserTableauMP();;
	 
		for (int i=0;i<listCoutMP.size();i++)
		{	
			
			//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
			CoutMP coutmp=listCoutMP.get(i);
			
			Object []ligne={coutmp.getMatierePremier().getCode() , coutmp.getMatierePremier().getNom(),coutmp.getPrixUnitaire(), coutmp.getQuantConsomme(),coutmp.getQuantDechet(),coutmp.getQuantiteOffre(),coutmp.getQuantDechetFournisseur(),coutmp.getQuantiteManquante(), coutmp.getPrixTotal(), coutmp.getCoutDechet(),coutmp.getCoutOffre(),coutmp.getCoutDechetFournisseur(),coutmp.getCoutManquante()};

			modeleMP.addRow( ligne);
		
		}
		
	
}






void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num OF","Date", "Depot","Article","Statut"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleProd); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(160);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);

 
}


void intialiserTableauMP(){
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
				  		"Code MP", "Matiere Premiere","prix unitaire", "Quantite Consomme", "Quantite Dechet", "Quantite Offre","Quantite Dechet Fournisseur", "Quantite Manquante","Cout Quantite Consomme", "Cout Dechet", "Cout Offre","Cout Dechet Fournisseur","Cout Manquante"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 tableMP.setModel(modeleMP); 
		 tableMP.getColumnModel().getColumn(0).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(1).setPreferredWidth(160);
		 tableMP.getColumnModel().getColumn(2).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(3).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(4).setPreferredWidth(60); 
		 tableMP.getColumnModel().getColumn(5).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(6).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(7).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(8).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(9).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(10).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(11).setPreferredWidth(60);
		 tableMP.getColumnModel().getColumn(12).setPreferredWidth(60);
}
}
