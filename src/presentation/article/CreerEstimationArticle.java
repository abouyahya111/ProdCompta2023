package presentation.article;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.GrammageCartonDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
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
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.FactureAchatMP;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.Component;

import javax.swing.JTable;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JCheckBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class CreerEstimationArticle extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleArticle;
	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgAjouter;
	private JButton btnIntialiserOF;
	private Utilisateur utilisateur;
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<DetailPrixArticle> listdetailprixarticle =new ArrayList<DetailPrixArticle>();
	private List<DetailPrixArticle> listdetailprixarticleTmp =new ArrayList<DetailPrixArticle>();
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, String> mapPrixMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map< String, FamilleArticlePF> mapFamilleArticle = new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapSousFamilleArticle = new HashMap<>();
	private List<FamilleArticlePF> listFamilleArticlePF =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticlePF =new ArrayList<SousFamilleArticlePF>();
	private List<GrammageBox> listGrammageBox =new ArrayList<GrammageBox>();
	private List<GrammageCarton> listGrammageCarton =new ArrayList<GrammageCarton>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, BigDecimal> mapGrammageBox= new HashMap<>();
	private Map< String, BigDecimal> mapGrammageCarton= new HashMap<>();
	private ClientPFDAO clientpfdao;
	private  FamilleArticlesPFDAO famillearticleDAO;
	private SousFamilleArticlesPFDAO sousfamilearticleDAO;	
	private JComboBox combomagasin = new JComboBox();
	private JLabel lblDpotDestination;
	private JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesDAO articlesDAO;
	private GrammageBoxDAO grammageBoxDAO;
	private GrammageCartonDAO grammageCartonDAO;
	private	Articles article =new Articles ();
	private JTextField txtCode;
	private JTextField txtLibelle;
	private JTextField txtConditionnement;
	private JXTitledSeparator titledSeparator_1;
	private JTable tablePrixArticle;
	private JTextField txtPrix;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private DetailPrixArticleDAO detailPrixArticleDAO;
	private   JCheckBox checkPacket = new JCheckBox("Packet");
	JComboBox combogrammagebox = new JComboBox();
	 JComboBox combogrammagecarton = new JComboBox();
	  JLabel lblGrammageBox = new JLabel("Grammage Box :");
	  JLabel lblGrammageCarton = new JLabel("Grammage carton :");
	  private DetailEstimationDAO detailestimationdao;
	  JComboBox combofamille = new JComboBox();
	  JComboBox combosousfamille = new JComboBox();
	  JCheckBox checkBoxPromo = new JCheckBox("Promo");
	  JCheckBox checkMConsommable = new JCheckBox("MC");
	  private JTextField txtConditionnementCaisse;
	  private JTextField txtprixachat;
	  private Map< String, ClientPF> mapClientPF= new HashMap<>();
		private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
		JComboBox comboClientpf = new JComboBox();
		private JTextField txtlien;
		private JFrame mainFrame;
		
		private static XSSFSheet ExcelWSheet;
	    private static XSSFWorkbook ExcelWBook;
	    private static XSSFCell CellCodeArticle;
	    private static XSSFCell CellLibelle;
	    private static XSSFCell CellConditionnement;
	    private static XSSFCell CellGrammageBox;
	    private static XSSFCell CellGrammageCarton;
	     private SequenceurDAO sequenceurDAO;
		
		
		
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public CreerEstimationArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1492, 858);
        try{
        	
        	
        	utilisateur=AuthentificationView.utilisateur;
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	articlesDAO=new ArticlesDAOImpl();
        	depotdao=new DepotDAOImpl();
        	grammageBoxDAO=new GrammageBoxDAOImpl();
        	grammageCartonDAO=new GrammageCartonDAOImpl();
        	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
        	detailestimationdao=new DetailEstimationDAOImpl();
        	famillearticleDAO=new FamilleArticlesPFDAOImpl();
        	sousfamilearticleDAO=new SousFamilleArticlesPFDAOImpl();
        	clientpfdao=new ClientPFDAOImpl();
        	sequenceurDAO=new SequenceurDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(422, 824, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table = new JXTable();
				  		     table.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {

				  		     	
				  		    
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		   table.getDefaultEditor(String.class).addCellEditorListener(
				  	                new CellEditorListener() {
				  	                 
										@Override
										public void editingCanceled(
												ChangeEvent arg0) {

										}

										@Override
										public void editingStopped(
												ChangeEvent arg0) {
											if(!table.getValueAt(table.getSelectedRow(), 2).equals(""))
											{

//JOptionPane.showMessageDialog(null, table.getValueAt(table.getSelectedRow(), 2));
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
				  	        JTextComponent textField = (JTextComponent) ce.getComponent();
				  	        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(10, 507, 886, 306);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(10, 475, 886, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 1473, 124);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Article");
				  		     	lblMachine.setBounds(10, 35, 67, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(190, 34, 77, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  txtCode = new JTextField();
				  		  txtCode.setEditable(false);
				  		  
				  		util.Utils.copycoller(txtCode);
				  		  txtCode.setBounds(73, 35, 107, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		  txtLibelle = new JTextField();
				  		util.Utils.copycoller(txtLibelle);
				  		  txtLibelle.setColumns(10);
				  		  txtLibelle.setBounds(265, 35, 242, 24);
				  		  layeredPane.add(txtLibelle);
				  		  
				  		  JLabel lblCon = new JLabel("Conditionnement");
				  		  lblCon.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblCon.setBounds(511, 35, 95, 24);
				  		  layeredPane.add(lblCon);
				  		  
				  		  txtConditionnement = new JTextField();
				  		util.Utils.copycoller(txtConditionnement);
				  		  txtConditionnement.setColumns(10);
				  		  txtConditionnement.setBounds(604, 35, 77, 24);
				  		  layeredPane.add(txtConditionnement);
				  		  
				  		   checkPacket = new JCheckBox("Packet");
				  		  checkPacket.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		  checkPacket.setBounds(778, 35, 77, 23);
				  		  layeredPane.add(checkPacket);
				  		  
				  		  JCheckBox chckbxTh = new JCheckBox("Th\u00E9");
				  		  chckbxTh.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
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
				  		  });
				  		  chckbxTh.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		  chckbxTh.setBounds(857, 35, 53, 23);
				  		  layeredPane.add(chckbxTh);
				  		  
				  		   lblGrammageBox = new JLabel("Grammage Box :");
				  		  lblGrammageBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblGrammageBox.setBounds(996, 35, 95, 24);
				  		  layeredPane.add(lblGrammageBox);
				  		  
				  		   lblGrammageCarton = new JLabel("Grammage carton :");
				  		  lblGrammageCarton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblGrammageCarton.setBounds(1226, 35, 95, 24);
				  		  layeredPane.add(lblGrammageCarton);
				  		  
				  		   combogrammagebox = new JComboBox();
				  		  combogrammagebox.setSelectedIndex(-1);
				  		  combogrammagebox.setBounds(1080, 37, 136, 24);
				  		  layeredPane.add(combogrammagebox);
				  		  
				  		   combogrammagecarton = new JComboBox();
				  		  combogrammagecarton.setSelectedIndex(-1);
				  		  combogrammagecarton.setBounds(1331, 37, 132, 24);
				  		  layeredPane.add(combogrammagecarton);
				  		
				  		 
		JButton btnValiderTransfer = new JButton("Ajouter Article");
		btnValiderTransfer.setIcon(imgAjouter);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					/*
					if(!remplirMapQuantiteEstimation())	{
						JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						*/
					
						if(txtCode.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Il faut saisir le code", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
							
						}else if(txtLibelle.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Il faut saisir le libelle", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
							
						} else if(txtConditionnement.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Il faut remplir le conditionnement", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
							
						}else if(txtprixachat.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Il faut saisir le prix d'achat SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
						}
												
						else
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
							
							
							Articles articleexiste=articlesDAO.findByCode(txtCode.getText());
							if(articleexiste!=null)
							{
								JOptionPane.showMessageDialog(null, "Article existe déja !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
								
							}else
							{
							if(listdetailprixarticle.size()!=0)
							{
								
								//	article =new Articles ();
								article.setDateCreation(new Date());
								article.setCodeArticle(txtCode.getText());
								article.setLiblle(txtLibelle.getText());
								article.setConditionnement(new BigDecimal(txtConditionnement.getText()));
								if(!txtConditionnementCaisse.getText().equals(""))
								{
																		
									article.setConditionnementcaisse(new BigDecimal(txtConditionnementCaisse.getText()));
									
								}else
								{
									article.setConditionnementcaisse(new BigDecimal(1));
								}
								
								article.setPrixAchat(new BigDecimal(txtprixachat.getText()));
								article.setDetailPrixArticle(listdetailprixarticle);
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
								if(checkPacket.isSelected()==true)
								{
									article.setCentreCout2(Constantes.ARTICLE_PACKET); // centrecout2 = packet ou piece
								}else
								{
									article.setCentreCout2(Constantes.ARTICLE_PIECE);
								}
								
								
								if(checkMConsommable.isSelected()==true)
								{
									
									article.setCodeFonction(Constantes.ARTICLE_CONSOMMABLE); // code fonction = Article Consommable
									
								}
								
								
								
								//article.setCreerPar(utilisateur);
								articlesDAO.add(article);
								if(remplirMapQuantiteEstimation())	{
									//article.setDetailEstimation(remplirDetailEstimation());
								
								
								
								for(int i=0;i<remplirDetailEstimation().size();i++)
								{
									
									DetailEstimation detailestimation=new DetailEstimation();
									detailestimation.setMatierePremier(remplirDetailEstimation().get(i).getMatierePremier());
				    				detailestimation.setQuantite(remplirDetailEstimation().get(i).getQuantite());	
				    				detailestimation.setPriorite(remplirDetailEstimation().get(i).getPriorite());
				    				detailestimation.setArticles(article);
				    				detailestimationdao.add(detailestimation);
									
								}
								}
								
							 
								
								JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                               
							 
                                
								intialiser();
								vider_PrixArticle();
								article=new Articles();
							
							}else
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir les prix d'Article SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
								return;
							}
								
						
							}
							
						}
						
						
					//}
					
					
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Erreur format nombre Veuillez respecter les cases au format nombre SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(254, 824, 158, 23);
		add(btnValiderTransfer);
		
		titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Les Prix d'Articles Par Magasin");
		titledSeparator_1.setBounds(10, 148, 1274, 30);
		add(titledSeparator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 304, 1472, 160);
		add(scrollPane_1);
		
		tablePrixArticle = new JTable();
		tablePrixArticle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(tablePrixArticle.getSelectedRow()!=-1)
				{
					combodepot.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 3));
					combomagasin.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 4));
					txtPrix.setText(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 5).toString());
					if(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 6)!=null)
					{
						comboClientpf.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 6).toString());
					}else
					{
						comboClientpf.setSelectedIndex(-1);;
						
					}
					
					combofamille.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 1));
					combosousfamille.setSelectedItem(tablePrixArticle.getValueAt(tablePrixArticle.getSelectedRow(), 2));
					
					 btnAjouter.setEnabled(false);
					 btnModifier.setEnabled(true);
					 btnSupprimer.setEnabled(true);
					
				}
				
				
			}
		});
		tablePrixArticle.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Article", "Famille","Sous Famille", "Depot", "Magasin", "Prix","Client PF"
			}
		));
		tablePrixArticle.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tablePrixArticle);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 180, 1472, 113);
		add(layeredPane_1);
		
		JLabel lblDepot = new JLabel("Depot");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDepot.setBounds(10, 23, 56, 24);
		layeredPane_1.add(lblDepot);
		
		JLabel lblMagasin = new JLabel("Magasin");
		lblMagasin.setBounds(232, 22, 64, 26);
		layeredPane_1.add(lblMagasin);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPrix.setBounds(527, 23, 32, 24);
		layeredPane_1.add(lblPrix);
		
		txtPrix = new JTextField();
		txtPrix.setColumns(10);
		txtPrix.setBounds(557, 23, 107, 24);
		layeredPane_1.add(txtPrix);
		
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
		combodepot.setBounds(54, 23, 168, 24);
		layeredPane_1.add(combodepot);
		
		 combomagasin = new JComboBox();
		combomagasin.setSelectedIndex(-1);
		combomagasin.setBounds(306, 23, 211, 24);
		layeredPane_1.add(combomagasin);
		
		 btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				try {
					

					boolean trouve=false;
				
					
				 if(txtCode.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(txtLibelle.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez saisir l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(txtConditionnement.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez saisir conditonnement SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					} 
					else if(txtPrix.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer le prix","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if( new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<0)
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
				         for(int i=0;i<listdetailprixarticle.size();i++)
				         {
				        	 if(i!=tablePrixArticle.getSelectedRow())
				        	 {
				        		 DetailPrixArticle detailPrixArticle =listdetailprixarticle.get(i);
				        		 
				        		 	if(clientPF!=null)
						        	{
						        		
						        	if(detailPrixArticle.getClientPF()!=null)
						        	{
						        		
						        		 if(   detailPrixArticle.getMagasin().equals(magasin) && detailPrixArticle.getFamilleArticlePF().equals(familleArticlePF) && detailPrixArticle.getSousFamilleArticlePF().equals(sousFamilleArticlePF) && detailPrixArticle.getClientPF().getId()==clientPF.getId())
							        	 {
							        		 trouve=true;
							        	 }
						        		
						        		
						        	}
						        		
						        		
						        		
						        	}else if( detailPrixArticle.getMagasin().equals(magasin)  && detailPrixArticle.getFamilleArticlePF().equals(familleArticlePF) && detailPrixArticle.getSousFamilleArticlePF().equals(sousFamilleArticlePF) &&  detailPrixArticle.getClientPF().equals(null))
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
				        	 detailPrixArticle.setPrix(new BigDecimal(txtPrix.getText()));
				        	 detailPrixArticle.setFamilleArticlePF(familleArticlePF);
				        	 if(clientPF!=null)
				        	 {
				        		 detailPrixArticle.setClientPF(clientPF);
				        	 }else
				        	 {
				        		 detailPrixArticle.setClientPF(null);
				        	 }
				        	 detailPrixArticle.setSousFamilleArticlePF(sousFamilleArticlePF);
				             listdetailprixarticle.set(tablePrixArticle.getSelectedRow(),detailPrixArticle);
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
		btnModifier.setBounds(344, 67, 107, 24);
		layeredPane_1.add(btnModifier);
		
		JButton button_1 = new JButton("Initialiser");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				vider_PrixArticle();
				
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(476, 68, 106, 23);
		layeredPane_1.add(button_1);
		
		 btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablePrixArticle.getSelectedRow()!=-1)
				{
					
					listdetailprixarticle.remove(tablePrixArticle.getSelectedRow());
					 afficher_tablePrixArticle(listdetailprixarticle);
					   vider_PrixArticle();
					
				}
				
				
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBounds(612, 67, 107, 24);
		layeredPane_1.add(btnSupprimer);
		
		 btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				
				
				try {
					

					boolean trouve=false;
				
					
				 if(txtCode.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(txtLibelle.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez saisir l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(txtConditionnement.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez saisir conditonnement SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					} 
					else if(txtPrix.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer le prix","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<0)
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
						  FamilleArticlePF familleArticlePF=mapFamilleArticle.get(combofamille.getSelectedItem());
						     SousFamilleArticlePF sousFamilleArticlePF=mapSousFamilleArticle.get(combosousfamille.getSelectedItem());
						 if(articleTMP!=null)
						 {
							  JOptionPane.showMessageDialog(null, "Article déja existant SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    	   return;
						 }
				        
				         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				      Depot depot=mapDepot.get(combodepot.getSelectedItem());
				      
				      ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
				         for(int i=0;i<listdetailprixarticle.size();i++)
				         {
				        	 DetailPrixArticle detailPrixArticle =listdetailprixarticle.get(i);
				        	if(clientPF!=null)
				        	{
				        		
				        	if(detailPrixArticle.getClientPF()!=null)
				        	{
				        		
				        		 if(detailPrixArticle.getMagasin().equals(magasin) && detailPrixArticle.getFamilleArticlePF().equals(familleArticlePF) && detailPrixArticle.getSousFamilleArticlePF().equals(sousFamilleArticlePF) && detailPrixArticle.getClientPF().getId()==clientPF.getId())
					        	 {
					        		 trouve=true;
					        	 }
				        		
				        		
				        	}
				        		
				        		
				        		
				        	}else
				        	{
				        		 if(detailPrixArticle.getMagasin().equals(magasin) && detailPrixArticle.getFamilleArticlePF().equals(familleArticlePF) && detailPrixArticle.getSousFamilleArticlePF().equals(sousFamilleArticlePF) && detailPrixArticle.getClientPF()==null)
					        	 {
					        		 trouve=true;
					        	 }
				        	}
				        	
				         }
				         
				         if(trouve==false)
				         {

				        	 DetailPrixArticle detailPrixArticle=new DetailPrixArticle();
				        	 detailPrixArticle.setArticles(article);
				        	 detailPrixArticle.setDepot(depot);
				        	 detailPrixArticle.setMagasin(magasin);
				        	 detailPrixArticle.setFamilleArticlePF(familleArticlePF);
				        	 detailPrixArticle.setSousFamilleArticlePF(sousFamilleArticlePF);
				        	 if(clientPF!=null)
				        	 {
				        		 detailPrixArticle.setClientPF(clientPF); 
				        	 }
				        	 detailPrixArticle.setPrix(new BigDecimal(txtPrix.getText()));
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
		btnAjouter.setBounds(736, 67, 107, 24);
		layeredPane_1.add(btnAjouter);
		List<MatierePremier> listMatierePremier=matierePremiereDAO.findAll();
		afficher_tableMP(listMatierePremier);
		
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(Constantes.MAGASIN_CODE_TYPE_MP_ATT);
		     		if(magasin!=null)
		     		{
		     			if(depot.getId()!=magasin.getDepot().getId())
			     		{
			     			combodepot.addItem(depot.getLibelle());
				     		
				     		mapDepot.put(depot.getLibelle(), depot);
				     	
			     		}
		     		}else
		     		{
		     			combodepot.addItem(depot.getLibelle());
			     		
			     		mapDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JLabel label = new JLabel("Famille Article :");
		  label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label.setBounds(674, 22, 87, 24);
		  layeredPane_1.add(label);
		  
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
		  combofamille.setBounds(765, 23, 134, 24);
		  layeredPane_1.add(combofamille);
		  
		  JLabel label_1 = new JLabel("Sous Famille :");
		  label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label_1.setBounds(909, 22, 87, 24);
		  layeredPane_1.add(label_1);
		  
		   combosousfamille = new JComboBox();
		  combosousfamille.setBounds(983, 23, 154, 24);
		  layeredPane_1.add(combosousfamille);
		  
		  JLabel label_2 = new JLabel("Client :");
		  label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label_2.setBounds(1149, 23, 56, 24);
		  layeredPane_1.add(label_2);
		  
		   comboClientpf = new JComboBox();
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(1215, 28, 247, 24);
		  AutoCompleteDecorator.decorate(comboClientpf);
		  layeredPane_1.add(comboClientpf);
			listGrammageBox=grammageBoxDAO.findAll();
        	listGrammageCarton=grammageCartonDAO.findAll();
		  
		  for(int j=0;j<listGrammageBox.size() ; j++)
		  {
			  
			  GrammageBox gramaBox=listGrammageBox.get(j);
			  combogrammagebox.addItem(gramaBox.getCodeGrammage());
			  mapGrammageBox.put(gramaBox.getCodeGrammage(), gramaBox.getGrammageBox());
			  
		  }
		  combogrammagebox.setSelectedIndex(-1);
		  
		  
		  for(int k=0;k<listGrammageCarton.size() ; k++)
		  {
			  
			  GrammageCarton gramacaCarton=listGrammageCarton.get(k);
			  combogrammagecarton.addItem(gramacaCarton.getCodeGrammage());
			  mapGrammageCarton.put(gramacaCarton.getCodeGrammage(), gramacaCarton.getGrammageCarton());
			  
		  }
		  combogrammagecarton.setSelectedIndex(-1);
		  
		   checkBoxPromo = new JCheckBox("Promo");
		  checkBoxPromo.setFont(new Font("Tahoma", Font.BOLD, 12));
		  checkBoxPromo.setBounds(912, 35, 77, 23);
		  layeredPane.add(checkBoxPromo);
		  
		   checkMConsommable = new JCheckBox("MC");
		  checkMConsommable.setFont(new Font("Tahoma", Font.BOLD, 12));
		  checkMConsommable.setBounds(687, 36, 77, 23);
		  layeredPane.add(checkMConsommable);
		  
		  JLabel lblConditionnementCaisse = new JLabel("Conditionnement Caisse :");
		  lblConditionnementCaisse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  lblConditionnementCaisse.setBounds(10, 89, 140, 24);
		  layeredPane.add(lblConditionnementCaisse);
		  
		  txtConditionnementCaisse = new JTextField();
		  txtConditionnementCaisse.setColumns(10);
		  txtConditionnementCaisse.setBounds(160, 89, 95, 24);
		  layeredPane.add(txtConditionnementCaisse);
		  
		  JLabel lblPrixAchat = new JLabel("Prix Achat :");
		  lblPrixAchat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  lblPrixAchat.setBounds(270, 89, 75, 24);
		  layeredPane.add(lblPrixAchat);
		  
		  txtprixachat = new JTextField();
		  txtprixachat.setColumns(10);
		  txtprixachat.setBounds(339, 89, 140, 24);
		  layeredPane.add(txtprixachat);
		  
		  JButton button = new JButton("Lire Fichier excel");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		

        		
        		 
        		
        		if(!txtlien.getText().equals(""))
  				{
        			
   				 File fileName = new File(txtlien.getText());
	  				
  				 
	  				try {
	  					
	  			 
	  						
	  				       FileInputStream ExcelFile = new FileInputStream(fileName);
	  				      
	  				        
	  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
	  				       
	  				      ExcelWSheet = ExcelWBook.getSheetAt(0);
	  				      
	  				      
	  				      
	  				      
	  				      
	  				   
	  				      
	  				      
	  				      
	  				    
	  				      
int t=0;							  				      
	  				      
int id=0;		




for(int i=1;i<ExcelWSheet.getPhysicalNumberOfRows();i++)
{
	
	

try {
	
	 
	Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
	
	CellCodeArticle=ExcelWSheet.getRow(i).getCell(0);
	CellLibelle=ExcelWSheet.getRow(i).getCell(1);
	CellConditionnement=ExcelWSheet.getRow(i).getCell(2);
	CellGrammageBox=ExcelWSheet.getRow(i).getCell(3);
	CellGrammageCarton=ExcelWSheet.getRow(i).getCell(4);
	 
	 
	
	DataFormatter dataFormatter = new DataFormatter();
	String formattedCellStr = dataFormatter.formatCellValue(CellCodeArticle);
	String formattedCellStrCarton = dataFormatter.formatCellValue(CellGrammageCarton);
	String formattedCellStrBox = dataFormatter.formatCellValue(CellGrammageBox);
	
 if(formattedCellStr!=null && formattedCellStr.isEmpty()==false && !formattedCellStr.equals(""))
 {
	  
		//	article =new Articles ();
	 
	 Articles articles=new Articles();
	 articles.setDateCreation(new Date());
	 articles.setCodeArticle(formattedCellStr);
	 articles.setLiblle(CellLibelle.getStringCellValue().toString());
	 articles.setConditionnement(new BigDecimal(CellConditionnement.getNumericCellValue()));
	 articles.setConditionnementcaisse(BigDecimal.ZERO);
	 articles.setPrixAchat(BigDecimal.ZERO);
	 
	 if(formattedCellStrCarton!=null && formattedCellStrCarton.isEmpty()==false && !formattedCellStrCarton.equals(""))
	 {
		 articles.setCentreCout3(new BigDecimal( CellGrammageCarton.getNumericCellValue()));
	 }
	 if(formattedCellStrBox!=null && formattedCellStrBox.isEmpty()==false && !formattedCellStrBox.equals(""))
	 {
		 articles.setCentreCout1(new BigDecimal( CellGrammageBox.getNumericCellValue())); // grammage box 

	 }
	 articles.setCentreCout5(BigDecimal.ZERO);
	 articles.setCentreCout2(Constantes.ARTICLE_PIECE);
     articlesDAO.add(articles);
 
	
	
	 


 }
 
	



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


t=t+1;


 




} catch (Exception ex) {
// TODO Auto-generated catch block
JOptionPane.showMessageDialog(null, "Erreur Dans La Ligne : "+(i+1)); 
JOptionPane.showMessageDialog(null, ex.getMessage()); 






return;
}









}



	
	  							
	  							
	  						
	  					
	  					
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						  					
	  					
	  					
	  					JOptionPane.showMessageDialog(null, "OK");
	  					
						
						
						
						
					} catch (Exception exx) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, exx.getMessage()); 
					}
        			
        			
        			
  				}
        		
        		 	
        	
		  		
		  		
		  		
		  		
		  	}
		  });
		  button.setBounds(690, 837, 129, 36);
		  add(button);
		  button.setVisible(false);
		  txtlien = new JTextField();
		  txtlien.setEditable(false);
		  txtlien.setColumns(10);
		  txtlien.setBounds(869, 837, 459, 36);
		  add(txtlien);
		  txtlien.setVisible(false);
		  JFileChooser fileDialog = new JFileChooser();
		  JButton button_2 = new JButton("Ouvrir");
		  button_2.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		 
                int returnVal = fileDialog.showOpenDialog(mainFrame);
                File file = fileDialog.getSelectedFile();
               txtlien.setText(file.getAbsolutePath());
            
		  	}
		  });
		  button_2.setBounds(1338, 837, 89, 30);
		  add(button_2);
		  button_2.setVisible(false);
		  combogrammagebox.setVisible(false);
  			combogrammagecarton.setVisible(false);
  			lblGrammageCarton.setVisible(false);
  			checkBoxPromo.setVisible(false);
  		lblGrammageBox.setVisible(false);
  		listFamilleArticlePF=famillearticleDAO.findAll();
		
	  	for(int i=0;i<listFamilleArticlePF.size();i++)
	  	{
	  		FamilleArticlePF familleArticlePF=listFamilleArticlePF.get(i);
	  		mapFamilleArticle.put(familleArticlePF.getLiblle(), familleArticlePF);
	  		combofamille.addItem(familleArticlePF.getLiblle());
	  	} 	
	  	
	  	
	  	 
	}
	
	void vider_PrixArticle()
	{
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		 combofamille.setSelectedIndex(-1);
		 combosousfamille.setSelectedIndex(-1);
		 comboClientpf.setSelectedIndex(-1);
		txtPrix.setText("");
		 btnAjouter.setEnabled(true);
		 btnModifier.setEnabled(false);
		 btnSupprimer.setEnabled(false);
		
		
	}
	
	void intialiser()
	{
		//txtCode.setText("");
		txtConditionnement.setText("");
		txtLibelle.setText("");
		checkPacket.setSelected(false);
        checkBoxPromo.setSelected(false);
		combogrammagebox.setSelectedIndex(-1);
		combogrammagecarton.setSelectedIndex(-1);
		List<MatierePremier> listMatierePremier=matierePremiereDAO.findAll();
		afficher_tableMP(listMatierePremier);
		listdetailprixarticle.clear();
		vider_PrixArticle();
		afficher_tablePrixArticle(listdetailprixarticle);
		txtprixachat.setText("");
		txtConditionnementCaisse.setText("");
	}
	
	void afficher_tableMP(List<MatierePremier> listMatierePremier)
	{

		

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP", "Quantité Estimé"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,true
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		 table.setModel(modeleMP); 
	  		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
	         table.getColumnModel().getColumn(1).setPreferredWidth(260);
	         table.getColumnModel().getColumn(2).setPreferredWidth(160);
	        //table.getColumnModel().getColumn(3).setPreferredWidth(160);
	        //table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listMatierePremier.size())
			{	
				
				MatierePremier matierePremier=listMatierePremier.get(i);
				mapMatierePremierTmp.put(matierePremier.getCode(), matierePremier);
				Object []ligne={matierePremier.getCode(),matierePremier.getNom(),""};

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
	  		     			"Article", "Famille","Sous Famille", "Depot", "Magasin", "Prix" , "Client PF"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false
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
	        tablePrixArticle.getColumnModel().getColumn(5).setPreferredWidth(160);
		  int i=0;
			while(i<listDetailPrixArticle.size())
			{	
				
				DetailPrixArticle detailprixarticle=listDetailPrixArticle.get(i);
				if(detailprixarticle.getClientPF()!=null)
				{
					
					Object []ligne={detailprixarticle.getArticles().getLiblle() , detailprixarticle.getFamilleArticlePF().getLiblle() , detailprixarticle.getSousFamilleArticlePF().getLiblle() ,  detailprixarticle.getDepot().getLibelle(),detailprixarticle.getMagasin().getLibelle(),detailprixarticle.getPrix() , detailprixarticle.getClientPF().getNom()};

					modeleArticle.addRow(ligne);
					
				}else
				{
					Object []ligne={detailprixarticle.getArticles().getLiblle() , detailprixarticle.getFamilleArticlePF().getLiblle() , detailprixarticle.getSousFamilleArticlePF().getLiblle() ,  detailprixarticle.getDepot().getLibelle(),detailprixarticle.getMagasin().getLibelle(),detailprixarticle.getPrix()};

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
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}




List<DetailEstimation> remplirDetailEstimation(){
	BigDecimal quantite=BigDecimal.ZERO;

	
	List<DetailEstimation> listDetailEstimation= new ArrayList<DetailEstimation>();
	
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
		DetailEstimation detailEstimation=new DetailEstimation();
	
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal((mapQuantiteMP.get(matierePremier.getCode()))) ;
		
		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			detailEstimation.setPriorite(1);
		else 
			detailEstimation.setPriorite(0);
		
		detailEstimation.setQuantite(quantite);
		detailEstimation.setMatierePremier(matierePremier);
		detailEstimation.setArticles(article);
		
		listDetailEstimation.add(detailEstimation);
	}
	
	
	return listDetailEstimation;
	
}
}
