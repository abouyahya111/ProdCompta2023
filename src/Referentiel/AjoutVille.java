package Referentiel;

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

import com.sun.security.sasl.ClientFactoryImpl;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.VilleDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.VilleDAO;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.Utilisateur;
import dao.entity.Ville;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjoutVille extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private DefaultTableModel	 modele;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	private  VilleDAO villeDAO;
	private DepotDAO depotDAO;
	
	private JTextField txtNom;
	private JComboBox comboTypeClient = new JComboBox();
	
	private Map< String, String> mapDepot = new HashMap<>();
	private Map< String, CompteClient> mapcompte = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Ville> listville =new ArrayList<Ville>();
	private JTextField txtCode;
	private JLabel lblCodeClient;
	private Utilisateur utilisateur;
	private JTable table;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutVille() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 725);
        try{
        	
        	villeDAO=new VilleDAOImpl();
        	depotDAO=new DepotDAOImpl();
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
    
        final Utilisateur utilCreation=AuthentificationView.utilisateur;  	
	      	
	    
        
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		

				  		   		
					  		   	if(txtNom.getText().equals(""))
			  		     			JOptionPane.showMessageDialog(null, "Il faut saisir : NOM Ville!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     		
					  		 
					  		  else if (txtCode.equals(""))
			  		     			 JOptionPane.showMessageDialog(null, "Il faut saisir: Code Ville!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   	
					  		   	
			  		     		else {
			  		     			int maxidville=villeDAO.maxIdVille();
			  		     			String codeVille =CODE_VILLE+(maxidville+1);
			  		     			
			  		     			Ville villeTmp=villeDAO.findVilleByVille(txtNom.getText());
			  		     			
			  		     			if(villeTmp!=null)
			  		     			{
			  		     			 JOptionPane.showMessageDialog(null, "la ville existe déja!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     			 return;
			  		     			}
			  		     			
			  		     				txtCode.setText(codeVille);
						  		  		Ville ville = new Ville();
						  		  	ville.setVille(txtNom.getText());
						  		
						  		  ville.setDateCreation(new Date());
						  		
						  		
						  		 
						  		ville.setCode(codeVille);
						  		ville.setUtilCreation(utilCreation);
						  		
						  		  	villeDAO.add(ville);
						  		  		
						  		  		JOptionPane.showMessageDialog(null, "La Ville a été ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
						  		  		Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
						  		  		listville=villeDAO.findAll();
						  		  		intialiser ();
						  		  	charger();
					  		   		afficher_Ville(listville);
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
				  		   titledSeparator.setBounds(29, 78, 686, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout Ville");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(30, 103, 685, 136);
				  		   add(layeredPane);
				  		   txtNom = new JTextField();
				  		 util.Utils.copycoller(txtNom);
				  		   txtNom.setBounds(155, 56, 191, 26);
				  		   layeredPane.add(txtNom);
				  		   txtNom.setColumns(10);
				  		   
				  		   JLabel lblNomClient = new JLabel("Nom Ville:");
				  		   lblNomClient.setBounds(10, 55, 114, 26);
				  		   layeredPane.add(lblNomClient);
				  		   lblNomClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			
				  		   			txtCode = new JTextField();
				  		   		util.Utils.copycoller(txtCode);
				  		   			txtCode.setCaretColor(Color.RED);
				  		   			txtCode.setBackground(Color.WHITE);
				  		   			txtCode.setEnabled(false);
				  		   			txtCode.setColumns(10);
				  		   			txtCode.setBounds(487, 56, 191, 26);
				  		   			layeredPane.add(txtCode);
				  		   			
				  		   			lblCodeClient = new JLabel("Code Ville:");
				  		   			lblCodeClient.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblCodeClient.setBounds(400, 56, 88, 26);
				  		   			layeredPane.add(lblCodeClient);
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(29, 287, 889, 365);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.setFillsViewportHeight(true);
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		   					
				  		   					if(table.getSelectedRow()!=-1)
				  		   					{
				  		   						
				  		   				Ville ville=	listville.get(table.getSelectedRow())	;
				  		   						
				  		   						txtCode.setText(ville.getCode());
				  		   						
				  		   						txtNom.setText(ville.getVille());
				  		   						
				  		   						btnAjouter.setEnabled(false);
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Nom Ville", "Code Ville"
				  		   				}
				  		   			));
				  		   			scrollPane.setViewportView(table);
				  		   
				  		   			
				  		   		charger();
				  		   		afficher_Ville(listville);
	}
	
	
	void charger()
	{
		
		listville.clear();
		listville.addAll(villeDAO.findAll());
 		
		
	}
	

	
void intialiser (){
		
		txtNom.setText("");
		
		txtCode.setText("");
		btnAjouter.setEnabled(true);
		
	}


void afficher_Ville(List<Ville> listVille)
{
	
	modele=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nom Ville", "Code Ville"		}
		){
		  	boolean[] columnEditables = new boolean[] {
			  		false,false
			  	};
		  	
			  	public boolean isCellEditable(int row, int column) {
			  		return columnEditables[column];
			  	}
			  };
		setLayout(null);
	
	
int i=0;
	while(i<listVille.size()){
		Ville ville=listVille.get(i);
		
		
		Object []ligne={ville.getVille() , ville.getCode() };
       modele.addRow(ligne);
              
               
i++;
      
	}
	 table.setModel(modele);
	 
	 JButton btnModifier = new JButton("Modifier");
	 btnModifier.addActionListener(new ActionListener() {
	 	public void actionPerformed(ActionEvent arg0) {
	 		
	 		if(table.getSelectedRow()!=-1)
	 		{
	 			
	 		

  		  		

  		   		
	  		   	if(txtNom.getText().equals(""))
		     			JOptionPane.showMessageDialog(null, "Il faut saisir : NOM Ville!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		     		
	  		 
	  		  else if (txtCode.equals(""))
		     			 JOptionPane.showMessageDialog(null, "Il faut saisir: Code Ville!", "Attention", JOptionPane.INFORMATION_MESSAGE);
	  		   	
	  		   	
		     		else {
		     			
		     				
		  		  		Ville ville = listville.get(table.getSelectedRow());
		  		  	ville.setVille(txtNom.getText());
		  		
		  		
		  		ville.setUtilisateurMAJ (utilisateur);
		  		
		  		  	villeDAO.edit(ville);
		  		  		
		  		  		JOptionPane.showMessageDialog(null, "La Ville a été modifier avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  		  		listville=villeDAO.findAll();
		  		  		intialiser ();
		  		  	charger();
	  		   		afficher_Ville(listville);
		     		 }
  		  	
  		  	
	 			
	 			
	 			
	 			
	 			
	 			
	 			
	 		}
	 		
	 		
	 		
	 	}
	 });
	 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 btnModifier.setBounds(931, 335, 114, 26);
	 add(btnModifier);
	
}
}
