package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import util.Constantes;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.EmployeBloqueDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.TypeResEmployeDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeBloqueDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.TypeResEmployeDAO;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.EmployeBloque;
import dao.entity.Equipe;
import dao.entity.Magasin;
import dao.entity.TypeResEmploye;
import dao.entity.Utilisateur;

import java.awt.event.MouseAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JCheckBox;


public class AjoutEmploye extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgSupprimer;
	private ImageIcon imgBloquer;
	
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	
	private  EmployeDAO employeDAO;
	private  EquipeDAO equipeDAO;
	private TypeResEmployeDAO typeResEmployeDAO;
	private  EmployeBloqueDAO employeBloqueDAO;
	private DepotDAO depotDAO;
	
	private JTextField txtCode;
	private JTextField txtNomEmploye;
	private JTextField txtTel;
	private JTextField txtAdresse;
	private JTextField txtNumDoss;
	private JTextField txtCoutHoraire;
	JComboBox comboDepot = new JComboBox();
	
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private Map< String, Equipe> mapEquipe = new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, TypeResEmploye> mapTypeResEmploye = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private JComboBox comboEquipe = new JComboBox();
	private JComboBox comboRespon = new JComboBox();
	private JDateChooser dateNaissanceChooser ;
	private JDateChooser dateEntreChooser;
	
	
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private List<Equipe> listEquipe =new ArrayList<Equipe>();
	private List<TypeResEmploye> listTypeResEmploye=new ArrayList<TypeResEmploye>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private Utilisateur utilisateur;
	
	private JTextField txtNumCNSS;
	private JTextField txtService;
	private JTextField txtLieuNaiss;
	private JCheckBox chckbxActif ;
	private JCheckBox checkSalarie;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutEmploye() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	employeDAO=new EmployeDAOImpl();
        	equipeDAO=new EquipeDAOImpl();
        	typeResEmployeDAO=new TypeResEmployeDAOImpl();
        	employeBloqueDAO=new EmployeBloqueDAOImpl();
depotDAO=new DepotDAOImpl();
utilisateur=AuthentificationView.utilisateur;
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
       
        mapParametre=Utils.listeParametre();
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
            imgBloquer = new ImageIcon(this.getClass().getResource("/img/bloquer.png"));
          } catch (Exception exp){exp.printStackTrace();}
        comboEquipe.addItem("");
        comboRespon.addItem("");
        comboDepot.addItem("");
         chckbxActif = new JCheckBox("");
         checkSalarie = new JCheckBox("");
        listEquipe = equipeDAO.findAll();	
	     	
	      int i=0;
	      	while(i<listEquipe.size())
	      		{	
	      			Equipe equipe=listEquipe.get(i);
	      			mapEquipe.put(equipe.getNomEquipe(), equipe);
	      			
	      			comboEquipe.addItem(equipe.getNomEquipe());
	      			i++;
	      		}
	      	
	      	listTypeResEmploye = typeResEmployeDAO.findAll();	
	      	i=0;
	      	while(i<listTypeResEmploye.size())
	      		{	
	      		TypeResEmploye typeResEmploye=listTypeResEmploye.get(i);
	      			mapTypeResEmploye.put(typeResEmploye.getLibelle(), typeResEmploye);
	      			comboRespon.addItem(typeResEmploye.getLibelle());
	      			i++;
	      		}
	      
	      
	      	
	      	
	      	
	      	 txtNomEmploye = new JTextField();
        util.Utils.copycoller(txtNomEmploye);
				  		  btnAjouter = new JButton("Ajouter Employe");
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  	if(txtCode.getText().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le code!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  	else if(comboDepot.getSelectedItem().equals(""))
				  		  	{
				  		  	JOptionPane.showMessageDialog(null, "Il faut remplir le depot!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  	}
		  		     		else {
		  		     			EmployeBloque employeBloque =employeBloqueDAO.findByCode(txtCode.getText());
		  		     			
		  		     			if(employeBloque!=null){
		  		     				
		  		     				JOptionPane.showMessageDialog(null, "Cet empploy� est bloqu� par le syst�me!", "Attention", JOptionPane.ERROR_MESSAGE);
		  		     			}else {
		  		     				if (txtNomEmploye.getText().equals(""))
				  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		else if (comboRespon.getSelectedItem().equals(""))
				  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir la Responsabilit�!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		 else {
				  		     			txtNumDoss.setText(Utils.genererNumDossierEmploye());
				  		     			Employe employe = new Employe();
				  		  		Equipe equipe =mapEquipe.get(comboEquipe.getSelectedItem());
				  		  	employe.setDateEntre(dateEntreChooser.getDate());
			  		     	employe.setService(SERVICE_EMPLOYE);
			  		     	employe.setNumCNSS(txtNumCNSS.getText());
			  		     	employe.setActif(chckbxActif.isSelected());
			  		     	employe.setAdresse(txtAdresse.getText());
	  		     			employe.setBlocageEmploye(Constantes.CODE_NON);
	  		     			employe.setDateCreation(new Date());
	  		     			employe.setMatricule(txtCode.getText());
	  		     			employe.setNom(txtNomEmploye.getText());
	  		     			employe.setNumTel(txtTel.getText());
	  		     			employe.setResponsabilite(comboRespon.getSelectedItem().toString());
	  		     			employe.setTypeResEmploye(mapTypeResEmploye.get(comboRespon.getSelectedItem()));
	  		     			employe.setNumDossier(txtNumDoss.getText());
	  		     			employe.setUtilCreation(AuthentificationView.utilisateur);
	  		     			employe.setDateCreation(new Date());
	  		     			employe.setDateNaissance(dateNaissanceChooser.getDate());
			  		     	employe.setEquipe(equipe);
			  		     	employe.setLieuNaissance(txtLieuNaiss.getText());
			  		     	employe.setSalarie(checkSalarie.isSelected());	
			  		     	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
			  		     	employe.setDepot(depot);
			  		     	if(checkSalarie.isSelected()){
			  		     		employe.setCoutHoraire(BigDecimal.ZERO);
			  		     	}else{
			  		     		employe.setCoutHoraire(mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE));
			  		     	}
				  		  		employeDAO.add(employe);
				  		  		
				  		  		
				  		  	/*if(equipe !=null){
				  		  		List<Employe> listeEmploye=equipeDAO.findListeEmployeByEquipe(equipe.getId());
				  		  		equipe.setListEmploye(listeEmploye);
				  		  		equipeDAO.edit(equipe) ;
				  		  		
				  		  	}*/
				  		  	
				  		  		JOptionPane.showMessageDialog(null, "L'employ� a �t� ajout� avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				  		  		intialiser ();
				  		  		
				  		     		 }
		  		     			}
		  		     		}
				  		  		
				  		  		}
				  		  });
				  		btnAjouter.setIcon(imgAjouter);
				  		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnAjouter.setBounds(248, 326, 139, 26);
				  		 add(btnAjouter);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(397, 326, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(10, 24, 900, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Employ\u00E9");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 38, 903, 277);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(7, 25, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		  
				  		   txtNomEmploye.setBounds(99, 26, 191, 26);
				  		   layeredPane.add(txtNomEmploye);
				  		   txtNomEmploye.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Matricule :");
				  		   lblCodeLigne.setBounds(7, 62, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  
				  		   			
				  		   			JLabel label = new JLabel("Responsabilti\u00E9 :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(7, 136, 114, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			 
				  		   			comboRespon.setBounds(100, 137, 191, 26);
				  		   			layeredPane.add(comboRespon);
				  		   			
				  		   			JLabel lblCoutHoraire = new JLabel("Cout Horaire :");
				  		   			lblCoutHoraire.setVisible(false);
				  		   			lblCoutHoraire.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblCoutHoraire.setBounds(352, 203, 130, 26);
				  		   			layeredPane.add(lblCoutHoraire);
				  		   			
				  		   			txtCoutHoraire = new JTextField();
				  		   		 util.Utils.copycoller(txtCoutHoraire);
				  		   			txtCoutHoraire.setVisible(false);
				  		   			txtCoutHoraire.setColumns(10);
				  		   			txtCoutHoraire.setBounds(450, 204, 191, 26);
				  		   			layeredPane.add(txtCoutHoraire);
				  		   			
				  		   			JLabel lblNTel = new JLabel("N\u00B0 Tel :");
				  		   			lblNTel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNTel.setBounds(7, 100, 130, 26);
				  		   			layeredPane.add(lblNTel);
				  		   			
				  		   			txtTel = new JTextField();
				  		   		  util.Utils.copycoller(txtTel);
				  		   			txtTel.setColumns(10);
				  		   			txtTel.setBounds(100, 100, 191, 26);
				  		   			layeredPane.add(txtTel);
				  		   			
				  		   			JLabel lblAdresse = new JLabel("Adresse:");
				  		   			lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblAdresse.setBounds(352, 169, 130, 26);
				  		   			layeredPane.add(lblAdresse);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   		 util.Utils.copycoller(txtAdresse);
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(450, 170, 273, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			
				  		   			comboEquipe.setBounds(450, 100, 191, 26);
				  		   			layeredPane.add(comboEquipe);
				  		   			
				  		   			JLabel lblEquipe = new JLabel("Equipe :");
				  		   			lblEquipe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblEquipe.setBounds(350, 99, 130, 26);
				  		   			layeredPane.add(lblEquipe);
				  		   			
				  		   			txtNumDoss = new JTextField();
				  		   		 util.Utils.copycoller(txtNumDoss);
				  		   			txtNumDoss.setForeground(Color.RED);
				  		   			txtNumDoss.setBackground(Color.WHITE);
				  		   			txtNumDoss.setEditable(false);
				  		   			txtNumDoss.setColumns(10);
				  		   			txtNumDoss.setBounds(450, 26, 191, 26);
				  		   			layeredPane.add(txtNumDoss);
				  		   			
				  		   			JLabel lblDateNaissance = new JLabel("Date Naissance :");
				  		   			lblDateNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDateNaissance.setBounds(350, 137, 130, 26);
				  		   			layeredPane.add(lblDateNaissance);
				  		   			
				  		   			 dateNaissanceChooser = new JDateChooser();
				  		   			dateNaissanceChooser.setDateFormatString("dd/MM/yyyy");
				  		   			dateNaissanceChooser.setBounds(450, 136, 181, 26);
				  		   			layeredPane.add(dateNaissanceChooser);
				  		   			
				  		   			JLabel lblNDossier = new JLabel("N\u00B0 Dossier  :");
				  		   			lblNDossier.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNDossier.setBounds(350, 26, 130, 26);
				  		   			layeredPane.add(lblNDossier);
				  		   			
				  		   			 dateEntreChooser = new JDateChooser();
				  		   			dateEntreChooser.setDateFormatString("dd/MM/yyyy");
				  		   			dateEntreChooser.setBounds(101, 169, 181, 26);
				  		   			layeredPane.add(dateEntreChooser);
				  		   			
				  		   			JLabel lblDateEntre = new JLabel("Date Entr\u00E9e:");
				  		   			lblDateEntre.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDateEntre.setBounds(6, 169, 130, 26);
				  		   			layeredPane.add(lblDateEntre);
				  		   			
				  		   			txtNumCNSS = new JTextField();
				  		   		util.Utils.copycoller(txtNumCNSS);
				  		   			txtNumCNSS.setColumns(10);
				  		   			txtNumCNSS.setBounds(99, 204, 191, 26);
				  		   			layeredPane.add(txtNumCNSS);
				  		   			
				  		   			JLabel lblNCnss = new JLabel("N\u00B0 CNSS:");
				  		   			lblNCnss.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNCnss.setBounds(6, 204, 130, 26);
				  		   			layeredPane.add(lblNCnss);
				  		   			
				  		   			JLabel lblService = new JLabel("Service :");
				  		   			lblService.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblService.setBounds(347, 62, 130, 26);
				  		   			layeredPane.add(lblService);
				  		   			txtCode = new JTextField();
				  		   		  util.Utils.copycoller(txtCode);
				  		   			txtCode.setBounds(99, 63, 191, 26);
				  		   			layeredPane.add(txtCode);
				  		   			txtCode.setForeground(Color.BLACK);
				  		   			txtCode.setBackground(Color.WHITE);
				  		   			txtCode.setDisabledTextColor(Color.BLACK);
				  		   			txtCode.setColumns(10);
				  		   			
				  		   			txtService = new JTextField();
				  		   		 util.Utils.copycoller(txtService);
				  		   			txtService.setBounds(450, 63, 191, 26);
				  		   			layeredPane.add(txtService);
				  		   			txtService.setColumns(10);
				  		   			
				  		   			txtLieuNaiss = new JTextField();
				  		   		  util.Utils.copycoller(txtLieuNaiss);
				  		   			txtLieuNaiss.setColumns(10);
				  		   			txtLieuNaiss.setBounds(99, 237, 191, 26);
				  		   			layeredPane.add(txtLieuNaiss);
				  		   			
				  		   			JLabel lblLieuNaissance = new JLabel("Lieu Naissance:");
				  		   			lblLieuNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblLieuNaissance.setBounds(7, 237, 130, 26);
				  		   			layeredPane.add(lblLieuNaissance);
				  		   			
				  		   			
				  		   			chckbxActif.setBounds(854, 58, 28, 18);
				  		   			layeredPane.add(chckbxActif);
				  		   			
				  		   			JLabel lblActif = new JLabel("Actif :");
				  		   			lblActif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblActif.setBounds(756, 57, 87, 19);
				  		   			layeredPane.add(lblActif);
				  		   			
				  		   			
				  		   			checkSalarie.setBounds(854, 25, 28, 18);
				  		   			layeredPane.add(checkSalarie);
				  		   			
				  		   			JLabel lblSalari = new JLabel("Salari\u00E9");
				  		   			lblSalari.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblSalari.setBounds(756, 25, 87, 18);
				  		   			layeredPane.add(lblSalari);
				  		   			
				  		   			 comboDepot = new JComboBox();
				  		   			comboDepot.setBounds(450, 241, 191, 26);
				  		   			layeredPane.add(comboDepot);
				  		   			
				  		   			JLabel lblDepot = new JLabel("Depot :");
				  		   			lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDepot.setBounds(350, 240, 130, 26);
				  		   			layeredPane.add(lblDepot);
				  		   			
				  		   			
				  		   			
				  		   			 listEmploye = employeDAO.findAll();	
					  		     	
					  		       i=0;
					  		      	while(i<listEmploye.size())
					  		      		{	
					  		      			Employe employe=listEmploye.get(i);
					  		      			mapLibelle.put(employe.getNom(), employe.getMatricule());
					  		      			mapCode.put(employe.getMatricule(),employe.getNom());
					  		      			i++;
					  		      		}
					  		      	
					  		  
					  	      
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
	
	
	

	
void intialiser (){
		
		txtCode.setText("");
		txtAdresse.setText("");
		txtCoutHoraire.setText("");
		txtNomEmploye.setText("");
		txtTel.setText("");
		comboEquipe.setSelectedItem("");
		comboRespon.setSelectedItem("");
		txtService.setText("");
		dateEntreChooser.setDate(null);
		dateNaissanceChooser.setDate(null);
		txtNumCNSS.setText("");
		txtLieuNaiss.setText("");
		comboDepot.setSelectedItem("");
		
	}
}
