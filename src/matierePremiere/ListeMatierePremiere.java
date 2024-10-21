package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import util.Constantes;
import util.JasperUtils;
import dao.daoImplManager.ArticlesMPDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Articles;
import dao.entity.ArticlesMP;
import dao.entity.CategorieMp;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;


public class ListeMatierePremiere extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgAjouter;
	private ImageIcon imgImprimer;
	List<MatierePremier> listeMatierePremiere= new ArrayList<MatierePremier>();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, Integer> subcatMap = new HashMap<>();
	private Map< String, Integer> catMap = new HashMap<>();
	private JLabel lblDpotDestination;
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesMPDAO articlesMPDAO;
	ArticlesMP articlesMP =new ArticlesMP ();
	private JComboBox soucategoriempcombo;
	private JComboBox categoriempcombo;
	private CategorieMp categoriemp;
	private CategorieMpDAO categoriempdao;
	private SubCategorieMp subcategoriemp;
	private SubCategorieMPDAO subcategoriempdao;
	private JButton btnAfficher;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ListeMatierePremiere() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	articlesMPDAO=new ArticlesMPDAOImpl();
        	categoriempdao=new CategorieMpDAOImpl();
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	listsubcategoriemp=subcategoriempdao.findAll();
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
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
				  		     	scrollPane.setBounds(9, 167, 782, 337);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 135, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 781, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblsouscategorie = new JLabel("Sous-Categorie Mp");
				  		     	lblsouscategorie.setBounds(10, 35, 144, 24);
				  		     	layeredPane.add(lblsouscategorie);
				  		     	lblsouscategorie.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Categorie Mp");
				  		  lblDpotDestination.setBounds(339, 34, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  soucategoriempcombo = new JComboBox();
				  		  soucategoriempcombo.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent arg0) {
				  		  		int i=0;
				  		  		if(soucategoriempcombo.getSelectedIndex()!=-1 && !soucategoriempcombo.getSelectedItem().equals(""))
				  		  		{
				  		  			categoriempcombo.removeAllItems();
				  		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()));
				  		  			if(listecategoriemp!=null)
				  		  			{
				  		  				while(i<listecategoriemp.size())
				  		  				{
				  		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i).getId());
				  		  					categoriempcombo.addItem(listecategoriemp.get(i).getNom());
				  		  					i++;
				  		  				}
				  		  				
				  		  			}
				  		  			
				  		  		}else
				  		  		{
				  		  		categoriempcombo.removeAllItems();
				  		  		}
				  		  		
				  		  	}
				  		  });
				  		  soucategoriempcombo.setBounds(117, 35, 184, 24);
				  		  layeredPane.add(soucategoriempcombo);
				  		  
				  		  categoriempcombo = new JComboBox();
				  		  categoriempcombo.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent arg0) {
				  		  		
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		  categoriempcombo.setBounds(438, 35, 184, 24);
				  		  layeredPane.add(categoriempcombo);
				  		  categoriempcombo.removeAllItems();
				  		  categoriempcombo.addItem("");
				  		soucategoriempcombo.removeAllItems();
				  		soucategoriempcombo.addItem("");
				  		
				  		btnAfficher = new JButton("Afficher");
				  		btnAfficher.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent e) {
				  				
				  				if(soucategoriempcombo.getSelectedIndex()!=-1 && !soucategoriempcombo.getSelectedItem().equals("") && !categoriempcombo.getSelectedItem().equals("") && categoriempcombo.getSelectedIndex()!=-1)
				  				{
				  					
				  					listeMatierePremiere=matierePremiereDAO.listeMatierePremierByidcategorie(catMap.get(categoriempcombo.getSelectedItem()));
				  					afficher_tableMP(listeMatierePremiere);
				  				}else
				  				{
				  					listeMatierePremiere=matierePremiereDAO.findAll();
				  					afficher_tableMP(listeMatierePremiere);
				  				}
				  				
				  				
				  				
				  			}
				  		});
				  		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		btnAfficher.setBounds(651, 35, 97, 24);
				  		layeredPane.add(btnAfficher);
				  		  int i=0;
				  		  while(i<listsubcategoriemp.size())
				  		  {
				  			  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i).getId());
				  			  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
				  			  i++;
				  		  }
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setIcon(imgImprimer);
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listeMatierePremiere.size()!=0)
				{
					 
					
					Map parameters = new HashMap();
				
					JasperUtils.imprimerListeMatierePremiere(listeMatierePremiere,parameters);
					
					
				}
				
			}
		});
		btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImprimer.setBounds(310, 513, 158, 23);
		add(btnImprimer);
		List<MatierePremier> listMatierePremier=matierePremiereDAO.findAll();
		afficher_tableMP(listMatierePremier);
	}
	
	void afficher_tableMP(List<MatierePremier> listMatierePremier)
	{

		intialiserTableau();
		  int i=0;
			while(i<listeMatierePremiere.size())
			{	
				MatierePremier matierePremiere=listeMatierePremiere.get(i);
				
				Object []ligne={matierePremiere.getId(),matierePremiere.getCode(),matierePremiere.getNom(),matierePremiere.getCategorieMp().getNom(),matierePremiere.getCategorieMp().getSubCategorieMp().getNom()};

				modeleMP.addRow(ligne);
				i++;
			}

			table.setModel(modeleMP); 

		

	        
		 
	}
	
	void intialiserTableau(){
		modeleMP=	new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id","Code","Nom MP", "CatÈgorie", "Sous CatÈgorie"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			table.setModel(modeleMP);
			table.getColumnModel().getColumn(0).setPreferredWidth(10);
		    table.getColumnModel().getColumn(1).setPreferredWidth(60);
		    table.getColumnModel().getColumn(2).setPreferredWidth(260);
		    table.getColumnModel().getColumn(3).setPreferredWidth(60);
		    table.getColumnModel().getColumn(4).setPreferredWidth(60);
			
	}
}


