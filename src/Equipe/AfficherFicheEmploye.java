package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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

import org.codehaus.groovy.syntax.Reduction;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.entity.CompteurAbsenceEmploye;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.RecapFicheEmploye;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;


public class AfficherFicheEmploye extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	


	private JTextField txtCNI;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<FicheEmploye> listFicheEmploye=new ArrayList<FicheEmploye>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private Utilisateur utilisateur;
	private JTextField txtTotalAvance;
	private JTextField txtTotalPrime;
	private JTextField txtTotalCout;
	private JTextField txtTotalDu;
	private JComboBox comboDepot = new JComboBox();
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private JTextField txtTotalCout25;
	private JTextField txtTotalCout50;
	private JTextField txtNbrAbsence;
	private JTextField txtreduction;
	private DepotDAO depotDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherFicheEmploye() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1321, 609);
        try{
        	
        	
        	ficheEmployeDAO =new FicheEmployeDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	compteurabsenceemployedao=new CompteurAbsenceEmployeDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
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
				  		     
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Code","Date","Nom Employ�", "D�lai", "Cout Horaire", "Prime Productivit�", "Cout Supp 25", "Cout Supp 50","Avance","Heur Supp 25", "Heur Supp 50"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false, false, false, false, false, false, false, false, false, false, false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		 table.setModel(new DefaultTableModel(
					  		 	new Object[][] {
					  		 	},
					  		 	new String[] {
					  		 		"Code", "Date", "Nom Employ\u00E9", "D\u00E9lai", "Cout Horaire", "Prime Productivit\u00E9", "Cout Supp 25", "Cout Supp 50", "Avance", "Heur Supp 25", "Heur Supp 50"
					  		 	}
					  		 ) {
					  		 	boolean[] columnEditables = new boolean[] {
					  		 			false, false, false, false, false, false, false, false, false, false, false
					  		 	};
					  		 	public boolean isCellEditable(int row, int column) {
					  		 		return columnEditables[column];
					  		 	}
					  		 });
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(2).setPreferredWidth(160);
					  		 table.getColumnModel().getColumn(3).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(4).setPreferredWidth(90);
					  		 table.getColumnModel().getColumn(5).setPreferredWidth(90);
					  		 table.getColumnModel().getColumn(6).setPreferredWidth(76);
					  		 table.getColumnModel().getColumn(7).setPreferredWidth(76);
					  		 table.getColumnModel().getColumn(8).setPreferredWidth(76);
					  		 table.getColumnModel().getColumn(9).setPreferredWidth(76);
					  		table.getColumnModel().getColumn(10).setPreferredWidth(76);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1302, 383);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 1053, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	 lblDateFin.setBounds(262, 10, 102, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1012, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else if(txtCNI.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir le N� CNI", "Erreur", JOptionPane.ERROR_MESSAGE);
		//	}else if((dateDebutChooser.getDate().getDay()!=1 && dateFinChooser.getDate().getDay()!=15) ||(dateDebutChooser.getDate().getDay()!=16 && dateFinChooser.getDate().getDay()!=30))
					//{
				//JOptionPane.showMessageDialog(null, "la periode de travail doit etre saisir comme �a : date debut : 01/mois/ann�e date fin 01/mois/ann�e ou date debut : 16/mois/ann�e date fin 30/mois/ann�e ", "Erreur", JOptionPane.ERROR_MESSAGE);
					
						//	return;	
			
			}else if(comboDepot.getSelectedItem().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le depot ", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else {
				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				Employe employe =employeDAO.findByCode(txtCNI.getText(),"",depot.getId());
				if(employe==null){
					JOptionPane.showMessageDialog(null, "Employ� n'existe pas !!", "Erreur", JOptionPane.ERROR_MESSAGE);
					intialiserTableau();
					vider();
					listFicheEmploye.clear();
				}else {
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					
					//calculerTotaux(listFicheEmploye);
					if(listFicheEmploye==null ||  listFicheEmploye.size()==0){
						JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activit� pour cet employ� dans cette p�riode!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						intialiserTableau();
						vider();
						listFicheEmploye.clear();
					}else {
						remplirMapAvance();
						afficher_tableMP(listFicheEmploye);
					}
					
					
				}
			
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 11, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(324, 11, 140, 24);
		layeredPane.add(dateFinChooser);
		
		txtCNI = new JTextField();
		txtCNI.setBounds(555, 12, 130, 22);
		layeredPane.add(txtCNI);
		txtCNI.setColumns(10);
		util.Utils.copycoller(txtCNI);
		JLabel lblMatricule = new JLabel("Matricule:");
		lblMatricule.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMatricule.setBounds(495, 11, 102, 24);
		layeredPane.add(lblMatricule);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(695, 8, 130, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(764, 10, 191, 26);
		layeredPane.add(comboDepot);
		
		JButton btnValiderAvance = new JButton("Valider Avance");
		btnValiderAvance.setIcon(imgValider);
		btnValiderAvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!remplirMapAvance())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir au moins une avance ", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {

				//	validerAvance(listFicheEmploye);
					
					JOptionPane.showMessageDialog(null, "L'avance a �t� valid�e avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnValiderAvance.setBounds(290, 574, 129, 24);
		add(btnValiderAvance);
		
		JLabel lblTotal = new JLabel("Total ");
		lblTotal.setBounds(290, 459, 114, 23);
		add(lblTotal);
		
		txtTotalAvance = new JTextField();
		txtTotalAvance.setBackground(Color.WHITE);
		txtTotalAvance.setEditable(false);
		txtTotalAvance.setBounds(939, 457, 104, 26);
		add(txtTotalAvance);
		txtTotalAvance.setColumns(10);
		
		txtTotalPrime = new JTextField();
		txtTotalPrime.setBackground(Color.WHITE);
		txtTotalPrime.setEditable(false);
		txtTotalPrime.setColumns(10);
		txtTotalPrime.setBounds(575, 457, 97, 26);
		add(txtTotalPrime);
		
		txtTotalCout = new JTextField();
		txtTotalCout.setBackground(Color.WHITE);
		txtTotalCout.setEditable(false);
		txtTotalCout.setColumns(10);
		txtTotalCout.setBounds(431, 459, 104, 26);
		add(txtTotalCout);
		
		JButton btnImprimer = new JButton("Fiche Employ�");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if(listFicheEmploye.size()!=0)
				 {
					 String sommetowords="";
						
			  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
			  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
						 List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
						 
						 FicheEmploye ficheEmploye=listFicheEmploye.get(0);
						Map parameters = new HashMap();
						parameters.put("dateDu", dateDu);
						parameters.put("dateAu", dateAu);
						parameters.put("matricule", ficheEmploye.getEmploye().getMatricule());
						parameters.put("nom", ficheEmploye.getEmploye().getNom());
						
						parameters.put("totalCout", txtTotalCout.getText());
						parameters.put("totalAvance", txtTotalAvance.getText());
						parameters.put("totalPrime", txtTotalPrime.getText());
						parameters.put("totalDu", txtTotalDu.getText());
						
						sommetowords= ConverterNumberToWords.converter(String.valueOf(txtTotalDu.getText()));
						
						
						parameters.put("somme",sommetowords);
						
						JasperUtils.imprimerFicheEmploye(listFicheEmploye,parameters,ficheEmploye.getEmploye().getNom());
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activit� pour cet employ� dans cette p�riode!! ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF export� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimer.setBounds(432, 574, 136, 24);
		add(btnImprimer);
		
		txtTotalDu = new JTextField();
		txtTotalDu.setBackground(Color.WHITE);
		txtTotalDu.setEditable(false);
		txtTotalDu.setColumns(10);
		txtTotalDu.setBounds(431, 537, 104, 26);
		add(txtTotalDu);
		
		JLabel lblTotalDu = new JLabel("Total A payer");
		lblTotalDu.setBounds(290, 537, 97, 26);
		add(lblTotalDu);
		
		JButton btnImprimerBulletinPaie = new JButton("Bulletin Paie");
		btnImprimerBulletinPaie.setIcon(imgImprimer);
		btnImprimerBulletinPaie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
	  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
				 List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
				 FicheEmploye ficheEmploye=listFicheEmploye.get(0);
				 BigDecimal salaireBrut= mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE_CNSS).multiply(totalHoraire);
				 BigDecimal retenu226=salaireBrut.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
				 BigDecimal retenu448=salaireBrut.multiply(mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)).divide(new BigDecimal(100), 6,RoundingMode.HALF_UP);
				 BigDecimal totalRetenu=retenu448.add(retenu226);
				 BigDecimal netApayer=salaireBrut.subtract(totalRetenu);
				String dateEntre=dateFormat.format(ficheEmploye.getEmploye().getDateEntre());
				Map parameters = new HashMap();
				parameters.put("dateDu", dateDu);
				parameters.put("dateAu", dateAu);
				parameters.put("matricule", ficheEmploye.getEmploye().getMatricule());
				parameters.put("nom", ficheEmploye.getEmploye().getNom());
				parameters.put("qualif", ficheEmploye.getEmploye().getTypeResEmploye().getLibelle());
				parameters.put("service", ficheEmploye.getEmploye().getService());
				parameters.put("dateEntre",dateEntre );
				
				parameters.put("nbreHoraire", totalHoraire+"");
				parameters.put("tauxHoraire", mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE_CNSS)+"");
				parameters.put("salaireBrut", salaireBrut+"");
				parameters.put("taux226", mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)+"");
				parameters.put("taux448", mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)+"");
				
				parameters.put("retenu226", retenu226+"");
				parameters.put("retenu448", retenu448+"");
				parameters.put("totalRetenu", totalRetenu+"");
				parameters.put("netApayer", netApayer+"");
				
				JasperUtils.imprimerBulletinPaieEmploye(listFicheEmploye,parameters,ficheEmploye.getEmploye().getNom());
				
				//JOptionPane.showMessageDialog(null, "PDF export� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimerBulletinPaie.setBounds(575, 574, 114, 24);
		add(btnImprimerBulletinPaie);
		
		txtTotalCout25 = new JTextField();
		txtTotalCout25.setEditable(false);
		txtTotalCout25.setColumns(10);
		txtTotalCout25.setBackground(Color.WHITE);
		txtTotalCout25.setBounds(694, 457, 97, 26);
		add(txtTotalCout25);
		
		txtTotalCout50 = new JTextField();
		txtTotalCout50.setEditable(false);
		txtTotalCout50.setColumns(10);
		txtTotalCout50.setBackground(Color.WHITE);
		txtTotalCout50.setBounds(818, 457, 97, 26);
		add(txtTotalCout50);
		
		txtNbrAbsence = new JTextField();
		txtNbrAbsence.setEditable(false);
		txtNbrAbsence.setColumns(10);
		txtNbrAbsence.setBackground(Color.WHITE);
		txtNbrAbsence.setBounds(431, 496, 104, 26);
		add(txtNbrAbsence);
		
		JLabel lblNombreAbsence = new JLabel("Nombre Absence");
		lblNombreAbsence.setBounds(290, 500, 97, 26);
		add(lblNombreAbsence);
		
		txtreduction = new JTextField();
		txtreduction.setEditable(false);
		txtreduction.setColumns(10);
		txtreduction.setBackground(Color.WHITE);
		txtreduction.setBounds(575, 499, 97, 26);
		add(txtreduction);
	
		
	      
      	if(utilisateur.getNom().equals("admin"))
      	{
			  listDepot=depotDAO.findAll();
			  int k=0;
			  comboDepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
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
		     		k++;
		     		
		     	}
		      
		  }else
      	{
      		
      		Depot depot= depotDAO.findByCode(utilisateur.getCodeDepot());
      		
      		if(depot!=null)
      		{
      			comboDepot.addItem(depot.getLibelle());
      			mapDepot.put(depot.getLibelle(), depot);
      		}
      	}
	
	   		     
				  		 
	}
	
void afficher_tableMP(List<FicheEmploye> listFicheEmploye)
	{
		intialiserTableau();
		  int i=0;
		  BigDecimal totalAvance=BigDecimal.ZERO;
			BigDecimal totalPrime=BigDecimal.ZERO;
			BigDecimal totalCout=BigDecimal.ZERO;
			BigDecimal totalDu=BigDecimal.ZERO;
			BigDecimal totalCout25=BigDecimal.ZERO;
			BigDecimal totalcout50=BigDecimal.ZERO;
			int compteurbsenseEmployernbr=0;
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  
			String date="";
			BigDecimal avance=BigDecimal.ZERO;
			NumberFormat nf = new DecimalFormat("0.###");
			while(i<listFicheEmploye.size())
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				FicheEmploye ficheEmploye=listFicheEmploye.get(i);
				
				 date=dateFormat.format(ficheEmploye.getDateSituation());
				/*date=dateFormat.format(ficheEmploye[1]);
				String delai1=nf.format(ficheEmploye[2]);
				String cout1=nf.format(ficheEmploye[3]);
				String remise1=nf.format(ficheEmploye[4]);
				String avance1=nf.format(ficheEmploye[5]);*/
					//avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
					totalAvance=totalAvance.add(ficheEmploye.getAvance());
					totalPrime=totalPrime.add(ficheEmploye.getRemise());
					totalCout=totalCout.add(ficheEmploye.getCoutTotal());
					totalHoraire=totalHoraire.add(ficheEmploye.getDelaiEmploye());
					totalCout25=totalCout25.add(ficheEmploye.getCoutSupp25());
					totalcout50=totalcout50.add(ficheEmploye.getCoutSupp50());
				Object []ligne={ficheEmploye.getId(),date,ficheEmploye.getEmploye().getNom(),ficheEmploye.getDelaiEmploye(),ficheEmploye.getCoutTotal(),ficheEmploye.getRemise(),ficheEmploye.getCoutSupp25(),ficheEmploye.getCoutSupp50(),ficheEmploye.getAvance(),ficheEmploye.getHeureSupp25(),ficheEmploye.getHeureSupp50()};

				modeleMP.addRow( ligne);
				i++;
			}
			
			String dateabsent=Utils.genereCodeDateMoisAnnee(dateDebutChooser.getDate());
			
			CompteurAbsenceEmploye	compteurbsenseEmployer=compteurabsenceemployedao.findByDateAbsencePeriode(dateabsent, listFicheEmploye.get(0).getEmploye().getId());
			if(compteurbsenseEmployer==null)
			{
				compteurbsenseEmployernbr=0;
			}else
			{
				compteurbsenseEmployernbr=compteurbsenseEmployer.getCompteur();
			}
			totalDu=totalAvance.add(totalCout).add(totalCout25).add(totalcout50).add(totalPrime);
			 DecimalFormat format = new DecimalFormat("#.00");
			//totalDu=(totalPrime+totalCout)-totalAvance;
			txtTotalAvance.setText(totalAvance+"");
			txtTotalCout.setText(format.format(totalCout));
			txtTotalPrime.setText(totalPrime+""); 
			//txtTotalDu.setText(totalDu+"");
			txtTotalCout25.setText(totalCout25+"");
			txtTotalCout50.setText(totalcout50+"");
			txtNbrAbsence.setText(compteurbsenseEmployernbr+"");
			
			

			calculertotalApayer(totalDu, compteurbsenseEmployernbr, totalPrime);
			
				
			
	}

boolean remplirMapAvance(){
	boolean trouve=false;
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("")){
			mapAvance.put(Integer.parseInt(table.getValueAt(j, 0).toString()), table.getValueAt(j, 9).toString());
			trouve=true;
		}else {
			mapAvance.put(Integer.parseInt(table.getValueAt(j, 0).toString()), "0");
		}
		
	}
	return trouve;
}

void validerAvance(List<FicheEmploye> listFicheEmploye){
	BigDecimal avance=BigDecimal.ZERO;
	BigDecimal totalAvance=BigDecimal.ZERO;
	BigDecimal totalPrime=BigDecimal.ZERO;
	BigDecimal totalCout=BigDecimal.ZERO;
	BigDecimal totalDu=BigDecimal.ZERO;
	for(int i=0;i<listFicheEmploye.size();i++){	
		
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		
		avance=new BigDecimal(mapAvance.get(ficheEmploye.getId()));
		
		ficheEmploye.setAvance(avance);
		
		avance=new BigDecimal(mapAvance.get(ficheEmploye.getId()));
		totalAvance=totalAvance.add(avance);
		totalPrime=totalPrime.add(ficheEmploye.getRemise());
		totalCout=totalCout.add(ficheEmploye.getCoutTotal());
		
		ficheEmployeDAO.edit(ficheEmploye);
		
	}
	
	
	totalDu=(totalPrime.add(totalCout)).subtract(totalAvance);
	
	  DecimalFormat format = new DecimalFormat("#0.00");
	txtTotalAvance.setText(totalAvance+"");
	txtTotalCout.setText(totalCout+"");
	txtTotalPrime.setText(totalPrime+""); 
	txtTotalDu.setText(format.format(totalDu));
	
}

void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Date","Nom Employ�", "D�lai", "Cout Horaire", "Prime Productivit�", "Cout Supp 25", "Cout Supp 50","Avance","Heur Supp 25", "Heur Supp 50"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false, false, false, false, false, false, false, false, false, false, false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);
      table.getColumnModel().getColumn(3).setPreferredWidth(60);
      table.getColumnModel().getColumn(4).setPreferredWidth(90);
      table.getColumnModel().getColumn(5).setPreferredWidth(90);
      //table.getColumnModel().getColumn(6).setPreferredWidth(90);
}


void calculertotalApayer(BigDecimal total,int nbabsence,BigDecimal prime)
{
	
	BigDecimal netapayer=BigDecimal.ZERO;
	BigDecimal primeTmp=prime;
	BigDecimal nbabsencetmp=new BigDecimal(nbabsence);
	BigDecimal val=new BigDecimal(0.25);
	BigDecimal reduction=primeTmp.multiply(nbabsencetmp).multiply(val);
		
	if(nbabsence>=4)
	{
		
		netapayer=total.subtract(primeTmp);
		
	}else if(nbabsence<4 && nbabsence>0)
	{
		
		netapayer=total.subtract(reduction);
	}
	else if(nbabsence==0)
	{
		netapayer=total;
	}
	
	
	if(netapayer.compareTo(BigDecimal.ZERO)<0)
	{
		netapayer=BigDecimal.ZERO;
	}
	
	
	 
	txtreduction.setText((reduction.setScale(6)+""));
	 DecimalFormat format = new DecimalFormat("#.00");
	txtTotalDu.setText(format.format(netapayer));
	
	
}

void vider()
{
	txtNbrAbsence.setText("");
	txtreduction.setText("");
	txtTotalAvance.setText("");
	txtTotalCout.setText("");
	txtTotalCout25.setText("");
	txtTotalCout50.setText("");
	txtTotalDu.setText("");
	txtTotalPrime.setText("");
	
	
}



}
