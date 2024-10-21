package Referentiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import dao.daoImplManager.EnTeteDAOImpl;
import dao.daoImplManager.TypeBLDAOImpl;
import dao.daoImplManager.VilleDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EnTeteDAO;
import dao.daoManager.TypeBLDAO;
import dao.daoManager.VilleDAO;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.EnTete;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.TypeBL;
import dao.entity.Utilisateur;
import dao.entity.Ville;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.util.IOUtils;
import org.jdesktop.swingx.JXLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class AjoutEnTete extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private DefaultTableModel	 modele;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	private  EnTeteDAO enTeteDAO;
	private DepotDAO depotDAO;
	
	private JTextField txtVille;
	private JComboBox comboTypeClient = new JComboBox();
	
	private Map< String, Depot> mapDepot = new HashMap<>();
	private Map< String, CompteClient> mapcompte = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<EnTete> listEnTete =new ArrayList<EnTete>();
	private Utilisateur utilisateur;
	private JTable table;
	private JTextField txturl;
	 JComboBox combodepot = new JComboBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutEnTete() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 725);
        try{
        	
        	enTeteDAO=new  EnTeteDAOImpl();
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
				  		  		
if(combodepot.getSelectedIndex()==-1)
{
	JOptionPane.showMessageDialog(null, "Il faut Selectionner le Depot  SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
	 return;
	
}else
{
	
	if(combodepot.getSelectedItem().equals(""))
	{
		JOptionPane.showMessageDialog(null, "Il faut Selectionner le Depot  SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
		 return;
	} 
	
}
				  		   		
					  		   	if(txtVille.getText().equals("") || txturl.getText().equals(""))
					  		   	{
					  		   		
					  		   	JOptionPane.showMessageDialog(null, "Il faut saisir  Tous les Champs  SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   	return;
					  		   	
					  		   	}else 
					  		   	{
					  		   		
					  		   		
					  		   	Depot depot=mapDepot.get(combodepot.getSelectedItem())	;
			  		     			 if(depot==null)
			  		     			 {
			  		     				JOptionPane.showMessageDialog(null, "Il faut Selectionner le Depot  SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     			 return; 
			  		     			 }
			  		     			 
			  		     			 
			  		     			
			  		     			EnTete enTeteTmp=enTeteDAO.findEnTeteByVille (txtVille.getText().toUpperCase().trim());
			  		     			
			  		     			if(enTeteTmp!=null)
			  		     			{
			  		     			 JOptionPane.showMessageDialog(null, "la Ville  existe déja!", "Attention", JOptionPane.INFORMATION_MESSAGE);
			  		     			 return;
			  		     			 
			  		     			}
			  		     			
			  		     			try {
			  		     				
			  		     		  		EnTete enTete = new EnTete();
							  		  	enTete.setDepot(depot);
							  		  		
							  		  	enTete.setVille(txtVille.getText().toUpperCase());
							  		
							  		  enTete.setDateCreation(new Date());
							  		
							  	 
							  		  
							  		 InputStream is = new FileInputStream(new File(txturl.getText()));
							  		byte[] data = new byte[is.available()];
							  		is.read(data);
							  		
							  		enTete.setUrl(data);
							  		
							  		enTete.setUtilCreation(utilCreation);
							  		  	enTeteDAO.add(enTete);
			  		     				
			  		     				
										
									} catch (Exception e2) {
										 JOptionPane.showMessageDialog(null, e2.getMessage());
									}
			  		     				 
						  		
						  		  		
						  		  		JOptionPane.showMessageDialog(null, "L'Entet a été ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
						  		  		 
						  		  		listEnTete=enTeteDAO.findAll();
						  		  		intialiser ();
						  		  	charger();
					  		   		afficher_Ville(listEnTete);
			  		     		 }
				  		  	
				  		  	}
				  		  });
				  		btnAjouter.setIcon(imgAjouter);
				  		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnAjouter.setBounds(259, 280, 114, 26);
				  		 add(btnAjouter);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(383, 280, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(29, 78, 686, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout En Tete");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(30, 103, 685, 166);
				  		   add(layeredPane);
				  		   txtVille = new JTextField();
				  		 util.Utils.copycoller(txtVille);
				  		   txtVille.setBounds(134, 61, 379, 26);
				  		   layeredPane.add(txtVille);
				  		   txtVille.setColumns(10);
				  		   
				  		   JLabel lblNomClient = new JLabel("Ville :");
				  		   lblNomClient.setBounds(10, 60, 114, 26);
				  		   layeredPane.add(lblNomClient);
				  		   lblNomClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   
				  		   JXLabel label = new JXLabel();
				  		   label.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		   label.setText("En Tete");
				  		   label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		   label.setBounds(10, 115, 116, 23);
				  		   layeredPane.add(label);
				  		   
				  		   txturl = new JTextField();
				  		   txturl.setEditable(false);
				  		   txturl.setColumns(10);
				  		   txturl.setBorder(new CompoundBorder(new CompoundBorder(null, new EmptyBorder(0, 0, 0, 0)), null));
				  		   txturl.setBounds(135, 113, 392, 24);
				  		   layeredPane.add(txturl);
				  		   
				  		   JButton button = new JButton("Ouvrir");
				  		   button.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent arg0) {
				  		   		

				  		 		
				  		 		
				  		 		
				  		 		JFileChooser fileChooser=new JFileChooser();
				  		 		FileNameExtensionFilter filter=new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
				  		 		fileChooser.addChoosableFileFilter(filter);
				  		 		int result=fileChooser.showSaveDialog(null);
				  		 		if(result==JFileChooser.APPROVE_OPTION) {
				  		 			File selectedFile=fileChooser.getSelectedFile();
				  		 			String path=selectedFile.getAbsolutePath();
				  		 			txturl.setText(path);
				  		 		}else if(result==JFileChooser.CANCEL_OPTION) {
				  		 			
				  		 			
				  		 			
				  		 		}
				  		 		
				  		 		
				  		 		
				  		 	
				  		   		
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   button.setBounds(541, 115, 107, 24);
				  		   layeredPane.add(button);
				  		   
				  		   JLabel lblDepot = new JLabel("Depot :");
				  		   lblDepot.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   lblDepot.setBounds(10, 22, 114, 26);
				  		   layeredPane.add(lblDepot);
				  		   
				  		     combodepot = new JComboBox();
				  		   combodepot.setSelectedIndex(-1);
				  		   combodepot.setBounds(134, 25, 379, 24);
				  		   layeredPane.add(combodepot);
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(32, 317, 889, 365);
				  		   			add(scrollPane);
				  		   			
				  		   			
				  		   		 JButton btnModifier = new JButton("Modifier");
				  		  	 btnModifier.addActionListener(new ActionListener() {
				  		  	 	public void actionPerformed(ActionEvent arg0) {
				  		  	 		
				  		  	 		if(table.getSelectedRow()!=-1)
				  		  	 		{
				  		  	 			
				  		  	 		

				  		    		  		

				  		    		   		
				  		  	 		if(txtVille.getText().equals(""))
						  		   	{
						  		   		
						  		   	JOptionPane.showMessageDialog(null, "Il faut saisir  Tous les Champs  SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
						  		   	return;
						  		   	}
				  		  	  		 
				  		  	  		  
				  		  	  		   	
				  		  	  		   	
				  		  		     		else {
				  		  		     			
				  		  		     			
				  		  		     			
				  		  		     		if(combodepot.getSelectedIndex()==-1)
				  		  		     	{
				  		  		     		JOptionPane.showMessageDialog(null, "Il faut Selectionner le Depot  SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		     		 return;
				  		  		     		
				  		  		     	}else
				  		  		     	{
				  		  		     		
				  		  		     		if(combodepot.getSelectedItem().equals(""))
				  		  		     		{
				  		  		     			JOptionPane.showMessageDialog(null, "Il faut Selectionner le Depot  SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		     			 return;
				  		  		     		} 
				  		  		     		
				  		  		     	}
				  		  		     			
				  		  		     			
				  		  		     	Depot depot=mapDepot.get(combodepot.getSelectedItem())	;
				  		     			 if(depot==null)
				  		     			 {
				  		     				JOptionPane.showMessageDialog(null, "Il faut Selectionner le Depot  SVP", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     			 return; 
				  		     			 }
				  		  		     			
				  		  		     				
				  		  		  		  		EnTete entete = listEnTete.get(table.getSelectedRow());
				  		  		  		  		
				  		  		  		  		
				  		  		  		  		boolean existe=false;
				  		  		  		  		for(int t=0;t<listEnTete.size();t++)
				  		  		  		  		{
				  		  		  		  			
				  		  		  		  		if(t!=table.getSelectedRow())	
				  		  		  		  		{
				  		  		  		  			
				  		  		  		  			if(listEnTete.get(t).getVille().toUpperCase().equals(txtVille.getText().toUpperCase()))
				  		  		  		  			{
				  		  		  		  		existe=true;
				  		  		  		  				
				  		  		  		  			}
				  		  		  		  			
				  		  		  		  		}
				  		  		  		  			
				  		  		  		  			
				  		  		  		  		}
				  		  		  		  		
				  		  		  		  		if(existe==true)
				  		  		  		  		{
				  		  		  		  	JOptionPane.showMessageDialog(null, "L'EntTete Déja Existant!", "Erreur", JOptionPane.WARNING_MESSAGE);
				  		  		  		  	return;
				  		  		  		  			
				  		  		  		  		}else
				  		  		  		  		{
				  		  		  		  			
				  		  		  		  		try {
				  		  		  		  			
				  		  		  		  			
				  		  		  		  	entete.setVille (txtVille.getText().toUpperCase());
				  		  		  		entete.setDepot(depot);
				  		  		  		  	
				  		  		  		  	if(!txturl.getText().equals(""))
				  		  		  		  	{
				  		  		  		  InputStream is = new FileInputStream(new File(txturl.getText()));
									  		byte[] data = new byte[is.available()];
									  		is.read(data);
									  		
									  		entete.setUrl(data);
				  		  		  		  	}
					  		  		  		  
						  		  		  		
					  		  		  		entete.setUtilisateurMAJ (utilisateur);
													
												} catch (Exception e) {
													JOptionPane.showMessageDialog(null, e.getMessage());
													return;
												}	
				  		  		  		  			
				  		  		  		  			
				  		  		  		  	
					  		  		  		
					  		  		  		  	enTeteDAO.edit(entete);
					  		  		  		  		
					  		  		  		  		JOptionPane.showMessageDialog(null, "L'EntTete a été modifier avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
					  		  		  		  		listEnTete=enTeteDAO.findAll();
					  		  		  		  		intialiser ();
					  		  		  		  	charger();
					  		  	  		   		afficher_Ville(listEnTete);
				  		  		  		  		}
				  		  		  		  		
				  		  		  		  
				  		  		     		 }
				  		    		  	
				  		    		  	
				  		  	 			
				  		  	 			
				  		  	 			
				  		  	 			
				  		  	 			
				  		  	 			
				  		  	 			
				  		  	 		}
				  		  	 		
				  		  	 		
				  		  	 		
				  		  	 	}
				  		  	 	
				  		  	 });
				  		  	 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  	 btnModifier.setBounds(931, 335, 114, 26);
				  		  	 add(btnModifier);	
				  		   			
				  		   			
				  		   			
				  		   			
				  		   			
				  		   			table = new JTable();
				  		   			table.setFillsViewportHeight(true);
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		   					
				  		   					if(table.getSelectedRow()!=-1)
				  		   					{
				  		   						
				  		   				EnTete enTete=	listEnTete.get(table.getSelectedRow())	;
				  		   						
				  		   						 
				  		   						combodepot.setSelectedItem(enTete.getDepot().getLibelle());
				  		   						txtVille.setText(enTete.getVille().toUpperCase());
				  		   						
				  		   						btnAjouter.setEnabled(false);
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"ID", "Type BL"
				  		   				}
				  		   			));
				  		   			scrollPane.setViewportView(table);
				  		   
				  		   			
				  		   		charger();
				  		   		afficher_Ville(listEnTete);
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	  if(utilisateur.getLogin().equals("admin"))
				  			  {
				  				  listDepot=depotDAO.findAll();
				  				  int k=0;
				  			     	 combodepot.addItem("");
				  			     	while (k<listDepot.size())
				  			     	{
				  			     		Depot depot=listDepot.get(k);
				  			     		Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
				  			     		if(magasin!=null)
				  			     		{
				  			     			if(depot.getId()!=magasin.getDepot().getId())
				  				     		{
				  				     			combodepot.addItem(depot.getLibelle());
				  					     		
				  					     		mapDepot.put(depot.getLibelle(), depot);
				  					     	
				  					     		
				  				     			
				  				     		}
				  			     		}else
				  			     		{
				  			     			combodepot.addItem(depot.getLibelle());
				  				     		
				  				     		mapDepot.put(depot.getLibelle(), depot);
				  				     	
				  				     		
				  			     		}
				  			     		k++;
				  			     		
				  			     	}
				  			      
				  			  }else
				  			  {
				  				  
				  				 combodepot.addItem("");
				  				  Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
				  				  if(depot!=null)
				  				  {
				  					  combodepot.addItem(depot.getLibelle());
				  					
				  			     		mapDepot.put(depot.getLibelle(), depot);
				  				  }
				  			  }	
				  		   	  
				  		   	  
			combodepot.setSelectedItem("");	  		   		
	}
	
	
	void charger()
	{
		
		listEnTete.clear();
		listEnTete.addAll(enTeteDAO.findAll());
 		
		
	}
	

	
void intialiser (){
		
		txtVille.setText("");
		txturl.setText("");
		 combodepot.setSelectedItem("");
		btnAjouter.setEnabled(true);
		
	}


void afficher_Ville(List<EnTete> listEnTete)
{
	
	modele=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID","Depot","Ville", "URL"		}
		){
		  	boolean[] columnEditables = new boolean[] {
			  		false,false,false,false
			  	};
		  	
			  	public boolean isCellEditable(int row, int column) {
			  		return columnEditables[column];
			  	}
			  };
		setLayout(null);
	
	
int i=0;
	while(i<listEnTete.size()){
		EnTete enTete=listEnTete.get(i);
		
		
		Object []ligne={enTete.getId() ,enTete.getDepot().getLibelle(), enTete.getVille(),enTete.getUrl() };
       modele.addRow(ligne);
              
               
i++;
      
	}
	table.setModel(modele);
}
}

