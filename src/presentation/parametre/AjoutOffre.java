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


public class AjoutOffre extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modelePromotion;

	private JXTable  tablematiere = new JXTable();
	private List<DetailEstimationPromo> listDetailEstimation =new ArrayList<DetailEstimationPromo>();

	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	private JCheckBox checkactif;
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private JComboBox comboarticle;
	private JTextField txtlibelle;
	private JTextField txtQuantite;
private PromotionDAO promotiondao=new PromotionDAOImpl();
private ArticlesDAO articledao=new ArticlesDAOImpl();
private Map<String, Articles> mapArticle = new HashMap<>();
private List<Articles> listArticles =new ArrayList<Articles>();
private MatierePremiereDAO matierepremierdao=new MatierePremierDAOImpl();
private List<DetailEstimationPromo> listDetailEstimationPromo =new ArrayList<DetailEstimationPromo>();
	private JTextField txtcodematiere;
	private JTextField textcode;
	private JButton suppbutton;
	private JButton modifbutton;
	private JLabel lblCodeArticle;
	private JTextField txtcodearticle;
	private DetailEstimationPromoDAO detailestimationpromodao;
	private Promotion promotion =new Promotion();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutOffre(){
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 624);
      
	
        try{
        	
        	promotion =new Promotion();
        	detailestimationpromodao=new DetailEstimationPromoDAOImpl();
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
        tablematiere.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       			txtcodematiere.setText(tablematiere.getValueAt(tablematiere.getSelectedRow(), 0).toString());
       			txtlibelle.setText(tablematiere.getValueAt(tablematiere.getSelectedRow(), 1).toString());
       			txtQuantite.setText(tablematiere.getValueAt(tablematiere.getSelectedRow(), 2).toString());
       			btnAjouter.setEnabled(false);
       			modifbutton.setEnabled(true);
       			suppbutton.setEnabled(true);
       		
       		
       		 	}
       });
        
       tablematiere.setModel(new DefaultTableModel(
				  		   	new Object[][] {
				  		   	},
				  		   	new String[] {
				  		   		"Code", "Matiere Premiere", "Quantite"
				  		   	}
				  		   ));
				  		
       tablematiere.setShowVerticalLines(false);
       tablematiere.setSelectionBackground(new Color(51, 204, 255));
       tablematiere.setRowHeightEnabled(true);
       tablematiere.setBackground(new Color(255, 255, 255));
       tablematiere.setHighlighters(HighlighterFactory.createSimpleStriping());
       tablematiere.setColumnControlVisible(true);
       tablematiere.setForeground(Color.BLACK);
       tablematiere.setGridColor(new Color(0, 0, 255));
       tablematiere.setAutoCreateRowSorter(true);
       tablematiere.setBounds(2, 27, 411, 198);
       tablematiere.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tablematiere);
				  		     	scrollPane.setBounds(14, 192, 933, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Matiere Premiere ");
				  		     	titledSeparator.setBounds(14, 167, 933, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(4, 48, 943, 108);
				  		     	add(layeredPane);
				  		  
				  		  JLabel lblMontant = new JLabel("Quantite :");
				  		  lblMontant.setBounds(608, 11, 102, 26);
				  		  layeredPane.add(lblMontant);
		
		  JLabel codeoffre = new JLabel("Code Matiere :");
		  codeoffre.setBounds(105, 11, 82, 26);
		  layeredPane.add(codeoffre);
		  codeoffre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  txtlibelle = new JTextField();
		  txtlibelle.setEditable(false);
		  txtlibelle.setBounds(427, 11, 157, 26);
		  layeredPane.add(txtlibelle);
		  txtlibelle.setColumns(10);
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(380, 12, 68, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		    
		    txtQuantite = new JTextField();
		    util.Utils.copycoller(txtQuantite);
		    txtQuantite.setColumns(10);
		    txtQuantite.setBounds(674, 12, 157, 26);
		    layeredPane.add(txtQuantite);
		   
		    
		    btnAjouter = new JButton("Ajouter");
		    btnAjouter.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		
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
		    		else if(txtQuantite.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantit� de matiere premiere SVP !!!");
		    			return;
		    		}else if(new BigDecimal(txtQuantite.getText()).compareTo(BigDecimal.ZERO) <=0)
		    		{
		    			JOptionPane.showMessageDialog(null, "la Quantit� de matiere premiere doit etre en chiffre et sup�rieur � 0 SVP !!!");
		    			return;
		    		}
		    		else
		    		{
		    			
		    			MatierePremier matierepremiere=matierepremierdao.findByCode(txtcodematiere.getText().toUpperCase());
		    			if(matierepremiere!=null)
		    			{
		    				DetailEstimationPromo detailestimationpromo=new DetailEstimationPromo();
		    			    matierepremiere.setCode(txtcodematiere.getText().toUpperCase());
		    				matierepremiere.setNom(txtlibelle.getText());
		    				detailestimationpromo.setMatierePremiere(matierepremiere);
		    				detailestimationpromo.setQuantite(new BigDecimal(txtQuantite.getText()));
		    				detailestimationpromo.setPromotion(promotion);
		    				
		    				listDetailEstimationPromo.add(detailestimationpromo);
		    				afficher_tablePromotion(listDetailEstimationPromo);
		    				intialiser();
		    				
		    				
		    			}
		    				}
		    		
		    					
					}  catch (NumberFormatException e) {  JOptionPane.showMessageDialog(null, "la Quantite de matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}
		    		
		    				
		    		
		    	}
		    });
		    btnAjouter.setBounds(375, 74, 107, 24);
		    layeredPane.add(btnAjouter);
		    btnAjouter.setIcon(imgAjouter);
		    
		      btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      btnInitialiser = new JButton("Initialiser");
		      btnInitialiser.setBounds(509, 75, 106, 23);
		      layeredPane.add(btnInitialiser);
		      btnInitialiser.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      	intialiser();
		      		
		      	}
		      });
		      btnInitialiser.setIcon(imgInit);
		      btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
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
		      txtcodematiere.setBounds(193, 11, 157, 26);
		      layeredPane.add(txtcodematiere);
		
		 modifbutton = new JButton();
		modifbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				if(txtcodematiere.getText().equals("") || txtlibelle.getText().equals("") || txtQuantite.getText().equals(""))
	    		{
	    		  JOptionPane.showMessageDialog(null, "Veuillez  entrer tous les donn�es SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	    			   
	    		}
	    		else
	    		{
	    		try {
	    			
	    			if(new BigDecimal(txtQuantite.getText()).compareTo(BigDecimal.ZERO)>0)
	    			{
	    				
	    		MatierePremier matierepremiere=matierepremierdao.findByCode(txtcodematiere.getText().toUpperCase());
	    		
	    		if(matierepremiere!=null)
	    		{
	    			
	    			Integer row=tablematiere.getSelectedRow();
	    			if(row!=-1)
	    			{
	   
	    				matierepremiere.setCode(txtcodematiere.getText().toUpperCase());
	    				matierepremiere.setNom(txtlibelle.getText());
	    				DetailEstimationPromo detailEstimationPromo=new DetailEstimationPromo();
	    				detailEstimationPromo.setMatierePremiere(matierepremiere);
	    				detailEstimationPromo.setQuantite(new BigDecimal(txtQuantite.getText()));
	    				detailEstimationPromo.setPromotion(promotion);
	    				
	    				listDetailEstimationPromo.set(row, detailEstimationPromo);
	    				afficher_tablePromotion(listDetailEstimationPromo);
	    				intialiser();
	    			}else
	    			{
	    				 JOptionPane.showMessageDialog(null, "Veuillez selectionner un enregistrement SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	    			}
	    		
	    		
	    		
	    		}else
	    		{
	    			 JOptionPane.showMessageDialog(null, "Le code matiere premiere  introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
	    		}
	    		
	    		
	    			}else
	    			{
	    				JOptionPane.showMessageDialog(null, "la quantite de matiere premiere doit etre en supperier � 0 SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	    			}
	    		
					} catch (NumberFormatException e) {  JOptionPane.showMessageDialog(null, "la Quantite de Matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}
	    		
	    		
	    		}
				
			
				
				
			}
		});
		modifbutton.setIcon(imgModifierr);
		modifbutton.setBounds(969, 264, 73, 24);
		add(modifbutton);
		
		 suppbutton = new JButton();
	 	 suppbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablematiere.getSelectedRow() !=-1)
				{
				
				listDetailEstimationPromo.remove(tablematiere.getSelectedRow());
				afficher_tablePromotion(listDetailEstimationPromo);
				intialiser();
					
				}
				else
				{

   				 JOptionPane.showMessageDialog(null, "Veuillez selectionner un enregistrement SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				}
					
			}
		});
		suppbutton.setIcon(imgSupprimer);
		suppbutton.setBounds(969, 311, 73, 24);
		add(suppbutton);
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				BigDecimal totale=BigDecimal.ZERO;
				if(textcode.getText().equals(""))
						{
					
					 JOptionPane.showMessageDialog(null, "Veuillez actualiser la page SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					 return;
					
						}else if(comboarticle.getSelectedItem()==null)
						{
							 JOptionPane.showMessageDialog(null, "Veuillez choisir un article SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
							 return;
						}else if(listDetailEstimationPromo.size()>0)
							{
								    
						
								    promotion.setCode(textcode.getText());
									promotion.setActif(checkactif.isSelected());
									promotion.setArticle(mapArticle.get(comboarticle.getSelectedItem()));
								//	promotion.setDetailEstimationPromo(listDetailEstimationPromo);
									promotiondao.add(promotion);
									
									for(int i=0 ; i<listDetailEstimationPromo.size();i++)
									{
										
										detailestimationpromodao.add(listDetailEstimationPromo.get(i));
										
									}
									JOptionPane.showMessageDialog(null, "promotion valider avec succ�e !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
									intialiser();
									listDetailEstimationPromo.clear();
									afficher_tablePromotion(listDetailEstimationPromo);
									 checkactif.setSelected(false);
								     comboarticle.setSelectedIndex(-1);
								     txtcodearticle.setText("");
								     textcode.setText("");
									
						
							}
							else
							{
								 JOptionPane.showMessageDialog(null, "La liste des matieres premiere est vide !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
								
							}}
			});
		
		
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(319, 478, 112, 24);
		add(buttonvalider);
		
		 comboarticle = new JComboBox();
	 comboarticle.addItem("");
		 comboarticle.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(comboarticle.getSelectedIndex()!=-1)
		 		{
		 			if(!comboarticle.getSelectedItem().equals(""))
			 		{
			 			
			 		
			 			txtcodearticle.setText(mapArticle.get(comboarticle.getSelectedItem()).getCodeArticle());
			 			textcode.setText(txtcodearticle.getText()+Constantes.PROMOTION_OFFRE+promotiondao.maxIdPromotion());
			 		}
			 	
		 		}
		 		
		 		
		 	}
		 });
		comboarticle.setBounds(601, 11, 272, 26);
		add(comboarticle);
		
		JLabel lblArticle = new JLabel("   Article :");
		lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblArticle.setBounds(529, 11, 62, 26);
		add(lblArticle);
		
		 checkactif = new JCheckBox("  Actif");
		checkactif.setBounds(879, 15, 62, 23);
		add(checkactif);
		
		textcode = new JTextField();
		 util.Utils.copycoller(textcode);
		textcode.setEditable(false);
		textcode.setColumns(10);
		textcode.setBounds(57, 11, 157, 26);
		add(textcode);
		
		JLabel lblCode = new JLabel("Code :");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCode.setBounds(10, 11, 68, 26);
		add(lblCode);
		
		  modifbutton.setEnabled(false);
		  suppbutton.setEnabled(false);
		  
		  lblCodeArticle = new JLabel("Code Article :");
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  lblCodeArticle.setBounds(246, 11, 82, 26);
		  add(lblCodeArticle);
		  
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
	      				}else{
		      				JOptionPane.showMessageDialog(null, "Code Article introuvable !!!");
		      			}
			  		
	      				
	      			}
		  		
	      		}
		  		
		  		
		  	}
		  });
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
			
		
		
		  
		  txtcodearticle.setColumns(10);
		  txtcodearticle.setBounds(324, 11, 185, 26);
		  add(txtcodearticle);
		
		}
	
	
	void intialiser()
	{
		txtlibelle.setText("");
		txtcodematiere.setText("");
	     txtQuantite.setText("");	
	     txtcodematiere.requestFocus();
	     btnAjouter.setEnabled(true);
	    
	     modifbutton.setEnabled(false);
		 suppbutton.setEnabled(false);
		
	}
	
	void afficher_tablePromotion(List<DetailEstimationPromo> listDetailEstimationPromo)
	{
		modelePromotion =new DefaultTableModel(
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
		tablematiere.setModel(modelePromotion);
		int i=0;
		 
		while(i<listDetailEstimationPromo.size())
		{	
			DetailEstimationPromo detailestimationpromo=listDetailEstimationPromo.get(i);
			MatierePremier matierepremiere=detailestimationpromo.getMatierePremiere();
			
			String code=matierepremiere.getCode();
			String nom=matierepremiere.getNom();
			BigDecimal quantite =detailestimationpromo.getQuantite();
			Object []ligne={code,nom,quantite};

			modelePromotion.addRow(ligne);
			i++;
		}
}
}



