package Referentiel;

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
import dao.daoImplManager.TypeVenteDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.TypeVente;
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


public class AjoutTypeVente extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  TypeVenteDAO typeventeDAO;
	
	
 	
	
 	
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtcodetypevente;
	private JTextField txtlibelle;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	private	JButton btnSupprimer ;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutTypeVente() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	typeventeDAO=new TypeVenteDAOImpl();
        	
        	
        	listTypeVente.addAll(typeventeDAO.findAll());
        	
        

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion � la base de donn�es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(31, 32, 685, 151);
				  		   add(layeredPane);
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Code  :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(7, 62, 144, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Libelle :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(7, 99, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtcodetypevente = new JTextField();
				  		   		util.Utils.copycoller(txtcodetypevente);
				  		   			txtcodetypevente.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {
				  		   				if (e.getKeyCode() == e.VK_ENTER)
								  		{
				  		   					if(!txtcodetypevente.getText().equals(""))
				  		   					{
				  		   						
				  		   						TypeVente typevente=typeventeDAO.findTypeVenteByCodeTypeVente(txtcodetypevente.getText());
				  		   						if(typevente!=null)
				  		   						{
				  		   							for(int i=0;i<listTypeVente.size();i++)
				  		   							{
				  		   								if(listTypeVente.get(i).getCode().equals(typevente.getCode()))
				  		   								{
				  		   									table.setRowSelectionInterval(i, i);
				  		   									
				  		   								btnSupprimer.setEnabled(true);
						  				 		     	btnModifier.setEnabled(true);
						  				 			    btnAjouter.setEnabled(false);
				  		   									
				  		   								}
				  		   								
				  		   							}
				  		   					txtlibelle.setText(typevente.getLibelle());
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   					}
				  		   					
								  		}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   		util.Utils.copycoller(txtcodetypevente);
				  		   			txtcodetypevente.setColumns(10);
				  		   			txtcodetypevente.setBounds(152, 62, 191, 26);
				  		   			layeredPane.add(txtcodetypevente);
				  		   			
				  		   			txtlibelle = new JTextField();
				  		   		util.Utils.copycoller(txtlibelle);
				  		   			txtlibelle.setColumns(10);
				  		   			txtlibelle.setBounds(152, 99, 191, 26);
				  		   			layeredPane.add(txtlibelle);
				  		   			
				  		   		
				  		   			
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
				  		   						if(listTypeVente.size()>0)
				  		   						{
				  		   							
				  		   						
				  		   							txtcodetypevente.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		   							txtlibelle.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				  		   							btnSupprimer.setEnabled(true);
					  				 		     	btnModifier.setEnabled(true);
					  				 			    btnAjouter.setEnabled(false);
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			scrollPane.setViewportView(table);
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Code", "Libelle"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   		afficher_tableTypeVente(listTypeVente);
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   			 if(txtcodetypevente.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code Type de Vente SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtlibelle.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir libelle Type de Vente SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else
			  		   					{
			  		   					if(listTypeVente.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier le type de vente ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listTypeVente.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listTypeVente.get(i).getCode().equals(txtcodetypevente.getText().toUpperCase()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Type de Vente existe deja dans la liste des type de vente SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												TypeVente typevente=listTypeVente.get(table.getSelectedRow());
			  												typevente.setCode(txtcodetypevente.getText().toUpperCase());
			  												typevente.setLibelle(txtlibelle.getText().toUpperCase());
				  											
				  											typeventeDAO.edit(typevente);
				  											charger();
						  		  							JOptionPane.showMessageDialog(null, "Type de Vente modifier avec succ�e ");
						  		  							initialiser();
				  											
			  											}
			  											
			  											
			  											
			  											
			  											
			  											
			  										}
			  		   						
			  								}
			  		   						
			  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			}});
				  		   			btnModifier.setBounds(91, 194, 107, 24);
				  		   			add(btnModifier);
				  		   			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnModifier.setEnabled(false);
				  		   			
				  		   			button_1 = new JButton("Initialiser");
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   					initialiser();
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(442, 195, 106, 23);
				  		   			add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			btnAjouter = new JButton("Ajouter");
				  		   			btnAjouter.setBounds(325, 194, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					boolean trouve=false;
				  		   					 if(txtcodetypevente.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code de type vente SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(txtlibelle.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir libelle type vente SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else
				  		   					{
				  		   						int i=0;
				  		   						while(i<listTypeVente.size())
				  		   						{
				  		   							if(listTypeVente.get(i).getCode().equals(txtcodetypevente.getText().toUpperCase()))
				  		   							{
				  		   								trouve=true;
				  		   								
				  		   							}
				  		   						i++;
				  		   						}
				  		   						
				  		   						if(trouve==true)
				  		   						{
				  		   						JOptionPane.showMessageDialog(null, "Type de Vente existe deja dans la liste des type de vente SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
				  		   						}else
				  		   						{
				  		   							
				  		   							TypeVente typevente=new TypeVente();
				  		   						typevente.setCode(txtcodetypevente.getText().toUpperCase());
				  		   					typevente.setLibelle(txtlibelle.getText().toUpperCase());
				  		   					
				  		   					typeventeDAO.add(typevente);
				  		   				charger();
				  		   					
				  		   				JOptionPane.showMessageDialog(null, "Type de Vente ajout� avec succ�e SVP !!!!","Succ�e", JOptionPane.INFORMATION_MESSAGE);
				  		   				initialiser();
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			 btnSupprimer = new JButton("Supprimer");
				  		   			btnSupprimer.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					
				  		   					if(table.getSelectedRow()!=-1)
				  		   					{
				  		   						TypeVente typevente =listTypeVente.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer le type de vente ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											typeventeDAO.delete(typevente);
		  											
		  											charger();
		  											JOptionPane.showMessageDialog(null, " Type de Vente supprimer avec succ�e ");
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(208, 194, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
	}
	
	
	
	void charger()
	{
		
		listTypeVente.clear();
 			listTypeVente.addAll(typeventeDAO.findAll());
 			afficher_tableTypeVente(listTypeVente);	
		
	}
	
	void initialiser()
	{
		
		txtcodetypevente.setText("");
		txtlibelle.setText("");
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
	}
	
	void afficher_tableTypeVente(List<TypeVente> listtTypeVentes)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code", "Libelle"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleCat); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listtTypeVentes.size())
			{	
				
				
				Object []ligne={listtTypeVentes.get(i).getCode() ,listtTypeVentes.get(i).getLibelle()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}
