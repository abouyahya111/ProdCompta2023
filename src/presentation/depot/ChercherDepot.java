package presentation.depot;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.MachineDAO;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.Machine;
import dao.entity.Magasin;
import dao.entity.Utilisateur;


public class ChercherDepot extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel modele;
	private DefaultTableModel	 modeleLigneMachine;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	private JTextField code;
	
	
	private  DepotDAO depotDAO;
	private MachineDAO machineDAO; 
	private ClientDAO clientDAO;
	private JTextField NomLigne;
	private List<Magasin> listMagasin = new ArrayList<Magasin>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private Map< String, String> libelleDepotMap = new HashMap<>();
	private Map< String, String> codeDepotMap = new HashMap<>();
	private Map< String, String> mapTypeMagasin = new HashMap<>();
	private Map< String, String> mapMachine = new HashMap<>();
	private Map< String, String> mapCategorieMagasin = new HashMap<>();
	private Map< String, String> mapClient = new HashMap<>();
	
	private Depot depot=new Depot();
	private JTextField txtCodeDepot;
	private JTextField txtLibelleDepot;
	
	private JComboBox comboDepot ;
	private JComboBox comboTypeMagasin = new JComboBox();
	private JComboBox comboMachine = new JComboBox();
	private JComboBox comboCatMagasin = new JComboBox();
	private JComboBox comboClient = new JComboBox();
	private Utilisateur utilisateur;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public ChercherDepot() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	depotDAO=new DepotDAOImpl();
        	machineDAO =new MachineDAOImpl();
        	clientDAO=new ClientDAOImpl();
        	
        	comboClient = new JComboBox();
        	comboMachine = new JComboBox();
        	comboCatMagasin = new JComboBox();
        	
        	
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_MP, Constantes.MAGASIN_CODE_TYPE_MP);
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_PF, Constantes.MAGASIN_CODE_TYPE_PF);
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_MP_ATT, Constantes.MAGASIN_CODE_TYPE_MP_ATT);
        	
        	
        	comboTypeMagasin.addItem("");
        	comboTypeMagasin.addItem(MAGASIN_LIBELLE_TYPE_MP);
        	comboTypeMagasin.addItem(MAGASIN_LIBELLE_TYPE_PF);
        	comboTypeMagasin.addItem(MAGASIN_LIBELLE_TYPE_MP_ATT);
        	
        	comboCatMagasin.addItem("");
        	comboCatMagasin.addItem(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE);
        	comboCatMagasin.addItem(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION);
        	comboCatMagasin.addItem(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE_ATT);
	  		
	  		mapCategorieMagasin.put(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE, MAGASIN_CODE_CATEGORIE_STOCKAGE);
	  		mapCategorieMagasin.put(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION, MAGASIN_CODE_CATEGORIE_PRODUCTION);
	  		mapCategorieMagasin.put(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE_ATT, MAGASIN_CODE_CATEGORIE_STOCKAGE_ATT);
	  		utilisateur=AuthentificationView.utilisateur;
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        
        /*action li� aux listes d�rolantes */
        comboClient.addItem("");
        comboCatMagasin.addItemListener(new ItemListener() {
	     	 	public void itemStateChanged(ItemEvent e) {
	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	 		 
	     	 		 if(comboCatMagasin!=null && !comboCatMagasin.getSelectedItem().equals("") && comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE)){
	     	 			comboMachine.setSelectedItem("");
	     	 			comboMachine.setEnabled(false);
	     	 			comboClient.setEnabled(true);
	     	 			//comboMachine.setVisible(false);
	     	 			//comboClient.setVisible(true);
	     	 			
	     	 		 }else if(comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION)) {
	     	 			comboMachine.setEnabled(true);
	     	 			comboClient.setEnabled(false);
	     	 			comboClient.setSelectedItem("");
	     	 			//comboMachine.setVisible(true);
	     	 			//comboClient.setVisible(false);
	     	 		 }else {
	     	 			comboMachine.setSelectedItem("");
	     	 			comboClient.setSelectedItem("");
	     	 			comboMachine.setEnabled(true);
	     	 			comboClient.setEnabled(true);
	     	 		 }
	  		    
	     	 	 }
	     	 	}
	     	 });
        
        comboTypeMagasin.addItemListener(new ItemListener() {
     	 	public void itemStateChanged(ItemEvent e) {
     	 	
     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
     	 		 
     	 		 if(!comboTypeMagasin.getSelectedItem().equals("") && comboTypeMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_TYPE_PF)){
     	 			comboCatMagasin.setSelectedItem(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE);
     	 			comboCatMagasin.setEnabled(false);
     	 		 }else {
     	 			comboCatMagasin.setSelectedItem("");
     	 			comboCatMagasin.setEnabled(true);
     	 		 }
     	 	
  		    
     	 	 }
     	 	}
     	 });
		
   /* REMPLIR LA LISTE DES MACHINES */     
        String Codedepot = AuthentificationView.utilisateur.getCodeDepot();
        code = new JTextField();	     
        util.Utils.copycoller(code);
  		
  		
  		
  		
  		
  		
  /*		if(Codedepot.equals(CODE_DEPOT_SIEGE)){
  			listMachine= machineDAO.findListMachineByCodeDepot(code.getText());
		   	} else {
		   		listMachine= machineDAO.findListMachineByCodeDepot(Codedepot);
		   	} 
  		
  		comboMachine.addItem("");
		      	for(int i=0;i<listMachine.size();i++)
		      		{	
		      		Machine  machine=listMachine.get(i);
		      			mapMachine.put(machine.getNom() ,machine.getMatricule());
		      			comboMachine.addItem(machine.getNom());
		      			
		      		}*/
		      	
  /* REMPLIR LA LISTE DES CLIENTS */     
		      	  
		 
		      	
		 
/*		 	if(Codedepot.equals(CODE_DEPOT_SIEGE)){
		 		listClient= clientDAO.findAll();
  		   	} else {
  		   	 listClient= clientDAO.findListClientByCodeDepot(Codedepot);
  		   	}
		
		  for(int i=0;i<listClient.size();i++)
		 		{	
			  Client  client=listClient.get(i);
	      			mapClient.put(client.getNom() ,client.getCode());
	      			comboClient.addItem(client.getNom());
	      			
	      		}	  */			
	
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
      
				  		  btnModifier = new JButton("  Modifier");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(depot.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher la d�pot � modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  			
				  		  		//Utils.genererCodeMagasin(listMagasin,depot.getLibelle());
				  		  		
				  		  		depot.setListMagasin(listMagasin);
				  		  		
				  		  		depotDAO.edit(depot);
				  		  		JOptionPane.showMessageDialog(null, "Le d�pot a �t� modifi� avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				  		  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(264, 528, 112, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiserDepot ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(386, 528, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		txtCodeDepot = new JTextField();
				  		 util.Utils.copycoller(txtCodeDepot);
				  		txtCodeDepot.setForeground(Color.BLUE);
				  		txtCodeDepot.setBackground(Color.WHITE);
				  		txtCodeDepot.setEditable(false);
	  		   			txtCodeDepot.setColumns(10);
	  		   			txtCodeDepot.setBounds(99, 11, 191, 26);
	  		   			layeredPane.add(txtCodeDepot);
	  		   			
	  		   			JLabel lblLibelle_1 = new JLabel("Libelle  :");
	  		   			lblLibelle_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	  		   			lblLibelle_1.setBounds(7, 45, 130, 26);
	  		   			layeredPane.add(lblLibelle_1);
	  		   			
	  		   			txtLibelleDepot = new JTextField();
	  		   		util.Utils.copycoller(txtLibelleDepot);
	  		   			txtLibelleDepot.setColumns(10);
	  		   			txtLibelleDepot.setBounds(99, 45, 191, 26);
	  		   			layeredPane.add(txtLibelleDepot);
				  		   
				  		   JLabel lblCode = new JLabel("Code D\u00E9pot: ");
				  		   lblCode.setBounds(24, 25, 114, 26);
				  		   add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		  
				  		   code.setBounds(107, 26, 191, 26);
				  		   add(code);
				  		   code.setColumns(10);
				  		   
				  		   JLabel lblLibelle = new JLabel("Libelle:");
				  		   lblLibelle.setBounds(348, 26, 130, 26);
				  		   add(lblLibelle);
				  		   lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 92, 825, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Magasin");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 106, 826, 411);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom Magasin :");
				  		   lblNom.setBounds(7, 113, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   NomLigne = new JTextField();
				  		 util.Utils.copycoller(NomLigne);
				  		   NomLigne.setBounds(119, 114, 191, 26);
				  		   layeredPane.add(NomLigne);
				  		   NomLigne.setColumns(10);
				  		   
				  		   JButton btnAjoutAligne = new JButton("");
				  		   btnAjoutAligne.setBounds(679, 225, 60, 26);
				  		   layeredPane.add(btnAjoutAligne);
				  		   btnAjoutAligne.setIcon(imgAjouter);
				  		   
				  		   JLabel lblCatMagasin = new JLabel("Cat\u00E9gorieMagasin : ");
				  		   lblCatMagasin.setBounds(7, 150, 114, 26);
				  		   layeredPane.add(lblCatMagasin);
				  		   lblCatMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   JXLabel lblListDes = new JXLabel();
				  		   lblListDes.setBounds(7, 82, 822, 24);
				  		   layeredPane.add(lblListDes);
				  		   lblListDes.setForeground(new Color(255, 69, 0));
				  		   lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		   lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		   lblListDes.setText("List des Magasins");
				  		   
				  		       
				  		   
				  		   
				  		   		table = new JXTable();
				  		   		table.setSelectionBackground(new Color(51, 204, 255));
				  		   		table.setRowHeightEnabled(true);
				  		   		table.setBackground(new Color(255, 255, 255));
				  		   		table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		   		table.setColumnControlVisible(true);
				  		   		table.setForeground(Color.BLACK);
				  		   		table.setGridColor(new Color(0, 0, 255));
				  		   		table.setAutoCreateRowSorter(true);
				  		   		

				  		   		table.setModel(new DefaultTableModel(
				  		   			new Object[][] {
				  		   			},
				  		   			new String[] {
				  		   					"id","Code","Libelle"
				  		   			}
				  		   		) {
				  		   			boolean[] columnEditables = new boolean[] {
				  		   					false,false,false
				  		   			};
				  		   			public boolean isCellEditable(int row, int column) {
				  		   				return columnEditables[column];
				  		   			}
				  		   		});
				  		   		

				  		   			table.setBounds(2, 27, 411, 198);
				  		   			table.setRowHeight(20);
				  		   			
 
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane(table);
				  		   			scrollPane.setBounds(10, 223, 666, 177);
				  		   			layeredPane.add(scrollPane);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			
				  		   			JLabel label = new JLabel("Code: ");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(4, 10, 114, 26);
				  		   			layeredPane.add(label);
				  		   			
				  		   			
				  		   			comboTypeMagasin.setBounds(511, 114, 140, 26);
				  		   			layeredPane.add(comboTypeMagasin);
				  		   			
				  		   			JLabel lblTypeMagasin = new JLabel("Type Magasin : ");
				  		   			lblTypeMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblTypeMagasin.setBounds(391, 113, 114, 26);
				  		   			layeredPane.add(lblTypeMagasin);
				  		   			
				  		   			
				  		   			comboMachine.setBounds(511, 147, 140, 26);
				  		   			layeredPane.add(comboMachine);
				  		   			
				  		   			JLabel lblDpot = new JLabel("Machine :");
				  		   			lblDpot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblDpot.setBounds(391, 146, 130, 26);
				  		   			layeredPane.add(lblDpot);
				  		   			
				  		   			
				  		   			comboCatMagasin.setBounds(119, 150, 191, 26);
				  		   			layeredPane.add(comboCatMagasin);
				  		   			
				  		   			
				  		   			comboClient.setBounds(119, 186, 191, 26);
				  		   			layeredPane.add(comboClient);
				  		   			
				  		   			JLabel lblClient = new JLabel("Client: ");
				  		   			lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblClient.setBounds(7, 185, 114, 26);
				  		   			layeredPane.add(lblClient);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 824, 70);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherMachine = new JButton();
				  		   			btnChercherMachine.setBounds(706, 15, 31, 31);
				  		   			layeredPane_1.add(btnChercherMachine);
				  		   			btnChercherMachine.setIcon(imgRechercher);
				  		   			
				  		   			 comboDepot = new JComboBox();
				  		   			 comboDepot.addItem("");
				  		   		 /* 	 
				  		   		 listDepot =depotDAO.findAll();	
					  		   	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot  depot=listDepot.get(i);
					  		      			libelleDepotMap.put(depot.getLibelle(), depot.getCode());
					  		      			codeDepotMap.put(depot.getCode(),depot.getLibelle());
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
				  			     				libelleDepotMap.put(depot.getLibelle(), depot.getCode());
						  		      			codeDepotMap.put(depot.getCode(),depot.getLibelle());
						  		      			comboDepot.addItem(depot.getLibelle());
				  					     	
				  					     		
				  				     			
				  				     		}
				  			     		}else
				  			     		{
				  			     			libelleDepotMap.put(depot.getLibelle(), depot.getCode());
					  		      			codeDepotMap.put(depot.getCode(),depot.getLibelle());
					  		      			comboDepot.addItem(depot.getLibelle());
				  				     	
				  				     		
				  			     		}
				  			     		k++;
				  			     		
				  			     	}
				  			      
				  			  }else
				  		      	{
				  		      		
				  		      		Depot depot= depotDAO.findByCode(utilisateur.getCodeDepot());
				  		      		
				  		      		if(depot!=null)
				  		      		{
				  		      		libelleDepotMap.put(depot.getLibelle(), depot.getCode());
			  		      			codeDepotMap.put(depot.getCode(),depot.getLibelle());
			  		      			comboDepot.addItem(depot.getLibelle());
				  		      		}
				  		      	}
				  		   		 
				  		   		 
				  		   		 
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {

					  		     	 		 code.setText(libelleDepotMap.get(comboDepot.getSelectedItem()));
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
					  		      
					  		    code.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboDepot.setSelectedItem(codeDepotMap.get(code.getText()));
								  		}}});
							
							
					  		    	AutoCompleteDecorator.decorate(comboDepot);
				  		   			comboDepot.setBounds(437, 15, 188, 26);
				  		   			layeredPane_1.add(comboDepot);
				  		   			btnChercherMachine.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   				if(code.getText().equals("") && comboDepot.getSelectedItem().equals(""))
				  		   					JOptionPane.showMessageDialog(null, "Il faut remplir au crit�re de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			depot = depotDAO.findByCode(code.getText());
		  		     			List<Machine> listMachine=new ArrayList<Machine>();
		  		     	  		List<Client> listClient=new ArrayList<Client>(); 
		  		     	  		//comboCatMagasin = new JComboBox();
		  		     	  		comboClient.removeAllItems();
		  		     	  		comboMachine.removeAllItems();
		  		     	  		
		  		     	  		comboClient.addItem("");
		  		     	  		comboMachine.addItem("");
		  		     	  		
		  		     	  		mapClient = new HashMap<>();
		  		     	  		mapMachine = new HashMap<>();
		  		     	  
		  		     			listMachine= machineDAO.findListMachineByCodeDepot(code.getText());
	  		     	 			listClient= clientDAO.findListClientByCodeDepot(code.getText()); 
	  		     	 			for(int i=0;i<listMachine.size();i++)
	  		     	 				{	
	  		     	 					Machine  machine=listMachine.get(i);
	  		     	 					mapMachine.put(machine.getNom() ,machine.getMatricule());
	  		     	 					comboMachine.addItem(machine.getNom());
		  		  			
	  		     	 				} 
		  		  		
	  		     	 			for(int i=0;i<listClient.size();i++)
	  		     	 				{	
	  		     	 					Client  client=listClient.get(i);
	  		     	 					mapClient.put(client.getNom() ,client.getCode());
	  		     	 					comboClient.addItem(client.getNom());
		  		   			
	  		     	 				}	  
		  		     			  
		  		     			  if(depot!=null){
		  		     			 
		  		     				  		listMagasin=depot.getListMagasin();
		  		     				  		txtCodeDepot.setText(depot.getCode());
		  		     				  		txtLibelleDepot.setText(depot.getLibelle());
		  		     				  		afficher_Magasin(listMagasin);
		  		     			  }else {
		  		     				  		JOptionPane.showMessageDialog(null, "Aucun d�pot existe pour ces crit�res de recherche. Merci de v�rifier votre crit�re !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     			  }
		  		     		 }
				  		   					
				  		   				}
				  		   			});
				  		   			
				  		   		
					  		 
				  		   
				  		     btnAjoutAligne.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     		
				  		     		if(txtCodeDepot.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut Chercher le d�pot!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     		}else {
				  		     			
				  		     		 String codeMagasin =Utils.genererCodeMagasin(Constantes.MAGASIN_LIBELLE,depot.getLibelle());
				  		     			
				  		     		

					  		     	
					  		     	 //CodeLigne.setText(codeMagasin);
					  		     	if (NomLigne.getText().equals(""))
					  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     	else if(comboTypeMagasin.getSelectedItem().equals(""))
					  		     		JOptionPane.showMessageDialog(null, "Il faut remplir le Type Magasin!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     	else if(comboCatMagasin.getSelectedItem().equals(""))
					  		     		JOptionPane.showMessageDialog(null, "Il faut remplir la cat�gorie Magasin!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     	else if(comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE) && comboClient.getSelectedItem().equals(""))
					  		     		JOptionPane.showMessageDialog(null, "Il faut remplir le Client!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     	else if(comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION) && comboMachine.getSelectedItem().equals(""))
					  		     		JOptionPane.showMessageDialog(null, "Il faut remplir la Machine!", "Attention", JOptionPane.ERROR_MESSAGE);
					  		     		 else {
					  		     			 String code="";
					  		     			 if(!comboClient.getSelectedItem().equals(""))
					  		     				code= mapClient.get(comboClient.getSelectedItem());
					  		     			 if(!comboMachine.getSelectedItem().equals(""))
						  		     			code= mapMachine.get(comboMachine.getSelectedItem());
					  		     			 
					  		     			if(comboTypeMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE_ATT))
					  		     			{
					  		     				Magasin magasinstkattente=depotDAO.MagasinByTypeMagasinStkAttente(mapTypeMagasin.get(comboTypeMagasin.getSelectedItem())) ;
						  		     			if(magasinstkattente!=null)
						  		     			{
						  		     				if(magasinstkattente.getTypeMagasin().equals(Constantes.MAGASIN_CODE_TYPE_MP_ATT))
						  		     				{
						  		     					JOptionPane.showMessageDialog(null, "Le d�pot Stockage en Attente existe d�j� ...!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
							  		     				return;
						  		     				}
						  		     				
						  		     			}
					  		     			}
					  		     			
					  		     			 
					  		     			 
					  		     			 Magasin magasin = new Magasin();
					  		     			 magasin.setLibelle(NomLigne.getText());
					  		     			 magasin.setCode(codeMagasin);
					  		     			 magasin.setTypeMagasin(mapTypeMagasin.get(comboTypeMagasin.getSelectedItem()));
					  		     			 magasin.setCatMagasin(mapCategorieMagasin.get(comboCatMagasin.getSelectedItem()));
					  		     			 magasin.setDepot(depot);
					  		     			 magasin.setCodeMachine(code);
					  		     			 listMagasin.add(magasin);
					  		     			 Utils.incrementerValeurSequenceur(Constantes.MAGASIN_LIBELLE);
					  		     			 afficher_Magasin(listMagasin);
					  		     			 intialiserMagasin ();
					  		     		 }
					  		     	
				  		     		}
					  		     	
				  		     		
				  		     	}
				  		     });
				  		 
				  		
				  		 
	}
	
	
	
	void afficher_Magasin(List<Magasin> listMagasin)
	{

		modeleLigneMachine =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"id","Code","Libelle"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
		  int i=0;
			while(i<listMagasin.size())
			{	
				Magasin magasin=listMagasin.get(i);
				Object []ligne={magasin.getId(),magasin.getCode(),magasin.getLibelle()};

				modeleLigneMachine.addRow(ligne);
				i++;
			}

			table.setModel(modeleLigneMachine); 

	}

	
void intialiserMagasin (){
	
	NomLigne.setText("");
	comboTypeMagasin.setSelectedItem("");
	comboCatMagasin.setSelectedItem("");
	comboClient.setSelectedItem("");
	comboMachine.setSelectedItem("");
	
}
	
void intialiserDepot (){
		
	intialiserMagasin();
	txtCodeDepot.setText("");
	txtLibelleDepot.setText("");
	comboDepot.setSelectedItem("");
	code.setText("");
	listMagasin= new ArrayList<Magasin>();
	afficher_Magasin(listMagasin);
	
		
	}
}
