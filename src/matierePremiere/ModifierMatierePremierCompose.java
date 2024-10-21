package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import dao.daoImplManager.ArticlesMPDAOImpl;
import dao.daoImplManager.DetailEstimationMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.Articles;
import dao.entity.ArticlesMP;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.MatierePremier;
import dao.entity.Promotion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifierMatierePremierCompose extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgModifier;
	private JButton btnIntialiserOF;

	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, ArticlesMP> mapAricleMP = new HashMap<>();
	private Map< String, String> mapCodeArticleMP= new HashMap<>();
	private Map< String, String> mapLibelleAricleMP = new HashMap<>();
	
	List < ArticlesMP> listArticlesMP = new ArrayList<ArticlesMP>();
	
	List<DetailEstimationMP> listDetailEstimationMP = new ArrayList<DetailEstimationMP>();
	
	
	private JLabel lblDpotDestination;
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesMPDAO articlesMPDAO;
	ArticlesMP articlesMP =new ArticlesMP() ;
	private JTextField txtCode;
	private JComboBox comboBox;
	private JButton btnChercher;
	private JButton btnIntialiser;
	private JTextField txtlibelle;
	private JTextField txtquantite;
	private JTextField txtcodematiere;
	private JButton bouttonModifier;
	private JButton btnSupprimer;
	private JButton btnAjouter;
	private DetailEstimationMPDAO detailestimationmpdao;


	public ModifierMatierePremierCompose() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 730);
        try{
        	
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	articlesMPDAO=new ArticlesMPDAOImpl();
        	detailestimationmpdao=new DetailEstimationMPDAOImpl();
        	 comboBox = new JComboBox();
        	 comboBox.addItem("");
        	  txtCode = new JTextField();
        	  util.Utils.copycoller(txtCode);
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(432, 664, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     
				  		   listArticlesMP=articlesMPDAO.findAll();
				  	        int i=0;
				  		      	while(i<listArticlesMP.size())
				  		      		{	
				  		      			ArticlesMP articleMP=listArticlesMP.get(i);
				  		      			mapCodeArticleMP.put(articleMP.getLiblle(), articleMP.getCodeArticle());
				  		      			mapLibelleAricleMP.put( articleMP.getCodeArticle(),articleMP.getLiblle());
				  		      			mapAricleMP.put(articleMP.getLiblle(), articleMP);
				  		      			comboBox.addItem(articleMP.getLiblle());
				  		      			i++;
				  		      		}
				  			
				  		      comboBox.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	
				  		     	 		txtCode.setText(mapCodeArticleMP.get(comboBox.getSelectedItem()));
				  	                  
				  		     	 	 	}
				  		     	 	}
				  		     	 });
				  		      
				  			txtCode.addKeyListener(new KeyAdapter() {
				  			  	@Override
				  			  	public void keyReleased(KeyEvent e)
				  			  	{
				  			  		if (e.getKeyCode() == e.VK_ENTER)
				  			  		{
				  			  			
				  			  		comboBox.setSelectedItem(mapLibelleAricleMP.get(txtCode.getText()));
				  			  		}}});
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		if(listDetailEstimationMP.size()!=0)
				  				 	{
				  				 		if(table.getSelectedRow()!=-1)
				  				 		{
				  				 			
				  				 			txtcodematiere.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  				 			txtlibelle.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				  				 			txtquantite.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				  				 			btnSupprimer.setEnabled(true);
				  				 			bouttonModifier.setEnabled(true);
				  				 			btnAjouter.setEnabled(false);
				  				 			txtcodematiere.setEditable(false);
				  				 			
				  				 			}
				  				 	}
				  		     		
				  		     		}
				  		     });
				  		     table.setShowVerticalLines(false);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		     table.setRowHeightEnabled(true);
				  		     table.setBackground(new Color(255, 255, 255));
				  		     table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table.setColumnControlVisible(true);
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(36, 309, 782, 344);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(36, 274, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(36, 13, 781, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 22, 144, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(383, 21, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		
				  		  txtCode.setBounds(98, 22, 144, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		 
				  		  comboBox.setBounds(466, 22, 206, 24);
				  		  layeredPane.add(comboBox);
				  		  
				  		  btnChercher = new JButton("Chercher");
				  		  btnChercher.setIcon(imgChercher);
				  		  btnChercher.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(!txtCode.getText().equals("") && comboBox.getSelectedIndex()!=-1)
				  		  		
				  		  		{
				  		  			
				  		  		articlesMP =articlesMPDAO.findByCode(txtCode.getText());
					  		  	listDetailEstimationMP=articlesMP.getDetailEstimationMP();
					  		    listDetailEstimationMP.size();
					  		  	afficher_tableMP(listDetailEstimationMP);
					  		  	
				  		  		}
				  		  		else
				  		  		{
				  		  			JOptionPane.showMessageDialog(null, "");
				  		  		}
				  		  	
				  		  	}
				  		  });
				  		  btnChercher.setBounds(231, 76, 114, 24);
				  		  layeredPane.add(btnChercher);
				  		  
				  		  btnIntialiser = new JButton("Intialiser");
				  		  btnIntialiser.setIcon(imgInit);
				  		  btnIntialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser();
				  		  	
				  		  	}
				  		  });
				  		  
				  		  btnIntialiser.setBounds(363, 76, 114, 24);
				  		  layeredPane.add(btnIntialiser);
		
		JButton btnModifier = new JButton("Modifier Estimation");
		btnModifier.setIcon(imgModifier);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		

				if(table.getRowCount()!=0)
				{
					if(listDetailEstimationMP.size()!=0)
					{
						ArticlesMP articlemp =listDetailEstimationMP.get(0).getArticles();
						articlemp.setDetailEstimationMP(listDetailEstimationMP);
					    articlesMPDAO.edit(articlemp);
				
						
					JOptionPane.showMessageDialog(null, "Detail Estimation modifier avec succée");
					initialiserMP();
					///listDetailEstimationPromo.clear();
					afficher_tableMP(listDetailEstimationMP);
					//afficher_tablePromotion(listPromotion);
				
						}
				}
				
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnModifier.setBounds(264, 664, 158, 23);
		add(btnModifier);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(36, 135, 782, 108);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Quantite :");
		label.setBounds(470, 11, 102, 26);
		layeredPane_1.add(label);
		
		JLabel label_1 = new JLabel("Code Matiere :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(10, 11, 82, 26);
		layeredPane_1.add(label_1);
		
		txtlibelle = new JTextField();
		txtlibelle.setEditable(false);
		txtlibelle.setColumns(10);
		txtlibelle.setBounds(303, 11, 157, 26);
		layeredPane_1.add(txtlibelle);
		
		JLabel label_2 = new JLabel("Libelle :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(256, 11, 68, 26);
		layeredPane_1.add(label_2);
		
		txtquantite = new JTextField();
		 util.Utils.copycoller(txtquantite);
		txtquantite.setColumns(10);
		txtquantite.setBounds(531, 11, 157, 26);
		layeredPane_1.add(txtquantite);
		
		 bouttonModifier = new JButton("Modifier");
		 bouttonModifier.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
				try {
					if(txtcodematiere.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entre le code Matiere premiere SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
						return;
						
					}else if(txtlibelle.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entre le Nom de Matiere premiere SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}else if(txtquantite.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entre la Quantité de Matiere premiere SVP !!!!","Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}
				else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO) <=0)
	    		{
	    			JOptionPane.showMessageDialog(null, "la Quantité de matiere premiere doit etre en chiffre et supérieur à 0 SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}else
					{
						if(listDetailEstimationMP.size()!=0)
						{
							if(table.getSelectedRow()!=-1)
							{
							 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la matiere premiere ?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION )
										
									{
										
								DetailEstimationMP detailestimationMP=listDetailEstimationMP.get(table.getSelectedRow());
								detailestimationMP.setQuantite(new BigDecimal(txtquantite.getText()));
								detailestimationmpdao.edit(detailestimationMP);
								listDetailEstimationMP.set(table.getSelectedRow(), detailestimationMP);
								afficher_tableMP(listDetailEstimationMP);
								JOptionPane.showMessageDialog(null, "Matiere Premiere modifier avec succée ");
								 initialiserMP();
								 
							}
									
							}
							
						}else
						{
							JOptionPane.showMessageDialog(null, "liste Matiere premiere est vide !!!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
							
						}
					}
						
				} catch (NumberFormatException r) {  JOptionPane.showMessageDialog(null, "la Quantite de matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
		 	}
		 });
		bouttonModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bouttonModifier.setEnabled(false);
		bouttonModifier.setBounds(115, 59, 107, 24);
		layeredPane_1.add(bouttonModifier);
		
		JButton button_1 = new JButton("Initialiser");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				initialiserMP();
				
				}
		});
		
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(247, 60, 106, 23);
		layeredPane_1.add(button_1);
		
		txtcodematiere = new JTextField();
		 util.Utils.copycoller(txtcodematiere);
		txtcodematiere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==e.VK_ENTER)
	      		{
	      			if(!txtcodematiere.getText().equals(""))
	      			{
	      				MatierePremier matierepremiere=matierePremiereDAO.findByCode(txtcodematiere.getText().toUpperCase());
			    		
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
		 btnSupprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		if(table.getSelectedRow()!=-1)
				{
					if(listDetailEstimationMP.size()!=0)
					{
						 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer la matiere premiere ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
							{
								DetailEstimationMP detailEstimationMP=listDetailEstimationMP.get(table.getSelectedRow());
								
							    detailestimationmpdao.delete(detailEstimationMP.getId());
								listDetailEstimationMP.remove(table.getSelectedRow());
								afficher_tableMP(listDetailEstimationMP);
								JOptionPane.showMessageDialog(null, "Matiere Premiere supprimer avec succée ");
								 initialiserMP();
								
							}
						}
					}
		 		}
		 });
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBounds(383, 59, 107, 24);
		layeredPane_1.add(btnSupprimer);
		
		 btnAjouter = new JButton("Ajouter");
		 btnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
				boolean trouve=false;
try {
	    			if(txtcodematiere.getText().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez entrer le code matiere premiere SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}else if(txtlibelle.getText().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez entrer le code matiere premiere SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		else if(txtquantite.getText().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantité de matiere premiere SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO) <=0)
	    		{
	    			JOptionPane.showMessageDialog(null, "la Quantité de matiere premiere doit etre en chiffre et supérieur à 0 SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		else
	    		{
	    			
	    			MatierePremier matierepremiere=matierePremiereDAO.findByCode(txtcodematiere.getText().toUpperCase());
	    			if(matierepremiere!=null)
	    			{
	    				for(int i=0; i<listDetailEstimationMP.size();i++)
	    				{
	    					if(listDetailEstimationMP.get(i).getMatierePremier().getCode().equals(matierepremiere.getCode()))
	    					{
	    						trouve=true;
	    						JOptionPane.showMessageDialog(null, "Matiere premiere existe deja dans l'estimation !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    						return;
	    					}
	    				}
	    				if(trouve==false)
	    				{
	    				DetailEstimationMP detailestimationMP=new DetailEstimationMP();
	    				detailestimationMP.setMatierePremier(matierepremiere);
	    				detailestimationMP.setQuantite(new BigDecimal(txtquantite.getText()));	
	    				detailestimationMP.setPriorite(1);
	    				detailestimationMP.setArticlesMP(articlesMP);
	    				
	    				detailestimationmpdao.add(detailestimationMP);
	    				listDetailEstimationMP.add(detailestimationMP);
	    				afficher_tableMP(listDetailEstimationMP);
	    				initialiserMP();
	    						
	    				}
	    				
	    				
	    			}else
	    			{
	    				JOptionPane.showMessageDialog(null, "la matiere premiere introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
	    				
	    			}
	    			
	    				}
	    		
	    					
				}  catch (NumberFormatException r) {  JOptionPane.showMessageDialog(null, "la Quantite de matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				}
	    		
		 		
		 		
		 	}
		 });
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAjouter.setBounds(507, 59, 107, 24);
		layeredPane_1.add(btnAjouter);
				  		 
	}
	void initialiserMP()
	{
		    btnSupprimer.setEnabled(false);
 			bouttonModifier.setEnabled(false);
 			btnAjouter.setEnabled(true);
 			txtcodematiere.setText("");
 			txtlibelle.setText("");
 			txtquantite.setText("");
 			txtcodematiere.requestFocus();
 			txtcodematiere.setEditable(true);
	}
	
	void intialiser()
	{
		
	txtCode.setText("");
	comboBox.setSelectedItem("");	
		
	}
	
	void afficher_tableMP(List<DetailEstimationMP> listDetailEstimationMP)
	{

		 DecimalFormat format = new DecimalFormat("#0.000000");

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP", "Quantité Estimé"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleMP); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
	  		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		  // table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listDetailEstimationMP.size())
			{	
				
				DetailEstimationMP detailEstimationMP=listDetailEstimationMP.get(i);
				
				Object []ligne={detailEstimationMP.getMatierePremier().getCode(),detailEstimationMP.getMatierePremier().getNom(),format.format(detailEstimationMP.getQuantite())};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	




}
