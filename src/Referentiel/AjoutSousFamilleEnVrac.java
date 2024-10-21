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
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
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


public class AjoutSousFamilleEnVrac extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  FamilleArticlesPFDAO famillearticleDAO;
	private SousFamilleEnVracDAO sousfamileEnvracDAO;	
	private MatierePremiereDAO matierePremiereDAO ;
	private SousFamilleArticlesPFDAO sousFamilleArticlesPFDAO;
	
 	
	
	private List<SousFamilleArticlePF> listSousFamilleArticlePF =new ArrayList<SousFamilleArticlePF>();
	private List<FamilleArticlePF> listFamilleArticlePF =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleEnVrac> listSousFamilleEnvrac =new ArrayList<SousFamilleEnVrac>();
	private List<MatierePremier> listEnVrac =new ArrayList<MatierePremier>();
	
	
	private JLabel lblsousfamille;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	private	JButton btnSupprimer ;
	private Map< String, FamilleArticlePF> mapFamilleArticle = new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapSousFamilleArticle = new HashMap<>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private JTextField txtcodeMP;
	JComboBox comboMP = new JComboBox();
 	JComboBox combosousfamille = new JComboBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutSousFamilleEnVrac() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	sousFamilleArticlesPFDAO=new SousFamilleArticlesPFDAOImpl();
        	famillearticleDAO=new FamilleArticlesPFDAOImpl();
        	sousfamileEnvracDAO=new SousFamilleEnVracDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	listEnVrac=matierePremiereDAO.findMatierePremierCatTHE();
        	listFamilleArticlePF.addAll(famillearticleDAO.findAll());
        	listSousFamilleArticlePF=sousFamilleArticlesPFDAO.findAll();
        

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(31, 32, 685, 166);
				  		   add(layeredPane);
				  		   			
				  		   			lblsousfamille = new JLabel("Code sous famille :");
				  		   			lblsousfamille.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblsousfamille.setBounds(10, 48, 144, 26);
				  		   			layeredPane.add(lblsousfamille);
				  		   			
				  		   			JLabel lblEnVrac = new JLabel("En Vrac :");
				  		   			lblEnVrac.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblEnVrac.setBounds(10, 96, 114, 26);
				  		   			layeredPane.add(lblEnVrac);
				  		   			
				  		   			txtcodeMP = new JTextField();
				  		   			txtcodeMP.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {
				  		   					

				  		  	    		

				  		  	     		

				  		       			if(e.getKeyCode()==e.VK_ENTER)
				  		  		      		{
				  		       				
				  		       					
				  		  		      			if(!txtcodeMP.getText().equals(""))
				  		  		      			{
				  		  		      				MatierePremier mp=mapCodeMP.get(txtcodeMP.getText().toUpperCase());
				  		  		      				
				  		  				    		
				  		  				    		if(mp!=null)
				  		  				    		{	
				  		  				    			comboMP.setSelectedItem(mp.getNom());
				  		  				    			
				  		  				    		}else
				  		  				    		{
				  		  				    			 JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		  				    		
				  		  				    			
				  		  				    		}
				  		  		      				
				  		  		      				
				  		  		      		}else
				  		  		      		{
				  		  		      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		  		      			
				  		  		      			
				  		  		      		}
				  		       				
				  		  		      		}
				  		  	     			
				  		  	    		
				  		  	    	
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			txtcodeMP.setColumns(10);
				  		   			txtcodeMP.setBounds(71, 96, 105, 26);
				  		   			layeredPane.add(txtcodeMP);
				  		   			
				  		   			 comboMP = new JComboBox();
				  		   			 comboMP.addActionListener(new ActionListener() {
				  		   			 	public void actionPerformed(ActionEvent arg0) {
				  		   			 		
				  		   			 	if(comboMP.getSelectedIndex()!=-1)
				  				 		{
				  		   			 		
				  				 			MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
				  				 			if(mp!=null)
				  				 			{
				  				 				txtcodeMP.setText(mp.getCode());
				  				 			}
				  				 			
				  				 			
				  				 		  				 			
				  				 		}else
				  				 		{
				  		txtcodeMP.setText(" ");
				  				 			
				  				 		}
				  		   			 		
				  		   			 		
				  		   			 	}
				  		   			 });
				  		   			comboMP.setBounds(186, 99, 257, 23);
				  		   			layeredPane.add(comboMP);
				  		   		
				  		   		for(int j=0;j<listEnVrac.size();j++)
				  		   		{
				  		   			
				  		   			MatierePremier mp = listEnVrac.get(j);
				  		   			comboMP.addItem(mp.getNom());
				  		   			mapMP.put(mp.getNom(), mp);
				  		   			mapCodeMP.put(mp.getCode(), mp);
				  		   			
				  		   			
				  		   		}
				  		   		
				  		   	comboMP.setSelectedIndex(-1);
				  		   	
				  		   	 combosousfamille = new JComboBox();
				  		   	combosousfamille.setBounds(186, 51, 191, 23);
				  		   	layeredPane.add(combosousfamille);
				  		   			
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
				  		   						if(listFamilleArticlePF.size()>0)
				  		   						{
				  		   							
				  		   						SousFamilleArticlePF sousFamilleArticlePF=mapSousFamilleArticle.get(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		   							combosousfamille.setSelectedItem(sousFamilleArticlePF.getCode());
				  		   							
				  		   							MatierePremier mp=	mapMP.get(table.getValueAt(table.getSelectedRow(), 3).toString());
				  		   							comboMP.setSelectedItem(mp.getNom());
				  		   							txtcodeMP.setText(mp.getCode());
				  		   			
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
				  		   					"Code Sous Famille", "Libelle", "Famille Article","En Vrac"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(111);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(123);
				  		   			table.getColumnModel().getColumn(2).setPreferredWidth(115);
				  		   		table.getColumnModel().getColumn(3).setPreferredWidth(115);
				  		   			table.setFillsViewportHeight(true);
				  		   	     	charger();
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   			 if(combosousfamille.getSelectedIndex()==-1)
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner sous famille En Vrac SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}
                                        else if(comboMP.getSelectedIndex()==-1)
			  		   					{
			  		   						
			  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner EnVrac SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}
			  		   					
			  		   					
			  		   					else
			  		   					{
			  		   					if(listSousFamilleEnvrac.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier Sous famille d'article ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											
			  											MatierePremier mp = mapMP.get(comboMP.getSelectedItem());
			  											
			  											int i=0;
			  						    				
			  							    			while( i<listSousFamilleEnvrac.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listSousFamilleEnvrac.get(i).getMatierePremier().getId() == mp.getId())
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Sous famille En Vrac  SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												SousFamilleArticlePF sousFamilleArticlePF=mapSousFamilleArticle.get(combosousfamille.getSelectedItem());
			  												
			  												SousFamilleEnVrac sousfamilleEnvrac=listSousFamilleEnvrac.get(table.getSelectedRow());
			  												sousfamilleEnvrac.setSousfamile(sousFamilleArticlePF);
			  												
			  												sousfamilleEnvrac.setMatierePremier(mp);
			  										
				  											sousfamileEnvracDAO.edit(sousfamilleEnvrac);
				  											charger();
						  		  							JOptionPane.showMessageDialog(null, "Sous Famille En Vrac modifier avec succée ");
						  		  							initialiser();
				  											
			  											}
			  											
			  											
			  											
			  											
			  											
			  											
			  										}
			  		   						
			  								}
			  		   						
			  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			}});
				  		   			btnModifier.setBounds(88, 209, 107, 24);
				  		   			add(btnModifier);
				  		   			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnModifier.setEnabled(false);
				  		   			
				  		   			button_1 = new JButton("Initialiser");
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   					initialiser();
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(439, 210, 106, 23);
				  		   			add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			btnAjouter = new JButton("Ajouter");
				  		   			btnAjouter.setBounds(322, 209, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					boolean trouve=false;
				  		   					 if(combosousfamille.getSelectedIndex()==-1)
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner Sous famille En Vrac SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else  if(comboMP.getSelectedIndex()==-1)
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner EnVrac SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}
				  		   					else
				  		   					{
				  		   						
				  		   					MatierePremier mp=mapMP.get(comboMP.getSelectedItem());
				  		   						int i=0;
				  		   						while(i<listSousFamilleEnvrac.size())
				  		   						{
				  		   							if(listSousFamilleEnvrac.get(i).getMatierePremier().getId()== mp.getId())
				  		   							{
				  		   								trouve=true;
				  		   								
				  		   							}
				  		   						i++;
				  		   						}
				  		   						
				  		   						if(trouve==true)
				  		   						{
				  		   						JOptionPane.showMessageDialog(null, "Sous Famille Envrac existe deja dans la liste des sous famille EnVrac SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
				  		   						}else
				  		   						{
				  		   							
				  		   						
  											SousFamilleArticlePF sousFamilleArticlePF=mapSousFamilleArticle.get(combosousfamille.getSelectedItem());
				  		   							
				  		   							
				  		   							
				  		   							SousFamilleEnVrac sousfamilleEnVrac=new SousFamilleEnVrac();
				  		   						sousfamilleEnVrac.setSousfamile(sousFamilleArticlePF);
				  		   				
				  		   					
				  		   			sousfamilleEnVrac.setMatierePremier(mp);
				  		   				
				  		   					sousfamileEnvracDAO.add(sousfamilleEnVrac);
				  		   				charger();
				  		   					
				  		   				JOptionPane.showMessageDialog(null, "Sous Famille EnVrac ajouté avec succée SVP !!!!","Succée", JOptionPane.INFORMATION_MESSAGE);
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
				  		   						SousFamilleEnVrac sousfamilleEnVrac =listSousFamilleEnvrac.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer sous famille d'En Vrac ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											sousfamileEnvracDAO.delete(sousfamilleEnVrac.getId());
		  											
		  											charger();
		  											JOptionPane.showMessageDialog(null, "Sous Famille En Vrac supprimer avec succée ");
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(205, 209, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
	
	for(int j=0;j<listSousFamilleArticlePF.size() ; j++)
	{
		
		
		SousFamilleArticlePF sousFamilleArticlePF =listSousFamilleArticlePF.get(j);
		combosousfamille.addItem(sousFamilleArticlePF.getCode());
		mapSousFamilleArticle.put(sousFamilleArticlePF.getCode(), sousFamilleArticlePF);
		
		
	}
	
	combosousfamille.setSelectedIndex(-1);
	
	
	
	}
	
	
	
	void charger()
	{
		
		listSousFamilleEnvrac.clear();
		listSousFamilleEnvrac.addAll(sousfamileEnvracDAO.findAll());
 		 afficher_tableSousFamille(listSousFamilleEnvrac);	
		
	}
	
	void initialiser()
	{
		
		
		
		combosousfamille.setSelectedIndex(-1);
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
			txtcodeMP.setText("");
			comboMP.setSelectedIndex(-1);
	}
	
	void afficher_tableSousFamille(List<SousFamilleEnVrac> listsousFamilleEnVrac)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code Sous Famille", "Libelle", "Famille Article", "En Vrac"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleCat);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listsousFamilleEnVrac.size())
			{	
				
					Object []ligne={listsousFamilleEnVrac.get(i).getSousfamile().getCode() ,listsousFamilleEnVrac.get(i).getSousfamile().getLiblle(),listsousFamilleEnVrac.get(i).getSousfamile().getFamileArticlePF().getLiblle(),listsousFamilleEnVrac.get(i).getMatierePremier().getNom()};

					modeleCat.addRow(ligne);
			
				
				
				i++;
			}
	}
}
