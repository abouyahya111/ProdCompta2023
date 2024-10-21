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
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
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


public class AjoutSousFamilleArticlePF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  FamilleArticlesPFDAO famillearticleDAO;
	private SousFamilleArticlesPFDAO sousfamilearticleDAO;	
	private MatierePremiereDAO matierePremiereDAO ;
	
 	
	
 	
	private List<FamilleArticlePF> listFamilleArticlePF =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticlePF =new ArrayList<SousFamilleArticlePF>();
	private List<MatierePremier> listEnVrac =new ArrayList<MatierePremier>();
	
	
	private JLabel lblConfirmerMotDe;
	private JTextField txtcodesousfamillearticlepf;
	private JTextField txtlibelle;
	private JButton btnModifier;
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	private	JButton btnSupprimer ;
	private JLabel lblFamilleArticle;
	private JComboBox comboFamilleArticle;
	private Map< String, FamilleArticlePF> mapFamilleArticle = new HashMap<>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutSousFamilleArticlePF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	famillearticleDAO=new FamilleArticlesPFDAOImpl();
        	sousfamilearticleDAO=new SousFamilleArticlesPFDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	listEnVrac=matierePremiereDAO.findMatierePremierCatTHE();
        	listFamilleArticlePF.addAll(famillearticleDAO.findAll());
        	
        

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
				  		   layeredPane.setBounds(31, 32, 685, 143);
				  		   add(layeredPane);
				  		   	
				  		   			
				  		   			JLabel lblMdp = new JLabel("Code Sous Famille Article PF :");
				  		   			lblMdp.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblMdp.setBounds(10, 11, 166, 26);
				  		   			layeredPane.add(lblMdp);
				  		   			
				  		   			lblConfirmerMotDe = new JLabel("Libelle :");
				  		   			lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblConfirmerMotDe.setBounds(10, 48, 144, 26);
				  		   			layeredPane.add(lblConfirmerMotDe);
				  		   			
				  		   			txtcodesousfamillearticlepf = new JTextField();
				  		   		util.Utils.copycoller(txtcodesousfamillearticlepf);
				  		   			txtcodesousfamillearticlepf.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {
				  		   				if (e.getKeyCode() == e.VK_ENTER)
								  		{
				  		   					if(!txtcodesousfamillearticlepf.getText().equals(""))
				  		   					{
				  		   						
				  		   						SousFamilleArticlePF sousfamillearticlepf=sousfamilearticleDAO.findByCode(txtcodesousfamillearticlepf.getText());
				  		   						if(sousfamillearticlepf!=null)
				  		   						{
				  		   							for(int i=0;i<listSousFamilleArticlePF.size();i++)
				  		   							{
				  		   								if(listSousFamilleArticlePF.get(i).getCode().equals(sousfamillearticlepf.getCode()))
				  		   								{
				  		   								table.setRowSelectionInterval(i, i);
				  		   									
				  		   								btnSupprimer.setEnabled(true);
						  				 		     	btnModifier.setEnabled(true);
						  				 			    btnAjouter.setEnabled(false);
				  		   									
				  		   								}
				  		   								
				  		   							}
				  		   					txtlibelle.setText(sousfamillearticlepf.getLiblle());
				  		   					comboFamilleArticle.setSelectedItem(sousfamillearticlepf.getFamileArticlePF().getLiblle());
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   					}
				  		   					
								  		}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   		util.Utils.copycoller(txtcodesousfamillearticlepf);
				  		   			txtcodesousfamillearticlepf.setColumns(10);
				  		   			txtcodesousfamillearticlepf.setBounds(186, 11, 191, 26);
				  		   			layeredPane.add(txtcodesousfamillearticlepf);
				  		   			
				  		   			txtlibelle = new JTextField();
				  		   		util.Utils.copycoller(txtlibelle);
				  		   			txtlibelle.setColumns(10);
				  		   			txtlibelle.setBounds(186, 48, 191, 26);
				  		   			layeredPane.add(txtlibelle);
				  		   			
				  		   			lblFamilleArticle = new JLabel("Famille Article :");
				  		   			lblFamilleArticle.setFont(new Font("Times New Roman", Font.BOLD, 12));
				  		   			lblFamilleArticle.setBounds(10, 91, 114, 26);
				  		   			layeredPane.add(lblFamilleArticle);
				  		   			
				  		   			comboFamilleArticle = new JComboBox();
				  		   			comboFamilleArticle.setBounds(186, 94, 191, 23);
				  		   			layeredPane.add(comboFamilleArticle);
				  		   			int i=0;
				  		   			while(i<listFamilleArticlePF.size())
				  		   			{
				  		   				FamilleArticlePF famillearticlepf=listFamilleArticlePF.get(i);
				  		   				comboFamilleArticle.addItem(famillearticlepf.getLiblle());
				  		   				mapFamilleArticle.put(famillearticlepf.getLiblle(), famillearticlepf);
				  		   				
				  		   				
				  		   				i++;
				  		   			}
				  		 
				  		   			
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
				  		   							
				  		   						
				  		   							txtcodesousfamillearticlepf.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		   							txtlibelle.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				  		   							comboFamilleArticle.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
				  		   						
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
				  		   					"Code Sous Famille", "Libelle", "Famille Article"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(111);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(123);
				  		   			table.getColumnModel().getColumn(2).setPreferredWidth(115);
				  		   			table.setFillsViewportHeight(true);
				  		   	     	charger();
				  		   			btnModifier = new JButton("Modifier");
				  		   			btnModifier.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					boolean trouve=false;
				  		   			 if(txtcodesousfamillearticlepf.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code sous famille article SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(txtlibelle.getText().equals(""))
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir libelle sous famille article SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else if(comboFamilleArticle.getSelectedIndex()==-1)
			  		   					{
			  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner famille article SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  							return;
			  		   					}else
			  		   					{
			  		   					if(listSousFamilleArticlePF.size()!=0)
			  							{
			  								if(table.getSelectedRow()!=-1)
			  								{
			  								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier Sous famille d'article ?", 
			  												"Satisfaction", JOptionPane.YES_NO_OPTION);
			  										 
			  										if(reponse == JOptionPane.YES_OPTION )
			  											
			  											
			  										{
			  											int i=0;
			  						    				
			  							    			while( i<listSousFamilleArticlePF.size())
			  							    				{
			  							    				
			  							    			
			  							    			
			  							    					if(i!=table.getSelectedRow())
			  							    					{
			  							    						if(listSousFamilleArticlePF.get(i).getCode().equals(txtcodesousfamillearticlepf.getText().toUpperCase()))
			  								    					{
			  								    						trouve=true;
			  								    						JOptionPane.showMessageDialog(null, "Sous famille d'article existe deja dans la liste des familles d'article SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			  								    						return;
			  								    					}
			  									    				
			  							    					}
			  							    				
			  							    					
			  							    					
			  							    					i++;
			  							    				}
			  											
			  											if(trouve==false)
			  											{
			  												
			  												
			  												FamilleArticlePF famillearticle=mapFamilleArticle.get(comboFamilleArticle.getSelectedItem());
			  												SousFamilleArticlePF sousfamillearticle=listSousFamilleArticlePF.get(table.getSelectedRow());
			  												sousfamillearticle.setCode(txtcodesousfamillearticlepf.getText().toUpperCase());
			  												sousfamillearticle.setLiblle(txtlibelle.getText().toUpperCase());
			  												sousfamillearticle.setFamileArticlePF(famillearticle);
			  											
				  											sousfamilearticleDAO.edit(sousfamillearticle);
				  											charger();
						  		  							JOptionPane.showMessageDialog(null, "Sous Famille Article modifier avec succée ");
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
				  		   					 if(txtcodesousfamillearticlepf.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir code  sous famille article SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else if(txtlibelle.getText().equals(""))
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez saisir libelle sous famille article SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else  if(comboFamilleArticle.getSelectedIndex()==-1)
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner famille article SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
				  							return;
				  		   					}else
				  		   					{
				  		   						int i=0;
				  		   						while(i<listSousFamilleArticlePF.size())
				  		   						{
				  		   							if(listSousFamilleArticlePF.get(i).getCode().equals(txtcodesousfamillearticlepf.getText().toUpperCase()))
				  		   							{
				  		   								trouve=true;
				  		   								
				  		   							}
				  		   						i++;
				  		   						}
				  		   						
				  		   						if(trouve==true)
				  		   						{
				  		   						JOptionPane.showMessageDialog(null, "Sous Famille Article existe deja dans la liste des sous famille articles SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
					  							return;
				  		   						}else
				  		   						{
				  		   					
				  		   							
				  		   							FamilleArticlePF famillearticle=mapFamilleArticle.get(comboFamilleArticle.getSelectedItem());
				  		   							
				  		   							SousFamilleArticlePF sousfamilleArticlePF=new SousFamilleArticlePF();
				  		   						sousfamilleArticlePF.setCode(txtcodesousfamillearticlepf.getText().toUpperCase());
				  		   					sousfamilleArticlePF.setLiblle(txtlibelle.getText().toUpperCase());
				  		   				sousfamilleArticlePF.setFamileArticlePF(famillearticle);
				  		   				
				  		   					sousfamilearticleDAO.add(sousfamilleArticlePF);
				  		   				charger();
				  		   					
				  		   				JOptionPane.showMessageDialog(null, "Sous Famille Article ajouté avec succée SVP !!!!","Succée", JOptionPane.INFORMATION_MESSAGE);
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
				  		   						SousFamilleArticlePF sousfamilleArticlePF =listSousFamilleArticlePF.get(table.getSelectedRow());
				  		   						
				  		   					 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer sous famille d'article ?", 
		  												"Satisfaction", JOptionPane.YES_NO_OPTION);
		  										 
		  										if(reponse == JOptionPane.YES_OPTION )
		  											
		  											
		  										{
		  											
		  											
		  											
		  											sousfamilearticleDAO.delete(sousfamilleArticlePF.getId());
		  											
		  											charger();
		  											JOptionPane.showMessageDialog(null, "Sous Famille Article supprimer avec succée ");
				  		  							initialiser();
		  											
		  										}
				  		   						
				  		   						
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnSupprimer.setEnabled(false);
				  		   			btnSupprimer.setBounds(205, 209, 107, 24);
				  		   			add(btnSupprimer);
				  		   			
	}
	
	
	
	void charger()
	{
		
		listSousFamilleArticlePF.clear();
		listSousFamilleArticlePF.addAll(sousfamilearticleDAO.findAll());
 		 afficher_tableSousFamille(listSousFamilleArticlePF);	
		
	}
	
	void initialiser()
	{
		
		txtcodesousfamillearticlepf.setText("");
		txtlibelle.setText("");
		comboFamilleArticle.setSelectedIndex(-1);
		btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnAjouter.setEnabled(true);
			
	}
	
	void afficher_tableSousFamille(List<SousFamilleArticlePF> listsousFamilleArticlePF)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code Sous Famille", "Libelle", "Famille Article"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false
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
			while(i<listsousFamilleArticlePF.size())
			{	
			
					Object []ligne={listsousFamilleArticlePF.get(i).getCode() ,listsousFamilleArticlePF.get(i).getLiblle(),listsousFamilleArticlePF.get(i).getFamileArticlePF().getLiblle()};

					modeleCat.addRow(ligne);
				
				
				
				i++;
			}
	}
}
