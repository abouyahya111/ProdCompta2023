package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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


public class ChercherEmploye extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgSupprimer;
	private ImageIcon imgBloquer;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	private JTextField matricule;
	
	private JComboBox comboDepot = new JComboBox();
	private  EmployeDAO employeDAO;
	private  EquipeDAO equipeDAO;
	private TypeResEmployeDAO typeResEmployeDAO;
	private  EmployeBloqueDAO employeBloqueDAO;
	
	private JTextField txtCode;
	private JTextField txtNomEmploye;
	private JTextField txtTel;
	private JTextField txtAdresse;
	private JTextField txtNumDoss;
	private JTextField txtCoutHoraire;
	
	
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private Map< String, Equipe> mapEquipe = new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, TypeResEmploye> mapTypeResEmploye = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private JComboBox comboEmploye = new JComboBox();
	private JComboBox comboEquipe = new JComboBox();
	private JComboBox comboRespon = new JComboBox();
	private JDateChooser dateNaissanceChooser ;
	private JDateChooser dateEntreChooser;
	private DepotDAO depotDAO ;
	private Utilisateur utilisateur;
	
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private List<Equipe> listEquipe =new ArrayList<Equipe>();
	private List<TypeResEmploye> listTypeResEmploye=new ArrayList<TypeResEmploye>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	
	private Employe employe = new Employe();
	private JTextField txtNumCNSS;
	private JTextField txtService;
	private JTextField txtNumDossier;
	private JTextField txtLieuNaiss;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ChercherEmploye() {
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
        
        comboEmploye.addItem("");
        comboEquipe.addItem("");
        comboRespon.addItem("");
        
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
        
				  		  btnModifier = new JButton("Modifier Employe");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(employe.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher l'employ� � modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  		Equipe equipeSource=employe.getEquipe();
				  		  		//equipeSource.getListEmploye().remove(employe);
				  		  		//equipeDAO.edit(equipeSource);
				  		  		Equipe equipe =mapEquipe.get(comboEquipe.getSelectedItem());
				  		  		employe.setNumCNSS(txtNumCNSS.getText());
				  		  		employe.setDateEntre(dateEntreChooser.getDate());
				  		  		employe.setService(txtService.getText());
				  		  		employe.setNom(txtNomEmploye.getText());
				  		  		employe.setAdresse(txtAdresse.getText());
				  		  		employe.setCoutHoraire(mapParametre.get(PARAMETRE_CODE_COUT_HORAIRE));
				  		  		employe.setNumTel(txtTel.getText());
				  		  		employe.setEquipe(mapEquipe.get(comboEquipe.getSelectedItem()));
				  		  		employe.setTypeResEmploye(mapTypeResEmploye.get(comboRespon.getSelectedItem()));
				  		  		employe.setDateModification(new Date());
				  		  		employe.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		  		employe.setDateNaissance(dateNaissanceChooser.getDate());
				  		  		employe.setLieuNaissance(txtLieuNaiss.getText());
				  		  		Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				  		    	employe.setDepot(depot);
				  		  		employeDAO.edit(employe);
				  		  		//equipe.addEmploye(employe);
				  		  		//equipeDAO.edit(equipe);
				  		  /*	if(equipeSource !=null){
				  		  		List<Employe> listeEmployeSource=equipeDAO.findListeEmployeByEquipe(equipeSource.getId());
				  		  		equipeSource.setListEmploye(listeEmployeSource);
				  		  		equipeDAO.edit(equipeSource) ;
				  		  	}
				  		  		
				  		  	if(equipe !=null){
				  		  	List<Employe> listeEmploye=equipeDAO.findListeEmployeByEquipe(equipe.getId());
				  		  		equipe.setListEmploye(listeEmploye);
				  		  		equipeDAO.edit(equipe) ;
				  		  		
				  		  	}*/
				  		  	
				  		  		JOptionPane.showMessageDialog(null, "L'employ� a �t� modifi�e avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				  		  		intialiser ();
				  		  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(255, 375, 139, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(404, 375, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JLabel lblCode = new JLabel("Matricule Employ\u00E9 :");
				  		   lblCode.setBounds(24, 25, 114, 26);
				  		   add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 92, 902, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Employ\u00E9");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 105, 903, 268);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(7, 25, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   txtNomEmploye = new JTextField();
				  		 util.Utils.copycoller(txtNomEmploye);
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
				  		   			lblCoutHoraire.setBounds(352, 237, 130, 26);
				  		   			layeredPane.add(lblCoutHoraire);
				  		   			
				  		   			txtCoutHoraire = new JTextField();
				  		   		util.Utils.copycoller(txtCoutHoraire);
				  		   			txtCoutHoraire.setVisible(false);
				  		   			txtCoutHoraire.setColumns(10);
				  		   			txtCoutHoraire.setBounds(450, 237, 191, 26);
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
				  		   			txtCode.setForeground(Color.RED);
				  		   			txtCode.setBackground(Color.WHITE);
				  		   			txtCode.setDisabledTextColor(Color.BLACK);
				  		   			txtCode.setEditable(false);
				  		   			txtCode.setColumns(10);
				  		   			
				  		   			txtService = new JTextField();
				  		   		 util.Utils.copycoller(txtService);
				  		   			txtService.setBounds(450, 63, 191, 26);
				  		   			layeredPane.add(txtService);
				  		   			txtService.setColumns(10);
				  		   			
				  		   			txtLieuNaiss = new JTextField();
				  		   		 util.Utils.copycoller(txtLieuNaiss);
				  		   			txtLieuNaiss.setColumns(10);
				  		   			txtLieuNaiss.setBounds(451, 203, 191, 26);
				  		   			layeredPane.add(txtLieuNaiss);
				  		   			
				  		   			JLabel lblLieuNaissance = new JLabel("Lieu Naissance:");
				  		   			lblLieuNaissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblLieuNaissance.setBounds(348, 203, 130, 26);
				  		   			layeredPane.add(lblLieuNaissance);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 901, 83);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherEmploye = new JButton("Chercher");
				  		   			btnChercherEmploye.setBounds(725, 15, 116, 26);
				  		   			layeredPane_1.add(btnChercherEmploye);
				  		   			btnChercherEmploye.setIcon(imgChercher);
				  		   			
				  		   			
				  		   			
				  		   			 listEmploye = employeDAO.findAll();	
					  		     	
					  		       i=0;
					  		      	while(i<listEmploye.size())
					  		      		{	
					  		      			Employe employe=listEmploye.get(i);
					  		      			mapLibelle.put(employe.getNom(), employe.getMatricule());
					  		      			mapCode.put(employe.getMatricule(),employe.getNom());
					  		      			comboEmploye.addItem(employe.getNom());
					  		      			i++;
					  		      		}
					  		      	
					  		      comboEmploye.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	matricule.setText(mapLibelle.get(comboEmploye.getSelectedItem()));
						  		     	   	txtNumDossier.setText("");
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
							
							
							AutoCompleteDecorator.decorate(comboEmploye);
				  		   			comboEmploye.setBounds(471, 15, 188, 26);
				  		   			layeredPane_1.add(comboEmploye);
				  		   			
				  		   			matricule = new JTextField();
				  		   		util.Utils.copycoller(matricule);
				  		   			matricule.setBounds(124, 15, 191, 26);
				  		   			layeredPane_1.add(matricule);
				  		   			matricule.setColumns(10);
				  		   			
				  		   			JLabel lblLibelle = new JLabel("Nom Employ\u00E9 :");
				  		   			lblLibelle.setBounds(359, 14, 130, 26);
				  		   			layeredPane_1.add(lblLibelle);
				  		   			lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			txtNumDossier = new JTextField();
				  		   		util.Utils.copycoller(txtNumDossier);
				  		   			txtNumDossier.addFocusListener(new FocusAdapter() {
				  		   				@Override
				  		   				public void focusGained(FocusEvent e) {
				  		   				matricule.setText("");
			  		   					comboEmploye.setSelectedItem("");
				  		   				}
				  		   			});
				  		   		
				  		   			txtNumDossier.setColumns(10);
				  		   			txtNumDossier.setBounds(124, 46, 191, 26);
				  		   			layeredPane_1.add(txtNumDossier);
				  		   			
				  		   			JLabel lblNDossier_1 = new JLabel("N\u00B0 Dossier :");
				  		   			lblNDossier_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblNDossier_1.setBounds(10, 46, 114, 26);
				  		   			layeredPane_1.add(lblNDossier_1);
				  		   			
				  		   			 comboDepot = new JComboBox();
				  		   			comboDepot.setBounds(471, 46, 188, 26);
				  		   			layeredPane_1.add(comboDepot);
				  		   			
				  		   			JLabel label_1 = new JLabel("Depot :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(369, 45, 80, 26);
				  		   			layeredPane_1.add(label_1);
				  		   			
				  		   			JButton btnBloquerEmploy = new JButton("Bloquer Employ\u00E9");
				  		   			btnBloquerEmploy.setIcon(imgBloquer);
				  		   			
				  		   			btnBloquerEmploy.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   				if(employe ==null || employe.getId()<=0){
				  		   				JOptionPane.showMessageDialog(null, "Il faut chercher l'employ� � bloquer!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		   				
				  		   				}else {
				  		   				EmployeBloque employeBolque =new EmployeBloque();
				  		   				employeBolque.setDateCreation(new Date());
				  		   				employeBolque.setAdresse(employe.getAdresse());
				  		   				employeBolque.setDateNaissance(employe.getDateNaissance());
				  		   				employeBolque.setMatricule(employe.getMatricule());
				  		   				employeBolque.setNom(employe.getNom());
				  		   				employeBolque.setNumTel(employe.getNumTel());
				  		   				employeBolque.setUtilCreation(AuthentificationView.utilisateur);
				  		   					employe.setActif(false);
				  		   					employe.setEquipe(null);
				  		   					employeDAO.edit(employe);
				  		   					employeBloqueDAO.add(employeBolque);
				  		   				JOptionPane.showMessageDialog(null, "L'employ� a �t� bloqu� avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
						  		  		intialiser ();
				  		   				}
				  		   				}
				  		   			});
				  		   			btnBloquerEmploy.setBounds(526, 375, 139, 26);
				  		   			add(btnBloquerEmploy);
				  		   			
					  		    matricule.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboEmploye.setSelectedItem(mapCode.get(matricule.getText()));
								  			txtNumDossier.setText("");
								  		}}});
				  		   			btnChercherEmploye.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   			/*	if(matricule.getText().equals("") || comboEmploye.getSelectedItem().equals("") || txtNumDossier.getText().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir au crit�re de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {*/
				  		   					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				  		   					
		  		     			  employe = employeDAO.findByCode(matricule.getText(),txtNumDossier.getText(),depot.getId());
		  		     			
		  		     			  
		  		     			  if(employe!=null){
		  		     				  
		  		     				  txtNumCNSS.setText(employe.getNumCNSS());
		  		     				  txtCode.setText(employe.getMatricule());
		  		     				  txtAdresse.setText(employe.getAdresse());
		  		     				  txtNomEmploye.setText(employe.getNom());
		  		     				  txtTel.setText(employe.getNumTel());
		  		     				  txtNumDoss.setText(employe.getNumDossier());
		  		     				  comboRespon.setSelectedItem(employe.getTypeResEmploye().getLibelle());
		  		     				 
		  		     				  dateNaissanceChooser.setDate(employe.getDateNaissance());
		  		     				  dateEntreChooser.setDate(employe.getDateEntre());
		  		     				  txtService.setText(employe.getService());
		  		     				  txtLieuNaiss.setText(employe.getLieuNaissance());
		  		     				  
		  		     				  if(employe.getEquipe()!=null)
		  		     				 comboEquipe.setSelectedItem(employe.getEquipe().getNomEquipe());
		  		     				  else 
		  		     					 comboEquipe.setSelectedItem("");
		  		     			  }else {
		  		     				
		  		     				JOptionPane.showMessageDialog(null, "Il n'existe aucun r�sultat pour ces crit�res de recherche. Merci de v�rifier votre crit�re !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     				intialiser ();
		  		     			  }
		  		     		// }
				  		   					
				  		   				}
				  		   			});
				  		   			
				  		   			
				  		   
						  	      
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
		
		txtNumCNSS.setText("");
		txtNumDossier.setText("");
		txtNumDoss.setText("");
		txtCode.setText("");
		txtAdresse.setText("");
		txtCoutHoraire.setText("");
		txtNomEmploye.setText("");
		txtTel.setText("");
		txtLieuNaiss.setText("");
		comboEquipe.setSelectedItem("");
		comboRespon.setSelectedItem("");
		txtService.setText("");
		dateEntreChooser.setDate(null);
		dateNaissanceChooser.setDate(null);
		
		
	}
}
