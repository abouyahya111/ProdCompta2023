package Referentiel;

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
import java.math.BigDecimal;
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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import util.Constantes;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.Magasin;
import dao.entity.Utilisateur;
import javax.swing.JRadioButton;
import java.awt.SystemColor;


public class ChercherClientPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private ImageIcon imgModifier;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	
	private  ClientPFDAO clientPFDAO;
	
	
	private JTextField txtCode;
	private JTextField txtNom;
	private JTextField txtcodeClient;
	
	private Map< String, ClientPF> mapClient = new HashMap<>();
	private Map< String, CompteClient> mapcompte = new HashMap<>();
	private List<CompteClient> listCompte =new ArrayList<CompteClient>();
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	private Map< String, CategorieMp> mapCategorieMp = new HashMap<>();
	private List < ClientPF> listeClient = new ArrayList<ClientPF>();
	private Map< String, String> mapDepot = new HashMap<>();
	private JComboBox comboClient = new JComboBox();
	private JComboBox comboDepot = new JComboBox();
	private JComboBox comboTypeClient = new JComboBox();
	private JComboBox combocompte = new JComboBox();

	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtNumTel;
	
	private ClientPF clientPF = new ClientPF();
	private DepotDAO depotDAO;
	private CompteClientDAO compteClientDAO;
	private Utilisateur utilisateur;
	private JTextField txtice;
	private JTextField txtPatente;
	private JTextField txtDelaiPaiement;
	 ButtonGroup group = new ButtonGroup();
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 JRadioButton radiobl = new JRadioButton("BL");
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ChercherClientPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	compteClientDAO=new CompteClientDAOImpl();
        	comboTypeClient = new JComboBox();
        	clientPFDAO=new ClientPFDAOImpl();
        	depotDAO=new DepotDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        comboClient.addItem("");
       
        
        //comboDepot.addItem("");
        utilisateur=AuthentificationView.utilisateur;
     
       
       if(utilisateur.getLogin().equals("admin"))
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
				     		
		     				mapDepot.put(depot.getLibelle(), depot.getCode());
				     	
				     		
			     			
			     		}
		     		}else
		     		{
		     			comboDepot.addItem(depot.getLibelle());
			     		
		     			mapDepot.put(depot.getLibelle(), depot.getCode());
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  comboDepot.addItem(depot.getLibelle());
				
					mapDepot.put(depot.getLibelle(), depot.getCode());
			  }
		  }
     
       Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
        listeClient = clientPFDAO.findListClientByCodeDepot(depot.getCode());	
	     	
	      int i=0;
	      	while(i<listeClient.size())
	      		{	
	      			ClientPF clientPF=listeClient.get(i);
	      		
	      					mapClient.put(clientPF.getNom(), clientPF);
			      			mapLibelle.put(clientPF.getNom(), clientPF.getCode());
				      		mapCode.put(clientPF.getCode(),clientPF.getNom());
			      			
			      			comboClient.addItem(clientPF.getNom());
	      					
	      			
	      			
	      			i++;
	      		}
	      	
	     comboTypeClient.addItem("");
	     comboTypeClient.addItem(CLIENT_CLIENT_INTERNE);
	     comboTypeClient.addItem(CLIENT_LIBELLE_EXTERNE);
	      
	 	
				  		  btnModifier = new JButton("Modifier ");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(clientPF.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher le client � modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  			
				  		  			
				  		  		try {
									
								
				  		  			
				  		  			
				  		  		if(!txtDelaiPaiement.getText().equals(""))
						  		{
						  			 
						  			
						  			
						  			
						  			
						  			if(radiobl.isSelected()==false && radiofacture.isSelected()==false)
							  		{
						  				JOptionPane.showMessageDialog(null, "Veuillez Selection Delai De paiement BL Ou Facture SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);  
						  				return;
							  		}
							  		
							  		 
						  			
						  			
						  		}
				  		  			
				  		  			
				  		  		CompteClient compteclient=mapcompte.get(combocompte.getSelectedItem());
				  		  		clientPF.setNom(txtNom.getText());
				  		  	clientPF.setDateModification(new Date());
				  		  clientPF.setEmail(txtEmail.getText());
				  		clientPF.setNumTel(txtNumTel.getText());
				  		clientPF.setAdresse(txtAdresse.getText());
				  		 
				  		clientPF.setTypeClient(comboTypeClient.getSelectedItem().toString());
				  		clientPF.setUtilisateurMAJ(utilisateur);
				  		clientPF.setCompteClient(compteclient);
				  		clientPF.setIce(txtice.getText());	
				  		clientPF.setPatente(txtPatente.getText());
				  		
						if(!txtDelaiPaiement.getText().equals(""))
				  		{
				  			clientPF.setDelaiPaiement(new BigDecimal(txtDelaiPaiement.getText()));
				  			
				  			
				  			
				  			
				  			if(radiobl.isSelected()==true)
					  		{
					  			clientPF.setDelaiPaiementParBlOuFacture(Constantes.TYPE_BON_LIVRAISON);
					  		}
					  		
					  		if(radiofacture.isSelected()==true)
					  		{
					  			clientPF.setDelaiPaiementParBlOuFacture(TRANSFERE_BL_FACTURE);
					  		}
				  			
				  			
				  			
				  		}else
				  		{
				  			clientPF.setDelaiPaiement(null);
				  			clientPF.setDelaiPaiementParBlOuFacture(null);
				  		}
				  		
				  		
				  		clientPFDAO.edit(clientPF);
				  		  		
				  		  		JOptionPane.showMessageDialog(null, "Le client a �t� modifi�e avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				  		  		intialiser();
				  		  	} catch (Exception e2) {
								 

								 JOptionPane.showMessageDialog(null, "Le delai De Paiement Doit etre En Chiffre SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
								 return;
							
				  		  		
				  		  		
							}	
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(316, 497, 126, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(451, 497, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 92, 902, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Client :");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 119, 1172, 367);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(20, 29, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   
				  		   txtNom = new JTextField();
				  		 util.Utils.copycoller(txtNom);
				  		   txtNom.setBounds(113, 30, 191, 26);
				  		   layeredPane.add(txtNom);
				  		   txtNom.setColumns(10);
				  		   txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		   txtCode.setBackground(Color.WHITE);
				  		   txtCode.setDisabledTextColor(Color.BLACK);
				  		   txtCode.setEditable(false);
				  		   txtCode.setBounds(551, 30, 191, 26);
				  		   layeredPane.add(txtCode);
				  		   txtCode.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Code :");
				  		   lblCodeLigne.setBounds(452, 29, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		  
				  		   			JLabel lblCatgorie = new JLabel("D\u00E9pot  :");
				  		   			lblCatgorie.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblCatgorie.setBounds(20, 144, 114, 26);
				  		   			layeredPane.add(lblCatgorie);
				  		   			
				  		   			 
				  		   			comboDepot.setBounds(113, 145, 191, 26);
				  		   			layeredPane.add(comboDepot);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   		 util.Utils.copycoller(txtAdresse);
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(113, 66, 629, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			txtEmail = new JTextField();
				  		   		 util.Utils.copycoller(txtEmail);
				  		   			txtEmail.setColumns(10);
				  		   			txtEmail.setBounds(113, 108, 191, 26);
				  		   			layeredPane.add(txtEmail);
				  		   			
				  		   			txtNumTel = new JTextField();
				  		   		 util.Utils.copycoller(txtNumTel);
				  		   			txtNumTel.setColumns(10);
				  		   			txtNumTel.setBounds(551, 108, 191, 26);
				  		   			layeredPane.add(txtNumTel);
				  		   			
				  		   			JLabel lblAdresse = new JLabel("Adresse  :");
				  		   			lblAdresse.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblAdresse.setBounds(20, 66, 114, 26);
				  		   			layeredPane.add(lblAdresse);
				  		   			
				  		   			JLabel lblEmail = new JLabel("Email  :");
				  		   			lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblEmail.setBounds(20, 108, 114, 26);
				  		   			layeredPane.add(lblEmail);
				  		   			
				  		   			JLabel lblNTel = new JLabel("N\u00B0 TEL  :");
				  		   			lblNTel.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblNTel.setBounds(452, 108, 114, 26);
				  		   			layeredPane.add(lblNTel);
				  		   			
				  		   		
				  		   			comboTypeClient.setBounds(551, 148, 191, 26);
				  		   			layeredPane.add(comboTypeClient);
				  		   			
				  		   			JLabel lblTypeClient = new JLabel("Type Client  :");
				  		   			lblTypeClient.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			lblTypeClient.setBounds(452, 147, 114, 26);
				  		   			layeredPane.add(lblTypeClient);
				  		   			
				  		   			JLabel label = new JLabel("Compte :");
				  		   			label.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			label.setBounds(10, 180, 78, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			 combocompte = new JComboBox();
				  		   			combocompte.setBounds(113, 180, 191, 26);
				  		   			layeredPane.add(combocompte);
				  		   			
				  		   			JLabel label_1 = new JLabel("ICE");
				  		   			label_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			label_1.setBounds(462, 180, 57, 26);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			txtice = new JTextField();
				  		   			txtice.setColumns(10);
				  		   			txtice.setBounds(551, 180, 191, 26);
				  		   			layeredPane.add(txtice);
				  		   			
				  		   			JLabel label_2 = new JLabel("PATENTE");
				  		   			label_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			label_2.setBounds(778, 180, 130, 26);
				  		   			layeredPane.add(label_2);
				  		   			
				  		   			txtPatente = new JTextField();
				  		   			txtPatente.setColumns(10);
				  		   			txtPatente.setBounds(865, 180, 191, 26);
				  		   			layeredPane.add(txtPatente);
				  		   			
				  		   			 radiofacture = new JRadioButton("Facture");
				  		   			radiofacture.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		   			radiofacture.setBackground(SystemColor.activeCaption);
				  		   			radiofacture.setBounds(464, 225, 107, 29);
				  		   		group.add(radiofacture);
				  		   			layeredPane.add(radiofacture);
				  		   			
				  		   			 radiobl = new JRadioButton("BL");
				  		   			radiobl.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		   			radiobl.setBackground(SystemColor.activeCaption);
				  		   			radiobl.setBounds(374, 225, 71, 29);
				  		   		group.add(radiobl);
				  		   			layeredPane.add(radiobl);
				  		   			
				  		   			txtDelaiPaiement = new JTextField();
				  		   			txtDelaiPaiement.setColumns(10);
				  		   			txtDelaiPaiement.setBounds(155, 225, 191, 26);
				  		   			layeredPane.add(txtDelaiPaiement);
				  		   			
				  		   			JLabel label_3 = new JLabel("Delai De Paiement :");
				  		   			label_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			label_3.setBounds(10, 225, 144, 26);
				  		   			layeredPane.add(label_3);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 901, 70);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherEmploye = new JButton("Chercher");
				  		   			btnChercherEmploye.setBounds(775, 15, 116, 26);
				  		   			layeredPane_1.add(btnChercherEmploye);
				  		   			btnChercherEmploye.setIcon(imgChercher);
				  		   			
				  		   		
					  		      comboClient.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	txtcodeClient.setText(mapLibelle.get(comboClient.getSelectedItem()));
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
							
							
							AutoCompleteDecorator.decorate(comboClient);
				  		   			comboClient.setBounds(471, 15, 263, 26);
				  		   			layeredPane_1.add(comboClient);
				  		   			
				  		   			txtcodeClient = new JTextField();
				  		   		util.Utils.copycoller(txtcodeClient);
				  		   			txtcodeClient.setBounds(124, 15, 191, 26);
				  		   			layeredPane_1.add(txtcodeClient);
				  		   			txtcodeClient.setColumns(10);
				  		   			
				  		   			JLabel lblLibelle = new JLabel("Nom :");
				  		   			lblLibelle.setBounds(419, 14, 130, 26);
				  		   			layeredPane_1.add(lblLibelle);
				  		   			lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			JLabel lblCode = new JLabel("Code :");
				  		   			lblCode.setBounds(60, 14, 114, 26);
				  		   			layeredPane_1.add(lblCode);
				  		   			lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
					  		    txtcodeClient.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboClient.setSelectedItem(mapCode.get(txtcodeClient.getText()));
								  		}}});
				  		   			btnChercherEmploye.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   				if(txtcodeClient.getText().equals("") && comboClient.getSelectedItem().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir au crit�re de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			clientPF = clientPFDAO.findClientByCodeClient(txtcodeClient.getText());
		  		     			  
		  		     			  if(txtcodeClient!=null){
		  		     				  
		  		     				 Depot depot = depotDAO.findByCode(clientPF.getCodeDepot());
		  		     			 
		  		     			txtCode.setText(clientPF.getCode());
		  		     			txtNom.setText(clientPF.getNom());
		  		     			comboDepot.setSelectedItem(depot.getLibelle());
		  		     			txtAdresse.setText(clientPF.getAdresse());
		  		     			
		  		     			
		  		     			if(clientPF.getDelaiPaiement()!=null)
		  		     			{
		  		     				txtDelaiPaiement.setText(clientPF.getDelaiPaiement()+"");
		  		     			}
		  		     			
		  		     			
		  		     			if(clientPF.getDelaiPaiementParBlOuFacture()!=null)
		  		     			{
		  		     				
		  		     			if(	clientPF.getDelaiPaiementParBlOuFacture().equals(Constantes.TYPE_BON_LIVRAISON))
		  		     			{
		  		     				radiobl.setSelected(true);
		  		     			}else if(	clientPF.getDelaiPaiementParBlOuFacture().equals(TRANSFERE_BL_FACTURE))
		  		     			{
		  		     				radiofacture.setSelected(true);
		  		     			}else
		  		     			{
		  		     				group.clearSelection();
		  		     			}
		  		     				
		  		     			}else
		  		     			{
		  		     				group.clearSelection();
		  		     			}
		  		     			
		  		     			
		  		     			if(clientPF.getPatente()!=null)
		  		     			{
		  		     				txtPatente.setText(clientPF.getPatente());
		  		     			}
		  		     			 
		  		     			txtEmail.setText(clientPF.getEmail());
		  		     			comboTypeClient.setSelectedItem(clientPF.getTypeClient());
		  		     			txtNumTel.setText(clientPF.getNumTel());
		  		     			combocompte.setSelectedItem(clientPF.getCompteClient().getLibelle());
		  		     			txtice.setText(clientPF.getIce());
		  		     			  }else {
		  		     				JOptionPane.showMessageDialog(null, "Il n'existe aucun r�sultat pour ces crit�res de recherche. Merci de v�rifier votre crit�re !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     			  }
		  		     		 }
				  		   					
				  		   				}
				  		   			});
				  		   			
				  		   		listCompte=compteClientDAO.findAll();
				  		      	int j=0;
				  		      	while(j<listCompte.size())
				  		      	{
				  		      		CompteClient compteclient=listCompte.get(j);
				  		      		combocompte.addItem(compteclient.getLibelle());
				  		      		mapcompte.put(compteclient.getLibelle(), compteclient);
				  		      		j++;
				  		      	}
				  		
				  		 
	}
	
	
	

	
void intialiser (){
		
		txtCode.setText("");
		txtNom.setText("");
		comboDepot.setSelectedItem("");
		txtAdresse.setText("");
		 
		txtEmail.setText("");
		comboTypeClient.setSelectedItem("");
		combocompte.setSelectedItem("");
		txtNumTel.setText("");
		txtice.setText("");
		txtPatente.setText("");
		txtDelaiPaiement.setText("");
		 group.clearSelection();
		 
	}
}
