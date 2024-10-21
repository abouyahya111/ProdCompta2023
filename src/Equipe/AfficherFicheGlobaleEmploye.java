package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.FicheEmployeGlobaleDAO;
import dao.entity.CompteurAbsenceEmploye;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.FicheEmployeGlobale;
import dao.entity.Magasin;
import dao.entity.RecapFicheEmploye;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;


public class AfficherFicheGlobaleEmploye extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	private JComboBox comboDepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();

	private List<FicheEmploye> listFicheEmploye=new ArrayList<FicheEmploye>();
	private List<FicheEmployeGlobale> listFicheEmployeGlobale=new ArrayList<FicheEmployeGlobale>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private DepotDAO depotDAO ;
	private Utilisateur utilisateur;
	
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	String message="";
	public AfficherFicheGlobaleEmploye() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1446, 609);
        try{
        	
        	
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	compteurabsenceemployedao=new CompteurAbsenceEmployeDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
					  		     			"Nom Employer", " Horaire ","H Supp 25", "H Supp 50", "Total Cout Delai", "Total Cout Supp 25","Total Cout Supp 50" , "Remise","Nb Absence","Reduction", "Total A Payer"
					  		     			
					  		     	}
					  		  
				  				   ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     		 false,false, false, false, false, false, false, false, false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		 table.setModel(new DefaultTableModel(
					  		 	new Object[][] {
					  		 	},
					  		 	new String[] {
					  		 			"Nom Employer", " Horaire ","H Supp 25", "H Supp 50", "Total Cout Delai", "Total Cout Supp 25","Total Cout Supp 50" , "Remise","Nb Absence","Reduction", "Total A Payer"
					  		 			
					  		 	}
					  		 ) {
					  		 	boolean[] columnEditables = new boolean[] {
					  		 			false, false,false, false, false, false, false, false, false, false,false
					  		 	};
					  		 	public boolean isCellEditable(int row, int column) {
					  		 		return columnEditables[column];
					  		 	}
					  		 });
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1427, 461);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 884, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 12, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Date Fin :");
				  		     	 lblDateFin.setBounds(262, 11, 102, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(828, 12, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listFicheEmploye.clear();
				listFicheEmployeGlobale.clear();
				
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
		
			}else if(!verifierdate())
					{
				JOptionPane.showMessageDialog(null,message, "Erreur", JOptionPane.ERROR_MESSAGE);
					
						return;	
			
				}else {
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					listFicheEmploye=ficheEmployeDAO.findByDateSitutaionGlobale(dateDebutChooser.getDate(), dateFinChooser.getDate(),depot.getId());
					
					
					//calculerTotaux(listFicheEmploye);
					if(listFicheEmploye==null ||  listFicheEmploye.size()==0){
						JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						intialiserTableau();
						
					}else {
					
						afficher_tableMP(listFicheEmploye);
					}
					
					
				}
			
			}
		  }
		);
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 12, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(324, 12, 140, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(542, 12, 73, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(601, 11, 191, 26);
		layeredPane.add(comboDepot);
		
	      
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
	
		JButton btnImprimer = new JButton("Fiche Globale");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  DecimalFormat format = new DecimalFormat("#0.00");
				  
				BigDecimal somme=BigDecimal.ZERO;
				String sommetowords="";
				if(listFicheEmployeGlobale.size()!=0)
				{
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
		  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
					// List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					// FicheEmploye ficheEmploye=listFicheEmployeGlobale.get(0);
					Map parameters = new HashMap();
					parameters.put("dateDu", dateDu);
					parameters.put("dateAu", dateAu);
					
				
					for(int i=0;i<listFicheEmployeGlobale.size();i++)
					{
						
						somme= somme.add(listFicheEmployeGlobale.get(i).getNetapayer()) ; 
						
					}
					
					
					
					sommetowords=ConverterNumberToWords.converter(format.format(somme));
					parameters.put("somme",sommetowords);
					
					
					
					JasperUtils.imprimerFicheEmployeGlobale(listFicheEmployeGlobale,parameters);
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "La list des employés est vide !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				}
					
				
	  		  	
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimer.setBounds(432, 574, 136, 24);
		add(btnImprimer);
		
		JButton btnImprimerBulletinPaie = new JButton("Bulletin Paie");
		btnImprimerBulletinPaie.setIcon(imgImprimer);
		btnImprimerBulletinPaie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	  		  /*	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String dateDu=dateFormat.format(dateDebutChooser.getDate());
	  		  	String dateAu=dateFormat.format(dateFinChooser.getDate());
				// List<FicheEmploye> listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
				 FicheEmploye ficheEmploye=listFicheEmploye.get(0);
				 BigDecimal salaireBrut=mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE_CNSS)*totalHoraire;
				 BigDecimal retenu226=salaireBrut*mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_226)/100;
				 BigDecimal retenu448=salaireBrut*mapParametre.get(PARAMETRE_CODE_TAUX_CNSS_448)/100;
				 BigDecimal totalRetenu=retenu448+retenu226;
				 BigDecimal netApayer=salaireBrut-totalRetenu;
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
				parameters.put("netApayer", netApayer+"");*/
				
				//JasperUtils.imprimerBulletinPaieEmploye(listFicheEmploye,parameters,ficheEmploye.getEmploye().getNom());
				
				//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}
		});
		btnImprimerBulletinPaie.setBounds(575, 574, 114, 24);
		add(btnImprimerBulletinPaie);
			  		 
	}
	
void afficher_tableMP(List<FicheEmploye> listFicheEmploye )
	{
		intialiserTableau();
		totalHoraire=BigDecimal.ZERO;
		  int i=0;
		  int j=0;
		  int k=0;
		    BigDecimal totalAvance=BigDecimal.ZERO;
			BigDecimal totalPrime=BigDecimal.ZERO;
			BigDecimal totalCout=BigDecimal.ZERO;
			BigDecimal totalDu=BigDecimal.ZERO;
			BigDecimal totalCout25=BigDecimal.ZERO;
			BigDecimal totalcout50=BigDecimal.ZERO;
			BigDecimal supp25=BigDecimal.ZERO;
			BigDecimal supp50=BigDecimal.ZERO;
			BigDecimal netapayer=BigDecimal.ZERO;
			boolean trouve=false;
			
			BigDecimal reduction=BigDecimal.ZERO;
			int compteurbsenseEmployernbr=0;
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  DecimalFormat format = new DecimalFormat("#0.00");
			String date="";
			BigDecimal avance=BigDecimal.ZERO;
			NumberFormat nf = new DecimalFormat("0.###");
			
			while(i<listFicheEmploye.size())
			{	
				
				trouve=false;
				k=0;
				  totalAvance=BigDecimal.ZERO;totalPrime=BigDecimal.ZERO; totalCout=BigDecimal.ZERO; totalCout25=BigDecimal.ZERO;totalcout50=BigDecimal.ZERO;
					int sizelist=listFicheEmployeGlobale.size();
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				FicheEmploye fiche = listFicheEmploye.get(i);
				if(sizelist==0)
				{
					FicheEmployeGlobale ficheglobale=new FicheEmployeGlobale();
					ficheglobale.setAvance(fiche.getAvance());
					ficheglobale.setCoutSupp25(fiche.getCoutSupp25());
					ficheglobale.setCoutSupp50(fiche.getCoutSupp50());
					ficheglobale.setHeureSupp25(fiche.getHeureSupp25());
					ficheglobale.setHeureSupp50(fiche.getHeureSupp50());
					ficheglobale.setCoutTotal(fiche.getCoutTotal());
					ficheglobale.setEmploye(fiche.getEmploye());
					ficheglobale.setDelaiEmploye(fiche.getDelaiEmploye());
					ficheglobale.setDateSituation(fiche.getDateSituation());
					ficheglobale.setRemise(fiche.getRemise());
					
					listFicheEmployeGlobale.add(ficheglobale);
				}
				else{
				while(k<sizelist)
					{
				FicheEmployeGlobale FicheEmployeGlobale=listFicheEmployeGlobale.get(k);
					
						if (fiche.getEmploye().getId()==FicheEmployeGlobale.getEmploye().getId())
						{
							
								totalAvance=fiche.getAvance().add(FicheEmployeGlobale.getAvance());
								totalPrime=fiche.getRemise().add(FicheEmployeGlobale.getRemise());
								totalCout=fiche.getCoutTotal().add(FicheEmployeGlobale.getCoutTotal());
								totalHoraire=fiche.getDelaiEmploye().add(FicheEmployeGlobale.getDelaiEmploye());
								totalCout25=fiche.getCoutSupp25().add(FicheEmployeGlobale.getCoutSupp25());
								totalcout50=fiche.getCoutSupp50().add(FicheEmployeGlobale.getCoutSupp50());
								supp25=fiche.getHeureSupp25().add(FicheEmployeGlobale.getHeureSupp25());
								supp50=fiche.getHeureSupp50().add(FicheEmployeGlobale.getHeureSupp50());
								FicheEmployeGlobale.setAvance(totalAvance);								
								FicheEmployeGlobale.setRemise(totalPrime);
								FicheEmployeGlobale.setCoutTotal(totalCout);
								FicheEmployeGlobale.setDelaiEmploye(totalHoraire);
								FicheEmployeGlobale.setCoutSupp25(totalCout25);
								FicheEmployeGlobale.setCoutSupp50(totalcout50);
								FicheEmployeGlobale.setHeureSupp25(supp25);
								FicheEmployeGlobale.setHeureSupp50(supp50);
								
							listFicheEmployeGlobale.set(k, FicheEmployeGlobale);
							
							
						trouve=true;
							
						}
						k++;
					}
				if(trouve==false)
				{
					FicheEmployeGlobale ficheglobale=new FicheEmployeGlobale();
					ficheglobale.setAvance(fiche.getAvance());
					ficheglobale.setCoutSupp25(fiche.getCoutSupp25());
					ficheglobale.setCoutSupp50(fiche.getCoutSupp50());
					ficheglobale.setHeureSupp25(fiche.getHeureSupp25());
					ficheglobale.setHeureSupp50(fiche.getHeureSupp50());
					ficheglobale.setCoutTotal(fiche.getCoutTotal());
					ficheglobale.setEmploye(fiche.getEmploye());
					ficheglobale.setDelaiEmploye(fiche.getDelaiEmploye());
					ficheglobale.setDateSituation(fiche.getDateSituation());
					ficheglobale.setRemise(fiche.getRemise());
					listFicheEmployeGlobale.add(ficheglobale);
				}
					
				}
				
				
				/* date=dateFormat.format(ficheEmploye.getDateSituation());
				/*date=dateFormat.format(ficheEmploye[1]);
				String delai1=nf.format(ficheEmploye[2]);
				String cout1=nf.format(ficheEmploye[3]);
				String remise1=nf.format(ficheEmploye[4]);
				String avance1=nf.format(ficheEmploye[5]);*/
					//avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
				/*	totalAvance=totalAvance+ficheEmploye.getAvance();
					totalPrime=totalPrime+ficheEmploye.getRemise();
					totalCout=totalCout+ficheEmploye.getCoutTotal();
					totalHoraire=totalHoraire+ficheEmploye.getDelaiEmploye();
					totalCout25=totalCout25+ficheEmploye.getCoutSupp25();
					totalcout50=totalcout50+ficheEmploye.getCoutSupp50();
				Object []ligne={ficheEmploye.getId(),date,ficheEmploye.getEmploye().getNom(),ficheEmploye.getDelaiEmploye(),ficheEmploye.getCoutTotal(),ficheEmploye.getRemise(),ficheEmploye.getCoutSupp25(),ficheEmploye.getCoutSupp50(),ficheEmploye.getAvance(),ficheEmploye.getHeureSupp25(),ficheEmploye.getHeureSupp50()};

				modeleMP.addRow( ligne);*/
				i++;
			}
			
			while(j<listFicheEmployeGlobale.size())
			{	
				 
				
				FicheEmployeGlobale ficheEmployeglobel=listFicheEmployeGlobale.get(j);
				String dateabsent=Utils.genereCodeDateMoisAnnee(dateDebutChooser.getDate());
				date=dateFormat.format(ficheEmployeglobel.getDateSituation());
				CompteurAbsenceEmploye	compteurbsenseEmployer=compteurabsenceemployedao.findByDateAbsencePeriode(dateabsent, listFicheEmployeGlobale.get(j).getEmploye().getId());
				if(compteurbsenseEmployer==null)
				{
					compteurbsenseEmployernbr=0;
				}else
				{
					compteurbsenseEmployernbr=compteurbsenseEmployer.getCompteur();
				}
				
				totalDu=ficheEmployeglobel.getAvance().add(ficheEmployeglobel.getCoutTotal()).add(ficheEmployeglobel.getCoutSupp25()).add(ficheEmployeglobel.getCoutSupp50());
				BigDecimal compteurbsenseEmployernbrtmp=new BigDecimal(compteurbsenseEmployernbr);
				BigDecimal val=new BigDecimal(0.25);
				
				reduction=ficheEmployeglobel.getRemise().multiply(compteurbsenseEmployernbrtmp).multiply(val) ;
				if(compteurbsenseEmployernbr>=4)
				{
					
					netapayer=totalDu.subtract(ficheEmployeglobel.getRemise());
					
				}else if(compteurbsenseEmployernbr<4 && compteurbsenseEmployernbr>0)
				{
					
					netapayer=totalDu.subtract(reduction);
				}
				else if(compteurbsenseEmployernbr==0)
				{
					netapayer=totalDu;
				}
				
				
				if(netapayer.compareTo(BigDecimal.ZERO)<0)
				{
					netapayer=BigDecimal.ZERO;
				}
			
				ficheEmployeglobel.setCompteur(compteurbsenseEmployernbr);
				ficheEmployeglobel.setReduction(reduction);
				ficheEmployeglobel.setNetapayer(netapayer);
				listFicheEmployeGlobale.set(j, ficheEmployeglobel);
			
				Object []ligne={ficheEmployeglobel.getEmploye().getNom(),format.format(ficheEmployeglobel.getDelaiEmploye()),ficheEmployeglobel.getHeureSupp25(),ficheEmployeglobel.getHeureSupp50(),format.format(ficheEmployeglobel.getCoutTotal()),format.format(ficheEmployeglobel.getCoutSupp25()),format.format(ficheEmployeglobel.getCoutSupp50()),format.format(ficheEmployeglobel.getRemise()),ficheEmployeglobel.getCompteur(),format.format(ficheEmployeglobel.getReduction()),format.format(ficheEmployeglobel.getNetapayer())};

				modeleMP.addRow( ligne);
				j++;
			}
			
			
	}



//void validerAvance(List<FicheEmploye> listFicheEmploye){
//	BigDecimal avance=0;
//	BigDecimal totalAvance=0;
//	BigDecimal totalPrime=0;
//	BigDecimal totalCout=0;
//	BigDecimal totalDu=0;
//	for(int i=0;i<listFicheEmploye.size();i++){	
//		
//		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
//		
//		avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
//		
//		ficheEmploye.setAvance(avance);
//		
//		avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
//		totalAvance=totalAvance+avance;
//		totalPrime=totalPrime+ficheEmploye.getRemise();
//		totalCout=totalCout+ficheEmploye.getCoutTotal();
//		
//		ficheEmployeDAO.edit(ficheEmploye);
//		
//	}
//	
//	
//	totalDu=(totalPrime+totalCout)-totalAvance;
//	 DecimalFormat format = new DecimalFormat("#.00");
//	
//	
//	
//}

void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		  		 		"Nom Employer", " Horaire ","H Supp 25", "H Supp 50", "Total Cout Delai", "Total Cout Supp 25","Total Cout Supp 50" , "Remise","Nb Absence","Reduction", "Total A Payer"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     		 false, false, false, false, false, false, false, false,false,false,false
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



boolean verifierdate()
{
	    SimpleDateFormat simpleFormatyear = new SimpleDateFormat("yyyy");
	    SimpleDateFormat simpleFormatMonth = new SimpleDateFormat("MM");
	    SimpleDateFormat simpleFormatDay = new SimpleDateFormat("dd");
	    
	boolean valider=true;
	if(Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate())) < Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate())))
	{
		if(Integer.valueOf(simpleFormatMonth.format(dateDebutChooser.getDate()))==Integer.valueOf(simpleFormatMonth.format(dateFinChooser.getDate())))
		{
			if(Integer.valueOf(simpleFormatyear.format(dateDebutChooser.getDate())).equals(Integer.valueOf(simpleFormatyear.format(dateFinChooser.getDate()))))
			{
				if(Integer.valueOf(simpleFormatMonth.format(dateDebutChooser.getDate()))!=2)
				{
					if((Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==1 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==15) || (Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==16 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==30))
					{
						
						
					}else
					{
						message="Veuillez choisir la date contient 15 jours Exemple : Date debut : 01/01/2018 et Date Fin : 15/01/2018 ou Date debut : 16/01/2018 et Date Fin : 30/01/2018 ";
						
						valider=false;
						
					}
					
				}else if(Integer.valueOf(simpleFormatMonth.format(dateDebutChooser.getDate()))==2)
				{
					
					 if ((Integer.valueOf(simpleFormatyear.format(dateDebutChooser.getDate())) % 400 == 0) || ((Integer.valueOf(simpleFormatyear.format(dateDebutChooser.getDate())) % 4 == 0) && (Integer.valueOf(simpleFormatyear.format(dateDebutChooser.getDate())) % 100 != 0)))
					 { 
						 if((Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==1 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==15) || (Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==16 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==29))
							 
							{
								
							}else
							{
								message="Veuillez choisir la date contient 15 jours Exemple : Date debut : 01/02/2018 et Date Fin : 15/02/2018 ou Date debut : 16/02/2018 et Date Fin : 29/02/2018 ";

								valider=false;
							}
						 
					 }else
					 {
						 if((Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==1 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==15) || (Integer.valueOf(simpleFormatDay.format(dateDebutChooser.getDate()))==16 && Integer.valueOf(simpleFormatDay.format(dateFinChooser.getDate()))==28))
							 
							{
								
								
							}else
							{
								message="Veuillez choisir la date contient 15 jours Exemple : Date debut : 01/02/2018 et Date Fin : 15/02/2018 ou Date debut : 16/02/2018 et Date Fin : 28/02/2018 ";
								valider=false;
							
							}
						
					 }
					
				}
				
				
			}else
			{
				message="L'année debut doit etre le meme année de la date de fin";
				valider=false;
			}
			
		}else
		{
			message= "Le mois de debut doit etre le meme mois de la date de fin";
			valider=false;
		}
		
	}else
	{
		message= "Le jour de date debut doit etre inferieur au jour de date de fin";
		valider=false;
	}
	
	return valider;
}
}
