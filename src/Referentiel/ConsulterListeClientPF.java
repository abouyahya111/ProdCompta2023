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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import util.JasperUtils;
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
import dao.entity.EtatVenteParFamilleArticle;
import dao.entity.Magasin;
import dao.entity.Utilisateur;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class ConsulterListeClientPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleClient;
	private ImageIcon imgModifier;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	
	private  ClientPFDAO clientPFDAO;
	private JTextField txtcodeClient;
	
	private Map< String, ClientPF> mapClient = new HashMap<>();
	private Map< String, CompteClient> mapcompte = new HashMap<>();
	private List<CompteClient> listCompte =new ArrayList<CompteClient>();
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	private Map< String, CategorieMp> mapCategorieMp = new HashMap<>();
	private List < ClientPF> listeClient = new ArrayList<ClientPF>();
	private List < ClientPF> listeClientImprimer = new ArrayList<ClientPF>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private JComboBox comboClient = new JComboBox();
	private JComboBox comboTypeClient = new JComboBox();
	private JComboBox combocompte = new JComboBox();
	
	private ClientPF clientPF = new ClientPF();
	private DepotDAO depotDAO;
	private CompteClientDAO compteClientDAO;
	private Utilisateur utilisateur;
	private JTable table;
	JComboBox comboDepot = new JComboBox();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterListeClientPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1385, 565);
        try{
        	compteClientDAO=new CompteClientDAOImpl();
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
        AutoCompleteDecorator.decorate(comboClient);
        
        //comboDepot.addItem("");
        utilisateur=AuthentificationView.utilisateur;
     
     
	      
	 	
				  		  btnModifier = new JButton("Imprimer");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

				  		  	
				  		  	Map parameters = new HashMap();
				  		  	if(listeClientImprimer.size()!=0)
				  		  	{
				  		  	 Depot depot = mapDepot.get(comboDepot.getSelectedItem().toString());
				  		  		if(depot!=null)
				  		  		{
				  		  		parameters.put("depot",depot.getLibelle());
				  		  		}
				  		  		
							JasperUtils.imprimerListeClientPF(listeClientImprimer,parameters);
							
							 }else
							 {
								 JOptionPane.showMessageDialog(null, "Il n'existe auccun Enregistrement ", "Erreur", JOptionPane.ERROR_MESSAGE); 
							 }
				  		  	
				  		  	
				  		  	
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(551, 514, 126, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(452, 79, 112, 26);
				  		 add(btnInitialiser);
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(10, 116, 1160, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Client :");
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 1160, 57);
				  		   			add(layeredPane_1);
				  		   			
				  		   		
					  		      comboClient.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	txtcodeClient.setText(mapLibelle.get(comboClient.getSelectedItem()));
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
							
							
							AutoCompleteDecorator.decorate(comboClient);
				  		   			comboClient.setBounds(294, 12, 263, 26);
				  		   			layeredPane_1.add(comboClient);
				  		   			
				  		   			txtcodeClient = new JTextField();
				  		   		util.Utils.copycoller(txtcodeClient);
				  		   			txtcodeClient.setBounds(74, 12, 146, 26);
				  		   			layeredPane_1.add(txtcodeClient);
				  		   			txtcodeClient.setColumns(10);
				  		   			
				  		   			JLabel lblLibelle = new JLabel("Nom :");
				  		   			lblLibelle.setBounds(242, 11, 130, 26);
				  		   			layeredPane_1.add(lblLibelle);
				  		   			lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			JLabel lblCode = new JLabel("Code :");
				  		   			lblCode.setBounds(10, 11, 114, 26);
				  		   			layeredPane_1.add(lblCode);
				  		   			lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			JLabel label = new JLabel("D\u00E9pot  :");
				  		   			label.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			label.setBounds(567, 11, 114, 26);
				  		   			layeredPane_1.add(label);
				  		   			
				  		   			 comboDepot = new JComboBox();
				  		   			comboDepot.setBounds(620, 12, 191, 26);
				  		   			layeredPane_1.add(comboDepot);
				  		   			
				  		   			JLabel label_1 = new JLabel("Type Client  :");
				  		   			label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
				  		   			label_1.setBounds(820, 12, 114, 26);
				  		   			layeredPane_1.add(label_1);
				  		   			
				  		   		comboTypeClient = new JComboBox();
				  		   	comboTypeClient.setBounds(919, 13, 191, 26);
				  		   			layeredPane_1.add(comboTypeClient);
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane();
				  		   			scrollPane.setBounds(20, 148, 1150, 355);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			scrollPane.setViewportView(table);
				  		   			table.setColumnSelectionAllowed(true);
				  		   			table.setCellSelectionEnabled(true);
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Code", "Nom", "Adresse", "Email", "Num Tel", "ICE", "Type Client"
				  		   				}
				  		   			));
				  		   			
				  		   			JButton btnChercherEmploye = new JButton("Chercher");
				  		   			btnChercherEmploye.setBounds(326, 79, 116, 26);
				  		   			add(btnChercherEmploye);
				  		   			btnChercherEmploye.setIcon(imgChercher);
				  		   			btnChercherEmploye.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   				listeClientImprimer.clear();
				  		   					String requete="";
				  		   					
				  		   				 Depot depot = mapDepot.get(comboDepot.getSelectedItem().toString());
				  		   					
				  		   				if(depot==null)
		  		     			JOptionPane.showMessageDialog(null, "Il faut selectionner le depot SVP!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			requete=requete+" codeDepot='"+depot.getCode()+"' ";
		  		     			if(!txtcodeClient.getText().equals("") && !comboClient.getSelectedItem().equals("")) 
		  		     			{
		  		     				requete=requete+" and code='"+txtcodeClient.getText()+"' ";
		  		     			}
		  		     			 
		  		     			 if(!comboTypeClient.getSelectedItem().equals(""))
		  		     			 {
		  		     				 
		  		     				requete=requete+" and typeClient='"+comboTypeClient.getSelectedItem()+"' "; 
		  		     			 }
		  		     			listeClientImprimer=clientPFDAO.findListClientByRequete(requete);

		  		     		 afficher_tableClient(listeClientImprimer);
		  		     		 
		  		     		 
		  		     		 
		  		     		 
		  		     		 }
				  		   					
				  		   				}
				  		   			});
				  		   			
					  		    txtcodeClient.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboClient.setSelectedItem(mapCode.get(txtcodeClient.getText()));
								  		}}});
				  		   			
					  		  comboDepot.addItem("");
					  		  
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
					  				  Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
					  				  if(depot!=null)
					  				  {
					  					  comboDepot.addItem(depot.getLibelle());
					  					
					  						mapDepot.put(depot.getLibelle(), depot);
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
				  		      	
				  		      	
				  		      	
				  		
				  		 
	}
	
	
	
	void afficher_tableClient(List<ClientPF> listClientPF)
	{
		
		

		modeleClient =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code", "Nom", "Adresse", "Email", "Num Tel", "ICE", "Type Client"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleClient);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listClientPF.size())
			{	
				ClientPF clientPF=listClientPF.get(i);
				
				Object []ligne={clientPF.getCode(),clientPF.getNom(),clientPF.getAdresse(),clientPF.getEmail(),clientPF.getNumTel(),clientPF.getIce(),clientPF.getTypeClient()};

				modeleClient.addRow(ligne);
				i++;
			}
	}

	
void intialiser (){
		
		txtcodeClient.setText("");
		comboClient.setSelectedItem("");;
		comboDepot.setSelectedItem("");
		comboTypeClient.setSelectedItem("");
		
	}
}
