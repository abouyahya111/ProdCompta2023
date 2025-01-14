package fournisseur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main1.Main;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;

import util.Constantes;
import util.JasperUtils;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.entity.CoutMP;
import dao.entity.Fournisseur;
import dao.entity.Production;
import dao.entity.RipFournisseur;
import dao.entity.Sequenceur;


public class AjouterFournisseurOld extends JLayeredPane {

	public JLayeredPane contentPane;	
	private JTextField code;
	private String query;
	private String query1;
	private ResultSet rset;
	private Statement stx;
	private Connection con;
	private JTable table;
	private DefaultTableModel	 modele;
	private DefaultTableModel	 modelRIB;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JLabel lblTel;
	private JTextField txtTel;
	private JLabel lblAdresse;
	private JTextField txtAdresse;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private String seq;
	private Connection conSqlServ;
	private Statement stxSqlServ;
	private JButton initialiserRib;
	private ImageIcon imgAjouter;
	private ImageIcon imgModifier;
	private ImageIcon imgCalendar;
	private ImageIcon imgInit;
	private ImageIcon imgSupp;
	private ImageIcon imgBackG;
	private JLabel lblCode;
	private JTextField txtCodeRIB;
	private JTextField txtRIB;
	private JLabel lblNCompteBancaire;
	private JButton btnAjouter;
	private JButton initialiser;
	private JTextField nom;
	private JLayeredPane layeredPane_Rib;
	private JXTable table_1;
	
	private String id_fournisseur;
	private JLabel lblbg;
	
	List<RipFournisseur> listRipFournisseur= new ArrayList<RipFournisseur>();
	List<Fournisseur> listFournisseur= new ArrayList<Fournisseur>();
	
	private FournisseurDAO fournisseurDAO;
	private MatierePremiereDAO matierePremierDAO;
	private ProductionDAO productionDAO;
	private SequenceurDAO sequenceurDAO;
	
	private Production production;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterFournisseurOld() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
        try{
        	
        	fournisseurDAO=new FournisseurDAOImpl();
        	matierePremierDAO=new MatierePremierDAOImpl();
        	productionDAO=new ProductionDAOImpl();
        	sequenceurDAO=new SequenceurDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		

		
		try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgCalendar = new ImageIcon(this.getClass().getResource("/img/calendar.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupp=new ImageIcon(this.getClass().getResource("/img/supp.png"));
       	  imgBackG= new ImageIcon(this.getClass().getResource("/img/bg.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
		lblbg=new JLabel();
		
		
			 
		
		
		
		table = new JTable();
		table.setBounds(8, 31, 493, 0);
		
		table.setAutoCreateRowSorter(true);
		modele=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "CODE", "NOM", "ADRESSE", "TEL", "EMAIL"
			}
		){
		  	boolean[] columnEditables = new boolean[] {
			  		false, false, false, false, false, false
			  	};
		  	Class[] columnTypes = new Class[] {
	           		Integer.class,String.class,String.class,String.class,String.class,String.class
				};
		  	 public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			  	public boolean isCellEditable(int row, int column) {
			  		return columnEditables[column];
			  	}
			  };
		setLayout(null);
			  //table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
	
		table.setModel(modele);
		table.setRowHeight(20);
		this.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(table.getSelectedRow()!=-1)
				{
					code.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					nom.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtAdresse.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					txtTel.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					txtEmail.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					btnAjouter.setEnabled(false);
				}
				
				
				
			}
		});
		scrollPane.setBounds(8, 255, 513, 240);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportBorder(new CompoundBorder(new EmptyBorder(4, 6, 10, 10), null));
		this.add(scrollPane);
		charger();
		
		btnModifier = new JButton("  Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1)
				{
					boolean trouve=false;

					try{
						
						if( nom.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Veuillez entrer le nom!");
						} else if(txtTel.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Veuillez entrer le Numéro de téléphone!");
							
						}else if(txtEmail.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Veuillez entrer Adresse Email!");
							
						}else if(txtAdresse.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Veuillez entrer l'adresse!");
							
						}else {
							for(int i=0;i<listFournisseur.size();i++)
							{
								if(listFournisseur.get(i).getNom().equals(nom.getText()) && i !=table.getSelectedRow())
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
								JOptionPane.showMessageDialog(null, "Fournisseur modifier avec succ�e !!!!!","Bravo",JOptionPane.INFORMATION_MESSAGE);
								
								incrementer();
							}else
							{
								JOptionPane.showMessageDialog(null, "Fournisseur deja existant !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}
							
					
						}
					 }catch(Exception exp){
					  exp.printStackTrace();	
					 JOptionPane.showMessageDialog(null, "Erreur requète ajouter!", "Erreur", JOptionPane.ERROR_MESSAGE);
					 }
					
					
					
					
					
					
				}
				
			}
		});
		btnModifier.setBounds(531, 267, 100, 23);
	
		btnModifier.setIcon(imgModifier);
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=0;
				   if(table.getSelectedRow()==-1)
					     JOptionPane.showMessageDialog(null, "Il faut sélectionner un client!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				   else
				   { 
					   row = table.getSelectedRow();
					 String id_pr=table.getModel().getValueAt(row, 0).toString();
			//Modification
					 
				//  contentPane  = new ModifierClient(id_pr);
                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                  Main.generalScrollPane.setViewportView(contentPane);                
                  contentPane.setOpaque(true);
				   }
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(btnModifier);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow()!=-1)
				{
				Fournisseur fournisseur=listFournisseur.get(table.getSelectedRow())	;
				
				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer cette famille d'article ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
						
					{
						
						fournisseurDAO.delete(fournisseur.getId());
						charger();
							JOptionPane.showMessageDialog(null, "Fo supprimer avec succ�e ");
							initialiser();
						
						
					}
						
						
					
					
					
					
					
					
				}
				
			}
		});
		btnSupprimer.setBounds(531, 306, 100, 23);
		btnSupprimer.setIcon(imgSupp);
		
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				 try{
					   int row=0;
					   if(table.getSelectedRow()==-1)
						     JOptionPane.showMessageDialog(null, "Il faut sélectionner un client!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					   else
					   {
						   
						   int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment suprimer ce client?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							{
						  
						   afficher_Fournisseur(listFournisseur);;  
						  
							}
					   }

				 }catch (Exception e){
		                	}
						
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(btnSupprimer);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 498, 202);
		layeredPane.setBackground(SystemColor.menu);
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, null));
		add(layeredPane);
		
		lblTel = new JLabel("Tel :");
		lblTel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 255)));
		lblTel.setBounds(10, 52, 56, 19);
		layeredPane.add(lblTel);
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblAdresse = new JLabel("Adresse :");
		lblAdresse.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
		lblAdresse.setBackground(new Color(135, 206, 235));
		lblAdresse.setBounds(10, 87, 86, 20);
		layeredPane.add(lblAdresse);
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtTel = new JTextField();
		util.Utils.copycoller(txtTel);
		txtTel.setBounds(71, 47, 142, 26);
		layeredPane.add(txtTel);
		txtTel.setColumns(10);
		
		txtAdresse = new JTextField();
		util.Utils.copycoller(txtAdresse);
		txtAdresse.setBounds(71, 82, 382, 26);
		layeredPane.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		lblEmail.setBounds(241, 51, 102, 19);
		layeredPane.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
		txtEmail = new JTextField();
		txtEmail.setBounds(310, 45, 142, 26);
		layeredPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom ");
		lblNom.setBounds(9, 11, 56, 20);
		layeredPane.add(lblNom);
		lblNom.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		layeredPane_Rib = new JLayeredPane();
		layeredPane_Rib.setBounds(643, 11, 381, 202);
		layeredPane_Rib.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 127, 80), new Color(255, 99, 71)));
		add(layeredPane_Rib);
		layeredPane_Rib.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("CODE:");
				lblNewLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				lblNewLabel.setBounds(241, 10, 142, 20);
				layeredPane.add(lblNewLabel);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				code = new JTextField();
				code.setEnabled(false);
				code.setBackground(Color.WHITE);
				code.setBounds(310, 9, 142, 24);
				layeredPane.add(code);
				code.setForeground(Color.RED);
				code.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				code.setColumns(10);
				incrementer();
				btnAjouter = new JButton("Ajouter");
				btnAjouter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean trouve=false;

						try{
							
							if( nom.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Veuillez entrer le nom!");
							} else if(txtTel.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Veuillez entrer le Numéro de téléphone!");
								
							}else if(txtEmail.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Veuillez entrer Adresse Email!");
								
							}else if(txtAdresse.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Veuillez entrer l'adresse!");
								
							}else {
								for(int i=0;i<listFournisseur.size();i++)
								{
									if(listFournisseur.get(i).getNom().equals(nom.getText()))
									{
										trouve=true;
									}
									
								}
								if(trouve==false)
								{
									Fournisseur fournisseur=new Fournisseur();
									fournisseur.setCode(code.getText());
									fournisseur.setNom(nom.getText());
									fournisseur.setAdresse(txtAdresse.getText());
									fournisseur.setTel(txtTel.getText());
									fournisseur.setEmail(txtEmail.getText());
									fournisseurDAO.add(fournisseur);

								  charger();
									initialiser();
									JOptionPane.showMessageDialog(null, "Fournisseur Ajouter avec succ�e !!!!!","Bravo",JOptionPane.INFORMATION_MESSAGE);
									Sequenceur  sequenceur=sequenceurDAO.findByCode(Constantes.CODE_FOURNISSEUR);
									String codefournisseur="";
									if(sequenceur!=null)
									{
										int valeur=sequenceur.getValeur()+1;
										
										
										sequenceur.setValeur(valeur);
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
						 JOptionPane.showMessageDialog(null, "Erreur requète ajouter!", "Erreur", JOptionPane.ERROR_MESSAGE);
						 }
						
						
					
						
						
					}
				});
				btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnAjouter.setBounds(113, 150, 107, 24);
				layeredPane.add(btnAjouter);
				
				initialiser = new JButton("Initialiser");
				initialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				initialiser.setBounds(261, 151, 102, 23);
				layeredPane.add(initialiser);
				
				JButton btnPreparerAjoutRIB = new JButton("Ajouter RIB");
				btnPreparerAjoutRIB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//List<MatierePremier>	listMatierePremier =matierePremierDAO.findAll();
							
						
					}
				});
				btnPreparerAjoutRIB.setBounds(531, 340, 100, 23);
			/*	btnPreparerAjoutRIB.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						int row=0;
						   if(table.getSelectedRow()==-1)
							     JOptionPane.showMessageDialog(null, "Il faut sélectionner un fournisseur!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						   else
						   { 
							   layeredPane_Rib.setVisible(true);
							   row = table.getSelectedRow();
							  id_fournisseur=table.getModel().getValueAt(row, 0).toString();
							 
					
						   }
					}
				});*/
				btnPreparerAjoutRIB.setFont(new Font("Tahoma", Font.PLAIN, 11));
				add(btnPreparerAjoutRIB);
				
				
				//dep.setSelectedItem(authentification.depot);
				JButton btnAjouterRib = new JButton("Ajouter");
				btnAjouterRib.setBounds(79, 138, 107, 24);
				layeredPane_Rib.add(btnAjouterRib);
				btnAjouterRib.setIcon(imgAjouter);
				btnAjouterRib.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						List<Fournisseur>	listFournisseur = fournisseurDAO.findAll();
						Map parameters = new HashMap();
			            parameters.put("Titre", "Titre");
					//	JasperUtils.jasperGenerator(listFournisseur,parameters);
						/*
						RipFournisseur ripFournisseur = new RipFournisseur();
						ripFournisseur.setCode(txtCodeRIB.getText());
						ripFournisseur.setRip(txtRIB.getText());
						
						listRipFournisseur.add(ripFournisseur);
						afficher_tableRIB(listRipFournisseur);
					*/}
				});
				btnAjouter.setIcon(imgAjouter);
				btnAjouter.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {}
				});
				btnAjouterRib.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
				initialiserRib = new JButton("Initialiser");
				initialiserRib.setBounds(227, 139, 102, 23);
				layeredPane_Rib.add(initialiserRib);
				initialiserRib.setIcon(imgInit);
				
				initialiserRib.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
				lblCode = new JLabel("Code ");
				lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblCode.setBounds(10, 45, 73, 23);
				layeredPane_Rib.add(lblCode);
				
				txtCodeRIB = new JTextField();
				util.Utils.copycoller(txtCodeRIB);
				txtCodeRIB.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtCodeRIB.setBounds(164, 44, 165, 26);
				layeredPane_Rib.add(txtCodeRIB);
				txtCodeRIB.setColumns(10);
				
				txtRIB = new JTextField();
				util.Utils.copycoller(txtRIB);
				txtRIB.setBounds(164, 78, 165, 26);
				layeredPane_Rib.add(txtRIB);
				txtRIB.setColumns(10);
				
				lblNCompteBancaire = new JLabel("N\u00B0 Compte bancaire (RIB) ");
				lblNCompteBancaire.setBounds(10, 80, 200, 23);
				layeredPane_Rib.add(lblNCompteBancaire);
				
				
				
			
				
				
				
		initialiser.setIcon(imgInit);
		
		nom = new JTextField();
		util.Utils.copycoller(nom);
		nom.setBounds(71, 9, 142, 26);
		layeredPane.add(nom);
		nom.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(657, 224, 340, 166);
		add(scrollPane_1);
		table_1 = new JXTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setAutoCreateRowSorter(true);
		
		
		 
		initialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiser();
			}
		});
		
	
	//Fournisseur fournisseur=fournisseurDAO.findById(1);
	//listRipFournisseur=fournisseur.getRipFournisseurs();
	//afficher_tableRIB(listRipFournisseur);
	 lblbg.setIcon(imgBackG);
		// lblNewLabel.setBounds(0, 0, 573, 257);
	 lblbg.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		add(lblbg);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(531, 389, 100, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(527, 389, 89, 23);
		add(btnNewButton_1);
		
		JButton btnImprimer = new JButton("Imprimer ");
		btnImprimer.setBounds(542, 407, 89, 23);
		add(btnImprimer);
		
		JButton button = new JButton("Ajouter RIB");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(531, 423, 100, 23);
		add(button);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(542, 389, 89, 23);
		add(btnNewButton_2);
		
	}

void charger()
{
		
	listFournisseur=fournisseurDAO.findAll();
	afficher_Fournisseur(listFournisseur);
	
}
	
void incrementer()
{
	code.setText(util.Utils.genererCodeFournisseur());
	
}


	void afficher_Fournisseur(List<Fournisseur> listFournisseur )
	{
		try{
			
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
					  //table.getColumnModel().getColumn(2).setPreferredWidth(100);
				
			
				table.setModel(modele);
			
			
	int i=0;
			while(i<listFournisseur.size()){
				Fournisseur fournisseur=listFournisseur.get(i);
				
				Object []ligne={fournisseur.getCode(),fournisseur.getNom(),fournisseur.getAdresse(),fournisseur.getTel(),fournisseur.getEmail()};
		       modele.addRow(ligne);
                        
                       
i++;
		      
			}
			
		}
	  catch(Exception ex){
		ex.printStackTrace();
	  }	
	}
	
	
	void initialiser()
	{
		nom.setText("");
		txtTel.setText("");
		txtAdresse.setText("");
		txtEmail.setText("");
		btnAjouter.setEnabled(true);
	}
	
	void afficher_tableRIB(List<RipFournisseur> listRipFournisseur)
	{

		modelRIB=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				 "Code","RIB"
				}
			){
			  	boolean[] columnEditables = new boolean[] {
				  	false,false
				  	};
			  	Class[] columnTypes = new Class[] {
	           		String.class,String.class
				};
			  	
	           public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
	            
				  	public boolean isCellEditable(int row, int column) {
				  		return columnEditables[column];
				  	}
				  };
		  int i=0;
			while(i<listRipFournisseur.size())
			{	
				RipFournisseur ripFournisseur=listRipFournisseur.get(i);
				Object []ligne={ripFournisseur.getCode(),ripFournisseur.getRip()};

				modelRIB.addRow(ligne);
				i++;
			}

			table_1.setModel(modelRIB); 

	}
}
