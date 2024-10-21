package Referentiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTitledSeparator;

import com.sun.security.sasl.ClientFactoryImpl;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.Utilisateur;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import java.awt.SystemColor;


public class AjoutClientPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private DefaultTableModel	 modele;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	private  ClientPFDAO clientPFDAO;
	private DepotDAO depotDAO;
	private CompteClientDAO compteClientDAO;
	
	private JTextField txtNom;
	private JTextField txtTel;
	private JComboBox comboDepot = new JComboBox();
	private JComboBox comboTypeClient = new JComboBox();
	
	private Map< String, String> mapDepot = new HashMap<>();
	private Map< String, CompteClient> mapcompte = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<CompteClient> listCompte =new ArrayList<CompteClient>();
	private List<ClientPF> listclient =new ArrayList<ClientPF>();
	private JLabel lblConfirmerMotDe;
	private JTextField txtCode;
	private JLabel lblCodeClient;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JLabel lblCompte;
	private JComboBox comboCompte;
	private Utilisateur utilisateur;
	private JTable table;
	private JLabel lblIce;
	private JTextField txtice;
	private JButton button;
	private JLabel lblPatente;
	private JTextField txtPatente;
	private JLabel lblDelaiDePaiement;
	private JTextField txtDelaiPaiement;
	JRadioButton radiofacture = new JRadioButton("Facture");
	JRadioButton radiobl = new JRadioButton("BL");
	 ButtonGroup group = new ButtonGroup();
	 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutClientPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 849);
        try{
        	compteClientDAO=new CompteClientDAOImpl();
        	clientPFDAO=new ClientPFDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	comboTypeClient = new JComboBox();
        	 utilisateur=AuthentificationView.utilisateur;

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        /*
        listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepot.put(depot.getLibelle(), depot.getCode());
	      			comboDepot.addItem(depot.getLibelle());
	      			i++;
	      		}
	      		*/
        
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
        
        
        
	      	comboTypeClient.addItem("");
	      	comboTypeClient.addItem(CLIENT_CLIENT_INTERNE);
	      	comboTypeClient.addItem(CLIENT_LIBELLE_EXTERNE);
	      	
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
        
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		

				  		   		
					  		   	if(txtNom.getText().equals(""))
			  		     			JOptionPane.showMessageDialog(null, "Il faut saisir : NOM CLIENT!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		
					  		   	else if (comboDepot.getSelectedItem().equals(""))
			  		     			 JOptionPane.showMessageDialog(null, "Il faut choisir : DEPOT !", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		  else if (comboTypeClient.getSelectedItem().equals(""))
			  		     			 JOptionPane.showMessageDialog(null, "Il faut choisir: TYPE CLIENT!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   	
					  		  else if(comboCompte.getSelectedItem().equals(""))
					  		  {
					  			 JOptionPane.showMessageDialog(null, "Il faut choisir: Compte Client!", "Attention", JOptionPane.INFORMATION_MESSAGE);  
					  		  }
					  		   	
					  		   	
					  		   	
			  		     		else {
			  		     			
			  		     			
			  		     			if(!txtDelaiPaiement.getText().equals(""))
							  		{
							  			 
							  			
							  			
							  			
							  			
							  			if(radiobl.isSelected()==false && radiofacture.isSelected()==false)
								  		{
							  				JOptionPane.showMessageDialog(null, "Veuillez Selection Delai De paiement BL Ou Facture SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);  
							  				return;
								  		}
								  		
								  		 
							  			
							  			
							  		}
			  		     			
			  		     			
			  		     			try {
										
									
			  		     			
			  		     			CompteClient compteclient=mapcompte.get(comboCompte.getSelectedItem());
			  		     			int maxidclient=clientPFDAO.maxIdClientPF();
			  		     			String codeClient =Utils.genererCodeReferentiel(maxidclient,mapDepot.get(comboDepot.getSelectedItem()));
			  		     				txtCode.setText(codeClient);
						  		  		ClientPF clientPF = new ClientPF();
						  		  	clientPF.setNom(txtNom.getText());
						  		  clientPF.setNumTel(txtTel.getText());
						  		clientPF.setAdresse(txtAdresse.getText());
						  	 
						  		clientPF.setDateCreation(new Date());
						  		clientPF.setEmail(txtEmail.getText());
						  		  	clientPF.setCodeDepot(mapDepot.get(comboDepot.getSelectedItem()));
						  		  clientPF.setTypeClient(comboTypeClient.getSelectedItem().toString());
						  		clientPF.setCode(codeClient);
						  		clientPF.setUtilCreation(utilCreation);
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
						  			
						  			
						  			
						  		}
						  		
						  		
						  		
						  		
						  		
						  		
						  		  	clientPFDAO.add(clientPF);
						  		  		Utils.incrementerValeurSequenceur(Constantes.CLIENT_LIBELLE);
						  		  		JOptionPane.showMessageDialog(null, "Le client PF a été ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
						  		  		Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
						  		  		listclient=clientPFDAO.findListClientByCodeDepot(depot.getCode());
						  		  		intialiser ();
						  		  	charger();
					  		   		afficher_Client(listclient);
					  		   		
			  		     			} catch (NumberFormatException e2) {
										 JOptionPane.showMessageDialog(null, "Le delai De Paiement Doit etre En Chiffre SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
										 return;
									}
			  		     		 }
				  		  	
				  		  	}
				  		  });
				  		btnAjouter.setIcon(imgAjouter);
				  		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnAjouter.setBounds(257, 410, 114, 26);
				  		 add(btnAjouter);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(381, 410, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(29, 11, 686, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout Nouveau Client");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(30, 23, 1199, 376);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblLogin = new JLabel("N\u00B0 TEL");
				  		   lblLogin.setBounds(397, 105, 130, 26);
				  		   layeredPane.add(lblLogin);
				  		   lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   
				  		   txtTel = new JTextField();
				  		 util.Utils.copycoller(txtTel);
				  		   txtTel.setBounds(484, 105, 191, 26);
				  		   layeredPane.add(txtTel);
				  		   txtTel.setColumns(10);
				  		   txtNom = new JTextField();
				  		 util.Utils.copycoller(txtNom);
				  		   txtNom.setBounds(152, 26, 191, 26);
				  		   layeredPane.add(txtNom);
				  		   txtNom.setColumns(10);
				  		   
				  		   JLabel lblNomClient = new JLabel("Nom Client :");
				  		   lblNomClient.setBounds(7, 25, 114, 26);
				  		   layeredPane.add(lblNomClient);
				  		   lblNomClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Adresse  :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(7, 62, 130, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			
				  		   			comboDepot.setBounds(152, 142, 191, 26);
				  		   			layeredPane.add(comboDepot);
				  		   			
				  		   			JLabel lblDpot = new JLabel("D\u00E9pot :");
				  		   			lblDpot.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblDpot.setBounds(7, 142, 130, 26);
				  		   			layeredPane.add(lblDpot);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Email :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(7, 105, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtCode = new JTextField();
				  		   		util.Utils.copycoller(txtCode);
				  		   			txtCode.setCaretColor(Color.RED);
				  		   			txtCode.setBackground(Color.WHITE);
				  		   			txtCode.setEnabled(false);
				  		   			txtCode.setColumns(10);
				  		   			txtCode.setBounds(484, 26, 191, 26);
				  		   			layeredPane.add(txtCode);
				  		   			
				  		   			lblCodeClient = new JLabel("Code Client :");
				  		   			lblCodeClient.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblCodeClient.setBounds(397, 26, 88, 26);
				  		   			layeredPane.add(lblCodeClient);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   		util.Utils.copycoller(txtAdresse);
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(152, 62, 333, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			txtEmail = new JTextField();
				  		   		util.Utils.copycoller(txtEmail);
				  		   			txtEmail.setColumns(10);
				  		   			txtEmail.setBounds(152, 105, 191, 26);
				  		   			layeredPane.add(txtEmail);
				  		   			
				  		   			JLabel lblTypeClient = new JLabel("Type Client :");
				  		   			lblTypeClient.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblTypeClient.setBounds(397, 142, 130, 26);
				  		   			layeredPane.add(lblTypeClient);
				  		   			
				  		   			
				  		   			comboTypeClient.setBounds(484, 142, 191, 26);
				  		   			layeredPane.add(comboTypeClient);
				  		   			
				  		   			lblCompte = new JLabel("Compte :");
				  		   			lblCompte.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblCompte.setBounds(7, 186, 130, 26);
				  		   			layeredPane.add(lblCompte);
				  		   			
				  		   			comboCompte = new JComboBox();
				  		   			comboCompte.setBounds(152, 186, 191, 26);
				  		   			layeredPane.add(comboCompte);
				  		   			
				  		   			lblIce = new JLabel("ICE");
				  		   			lblIce.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblIce.setBounds(397, 186, 130, 26);
				  		   			layeredPane.add(lblIce);
				  		   			
				  		   			txtice = new JTextField();
				  		   			txtice.setColumns(10);
				  		   			txtice.setBounds(484, 186, 191, 26);
				  		   			layeredPane.add(txtice);
				  		   			
				  		   			lblPatente = new JLabel("PATENTE");
				  		   			lblPatente.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblPatente.setBounds(698, 186, 130, 26);
				  		   			layeredPane.add(lblPatente);
				  		   			
				  		   			txtPatente = new JTextField();
				  		   			txtPatente.setColumns(10);
				  		   			txtPatente.setBounds(785, 186, 191, 26);
				  		   			layeredPane.add(txtPatente);
				  		   			
				  		   			lblDelaiDePaiement = new JLabel("Delai De Paiement :");
				  		   			lblDelaiDePaiement.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblDelaiDePaiement.setBounds(7, 234, 144, 26);
				  		   			layeredPane.add(lblDelaiDePaiement);
				  		   			
				  		   			txtDelaiPaiement = new JTextField();
				  		   			txtDelaiPaiement.setColumns(10);
				  		   			txtDelaiPaiement.setBounds(152, 234, 191, 26);
				  		   			layeredPane.add(txtDelaiPaiement);
				  		   			
				  		   			 radiobl = new JRadioButton("BL");
				  		   			radiobl.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		   			radiobl.setBackground(SystemColor.activeCaption);
				  		   			radiobl.setBounds(371, 234, 71, 29);
				  		   		group.add(radiobl);
				  		   			layeredPane.add(radiobl);
				  		   			
				  		   			 radiofacture = new JRadioButton("Facture");
				  		   			radiofacture.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		   			radiofacture.setBackground(SystemColor.activeCaption);
				  		   			radiofacture.setBounds(461, 234, 107, 29);
				  		   		group.add(radiofacture);
				  		   			layeredPane.add(radiofacture);
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(29, 447, 1200, 365);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Nom Client", "Adresse", "Email", "Depot", "Compte", "Tel", "Type Client","ICE","Patente","Delai Paiement"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   			scrollPane.setViewportView(table);
				  		   		listCompte=compteClientDAO.findListCompteClientByUtilisateur(utilisateur);
				  		      	int j=0;
				  		      	while(j<listCompte.size())
				  		      	{
				  		      		CompteClient compteclient=listCompte.get(j);
				  		      		comboCompte.addItem(compteclient.getLibelle());
				  		      		mapcompte.put(compteclient.getLibelle(), compteclient);
				  		      		j++;
				  		      	}
				  		   			
				  		   		charger();
				  		   		afficher_Client(listclient);
	}
	
	
	void charger()
	{
		
		listclient.clear();
		Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
		listclient.addAll(clientPFDAO.findListClientByCodeDepot(depot.getCode()));
 		
		
	}
	

	
void intialiser (){
		
		txtNom.setText("");
		txtTel.setText("");
		txtAdresse.setText("");
		 
		txtCode.setText("");
		txtEmail.setText("");
		comboDepot.setSelectedItem("");
		comboCompte.setSelectedItem("");
		txtice.setText("");
		txtPatente.setText("");
		txtDelaiPaiement.setText("");
		 group.clearSelection();
	}


void afficher_Client(List<ClientPF> listClient)
{
	
	modele=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nom Client", "Adresse", "Email", "Depot", "Compte", "Tel", "Type Client","ICE","PATENTE","DELAI PAIEMENT","DELAI PAIEMENT PAR DATE"
			}
		){
		  	boolean[] columnEditables = new boolean[] {
			  		false, false, false, false, false,false,false,false,false,false,false
			  	};
		  	
			  	public boolean isCellEditable(int row, int column) {
			  		return columnEditables[column];
			  	}
			  };
		setLayout(null);
	
	
int i=0;
String delaiPaiement="";
String delaiPaiementParDate="";

	while(i<listClient.size()){
		
		  delaiPaiement="";
		  delaiPaiementParDate="";
		  
		ClientPF client=listClient.get(i);
		Depot depot=depotDAO.findByCode(client.getCodeDepot());
		if(client.getDelaiPaiement()!=null)
		{
			delaiPaiement=client.getDelaiPaiement().toString();
		}
		
		if(client.getDelaiPaiementParBlOuFacture()!=null)
		{
			delaiPaiementParDate=client.getDelaiPaiementParBlOuFacture();
		}
		
		
		Object []ligne={client.getNom(),client.getAdresse(),client.getEmail(),depot.getLibelle(),client.getCompteClient().getLibelle(),client.getNumTel(),client.getTypeClient(),client.getIce(),client.getPatente(),delaiPaiement,delaiPaiementParDate};
       modele.addRow(ligne);
              
               
i++;
      
	}
	 table.setModel(modele); 
	 
	 button = new JButton("Imprimer");
	 button.addActionListener(new ActionListener() {
	 	public void actionPerformed(ActionEvent arg0) {
	 		


  		  	
  		  	Map parameters = new HashMap();
  		  	if(listclient.size()!=0)
  		  	{
  		  	 
  		  	 
  		  	 Depot depot=depotDAO.findByCode(AuthentificationView.utilisateur.getCodeDepot());
  		  		parameters.put("depot",depot.getLibelle());
  		  		
  		  		
			JasperUtils.imprimerListeClientPF(listclient,parameters);
			
			 }else
			 {
				 
				 JOptionPane.showMessageDialog(null, "Il n'existe auccun Enregistrement ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 
			 }
  		  	
  		  	
  		  	
  		  	
	 		
	 		
	 		
	 	}
	 });
	 button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 button.setBounds(507, 823, 126, 26);
	 add(button);
	
}
}
