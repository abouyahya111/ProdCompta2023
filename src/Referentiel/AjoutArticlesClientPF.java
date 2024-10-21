package Referentiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.jdesktop.swingx.JXTitledSeparator;

import com.sun.security.sasl.ClientFactoryImpl;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.ArticlesClientDAOImpl;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailArticlesClientDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoManager.ArticlesClientDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailArticlesClientDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockPFDAO;
import dao.entity.Articles;
import dao.entity.ArticlesClient;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.DetailArticlesClient;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockPF;
import dao.entity.Utilisateur;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjoutArticlesClientPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private DefaultTableModel	 modele;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	private  ClientPFDAO clientPFDAO;
	private DepotDAO depotDAO;
	private CompteClientDAO compteClientDAO;
	private JComboBox comboArticle = new JComboBox();

	
	private Map< String, Depot> mapDepot = new HashMap<>();
	private Map< String, ClientPF> mapClientPF = new HashMap<>();
	private Map< String, ClientPF> mapCodeClientPF = new HashMap<>();
	private Map< String, Magasin> mapMagasin = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticlePF = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<ClientPF> listclientPF =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private FamilleArticlesPFDAO familleArticleDAO;
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	
	private List<DetailArticlesClient> listDetailArticleClient =new ArrayList<DetailArticlesClient>();
	private JTextField txtCodeArticle;
	private JLabel lblCodeClient;
	private Utilisateur utilisateur;
	private JTable table;
	private JTextField txtcodeclient;
	 JComboBox comboDepot = new JComboBox();
	 JComboBox comboMagasin = new JComboBox();
	 JComboBox comboClientPF = new JComboBox();
	 ArticlesDAO articlesDAO;
	 ArticlesClientDAO articlesClientDAO;
	 ArticlesClient articlesClient=new ArticlesClient();
	 JButton btnModifier = new JButton();
	 JButton btnSupprimer = new JButton();
	 JButton buttonvalider = new JButton("Valider");
	 private ImageIcon imgModifierr;
		private ImageIcon imgSupprimer;
	
		private ImageIcon imgValider;
		DetailArticlesClientDAO detailArticlesClientDAO;
		JComboBox combofamille = new JComboBox();
		JComboBox combosousfamille = new JComboBox();
		private StockPFDAO stockpfDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutArticlesClientPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 689);
        try{
        	compteClientDAO=new CompteClientDAOImpl();
        	clientPFDAO=new ClientPFDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	 utilisateur=AuthentificationView.utilisateur;
        	 articlesDAO=new ArticlesDAOImpl();
        	 articlesClientDAO=new ArticlesClientDAOImpl();
        	 detailArticlesClientDAO=new DetailArticlesClientDAOImpl();
        	
        	 familleArticleDAO=new FamilleArticlesPFDAOImpl();
          	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
          	listFamilleArticle=familleArticleDAO.findAll();
          	stockpfDAO=new StockPFDAOImpl();
        	 
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
       	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        /*
        listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepot.put(depot.getLibelle(), depot.getCode());
	      			comboDepot.addItem(depot.getLibelle());
	      			i++;
	      		}
	      		*/
        
   
	      	
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
        
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

				  		  	Articles articles=mapArticle.get(comboArticle.getSelectedItem());
				  		  	FamilleArticlePF familleArticlePF=mapfamille.get(combofamille.getSelectedItem());
				  		  	SousFamilleArticlePF sousFamilleArticlePF=mapsousfamille.get(combosousfamille.getSelectedItem());
				  		  	
				  		  	if(familleArticlePF==null)
				  		  	{
				  		  		

				  		  		
				  		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner Famille d'Article SVP");
				  		  		return;
				  		  		
				  		  	
				  		  	}
				  		  	
				  		 	if(sousFamilleArticlePF==null)
				  		  	{
				  		  		

				  		  		
				  		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner Sous Famille d'Article SVP");
				  		  		return;
				  		  		
				  		  	
				  		  	}
				  		  	
				  		  	
				  		  	if(articles!=null)
				  		  	{
				  		  		
				  		  	boolean existe=false;
				  		  	
				  		  	for(int i=0;i<listDetailArticleClient.size();i++)
				  		  	{
				  		  		
				  		  		DetailArticlesClient detailArticlesClient=listDetailArticleClient.get(i);
				  		  		
				  		  		if(detailArticlesClient.getArticle().getId()==articles.getId())
				  		  		{
				  		  			
				  		  		if(detailArticlesClient.getSousFamille().getId()==sousFamilleArticlePF.getId())
			  		  			{
			  		  			existe=true;
			  		  			}	
				  		  			
				  		  		}
				  		  		
				  		  		
				  		  	}
				  		  		
				  		  		if(existe==false)
				  		  		{
				  		  			
				  		  			DetailArticlesClient detailArticlesClient=new DetailArticlesClient();
				  		  		detailArticlesClient.setArticle(articles);
				  		  	detailArticlesClient.setArticlesclient(articlesClient);
				  		  detailArticlesClient.setSousFamille(sousFamilleArticlePF);
				  		  listDetailArticleClient.add(detailArticlesClient);
				  		  afficher_DetailArticlesClient(listDetailArticleClient);
				  		  intialiser();
				  		  
				  		  			
				  		  		}else
				  		  		{
				  		  		JOptionPane.showMessageDialog(null, "l'Article existe déja , Veuillez selectionner un autre article SVP");
				  		  		return;
				  		  		}
				  		  		
				  		  		
				  		  		
				  		  	}else
				  		  	{
				  		  		
				  		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'Article SVP");
				  		  		return;
				  		  		
				  		  	}
				  		  	
				  		  	
				  		  	}
				  		  });
				  		btnAjouter.setIcon(imgAjouter);
				  		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnAjouter.setBounds(260, 250, 114, 26);
				  		 add(btnAjouter);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(384, 250, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(10, 130, 1137, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Ajout Articles");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(10, 165, 1137, 74);
				  		   add(layeredPane);
				  		   			comboArticle.addItemListener(new ItemListener() {
				  		   				public void itemStateChanged(ItemEvent e) {
				  		   					

							  		  	  	 if(e.getStateChange() == ItemEvent.SELECTED)
						  		  	 		 {
							  		  	  		 
							  		  	  		 if(!comboArticle.getSelectedItem().equals(""))
							  		  	  		 {
							  		  	  			 
							  		  	  			 Articles articles=mapArticle.get(comboArticle.getSelectedItem());
							  		  	  			 if(articles!=null)
							  		  	  			 {
							  		  	  				 txtCodeArticle.setText(articles.getCodeArticle());
							  		  	  			 }else
							  		  	  			 {
							  		  	  			txtCodeArticle.setText("");
							  		  	  			 }
							  		  	  			 
							  		  	  			 
							  		  	  			 
							  		  	  			 
							  		  	  			 
							  		  	  		 }
							  		  	  		 
							  		  	  		 
							  		  	  		 
							  		  	  		 
						  		  	 		 }
							  		  	  		
							  		  	  		
							  		  	  		
							  		  	  	
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			
				  		   			
				  		   			comboArticle.setBounds(788, 11, 339, 26);
				  		   			layeredPane.add(comboArticle);
				  		   			
				  		   			JLabel lblDpot = new JLabel("Article:");
				  		   			lblDpot.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblDpot.setBounds(735, 11, 59, 26);
				  		   			layeredPane.add(lblDpot);
				  		   			
				  		   			txtCodeArticle = new JTextField();
				  		   			txtCodeArticle.addKeyListener(new KeyAdapter() {
				  		   				@Override
				  		   				public void keyPressed(KeyEvent e) {
				  		   					

						  		  	 		
							  		  		if(e.getKeyCode()==e.VK_ENTER)
					  			      		{
							  		  			
							  		  			if(!txtCodeArticle.getText().equals(""))
							  		  			{
							  		  				
							  		  				Articles articles=mapCodeArticlePF.get(txtCodeArticle.getText());
							  		  				if(articles!=null)
							  		  				{
							  		  					comboArticle.setSelectedItem(articles.getLiblle());
							  		  					
							  		  					
							  		  				}else
							  		  				{
							  		  				comboArticle.setSelectedItem("");	
							  		  				}
							  		  				
							  		  				
							  		  				
							  		  				
							  		  			}else
							  		  			{
							  		  			comboArticle.setSelectedItem("");	
							  		  			}
							  		  			
					  			      		}
							  		  	 		
							  		  	 		
							  		  	 	
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   		util.Utils.copycoller(txtCodeArticle);
				  		   			txtCodeArticle.setCaretColor(Color.RED);
				  		   			txtCodeArticle.setBackground(Color.WHITE);
				  		   			txtCodeArticle.setColumns(10);
				  		   			txtCodeArticle.setBounds(620, 11, 105, 26);
				  		   			layeredPane.add(txtCodeArticle);
				  		   			
				  		   			lblCodeClient = new JLabel("Code Article:");
				  		   			lblCodeClient.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		   			lblCodeClient.setBounds(522, 11, 88, 26);
				  		   			layeredPane.add(lblCodeClient);
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(29, 287, 889, 326);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		   					
				  		   				if(table.getSelectedRow()!=-1)
				  		         		{
				  		   					DetailArticlesClient detailArticlesClient=listDetailArticleClient.get(table.getSelectedRow());
				  		   					if(detailArticlesClient!=null)
				  		   					{
				  		   					btnModifier.setEnabled(true);
				  		   					btnSupprimer.setEnabled(true);
				  		   					
				  		   					combofamille.setSelectedItem(detailArticlesClient.getSousFamille().getFamileArticlePF().getLiblle());
				  		   					combosousfamille.setSelectedItem(detailArticlesClient.getSousFamille().getLiblle());
				  		   					txtCodeArticle.setText(detailArticlesClient.getArticle().getCodeArticle());
				  		   					comboArticle.setSelectedItem(detailArticlesClient.getArticle().getLiblle());
				  		   					}
				  		   				
				  		   					
				  		         		}
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Famille", "Sous Famille","Code Article", "Article"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   			scrollPane.setViewportView(table);
				  		   			
				  		   			
				  		   			
				  		   		 JXTitledSeparator titledSeparator1 = new JXTitledSeparator();
				  		  	 GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator1.getLayout();
				  		  	 gridBagLayout.rowWeights = new double[]{0.0};
				  		  	 gridBagLayout.rowHeights = new int[]{0};
				  		  	 gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		  	 gridBagLayout.columnWidths = new int[]{0, 0, 0};
				  		  	titledSeparator1.setTitle("Information Client");
				  		  titledSeparator1.setBounds(10, 10, 686, 24);
				  		  	 add(titledSeparator1);

				  		  	 JLayeredPane layeredPane1 = new JLayeredPane();
				  		  	layeredPane1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		  layeredPane1.setBounds(10, 45, 1137, 74);
				  		  	 add(layeredPane1);
				  		  	 
				  		  	  comboClientPF = new JComboBox();
				  		  	  comboClientPF.addItemListener(new ItemListener() {
				  		  	  	public void itemStateChanged(ItemEvent e) {
				  		  	  	 if(e.getStateChange() == ItemEvent.SELECTED)
			  		  	 		 {
				  		  	  		 
				  		  	  		 if(!comboClientPF.getSelectedItem().equals(""))
				  		  	  		 {
				  		  	  			 
				  		  	  			 ClientPF clientPF=mapClientPF.get(comboClientPF.getSelectedItem());
				  		  	  			 if(clientPF!=null)
				  		  	  			 {
				  		  	  				 txtcodeclient.setText(clientPF.getCode());
				  		  	  			 }else
				  		  	  			 {
				  		  	  			 txtcodeclient.setText("");
				  		  	  			 }
				  		  	  			 
				  		  	  			 
				  		  	  			 
				  		  	  			 
				  		  	  			 
				  		  	  		 }
				  		  	  		 
				  		  	  		 
				  		  	  		 
				  		  	  		 
			  		  	 		 }
				  		  	  		
				  		  	  		
				  		  	  		
				  		  	  	}
				  		  	  });
				  		  	 comboClientPF.setBounds(896, 25, 231, 26);
				  		  	layeredPane1.add(comboClientPF);
				  		  	 
				  		  	 JLabel lblClient = new JLabel("Client :");
				  		  	 lblClient.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		  	 lblClient.setBounds(845, 25, 59, 26);
				  		  	layeredPane1.add(lblClient);
				  		  	 
				  		  	 txtcodeclient = new JTextField();
				  		  	 txtcodeclient.addKeyListener(new KeyAdapter() {
				  		  	 	@Override
				  		  	 	public void keyPressed(KeyEvent e) {
				  		  	 		
				  		  		if(e.getKeyCode()==e.VK_ENTER)
		  			      		{
				  		  			
				  		  			if(!txtcodeclient.getText().equals(""))
				  		  			{
				  		  				
				  		  				ClientPF clientPF=mapCodeClientPF.get(txtcodeclient.getText());
				  		  				if(clientPF!=null)
				  		  				{
				  		  					comboClientPF.setSelectedItem(clientPF.getNom());
				  		  					
				  		  					
				  		  				}else
				  		  				{
				  		  				comboClientPF.setSelectedItem("");	
				  		  				}
				  		  				
				  		  				
				  		  				
				  		  				
				  		  			}else
				  		  			{
				  		  			comboClientPF.setSelectedItem("");	
				  		  			}
				  		  			
		  			      		}
				  		  	 		
				  		  	 		
				  		  	 	}
				  		  	 });
				  		  	 txtcodeclient.setColumns(10);
				  		  	 txtcodeclient.setCaretColor(Color.RED);
				  		  	 txtcodeclient.setBackground(Color.WHITE);
				  		  	 txtcodeclient.setBounds(662, 25, 153, 26);
				  		  	layeredPane1.add(txtcodeclient);
				  		  	 
				  		  	 JLabel lblCodeClient_1 = new JLabel("Code Client:");
				  		  	 lblCodeClient_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		  	 lblCodeClient_1.setBounds(575, 25, 88, 26);
				  		  	layeredPane1.add(lblCodeClient_1);
				  		  	 
				  		  	 JLabel lblDepot = new JLabel("Depot :");
				  		  	 lblDepot.setBounds(20, 25, 59, 26);
				  		  	layeredPane1.add(lblDepot);
				  		  	 lblDepot.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		  	 
				  		  	  comboDepot = new JComboBox();
				  		  	  comboDepot.addItemListener(new ItemListener() {
				  		  	  	public void itemStateChanged(ItemEvent e) {
				  		  	  		

				  		  	  		

				  		  	 			
				  		  	 		 if(e.getStateChange() == ItemEvent.SELECTED)
				  		  	 		 {
				  		  	 			int i=0;
				  		  	 		
				  		  	 				if(!comboDepot.getSelectedItem().equals(""))
				  		  			{
				  		  				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				  		  				if(depot!=null)
				  		  				{
				  		  					listMagasin=depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_PF);
				  		  	     				if(listMagasin.size()!=0)
				  		  	     				{
				  		  	     					comboMagasin.removeAllItems();
				  		  	     					comboMagasin.addItem("");
				  		  	     					while(i<listMagasin.size())
				  		  		     				{
				  		  		     					Magasin magasin=listMagasin.get(i);
				  		  		     					comboMagasin.addItem(magasin.getLibelle());
				  		  		     					mapMagasin.put(magasin.getLibelle(), magasin);
				  		  		     					i++;
				  		  		     				}
				  		  	     				}else
				  		  	     				{
				  		  	     					comboMagasin.removeAllItems();
				  		  	     					
				  		  	     				}
				  		  	     				
				  		  	     				
				  		  	     				
				  		  	     				mapClientPF.clear();
				  		  	     				mapCodeClientPF.clear();
				  		  	     				listclientPF=clientPFDAO.findListClientByCodeDepot(depot.getCode());
				  		  	     				
				  		  	     				if(listclientPF.size()!=0)
				  		  	     				{
				  		  	     					comboClientPF.removeAllItems();
				  		  	     					comboClientPF.addItem("");
				  		  	     					for(int j=0;j<listclientPF.size();j++)
				  		  	     					{
				  		  	     						ClientPF clientpf=listclientPF.get(j);
				  		  	     						
				  		  	     						comboClientPF.addItem(clientpf.getNom());
				  		  	     						mapClientPF.put(clientpf.getNom(), clientpf);
				  		  	     						mapCodeClientPF.put(clientpf.getCode(), clientpf);
				  		  	     						
				  		  	     					}
				  		  	     				}else
				  		  	     				{
				  		  	     					comboClientPF.removeAllItems();
				  		  	     				}
				  		  	     				
				  		  	     				
				  		  	     			}else
				  		  	     			{
				  		  	     				comboMagasin.removeAllItems();
				  		  	     				
				  		  	     			}
				  		  	     				
				  		  					
				  		  				}
				  		  				
				  		  				
				  		  	 		 }
				  		  	 	

				  		  	  		
				  		  	  		
				  		  	  		
				  		  	  		
				  		  	  		
				  		  	  	}
				  		  	  });
				  		  	 comboDepot.setBounds(71, 25, 210, 26);
				  		  	layeredPane1.add(comboDepot);
				  		  	 
				  		  	 JLabel lblMagasin = new JLabel("Magasin :");
				  		  	 lblMagasin.setBounds(304, 25, 59, 26);
				  		  	layeredPane1.add(lblMagasin);
				  		  	 lblMagasin.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		  	 
				  		  	  comboMagasin = new JComboBox();
				  		  	 comboMagasin.setBounds(366, 25, 199, 26);
				  		  	layeredPane1.add(comboMagasin);
				  		  	
				  		   			
				  		   			
				  		   			
				  		   			
				  		   			
				  		   		
				  		   			comboArticle.addItem("");
				  		   			
				  		   			JLabel label = new JLabel("Famille Article :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(3, 12, 87, 24);
				  		   			layeredPane.add(label);
				  		   			
				  		   			 combofamille = new JComboBox();
				  		   			 combofamille.addItemListener(new ItemListener() {
				  		   			 	public void itemStateChanged(ItemEvent e) {

		    		                   		
		    		                   		 if(e.getStateChange() == ItemEvent.SELECTED)
		     	 		 {
		    		                   			 if(!combofamille.getSelectedItem().equals(""))
		    		                   			 {
		    		                   				 
		    		                   					FamilleArticlePF famille=mapfamille.get(combofamille.getSelectedItem());
		    		        		if(famille!=null)
		    		        		{
		    		        			 combosousfamille.removeAllItems();
		    		        			 combosousfamille.addItem("");
		    		        			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
		    		        			for(int i=0;i<listSousFamilleArticle.size();i++)
		    		        			{
		    		        			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
		    		        			combosousfamille.addItem(sousfamille.getLiblle());
		    		        			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
		    		        				
		    		        			}
		    		        			
		    		        		}else
		    		        		{
		    		        			 combosousfamille.removeAllItems();
		    		        		}
		    		        			
		    		                   				 
		    		                   			 }else
		    		                   			 {
		    		                   				//combosousfamille.removeAllItems(); 
		    		                   			 }
		    		                   			 
		    		                   			
		    		                   	 
		    		                   			
		    		                   			 
		     	 		 }
		     	 			 
		    		                   		
		    		                   		
		    		                   	
				  		   			 		
				  		   			 		
				  		   			 	}
				  		   			 });
				  		   			combofamille.setSelectedIndex(-1);
				  		   		
	    		                   combofamille.addItem("");
				  		   			combofamille.setBounds(94, 13, 167, 24);
				  		   			layeredPane.add(combofamille);
				  		   			
				  		   			JLabel label_1 = new JLabel("Sous Famille :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(271, 12, 87, 24);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			 combosousfamille = new JComboBox();
				  		   			 combosousfamille.addItemListener(new ItemListener() {
				  		   			 	public void itemStateChanged(ItemEvent e) {
				  		   			 		
						            		 if(e.getStateChange() == ItemEvent.SELECTED)
						     	 		 {
						            			 if(!combosousfamille.getSelectedItem().equals(""))
						            			 {
						            				
						            				 SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
						            				 Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
						            				 if(magasin!=null)
						            				 {
						            					 if(sousfamille!=null)
								        			 {
								        				 comboArticle.removeAllItems();
								        				 comboArticle.addItem("");
								        				 listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
								        				 for(int i=0;i<listArticleStockPF.size();i++)
								        				 {
								        					 
								        					Articles article= listArticleStockPF.get(i).getArticles();
								        					
								        					if(sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
								        					{
								        						
								        						if(!article.getLiblle().contains("(OFFRE)"))
								        						{
								        							
										        					comboArticle.addItem(article.getLiblle());
										        					 mapArticle.put(article.getLiblle(), article);
								        							
								        							
								        						}
								        						
								        						
								        					}else
								        					{

									        					comboArticle.addItem(article.getLiblle());
									        					 mapArticle.put(article.getLiblle(), article);
									        					 	
								        						
								        					}
								        					
								        				 }
								        				 
								        			 }else
								        			 {
								        				 comboArticle.removeAllItems();
								        				 txtCodeArticle.setText("");
								        			 }
							        				   
						            				 }
							        			
						            			 }else
						            			 {
						            				 comboArticle.removeAllItems();
						            				 txtCodeArticle.setText("");
						            			 }
						            			  
						     	 		 }
						            		
						            		
						            		
						            	
				  		   			 		
				  		   			 		
				  		   			 	}
				  		   			 });
				  		   			combosousfamille.setBounds(345, 13, 167, 24);
				  		   			layeredPane.add(combosousfamille);
				  		   			
				  		   			 buttonvalider = new JButton("Valider");
				  		   			 buttonvalider.addActionListener(new ActionListener() {
				  		   			 	public void actionPerformed(ActionEvent e) {
				  		   			 		
				  		   			 		Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				  		   			 		
				  		   			 		Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
				  		   			 		ClientPF clientPF=mapClientPF.get(comboClientPF.getSelectedItem());
				  		   			 		
				  		   			 		if(depot==null)
				  		   			 		{
				  		   			 			
				  		   			 			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le depot SVP");
				  		   			 			return;
				  		   			 			
				  		   			 		}else if(magasin==null)
				  		   			 		{
				  		   			 			
				  		   			 		JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin SVP");
			  		   			 			return;
			  		   			 			
				  		   			 		}else if(clientPF==null)
				  		   			 		{
				  		   			 			
				  		   			 		JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Client PF SVP");
			  		   			 			return;
			  		   			 			
				  		   			 		}else if(listDetailArticleClient.size()==0)
				  		   			 		{
				  		   			 		JOptionPane.showMessageDialog(null, "Veuillez Entrer les articles SVP");
			  		   			 			return;
				  		   			 			
				  		   			 		}else
				  		   			 			
				  		   			 			
				  		   			 		{
				  		   			 			
				  		   			 		ArticlesClient articlesClientTmp=articlesClientDAO.findByClientByDepot(clientPF, depot)	;
				  		   			 			if(articlesClientTmp==null)
				  		   			 			{
				  		   			 				
				  		  			 			articlesClient.setClientPF(clientPF);
					  		   			 		articlesClient.setDepot(depot);
					  		   			 	articlesClient.setMagasin(magasin);
					  		   			articlesClient.setCreerPar(utilisateur);	
					  		   		articlesClient.setDateSaisi(new Date());		
					  		  	
					  		   	articlesClientDAO.add(articlesClient);
					  		   	
					  		   	
					  		   	for(int i=0;i<listDetailArticleClient.size();i++)
					  		   	{
					  		   		
					  		   		DetailArticlesClient detailArticlesClient=listDetailArticleClient.get(i);
					  		   	detailArticlesClientDAO.add(detailArticlesClient);
					  		   	}
					  		   	
					  		  articlesClient=new ArticlesClient();
					  		listDetailArticleClient.clear();
					  		afficher_DetailArticlesClient(listDetailArticleClient);	 		
					  		intialiser();
					  		comboDepot.setSelectedItem("");
					  		comboMagasin.setSelectedItem("");
					  		comboClientPF.setSelectedItem("");
					  		txtcodeclient.setText("");
		   			 		JOptionPane.showMessageDialog(null, "Les Articles Client Ajouté Avec Succée !!!!!","Bravo",JOptionPane.INFORMATION_MESSAGE);

				  		   			 				
				  		   			 			}else
				  		   			 			{
				  		   			 			JOptionPane.showMessageDialog(null, "Le Client Déja Existant Veuillez entrer un autre client SVP");
				  		   			 			return;
				  		   			 			}
				  		   			 			
				  		   
				  		   	
				  		   	
				  		   	
				  		   			 		}
				  		   			 		
				  		   			 		
				  		   			 		
				  		   			 		
				  		   			 		
				  		   			 		
				  		   			 	}
				  		   			 });
				  		   			buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   		buttonvalider.setIcon(imgAjouter);
				  		   			buttonvalider.setBounds(370, 635, 114, 26);
				  		   			
				  		   			add(buttonvalider);
				  		   			
				  		   			 btnModifier = new JButton();
				  		   			 btnModifier.addActionListener(new ActionListener() {
				  		   			 	public void actionPerformed(ActionEvent arg0) {
				  		   			 		


								  		  	Articles articles=mapArticle.get(comboArticle.getSelectedItem());
								  		  FamilleArticlePF familleArticlePF=mapfamille.get(combofamille.getSelectedItem());
								  		  	SousFamilleArticlePF sousFamilleArticlePF=mapsousfamille.get(combosousfamille.getSelectedItem());
								  		  	
								  		  	if(familleArticlePF==null)
								  		  	{
								  		  		

								  		  		
								  		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner Famille d'Article SVP");
								  		  		return;
								  		  		
								  		  	
								  		  	}
								  		  	
								  		 	if(sousFamilleArticlePF==null)
								  		  	{
								  		  		

								  		  		
								  		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner Sous Famille d'Article SVP");
								  		  		return;
								  		  		
								  		  	
								  		  	}
								  		  	
								  		  	if(articles!=null)
								  		  	{
								  		  		
								  		  	boolean existe=false;
								  		  	
								  		  	for(int i=0;i<listDetailArticleClient.size();i++)
								  		  	{
								  		  		if(i!=table.getSelectedRow())
								  		  		{
	                                       DetailArticlesClient detailArticlesClient=listDetailArticleClient.get(i);
								  		  		
								  		  		if(detailArticlesClient.getArticle().getId()==articles.getId())
								  		  		{
								  		  			
								  		  		if(detailArticlesClient.getSousFamille().getId()==sousFamilleArticlePF.getId())
							  		  			{
								  		  			
							  		  			existe=true;
							  		  			
							  		  			}	
								  		  			
								  		  		}
								  		  		
								  		  		
								  		  		}
								  		  	
								  		  		
								  		  		
								  		  	}
								  		  		
								  		  		if(existe==false)
								  		  		{
								  		  			
								  		  		 DetailArticlesClient detailArticlesClient=listDetailArticleClient.get(table.getSelectedRow());
								  		  		detailArticlesClient.setSousFamille(sousFamilleArticlePF);
								  		  		detailArticlesClient.setArticle(articles);
								  		  	listDetailArticleClient.set(table.getSelectedRow(), detailArticlesClient);
								  		  afficher_DetailArticlesClient(listDetailArticleClient);
								  		intialiser();
								  		  			
								  		  		}else
								  		  		{
								  		  		JOptionPane.showMessageDialog(null, "l'Article existe déja , Veuillez selectionner un autre article SVP");
								  		  		return;
								  		  		}
								  		  		
								  		  		
								  		  		
								  		  	}else
								  		  	{
								  		  		
								  		  		JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'Article SVP");
								  		  		return;
								  		  		
								  		  	}
								  		  	
								  		  	
								  		  	
				  		   			 		
				  		   			 		
				  		   			 		
				  		   			 		
				  		   			 	}
				  		   			 });
				  		   			btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   		btnModifier.setIcon(imgModifierr);
				  		   			btnModifier.setBounds(928, 312, 73, 24);
				  		   		
				  		   			add(btnModifier);
				  		   			
				  		   			 btnSupprimer = new JButton();
				  		   			 btnSupprimer.addActionListener(new ActionListener() {
				  		   			 	public void actionPerformed(ActionEvent e) {
				  		   			 		
				  		   			 		if(table.getSelectedRow()!=-1)
				  		   			 		{
				  		   			 			
				  		   			 			listDetailArticleClient.remove(table.getSelectedRow());
				  		   			 		afficher_DetailArticlesClient(listDetailArticleClient);
				  		   			 			
				  		   			 	intialiser();
				  		   			 		}
				  		   			 		
				  		   			 		
				  		   			 		
				  		   			 	}
				  		   			 });
				  		   			btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   		btnSupprimer.setIcon(imgSupprimer);
				  		   			btnSupprimer.setBounds(928, 342, 73, 24);
				  		   		
				  		   			add(btnSupprimer);


				  		   			
				  		   		
				  		      comboDepot.addItem("");
				  		      if(utilisateur.getLogin().equals("admin"))
				  			  {
				  				  listDepot=depotDAO.findAll();
				  				  int k=0;
				  				
				  			     	while (k<listDepot.size())
				  			     	{
				  			     		Depot depot=listDepot.get(k);
				  			     		Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
				  			     		if(magasin!=null)
				  			     		{
				  			     			if(depot.getId()!=magasin.getDepot().getId())
				  				     		{
				  			     				comboDepot.addItem(depot.getLibelle());
				  					     		
				  			     				mapDepot.put(depot.getLibelle(), depot);
				  					     	
				  					     		
				  				     			
				  				     		}
				  			     		}else
				  			     		{
				  			     			comboDepot.addItem(depot.getLibelle());
				  				     		
				  			     			mapDepot.put(depot.getLibelle(), depot);
				  				     	
				  				     		
				  			     		}
				  			     		k++;
				  			     		
				  			     	}
				  			      
				  			  }else
				  			  {
				  				  Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
				  				  if(depot!=null)
				  				  {
				  					  comboDepot.addItem(depot.getLibelle());
				  					
				  						mapDepot.put(depot.getLibelle(), depot);
				  				  }
				  			  }
	
	
				  	    	   int p=0;
							      while(p<listFamilleArticle.size())
							      {
							    	  
							    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
							    	  combofamille.addItem(famillearticle.getLiblle());
							    	 
							    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
							    	  
							    	  
							    	  
							    	  
							    	  p++;
							      }	
	
	}
	
	

	

	
void intialiser (){
		
		
		
		combofamille.setSelectedItem("");
		combosousfamille.setSelectedIndex(-1);
		txtCodeArticle.setText("");
		
		comboArticle.setSelectedItem("");
	}


void afficher_DetailArticlesClient(List<DetailArticlesClient> listDetailArticlesClient)
{
	
	modele=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Famille", "Sous Famille","Code Article", "Article"
			}
		){
		  	boolean[] columnEditables = new boolean[] {
			  		false, false,false,false
			  	};
		  	
			  	public boolean isCellEditable(int row, int column) {
			  		return columnEditables[column];
			  	}
			  };
		setLayout(null);
	
	
int i=0;
	while(i<listDetailArticlesClient.size()){
		
		DetailArticlesClient detailArticlesClient=listDetailArticlesClient.get(i);
		Object []ligne={detailArticlesClient.getSousFamille().getFamileArticlePF().getLiblle() , detailArticlesClient.getSousFamille().getLiblle(), detailArticlesClient.getArticle().getCodeArticle(),detailArticlesClient.getArticle().getLiblle()};
       modele.addRow(ligne);
              
               
i++;
      
	}
	 table.setModel(modele); 

	
} 
}
