package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Depot;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import javax.swing.JCheckBox;


public class AjoutMatierePremiere extends JLayeredPane {
	public JLayeredPane contentPane;	
	private DefaultTableModel modele;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private JTextField code;
	private JTextField nom;
	
	private Map< String, Integer> catMap = new HashMap<>();
	List<MatierePremier> listeMatierePremiere= new ArrayList<MatierePremier>();
	
	private  MatierePremiereDAO dao;
	private  CategorieMpDAO daoCategorie;
	private StockMPDAO stockMPDAO;
	private DepotDAO depotDAO;
	private SousFamilleArticlesPFDAO sousFamilleDAO;
	private FamilleArticlesPFDAO familleArticleDAO;
	private JComboBox categorie;
	private JLayeredPane layeredPane_1;
	private JComboBox comboCatModif;
	private JTextField txtCodeModif;
	private JTextField txtNomModif;
	private JLabel lblCatModif;
	private JLabel lblNomModif;
	private JLabel lblCodeModif;
	private JButton btnValiderModif;
	private JButton initialiserModif;
	
	private int id_mp;
	private JTextField txtAutreCodeMP;
	JCheckBox checkAutreCodeMP = new JCheckBox("Autre Code");
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutMatierePremiere() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 681);
        try{
        	
        	dao=new MatierePremierDAOImpl();
        	daoCategorie=new CategorieMpDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	sousFamilleDAO=new SousFamilleArticlesPFDAOImpl();
        	familleArticleDAO=new FamilleArticlesPFDAOImpl();
        	

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
        String Codedepot = AuthentificationView.utilisateur.getCodeDepot(); 	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}

        comboCatModif = new JComboBox();
 		  categorie = new JComboBox();
 		 categorie.setBounds(166, 75, 218, 26);
    	categorie.addItem(""); 
    	List<CategorieMp> listeCategorie =daoCategorie.findAll();
		
			
			for(int i=0;i<listeCategorie.size();i++)
			{
				CategorieMp categorieObject =listeCategorie.get(i);
				catMap.put(categorieObject.getNom(), categorieObject.getId());
				categorie.addItem(categorieObject.getNom());
				
			}
		

		table = new JXTable();
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
		intialiserTableau();
 
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(0, 235, 913, 403);
		
		this.add(scrollPane);
				  		 
				  		 JXLabel lblListDes = new JXLabel();
				  		 lblListDes.setForeground(new Color(255, 69, 0));
				  		 lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		 lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		 lblListDes.setText("List des Mati\u00E8res Premi\u00E8res");
				  		 lblListDes.setBounds(0, 207, 782, 24);
				  		 add(lblListDes);
				  		layeredPane_1 = new JLayeredPane();
				  		  btnModifier = new JButton("  Modifier");
				  		  btnModifier.setVisible(false);
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  
								int row=0;
								   if(table.getSelectedRow()==-1)
									     JOptionPane.showMessageDialog(null, "Il faut sÃ©lectionner un article!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								   else
								   {  
									   layeredPane_1.setVisible(true);
									   row = table.getSelectedRow();
									   id_mp=Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
									   txtCodeModif.setText(table.getModel().getValueAt(row, 1).toString());
									   txtNomModif.setText(table.getModel().getValueAt(row, 2).toString());
									   comboCatModif.setSelectedItem(table.getModel().getValueAt(row,3).toString());
				                 
								   }
							
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(923, 270, 112, 24);
				  		 add(btnModifier);
				  		 
				  		  btnSupprimer = new JButton("D\u00E9sactiver");
				  		  btnSupprimer.setVisible(false);
				  		  btnSupprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

								
								 try{
									   int row=0;
									   if(table.getSelectedRow()==-1)
										     JOptionPane.showMessageDialog(null, "Il faut sélectionner une Matière Première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
									   else
									   {
										   
										   int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment désactiver cette Matière Première?", 
												"Satisfaction", JOptionPane.YES_NO_OPTION);
										 
										if(reponse == JOptionPane.YES_OPTION ){
										   
										row = table.getSelectedRow();
									  
									   //stx=con.createStatement();
										int  id=Integer.parseInt(table.getValueAt(row, 0).toString());
									     MatierePremier mp = dao.findById(id);
									     mp.setDeleted(true);
									   	 dao.edit(mp);
									   	 
									   	 listeMatierePremiere = new ArrayList<MatierePremier>();
									  		
									   	listeMatierePremiere=dao.findAll();
									     afficher_table(listeMatierePremiere);  
				                      
											}
									   }
						                }catch (Exception e1){
						                	}
										
							
				  		  	}
				  		  });
				  		btnSupprimer.setIcon(imgSupprimer);
				  		 btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnSupprimer.setBounds(923, 305, 112, 23);
				  		 add(btnSupprimer);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		 layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		 layeredPane.setBounds(10, 11, 630, 192);
				  		 add(layeredPane);
				  		
				  		 layeredPane.add(categorie);
				  		 
				  		 code = new JTextField();
				  		 code.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		 code.setForeground(Color.BLUE);
				  		 code.setBackground(Color.WHITE);
				  		 code.setEditable(false);
				  		 code.setColumns(10);
				  		 code.setBounds(165, 13, 219, 26);
				  		 layeredPane.add(code);
				  		 
				  		 nom = new JTextField();
				  		 util.Utils.copycoller(nom);
				  		 nom.setColumns(10);
				  		 nom.setBounds(165, 44, 219, 26);
				  		 layeredPane.add(nom);
				  		 
				  		 JLabel label = new JLabel("Catégorie:");
				  		 label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 label.setBounds(54, 73, 102, 26);
				  		 layeredPane.add(label);
				  		 
				  		 JLabel label_1 = new JLabel("NOM:");
				  		 label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 label_1.setBounds(54, 42, 130, 26);
				  		 layeredPane.add(label_1);
				  		 
				  		 JLabel label_2 = new JLabel("CODE:");
				  		 label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 label_2.setBounds(54, 13, 114, 26);
				  		 layeredPane.add(label_2);
				  		 
				  		  
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.setBounds(120, 142, 107, 24);
				  		  layeredPane.add(btnAjouter);
				  		  btnAjouter.setIcon(imgAjouter);
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {

				  		   		
				  		   		if(checkAutreCodeMP.isSelected()==true)
				  		   		{
				  		   			
				  		   			if(txtAutreCodeMP.getText().equals(""))
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le Code de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   			return;
				  		   				
				  		   			}
				  		   			else if(nom.getText().equals(""))
				  		   			{
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le nom de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   			return;
				  		   			}
					  		   			
					  		   		else if (categorie.getSelectedItem().equals(""))
					  		   		{
					  		   		JOptionPane.showMessageDialog(null, "Il faut saisir la catégorie de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   			return;
					  		   		}
					  		   			
					  		   		else {
					  		   			
					  		   			MatierePremier matierePremier=dao.findByCode(txtAutreCodeMP.getText().trim());
					  		   			if(matierePremier!=null)
					  		   			{
					  		   				JOptionPane.showMessageDialog(null, "Code MP Déja Existant , Veuillez Entrer Un Autre Code SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		   				return;
					  		   				
					  		   			}else
					  		   			{
					  		   				
					  		   			int idCat=catMap.get(categorie.getSelectedItem());

										MatierePremier	p= new MatierePremier();
										p.setCode(txtAutreCodeMP.getText().trim());
										p.setNom(nom.getText());
										CategorieMp  CategorieMp =daoCategorie.findById(idCat);
										
										p.setCategorieMp(CategorieMp);
										
										dao.add(p);
										 
										// listeMatierePremiere = new ArrayList<MatierePremier>();
										 //listeMatierePremiere=dao.findAll();
										
										// ajouter sous famille de MP Comme PF //
										
										FamilleArticlePF familleDechet=familleArticleDAO.findByLibelle(Constantes.FAMILLE_DECHET);
										FamilleArticlePF familleCadeau=familleArticleDAO.findByLibelle(Constantes.FAMILLE_CADEAU);
										//sous famille dechet
									SousFamilleArticlePF sousFamilleArticlePFDechet=new SousFamilleArticlePF();
									sousFamilleArticlePFDechet.setCode(txtAutreCodeMP.getText().trim());
									sousFamilleArticlePFDechet.setLiblle(nom.getText());
									sousFamilleArticlePFDechet.setFamileArticlePF(familleDechet);
									sousFamilleDAO.add(sousFamilleArticlePFDechet);
									//sous famille offre
									SousFamilleArticlePF sousFamilleArticlePFOffre=new SousFamilleArticlePF();
									sousFamilleArticlePFOffre.setCode(txtAutreCodeMP.getText().trim()+"_O");
									sousFamilleArticlePFOffre.setLiblle(nom.getText());
									sousFamilleArticlePFOffre.setFamileArticlePF(familleCadeau);
									sousFamilleDAO.add(sousFamilleArticlePFOffre);
										
										///////////////////////////////////////////
										 
										 listeMatierePremiere.add(p);
										 intialiser();
										 
										     afficher_table(listeMatierePremiere);  
					  		   				
					  		   			}
					  		   			
					  		   			
					  		   
					  		   		}
				  		   			
				  		   		}else
				  		   		{
				  		   			
				  			   		if(nom.getText().equals(""))
					  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le nom de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   		else if (categorie.getSelectedItem().equals(""))
					  		   			JOptionPane.showMessageDialog(null, "Il faut saisir la catégorie de la matière première!", "Attention", JOptionPane.INFORMATION_MESSAGE);
					  		   		else {
					  		   		int idCat=catMap.get(categorie.getSelectedItem());

									MatierePremier	p= new MatierePremier();
									p.setCode(code.getText());
									p.setNom(nom.getText());
									CategorieMp  CategorieMp =daoCategorie.findById(idCat);
									
									p.setCategorieMp(CategorieMp);
									
									dao.add(p);
									Utils.incrementerValeurSequenceur(Constantes.MATIERE_PREMIERE_LIBELLE);
									// listeMatierePremiere = new ArrayList<MatierePremier>();
									 //listeMatierePremiere=dao.findAll();
									
									// ajouter sous famille de MP Comme PF //
									
									FamilleArticlePF familleDechet=familleArticleDAO.findByLibelle(Constantes.FAMILLE_DECHET);
									FamilleArticlePF familleCadeau=familleArticleDAO.findByLibelle(Constantes.FAMILLE_CADEAU);
									//sous famille dechet
								SousFamilleArticlePF sousFamilleArticlePFDechet=new SousFamilleArticlePF();
								sousFamilleArticlePFDechet.setCode(code.getText());
								sousFamilleArticlePFDechet.setLiblle(nom.getText());
								sousFamilleArticlePFDechet.setFamileArticlePF(familleDechet);
								sousFamilleDAO.add(sousFamilleArticlePFDechet);
								//sous famille offre
								SousFamilleArticlePF sousFamilleArticlePFOffre=new SousFamilleArticlePF();
								sousFamilleArticlePFOffre.setCode(code.getText()+"_O");
								sousFamilleArticlePFOffre.setLiblle(nom.getText());
								sousFamilleArticlePFOffre.setFamileArticlePF(familleCadeau);
								sousFamilleDAO.add(sousFamilleArticlePFOffre);
									
									///////////////////////////////////////////
									 
									 listeMatierePremiere.add(p);
									 intialiser();
									   	String codegenere =Utils.genererCode(Constantes.MATIERE_PREMIERE_LIBELLE);
							  			code.setText(codegenere);
									     afficher_table(listeMatierePremiere);  
					  		   		}
				  		   		}
				  		   		
				  		   		
				  		   		
				  	
				  		   	}
				  		   });
				  		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		   btnInitialiser = new JButton("Initialiser");
				  		   btnInitialiser.setBounds(270, 143, 102, 23);
				  		   layeredPane.add(btnInitialiser);
				  		   btnInitialiser.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	intialiser();
				  		   		
				  		   	}
				  		   });
				  		   btnInitialiser.setIcon(imgInit);
				  		   btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
				  		   JButton btnGenererStock = new JButton("G\u00E9n\u00E9rer Stock");
				  		   btnGenererStock.addActionListener(new ActionListener() { 
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	List<MatierePremier> listMP =new ArrayList<MatierePremier>();
				  		   	List<Magasin> listMagasin= new ArrayList<Magasin>();
				  		  listMP =dao.findAll();
				  		if(Codedepot.equals(Constantes.CODE_DEPOT_SIEGE)){
				  			listMagasin=depotDAO.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_MP);
			  		   	} else {
			  		   		Depot depot=depotDAO.findByCode(Codedepot);
			  		   		listMagasin=depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
			  		   	}
				  		  
				  		  
				  		  
				  		   		Utils.genererStockByMagasinMP(listMP,listMagasin);
				  		   	JOptionPane.showMessageDialog(null, "Le stock est généré avec succès", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   	}
				  		   });
				  		   btnGenererStock.setBounds(400, 143, 102, 23);
				  		   layeredPane.add(btnGenererStock);
				  		   
				  		   
				  		   layeredPane_1.setBackground(new Color(135, 206, 235));
				  		   layeredPane_1.setVisible(false);
				  		   layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(135, 206, 250), new Color(135, 206, 250)));
				  		   layeredPane_1.setBounds(654, 11, 495, 192);
				  		   add(layeredPane_1);
				  		   
				  		
				  		   comboCatModif.setBounds(166, 75, 191, 26);
				  		   layeredPane_1.add(comboCatModif);
				  		   
				  		   txtCodeModif = new JTextField();
				  		 util.Utils.copycoller(txtCodeModif);
				  		   txtCodeModif.setColumns(10);
				  		   txtCodeModif.setBounds(165, 13, 191, 26);
				  		   layeredPane_1.add(txtCodeModif);
				  		   
				  		   txtNomModif = new JTextField();
				  		 util.Utils.copycoller(txtNomModif);
				  		   txtNomModif.setColumns(10);
				  		   txtNomModif.setBounds(165, 44, 191, 26);
				  		   layeredPane_1.add(txtNomModif);
				  		   
				  		   lblCatModif = new JLabel("Cat\u00E9gorie:");
				  		   lblCatModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCatModif.setBounds(54, 73, 102, 26);
				  		   layeredPane_1.add(lblCatModif);
				  		   
				  		   lblNomModif = new JLabel("NOM:");
				  		   lblNomModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblNomModif.setBounds(54, 42, 130, 26);
				  		   layeredPane_1.add(lblNomModif);
				  		   
				  		   lblCodeModif = new JLabel("CODE:");
				  		   lblCodeModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCodeModif.setBounds(54, 13, 114, 26);
				  		   layeredPane_1.add(lblCodeModif);
				  		   
				  		   btnValiderModif = new JButton("Valider Modification");
				  		   btnValiderModif.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   		
				  		   	
				  		int idCat=catMap.get(comboCatModif.getSelectedItem());
				  		StockMP stockMP = new StockMP();
				  		 MatierePremier mp = dao.findById(id_mp);
				  		stockMP.setMatierePremier(mp);
				  		mp.setNom(txtNomModif.getText());
						CategorieMp  CategorieMp =daoCategorie.findById(idCat);
						mp.setCategorieMp(CategorieMp);
						 Depot depot=depotDAO.findById(1);
				  		 Magasin magasin=depot.getListMagasin().get(0);
						//stockMP.setMatierePremier(mp);
						stockMP.setQuantiteCommande(BigDecimal.ZERO);
						stockMP.setStock(new BigDecimal(4000) );    
						stockMP.setStockMin(BigDecimal.ZERO);
						stockMP.setMagasin(magasin);
						stockMPDAO.add(stockMP);
						   	 dao.edit(mp);
						   	JOptionPane.showMessageDialog(null, "la Matière Première a été modifiée avec succès!", "Attention", JOptionPane.INFORMATION_MESSAGE);
						   	layeredPane_1.setVisible(false);
						    listeMatierePremiere = new ArrayList<MatierePremier>();
					  		
						   	listeMatierePremiere=dao.findAll();
						     afficher_table(listeMatierePremiere);   
						     
				  		   		
				  		   	}
				  		   });
				  		   btnValiderModif.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnValiderModif.setBounds(141, 142, 130, 24);
				  		   layeredPane_1.add(btnValiderModif);
				  		   
				  		   initialiserModif = new JButton("Initialiser");
				  		  
				  		   initialiserModif.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   initialiserModif.setBounds(292, 143, 102, 23);
				  		   layeredPane_1.add(initialiserModif);
				  			String codegenere =Utils.genererCode(Constantes.MATIERE_PREMIERE_LIBELLE);
				  			code.setText(codegenere);
				  			
				  			  checkAutreCodeMP = new JCheckBox("Autre Code");
				  			checkAutreCodeMP.addActionListener(new ActionListener() {
				  				public void actionPerformed(ActionEvent arg0) {
				  					
				  					if(checkAutreCodeMP.isSelected()==true)
				  					{
				  						
				  						
				  						txtAutreCodeMP.setVisible(true);
				  						code.setVisible(false);
				  					}else
				  					{
				  						
				  						txtAutreCodeMP.setVisible(false);
				  						code.setVisible(true);
				  					}
				  					
				  					
				  				}
				  			});
				  			checkAutreCodeMP.setBounds(405, 15, 97, 23);
				  			layeredPane.add(checkAutreCodeMP);
				  			
				  			txtAutreCodeMP = new JTextField();
				  			txtAutreCodeMP.setColumns(10);
				  			txtAutreCodeMP.setBounds(166, 13, 219, 26);
				  			layeredPane.add(txtAutreCodeMP);
				  		
				  		 
				  		 listeMatierePremiere = new ArrayList<MatierePremier>();
					  		
						   	listeMatierePremiere=dao.findAll();
						     afficher_table(listeMatierePremiere);  
						     
				txtAutreCodeMP.setVisible(false);	     
						     
	}
	
	void afficher_table(List<MatierePremier> listeMatierePremiere)
	{
		intialiserTableau();
		
		
			  int i=0;
				while(i<listeMatierePremiere.size())
				{	
					MatierePremier matierePremiere=listeMatierePremiere.get(i);
					
					String categorie="--";
					String subCategorie="--";
					
					if(matierePremiere.getCategorieMp()!=null )
						categorie=matierePremiere.getCategorieMp().getNom();
					
					if(matierePremiere.getCategorieMp()!=null && matierePremiere.getCategorieMp().getSubCategorieMp()!=null)
						subCategorie=matierePremiere.getCategorieMp().getSubCategorieMp().getNom();
					
					Object []ligne={matierePremiere.getId(),matierePremiere.getCode(),matierePremiere.getNom(),categorie,subCategorie};

					modele.addRow(ligne);
					i++;
				}

				table.setModel(modele); 
	
	}
	
	void intialiserTableau(){
		modele=	new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id","Code","Nom MP", "Catégorie", "Sous Catégorie"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			table.setModel(modele);
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
		    table.getColumnModel().getColumn(1).setPreferredWidth(60);
		    table.getColumnModel().getColumn(2).setPreferredWidth(260);
		    table.getColumnModel().getColumn(3).setPreferredWidth(60);
		    table.getColumnModel().getColumn(4).setPreferredWidth(60);
			
	}
	void intialiser()
	{		
		nom.setText("");
		categorie.setSelectedItem("");
		txtAutreCodeMP.setText("");
		checkAutreCodeMP.setSelected(false);
		txtAutreCodeMP.setVisible(false);
		code.setVisible(true);		
		
	}
	
	public static boolean générerStockByMagasinMP(List<MatierePremier> listMP,List<Magasin> listMagasin){ 
	//	  stockMP = new StockMP();
		for(int i=0;i<listMP.size();i++)
			for(int j=0;j<listMagasin.size();j++){
				StockMP	stockMP = new StockMP();
					
					
			}
		
		
		return true ;
		
	}
}
