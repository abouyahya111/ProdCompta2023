package presentation.depot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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


public class AjoutDepot extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel modele;
	private DefaultTableModel	 modeleLigneMachine;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private JTextField code;
	private JTextField nom;
	
	private JTextField NomLigne;
	private List<Magasin> listMagasin = new ArrayList<Magasin>();
	private Map< String, String> mapTypeMagasin = new HashMap<>();
	private Map< String, String> mapLibelleTypeMagasin = new HashMap<>();
	private Map< String, String> mapCategorieMagasin = new HashMap<>();
	private Map< String, String> mapLibelleCategorieMagasin = new HashMap<>();
	
	private Map< String, String> mapMachine = new HashMap<>();
	private Map< String, String> mapNomClient = new HashMap<>();
	private Map< String, String> mapClient = new HashMap<>();
	private JComboBox comboTypeMagasin = new JComboBox();
	private JComboBox comboMachine;
	private JComboBox comboClient;
	JComboBox comboCatMagasin ;
	private Depot depot=new Depot();
	
	private  DepotDAO depotDAO;
	private MachineDAO machineDAO;
	private ClientDAO clientDAO;
	
	
	int debut=0;
	int compteur=0;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutDepot() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	mapLibelleCategorieMagasin = new HashMap<>();
        	compteur=0;
        	depotDAO=new DepotDAOImpl();
        	machineDAO =new MachineDAOImpl();
        	clientDAO=new ClientDAOImpl();
        	comboClient = new JComboBox();
        	comboMachine = new JComboBox();
        	comboCatMagasin = new JComboBox();
        	
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_MP, Constantes.MAGASIN_CODE_TYPE_MP);
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_PF, Constantes.MAGASIN_CODE_TYPE_PF);
        	mapTypeMagasin.put(MAGASIN_LIBELLE_TYPE_MP_ATT, Constantes.MAGASIN_CODE_TYPE_MP_ATT);
        	
        	
        	mapLibelleTypeMagasin.put( Constantes.MAGASIN_CODE_TYPE_MP,MAGASIN_LIBELLE_TYPE_MP);
        	mapLibelleTypeMagasin.put( Constantes.MAGASIN_CODE_TYPE_PF,MAGASIN_LIBELLE_TYPE_PF);
        	mapLibelleTypeMagasin.put( Constantes.MAGASIN_CODE_TYPE_MP_ATT,MAGASIN_LIBELLE_TYPE_MP_ATT);
        	
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
	  		
	  		
	  		mapLibelleCategorieMagasin.put(MAGASIN_CODE_CATEGORIE_STOCKAGE, MAGASIN_LIBELLE_CATEGORIE_STOCKAGE) ;
	  		mapLibelleCategorieMagasin.put( MAGASIN_CODE_CATEGORIE_PRODUCTION,MAGASIN_LIBELLE_CATEGORIE_PRODUCTION);
	  		mapLibelleCategorieMagasin.put(MAGASIN_CODE_CATEGORIE_STOCKAGE_ATT, MAGASIN_LIBELLE_CATEGORIE_STOCKAGE_ATT) ;

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        /*action li� aux listes d�rolantes */
        comboClient.addItem("");
        comboCatMagasin.addItemListener(new ItemListener() {
	     	 	public void itemStateChanged(ItemEvent e) {
	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	 		 
	     	 		 if(!comboCatMagasin.getSelectedItem().equals("") && comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE)){
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
		   
  		List<Machine> listMachine= machineDAO.findListMachineByCodeDepot(Codedepot);
  			 
  		comboMachine.addItem("");
		      	for(int i=0;i<listMachine.size();i++)
		      		{	
		      		Machine  machine=listMachine.get(i);
		      			mapMachine.put(machine.getNom() ,machine.getMatricule());
		      			comboMachine.addItem(machine.getNom());
		      			
		      		}
		      	
  /* REMPLIR LA LISTE DES CLIENTS */     
				   
		 List<Client> listClient= clientDAO.findListClientByCodeDepot(Codedepot);
		  			 
		  //comboClient.addItem("");
		  for(int i=0;i<listClient.size();i++)
		 		{	
			  Client  client=listClient.get(i);
	      			mapClient.put(client.getNom() ,client.getCode());
	      			mapNomClient.put(client.getCode(),client.getNom() );
	      			comboClient.addItem(client.getNom());
	      			
	      		}	   			
	
        try{
            	imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            	imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            	imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            	imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
            	imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
            	
          } catch (Exception exp){exp.printStackTrace();}
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  	intialiserDepot();
				  		  		
				  		  	}
				  		  });
				  		initialiserTableau ();
				  		  btnInitialiser.setIcon(imgInit);
				  		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  btnInitialiser.setBounds(374, 454, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 
				  		   code = new JTextField();
				  		 util.Utils.copycoller(code);
				  		   code.setBounds(98, 26, 191, 26);
				  		   add(code);
				  		   code.setColumns(10);
				  		   
				  		   JLabel lblLibelle = new JLabel("Libelle");
				  		   lblLibelle.setBounds(374, 25, 130, 26);
				  		   add(lblLibelle);
				  		   lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   nom = new JTextField();
				  		 util.Utils.copycoller(nom);
				  		   nom.setBounds(422, 26, 191, 26);
				  		   add(nom);
				  		   nom.setColumns(10);
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(7, 65, 982, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout Magasin");
				  		   
				  		   JButton btnValiderAjoutMachine = new JButton("Valider l'Ajout");
				  		   btnValiderAjoutMachine.setIcon(imgValider);

				  		   btnValiderAjoutMachine.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	if(code.getText().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir le code!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		else if (nom.getText().equals(""))
		  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir le nom!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		/*else if (listMagasin.size()<1){
		  		     		 JOptionPane.showMessageDialog(null, "Il faut Ajouter au moins une ligne !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		}*/
		  		     		 else {
		  		     			 
		  		     			
		  		     		Depot depotTmp=depotDAO.findByCode(code.getText());
		  		     			 if(depotTmp!=null && !depotTmp.getCode().equals("")) {
		  		     				 
		  		     				JOptionPane.showMessageDialog(null, "Le d�pot existe d�j� ...!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		     				//intialiserDepot ();
		  		     				 
		  		     			 }else{
		  		     				// depot=new Depot();
		  		     				
		  		     				 depot.setLibelle(nom.getText());
			  		     			 depot.setCode(code.getText());
			  		     			 depot.setListMagasin(listMagasin);
			  		     			 depotDAO.add(depot);
			  		     	
			  		     			 	JOptionPane.showMessageDialog(null, "Le d�pot a �t� ajout� avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
			  		     			 	intialiserDepot ();
		  		     				 
		  		     			 }
		  		     				 
		  		     				
		  		     		 }
				  		   		
				  		   	}
				  		   });
				  		   btnValiderAjoutMachine.setBounds(215, 454, 148, 26);
				  		   add(btnValiderAjoutMachine);
				  		   
				  		   JLayeredPane layeredPane = new JLayeredPane();
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(6, 78, 983, 371);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Libelle");
				  		   lblNom.setBounds(10, 23, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   NomLigne = new JTextField();
				  	     util.Utils.copycoller(NomLigne);
				  		   NomLigne.setBounds(124, 24, 191, 26);
				  		   layeredPane.add(NomLigne);
				  		   NomLigne.setColumns(10);
				  		   
				  		   JButton btnAjoutAligne = new JButton("");
				  		   btnAjoutAligne.setBounds(913, 214, 60, 26);
				  		   layeredPane.add(btnAjoutAligne);
				  		   btnAjoutAligne.setIcon(imgAjouter);
				  		   
				  		   JXLabel lblListDes = new JXLabel();
				  		   lblListDes.setBounds(10, 134, 973, 24);
				  		   layeredPane.add(lblListDes);
				  		   lblListDes.setForeground(new Color(255, 69, 0));
				  		   lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
				  		   lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		   lblListDes.setText("Liste des Magasins");
				  		   
				  		   JButton btnSupprimerLigne = new JButton("");
				  		   btnSupprimerLigne.setIcon(imgSupprimer);
				  		   btnSupprimerLigne.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	 try{
								   int row=0;
								   if(table.getSelectedRow()==-1)
									     JOptionPane.showMessageDialog(null, "Il faut s�lectionner une Ligne � supprimer!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								   else
								   {
									   
									   int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez Supprimer  cette Ligne?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION ){
									   
									row = table.getSelectedRow();
								  
									listMagasin.remove(row);
								   afficher_Magasin(listMagasin);
			                        table.setRowSorter(null);
								     modele.removeRow(row);
										}
								   }
					                }catch (Exception e1){
					                	}
				  		   		}
				  		   });
				  		   btnSupprimerLigne.setBounds(913, 242, 60, 26);
				  		   layeredPane.add(btnSupprimerLigne);
				  		   
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
				  		   			scrollPane.setBounds(10, 158, 893, 207);
				  		   			layeredPane.add(scrollPane);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			
				  		   			comboTypeMagasin.setBounds(429, 25, 191, 24);
				  		   			layeredPane.add(comboTypeMagasin);
				  		   			
				  		   			JLabel lblTypeMag = new JLabel("Type Magasin : ");
				  		   			lblTypeMag.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblTypeMag.setBounds(325, 23, 114, 26);
				  		   			layeredPane.add(lblTypeMag);
				  		   			
				  		   			JLabel lblMachine = new JLabel("Machine : ");
				  		   			lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblMachine.setBounds(325, 60, 114, 26);
				  		   			layeredPane.add(lblMachine);
				  		   			
				  		   			comboMachine.setBounds(429, 60, 191, 24);
				  		   			layeredPane.add(comboMachine);
				  		   			
				  		   			JLabel lblClient = new JLabel("Client : ");
				  		   			lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblClient.setBounds(10, 97, 114, 26);
				  		   			layeredPane.add(lblClient);
				  		   			
				  		   			JLabel lblCatgorieMagasin = new JLabel("Cat\u00E9gorie Magasin : ");
				  		   			lblCatgorieMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblCatgorieMagasin.setBounds(10, 60, 114, 26);
				  		   			layeredPane.add(lblCatgorieMagasin);
				  		   			
				  		   			comboCatMagasin.setBounds(124, 64, 191, 24);
				  		   			layeredPane.add(comboCatMagasin);
				  		   			
				  		   			comboClient.setBounds(124, 99, 191, 24);
				  		   			layeredPane.add(comboClient);
				  		   
				  		   JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   layeredPane_1.setBounds(6, 11, 983, 56);
				  		   add(layeredPane_1);
				  		   
				  		   JLabel lblCode = new JLabel("Code : ");
				  		   lblCode.setBounds(10, 14, 114, 26);
				  		   layeredPane_1.add(lblCode);
				  		   lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 	
				  		 //  String code =Utils.genererCodeMagasin(Constantes.MAGASIN_LIBELLE,nom.getText());
				  		 //	CodeLigne.setText(code);
				  		   
				  		     btnAjoutAligne.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	if (nom.getText().equals(""))
				  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir: Nom D�pot !", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     	else if (code.getText().equals(""))
				  		     			 JOptionPane.showMessageDialog(null, "Il faut remplir: Code D�pot !", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     	else if (NomLigne.getText().equals(""))
				  		     			 JOptionPane.showMessageDialog(null, "Il faut saisir: NOM MAGASIN!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     	else if(comboTypeMagasin.getSelectedItem().equals(""))
				  		     		JOptionPane.showMessageDialog(null, "Il faut choisir: TYPE MAGASIN!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     	else if(comboCatMagasin.getSelectedItem().equals(""))
				  		     		JOptionPane.showMessageDialog(null, "Il faut choisir : CATEGORIE MAGASIN!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     	else if(comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_STOCKAGE) && comboClient.getSelectedItem().equals(""))
				  		     		JOptionPane.showMessageDialog(null, "Il faut choisir : CLIENT!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     	else if(comboCatMagasin.getSelectedItem().equals(MAGASIN_LIBELLE_CATEGORIE_PRODUCTION) && comboMachine.getSelectedItem().equals(""))
				  		     		JOptionPane.showMessageDialog(null, "Il faut choisir : MACHINE!", "Attention", JOptionPane.ERROR_MESSAGE);
				  		     		 else {
				  		     			String codeMagasin =Utils.genererCodeMagasin(Constantes.MAGASIN_LIBELLE,nom.getText());
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
				  		     			 intialiserMagsin ();
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
	  		     			"id","Code","Libelle","Cat�gorie Magasin","Type Magasin"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
		  int i=0;
			while(i<listMagasin.size())
			{	
				Magasin magasin=listMagasin.get(i);
				String CategorieMagasin=mapLibelleCategorieMagasin.get(magasin.getCatMagasin());
				String LibelleTypeMagasin=mapLibelleTypeMagasin.get(magasin.getTypeMagasin());
			//	String NomClient=mapNomClient.get(magasin.getCodeMachine());
				
				Object []ligne={magasin.getId(),magasin.getCode(),magasin.getLibelle(),CategorieMagasin,LibelleTypeMagasin};

				modeleLigneMachine.addRow(ligne);
				i++;
			}

			table.setModel(modeleLigneMachine); 

	}
	void intialiserMagsin (){
		
		NomLigne.setText("");
		comboTypeMagasin.setSelectedItem("");
		comboCatMagasin.setSelectedItem("");
		comboClient.setSelectedItem("");
		comboMachine.setSelectedItem("");
		
	}
	
void intialiserDepot (){
		
	nom.setText("");
	code.setText("");
	comboTypeMagasin.setSelectedItem("");
	listMagasin= new ArrayList<Magasin>();
	afficher_Magasin(listMagasin);
	
		
	}

void initialiserTableau (){
	modeleLigneMachine =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"id","Code","Libelle","Cat�gorie Magasin","type Magasin"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
}
}
