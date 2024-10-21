package presentation.article;

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
import java.math.RoundingMode;
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

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.GrammageCartonDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.GrammageBoxDAO;
import dao.daoManager.GrammageCartonDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailPrixArticle;
import dao.entity.FamilleArticlePF;
import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.Utilisateur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JCheckBox;


public class ModifierEstimationArticle extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleArticle;
	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	private ImageIcon imgModifier;
private JButton btnmodifier;
private JButton btnajouter;
	private JButton btnsupprimer;
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Utilisateur utilisateur;
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<Magasin> listParMagasin =new ArrayList<Magasin>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<DetailPrixArticle> listdetailprixarticle =new ArrayList<DetailPrixArticle>();
	private Map< String, Articles> mapAricle = new HashMap<>();
	private Map< String, String> mapCodeArticle= new HashMap<>();
	private Map< String, String> mapLibelleAricle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	List < Articles> listArticles = new ArrayList<Articles>();
	private List<GrammageBox> listGrammageBox =new ArrayList<GrammageBox>();
	private List<GrammageCarton> listGrammageCarton =new ArrayList<GrammageCarton>();
	List<DetailEstimation> listDetailEstimation = new ArrayList<DetailEstimation>();
	private Map< String, BigDecimal> mapGrammageBox= new HashMap<>();
	private Map< String, BigDecimal> mapGrammageCarton= new HashMap<>();
	private Map<BigDecimal, String> mapGrammageBoxbyGramme= new HashMap<>();
	private Map<BigDecimal , String> mapGrammageCartonbyGramme= new HashMap<>();
	private List<FamilleArticlePF> listFamilleArticlePF =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticlePF =new ArrayList<SousFamilleArticlePF>();
	private Map< String, FamilleArticlePF> mapFamilleArticle = new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapSousFamilleArticle = new HashMap<>();
	private DepotDAO depotdao;
	private  FamilleArticlesPFDAO famillearticleDAO;
	private SousFamilleArticlesPFDAO sousfamilearticleDAO;	
	private GrammageBoxDAO grammageBoxDAO;
	private GrammageCartonDAO grammageCartonDAO;
	private JLabel lblDpotDestination;
	private JComboBox combomagasin = new JComboBox();
private JComboBox combodepot = new JComboBox();
	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesDAO articlesDAO;
	Articles article =new Articles ();
	private JTextField txtCode;
	private JComboBox comboBox;
	private JButton btnChercher;
	private JButton btnIntialiser;
	private JTextField txtlibelle;
	private JTextField txtquantite;
	private JTextField txtcodematiere;
	private JTextField txtpriorite;
	private DetailEstimationDAO detailestimationdao;
	private DetailPrixArticleDAO detailPrixArticleDAO;
	private JTextField txtlibelleArticle;
	private JTextField txtprix;
	private JTable tablePrixArticle;
	private JButton btnAjouter ;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JLabel label_6;
	private JComboBox comboPardepot;
	private JLabel label_7;
	private JComboBox comboparmagasin;
	private JCheckBox checkpacket;
	private JCheckBox chckbxTh;
	private JLabel lblGrammageBox;
	private JComboBox combogrammagebox;
	private JLabel lblGrammageCarton;
	private JComboBox combogrammagecarton;
	private JTextField txtConditionnement;
	JComboBox combofamille = new JComboBox();
	JComboBox combosousfamille = new JComboBox();
	private JCheckBox checkBoxPromo;
	private JCheckBox checkMConsommable;
	private JLabel label_10;
	private JTextField txtConditionnementCaisse;
	private JLabel label_11;
	private JTextField txtprixachat;
	private JLabel label_12;
	private ClientPFDAO clientpfdao;
	 private Map< String, ClientPF> mapClientPF= new HashMap<>();
		private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
		JComboBox comboClientpf = new JComboBox();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ModifierEstimationArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1582, 874);
        try{
        	
        	
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	grammageCartonDAO=new GrammageCartonDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	articlesDAO=new ArticlesDAOImpl();
        	detailestimationdao=new DetailEstimationDAOImpl();
        	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	depotdao=new DepotDAOImpl();
        	famillearticleDAO=new FamilleArticlesPFDAOImpl();
        	sousfamilearticleDAO=new SousFamilleArticlesPFDAOImpl();
        	 comboBox = new JComboBox();
        	 comboBox.addItem("");
        	  txtCode = new JTextField();
        	  clientpfdao=new ClientPFDAOImpl();
        	  util.Utils.copycoller(txtCode);
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
       
				  		ChargerComboArticle();
				  		      comboBox.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 		 if(!comboBox.getSelectedItem().equals(""))
				  		     	 		 {
				  		     	 			txtCode.setText(mapCodeArticle.get(comboBox.getSelectedItem()));
					  		     	 		txtlibelleArticle.setText(comboBox.getSelectedItem().toString());
					  		     	 		Articles article=mapAricle.get(comboBox.getSelectedItem());
					  		     	 		if(article.getCentreCout2()!=null)
					  		     	 		{
					  		     	 			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					  		     	 			{
					  		     	 				checkpacket.setSelected(true);
					  		     	 			}else
					  		     	 			{
					  		     	 			checkpacket.setSelected(false);
					  		     	 			}
					  		     	 		}else
					  		     	 		{
					  		     	 		  checkpacket.setSelected(false);	
					  		     	 		}
					  		     	 		
					  		     	 	 	}else
					  		     	 	 	{
					  		     	 	 	initialiserMP();
					  		     	 	 	listDetailEstimation.clear();
					  		     	 	 	afficher_tableMP(listDetailEstimation);
					  		     	 	 	}
					  		     	 	  
				  		     	 		 }
				  		     	
				  		     	 		
				  		     	 	 
				  		     	 	 
				  		     	 	 
				  		     	 	 
				  		     	 	}
				  		     	 });
				  		      
				  			txtCode.addKeyListener(new KeyAdapter() {
				  			  	@Override
				  			  	public void keyReleased(KeyEvent e)
				  			  	{
				  			  		if (e.getKeyCode() == e.VK_ENTER)
				  			  		{
				  			  			if(mapLibelleAricle.get(txtCode.getText())!=null)
				  			  			{
				  			  			comboBox.setSelectedItem(mapLibelleAricle.get(txtCode.getText()));
				  			  			Articles article=articlesDAO.findByCode(txtCode.getText());
				  			  			if(article!=null)
				  			  			{
				  			  				if(article.getCentreCout2()!=null)
				  			  				{
				  			  					if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
				  			  					{
				  			  					checkpacket.setSelected(true);
				  			  					}else
				  			  					{
				  			  					checkpacket.setSelected(false);
				  			  					}
				  			  					
				  			  				}else
				  			  				{
				  			  				checkpacket.setSelected(false);
				  			  				}
				  			  				
				  			  				
				  			  			}
				  			  			
				  			  			
				  			  			
				  			  			}else
				  			  			{
				  			  				JOptionPane.showMessageDialog(null, "Article introuvable !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  			  				return;
				  			  			}
				  			  			
				  			  	
				  			  		}}});
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		
				  		     		if(listDetailEstimation.size()!=0)
				  				 	{
				  				 		if(table.getSelectedRow()!=-1)
				  				 		{
				  				 			
				  				 			txtcodematiere.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  				 			txtlibelle.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				  				 			txtquantite.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				  				 			txtpriorite.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				  				 			btnsupprimer.setEnabled(true);
				  				 			btnmodifier.setEnabled(true);
				  				 			btnajouter.setEnabled(false);
				  				 			//txtcodematiere.setEditable(false);
				  				 			
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
				  		     	scrollPane.setBounds(36, 654, 1410, 209);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(36, 613, 1410, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(36, 13, 1488, 143);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 12, 90, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(182, 10, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		
				  		  txtCode.setBounds(71, 12, 101, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		 
				  		  comboBox.setBounds(259, 10, 206, 24);
				  		  layeredPane.add(comboBox);
				  		  AutoCompleteDecorator.decorate(comboBox);
				  		  btnChercher = new JButton("Chercher");
				  		  btnChercher.setIcon(imgChercher);
				  		  btnChercher.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		

				  				

checkpacket.setSelected(false);
checkBoxPromo.setSelected(false);
txtConditionnement.setText("");
txtConditionnementCaisse.setText("");
combogrammagebox.setSelectedIndex(-1);
combogrammagecarton.setSelectedIndex(-1);
txtprixachat.setText("");
chckbxTh.setSelected(false);
theselectionner();
				
				  		  		
				  		  	listdetailprixarticle.clear();
				  		  		article =articlesDAO.findByCode(txtCode.getText());
				  		  		txtConditionnement.setText(article.getConditionnement()+"");
				  		  		if(article.getCentreCout3()!=null)
				  		  		{
				  		  		chckbxTh.setSelected(true);
				  		  	theselectionner();
				  		  		String codegrammagecarton=mapGrammageCartonbyGramme.get(article.getCentreCout3().setScale(2, RoundingMode.HALF_UP));
				  		  		
				  		  		combogrammagecarton.setSelectedItem(codegrammagecarton);
				  		  		}
				  		  	  if(article.getCentreCout1()!=null) 
				  		  	  {
				  		  		chckbxTh.setSelected(true);
				  		  	theselectionner();
				  		  		String codegrammagebox=mapGrammageBoxbyGramme.get(article.getCentreCout1().setScale(2, RoundingMode.HALF_UP));
				  		  
				  		  		combogrammagebox.setSelectedItem(codegrammagebox);
				  		  	  }
				  		  	  
				  		  	  if(article.getCentreCout5()!=null)
				  		  	  {
				  		  		 
				  		  		 if(article.getCentreCout5().compareTo(BigDecimal.ONE)==0) 
				  		  		 {
				  		  			 checkBoxPromo.setSelected(true);
				  		  		 }else
				  		  		 {
				  		  			checkBoxPromo.setSelected(false);
				  		  		 }
				  		  		  
				  		  	  }
				  		  	  
				  		  	  if(article.getCodeFonction()!=null)
				  		  	  {
				  		  		  
				  		  		if(article.getCodeFonction().equals(Constantes.ARTICLE_CONSOMMABLE))  
				  		  		{
				  		  			checkMConsommable.setSelected(true);
				  		  			
				  		  		}else
				  		  		{
				  		  			
				  		  		checkMConsommable.setSelected(false);
				  		  		
				  		  		}
				  		  		  
				  		  	  }
				  		  	  
				  		  	  if(article.getCentreCout2()!=null)
				  		  	  {
				  		  		  
				  		  		 if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET)) 
				  		  		 {
				  		  			 checkpacket.setSelected(true);
				  		  		 }else
				  		  		 {
				  		  		 checkpacket.setSelected(false);
				  		  		 }
				  		  		  
				  		  		  
				  		  	  }
				  		  	  
				  		  	  
				  		  	  
				  		  	  if(article.getConditionnementcaisse()!=null)
				  		  	  {
				  		  		  
				  		  		 txtConditionnementCaisse.setText(article.getConditionnementcaisse()+""); 
				  		  		 
				  		  		  
				  		  	  }
				  		  	 
				  		  	  if(article.getPrixAchat()!=null)
				  		  	  {
				  		  		  txtprixachat.setText(article.getPrixAchat()+"");
				  		  	  }else
				  		  	  {
				  		  		txtprixachat.setText(new BigDecimal(0)+"");
				  		  	  }
				  		  	  
				  		  	  
				  		  	  Magasin magasin=mapMagasin.get(comboparmagasin.getSelectedItem());
				  		  		if(article!=null && magasin!=null )
				  		  		{
				  		  		listDetailEstimation=detailestimationdao.findDetilestimationByArticle(article.getId());
				  		  	listdetailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasin(article.getId(), magasin.getId());
				  		  	if(listdetailprixarticle.size()!=0)
				  		  	{
				  		  	 
				  		  	afficher_tablePrixArticle(listdetailprixarticle);	
				  		  	}else
				  		  	{
				  		  
				  			afficher_tablePrixArticle(listdetailprixarticle);	
				  		  		
				  		  	}
				  		
						  		afficher_tableMP(listDetailEstimation);
				  		  		
				  		  		}else
				  		  		{
				  		  			JOptionPane.showMessageDialog(null, "Article introuvable !!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		  			return;
				  		  		}
				  		  		
				  		  	}
				  		  });
				  		  btnChercher.setBounds(590, 107, 114, 24);
				  		  layeredPane.add(btnChercher);
				  		  
				  		  btnIntialiser = new JButton("Intialiser");
				  		  btnIntialiser.setIcon(imgInit);
				  		  btnIntialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser();
				  		  	}
				  		  });
				  		  btnIntialiser.setBounds(722, 107, 114, 24);
				  		  layeredPane.add(btnIntialiser);
				  		  
				  		  txtlibelleArticle = new JTextField();
				  		  txtlibelleArticle.setColumns(10);
				  		  txtlibelleArticle.setBounds(534, 10, 206, 26);
				  		  util.Utils.copycoller(txtlibelleArticle);
				  		  layeredPane.add(txtlibelleArticle);
				  		  
				  		  
				  		  JLabel lblArticle = new JLabel("    Article");
				  		  lblArticle.setBounds(475, 10, 63, 26);
				  		  layeredPane.add(lblArticle);
				  		  
				  		  JButton button = new JButton("Modifier");
				  		  button.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent arg0) {
				  		  
				  		  		if(!comboBox.getSelectedItem().equals(""))
				  		  		{
				  		  			if(!txtlibelleArticle.getText().equals(""))
				  		  			{
				  		  				
				  		  			if(chckbxTh.isSelected()==true)
									{
										if(combogrammagebox.getSelectedIndex()==-1)
										{
											JOptionPane.showMessageDialog(null, "Il faut selectionner le grammage Box SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
											return;	
										}
										
										if(combogrammagecarton.getSelectedIndex()==-1)
										{
											JOptionPane.showMessageDialog(null, "Il faut selectionner le grammage Carton SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
											return;	
										}
										
										
										
										
										
										
										
									}
				  		  		if(txtConditionnement.getText().equals("")){
									JOptionPane.showMessageDialog(null, "Il faut saisir le conditionnement", "Erreur", JOptionPane.ERROR_MESSAGE);
									return;
									
								}else if(chckbxTh.isSelected()==true && checkpacket.isSelected()==true)
								{
									JOptionPane.showMessageDialog(null, "un Article ne peut pas etre de type thé et en packet", "Erreur", JOptionPane.ERROR_MESSAGE);
									return;
								}
				  		  		
				  		  		if(txtprixachat.getText().equals(""))
				  		  		{
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Veuillez entrer le prix Achat SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
								return;
								
				  		  		}
				  		  			
				  		  			
				  		  		    Articles article=articlesDAO.findByCode(txtCode.getText());
				  		  			article.setLiblle(txtlibelleArticle.getText());
				  		  		article.setConditionnement(new BigDecimal(txtConditionnement.getText()));
				  		  	article.setPrixAchat(new BigDecimal(txtprixachat.getText()));
				  		  		if(!txtConditionnementCaisse.getText().equals(""))
				  		  		{
				  		  			
				  		  		article.setConditionnementcaisse(new BigDecimal(txtConditionnementCaisse.getText()));
				  		  			
				  		  		}else
				  		  		{
				  		  			
				  		  		article.setConditionnementcaisse(new BigDecimal(1));
				  		  			
				  		  		}
				  		  		
				  		  		
				  		  		if(checkpacket.isSelected()==true)
				  		  		{
				  		  		article.setCentreCout2(Constantes.ARTICLE_PACKET);
				  		  	article.setCentreCout1(null); // grammage box 
				  		  		}else
				  		  		{
				  		  		article.setCentreCout2(Constantes.ARTICLE_PIECE);
				  		  		}
				  		  		
				  		  	if(chckbxTh.isSelected()==true)
							{
								BigDecimal grammagebox=mapGrammageBox.get(combogrammagebox.getSelectedItem());
								BigDecimal grammageCarton=mapGrammageCarton.get(combogrammagecarton.getSelectedItem()); // grammage carton
								
								article.setCentreCout3(grammageCarton);
								if(grammagebox.compareTo(new BigDecimal(100))<0)
								{
									
									article.setCentreCout1(BigDecimal.ONE);
									
								}else
								{
									article.setCentreCout1(grammagebox); // grammage box 
								}
								
								
								if(checkBoxPromo.isSelected()==true)
								{
									article.setCentreCout5(BigDecimal.ONE); // centrecout5 = article promotion ou non
								}else
								{
									article.setCentreCout5(BigDecimal.ZERO);
								}
								
								
							}else
							{
								article.setCentreCout5(BigDecimal.ZERO);
								
							}
				  		  	
				  		  	if(checkMConsommable.isSelected()==true)
				  		  	{
				  		  		
				  		  	article.setCodeFonction(Constantes.ARTICLE_CONSOMMABLE); // code fonction = Article Consommable;
				  		  		
				  		  	}else
				  		  	{
				  		  		
				  		  	article.setCodeFonction(null);
				  		  	
				  		  	}
				  		  	
				  		  	
				  		  	
				  		  	
				  		  //  article.setModifierPar(utilisateur);
				  		  			articlesDAO.edit(article);
				  		  		    JOptionPane.showMessageDialog(null, "Article Modifier Avec Succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
				  		  			ChargerComboArticle();
				  		  			intialiser();
				  		  		   	initialiserMP();
		  		     	 	     	listDetailEstimation.clear();
		  		     	 	 	    afficher_tableMP(listDetailEstimation);
		  		     	 	 	    
				  		  				}
				  		  				
				  		  				
				  		  			}
				  		  			
				  		  		}
				  		  	
				  		  });
				  		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  button.setBounds(473, 108, 107, 24);
				  		  layeredPane.add(button);
				  		  
				  		  label_6 = new JLabel("Depot");
				  		  label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  label_6.setBounds(750, 12, 56, 24);
				  		  layeredPane.add(label_6);
				  		  
				  		  comboPardepot = new JComboBox();
				  		  comboPardepot.addItemListener(new ItemListener() {
				  		  	public void itemStateChanged(ItemEvent e) {
				  		  		
				  		  		
				 	 			
					   	 		 if(e.getStateChange() == ItemEvent.SELECTED)
					   	 		 {
					   	 			int i=0;
					   	 		
					   	 				if(!comboPardepot.getSelectedItem().equals(""))
					    			{
					    				Depot depot=mapDepot.get(comboPardepot.getSelectedItem());
					    				if(depot!=null)
					    				{
					    					
					    						listParMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_PF);
							     				if(listParMagasin.size()!=0)
							     				{
							     					comboparmagasin.removeAllItems();
							     					comboparmagasin.addItem("");
							     					while(i<listParMagasin.size())
					 		     				{
					 		     					Magasin magasin=listParMagasin.get(i);
					 		     					comboparmagasin.addItem(magasin.getLibelle());
					 		     					mapMagasin.put(magasin.getLibelle(), magasin);
					 		     					i++;
					 		     				}
							     					
							     					
							     				}else
							     				{
							     					comboparmagasin.removeAllItems();
							     					
							     				}
							     				
							     			
							     			}else
							     			{
							     				comboparmagasin.removeAllItems();
							     				
							     			}
							     				
					    					
					    				}
					    				
					    				
					   	 		 }
					   	 	
						
								
								
								
								
							
						 		
						 	
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		  comboPardepot.setSelectedIndex(-1);
				  		  comboPardepot.setBounds(794, 12, 168, 24);
				  		  layeredPane.add(comboPardepot);
				  		  
				  		  label_7 = new JLabel("Magasin");
				  		  label_7.setBounds(972, 10, 56, 26);
				  		  layeredPane.add(label_7);
				  		  
				  		  comboparmagasin = new JComboBox();
				  		  comboparmagasin.setSelectedIndex(-1);
				  		  comboparmagasin.setBounds(1021, 11, 211, 24);
				  		  layeredPane.add(comboparmagasin);
				  		  
				  		  checkpacket = new JCheckBox("Packet");
				  		  checkpacket.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		  checkpacket.setBounds(1248, 12, 77, 23);
				  		  layeredPane.add(checkpacket);
				  		  
				  		  chckbxTh = new JCheckBox("Th\u00E9");
				  		  chckbxTh.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent arg0) {
				  		  		
				  		  		theselectionner();
				  		  	
				  		  	
				  		  	}
				  		  });
				  		  chckbxTh.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		  chckbxTh.setBounds(673, 49, 53, 23);
				  		  layeredPane.add(chckbxTh);
				  		  
				  		  lblGrammageBox = new JLabel("Grammage Box :");
				  		  lblGrammageBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblGrammageBox.setBounds(816, 48, 101, 24);
				  		  layeredPane.add(lblGrammageBox);
				  		  
				  		  combogrammagebox = new JComboBox();
				  		  combogrammagebox.setSelectedIndex(-1);
				  		  combogrammagebox.setBounds(904, 47, 168, 24);
				  		  layeredPane.add(combogrammagebox);
				  		  
				  		  lblGrammageCarton = new JLabel("Grammage carton :");
				  		  lblGrammageCarton.setBounds(1082, 46, 101, 26);
				  		  layeredPane.add(lblGrammageCarton);
				  		  
				  		  combogrammagecarton = new JComboBox();
				  		  combogrammagecarton.setSelectedIndex(-1);
				  		  combogrammagecarton.setBounds(1189, 47, 211, 24);
				  		  layeredPane.add(combogrammagecarton);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(36, 494, 1410, 108);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Quantite :");
		label.setBounds(630, 11, 102, 26);
		layeredPane_1.add(label);
		
		JLabel label_1 = new JLabel("Code Matiere :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(170, 11, 82, 26);
		layeredPane_1.add(label_1);
		
		txtlibelle = new JTextField();
		txtlibelle.setEditable(false);
		txtlibelle.setColumns(10);
		txtlibelle.setBounds(463, 11, 157, 26);
		layeredPane_1.add(txtlibelle);
		
		JLabel label_2 = new JLabel("Libelle :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(416, 11, 68, 26);
		layeredPane_1.add(label_2);
		
		txtquantite = new JTextField();
		util.Utils.copycoller(txtquantite);
		txtquantite.setColumns(10);
		txtquantite.setBounds(691, 11, 157, 26);
		layeredPane_1.add(txtquantite);


 		  
 		  
		 btnmodifier = new JButton("Modifier");
		 btnmodifier.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		BigDecimal number ; 
				BigDecimal somme = new BigDecimal("0");
				BigDecimal result = new BigDecimal("1");
				BigDecimal priorite = new BigDecimal("2");
boolean envrac=false;
				boolean trouve=false;
				
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
					else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)
					{
						JOptionPane.showMessageDialog(null, "la Quantité de matiere premiere  doit etre supérieur à 0  SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
		    			return;
					}
				else if(new BigDecimal(txtpriorite.getText()).compareTo(priorite)==0 && (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0 || new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ONE)>=0))
	    		{
	    			JOptionPane.showMessageDialog(null, "la Quantité de matiere premiere En Vrac doit etre entre  0 et 1 SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}else if(Integer.valueOf(txtpriorite.getText())<0)
	    		{
	    			JOptionPane.showMessageDialog(null, "la Priorité de matiere premiere doit etre supérieur ou egale  0 !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}else
					{
						if(listDetailEstimation.size()!=0)
						{
							if(table.getSelectedRow()!=-1)
							{
							 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier la matiere premiere ?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION )
										
										
									{
										MatierePremier matierepremiere=matierePremiereDAO.findByCode(txtcodematiere.getText().toUpperCase());
										if(!matierepremiere.getNom().equals(txtlibelle.getText()))
												{
											JOptionPane.showMessageDialog(null, "Le Nom de la Matiere premiere incorrect Veuillez appuyer sur le boutton entrer dans la case de code MP pour afficher le nom de MP SVP !!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
											return;
												}
										if(matierepremiere!=null)
										{
										
										int i=0;
					    				
						    			while( i<listDetailEstimation.size())
						    				{
						    				
						    				if(listDetailEstimation.get(i).getPriorite()==2)
						    				{
						    					if(listDetailEstimation.get(i).getPriorite()==Integer.valueOf(txtpriorite.getText()))
						    					{
						    						envrac=true;
						    						number =new BigDecimal(listDetailEstimation.get(i).getQuantite()+"");
													somme=somme.add(number);
						    					}
						    				}
						    			
						    					if(i!=table.getSelectedRow())
						    					{
						    						if(listDetailEstimation.get(i).getMatierePremier().getCode().equals(matierepremiere.getCode()) && listDetailEstimation.get(i).getPriorite()== Integer.valueOf(txtpriorite.getText()))
							    					{
							    						trouve=true;
							    						JOptionPane.showMessageDialog(null, "Matiere premiere existe deja dans l'estimation !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
							    						return;
							    					}
								    				
						    					}
						    				
						    					
						    					
						    					i++;
						    				}
						    			if(envrac==true)
						    			{
						    				if(listDetailEstimation.get(table.getSelectedRow()).getPriorite()==Integer.valueOf(txtpriorite.getText()))
						    				{
						    					number =new BigDecimal(txtquantite.getText()+"");
												somme=somme.add(number);
												number=new BigDecimal(listDetailEstimation.get(table.getSelectedRow()).getQuantite()+"");
												somme=somme.subtract(number);
						    					
						    				}else
						    				{
						    					number =new BigDecimal(txtquantite.getText()+"");
												somme=somme.add(number);
						    				}
						    					
						    					
												
												if(somme.compareTo(result)==1)
												{
													JOptionPane.showMessageDialog(null, "La somme de la quantité de la matiere premiere En Vrac de meme priorité doit etre egale 1 ", "Erreur", JOptionPane.ERROR_MESSAGE);
													return;
													
												}
												else 
												{
										
										
								DetailEstimation detailestimation=listDetailEstimation.get(table.getSelectedRow());
								detailestimation.setQuantite(new BigDecimal(txtquantite.getText()));
								detailestimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
								
				    			detailestimation.setMatierePremier(matierepremiere);
				    				
								detailestimationdao.edit(detailestimation);
								listDetailEstimation.set(table.getSelectedRow(), detailestimation);
								
								afficher_tableMP(listDetailEstimation);
								JOptionPane.showMessageDialog(null, "Matiere Premiere modifier avec succée ");
								 initialiserMP();
								 
												}
						    			}else
						    			{
						    				DetailEstimation detailestimation=listDetailEstimation.get(table.getSelectedRow());
											detailestimation.setQuantite(new BigDecimal(txtquantite.getText()));
											detailestimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
											
							    			detailestimation.setMatierePremier(matierepremiere);
							    				
											detailestimationdao.edit(detailestimation);
											listDetailEstimation.set(table.getSelectedRow(), detailestimation);
											
											afficher_tableMP(listDetailEstimation);
											JOptionPane.showMessageDialog(null, "Matiere Premiere modifier avec succée ");
											 initialiserMP();
						    			}
						    			
						    		
									}else
					    			{
					    				JOptionPane.showMessageDialog(null, "la matiere premiere introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
					    				
					    			}
									
							}
							
						}else
						{
							JOptionPane.showMessageDialog(null, "liste Matiere premiere est vide !!!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
							
						}
					}
					}	
				} catch (NumberFormatException r) {  JOptionPane.showMessageDialog(null, "la Quantite et la priorité de matiere premiere doit etre en chiffre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
				initialiserMP();
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		btnmodifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnmodifier.setEnabled(false);
		btnmodifier.setBounds(376, 61, 107, 24);
		layeredPane_1.add(btnmodifier);
		
		JButton btninitialiser = new JButton("Initialiser");
		btninitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				initialiserMP();
				
				
				
			}
		});
		btninitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btninitialiser.setBounds(508, 62, 106, 23);
		layeredPane_1.add(btninitialiser);
		
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
		txtcodematiere.setBounds(249, 11, 157, 26);
		layeredPane_1.add(txtcodematiere);
		
		 btnsupprimer = new JButton("Supprimer");
		 btnsupprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		if(table.getSelectedRow()!=-1)
				{
					if(listDetailEstimation.size()!=0)
					{
						 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer la matiere premiere ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
							{
								
							    detailestimationdao.delete(listDetailEstimation.get(table.getSelectedRow()).getId());
								listDetailEstimation.remove(table.getSelectedRow());
								afficher_tableMP(listDetailEstimation);
								JOptionPane.showMessageDialog(null, "Matiere Premiere supprimer avec succée ");
								initialiserMP();
								
							}
						}
					}
		 		
		 		
		 	}
		 });
		btnsupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnsupprimer.setEnabled(false);
		btnsupprimer.setBounds(644, 61, 107, 24);
		layeredPane_1.add(btnsupprimer);
		
		 btnajouter = new JButton("Ajouter");
		 btnajouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		BigDecimal number ; 
				BigDecimal somme = new BigDecimal("0");
				BigDecimal result = new BigDecimal("1");
				BigDecimal priorite = new BigDecimal("2");
				
				

		 		boolean envrac=false;
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
	    		}else if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
	    		{
	    			JOptionPane.showMessageDialog(null, "la Quantité de matiere premiere doit etre en chiffre et supérieur à 0 SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
			else if(new BigDecimal(txtpriorite.getText()).compareTo(priorite)==0 && (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0 || new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ONE)>=0 ))
    		{
    			JOptionPane.showMessageDialog(null, "la Quantité de matiere premiere En Vrac doit etre entre 0 et 1 SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
    			return;
    		}
	    		else if(Integer.valueOf(txtpriorite.getText())<0 )
	    		{

	    			JOptionPane.showMessageDialog(null, "la priorité de matiere premiere doit etre supérieur ou egale 0 SVP !!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		else
	    		{
	    			
	    			MatierePremier matierepremiere=matierePremiereDAO.findByCode(txtcodematiere.getText().toUpperCase());
	    			
	    			
	    			if(matierepremiere!=null)
	    			{
	    				
	    				if(!matierepremiere.getNom().equals(txtlibelle.getText()))
						{
					JOptionPane.showMessageDialog(null, "Le Nom de la Matiere premiere incorrect Veuillez appuyer sur le boutton entrer dans la case de code MP pour afficher le nom de MP SVP !!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
						}
	    				
	    				int i=0;
	    				
	    			while( i<listDetailEstimation.size())
	    				{
	    				
	    				if(listDetailEstimation.get(i).getPriorite()==2)
	    				{
	    					if(listDetailEstimation.get(i).getPriorite()==Integer.valueOf(txtpriorite.getText()))
	    					{
	    						envrac=true;
	    						number =new BigDecimal(listDetailEstimation.get(i).getQuantite()+"");
								somme=somme.add(number);
	    					}
	    				}
	    			
	    					
	    					if(listDetailEstimation.get(i).getMatierePremier().getCode().equals(matierepremiere.getCode()) && listDetailEstimation.get(i).getPriorite()==Integer.valueOf(txtpriorite.getText()))
	    					{
	    						trouve=true;
	    						JOptionPane.showMessageDialog(null, "Matiere premiere existe deja dans l'estimation !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
	    						return;
	    					}
	    					
	    					i++;
	    				}
	    				if(trouve==false)
	    				{
	    					if(envrac==true)
	    					{
	    						
	    						number =new BigDecimal(txtquantite.getText()+"");
								somme=somme.add(number);
		    					
								
								if(somme.compareTo(result)==1 )
								{
									JOptionPane.showMessageDialog(null, "La somme de la quantité de la matiere premiere En Vrac doit etre egale 1 ", "Erreur", JOptionPane.ERROR_MESSAGE);
									return;
									
								}
								else 
								{
		    				DetailEstimation detailestimation=new DetailEstimation();
		    				detailestimation.setMatierePremier(matierepremiere);
		    				detailestimation.setQuantite(new BigDecimal(txtquantite.getText()));	
		    				detailestimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
		    				detailestimation.setArticles(article);
		    				
		    				detailestimationdao.add(detailestimation);
		    				listDetailEstimation.add(detailestimation);
		    				afficher_tableMP(listDetailEstimation);
		    				initialiserMP();
								}
	    						
	    					}else
	    						{
	    						
	    						DetailEstimation detailestimation=new DetailEstimation();
			    				detailestimation.setMatierePremier(matierepremiere);
			    				detailestimation.setQuantite(new BigDecimal(txtquantite.getText()));	
			    				detailestimation.setPriorite(Integer.valueOf(txtpriorite.getText()));
			    				detailestimation.setArticles(article);
			    				
			    				detailestimationdao.add(detailestimation);
			    				listDetailEstimation.add(detailestimation);
			    				afficher_tableMP(listDetailEstimation);
			    				initialiserMP();
	    						
	    						}
	    						
	    					
	    				
	    						
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
		btnajouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnajouter.setBounds(768, 61, 107, 24);
		layeredPane_1.add(btnajouter);
		
		JLabel lblPriorit = new JLabel("Priorit\u00E9 :");
		lblPriorit.setBounds(858, 11, 61, 26);
		layeredPane_1.add(lblPriorit);
		
		txtpriorite = new JTextField();
		util.Utils.copycoller(txtpriorite);
		txtpriorite.setColumns(10);
		txtpriorite.setBounds(913, 11, 157, 26);
		layeredPane_1.add(txtpriorite);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Les Prix d'Articles Par Magasin");
		titledSeparator_1.setBounds(36, 167, 1488, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_2.setBounds(36, 199, 1488, 113);
		add(layeredPane_2);
		
		JLabel label_3 = new JLabel("Depot");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(33, 12, 56, 24);
		layeredPane_2.add(label_3);
		
		JLabel label_4 = new JLabel("Magasin");
		label_4.setBounds(255, 11, 56, 26);
		layeredPane_2.add(label_4);
		
		JLabel label_5 = new JLabel("Prix");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(522, 12, 38, 24);
		layeredPane_2.add(label_5);
		
		txtprix = new JTextField();
		txtprix.setColumns(10);
		txtprix.setBounds(550, 12, 78, 24);
		layeredPane_2.add(txtprix);
		
		 combodepot = new JComboBox();
		 combodepot.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		

				

		  		

 	 			
	   	 		 if(e.getStateChange() == ItemEvent.SELECTED)
	   	 		 {
	   	 			int i=0;
	   	 		
	   	 				if(!combodepot.getSelectedItem().equals(""))
	    			{
	    				Depot depot=mapDepot.get(combodepot.getSelectedItem());
	    				if(depot!=null)
	    				{
	    					
	    						listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_PF);
			     				if(listMagasin.size()!=0)
			     				{
			     					combomagasin.removeAllItems();
			     					combomagasin.addItem("");
			     					while(i<listMagasin.size())
	 		     				{
	 		     					Magasin magasin=listMagasin.get(i);
	 		     					combomagasin.addItem(magasin.getLibelle());
	 		     					mapMagasin.put(magasin.getLibelle(), magasin);
	 		     					i++;
	 		     				}
			     					
			     					
			     				}else
			     				{
			     					combomagasin.removeAllItems();
			     					
			     				}
			     				
			     				
			     				
			     				mapClientPF.clear();
			     				listClientPF=clientpfdao.findListClientByCodeDepot(depot.getCode());
			     				
			     				if(listClientPF.size()!=0)
			     				{
			     					comboClientpf.removeAllItems();
			     					comboClientpf.addItem("");
			     					for(int j=0;j<listClientPF.size();j++)
			     					{
			     						ClientPF clientpf=listClientPF.get(j);
			     						
			     						comboClientpf.addItem(clientpf.getNom());
			     						mapClientPF.put(clientpf.getNom(), clientpf);
			     						
			     						
			     					}
			     				}else
			     				{
			     					comboClientpf.removeAllItems();
			     				}
			     				
			     				
			     				
			     				
			     			
			     			}else
			     			{
			     				combomagasin.removeAllItems();
			     				
			     			}
			     				
	    					
	    				}
	    				
	    				
	   	 		 }
	   	 	
		
				
				
				
				
			
		 		
		 	}
		 });
		combodepot.setSelectedIndex(-1);
		combodepot.setBounds(77, 12, 168, 24);
		layeredPane_2.add(combodepot);
		
		 combomagasin = new JComboBox();
		combomagasin.setSelectedIndex(-1);
		combomagasin.setBounds(301, 12, 211, 24);
		layeredPane_2.add(combomagasin);
		
		 btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					

					boolean trouve=false;
				
					
				 if(txtCode.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(comboBox.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else if(txtprix.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer le prix","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(  new BigDecimal(txtprix.getText()).compareTo(BigDecimal.ZERO)<0)
					{
						JOptionPane.showMessageDialog(null, "le prix d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					
					}else if(combodepot.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combomagasin.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
						
					}else if(combomagasin.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combofamille.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir la famille d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combosousfamille.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le sous famille d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}
					
					else 
					{
						
						 Articles articleTMP=listdetailprixarticle.get(tablePrixArticle.getSelectedRow()).getArticles();
				        
				         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				      Depot depot=mapDepot.get(combodepot.getSelectedItem());
				      FamilleArticlePF familleArticlePF=mapFamilleArticle.get(combofamille.getSelectedItem());
					     SousFamilleArticlePF sousFamilleArticlePF=mapSousFamilleArticle.get(combosousfamille.getSelectedItem());
					     
					     ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
					     
				      if(!comboparmagasin.getSelectedItem().equals(magasin.getLibelle()))
					     {
					    	JOptionPane.showMessageDialog(null, "Veuillez selectionner le meme magasin d'Article SVP","Erreur",JOptionPane.ERROR_MESSAGE); 
					    	return;
					     }
				         for(int i=0;i<listdetailprixarticle.size();i++)
				         {
				        	 if(i!=tablePrixArticle.getSelectedRow())
				        	 {
				        		 DetailPrixArticle detailPrixArticle =listdetailprixarticle.get(i);
					        	 
					     	 	if(clientPF!=null)
					        	{
					        		
					        	if(detailPrixArticle.getClientPF()!=null)
					        	{
					        		
					        		 if( detailPrixArticle.getArticles().getLiblle().equals(articleTMP.getLiblle()) && detailPrixArticle.getArticles().getCodeArticle().equals(articleTMP.getCodeArticle()) &&  detailPrixArticle.getMagasin().equals(magasin) && detailPrixArticle.getFamilleArticlePF().equals(familleArticlePF) && detailPrixArticle.getSousFamilleArticlePF().equals(sousFamilleArticlePF) && detailPrixArticle.getClientPF().getId()==clientPF.getId())
						        	 {
						        		 trouve=true;
						        	 }
					        		
					        		
					        	}
					        		
					        		
					        		
					        	}else if(detailPrixArticle.getArticles().getLiblle().equals(articleTMP.getLiblle()) && detailPrixArticle.getArticles().getCodeArticle().equals(articleTMP.getCodeArticle()) && detailPrixArticle.getMagasin().equals(magasin)  && detailPrixArticle.getFamilleArticlePF().equals(familleArticlePF) && detailPrixArticle.getSousFamilleArticlePF().equals(sousFamilleArticlePF) &&  detailPrixArticle.getClientPF().equals(null))
				        	 {
					        		
					        		
					        		
				        		 trouve=true;
				        		 
				        		 
				        		 
				        		 
				        	 }
					        	 
					        	 
				        	 }
				        	 
				        	
				         }
				         
				         if(trouve==false)
				         {

				        	 DetailPrixArticle detailPrixArticle=listdetailprixarticle.get(tablePrixArticle.getSelectedRow());
				        	
				        	 detailPrixArticle.setDepot(depot);
				        	 detailPrixArticle.setMagasin(magasin);
				        	 detailPrixArticle.setPrix(new BigDecimal(txtprix.getText()));
				        	 detailPrixArticle.setFamilleArticlePF(familleArticlePF);
				        	 detailPrixArticle.setSousFamilleArticlePF(sousFamilleArticlePF);
				         	 if(clientPF!=null)
				        	 {
				        		 detailPrixArticle.setClientPF(clientPF);
				        	 }else
				        	 {
				        		 detailPrixArticle.setClientPF(null);
				        	 }
				             listdetailprixarticle.set(tablePrixArticle.getSelectedRow(),detailPrixArticle);
				             detailPrixArticleDAO.edit(detailPrixArticle);
				             afficher_tablePrixArticle(listdetailprixarticle);
				             vider_PrixArticle();
				         }else
					       {
					    	   JOptionPane.showMessageDialog(null, "Article déja existant Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    	   return;
					       }
					       
				         }
				        
						
					
					
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix  doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			
				
			
				
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnModifier.setEnabled(false);
		btnModifier.setBounds(370, 60, 107, 24);
		layeredPane_2.add(btnModifier);
		
		JButton button_2 = new JButton("Initialiser");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vider_PrixArticle();
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_2.setBounds(502, 61, 106, 23);
		layeredPane_2.add(button_2);
		
		 btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablePrixArticle.getSelectedRow()!=-1)
				{
					DetailPrixArticle detailPrixArticle=listdetailprixarticle.get(tablePrixArticle.getSelectedRow());
					detailPrixArticleDAO.delete(detailPrixArticle.getId());
					listdetailprixarticle.remove(tablePrixArticle.getSelectedRow());
					afficher_tablePrixArticle(listdetailprixarticle);
					vider_PrixArticle();
				}
				
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBounds(638, 60, 107, 24);
		layeredPane_2.add(btnSupprimer);
		
		 btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					

					boolean trouve=false;
				
					
				 if(txtCode.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(comboBox.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else if(txtprix.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer le prix","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if( new BigDecimal(txtprix.getText()).compareTo(BigDecimal.ZERO)<0)
					{
						JOptionPane.showMessageDialog(null, "le prix d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					
					}else if(combodepot.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combomagasin.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
						
					}else if(combomagasin.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combofamille.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir la famille d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(combosousfamille.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le sous famille d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;	
					}
					else
					{
						
						 Articles articleTMP=articlesDAO.findByCode(txtCode.getText());
				        
				         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				      Depot depot=mapDepot.get(combodepot.getSelectedItem());
				     FamilleArticlePF familleArticlePF=mapFamilleArticle.get(combofamille.getSelectedItem());
				     SousFamilleArticlePF sousFamilleArticlePF=mapSousFamilleArticle.get(combosousfamille.getSelectedItem());
				     ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
				     if(!comboparmagasin.getSelectedItem().equals(magasin.getLibelle()))
				     {
				    	JOptionPane.showMessageDialog(null, "Veuillez selectionner le meme magasin d'Article SVP","Erreur",JOptionPane.ERROR_MESSAGE); 
				    	return;
				     }
				         
				         for(int i=0;i<listdetailprixarticle.size();i++)
				         {
				        	 DetailPrixArticle detailPrixArticle =listdetailprixarticle.get(i);
				        
				        	 
				           	if(clientPF!=null)
				        	{
				        		
				        	if(detailPrixArticle.getClientPF()!=null)
				        	{
				        		
				        		 if(detailPrixArticle.getArticles().getLiblle().equals(articleTMP.getLiblle()) && detailPrixArticle.getArticles().getCodeArticle().equals(articleTMP.getCodeArticle()) && detailPrixArticle.getMagasin().equals(magasin) && detailPrixArticle.getFamilleArticlePF().equals(familleArticlePF) && detailPrixArticle.getSousFamilleArticlePF().equals(sousFamilleArticlePF) && detailPrixArticle.getClientPF().getId()==clientPF.getId())
					        	 {
					        		 trouve=true;
					        	 }
				        		
				        		
				        	}
				        		
				        		
				        		
				        	}else
				        	{
				        		 if(detailPrixArticle.getArticles().getLiblle().equals(articleTMP.getLiblle()) && detailPrixArticle.getArticles().getCodeArticle().equals(articleTMP.getCodeArticle()) && detailPrixArticle.getMagasin().equals(magasin) && detailPrixArticle.getFamilleArticlePF().equals(familleArticlePF) && detailPrixArticle.getSousFamilleArticlePF().equals(sousFamilleArticlePF) && detailPrixArticle.getClientPF()==null)
					        	 {
					        		 trouve=true;
					        	 }
				        	}
				        	 
				        	 
				        	 
				        	 
				        	 
				        	 
				         }
				         
				         if(trouve==false)
				         {

				        	 DetailPrixArticle detailPrixArticle=new DetailPrixArticle();
				        	 detailPrixArticle.setArticles(articleTMP);
				        	 detailPrixArticle.setDepot(depot);
				        	 detailPrixArticle.setMagasin(magasin);
				        	 detailPrixArticle.setPrix(new BigDecimal(txtprix.getText()));
				        	 detailPrixArticle.setFamilleArticlePF(familleArticlePF);
				        	 detailPrixArticle.setSousFamilleArticlePF(sousFamilleArticlePF);
				        	 if(clientPF!=null)
				        	 {
				        		 detailPrixArticle.setClientPF(clientPF); 
				        	 }
				        	 
				        	 detailPrixArticleDAO.add(detailPrixArticle);
				             listdetailprixarticle.add(detailPrixArticle);
				             afficher_tablePrixArticle(listdetailprixarticle);
				             vider_PrixArticle();
				         
				         }else
					       {
					    	   JOptionPane.showMessageDialog(null, "Article déja existant Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    	   return;
					       }
					       
				         }
				        
						
					
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix  doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			
			
				
				
			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAjouter.setBounds(762, 60, 107, 24);
		layeredPane_2.add(btnAjouter);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(36, 323, 1410, 160);
		add(scrollPane_1);
		
		tablePrixArticle = new JTable();
		tablePrixArticle.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Article", "Famille","Sous Famille", "Depot", "Magasin", "Prix","Client PF"
			}
		));
		tablePrixArticle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(tablePrixArticle.getSelectedRow()!=-1)
				{
					
					combodepot.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 3));
					combomagasin.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 4));
					txtprix.setText(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 5).toString());
					combofamille.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 1));
					combosousfamille.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 2));
					
					if(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 6)!=null)
					{
						comboClientpf.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 6).toString());
					}else
					{
						comboClientpf.setSelectedIndex(-1);;
						
					}
					 btnAjouter.setEnabled(false);
					 btnModifier.setEnabled(true);
					 btnSupprimer.setEnabled(true);
					
				}
				
				
				
			}
		});
		tablePrixArticle.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tablePrixArticle);
		
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	comboPardepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(Constantes.MAGASIN_CODE_TYPE_MP_ATT);
		     		if(magasin!=null)
		     		{
		     			if(depot.getId()!=magasin.getDepot().getId())
			     		{
			     			combodepot.addItem(depot.getLibelle());
				     		comboPardepot.addItem(depot.getLibelle());
				     		mapDepot.put(depot.getLibelle(), depot);
				     	
				     		
			     			
			     		}
		     		}else
		     		{
		     			
		     			combodepot.addItem(depot.getLibelle());
		     			comboPardepot.addItem(depot.getLibelle());
			     		mapDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem("");
			     	comboPardepot.addItem("");
			     	
				  combodepot.addItem(depot.getLibelle());
				  comboPardepot.addItem(depot.getLibelle());
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		
		combodepot.setSelectedIndex(-1);	
		
		JLabel label_8 = new JLabel("Famille Article :");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_8.setBounds(638, 11, 87, 24);
		layeredPane_2.add(label_8);
		
		 combofamille = new JComboBox();
		 combofamille.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		

           		
          		 if(e.getStateChange() == ItemEvent.SELECTED)
{
          			 if(!combofamille.getSelectedItem().equals(""))
          			 {
          				 
          					FamilleArticlePF famille=mapFamilleArticle.get(combofamille.getSelectedItem());
		if(famille!=null)
		{
			 combosousfamille.removeAllItems();
			 combosousfamille.addItem("");
			 listSousFamilleArticlePF=sousfamilearticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
			for(int i=0;i<listSousFamilleArticlePF.size();i++)
			{
			SousFamilleArticlePF sousfamille=listSousFamilleArticlePF.get(i);
			combosousfamille.addItem(sousfamille.getLiblle());
			mapSousFamilleArticle.put(sousfamille.getLiblle(), sousfamille);
				
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
		combofamille.setBounds(729, 12, 167, 24);
		layeredPane_2.add(combofamille);
		
		JLabel label_9 = new JLabel("Sous Famille :");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_9.setBounds(906, 11, 87, 24);
		layeredPane_2.add(label_9);
		
		 combosousfamille = new JComboBox();
		combosousfamille.setBounds(980, 12, 167, 24);
		layeredPane_2.add(combosousfamille);
		
		label_12 = new JLabel("Client :");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_12.setBounds(1157, 7, 56, 24);
		layeredPane_2.add(label_12);
		
		comboClientpf = new JComboBox();
		comboClientpf.setSelectedIndex(-1);
		comboClientpf.setBounds(1223, 12, 247, 24);
		layeredPane_2.add(comboClientpf);
		
		listGrammageBox=grammageBoxDAO.findAll();
    	listGrammageCarton=grammageCartonDAO.findAll();
	  
	  for(int j=0;j<listGrammageBox.size() ; j++)
	  {
		  
		  GrammageBox gramaBox=listGrammageBox.get(j);
		  combogrammagebox.addItem(gramaBox.getCodeGrammage());
		  mapGrammageBox.put(gramaBox.getCodeGrammage(), gramaBox.getGrammageBox());
		  mapGrammageBoxbyGramme.put(gramaBox.getGrammageBox().setScale(2, RoundingMode.HALF_UP), gramaBox.getCodeGrammage());
	  }
	  combogrammagebox.setSelectedIndex(-1);
	  
	  
	  for(int k=0;k<listGrammageCarton.size() ; k++)
	  {
		  
		  GrammageCarton gramacaCarton=listGrammageCarton.get(k);
		  combogrammagecarton.addItem(gramacaCarton.getCodeGrammage());
		  mapGrammageCarton.put(gramacaCarton.getCodeGrammage(), gramacaCarton.getGrammageCarton());
		  mapGrammageCartonbyGramme.put(gramacaCarton.getGrammageCarton().setScale(2, RoundingMode.HALF_UP), gramacaCarton.getCodeGrammage());
	  }
	  combogrammagecarton.setSelectedIndex(-1);
	  
	  txtConditionnement = new JTextField();
	  txtConditionnement.setColumns(10);
	  txtConditionnement.setBounds(99, 47, 108, 26);
	  layeredPane.add(txtConditionnement);
	  
	  JLabel lblConditionnement = new JLabel("Conditionnement :");
	  lblConditionnement.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  lblConditionnement.setBounds(10, 47, 105, 24);
	  layeredPane.add(lblConditionnement);
	  
	  checkBoxPromo = new JCheckBox("Promo");
	  checkBoxPromo.setFont(new Font("Tahoma", Font.BOLD, 12));
	  checkBoxPromo.setBounds(739, 50, 71, 23);
	  layeredPane.add(checkBoxPromo);
	  
	  checkMConsommable = new JCheckBox("MC");
	  checkMConsommable.setFont(new Font("Tahoma", Font.BOLD, 12));
	  checkMConsommable.setBounds(1327, 13, 77, 23);
	  layeredPane.add(checkMConsommable);
	  
	  label_10 = new JLabel("Conditionnement Caisse :");
	  label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  label_10.setBounds(217, 49, 140, 24);
	  layeredPane.add(label_10);
	  
	  txtConditionnementCaisse = new JTextField();
	  txtConditionnementCaisse.setColumns(10);
	  txtConditionnementCaisse.setBounds(344, 49, 95, 24);
	  layeredPane.add(txtConditionnementCaisse);
	  
	  label_11 = new JLabel("Prix Achat :");
	  label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
	  label_11.setBounds(449, 48, 75, 24);
	  layeredPane.add(label_11);
	  
	  txtprixachat = new JTextField();
	  txtprixachat.setColumns(10);
	  txtprixachat.setBounds(518, 48, 140, 24);
	  layeredPane.add(txtprixachat);
	  
	  combogrammagebox.setVisible(false);
			combogrammagecarton.setVisible(false);
			lblGrammageCarton.setVisible(false);
		lblGrammageBox.setVisible(false);
		checkBoxPromo.setVisible(false);
		
		
		listFamilleArticlePF=famillearticleDAO.findAll();
		
				  	for(int i=0;i<listFamilleArticlePF.size();i++)
				  	{
				  		FamilleArticlePF familleArticlePF=listFamilleArticlePF.get(i);
				  		mapFamilleArticle.put(familleArticlePF.getLiblle(), familleArticlePF);
				  		combofamille.addItem(familleArticlePF.getLiblle());
				  	}
				  	
				 
				 	
	}
	
	void theselectionner()
	{
		if(chckbxTh.isSelected()==true)
	  		{
	  			combogrammagebox.setVisible(true);
	  			combogrammagecarton.setVisible(true);
	  			lblGrammageCarton.setVisible(true);
	  			checkBoxPromo.setVisible(true);
	  		lblGrammageBox.setVisible(true);
	  		
	  		}else
	  		{
	  		combogrammagebox.setVisible(false);
			combogrammagecarton.setVisible(false);
			lblGrammageCarton.setVisible(false);
			checkBoxPromo.setVisible(false);
		lblGrammageBox.setVisible(false);
	  		}
	}
	
	 void ChargerComboArticle()
	 {
		 comboBox.removeAllItems();
		 comboBox.addItem("");
		   listArticles=articlesDAO.findAll();
 	        int i=0;
 		      	while(i<listArticles.size())
 		      		{	
 		      			Articles article=listArticles.get(i);
 		      			mapCodeArticle.put(article.getLiblle(), article.getCodeArticle());
 		      			mapLibelleAricle.put( article.getCodeArticle(),article.getLiblle());
 		      			mapAricle.put(article.getLiblle(), article);
 		      			comboBox.addItem(article.getLiblle());
 		      			i++;
 		      		}
 			 
		 
		 
		 
	 }
	 void vider_PrixArticle()
	 {
		 combodepot.setSelectedIndex(-1);
		 combomagasin.setSelectedIndex(-1);
		 combofamille.setSelectedIndex(-1);
		 combosousfamille.setSelectedIndex(-1);
		 comboClientpf.setSelectedIndex(-1);
		 txtprix.setText("");
		 btnAjouter.setEnabled(true);
		 btnModifier.setEnabled(false);
		 btnSupprimer.setEnabled(false);
	 }
	
	void initialiserMP()
	{
		 btnsupprimer.setEnabled(false);
			btnmodifier.setEnabled(false);
			btnajouter.setEnabled(true);
			txtcodematiere.setText("");
			txtlibelle.setText("");
			txtquantite.setText("");
			txtpriorite.setText("");
			txtcodematiere.requestFocus();
			txtcodematiere.setEditable(true);
	}
	
	void intialiser()
	{
		
txtCode.setText("");
comboBox.setSelectedItem("");	
txtlibelleArticle.setText("");
checkpacket.setSelected(false);
checkBoxPromo.setSelected(false);
txtConditionnement.setText("");
txtConditionnementCaisse.setText("");
combogrammagebox.setSelectedIndex(-1);
combogrammagecarton.setSelectedIndex(-1);
txtprixachat.setText("");
chckbxTh.setSelected(false);
theselectionner();
		
	}
	
	void afficher_tableMP(List<DetailEstimation> listDetailEstimation)
	{
		 DecimalFormat format = new DecimalFormat("#0.000000");
		

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP", "Quantité Estimé","Priorité"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleMP); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
	  		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listDetailEstimation.size())
			{	
				
				DetailEstimation detailEstimation=listDetailEstimation.get(i);
				
				Object []ligne={detailEstimation.getMatierePremier().getCode(),detailEstimation.getMatierePremier().getNom(),format.format(detailEstimation.getQuantite()),detailEstimation.getPriorite()};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	
	
	void afficher_tablePrixArticle(List<DetailPrixArticle> listDetailPrixArticle)
	{

		

		modeleArticle =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Article", "Famille","Sous Famille", "Depot", "Magasin", "Prix","Client PF"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		 tablePrixArticle.setModel(modeleArticle); 
	  		tablePrixArticle.getColumnModel().getColumn(0).setPreferredWidth(10);
	  		tablePrixArticle.getColumnModel().getColumn(1).setPreferredWidth(260);
	  		tablePrixArticle.getColumnModel().getColumn(2).setPreferredWidth(160);
	        tablePrixArticle.getColumnModel().getColumn(3).setPreferredWidth(160);
	        tablePrixArticle.getColumnModel().getColumn(4).setPreferredWidth(160);
	        tablePrixArticle.getColumnModel().getColumn(5).setPreferredWidth(160);
	        tablePrixArticle.getColumnModel().getColumn(6).setPreferredWidth(160);
		  int i=0;
			while(i<listDetailPrixArticle.size())
			{	
				
				DetailPrixArticle detailprixarticle=listDetailPrixArticle.get(i);
				if(detailprixarticle.getClientPF()!=null)
				{
					Object []ligne={detailprixarticle.getArticles().getLiblle(),detailprixarticle.getFamilleArticlePF().getLiblle(),detailprixarticle.getSousFamilleArticlePF().getLiblle(),detailprixarticle.getDepot().getLibelle(),detailprixarticle.getMagasin().getLibelle(),detailprixarticle.getPrix(),detailprixarticle.getClientPF().getNom()};

					modeleArticle.addRow(ligne);
				}else
				{
					Object []ligne={detailprixarticle.getArticles().getLiblle(),detailprixarticle.getFamilleArticlePF().getLiblle(),detailprixarticle.getSousFamilleArticlePF().getLiblle(),detailprixarticle.getDepot().getLibelle(),detailprixarticle.getMagasin().getLibelle(),detailprixarticle.getPrix()};

					modeleArticle.addRow(ligne);
				}
				
				i++;
			}
	}
	
	
	
	

boolean remplirMapQuantiteEstimation(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 2).toString().equals("") ){
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 2).toString());
			
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailEstimation> remplirDetailEstimation(List<DetailEstimation> listDetailEstimation){
	BigDecimal quantite=BigDecimal.ZERO;

	
	List<DetailEstimation> listDetailEstimationTmp= new ArrayList<DetailEstimation>();
	
	
	for(int i=0;i<listDetailEstimation.size();i++){
		
		DetailEstimation detailEstimation=listDetailEstimation.get(i);
	
		
		quantite=new BigDecimal(mapQuantiteMP.get(detailEstimation.getMatierePremier().getCode()));
		
		detailEstimation.setQuantite(quantite);
		
		listDetailEstimationTmp.add(detailEstimation);
	}
	
	
	return listDetailEstimationTmp;
	
}
}
