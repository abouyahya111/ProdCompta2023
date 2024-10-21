package presentation.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.jdesktop.swingx.JXTitledSeparator;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.SequenceurDAO;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.Magasin;
import dao.entity.Sequenceur;
import dao.entity.Utilisateur;
import javax.swing.JCheckBox;


public class AjoutClient extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	private  ClientDAO clientDAO;
	private DepotDAO depotDAO;
	
	private JTextField txtNom;
	private JTextField txtTel;
	private JComboBox comboDepot = new JComboBox();
	private JComboBox comboTypeClient = new JComboBox();
	
	private Map< String, String> mapDepot = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Client> listClient =new ArrayList<Client>();
	private JLabel lblConfirmerMotDe;
	private JTextField txtCode;
	private JLabel lblCodeClient;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private Utilisateur utilisateur;
	private JLabel lblIce;
	private JTextField txtice;
	JCheckBox checktva = new JCheckBox("");
	private SequenceurDAO sequenceurDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutClient() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 565);
        try{
        	
        	clientDAO=new ClientDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	comboTypeClient = new JComboBox();
        	utilisateur=AuthentificationView.utilisateur;
        	sequenceurDAO=new SequenceurDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion � la base de donn�es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
	      		
	      		Depot depot= depotDAO.findByCode(utilisateur.getCodeDepot());
	      		
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
			  		     		else {
			  		     			Sequenceur  sequenceur=sequenceurDAO.findByLibelle(Constantes.CLIENT_LIBELLE);
			  		     			int valeur=sequenceur.getValeur()+1;
			  		     			String codeClient =Utils.genererCodeReferentiel(valeur,mapDepot.get(comboDepot.getSelectedItem()));
			  		     			
			  		     				txtCode.setText(codeClient);
						  		  		Client client = new Client();
						  		  		client.setNom(txtNom.getText());
						  		  		client.setNumTel(txtTel.getText());
						  		  		client.setAdresse(txtAdresse.getText());
						  		  		client.setDateCreation(new Date());
						  		  		client.setEmail(txtEmail.getText());
						  		  		client.setCodeDepot(mapDepot.get(comboDepot.getSelectedItem()));
						  		  		client.setTypeClient(comboTypeClient.getSelectedItem().toString());
						  		  		client.setCode(codeClient);
						  		  		client.setUtilCreation(utilCreation);
						  		    	client.setIce(txtice.getText());
						  		    	client.setTva(checktva.isSelected());
						  		  		clientDAO.add(client);						  		  		
						  		  		Utils.incrementerValeurSequenceur(Constantes.CLIENT_LIBELLE);
						  		  		JOptionPane.showMessageDialog(null, "Le client a �t� ajout� avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
						  		  		listClient=clientDAO.findAll();
						  		  		intialiser ();
			  		     		 }
				  		  	
				  		  	}
				  		  });
				  		btnAjouter.setIcon(imgAjouter);
				  		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnAjouter.setBounds(260, 250, 114, 26);
				  		 add(btnAjouter);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(384, 250, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(29, 11, 686, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout Nouveau Client");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(30, 23, 685, 216);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblLogin = new JLabel("N\u00B0 TEL");
				  		   lblLogin.setBounds(397, 99, 130, 26);
				  		   layeredPane.add(lblLogin);
				  		   lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   
				  		   txtTel = new JTextField();
				  		 util.Utils.copycoller(txtTel);
				  		   txtTel.setBounds(484, 99, 191, 26);
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
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Adresse :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(7, 62, 130, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			
				  		   			comboDepot.setBounds(152, 136, 191, 26);
				  		   			layeredPane.add(comboDepot);
				  		   			
				  		   			JLabel lblDpot = new JLabel("D\u00E9pot :");
				  		   			lblDpot.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblDpot.setBounds(7, 136, 130, 26);
				  		   			layeredPane.add(lblDpot);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Email :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(7, 99, 144, 26);
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
				  		   			txtAdresse.setBounds(152, 62, 523, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			txtEmail = new JTextField();
				  		   		util.Utils.copycoller(txtEmail);
				  		   			txtEmail.setColumns(10);
				  		   			txtEmail.setBounds(152, 99, 191, 26);
				  		   			layeredPane.add(txtEmail);
				  		   			
				  		   			JLabel lblTypeClient = new JLabel("Type Client :");
				  		   			lblTypeClient.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblTypeClient.setBounds(397, 136, 130, 26);
				  		   			layeredPane.add(lblTypeClient);
				  		   			
				  		   			
				  		   			comboTypeClient.setBounds(484, 136, 191, 26);
				  		   			layeredPane.add(comboTypeClient);
				  		   			
				  		   			lblIce = new JLabel("ICE :");
				  		   			lblIce.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblIce.setBounds(7, 173, 130, 26);
				  		   			layeredPane.add(lblIce);
				  		   			
				  		   			txtice = new JTextField();
				  		   			txtice.setColumns(10);
				  		   			txtice.setBounds(152, 173, 191, 26);
				  		   			layeredPane.add(txtice);
				  		   			
				  		   			JLabel lblTva = new JLabel("TVA :");
				  		   			lblTva.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblTva.setBounds(397, 173, 68, 26);
				  		   			layeredPane.add(lblTva);
				  		   			
				  		   			 checktva = new JCheckBox("");
				  		   			checktva.setBounds(484, 175, 195, 23);
				  		   			layeredPane.add(checktva);
	}

	
void intialiser (){
		
		txtNom.setText("");
		txtTel.setText("");
		txtAdresse.setText("");
		txtCode.setText("");
		txtEmail.setText("");
		comboDepot.setSelectedItem("");
		checktva.setSelected(false);
	}
}
