package fournisseur;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTitledSeparator;

import Production.MatierePremiere;
import util.Constantes;
import util.Utils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.MatierePremier;
import dao.entity.Sequenceur;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import javax.swing.border.EtchedBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;


public class AjouterFournisseur extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modele;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	private DepotDAO depotdao;
	private List<Fournisseur> listFournisseur= new ArrayList<Fournisseur>();
	private List<Fournisseur> listFournisseurTmp= new ArrayList<Fournisseur>();
	private FournisseurDAO fournisseurDAO;
	private MatierePremiereDAO matierePremierDAO;
	private ProductionDAO productionDAO;
	private SequenceurDAO sequenceurDAO;
	private Utilisateur utilisateur;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	private	JButton btnSupprimer ;
	private JTextField txtTel;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField code;
	private JTextField nom;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterFournisseur() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	fournisseurDAO=new FournisseurDAOImpl();
        	matierePremierDAO=new MatierePremierDAOImpl();
        	productionDAO=new ProductionDAOImpl();
        	sequenceurDAO=new SequenceurDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	depotdao=new DepotDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion ‡ la base de donnÈes", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
    	
	    
	      
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(30, 255, 686, 365);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		  				
				  		  				if(table.getSelectedRow()!=-1)
				  		  				{
				  		  					code.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		  					nom.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				  		  					txtAdresse.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				  		  					txtTel.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				  		  					txtEmail.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				  		  				btnSupprimer.setEnabled(true);
		  				 		     	btnModifier.setEnabled(true);
		  				 			    btnAjouter.setEnabled(false);
				  		  				}
				  		  				
				  		  				
				  		  				
				  		  			}
				  		   			});
				  		   			scrollPane.setViewportView(table);
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Code", "Nom", "Adresse", "Tel", "Email"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   		charger(); 
				  		   		
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   			
				  		  				if(table.getSelectedRow()!=-1)
				  		  				{
				  		  					boolean trouve=false;

				  		  					try{
				  		  						
				  		  						if( nom.getText().equals("")){
				  		  							JOptionPane.showMessageDialog(null, "Veuillez entrer le nom!");
				  		  						} else if(txtTel.getText().equals("")){
				  		  							JOptionPane.showMessageDialog(null, "Veuillez entrer le Num√©ro de t√©l√©phone!");
				  		  							
				  		  						}else if(txtEmail.getText().equals("")){
				  		  							JOptionPane.showMessageDialog(null, "Veuillez entrer Adresse Email!");
				  		  							
				  		  						}else if(txtAdresse.getText().equals("")){
				  		  							JOptionPane.showMessageDialog(null, "Veuillez entrer l'adresse!");
				  		  							
				  		  						}else {
				  		  							for(int i=0;i<listFournisseur.size();i++)
				  		  							{
				  		  								if(listFournisseur.get(i).getNom().toUpperCase().equals(nom.getText().toUpperCase()) && i !=table.getSelectedRow())
				  		  								{
				  		  									trouve=true;
				  		  								}
				  		  								
				  		  							}
				  		  							if(trouve==false)
				  		  							{
				  		  								Fournisseur fournisseur=listFournisseur.get(table.getSelectedRow());
				  		  								
				  		  								fournisseur.setNom(nom.getText());
				  		  								fournisseur.setAdresse(txtAdresse.getText());
				  		  								fournisseur.setTel(txtTel.getText());
				  		  								fournisseur.setEmail(txtEmail.getText());
				  		  								fournisseurDAO.edit(fournisseur);

				  		  							  charger();
				  		  								initialiser();
				  		  								JOptionPane.showMessageDialog(null, "Fournisseur modifier avec succÈe !!!!!","Bravo",JOptionPane.INFORMATION_MESSAGE);
				  		  								Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
				  		  							listFournisseurTmp=fournisseurDAO.findFournisseurByDepot(depot);
				  		  								incrementer();
				  		  							}else
				  		  							{
				  		  								JOptionPane.showMessageDialog(null, "Fournisseur deja existant !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		  								return;
				  		  							}
				  		  							
				  		  					
				  		  						}
				  		  					 }catch(Exception exp){
				  		  					  exp.printStackTrace();	
				  		  					 JOptionPane.showMessageDialog(null, "Erreur requ√®te ajouter!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		  					 }
				  		  					
				  		  					
				  		  					
				  		  					
				  		  					
				  		  					
				  		  				}
				  		  				
				  		  			}
				  		  		});
				  		   			btnModifier.setBounds(91, 220, 107, 24);
				  		   			add(btnModifier);
				  		   			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnModifier.setEnabled(false);
				  		   			
				  		   			button_1 = new JButton("Initialiser");
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   					initialiser();
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(442, 221, 106, 23);
				  		   			add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			btnAjouter = new JButton("Ajouter");
				  		   			btnAjouter.setBounds(325, 220, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  							boolean trouve=false;

				  							try{
				  								
				  								if( nom.getText().equals("")){
				  									JOptionPane.showMessageDialog(null, "Veuillez entrer le nom!");
				  								}/* else if(txtTel.getText().equals("")){
				  									JOptionPane.showMessageDialog(null, "Veuillez entrer le Num√©ro de t√©l√©phone!");
				  									
				  								}else if(txtEmail.getText().equals("")){
				  									JOptionPane.showMessageDialog(null, "Veuillez entrer Adresse Email!");
				  									
				  								}else if(txtAdresse.getText().equals("")){
				  									JOptionPane.showMessageDialog(null, "Veuillez entrer l'adresse!");
				  									
				  								}*/else {
				  									for(int i=0;i<listFournisseur.size();i++)
				  									{
				  										if(listFournisseur.get(i).getNom().toUpperCase().equals(nom.getText().toUpperCase()))
				  										{
				  											trouve=true;
				  										}
				  										
				  									}
				  									if(trouve==false)
				  									{
				  										
				  										Depot depot =depotdao.findByCode(utilisateur.getCodeDepot());
				  										Fournisseur fournisseur=new Fournisseur();
				  										fournisseur.setCode(code.getText());
				  										fournisseur.setNom(nom.getText());
				  										fournisseur.setAdresse(txtAdresse.getText());
				  										fournisseur.setTel(txtTel.getText());
				  										fournisseur.setEmail(txtEmail.getText());
				  										fournisseur.setDepot(depot);
				  										fournisseurDAO.add(fournisseur);
				  										
				  										listFournisseurTmp=fournisseurDAO.findFournisseurByDepot(depot);
				  									  charger();
				  										initialiser();
				  										
				  										JOptionPane.showMessageDialog(null, "Fournisseur Ajouter avec succÈe !!!!!","Bravo",JOptionPane.INFORMATION_MESSAGE);
				  										Sequenceur  sequenceur=sequenceurDAO.findByCode(Constantes.CODE_FOURNISSEUR);
				  										
				  										if(sequenceur!=null)
				  										{
				  											int valeur=sequenceur.getValeur()+1;
				  											
				  											
				  											sequenceur.setValeur(valeur);
				  											sequenceurDAO.edit(sequenceur);
				  											}
				  										else
				  										{
				  											Sequenceur sequenceurTmp=new Sequenceur();
				  											sequenceurTmp.setCode(Constantes.CODE_FOURNISSEUR);
				  											sequenceurTmp.setLibelle(Constantes.LIBELLE_FOURNISSEUR);
				  											sequenceurTmp.setValeur(1);
				  											sequenceurDAO.add(sequenceurTmp);
				  											
				  										}
				  										incrementer();
				  									}else
				  									{
				  										JOptionPane.showMessageDialog(null, "Fournisseur deja existant !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  										return;
				  									}
				  									
				  							
				  								}
				  							 }catch(Exception exp){
				  							  exp.printStackTrace();	
				  							 JOptionPane.showMessageDialog(null, "Erreur requ√®te ajouter!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  							 }
				  							
				  							
				  						
				  							
				  							
				  						}
				  		   			});
				  		   			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			 btnSupprimer = new JButton("Supprimer");
				  		   			btnSupprimer.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		  				
				  		  				if(table.getSelectedRow()!=-1)
				  		  				{
				  		  				Fournisseur fournisseur=listFournisseur.get(table.getSelectedRow())	;
				  		  				
				  		  				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer le fournisseur ?", 
				  		  							"Satisfaction", JOptionPane.YES_NO_OPTION);
				  		  					 
				  		  					if(reponse == JOptionPane.YES_OPTION )
				  		  						
				  		  						
				  		  					{
				  		  						
				  		  						fournisseurDAO.delete(fournisseur.getId());
				  		  						charger();
				  		  							JOptionPane.showMessageDialog(null, "Fournisseur supprimer avec succÈe ");
				  		  							initialiser();
				  		  							Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
				  		  						listFournisseurTmp=fournisseurDAO.findFournisseurByDepot(depot);
				  		  						
				  		  					}
				  		  						
				  		  					
				  		  				}
				  		  				
				  		  			}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(208, 220, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
				  		   			JLayeredPane layeredPane = new JLayeredPane();
				  		   			layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, null));
				  		   			layeredPane.setBackground(SystemColor.menu);
				  		   			layeredPane.setBounds(30, 7, 686, 202);
				  		   			add(layeredPane);
				  		   			
				  		   			JLabel label = new JLabel("Tel :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 255)));
				  		   			label.setBounds(67, 79, 56, 19);
				  		   			layeredPane.add(label);
				  		   			
				  		   			JLabel label_1 = new JLabel("Adresse :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
				  		   			label_1.setBackground(new Color(135, 206, 235));
				  		   			label_1.setBounds(67, 114, 86, 20);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			txtTel = new JTextField();
				  		   			txtTel.setColumns(10);
				  		   			txtTel.setBounds(128, 74, 142, 26);
				  		   			layeredPane.add(txtTel);
				  		   			
				  		   			txtAdresse = new JTextField();
				  		   			txtAdresse.setColumns(10);
				  		   			txtAdresse.setBounds(128, 109, 382, 26);
				  		   			layeredPane.add(txtAdresse);
				  		   			
				  		   			JLabel label_2 = new JLabel("Email:");
				  		   			label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				  		   			label_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		   			label_2.setBounds(298, 78, 102, 19);
				  		   			layeredPane.add(label_2);
				  		   			
				  		   			txtEmail = new JTextField();
				  		   			txtEmail.setColumns(10);
				  		   			txtEmail.setBounds(367, 72, 142, 26);
				  		   			layeredPane.add(txtEmail);
				  		   			
				  		   			JLabel label_3 = new JLabel("Nom ");
				  		   			label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_3.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
				  		   			label_3.setBounds(66, 38, 56, 20);
				  		   			layeredPane.add(label_3);
				  		   			
				  		   			JLabel label_4 = new JLabel("CODE:");
				  		   			label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				  		   			label_4.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		   			label_4.setBounds(298, 37, 142, 20);
				  		   			layeredPane.add(label_4);
				  		   			
				  		   			code = new JTextField();
				  		   			code.setEditable(false);
				  		   			code.setText((String) null);
				  		   			code.setForeground(Color.RED);
				  		   			code.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				  		   			code.setColumns(10);
				  		   			code.setBackground(Color.WHITE);
				  		   			code.setBounds(367, 36, 142, 24);
				  		   			layeredPane.add(code);
				  		   			
				  		   		incrementer();
				  		   			nom = new JTextField();
				  		   			
				  		   			nom.setColumns(10);
				  		   			nom.setBounds(128, 36, 142, 26);
				  		   			layeredPane.add(nom);
				  		   			
	}
	
	
	
	void charger()
	{
			Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
		listFournisseur=fournisseurDAO.findFournisseurByDepot(depot);
		afficher_Fournisseur(listFournisseur);
		
	}
		
	void incrementer()
	{
		code.setText(util.Utils.genererCodeFournisseur());
		
	}
	
	void initialiser()
	{
		nom.setText("");
		txtTel.setText("");
		txtAdresse.setText("");
		txtEmail.setText("");
		btnAjouter.setEnabled(true);
		btnSupprimer.setEnabled(false);
		btnModifier.setEnabled(false);
		btnAjouter.setEnabled(true);
		incrementer();
	}
	
	void afficher_Fournisseur(List<Fournisseur> listFournisseur )
	{
		
		modele=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						 "CODE", "NOM", "ADRESSE", "TEL", "EMAIL"
				}
			){
			  	boolean[] columnEditables = new boolean[] {
				  		false, false, false, false, false
				  	};
			  	Class[] columnTypes = new Class[] {
		           		String.class,String.class,String.class,String.class,String.class
					};
			  	 public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				  	public boolean isCellEditable(int row, int column) {
				  		return columnEditables[column];
				  	}
				  };
			setLayout(null);
		
		
int i=0;
		while(i<listFournisseur.size()){
			Fournisseur fournisseur=listFournisseur.get(i);
			
			Object []ligne={fournisseur.getCode(),fournisseur.getNom(),fournisseur.getAdresse(),fournisseur.getTel(),fournisseur.getEmail()};
	       modele.addRow(ligne);
                  
                   
i++;
	      
		}
		 table.setModel(modele); 
		
	}
}
