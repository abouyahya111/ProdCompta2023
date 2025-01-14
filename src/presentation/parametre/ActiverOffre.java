package presentation.parametre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DetailEstimationPromoDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.Articles;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.StockMP;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JCheckBox;

import java.awt.GridBagLayout;
import java.awt.Component;

import javax.swing.JTable;


public class ActiverOffre extends JLayeredPane implements Constantes{
	
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modelePromotion;
	private DefaultTableModel	 modelePromotionOffre;
	private JXTable  tableoffre = new JXTable();
	

	
private ImageIcon imgModifierr;
private ImageIcon imgSupprimer;
private ImageIcon imgAjouter;
private ImageIcon imgInit;
private ImageIcon imgValider;
private JButton btnChercherOF;
private JButton btnImprimer;
private JButton btnRechercher;
private Utilisateur utilisateur;
private JComboBox comboarticle;
private JButton buttonvalider;
private PromotionDAO promotiondao=new PromotionDAOImpl();
private ArticlesDAO articledao=new ArticlesDAOImpl();
private Map<String, Articles> mapArticle = new HashMap<>();
private List<Articles> listArticles =new ArrayList<Articles>();
private List<DetailEstimationPromo> listDetailEstimationPromo =new ArrayList<DetailEstimationPromo>();
private MatierePremiereDAO matierepremierdao=new MatierePremierDAOImpl();
private List<Promotion> listPromotion =new ArrayList<Promotion>();
private JTextField txtcodearticle;
private JTextField txtlibelle;
private JTextField txtquantite;
private JTextField txtcodematiere;
private JButton buttonsupprimer ;
private DetailEstimationPromoDAO detailestimationpromodao;
private JXTable table;
private JButton btnModifier;
private JButton btnSupprimer;
private Promotion promotion;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */

	public ActiverOffre(){
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1351, 624);
       
        try{
        	detailestimationpromodao=new DetailEstimationPromoDAOImpl();
        	   imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
       tableoffre.setSortable(false);
       tableoffre.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		if(tableoffre.getSelectedRow()!=-1)
       		{
       			buttonsupprimer.setEnabled(true);
       		}				
       	}
       });
 
       tableoffre.setModel(new DefaultTableModel(
				  		   	new Object[][] {
				  		   	},
				  		   	new String[] {
				  		   		"Code", "Article", "Etat"
				  		   	}
    		   ) {
	     	boolean[] columnEditables = new boolean[] {
	     			false,false,true 
	     	};
	     	Class[] columnTypes = new Class[] {
	     			String.class,String.class, Boolean.class
				};
			  	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
	     	public boolean isCellEditable(int row, int column) {
	     		return columnEditables[column];
	     	}
	     });
       tableoffre.setShowVerticalLines(false);
       tableoffre.setSelectionBackground(new Color(51, 204, 255));
       tableoffre.setRowHeightEnabled(true);
       tableoffre.setBackground(new Color(255, 255, 255));
       tableoffre.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableoffre.setColumnControlVisible(true);
       tableoffre.setForeground(Color.BLACK);
       tableoffre.setGridColor(new Color(0, 0, 255));
       tableoffre.setAutoCreateRowSorter(true);
       tableoffre.setBounds(2, 27, 411, 198);
       tableoffre.setRowHeight(20);
       tableoffre.setSortable(false);
       DefaultCellEditor ce = (DefaultCellEditor) tableoffre.getDefaultEditor(Object.class);
	        JTextComponent textField = (JTextComponent) ce.getComponent();
	        util.Utils.copycollercell(textField);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableoffre);
				  		     	scrollPane.setBounds(14, 192, 607, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Offres");
				  		     	titledSeparator.setBounds(14, 167, 607, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(14, 65, 607, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblArticle = new JLabel("   Article :");
				  		     	lblArticle.setBounds(298, 11, 82, 26);
				  		     	layeredPane.add(lblArticle);
				  		     	lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		      comboarticle = new JComboBox();
				  			 comboarticle.addItem("");
				  		     	 comboarticle.addActionListener(new ActionListener() {
				  		     	 	public void actionPerformed(ActionEvent arg0) {
				  		     	 	
				  		     	 	if(comboarticle.getSelectedIndex()!=-1)
				  		     	 	{
				  		     	 		
				  		     	 		
				  		     	 		if(!comboarticle.getSelectedItem().equals(""))
				  		     	 		{
				  		     	 		
				  		     	  	txtcodearticle.setText(mapArticle.get(comboarticle.getSelectedItem()).getCodeArticle());
			  		     	 		
			  		     	 		
		  		     	 			
		  		     	 			if(!comboarticle.getSelectedItem().equals(""))
		  		     	 			{
		  		     	 				Articles article =mapArticle.get(comboarticle.getSelectedItem());
		  		     	 				
		  		     	 				listPromotion=promotiondao.findByArticle(article.getId());
		  		     	 				if(listPromotion.size()!=0)
		  		     	 				{
		  		     	 					afficher_tablePromotion(listPromotion);
		  		     	 					buttonvalider.setEnabled(true);
		  		     	 					
		  		     	 				}else
		  		     	 				{
		  		     	 			    JOptionPane.showMessageDialog(null, "Il y a Aucun offre pour cet article SVP");
		  		     	 				buttonvalider.setEnabled(false);
		  		     	 				listPromotion.clear();
		  		     	 			     afficher_tablePromotion(listPromotion);
		  		     	 			    
		  		     	 				}
		  		     	 				
		  		     	 			}
		  		     	 			
		  		     	 			
				  		     	 	}
		  		     	 			
		  		     	 			
				  		     	 	}
				  		     	
				  		     	 	}
				  		     	 });
				  		     	 comboarticle.setBounds(350, 11, 245, 26);
				  		     	 layeredPane.add(comboarticle);
		
		 buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valider=false;
				if(listPromotion.size()!=0)
				{
					for(int i=0;i<tableoffre.getRowCount();i++)
					{
						 promotion=listPromotion.get(i);
						promotion.setActif(Boolean.valueOf(tableoffre.getValueAt(i, 2).toString()));
						listPromotion.set(i,promotion);
						promotiondao.edit(promotion);
						valider=true;
						}
					if(valider==true)
					{
						JOptionPane.showMessageDialog(null, "Promotion valider avec succee ");
						InitialisertablePromotion();
						buttonvalider.setEnabled(false);
						comboarticle.setSelectedIndex(-1);
						txtcodearticle.setText("");
					}
					
				}
				
				}
			});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(239, 467, 112, 24);
		add(buttonvalider);
		
		
		txtcodearticle = new JTextField();
		util.Utils.copycoller(txtcodearticle);
		txtcodearticle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

		  		if(e.getKeyCode()==e.VK_ENTER)
	      		{
	      			if(!txtcodearticle.getText().equals(""))
	      			{
	      				Articles article=articledao.findByCode(txtcodearticle.getText());
	      				if(article!=null)
	      				{
	      					comboarticle.setSelectedItem(article.getLiblle());
	      				}
	      				
	      			}else{
	      				JOptionPane.showMessageDialog(null, "Code Article introuvable !!!");
	      			}
		  		
	      		}	
			}
		});
		txtcodearticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
		});
		txtcodearticle.setColumns(10);
		txtcodearticle.setBounds(103, 11, 185, 26);
		layeredPane.add(txtcodearticle);
		
		JLabel label = new JLabel("Code Article :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(25, 11, 82, 26);
		layeredPane.add(label);
		listPromotion.clear();
		afficher_tablePromotion(listPromotion);
		buttonvalider.setEnabled(false);
		
		 listArticles.clear();
			listArticles=articledao.findAll();
			comboarticle.addItem("");
			int i=0;
			while (i<listArticles.size()) {
				Articles article=listArticles.get(i);
				mapArticle.put(article.getLiblle(), article);
				comboarticle.addItem(article.getLiblle());
				i++;
			}
			comboarticle.setSelectedIndex(-1);
			
			JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
			GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
			gridBagLayout.rowWeights = new double[]{0.0};
			gridBagLayout.rowHeights = new int[]{0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
			gridBagLayout.columnWidths = new int[]{0, 0, 0};
			titledSeparator_1.setTitle("Liste Matiere Premiere ");
			titledSeparator_1.setBounds(763, 172, 580, 30);
			add(titledSeparator_1);
			
			JLayeredPane layeredPane_1 = new JLayeredPane();
			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			layeredPane_1.setBounds(740, 48, 693, 108);
			add(layeredPane_1);
			
			JLabel label_1 = new JLabel("Quantite :");
			label_1.setBounds(470, 11, 102, 26);
			layeredPane_1.add(label_1);
			
			JLabel label_2 = new JLabel("Code Matiere :");
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_2.setBounds(10, 11, 82, 26);
			layeredPane_1.add(label_2);
			
			txtlibelle = new JTextField();
			txtlibelle.setEditable(false);
			txtlibelle.setColumns(10);
			txtlibelle.setBounds(303, 11, 157, 26);
			layeredPane_1.add(txtlibelle);
			
			JLabel label_3 = new JLabel("Libelle :");
			label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
			label_3.setBounds(256, 11, 68, 26);
			layeredPane_1.add(label_3);
			
			txtquantite = new JTextField();
			util.Utils.copycoller(txtquantite);
			txtquantite.setColumns(10);
			txtquantite.setBounds(531, 11, 157, 26);
			layeredPane_1.add(txtquantite);
			
			 btnModifier = new JButton("Modifier");
			btnModifier.setEnabled(false);
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						
						
						if(txtcodematiere.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez entre le code Matiere premiere SVP !!!!");
							return;
							
						}else if(txtlibelle.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez entre le Nom de Matiere premiere SVP !!!!");
							return;
						}else if(txtquantite.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez entre la Quantit� de Matiere premiere SVP !!!!");
							return;
						}
					else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
		    		{
		    			JOptionPane.showMessageDialog(null, "la Quantit� de matiere premiere doit etre en chiffre et sup�rieur � 0 SVP !!!");
		    			return;
		    		}else
						{
							if(listDetailEstimationPromo.size()!=0)
							{
								if(table.getSelectedRow()!=-1)
								{
								 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la matiere premiere ?", 
												"Satisfaction", JOptionPane.YES_NO_OPTION);
										 
										if(reponse == JOptionPane.YES_OPTION )
										{
											
											
											
									DetailEstimationPromo detailestimationpromo=listDetailEstimationPromo.get(table.getSelectedRow());
									detailestimationpromo.setQuantite(new BigDecimal(txtquantite.getText()));
									detailestimationpromodao.edit(detailestimationpromo);
									listDetailEstimationPromo.set(table.getSelectedRow(), detailestimationpromo);
									afficher_tablePromotionOffre(listDetailEstimationPromo);
									JOptionPane.showMessageDialog(null, "Matiere Premiere modifier avec succ�e ");
									 initialiser();
										}
								}
								
							}else
							{
								JOptionPane.showMessageDialog(null, "liste Matiere premiere est vide !!!!!!");
								
							}
							
							
						}
							
					} catch (NumberFormatException r) {  JOptionPane.showMessageDialog(null, "la Quantite de matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					
			}
			});
			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnModifier.setBounds(115, 59, 107, 24);
			layeredPane_1.add(btnModifier);
			
			JButton initialiser = new JButton("Initialiser");
			initialiser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					initialiser();
							
				}
			});
			
			
			initialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
			initialiser.setBounds(247, 60, 106, 23);
			layeredPane_1.add(initialiser);
			
			txtcodematiere = new JTextField();
			util.Utils.copycoller(txtcodematiere);
			txtcodematiere.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					
					if(e.getKeyCode()==e.VK_ENTER)
		      		{
		      			if(!txtcodematiere.getText().equals(""))
		      			{
		      				MatierePremier matierepremiere=matierepremierdao.findByCode(txtcodematiere.getText().toUpperCase());
				    		
				    		if(matierepremiere!=null)
				    		{	
				    			txtlibelle.setText(matierepremiere.getNom());
				    			
				    		}else
				    		{
				    			 JOptionPane.showMessageDialog(null, "Code matiere premiere Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				    			 txtlibelle.setText("");
				    			 txtcodematiere.requestFocus();
				    		}
		      				
		      				
		      		}else
		      		{
		      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code matiere premiere SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		      			 txtlibelle.setText("");
		      			txtcodematiere.requestFocus();
		      		}
		      		}
					
					
					
					
					
					
					
					
				}
			});
			txtcodematiere.setColumns(10);
			txtcodematiere.setBounds(89, 11, 157, 26);
			layeredPane_1.add(txtcodematiere);
			
			 btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setEnabled(false);
			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow()!=-1)
					{
						if(listDetailEstimationPromo.size()!=0)
						{
							 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer la matiere premiere ?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
								 
								if(reponse == JOptionPane.YES_OPTION )
								{
									detailestimationpromodao.delete(listDetailEstimationPromo.get(table.getSelectedRow()).getId());
									listDetailEstimationPromo.remove(table.getSelectedRow());
									afficher_tablePromotionOffre(listDetailEstimationPromo);
									JOptionPane.showMessageDialog(null, "Matiere Premiere supprimer avec succ�e ");
									 initialiser();
									
								}
							
							
						}
						}
					
				}
			});
			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnSupprimer.setBounds(383, 59, 107, 24);
			layeredPane_1.add(btnSupprimer);
			
			JButton btnAjouter = new JButton("Ajouter");
			btnAjouter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					boolean trouve=false;
try {
		    			
		    			if(txtcodematiere.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez entrer le code matiere premiere SVP !!!");
		    			return;
		    		}else if(txtlibelle.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez entrer le code matiere premiere SVP !!!");
		    			return;
		    		}
		    		else if(txtquantite.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantit� de matiere premiere SVP !!!");
		    			return;
		    		}else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
		    		{
		    			JOptionPane.showMessageDialog(null, "la Quantit� de matiere premiere doit etre en chiffre et sup�rieur � 0 SVP !!!");
		    			return;
		    		}
		    		else
		    		{
		    			
		    			MatierePremier matierepremiere=matierepremierdao.findByCode(txtcodematiere.getText().toUpperCase());
		    			if(matierepremiere!=null)
		    			{
		    				for(int i=0; i<listDetailEstimationPromo.size();i++)
		    				{
		    					if(listDetailEstimationPromo.get(i).getMatierePremiere().getCode().equals(matierepremiere.getCode()))
		    					{
		    						trouve=true;
		    						JOptionPane.showMessageDialog(null, "Matiere premiere existe deja dans l'offre !!!!!");
		    						return;
		    					}
		    				}
		    				if(trouve==false)
		    				{
		    				DetailEstimationPromo detailestimationpromo=new DetailEstimationPromo();
		    			  	detailestimationpromo.setMatierePremiere(matierepremiere);
		    				detailestimationpromo.setQuantite(new BigDecimal(txtquantite.getText()));	
		    				detailestimationpromo.setPromotion(promotion);
		    				
		    				detailestimationpromodao.add(detailestimationpromo);
		    				listDetailEstimationPromo.add(detailestimationpromo);
		    				afficher_tablePromotionOffre(listDetailEstimationPromo);
		    				initialiser();
		    						
		    				}
		    				
		    			
		    				
		    				
		    			}
		    				}
		    		
		    					
					}  catch (NumberFormatException r) {  JOptionPane.showMessageDialog(null, "la Quantite de matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}
		    		
					
					
					
					
					
					
					
				}
			});
			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnAjouter.setBounds(507, 59, 107, 24);
			layeredPane_1.add(btnAjouter);
			
			JButton btnvalider = new JButton("Valider ");
			btnvalider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(table.getRowCount()!=0)
					{
						if(listDetailEstimationPromo.size()!=0)
						{
							
					
						Promotion promotion =listDetailEstimationPromo.get(0).getPromotion();
						promotion.setDetailEstimationPromo(listDetailEstimationPromo);
						promotiondao.edit(promotion);
					
							
						JOptionPane.showMessageDialog(null, "l'offre modifier avec succ�e");
						initialiser();
						///listDetailEstimationPromo.clear();
						//afficher_tablePromotionOffre(listDetailEstimationPromo);
						//afficher_tablePromotion(listPromotion);
						InitialisertablePromotionOffre();
						buttonsupprimer.setEnabled(false);
							
							
							}
					}
					
					
					
				}
			});
			btnvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnvalider.setIcon(imgValider);
			btnvalider.setBounds(1011, 478, 112, 24);
			add(btnvalider);
			
			JButton btnNewButton = new JButton("Consulter Offre");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg ) {
				
					if(listPromotion.size()!=0)
					{
						if(tableoffre.getSelectedRow()!=-1)
						{
							 promotion =listPromotion.get(tableoffre.getSelectedRow());
                            listDetailEstimationPromo=detailestimationpromodao.findByIdPromo(promotion.getId());
							
							afficher_tablePromotionOffre(listDetailEstimationPromo);
							
					}
						
						
					}else
					{
						JOptionPane.showMessageDialog(null, "la liste des promotion est vide !!!!!");
					}
	
				}
			});
			btnNewButton.setBounds(631, 233, 112, 23);
			add(btnNewButton);
			
			JScrollPane scrollPane_1 = new JScrollPane((Component) null);
			scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			scrollPane_1.setBounds(761, 202, 582, 264);
			add(scrollPane_1);
			
			 table = new JXTable();
			 table.setSortable(false);
			 table.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent arg0) {
			 		
			 	if(listDetailEstimationPromo.size()!=0)
			 	{
			 		if(table.getSelectedRow()!=-1)
			 		{
			 			
			 			txtcodematiere.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			 			txtlibelle.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			 			txtquantite.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			 			btnSupprimer.setEnabled(true);
			 			btnModifier.setEnabled(true);
			 			txtcodematiere.setEditable(false);
			 			
			 			
			 			
			 		}
			 		
			 		
			 	}
			 		
			 		
			 		
			 		
			 		
			 		
			 		
			 	}
			 });
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Code", "Matiere Premiere", "Quantite"
				}
			));
			table.getColumnModel().getColumn(1).setPreferredWidth(92);
			table.setShowVerticalLines(false);
			table.setSelectionBackground(new Color(51, 204, 255));
			table.setRowHeightEnabled(true);
			table.setRowHeight(20);
			table.setGridColor(Color.BLUE);
			table.setForeground(Color.BLACK);
			table.setColumnControlVisible(true);
			table.setBackground(Color.WHITE);
			scrollPane_1.setViewportView(table);
			
			 buttonsupprimer = new JButton();
			buttonsupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(tableoffre.getSelectedRow()!=-1)
					{
						
						if(table.getRowCount()!=0 && listDetailEstimationPromo.size()!=0 )
						{
							if(listDetailEstimationPromo.get(0).getPromotion().getCode().equals(tableoffre.getValueAt(0, tableoffre.getSelectedRow())))
							{
								
								JOptionPane.showMessageDialog(null, "Impossible de supprimer l'offre veullez valider la matiere premier avant la suppression de son offre !!!!!");
								return;
							}
							
							}
						
						  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer Cet Offre?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
							{
								
									Promotion promotion=listPromotion.get(tableoffre.getSelectedRow());
								promotiondao.delete(promotion.getId());
								
								JOptionPane.showMessageDialog(null, "Offre Supprimer avec succ�e ");
								Articles article =mapArticle.get(comboarticle.getSelectedItem());
								listPromotion=promotiondao.findByArticle(article.getId());
								afficher_tablePromotion(listPromotion);
								List<DetailEstimationPromo> listDetailEstimationPromo=new ArrayList<DetailEstimationPromo>();
								afficher_tablePromotionOffre(listDetailEstimationPromo);
								buttonsupprimer.setEnabled(false);
								
							}
						
						
					}
					
					}
				});
					
					
					
			
			buttonsupprimer.setEnabled(false);
			buttonsupprimer.setBounds(647, 281, 73, 24);
			buttonsupprimer.setIcon(imgSupprimer);
			add(buttonsupprimer);
		
		
		}
	 void initialiser()
	 {
		 txtcodematiere.setText("");
			txtlibelle.setText("");
			txtquantite.setText("");
			txtcodematiere.requestFocus();
			btnSupprimer.setEnabled(false);
 			btnModifier.setEnabled(false);
 			txtcodematiere.setEditable(true);
			
		 
	 }
	void afficher_tablePromotion(List<Promotion> listPromotion)
	{
		modelePromotion =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code", "Article", "Etat"
				}
			)    {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,true 
		     	};
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		tableoffre.setModel(modelePromotion);
		int i=0;
		 
		while(i<listPromotion.size())
		{	
			Promotion promotion=listPromotion.get(i);
			
			Object []ligne={promotion.getCode(),promotion.getArticle().getLiblle(),promotion.isActif()};

			modelePromotion.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tablePromotionOffre(List<DetailEstimationPromo> listDetailEstimationPromo)
	{
		modelePromotionOffre =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code", "Matiere Premier", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelePromotionOffre);
		int i=0;
		
		while(i<listDetailEstimationPromo.size())
		{	
			DetailEstimationPromo detailestimationpromo=listDetailEstimationPromo.get(i);
			
		
			MatierePremier matierepremiere=detailestimationpromo.getMatierePremiere();
			if(matierepremiere!=null)
			{
			String code=matierepremiere.getCode();
			String nom=matierepremiere.getNom();
			BigDecimal quantite =detailestimationpromo.getQuantite();
			Object []ligne={code,nom,quantite};

			modelePromotionOffre.addRow(ligne);
			}
			i++;
		}
}
	
	
	
	void InitialisertablePromotionOffre()
	{
		modelePromotionOffre =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code", "Matiere Premier", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelePromotionOffre);
	
}
	
	
	void InitialisertablePromotion()
	{
		modelePromotion =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code", "Article", "Etat"
				}
			)    {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,true 
		     	};
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class, Boolean.class
					};
				  	
		       public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		tableoffre.setModel(modelePromotion);
		
}
	
	
}



